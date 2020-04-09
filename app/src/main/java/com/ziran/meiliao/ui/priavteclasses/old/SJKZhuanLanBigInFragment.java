package com.ziran.meiliao.ui.priavteclasses.old;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.WpyxConfig;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.ZhuanLanBigInBean;
import com.ziran.meiliao.ui.priavteclasses.adapter.SJKZhuanLanBigInAudioNewAdapter;
import com.ziran.meiliao.ui.priavteclasses.bean.ZhuanLanData;
import com.ziran.meiliao.ui.priavteclasses.contract.SJKZhuanLanBigInContract;
import com.ziran.meiliao.ui.priavteclasses.presenter.SJKZhuanLanBinInPresenter;
import com.ziran.meiliao.ui.priavteclasses.util.BuyZhuanLanUtil;
import com.ziran.meiliao.ui.priavteclasses.util.ZhuanLanHeadUtil;
import com.ziran.meiliao.ui.priavteclasses.util.ZhuanLanMusicDataConfig;
import com.ziran.meiliao.ui.settings.activity.RechargeActivity;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.PayUtil;
import com.ziran.meiliao.widget.pupop.BuyAlbumPopupWindow;
import com.ziran.meiliao.widget.pupop.JoinVipPopupWindow;
import com.ziran.meiliao.widget.pupop.PayResultPopupWindow;
import com.ziran.meiliao.widget.pupop.PopupWindowUtil;

import java.util.Map;

import rx.functions.Action1;


/**
 * 专栏详情Fragment
 */
