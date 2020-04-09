package com.ziran.meiliao.ui.priavteclasses.old;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.pili.pldroid.player.widget.PLVideoTextureView;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.base.BaseFragmentAdapter;
import com.ziran.meiliao.common.commonutils.ArrayUtils;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.BigInMediaControllerListener;
import com.ziran.meiliao.envet.CommonMediaControllerListener;
import com.ziran.meiliao.envet.HistoryMediaControllerListener;
import com.ziran.meiliao.envet.LiveCallBack;
import com.ziran.meiliao.ui.bean.SpecColumnData;
import com.ziran.meiliao.ui.bean.ZhuanLanBigInBean;
import com.ziran.meiliao.ui.priavteclasses.util.VideoPlayerHelper1;
import com.ziran.meiliao.utils.MusicPanelFloatManager;
import com.ziran.meiliao.widget.ARewardV2View;
import com.ziran.meiliao.widget.HistoryCourseMediaController;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import rx.functions.Action1;


public class ZhuanLanBigInVideoActivity extends BaseActivity {


    @Bind(R.id.aRewardView)
    ARewardV2View mARewardV2View;
    @Bind(R.id.iv_cover)
    ImageView ivCover;
    @Bind(R.id.media_controller)
    HistoryCourseMediaController mLiveMediaController;
    @Bind(R.id.LoadingView)
    View mLoadingView;
    @Bind(R.id.fl_subscribe_video)
    RelativeLayout mVideoController;
    @Bind(R.id.ll_subscribe_video_content)
    View mContentView;
    @Bind(R.id.VideoView)
    PLVideoTextureView mPLVideoTextureView;
    @Bind(R.id.tab_layout)
    TabLayout tabLayout;
    @Bind(R.id.viewpager)
    ViewPager viewPager;

    private SJKZhuanLanBigInFragment mMSJKZhuanLanBigInFragment;
    private String subscriptionId;
    private List<Fragment> fragments;
    private VideoPlayerHelper1 mVideoPlayerHelper;

    @Override
    public int getLayoutId() {
        return R.layout.activity_sjk_zhuanlan_big_in_video;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    public static void startAction(Context context, int specColumnId) {
        Intent intent = new Intent(context, ZhuanLanBigInVideoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(AppConstant.ExtraKey.SUBSCRIPTION_ID, String.valueOf(specColumnId));
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static void startAction(Context context, Object obj) {
        SpecColumnData specColumnData = EmptyUtils.parseObject(obj);
        startAction(context, specColumnData.getId());
    }
    private ZhuanLanBigInBean.DataBean.DirBean mCurrentDirBean ;

    @Override
    public void initView() {
        mLiveMediaController.setWatchCount(-1);
        mLiveMediaController.setFromType(2);
        mLiveMediaController.setPlayBtnState(View.GONE);
        try {
            subscriptionId = getIntent().getExtras().getString(AppConstant.ExtraKey.SUBSCRIPTION_ID);
        } catch (Exception e) {
            subscriptionId = "";
            e.printStackTrace();
        }
        mMSJKZhuanLanBigInFragment = new SJKZhuanLanBigInFragment();
        MusicPanelFloatManager.getInstance().res(true);
        String[] tabs = ArrayUtils.getArray(this, R.array.subscribe_tag);
        if (EmptyUtils.isEmpty(fragments)) {
            fragments = new ArrayList<>();
            fragments.add(mMSJKZhuanLanBigInFragment);
            SJKZhuanLanBigInPPTFragment pptFragment = new SJKZhuanLanBigInPPTFragment();
            pptFragment.setAudioFragment(mMSJKZhuanLanBigInFragment, "1");
            fragments.add(pptFragment);
            viewPager.setAdapter(new BaseFragmentAdapter(getSupportFragmentManager(), fragments));
            tabLayout.setupWithViewPager(viewPager);
            ViewUtil.changeTabText(tabLayout, tabs);
        }
        mARewardV2View.setOnClick(mMSJKZhuanLanBigInFragment.getArewardClick());
        mRxManager.on(AppConstant.RXTag.BIG_IN_TAG, new Action1<Message>() {
            @Override
            public void call(Message msg) {
                switch (msg.what) {
                    case 0:
                        mLiveMediaController.setGz((boolean) msg.obj);
                        break;
                    case 1:
                        setTitles((ZhuanLanBigInBean.DataBean) msg.obj);
                        break;
                    case 3:
                        mARewardV2View.setVisibility(View.GONE);
                        break;
                    case 2:
                        mCurrentDirBean = (ZhuanLanBigInBean.DataBean.DirBean) msg.obj;
                        mVideoPlayerHelper.startHistoryPlay(mCurrentDirBean.getAESUrl());
                        break;
                }
            }
        });
        initVideo();
    }

    private void initVideo() {

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
                mVideoPlayerHelper.changeFull(isFull, ZhuanLanBigInVideoActivity.this, mContentView, mVideoController);
            }

            @Override
            public void onBack() {
               onBackPressed();
            }

            @Override
            public void onShare() {
                mMSJKZhuanLanBigInFragment.share();
            }
        });
        mLiveMediaController.setBigInMediaControllerListener(new BigInMediaControllerListener() {
            @Override
            public void onGz() {
                mMSJKZhuanLanBigInFragment.gzZhuanLan();
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
                if (mCurrentDirBean==null) return;
                if (mVideoPlayerHelper.startHistoryPlay(mCurrentDirBean.getAESUrl())){
                    mLiveMediaController.startPlay();
                }else{
                    mLiveMediaController.updatePausePlay();
                }
            }
        });
        mVideoPlayerHelper.init(mPLVideoTextureView, mLoadingView, mLiveMediaController, ivCover);
        mVideoPlayerHelper.setIsLiveStreaming(0, 2);
        mLiveMediaController.setMediaPlayer(mPLVideoTextureView);
    }


    public void setTitles(ZhuanLanBigInBean.DataBean mDataBean) {
        if (EmptyUtils.isNotEmpty(mDataBean)) {
            mARewardV2View.setNeedCoin("专栏", mDataBean.getNeedCoin());
            mARewardV2View.setVisibility(mDataBean.isIsBuy() ? View.GONE : View.VISIBLE);
            mVideoPlayerHelper.setCoverView(mDataBean.getPic());
            mLiveMediaController.setGz(mDataBean.isIsGz());
            if (EmptyUtils.isNotEmpty(mDataBean.getDir()))
            mCurrentDirBean = mDataBean.getDir().get(0);
        }
    }

    @Override
    public void onBackPressed() {
        try {
            boolean flag = true;
            if (mLiveMediaController!=null && mLiveMediaController.isFullScreen()) {
                mLiveMediaController.changeScreen();
                mVideoPlayerHelper.changeFull(false, ZhuanLanBigInVideoActivity.this, mContentView, mVideoController);
                flag = false;
            }
            if (flag) {
                super.onBackPressed();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mVideoPlayerHelper.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVideoPlayerHelper.onResume();
    }
}
