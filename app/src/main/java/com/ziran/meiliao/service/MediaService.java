/**
 * Copyright (c) www.longdw.com
 */
package com.ziran.meiliao.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.RemoteViews;

import com.ziran.meiliao.R;
import com.ziran.meiliao.aidl.IMyMediaService;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.baserx.RxManager;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.commonutils.SPUtils;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.constant.IConstants;
import com.ziran.meiliao.entry.MusicEntry;
import com.ziran.meiliao.service.shake.ShakeDetector;
import com.ziran.meiliao.ui.decompressionmuseum.activity.AlbumPlayerActivity;
import com.ziran.meiliao.utils.MusicPanelFloatManager;
import com.ziran.meiliao.utils.MyValueTempCache;

import java.util.List;

import rx.functions.Action1;


/**
 * 后台Service 控制歌曲的播放 控制顶部Notification的显示
 *
 * @author longdw(longdawei1988@gmail.com)
 */
public class MediaService extends Service implements IConstants, ShakeDetector.OnShakeListener {

    private static final String PAUSE_BROADCAST_NAME = "com.ldw.music.pause.broadcast";
    private static final String NEXT_BROADCAST_NAME = "com.ldw.music.next.broadcast";
    private static final String CLOSE_BROADCAST_NAME = "com.ldw.music.close.broadcast";
    public static final int PAUSE_FLAG = 0x1;
    public static final int NEXT_FLAG = 0x2;
    public static final int CLOSE_FLAG = 0x5;
    public static final int PLAY_FLAG = 0x4;

    public MusicControl mMc;
    private NotificationManager mNotificationManager;

    private int NOTIFICATION_ID = 0x9527;
    private RemoteViews rv;
    private ShakeDetector mShakeDetector;
    /**
     * 当前是否正在播放
     */
    private boolean mIsPlaying;
    /**
     * 在设置界面是否开启了摇一摇的监听
     */
    public boolean mShake;
    //偏好设置存储
    private ControlBroadcast mControlBroadcast;
    private MusicPlayBroadcast mPlayBroadcast;
    private Bitmap notifyBitmap;

    private Notification notification;
    private RxManager mRxManager;

    @Override
    public void onCreate() {
        super.onCreate();
        mMc = new MusicControl(this);
        mShakeDetector = new ShakeDetector(this);
        mShakeDetector.setOnShakeListener(this);
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notifyBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        mControlBroadcast = new ControlBroadcast();
        IntentFilter filter = new IntentFilter();
        filter.addAction(PAUSE_BROADCAST_NAME);
        filter.addAction(NEXT_BROADCAST_NAME);
        filter.addAction(CLOSE_BROADCAST_NAME);
        registerReceiver(mControlBroadcast, filter);

        mPlayBroadcast = new MusicPlayBroadcast();
        IntentFilter filter1 = new IntentFilter(BROADCAST_NAME);
        filter1.addAction(BROADCAST_SHAKE);
        registerReceiver(mPlayBroadcast, filter1);
        mRxManager = new RxManager();
        mRxManager.on(AppConstant.RXTag.HOME_MUSIC_PLANE_SHOW_OR_HIDE, new Action1<Boolean>() {
            @Override
            public void call(Boolean showOrHide) {
                MusicPanelFloatManager.getInstance().setIsShowingAnimation(showOrHide);
            }
        });

    }


