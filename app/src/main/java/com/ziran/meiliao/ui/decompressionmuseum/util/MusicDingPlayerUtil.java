package com.ziran.meiliao.ui.decompressionmuseum.util;

import android.media.AudioManager;
import android.media.MediaPlayer;

import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.compressorutils.FileUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 吴祖清
 * @version 1.0
 * @createDate 2017/8/27 20:27
 * @des ${TODO}
 * @updateAuthor #author
 * @updateDate 2017/8/27
 * @updateDes ${TODO}
 */

public class MusicDingPlayerUtil {
    private MediaPlayer mMediaPlayer;
    private boolean isSetPlayer = false;

    private boolean isDingEnd;
    private OnDingFinishListener mOnDingFinishListener;

    //当前播放的音乐url
    private String currentPlayUrl;
    //播放的音乐url的进度
    private Map<String, Integer> playPositions;
    //是否是否资源
    private boolean isDestroy;

    public MusicDingPlayerUtil() {

        playPositions = new HashMap<>();
        isDestroy = false;
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                isDingEnd = true;
                if (mOnDingFinishListener != null) {
                    mOnDingFinishListener.onFinish();
                }
            }
        });
    }

    public void startPlayDing() {
        try {
            if (!isSetPlayer) {
                mMediaPlayer.setDataSource(FileUtil.getExerciseDingMp3FileName());
                mMediaPlayer.prepareAsync();
                isSetPlayer = true;
            } else {
                mMediaPlayer.start();
            }
            isDingEnd = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isDingEnd() {
        return isDingEnd;
    }

    public void setOnDingFinishListener(OnDingFinishListener onDingFinishListener) {
        mOnDingFinishListener = onDingFinishListener;
    }

    public interface OnDingFinishListener {
        void onFinish();
    }


    /**
     * 根据URL播放音频
     *
     * @param url 需要播放的音乐（）
     */
    public void startPlay(String url) {
        if (isDestroy) {
            return;
        }
        if (EmptyUtils.isNotEmpty(url) && url.equals(currentPlayUrl)) {
            if (MyAPP.mServiceManager.isPlaying()) {
                MyAPP.mServiceManager.pause();
            } else {
                MyAPP.mServiceManager.rePlay();
            }
        } else {
            if (EmptyUtils.isNotEmpty(currentPlayUrl)) {
                playPositions.put(currentPlayUrl, MyAPP.mServiceManager.position());
            }
            currentPlayUrl = url;
            MyAPP.mServiceManager.playUrl(url);
            LogUtils.logd("currentPlayUrl" + currentPlayUrl);
        }
    }


    public void seekTo(String url) {
        if (currentPlayUrl != null && currentPlayUrl.equals(url)) {
            if (playPositions.containsKey(currentPlayUrl)) {
                Integer integer = playPositions.get(currentPlayUrl);
                if (integer != null && integer != 0) {
                    MyAPP.mServiceManager.seekTo(integer);
                }
            }
        }
    }

    /**
     * 释放资源(在activity销毁时执行)
     */
    public void onDestroy() {
        if (isDestroy) return;
        mMediaPlayer.release();
        playPositions.clear();
        mMediaPlayer = null;
        playPositions = null;
        isDestroy = true;
    }


}
