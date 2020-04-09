package com.ziran.meiliao.ui.settings.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.ui.base.CommonHttpFragment;
import com.ziran.meiliao.ui.bean.ExchangeBean;
import com.ziran.meiliao.ui.bean.IntegralDetailBean;
import com.ziran.meiliao.ui.bean.MemberCenterBean;
import com.ziran.meiliao.ui.bean.MemberExchangeBean;
import com.ziran.meiliao.ui.settings.activity.IntegralDetailsActivity;
import com.ziran.meiliao.ui.settings.adapter.IntegralAdapter;
import com.ziran.meiliao.ui.settings.contract.MemberDetailsContract;
import com.ziran.meiliao.ui.settings.model.MemberModel;
import com.ziran.meiliao.ui.settings.presenter.MemberDetailsPresenter;
import com.ziran.meiliao.utils.MapUtils;

import java.util.Map;

import butterknife.Bind;


public class IntegralDetailsFragment extends CommonHttpFragment<MemberDetailsPresenter, MemberModel> implements MemberDetailsContract.View {

    @Bind(R.id.recyclerView)
    public RecyclerView recyclerView;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_one_week;
    }

    @Override
    protected void initBundle(Bundle extras) {
    }
    @Override
    protected void initView() {
        super.initView();
        String str = (String)getArguments().get("num");
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("type", str);
        if(mPresenter!=null){
            mPresenter.postGetScore(defMap,getContext());
        }
    }
    @Override
    public void initPresenter() {
        if (mModel != null && mPresenter != null) {
            mPresenter.setVM(this, mModel);
        }
    }

    @Override
    public void showGoodsData(MemberExchangeBean.DataBean result) {

    }

    @Override
    public void showMemberCenterData(MemberCenterBean.DataBean result) {

    }

    @Override
    public void showResult(ExchangeBean.DataBean result) {

    }

    @Override
    public void showScore(IntegralDetailBean.DataBean result) {
        ((IntegralDetailsActivity ) getActivity()).setData(result.getCustomerScore()+"");
        IntegralAdapter  mAdapter = new IntegralAdapter(result.getScoreList(),getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
    }

}
