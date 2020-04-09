package com.ziran.meiliao.ui.settings.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.ziran.meiliao.entry.MusicEntry;
import com.ziran.meiliao.ui.base.ShareActivity;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by Administrator on 2018/7/24.
 */

public class VideoActivity extends ShareActivity implements View.OnClickListener {

    @Bind(R.id.surfaceVIew)
    SurfaceView mSurfaceView ;
    @Bind(R.id.btn_play)
    Button btnPlay;
    @Bind(R.id.btn_pause)
    Button btnPause;
    @Bind(R.id.btn_stop)
    Button btnStop;
    @Bind(R.id.LinearLayout1)
    LinearLayout LinearLayout1;
    private String path;
    private MediaPlayer mediaPlayer;
    private int position;


    @Override
    public int getLayoutId() {
        return R.layout.activity_video;
    }

    @Override
    public void initPresenter() {

    }


    public static void startAction(Context context, ArrayList<MusicEntry> list, int position) {
        Intent mIntent = new Intent();
        mIntent.setClass(context, VideoActivity.class);
        if (list.get(position).getFilePath().contains("正念相逢")) {
            mIntent.putExtra("path", FileUtil.getDownZlFolder() + "第一堂课 自我关爱/正念相逢.mp3");
        } else {
            mIntent.putExtra("path", "" + list.get(position).getFilePathZl().substring(0, list.get(position).getFilePathZl().length() - 4));
        }
        context.startActivity(mIntent);
    }

    /* 为5个按钮设置监听 */
    private void setListeners() {
        btnPlay.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        btnStop.setOnClickListener(this);
    }

    @Override
    public void initView() {
               /*设置窗口无title*/
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
                 /*全屏显示*/
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setListeners();
        mediaPlayer = new MediaPlayer();
        setSurfaceView();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer = null;
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_play:

                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    return;
                } else {
                    playMedia();
                }
                break;
            case R.id.btn_pause:// 暂停
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    position = mediaPlayer.getCurrentPosition();
                    mediaPlayer.pause();
                } else {
                    return;
                }
                break;
            case R.id.btn_stop:// 停止
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    position = 0;
                } else {
                    return;
                }
                break;
        }
    }

    /* 设置surfaceView视图 */
    private void setSurfaceView() {
// creates a "push" surface
        mSurfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
// 设置事件，回调函数
        mSurfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            /* SurfaceView创建时 */
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if (position > 0) {
                    playMedia();
                    position = 0;
                }
            }
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
// TODO Auto-generated method stub
            }
            /* SurfaceView销毁视图 */
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                if(mediaPlayer==null){
                    return;
                }
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
/* Activity销毁时停止播放，释放资源。不做这个操作，即使退出，还是能听到视频的声音 */
                mediaPlayer.release();
            }
        });
    }
    // 横竖屏切换时的处理
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {// 如果在播放的时候切换屏幕则保存当前观看的位置
            outState.putInt("position", mediaPlayer.getCurrentPosition());
        }
        super.onSaveInstanceState(outState);
    }
    /* 播放视频 */
    private void playMedia() {
/* 初始化状态 */
        mediaPlayer.reset();
/* 设置声音流类型 */
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
/* 设置mp3\mp4加载路径 */
        try {
            if(getIntent()!=null){
                Intent intent = getIntent();
                 path = intent.getStringExtra("path");
            }
            mediaPlayer.setDataSource(path);
// 缓冲
            mediaPlayer.prepare();
// 开始播放
            mediaPlayer.start();
// 具体位置
            mediaPlayer.seekTo(position);
// 视频输出到View
            mediaPlayer.setDisplay(mSurfaceView.getHolder());
// 重置位置为0
            position = 0;
        } catch (IllegalArgumentException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalStateException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
