package com.ziran.meiliao.ui.priavteclasses.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pili.pldroid.player.widget.PLVideoTextureView;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.WpyxConfig;
import com.ziran.meiliao.common.base.BaseFragmentAdapter;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.commonutils.ArrayUtils;
import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.CommonMediaControllerListener;
import com.ziran.meiliao.envet.HistoryMediaControllerListener;
import com.ziran.meiliao.envet.LiveCallBack;
import com.ziran.meiliao.ui.base.ShareActivity;
import com.ziran.meiliao.ui.bean.SubscribeAudioDataBean;
import com.ziran.meiliao.ui.priavteclasses.bean.ZhuanLanData;
import com.ziran.meiliao.ui.priavteclasses.fragment.EditCommentFragment;
import com.ziran.meiliao.ui.priavteclasses.fragment.SJKSubscribeAudioProfitFragment;
import com.ziran.meiliao.ui.priavteclasses.fragment.SJKSubscribePPTFragment;
import com.ziran.meiliao.ui.priavteclasses.fragment.SJKSubscribeVideoProfitFragment;
import com.ziran.meiliao.ui.priavteclasses.model.BuyCallBack;
import com.ziran.meiliao.ui.priavteclasses.util.VideoPlayerHelper1;
import com.ziran.meiliao.utils.MusicPanelFloatManager;
import com.ziran.meiliao.widget.HistoryCourseMediaController;
import com.ziran.meiliao.widget.PlayPauseView;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/15 10:09
 * @des
 * @updateAuthor
 * @updateDate
 * @updateDes
 */

public class SubscribeVideoActivity extends ShareActivity {


    private static boolean mfree=false;
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
    HistoryCourseMediaController mLiveMediaController;
    private VideoPlayerHelper1 mVideoPlayerHelper;

    @Bind(R.id.tv_subscribe_audio_dingyue)
    TextView tvSubscribeAudioSub;
    List<Fragment> fragments;

    private EditCommentFragment mEditCommentFragment;
    private SJKSubscribeVideoProfitFragment mSubscribeVideoProfitFragment;
    protected int defVideoHeight;
    private String subscriptionId;
    private String targetId;


    public static void startAction(Context context, String subscriptionId, String targetId) {
        Intent intent = new Intent(context, SubscribeVideoActivity.class);
        intent.putExtra(AppConstant.RXTag.AUDIO_ID, targetId);
        intent.putExtra(AppConstant.ExtraKey.FROM_ID, subscriptionId);
        context.startActivity(intent);
    }

    public static void startAction(Context context, String subscriptionId, String targetId,boolean free) {
        mfree=free;
        Intent intent = new Intent(context, SubscribeVideoActivity.class);
        intent.putExtra(AppConstant.ExtraKey.FROM_ID, subscriptionId);
        intent.putExtra(AppConstant.RXTag.AUDIO_ID, targetId);
        context.startActivity(intent);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_subscribe_video;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    boolean editCommentFragmentShowOrHide = false;

    @Override
    public void initView() {
        Intent intent = getIntent();
         subscriptionId = intent.getStringExtra(AppConstant.ExtraKey.FROM_ID);
        targetId = intent.getStringExtra(AppConstant.RXTag.AUDIO_ID);
        mLiveMediaController.setWatchCount(-1);
        mLiveMediaController.setPlayBtnState(View.GONE);
        defVideoHeight = getResources().getDimensionPixelSize(R.dimen.sjk_live_unfull_height);
        String[] tabs = ArrayUtils.getArray(this, R.array.subscribe_tag);
        if (EmptyUtils.isEmpty(fragments)) {
            fragments = new ArrayList<>();
            mSubscribeVideoProfitFragment = new SJKSubscribeVideoProfitFragment();
            fragments.add(mSubscribeVideoProfitFragment);
            SJKSubscribePPTFragment SJKSubscribePPTFragment = new SJKSubscribePPTFragment();
            SJKSubscribePPTFragment.setBuyCallBack(new BuyCallBack() {
                @Override
                public boolean getIsBuy() {
                    return isBuy;
                }
            });
            fragments.add(SJKSubscribePPTFragment);
            viewPager.setAdapter(new BaseFragmentAdapter(getSupportFragmentManager(), fragments));
            tabLayout.setupWithViewPager(viewPager);
            ViewUtil.changeTabText(tabLayout, tabs);
        }
        mRxManager.on(AppConstant.RXTag.SUBSCRIBE_COMMENT_FRAGMENT_SHOW_OR_HIDE, new Action1<Boolean>() {
            @Override
            public void call(Boolean showOrHide) {
                editCommentFragmentShowOrHide = showOrHide;
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                if (showOrHide) {
                    if (mEditCommentFragment == null) {
                        mEditCommentFragment = new EditCommentFragment();
                        ft.add(R.id.frameLayout, mEditCommentFragment, "EditCommentFragment");
                    } else {
                        ft.show(mEditCommentFragment);
                    }
                } else {
                    ft.hide(mEditCommentFragment);
                }
                ft.commit();
            }
        });
        mRxManager.on(AppConstant.RXTag.SUBSCRIBE_UPDATE, new Action1<Message>() {
            @Override
            public void call(Message msg) {
                switch (msg.what) {
                    case 0:
                        mLiveMediaController.setCollect((Boolean) msg.obj);
                        break;
                    case 1:
                        mLiveMediaController.setLikes((Boolean) msg.obj, true);
                        break;
                    case 2:
                        setBuy((Boolean) msg.obj);
                        break;
                    case 10:
                        mDataBean = (SubscribeAudioDataBean.DataBean) msg.obj;
                        mLiveMediaController.setCollect(mDataBean.isIsCollect());
                        mLiveMediaController.setLikeCount(mDataBean.getLikeCount());
                        mLiveMediaController.setLikes(mDataBean.isIsLike(), false);
                        mVideoPlayerHelper.setCoverView(mDataBean.getPic());
                        mLiveMediaController.showShikan(!mfree);
                        ivCover.setVisibility(!mfree ? View.GONE : View.VISIBLE);
                        break;
                }
            }
        });
        ZhuanLanData zhuanLanData = WpyxConfig.getZhuanLanData();
        if (zhuanLanData != null) {
            tvSubscribeAudioSub.setText(zhuanLanData.getZhuanLanNeedCoin());
            buyState(mfree);
        }
        initVideoView();
    }

    private SubscribeAudioDataBean.DataBean mDataBean;

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
                mVideoPlayerHelper.changeFull(isFull, SubscribeVideoActivity.this, mContentView, mVideoController);
            }

