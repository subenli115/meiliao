package com.ziran.meiliao.ui.NewDecompressionmuseum.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.commonwidget.RoundImageView;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.ui.NewDecompressionmuseum.contract.PracticeOneDataContract;
import com.ziran.meiliao.ui.NewDecompressionmuseum.presenter.PracticeOneDataPresenter;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.JdxShareBean;
import com.ziran.meiliao.ui.bean.PracticeOneDetailBean;
import com.ziran.meiliao.ui.bean.PracticeSaveBean;
import com.ziran.meiliao.ui.decompressionmuseum.util.BeizerAnimationUtil;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.MusicPanelFloatManager;
import com.ziran.meiliao.widget.BottomMbsrView;
import com.zhy.autolayout.AutoRelativeLayout;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;

import static com.ziran.meiliao.constant.ApiKey.PRACTIEACTIVITY_DetailONE;
import static com.ziran.meiliao.constant.ApiKey.PRACTIEACTIVITY_STATUSUPDATE;

/**
 *  音频练习 on 2018/8/20.
 */
public class WeekPracticeWithPlayerActicity extends CommonHttpActivity<PracticeOneDataPresenter, CommonModel> implements PracticeOneDataContract.View, View.OnClickListener, MediaPlayer.OnBufferingUpdateListener {
    private static Boolean mIsFinish;
    @Bind(R.id.ntb_title)
    NormalTitleBar ntbTitle;
    @Bind(R.id.iv_jyg_album_play_cover)
    RoundImageView ivJygAlbumPlayCover;
    @Bind(R.id.tv_player)
    TextView tvPlayer;
    @Bind(R.id.music_length)
    TextView musicLength;
    @Bind(R.id.music_cur)
    TextView musicCur;
    @Bind(R.id.seekBar)
    SeekBar seekBar;
    @Bind(R.id.bottom_exercise_view)
    BottomMbsrView mBottomExerciseView;
    @Bind(R.id.iv_bg)
    ImageView ivbg;
    @Bind(R.id.rootView)
    AutoRelativeLayout rootView;
    private int currentPosition;//当前音乐播放的进度

    private String itemId;
    private SimpleDateFormat format;
    private Timer timer;
    private boolean isSeekBarChanging;
    private String id;
    private String url;
    private JdxShareBean.DataBean mBean;
    private BeizerAnimationUtil mImageViewUtil;
    private RotateAnimation rotate;
    private MediaPlayer mediaPlayer;
    private boolean mRefresh;
    private Map<String, String> statusMap;
    private int practiceStatus;

    public static void startAction(Context mContext, String itemId, String id, JdxShareBean.DataBean result,Boolean isFinish) {
        Intent intent = new Intent(mContext, WeekPracticeWithPlayerActicity.class);
        Bundle bundle = new Bundle();
        mIsFinish=isFinish;
        bundle.putParcelable("JdxShareBean", result);
        intent.putExtra("itemId",itemId);
        intent.putExtra("id",id);
        intent.putExtras(bundle);
        intent.setExtrasClassLoader(JdxShareBean.class.getClassLoader());
        mContext.startActivity(intent);
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_jdx_player;
    }

    @Override
    public void initPresenter() {
        if (mModel != null && mPresenter != null) {
            mPresenter.setVM(this, mModel);
        }
    }

