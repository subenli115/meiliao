package com.ziran.meiliao.ui.settings.fragment;

import android.view.View;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpFragment;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.StringDataBean;
import com.ziran.meiliao.ui.settings.activity.RechargeActivity;
import com.ziran.meiliao.utils.MapUtils;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * 优惠劵Fragment
 * Created by Administrator on 2017/1/16.
 */

public class WalletFragment extends CommonHttpFragment<CommonPresenter, CommonModel> implements CommonContract.View<StringDataBean> {

    @Bind(R.id.tv_wallet_balance)
    TextView tvWalletBalance;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_me_wallet;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {
        mPresenter.getData(ApiKey.PURSE_GET_BALANCE, MapUtils.getDefMap(true), StringDataBean.class);
        mRxManager.on(AppConstant.RXTag.BALANCE, new Action1<String>() {
            @Override
            public void call(String balance) {
                tvWalletBalance.setText(balance);
            }
        });
    }

    @Override
    public void returnData(StringDataBean result) {
        tvWalletBalance.setText(result.getNornemData());
    }

    @OnClick({R.id.igv_wallet_recharge, R.id.igv_wallet_coupon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.igv_wallet_recharge:
                RechargeActivity.startAction(getActivity(), tvWalletBalance.getText().toString());
                break;
            case R.id.igv_wallet_coupon:
                //金币页面
                showShortToast("该功能暂未开放,敬请期待");
//                startActivity(GetGoalActivity.class);
                break;
        }
    }
}
