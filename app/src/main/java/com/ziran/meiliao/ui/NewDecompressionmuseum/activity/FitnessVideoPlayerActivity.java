package com.ziran.meiliao.ui.NewDecompressionmuseum.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pili.pldroid.player.PLMediaPlayer;
import com.pili.pldroid.player.widget.PLVideoTextureView;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.WpyxConfig;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.commonutils.ArrayUtils;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.CommonMediaControllerListener;
import com.ziran.meiliao.envet.FitnessMediaControllerListener;
import com.ziran.meiliao.envet.HistoryMediaControllerListener;
import com.ziran.meiliao.envet.LiveCallBack;
import com.ziran.meiliao.ui.NewDecompressionmuseum.adapter.FitnessGridAdapter;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.ui.bean.FintessDetailBean;
import com.ziran.meiliao.ui.bean.FitnessSavePracticeBean;
import com.ziran.meiliao.ui.bean.RecListMainBean;
import com.ziran.meiliao.ui.bean.RecSummaryBean;
import com.ziran.meiliao.ui.priavteclasses.util.VideoPlayerHelper1;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.MusicPanelFloatManager;
import com.ziran.meiliao.widget.CustomGridView;
import com.ziran.meiliao.widget.FitnessHistoryCourseMediaController;
import com.ziran.meiliao.widget.FitnessPlayPauseView;
import com.ziran.meiliao.widget.pupop.BuyCourseKeepLandWindow;
import com.ziran.meiliao.widget.pupop.PopupWindowUtil;
import com.ziran.meiliao.widget.pupop.SharePopupWindow;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.soexample.ShareUtil;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;

import static com.ziran.meiliao.constant.ApiKey.JSCOURSE_SAVEPRACTICE;
import static com.ziran.meiliao.constant.ApiKey.RECORD_RECSHARESUCCESS;

/**健身功法播放器
 *
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/15 10:09
 * @des
 * @updateAuthor
 * @updateDate
 * @updateDes
 */

public class FitnessVideoPlayerActivity extends CommonHttpActivity<CommonPresenter, CommonModel> implements CommonContract.ActionView<Result, Result> {

    @Bind(R.id.tab_layout)
    TabLayout tabLayout;
    @Bind(R.id.viewpager)
    ViewPager viewPager;
    @Bind(R.id.iv_cover)
    ImageView ivCover;
    @Bind(R.id.centerPlayPauseView)
    FitnessPlayPauseView mPlayPauseView;

    @Bind(R.id.LoadingView)
    View mLoadingView;
    @Bind(R.id.fl_subscribe_video)
    RelativeLayout mVideoController;
    @Bind(R.id.ll_subscribe_video_content)
    View mContentView;
    @Bind(R.id.VideoView)
    PLVideoTextureView mPLVideoTextureView;
    @Bind(R.id.media_controller)
    FitnessHistoryCourseMediaController mLiveMediaController;
    private VideoPlayerHelper1 mVideoPlayerHelper;
    @Bind(R.id.rl)
    RelativeLayout rl;
    @Bind(R.id.tv_subscribe_audio_dingyue)
    TextView tvSubscribeAudioSub;


    protected int defVideoHeight;
    private String recId;
    private String[] tabs;
    private RecSummaryBean.DataBean.RecordBean shareBean;
    private String url;
    private String courseId;
    private RecListMainBean.DataBean.RecListBean mBeanCurrent;
    private PowerManager.WakeLock wakeLock;
    private RelativeLayout startPop;
    private  FintessDetailBean.DataBean  fdb;
    private RelativeLayout stopPop;
    private RelativeLayout buySuccess;
    private boolean isFirstFinished;
    private int mCurrent;
    private FintessDetailBean.DataBean.DetailsBean mCurrentBean;
    private String recPic;
    private FintessDetailBean.DataBean.JsCourseBean jsBean;
    private boolean isBuy;
    private String courseName;
    private FitnessGridAdapter fitnessGridAdapter;
    private int size;
    private boolean portrait;


    public static void startAction(Context context, FintessDetailBean.DataBean bean, int id) {
        Intent intent = new Intent(context, FitnessVideoPlayerActivity.class);
        intent.putExtra("FintessDetailBean",bean);
        intent.putExtra("CourseId",id+"");
        context.startActivity(intent);
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        portrait = newConfig.orientation == Configuration.ORIENTATION_PORTRAIT;
        tryFullScreen(!portrait);
    }

    private void tryFullScreen(boolean fullScreen) {
        if (this instanceof AppCompatActivity) {
            ActionBar supportActionBar = ((AppCompatActivity) this).getSupportActionBar();
            if (supportActionBar != null) {
                if (fullScreen) {
                    supportActionBar.hide();
                } else {
                    supportActionBar.show();
                }
            }
        }
        setFullScreen(fullScreen);
    }


