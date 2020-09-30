package com.ziran.meiliao.im.activity.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MeiliaoConfig;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.im.activity.CommonActivity;
import com.ziran.meiliao.im.utils.SharePreferenceManager;
import com.ziran.meiliao.im.utils.ThreadUtil;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpFragment;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.UpdateUserNewHeadBean;
import com.ziran.meiliao.ui.bean.UserAccountBean;
import com.ziran.meiliao.ui.bean.UserBean;
import com.ziran.meiliao.ui.bean.VisitorBean;
import com.ziran.meiliao.ui.me.activity.FeedbackActivity;
import com.ziran.meiliao.ui.me.activity.MyActivityActivity;
import com.ziran.meiliao.ui.priavteclasses.activity.UserHomeActivity;
import com.ziran.meiliao.ui.settings.activity.RechargeActivity;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.Utils;
import com.ziran.meiliao.widget.GlideCircleTransform;
import com.ziran.meiliao.widget.ItemGroupView;
import com.ziran.meiliao.widget.TTadUtil;
import com.ziran.meiliao.widget.pupop.UpdatePopupWindow;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;
import rx.functions.Action1;

import static android.app.Activity.RESULT_OK;
import static com.ziran.meiliao.constant.ApiKey.ACCOUNT_ACCOUNT_INFO;
import static com.ziran.meiliao.constant.ApiKey.ADMIN_VISITOR_VISIORNUM;

/**
 * 我的
 * Created by Administrator on 2017/1/4.
 */