            @Override
            public void onBack() {
                onBackPressed();
            }

            @Override
            public void onShare() {
                mSubscribeVideoProfitFragment.doWhat(SJKSubscribeAudioProfitFragment.WHAT_SHARE);
            }
        });
        mLiveMediaController.setHistoryMediaControllerListener(new HistoryMediaControllerListener() {
            @Override
            public void onCollect() {
                mSubscribeVideoProfitFragment.doWhat(SJKSubscribeAudioProfitFragment.WHAT_COLLECT);
            }

            @Override
            public void onLike() {
                mSubscribeVideoProfitFragment.doWhat(SJKSubscribeAudioProfitFragment.WHAT_LIKES);
            }

            @Override
            public void playOrPause() {
                startPlay();
            }
        });
        mVideoPlayerHelper.init(mPLVideoTextureView, mLoadingView, mLiveMediaController, ivCover);
        mVideoPlayerHelper.setIsLiveStreaming(0, 2);
        mLiveMediaController.setMediaPlayer(mPLVideoTextureView);

        mPlayPauseView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPlay();
            }
        });
        mRxManager.on(AppConstant.RXTag.PLAYER_END, new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                LogUtils.logd("PLAYER_END:" + "11111111111");
                changeScreen();
//                ZhuanLanData data = WpyxConfig.getZhuanLanData();
//                if (data.getDirBean() != null) {
//                    data.getDirBean().setProgress("100");
//                    data.getDirBean().setStudyStatus(2);
//                }
                mSubscribeVideoProfitFragment.updateStudyFinish();
                RxManagerUtil.post(AppConstant.RXTag.UPDATE_STUDY_FINISH, true);
//                Log.e("UPDATE_STUDY_FINISH",""+data.getProgress());
//                if (data != null && !"100".equals(data.getProgress())) {
//                    data.setProgress("100");
//                }
                mLiveMediaController.setPlayBtnState(View.GONE);
                mPlayPauseView.setVisibility(View.VISIBLE);
                mLiveMediaController.setReset();
            }
        });
    }

    private void startPlay() {
        if (!mfree) {
            ToastUitl.showShort("该课程需订阅才能观看");
            return;
        }
        mVideoPlayerHelper.setVisibtion();
        mPlayPauseView.setVisibility(View.GONE);
        mLiveMediaController.setPlayBtnState(View.VISIBLE);
        if(mDataBean==null){
            return;
        }
        if (mVideoPlayerHelper.startHistoryPlay(mDataBean.getAESUrl())) {
            mLiveMediaController.startPlay();
        } else {
            mLiveMediaController.updatePausePlay();
        }
    }

    private boolean isBuy;

    public void buyState(boolean isBuy) {
        this.isBuy = isBuy;
        if (isBuy) {
            mLiveMediaController.showShikan(false);
            tvSubscribeAudioSub.setVisibility(View.GONE);
        } else {
            mLiveMediaController.showShikan(true);
            tvSubscribeAudioSub.setVisibility(View.VISIBLE);
        }
        ivCover.setVisibility(isBuy ? View.VISIBLE : View.GONE);
    }

    public void changeScreen() {
        if (mLiveMediaController != null && mLiveMediaController.isFullScreen()) {
            mLiveMediaController.changeScreen();
            mVideoPlayerHelper.changeFull(false, SubscribeVideoActivity.this, mContentView, mVideoController);
        }
    }

    @Override
    public void onBackPressed() {
        try {
            boolean flag = true;
            if (mLiveMediaController != null && mLiveMediaController.isFullScreen()) {
                mLiveMediaController.changeScreen();
                mVideoPlayerHelper.changeFull(false, SubscribeVideoActivity.this, mContentView, mVideoController);
                flag = false;
            }
            if (mEditCommentFragment != null && editCommentFragmentShowOrHide) {
                mEditCommentFragment.hide();
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
                mSubscribeVideoProfitFragment.doWhat(SJKSubscribeAudioProfitFragment.WHAT_BUY);
                break;
        }
    }


    @Override
    public void onStart(SHARE_MEDIA share_media) {

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void setBuy(boolean isBuy) {
        buyState(isBuy);
        if(isBuy){
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mVideoPlayerHelper.onPause();
        mLiveMediaController.setPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVideoPlayerHelper.setVisibtion(WpyxConfig.pic);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        super.onSaveInstanceState(outState);
        outState.putString("position", mDataBean.getPic());
        WpyxConfig.pic=mDataBean.getPic();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }

    @Override
    protected void onDestroy() {
        mVideoPlayerHelper.onDestroy();
        super.onDestroy();
    }
}
