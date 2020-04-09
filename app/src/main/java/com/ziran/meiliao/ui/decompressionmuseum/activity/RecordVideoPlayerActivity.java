package com.ziran.meiliao.ui.decompressionmuseum.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Message;
import android.os.PowerManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pili.pldroid.player.PLMediaPlayer;
import com.pili.pldroid.player.widget.PLVideoTextureView;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.WpyxConfig;
import com.ziran.meiliao.common.base.BaseFragmentAdapter;
import com.ziran.meiliao.common.commonutils.ArrayUtils;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.security.AES;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.CommonMediaControllerListener;
import com.ziran.meiliao.envet.HistoryMediaControllerListener;
import com.ziran.meiliao.envet.LiveCallBack;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.ui.bean.RecListMainBean;
import com.ziran.meiliao.ui.bean.RecSummaryBean;
import com.ziran.meiliao.ui.decompressionmuseum.fragment.CatalogVideoFragment;
import com.ziran.meiliao.ui.decompressionmuseum.fragment.IntroduceVideoFragment;
import com.ziran.meiliao.ui.priavteclasses.util.VideoPlayerHelper1;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.MusicPanelFloatManager;
import com.ziran.meiliao.widget.NewHistoryCourseMediaController;
import com.ziran.meiliao.widget.PlayPauseView;
import com.ziran.meiliao.widget.pupop.SharePopupWindow;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.soexample.ShareUtil;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;

import static com.ziran.meiliao.constant.ApiKey.RECORD_GETRECSUMMARY;
import static com.ziran.meiliao.constant.ApiKey.RECORD_RECSHARESUCCESS;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/15 10:09
 * @des
 * @updateAuthor
 * @updateDate
 * @updateDes
 */

public class RecordVideoPlayerActivity extends CommonHttpActivity<CommonPresenter, CommonModel> implements CommonContract.ActionView<Result, Result> {

    @Bind(R.id.tab_layout)
    TabLayout tabLayout;
    @Bind(R.id.viewpager)
    ViewPager viewPager;
    @Bind(R.id.iv_cover)
    ImageView ivCover;
    @Bind(R.id.centerPlayPauseView)
    PlayPauseView mPlayPauseView;

    @Bind(R.id.LoadingView)
    View mLoadingView;
    @Bind(R.id.fl_subscribe_video)
    RelativeLayout mVideoController;
    @Bind(R.id.ll_subscribe_video_content)
    View mContentView;
    @Bind(R.id.VideoView)
    PLVideoTextureView mPLVideoTextureView;
    @Bind(R.id.media_controller)
    NewHistoryCourseMediaController mLiveMediaController;
    private VideoPlayerHelper1 mVideoPlayerHelper;
    @Bind(R.id.rl)
    RelativeLayout rl;
    @Bind(R.id.tv_subscribe_audio_dingyue)
    TextView tvSubscribeAudioSub;
    List<Fragment> fragments;

    protected int defVideoHeight;
    private CatalogVideoFragment mCatalogVideoFragment;
    private String recId;
    private String[] tabs;
    private IntroduceVideoFragment introduceVideoFragment;
    private RecSummaryBean.DataBean.RecordBean shareBean;
    private String url;
    private BaseFragmentAdapter bfAdapter;
    private String courseId;
    private View contentShareView;
    private SHARE_MEDIA mShareMedia;
    private RecListMainBean.DataBean.RecListBean mBeanCurrent;
    private PopupWindow popupWindow;
    private PowerManager.WakeLock wakeLock;


