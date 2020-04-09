package com.ziran.meiliao.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.pili.pldroid.player.IMediaController;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;

import java.util.Locale;

/**
 * 媒体播放器的控制器
 * Created by Administrator on 2017/1/20.
 */

public class SimpleMediaController extends FrameLayout implements IMediaController, View.OnClickListener {

    private boolean mShowing;
    private static int sDefaultTimeout = 6000;

    private static final int FADE_OUT = 1;

    //    private AudioManager mAM;
    private ImageView ivPlayOrPause;
    private ImageView iv_back;
    private TextView tvTitle;
    //父容器
    private View rootView;

    //进度条
    private MyProgressView mMyProgressView;
    //控制器
    private MediaPlayerControl mPlayer;


    private AudioManager mAM;

    public SimpleMediaController(Context context) {
        this(context, null);
    }

    public SimpleMediaController(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleMediaController(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
        mAM = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }

    private void initViews() {
        rootView = LayoutInflater.from(getContext()).inflate(R.layout.simple_media_contorller, this, true);
        ivPlayOrPause = (ImageView) rootView.findViewById(R.id.iv_contorller_playOrPause);
        iv_back = (ImageView) rootView.findViewById(R.id.iv_contorller_back);
        tvTitle = (TextView) rootView.findViewById(R.id.tv_contorller_title);
        iv_back.setOnClickListener(this);
        ivPlayOrPause.setOnClickListener(this);
        mMyProgressView = ViewUtil.getView(rootView, R.id.myProgressView);
        if (mMyProgressView != null) {
            mMyProgressView.setAutoSeek(false);
            mMyProgressView.setOnSeekStopListener(new MyProgressView.OnSeekStopListener() {
                @Override
                public void onSeekStop(MyProgressView myProgressView, int progress) {
                    if (!mInstantSeeking) mPlayer.seekTo(mDuration * progress / 1000);

                    show(sDefaultTimeout);
                    mHandler.removeMessages(SHOW_PROGRESS);
                    mAM.setStreamMute(AudioManager.STREAM_MUSIC, false);
                    mDragging = false;
                    mHandler.sendEmptyMessageDelayed(SHOW_PROGRESS, 1000);
                }
            });
            mMyProgressView.setMax(1000);
        }
    }

    @Override
    public void setMediaPlayer(MediaPlayerControl mediaPlayerControl) {
        this.mPlayer = mediaPlayerControl;
    }

    @Override
    public void show() {
        show(sDefaultTimeout);
    }

    @Override
    public void show(int timeout) {
        if (!mShowing) {
            mShowing = true;
            setVisibility(VISIBLE);
        }
        updatePausePlay();
        mHandler.sendEmptyMessage(SHOW_PROGRESS);

        if (timeout != 0) {
            mHandler.removeMessages(FADE_OUT);
            mHandler.sendMessageDelayed(mHandler.obtainMessage(FADE_OUT), timeout);
        }
    }

    @Override
    public void hide() {
        if (mShowing) {
            mShowing = false;
            setVisibility(GONE);
            ViewUtil.setSystemUiHide((Activity) getContext(), true);
        }
        try {
            mHandler.removeMessages(SHOW_PROGRESS);
        } catch (IllegalArgumentException ex) {
            Log.d(TAG, "MediaController already removed");
        }
    }

    private static final String TAG = "CusonMediaController";

    @Override
    public boolean isShowing() {
        return mShowing;
    }

    //    private View anchorView;
    @Override
    public void setAnchorView(View view) {
        //        anchorView = view;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        show(sDefaultTimeout);
        return true;
    }

    @Override
    public boolean onTrackballEvent(MotionEvent ev) {
        show(sDefaultTimeout);
        return false;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (event.getRepeatCount() == 0 && (keyCode == KeyEvent.KEYCODE_HEADSETHOOK || keyCode == KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE ||
                keyCode == KeyEvent.KEYCODE_SPACE)) {
            doPauseResume();
            show(sDefaultTimeout);
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_MEDIA_STOP) {
            if (mPlayer.isPlaying()) {
                mPlayer.pause();
                updatePausePlay();
            }
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_MENU) {
            hide();
            return true;
        } else {
            show(sDefaultTimeout);
        }
        return super.dispatchKeyEvent(event);
    }


    //点击监听
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_contorller_playOrPause:
                setPlay();
                break;
            case R.id.iv_contorller_back:
                ((Activity) getContext()).finish();
                break;
        }
    }


    public void setPlay() {
        doPauseResume();
        show(sDefaultTimeout);
    }

    private static final int SHOW_PROGRESS = 4;
    boolean mDragging;
    private boolean mInstantSeeking = true;


    private long mDuration;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case FADE_OUT:
                    hide();
                    break;
                case SHOW_PROGRESS:
                    long pos = setProgress();
                    if (!mDragging && mShowing) {
                        msg = obtainMessage(SHOW_PROGRESS);
                        sendMessageDelayed(msg, 1000 - (pos % 1000));
                        updatePausePlay();
                    }
                    break;
            }
        }
    };

    private long setProgress() {
        if (mPlayer == null || mDragging) return 0;
        long position = mPlayer.getCurrentPosition();
        long duration = mPlayer.getDuration();
        if (mMyProgressView != null) {
            if (duration > 0) {
                long pos = 1000L * position / duration;
                mMyProgressView.setProgress((int) pos);
            }
            int percent = mPlayer.getBufferPercentage();
            mMyProgressView.setCacheProgress(percent*10);
            mDuration = duration;
            mMyProgressView.setProgressAndMax((int)position,(int)mDuration);
        }
        return position;
    }

    private static String generateTime(long position) {
        int totalSeconds = (int) (position / 1000);

        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;

        if (hours > 0) {
            return String.format(Locale.US, "%02d:%02d:%02d", hours, minutes, seconds);
        } else {
            return String.format(Locale.US, "%02d:%02d", minutes, seconds);
        }
    }


    private void doPauseResume() {
        if (mPlayer == null) return;
        if (mPlayer.isPlaying()) mPlayer.pause();
        else mPlayer.start();
        updatePausePlay();
    }

    public void setTitle(String title) {
        if (EmptyUtils.isNotEmpty(title)) {
            tvTitle.setText(title);
        }
    }

    private void updatePausePlay() {
        if (rootView == null || ivPlayOrPause == null || mPlayer == null) return;
        if (mPlayer.isPlaying()) ivPlayOrPause.setImageResource(R.mipmap.ic_sjk_live_video_stop);
        else ivPlayOrPause.setImageResource(R.mipmap.ic_sjk_live_video_play);
    }

}
