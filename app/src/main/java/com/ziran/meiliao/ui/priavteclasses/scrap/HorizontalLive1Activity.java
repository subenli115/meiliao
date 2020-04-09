//package com.ziran.meiliao.ui.priavteclasses.activity;
//
//import android.app.Dialog;
//import android.content.Context;
//import android.content.Intent;
//import android.content.pm.ActivityInfo;
//import android.content.res.Configuration;
//import android.support.design.widget.TabLayout;
//import android.support.v4.app.Fragment;
//import android.support.v4.view.ViewPager;
//import android.text.TextUtils;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//
//import com.pili.pldroid.player.widget.PLVideoTextureView;
//import com.ziran.meiliao.R;
//import com.ziran.meiliao.app.MyAPP;
//import com.ziran.meiliao.common.base.BaseFragmentAdapter;
//import com.ziran.meiliao.common.commonutils.KeyBordUtil;
//import com.ziran.meiliao.common.commonutils.LogUtils;
//import com.ziran.meiliao.common.commonutils.ToastUitl;
//import com.ziran.meiliao.common.commonutils.ViewUtil;
//import com.ziran.meiliao.common.compressorutils.EmptyUtils;
//import com.ziran.meiliao.common.okhttp.Result;
//import com.ziran.meiliao.common.receiver.NetUtil;
//import com.ziran.meiliao.constant.ApiKey;
//import com.ziran.meiliao.constant.AppConstant;
//import com.ziran.meiliao.entry.VideoSectionEntry;
//import com.ziran.meiliao.envet.CommonMediaControllerListener;
//import com.ziran.meiliao.envet.LiveCallBack;
//import com.ziran.meiliao.envet.LiveMediaControllerListener;
//import com.ziran.meiliao.ui.base.CommonModel;
//import com.ziran.meiliao.ui.base.ShareActivity;
//import com.ziran.meiliao.ui.bean.GiftBean;
//import com.ziran.meiliao.ui.bean.GiveGiftResultBean;
//import com.ziran.meiliao.ui.bean.SJKLiveDetailProfileBean;
//import com.ziran.meiliao.ui.priavteclasses.contract.SJKFullLiveContract;
//import com.ziran.meiliao.ui.priavteclasses.fragment.SJKLiveDetailCommentFragment;
//import com.ziran.meiliao.ui.priavteclasses.fragment.SJKLiveDetailProfileFragment;
//import com.ziran.meiliao.ui.priavteclasses.fragment.SJKLiveDetailRecommendCourseFragment;
//import com.ziran.meiliao.ui.priavteclasses.fragment.SJKLiveDetailRoomFragment;
//import com.ziran.meiliao.ui.priavteclasses.model.ItemPlayCallBack;
//import com.ziran.meiliao.ui.priavteclasses.presenter.SJKCoursePresenter;
//import com.ziran.meiliao.ui.priavteclasses.util.LiveRoomUtil;
//import com.ziran.meiliao.ui.priavteclasses.util.SJKRoomHelper;
//import com.ziran.meiliao.ui.priavteclasses.util.VideoPlayerHelper1;
//import com.ziran.meiliao.utils.HandlerUtil;
//import com.ziran.meiliao.utils.HomePageMusicManager;
//import com.ziran.meiliao.utils.MapUtils;
//import com.ziran.meiliao.utils.PayUtil;
//import com.ziran.meiliao.widget.ARewardV2View;
//import com.ziran.meiliao.widget.ChatCustomView;
//import com.ziran.meiliao.widget.LiveMediaController1;
//import com.ziran.meiliao.widget.pupop.GiveGiftPopupWindow;
//import com.ziran.meiliao.widget.pupop.JoinVipPopupWindow;
//import com.ziran.meiliao.widget.pupop.PayResultPopupWindow;
//import com.ziran.meiliao.widget.pupop.PopupWindowUtil;
//import com.ziran.meiliao.widget.pupop.SharePopupWindow;
//import com.ziran.meiliao.widget.pupop.UseVideoCouponPopupWindow;
//import com.ziran.meiliao.widget.pupop.VideoCouponTipsPopupWindow;
//import com.umeng.socialize.bean.SHARE_MEDIA;
//import com.wevey.selector.dialog.MDAlertDialog;
//import com.wevey.selector.dialog.SimpleDialogClickListener;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import butterknife.Bind;
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
//public class HorizontalLive1Activity extends ShareActivity<SJKCoursePresenter, CommonModel> implements SJKFullLiveContract.View,
//        ItemPlayCallBack, CommonMediaControllerListener,LiveCallBack, LiveMediaControllerListener {
//    @Bind(R.id.tab_layout)
//    protected TabLayout tabLayout;
//    @Bind(R.id.viewpager)
//    protected ViewPager viewPager;
//    @Bind(R.id.aRewardView)
//    protected ARewardV2View aRewardView;
//    @Bind(R.id.include_video)
//    protected RelativeLayout videoContoller;
//    @Bind(R.id.contentContoller)
//    protected View contentContoller;
//    @Bind(R.id.VideoView)
//    protected PLVideoTextureView mVideoView;
//    @Bind(R.id.LoadingView)
//    protected View mLoadingView;
//    @Bind(R.id.coverView)
//    protected ImageView coverView;
//    @Bind(R.id.media_controller)
//    protected LiveMediaController1 mMediaController;
//    protected String[] tabs;
//    protected List<Fragment> fragments;
//    protected int defVideoHeight;
//    private SharePopupWindow mSharePopupWindow;
//    protected String mVideoPath = null;
//    protected String courseId;
//    protected SJKLiveDetailCommentFragment mSJKLiveDetailCommentFragment;
//    private SJKLiveDetailRoomFragment roomFragment;
//    protected VideoPlayerHelper1 mVideoPlayerHelper;
//    public SJKLiveDetailProfileBean.DataBean mDataBean;
//    protected boolean isInitView = false;
//    private SJKRoomHelper mSJKRoomHelper;
//    private VideoSectionEntry currentDirbean;
//
//    public static void startAction(Context context, String courseId) {
//        Intent intent = new Intent(context, HorizontalLive1Activity.class);
//        intent.putExtra("courseId", courseId);
//        context.startActivity(intent);
//    }
//
//    @Override
//    public int getLayoutId() {
//        return R.layout.activity_sjk_horizontal_live;
//    }
//
//    @Override
//    public void initPresenter() {
//        if (mPresenter != null && mModel != null) {
//            mPresenter.setVM(this, mModel);
//        }
//    }
//
//    @Override
//    public void initView() {
//        if (!isInitView) {
//            defVideoHeight = getResources().getDimensionPixelSize(R.dimen.sjk_live_unfull_height);
//            courseId = getIntentExtra(getIntent(), AppConstant.SPKey.COURSE_ID);
//            if (EmptyUtils.isEmpty(courseId)) {
//                courseId = "32";
//            }
//            initViewPager();
//            initVideo();
//            isInitView = false;
//        }
//    }
//
//    private void initVideo() {
//
//        MyAPP.initLiveKit(this);
//        HomePageMusicManager.get().res(true);
//        mVideoPlayerHelper = new VideoPlayerHelper1(this);
//        mVideoPlayerHelper.init(mVideoView, mLoadingView, mMediaController, coverView);
//        mVideoPlayerHelper.setIsLiveStreaming(1, 2);
//
//
//        mRxManager.on(AppConstant.RXTag.VIDEO_PATH, new Action1<SJKLiveDetailProfileBean.DataBean>() {
//            @Override
//            public void call(final SJKLiveDetailProfileBean.DataBean bean) {
//                if (bean == null) return;
//                mDataBean = bean;
//                mDataBean.setCourseId(courseId);
//                LiveRoomUtil.get().onDestroy(mDataBean);
//                roomFragment.loadRoom();
////                mMediaController.setWatchCount(mDataBean.getWatchCount());
//                mMediaController.setName(mDataBean.getName());
//                currentDirbean = mVideoPlayerHelper.getCurrentSection(bean);
//
//                mVideoPath = mVideoPlayerHelper.getCurrentVideoPath(bean);
//                if (bean.isHasBuy()) {
//                    initIsBuy();
//                    aRewardView.setVisibility(View.GONE);
//                } else {
//                    aRewardView.setVisibility(View.VISIBLE);
//                    mMediaController.showShikan(true);
//                    aRewardView.setCount(1);
//                    aRewardView.setNeedCoin("课程", mDataBean.getNeedCoin());
//                    mVideoPlayerHelper.checkShiKan(mDataBean.isShikan(), mVideoPath);
//                }
//            }
//        });
//
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
//        mRxManager.on(AppConstant.RXTag.BALANCE, new Action1<String>() {
//            @Override
//            public void call(String balance) {
//                mDataBean.setUserCoin(Integer.parseInt(balance));
//            }
//        });
//        mRxManager.on(AppConstant.RXTag.REQ_BUY_COURSE, new Action1<Boolean>() {
//            @Override
//            public void call(Boolean aBoolean) {
//                    onPayResult(null);
////                mPresenter.buyCourse(ApiKey.COURSE_BUY, MapUtils.getCourseMap(courseId));
//            }
//        });
//
//        mRxManager.on(AppConstant.RXTag.USER_TICK, new Action1<SJKLiveDetailProfileBean.DataBean.UserTickBean>() {
//            @Override
//            public void call(SJKLiveDetailProfileBean.DataBean.UserTickBean userTickBean) {
//                Map<String, String> courseMap = MapUtils.getCourseMap(courseId);
//                courseMap.put("tickId", userTickBean.getTickId());
//                mPresenter.usedTick(ApiKey.COURSE_TICKET_USETICK, courseMap);
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
//        aRewardView.setOnClick(onArewardClickListener);
//        mMediaController.setCommonMediaControllerListener(this);
//        mMediaController.setLiveMediaControllerListener(this);
//
//        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
//            @Override
//            public void onSystemUiVisibilityChange(int visibility) {
//                if (visibility == View.VISIBLE) {
//                    mMediaController.show();
//                }
//            }
//        });
//    }
//
//    private void initIsBuy() {
//        if (!TextUtils.isEmpty(mVideoPath)) {
//            int netWorkState = NetUtil.getNetWorkState(this);
//            if (netWorkState == NetUtil.NETWORK_MOBILE) {
//                MDAlertDialog.createDialog(HorizontalLive1Activity.this, getString(R.string.mobile_toast), new SimpleDialogClickListener() {
//                    @Override
//                    public void clickRightButton(Dialog dialog, View view) {
//                        dialog.dismiss();
//                        startPlaying();
//                    }
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
//    private boolean isWatchUp = false;
//    private void startPlaying() {
//        if (isBuyCourse()) {
//            roomFragment.setInputPanelVisibility(true);
//        }
//        mVideoPlayerHelper.startPlay(mVideoPath);
//        if (!isWatchUp){
//            isWatchUp = true;
//            mPresenter.watchUp(ApiKey.watchUp, MapUtils.getOnlyCan(AppConstant.SPKey.COURSE_ID, courseId));
//        }
//    }
//
//    private boolean isFreeShare;
//    private View.OnClickListener onArewardClickListener = new View.OnClickListener() {
//        @Override
//        public void onContentClick(View v) {
//            switch (v.getId()) {
//                case R.id.ll_horizontal_use_video_coupon_vip:
//                    JoinVipPopupWindow popupwindow = new JoinVipPopupWindow(HorizontalLive1Activity.this);
//                    popupwindow.setOnPayCallBack(mOnPayCallBack);
//                    PopupWindowUtil.show(popupwindow);
//                    break;
//                case R.id.ll_horizontal_use_video_coupon_buy:
//                    if (mDataBean.isHasTick()) {
//                        UseVideoCouponPopupWindow useVideoCouponPopupwindow = new UseVideoCouponPopupWindow(HorizontalLive1Activity.this);
//                        useVideoCouponPopupwindow.setData(mDataBean.getUserTick());
//                        PopupWindowUtil.show(HorizontalLive1Activity.this, useVideoCouponPopupwindow);
//                    } else {
//                        VideoCouponTipsPopupWindow videoCouponTipsPopupwindow = new VideoCouponTipsPopupWindow(HorizontalLive1Activity
//                                .this);
//                        isFreeShare = true;
//                        videoCouponTipsPopupwindow.setCourseId(mDataBean.getCourseId());
//                        videoCouponTipsPopupwindow.setParams(mDataBean.getUserCoin(), mDataBean.getNeedCoin(), mDataBean.getName(),
//                                "还没改字段", "观看券", mDataBean.getPicture());
//                        PopupWindowUtil.show(HorizontalLive1Activity.this, videoCouponTipsPopupwindow);
//                    }
//                    break;
//            }
//        }
//    };
//
//    private PayUtil.OnPayCallBack mOnPayCallBack = new PayUtil.OnPayCallBack() {
//        @Override
//        public void onPaySuccess(int platform) {
//            onPayResult(null);
//        }
//
//        @Override
//        public void onPayFailed() {
//
//        }
//    };
//
//    private void initViewPager() {
//        fragments = new ArrayList<>();
//        tabs = getResources().getStringArray(getStringArrayId());
//        initFragments(fragments);
//        viewPager.setAdapter(new BaseFragmentAdapter(getSupportFragmentManager(), fragments));
//        tabLayout.setupWithViewPager(viewPager);
//        viewPager.setOffscreenPageLimit(getOffscreenPageLimit());
//        ViewUtil.changeTabText(tabLayout, tabs);
//
//    }
//
//    private int getOffscreenPageLimit() {
//        return 4;
//    }
//
//    private void initFragments(List<Fragment> fragments) {
//        fragments.add(new SJKLiveDetailProfileFragment());
//        roomFragment = new SJKLiveDetailRoomFragment();
//        fragments.add(roomFragment);
//        fragments.add(new SJKLiveDetailRecommendCourseFragment());
//        mSJKLiveDetailCommentFragment = new SJKLiveDetailCommentFragment();
//        fragments.add(mSJKLiveDetailCommentFragment);
//    }
//
//    private int getStringArrayId() {
//        return R.array.sjk_live_tabs;
//    }
//
//    @Override
//    public void onStart(SHARE_MEDIA share_media) {
//        if (!isFreeShare) {
//            startProgressDialog("加载中");
//        }
//    }
//
//    @Override
//    public void onResult(SHARE_MEDIA share_media) {
//        super.onResult(share_media);
//        if (mSharePopupWindow != null) {
//            mSharePopupWindow.dismiss();
//        }
//    }
//
//
//    @Override
//    public void showCourseData(SJKLiveDetailProfileBean.DataBean data) {
//
//    }
//
//    @Override
//    public void setCollect(Result result) {
////        mDataBean.setCollect(!mDataBean.isCollect());
////        mMediaController.setCollect(mDataBean.isCollect());
////        ToastUitl.showShort(result.getResultMsg());
//    }
//
//    @Override
//    public void setLike(Result result) {
////        mDataBean.setLike(!mDataBean.isLike());
////        mMediaController.setLikes(mDataBean.isLike());
////        ToastUitl.showShort(result.getResultMsg());
//    }
//
//    @Override
//    public void setUserCount(String userCount) {
//        mMediaController.setWatchCount(Integer.parseInt(userCount));
//    }
//
//    @Override
//    public void onPayResult(String url) {
//        PayResultPopupWindow payResultPopupwindow = new PayResultPopupWindow(this);
//        payResultPopupwindow.setResult(true);
//        PopupWindowUtil.show(this, payResultPopupwindow);
//        mDataBean.setHasBuy(true);
//        aRewardView.setVisibility(View.GONE);
//        mMediaController.showShikan(false);
//        initIsBuy();
//    }
//    private List<GiftBean.DataBean> giftList;
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
//    @Override
//    public void showListGift(List<GiftBean.DataBean> beanList) {
//        giftList = beanList;
//        showGiftDialog();
//    }
//
//    @Override
//    public void giveGiftResult(GiveGiftResultBean.DataBean result) {
//        mDataBean.setUserCoin(result.getYue());
//        if (giveGiftPopupwindow != null) {
//            giveGiftPopupwindow.setBalance(result.getYue());
//        }
//    }
//
//    @Override
//    public void playUrl(VideoSectionEntry dirBean) {
//        LogUtils.logd("videoSectionEntry"+dirBean);
//        if (dirBean.getTag()==0 ){
//            HorizontalHistoryLiveActivity.startAction(this,courseId,0);
//            return;
//        }else if (dirBean.getTag() == 2 ){
//            ToastUitl.showShort("课程还未开始");
//            return;
//        }
////        if (currentDirbean == dirBean) {
////            if (isSetVideoPath) {
////                mMediaController.showShikan(View.GONE);
////                mMediaController.setPlay();
////            } else if (mDataBean.isHasBuy() || dirBean.isFree()) {
////                reStart(dirBean);
////            } else {
////                mMediaController.showShikan(View.VISIBLE);
////                mVideoPlayerHelper.stop();
////                isSetVideoPath = false;
////                ToastUitl.showShort("该章节需要购买课程或成为VIP才能观看!");
////            }
////            return;
////        }
////        if (mDataBean.isHasBuy() || dirBean.isFree()) {
////            reStart(dirBean);
////        } else {
////            mMediaController.showShikan(View.VISIBLE);
////            mVideoPlayerHelper.stop();
////            isSetVideoPath = false;
////            ToastUitl.showShort("该章节需要购买课程或成为VIP才能观看");
////        }
//    }
//
//    @Override
//    public void playShiKan(VideoSectionEntry videoSectionEntry) {
//
//    }
//
//    @Override
//    public int isLiveStreaming() {
//        return 1;
//    }
//
//    @Override
//    public void changeScreen(boolean isFull) {
//        if (isFull) {
//            if (mSJKRoomHelper != null) {
//                mSJKRoomHelper.onFragmentVisibleChange(true);
//            }
//            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT,
//                    RelativeLayout.LayoutParams.FILL_PARENT);
//            contentContoller.setVisibility(View.GONE);
//            videoContoller.setLayoutParams(layoutParams);
//            if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
//                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//            }
//            ViewUtil.setSystemUiHide(this, false);
//
//        } else {
//            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, defVideoHeight);
//            videoContoller.setLayoutParams(lp);
//            if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
//                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//            }
//            ViewUtil.setSystemUiShow(this);
//        }
//        contentContoller.setVisibility(isFull ? View.GONE : View.VISIBLE);
//    }
//
//    @Override
//    public void onBack() {
//        try {
//            boolean flag = true;
//            if (mMediaController.isFullScreen()) {
//                mMediaController.changeScreen();
//                changeScreen(false);
//                flag = false;
//            }
//            if (flag) {
//                super.onBackPressed();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void onBackPressed() {
//        onBack();
//    }
//
//    @Override
//    public void onShare() {
//        if (mSharePopupWindow ==null){
//            mSharePopupWindow = new SharePopupWindow(this);
//            mSharePopupWindow.setShareUrl(mDataBean.getShareUrl());
//            mSharePopupWindow.setShareTitle(mDataBean.getShareTitle());
//            mSharePopupWindow.setShareDescript(mDataBean.getShareDescript());
//        }
//        PopupWindowUtil.show(this, mSharePopupWindow);
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        mVideoPlayerHelper.onPause();
//        KeyBordUtil.hideSoftKeyboard(contentContoller);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        mVideoPlayerHelper.onResume();
//        isFreeShare = false;
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mVideoPlayerHelper.onDestroy();
//        mSJKRoomHelper.onDestroy();
//    }
//
//    @Override
//    public void postShiKan() {
//        mPresenter.postShiKan(ApiKey.COURSE_SHIKAN, MapUtils.getCourseMap(courseId));
//    }
//
//    @Override
//    public void showBuyTip() {
//        mMediaController.showShikan(false);
//    }
//
//    @Override
//    public boolean isBuyCourse() {
//        return EmptyUtils.isNotEmpty(mDataBean) && mDataBean.isHasBuy();
//    }
//
//    @Override
//    public void historyShiKan() {
//
//    }
//
//    @Override
//    public void sendMessage() {
//        if (mSJKRoomHelper != null) {
//            showKeyBoard(true);
//        }
//    }
//    public void showKeyBoard(boolean show) {
//        mSJKRoomHelper.showInputPanel(show);
//        if (show) {
//            mMediaController.hide();
//        } else {
//            mMediaController.show();
//        }
//        mSJKRoomHelper.showKeyboard(show);
//    }
//    @Override
//    public void giveGift() {
//        if (EmptyUtils.isNotEmpty(giftList)) {
//            showGiftDialog();
//        } else {
//            mPresenter.listGift(ApiKey.COURSE_LIST_GIFT, MapUtils.getDefMap(false));
//        }
//    }
//
//    @Override
//    public void getChatListView(ChatCustomView chatCustomView) {
//        LogUtils.logd("chatCustomView"+chatCustomView);
//        mSJKRoomHelper = new SJKRoomHelper(chatCustomView,false);
//        mSJKRoomHelper.startGetUserCount();
//    }
//
//}
