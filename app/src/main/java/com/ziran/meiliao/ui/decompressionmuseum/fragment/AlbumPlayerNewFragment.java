package com.ziran.meiliao.ui.decompressionmuseum.fragment;

import android.animation.ObjectAnimator;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.okhttplib.bean.DownloadFileInfo;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonutils.SPUtils;
import com.ziran.meiliao.common.commonutils.TimeUtil;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.constant.IConstants;
import com.ziran.meiliao.entry.MusicEntry;
import com.ziran.meiliao.service.MediaService;
import com.ziran.meiliao.service.ServiceManager;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpFragment;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.AlbumBean;
import com.ziran.meiliao.ui.bean.AlbumBgBean;
import com.ziran.meiliao.ui.bean.AlbumPracBean;
import com.ziran.meiliao.ui.bean.AlbumShareDataBean;
import com.ziran.meiliao.ui.bean.StringDataBean;
import com.ziran.meiliao.ui.decompressionmuseum.activity.AlbumPlayerActivity;
import com.ziran.meiliao.ui.decompressionmuseum.adapter.FilterBureauAdapter;
import com.ziran.meiliao.ui.decompressionmuseum.adapter.PracticeaDayAdapter;
import com.ziran.meiliao.ui.decompressionmuseum.util.BeizerAnimationUtil;
import com.ziran.meiliao.ui.settings.activity.RechargeActivity;
import com.ziran.meiliao.ui.settings.widget.DonutProgress;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.DownloadUtil;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.MusicPanelFloatManager;
import com.ziran.meiliao.utils.MyValueTempCache;
import com.ziran.meiliao.utils.StringUtils;
import com.ziran.meiliao.utils.WpyxDownloadUtil;
import com.ziran.meiliao.widget.BottomPlayerView;
import com.ziran.meiliao.widget.GlideRoundTransform;
import com.ziran.meiliao.widget.MyProgressView;
import com.ziran.meiliao.widget.PlayPauseView;
import com.ziran.meiliao.widget.pupop.BuyAlbumPopupWindow;
import com.ziran.meiliao.widget.pupop.PayResultPopupWindow;
import com.ziran.meiliao.widget.pupop.PopupWindowUtil;
import com.ziran.meiliao.widget.pupop.SharePopupWindow;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;



/**
 * 减压馆-音频播放
 * Created by Administrator on 2017/1/7.
 */

public class  AlbumPlayerNewFragment extends CommonHttpFragment<CommonPresenter, CommonModel> implements CommonContract.ActionView<Result, Result>, WpyxDownloadUtil.onDownloadListener{

    public static final int PLAY_MODE_ONE = 1;      //单首循环播放播放
    //    public static final int PLAY_MODE_RANDOM = 2;   //随机播放播放
    public static final int PLAY_MODE_LOOP = 0;    //列表循环播放

    //标记当前播放的模式
    private int currentPlayMode;
    //下载点击按钮
    @Bind(R.id.iv_jyg_album_play_download)
    ImageView ivJygPlayerDownload;
    //下载点击按钮
    @Bind(R.id.iv_jyg_album_play_cover)
    ImageView ivJYGCover;
    //收藏点击按钮
    @Bind(R.id.iv_jyg_album_play_follow)
    ImageView ivJygPlayerCollect;
    //当前播放模式切换按钮
    @Bind(R.id.iv_jyg_album_play_mode)
    ImageView ivJygPlayerMode;
    //上一首播放按钮
    @Bind(R.id.iv_jyg_album_play_pre)
    ImageView ivPre;
    //下一首播放按钮
    @Bind(R.id.iv_jyg_album_play_next)
    ImageView ivNext;
    //下一首播放按钮
    @Bind(R.id.iv_jyg_album_play_pause_or_play)
    PlayPauseView ivPlayOrPause;
    @Bind(R.id.myProgressView)
    MyProgressView mMyProgressView;
    @Bind(R.id.donutProgress)
    DonutProgress mDonutProgress;
    @Bind(R.id.activity_main)
    FrameLayout arlMain;
    @Bind(R.id.bottom_exercise_view)
    BottomPlayerView mBottomExerciseView;


