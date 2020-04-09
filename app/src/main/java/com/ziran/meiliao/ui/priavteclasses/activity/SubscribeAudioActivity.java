package com.ziran.meiliao.ui.priavteclasses.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.app.WpyxConfig;
import com.ziran.meiliao.common.base.BaseFragmentAdapter;
import com.ziran.meiliao.common.commonutils.ArrayUtils;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.commonutils.TimeUtil;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.entry.MusicEntry;
import com.ziran.meiliao.ui.base.ShareActivity;
import com.ziran.meiliao.ui.bean.SubscribeAudioDataBean;
import com.ziran.meiliao.ui.priavteclasses.bean.ZhuanLanData;
import com.ziran.meiliao.ui.priavteclasses.fragment.EditCommentFragment;
import com.ziran.meiliao.ui.priavteclasses.fragment.SJKSubscribeAudioProfitFragment;
import com.ziran.meiliao.ui.priavteclasses.fragment.SJKSubscribePPTFragment;
import com.ziran.meiliao.ui.priavteclasses.model.BuyCallBack;
import com.ziran.meiliao.utils.CoordinatorLayoutContentViewUtil;
import com.ziran.meiliao.utils.MusicPanelFloatManager;
import com.ziran.meiliao.widget.CustomMusicPanelNewView;
import com.ziran.meiliao.widget.MyCoordinatorLayout;
import com.ziran.meiliao.widget.MyProgressView;
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

public class SubscribeAudioActivity extends ShareActivity implements View.OnClickListener {


    @Bind(R.id.ntb)
    NormalTitleBar ntb;
    @Bind(R.id.customMusicPanelView)
    CustomMusicPanelNewView mCustomMusicPanelNewView;
    @Bind(R.id.myCoordinatorLayout)
    MyCoordinatorLayout mMyCoordinatorLayout;
    View ivSubscribeAudioPanel;
    ImageView ivSubscribeAudioPic;
    MyProgressView mMyProgressView;
    PlayPauseView mPlayPauseView;
    @Bind(R.id.tv_subscribe_audio_dingyue)
    TextView tvSubscribeAudioSub;
    List<Fragment> fragments;

    private EditCommentFragment mEditCommentFragment;
    private SJKSubscribeAudioProfitFragment mSJKSubscribeAudioProfitFragment;
    private String subscriptionId="-1";
    private MusicEntry musicBean;


    public static void startAction(Context context, String subscriptionId, String targetId) {
        Intent intent = new Intent(context, SubscribeAudioActivity.class);
        intent.putExtra(AppConstant.RXTag.AUDIO_ID, targetId);
        intent.putExtra(AppConstant.ExtraKey.FROM_ID, subscriptionId);
        context.startActivity(intent);
    }
    public static void startAction1(Context context, String subscriptionId, MusicEntry musicEntry) {
        Intent intent = new Intent(context, SubscribeAudioActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("RecordListBean",musicEntry);
        bundle.putString(AppConstant.ExtraKey.FROM_ID, subscriptionId);
        intent.putExtras(bundle);
        context.startActivity(intent);


    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_subscribe_audio1;
    }

    @Override
    public void initPresenter() {

    }

    boolean editCommentFragmentShowOrHide = false;
    SubscribeAudioDataBean.DataBean bean;
    String musicUrl;
    boolean isCurrentMusic;

    //    ProgressUtil mProgressUtil;
    @Override
    public void initView() {
        subscriptionId = getIntent().getStringExtra(AppConstant.ExtraKey.FROM_ID);
         musicBean = getBundle().getParcelable("RecordListBean");
        ntb.setOnRightImagListener(this);
        ntb.setOnRightImag2Listener(this);
        ntb.setOnRightImag3Listener(this);
        ivSubscribeAudioPanel = LayoutInflater.from(this).inflate(R.layout.inflate_header_image, null);
        ivSubscribeAudioPanel.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams
                .WRAP_CONTENT));

        mMyCoordinatorLayout.setHeaderView(ivSubscribeAudioPanel);

        ivSubscribeAudioPic = ViewUtil.getView(ivSubscribeAudioPanel, R.id.iv_ban_pic);
        mMyProgressView = ViewUtil.getView(ivSubscribeAudioPanel, R.id.myProgressView);
        mPlayPauseView = ViewUtil.getView(ivSubscribeAudioPanel, R.id.playPauseView);
        mMyProgressView.setOnSeekStopListener(new MyProgressView.OnSeekStopListener() {
            @Override
            public void onSeekStop(MyProgressView myProgressView, int progress) {
                LogUtils.logd("progress:" + progress);
                MyAPP.mServiceManager.seekTo(progress * 1000);

                LogUtils.logd("position:" + MyAPP.mServiceManager.position());
            }
        });
        mRxManager.on(AppConstant.RXTag.PLAY_STATE, new Action1<String>() {
            @Override
            public void call(String s) {
                if (EmptyUtils.isNotEmpty(musicUrl) && musicUrl.equals(s)) {
                    mPlayPauseView.toggle(MyAPP.mServiceManager.isPlaying());
                }
            }
        });


