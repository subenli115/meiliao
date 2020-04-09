package com.ziran.meiliao.ui.priavteclasses.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.BankInfoBean;
import com.ziran.meiliao.ui.priavteclasses.activity.MyBankListActivity;
import com.ziran.meiliao.ui.priavteclasses.adapter.MyBankListAdapter;
import com.ziran.meiliao.utils.MapUtils;
import com.wevey.selector.dialog.DialogOnClickListener;
import com.wevey.selector.dialog.MDAlertDialog;

import java.util.List;

import rx.functions.Action1;

/**
 * 私家课-直播详情-简介Fragment
 * Created by Administrator on 2017/1/7.
 */

public class MyBankListFragment extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract.View<BankInfoBean> {
    @Override
    public CommonRecycleViewAdapter getAdapter() {
        MyBankListActivity myBankListActivity = (MyBankListActivity) getActivity();
        String title = myBankListActivity.getNtbTitle();
        isChooseBank = "选择银行卡".equals(title);
        setRecyclerEnabled(false);
        return new MyBankListAdapter(getContext(), R.layout.item_my_bank_list, isChooseBank);
    }

    @Override
    protected void initView() {
        super.initView();
        rootView.setBackgroundColor(getColor(R.color.transparent));
        mRxManager.on(AppConstant.RXTag.UNBIND_BANK, new Action1<String>() {
            @Override
            public void call(final String bankId) {
                MDAlertDialog.createDialog(getContext(), "温馨提示", "您真的需要解绑这张银行卡吗", "取消", "确定", new DialogOnClickListener() {
                    @Override
                    public void clickLeftButton(Dialog dialog, View view) {
                        dialog.dismiss();
                    }

                    @Override
                    public void clickRightButton(Dialog dialog, View view) {
                        dialog.dismiss();
                        deleteBankNo = bankId;
                        mPresenter.postData(ApiKey.LIVE_BANK_CARD_DELETE, MapUtils.getOnlyCan("bankCardNo", bankId), BankInfoBean.class);
                    }
                });
            }
        });

    }
    private String deleteBankNo;
    private boolean isChooseBank = true;

    @Override
    protected void loadData() {
        Intent intent = getActivity().getIntent();
        boolean isLoadData = false;
        if (intent != null && intent.getExtras() != null) {
            Bundle extras = intent.getExtras();
            BankInfoBean bankInfoBean = extras.getParcelable(AppConstant.ExtraKey.BANK_LIST);
            if (bankInfoBean != null) {
                updateData(bankInfoBean.getData());
                isLoadData = true;
            }
        }
        if (!isLoadData) {
            isChooseBank = true;
            mPresenter.getData(ApiKey.BANK_CARD_LIST_BIND_CARD, MapUtils.getDefMap(true), BankInfoBean.class);
        }
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        if (isChooseBank){
            BankInfoBean.DataBean bean = EmptyUtils.parseObject(o);
            Intent intentResult = getActivity().getIntent();
            intentResult.putExtra(AppConstant.ExtraKey.BANK_INFO, bean);
            getActivity().setResult(Activity.RESULT_OK, intentResult);
            finish();
        }
    }

    @Override
    public void returnData(BankInfoBean result) {
        List<BankInfoBean.DataBean> all = mAdapter.getAll();
        for (BankInfoBean.DataBean bean : all) {
            if (deleteBankNo.equals(bean.getBankCardNo())){
                mAdapter.remove(bean);
                return;
            }
        }
//        updateData(result.getData());
    }

    public void addBank(BankInfoBean.DataBean bankInfo) {
        mAdapter.add(bankInfo);
    }
}