    @Override
    public void initView() {
        super.initView();
        if(getIntent()!=null){
            Intent intent = getIntent();
             itemId = intent.getStringExtra("itemId");
             id = intent.getStringExtra("id");
            mBean =  intent.getExtras().getParcelable("JdxShareBean");
        }
        mediaPlayer = new MediaPlayer();
        seekBar.setOnSeekBarChangeListener(new MySeekBar());
        format = new SimpleDateFormat("hh:mm:ss");
        ntbTitle.setTitleWeizhi();
        ntbTitle.setBackGroundColor(R.color.transparent);
        ntbTitle.setTvLeftVisiable(true, true);
        ntbTitle.setLeftImagSrc(R.mipmap.exercise_back);
        tvPlayer.setOnClickListener(this);

        ntbTitle.setOnivBackImagListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    setResult(2, new Intent());
                finish();
            }
        });
        if(mIsFinish){
            isFinish();
            return;
        }
        Map<String, String> oneMap = MapUtils.getDefMap(true);
        oneMap.put("itemId", itemId);
        oneMap.put("id", id);
        mPresenter.getPracticeOneData(PRACTIEACTIVITY_DetailONE,oneMap);

        statusMap = MapUtils.getDefMap(true);
        statusMap.put("id",id);
        statusMap.put("itemId",itemId+"");
        statusMap.put("status","1");
        initAnimation();

    }

    private void initAnimation() {
         rotate  = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        LinearInterpolator lin = new LinearInterpolator();
        rotate.setInterpolator(lin);
        rotate.setDuration(10000);//设置动画持续周期
        rotate.setRepeatCount(-1);//设置重复次数
        rotate.setFillAfter(true);//动画执行完后是否停留在执行完的状态
        rotate.setStartOffset(10);//执行前的等待时间
        ivJygAlbumPlayCover.setAnimation(rotate);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initMediaPlayer(url);
                } else {
                    ToastUitl.showShort("denied access");
                    finish();
                }
                break;
            default:
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MyAPP.mServiceManager.stop();
//        MusicPanelFloatManager.getInstance().setIvCover(ivJygAlbumPlayCover);
//        MusicPanelFloatManager.getInstance().registerProgressListener(mMyProgressView);
        HandlerUtil.runMain(new Runnable() {
            @Override
            public void run() {
                MusicPanelFloatManager.getInstance().updateRotationAnimation();
            }
        }, 300);
    }

    private void initMediaPlayer(String url) {
        try {
            mediaPlayer.setDataSource(url);//指定音频文件的路径
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setLooping(false);
            mediaPlayer.prepare();//让mediaplayer进入准备状态
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                public void onPrepared(MediaPlayer mp) {
                    seekBar.setMax(mediaPlayer.getDuration());
                    int time = mediaPlayer.getDuration();
                    setTime(time,true);
                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    tvPlayer.setBackgroundResource(R.mipmap.video_play);
                    if( practiceStatus==0){
                        mPresenter.changePracticeStatus(PRACTIEACTIVITY_STATUSUPDATE,statusMap);
                    }
                    mediaPlayer.seekTo(0);
                    isFinish();
                }
            });
            mediaPlayer.setOnBufferingUpdateListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void isFinish() {
        if (EmptyUtils.isNotEmpty(mBean)) {
            mBottomExerciseView.setId(id+"");
            mBottomExerciseView.setItemId(itemId);
            mBottomExerciseView.setUserHead(mBean.getUserPic(),mBean.getPicture());
            mBottomExerciseView.setTitleText( mBean.getDuration()+"" ,mBean.getWords(),mBean.getTitle());
            mBottomExerciseView.setHisId(mBean.getHisId()+"");
            mBottomExerciseView.setShow(true);
            mBottomExerciseView.setOnCloseListener(new BottomMbsrView.OnCloseListener() {
                @Override
                public void onClose() {
                    if(mIsFinish){
                        finish();
                    }else{
                        ivJygAlbumPlayCover.clearAnimation();
                        mBottomExerciseView.setVisibility(View.GONE);
                    }

                }
            });
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_player:
                if (!mediaPlayer.isPlaying()) {
                    playMusic();
                }else {
                     currentPosition = mediaPlayer.getCurrentPosition();
                    tvPlayer.setBackgroundResource(R.mipmap.video_play);
                    mediaPlayer.pause();//暂停播放
                    ivJygAlbumPlayCover.clearAnimation();
                }
                break;
        }
    }

    private void playMusic() {
        if (rotate != null) {
            ivJygAlbumPlayCover.startAnimation(rotate);
        }  else {
            ivJygAlbumPlayCover.setAnimation(rotate);
            ivJygAlbumPlayCover.startAnimation(rotate);
        }
        mediaPlayer.start();//开始播放
        tvPlayer.setBackgroundResource(R.mipmap.video_stop);
        mediaPlayer.seekTo(currentPosition);

        //监听播放时回调函数
        timer = new Timer();
        timer.schedule(new TimerTask() {

            Runnable updateUI = new Runnable() {
                @Override
                public void run() {
                    if(mediaPlayer!=null){
                        int time = mediaPlayer.getCurrentPosition();
                        setTime(time,false);
                    }
                }
            };
            @Override
            public void run() {
                if(!isSeekBarChanging&&mediaPlayer!=null&&seekBar!=null){
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    runOnUiThread(updateUI);
                }
            }
        },0,50);
    }

    private void setTime(int time,boolean isfirst) {
        int m= time / 1000 / 60;
        int s = (time / 1000) % 60;
        String mm = m+"";
        String ss=s+"";
        if(s<10){
           ss="0"+s;
        }
        if(m<10){
             mm="0"+m;
        }
        if(isfirst){
            musicCur.setText(00+":"+00);
            musicLength.setText(mm+":"+ss+"");
        }else{
            musicCur.setText(mm+":"+ss);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isSeekBarChanging = true;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        if (timer != null){
            timer.cancel();
            timer = null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void showOneData(PracticeOneDetailBean.DataBean result) {
        Glide.with(mContext).load(result.getBgPic()).error(R.mipmap.jdx_bg_live).into(ivbg);
         url = result.getUrl();
         initMediaPlayer(url);
         practiceStatus = result.getPracticeStatus();
        ntbTitle.setTitleText(result.getItemTitle());
        ImageLoaderUtils.displayTager(mContext, ivJygAlbumPlayCover, result.getRoundPic(), R.mipmap.ic_jyg_share_music_norm);
        playMusic();
    }

    @Override
    public void showOneSaveData(PracticeSaveBean.DataBean result) {

    }

    @Override
    public void showPracticeStatusData(JdxShareBean.DataBean result) {

    }

    @Override
    public void onBufferingUpdate(MediaPlayer mediaPlayer, final int i) {
        seekBar.setSecondaryProgress(i);
    }


    /*进度条处理*/
    public class MySeekBar implements SeekBar.OnSeekBarChangeListener {

        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {



        }

        /*滚动时,应当暂停后台定时器*/
        public void onStartTrackingTouch(SeekBar seekBar) {
            isSeekBarChanging = true;
        }
        /*滑动结束后，重新设置值*/
        public void onStopTrackingTouch(SeekBar seekBar) {
            isSeekBarChanging = false;
            mediaPlayer.seekTo(seekBar.getProgress());
        }
    }
}