        mPlayPauseView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMyProgressView.setEnabled(true);
                if(subscriptionId.equals("-1")){
                    MyAPP.mServiceManager.playUrl(musicBean.getFilePathVideo());
                }else{

                    MyAPP.mServiceManager.playUrl(musicUrl);
                }
                mSJKSubscribeAudioProfitFragment.play();
                mPlayPauseView.toggle(MyAPP.mServiceManager.isPlaying());
                isCurrentMusic = MyAPP.mServiceManager.checkUrl(musicUrl);
                MusicPanelFloatManager.getInstance().registerProgressListener(mMyProgressView, musicUrl);
                updateState();
            }
        });
        CoordinatorLayoutContentViewUtil coordinatorLayoutContentViewUtil = new CoordinatorLayoutContentViewUtil();
        coordinatorLayoutContentViewUtil.init(this);

        String[] tabs = ArrayUtils.getArray(this, R.array.subscribe_tag);
        if (EmptyUtils.isEmpty(fragments)) {
            fragments = new ArrayList<>();
            mSJKSubscribeAudioProfitFragment = new SJKSubscribeAudioProfitFragment();
            fragments.add(mSJKSubscribeAudioProfitFragment);
            SJKSubscribePPTFragment SJKSubscribePPTFragment = new SJKSubscribePPTFragment();
            SJKSubscribePPTFragment.setBuyCallBack(new BuyCallBack() {
                @Override
                public boolean getIsBuy() {
                    return isBuy;
                }
            });
            fragments.add(SJKSubscribePPTFragment);

            coordinatorLayoutContentViewUtil.setAdapter(new BaseFragmentAdapter(getSupportFragmentManager(), fragments), tabs);
            coordinatorLayoutContentViewUtil.bindTarget(mMyCoordinatorLayout);

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
                        setCollect((Boolean) msg.obj);
                        break;
                    case 1:
                        setLike((Boolean) msg.obj);
                        break;
                    case 2:
                        WpyxConfig.getZhuanLanData().setBuy(true);
                        setBuy((Boolean) msg.obj);
                        break;
                    case 10:
                        bean = (SubscribeAudioDataBean.DataBean) msg.obj;
                        musicUrl = bean.getAESUrl();
                        if (MyAPP.mServiceManager.checkUrl(musicUrl) && MyAPP.mServiceManager.isPlaying()) {
                            mPlayPauseView.toggle(MyAPP.mServiceManager.isPlaying());
                            mMyProgressView.setEnabled(true);
                        } else {
                            mMyProgressView.setEnabled(false);
                        }
                        int max = TimeUtil.getMax(bean.getDuration());
                        mMyProgressView.setMax(max);
                        MusicPanelFloatManager.getInstance().setMax(max);
                        MusicPanelFloatManager.getInstance().registerProgressListener(mMyProgressView, musicUrl);
                        setCollect(bean.isIsCollect());
                        setLike(bean.isIsLike());
                        loadImg(bean.getPic());

                        ZhuanLanData zhuanLanData = WpyxConfig.getZhuanLanData();
                        if(zhuanLanData!=null){

                            tvSubscribeAudioSub.setText(zhuanLanData.getZhuanLanNeedCoin());
                        }
                        buyState(WpyxConfig.checkBuy(subscriptionId));
//                        isCurrentMusic = MyAPP.mServiceManager.checkUrl(musicUrl);
                        if (EmptyUtils.isNotEmpty(musicUrl) && !MyAPP.mServiceManager.checkUrl(musicUrl)) {

                            MusicPanelFloatManager.getInstance().bindView(mCustomMusicPanelNewView);
                        }
                        break;
                }
            }
        });

    }

    private void updateState() {
        if (MyAPP.mServiceManager.isPlaying()) {
            MusicPanelFloatManager.getInstance().startPlay();
        } else {
            MusicPanelFloatManager.getInstance().onPause();
        }
    }

    private boolean isBuy;

    private void buyState(boolean isBuy) {
        this.isBuy = isBuy;
        if (isBuy) {
            tvSubscribeAudioSub.setVisibility(View.GONE);
        } else {
            tvSubscribeAudioSub.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onBackPressed() {
        if (mEditCommentFragment != null && editCommentFragmentShowOrHide) {
            mEditCommentFragment.hide();
        } else {
            super.onBackPressed();
        }
    }

    @OnClick({R.id.tv_subscribe_audio_dingyue})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_subscribe_audio_dingyue:
                mSJKSubscribeAudioProfitFragment.doWhat(SJKSubscribeAudioProfitFragment.WHAT_BUY);
                break;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (EmptyUtils.isNotEmpty(musicUrl) && !MyAPP.mServiceManager.checkUrl(musicUrl)) {
            MusicPanelFloatManager.getInstance().bindView(mCustomMusicPanelNewView);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (EmptyUtils.isNotEmpty(musicUrl) && !MyAPP.mServiceManager.checkUrl(musicUrl)) {
            MusicPanelFloatManager.getInstance().unBindView(mCustomMusicPanelNewView);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MusicPanelFloatManager.getInstance().unRegisterProgressListener(mMyProgressView);
    }

    public void setCollect(boolean isCollect) {
        ntb.getIvRight2().setSelected(isCollect);
    }

    public void setLike(boolean isLike) {
        ntb.getIvRight().setSelected(isLike);
    }

    public void loadImg(String imgUrl) {
        ImageLoaderUtils.displayTager(this, ivSubscribeAudioPic, imgUrl, R.mipmap.ic_loading_square_big);
    }

    @Override
    public void onStart(SHARE_MEDIA share_media) {

    }

    public void setBuy(boolean isBuy) {
        buyState(isBuy);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_right_img1:
                mSJKSubscribeAudioProfitFragment.doWhat(SJKSubscribeAudioProfitFragment.WHAT_LIKES);
                break;
            case R.id.id_right_img2:
                mSJKSubscribeAudioProfitFragment.doWhat(SJKSubscribeAudioProfitFragment.WHAT_COLLECT);
                break;
            case R.id.id_right_img3:
                mSJKSubscribeAudioProfitFragment.doWhat(SJKSubscribeAudioProfitFragment.WHAT_SHARE);
                break;
        }
    }
}