    private void setFullScreen(boolean fullScreen) {
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        if (fullScreen) {
            attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            getWindow().setAttributes(attrs);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        } else {
            attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setAttributes(attrs);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.ac_fitness_video;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }


    @Override
    public void initView() {
        if(getIntent()!=null){
            Intent intent = getIntent();
            recId = intent.getStringExtra("recId");
            courseId = intent.getStringExtra("CourseId");
            fdb = ( FintessDetailBean.DataBean )intent.getSerializableExtra("FintessDetailBean");
        }
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        /*全屏显示*/
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        PowerManager powerManager = (PowerManager) getBaseContext().getSystemService(Context.POWER_SERVICE);
        this.wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, getClass().getName());
        wakeLock.acquire();
        mLiveMediaController.setWatchCount(-1);

        defVideoHeight = getResources().getDimensionPixelSize(R.dimen.sjk_live_unfull_height);
        tabs = ArrayUtils.getArray(this, R.array.record_tag);
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("recId",recId);
        if(fdb!=null){

            isBuy = fdb.isIsBuy();
            mCurrentBean = fdb.getDetails().get(mCurrent);
            size = fdb.getDetails().size();
        }
        mLiveMediaController.changeLeftRight(mCurrent,size);
        initVideoView();
        ShareUtil.addCallBack(mUMShareListener);
         ViewTreeObserver vto = rl.getViewTreeObserver();
         if(fdb!=null){
             courseName = fdb.getJsCoursesRecommend().get(0).getName();
         }
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(!isBuy){
                    mLiveMediaController.setBuyHide();
                        showStartPop("目前只能试学前2个课程 购买后可以学习完整的课程哦");
                }
                rl.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
        mRxManager.on(AppConstant.RXTag.REQ_BUY_COURSE, new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                mPresenter.postData(ApiKey.JSCOURSE_BUY, MapUtils.getCourseMap(courseId+""),Result.class);
            }
        });
        mHandler.postDelayed(r, 100);//延时100毫秒
        startPlay();
    }


    Handler mHandler = new Handler();
    Runnable r = new Runnable() {
        @Override
        public void run() {
            long position = mVideoPlayerHelper.getPosition();
            int time=  new Long(position).intValue();
            int  min=time/1000/60;
            mLiveMediaController.setName(fdb.getDetails().get(mCurrent).getName(),time);
            //每隔1s循环执行run方法
            mHandler.postDelayed(r, 1000);
        }
    };



    SharePopupWindow mSharePopupWindow;
    private void initVideoView() {
        MusicPanelFloatManager.getInstance().res(true);
        mVideoPlayerHelper = new VideoPlayerHelper1(new LiveCallBack() {
            @Override
            public void postShiKan() {

            }

            @Override
            public void showBuyTip() {

            }

            @Override
            public boolean isBuyCourse() {
                return false;
            }

            @Override
            public void historyShiKan() {

            }
        });
        mLiveMediaController.setFitnessControllerListener(new FitnessMediaControllerListener() {

            @Override
            public void showList(final CustomGridView gridView, final TextView tvLeft, final TextView tvRight) {

                if(gridView.getVisibility()==View.GONE){
                    gridView.setVisibility(View.VISIBLE);
                    mLiveMediaController.setTopBottomVisible(false);
                    mPlayPauseView.setVisibility(View.GONE);
                }
                 fitnessGridAdapter = new FitnessGridAdapter(mContext,fdb.getDetails());
                gridView.setAdapter(fitnessGridAdapter);
                //注册监听事件
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    public void onItemClick(AdapterView<?> parent, View v, int position, long id)
                    {
                        if(fdb.getDetails().get(position).getFree()==1||isBuy){
//                            mPlayPauseView.setVisibility(View.VISIBLE);
                            mLiveMediaController.setTopBottomVisible(true);
                            gridView.setVisibility(View.GONE);
                            mCurrent=position;
                            startPlay();
                           mLiveMediaController.changeLeftRight(mCurrent,size);
                        }else{
                            ToastUitl.showShort("购买后才可以学习该课程哦");
                        }

                    }
                });
                gridView.setOnTouchBlankPositionListener(new CustomGridView.OnTouchBlankPositionListener() {
                    @Override
                    public void onTouchBlank(MotionEvent event) {
                        gridView.setVisibility(View.GONE);
                    }
                });

            }

            @Override
            public void buy() {
                showBuyPop();
            }

            @Override
            public void left(TextView tvLeft, TextView tvRight) {

                if(mCurrent>0){
                    if(fdb.getDetails().get(mCurrent-1).getFree()==1||isBuy){
                        mCurrent--;
                        startPlay();
                    }else {
                        ToastUitl.showShort("购买后才可以学习该课程哦");
                    }
                }
                mLiveMediaController.changeLeftRight(mCurrent,size);
            }

            @Override
            public void right(TextView tvLeft, TextView tvRight) {
                if(mCurrent<fdb.getDetails().size()-1){
                    if(fdb.getDetails().get(mCurrent+1).getFree()==1||isBuy){
                        mCurrent++;
                        startPlay();
                    }else {
                        ToastUitl.showShort("购买后才可以学习该课程哦");
                    }
                }
                mLiveMediaController.changeLeftRight(mCurrent,size);
            }
        });

        mLiveMediaController.setCommonMediaControllerListener(new CommonMediaControllerListener() {
            @Override
            public void changeScreen(boolean isFull) {
                mVideoPlayerHelper.changeFull(isFull, FitnessVideoPlayerActivity.this, mContentView, mVideoController);
            }

            @Override
            public void onBack() {
                if(isBuy){
                    showBackPop();
                }else {
                    onBackPressed();
                }
            }

            @Override
            public void onShare() {
                SharePopupWindow.showPopup(mContext, mSharePopupWindow, shareBean.getShareTitle(), shareBean.getShareDesc(), shareBean
                        .getShareUrl(),shareBean.getSharePicture());

            }
        });
        mLiveMediaController.setHistoryMediaControllerListener(new HistoryMediaControllerListener() {
            @Override
            public void onCollect() {

            }

            @Override
            public void onLike() {
            }

            @Override
            public void playOrPause() {
                startPlay();
            }
        });
        mVideoPlayerHelper.init(mPLVideoTextureView, mLoadingView, mLiveMediaController, ivCover);
        mVideoPlayerHelper.setIsLiveStreaming(0, 2);
        mLiveMediaController.setMediaPlayer(mPLVideoTextureView);
        mLiveMediaController.setName(mCurrentBean.getName(),0);
        mPLVideoTextureView.setOnPreparedListener(new PLMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(PLMediaPlayer plMediaPlayer) {
            }
        });
        mPLVideoTextureView.setOnCompletionListener(new PLMediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(PLMediaPlayer plMediaPlayer) {
                mLiveMediaController.reset();
                plMediaPlayer.reset();
                mVideoPlayerHelper.onPause();
                mLiveMediaController.updatePausePlay();
                if(!isBuy){
                    if(mCurrentBean.getFree()==1&&mCurrent==1){
                        mLiveMediaController.setFreeFinish();
                    }
                }

                postPracticeSave(mCurrentBean.getId());
            }
        });
        mPlayPauseView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPlay();
            }
        });
        mRxManager.on(AppConstant.RXTag.SUBSCRIBE_UPDATE, new Action1<Message>() {
            @Override
            public void call(Message msg) {
                switch (msg.what) {
                    case 7:
                        mBeanCurrent = (RecListMainBean.DataBean.RecListBean) msg.obj;
                        url = mBeanCurrent.getRecordUrl();
                        startPlay();
                        break;
                    case 8:
                        courseId = (String) msg.obj;
                        break;
                }
            }
        });
        if (mLiveMediaController != null) {

            mLiveMediaController.changeScreen();

            mVideoPlayerHelper.changeFull(true, FitnessVideoPlayerActivity.this, mContentView, mVideoController);
        }
    }


    private void postPracticeSave(int id) {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("detailId",id+"");
        mPresenter.postData(JSCOURSE_SAVEPRACTICE,defMap, FitnessSavePracticeBean.class);
    }

    private void startPlay() {
        mVideoPlayerHelper.setVisibtion();
        mPlayPauseView.setVisibility(View.GONE);
        mLiveMediaController.setPlayBtnState(View.VISIBLE);
        mLiveMediaController.setMax("");

            url = fdb.getDetails().get(mCurrent).getUrl();
        if (mVideoPlayerHelper.startHistoryPlay(url)) {
            mLiveMediaController.startPlay();
        } else {
            mLiveMediaController.updatePausePlay();
        }
    }

    private void showBackPop() {
        Log.e("showBackPop","showBackPop");
        // 一个自定义的布局，作为显示的内容
        stopPop = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.pop_fitness_try_and_stop, null);
        final PopupWindow popupWindow = new PopupWindow(stopPop,
                AutoLinearLayout.LayoutParams.WRAP_CONTENT, AutoLinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);// 设置同意在外点击消失
        popupWindow.setFocusable(true);// 点击空白处时，隐藏掉pop窗口
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAtLocation(rl, Gravity.CENTER, 0, 0);
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        TextView  tvClose = stopPop.findViewById(R.id.tv_close);
        TextView  tvPass = stopPop.findViewById(R.id.tv_pass);
        mPLVideoTextureView.pause();
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                mPLVideoTextureView.start();
            }
        });
        tvPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                mRxManager.post(AppConstant.RXTag.FITNESS_BACK, HandlerUtil.obj(0));
                onBackPressed();
            }
        });
    }
