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

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.base.BaseFragmentAdapter;
import com.ziran.meiliao.common.commonutils.ArrayUtils;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.bean.SpecColumnData;
import com.ziran.meiliao.ui.bean.ZhuanLanBigInBean;
import com.ziran.meiliao.ui.priavteclasses.util.ZhuanLanMusicDataConfig;
import com.ziran.meiliao.widget.ARewardV2View;
import com.ziran.meiliao.widget.CustomMusicPanelNewView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import rx.functions.Action1;


public class ZhuanLanBigInAudioActivity extends BaseActivity {


    //    @Bind(R.id.tv_sjk_zhuanlan_follow)
//    TextView tvSjkZhuanlanFollow;
    @Bind(R.id.ntb)
    NormalTitleBar ntb;
    @Bind(R.id.aRewardView)
    ARewardV2View mARewardV2View;
    @Bind(R.id.iv_zhuanlan_big_in_audio_ban)
    ImageView ivAudioBan;
    @Bind(R.id.tab_layout)
    TabLayout tabLayout;
    @Bind(R.id.viewpager)
    ViewPager viewPager;
    @Bind(R.id.customMusicPanelView)
    CustomMusicPanelNewView mCustomMusicPanelView;

    private String subscriptionId;
    private List<Fragment> fragments;

    @Override
    public int getLayoutId() {
        return R.layout.activity_sjk_zhuanlan_big_in_audio;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    public static void startAction(Context context, int specColumnId) {
        Intent intent = new Intent(context, ZhuanLanBigInAudioActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(AppConstant.ExtraKey.SUBSCRIPTION_ID, String.valueOf(specColumnId));
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static void startAction(Context context, Object obj) {
        SpecColumnData specColumnData = EmptyUtils.parseObject(obj);
        startAction(context, specColumnData.getId());
    }

    private SJKZhuanLanBigInFragment mSJKZhuanLanBigInFragment;

    @Override
    public void initView() {
        try {
            subscriptionId = getIntent().getExtras().getString(AppConstant.ExtraKey.SUBSCRIPTION_ID);
        } catch (Exception e) {
            subscriptionId = "";
            e.printStackTrace();
        }
        mSJKZhuanLanBigInFragment = new SJKZhuanLanBigInFragment();
        mCustomMusicPanelView.setCloseVisibility(View.INVISIBLE);
        mCustomMusicPanelView.setCloseToStop(true);
        String[] tabs = ArrayUtils.getArray(this, R.array.subscribe_tag);
        if (EmptyUtils.isEmpty(fragments)) {
            fragments = new ArrayList<>();
            fragments.add(mSJKZhuanLanBigInFragment);
            SJKZhuanLanBigInPPTFragment pptFragment = new SJKZhuanLanBigInPPTFragment();
            pptFragment.setAudioFragment(mSJKZhuanLanBigInFragment, "2");
            fragments.add(pptFragment);
            viewPager.setAdapter(new BaseFragmentAdapter(getSupportFragmentManager(), fragments));
            tabLayout.setupWithViewPager(viewPager);
            ViewUtil.changeTabText(tabLayout, tabs);
        }
        mARewardV2View.setOnClick(mSJKZhuanLanBigInFragment.getArewardClick());
        mRxManager.on(AppConstant.RXTag.BIG_IN_TAG, new Action1<Message>() {
            @Override
            public void call(Message msg) {
                switch (msg.what) {
                    case 0:
                        ntb.getIvRight().setSelected((boolean) msg.obj);
//                        tvSjkZhuanlanFollow.setText((boolean) msg.obj ? "关注" : "取消关注");
                        break;
                    case 1:
                        setTitles((ZhuanLanBigInBean.DataBean) msg.obj);
                        break;
                    case 2:
                        setCustomMusicPanelView((ZhuanLanBigInBean.DataBean.DirBean) msg.obj, true);
                        break;
                    case 3:
                        mARewardV2View.setVisibility(View.GONE);
                        break;
                }
            }
        });
        ntb.setOnRightImagListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSJKZhuanLanBigInFragment.gzZhuanLan();
            }
        });
        ntb.setOnRightImag2Listener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSJKZhuanLanBigInFragment.share();
            }
        });
    }
//
//    @OnClick({R.id.iv_sjk_zhuanlan_back, R.id.iv_sjk_zhuanlan_share, R.id.tv_sjk_zhuanlan_follow})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.iv_sjk_zhuanlan_back:
//                finish();
//                break;
//            case R.id.iv_sjk_zhuanlan_share:
//
//                break;
//            case R.id.tv_sjk_zhuanlan_follow:
//
//                break;
//        }
//    }

    public void setTitles(ZhuanLanBigInBean.DataBean mDataBean) {
        if (EmptyUtils.isNotEmpty(mDataBean)) {
            ImageLoaderUtils.displayTager(this, ivAudioBan, mDataBean.getPic(), R.mipmap.ic_loading_square_big);
            mARewardV2View.setNeedCoin("课程", mDataBean.getNeedCoin());
            mARewardV2View.setVisibility(mDataBean.isIsBuy() ? View.GONE : View.VISIBLE);
            ntb.getIvRight().setSelected(mDataBean.isIsGz());
//            tvSjkZhuanlanFollow.setText(mDataBean.isIsGz() ? "关注" : "取消关注");
            restartPlay(mDataBean);
        }
    }

    private void restartPlay(ZhuanLanBigInBean.DataBean mDataBean) {
        String targetId = ZhuanLanMusicDataConfig.getTargetId();
        if (MyAPP.mServiceManager.isPlaying() && EmptyUtils.isNotEmpty(targetId)) {
            List<ZhuanLanBigInBean.DataBean.DirBean> dir = mDataBean.getDir();
            for (int i = 0; i < dir.size(); i++) {
//                LogUtils.logd("targetId"+targetId + "   restartPlay"+dir.get(i).getTargetId());
                if (targetId.equals(dir.get(i).getTargetId())) {
                    setCustomMusicPanelView(dir.get(i), false);
                    return;
                }
            }
        }
    }

    public void setCustomMusicPanelView(ZhuanLanBigInBean.DataBean.DirBean dirBean, boolean needPlay) {
        if (!mCustomMusicPanelView.isShown()) {
            mCustomMusicPanelView.setVisibility(View.VISIBLE);
        }
        mCustomMusicPanelView.setIvMusicPanelCover(dirBean.getRoundPic());
        mCustomMusicPanelView.setTvMusicPanelTitle(dirBean.getTitle());
        mCustomMusicPanelView.setTvMusicPanelDuration(dirBean.getDuration());
        mCustomMusicPanelView.setMusicUrl(dirBean.getAESUrl());
//        mCustomMusicPanelView.setMusicUrl("http://www.dgli.net/resource/music/albumMusic/9ircmik.mp3");
        if (needPlay) {
            mCustomMusicPanelView.onPlay(true);
        } else {
            mCustomMusicPanelView.restartUpdate();
        }

    }
}
