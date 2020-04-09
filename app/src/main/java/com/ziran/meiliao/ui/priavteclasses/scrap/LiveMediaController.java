//package com.ziran.meiliao.widget;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.os.Handler;
//import android.os.Message;
//import android.support.annotation.AttrRes;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.util.AttributeSet;
//import android.view.KeyEvent;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewStub;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.pili.pldroid.player.IMediaController;
//import com.ziran.meiliao.R;
//import com.ziran.meiliao.app.MyAPP;
//import com.ziran.meiliao.common.commonutils.DisplayUtil;
//import com.ziran.meiliao.common.commonutils.TimeUtil;
//import com.ziran.meiliao.common.commonutils.ViewUtil;
//import com.ziran.meiliao.envet.CommonMediaControllerListener;
//import com.ziran.meiliao.envet.HistoryMediaControllerListener;
//import com.ziran.meiliao.envet.LiveMediaControllerListener;
//import com.ziran.meiliao.ui.bean.GiftBean;
//import com.ziran.meiliao.ui.priavteclasses.util.MediaControllerUtil;
//import com.ziran.meiliao.utils.StringUtils;
//import com.ziran.meiliao.utils.UpdateVersionUtil;
//import com.ziran.meiliao.widget.livegift.GiftControl;
//import com.ziran.meiliao.widget.livegift.GiftFrameLayout;
//import com.ziran.meiliao.widget.livegift.GiftModel;
//
//import butterknife.Bind;
//import butterknife.ButterKnife;
//
///**
// * 媒体播放器的控制器
// * Created by Administrator on 2017/1/20.
// */
//
//public class LiveMediaController extends FrameLayout implements IMediaController {
//
//    private MediaPlayerControl mPlayer;
//    private int sDefaultTimeout = 5000;
//    @Bind(R.id.rl_bottom)
//    View viewBottom;
//    @Bind(R.id.rl_top)
//    View viewTop;
//    @Bind(R.id.iv_contorller_full)
//    ImageView ivFull;
//    @Bind(R.id.liveAvatarView)
//    LiveAvatarView mLiveAvatarView;
//    @Bind(R.id.iv_controller_message)
//    ImageView ivMessage;
//    @Bind(R.id.iv_controller_left_gift)
//    ImageView ivLeftGift;
//    @Bind(R.id.iv_controller_right_gift)
//    ImageView ivRightGift;
//    @Bind(R.id.iv_contorller_share)
//    ImageView ivShare;
//    @Bind(R.id.iv_contorller_collect)
//    ImageView ivCollect;
//    @Bind(R.id.iv_contorller_like)
//    ImageView ivLike;
//    @Bind(R.id.iv_contorller_back)
//    ImageView ivBack;
//    @Bind(R.id.tv_contorller_lister)
//    TextView tvControllerLister;
//    @Bind(R.id.tv_controller_title)
//    TextView tvTitle;
//    @Bind(R.id.tv_contorller_like_count)
//    TextView tvLikeCount;
//    @Bind(R.id.tv_contorller_toast)
//    TextView tvControllerToast;
//    ViewStub viewStubSeekBar;
//
//
//    ViewStub viewStubChatCustomView;
//    private  ImageView ivPlayOrPause;
//    private MyProgressView mMyProgressView;
//
//
//    private ChatCustomView mChatCustomView;
//    private int paddRight;
//    private int likeCount;
//    private static final int FADE_OUT = 1;
//    private boolean topBottomVisible;
//    private boolean isLiveOrHistory;
//    private MediaControllerUtil mMediaControllerUtil;
//
//    GiftFrameLayout mGiftFrameLayout1;
//    GiftFrameLayout mGiftFrameLayout2;
//    private GiftControl giftControl;
//    private GiftModel giftModel;
//
//    public LiveMediaController(@NonNull Context context) {
//        this(context, null);
//    }
//
//    public LiveMediaController(@NonNull Context context, @Nullable AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public LiveMediaController(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        initViews(context);
//    }
//
//    private void initViews(Context context) {
//        LayoutInflater.from(context).inflate(R.layout.layout_live_mediacontroller_full, this, true);
//        ButterKnife.bind(this, this);
//        mMediaControllerUtil = new MediaControllerUtil(mHandler);
//        ivShare.setOnClickListener(mOnClickListener);
//        ivCollect.setOnClickListener(mOnClickListener);
//        ivLike.setOnClickListener(mOnClickListener);
//        ivFull.setOnClickListener(mOnClickListener);
//        ivBack.setOnClickListener(mOnClickListener);
//        ivLeftGift.setOnClickListener(mOnClickListener);
//        ivRightGift.setOnClickListener(mOnClickListener);
//        ivMessage.setOnClickListener(mOnClickListener);
//        ivBack.setOnClickListener(mOnClickListener);
//
//        paddRight = DisplayUtil.getNavigationBarHeightEx(getContext()) - DisplayUtil.dip2px(2);
//        if (paddRight < 0) {
//            paddRight = 0;
//        }
//    }
//
//
//    @Override
//    public void setMediaPlayer(MediaPlayerControl mediaPlayerControl) {
//        this.mPlayer = mediaPlayerControl;
//    }
//
//    @Override
//    public void show() {
//        show(sDefaultTimeout);
//    }
//
//    private boolean mShowing;
//
//    @SuppressLint("HandlerLeak")
//    private Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case FADE_OUT:
//                    hide();
//                    break;
//                case 200:
//                    setTopBottomVisible(false);
//                    break;
//                case 500:
//                    if (mMediaControllerUtil != null) {
//                        if (mMediaControllerUtil.updateProgress()) {
//                            mHandler.sendEmptyMessageDelayed(500, 800);
//                        }
//                    }
//                    break;
//            }
//        }
//    };
//
//    @Override
//    public void show(int time) {
//        if (!mShowing) {
//            mShowing = true;
////            setVisibility(VISIBLE);
//        }
//        setTopBottomVisible(true);
//        if (!isLiveOrHistory && mPlayer.isPlaying()) {
//            mMediaControllerUtil.startUpdateProgressTimer();
//        }
//    }
//
//    @Override
//    public void hide() {
//        if (mShowing) {
//            mShowing = false;
//        }
//        setTopBottomVisible(false);
//        if (!isLiveOrHistory) {
//            mMediaControllerUtil.cancelUpdateProgressTimer();
//        }
//    }
//
//    @Override
//    public boolean isShowing() {
//        return mPlayer.isPlaying();
//    }
//
//    @Override
//    public void setAnchorView(View view) {
//
//    }
//
//    private CommonMediaControllerListener mCommonMediaControllerListener;
//    private LiveMediaControllerListener mLiveMediaControllerListener;
//    private HistoryMediaControllerListener mHistoryMediaControllerListener;
//
//    public boolean isAutoHide() {
//        return isAutoHide;
//    }
//
//    public void setAutoHide(boolean autoHide) {
//        isAutoHide = autoHide;
//    }
//
//    public void setCommonMediaControllerListener(CommonMediaControllerListener commonMediaControllerListener) {
//        mCommonMediaControllerListener = commonMediaControllerListener;
//    }
//
//
//    public void setLiveMediaControllerListener(LiveMediaControllerListener liveMediaControllerListener) {
//        mLiveMediaControllerListener = liveMediaControllerListener;
//    }
//
//
//    public void setHistoryMediaControllerListener(HistoryMediaControllerListener historyMediaControllerListener) {
//        mHistoryMediaControllerListener = historyMediaControllerListener;
//    }
//
//    private View.OnClickListener mOnClickListener = new OnClickListener() {
//        @Override
//        public void onContentClick(View v) {
//            switch (v.getId()) {
//                case R.id.iv_contorller_back:
//                    if (mCommonMediaControllerListener != null) {
//                        mCommonMediaControllerListener.onBack();
//                    }
//                    return;
//                case R.id.iv_contorller_full:
//                    changeScreen();
//                    if (mCommonMediaControllerListener != null) {
//                        mCommonMediaControllerListener.changeScreen(isFullScreen);
//                    }
//                    break;
//                case R.id.iv_controller_message:
//                    if (mLiveMediaControllerListener != null) {
//                        mLiveMediaControllerListener.sendMessage();
//                    }
//                    break;
//                case R.id.iv_controller_left_gift:
//                case R.id.iv_controller_right_gift:
//                    if (mLiveMediaControllerListener != null) {
//                        mLiveMediaControllerListener.giveGift();
//                    }
//                    break;
//                case R.id.iv_contorller_collect:
//                    if (mHistoryMediaControllerListener != null) {
//                        mHistoryMediaControllerListener.onCollect();
//                    }
//                    break;
//                case R.id.iv_contorller_like:
//                    if (mHistoryMediaControllerListener != null) {
//                        mHistoryMediaControllerListener.onLike();
//                    }
//                    break;
//                case R.id.iv_contorller_playOrPause:
//                    if (mHistoryMediaControllerListener != null) {
//                        mHistoryMediaControllerListener.playOrPause();
//                    }
//                    break;
//                case R.id.iv_contorller_share:
//                    if (mCommonMediaControllerListener != null) {
//                        mCommonMediaControllerListener.onShare();
//                    }
//                    break;
//            }
//            mMediaControllerUtil.startDismissTopBottomTimer();
//        }
//    };
//
//    public void setIsLiveOrHistory(boolean isLive) {
//        isLiveOrHistory = isLive;
//        initSeekBar();
//    }
//
//    //初始化进度条
//    private void initSeekBar() {
//        if (!isLiveOrHistory) {
//            if (viewStubSeekBar == null) {
//                viewStubSeekBar = ViewUtil.getView(this, R.id.viewStub_seekBar);
//                View view = viewStubSeekBar.inflate();
//                mMyProgressView = ViewUtil.getView(view, R.id.myProgressView);
//                ivPlayOrPause = ViewUtil.getView(view, R.id.iv_contorller_playOrPause);
//                ivPlayOrPause.setOnClickListener(mOnClickListener);
//                mMediaControllerUtil.setPlayer(mPlayer);
//                mMediaControllerUtil.setViews(mMyProgressView);
//                mMediaControllerUtil.setPlayPauseView(ivPlayOrPause);
//                if (mMyProgressView != null) {
//                    mMyProgressView.setCanTouchProgress(false);
//                }
//            }
//        } else {
//            if (viewStubChatCustomView == null) {
//                viewStubChatCustomView = ViewUtil.getView(this, R.id.viewStub_chat_custom_view);
//                viewStubChatCustomView.inflate();
//                mChatCustomView = ViewUtil.getView(this, R.id.chatCustomView);
//                mGiftFrameLayout1 = ViewUtil.getView(this, R.id.gift_layout1);
//                mGiftFrameLayout2 = ViewUtil.getView(this, R.id.gift_layout2);
//                giftControl = new GiftControl(mGiftFrameLayout1, mGiftFrameLayout2);
//                mChatCustomView.setChatListViewWidth(getResources().getDimensionPixelSize(R.dimen.chat_width));
//                mChatCustomView.setVisibility(GONE);
//                if (mLiveMediaControllerListener != null) {
//                    mLiveMediaControllerListener.getChatListView(mChatCustomView);
//                }
//            }
//            setAutoHide(false);
//        }
//    }
//
//
//    private void setTopBottomVisible(boolean visible) {
//        if (viewTop != null) {
//            viewTop.setVisibility(visible ? View.VISIBLE : View.GONE);
//        }
//        if (visible) {
//            mMediaControllerUtil.updateProgress();
//        }
//        if (viewBottom != null) {
//            viewBottom.setVisibility(visible ? View.VISIBLE : View.GONE);
//        }
//
//        topBottomVisible = visible;
//        if (!isAutoHide) return;
//        if (visible) {
//            if (mPlayer!=null &&  mPlayer.isPlaying()) {
//                mMediaControllerUtil.startDismissTopBottomTimer();
//            }
//            updatePausePlay();
//        } else {
//            mMediaControllerUtil.cancelDismissTopBottomTimer();
//        }
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if (isAutoHide) {
//            show(sDefaultTimeout);
//        } else {
//            switch (event.getAction()) {
//                case MotionEvent.ACTION_UP:
//                    setTopBottomVisible(!topBottomVisible);
//                    break;
//            }
//        }
//        return true;
//    }
//
//    private boolean isAutoHide = true;
//
//    @Override
//    public boolean onTrackballEvent(MotionEvent ev) {
//        if (isAutoHide) {
//            show(sDefaultTimeout);
//        } else {
//            setTopBottomVisible(!topBottomVisible);
//        }
//        return false;
//    }
//
//    @Override
//    public boolean dispatchKeyEvent(KeyEvent event) {
//        int keyCode = event.getKeyCode();
//        if (event.getRepeatCount() == 0 && (keyCode == KeyEvent.KEYCODE_HEADSETHOOK || keyCode == KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE ||
//                keyCode == KeyEvent.KEYCODE_SPACE)) {
//            doPauseResume();
//            show(sDefaultTimeout);
//            return true;
//        } else if (keyCode == KeyEvent.KEYCODE_MEDIA_STOP) {
//            if (mPlayer.isPlaying()) {
//                mPlayer.pause();
//                updatePausePlay();
//            }
//            return true;
////        } else if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_MENU) {
////            hide();
////            return true;
//        } else {
//            show(sDefaultTimeout);
//        }
//        return super.dispatchKeyEvent(event);
//    }
//
//
//    private void doPauseResume() {
//        if (mPlayer == null) return;
//        if (mPlayer.isPlaying()) mPlayer.pause();
//        else mPlayer.start();
//        updatePausePlay();
//    }
//
//    private void updatePausePlay() {
//        if (ivPlayOrPause == null || mPlayer == null) return;
//        if (mPlayer.isPlaying()) ivPlayOrPause.setImageResource(R.mipmap.ic_sjk_live_video_stop);
//        else ivPlayOrPause.setImageResource(R.mipmap.ic_sjk_live_video_play);
//
//    }
//
//    public void setCollect(boolean isCollect) {
//        ivCollect.setImageResource(isCollect ? R.mipmap.ic_jyg_player_collect_green : R.mipmap.ic_sjk_live_list_collect);
//    }
//
//    public void setLikeCount(int likeCount) {
//        this.likeCount = likeCount;
//        if (likeCount == 0) {
//            tvLikeCount.setText("");
//        } else if (likeCount > 999) {
//            tvLikeCount.setText("999+");
//        } else tvLikeCount.setText(String.valueOf(likeCount));
//    }
//
//    public void setLikes(boolean isCollect) {
//        int count = isCollect ? ++likeCount : --likeCount;
//        setLikeCount(count);
//        ivLike.setImageResource(isCollect ? R.mipmap.ic_sjk_live_list_like_pre : R.mipmap.ic_sjk_live_list_like);
//    }
//
//    public void setWachCount(int watchCount) {
//        if (watchCount == 0) {
//            tvControllerLister.setText("");
//        } else tvControllerLister.setText(String.valueOf(watchCount));
//    }
//
//    public void showShikan(int visible) {
//        mMyProgressView.setCanTouchProgress(false);
//        tvControllerToast.setVisibility(visible);
//        show(5 * 1000 * 60 * 24);
//        ivFull.setEnabled(visible == View.GONE);
//    }
//
//    @Override
//    protected void onDetachedFromWindow() {
//        super.onDetachedFromWindow();
//        ButterKnife.unbind(this);
//    }
//
//    /**
//     * 控制器恢复到初始状态
//     */
//    public void reset() {
//        topBottomVisible = false;
//        mMediaControllerUtil.cancelUpdateProgressTimer();
//        mMediaControllerUtil.cancelDismissTopBottomTimer();
//        mMyProgressView.resProgress();
//
//    }
//
//    public void giveGift(GiftBean.DataBean giftBean) {
//        giftModel = new GiftModel(giftBean.getName(), giftBean.getName(), 1, giftBean.getPath(), "1", MyAPP.getUserInfo().getNickName(),
//                StringUtils.headImg(), System.currentTimeMillis());
//        giftControl.loadGift(giftModel);
//    }
//
//    public void setName(String title) {
//        tvTitle.setText(title);
//    }
//
//
//    public void setTitleVisibility(int visibility) {
//        tvTitle.setVisibility(visibility);
//    }
//
//    public static final int HISTORY_STATE_HELF = 0;
//
//    public static final int HISTORY_STATE_FULL = 1;
//
//    public static final int LIVE_STATE_HELF = 2;
//    public static final int LIVE_STATE_FULL = 4;
//
//    private boolean isFullScreen = false;
//
//    public void setState(int state) {
//        switch (state) {
//            case LIVE_STATE_HELF:
//                setViewState(GONE, tvTitle, ivLike, ivCollect, ivRightGift, ivMessage, mLiveAvatarView, mChatCustomView, tvLikeCount);
//                setViewState(VISIBLE, ivLeftGift);
//                break;
//            case LIVE_STATE_FULL:
//                setViewState(VISIBLE, tvTitle, ivRightGift, ivMessage, mChatCustomView);
//                setViewState(GONE, ivLeftGift, ivLike, ivCollect, tvLikeCount, mLiveAvatarView);
//                break;
//            case HISTORY_STATE_HELF:
//                setViewState(VISIBLE, ivLike, ivCollect);
//                setViewState(GONE, ivLeftGift, ivRightGift, ivMessage, tvTitle, mLiveAvatarView);
//                break;
//            case HISTORY_STATE_FULL:
//                setViewState(VISIBLE, ivLike, ivCollect, tvTitle);
//                setViewState(GONE, ivLeftGift, ivRightGift, ivMessage, mLiveAvatarView);
//                break;
//        }
//    }
//
//    public void setViewState(int visibility, View... views) {
//        for (int i = 0; i < views.length; i++) {
//            if (views[i].getVisibility() != visibility) {
//                views[i].setVisibility(visibility);
//            }
//        }
//    }
//
//    public void changeScreen() {
//        isFullScreen = !isFullScreen;
//        if (mChatCustomView != null) mChatCustomView.setVisibility(isFullScreen ? VISIBLE : GONE);
//        if (isLiveOrHistory) {
//            setState(isFullScreen ? LIVE_STATE_FULL : LIVE_STATE_HELF);
//        } else {
//            setState(isFullScreen ? HISTORY_STATE_FULL : HISTORY_STATE_HELF);
//        }
//        if (isFullScreen) {
//            ivFull.setImageResource(R.drawable.selector_sjk_live_unfull);
//        } else {
//            ivFull.setImageResource(R.mipmap.ic_sjk_live_list_full);
//        }
//        if (UpdateVersionUtil.IS(19)) {
//            if (isFullScreen) {
//                setPadding(0, 0, paddRight, 0);
//            } else {
//                setPadding(0, 0, 0, 0);
//            }
//        }
//    }
//
//    private boolean hasBuy;
//
//    public void setBuy(boolean hasBuy) {
//        this.hasBuy = hasBuy;
//    }
//
//    public ImageView getPlayOrPauseView() {
//        return ivPlayOrPause;
//    }
//
//    public void setPlay() {
//        mMyProgressView.setCanTouchProgress(true);
//        doPauseResume();
//        show(sDefaultTimeout);
//    }
//
//    private boolean isShiKan;
//
//    public void setIsShiKanPlay(boolean free) {
//        this.isShiKan = free;
//    }
//
//    public void historyShiKan() {
//        ivFull.setEnabled(false);
//    }
//
//    public void setMax(String duration) {
//        mMediaControllerUtil.setMax(TimeUtil.getMax(duration));
//    }
//
//
//
//    public boolean isFullScreen() {
//        return isFullScreen;
//    }
//}