//进入pop
    private void showStartPop(String str) {
        // 一个自定义的布局，作为显示的内容
        int[] location = new int[2];
        startPop = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.pop_fitness_try_and_buy, null);
        startPop.getLocationOnScreen(location);
        final PopupWindow popupWindow = new PopupWindow(startPop,
                AutoLinearLayout.LayoutParams.WRAP_CONTENT, AutoLinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);// 设置同意在外点击消失
        popupWindow.setFocusable(true);// 点击空白处时，隐藏掉pop窗口
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAtLocation(rl, Gravity.CENTER, 0, 0);
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        TextView tvContent = startPop.findViewById(R.id.tv_content);
        TextView  tvBuy = startPop.findViewById(R.id.tv_buy);
        TextView  tvClose = startPop.findViewById(R.id.tv_close);
        tvContent.setText(str);
        tvBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                showBuyPop();
            }
        });
        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
    }

    private void showBuyPop() {
        BuyCourseKeepLandWindow popupwindow = new BuyCourseKeepLandWindow(this);
        popupwindow.setText(fdb.getJsCoursesRecommend().get(0).getName(), String.valueOf(fdb.getUserCoin()), fdb.getNeedCoin()+1000,fdb.getNeedCoin());
        popupwindow.setStyle(fdb.getUserCoin()<=fdb.getNeedCoin());
        PopupWindowUtil.show((Activity) mContext, popupwindow);
    }

    /**
     *
     *  购买成功弹窗
     */
    private void showPopSuccess() {
        // 一个自定义的布局，作为显示的内容
        int[] location = new int[2];

        buySuccess = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.pop_fitness_share_course, null);
        buySuccess.getLocationOnScreen(location);
        final PopupWindow popupWindow = new PopupWindow(buySuccess,
                AutoLinearLayout.LayoutParams.MATCH_PARENT, AutoLinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);// 设置同意在外点击消失
        popupWindow.setFocusable(true);// 点击空白处时，隐藏掉pop窗口
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.showAtLocation(rl,Gravity.CENTER,0,0);
        TextView   tvShare = buySuccess.findViewById(R.id.tv_share_bottom);
        tvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share();
                popupWindow.dismiss();
            }
        });
    }
    private void share() {
        Log.e("mSharePopupWindow","mSharePopupWindow");
//        SharePopupWindow.showPopup(mContext, mSharePopupWindow, bean.getShareTitle(), bean.getShareDesc(), bean
//                .getShareUrl(),bean.getSharePic());
    }
    @Override
    public void onBackPressed() {
        try {
                super.onBackPressed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick(R.id.tv_subscribe_audio_dingyue)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_subscribe_audio_dingyue:
                break;
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mVideoPlayerHelper.onPause();
        mLiveMediaController.setPause();
        if (this.wakeLock.isHeld()) {

            if (wakeLock != null) {
                try {
                    wakeLock.release();
                } catch (Throwable th) {

                }
            } else {

            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVideoPlayerHelper.setVisibtion(WpyxConfig.pic);
        wakeLock.setReferenceCounted(false);
        this.wakeLock.acquire();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        super.onSaveInstanceState(outState);
//        outState.putString("position", mDataBean.getPic());
//        WpyxConfig.pic=mDataBean.getPic();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        mHandler.removeCallbacks(r);
        mVideoPlayerHelper.onDestroy();
        super.onDestroy();
        wakeLock = null;
    }

    @Override
    public void returnData(Result result) {
        if(result instanceof FitnessSavePracticeBean){
            FitnessSavePracticeBean bean=(FitnessSavePracticeBean)result;
             isFirstFinished = bean.getData().isIsFirstFinished();
             if(mCurrent==fdb.getDetails().size()-1){
                 isFirstFinished=true;
                 FitnessFinishActivity.startAction(mContext,isFirstFinished,bean,fdb.getJsCourse(),fdb.getUserPracticeCount());
             }
        }else {
            isBuy=true;
            RxManagerUtil.post(AppConstant.RXTag.REQ_BUY_COURSE_FINISH, true);
            showPopSuccess();
        }
    }


    @Override
    public void returnAction(Result result) {

    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {

    }

    @Override
    public void showEmtry() {

    }

    private UMShareListener mUMShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {
            ToastUitl.showShort("分享成功");
            Map<String, String> defMap = MapUtils.getDefMap(true);
            defMap.put("courseId",courseId+"");
            mPresenter.postData(RECORD_RECSHARESUCCESS,defMap, Result.class);
            mRxManager.post(AppConstant.RXTag.SWITCH_FRAGMENT, HandlerUtil.obj(0,"update"));
            ViewUtil.changeTabText(tabLayout, tabs);
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {

        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {

        }
    };

}
