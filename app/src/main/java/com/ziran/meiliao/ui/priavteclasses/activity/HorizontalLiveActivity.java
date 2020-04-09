package com.ziran.meiliao.ui.priavteclasses.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.receiver.NetUtil;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.entry.VideoSectionEntry;
import com.ziran.meiliao.envet.LiveMediaControllerListener;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.GiftBean;
import com.ziran.meiliao.ui.bean.GiveGiftResultBean;
import com.ziran.meiliao.ui.bean.SJKLiveDetailProfileBean;
import com.ziran.meiliao.ui.priavteclasses.AbsHorizontalLiveActivity;
import com.ziran.meiliao.ui.priavteclasses.fragment.SJKLiveDetailCommentFragment;
import com.ziran.meiliao.ui.priavteclasses.fragment.SJKLiveDetailProfileFragment;
import com.ziran.meiliao.ui.priavteclasses.fragment.SJKLiveDetailRecommendCourseFragment;
import com.ziran.meiliao.ui.priavteclasses.fragment.SJKLiveDetailRoomFragment;
import com.ziran.meiliao.ui.priavteclasses.presenter.SJKCoursePresenter;
import com.ziran.meiliao.ui.priavteclasses.util.LiveRoomUtil;
import com.ziran.meiliao.ui.priavteclasses.util.SJKRoomHelper;
import com.ziran.meiliao.ui.priavteclasses.util.VideoPlayerHelper1;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.ChatCustomView;
import com.ziran.meiliao.widget.LiveMediaController;
import com.ziran.meiliao.widget.pupop.GiveGiftPopupWindow;
import com.ziran.meiliao.widget.pupop.PopupWindowUtil;
import com.wevey.selector.dialog.MDAlertDialog;
import com.wevey.selector.dialog.SimpleDialogClickListener;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import cn.rongcloud.live.LiveKit;
import cn.rongcloud.live.ui.message.GiftMessage;
import rx.functions.Action1;


/**
 *
 * Created by Administrator on 2017/1/14.
 */

