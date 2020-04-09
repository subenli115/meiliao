package com.ziran.meiliao.ui.settings.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.base.BaseFragmentAdapter;
import com.ziran.meiliao.common.commonutils.ArrayUtils;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.commonwidget.ViewPagerFixed;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.settings.fragment.WalletDetailFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 正念币明细界面
 * Created by Administrator on 2017/1/4.
 */

public class WalletDetailActivity extends BaseActivity {
    @Bind(R.id.ntb)
    public NormalTitleBar ntb;
    @Bind(R.id.tab_layout)
    TabLayout tabLayout;
    @Bind(R.id.viewpager)
    ViewPagerFixed viewPager;
    protected List<Fragment> fragments;


    @Override
    public int getLayoutId() {
        return R.layout.activity_wallet_detail;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        ntb.setTvLeftVisiable(true, true);
        ntb.setTitleText("明细");
        String[] tabs = ArrayUtils.getArray(this, R.array.wallet_detail);
        if (EmptyUtils.isEmpty(fragments)) {
            fragments = new ArrayList<>();

            fragments.add(createFragment( WalletDetailFragment.FROM_TYPE_ZHICHU));
            fragments.add(createFragment( WalletDetailFragment.FROM_TYPE_SHOURU));
            viewPager.setAdapter(new BaseFragmentAdapter(getSupportFragmentManager(), fragments));
            tabLayout.setupWithViewPager(viewPager);
            ViewUtil.changeTabText(tabLayout, tabs);
        }
//        initCanTouchListener(viewPager);
    }

    public WalletDetailFragment createFragment(int fromType){
        WalletDetailFragment fragment = new WalletDetailFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putInt(AppConstant.ExtraKey.FROM_TYPE,fromType);
        fragment.setArguments(bundle1);
        return fragment;
    }
}