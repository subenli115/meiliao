package com.ziran.meiliao.im.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import cn.jpush.im.android.api.ContactManager;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;

import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdManager;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAppDownloadListener;
import com.bytedance.sdk.openadsdk.TTRewardVideoAd;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MeiliaoConfig;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.app.TTAdManagerHolder;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.im.database.FriendRecommendEntry;
import com.ziran.meiliao.im.database.UserEntry;
import com.ziran.meiliao.im.entity.FriendInvitation;
import com.ziran.meiliao.im.model.InfoModel;
import com.ziran.meiliao.im.utils.DialogCreator;
import com.ziran.meiliao.im.utils.ToastUtil;
import com.ziran.meiliao.ui.bean.StringDataV2Bean;
import com.ziran.meiliao.ui.bean.UserAccountBean;
import com.ziran.meiliao.ui.settings.activity.RechargeActivity;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.Utils;

import java.util.Map;

import static com.ziran.meiliao.constant.ApiKey.ADMIN_GIFTRECORD_ADD;
import static com.ziran.meiliao.constant.ApiKey.ADMIN_GIFTRECORD_REPEAT;

/**
 * Created by ${chenyn} on 2017/3/14.
 */

public class VerificationActivity extends BaseActivity implements View.OnClickListener{

    private EditText mEt_reason;
    private UserInfo mMyInfo;
    private String mTargetAppKey;
    private int gold;
    private boolean mHasShowDownloadActive = false;
    private TTRewardVideoAd mttRewardVideoAd;
    private View contentView;
    private AutoRelativeLayout autoRelativeLayout;
    private TTAdNative mTTAdNative;
    private VerificationActivity activity;
    private View contentView1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_add_friend_im);
        getUserAccount();
        initView();
        initData();
    }

    private void getUserAccount() {
        OkHttpClientManager.getDataOneHead(ApiKey.ACCOUNT_ACCOUNT_INFO, MyAPP.getUserId(),MyAPP.getAccessToken(), new
                NewRequestCallBack<UserAccountBean>(UserAccountBean.class) {
                    @Override
                    public void onSuccess(UserAccountBean result) {
                        gold = MyAPP.saveMoney(result);
                    }

                    @Override
                    public void onError(String msg, int code) {

                    }
                });

    }

    private void initData() {
        mEt_reason.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                if(gold>1000){
                    sendAddReason();
                }else {
                    showPopNoMoneyWindow();
                }
            }
            return false;
        });
        TextView tv_commit = (TextView) findViewById(R.id.tv_qd);
        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(gold>1000){
                    sendAddReason();

                }else {
                    showPopNoMoneyWindow();
                }
            }
        });
    }
    /**
     *
     * 送礼物
     * @param userName
     */
    private void GiveGift(String userName) {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("giveUserId", MyAPP.getUserId());
        defMap.put("receiveUserId", userName);
        defMap.put("giftId", "20");
        defMap.put("type","0");
        OkHttpClientManager.postAsyncAddHead(ADMIN_GIFTRECORD_ADD, defMap, "", new
                NewRequestCallBack<UserAccountBean>(UserAccountBean.class) {

                    @Override
                    public void onSuccess(UserAccountBean bean) {
                         gold = MyAPP.saveMoney(bean);
                    }

                    @Override
                    public void onError(String msg, int code) {
                        ToastUitl.showShort(msg);
                        return;
                    }
                });
    }

    /**
     *
     * 验证好友
     * @param displayName
     * @param userName
     * @param targetUid
     */
    private void giftrecordRepeat(String userName, String displayName, String targetAvatar, Long targetUid) {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("giveUserId", MyAPP.getUserId());
        defMap.put("receiveUserId", userName);
        OkHttpClientManager.postAsyncAddHead(ADMIN_GIFTRECORD_REPEAT, defMap, "", new
                NewRequestCallBack<StringDataV2Bean>(StringDataV2Bean.class) {

                    @Override
                    public void onSuccess(StringDataV2Bean bean) {
                        next(userName, displayName, targetAvatar, targetUid);
                    }

                    @Override
                    public void onError(String msg, int code) {
                        if(code==1003){
                            showPopAgainWindow(userName, displayName, targetAvatar, targetUid);
                        }

                    }
                });
    }

    private void showPopNoMoneyWindow() {
        // 一个自定义的布局，作为显示的内容
        int[] location = new int[2];
        contentView = LayoutInflater.from(getBaseContext()).inflate(R.layout.pop_no_enough_money, null);
        contentView.getLocationOnScreen(location);
        final PopupWindow popupWindow = new PopupWindow(contentView,
                AutoLinearLayout.LayoutParams.WRAP_CONTENT, AutoLinearLayout.LayoutParams.WRAP_CONTENT, true);

        popupWindow.setTouchable(true);
        //设置成不被键盘挡住
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setOutsideTouchable(true);// 设置同意在外点击消失
        popupWindow.setFocusable(true);// 点击空白处时，隐藏掉pop窗口
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.showAtLocation(autoRelativeLayout, Gravity.CENTER, 0, 0);

        TextView tvContent = contentView.findViewById(R.id.tv_update_version_content);
        TextView tvRecharge = contentView.findViewById(R.id.tv_recharge);
        TextView tvReceive = contentView.findViewById(R.id.tv_receive);
//        setBackgroundAlpha(0.5f);
        tvContent.setText("ML币不足，无法送礼物");
        autoRelativeLayout.setVisibility(View.GONE);
        tvRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autoRelativeLayout.setVisibility(View.VISIBLE);
                popupWindow.dismiss();

                RechargeActivity.startAction(getBaseContext(),"");
            }
        });
        tvReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFastLoadAdDoubleClick()) {
                    return;
                }
                autoRelativeLayout.setVisibility(View.VISIBLE);
                popupWindow.dismiss();
                loadAd(MeiliaoConfig.AdvertisementId, TTAdConstant.VERTICAL);
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                autoRelativeLayout.setVisibility(View.VISIBLE);
            }
        });
    }
    private static long lastClickTime;
    public static boolean isFastLoadAdDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if ( 0 < timeD && timeD < 4000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }


    private void showPopAgainWindow(String userName, String displayName, String targetAvatar, Long targetUid) {
        // 一个自定义的布局，作为显示的内容
        int[] location = new int[2];
        contentView1 = LayoutInflater.from(getBaseContext()).inflate(R.layout.pop_again_apply, null);
        contentView1.getLocationOnScreen(location);
        final PopupWindow popupWindow = new PopupWindow(contentView1,
                AutoLinearLayout.LayoutParams.MATCH_PARENT, AutoLinearLayout.LayoutParams.MATCH_PARENT, true);

        popupWindow.setTouchable(true);
        //设置成不被键盘挡住
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setOutsideTouchable(true);// 设置同意在外点击消失
        popupWindow.setFocusable(true);// 点击空白处时，隐藏掉pop窗口
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.showAtLocation(autoRelativeLayout, Gravity.CENTER, 0, 0);
        TextView tvqd = contentView1.findViewById(R.id.tv_qd1);
        TextView tvqx = contentView1.findViewById(R.id.tv_qx1);
//        setBackgroundAlpha(0.5f);
        autoRelativeLayout.setVisibility(View.GONE);
        tvqd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next(userName, displayName, targetAvatar, targetUid);
            }
        });
        tvqx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autoRelativeLayout.setVisibility(View.VISIBLE);
                popupWindow.dismiss();
            }
        });


        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                autoRelativeLayout.setVisibility(View.VISIBLE);
            }
        });
    }



    private void initVideo() {
        //step1:初始化sdk
        TTAdManager ttAdManager = TTAdManagerHolder.get();
        //step2:(可选，强烈建议在合适的时机调用):申请部分权限，如read_phone_state,防止获取不了imei时候，下载类广告没有填充的问题。
        TTAdManagerHolder.get().requestPermissionIfNecessary(this);
        //step3:创建TTAdNative对象,用于调用广告请求接口
        mTTAdNative = ttAdManager.createAdNative(getApplicationContext());
    }
    private void loadAd(final String codeId, int orientation) {
        //step4:创建广告请求参数AdSlot,具体参数含义参考文档
        AdSlot adSlot;
        if (true) {
            //个性化模板广告需要传入期望广告view的宽、高，单位dp，
            adSlot = new AdSlot.Builder()
                    .setCodeId(codeId)
                    .setSupportDeepLink(true)
                    .setRewardName("金币") //奖励的名称
                    .setRewardAmount(50)  //奖励的数量
                    //模板广告需要设置期望个性化模板广告的大小,单位dp,激励视频场景，只要设置的值大于0即可
                    .setExpressViewAcceptedSize(500,500)
                    .setUserID("user123")//用户id,必传参数
                    .setMediaExtra("media_extra") //附加参数，可选
                    .setOrientation(orientation) //必填参数，期望视频的播放方向：TTAdConstant.HORIZONTAL 或 TTAdConstant.VERTICAL
                    .build();
        } else {
            //模板广告需要设置期望个性化模板广告的大小,单位dp,代码位是否属于个性化模板广告，请在穿山甲平台查看
            adSlot = new AdSlot.Builder()
                    .setCodeId(codeId)
                    .setSupportDeepLink(true)
                    .setRewardName("金币") //奖励的名称
                    .setRewardAmount(50)  //奖励的数量
                    .setUserID("user123")//用户id,必传参数
                    .setMediaExtra("media_extra") //附加参数，可选
                    .setOrientation(orientation) //必填参数，期望视频的播放方向：TTAdConstant.HORIZONTAL 或 TTAdConstant.VERTICAL
                    .build();
        }


        //step5:请求广告
        mTTAdNative.loadRewardVideoAd(adSlot, new TTAdNative.RewardVideoAdListener() {
            @Override
            public void onError(int code, String message) {
            }

            //视频广告加载后，视频资源缓存到本地的回调，在此回调后，播放本地视频，流畅不阻塞。
            @Override
            public void onRewardVideoCached() {
                if (mttRewardVideoAd != null) {
                    //step6:在获取到广告后展示,强烈建议在onRewardVideoCached回调后，展示广告，提升播放体验
                    //该方法直接展示广告
//                    mttRewardVideoAd.showRewardVideoAd(RewardVideoActivity.this);

                    //展示广告，并传入广告展示的场景
                    mttRewardVideoAd.showRewardVideoAd(VerificationActivity.this, TTAdConstant.RitScenes.CUSTOMIZE_SCENES, "scenes_test");
                    mttRewardVideoAd = null;
                } else {
                    ToastUitl.showShort("请先加载广告");
                }
            }

            //视频广告的素材加载完毕，比如视频url等，在此回调后，可以播放在线视频，网络不好可能出现加载缓冲，影响体验。
            @Override
            public void onRewardVideoAdLoad(TTRewardVideoAd ad) {

                mttRewardVideoAd = ad;
                mttRewardVideoAd.setRewardAdInteractionListener(new TTRewardVideoAd.RewardAdInteractionListener() {

                    @Override
                    public void onAdShow() {
                    }

                    @Override
                    public void onAdVideoBarClick() {
                    }

                    @Override
                    public void onAdClose() {
                    }

                    //视频播放完成回调
                    @Override
                    public void onVideoComplete() {
                    }

                    @Override
                    public void onVideoError() {
                    }

                    //视频播放完成后，奖励验证回调，rewardVerify：是否有效，rewardAmount：奖励梳理，rewardName：奖励名称
                    @Override
                    public void onRewardVerify(boolean rewardVerify, int rewardAmount, String rewardName) {
                        putGoldResult();
                    }

                    @Override
                    public void onSkippedVideo() {
                    }
                });
                mttRewardVideoAd.setDownloadListener(new TTAppDownloadListener() {
                    @Override
                    public void onIdle() {
                        mHasShowDownloadActive = false;
                    }

                    @Override
                    public void onDownloadActive(long totalBytes, long currBytes, String fileName, String appName) {
                        Log.d("DML", "onDownloadActive==totalBytes=" + totalBytes + ",currBytes=" + currBytes + ",fileName=" + fileName + ",appName=" + appName);

                        if (!mHasShowDownloadActive) {
                            mHasShowDownloadActive = true;
                        }
                    }

                    @Override
                    public void onDownloadPaused(long totalBytes, long currBytes, String fileName, String appName) {
                        Log.d("DML", "onDownloadPaused===totalBytes=" + totalBytes + ",currBytes=" + currBytes + ",fileName=" + fileName + ",appName=" + appName);
                    }

                    @Override
                    public void onDownloadFailed(long totalBytes, long currBytes, String fileName, String appName) {
                        Log.d("DML", "onDownloadFailed==totalBytes=" + totalBytes + ",currBytes=" + currBytes + ",fileName=" + fileName + ",appName=" + appName);
                    }

                    @Override
                    public void onDownloadFinished(long totalBytes, String fileName, String appName) {
                        Log.d("DML", "onDownloadFinished==totalBytes=" + totalBytes + ",fileName=" + fileName + ",appName=" + appName);
                    }

                    @Override
                    public void onInstalled(String fileName, String appName) {
                        Log.d("DML", "onInstalled==" + ",fileName=" + fileName + ",appName=" + appName);

                    }
                });
            }
        });
    }
    private void putGoldResult() {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("reward","50");
        defMap.put("userId",MyAPP.getUserId());
        OkHttpClientManager.putAsyncAddHead(ApiKey.ACCOUNT_ADVERTISEMENTRECORD_RECEIVE, defMap, new
                NewRequestCallBack<UserAccountBean>(UserAccountBean.class) {
                    @Override
                    public void onSuccess(UserAccountBean result) {
                        showPopWindow();
                        if(result!=null){
                            gold = MyAPP.saveMoney(result);
                        }
                    }

                    @Override
                    public void onError(String msg, int code) {
                    }
                });
    }

    private void showPopWindow() {
        // 一个自定义的布局，作为显示的内容
        int[] location = new int[2];
        contentView = LayoutInflater.from(getBaseContext()).inflate(R.layout.pop_get_money_im, null);
        contentView.getLocationOnScreen(location);
        final PopupWindow popupWindow = new PopupWindow(contentView,
                AutoLinearLayout.LayoutParams.MATCH_PARENT, AutoLinearLayout.LayoutParams.MATCH_PARENT, true);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);// 设置同意在外点击消失
        popupWindow.setFocusable(true);// 点击空白处时，隐藏掉pop窗口
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.showAtLocation(autoRelativeLayout, Gravity.CENTER, 0, 0);
        TextView qd = contentView.findViewById(R.id.tv_qd);
