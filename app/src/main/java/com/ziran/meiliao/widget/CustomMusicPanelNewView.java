package com.ziran.meiliao.widget;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.app.WpyxConfig;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonutils.TimeUtil;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.envet.OnProgressChangeListener;
import com.ziran.meiliao.ui.decompressionmuseum.activity.AlbumPlayerActivity;
import com.ziran.meiliao.ui.priavteclasses.activity.ZLAudioImageTextActivity;
import com.ziran.meiliao.utils.MusicPanelFloatManager;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/9/8 14:18
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/9/8$
 * @updateDes ${TODO}
 */

public class CustomMusicPanelNewView extends RelativeLayout implements View.OnClickListener, OnProgressChangeListener {
    @Bind(R.id.iv_music_panel_cover)
    ImageView ivMusicPanelCover;
    @Bind(R.id.iv_music_panel_play_state)
    PlayPauseView mPlayPauseView;

    @Bind(R.id.tv_music_panel_title)
    TextView tvMusicPanelTitle;
    @Bind(R.id.myProgressView)
    MyProgressView mMyProgressView;
    @Bind(R.id.iv_music_panel_close)
    View ivMusicPanelClose;
    @Bind(R.id.fl_music_panel_play)
    View flPlay;

    private String musicUrl;

    private boolean isCloseToStopMusic = false;
    private long mMusicId = -1L;
    private MyThread thread;
//    private boolean isSubscription = false;

    public CustomMusicPanelNewView(Context context) {
        this(context, null);
    }

    public CustomMusicPanelNewView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomMusicPanelNewView(final Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.custom_music_panel_new, this, true);
        ButterKnife.bind(this, this);

        start();
        flPlay.setOnClickListener(this);
        ivMusicPanelClose.setOnClickListener(this);

        mMyProgressView.setOnSeekStopListener(new MyProgressView.OnSeekStopListener() {
            @Override
            public void onSeekStop(MyProgressView myProgressView, int progress) {
                MyAPP.mServiceManager.seekTo(progress*1000);
            }
        });
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isZl){
                    Bundle bundle1 = WpyxConfig.getBundle();
                    ZLAudioImageTextActivity.startAction(getContext(),bundle1);


                }else{
                    AlbumPlayerActivity.startAction(getContext(),MyAPP.mServiceManager.getAlbumId());
                }
            }
        });
    }

    @Override
    protected void onDetachedFromWindow() {
        MusicPanelFloatManager.getInstance().unRegisterProgressListener(this);
        super.onDetachedFromWindow();
        MusicPanelFloatManager.getInstance().removePlayOrPauseView(null);
        ButterKnife.unbind(this);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1){
                //do something
                if(mMyProgressView!=null&&MyAPP.mServiceManager.getCurMusic()!=null){
//                    if(mMyProgressView.getMax()==mMyProgressView.getProgress()){
//                        MyAPP.mServiceManager.rePlay();
//                    }


                    if(!MyAPP.mServiceManager.getCurMusic().isSt()&&!MyAPP.mServiceManager.isIsbuy()) {
                        MyAPP.mServiceManager.prev();

//                                    }
                    }
                }
            }
            super.handleMessage(msg);

        }
    };

    /**
     * 启动线程
     * */
    private void start() {
        if (thread == null) {
            thread = new MyThread();
            thread.start();
        }
    }

    /**
     * 停止线程
     * */
    private void stopTread() {
        if (thread != null) {
            thread.stop = true;
            thread = null;
        }
    }

    private class MyThread extends Thread {

        public boolean stop;

        public void run() {
            while (!stop) {
                // 处理功能

                // 通过睡眠线程来设置定时时间
                try {
                    Thread.sleep(1000);//每隔1s执行一次
                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };
    };
    public void updatePlayState() {
        updatePlayState(MyAPP.mServiceManager.isPlaying());
    }

    public void updatePlayState(boolean isPlaying) {
        mPlayPauseView.toggle(isPlaying);
        setCloseVisibility(isPlaying ? INVISIBLE : VISIBLE);
    }

    public void setIvMusicPanelCover(String url) {
        ImageLoaderUtils.displayCircle(getContext(), ivMusicPanelCover, url, R.mipmap.ic_loading_rectangle);
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
        if (MyAPP.mServiceManager.isPlaying() && MyAPP.mServiceManager.checkUrl(musicUrl)) {
            restartUpdate1();
        }
    }

    public void setTvMusicPanelTitle(String title) {
        ViewUtil.setText(tvMusicPanelTitle, title);
    }


    public void setTvMusicPanelDuration(String duration) {
        mMyProgressView.setProgressAndMax(0, TimeUtil.getMax(duration));
    }


    public void setCloseVisibility(int vis) {
        ivMusicPanelClose.setVisibility(vis);
    }


    public void restartUpdate() {
        updatePlayState(false);
    }

    public void restartUpdate1() {
        MusicPanelFloatManager.getInstance().registerProgressListener(this);
        postDelayed(new Runnable() {
            @Override
            public void run() {
                MusicPanelFloatManager.getInstance().setIvCover(getIvCover());
            }
        },300);
        updatePlayState();
    }

    public void setPlayClick(OnClickListener onClickListener) {
        if (onClickListener != null) flPlay.setOnClickListener(onClickListener);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fl_music_panel_play:
                onPlay(false);
                setCloseVisibility(MyAPP.mServiceManager.isPlaying() ? INVISIBLE : VISIBLE);
                break;
            case R.id.iv_music_panel_close:
                MyAPP.mServiceManager.stop();
                stopTread();
                break;
        }
    }

    private boolean isClose;

    public void onPlay(boolean isResPlay) {
        if (isZl) {
            MusicPanelFloatManager.getInstance().addPlayOrPauseView(mPlayPauseView);
            MusicPanelFloatManager.getInstance().registerProgressListener(this);
            MyAPP.mServiceManager.playZlUrl(musicUrl);

        } else {
            if (MyAPP.mServiceManager.isPlaying()) {
                MyAPP.mServiceManager.pause();
            } else {
                MyAPP.mServiceManager.rePlay();
            }
        }
    }


    public void setCloseToStop(boolean closeToStop) {
        this.isCloseToStopMusic = closeToStop;
    }

    public ImageView getIvCover() {
        return ivMusicPanelCover;
    }

    @Override
    public void setProgress(int progress) {
        if (mMyProgressView != null) mMyProgressView.setProgress(progress);
    }

    public long getMusicId() {
        return mMusicId;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicId(long musicId) {
        mMusicId = musicId;
    }
    private String subscriptionId;

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    private boolean isZl;
    public void setIsZhuanLan(boolean isZl) {
        this.isZl = isZl;
    }
}