public class HorizontalLiveActivity extends AbsHorizontalLiveActivity<SJKCoursePresenter, CommonModel> implements
        LiveMediaControllerListener {

    @Bind(R.id.media_controller)
    protected LiveMediaController mMediaController;

    private SJKLiveDetailRoomFragment roomFragment;
    private SJKRoomHelper mSJKRoomHelper;

    public static void startAction(Context context, String courseId, int liveStreaming) {
        if (CheckUtil.check(context)) {
            Intent intent = new Intent(context, HorizontalLiveActivity.class);
            intent.putExtra(AppConstant.SPKey.COURSE_ID, courseId);
            intent.putExtra("liveStreaming", liveStreaming);
            context.startActivity(intent);
        }
    }
    public static void startAction(int courseId) {
        Activity activity = AppManager.getAppManager().currentActivity();
        startAction(activity, String.valueOf(courseId), 1);
        activity.finish();
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_sjk_horizontal_live;
    }


    @Override
    protected void initVideoPlayerHelper() {
        MyAPP.initLiveKit(this);
        mVideoPlayerHelper = new VideoPlayerHelper1(this);
        mVideoPlayerHelper.init(mVideoView, mLoadingView, mMediaController, coverView);
        mVideoPlayerHelper.setIsLiveStreaming(1, 2);
        mMediaController.setCommonMediaControllerListener(this);
        mMediaController.setLiveMediaControllerListener(this);
        mIMyMediaController = mMediaController;
        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if (visibility == View.VISIBLE) {
                    mMediaController.show();
                }
            }
        });
    }
    boolean isPauseNew = false;
    @Override
    protected void initRx() {
        super.initRx();
        mRxManager.on(AppConstant.RXTag.VIDEO_PATH, new Action1<SJKLiveDetailProfileBean.DataBean>() {
            @Override
            public void call(final SJKLiveDetailProfileBean.DataBean bean) {
                if (bean == null || isPauseNew ) return;
                mDataBean = bean;
                mDataBean.setCourseId(courseId);
                LiveRoomUtil.get().onDestroy(mDataBean);
                roomFragment.loadRoom();
                mMediaController.setTitle(mDataBean.getTitle());
                currentDirbean = mVideoPlayerHelper.getCurrentSection(bean);
                mVideoPath = mVideoPlayerHelper.getCurrentVideoPath(bean);
                if (bean.isHasBuy()) {
                    initIsBuy();
                    aRewardView.setVisibility(View.GONE);
                } else {
                    aRewardView.setVisibility(View.VISIBLE);
                    mMediaController.showShikan(true);
                    aRewardView.setCount(1);
                    aRewardView.setNeedCoin("课程", mDataBean.getNeedCoin());
                    mVideoPlayerHelper.checkShiKan(mDataBean.isShikan(), mVideoPath);
                }
            }
        });


        mRxManager.on(AppConstant.RXTag.LIVE_OVER, new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                //直播已结束
                ToastUitl.showShort("直播已结束,谢谢观看");
                HandlerUtil.runMain(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 5000);
            }
        });
        mRxManager.on(AppConstant.RXTag.GIVE_GIFT, new Action1<GiftBean.DataBean>() {
            @Override
            public void call(GiftBean.DataBean giftBean) {
                mMediaController.giveGift(giftBean);
                Map<String, String> stringMap = MapUtils.getOnlyCan("giftId", giftBean.getGiftId());
                stringMap.put("courseId",courseId);
                mPresenter.postGiveGift(ApiKey.COURSE_GIVE_GIFT,stringMap);
                LiveKit.sendMessage(new GiftMessage(giftBean.getPath(), "送给老师" + giftBean .getName()));
            }
        });


        mRxManager.on(AppConstant.RXTag.USER_COUNT, new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                mPresenter.postUserCount(ApiKey.COURSE_GETCHRM_SUERCOUNT, MapUtils.getCourseMap(courseId));
            }
        });
        mRxManager.on(AppConstant.RXTag.SEND, new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                showKeyBoard(false);
            }
        });
    }



    private void initIsBuy() {
        if (!TextUtils.isEmpty(mVideoPath)) {
            int netWorkState = NetUtil.getNetWorkState(this);
            if (netWorkState == NetUtil.NETWORK_MOBILE) {
                MDAlertDialog.createDialog(HorizontalLiveActivity.this, getString(R.string.mobile_toast), new SimpleDialogClickListener() {
                    @Override
                    public void clickRightButton(Dialog dialog, View view) {
                        dialog.dismiss();
                        startPlaying();
                    }
                    @Override
                    public void clickLeftButton(Dialog dialog, View view) {
                        dialog.dismiss();
                        finish();
                    }
                });
            } else if (netWorkState == NetUtil.NETWORK_WIFI) {
                startPlaying();
            }
        }
    }
    private boolean isWatchUp = false;
    private void startPlaying() {
        if (isBuyCourse()) {
            roomFragment.setInputPanelVisibility(true);
        }
        mVideoPlayerHelper.startPlay(mVideoPath);
        if (!isWatchUp){
            isWatchUp = true;
            mPresenter.watchUp(ApiKey.watchUp, MapUtils.getOnlyCan(AppConstant.SPKey.COURSE_ID, courseId));
        }
    }

    public int getOffscreenPageLimit() {
        return 4;
    }
    public void initFragments(List<Fragment> fragments) {
        fragments.add(new SJKLiveDetailProfileFragment());
        roomFragment = new SJKLiveDetailRoomFragment();
        fragments.add(roomFragment);
        fragments.add(new SJKLiveDetailRecommendCourseFragment());
        mSJKLiveDetailCommentFragment = new SJKLiveDetailCommentFragment();
        fragments.add(mSJKLiveDetailCommentFragment);
    }
    public int getStringArrayId() {
        return R.array.sjk_live_tabs;
    }



    @Override
    public void setUserCount(String userCount) {
        mMediaController.setWatchCount(Integer.parseInt(userCount));
    }

    @Override
    public void onPayResult(String url) {
      super.onPayResult(url);
        initIsBuy();
    }

    private List<GiftBean.DataBean> giftList;
    private GiveGiftPopupWindow giveGiftPopupwindow;

    private void showGiftDialog() {
        if (giveGiftPopupwindow==null){
            giveGiftPopupwindow = new GiveGiftPopupWindow(this);
            giveGiftPopupwindow.setDatas(giftList);
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                giveGiftPopupwindow.addBottomMargin();
            }
        }
        giveGiftPopupwindow.setBalance(mDataBean.getUserCoin());
        PopupWindowUtil.showTop(this, giveGiftPopupwindow);
    }

    @Override
    public void showListGift(List<GiftBean.DataBean> beanList) {
        giftList = beanList;
        showGiftDialog();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isPauseNew = false;
    }

    @Override
    public void giveGiftResult(GiveGiftResultBean.DataBean result) {
        mDataBean.setUserCoin(result.getYue());
        if (giveGiftPopupwindow != null) {
            giveGiftPopupwindow.setBalance(result.getYue());
        }
    }

    @Override
    public void playUrl(VideoSectionEntry dirBean) {
        if (isPauseNew) return;
        LogUtils.logd("videoSectionEntry"+dirBean);
        if (dirBean.getTag()==0 ){
            isPauseNew = true;
            HorizontalHistoryActivity.startAction(this,courseId,0);
        }else if (dirBean.getTag() == 2 ){
            ToastUitl.showShort("课程还未开始");
        }
    }

    @Override
    public void playShiKan(VideoSectionEntry videoSectionEntry) {

    }

    @Override
    public int isLiveStreaming() {
        return 1;
    }



    @Override
    public void changeScreen(boolean isFull) {
        super.changeScreen(isFull);
        if (isFull) {
            if (mSJKRoomHelper != null) {
                mSJKRoomHelper.onFragmentVisibleChange(true);
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSJKRoomHelper.onDestroy();
    }

    @Override
    public void postShiKan() {
        mPresenter.postShiKan(ApiKey.COURSE_SHIKAN, MapUtils.getCourseMap(courseId));
    }


    @Override
    public void sendMessage() {
        if (mSJKRoomHelper != null) {
            showKeyBoard(true);
        }
    }
    public void showKeyBoard(boolean show) {
        mSJKRoomHelper.showInputPanel(show);
        if (show) {
            mMediaController.hide();
        } else {
            mMediaController.show();
        }
        mSJKRoomHelper.showKeyboard(show);
    }
    @Override
    public void giveGift() {
        if (EmptyUtils.isNotEmpty(giftList)) {
            showGiftDialog();
        } else {
            mPresenter.listGift(ApiKey.COURSE_LIST_GIFT, MapUtils.getDefMap(false));
        }
    }

    @Override
    public void getChatListView(ChatCustomView chatCustomView) {
//        LogUtils.logd("chatCustomView"+chatCustomView);
        mSJKRoomHelper = new SJKRoomHelper(chatCustomView,false);
        mSJKRoomHelper.startGetUserCount();
    }

}
