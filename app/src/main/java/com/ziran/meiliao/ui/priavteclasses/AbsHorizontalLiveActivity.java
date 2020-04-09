package com.ziran.meiliao.ui.priavteclasses;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.pili.pldroid.player.widget.PLVideoTextureView;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseFragmentAdapter;
import com.ziran.meiliao.common.commonutils.KeyBordUtil;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.entry.VideoSectionEntry;
import com.ziran.meiliao.envet.CommonMediaControllerListener;
import com.ziran.meiliao.envet.LiveCallBack;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.ShareActivity;
import com.ziran.meiliao.ui.bean.GiftBean;
import com.ziran.meiliao.ui.bean.GiveGiftResultBean;
import com.ziran.meiliao.ui.bean.SJKLiveDetailProfileBean;
import com.ziran.meiliao.ui.priavteclasses.contract.SJKFullLiveContract;
import com.ziran.meiliao.ui.priavteclasses.fragment.SJKLiveDetailCommentFragment;
import com.ziran.meiliao.ui.priavteclasses.model.ItemPlayCallBack;
import com.ziran.meiliao.ui.priavteclasses.presenter.SJKCoursePresenter;
import com.ziran.meiliao.ui.priavteclasses.util.VideoPlayerHelper1;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.MusicPanelFloatManager;
import com.ziran.meiliao.utils.PayUtil;
import com.ziran.meiliao.widget.ARewardV2View;
import com.ziran.meiliao.widget.IMyMediaController;
import com.ziran.meiliao.widget.pupop.JoinVipPopupWindow;
import com.ziran.meiliao.widget.pupop.PayResultPopupWindow;
import com.ziran.meiliao.widget.pupop.PopupWindowUtil;
import com.ziran.meiliao.widget.pupop.SharePopupWindow;
import com.ziran.meiliao.widget.pupop.UseVideoCouponPopupWindow;
import com.ziran.meiliao.widget.pupop.VideoCouponTipsPopupWindow;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import rx.functions.Action1;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/9/11 18:06
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/9/11$
 * @updateDes ${TODO}
 */

