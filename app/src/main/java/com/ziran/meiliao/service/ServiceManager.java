/**
 * Copyright (c) www.longdw.com
 */
package com.ziran.meiliao.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.RemoteException;

import com.ziran.meiliao.aidl.IMyMediaService;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.constant.IConstants;
import com.ziran.meiliao.entry.MusicEntry;
import com.ziran.meiliao.ui.bean.AlbumBean;
import com.ziran.meiliao.utils.MusicPanelFloatManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 控制Service
 *
 * @author longdw(longdawei1988@gmail.com)
 */
public class ServiceManager implements IConstants {
    public static final String ACTION_SERVICE = "com.ziran.meiliao.service.MediaService";
    public static final String PACKNAME = "com.ziran.meiliao";
    public IMyMediaService mService;
    private Context mContext;
    private ServiceConnection mConn;
    private IOnServiceConnectComplete mIOnServiceConnectComplete;
    private boolean isNervePlay;
    private MediaPlayer mediaPlayer1;



    public int getmBgNum() {
        return mBgNum;
    }


    private int mBgNum;

    public ServiceManager(Context context) {
        this.mContext = context;
        initConn();
    }

    private void initConn() {
        mediaPlayer1 = new MediaPlayer();
            mConn = new ServiceConnection() {
            @Override
            public void onServiceDisconnected(ComponentName name) {

            }

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mService = IMyMediaService.Stub.asInterface(service);
                if (mService != null && mIOnServiceConnectComplete != null) {
                    mIOnServiceConnectComplete.onServiceConnectComplete(mService);
                }
            }
        };
    }

    public void connectService() {
        if (mIsBound) return;
        Intent intent = new Intent(ACTION_SERVICE);
        intent.setAction(ACTION_SERVICE);//Service能够匹配的Action
        intent.setPackage(PACKNAME);//应用的包名
        mContext.bindService(intent, mConn, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    private boolean mIsBound = false;


    public void refreshMusicList(List<MusicEntry> musicList) {
        if (musicList != null && mService != null) {
            try {
                MusicPanelFloatManager.getInstance().removePlayOrPauseView(null);
                mSize = musicList.size();
                mService.refreshMusicList(musicList);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
    public List<MusicEntry> getMusicList() {
        List<MusicEntry> musicList = new ArrayList<>();
        try {
            if (mService != null) {
                mService.getMusicList(musicList);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return musicList;
    }



    public boolean play(final int pos) {
        if (mService != null) {
            try {
                isNervePlay = false;
                return mService.play(pos);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean playById(int id) {
        if (mService != null) {
            try {

                isNervePlay = false;
                return mService.playById(id);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean rePlay() {
                  bgPlay();
        if (mService != null) {
            try {
                return mService.rePlay();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean pause() {
        bgPause();
        if (mService != null) {
            try {
                return mService.pause();
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }
        return false;
    }
    public boolean pause(int from) {
        if (mService != null) {
            try {
                return mService.pause();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean prev() {
        if (mService != null) {
            try {
                mService.pause();
                return mService.prev();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean next() {
        if (mService != null) {
            try {
                mService.pause();
                return mService.next();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean seekTo(int progress) {
        if (mService != null) {
            try {
//                if (isPlaying()){
//
//                }else{
//                    return false;
//                }
                return mService.seekTo(progress);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public int position() {
        if (mService != null) {
            try {
                return mService.position();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public int duration() {
        if (mService != null) {
            try {
                return mService.duration();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public boolean isPlaying() {
        return getPlayState() == IConstants.MPS_PLAYING;
    }

    public boolean isPlayStop(){
        return getClick_from() ==CLICK_FROM_NONE;
    }
    public int getPlayState() {
        if (mService != null) {
            try {
                return mService.getPlayState();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public void setPlayMode(int mode) {
        if (mService != null) {
            try {
                mService.setPlayMode(mode);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public int getPlayMode() {
        if (mService != null) {
            try {
                return mService.getPlayMode();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public int getCurMusicId() {
        if (mService != null) {
            try {
                return mService.getCurMusicId();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public MusicEntry getCurMusic() {
        if (mService != null) {
            try {
                return mService.getCurMusic();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void sendBroadcast() {
        if (mService != null) {
            try {
                mService.sendPlayStateBrocast();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void exit() {
        if (mConn != null && mIsBound) {
            mContext.unbindService(mConn);
            mIsBound = false;
        }
        if (mService != null) {
            try {
                mService.cancelNotification();
                mService.exit();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

    }

    public void updateNotification(Bitmap bitmap, String title, String name) {
        try {
            mService.updateNotification(bitmap, title, name);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void cancelNotification() {
        try {
            mService.cancelNotification();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private int mSize;

    public int getMusicSize() {
        return mSize;
    }


    private int click_from = -2;
    public static final int CLICK_FROM_ALBUM = 1;
    public static final int CLICK_FROM_MAIN_HOME = 4;
    public static final int CLICK_FROM_EXERCISE = 5;
    public static final int CLICK_FROM_DOWNLOAD = 0;
    public static final int CLICK_FROM_PUSH = 16;
    public static final int CLICK_FROM_NONE = -2;
    public static final int CLICK_FROM_URL =15;

    public void setClickFrom(int clickFrom) {
        this.click_from = clickFrom;
        currentPlayUrl = null;
    }


    public int getClick_from() {
        return click_from;
    }

    public String currentPlayUrl;
    public void     playUrl(String url) {
        if (mService != null) {
            try {
                isNervePlay = false;
                cancelNotification();
                MusicPanelFloatManager.getInstance().setIsShowing(true);
                MusicPanelFloatManager.getInstance().unBindView(null);

                setClickFrom(CLICK_FROM_URL);
                mSize=-1;
                mService.playUrl(url);
                currentPlayUrl = url;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
    public void playZlUrl(String url) {
        if (mService != null) {
            try {
                isNervePlay = false;
                cancelNotification();
                mService.playUrl(url);
                currentPlayUrl = url;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        if (mService != null) {
            MyAPP.mServiceManager.setBGNum(0);
            if (getClick_from() != CLICK_FROM_NONE) {
                try {
                    mService.stop();
                    MusicPanelFloatManager.getInstance().removePlayOrPauseView(null);
                    setClickFrom(CLICK_FROM_NONE);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String albumPicture;
    private String albumId;
    private String  albumName;

    public String getAlbumName1() {
        return albumName1;
    }

    public void setAlbumName1(String albumName1) {
        this.albumName1 = albumName1;
    }

    private String  albumName1;

    public boolean isIsbuy() {
        return isbuy;
    }

    public void setIsbuy(boolean isbuy) {
        this.isbuy = isbuy;
    }

    private boolean  isbuy;

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumName(String  albumName) {
        this.albumName = albumName;
    }

    public String getAlbumPicture() {
        return albumPicture;
    }

    public void setAlbumPicture(String albumPicture) {
        this.albumPicture = albumPicture;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String  getAlbumName() {
        return albumName;
    }


    public boolean checkUrl(String url){
        LogUtils.logd("url:"+url + "    currentPlayUrl:"  +currentPlayUrl);
        return EmptyUtils.isNotEmpty(url) && EmptyUtils.isNotEmpty(currentPlayUrl) && url.equals(currentPlayUrl);
    }

    public AlbumBean getAlbum() {
        return album;
    }

    /**
     * 播放音乐
     */
    public void playBgMusic(final String url) {
        stopmediaPlayer();
        try {
            // 设置音频流的类型
            mediaPlayer1.setDataSource(url);
            // 通过异步的方式装载媒体资源
            mediaPlayer1.prepare();
            mediaPlayer1.setLooping(true);
            mediaPlayer1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    // 装载完毕 开始播放流媒体
                    mediaPlayer1.start();
                    mediaPlayer1.setVolume(0.5f,0.5f);
                    // 避免重复播放，把播放按钮设置为不可用
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void bgPlay(){
        if(mediaPlayer1!=null){
            mediaPlayer1.start();
        }
    }

    public void bgPause(){
        if(mediaPlayer1!=null&&mediaPlayer1.isPlaying()){
            mediaPlayer1.pause();
        }
    }




    //切换歌曲
    public void stopmediaPlayer() {
        if (mediaPlayer1 != null) {
            mediaPlayer1.reset();
        }
    }

    private AlbumBean album ;

    public void setAlbum(AlbumBean album) {
        this.album = album;
    }

    public void setBGNum(int mPosition) {
        mBgNum=mPosition;
    }
}