    /**
     * 更新notification
     *
     * @param bitmap
     * @param title
     * @param name
     */
    private void updateNotification(Bitmap bitmap, String title, String name) {
        isCancekNotification = false;
        Intent intent = new Intent(getApplicationContext(), AlbumPlayerActivity.class);
        intent.putExtra(IConstants.FLAG, ServiceManager.CLICK_FROM_MAIN_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        rv = new RemoteViews(this.getPackageName(), R.layout.notification_album_music);
        notification = new Notification();
        notification.icon = R.mipmap.ic_launcher;
        notification.tickerText = title;
        notification.contentIntent = pi;
        notification.contentView = rv;
        notification.flags |= Notification.FLAG_ONGOING_EVENT;

        if (bitmap != null) {
            rv.setImageViewBitmap(R.id.image, bitmap);
        } else {
            rv.setImageViewResource(R.id.image, R.drawable.img_album_background);
        }
        rv.setTextViewText(R.id.tv_name, title);
        rv.setTextViewText(R.id.text, name);

        //此处action不能是一样的 如果一样的 接受的flag参数只是第一个设置的值
        Intent pauseIntent = new Intent(PAUSE_BROADCAST_NAME);
        pauseIntent.putExtra(IConstants.FLAG, PAUSE_FLAG);
        PendingIntent pausePIntent = PendingIntent.getBroadcast(this, 0, pauseIntent, 0);
        rv.setOnClickPendingIntent(R.id.iv_pause, pausePIntent);

        Intent nextIntent = new Intent(NEXT_BROADCAST_NAME);
        nextIntent.putExtra(IConstants.FLAG, NEXT_FLAG);
        PendingIntent nextPIntent = PendingIntent.getBroadcast(this, 0, nextIntent, 0);
        rv.setOnClickPendingIntent(R.id.iv_next, nextPIntent);

        Intent closeIntent = new Intent(CLOSE_BROADCAST_NAME);
        closeIntent.putExtra(IConstants.FLAG, CLOSE_FLAG);
        PendingIntent prePIntent = PendingIntent.getBroadcast(this, 0, closeIntent, 0);
        rv.setOnClickPendingIntent(R.id.iv_close, prePIntent);

        startForeground(NOTIFICATION_ID, notification);

    }

    private class MusicPlayBroadcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(BROADCAST_NAME)) {
                int playState = intent.getIntExtra(PLAY_STATE_NAME, MPS_NOFILE);
                Bundle bundleExtra = intent.getBundleExtra(AppConstant.KEY_MUSIC);
                switch (playState) {
                    case MPS_PLAYING:
                        AudioFocusService.requestAudioFocus(context);
                        mIsPlaying = true;
                        if (SPUtils.getBoolean(IConstants.SP_SHAKE_CHANGE_SONG)) {
                            mShakeDetector.start();
                        }
                        MusicPanelFloatManager.getInstance().startPlay();
                        if (intent.hasExtra(PLAY_MUSIC_URL)) {
                            RxManagerUtil.post(AppConstant.RXTag.PLAY_STATE, intent.getStringExtra(PLAY_MUSIC_URL));
                        }
                        break;
                    case MPS_PREPARE:

                        if (bundleExtra != null) {
                            MusicEntry musicEntry = MyAPP.mServiceManager.getCurMusic();
                            if (EmptyUtils.isNotEmpty(musicEntry) && MyAPP.mServiceManager.getClick_from() == ServiceManager
                                    .CLICK_FROM_ALBUM) {
                                isPlay = true;
                                RxManagerUtil.post(AppConstant.SPKey.PLAY_CURRENT_DATA, musicEntry);
                                MyValueTempCache.setCurrentPlayMusicEntry(musicEntry);
                                MyAPP.mServiceManager.updateNotification(notifyBitmap, musicEntry.getName(), MyAPP.mServiceManager
                                        .getAlbumName());
                                MusicPanelFloatManager.getInstance().setMusicPrepare(musicEntry);
                            }
                        }
                        break;
                    case MPS_COMPLETION:
                        if (intent.hasExtra(PLAY_MUSIC_URL)) {
                            RxManagerUtil.post(AppConstant.RXTag.MPS_COMPLETION, intent.getStringExtra(PLAY_MUSIC_URL));
//                            MusicPanelFloatManager.getInstance().setIsShowingShare(true);
                        }

                        break;
                    default:
                        AudioFocusService.releaseFocus(context);
                        mIsPlaying = false;
                        mShakeDetector.stop();
                        MusicPanelFloatManager.getInstance().onPause();
                }

