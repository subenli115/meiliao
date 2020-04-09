//package com.ziran.meiliao.ui.priavteclasses.activity;
//
//import android.app.Activity;
//import android.app.Dialog;
//import android.content.Context;
//import android.content.Intent;
//import android.content.res.Configuration;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.text.TextUtils;
//import android.view.KeyEvent;
//import android.view.MotionEvent;
//import android.view.View;
//
//import com.ziran.meiliao.R;
//import com.ziran.meiliao.app.MyAPP;
//import com.ziran.meiliao.common.baseapp.AppManager;
//import com.ziran.meiliao.common.commonutils.LogUtils;
//import com.ziran.meiliao.common.commonutils.ToastUitl;
//import com.ziran.meiliao.common.compressorutils.EmptyUtils;
//import com.ziran.meiliao.common.receiver.NetUtil;
//import com.ziran.meiliao.constant.ApiKey;
//import com.ziran.meiliao.constant.AppConstant;
//import com.ziran.meiliao.envet.LiveMediaControllerListener;
//import com.ziran.meiliao.ui.base.CommonModel;
//import com.ziran.meiliao.ui.bean.GiftBean;
//import com.ziran.meiliao.ui.bean.GiveGiftResultBean;
//import com.ziran.meiliao.ui.priavteclasses.fragment.SJKLiveDetailCommentFragment;
//import com.ziran.meiliao.ui.priavteclasses.fragment.SJKLiveDetailProfileFragment;
//import com.ziran.meiliao.ui.priavteclasses.fragment.SJKLiveDetailRecommendCourseFragment;
//import com.ziran.meiliao.ui.priavteclasses.fragment.SJKLiveDetailRoomFragment;
//import com.ziran.meiliao.ui.priavteclasses.presenter.SJKCoursePresenter;
//import com.ziran.meiliao.ui.priavteclasses.util.SJKRoomHelper;
//import com.ziran.meiliao.utils.CheckUtil;
//import com.ziran.meiliao.utils.HandlerUtil;
//import com.ziran.meiliao.utils.MapUtils;
//import com.ziran.meiliao.widget.ChatCustomView;
//import com.ziran.meiliao.widget.LiveMediaController;
//import com.ziran.meiliao.widget.pupop.GiveGiftPopupWindow;
//import com.ziran.meiliao.widget.pupop.PopupWindowUtil;
//import com.wevey.selector.dialog.MDAlertDialog;
//import com.wevey.selector.dialog.SimpleDialogClickListener;
//
//import java.util.List;
//import java.util.Map;
//
//import cn.rongcloud.live.LiveKit;
//import cn.rongcloud.live.ui.message.GiftMessage;
//import rx.functions.Action1;
//
//
///**
// * 减压馆 -音频详情界面
// * Created by Administrator on 2017/1/14.
// */
//
//public class HorizontalLiveActivity extends CommonLiveActivity<SJKCoursePresenter, CommonModel> {
//
//    private SJKLiveDetailRoomFragment roomFragment;
//    private SJKRoomHelper mSJKRoomHelper;
//
//    public static void startAction(Context context, String courseId, int liveStreaming) {
//        if (CheckUtil.check(context)) {
//            Intent intent = new Intent(context, HorizontalLiveActivity.class);
//            intent.putExtra(AppConstant.SPKey.COURSE_ID, courseId);
//            intent.putExtra("liveStreaming", liveStreaming);
//            context.startActivity(intent);
//        }
//    }
//
//    public static void startAction(int courseId) {
//        Activity activity = AppManager.getAppManager().currentActivity();
//        startAction(activity, String.valueOf(courseId), 1);
//        activity.finish();
//    }
//
//    @Override
//    public void initView() {
//
//        mMediaController.setLiveMediaControllerListener(new LiveMediaControllerListener() {
//            @Override
//            public void sendMessage() {
//                if (mSJKRoomHelper != null) {
//                    showKeyBoard(true);
//                }
//            }
//
//            @Override
//            public void giveGift() {
//                if (EmptyUtils.isNotEmpty(giftList)) {
//                    showGiftDialog();
//                } else {
//                    mPresenter.listGift(ApiKey.COURSE_LIST_GIFT, MapUtils.getDefMap(false));
//                }
//            }
//
//            @Override
//            public void getChatListView(ChatCustomView chatCustomView) {
//                mSJKRoomHelper = new SJKRoomHelper(chatCustomView);
//                mSJKRoomHelper.startGetUserCount();
//            }
//        });
//        mMediaController.setIsLiveOrHistory(true);
//        mMediaController.setState(LiveMediaController.LIVE_STATE_HELF);
//
//        super.initView();
//
//    }
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        MyAPP.initLiveKit(this);
//
//        mRxManager.on(AppConstant.RXTag.LIVE_OVER, new Action1<Boolean>() {
//            @Override
//            public void call(Boolean aBoolean) {
//                //直播已结束
//                ToastUitl.showShort("直播已结束,谢谢观看");
//                HandlerUtil.runMain(new Runnable() {
//                    @Override
//                    public void run() {
//                        finish();
//                    }
//                }, 5000);
//            }
//        });
//        mRxManager.on(AppConstant.RXTag.GIVE_GIFT, new Action1<GiftBean.DataBean>() {
//            @Override
//            public void call(GiftBean.DataBean giftBean) {
//                mMediaController.giveGift(giftBean);
//                LiveKit.sendMessage(new GiftMessage(giftBean.getPath(), "送给老师" + giftBean .getName()));
//                Map<String, String> stringMap = MapUtils.getOnlyCan("giftId", giftBean.getGiftId());
//                stringMap.put("courseId",courseId);
//                mPresenter.postGiveGift(ApiKey.COURSE_GIVE_GIFT,stringMap);
//            }
//        });
//        mRxManager.on(AppConstant.RXTag.USER_COUNT, new Action1<Boolean>() {
//            @Override
//            public void call(Boolean aBoolean) {
//                mPresenter.postUserCount(ApiKey.COURSE_GETCHRM_SUERCOUNT, MapUtils.getCourseMap(courseId));
//            }
//        });
//        mRxManager.on(AppConstant.RXTag.SEND, new Action1<Boolean>() {
//            @Override
//            public void call(Boolean aBoolean) {
//                showKeyBoard(false);
//            }
//        });
//    }
//
//    public void showKeyBoard(boolean show) {
//        mSJKRoomHelper.showInputPanel(show);
//        if (show) {
//            mMediaController.hide();
//        } else {
//            mMediaController.show();
//        }
//        mSJKRoomHelper.showKeyboard(show);
//    }
//
//    private boolean fristInitRoom = true;
//
//    @Override
//    public void changeScreen(boolean isFull) {
//        super.changeScreen(isFull);
//        if (fristInitRoom) {
//            fristInitRoom = false;
//            if (mSJKRoomHelper != null) {
//                mSJKRoomHelper.onFragmentVisibleChange(true);
//            }
//        }
//    }
//
//    @Override
//    public void postShiKan() {
//        mPresenter.postShiKan(ApiKey.COURSE_SHIKAN, MapUtils.getCourseMap(courseId));
//    }
//
//
//    @Override
//    protected int getStringArrayId() {
//        return R.array.sjk_live_tabs;
//    }
//
//    @Override
//    protected void initFragments(List<Fragment> fragments) {
//        fragments.add(new SJKLiveDetailProfileFragment());
//        roomFragment = new SJKLiveDetailRoomFragment();
//        fragments.add(roomFragment);
//        fragments.add(new SJKLiveDetailRecommendCourseFragment());
//        mSJKLiveDetailCommentFragment = new SJKLiveDetailCommentFragment();
//        fragments.add(mSJKLiveDetailCommentFragment);
//    }
//
//    @Override
//    protected int getOffscreenPageLimit() {
//        return 4;
//    }
//
//    @Override
//    public boolean onTrackballEvent(MotionEvent event) {
//        LogUtils.logd("onTrackballEvent" + event.getAction());
//        return super.onTrackballEvent(event);
//    }
//
//    @Override
//    public boolean onKeyUp(int keyCode, KeyEvent event) {
//        LogUtils.logd("onKeyUp" + keyCode);
//        return super.onKeyUp(keyCode, event);
//
//    }
//
//    @Override
//    protected void initIsBuy() {
//        if (!TextUtils.isEmpty(mVideoPath)) {
//            int netWorkState = NetUtil.getNetWorkState(this);
//            if (netWorkState == NetUtil.NETWORK_MOBILE) {
//                MDAlertDialog.createDialog(HorizontalLiveActivity.this, getString(R.string.mobile_toast), new SimpleDialogClickListener() {
//                    @Override
//                    public void clickRightButton(Dialog dialog, View view) {
//                        dialog.dismiss();
//                        startPlaying();
//                    }
//
//                    @Override
//                    public void clickLeftButton(Dialog dialog, View view) {
//                        dialog.dismiss();
//                        finish();
//                    }
//                });
//            } else if (netWorkState == NetUtil.NETWORK_WIFI) {
//                startPlaying();
//            }
//        }
//    }
//
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        mSJKRoomHelper.onPause();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        mSJKRoomHelper.onResume();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mSJKRoomHelper.onDestroy();
//    }
//
//
//    private void startPlaying() {
//        if (isBuy()) {
//            roomFragment.setInputPanelVisibility(true);
//        }
//        mVideoPlayerHelper.startPlay(null);
//        mPresenter.watchUp(ApiKey.watchUp, MapUtils.getOnlyCan(AppConstant.SPKey.COURSE_ID, courseId));
//        isPlayerEnd = false;
//    }
//
//    private GiveGiftPopupWindow giveGiftPopupwindow;
//
//    private void showGiftDialog() {
//        if (giveGiftPopupwindow==null){
//            giveGiftPopupwindow = new GiveGiftPopupWindow(this);
//            giveGiftPopupwindow.setDatas(giftList);
//            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
//                giveGiftPopupwindow.addBottomMargin();
//            }
//        }
//        giveGiftPopupwindow.setBalance(mDataBean.getUserCoin());
//        PopupWindowUtil.showTop(this, giveGiftPopupwindow);
//    }
//
//    private List<GiftBean.DataBean> giftList;
//
//    @Override
//    public void showListGift(List<GiftBean.DataBean> beanList) {
//        giftList = beanList;
//        showGiftDialog();
//    }
//
//    @Override
//    public void setUserCount(String userCount) {
//        mMediaController.setWachCount(Integer.parseInt(userCount));
//    }
//
//    @Override
//    public void giveGiftResult(GiveGiftResultBean.DataBean result) {
//        mDataBean.setUserCoin(result.getYue());
//        if (giveGiftPopupwindow != null) {
//            giveGiftPopupwindow.setBalance(result.getYue());
//        }
//    }
//}
