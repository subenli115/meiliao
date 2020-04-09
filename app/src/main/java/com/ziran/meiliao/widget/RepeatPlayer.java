package com.ziran.meiliao.widget;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;

import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.utils.AnimationUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/9/21 10:03
 * @des ${重复播放视频的播放器}
 * @updateAuthor $Author$
 * @updateDate 2017/9/21$
 * @updateDes ${TODO}
 */


public class RepeatPlayer implements MediaPlayer.OnPreparedListener, SurfaceHolder.Callback, MediaPlayer.OnCompletionListener {
    //用于播放视频的mediaPlayer对象
    private MediaPlayer currentPlayer;     //负责setNextMediaPlayer的player缓存对象
    private SurfaceHolder surfaceHolder;
    private String path;


    public RepeatPlayer(SurfaceView surface) {
        surfaceHolder = surface.getHolder();// SurfaceHolder是SurfaceView的控制接口
        surfaceHolder.addCallback(this); // 因为这个类实现了SurfaceHolder.Callback接口，所以回调参数直接this
    }


    float volume = 0f;

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
        mediaPlayer.setVolume(volume, volume);
        if (ivCover != null && ivCover.isShown()) {
            AnimationUtil.startAlphaAnimation(ivCover);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (!isSurfaceLive) {
            initFirstPlayer();
            if (needPlay) {
                startPlayFirstVideo();
                neverPlay = false;
                needPlay = false;
            }
        }
        LogUtils.logd("RepeatPlayer.surfaceCreated");
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        LogUtils.logd("RepeatPlayer.surfaceDestroyed:" + isPlaying());
        if (currentPlayer != null) {
            currentPlayer.reset();
            currentPlayer.release();
            currentPlayer = null;
        }
        isSurfaceLive = false;
        neverPlay = true;
    }

    private boolean isSurfaceLive;

    /*
     * 初始化播放首段视频的player
     */
    private void initFirstPlayer() {
        isSurfaceLive = true;
        currentPlayer = createPlayer();
        currentPlayer.setOnPreparedListener(this);
        currentPlayer.setDisplay(surfaceHolder);
    }

    public boolean isSurfaceLive() {
        return isSurfaceLive;
    }

    private MediaPlayer createPlayer() {
        MediaPlayer player = new MediaPlayer();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);

        player.setOnCompletionListener(this);
        return player;
    }


    public void setResource(String path) {
        this.path = path;
    }

    public void setParams(ImageView iv, String path) {
        this.ivCover = iv;
        this.path = path;
    }

    private void startPlayFirstVideo() {
        if (isPlaying()) return;
        try {
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            currentPlayer.setDataSource(fis.getFD());
            currentPlayer.prepareAsync();
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }


    /*
        * 负责处理一段视频播放过后，切换player播放下一段视频
        */

    private static final String TAG = "RepeatPlayer";

    @Override
    public void onCompletion(MediaPlayer mp) {
        try {
            mp.seekTo(60);
            mp.setVolume(volume, volume);
            mp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onPause() {
        if (isPlaying()) {
            currentPlayer.pause();
            if (ivCover != null) {
                ivCover.setVisibility(View.VISIBLE);
            }
        }
    }

    private boolean needPlay;
    private boolean neverPlay = true;

    public void onStart() {
        if (isSurfaceLive) { //如果holder已经生成
            if (neverPlay) {
                startPlayFirstVideo();
                neverPlay = false;
            } else {
                if (ivCover != null) {
                    ivCover.setVisibility(View.GONE);
                }
                currentPlayer.setDisplay(surfaceHolder);
                currentPlayer.start();
            }
        } else {
            needPlay = true;
        }
    }

    private ImageView ivCover;

    public void setCover(ImageView ivCover) {
        this.ivCover = ivCover;
    }

    public boolean isPlaying() {
        return currentPlayer != null && currentPlayer.isPlaying();
    }

}
