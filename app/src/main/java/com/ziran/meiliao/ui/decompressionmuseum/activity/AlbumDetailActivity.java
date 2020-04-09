package com.ziran.meiliao.ui.decompressionmuseum.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.citypicker.citylist.sortlistview.SortModel;
import com.citypicker.citylist.utils.CityDataDb;
import com.mob.moblink.MobLink;
import com.mob.moblink.Scene;
import com.mob.moblink.SceneRestorable;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.base.BaseFragmentAdapter;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.entry.LoginBean;
import com.ziran.meiliao.entry.MusicEntry;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.ShareActivity;
import com.ziran.meiliao.ui.bean.AlbumBean;
import com.ziran.meiliao.ui.bean.AlbumGainBean;
import com.ziran.meiliao.ui.bean.CheckPhoneBean;
import com.ziran.meiliao.ui.bean.StringDataBean;
import com.ziran.meiliao.ui.bean.TagCheckBean;
import com.ziran.meiliao.ui.decompressionmuseum.fragment.AlbumDetailFragment;
import com.ziran.meiliao.ui.decompressionmuseum.fragment.AlbumDetailListFragment;
import com.ziran.meiliao.ui.main.contract.LoginContract;
import com.ziran.meiliao.ui.main.model.LoginModel;
import com.ziran.meiliao.ui.main.presenter.LoginPresenter;
import com.ziran.meiliao.ui.settings.activity.RechargeActivity;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.CoordinatorLayoutContentViewUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.MusicPanelFloatManager;
import com.ziran.meiliao.utils.MyValueTempCache;
import com.ziran.meiliao.utils.StringUtils;
import com.ziran.meiliao.utils.WpyxDownloadUtil;
import com.ziran.meiliao.widget.ARewardV1View;
import com.ziran.meiliao.widget.BottomDownView;
import com.ziran.meiliao.widget.BottomMoreView;
import com.ziran.meiliao.widget.CustomMusicPanelNewView;
import com.ziran.meiliao.widget.MyCoordinatorLayout;
import com.ziran.meiliao.widget.SmsCodeView;
import com.ziran.meiliao.widget.pupop.BuyAlbumPopupWindow;
import com.ziran.meiliao.widget.pupop.PayResultPopupWindow;
import com.ziran.meiliao.widget.pupop.PopupWindowUtil;
import com.ziran.meiliao.widget.pupop.SetCountTimePopupWindow;
import com.ziran.meiliao.widget.pupop.SharePopupWindow;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import rx.functions.Action1;

/**
 * 减压馆 -音频详情界面
 * Created by Administrator on 2017/1/14.
 */

