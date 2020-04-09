package com.ziran.meiliao.ui.settings.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.base.BaseFragmentAdapter;
import com.ziran.meiliao.common.commonutils.ArrayUtils;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.commonwidget.ViewPagerFixed;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.ui.settings.fragment.BuyAlbumFragment;
import com.ziran.meiliao.ui.settings.fragment.BuyBootCampFragment;
import com.ziran.meiliao.ui.settings.fragment.BuyClassFragment;
import com.ziran.meiliao.ui.settings.fragment.BuyCourseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 已购买课程
 * Created by Administrator on 2017/1/4.
 */

public class MBuyCourseActivity extends BaseActivity {

    @Bind(R.id.ntb)
    public NormalTitleBar ntb;
    @Bind(R.id.tab_layout)
    public TabLayout tabLayout;
    @Bind(R.id.headview_vip)
    public View headview_vip;
    @Bind(R.id.viewpager)
    public ViewPagerFixed viewPager;


    @Override
    public int getLayoutId() {
        return R.layout.activity_me_buy_course;
    }

    @Override
    public void initPresenter() {

    }

    protected List<Fragment> fragments;

    @Override
    public void initView() {
        ntb.setTvLeftVisiable(true, true);
        ntb.setTitleText(getString(R.string.main_me_buy_couse));
        String[] tabs = ArrayUtils.getArray(this, getTabsArrayId());

        if (MyAPP.isVip()) {
            headview_vip.setVisibility(View.VISIBLE);
            viewPager.setVisibility(View.GONE);
            tabLayout.addTab(tabLayout.newTab().setText(tabs[0]));
            tabLayout.addTab(tabLayout.newTab().setText(tabs[1]));
            tabLayout.addTab(tabLayout.newTab().setText(tabs[2]));
            tabLayout.addTab(tabLayout.newTab().setText(tabs[3]));
        } else {
            if (EmptyUtils.isEmpty(fragments)) {
                fragments = new ArrayList<>();
                fragments.add(new BuyAlbumFragment());
                fragments.add(new BuyCourseFragment());
                fragments.add(new BuyClassFragment());
                fragments.add(new BuyBootCampFragment());
                viewPager.setAdapter(new BaseFragmentAdapter(getSupportFragmentManager(), fragments));
                tabLayout.setupWithViewPager(viewPager);
                ViewUtil.changeTabText(tabLayout, tabs);
            }
        }
    }

    public int getTabsArrayId() {
        return R.array.buy_course;
    }

}