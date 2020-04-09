package com.ziran.meiliao.ui.priavteclasses.fragment;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.pili.pldroid.player.AVOptions;
import com.pili.pldroid.player.widget.PLVideoTextureView;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.base.BaseModel;
import com.ziran.meiliao.common.commonutils.DisplayUtil;
import com.ziran.meiliao.common.commonutils.KeyBordUtil;
import com.ziran.meiliao.common.commonutils.SPUtils;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonwidget.ViewDragHelperLayout;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.entry.VideoSectionEntry;
import com.ziran.meiliao.envet.LiveCallBack;
import com.ziran.meiliao.ui.base.CommonHttpFragment;
import com.ziran.meiliao.ui.bean.GiftBean;
import com.ziran.meiliao.ui.bean.GiveGiftResultBean;
import com.ziran.meiliao.ui.bean.SJKLiveDetailProfileBean;
import com.ziran.meiliao.ui.priavteclasses.activity.VerticalLiveActivity;
import com.ziran.meiliao.ui.priavteclasses.contract.SJKFullLiveContract;
import com.ziran.meiliao.ui.priavteclasses.model.ItemPlayCallBack;
import com.ziran.meiliao.ui.priavteclasses.presenter.SJKCoursePresenter;
import com.ziran.meiliao.ui.priavteclasses.util.LiveRoomUtil;
import com.ziran.meiliao.ui.priavteclasses.util.VideoPlayerHelper;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.MusicPanelFloatManager;
import com.ziran.meiliao.widget.KeyboardHeightLayout;
import com.ziran.meiliao.widget.LiveAvatarView;
import com.ziran.meiliao.widget.NetWorkStateReceiver;
import com.ziran.meiliao.widget.pupop.BasePopupWindow;
import com.ziran.meiliao.widget.pupop.HasTickPopupWindow;
import com.ziran.meiliao.widget.pupop.PayResultPopupWindow;
import com.ziran.meiliao.widget.pupop.PopupWindowUtil;
import com.ziran.meiliao.widget.spotlight.CustomTarget;
import com.ziran.meiliao.widget.spotlight.OnSpotlightEndedListener;
import com.ziran.meiliao.widget.spotlight.OnSpotlightStartedListener;
import com.ziran.meiliao.widget.spotlight.Spotlight;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;


/**
 * 私家课-活动Fragment
 * Created by Administrator on 2017/1/7.
 */

