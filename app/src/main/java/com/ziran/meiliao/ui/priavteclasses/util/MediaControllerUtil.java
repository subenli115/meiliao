package com.ziran.meiliao.ui.priavteclasses.util;

import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;

import com.pili.pldroid.player.IMediaController;
import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.widget.MyProgressView;
import com.ziran.meiliao.widget.PlayPauseView;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/27 11:12
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/27$
 * @updateDes ${TODO}
 */

public class MediaControllerUtil {

    private CountDownTimer mDismissTopBottomCountDownTimer;
    private Handler mHandler;
    private IMediaController.MediaPlayerControl mPlayer;
    private MyProgressView mMyProgressView;
    private int max = 0;
    private PlayPauseView mPlayPauseView;

    public MediaControllerUtil(Handler handler) {
        this.mHandler = handler;
    }

    public void setPlayer(IMediaController.MediaPlayerControl player) {
        this.mPlayer = player;
    }

    public void setPlayPauseView(PlayPauseView playPauseView) {
        this.mPlayPauseView = playPauseView;
    }
    public void setViews(MyProgressView myProgressView) {
        mMyProgressView = myProgressView;
        if (mMyProgressView != null) {
            mMyProgressView.setMax(max);
            mMyProgressView.setOnSeekStopListener(new MyProgressView.OnSeekStopListener() {
                @Override
                public void onSeekStop(MyProgressView myProgressView, int progress) {
                    if (mPlayer == null) return;
                    if (max==0) return;
                    if (!mPlayer.isPlaying()) {
                        mPlayer.start();
                    }
                    LogUtils.logd("onSeekStop" + max);
                    int position = (int) (mPlayer.getDuration() * progress / max);
                    mPlayer.seekTo(position);
                    startDismissTopBottomTimer();
                    startUpdateProgressTimer();
                }
            });
        }
    }

    public void startUpdateProgressTimer() {
        mHandler.removeMessages(500);
        mHandler.sendEmptyMessage(500);
    }

    public boolean updateProgress() {
        if (mPlayer == null) return false;
        if (!mPlayer.isPlaying()) return false;
        long position = mPlayer.getCurrentPosition();
        long duration = mPlayer.getDuration();
        if (max == 0) {
            max = (int) (duration / 1000);
            mMyProgressView.setMax(max);
        }
        int progress = (int) (max * position / duration);
        if (position + 1800 > duration && duration > 5000) {
            mMyProgressView.setProgress(0);
            max = 0;
            return false;
        }
        mMyProgressView.setProgress(progress);
        return true;
    }


    public void cancelUpdateProgressTimer() {
        mHandler.removeMessages(500);
    }

    private int sDefaultTimeout = 5000;

    public void startDismissTopBottomTimer() {
        cancelDismissTopBottomTimer();
        if (mDismissTopBottomCountDownTimer == null) {
            mDismissTopBottomCountDownTimer = new CountDownTimer(sDefaultTimeout, sDefaultTimeout) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    mHandler.sendEmptyMessage(200);
                    mHandler.removeMessages(500);
                }
            };
        }
        mDismissTopBottomCountDownTimer.start();
    }


    public void cancelDismissTopBottomTimer() {
        if (mDismissTopBottomCountDownTimer != null) {
            mDismissTopBottomCountDownTimer.cancel();
        }
    }


    public void doPauseResume() {
        if (mPlayer == null) return;
        if (mPlayer.isPlaying()) {
            mPlayer.pause();
            cancelUpdateProgressTimer();
        } else {
            mPlayer.start();
            startUpdateProgressTimer();
        }
        updatePausePlay();
    }

    public void updatePausePlay() {
        if (mPlayPauseView == null || mPlayer == null) return;
        HandlerUtil.runMain(new Runnable() {
            @Override
            public void run() {
                mPlayPauseView.toggle(mPlayer.isPlaying());
            }
        }, tempTime);
    }

    private long tempTime = 500;

    public void startPlay() {
        if (mPlayPauseView != null){
            HandlerUtil.runMain(new Runnable() {
                @Override
                public void run() {
                    mPlayPauseView.setVisibility(View.VISIBLE);

                }
            }, tempTime);
            mPlayPauseView.toggle(true);
        }
        startUpdateProgressTimer();
        if (mMyProgressView!=null){
            mMyProgressView.setVisibility(View.VISIBLE);
        }
    }

    public void setMax(int max) {
        this.max = max;
        LogUtils.logd("setMax" + max);
        mMyProgressView.setMax(max);
    }


}
