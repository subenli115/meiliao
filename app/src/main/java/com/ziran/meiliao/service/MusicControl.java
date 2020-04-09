/**
 * Copyright (c) www.longdw.com
 */
package com.ziran.meiliao.service;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;

import com.google.gson.Gson;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.constant.IConstants;
import com.ziran.meiliao.entry.MusicEntry;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.AlbumBean;
import com.ziran.meiliao.ui.bean.AlbumBgMusicBean;
import com.ziran.meiliao.ui.bean.HomeAlbumBean;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.PrefUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 歌曲控制
 *
 * @author longdw(longdawei1988@gmail.com)
 */
public class MusicControl implements IConstants, OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener {

    private MediaPlayer mMediaPlayer;
    private int mPlayMode;
    private List<MusicEntry> mMusicList = new ArrayList<>();
    private int mPlayState;
    private int mCurPlayIndex;
    private Context mContext;
    private Random mRandom;
    private int mCurMusicId;
    private MusicEntry mCurMusic;
    private boolean neverPlay = true;
    private AlbumBean albumBean;

    public MusicControl(Context context) {
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setOnCompletionListener(this);
        mMediaPlayer.setOnPreparedListener(this);
        mMediaPlayer.setOnErrorListener(this);
        mPlayMode = MPM_LIST_LOOP_PLAY;
        mPlayState = MPS_NOFILE;
        mCurPlayIndex = -1;
        mCurMusicId = -1;
        this.mContext = context;
        mRandom = new Random();
        mRandom.setSeed(System.currentTimeMillis());
    }

    //根据下标播放列表的音乐
    public boolean play(int pos) {
        playUrl = null;
        if (mCurPlayIndex == pos) {
            if (!mMediaPlayer.isPlaying()) { //获取焦点
                mMediaPlayer.start();
                mPlayState = MPS_PLAYING;
                sendBroadCast();
            } else {
                pause();
            }
            return true;
        }
        return prepare(pos);
    }

    private String playUrl;

    //根据url播放音乐
    public boolean playUrl(String url) {
        if (EmptyUtils.isNotEmpty(mMusicList)){
            mMusicList.clear();
            mCurPlayIndex = -1;
            mCurMusicId = -1;
            mCurMusic = null;
        }
        if (EmptyUtils.isEmpty(url)) {
            mPlayState = MPS_INVALID;
            mMediaPlayer.pause();
            return false;
        }
        if (url.equals(playUrl)) {
            if (!mMediaPlayer.isPlaying()) {
                replay();
            } else {
                pause();
            }
            return true;
        }
        try {
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(url);
            mMediaPlayer.prepareAsync();
            neverPlay = false;
            playUrl = url;
        } catch (Exception e) {
            mPlayState = MPS_INVALID;
            return false;
        }
        Intent intent = new Intent(BROADCAST_NAME);
        intent.putExtra(PLAY_STATE_NAME, MPS_PREPARE_OTHER);
        intent.putExtra(PLAY_MUSIC_URL, url);
        mContext.sendBroadcast(intent);
        return true;
    }

    /**
     * 根据歌曲的id来播放
     *
     * @param id
     * @return
     */
    public boolean playById(int id) {
        playUrl = null;
        int position = seekPosInListById(mMusicList, id);
        return play(position);
    }

    //继续播放
    public boolean replay() {
        if (mPlayState == MPS_INVALID || mPlayState == MPS_NOFILE) {
            return false;
        }
        mMediaPlayer.start();
        mPlayState = MPS_PLAYING;
        sendBroadCast();
        return true;
    }

    //暂停
    public boolean pause() {
        if (mPlayState != MPS_PLAYING) {
            return false;
        }
        mMediaPlayer.pause();
        mPlayState = MPS_PAUSE;
        sendBroadCast();
        return true;
    }

    //播放上一首
    public boolean prev() {
        if (mPlayState == MPS_NOFILE) {
            return false;
        }
        mCurPlayIndex--;
        mCurPlayIndex = reviseIndex(mCurPlayIndex);
        return prepare(mCurPlayIndex);
    }

    //播放下一首
    public boolean next() {
        if (mPlayState == MPS_NOFILE) {
            return false;
        }
        if (mPlayMode == IConstants.MPM_RANDOM_PLAY) {
            mCurPlayIndex = getRandomIndex();
        } else {
            mCurPlayIndex++;
        }
        mCurPlayIndex = reviseIndex(mCurPlayIndex);
        return prepare(mCurPlayIndex);
    }

