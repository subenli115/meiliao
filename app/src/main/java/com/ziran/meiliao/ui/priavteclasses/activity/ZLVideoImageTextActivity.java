//package com.ziran.meiliao.ui.priavteclasses.activity;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//
//import com.pili.pldroid.player.widget.PLVideoTextureView;
//import com.ziran.meiliao.R;
//import com.ziran.meiliao.app.WpyxConfig;
//import com.ziran.meiliao.common.baserx.RxManagerUtil;
//import com.ziran.meiliao.common.commonutils.LogUtils;
//import com.ziran.meiliao.common.commonwidget.OnNoDoubleClickListener;
//import com.ziran.meiliao.common.security.AES;
//import com.ziran.meiliao.constant.ApiKey;
//import com.ziran.meiliao.constant.AppConstant;
//import com.ziran.meiliao.envet.CommonMediaControllerListener;
//import com.ziran.meiliao.envet.LiveCallBack;
//import com.ziran.meiliao.ui.base.ShareActivity;
//import com.ziran.meiliao.ui.bean.MediaDetailBean;
//import com.ziran.meiliao.ui.priavteclasses.bean.ZhuanLanData;
//import com.ziran.meiliao.ui.priavteclasses.fragment.ZLVideoImageTextFragment;
//import com.ziran.meiliao.ui.priavteclasses.util.VideoPlayerHelper1;
//import com.ziran.meiliao.utils.MusicPanelFloatManager;
//import com.ziran.meiliao.utils.StringUtils;
//import com.ziran.meiliao.widget.ARewardV1View;
//import com.ziran.meiliao.widget.HistoryCourseMediaController;
//import com.ziran.meiliao.widget.PlayPauseView;
//
//import butterknife.Bind;
//import rx.functions.Action1;
//
//
//public class ZLVideoImageTextActivity extends ShareActivity {
//
//    @Bind(R.id.VideoView)
//    PLVideoTextureView mVideoView;
//    @Bind(R.id.iv_cover)
//    ImageView mIvCover;
//    @Bind(R.id.media_controller)
//    HistoryCourseMediaController mMediaController;
//    @Bind(R.id.LoadingView)
//    LinearLayout mLoadingView;
//    @Bind(R.id.centerPlayPauseView)
//    PlayPauseView mCenterPlayPauseView;
//    @Bind(R.id.fl_subscribe_video)
//    RelativeLayout mFlSubscribeVideo;
//    @Bind(R.id.frameLayout)
//    View mContentView;
//    @Bind(R.id.aRewardView)
//    ARewardV1View mARewardV1View;
//    private VideoPlayerHelper1 mVideoPlayerHelper;
//
//    @Override
//    public int getLayoutId() {
//        return R.layout.activity_zl_video;
//    }
//
//    @Override
//    public void initPresenter() {
//
//    }
//
//    private String typeId;
//
//    @Override
//    protected void initBundle(Bundle extras) {
//        typeId = extras.getString("typeId");
//    }
//
//    ZLVideoImageTextFragment imageTextFragment;
//
//    @Override
//    public void initView() {
//        imageTextFragment = new ZLVideoImageTextFragment();
//        initFragment(imageTextFragment);
//        mMediaController.setWatchCount(-1);
//        mMediaController.setPlayBtnState(View.GONE);
//
//        MusicPanelFloatManager.getInstance().res(true);
//        mVideoPlayerHelper = new VideoPlayerHelper1(new LiveCallBack() {
//            @Override
//            public void postShiKan() {
//
//            }
//
//            @Override
//            public void showBuyTip() {
//            }
//
//            @Override
//            public boolean isBuyCourse() {
//                return false;
//            }
//
//            @Override
//            public void historyShiKan() {
//
//            }
//        });
//        mMediaController.setCommonMediaControllerListener(new CommonMediaControllerListener() {
//            @Override
//            public void changeScreen(boolean isFull) {
//                if (!mediaTextData.isBuy())
//                    mARewardV1View.setVisibility(isFull?View.GONE:View.VISIBLE);
//                mVideoPlayerHelper.changeFull(isFull, ZLVideoImageTextActivity.this, mContentView, mFlSubscribeVideo);
//            }
//
//            @Override
//            public void onBack() {
//                if (!mContentView.isShown()){
//                    if (!mediaTextData.isBuy())
//                        mARewardV1View.setVisibility(View.VISIBLE);
//                    mVideoPlayerHelper.changeFull(false, ZLVideoImageTextActivity.this, mContentView, mFlSubscribeVideo);
//                }else{
//                    onBackPressed();
//                }
//            }
//
//            @Override
//            public void onShare() {
//
//            }
//        });
//        mMediaController.setHistoryMediaControllerListener(imageTextFragment);
//
//        mVideoPlayerHelper.init(mVideoView, mLoadingView, mMediaController, mIvCover);
//        mVideoPlayerHelper.setIsLiveStreaming(0, 2);
//        mMediaController.setMediaPlayer(mVideoView);
//        mCenterPlayPauseView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startPlay();
//            }
//        });
//        mRxManager.on(AppConstant.RXTag.PLAYER_END, new Action1<Boolean>() {
//            @Override
//            public void call(Boolean aBoolean) {
//                ZhuanLanData data = WpyxConfig.getZhuanLanData();
//                LogUtils.logd("data:" + data);
//                if (data.getDirBean() != null) {
//                    data.getDirBean().setProgress("100");
//                    data.getDirBean().setStudyStatus(2);
//                }
//                if (data != null && !"100".equals(data.getProgress())) {
//                    data.setProgress("100");
//                    RxManagerUtil.post(AppConstant.RXTag.UPDATE_STUDY_FINISH, true);
//                }
//                mMediaController.setPlayBtnState(View.GONE);
//                mMediaController.setReset();
//            }
//        });
//
//        mARewardV1View.setOnClick(new OnNoDoubleClickListener() {
//            @Override
//            protected void onNoDoubleClick(View v) {
//                //订阅
//                imageTextFragment. buy();
//            }
//        });
//
//        mRxManager.on("MediaDetailBean", new Action1<MediaDetailBean.DataBean>() {
//            @Override
//            public void call(MediaDetailBean.DataBean result) {
//                mediaTextData = result;
//                musicUrl = AES.get().decrypt(result.getUrl()).trim();
//                mVideoPlayerHelper.setCoverView(musicUrl);
//                if (!result.isBuy()) {
//                    String text = StringUtils.format("订阅 ( ¥%d )", result.getBuyCount());
//                    mARewardV1View.setPrice(text);
//                    mARewardV1View.setParmes(0, 0);
//                    mARewardV1View.setVisibility(View.VISIBLE);
//                }
//            }
//        });
//        mRxManager.on(ApiKey.SPEC_COLUMN_BUY, new Action1<Boolean>() {
//            @Override
//            public void call(Boolean result) {
//                mARewardV1View.setVisibility(View.GONE);
//            }
//        });
//    }
//
//    private String musicUrl;
//    private  MediaDetailBean.DataBean  mediaTextData;
//    private void startPlay() {
//
//        mCenterPlayPauseView.setVisibility(View.GONE);
//        mMediaController.setPlayBtnState(View.VISIBLE);
//
//        if (mVideoPlayerHelper.startHistoryPlay(musicUrl)) {
//            mMediaController.startPlay();
//        } else {
//            mMediaController.updatePausePlay();
//        }
//    }
//
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        mVideoPlayerHelper.onResume();
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        mVideoPlayerHelper.onDestroy();
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        mVideoPlayerHelper.onDestroy();
//    }
//
//
//
//}
