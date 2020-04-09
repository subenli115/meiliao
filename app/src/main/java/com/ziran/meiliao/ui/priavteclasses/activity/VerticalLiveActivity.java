package com.ziran.meiliao.ui.priavteclasses.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.common.baserx.RxManager;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.entry.VideoSectionEntry;
import com.ziran.meiliao.envet.BuyCourseCallBack;
import com.ziran.meiliao.ui.bean.SJKLiveDetailProfileBean;
import com.ziran.meiliao.ui.priavteclasses.fragment.SJKFullLiveMoreFragment;
import com.ziran.meiliao.ui.priavteclasses.fragment.SJKVerticalHistoryLiveFragment;
import com.ziran.meiliao.ui.priavteclasses.fragment.SJKVerticalLiveBaseFragment;
import com.ziran.meiliao.ui.priavteclasses.fragment.SJKVerticalLiveFragment;
import com.ziran.meiliao.ui.priavteclasses.model.ItemPlayCallBack;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.PayUtil;
import com.ziran.meiliao.widget.pupop.JoinVipPopupWindow;
import com.ziran.meiliao.widget.pupop.PopupWindowUtil;
import com.ziran.meiliao.widget.pupop.SimpleSharePopupWindow;
import com.ziran.meiliao.widget.pupop.UseVideoCouponPopupWindow;
import com.ziran.meiliao.widget.pupop.VideoCouponTipsPopupWindow;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 减压馆 -音频详情界面
 * Created by Administrator on 2017/1/14.
 */

public class VerticalLiveActivity extends AppCompatActivity implements BuyCourseCallBack,ItemPlayCallBack {


    @Bind(R.id.frameLayout_more)
    protected View frameMore;
    protected String courseId;
    private SimpleSharePopupWindow mSimpleSharePopupwindow;
    private SJKVerticalLiveBaseFragment mSJKVerticalLiveFragment;
    private SJKFullLiveMoreFragment mSJKFullLiveMoreFragment;

    public static void startAction(Context context, String courseId, int liveStreaming) {
        if (CheckUtil.check(context)) {
            Intent intent = new Intent(context, VerticalLiveActivity.class);
            intent.putExtra(AppConstant.SPKey.COURSE_ID, courseId);
            intent.putExtra(AppConstant.ExtraKey.LIVE_STREAMING, liveStreaming);
            context.startActivity(intent);
        }
    }

    public static void startAction(String courseId) {
        Activity activity = AppManager.getAppManager().currentActivity();
        startAction(activity, courseId, 1);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initView();
    }

    public int getLayoutId() {
        return R.layout.activity_sjk_vertical_live;
    }


    public int getIntentExtra(Intent intent, String key, int defValue) {
        if (intent == null) {
            return defValue;
        }
        return getIntent().getIntExtra(key, defValue);
    }

    public String getIntentExtra(Intent intent, String key) {
        if (intent == null) {
            return "1";
        }
        return getIntent().getStringExtra(key);
    }


    public void initFragment(Fragment fragment) {
        initFragment(fragment, new Bundle());
    }