public class SJKVerticalLiveBaseFragment<T extends SJKCoursePresenter, E extends BaseModel> extends CommonHttpFragment<T, E> implements
        SJKFullLiveContract.View, LiveCallBack, ItemPlayCallBack {
    @Bind(R.id.videoView)
    protected PLVideoTextureView mVideoView;

    //视频播放区背景
    @Bind(R.id.LoadingView)
    protected View mLoadingView;


    @Bind(R.id.liveAvatarView)
    protected LiveAvatarView mLveAvatarView;


    @Bind(R.id.ll_sjk_fulllive_share)
    View llSjkFullliveBottom;
    @Bind(R.id.iv_sjk_fulllive_buycourse)
    Button btnBuyCourse;


    @Bind(R.id.view_drag_helper)
    ViewDragHelperLayout viewDragHelperLayout;


    @Bind(R.id.iv_sjk_fulllive_coverview)
    ImageView ivCoverView;
    @Bind(R.id.iv_sjk_fulllive_more)
    ImageView ivMoreView;
    @Bind(R.id.fl_sjk_fulllive_shikan)
    View fullLiveShiKan;

    protected String courseId;
    protected VerticalLiveActivity mVerticalOpenLiveActivity;
    private boolean isLoadData;
    protected SJKLiveDetailProfileBean.DataBean mDataBean;
    protected KeyboardHeightLayout mKeyboardHeightLayout;
    private int isLiveStreaming;
    private NetWorkStateReceiver netWorkStateReceiver;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_sjk_vertical_live;
    }

    protected VideoPlayerHelper mVideoPlayerHelper;

    @Override
    protected void initView() {
        MusicPanelFloatManager.getInstance().res(true);
        mVerticalOpenLiveActivity = (VerticalLiveActivity) getActivity();
        mKeyboardHeightLayout = (KeyboardHeightLayout) getActivity().findViewById(R.id.fl_sjk_fulllive_root);
    }

    @Override
    protected void initOther() {

        mVideoPlayerHelper = new VideoPlayerHelper(this);
        int codec = getActivity().getIntent().getIntExtra(AVOptions.KEY_MEDIACODEC, AVOptions.MEDIA_CODEC_AUTO);
        isLiveStreaming = getActivity().getIntent().getIntExtra(AppConstant.ExtraKey.LIVE_STREAMING, 1);
        courseId = getIntentExtra(getActivity().getIntent(), AppConstant.SPKey.COURSE_ID);
        mVideoPlayerHelper.init(mVideoView, mLoadingView, null, ivCoverView);
        mVideoPlayerHelper.setOptions(mVideoView, codec, isLiveStreaming);

        viewDragHelperLayout.setOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mVerticalOpenLiveActivity != null && mVerticalOpenLiveActivity.isShowMore()) {
                    mVerticalOpenLiveActivity.hideMore();
                } else if (mKeyboardHeightLayout.isShowKeyBoard()) {
                    KeyBordUtil.hideSoftKeyboard(fullLiveShiKan);
                } else {
                    showOrHide(viewDragHelperLayout.changeChildViewShown());
                }
            }
        });

        viewDragHelperLayout.setCanTouch(false);
        mPresenter.getCourseData(ApiKey.GET_COURSE_DETAIL, MapUtils.getCourseMap(courseId));
        mRxManager.on(AppConstant.RXTag.REQ_BUY_COURSE, new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                mPresenter.buyCourse(ApiKey.COURSE_BUY, MapUtils.getCourseMap(courseId));
            }
        });
        mRxManager.on(AppConstant.RXTag.USER_TICK, new Action1<SJKLiveDetailProfileBean.DataBean.UserTickBean>() {
            @Override
            public void call(SJKLiveDetailProfileBean.DataBean.UserTickBean userTickBean) {
                Map<String, String> courseMap = MapUtils.getCourseMap(courseId);
                if (EmptyUtils.isNotEmpty(userTickBean.getTickId())) {
                    courseMap.put("tickId", userTickBean.getTickId());
                } else {
                    courseMap.put("tickId", mDataBean.getUserTick().get(0).getTickId());
                }
                mPresenter.usedTick(ApiKey.COURSE_TICKET_USETICK, courseMap);
            }
        });
        boolean firstTips = SPUtils.getBoolean(AppConstant.SPKey.COURSE_FRIST_TIPS + MyAPP.getUserInfo().getPhone() + isLiveStreaming,
                true);
        if (firstTips) {
            HandlerUtil.runMain(new Runnable() {
                @Override
                public void run() {
                    showFirstTips();
                    SPUtils.setBoolean(AppConstant.SPKey.COURSE_FRIST_TIPS + MyAPP.getUserInfo().getPhone() + isLiveStreaming, false);
                }
            }, 20);
        }
    }

    private Spotlight mSpotlight;

    private void showFirstTips() {
        if (mSpotlight == null) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.spotilight_moble_live, null);
            View downView = view.findViewById(R.id.iv_down);
            View moreView = view.findViewById(R.id.iv_more);
            view.findViewById(R.id.iv_spotlight_close).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mSpotlight != null) {
                        mSpotlight.finishSpotlight();
                        mSpotlight = null;
                    }
                }
            });
            int[] location = new int[2];
            ivMoreView.getLocationInWindow(location);
            moreView.setX(location[0]);
            downView.setX(location[0] - DisplayUtil.dip2px(8));
            int navigationBarHeight = DisplayUtil.getNavigationBarHeightEx(getContext()) - DisplayUtil.dip2px(2);
            if (navigationBarHeight > 0) view.setPadding(0, 0, 0, navigationBarHeight);
            CustomTarget thirdTarget = new CustomTarget.Builder(getActivity()).setPoint(ivMoreView).setRadius(50f).setView(view).build();
            mSpotlight = Spotlight.with(getActivity()).setDuration(1000L).setAnimation(new DecelerateInterpolator(2f)).setTargets
                    (thirdTarget).setOnSpotlightStartedListener(new OnSpotlightStartedListener() {
                @Override
                public void onStarted() {
                    ivMoreView.setVisibility(View.INVISIBLE);
                }
            }).setOnSpotlightEndedListener(new OnSpotlightEndedListener() {
                @Override
                public void onEnded() {
                    ivMoreView.setVisibility(View.VISIBLE);
                }
            }).start();
        }
    }

    protected void showOrHide(boolean viewShown) {

    }

    @Override
    public void onPause() {
        getActivity().unregisterReceiver(netWorkStateReceiver);
        super.onPause();
        mVideoPlayerHelper.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (netWorkStateReceiver == null) {
            netWorkStateReceiver = new NetWorkStateReceiver();
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        getActivity().registerReceiver(netWorkStateReceiver, filter);
        mVideoPlayerHelper.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mVideoPlayerHelper.onDestroy();
    }


    @OnClick({R.id.iv_sjk_fulllive_share, R.id.iv_sjk_fulllive_close, R.id.iv_sjk_fulllive_more, R.id.iv_sjk_vertical_live_buyvip, R.id
            .iv_sjk_fulllive_buycourse})
    public void onViewClicked(View view) {
        try {
            switch (view.getId()) {
                case R.id.iv_sjk_vertical_live_buyvip:
                    mVerticalOpenLiveActivity.buyVip();
                    break;
                case R.id.iv_sjk_fulllive_buycourse:
                    mVerticalOpenLiveActivity.buyCourse(mDataBean);
                    break;

                case R.id.iv_sjk_fulllive_share:
                    if (mVerticalOpenLiveActivity != null) {
                        mVerticalOpenLiveActivity.onShare(mDataBean);
                    }
                    break;

                case R.id.iv_sjk_fulllive_more:
                    mVerticalOpenLiveActivity.showMore();
                    break;

                case R.id.iv_sjk_fulllive_close:
                    close();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void close() {
        finish();
    }

    public boolean onBackAction() {
        if (mSpotlight != null) {
            mSpotlight.finishSpotlight();
            mSpotlight = null;
            return true;
        }
        return false;
    }

    public SJKLiveDetailProfileBean.DataBean getDataBean() {
        return mDataBean;
    }


    @Override
    public void postShiKan() {
        mPresenter.postShiKan(ApiKey.COURSE_SHIKAN, MapUtils.getCourseMap(courseId));
    }

    @Override
    public void showBuyTip() {
        setUIState(false);
        mLoadingView.setVisibility(View.GONE);
    }

    @Override
    public boolean isBuyCourse() {
        return mDataBean.isHasBuy();
    }

    @Override
    public void historyShiKan() {

    }

    @Override
    public void showCourseData(SJKLiveDetailProfileBean.DataBean result) {
        try {
            if (!isLoadData) {
                mDataBean = result;
                mDataBean.setCourseId(courseId);
                mRxManager.post(AppConstant.RXTag.VIDEO_PATH, mDataBean);
                if (mDataBean.getUserCoin() < mDataBean.getNeedCoin()) {
                    btnBuyCourse.setText("购买课程");
                }
                LiveRoomUtil.get().onDestroy(mDataBean);
                if (result.isHasBuy()) {
                    if (isLiveStreaming == 1) {
                        startPlay();
                    } else {
                        mLoadingView.setVisibility(View.GONE);
                    }
                } else {
                    if (mDataBean.isHasTick()) {
                        HasTickPopupWindow hasTickPopupwindow = new HasTickPopupWindow(getContext());
                        hasTickPopupwindow.setOnDissmisListener(new BasePopupWindow.OnDismissListener() {
                            @Override
                            public void onDismiss() {
                                mVideoPlayerHelper.checkShiKan(mDataBean.isShikan(), mDataBean.getUrl());
                            }
                        });
                        hasTickPopupwindow.setParmes(mDataBean.getAuthor().getName(), mDataBean.getPicture(), mDataBean.getAuthor()
                                .getTeaIntro());
                        PopupWindowUtil.show(getActivity(), hasTickPopupwindow);
                    } else {
                        mVideoPlayerHelper.checkShiKan(mDataBean.isShikan(), mDataBean.getUrl());
                    }
                }
                if (EmptyUtils.isNotEmpty(mDataBean.getAuthor())){
                    mLveAvatarView.setImageUrl(mDataBean.getAuthor().getHeadImg());
                }
                isLoadData = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void startPlay() {
        mVideoPlayerHelper.startPlay(mDataBean.getUrl());
    }

    @Override
    public void setCollect(Result result) {

    }

    @Override
    public void setLike(Result result) {
    }

    @Override
    public void setUserCount(String userCount) {
    }

    @Override
    public void onPayResult(String url) {
        PayResultPopupWindow payResultPopupwindow = new PayResultPopupWindow(getContext());
        payResultPopupwindow.setResult(true);
        PopupWindowUtil.show(getActivity(), payResultPopupwindow);
        mDataBean.setHasBuy(true);
        setUIState(true);
    }

    @Override
    public void showListGift(List<GiftBean.DataBean> beanList) {

    }

    @Override
    public void giveGiftResult(GiveGiftResultBean.DataBean result) {

    }

    //是否隐藏购买提示
    public void setUIState(boolean state) {
        fullLiveShiKan.setVisibility(state ? View.GONE : View.VISIBLE);

    }


    public void setNeedMeasure(boolean flag) {
        mKeyboardHeightLayout.setNeedMeasure(flag);
    }

    protected VideoSectionEntry currentDirbean;
    protected boolean isSetVideoPath;

    @Override
    public void playUrl(VideoSectionEntry dirBean) {
        if (dirBean.getTag() == 0 && isLiveStreaming() == 1) {
            VerticalLiveActivity.startAction(getContext(), courseId, 0);
            return;
        } else if (dirBean.getTag() == 2 && isLiveStreaming() == 1) {
            ToastUitl.showShort("课程还未开始");
            return;
        }

        if (currentDirbean == dirBean) {
            if (isSetVideoPath) {
                if (mVideoView.isPlaying()){
                    mVideoView.pause();
                }else{
                    mVideoView.start();
                }
            } else if (mDataBean.isHasBuy() || dirBean.isFree()) {
                reStart(dirBean);
            } else {
                mVideoPlayerHelper.stop();
                isSetVideoPath = false;
                ToastUitl.showShort("该章节需要购买课程或成为VIP才能观看!");
            }
            return;
        }
        if (mDataBean.isHasBuy() || dirBean.isFree()) {
            reStart(dirBean);
        } else {
            mVideoPlayerHelper.stop();
            isSetVideoPath = false;
            ToastUitl.showShort("该章节需要购买课程或成为VIP才能观看");
        }
    }

    @Override
    public void playShiKan(VideoSectionEntry videoSectionEntry) {

    }

    public void reStart(VideoSectionEntry dirBean) {
        currentDirbean = dirBean;
        isSetVideoPath = true;
        updateStudy(dirBean,false);
        mVideoPlayerHelper.startPlay(currentDirbean.getUrlAndPath());
    }

    protected void updateStudy(VideoSectionEntry dirBean,boolean isPlayerEnd) {
        if (dirBean.getStudyStatus() != 2 && isLiveStreaming == 0) {
            Map<String, String> stringMap = MapUtils.getOnlyCan("courseId", dirBean.getCourseId());
            if (isPlayerEnd){
                stringMap.put("progress", "100");
            }else{
                int progress = (int) (mVideoView.getCurrentPosition() * 100 / mVideoView.getDuration());
                stringMap.put("progress", String.valueOf(progress));
            }

            mPresenter.updateStudy(ApiKey.BANK_CARD_CHECK_CARD, stringMap);
        }
    }


    @Override
    public int isLiveStreaming() {
        return isLiveStreaming;
    }

    @Override
    public boolean isPlaying() {
        return mVideoView.isPlaying();
    }
}
