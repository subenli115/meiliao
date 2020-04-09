//package com.ziran.meiliao.ui.priavteclasses.activity;
//
//import android.app.Activity;
//import android.app.Dialog;
//import android.content.Context;
//import android.content.Intent;
//import android.support.v4.app.Fragment;
//import android.view.View;
//
//import com.ziran.meiliao.R;
//import com.ziran.meiliao.common.baseapp.AppManager;
//import com.ziran.meiliao.common.commonutils.ToastUitl;
//import com.ziran.meiliao.common.compressorutils.EmptyUtils;
//import com.ziran.meiliao.common.receiver.NetUtil;
//import com.ziran.meiliao.constant.ApiKey;
//import com.ziran.meiliao.constant.AppConstant;
//import com.ziran.meiliao.envet.HistoryMediaControllerListener;
//import com.ziran.meiliao.ui.base.CommonModel;
//import com.ziran.meiliao.ui.bean.GiftBean;
//import com.ziran.meiliao.ui.bean.GiveGiftResultBean;
//import com.ziran.meiliao.ui.priavteclasses.fragment.SJKLiveDetailCommentFragment;
//import com.ziran.meiliao.ui.priavteclasses.fragment.SJKLiveDetailProfileFragment;
//import com.ziran.meiliao.ui.priavteclasses.fragment.SJKLiveDetailRecommendCourseFragment;
//import com.ziran.meiliao.ui.priavteclasses.presenter.SJKCoursePresenter;
//import com.ziran.meiliao.utils.CheckUtil;
//import com.ziran.meiliao.utils.MapUtils;
//import com.ziran.meiliao.widget.LiveMediaController;
//import com.wevey.selector.dialog.MDAlertDialog;
//import com.wevey.selector.dialog.SimpleDialogClickListener;
//
//import java.util.List;
//
//import rx.functions.Action1;
//
//
///**
// * 减压馆 -音频详情界面
// * Created by Administrator on 2017/1/14.
// */
//
//public class HorizontalHistoryLiveActivity extends CommonLiveActivity<SJKCoursePresenter, CommonModel> {
//
//    public static void startAction(Context context, String courseId, int liveStreaming) {
//        startAction(context, courseId, liveStreaming, false);
//    }
//
//    public static void startAction(int courseId) {
//        startAction(AppManager.getAppManager().currentActivity(), String.valueOf(courseId), 0, true);
//    }
//
//    public static void startAction(Context context, String courseId, int liveStreaming, boolean isFinish) {
//
//        if (CheckUtil.check(context)) {
//            Intent intent = new Intent(context, HorizontalHistoryActivity.class);
//            intent.putExtra("courseId", courseId);
//            intent.putExtra("liveStreaming", liveStreaming);
//            context.startActivity(intent);
//            if (isFinish) {
//                ((Activity) context).finish();
//            }
//        }
//    }
//
//
//    @Override
//    public void initView() {
//        mMediaController.setState(LiveMediaController.HISTORY_STATE_HELF);
//        mMediaController.setMediaPlayer(mVideoView);
//        mMediaController.setIsLiveOrHistory(false);
//        super.initView();
//        mMediaController.setHistoryMediaControllerListener(new HistoryMediaControllerListener() {
//            @Override
//            public void onCollect() {
//                if (mDataBean != null) {
//                    mPresenter.postCollect(ApiKey.COLLECT_COURSE, MapUtils.getCollect(mDataBean.isCollect(), "courseIds", courseId));
//                }
//            }
//
//            @Override
//            public void onLike() {
//                if (mDataBean != null) {
//                    mPresenter.postLike(ApiKey.LIKE_COURSE, MapUtils.getLike("courseId", courseId, mDataBean.isLike()));
//                }
//            }
//
//            @Override
//            public void playOrPause() {
//                doPlayOrPause();
//            }
//        });
//        mRxManager.on(AppConstant.RXTag.PLAYER_END, new Action1<Boolean>() {
//            @Override
//            public void call(Boolean aBoolean) {
//                if (currentDirbean!=null){
//                    updateStudy(currentDirbean,true);
//                }
//            }
//        });
//    }
//
//    @Override
//    public void postShiKan() {
//        mPresenter.postShiKan(ApiKey.COURSE_SHIKAN, MapUtils.getCourseMap(courseId));
//    }
//
//    @Override
//    protected int getStringArrayId() {
//        return R.array.sjk_live_tabs_3;
//    }
//
//    @Override
//    protected void initFragments(List<Fragment> fragments) {
//        fragments.add(new SJKLiveDetailProfileFragment());
//        fragments.add(new SJKLiveDetailRecommendCourseFragment());
//        mSJKLiveDetailCommentFragment = new SJKLiveDetailCommentFragment();
//        fragments.add(mSJKLiveDetailCommentFragment);
//    }
//
//
//    public boolean setVideoPath() {
//        if (EmptyUtils.isNotEmpty(mVideoPath)) {
//            isSetVideoPath = true;
//            mPresenter.watchUp(ApiKey.watchUp, MapUtils.getOnlyCan(AppConstant.SPKey.COURSE_ID, courseId));
//            isPlayerEnd = false;
//            mVideoPlayerHelper.startPlay(mVideoPath);
//            mVideoPlayerHelper.startShiKan();
//            mMediaController.setPlay();
//        } else {
//            ToastUitl.showShort("视频走丢了,请稍后重试");
//        }
//        return true;
//    }
//
//
//    public boolean doPlayOrPause() {
//        if (!isSetVideoPath) {
//            if (NetUtil.getNetWorkState(this) == NetUtil.NETWORK_MOBILE) {
//                MDAlertDialog.createDialog(HorizontalHistoryLiveActivity.this, getString(R.string.mobile_toast), new
//                        SimpleDialogClickListener() {
//                    @Override
//                    public void clickRightButton(Dialog dialog, View view) {
//                        dialog.dismiss();
//                        setVideoPath();
//                    }
//                });
//            } else {
//                setVideoPath();
//            }
//        } else {
//            mMediaController.setPlay();
//        }
//        return isSetVideoPath;
//    }
//
//
//    @Override
//    public void showListGift(List<GiftBean.DataBean> beanList) {
//
//    }
//
//    @Override
//    public void giveGiftResult(GiveGiftResultBean.DataBean result) {
//
//    }
//}
