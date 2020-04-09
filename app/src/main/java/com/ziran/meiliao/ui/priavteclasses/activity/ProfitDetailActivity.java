package com.ziran.meiliao.ui.priavteclasses.activity;

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
import com.ziran.meiliao.ui.settings.fragment.ProfitDetailFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


public class ProfitDetailActivity extends BaseActivity {


    @Bind(R.id.ntb)
    public NormalTitleBar ntb;
    @Bind(R.id.tab_layout)
    public TabLayout tabLayout;
    @Bind(R.id.viewpager)
    public ViewPagerFixed viewPager;

    protected List<Fragment> fragments;
    String mIncomeId;
    @Override
    protected void initBundle(Bundle extras) {
        mIncomeId  = extras.getString("_ID");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_sjk_live_room_profit_detail;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {

        String[] tabs = ArrayUtils.getArray(this, getTabsArrayId());

        if (EmptyUtils.isEmpty(fragments)) {
            fragments = new ArrayList<>();
            fragments.add( ProfitDetailFragment.newInstance(mIncomeId,"1"));
            fragments.add( ProfitDetailFragment.newInstance(mIncomeId,"2"));
            viewPager.setAdapter(new BaseFragmentAdapter(getSupportFragmentManager(), fragments));
            tabLayout.setupWithViewPager(viewPager);
            ViewUtil.changeTabText(tabLayout, tabs);
        }
    }



    public int getTabsArrayId() {
        return R.array.profit_detail;
    }

}