public class AlbumDetailActivity extends ShareActivity<CommonPresenter, CommonModel> implements BottomMoreView.OnMoreCallBack,
        CommonContract.ActionView<Result, StringDataBean> ,LoginContract.View, SceneRestorable {

    //头部背景图
    @Bind(R.id.myCoordinatorLayout)
    MyCoordinatorLayout mMyCoordinatorLayout;
    //    //头部背景图
//    ImageView headBg;
    //打赏控件
    @Bind(R.id.aRewardView)
    ARewardV1View aRewardView;
    //    //用户头像
//    ImageView ivUserHead;
//
//    //    //标题
//    TextView tvJygAudioTitle;
//    //vip标签
//    TextView tv_jyg_audio_vip;
//    TextView tvJygAudioAnchor;
//    TextView tvJygAudioListCount;
    @Bind(R.id.bottomMoreView)
    BottomMoreView bottomMoreView;
    //标题栏
    @Bind(R.id.normalTitleBar)
    NormalTitleBar normalTitleBar;

    @Bind(R.id.bottomDownView)
    BottomDownView bottomDownView;
    private SharePopupWindow mSharePopupWindow;

    private AlbumDetailListFragment mAlbumDetailListFragment;
    @Bind(R.id.musicPanelView)
    CustomMusicPanelNewView mMusicPanelView;

    private MusicEntry mCurrentMusicData;

    private AlbumBean albumBean;

    public static final int MODE_ALBUM = 1;
    public static final int MODE_MUSIC = 2;
    private int currentMode = MODE_ALBUM;
    private Dialog threeLogindialog;
    private SmsCodeView bindDialogSmsCodeView;
    private SortModel mCityItem;
    private LoginPresenter loginPresenter;
    private Map<String, String> codeMap;
    private AlbumDetailListFragment listFragment;
    private boolean isCurrent=false;

    public static void startAction(Activity context, String albumId, int flag) {
        if (CheckUtil.check(context)) {
            Intent intent = new Intent(context, AlbumDetailActivity.class);
            intent.putExtra(AppConstant.SPKey.ALBUM_ID, albumId);
            intent.putExtra(AppConstant.SPKey.ALBUM_FLAG, flag);
            context.startActivityForResult(intent, 100);
        }
    }

    public static void startAction(Context context, String albumId) {
        if (CheckUtil.check(context)) {
            Intent intent = new Intent(context, AlbumDetailActivity.class);
            intent.putExtra(AppConstant.SPKey.ALBUM_ID, albumId);
            context.startActivity(intent);
        }
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_jyg_album_detail;
    }

    @Override
    public void initPresenter() {
        if (mModel != null) {
            mPresenter.setVM(this, mModel);
        }
    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }


    @Override
    public void initView() {
         loginPresenter = new LoginPresenter();
        loginPresenter.setVM(this,new LoginModel());
        mCityItem = CityDataDb.getCountryZipCode(this);
                 listFragment = new AlbumDetailListFragment();
        CoordinatorLayoutContentViewUtil coordinatorLayoutContentViewUtil = new CoordinatorLayoutContentViewUtil();
        coordinatorLayoutContentViewUtil.init(this);


        normalTitleBar.setLeftImagSrc(R.drawable.back1);
        normalTitleBar.setRightImagSrc(R.drawable.selector_jyg_detail_more);
        normalTitleBar.setRightImag2Src(R.mipmap.player_timeset);
        normalTitleBar.setBackGroundColor(R.color.transparent);
        initListener();

        initViewPager(coordinatorLayoutContentViewUtil);

        mRxManager.on(AppConstant.RXTag.BALANCE, new Action1<String>() {
            @Override
            public void call(String balance) {
                albumBean.setUserCoin(Integer.parseInt(balance));
            }
        });
        mRxManager.on(AppConstant.RXTag.UPDATE_TITLE, new Action1<AlbumBean>() {
            @Override
            public void call(AlbumBean author) {
                albumBean = author;
                mPresenter.postAction(ApiKey.CHECK_PAY_ALBUM, MapUtils.getAlbumData(albumBean.getAlbumId(), -1), StringDataBean.class);
                MyAPP.mServiceManager.setAlbumName1(albumBean.getTitle());
                normalTitleBar.setTitleText(author.getTitle());
                tvJygAudioTitle.setText(author.getTitle());
                ImageLoaderUtils.display(AlbumDetailActivity.this, ivUserHead, author.getZf(), R.mipmap.ic_loading_rectangle);
                ImageLoaderUtils.display(AlbumDetailActivity.this, headBg, author.getBg());
                tvJygAudioAnchor.setText(author.getName());
                tvJygAudioListCount.setText(StringUtils.format(getString(R.string.wach_count), author.getListenCount()));
                tv_jyg_audio_vip.setText(author.getVip());
                tv_jyg_audio_vip.setVisibility(View.VISIBLE);
                if (!author.isBuy()) {
                    aRewardView.setVisibility(View.VISIBLE);
                    aRewardView.setParames(albumBean.getShareTimes(),"支持" ,albumBean.getFinishTimes()  ,(int) albumBean.getPrice());
                } else {
                    aRewardView.setVisibility(View.GONE);
                }
                tv_jyg_audio_vip.setVisibility(View.VISIBLE);

            }
        });
        mRxManager.on(AppConstant.RXTag.MUSIC_BUY_SUCESS, new Action1<Boolean>() {
            @Override
            public void call(Boolean scroll) {
                if(scroll){
                    mPresenter.postAction(ApiKey.CHECK_PAY_ALBUM, MapUtils.getAlbumData(albumBean.getAlbumId(), -1), StringDataBean.class);
                }
            }
        });

    }
    @Bind(R.id.iv_jyg_audio_head_bg)
    ImageView headBg;
    @Bind(R.id.iv_jyg_audio_userHead)
    ImageView ivUserHead;
    @Bind(R.id.tv_jyg_audio_title)
    TextView tvJygAudioTitle;
    @Bind(R.id.tv_jyg_audio_vip)
    TextView tv_jyg_audio_vip;
    @Bind(R.id.tv_jyg_audio_anchor)
    TextView tvJygAudioAnchor;
    @Bind(R.id.tv_jyg_audio_list_count)
    TextView tvJygAudioListCount;
    private void initHeadViews() {
        headBg = mMyCoordinatorLayout.getHeaderChildView(R.id.iv_jyg_audio_head_bg);
        ivUserHead = mMyCoordinatorLayout.getHeaderChildView(R.id.iv_jyg_audio_userHead);
        tvJygAudioTitle = mMyCoordinatorLayout.getHeaderChildView(R.id.tv_jyg_audio_title);
        tv_jyg_audio_vip = mMyCoordinatorLayout.getHeaderChildView(R.id.tv_jyg_audio_vip);
        tvJygAudioAnchor = mMyCoordinatorLayout.getHeaderChildView(R.id.tv_jyg_audio_anchor);
        tvJygAudioListCount = mMyCoordinatorLayout.getHeaderChildView(R.id.tv_jyg_audio_list_count);
    }

    private void initViewPager(CoordinatorLayoutContentViewUtil coordinatorLayoutContentViewUtil) {
        List<Fragment> fragments = new ArrayList<>();
        mAlbumDetailListFragment = new AlbumDetailListFragment();
        fragments.add(mAlbumDetailListFragment);
        fragments.add(new AlbumDetailFragment());

        coordinatorLayoutContentViewUtil.setAdapter(new BaseFragmentAdapter(getSupportFragmentManager(), fragments), R.array
                .jyg_audio_tabs);
        coordinatorLayoutContentViewUtil.bindTarget(mMyCoordinatorLayout);
    }

    private void initListener() {
        normalTitleBar.setOnRightImag2Listener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetCountTimePopupWindow setCountTimePopupWindow = new SetCountTimePopupWindow(AlbumDetailActivity.this);
                PopupWindowUtil.show(AlbumDetailActivity.this, setCountTimePopupWindow);
            }
        });
        normalTitleBar.setOnRightImagListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!CheckUtil.check(AlbumDetailActivity.this, normalTitleBar)) return;
                if (albumBean == null) return;
                if (!bottomMoreView.isShowOrHide()) {
                    setCurrentMode(MODE_ALBUM);
                    bottomMoreView.setCollectText(albumBean.isIsCollectAlbum());
                    bottomMoreView.setShowMore(true);
                }
            }
        });
        normalTitleBar.setTvLeftVisiable(true,true);

        bottomMoreView.setOnMoreListener(this);

        aRewardView.setOnClick(aRewardClickListener);
        aRewardView.setOnShareClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareAlbum();
            }
        });
        mMyCoordinatorLayout.openOffsetChanged(true,true);
    }

    @Override
    public void onResult(SHARE_MEDIA share_media) {
        super.onResult(share_media);
        if (mSharePopupWindow != null) {
            mSharePopupWindow.dismiss();
        }
    }

    @Override
    public void onDownload() {
        if (!CheckUtil.check(this, normalTitleBar)) return;
        if (isBuy()) {
            if (currentMode == MODE_ALBUM) {
                bottomDownView.setAdapter(mAlbumDetailListFragment.getData());
                bottomDownView.setAlbumId(albumBean.getAlbumId());
                bottomDownView.setShowDownload(true);
            } else if (EmptyUtils.isNotEmpty(mCurrentMusicData)) {
                WpyxDownloadUtil.get().downMusic(mContext,null, albumBean.getAlbumId(), mCurrentMusicData,false,false);
            }
        } else {

            ToastUitl.showShort(getString(R.string.notbuy_album));
        }
    }
    //设置当前模式(对专辑,对音乐)
    public void setCurrentMode(int mode) {
        this.currentMode = mode;
    }

    //判断是否购买该专辑
    public boolean isBuy() {
        boolean flag = albumBean != null && albumBean.isBuy();
        if (!flag && !aRewardView.isShown()) {
            aRewardView.setVisibility(View.VISIBLE);
        }
        return flag;
    }

    //进行收藏请求
    @Override
    public void onCollect() {
        if (!CheckUtil.check(this, normalTitleBar)) return;
        isBuy();
        if (currentMode == MODE_ALBUM) {
            mAlbumDetailListFragment.collectAlbum();
        } else {
            if (mCurrentMusicData != null) {
                mAlbumDetailListFragment.collectMusic(mCurrentMusicData);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        stopProgressDialog();
        MusicPanelFloatManager.getInstance().bindView(mMusicPanelView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MusicPanelFloatManager.getInstance().unBindView(mMusicPanelView);
    }

    @Override
    public void onShare() {
        try {
            isBuy();
            if (!CheckUtil.check(this, normalTitleBar)) return;
                AlbumBean mAuthor = MyValueTempCache.get().get(AppConstant.DOWN_ALBUM);
            SharePopupWindow.showPopup(this, mSharePopupWindow, mAuthor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void shareAlbum() {
        isShowDialog = false;
        OkHttpClientManager.postAsync(ApiKey.ALBUM_GAIN, MapUtils.getOnlyCan(AppConstant.SPKey.ALBUM_ID, albumBean.getAlbumId()),
                new NewRequestCallBack<AlbumGainBean>(AlbumGainBean.class) {
            @Override
            public void onSuccess(AlbumGainBean result) {
                Bundle bundle = new Bundle();
                bundle.putString(AppConstant.ExtraKey.ALBUM_TITLE, albumBean.getTitle());
                bundle.putString(AppConstant.SPKey.ALBUM_ID, albumBean.getAlbumId());
                bundle.putParcelable(AppConstant.ExtraKey.ALBUM_GAIN, result.getData());
                startActivity(ShareAlbumActivity.class, bundle);
            }
        });
    }
    @Override
    public void onBackPressed() {
        boolean flag = true;
        if (bottomMoreView.checkShow()) {
            flag = false;
        }
        if (bottomDownView.checkShow()) {
            flag = false;
        }
        if (flag) {
            super.onBackPressed();
        }
    }

    @Override
    public void onCancel() {
        isBuy();
    }

    private BuyAlbumPopupWindow buyAlbumPopupwindow;


    private void showPayV13() {
        if (buyAlbumPopupwindow == null) {
            buyAlbumPopupwindow = new BuyAlbumPopupWindow(this);
            buyAlbumPopupwindow.setTitle(StringUtils.format(getString(R.string.buy_album), albumBean.getTitle()));
            buyAlbumPopupwindow.setAmount(albumBean.getPrice());
            buyAlbumPopupwindow.setHead(albumBean.getPicture());
//            buyAlbumPopupwindow.setMemberPrice(albumBean.getLevelDetail());
            buyAlbumPopupwindow.setTvAmountMember( albumBean.getMemberPrice());
            buyAlbumPopupwindow.setStyle((albumBean.getUserCoin() - albumBean.getNeedCoin()) < 0);
            buyAlbumPopupwindow.setBuyAlbumClickListener(new BuyAlbumPopupWindow.BuyAlbumClickListener() {
                @Override
                public void buyAlbum(boolean needRecharge) {
                    if (needRecharge) {
                        RechargeActivity.startAction(AlbumDetailActivity.this, String.valueOf(albumBean.getUserCoin()));
                    } else {
                        mPresenter.postData(ApiKey.PURSE_BUY, MapUtils.getAlbumData(albumBean.getAlbumId(), -1), Result.class);
                    }
                }
            });
        }
        buyAlbumPopupwindow.setBalance(albumBean.getUserCoin());
        buyAlbumPopupwindow.show(this);
    }

    private void showPayResultDialog(boolean result) {
        PayResultPopupWindow payResultPopupwindow = new PayResultPopupWindow(AlbumDetailActivity.this);
        payResultPopupwindow.setResult(result);
        PopupWindowUtil.show(payResultPopupwindow);

    }


    //调用下载音乐弹窗
    public void doMusicMore(MusicEntry bean) {
        if (!bottomMoreView.isShowOrHide()) {
            setCurrentMode(MODE_MUSIC);
            this.mCurrentMusicData = bean;
            bottomMoreView.setCollectText(mCurrentMusicData.getIsCollectMusic());
            bottomMoreView.setShowMore(true);
        }
    }

    //设置是否收藏音乐
    public void setIsCollect() {
        if (mCurrentMusicData != null) {
            mCurrentMusicData.setIsCollectMusic(!mCurrentMusicData.getIsCollectMusic());
            MusicEntry.doCollect(mCurrentMusicData);
        }
    }

    public AlbumBean getAlbumBean() {
        return albumBean;
    }

    @Override
    public void onStart(SHARE_MEDIA share_media) {
        if (isShowDialog) {
            startProgressDialog("加载中");
        }
    }

    private View.OnClickListener aRewardClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_album_buy:
                    if (!CheckUtil.check(getApplicationContext(), normalTitleBar)) return;
                    //进行购买请求
                    if (albumBean == null) return;
                    //判断是否绑定手机
                     showPayV13();
                    break;
            }
        }
    };
    @Override
    public void returnData(Result result) {
        isCurrent=true;
        mPresenter.postAction(ApiKey.CHECK_PAY_ALBUM, MapUtils.getAlbumData(albumBean.getAlbumId(), -1), StringDataBean.class);
    }




    @Override
    public void returnAction(StringDataBean result) {
        if ("true".equals(result.getNornemData())) {
            albumBean.setBuy(true);
        aRewardView.setVisibility(View.GONE);
        if(isCurrent){
            mAlbumDetailListFragment.getAdapter().clear();
            mAlbumDetailListFragment.onRefresh();
        }
    }
    }
     public void    updateBuyStatus(){
        if(aRewardView.getVisibility()==View.VISIBLE){
            aRewardView.setVisibility(View.GONE);
        }

    }
    @Override
    public void showErrorTip(String msg) {
        showPayResultDialog(false);
    }

    @Override
    public void returnLoginData(LoginBean registerBean) {

    }

    @Override
    public void showCheckSaveData(TagCheckBean data) {

    }

    @Override
    public void returnBindPhoneData(LoginBean registerBean) {
        if (threeLogindialog != null) {
            threeLogindialog.dismiss();
        }
        ToastUitl.show("绑定成功",0);
    }

    @Override
    public void returnLoginCode(Result registerBean) {

    }

    @Override
    public void returnBindCode(StringDataBean registerBean) {

    }

    @Override
    public void returnPartyLogin(LoginBean registerBean) {

    }

    @Override
    public void showLoginCheck(CheckPhoneBean result) {

    }

    @Override
    public void onReturnSceneData(Scene scene) {
    }

    @Override
    // 必须重写该方法，防止MobLink在某些情景下无法还原
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        MobLink.updateNewIntent(getIntent(), this);
    }
}
