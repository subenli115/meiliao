package com.ziran.meiliao.ui.priavteclasses.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.WpyxConfig;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.RecordListBean;
import com.ziran.meiliao.ui.priavteclasses.adapter.SJKZhuanLanSubscribeAdapter;
import com.ziran.meiliao.ui.priavteclasses.bean.ZhuanLanData;
import com.ziran.meiliao.ui.priavteclasses.contract.ZhuanLanSubscribeContract;
import com.ziran.meiliao.ui.priavteclasses.presenter.ZhuanLanScbscribePresenter;
import com.ziran.meiliao.ui.priavteclasses.util.BuyZhuanLanUtil;
import com.ziran.meiliao.ui.settings.activity.RechargeActivity;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.pupop.BuyAlbumPopupWindow;
import com.ziran.meiliao.widget.pupop.PayResultPopupWindow;
import com.ziran.meiliao.widget.pupop.PopupWindowUtil;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;


/**
 *
 */
public class SJKZhuanLanSubscribeFragment extends CommonRefreshFragment<ZhuanLanScbscribePresenter, CommonModel> implements
        ZhuanLanSubscribeContract.View {


    @Bind(R.id.tv_fm_sjk_zhuanlan_subscribe)
    TextView tvFmSjkZhuanlanSubscribe;

    private int freeOrSub = 0;
    private String specColumnId;
    private BuyZhuanLanUtil mBuyZhuanLanUtil;
    private BuyAlbumPopupWindow.BuyAlbumClickListener mBuyAlbumClickListener;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_sjk_zhuanlan_subscribe;
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        ZhuanLanData mZhuanLanData = WpyxConfig.getZhuanLanData();
        if (mZhuanLanData == null) mZhuanLanData = new ZhuanLanData();
        freeOrSub = mZhuanLanData.getType();
        specColumnId = mZhuanLanData.getSpecColumnId();
        if (freeOrSub != 0 && !mZhuanLanData.isBuy()) {
            tvFmSjkZhuanlanSubscribe.setVisibility(View.VISIBLE);
            ViewUtil.setText(tvFmSjkZhuanlanSubscribe, mZhuanLanData.getZhuanLanNeedCoin());
        }
        loadedTip.setEmptyMsg(getString(R.string.not_subscribe), R.mipmap.ic_empty_nocontent);
        return new SJKZhuanLanSubscribeAdapter(getContext(), R.layout.item_sjk_zhuanlan_subscribe, freeOrSub);
    }

    @Override
    protected void loadData() {
        Map<String, String> stringMap = MapUtils.getDefMap(true);
        stringMap.put(AppConstant.ExtraKey.SUBSCRIPTION_ID, specColumnId);
        stringMap.put("page", String.valueOf(page));
        stringMap.put("free", freeOrSub == 0 ? "1" : "0");
        mPresenter.getRecordList(ApiKey.SPEC_COLUMN_LIST_RECORD, stringMap);
    }


    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        RecordListBean.DataBean dataBean = EmptyUtils.parseObject(o);
//        switch (dataBean.getType()) {
//            case 2:
//                SubscribeAudioActivity.startAction(getContext(), dataBean.getId());
//                break;
//            case 1:
//                SubscribeVideoActivity.startAction(getContext(), dataBean.getId());
//                break;
//        }
    }

    @OnClick(R.id.tv_fm_sjk_zhuanlan_subscribe)
    public void onViewClicked() {
        if (mBuyZhuanLanUtil == null) mBuyZhuanLanUtil = new BuyZhuanLanUtil();
        if (mBuyAlbumClickListener == null) {
            mBuyAlbumClickListener = new BuyAlbumPopupWindow.BuyAlbumClickListener() {
                @Override
                public void buyAlbum(boolean needRecharge) {
                    if (needRecharge) {
                        RechargeActivity.startAction(getContext(), String.valueOf(WpyxConfig.getZhuanLanData().getUserCoin()));
                    } else {
                        mPresenter.postBuySpecColumn(ApiKey.SPEC_COLUMN_BUY, MapUtils.getOnlyCan(AppConstant.ExtraKey.SUBSCRIPTION_ID, specColumnId));
                    }
                }
            };
        }
        mBuyZhuanLanUtil.showPayV13(getContext(), mBuyAlbumClickListener);
    }

    @Override
    public void showBuySpecColumnResult(Result result) {
        boolean isBuy = "购买成功".equals(result.getResultMsg());
        PayResultPopupWindow payResultPopupWindow = new PayResultPopupWindow(getContext());
        payResultPopupWindow.setResult(isBuy, result.getResultMsg());
        PopupWindowUtil.show(getActivity(), payResultPopupWindow);
        WpyxConfig.getZhuanLanData().setBuy(isBuy);
        tvFmSjkZhuanlanSubscribe.setVisibility(isBuy ? View.GONE : View.VISIBLE);
    }

    @Override
    public void showRecordList(List<RecordListBean.DataBean> beanList) {
        updateData(beanList);
    }
}