    public void initFragment(Fragment fragment, Bundle arguments) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        fragment.setArguments(arguments);
        ft.replace(R.id.frameLayout, fragment, fragment.getClass().getSimpleName());
        ft.commit();
    }

    public void initView() {

        if (getIntentExtra(getIntent(), AppConstant.ExtraKey.LIVE_STREAMING, 1) == 1) {
            mSJKVerticalLiveFragment = new SJKVerticalLiveFragment();
        } else {
            mSJKVerticalLiveFragment = new SJKVerticalHistoryLiveFragment();
        }
        courseId = getIntentExtra(getIntent(), AppConstant.SPKey.COURSE_ID);
        initFragment(mSJKVerticalLiveFragment);
    }

    private RxManager mRxManager = new RxManager();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRxManager.clear();
        ButterKnife.unbind(this);
    }

    @Override
    public void onBackPressed() {
        if (mSJKVerticalLiveFragment != null && !mSJKVerticalLiveFragment.onBackAction()) {
            if (isShowMore) {
                hideMore();
            } else {
                super.onBackPressed();
            }
        }
    }

    public void onShare(SJKLiveDetailProfileBean.DataBean dataBean) {
        if (dataBean == null ) return;
        if (mSimpleSharePopupwindow ==null){
            mSimpleSharePopupwindow = new SimpleSharePopupWindow(this);
            mSimpleSharePopupwindow.setShareTitle(dataBean.getShareTitle());
            mSimpleSharePopupwindow.setShareDescript(dataBean.getShareDescript());
            mSimpleSharePopupwindow.setShareUrlAddOne(dataBean.getShareUrl());
            if (EmptyUtils.isNotEmpty(dataBean.getSharePic())) {
                mSimpleSharePopupwindow.setSharePic(dataBean.getSharePic());
            }
        }
        PopupWindowUtil.show(this, mSimpleSharePopupwindow);

    }

    public void buyCourse(SJKLiveDetailProfileBean.DataBean dataBean) {
        if (dataBean == null) return;

        if (dataBean.isHasTick()) {
            UseVideoCouponPopupWindow useVideoCouponPopupwindow = new UseVideoCouponPopupWindow(VerticalLiveActivity.this);
            useVideoCouponPopupwindow.setData(dataBean.getUserTick());
            PopupWindowUtil.show(VerticalLiveActivity.this, useVideoCouponPopupwindow);
        } else {
            VideoCouponTipsPopupWindow videoCouponTipsPopupwindow = new VideoCouponTipsPopupWindow(VerticalLiveActivity.this);
            SJKLiveDetailProfileBean.DataBean mDataBean = getDataBean();
            videoCouponTipsPopupwindow.setCourseId(mDataBean.getCourseId());
            videoCouponTipsPopupwindow.setParams(mDataBean.getUserCoin(), mDataBean.getNeedCoin(), mDataBean.getTitle(), "还没改字段", "观看券",
                    mDataBean.getPicture(),mDataBean.getLevelDetail(),mDataBean.getMemberPrice());
            PopupWindowUtil.show(VerticalLiveActivity.this, videoCouponTipsPopupwindow);
        }
    }


    private PayUtil.OnPayCallBack mOnPayCallBack = new PayUtil.OnPayCallBack() {
        @Override
        public void onPaySuccess(int platform) {
            mSJKVerticalLiveFragment.onPayResult(null);
        }

        @Override
        public void onPayFailed() {

        }
    };

    public void buyVip() {
        JoinVipPopupWindow popupwindow = new JoinVipPopupWindow(this);
        popupwindow.setOnPayCallBack(mOnPayCallBack);
        PopupWindowUtil.show(this, popupwindow);
    }

    private boolean isInitMore;

    public void showMore() {
        if (isShowMore) return;
        isShowMore = true;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.in_bottomtotop, R.anim.out_toptobottom);
        if (!isInitMore) {
            mSJKFullLiveMoreFragment = new SJKFullLiveMoreFragment();
            ft.replace(R.id.frameLayout_more, mSJKFullLiveMoreFragment, "SJKFullLiveMoreFragment");
            isInitMore = true;
        } else {
            ft.show(mSJKFullLiveMoreFragment);
        }
        frameMore.setVisibility(View.VISIBLE);
        mSJKVerticalLiveFragment.setNeedMeasure(false);
        ft.commit();

    }

    private boolean isShowMore;

    public SJKLiveDetailProfileBean.DataBean getDataBean() {
        if (mSJKVerticalLiveFragment != null) {
            return mSJKVerticalLiveFragment.getDataBean();
        }
        return null;
    }

    public boolean hideMore() {
        if (mSJKFullLiveMoreFragment == null) return false;
        if (!isShowMore) return false;
        isShowMore = false;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.in_bottomtotop, R.anim.out_toptobottom);
        ft.hide(mSJKFullLiveMoreFragment);
        ft.commit();
        mSJKVerticalLiveFragment.setNeedMeasure(true);
        return true;
    }

    @Override
    public boolean hasBuy() {
        return getDataBean().isHasBuy();
    }


    public boolean isShowMore() {
        return isShowMore;
    }

    @Override
    public void playUrl(VideoSectionEntry videoSectionEntry) {
        mSJKVerticalLiveFragment.playUrl(videoSectionEntry);
    }

    @Override
    public void playShiKan(VideoSectionEntry videoSectionEntry) {
        mSJKVerticalLiveFragment.playShiKan(videoSectionEntry);
    }

    @Override
    public int isLiveStreaming() {
        return mSJKVerticalLiveFragment.isLiveStreaming();
    }

    @Override
    public boolean isPlaying() {
        return false;
    }
}