    //检索下标是否正确
    private int reviseIndex(int index) {
        if (index < 0) {
            index = mMusicList.size() - 1;
        }
        if (index >= mMusicList.size()) {
            index = 0;
        }
        return index;
    }

    //获取当前播放的进度( 单位: 毫秒)
    public int position() {
        if (mPlayState == MPS_PLAYING || mPlayState == MPS_PAUSE) {
            return mMediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    /**
     * 获取当前音乐的时长(单位:毫秒)
     *
     * @return
     */
    public int duration() {
        if (mPlayState == MPS_INVALID || mPlayState == MPS_NOFILE) {
            return 0;
        }
        return mMediaPlayer.getDuration();
    }

    //根据进度跳转位置
    public boolean seekTo(int progress) {
        if (mPlayState == MPS_INVALID || mPlayState == MPS_NOFILE) {
            return false;
        }
        int time = mMediaPlayer.getDuration();
        progress = time > progress ? progress : time;
        progress = progress < 0 ? 0 : progress;
        mMediaPlayer.seekTo(progress);
        return true;
    }

    //检索播放进度
    private int reviseSeekValue(int progress) {
        if (progress < 0) {
            progress = 0;
        } else if (progress > 100) {
            progress = 100;
        }
        return progress;
    }

    //刷新列表数据
    public void refreshMusicList(List<MusicEntry> musicList) {
        mMusicList.clear();
        mMusicList.addAll(musicList);
        if (mMusicList.size() == 0) {
            mPlayState = MPS_NOFILE;
            mCurPlayIndex = -1;
        }
    }


    //准备就绪
    private boolean prepare(int pos) {
        neverPlay = false;
        mCurPlayIndex = pos;
        mMediaPlayer.reset();
        try {
        if (mMusicList.get(pos).isHead()) {
            mCurPlayIndex++;
        }
        mCurPlayIndex = reviseIndex(mCurPlayIndex);
        String path = mMusicList.get(mCurPlayIndex).getPath();
            MusicEntry musicEntry = mMusicList.get(mCurPlayIndex);
            musicEntry.getName();
            musicEntry.getMusicId();
            Gson gson=new Gson();
            albumBean= MyAPP.mServiceManager.getAlbum();
            HomeAlbumBean bean=new HomeAlbumBean();
            bean.setMusicId(musicEntry.getMusicId()+"");
            bean.setAlbumId(MyAPP.mServiceManager.getAlbumId());
            bean.setAlbumName(MyAPP.mServiceManager.getAlbumName1());
            bean.setMusicName(musicEntry.getName());
            bean.setPic(musicEntry.getSharePic());
            String albumBeanString=gson.toJson(bean);
            PrefUtils.putString("albumBean",albumBeanString,mContext);
            RxManagerUtil.post(AppConstant.RXTag.MPS_COMPLETION, "next");
            Map<String, String> defMap = MapUtils.getDefMap(true);
            defMap.put("albumId", MyAPP.mServiceManager.getAlbumId());
            defMap.put("musicId", musicEntry.getMusicId()+ "");
            OkHttpClientManager.postAsync(ApiKey.ALBUM_SAVERECALBUM, defMap, new NewRequestCallBack<AlbumBgMusicBean>(AlbumBgMusicBean.class) {
                @Override
                protected void onSuccess(AlbumBgMusicBean result) {
                }
                @Override
                public void onError(String msg, int code) {
                }
            });
            mMediaPlayer.setDataSource(path);
            mMediaPlayer.prepareAsync();
            mPlayState = MPS_PREPARE;
        } catch (Exception e) {
            mPlayState = MPS_INVALID;
            if (pos < mMusicList.size() - 1) { //10  8
                pos++;
                if (mMusicList.get(pos).isHead()) {
                    pos++;
                }
                pos = reviseIndex(pos);
                playById(mMusicList.get(pos).getMusicId());
            }
            return false;
        }
        sendBroadCast();
        return true;
    }

    //发送广播
    public void sendBroadCast() {
        Intent intent = new Intent(BROADCAST_NAME);
        intent.putExtra(PLAY_STATE_NAME, mPlayState);
        intent.putExtra(PLAY_MUSIC_INDEX, mCurPlayIndex);
        intent.putExtra(PLAY_MUSIC_URL, playUrl);
        intent.putExtra("music_num", mMusicList.size());
        if (mPlayState != MPS_NOFILE && mMusicList.size() > 0) {
            Bundle bundle = new Bundle();
            if(mCurPlayIndex<0){
                mCurPlayIndex=0;
            }
                mCurMusic = mMusicList.get(mCurPlayIndex);
            mCurMusicId = mCurMusic.getMusicId();
            intent.putExtra(AppConstant.KEY_MUSIC, bundle);
        }
        mContext.sendBroadcast(intent);
    }

    //获取当前播放的音乐ID
    public int getCurMusicId() {
        return mCurMusicId;
    }

    //获取当前播放的音乐
    public MusicEntry getCurMusic() {
        return mCurMusic;
    }

    //获取当前播放的状态
    public int getPlayState() {
        return mPlayState;
    }

    //获取当前播放的模式
    public int getPlayMode() {
        return mPlayMode;
    }

    public void setPlayMode(int mode) {
        switch (mode) {
            case MPM_LIST_LOOP_PLAY:
            case MPM_ORDER_PLAY:
            case MPM_RANDOM_PLAY:
            case MPM_SINGLE_LOOP_PLAY:
                mPlayMode = mode;
//                if (mPlayMode == MPM_SINGLE_LOOP_PLAY) {
//                    mMediaPlayer.setLooping(true);
//                } else {
//                    mMediaPlayer.setLooping(false);
//                }
                break;
        }
    }

    List<MusicEntry> getMusicList() {
        return mMusicList;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        RxManagerUtil.post(AppConstant.RXTag.MPS_COMPLETION, "onCompletion");
        if (EmptyUtils.isNotEmpty(playUrl)) {
            RxManagerUtil.post(AppConstant.RXTag.MPS_COMPLETION, playUrl);
            Intent intent = new Intent(BROADCAST_NAME);
            intent.putExtra(PLAY_STATE_NAME, MPS_COMPLETION);
            intent.putExtra(PLAY_MUSIC_URL, playUrl);
            mContext.sendBroadcast(intent);
        }
        switch (mPlayMode) {
            case MPM_LIST_LOOP_PLAY:  // 列表循环
                if (mMusicList.isEmpty()) return;
                next();
                break;
            case MPM_ORDER_PLAY:         // 顺序播放
                if (mMusicList.isEmpty()) return;
                if (mCurPlayIndex != mMusicList.size() - 1) {
                    next();
                } else {
                    prepare(mCurPlayIndex);
                }
                break;
            case MPM_RANDOM_PLAY:           // 随机播放
                if (mMusicList.isEmpty()) return;
                int index = getRandomIndex();
                if (index != -1) {
                    mCurPlayIndex = index;
                } else {
                    mCurPlayIndex = 0;
                }
                prepare(mCurPlayIndex);
                break;
            case MPM_SINGLE_LOOP_PLAY:      // 单曲循环
                if (EmptyUtils.isEmpty(playUrl)) {
                    play(mCurPlayIndex);
                } else {
                    replay();
                }
                break;
        }
    }

    //获取随机的下标
    private int getRandomIndex() {
        int size = mMusicList.size();
        if (size == 0) {
            return -1;
        }
        int result;
        while (true) {
            result = mRandom.nextInt(size);
            if (result != mCurPlayIndex) {
                break;
            }
        }
        return result;
    }

    //退出应用
    public void exit() {
        stop();
        mMediaPlayer.release();
        mMediaPlayer = null;
    }

    //准备完成,开始播放
    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
        mPlayState = MPS_PLAYING;
        sendBroadCast();
    }

    //停止播放
    public boolean stop() {
        if (!neverPlay) {
            if (mMediaPlayer.isPlaying()){
                mMediaPlayer.stop();
            }
            mMediaPlayer.reset();
            mMusicList.clear();
            mCurPlayIndex = -1;
            mCurMusicId = -1;
            mCurMusic = null;
            playUrl = null;
        }
        return true;
    }

    /**
     * 根据歌曲的ID，寻找出歌曲在当前播放列表中的位置
     *
     * @param list
     * @param id
     * @return
     */
    private static int seekPosInListById(List<MusicEntry> list, int id) {
        if (id == -1) {
            return -1;
        }
        int result = -1;
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (id == list.get(i).getMusicId()) {
                    result = i;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }
}
