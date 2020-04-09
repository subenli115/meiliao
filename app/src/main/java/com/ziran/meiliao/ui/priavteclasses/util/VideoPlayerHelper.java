package com.ziran.meiliao.ui.priavteclasses.util;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.view.View;
import android.widget.ImageView;

import com.pili.pldroid.player.AVOptions;
import com.pili.pldroid.player.IMediaController;
import com.pili.pldroid.player.PLMediaPlayer;
import com.pili.pldroid.player.widget.PLVideoTextureView;
import com.pili.pldroid.player.widget.PLVideoView;
import com.pili.pldroid.playerdemo.utils.Utils;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.commonutils.NetWorkUtils;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.entry.VideoSectionEntry;
import com.ziran.meiliao.envet.LiveCallBack;
import com.ziran.meiliao.ui.bean.SJKLiveDetailProfileBean;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.MyValueTempCache;
import com.ziran.meiliao.widget.PlayPauseView;

import java.util.List;

/**
 * author 吴祖清
 * create  2017/4/8 13
 * des     Video初始化的工具类
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */

public class VideoPlayerHelper {


    private int mIsLiveStreaming;
    private LiveCallBack mLiveCallBack;
    private PLVideoTextureView mVideoView;
    private View mLoadingView;
    private IMediaController mMediaController;


    public static final int MESSAGE_ID_RECONNECTING = 0x01;
    private int reconnectCount;
    private boolean mIsActivityPaused;
    private boolean isPlayerEnd;
    private String mVideoPath;
    private ImageView mCoverView;
    private int mDisplayAspectRatio = PLVideoView.ASPECT_RATIO_FIT_PARENT;    //3
    PowerManager.WakeLock wakeLock = null;

    public VideoPlayerHelper(LiveCallBack liveCallBack) {
        this.mLiveCallBack = liveCallBack;
    }


    public void init(PLVideoTextureView videoView, View loadingView, IMediaController mediaController) {
       init(videoView, loadingView, mediaController,null);
    }

