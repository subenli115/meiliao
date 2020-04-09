package com.ziran.meiliao.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import com.ziran.meiliao.app.MyAPP;


/**
 * 音视频声音焦点服务
 */
public class AudioFocusService extends Service {
    private boolean needRestart;

    public AudioFocusService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw null;
    }

    private AudioManager mAm;
    private static boolean vIsActive = false;
    private MyOnAudioFocusChangeListener mListener;

    private class MyOnAudioFocusChangeListener implements AudioManager.OnAudioFocusChangeListener {
        @Override
        public void onAudioFocusChange(int focusChange) {
            // TODO Auto-generated method stub
            if (MyAPP.mServiceManager.getClick_from() == ServiceManager.CLICK_FROM_NONE) return;
            switch (focusChange) {
                //重新获取焦点
                case AudioManager.AUDIOFOCUS_GAIN:
                    //判断是否需要重新播放音乐
                    if (needRestart) {
                        MyAPP.mServiceManager.rePlay();
                        needRestart = false;
                        mHandler.removeMessages(1);
                    }
                    hasFocus = true;
                    break;
                //暂时失去焦点
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    //暂时失去焦点，暂停播放音乐（将needRestart设置为true）
                    if (MyAPP.mServiceManager.isPlaying()) {
                        needRestart = true;
                        MyAPP.mServiceManager.pause();
                    }
                    mHandler.removeMessages(1);
                    hasFocus = false;
                    break;
                //时期焦点
                case AudioManager.AUDIOFOCUS_LOSS:
                    //暂停播放音乐，不再继续播放
                    boolean needPause = MyAPP.mServiceManager.getClick_from() == ServiceManager.CLICK_FROM_EXERCISE;
                    if (!needPause) {
                        MyAPP.mServiceManager.pause();
                        needRestart = true;
                        hasFocus = false;
                        mHandler.sendEmptyMessageDelayed(1, 1000 * 60 * 5);
                    }
                    break;
            }
        }
    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (needRestart) {
                needRestart = false;
            }
            return true;
        }
    });
    private boolean hasFocus;

    private boolean isInitListener;

    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        vIsActive = mAm.isMusicActive();
        if (!isInitListener) {
            isInitListener = true;
            int result = mAm.requestAudioFocus(mListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
            hasFocus = AudioManager.AUDIOFOCUS_REQUEST_GRANTED == result;
        } else if (vIsActive) {
            int release = intent.getIntExtra("release", -2);
            if (release == 3) {
                if (hasFocus) {
                    mAm.abandonAudioFocus(mListener);
                    hasFocus = false;
                }
                return super.onStartCommand(intent, flags, startId);
            }
            if (!hasFocus) {
                int result = mAm.requestAudioFocus(mListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                hasFocus = result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED;
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mListener = new MyOnAudioFocusChangeListener();
        mAm = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (vIsActive) {
            mAm.abandonAudioFocus(mListener);
        }
    }

    public static void requestAudioFocus(Context context) {
        context.startService(new Intent(context, AudioFocusService.class));
    }

    public static void releaseFocus(Context context) {
        Intent intent = new Intent(context, AudioFocusService.class);
        intent.putExtra("release", 3);
        context.startService(intent);
    }

}
