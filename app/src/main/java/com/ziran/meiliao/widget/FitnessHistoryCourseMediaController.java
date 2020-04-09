package com.ziran.meiliao.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pili.pldroid.player.IMediaController;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.DisplayUtil;
import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.commonutils.TimeUtil;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.envet.BigInMediaControllerListener;
import com.ziran.meiliao.envet.CommonMediaControllerListener;
import com.ziran.meiliao.envet.FitnessMediaControllerListener;
import com.ziran.meiliao.envet.HistoryMediaControllerListener;
import com.ziran.meiliao.ui.priavteclasses.util.MediaControllerUtil;
import com.ziran.meiliao.utils.UpdateVersionUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 媒体播放器的控制器
 * Created by Administrator on 2017/1/20.
 */

public class FitnessHistoryCourseMediaController extends FrameLayout implements IMediaController, IMyMediaController {

    private MediaPlayerControl mPlayer;
    private int sDefaultTimeout = 5000;
    @Bind(R.id.rl_bottom)
    View viewBottom;
    @Bind(R.id.rl_top)
    View viewTop;
    @Bind(R.id.iv_contorller_full)
    ImageView ivFull;
    @Bind(R.id.iv_contorller_share)
    ImageView ivShare;
    @Bind(R.id.iv_contorller_collect)
    ImageView ivCollect;
    @Bind(R.id.iv_contorller_like)
    ImageView ivLike;
    @Bind(R.id.iv_contorller_back)
    ImageView ivBack;
    @Bind(R.id.tv_contorller_lister)
    TextView tvControllerLister;
    @Bind(R.id.tv_controller_title)
    TextView tvTitle;
    @Bind(R.id.tv_contorller_like_count)
    TextView tvLikeCount;
    @Bind(R.id.tv_contorller_toast)
    TextView tvControllerToast;
    @Bind(R.id.tv_contorller_gz)
    TextView tvControllerGz;
    @Bind(R.id.playPauseView)
    FitnessPlayPauseView mPlayPauseView;
    @Bind(R.id.iv_contorller_buy)
    TextView tvBuy;
    @Bind(R.id.rl_hint)
    RelativeLayout rlHint;
    @Bind(R.id.iv_contorller_back2)
    ImageView ivCb;
    @Bind(R.id.tv_replay)
    TextView tvReplay;
    @Bind(R.id.tv_hint_buy)
    TextView tvHintBuy;
    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.tv_list)
    TextView tvList;
    @Bind(R.id.gridView)
    CustomGridView gridView;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.rl_bottom_two)
    RelativeLayout rlBottomTwo;
    @Bind(R.id.tv_play_time)
    TextView tvPlayTime;
    @Bind(R.id.tv_play_name)
    TextView tvPlayName;
    private MyProgressView mMyProgressView;


    private int paddRight;
    private int likeCount;
    private static final int FADE_OUT = 1;
    private boolean topBottomVisible;
    private MediaControllerUtil mMediaControllerUtil;
    private boolean canHideTopAndBottom = true;
    private int min;
    private int second;
    private int mCurrentActiticy;
    private int mSize;


    public FitnessHistoryCourseMediaController(@NonNull Context context) {
        this(context, null);
    }

    public FitnessHistoryCourseMediaController(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FitnessHistoryCourseMediaController(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
    }


    private void initViews(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_media_controller_fitness, this, true);
        ButterKnife.bind(this, this);
        mMediaControllerUtil = new MediaControllerUtil(mHandler);
        ivShare.setOnClickListener(mOnClickListener);
        ivCollect.setOnClickListener(mOnClickListener);
        ivLike.setOnClickListener(mOnClickListener);
        ivFull.setOnClickListener(mOnClickListener);
        ivBack.setOnClickListener(mOnClickListener);
        tvControllerGz.setOnClickListener(mOnClickListener);
        ivCb.setOnClickListener(mOnClickListener);
        tvHintBuy.setOnClickListener(mOnClickListener);
         tvReplay.setOnClickListener(mOnClickListener);
        tvBuy.setOnClickListener(mOnClickListener);
        tvLeft.setOnClickListener(mOnClickListener);
        tvName.setOnClickListener(mOnClickListener);
        tvLeft.setVisibility(GONE);
        tvList.setOnClickListener(mOnClickListener);
        tvRight.setOnClickListener(mOnClickListener);
        paddRight = DisplayUtil.getVirtualBarHeight(getContext())  ;
        if (paddRight < 0) {
            paddRight = 0;
        }

        mMyProgressView = ViewUtil.getView(this, R.id.myProgressView);
        mPlayPauseView.setOnClickListener(mOnClickListener);
        mMediaControllerUtil.setViews(mMyProgressView);
        mMediaControllerUtil.setPlayPauseView(mPlayPauseView);
        if (mMyProgressView != null) {
            mMyProgressView.setCanTouchProgress(false);
        }
        setAutoHide(false);
    }


    @Override
    public void setMediaPlayer(MediaPlayerControl mediaPlayerControl) {
        this.mPlayer = mediaPlayerControl;
        mMediaControllerUtil.setPlayer(mPlayer);
    }

    @Override
    public void show() {
        show(sDefaultTimeout);
    }

    private boolean mShowing;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case FADE_OUT:
                    hide();
                    break;
                case 200:
                    if (canHideTopAndBottom) setTopBottomVisible(false);
                    break;
                case 500:
                    if (mMediaControllerUtil != null) {
                        if (mMediaControllerUtil.updateProgress()) {
                            mHandler.sendEmptyMessageDelayed(500, 800);
                        }
                    }
                    break;
            }
        }
    };

    @Override
    public void show(int time) {
        if (!canHideTopAndBottom) return;
        if (!mShowing) {
            mShowing = true;
        }
        setTopBottomVisible(true);
        if (mPlayer != null && mPlayer.isPlaying()) {
            mMediaControllerUtil.startUpdateProgressTimer();
        }
        mMediaControllerUtil.startDismissTopBottomTimer();
    }

    @Override
    public void hide() {
        if (!canHideTopAndBottom) return;
        if (mShowing) {
            mShowing = false;
        }
        setTopBottomVisible(false);
        mMediaControllerUtil.cancelUpdateProgressTimer();
    }

    @Override
    public boolean isShowing() {
        return mPlayer.isPlaying();
    }

    @Override
    public void setAnchorView(View view) {

    }

    private CommonMediaControllerListener mCommonMediaControllerListener;
    private HistoryMediaControllerListener mHistoryMediaControllerListener;
    private FitnessMediaControllerListener mFitnessMediaControllerListener;
    private BigInMediaControllerListener mBigInMediaControllerListener;

    public void setBigInMediaControllerListener(BigInMediaControllerListener bigInMediaControllerListener) {
        mBigInMediaControllerListener = bigInMediaControllerListener;
    }
    public void setFitnessControllerListener(FitnessMediaControllerListener m) {
        mFitnessMediaControllerListener = m;
    }

    public boolean isAutoHide() {
        return isAutoHide;
    }

    public void setAutoHide(boolean autoHide) {
        isAutoHide = autoHide;
    }

    public void setCommonMediaControllerListener(CommonMediaControllerListener commonMediaControllerListener) {
        mCommonMediaControllerListener = commonMediaControllerListener;
    }


    public void setHistoryMediaControllerListener(HistoryMediaControllerListener historyMediaControllerListener) {
        mHistoryMediaControllerListener = historyMediaControllerListener;
    }

    private OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_list:
                    if (mFitnessMediaControllerListener != null) {
                        setTopBottomVisible(false);
                        mFitnessMediaControllerListener.showList(gridView,tvLeft,tvRight);
                    }
                    break;
                case R.id.iv_contorller_buy:
                    if (mFitnessMediaControllerListener != null) {
                        mFitnessMediaControllerListener.buy();
                    }
                    break;
                case R.id.tv_left:
                    if (mFitnessMediaControllerListener != null) {
                        mFitnessMediaControllerListener.left(tvLeft,tvRight);

                    }
                    break;
                case R.id.tv_right:
                    if (mFitnessMediaControllerListener != null) {
                        mFitnessMediaControllerListener.right(tvLeft,tvRight);
                    }
                    break;
                case R.id.iv_contorller_back2:
                    rlHint.setVisibility(GONE);
                    break;
                case R.id.tv_replay:
                    startPlay();
                    rlHint.setVisibility(GONE);
                    break;
                case R.id.tv_hint_buy:
                    if (mFitnessMediaControllerListener != null) {
                        mFitnessMediaControllerListener.buy();
                    }
                    rlHint.setVisibility(GONE);
                    break;


                case R.id.iv_contorller_back:
                    if (mCommonMediaControllerListener != null) {
                        mCommonMediaControllerListener.onBack();
                    }
                    return;
                case R.id.iv_contorller_full:
                    changeScreen();
                    if (mCommonMediaControllerListener != null) {
                        mCommonMediaControllerListener.changeScreen(isFullScreen);
                    }
                    break;

                case R.id.iv_contorller_collect:
                    if (mHistoryMediaControllerListener != null) {
                        mHistoryMediaControllerListener.onCollect();
                    }
                    break;
                case R.id.iv_contorller_like:
                    if (mHistoryMediaControllerListener != null) {
                        mHistoryMediaControllerListener.onLike();
                    }
                    break;
                case R.id.playPauseView:
                    if (mHistoryMediaControllerListener != null) {
                        mHistoryMediaControllerListener.playOrPause();
                    }
                    break;
                case R.id.iv_contorller_share:
                    if (mCommonMediaControllerListener != null) {
                        mCommonMediaControllerListener.onShare();
                    }
                    break;
                case R.id.tv_contorller_gz:
                    if (mBigInMediaControllerListener != null) {
                        mBigInMediaControllerListener.onGz();
                    }
                    break;
            }
            show();
        }
    };


    public void setTopBottomVisible(boolean visible) {
        if(mPlayPauseView!=null){
            mPlayPauseView.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
        if (viewTop != null) {
            viewTop.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
        if(tvRight!=null){
            tvRight.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
        if(tvLeft!=null){
            tvLeft.setVisibility(visible ? View.VISIBLE : View.GONE);
            if(mSize!=0){
                updateLeftRight();
            }
        }
        if(tvName!=null){

            tvName.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
        if(tvList!=null){
            tvList.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
        if (visible) {
            mMediaControllerUtil.updateProgress();
        }
        if (viewBottom != null) {
            viewBottom.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
        if(rlBottomTwo!=null){
            rlBottomTwo.setVisibility(visible ? View.GONE : View.VISIBLE);
        }

        topBottomVisible = visible;
        if (!isAutoHide) return;
        if (visible) {
            if (mPlayer != null && mPlayer.isPlaying()) {
                mMediaControllerUtil.startDismissTopBottomTimer();
            }
            mMediaControllerUtil.updatePausePlay();
        } else {
            mMediaControllerUtil.cancelDismissTopBottomTimer();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isAutoHide) {
            show(sDefaultTimeout);
        } else {
            if (!canHideTopAndBottom) return true;
            switch (event.getAction()) {
                case MotionEvent.ACTION_UP:
                    setTopBottomVisible(!topBottomVisible);
                    if (topBottomVisible) {
                        mMediaControllerUtil.startUpdateProgressTimer();
                        mMediaControllerUtil.startDismissTopBottomTimer();
                    }
                    break;
            }
        }
        return true;
    }

    private boolean isAutoHide = true;


    public void setCollect(boolean isCollect) {
        ivCollect.setSelected(isCollect);
//        ivCollect.setImageResource(isCollect ? R.mipmap.ic_jyg_player_collect_green : R.mipmap.ic_sjk_live_list_collect);
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
        if (likeCount == 0) {
            tvLikeCount.setText("");
        } else if (likeCount > 999) {
            tvLikeCount.setText("999+");
        } else tvLikeCount.setText(String.valueOf(likeCount));
    }

    public void setLikes(boolean isCollect, boolean needUpdateCount) {
        if (needUpdateCount) {
            int count = isCollect ? ++likeCount : --likeCount;
            count = count > 0 ? count : 0;
            setLikeCount(count);
        }
        ivLike.setImageResource(isCollect ? R.mipmap.ic_sjk_live_list_like_pre : R.mipmap.ic_sjk_live_list_like);
    }

    public void setWatchCount(int watchCount) {
        if (watchCount <= 0) {
            tvControllerLister.setVisibility(GONE);
        } else {
            tvControllerLister.setText(String.valueOf(watchCount));
            tvControllerLister.setVisibility(VISIBLE);
        }
    }


    public void showShikan(boolean show) {
        if (show != canHideTopAndBottom) return;
        mMyProgressView.setCanTouchProgress(!show);
        tvControllerToast.setVisibility(show ? VISIBLE : GONE);
        this.canHideTopAndBottom = !show;
        show(5 * 1000 * 60 * 24);
        ivFull.setEnabled(!tvControllerToast.isShown());
    }

    public void startPlay() {
        mMediaControllerUtil.updateProgress();
        mMyProgressView.setCanTouchProgress(true);
        canHideTopAndBottom = true;
        ivFull.setEnabled(true);
        mMediaControllerUtil.startPlay();

    }

    public void updatePausePlay() {
        mMediaControllerUtil.updatePausePlay();
        show(sDefaultTimeout);
        canHideTopAndBottom = true;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ButterKnife.unbind(this);
    }

    /**
     * 控制器恢复到初始状态
     */
    public void reset() {
        topBottomVisible = false;
        mMediaControllerUtil.cancelUpdateProgressTimer();
        mMediaControllerUtil.cancelDismissTopBottomTimer();
    }


    public void setTitle(String title) {
        tvTitle.setText(title);
    }


    public static final int HISTORY_STATE_HELF = 0;
    public static final int HISTORY_STATE_FULL = 1;
    public static final int BIGIN_STATE_HELF = 3;
    public static final int BIGIN_STATE_FULL = 4;


    private boolean isFullScreen = false;

    public void setState(int state) {
        switch (state) {
            case HISTORY_STATE_HELF:
                setViewState(GONE, tvTitle);
                break;
            case HISTORY_STATE_FULL:
                setViewState(VISIBLE, tvTitle);
                break;
            case BIGIN_STATE_HELF:
            case BIGIN_STATE_FULL:
                setViewState(GONE, tvTitle, ivLike, ivCollect, tvLikeCount);
                setViewState(VISIBLE, tvControllerGz);
                break;
        }
    }

    public void setViewState(int visibility, View... views) {
        for (int i = 0; i < views.length; i++) {
            if (views[i].getVisibility() != visibility) {
                views[i].setVisibility(visibility);
            }
        }
    }

    private int fromType = 1;

    public void setFromType(int fromType) {
        if (this.fromType != fromType) {
            this.fromType = fromType;
            changeState(fromType);
        }
    }

    private void changeState(int fromType) {
        if (fromType == 1) {
            setState(isFullScreen ? HISTORY_STATE_FULL : HISTORY_STATE_HELF);
        } else if (fromType == 2) {
            setState(isFullScreen ? BIGIN_STATE_FULL : BIGIN_STATE_HELF);
        }
    }

    public void changeScreen() {
        isFullScreen = !isFullScreen;
        changeState(fromType);
        if (isFullScreen) {
            ivFull.setImageResource(R.drawable.selector_sjk_live_unfull);
        } else {
            ivFull.setImageResource(R.mipmap.ic_sjk_live_list_full);
        }
        if (UpdateVersionUtil.IS(19)) {
            if (isFullScreen) {
                setPadding(0, 0, 0, 0);
            } else {
                setPadding(0, 0, 0, 0);
            }
        }
        LogUtils.logd("paddRight:"+paddRight);
    }


    public void setMax(String duration) {
        mMediaControllerUtil.setMax(TimeUtil.getMax(duration));
    }


    public boolean isFullScreen() {
        return isFullScreen;
    }


    public void setGz(boolean isGz) {
        if (tvControllerGz != null) {
            ViewUtil.setText(tvControllerGz, isGz ? "关注" : "取消关注");
        }
    }

    public void setReset() {
        show();
        if (mMyProgressView != null) {
            mMyProgressView.setProgress(0);
        }
        if (mPlayPauseView != null) {
            mPlayPauseView.play(false);
        }
        canHideTopAndBottom = false;
    }
    public void setPause() {
        if (mPlayPauseView != null) {
            mPlayPauseView.play(false);
        }
        canHideTopAndBottom = true;
    }
    public void setPlayBtnState(int vis) {
        if (mPlayPauseView != null) {
            mPlayPauseView.setVisibility(vis);
        }
    }

    public void setBuyHide() {
        tvBuy.setVisibility(VISIBLE);
    }

    public void setFreeFinish() {
        rlHint.setVisibility(VISIBLE);
        mPlayPauseView.toggle(false);
    }
    public void setName(String name,int time) {
        tvName.setText(name);
        time=time/1000;
        second=time%60;
        min=time/60;
        String format = String.format("%02d:%02d", min, second);
        tvPlayTime.setText(format);
        tvPlayName.setText(name);
    }

    public void changeLeftRight(int mCurrent, int size) {
        Log.e("changeLeftRight",""+mCurrent+"");
        mSize=size;
        mCurrentActiticy= mCurrent;
        updateLeftRight();
    }

    private void updateLeftRight() {
        if(mCurrentActiticy==0){
            tvLeft.setVisibility(View.GONE);
        }else if(mCurrentActiticy==mSize-1){
            tvRight.setVisibility(View.GONE);
        }else {
            tvLeft.setVisibility(View.VISIBLE);
            tvRight.setVisibility(View.VISIBLE);
        }
    }
    public  void updateProgress(){
        mMediaControllerUtil.updateProgress();
    }

}