    public void init(PLVideoTextureView videoView, View loadingView, IMediaController mediaController, ImageView coverView) {
        this.mVideoView = videoView;
        this.mLoadingView = loadingView;
        this.mMediaController = mediaController;
        this.mCoverView = coverView;
        initVideoView();
        PowerManager powerManager = (PowerManager) videoView.getContext().getSystemService(Context.POWER_SERVICE);
        this.wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, this.getClass().getName());
        wakeLock.acquire();
    }


    private void initVideoView() {
        mVideoView.setBufferingIndicator(mLoadingView);
        mVideoView.setOnErrorListener(mOnErrorListener);
        mVideoView.setOnInfoListener(mOnInfoListener);
        mVideoView.setOnCompletionListener(onCompletionListener);
        mVideoView.setDisplayAspectRatio(mDisplayAspectRatio);
        if (mCoverView != null) {
            mVideoView.setCoverView(mCoverView);
        }

    }

    protected Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what != MESSAGE_ID_RECONNECTING) {
                return;
            }
            if (mIsActivityPaused || !Utils.isLiveStreamingAvailable()) {
                finish();
                return;
            }
            if (!NetWorkUtils.isNetConnected(MyAPP.getContext())) {
                sendReconnectMessage();
                return;
            }
            mVideoView.setVideoPath(mVideoPath);
            mVideoView.start();
        }
    };

    private void sendReconnectMessage() {
        if (reconnectCount == 50) {
            finish();
            if (mMediaController != null) {
                mMediaController.show(3000000);
            }
            return;
        }
        showToastTips(MyAPP.getContext().getString(R.string.reconnection));
        mLoadingView.setVisibility(View.VISIBLE);
        mHandler.removeCallbacksAndMessages(null);
        mHandler.sendMessageDelayed(mHandler.obtainMessage(MESSAGE_ID_RECONNECTING), 500);
        reconnectCount++;
    }

    public void finish() {
        ((Activity) mLoadingView.getContext()).finish();
    }

    private void showToastTips(final String tips) {
        if (mIsActivityPaused) {
            return;
        }
        ToastUitl.showShort(tips);
    }


    private PLMediaPlayer.OnCompletionListener onCompletionListener = new PLMediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(PLMediaPlayer plMediaPlayer) {
            isPlayerEnd = true;
            if (mIsLiveStreaming==0){
                RxManagerUtil.post(AppConstant.RXTag.PLAYER_END,true);
            }
        }
    };

    private PlayPauseView mPlayPauseView;

    public void setPlayPauseView(PlayPauseView playPauseView) {
        this.mPlayPauseView = playPauseView;
    }

    private PLMediaPlayer.OnInfoListener mOnInfoListener = new PLMediaPlayer.OnInfoListener() {
        @Override
        public boolean onInfo(PLMediaPlayer plMediaPlayer, int what, int extra) {
            if (mPlayPauseView == null || mIsLiveStreaming==1) return false;
            if (what == MediaPlayer.MEDIA_INFO_BUFFERING_END) {
                LogUtils.logd("onInfo" + mPlayPauseView + "  mIsLiveStreaming = "+ mIsLiveStreaming);
                if (!mPlayPauseView.isShown()) {
                    mPlayPauseView.setVisibility(View.VISIBLE);
                }
                return true;
            }
            if (mPlayPauseView.isShown()) {
                mPlayPauseView.setVisibility(View.GONE);
            }
            return false;
        }
    };

    private PLMediaPlayer.OnErrorListener mOnErrorListener = new PLMediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(PLMediaPlayer plMediaPlayer, int errorCode) {
            boolean isNeedReconnect = false;
//            LogUtils.logd("Error happened, errorCode = " + errorCode);
            switch (errorCode) {
                case PLMediaPlayer.ERROR_CODE_INVALID_URI:
                    showToastTips("视频链接失效,请重新进入...");
                    break;
                case PLMediaPlayer.ERROR_CODE_404_NOT_FOUND:
                    showToastTips("视频链接失效,请重新进入...");
                    break;
                case PLMediaPlayer.ERROR_CODE_CONNECTION_REFUSED:
                    showToastTips("视频链接失效,请重新进入...");
                    break;
                case PLMediaPlayer.ERROR_CODE_CONNECTION_TIMEOUT:
                    showToastTips("链接超时");
                    isNeedReconnect = true;
                    break;
                case PLMediaPlayer.ERROR_CODE_EMPTY_PLAYLIST:
                    showToastTips("视频链接失效,请重新进入...");
                    break;
                case PLMediaPlayer.ERROR_CODE_STREAM_DISCONNECTED:
                    showToastTips("视频链接已断开...");
                    isNeedReconnect = true;
                    break;
                case PLMediaPlayer.ERROR_CODE_IO_ERROR:
                    showToastTips("网络出现异常");
                    isNeedReconnect = true;
                    break;
                case PLMediaPlayer.ERROR_CODE_UNAUTHORIZED:
                    showToastTips("视频链接失效,请重新进入...");
                    break;
                case PLMediaPlayer.ERROR_CODE_PREPARE_TIMEOUT:
                    showToastTips("视频链接已断开...");
                    isNeedReconnect = true;
                    break;
                case PLMediaPlayer.ERROR_CODE_READ_FRAME_TIMEOUT:
                    showToastTips("视频链接已断开...");
                    isNeedReconnect = true;
                    break;
                case PLMediaPlayer.ERROR_CODE_HW_DECODE_FAILURE:
                    setOptions(mVideoView, AVOptions.MEDIA_CODEC_SW_DECODE, mIsLiveStreaming);
                    isNeedReconnect = true;
                    break;
                case PLMediaPlayer.MEDIA_ERROR_UNKNOWN:
                    break;
                default:
                    showToastTips("视频链接失效,请重新进入...");
                    break;
            }
            // Todo pls handle the error status here, reconnect or call finish()
            if (isNeedReconnect) {
                sendReconnectMessage();
            } else {
                finish();
                //                sendReconnectMessage();
            }

            // Return true means the error has been handled
            // If return false, then `onCompletion` will be called
            return true;
        }
    };

    public void setIsLiveStreaming(int isLiveStreaming, int codec) {
        mIsLiveStreaming = isLiveStreaming;
        //硬件编码           //软件编码
        // 1 -> hw codec enable, 0 -> disable [recommended]  2 auto 自动
        setOptions(mVideoView, codec, mIsLiveStreaming);
        if (mMediaController != null) {
            mVideoView.setMediaController(mMediaController);
        }
    }

    private boolean isShikan;

    public boolean isShikan() {
        return isShikan;
    }

    public void setShikan(boolean shikan) {
        isShikan = shikan;
    }

    public void onResume() {
        mIsActivityPaused = false;
        if (isPlayerEnd || isShikan) return;
        if (mIsLiveStreaming == 1) {
            if (EmptyUtils.isNotEmpty(mVideoPath)) {
                mVideoView.setVideoPath(mVideoPath);
                mVideoView.start();
            }
        } else {
            mVideoView.start();
        }
        wakeLock.setReferenceCounted(false);
        this.wakeLock.acquire();
    }


    public void startPlay(String videoPath) {
        if (EmptyUtils.isNotEmpty(videoPath)) {
            mVideoPath = videoPath;
//            LogUtils.logd("startPlay"+ videoPath);
        }
        mVideoView.setVisibility(View.VISIBLE);
        mVideoView.setVideoPath(mVideoPath);
        mLoadingView.setVisibility(View.VISIBLE);
        mVideoView.start();

    }


    public String getCurrentVideoPath(SJKLiveDetailProfileBean.DataBean mDataBean) {
        if (EmptyUtils.isNotEmpty(mVideoPath)) return mVideoPath;
        mVideoPath = mDataBean.getUrl();
        if (EmptyUtils.isNotEmpty(mVideoPath)) {
            return mVideoPath;
        }
        List<VideoSectionEntry> mDataBeanDir = mDataBean.getDir();
        if (EmptyUtils.isNotEmpty(mDataBeanDir)) {
            for (int i = 0; i < mDataBeanDir.size(); i++) {
                VideoSectionEntry sectionEntry = mDataBeanDir.get(i);
                if (sectionEntry.isCur()) {
                    mVideoPath = sectionEntry.getUrlAndPath();
                    break;
                }
            }
        }
        return mVideoPath;
    }

    public VideoSectionEntry getCurrentSection(SJKLiveDetailProfileBean.DataBean mDataBean) {
        List<VideoSectionEntry> mDataBeanDir = mDataBean.getDir();
        if (EmptyUtils.isNotEmpty(mDataBeanDir)) {
            for (int i = 0; i < mDataBeanDir.size(); i++) {
                VideoSectionEntry sectionEntry = mDataBeanDir.get(i);
                if (sectionEntry.isCur()) {
                    mVideoPath = sectionEntry.getUrlAndPath();
                    return sectionEntry;
                }
            }
        }
        return null;
    }

    public void setOptions(PLVideoTextureView mVideoView, int codecType, int isLiveStreaming) {
        this.mIsLiveStreaming = isLiveStreaming;
        AVOptions options = new AVOptions();
        // the unit of timeout is ms
        options.setInteger(AVOptions.KEY_PREPARE_TIMEOUT, 10 * 1000);
        options.setInteger(AVOptions.KEY_GET_AV_FRAME_TIMEOUT, 10 * 1000);
        options.setInteger(AVOptions.KEY_PROBESIZE, 128 * 1024);
        // Some optimization with buffering mechanism when be set to 1
        options.setInteger(AVOptions.KEY_LIVE_STREAMING, mIsLiveStreaming);
        if (mIsLiveStreaming == 1) {
            options.setInteger(AVOptions.KEY_DELAY_OPTIMIZATION, 1);
        }
        // 解码方式，codec＝1，硬解; codec=0, 软解
        // 默认值是：0
        // 1 -> hw codec enable, 0 -> disable [recommended]
        options.setInteger(AVOptions.KEY_MEDIACODEC, codecType);

        // whether start play automatically after prepared, default value is 1

        options.setInteger(AVOptions.KEY_START_ON_PREPARED, mIsLiveStreaming == 1 ? 0 : 1);

        mVideoView.setAVOptions(options);
    }

    public void onDestroy() {
        mVideoView.stopPlayback();
        MyValueTempCache.get().reomve(AppConstant.DOWN_COURSE);
        LiveRoomUtil.get().onDestroy(null);
        wakeLock = null;
    }

    public void onPause() {
        mIsActivityPaused = true;
        if (mIsLiveStreaming == 1) {
            mVideoView.stopPlayback();
        } else {
            mVideoView.pause();
        }
        if (wakeLock != null) {
            try {
                wakeLock.release();
            } catch (Throwable th) {

            }
        } else {

        }
    }

    public void setCoverView(String url) {
        if (mCoverView != null) {
            ImageLoaderUtils.display(mLoadingView.getContext(), mCoverView, url);
        }
    }

    public void stop() {
        if (mVideoView.isPlaying()) {
            mVideoView.stopPlayback();
            mLoadingView.setVisibility(View.GONE);
            mHandler.removeMessages(MESSAGE_ID_RECONNECTING);
        }
    }


    public void checkShiKan(boolean isShiKan, String url) {
        setShikan(true);
        if (isShiKan) {
            stop();
            mLiveCallBack.showBuyTip();
        } else {
            if (mIsLiveStreaming == 1) {
                startShiKan();
                startPlay(url);
            } else {
                mLiveCallBack.historyShiKan();
            }
        }
    }

    public void startShiKan() {
        mLiveCallBack.postShiKan();
        if (mMediaController != null) {
            mMediaController.hide();
        }
        HandlerUtil.runMain(shiKanRunnable, SHIKAN_TIME);
    }

    private Runnable shiKanRunnable = new Runnable() {
        @Override
        public void run() {
            if (mIsLiveStreaming == 0) {
                long milliSecond = mVideoView.getCurrentPosition();
                if (milliSecond < SHIKAN_TIME) {
                    HandlerUtil.runMain(shiKanRunnable, SHIKAN_TIME - milliSecond);
                } else {
                    stopPlay();
                }
            } else {
                stopPlay();
            }
        }
    };

    private void stopPlay() {
        if (!mLiveCallBack.isBuyCourse()) {
            stop();
            mLiveCallBack.showBuyTip();
            mCoverView.setVisibility(View.VISIBLE);
        }
    }

    //        private static final int SHIKAN_TIME = 3 * 2 * 1000;
    private static final int SHIKAN_TIME = 5 * 60 * 1000;




}