    //记录是否已收藏
    private boolean isCollect;
    //当前数据
    private MusicEntry currMusicEntry;
    private String downloadUrl;
    private boolean isMobleCheck;

    //当前专辑Id
    private String albumId;
    private AlbumPlayerActivity albumPlayActivity;

    private SharePopupWindow mSharePopupWindow;
    private BeizerAnimationUtil mImageViewUtil;
    private AutoLinearLayout bottomView;
    private RecyclerView rvBbureau;
    private MediaPlayer mediaPlayer1;
    private FilterBureauAdapter adapter;

    private int mPosition=0;
    private float fl=-1;
    private LinearLayout bottomAlbumView;
    private AutoRelativeLayout bottomPunchView;
    private AutoRelativeLayout arlPunch;

    private  List<AlbumBgBean.DataBean> bgList;
    private Map<String, String> defMap;
    private AlbumBean albumBean;
    private Timer timer;


    //该页面的布局文件
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_jyg_album_player;
    }

    //初始化View
    @Override
    protected void initView() {
        super.initView();
        mRxManager.on(AppConstant.RXTag.MPS_COMPLETION, new Action1<String>() {

            @Override
            public void call(String aBoolean) {
                if(aBoolean.equals("onCompletion")){
                    isFinish();
                }

        }
        });
         timer = new Timer();
        //恢复上一次的播放模式
        if (mImageViewUtil == null) {
            mImageViewUtil = new BeizerAnimationUtil((ViewGroup) rootView, ivJYGCover, ivJygPlayerDownload);
        }
        mediaPlayer1 = new MediaPlayer();
        downloadUrls.addAll(DownloadUtil.getInstance().getDownloadUrl());
        initPlayerMode();
        albumId = getIntentExtra(getActivity().getIntent(), AppConstant.SPKey.ALBUM_ID);
        //获取当前选中的数据
        currMusicEntry = MyValueTempCache.getCurrentData();
        if(MyAPP.mServiceManager.getClick_from() == ServiceManager.CLICK_FROM_DOWNLOAD){
            MusicPanelFloatManager.getInstance().setData(currMusicEntry.getName(),currMusicEntry.getDuration(),currMusicEntry.getSharePic(),currMusicEntry.getFilePathZl(),currMusicEntry.getMusicId()+"",false);
        }else{
            MusicPanelFloatManager.getInstance().setData(currMusicEntry.getName(),currMusicEntry.getDuration(),currMusicEntry.getSharePic(),currMusicEntry.getAESUrl(),currMusicEntry.getMusicId()+"",false);
        }
        ImageLoaderUtils.displayTager(getContext(), ivJYGCover, MyAPP.mServiceManager.getAlbumPicture(), R.mipmap.ic_jyg_share_music_norm);
        if (currMusicEntry != null) {
            albumPlayActivity = ViewUtil.getActivity(getActivity());
            albumPlayActivity.setTitle(currMusicEntry.getName());
            setCollect(currMusicEntry.getIsCollectMusic());
            String aesUrl = currMusicEntry.getAESUrl();
            if (downloadUrls.contains(aesUrl)){
                downloadUrl = aesUrl;
                DownloadUtil.getInstance().addProgressView(aesUrl,mDonutProgress);
            }

            setDownload(FileUtil.fileIsExists(currMusicEntry.getFilePath()), aesUrl);
            defMap = saveStatus();
            //如果点击来源是专辑则需要判断当前网络状态,如果是3G,4G连接方式则需要提示
            if (MyAPP.mServiceManager.getClick_from() == ServiceManager.CLICK_FROM_ALBUM) {
                startPlay(true);
                //如果点击来源是减压馆页面的按钮,则继续播放
            } else if (MyAPP.mServiceManager.getClick_from() == ServiceManager.CLICK_FROM_MAIN_HOME) {
                startPlay(false);
                //如果点击来源是我的下载页面,则直接播放,
            } else if (MyAPP.mServiceManager.getClick_from() == ServiceManager.CLICK_FROM_DOWNLOAD) {
                ivJygPlayerDownload.setEnabled(false);
                startPlay(false);
            }
        }







        //注册状态栏的点击监听
        mRxManager.on(IConstants.NOTIFY_FLAG, new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                switch (integer) {
                    case MediaService.PAUSE_FLAG:
                        setPlayOrPauseRes(true);
                        MyAPP.mServiceManager.bgPause();
                        break;
                    case MediaService.PLAY_FLAG:
                        setPlayOrPauseRes(false);
                        MyAPP.mServiceManager.bgPlay();

                        break;
                    case MediaService.CLOSE_FLAG:
                        finish();
                        break;
                }
            }
        });
        //注册当前播放的数据并重设进度和最大值
        mRxManager.on(AppConstant.SPKey.PLAY_CURRENT_DATA, new Action1<MusicEntry>() {
            @Override
            public void call(MusicEntry musicEntry) {
                mMyProgressView.setProgressAndMax(0, TimeUtil.getMax(musicEntry.getDuration()));
                currMusicEntry = musicEntry;
                albumPlayActivity.setTitle(currMusicEntry.getName());
                setPlayOrPauseRes(false);
                setCollect(musicEntry.getIsCollectMusic());
                setDownload(FileUtil.fileIsExists(musicEntry.getFilePath()), musicEntry.getAESUrl());
            }
        });

        mRxManager.on(DownloadUtil.DOWNLOAD_FINISH, new Action1<DownloadFileInfo>() {
            @Override
            public void call(DownloadFileInfo downloadFileInfo) {
                if (EmptyUtils.isNotEmpty(downloadUrl) && downloadUrl.equals(downloadFileInfo.getUrl())) {
                    downloadUrls.remove(downloadFileInfo.getUrl());
                    setDownload(true, downloadFileInfo.getUrl());
                }else{
                    setDownload(true);
                }
            }
        });
        mRxManager.on(AppConstant.RXTag.ALBUM_COUNT_DOWN_TIME, new Action1<Long>() {
            @Override
            public void call(Long millisUntilFinished) {
                if (millisUntilFinished == -1) {
                    finish();
                }
            }
        });
        mMyProgressView.setOnSeekStopListenerTwo(new MyProgressView.OnSeekStopListenerTwo() {
            @Override
            public void onSeekStoptwo(MyProgressView myProgressView, int progress, int max) {
                mMyProgressView.setSeekTo();
                if(progress==max){
//                    stop();
                }
            }
        });
        mPresenter.postData(ApiKey.ALBUM_GETBGMUSIC, MapUtils.getDefMap(true), AlbumBgBean.class);
    }



    public void setPlayOrPauseRes(boolean isPlaying) {
        ivPlayOrPause.toggle(!isPlaying);
    }
    private BuyAlbumPopupWindow buyAlbumPopupwindow;

    private void showPayV13() {
            albumBean = MyAPP.mServiceManager.getAlbum();

        if (buyAlbumPopupwindow == null) {
            buyAlbumPopupwindow = new BuyAlbumPopupWindow(getContext());
            buyAlbumPopupwindow.setTitle(StringUtils.format(getString(R.string.buy_album), albumBean.getTitle()));
            buyAlbumPopupwindow.setAmount(albumBean.getNeedCoin());
            buyAlbumPopupwindow.setHead(albumBean.getPicture());
//            buyAlbumPopupwindow.setMemberPrice(albumBean.getLevelDetail());
            buyAlbumPopupwindow.setTvAmountMember((int) albumBean.getMemberPrice());
            buyAlbumPopupwindow.setStyle((albumBean.getUserCoin() - albumBean.getNeedCoin()) < 0);
            buyAlbumPopupwindow.setBuyAlbumClickListener(new BuyAlbumPopupWindow.BuyAlbumClickListener() {
                @Override
                public void buyAlbum(boolean needRecharge) {
                    if (needRecharge) {
                        RechargeActivity.startAction(getContext(), String.valueOf(albumBean.getUserCoin()));
                    } else {
                        mPresenter.postData(ApiKey.PURSE_BUY, MapUtils.getAlbumData(albumBean.getAlbumId(), -1), Result.class);
                    }
                }
            });
        }
        buyAlbumPopupwindow.setBalance(albumBean.getUserCoin());
        buyAlbumPopupwindow.show(this);
    }
    //开始播放
    private void startPlay(boolean isRePlay) {
        boolean flagIsPlay;
        if (isRePlay) {
            mMyProgressView.setMax(TimeUtil.getMax(currMusicEntry.getDuration()));
            long musicId = currMusicEntry.getId();
            if (musicId == MyAPP.mServiceManager.getCurMusicId()) {
                flagIsPlay = MyAPP.mServiceManager.getPlayState() == IConstants.MPS_PLAYING;
                if (flagIsPlay) {
                    setPlayOrPauseRes(false);
                } else {
                    int currentProgress = MyAPP.mServiceManager.position() / 1000;
                    mMyProgressView.setProgress(currentProgress);
                }
            } else {
                setPlayOrPauseRes(false);
                MyAPP.mServiceManager.playById((int) musicId);
                if (MyAPP.mServiceManager.getClick_from() == ServiceManager.CLICK_FROM_DOWNLOAD) {
                    MyAPP.mServiceManager.playZlUrl(currMusicEntry.getFilePathZl());
                }
                mPresenter.postData(ApiKey.listenUp, MapUtils.getOnlyCan("musicId", musicId), Result.class);
            }
        } else {//如果是正在播放,则恢复当前播放进度
            flagIsPlay = MyAPP.mServiceManager.getPlayState() == IConstants.MPS_PLAYING;
            int duration = MyAPP.mServiceManager.duration() / 1000;
            mMyProgressView.setMax(duration);
            int position = MyAPP.mServiceManager.position();
            mMyProgressView.setProgress(position / 1000);
            setPlayOrPauseRes(!flagIsPlay);
        }
    }

    //恢复上一次的播放模式
    private void initPlayerMode() {
        currentPlayMode = SPUtils.getInt(AppConstant.SPKey.PLAY_MODE, PLAY_MODE_ONE);
        switch (currentPlayMode) {
            case PLAY_MODE_ONE:
                ivJygPlayerMode.setImageResource(R.mipmap.player_circleone);
                MyAPP.mServiceManager.setPlayMode(IConstants.MPM_SINGLE_LOOP_PLAY);
                break;
            case PLAY_MODE_LOOP:
                ivJygPlayerMode.setImageResource(R.mipmap.player_circle);
                MyAPP.mServiceManager.setPlayMode(IConstants.MPM_LIST_LOOP_PLAY);
                break;
//            case PLAY_MODE_RANDOM:
//                ivJygPlayerMode.setImageResource(R.mipmap.player_random);
//                MyAPP.mServiceManager.setPlayMode(IConstants.MPM_RANDOM_PLAY);
//                break;
        }
    }

    //点击监听
    @OnClick({R.id.iv_switch_bg,R.id.iv_jyg_album_play_pause_or_play, R.id.iv_jyg_album_play_download, R.id.iv_jyg_album_play_follow, R.id.iv_jyg_album_play_mode, R.id.iv_jyg_album_play_pre, R.id.iv_jyg_album_play_next, R.id.iv_jyg_album_play_share})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_jyg_album_play_pause_or_play:
                playOrPause();
                return;
            case R.id.iv_jyg_album_play_mode:
                changeMode();
                return;
            case R.id.iv_switch_bg:
                showPopMusic();
                return;

        }