                MusicPanelFloatManager.getInstance().updatePlayState();
            } else if (intent.getAction().equals(BROADCAST_SHAKE)) {
                mShake = intent.getBooleanExtra(SHAKE_ON_OFF, false);
                if (mShake && mIsPlaying) {//如果开启了监听并且歌曲正在播放
                    mShakeDetector.start();
                } else if (!mShake) {
                    mShakeDetector.stop();
                }
            }
        }
    }


    /**
     * 标识按钮状态：是否在播放
     */
    public boolean isPlay = false;

    private class ControlBroadcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int flag = intent.getIntExtra(IConstants.FLAG, -1);
            switch (flag) {
                case PAUSE_FLAG:
                    setPlayState(!isPlay);
                    break;
                case NEXT_FLAG:
                    mMc.next();
                    RxManagerUtil.post(NOTIFY_FLAG, NEXT_FLAG);
                    if(!MyAPP.mServiceManager.getCurMusic().isSt()&&!MyAPP.mServiceManager.isIsbuy()){
                        mMc.prev();
                    }
                    break;
                case CLOSE_FLAG:
                    MyAPP.mServiceManager.stop();
                    RxManagerUtil.post(NOTIFY_FLAG, CLOSE_FLAG);
                    break;
            }
        }
    }

    public void setPlayState(boolean isPlay) {
        if (rv == null) return;
        if (!isPlay) {
            this.isPlay = false;
            mMc.pause();
            rv.setImageViewResource(R.id.iv_pause, R.drawable.nc_play);
            RxManagerUtil.post(NOTIFY_FLAG, PAUSE_FLAG);
        } else {
            //当前暂停，则开始
            this.isPlay = true;
            mMc.replay();
            rv.setImageViewResource(R.id.iv_pause, R.drawable.nc_pause);
            RxManagerUtil.post(NOTIFY_FLAG, PLAY_FLAG);
        }
//        boolean needNotify = MyAPP.mServiceManager.getClick_from() == ServiceManager.CLICK_FROM_EXERCISE  ;
        if (MyAPP.mServiceManager.getMusicSize() > 0) {
            mNotificationManager.notify(NOTIFICATION_ID, notification);
        }
    }

    private boolean isCancekNotification = true;

    private void cancelNotification() {
        if (!isCancekNotification) {
            isCancekNotification = true;
            stopForeground(true);
            mNotificationManager.cancel(NOTIFICATION_ID);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private class ServerStub extends IMyMediaService.Stub {

        @Override
        public boolean pause() throws RemoteException {
            setPlayState(false);
            return mMc.pause();
        }

        @Override
        public boolean prev() throws RemoteException {
            return mMc.prev();
        }

        @Override
        public boolean stop() throws RemoteException {
            cancelNotification();
            MusicPanelFloatManager.getInstance().setVisibility(View.GONE);
            return mMc.stop();
        }

        @Override
        public boolean next() throws RemoteException {
            return mMc.next();
        }

        @Override
        public boolean play(int pos) throws RemoteException {
            return mMc.play(pos);
        }

        @Override
        public boolean playUrl(String url) throws RemoteException {
            return mMc.playUrl(url);
        }

        @Override
        public int duration() throws RemoteException {
            return mMc.duration();
        }

        @Override
        public int position() throws RemoteException {
            return mMc.position();
        }

        @Override
        public boolean seekTo(int progress) throws RemoteException {
            return mMc.seekTo(progress);
        }


        @Override
        public void refreshMusicList(List<MusicEntry> musicList) throws RemoteException {
            mMc.refreshMusicList(musicList);
        }

        @Override
        public void getMusicList(List<MusicEntry> musicList) throws RemoteException {
            List<MusicEntry> music = mMc.getMusicList();
            for (MusicEntry m : music) {
                musicList.add(m);
            }
        }


        @Override
        public int getPlayState() throws RemoteException {
            return mMc.getPlayState();
        }

        @Override
        public int getPlayMode() throws RemoteException {
            return mMc.getPlayMode();
        }

        @Override
        public void setPlayMode(int mode) throws RemoteException {
            mMc.setPlayMode(mode);
        }

        @Override
        public void sendPlayStateBrocast() throws RemoteException {
            mMc.sendBroadCast();
        }

        @Override
        public void exit() throws RemoteException {
            cancelNotification();
            stopSelf();
            mMc.exit();
        }

        @Override
        public boolean rePlay() throws RemoteException {

            setPlayState(true);
            return mMc.replay();
        }

        @Override
        public int getCurMusicId() throws RemoteException {
            return mMc.getCurMusicId();
        }

        @Override
        public void updateNotification(Bitmap bitmap, String title, String name) throws RemoteException {

            MediaService.this.updateNotification(bitmap, title, name);
        }

        @Override
        public void cancelNotification() throws RemoteException {

            MediaService.this.cancelNotification();
        }

        @Override
        public boolean playById(int id) throws RemoteException {
            return mMc.playById(id);
        }

        @Override
        public MusicEntry getCurMusic() throws RemoteException {
            return mMc.getCurMusic();
        }

    }

    private final IBinder mBinder = new ServerStub();

    //甩动手机时播放下一首
    @Override
    public void onShake() {
        mMc.next();
    }

    //销毁时执行
    @Override
    public void onDestroy() {
        super.onDestroy();
        mRxManager.clear();
        if (mControlBroadcast != null) {
            unregisterReceiver(mControlBroadcast);
        }
        MusicPanelFloatManager.getInstance().onDestroy();
        if (mPlayBroadcast != null) {
            unregisterReceiver(mPlayBroadcast);
        }
    }


}
