package com.ziran.meiliao.ui.settings.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.ui.bean.ExchangeBean;
import com.ziran.meiliao.ui.bean.IntegralDetailBean;
import com.ziran.meiliao.ui.bean.MemberCenterBean;
import com.ziran.meiliao.ui.bean.MemberExchangeBean;
import com.ziran.meiliao.ui.settings.adapter.IntegralDetailsPagerAdapter;
import com.ziran.meiliao.ui.settings.contract.MemberDetailsContract;
import com.ziran.meiliao.ui.settings.model.MemberModel;
import com.ziran.meiliao.ui.settings.presenter.MemberDetailsPresenter;

import butterknife.Bind;

public class IntegralDetailsActivity extends BaseActivity<MemberDetailsPresenter, MemberModel> implements MemberDetailsContract.View{



    @Bind(R.id.ntb)
    public NormalTitleBar ntb;
    private TabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.tv_money)
    TextView tvMoney;


    public static void startAction(Context mContext) {
        Intent intent = new Intent(mContext, IntegralDetailsActivity.class);
        mContext.startActivity(intent);
    }


    @Override
    public int getLayoutId() {
        return R.layout.ac_member_integral_details;
    }

    @Override
    public void initPresenter() {

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

    }

    @Override
    public void initView() {
        ntb.setVerLineVisiable(false);
        ntb.setNewTitleText("积分明细");
        tabLayout= (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("全部"));
        tabLayout.addTab(tabLayout.newTab().setText("收入"));
        tabLayout.addTab(tabLayout.newTab().setText("支出"));
        tabLayout.setupWithViewPager(viewPager);
        ntb.setBackGroundColor(R.color.transparent);
        viewPager.setAdapter(new IntegralDetailsPagerAdapter(getSupportFragmentManager(),mContext));

    }
    public void setData(String str){
        tvMoney.setText(str);
    }

}