//        if (MyAPP.mServiceManager.getClick_from() == ServiceManager.CLICK_FROM_DOWNLOAD) {
//            switch (view.getId()) {
//                case R.id.iv_jyg_album_play_pre:
//                    MyAPP.mServiceManager.prev();
//                    return;
//                case R.id.iv_jyg_album_play_next:
//                    MyAPP.mServiceManager.next();
//                    return;
//            }
//        }

        if (!CheckUtil.check(getContext(), getView())) return;
        switch (view.getId()) {
            case R.id.iv_jyg_album_play_download:
                if (currMusicEntry != null) {
                    currMusicEntry.setAlbumId(albumId);
                    downloadUrl = currMusicEntry.getAESUrl();
                    DownloadUtil.getInstance().cancel(currMusicEntry.getAESUrl());
                    WpyxDownloadUtil.get().downMusic(getActivity(),this, albumId, currMusicEntry, false,false,mDonutProgress);
                }
                break;
            case R.id.iv_jyg_album_play_pre:
                if(!MyAPP.mServiceManager.getCurMusic().isSt()&&!MyAPP.mServiceManager.isIsbuy()){
                    showNoBuyPop();
                    MyAPP.mServiceManager.next();
                }
                if (mDonutProgress != null&&mDonutProgress.getProgress()==100) {
                    mDonutProgress.setVisibility(View.GONE);
                }
                MyAPP.mServiceManager.prev();
                break;
            case R.id.iv_jyg_album_play_next:
                nextMusic(true);
                if (mDonutProgress != null&&mDonutProgress.getProgress()==100) {
                    mDonutProgress.setVisibility(View.GONE);
                }
                break;
            case R.id.iv_jyg_album_play_follow:
                mPresenter.postAction(ApiKey.COLLECT_MUSIC, MapUtils.getCollect(isCollect, "musicIds", String.valueOf(currMusicEntry.getId())), Result.class);
                break;
            case R.id.iv_jyg_album_play_share:
                SharePopupWindow.showPopup(getActivity(), mSharePopupWindow, currMusicEntry);
                break;
        }
    }

    private void nextMusic(boolean isClick) {
        if(MyAPP.mServiceManager.getCurMusic()==null){
            return;
        }
        if(isClick){
            MyAPP.mServiceManager.next();
            if(!MyAPP.mServiceManager.getCurMusic().isSt()&&!MyAPP.mServiceManager.isIsbuy()){
                showNoBuyPop();

                MyAPP.mServiceManager.prev();
            }
        }else {


                }
        currMusicEntry=MyAPP.mServiceManager.getCurMusic();
        saveStatus();
    }

    /**
     * 播放音乐
     */
    protected void playBgMusic(final String url) {
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
//            createNextMediaPlayer(url);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void showNoBuyPop() {
        // 一个自定义的布局，作为显示的内容
        int[] location = new int[2];
        bottomAlbumView = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.popupw_bottom_album_view, null);
        bottomAlbumView.getLocationOnScreen(location);
        final PopupWindow popupWindow = new PopupWindow(bottomAlbumView,
                AutoLinearLayout.LayoutParams.MATCH_PARENT, AutoLinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);// 设置同意在外点击消失
        popupWindow.setFocusable(true);// 点击空白处时，隐藏掉pop窗口
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.showAtLocation(arlMain, Gravity.BOTTOM, 0, 0);
        LinearLayout    llXc = bottomAlbumView.findViewById(R.id.ll_xczs);
        LinearLayout llBuy= bottomAlbumView.findViewById(R.id.ll_buy);
        TextView tv_album_name=(TextView) bottomAlbumView.findViewById(R.id.tv_album_name);
        tv_album_name.setText(MyAPP.mServiceManager.getAlbumName1());
        MyAPP.mServiceManager.pause();
        TextView tv_music_name=(TextView) bottomAlbumView.findViewById(R.id.tv_music_name);
        tv_music_name.setText(MyAPP.mServiceManager.getCurMusic().getName());
        llBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                MyAPP.mServiceManager.rePlay();
                showPayV13();
            }
        });
        llXc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
    }


    private void showPopMusic() {
        // 一个自定义的布局，作为显示的内容
        int[] location = new int[2];
        bottomView = (AutoLinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.album_bottom_view, null);
        bottomView.getLocationOnScreen(location);
        final PopupWindow popupWindow = new PopupWindow(bottomView,
                AutoLinearLayout.LayoutParams.MATCH_PARENT, AutoLinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);// 设置同意在外点击消失
        popupWindow.setFocusable(true);// 点击空白处时，隐藏掉pop窗口
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.showAtLocation(arlMain, Gravity.BOTTOM, 0, 0);
        rvBbureau = bottomView.findViewById(R.id.rvBbureau);
        SeekBar seekBar=bottomView.findViewById(R.id.timeline);
        if(fl!=-1){
            seekBar.setProgress((int) (fl*100));
        }
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                fl = (float) i/100;
                if(mediaPlayer1!=null){
                    mediaPlayer1.setVolume(fl,fl);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        initFilterBureau();
    }
    private void showPunch(AlbumPracBean.DataBean data) {
        // 一个自定义的布局，作为显示的内容
        int[] location = new int[2];
        bottomPunchView = (AutoRelativeLayout) LayoutInflater.from(getContext()).inflate(R.layout.activity_punch_gift_album, null);
        bottomPunchView.getLocationOnScreen(location);
        final PopupWindow popupWindow = new PopupWindow(bottomPunchView,
                AutoLinearLayout.LayoutParams.MATCH_PARENT, AutoLinearLayout.LayoutParams.MATCH_PARENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);// 设置同意在外点击消失
        popupWindow.setFocusable(true);// 点击空白处时，隐藏掉pop窗口
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.showAtLocation(arlMain, Gravity.BOTTOM, 0, 0);
        TextView ivClose = bottomPunchView.findViewById(R.id.iv_close);
        final RecyclerView recyclerView=bottomPunchView.findViewById(R.id.recyclerView);
        arlPunch=bottomPunchView.findViewById(R.id.arl_punch_bg);
        TextView exerciseDays=bottomPunchView.findViewById(R.id.exercise_days);
        TextView tvSecond=bottomPunchView.findViewById(R.id.tv_hint_second);
        ImageView ivBg=bottomPunchView.findViewById(R.id.iv_bg);
        Glide.with(getContext()).load(data.getBackgroud()).transform(new GlideRoundTransform(getContext())).into(ivBg);
        if(data.getNextPrizeDay()!=0){
            tvSecond.setText("再连续练习"+data.getNextPrizeDay()+"天即可获得奖励");
        }
        MyAPP.mServiceManager.pause();
        exerciseDays.setText("连续练习"+data.getSerialDays()+"天");
        TextView tvNotice=bottomPunchView.findViewById(R.id.tv_notice);
        tvNotice.setText(data.getNotice());
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                MyAPP.mServiceManager.rePlay();
                nextMusic(false);
            }
        });
        PracticeaDayAdapter practiceaDayAdapter = new PracticeaDayAdapter(data.getPunch(),getContext(),data.getIsGif(),getActivity());
        recyclerView.setAdapter(practiceaDayAdapter);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        practiceaDayAdapter.animationfinish(new PracticeaDayAdapter.TheFinishListener() {
            @Override
            public void onFinish() {
                alphaAnimationOne();
            }
        });
    }
    public void alphaAnimationOne(){
        //透明度起始为1，结束时为0
        ObjectAnimator animator = ObjectAnimator.ofFloat(arlPunch, "alpha", 0f, 1f,0f);
        animator.setDuration(2000);//时间1s
        animator.start();

    }



    public void initFilterBureau() {
        if(bgList!=null){
            rvBbureau.setLayoutManager(new GridLayoutManager(getActivity(),2,GridLayoutManager.HORIZONTAL,false));
            int bgNum = MyAPP.mServiceManager.getmBgNum();
            adapter = new FilterBureauAdapter(getActivity(), bgList,bgNum);
            rvBbureau.setAdapter(adapter);
            adapter.setOnItemClickListener(new FilterBureauAdapter.OnItemClickListener() {
                @Override
                public void itemClick(int position, String url) {
                    mPosition=position;
                    MyAPP.mServiceManager.setBGNum(mPosition);
                    adapter.refreshBureau(position);
                    if(MyAPP.mServiceManager.getPlayState() == IConstants.MPS_PLAYING){
//                        playBgMusic(url);
                        MyAPP.mServiceManager.playBgMusic(url);
                    }

                }

            });
        }
    }



    //切换播放模式
    private void changeMode() {
        switch (currentPlayMode) {
            case PLAY_MODE_ONE:
                currentPlayMode = PLAY_MODE_LOOP;
                ivJygPlayerMode.setImageResource(R.mipmap.player_circle);
                MyAPP.mServiceManager.setPlayMode(IConstants.MPM_LIST_LOOP_PLAY);
                break;
//            case PLAY_MODE_LOOP:
//                currentPlayMode = PLAY_MODE_RANDOM;
//                ivJygPlayerMode.setImageResource(R.mipmap.player_random);
//                MyAPP.mServiceManager.setPlayMode(IConstants.MPM_RANDOM_PLAY);
//                break;
            case PLAY_MODE_LOOP:
                ivJygPlayerMode.setImageResource(R.mipmap.player_circleone);
                currentPlayMode = PLAY_MODE_ONE;
                MyAPP.mServiceManager.setPlayMode(IConstants.MPM_SINGLE_LOOP_PLAY);
                break;
        }
        SPUtils.setInt(AppConstant.SPKey.PLAY_MODE, currentPlayMode);
    }

    private void playOrPause() {
        if (MyAPP.mServiceManager.getPlayState()==IConstants.MPS_PREPARE) return;
        if (!MyAPP.mServiceManager.isPlaying()) {
            if (isMobleCheck) {
                mMyProgressView.setMax(currMusicEntry.getDuration()); //00:18:24
                long musicId = currMusicEntry.getId();
                MyAPP.mServiceManager.playById((int) musicId);
                mPresenter.postData(ApiKey.listenUp, MapUtils.getOnlyCan("musicId", musicId), Result.class);
                isMobleCheck = false;
            } else {
                MyAPP.mServiceManager.rePlay();
                MyAPP.mServiceManager.bgPlay();
            }
            setPlayOrPauseRes(false);
        } else {
            MyAPP.mServiceManager.bgPause();
            MyAPP.mServiceManager.pause();
            setPlayOrPauseRes(true);
        }

    }





    @Override
    public void returnData(Result result) {
        if(result!=null){
            if(result.getResultMsg().equals("购买成功")){
                MyAPP.mServiceManager.setIsbuy(true);
                MyAPP.mServiceManager.next();
                mRxManager.post(AppConstant.RXTag.MUSIC_BUY_SUCESS,true);
                finish();
            }
            if(result instanceof AlbumBgBean){
                AlbumBgBean result1 = (AlbumBgBean) result;
                bgList = result1.getData();
            }else if(result instanceof AlbumPracBean){
                showPunch(((AlbumPracBean) result).getData());
            }else if(result instanceof AlbumShareDataBean){
                final AlbumShareDataBean.DataBean mBean = ((AlbumShareDataBean) result).getData();
                mBottomExerciseView.setUserHead("",mBean.getPicture());
                mBottomExerciseView.setTitleText( mBean.getDuration()+"" ,mBean.getDays()+"",mBean.getAlbumName());
                mBottomExerciseView.setShow(true);
                MyAPP.mServiceManager.pause();
                albumPlayActivity.setTitleBar(false);
                mBottomExerciseView.setOnCloseListener(new BottomPlayerView.OnCloseListener() {
                    @Override
                    public void onClose() {
                        albumPlayActivity.setTitleBar(true);
                        if(mBean.isHaveShare()){
                            nextMusic(false);
                        }else {
                            mPresenter.postData(ApiKey.ALBUM_GETALBUMPRAC, MapUtils.getDefMap(true), AlbumPracBean.class);
                        }
                        MyAPP.mServiceManager.rePlay();
                    }
                });
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MusicPanelFloatManager.getInstance().setIvCover(ivJYGCover);
        MusicPanelFloatManager.getInstance().setMedia();
        MusicPanelFloatManager.getInstance().registerProgressListener(mMyProgressView);
        HandlerUtil.runMain(new Runnable() {
            @Override
            public void run() {
                MusicPanelFloatManager.getInstance().updateRotationAnimation();
            }
        }, 300);
    }

    @Override
    public void onPause() {
        super.onPause();
        MusicPanelFloatManager.getInstance().unRegisterProgressListener(mMyProgressView);
        MusicPanelFloatManager.getInstance().setIvCover(null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        if (mediaPlayer1 != null && mediaPlayer1.isPlaying()) {
//            mediaPlayer1.stop();
//            mediaPlayer1.release();
//            mediaPlayer1 = null;
//        }

        super.onDestroy();
    }
    //切换歌曲
    public void stopmediaPlayer() {
        if (mediaPlayer1 != null) {
            mediaPlayer1.reset();
        }
    }

    private void isFinish() {
        if(!MyAPP.mServiceManager.getCurMusic().isSt()&&!MyAPP.mServiceManager.isIsbuy()){
            showNoBuyPop();
        }
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("albumId", albumId);
        defMap.put("musicId", currMusicEntry.getMusicId() + "");
        mPresenter.postData(ApiKey.ALBUM_GETALBUMSHAREDATA, defMap, AlbumShareDataBean.class);
        if(mediaPlayer1!=null&&mediaPlayer1.isPlaying()){
            mediaPlayer1.pause();
        }
        timer.schedule(new TimerTask() {
            public void run() {
                MyAPP.mServiceManager.pause();
            } }, 1500);
    }

    private Map<String, String> saveStatus() {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("albumId", albumId);
        defMap.put("musicId", currMusicEntry.getMusicId() + "");
//        mPresenter.postData(ApiKey.ALBUM_SAVERECALBUM,defMap, AlbumBgMusicBean.class);
//        Gson gson=new Gson();
//        albumBean= MyAPP.mServiceManager.getAlbum();
//        HomeAlbumBean bean=new HomeAlbumBean();
//        bean.setMusicId(currMusicEntry.getMusicId()+"");
//        bean.setAlbumId(albumId);
//        bean.setAlbumName(albumBean.getTitle());
//        bean.setMusicName(currMusicEntry.getName());
//        bean.setPic(currMusicEntry.getSharePic());
//        String albumBeanString=gson.toJson(bean);
//        PrefUtils.putString("albumBean",albumBeanString,getContext());
        return defMap;
    }

    @Override
    public void returnAction(Result result) {
        ToastUitl.showShort(result.getResultMsg());
        boolean isCollect = getString(R.string.collect_ok).equals(result.getResultMsg());
        setCollect(isCollect);
        MusicEntry.doCollect(currMusicEntry);
        if(result instanceof StringDataBean){
            StringDataBean data = ((StringDataBean) result);

            if ("true".equals(data.getNornemData())) {
                showPayResultDialog(true);
            }
        }
    }

    private void showPayResultDialog(boolean result) {
        PayResultPopupWindow payResultPopupwindow = new PayResultPopupWindow(getContext());
        payResultPopupwindow.setResult(result);
        PopupWindowUtil.show(payResultPopupwindow);

    }
    public void setCollect(boolean isCollect) {
        if (isCollect) {
            ivJygPlayerCollect.setImageResource(R.mipmap.btn_collected_black);
        } else {
            ivJygPlayerCollect.setImageResource(R.mipmap.btn_collect_black);
        }
        this.isCollect = isCollect;
        if (currMusicEntry != null) {
            currMusicEntry.setIsCollectMusic(this.isCollect);
        }
    }

    @Override
    public void onFinish(String url, File file) {
        if(url.equals(currMusicEntry.getAESUrl())){
            downloadUrls.remove(url);
                if (mDonutProgress != null) {
                mDonutProgress.setVisibility(View.GONE);
                setDownload(true);
            }
        }
    }

    private void setDownload(boolean isDownload) {

        ivJygPlayerDownload.setVisibility(View.VISIBLE);
        if (isDownload) {
            ivJygPlayerDownload.setImageResource(R.mipmap.player_download_unclick);
        } else {
            ivJygPlayerDownload.setImageResource(R.mipmap.player_download);
        }
    }

    private void setDownload(boolean isDownload, String url) {
        if (downloadUrls.contains(url)) {
            mDonutProgress.setVisibility(View.VISIBLE);
            ivJygPlayerDownload.setVisibility(View.INVISIBLE);
            return;
        }
        mDonutProgress.setVisibility(View.INVISIBLE);
        setDownload(isDownload);
    }

    Set<String> downloadUrls = new HashSet<>();


    @Override
    public void onDownStart(String url) {
        if (mImageViewUtil != null) {
            mImageViewUtil.start();
        }
        if (!downloadUrls.contains(url)) {
            downloadUrls.add(url);
        }
        ivJygPlayerDownload.setVisibility(View.INVISIBLE);
        mDonutProgress.setVisibility(View.VISIBLE);
    }



}