//        setBackgroundAlpha(0.5f);
        qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();

            }
        });
//        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                setBackgroundAlpha(1.0f);
//            }
//        });
    }
    @SuppressLint("WrongConstant")
    private void sendAddReason() {
        final String userName;
        String displayName;
        String targetAvatar;
        Long targetUid;
        if (getIntent().getFlags() == 1) {
            //添加好友申请时对方信息
            userName = getIntent().getStringExtra("detail_add_friend");
            displayName = getIntent().getStringExtra("detail_add_nick_name");
            targetAvatar = getIntent().getStringExtra("detail_add_avatar_path");
            targetUid = getIntent().getLongExtra("detail_add_uid", 0);
            if (TextUtils.isEmpty(displayName)) {
                displayName = userName;
            }
            //搜索方式添加好友
        } else {
            targetAvatar = InfoModel.getInstance().getAvatarPath();
            displayName = InfoModel.getInstance().getNickName();
            targetUid = InfoModel.getInstance().getUid();
            if (TextUtils.isEmpty(displayName)) {
                displayName = InfoModel.getInstance().getUserName();
            }
            userName = InfoModel.getInstance().getUserName();
        }
        giftrecordRepeat(userName,displayName,targetAvatar,targetUid);
    }

    private void next(String userName, String displayName, String targetAvatar, Long targetUid) {
        final String reason = mEt_reason.getText().toString();
        final String finalTargetAvatar = targetAvatar;
        final String finalDisplayName = displayName;
        final Long finalUid = targetUid;
        final Dialog dialog = DialogCreator.createLoadingDialog(this, this.getString(R.string.jmui_loading));
        dialog.show();


        ContactManager.sendInvitationRequest(userName, null, reason, new BasicCallback() {
            @Override
            public void gotResult(int responseCode, String responseMessage) {
                dialog.dismiss();
                if (responseCode == 0) {
                    UserEntry userEntry = UserEntry.getUser(mMyInfo.getUserName(), mMyInfo.getAppKey());
                    FriendRecommendEntry entry = FriendRecommendEntry.getEntry(userEntry,
                            userName, mTargetAppKey);
                    if (null == entry) {
                        entry = new FriendRecommendEntry(finalUid, userName, "", finalDisplayName, mTargetAppKey,
                                finalTargetAvatar, finalDisplayName, reason, FriendInvitation.INVITING.getValue(), userEntry, 100);
                    } else {
                        entry.state = FriendInvitation.INVITING.getValue();
                        entry.reason = reason;
                    }
                    entry.save();
                    ToastUtil.shortToast(VerificationActivity.this, "申请成功");
                    GiveGift(userName);
                    finish();
                } else if (responseCode == 871317) {
                    ToastUtil.shortToast(VerificationActivity.this, "不能添加自己为好友");
                } else {
                    ToastUtil.shortToast(VerificationActivity.this, "申请失败");
                }
            }
        });
    }


    @SuppressLint("WrongConstant")
    private void initView() {
        autoRelativeLayout = (AutoRelativeLayout) findViewById(R.id.arl);
        autoRelativeLayout.setOnClickListener(this);
        mEt_reason = (EditText) findViewById(R.id.et_reason);
        initVideo();
       activity=this;
        mMyInfo = JMessageClient.getMyInfo();
        mTargetAppKey = mMyInfo.getAppKey();
        String name;
        //群组详细信息点击非好友头像,跳转到此添加界面
        if (getIntent().getFlags() == 1) {
            name = getIntent().getStringExtra("detail_add_friend_my_nickname");
            if (TextUtils.isEmpty(name)) {
                mEt_reason.setText("我是");
            } else {
                mEt_reason.setText("我是" + name);
            }
            //搜索用户发送添加申请
        } else {
            name = mMyInfo.getNickname();
            if (TextUtils.isEmpty(name)) {
                mEt_reason.setText("我是");
            } else {
                mEt_reason.setText("我是" + name);
            }
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.arl:
                finish();
                break;
        }
    }
}
