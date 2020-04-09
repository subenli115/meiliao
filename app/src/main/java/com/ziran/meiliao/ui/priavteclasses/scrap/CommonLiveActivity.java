//package com.ziran.meiliao.ui.priavteclasses.activity;
//
//import android.content.pm.ActivityInfo;
//import android.support.design.widget.TabLayout;
//import android.support.v4.app.Fragment;
//import android.support.v4.view.ViewPager;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//
//import com.pili.pldroid.player.widget.PLVideoTextureView;
//import com.ziran.meiliao.R;
//import com.ziran.meiliao.common.base.BaseFragmentAdapter;
//import com.ziran.meiliao.common.commonutils.ToastUitl;
//import com.ziran.meiliao.common.commonutils.ViewUtil;
//import com.ziran.meiliao.common.compressorutils.EmptyUtils;
//import com.ziran.meiliao.common.okhttp.Result;
//import com.ziran.meiliao.constant.ApiKey;
//import com.ziran.meiliao.constant.AppConstant;
//import com.ziran.meiliao.entry.VideoSectionEntry;
//import com.ziran.meiliao.envet.BuyCourseCallBack;
//import com.ziran.meiliao.envet.CommonMediaControllerListener;
//import com.ziran.meiliao.envet.LiveCallBack;
//import com.ziran.meiliao.ui.base.CommonModel;
//import com.ziran.meiliao.ui.base.ShareActivity;
//import com.ziran.meiliao.ui.bean.SJKLiveDetailProfileBean;
//import com.ziran.meiliao.ui.priavteclasses.contract.SJKFullLiveContract;
//import com.ziran.meiliao.ui.priavteclasses.fragment.SJKLiveDetailCommentFragment;
//import com.ziran.meiliao.ui.priavteclasses.model.ItemPlayCallBack;
//import com.ziran.meiliao.ui.priavteclasses.presenter.SJKCoursePresenter;
//import com.ziran.meiliao.ui.priavteclasses.util.LiveRoomUtil;
//import com.ziran.meiliao.ui.priavteclasses.util.VideoPlayerHelper;
//import com.ziran.meiliao.utils.HomePageMusicManager;
//import com.ziran.meiliao.utils.MapUtils;
//import com.ziran.meiliao.utils.PayUtil;
//import com.ziran.meiliao.widget.ARewardV2View;
//import com.ziran.meiliao.widget.LiveMediaController;
//import com.ziran.meiliao.widget.pupop.JoinVipPopupWindow;
//import com.ziran.meiliao.widget.pupop.PayResultPopupWindow;
//import com.ziran.meiliao.widget.pupop.PopupWindowUtil;
//import com.ziran.meiliao.widget.pupop.SharePopupWindow;
//import com.ziran.meiliao.widget.pupop.UseVideoCouponPopupWindow;
//import com.ziran.meiliao.widget.pupop.VideoCouponTipsPopupWindow;
//import com.umeng.socialize.bean.SHARE_MEDIA;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import butterknife.Bind;
//import rx.functions.Action1;
//
///**
// * Created by Administrator on 2017/3/6.
// */
//
//public abstract class CommonLiveActivity<T extends SJKCoursePresenter, E extends CommonModel> extends ShareActivity<T, E> implements
//        SJKFullLiveContract.View, LiveCallBack, BuyCourseCallBack, ItemPlayCallBack, CommonMediaControllerListener {
//
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
//    @Bind(R.id.media_controller)
//    protected LiveMediaController mMediaController;
//
//    @Bind(R.id.VideoView)
//    protected PLVideoTextureView mVideoView;
//    @Bind(R.id.LoadingView)
//    protected View mLoadingView;
//    @Bind(R.id.coverView)
//    protected ImageView coverView;
//    protected String[] tabs;
//    protected List<Fragment> fragments;
//    protected int defVideoHeight;
//    protected int mIsLiveStreaming;
//
//    private SharePopupWindow mSharePopupWindow;
//    protected String mVideoPath = null;
//    protected String courseId;
//    protected SJKLiveDetailCommentFragment mSJKLiveDetailCommentFragment;
//    protected boolean isPlayerEnd = false;
//    public String currentVideoPath;
//    protected VideoPlayerHelper mVideoPlayerHelper;
//
//    protected boolean isInitView = false;
//
//    @Override
//    public int getLayoutId() {
//        return R.layout.activity_sjk_live;
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
//            initViewPager();
//            initVideo();
//            isInitView = false;
//        }
//    }
//
//    private void initVideo() {
//        HomePageMusicManager.get().res(true);
//        mVideoPlayerHelper = new VideoPlayerHelper(this);
//        mVideoPlayerHelper.init(mVideoView, mLoadingView, mMediaController, coverView);
//        mIsLiveStreaming = getIntent().getIntExtra(AppConstant.ExtraKey.LIVE_STREAMING, 1);
//        mVideoPlayerHelper.setIsLiveStreaming(mIsLiveStreaming, 2);
//
//        mRxManager.on(AppConstant.RXTag.VIDEO_PATH, new Action1<SJKLiveDetailProfileBean.DataBean>() {
//            @Override
//            public void call(final SJKLiveDetailProfileBean.DataBean bean) {
//                if (bean == null) return;
//                mDataBean = bean;
//                mDataBean.setCourseId(courseId);
//                LiveRoomUtil.get().onDestroy(mDataBean);
////                MyValueTempCache.get().put(AppConstant.DOWN_COURSE, mDataBean);
//                mMediaController.setBuy(mDataBean.isHasBuy());
//                mMediaController.setCollect(mDataBean.isCollect());
//                mMediaController.setLikes(mDataBean.isLike());
//                mMediaController.setWachCount(mDataBean.getWatchCount());
//                mMediaController.setLikeCount(mDataBean.getLikeCount());
//                mMediaController.setName(mDataBean.getName());
//                currentDirbean = mVideoPlayerHelper.getCurrentSection(bean);
//
//                mVideoPath = mVideoPlayerHelper.getCurrentVideoPath(bean);
//                if (bean.isHasBuy()) {
//                    initIsBuy();
//                    aRewardView.setVisibility(View.GONE);
//                } else {
//                    aRewardView.setVisibility(View.VISIBLE);
//                    aRewardView.setCount(1);
//                    aRewardView.setNeedCoin("课程",mDataBean.getNeedCoin());
//                    mVideoPlayerHelper.checkShiKan(mDataBean.isShikan(), mVideoPath);
//                }
//            }
//        });
//
//        mRxManager.on(AppConstant.RXTag.BALANCE, new Action1<String>() {
//            @Override
//            public void call(String balance) {
//                mDataBean.setUserCoin(Integer.parseInt(balance));
//            }
//        });
//        mRxManager.on(AppConstant.RXTag.REQ_BUY_COURSE, new Action1<Boolean>() {
//            @Override
//            public void call(Boolean aBoolean) {
//                mPresenter.buyCourse(ApiKey.COURSE_BUY, MapUtils.getCourseMap(courseId));
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
//        aRewardView.setOnClick(onArewardClickListener);
//        if (mIsLiveStreaming==0){
//            mVideoPlayerHelper.setPlayPauseView(mMediaController.getPlayOrPauseView());
//        }
//        mMediaController.setCommonMediaControllerListener(this);
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
//    @Override
//    public void changeScreen(boolean isFull) {
//        setFull(isFull);
//    }
//
//    @Override
//    public void onBack() {
//        onBackClick();
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
//
//    }
//
//    private View.OnClickListener onArewardClickListener = new View.OnClickListener() {
//        @Override
//        public void onContentClick(View v) {
//            switch (v.getId()) {
//                case R.id.ll_horizontal_use_video_coupon_vip:
//                    JoinVipPopupWindow popupwindow = new JoinVipPopupWindow(CommonLiveActivity.this);
//                    popupwindow.setOnPayCallBack(mOnPayCallBack);
//                    PopupWindowUtil.show(popupwindow);
//                    break;
//                case R.id.ll_horizontal_use_video_coupon_buy:
//                    if (mDataBean.isHasTick()) {
//                        UseVideoCouponPopupWindow useVideoCouponPopupwindow = new UseVideoCouponPopupWindow(CommonLiveActivity.this);
//                        useVideoCouponPopupwindow.setData(mDataBean.getUserTick());
//                        PopupWindowUtil.show(CommonLiveActivity.this, useVideoCouponPopupwindow);
//                    } else {
//                        VideoCouponTipsPopupWindow videoCouponTipsPopupwindow = new VideoCouponTipsPopupWindow(CommonLiveActivity.this);
//                        isFreeShare = true;
//                        videoCouponTipsPopupwindow.setCourseId(mDataBean.getCourseId());
//                        videoCouponTipsPopupwindow.setParams(mDataBean.getUserCoin(), mDataBean.getNeedCoin(), mDataBean.getName(),
//                                "还没改字段", "观看券", mDataBean.getPicture());
//                        PopupWindowUtil.show(CommonLiveActivity.this, videoCouponTipsPopupwindow);
//                    }
//                    break;
//            }
//        }
//    };
//
//    public SJKLiveDetailProfileBean.DataBean mDataBean;
//    private boolean isFreeShare = false;
//    //初始化ViewPager
//    private void initViewPager() {
//        fragments = new ArrayList<>();
//        tabs = getResources().getStringArray(getStringArrayId());
//        initFragments(fragments);
//        viewPager.setAdapter(new BaseFragmentAdapter(getSupportFragmentManager(), fragments));
//        tabLayout.setupWithViewPager(viewPager);
//        viewPager.setOffscreenPageLimit(getOffscreenPageLimit());
//        ViewUtil.changeTabText(tabLayout, tabs);
////        ViewUtil.initViewPager(viewPager,tabLayout);
//    }
//
//    protected int getOffscreenPageLimit() {
//        return 3;
//    }
//
//    protected abstract int getStringArrayId();
//
//    protected void initIsBuy() {
//    }
//
//    protected abstract void initFragments(List<Fragment> fragments);
//
//
//    @Override
//    public void onResult(SHARE_MEDIA share_media) {
//        super.onResult(share_media);
//        if (mSharePopupWindow!=null){
//            mSharePopupWindow.dismiss();
//        }
//    }
//
//
//    public boolean isBuy() {
//        return mDataBean != null && mDataBean.isHasBuy();
//    }
//
//    public void setFull(boolean isFull) {
//        if (isFull) {
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
//
//
//    public void onBackPressed() {
//        onBackClick();
//    }
//
//    private void onBackClick() {
//        try {
//            boolean flag = true;
//            if (mMediaController.isFullScreen()) {
//                mMediaController.changeScreen();
//                setFull(false);
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
//    @Override
//    protected void onPause() {
//        super.onPause();
//        mVideoPlayerHelper.onPause();
////        KeyboardUtil.hideKeyboard(contentContoller);
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
//    }
//
//
//    protected VideoSectionEntry currentDirbean;
//
//    public void playUrl(VideoSectionEntry dirBean) {
//        if (dirBean.getTag()==0 && isLiveStreaming()==1){
//            HorizontalHistoryActivity.startAction(this,courseId,0);
//            return;
//        }else if (dirBean.getTag() == 2  && isLiveStreaming()==1){
//            ToastUitl.showShort("课程还未开始");
//            return;
//        }
//        if (currentDirbean == dirBean) {
//            if (isSetVideoPath) {
//                mMediaController.showShikan(View.GONE);
//                mMediaController.setPlay();
//            } else if (mDataBean.isHasBuy() || dirBean.isFree()) {
//                reStart(dirBean);
//            } else {
//                mMediaController.showShikan(View.VISIBLE);
//                mVideoPlayerHelper.stop();
//                isSetVideoPath = false;
//                ToastUitl.showShort("该章节需要购买课程或成为VIP才能观看!");
//            }
//            return;
//        }
//        if (mDataBean.isHasBuy() || dirBean.isFree()) {
//            reStart(dirBean);
//        } else {
//            mMediaController.showShikan(View.VISIBLE);
//            mVideoPlayerHelper.stop();
//            isSetVideoPath = false;
//            ToastUitl.showShort("该章节需要购买课程或成为VIP才能观看");
//        }
//    }
//
//    private void reStart(VideoSectionEntry dirBean) {
//        currentDirbean = dirBean;
//        mMediaController.setIsShiKanPlay(dirBean.isFree());
//        mMediaController.setMax(dirBean.getDuration());
//        mVideoPath = currentDirbean.getUrlAndPath();
//        updateStudy(dirBean,false);
//        isSetVideoPath = false;
//        mMediaController.showShikan(View.GONE);
//        doPlayOrPause();
//    }
//
//    protected boolean isSetVideoPath;
//
//    public boolean doPlayOrPause() {
//        return true;
//    }
//
//
//    @Override
//    public void onStart(SHARE_MEDIA share_media) {
//        if (!isFreeShare){
//            startProgressDialog("加载中");
//        }
//    }
//
//    @Override
//    public void showBuyTip() {
//        mMediaController.showShikan(View.VISIBLE);
//        mVideoPlayerHelper.stop();
//    }
//
//    @Override
//    public boolean isBuyCourse() {
//        return EmptyUtils.isNotEmpty(mDataBean) && mDataBean.isHasBuy();
//    }
//
//    @Override
//    public void historyShiKan() {
//        mMediaController.historyShiKan();
//    }
//
//
//    @Override
//    public boolean hasBuy() {
//        return isBuyCourse();
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
//        mDataBean.setCollect(!mDataBean.isCollect());
//        mMediaController.setCollect(mDataBean.isCollect());
//        ToastUitl.showShort(result.getResultMsg());
//    }
//
//    @Override
//    public void setLike(Result result) {
//        mDataBean.setLike(!mDataBean.isLike());
//        mMediaController.setLikes(mDataBean.isLike());
//        ToastUitl.showShort(result.getResultMsg());
//    }
//
//    @Override
//    public void setUserCount(String userCount) {
//
//    }
//
//    @Override
//    public void onPayResult(String url) {
//        PayResultPopupWindow payResultPopupwindow = new PayResultPopupWindow(this);
//        payResultPopupwindow.setResult(true);
//        PopupWindowUtil.show(this, payResultPopupwindow);
//        mDataBean.setHasBuy(true);
//        if (mIsLiveStreaming == 1) {
//            mVideoPath = mDataBean.getDetailUrl();
//        } else {
//            mVideoPath = currentVideoPath;
//        }
//        aRewardView.setVisibility(View.GONE);
//        mMediaController.showShikan(View.GONE);
//        mMediaController.setBuy(true);
//        initIsBuy();
//    }
//
//    protected void updateStudy(VideoSectionEntry dirBean,boolean isPlayerEnd) {
//        if (dirBean.getStudyStatus() != 2 && mIsLiveStreaming == 0) {
//            Map<String, String> stringMap = MapUtils.getOnlyCan("courseId", dirBean.getCourseId());
//            if (isPlayerEnd){
//                stringMap.put("progress", "100");
//            }else{
//                int progress = (int) (mVideoView.getCurrentPosition() * 100 / mVideoView.getDuration());
//                stringMap.put("progress", String.valueOf(progress));
//            }
//
//            mPresenter.updateStudy(ApiKey.BANK_CARD_CHECK_CARD, stringMap);
//        }
//    }
//
//    @Override
//    public int isLiveStreaming() {
//        return mIsLiveStreaming;
//    }
//
//    @Override
//    public void playShiKan(VideoSectionEntry videoSectionEntry) {
//
//    }
//}