    public static void startAction(Context context, String recId) {
        Intent intent = new Intent(context, RecordVideoPlayerActivity.class);
        intent.putExtra("recId",recId);
        context.startActivity(intent);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_rec_video;
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
        }
        PowerManager powerManager = (PowerManager) getBaseContext().getSystemService(Context.POWER_SERVICE);
        this.wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, getClass().getName());
        wakeLock.acquire();
        mLiveMediaController.setWatchCount(-1);
        mLiveMediaController.setPlayBtnState(View.GONE);
        defVideoHeight = getResources().getDimensionPixelSize(R.dimen.sjk_live_unfull_height);
        tabs = ArrayUtils.getArray(this, R.array.record_tag);
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("recId",recId);
        mPresenter.postData(RECORD_GETRECSUMMARY,defMap, RecSummaryBean.class);
        initVideoView();
        ShareUtil.addCallBack(mUMShareListener);
    }
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
        mLiveMediaController.setCommonMediaControllerListener(new CommonMediaControllerListener() {
            @Override
            public void changeScreen(boolean isFull) {
                mVideoPlayerHelper.changeFull(isFull, RecordVideoPlayerActivity.this, mContentView, mVideoController);
            }

            @Override
            public void onBack() {
                onBackPressed();
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
        mPLVideoTextureView.setOnCompletionListener(new PLMediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(PLMediaPlayer plMediaPlayer) {
                mLiveMediaController.reset();
                plMediaPlayer.reset();
                mVideoPlayerHelper.onPause();
                showSharePopWindow();
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
                         url = AES.get().decrypt(mBeanCurrent.getRecordUrl());
                        startPlay();
                        break;
                    case 8:
                        courseId = (String) msg.obj;
                        break;
                }
            }
        });
    }
    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     *            屏幕透明度0.0-1.0 1表示完全不透明
     */
    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = ((Activity) mContext).getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        rl.setAlpha(bgAlpha);
    }

    private void share(SHARE_MEDIA shareMedia) {
        mShareMedia=shareMedia;
        ShareUtil.shareWeb1(mContext, mShareMedia, shareBean.getShareDesc(), shareBean.getSharePicture(), shareBean.getShareUrl(), shareBean.getShareTitle(), shareBean.getShareDesc());
    }
    private void showSharePopWindow() {
        // 一个自定义的布局，作为显示的内容
        int[] location = new int[2];
        contentShareView = LayoutInflater.from(mContext).inflate(R.layout.pop_rec_share, null);
        contentShareView.getLocationOnScreen(location);
          popupWindow = new PopupWindow(contentShareView,
                AutoLinearLayout.LayoutParams.WRAP_CONTENT, AutoLinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);// 设置同意在外点击消失
        popupWindow.setFocusable(true);// 点击空白处时，隐藏掉pop窗口
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        setBackgroundAlpha(0.5f);
       TextView tv_title=contentShareView.findViewById(R.id.tv_title);
        tv_title.setText("完成 "+mBeanCurrent.getTitle()+" 的学习！");
        TextView tv_detail=contentShareView.findViewById(R.id.tv_detail);
        popupWindow.showAtLocation(rl, Gravity.TOP, 0, 666);
        AutoLinearLayout wechat = contentShareView.findViewById(R.id.arl_share_wechat);
        wechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share(SHARE_MEDIA.WEIXIN.toSnsPlatform().mPlatform);

            }
        });
        AutoLinearLayout friend = contentShareView.findViewById(R.id.arl_share_friend);
        friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share(SHARE_MEDIA.WEIXIN_CIRCLE.toSnsPlatform().mPlatform);
            }
        });
        ImageView close = contentShareView.findViewById(R.id.iv_share_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });


        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackgroundAlpha(1.0f);
            }
        });
    }
    private void startPlay() {
        mVideoPlayerHelper.setVisibtion();
        mPlayPauseView.setVisibility(View.GONE);
        mLiveMediaController.setPlayBtnState(View.VISIBLE);
        mLiveMediaController.setMax("");

        if(url==null||url.equals("")){
            return;
        }
        if (mVideoPlayerHelper.startHistoryPlay(url)) {
            mLiveMediaController.startPlay();
        } else {
            mLiveMediaController.updatePausePlay();
        }
    }




    @Override
    public void onBackPressed() {
        try {
            boolean flag = true;
            if (mLiveMediaController != null && mLiveMediaController.isFullScreen()) {
                mLiveMediaController.changeScreen();
                mVideoPlayerHelper.changeFull(false, RecordVideoPlayerActivity.this, mContentView, mVideoController);
                flag = false;
            }
            if (flag) {
                super.onBackPressed();
            }
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
        mVideoPlayerHelper.onDestroy();
        super.onDestroy();
        wakeLock = null;
    }

    @Override
    public void returnData(Result result) {
        if(result instanceof RecSummaryBean){
            RecSummaryBean data = (RecSummaryBean) result;
            shareBean = data.getData().getRecord();
            mVideoPlayerHelper.setCoverView(shareBean.getCoverImage());
            RecSummaryBean.DataBean.RecordBean bean = data.getData().getRecord();
            if (EmptyUtils.isEmpty(fragments)) {
                fragments = new ArrayList<>();
                mCatalogVideoFragment = new CatalogVideoFragment();
                Bundle args = new Bundle();
                args.putString("html",bean.getCourseDesc());
                mCatalogVideoFragment.setArguments(args);
                fragments.add(mCatalogVideoFragment);
                introduceVideoFragment = new IntroduceVideoFragment();
                Bundle args1 = new Bundle();
                args1.putString("recId", recId+"");
                introduceVideoFragment.setArguments(args1);
                fragments.add(introduceVideoFragment);
                bfAdapter = new BaseFragmentAdapter(getSupportFragmentManager(), fragments);
                viewPager.setAdapter(bfAdapter);
                tabLayout.setupWithViewPager(viewPager);
                tabLayout.getTabAt(1).select();
                ViewUtil.changeTabText(tabLayout, tabs);
            }
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
