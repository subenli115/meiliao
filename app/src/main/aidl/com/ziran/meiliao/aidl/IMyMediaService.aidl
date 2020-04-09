/**
 * Copyright (c) www.longdw.com
 */
package com.ziran.meiliao.aidl;
import com.ziran.meiliao.entry.MusicEntry;
import android.graphics.Bitmap;

interface IMyMediaService {
    boolean play(int pos);
    boolean playUrl(String detailUrl);
    boolean playById(int id);
    boolean rePlay();
	boolean pause();
	boolean prev();
	boolean stop();
	boolean next();
	int duration();
    int position();
    boolean seekTo(int progress);
    void refreshMusicList(in List<MusicEntry> musicList);
    void getMusicList(out List<MusicEntry> musicList);
    
    int getPlayState();
    int getPlayMode();
    void setPlayMode(int mode);
    void sendPlayStateBrocast();
    void exit();
    int getCurMusicId();
    void updateNotification(in Bitmap bitmap, String title, String name);
    void cancelNotification();
    MusicEntry getCurMusic();
}