public class SJKZhuanLanBigInFragment extends CommonRefreshFragment<SJKZhuanLanBinInPresenter, CommonModel> implements
        SJKZhuanLanBigInContract.View {

    private ZhuanLanBigInBean.DataBean mDataBean;
    private ZhuanLanHeadUtil mZhuanLanHeadUtil;

    private Map<String, String> stringMap;
    private String targetId = "1";
    private SJKZhuanLanBigInAudioNewAdapter mSJKZhuanLanBigInAudioAdapter;
    private String currentUrl;

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        try {
            targetId = getActivity().getIntent().getExtras().getString(AppConstant.ExtraKey.SUBSCRIPTION_ID);
        } catch (Exception e) {
            targetId = "";
            e.printStackTrace();
        }
        stringMap = MapUtils.getOnlyCan(AppConstant.ExtraKey.TARGET_ID, targetId);
        stringMap.put(AppConstant.ExtraKey.SUBSCRIPTION_ID, targetId);
        setRecyclerEnabled(false, true);
        mPresenter.postListenUp(ApiKey.SPEC_COLUMN_SEE_UP, stringMap);

        mSJKZhuanLanBigInAudioAdapter = new SJKZhuanLanBigInAudioNewAdapter(getContext());
        mZhuanLanHeadUtil = new ZhuanLanHeadUtil(getContext());
        mZhuanLanHeadUtil.initHeadView(false);
        mZhuanLanHeadUtil.bindFooterView(iRecyclerView, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZhuanLanBigInBean.DataBean.DirBean item = getItem();
                startPlay(v, item);
            }
        });
        mZhuanLanHeadUtil.setTarget(iRecyclerView, mSJKZhuanLanBigInAudioAdapter);
        return mSJKZhuanLanBigInAudioAdapter;
    }

    private void startPlay(View v, ZhuanLanBigInBean.DataBean.DirBean item) {
        if (EmptyUtils.isNotEmpty(item.getUrl()) && item.getUrl().equals(currentUrl)) {
            showShortToast("播放中");
            if (v!=null){
                v.setVisibility(View.GONE);
            }
        } else if (mDataBean.isIsBuy() || item.isSt()) {
            ZhuanLanMusicDataConfig.setTarget(item);
            mRxManager.post(AppConstant.RXTag.BIG_IN_TAG, HandlerUtil.obj(2, item));
            currentUrl = item.getUrl();
        } else {
            showShortToast("请购买专栏后再播放");
        }
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(getContext(), 8);
    }

    @Override
    protected void loadData() {
        mPresenter.getData(ApiKey.SUBSCRIPTION_BIG_BRAND, stringMap);
    }

    private PayUtil.OnPayCallBack mOnPayCallBack = new PayUtil.OnPayCallBack() {
        @Override
        public void onPaySuccess(int platform) {
            onPayResult(null);
        }

        @Override
        public void onPayFailed() {

        }
    };

    public void onPayResult(String url) {
        PayResultPopupWindow payResultPopupwindow = new PayResultPopupWindow(getContext());
        payResultPopupwindow.setResult(true);
        PopupWindowUtil.show(getActivity(), payResultPopupwindow);
        mRxManager.post(AppConstant.RXTag.BIG_IN_TAG, HandlerUtil.obj(3));
    }

    public View.OnClickListener getArewardClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.ll_horizontal_use_video_coupon_vip:
                        JoinVipPopupWindow popupwindow = new JoinVipPopupWindow(getContext());
                        popupwindow.setOnPayCallBack(mOnPayCallBack);
                        PopupWindowUtil.show(popupwindow);
                        break;
                    case R.id.ll_horizontal_use_video_coupon_buy:
                        buy();
                        break;
                }
            }
        };
    }

    public void gzZhuanLan() {
        if (mDataBean == null) return;
        stringMap.put("gz", mDataBean.isIsGz() ? "false" : "true");
        mPresenter.postGz(ApiKey.SPEC_COLUMN_GZ, stringMap);
    }

    public void share() {
        mZhuanLanHeadUtil.share();
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        super.onItemClick(parent, view, o, position);
        ZhuanLanBigInBean.DataBean.DirBean dirBean = EmptyUtils.parseObject(o);
        mSJKZhuanLanBigInAudioAdapter.onItemClick(position);
        ZhuanLanMusicDataConfig.setTarget(dirBean);
        if (mZhuanLanHeadUtil.isShowCal()) {
            mZhuanLanHeadUtil.setFooterViewData(dirBean.getAESUrl(), dirBean.getTitle(), dirBean.getDuration(), dirBean.getStudyCount(),
                    dirBean.getStudyStatus(), dirBean.isSt(), mDataBean.isIsBuy(), true);
        } else {
            startPlay(null,dirBean);
//            if (mDataBean.isIsBuy() || dirBean.isSt()) {
//                mRxManager.post(AppConstant.RXTag.BIG_IN_TAG, HandlerUtil.obj(2, dirBean));
//            } else {
//                showShortToast("该音频不是试听音频,请购买后再收听");
//            }
        }
    }

    @Override
    public void showData(ZhuanLanBigInBean.DataBean result) {
        mDataBean = result;
        if (EmptyUtils.isNotEmpty(mDataBean.getDir())) {
            ZhuanLanBigInBean.DataBean.DirBean dirBean = mDataBean.getDir().get(0);
            dirBean.setCheck(true);
            dirBean.setSt(true);
            mZhuanLanHeadUtil.setFooterViewData(dirBean.getAESUrl(), dirBean.getTitle(), dirBean.getDuration(), dirBean.getStudyCount(),
                    dirBean.getStudyStatus(), dirBean.isSt(), mDataBean.isIsBuy());
        }
        mRxManager.post(AppConstant.RXTag.BIG_IN_TAG, HandlerUtil.obj(1, mDataBean));
        mZhuanLanHeadUtil.setHeadData(mDataBean.getPic(), mDataBean.getTitle(), mDataBean.getAuthor().getTeaIntro(), mDataBean.getDetail());
        mZhuanLanHeadUtil.bindTarget(iRecyclerView);
        mZhuanLanHeadUtil.setShareDataBean(mDataBean);
        updateData(mDataBean.getDir());
    }

    @Override
    public void showGzResult(Result result) {
        showShortToast(result.getResultMsg());
        mDataBean.setIsGz(!mDataBean.isIsGz());
        mRxManager.post(AppConstant.RXTag.BIG_IN_TAG, HandlerUtil.obj(0, mDataBean.isIsGz()));
    }

    @Override
    public void showBuyResult(Result result) {
        mDataBean.setIsBuy(true);
        onPayResult(null);
    }


    public <T> T getItem() {
        return (T) mAdapter.getItem();
    }

    public boolean isBuy() {
        return mDataBean.isIsBuy();
    }

    private BuyZhuanLanUtil mBuyZhuanLanUtil;

    private BuyAlbumPopupWindow.BuyAlbumClickListener mBuyAlbumClickListener;

    public void buy() {
        if (mBuyZhuanLanUtil == null) mBuyZhuanLanUtil = new BuyZhuanLanUtil();
        if (mBuyAlbumClickListener == null) {
            mBuyAlbumClickListener = new BuyAlbumPopupWindow.BuyAlbumClickListener() {
                @Override
                public void buyAlbum(boolean needRecharge) {
                    if (needRecharge) {
                        mRxManager.on(AppConstant.RXTag.BALANCE, new Action1<String>() {
                            @Override
                            public void call(String balance) {
//                    tvWalletBalance.setText(balance);
                                mBuyZhuanLanUtil.setUserCoin(Integer.parseInt(balance));
                            }
                        });
                        RechargeActivity.startAction(getContext(), String.valueOf(WpyxConfig.getZhuanLanData().getUserCoin()));
                    } else {
                        mPresenter.postBuy(ApiKey.SPEC_COLUMN_BUY, MapUtils.getOnlyCan(AppConstant.ExtraKey.SUBSCRIPTION_ID, targetId));
                    }
                }
            };
        }
        ZhuanLanData zhuanLanData = new ZhuanLanData(1, mDataBean.getUserCoin(), mDataBean.getNeedCoin(), targetId, mDataBean.getTitle(),
                mDataBean.getPic());
        zhuanLanData.setBuy(mDataBean.isIsBuy());
        WpyxConfig.setZhuanLanData(zhuanLanData);
        mBuyZhuanLanUtil.showPayV13(getContext(), mBuyAlbumClickListener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        WpyxConfig.setZhuanLanData(null);
    }
}
