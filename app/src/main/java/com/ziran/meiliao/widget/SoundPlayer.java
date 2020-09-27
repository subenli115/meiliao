package com.ziran.meiliao.widget;

import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.ziran.meiliao.R;

import java.io.IOException;

public class SoundPlayer {

    private static final String LOG_TAG = "SoundPlayer";
    private MediaPlayer mPlayer;
    private int pause;

    public SoundPlayer(){

        mPlayer = new MediaPlayer();
    }
    public void startPlaying(String fileName, ImageView view) {
        try {
            mPlayer.setDataSource(fileName);
            mPlayer.prepare();
            mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mPlayer.start();
                    view.setImageResource(R.mipmap.icon_record_suspend);
                }
            });
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed" + e.getMessage());
        }
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.stop();
                view.setImageResource(R.mipmap.icon_record_play);
            }
        });
    }


         public void   play(ImageView view){
             if(pause==0){//暂停状态
                 mPlayer.pause();
                 view.setImageResource(R.mipmap.icon_record_play);
                 pause=1;
             }else{//播放状态
                 mPlayer.start();
                 view.setImageResource(R.mipmap.icon_record_suspend);
                 pause=0;
             }


         }

    public void stopPlaying() {
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }

}