public abstract class AbsHorizontalLiveActivity<T extends SJKCoursePresenter, E extends CommonModel> extends ShareActivity<T, E>
        implements SJKFullLiveContract.View, ItemPlayCallBack, CommonMediaControllerListener, LiveCallBack {
    @Bind(R.id.tab_layout)
    protected TabLayout tabLayout;
    @Bind(R.id.viewpager)
    protected ViewPager viewPager;
    @Bind(R.id.aRewardView)
    protected ARewardV2View aRewardView;
    @Bind(R.id.include_video)
    protected RelativeLayout videoContoller;
    @Bind(R.id.contentContoller)
    protected View contentContoller;
    @Bind(R.id.VideoView)
    protected PLVideoTextureView mVideoView;
    @Bind(R.id.LoadingView)
    protected View mLoadingView;
    @Bind(R.id.coverView)
    protected ImageView coverView;
    protected String[] tabs;
    protected List<Fragment> fragments;
    protected int defVideoHeight;
    private SharePopupWindow mSharePopupWindow;
    protected String mVideoPath = null;
    protected String courseId;
    protected SJKLiveDetailCommentFragment mSJKLiveDetailCommentFragment;
    protected VideoPlayerHelper1 mVideoPlayerHelper;
    public SJKLiveDetailProfileBean.DataBean mDataBean;
    protected boolean isInitView = false;
    protected VideoSectionEntry currentDirbean;
    protected IMyMediaController mIMyMediaController;
    private boolean isFreeShare;


    @Override
    public void initPresenter() {
        if (mPresenter != null && mModel != null) {
            mPresenter.setVM(this, mModel);
        }
    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    public void initView() {
        if (!isInitView) {
            defVideoHeight = getResources().getDimensionPixelSize(R.dimen.sjk_live_unfull_height);
            courseId = getIntentExtra(getIntent(), AppConstant.SPKey.COURSE_ID);
            if (EmptyUtils.isEmpty(courseId)) {
                courseId = "32";
            }
            initViewPager();
            initRx();
            MusicPanelFloatManager.getInstance().res(true);
            aRewardView.setOnClick(onArewardClickListener);
            initVideoPlayerHelper();
            isInitView = false;
        }
    }

    protected void initRx() {

        mRxManager.on(AppConstant.RXTag.BALANCE, new Action1<String>() {
            @Override
            public void call(String balance) {
                mDataBean.setUserCoin(Integer.parseInt(balance));
            }
        });
        mRxManager.on(AppConstant.RXTag.REQ_BUY_COURSE, new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                mPresenter.buyCourse(ApiKey.COURSE_BUY, MapUtils.getCourseMap(courseId));
            }
        });

        mRxManager.on(AppConstant.RXTag.USER_TICK, new Action1<SJKLiveDetailProfileBean.DataBean.UserTickBean>() {
            @Override
            public void call(SJKLiveDetailProfileBean.DataBean.UserTickBean userTickBean) {
                Map<String, String> courseMap = MapUtils.getCourseMap(courseId);
                courseMap.put("tickId", userTickBean.getTickId());
                mPresenter.usedTick(ApiKey.COURSE_TICKET_USETICK, courseMap);
            }
        });
    }

    protected abstract void initVideoPlayerHelper();

    private View.OnClickListener onArewardClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ll_horizontal_use_video_coupon_vip:
                    JoinVipPopupWindow popupwindow = new JoinVipPopupWindow(AbsHorizontalLiveActivity.this);
                    popupwindow.setOnPayCallBack(mOnPayCallBack);
                    PopupWindowUtil.show(popupwindow);
                    break;
                case R.id.ll_horizontal_use_video_coupon_buy:
                    if (mDataBean.isHasTick()) {
                        UseVideoCouponPopupWindow useVideoCouponPopupwindow = new UseVideoCouponPopupWindow(AbsHorizontalLiveActivity.this);
                        useVideoCouponPopupwindow.setData(mDataBean.getUserTick());
                        PopupWindowUtil.show(AbsHorizontalLiveActivity.this, useVideoCouponPopupwindow);
                    } else {
                        VideoCouponTipsPopupWindow videoCouponTipsPopupwindow = new VideoCouponTipsPopupWindow(AbsHorizontalLiveActivity
                                .this);
                        isFreeShare = true;
                        videoCouponTipsPopupwindow.setCourseId(mDataBean.getCourseId());
                        videoCouponTipsPopupwindow.setParams(mDataBean.getUserCoin(), mDataBean.getNeedCoin(), mDataBean.getTitle(),
                                "还没改字段", "观看券", mDataBean.getPicture(),mDataBean.getLevelDetail(),mDataBean.getMemberPrice());
                        PopupWindowUtil.show(AbsHorizontalLiveActivity.this, videoCouponTipsPopupwindow);
                    }
                    break;
            }
        }
    };

    private PayUtil.OnPayCallBack mOnPayCallBack = new PayUtil.OnPayCallBack() {
        @Override
        public void onPaySuccess(int platform) {
            onPayResult(null);
        }

        @Override
        public void onPayFailed() {

        }
    };

    private void initViewPager() {
        fragments = new ArrayList<>();
        tabs = getResources().getStringArray(getStringArrayId());
        initFragments(fragments);
        viewPager.setAdapter(new BaseFragmentAdapter(getSupportFragmentManager(), fragments));
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(getOffscreenPageLimit());
        ViewUtil.changeTabText(tabLayout, tabs);

    }

    protected abstract int getOffscreenPageLimit();

    protected abstract void initFragments(List<Fragment> fragments);

    protected abstract int getStringArrayId();

    @Override
    public void postShiKan() {
        mPresenter.postShiKan(ApiKey.COURSE_SHIKAN, MapUtils.getCourseMap(courseId));
    }

    @Override
    public void changeScreen(boolean isFull) {
        mVideoPlayerHelper.changeFull(isFull, AbsHorizontalLiveActivity.this, contentContoller, videoContoller);
    }


    @Override
    public boolean isBuyCourse() {
        return EmptyUtils.isNotEmpty(mDataBean) && mDataBean.isHasBuy();
    }

    @Override
    public void historyShiKan() {

    }

    @Override
    public void onShare() {
        SharePopupWindow.showPopup(this, mSharePopupWindow, mDataBean);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mVideoPlayerHelper.onPause();
        KeyBordUtil.hideSoftKeyboard(contentContoller);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVideoPlayerHelper.onResume();

        isFreeShare = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVideoPlayerHelper.onDestroy();
    }

    public void onBackPressed() {
        onBack();
    }

    @Override
    public void onBack() {
        try {
            boolean flag = true;
            if (mIMyMediaController != null && mIMyMediaController.isFullScreen()) {
                mIMyMediaController.changeScreen();
                changeScreen(false);
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
    public void playShiKan(VideoSectionEntry videoSectionEntry) {

    }


    @Override
    public void showCourseData(SJKLiveDetailProfileBean.DataBean data) {

    }

    @Override
    public void setCollect(Result result) {

    }

    @Override
    public void setLike(Result result) {

    }

    @Override
    public void setUserCount(String userCount) {

    }

    @Override
    public void onPayResult(String url) {
        PayResultPopupWindow payResultPopupwindow = new PayResultPopupWindow(this);
        payResultPopupwindow.setResult(true);
        PopupWindowUtil.show(this, payResultPopupwindow);
        mDataBean.setHasBuy(true);
        aRewardView.setVisibility(View.GONE);
        mIMyMediaController.showShikan(false);
    }

    @Override
    public void showListGift(List<GiftBean.DataBean> beanList) {

    }

    @Override
    public void giveGiftResult(GiveGiftResultBean.DataBean result) {

    }

    @Override
    public void showBuyTip() {
        mIMyMediaController.showShikan(true);
    }

    @Override
    public void onStart(SHARE_MEDIA share_media) {
        if (!isFreeShare) {
//            startProgressDialog("加载中");
        }
    }

    @Override
    public void onResult(SHARE_MEDIA share_media) {
        super.onResult(share_media);
        if (mSharePopupWindow != null) {
            mSharePopupWindow.dismiss();
        }
    }
    @Override
    public boolean isPlaying() {
        return mVideoView.isPlaying();
    }
}