public class NewMeFragment extends CommonHttpFragment<CommonPresenter, CommonModel> implements CommonContract.View<Result> {
    private static String cameraScalePath;
    private static final int LIKE_LIST = 1;
    private static final int VISIT_LIST =2 ;
    private static final int UPDATE_BG =1 ;
    private static final int UPDATE_HEAD =2 ;
    @Bind(R.id.tv_money)
    TextView tvMoney;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_profit)
    TextView tvProfit;
    @Bind(R.id.tv_sign)
    TextView tvSign;
    @Bind(R.id.iv_get_money)
    ImageView ivGetMoney;
    @Bind(R.id.iv_head)
    ImageView ivHead;
    @Bind(R.id.iv_real_name)
    ImageView ivRealName;
    @Bind(R.id.iv_real_person)
    ImageView ivRealPerson;
    public final int REQUEST_CODE_B = 1;
    public final int REQUEST_CODE_A = 2;
    public static final int RequestCrop = 3;
    @Bind(R.id.arl_bg)
    AutoRelativeLayout clBg;
    @Bind(R.id.tv_me_main_new_likes)
    ItemGroupView tvLikes;
    @Bind(R.id.tv_me_main_new_visiter)
    ItemGroupView tvVisits;
    @Bind(R.id.tv_me_main_new_opinion)
    ItemGroupView tvOpinion;

    private UserAccountBean.DataBean mUserAccountBean;
    private View contentView;
    private VisitorBean.DataBean visitorBean;
    private UpdatePopupWindow pop;
    private TTadUtil tTadUtil;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_main_new_me;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    //全屏并且隐藏状态栏
    private void hideStatusBar() {
        WindowManager.LayoutParams attrs = getActivity().getWindow().getAttributes();
        attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getActivity(). getWindow().setAttributes(attrs);
    }
    @Override
    protected void initView() {
        tTadUtil = new TTadUtil(getActivity(),clBg,mRxManager);
        initData();
        mRxManager.on(AppConstant.RXTag.UPDATE_USER, new Action1<String>() {
            @Override
            public void call(String balance) {
                initData();
            }
        });
        pop = new UpdatePopupWindow(getContext(), getActivity(),this);
    }

    private void initData() {
        tvOpinion.showBottomLine(false);
        tvVisits.showBottomLine(false);
        tvLikes.showBottomLine(false);
        UserBean.DataBean dataBean = MyAPP.getmUserBean();
        tvName.setText(dataBean.getNickname());
        if(dataBean.getIntroduce()!=null){
            tvSign.setText(dataBean.getIntroduce());
        }
        if(MeiliaoConfig.getNewOpen()){
            ivGetMoney.setVisibility(View.VISIBLE);
        }
        Glide.with(getContext()).load(dataBean.getAvatar()).into(ivHead);
        if(dataBean.getIsReal()!=null&&dataBean.getIsReal().equals("1")) {
            ivRealPerson.setVisibility(View.VISIBLE);
        }
        mRxManager.on(AppConstant.RXTag.UPDATE_ACCOUNT, new Action1<UserAccountBean>() {
            @Override
            public void call(UserAccountBean mUserAccountBean) {
                UserAccountBean.DataBean data = mUserAccountBean.getData();
                tvMoney.setText(new DecimalFormat("#,###").format(data.getRecharge()));
                tvProfit.setText( new DecimalFormat("#,###").format(data.getCurrency()));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getDataOneHead(ACCOUNT_ACCOUNT_INFO, MyAPP.getUserId(), MyAPP.getAccessToken(), UserAccountBean.class);
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("userId",MyAPP.getUserId());
        mPresenter.getData(ADMIN_VISITOR_VISIORNUM, defMap, VisitorBean.class);
    }


    @Override
    public void returnData(Result result) {
        if(result instanceof UserAccountBean ){
            mUserAccountBean = ((UserAccountBean) result).getData();
            MyAPP.saveMoney(((UserAccountBean) result));
            tvMoney.setText(new DecimalFormat("#,###").format(mUserAccountBean.getRecharge()));
            tvProfit.setText( new DecimalFormat("#,###").format(mUserAccountBean.getCurrency()));
        }else if(result instanceof VisitorBean){
            visitorBean = ((VisitorBean) result).getData();
            if(visitorBean.getLove()!=0){
                tvLikes.setRigthText("+"+visitorBean.getLove());
            }
            if(visitorBean.getVisitor()!=0){
                tvVisits.setRigthText("+"+visitorBean.getVisitor());
            }
        }
    }

    /**
     * 启动裁剪
     * @param activity 上下文
     * @param sourceFilePath 需要裁剪图片的绝对路径
     * @param requestCode 比如：UCrop.REQUEST_CROP
     * @return
     */
    public static String startUCrop(Activity activity, String sourceFilePath,
                                    int requestCode) {
        Uri sourceUri = Uri.fromFile(new File(sourceFilePath));
        File outDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        if (!outDir.exists()) {
            outDir.mkdirs();
        }
        File outFile = new File(outDir, System.currentTimeMillis() + ".jpg");
        //裁剪后图片的绝对路径
        cameraScalePath = outFile.getAbsolutePath();
        Uri destinationUri = Uri.fromFile(outFile);
        //初始化，第一个参数：需要裁剪的图片；第二个参数：裁剪后图片
        UCrop uCrop = UCrop.of(sourceUri, destinationUri);
        //初始化UCrop配置
        UCrop.Options options = new UCrop.Options();
        //设置裁剪图片可操作的手势
        options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL);
        //是否隐藏底部容器，默认显示
        options.setHideBottomControls(true);
        //设置toolbar颜色
        //是否能调整裁剪框
        options.setFreeStyleCropEnabled(true);
        //UCrop配置
        uCrop.withOptions(options);
        //设置裁剪图片的宽高比，比如16：9
        uCrop.withAspectRatio(1, 1);
        //uCrop.useSourceImageAspectRatio();
        //跳转裁剪页面
        uCrop.start(activity, requestCode);
        return cameraScalePath;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ImgSelConfig.RequestCode && data != null) {
            final ArrayList<String> imgPaths = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
            startUCrop(getActivity(),imgPaths.get(0),RequestCrop);
        }else if(resultCode == RESULT_OK &&requestCode==RequestCrop){
            if(cameraScalePath.length()>0){
                updateUserHead();
            }
        }

    }
    private void updateUserHead() {
        upload();
    }
    private void eidtHead(UpdateUserNewHeadBean result) {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("id",MyAPP.getUserId());
            defMap.put("avatar",result.getData().getUrl());
        OkHttpClientManager.putAsyncAddHead(ApiKey.ADMIN_USER_UPDATE, defMap, new
                NewRequestCallBack<UserBean>(UserBean.class) {
                    @Override
                    public void onSuccess(UserBean result) {
                        MyAPP.setmUserBean(result.getData());
                            Glide.with(getContext()).load(result.getData().getAvatar()).error(R.drawable.jmui_head_icon).transform(new GlideCircleTransform(getContext())).into(ivHead);
                        if (TextUtils.isEmpty(MyAPP.getUserInfo().getHeadImg())) {
                            MyAPP.getUserInfo().setHeadImg(result.getData().getAvatar());
                        }
                    }
                    @Override
                    public void onError(String msg, int code) {
                        ToastUitl.showShort(msg);
                    }
                });
    }
    private void upload() {
            imHeadUpload();
        OkHttpClientManager.postContentAndFiles(ApiKey.ADMIN_FILE_UPLOAD, MapUtils.getDefMap(true), FileUtil.str2File(cameraScalePath), new
                NewRequestCallBack<UpdateUserNewHeadBean>(UpdateUserNewHeadBean.class) {

                    @Override
                    public void onSuccess(UpdateUserNewHeadBean result) {
                        eidtHead(result);
                    }

                    @Override
                    public void onError(String msg, int code) {
                        ToastUitl.showShort(msg);
                    }
                });
    }
    private void imHeadUpload() {
        ThreadUtil.runInThread(new Runnable() {
            @Override
            public void run() {
                JMessageClient.updateUserAvatar(new File(cameraScalePath), new BasicCallback() {
                    @Override
                    public void gotResult(int responseCode, String responseMessage) {
                        if (responseCode == 0) {
                            SharePreferenceManager.setCachedAvatarPath(cameraScalePath);
                        }else {

                        }
                    }
                });
            }
        });
    }

    @OnClick({R.id.tv_me_main_new_visiter,R.id.tv_me_main_new_likes,R.id.tv_me_main_new_opinion,R.id.tv_me_main_new_setting,
            R.id.arl,R.id.iv_head,R.id.arl_profit,R.id.arl_recharge,R.id.iv_get_money
    })
    public void onViewClicked(View view) {
        if (Utils.isFastDoubleClick()) {
            return;
        }else {
        switch (view.getId()) {
            case R.id.iv_get_money:
                tTadUtil.showVideoAd();
                break;
            case R.id.tv_me_main_new_visiter:
                CommonActivity.startAction(getContext(),VISIT_LIST,MyAPP.getUserId());
                break;
            case R.id.tv_me_main_new_likes:
                CommonActivity.startAction(getContext(),LIKE_LIST,MyAPP.getUserId());
                break;
            case R.id.tv_me_main_new_opinion:
                FeedbackActivity.startAction();
                break;
            case R.id.tv_me_main_new_setting:
                MyActivityActivity.startAction(getContext());
                break;
            case R.id.arl:
                UserHomeActivity.startAction(MyAPP.getUserId());
                break;
            case R.id.arl_profit:
                RechargeActivity.startAction(getContext(), "2");
                break;
            case R.id.arl_recharge:
                RechargeActivity.startAction(getContext(), "1");
                break;
            case R.id.iv_head:
                pop.setTitle("修改头像",true);
                pop.show();
                break;

        }}
    }
}