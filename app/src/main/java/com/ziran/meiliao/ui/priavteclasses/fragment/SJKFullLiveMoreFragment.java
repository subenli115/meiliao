package com.ziran.meiliao.ui.priavteclasses.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseFragment;
import com.ziran.meiliao.common.base.BaseFragmentAdapter;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.constant.AppConstant;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 私家课-直播详情-简介Fragment
 * Created by Administrator on 2017/1/7.
 */

public class SJKFullLiveMoreFragment extends BaseFragment {


    @Bind(R.id.tab_layout)
    TabLayout tabLayout;
    @Bind(R.id.viewpager)
    ViewPager viewPager;
    protected List<Fragment> fragments;
    protected String[] tabs;
    protected SJKLiveDetailCommentFragment mSJKLiveDetailCommentFragment;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_sjk_full_live_more;
    }

    @Override
    public void initPresenter() {

    }


    @Override
    protected void initView() {
        fragments = new ArrayList<>();
        tabs = getResources().getStringArray(R.array.sjk_live_tabs_3);
        SJKLiveDetailProfileFragment sjkLiveDetailProfileFragment = new SJKLiveDetailProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(AppConstant.ExtraKey.IS_LOAD_DETAIL, false);
        sjkLiveDetailProfileFragment.setArguments(bundle);
        fragments.add(sjkLiveDetailProfileFragment);
        fragments.add(new SJKLiveDetailRecommendCourseFragment());
        mSJKLiveDetailCommentFragment = new SJKLiveDetailCommentFragment();
        fragments.add(mSJKLiveDetailCommentFragment);
        viewPager.setOffscreenPageLimit(fragments.size());
        viewPager.setAdapter(new BaseFragmentAdapter(getChildFragmentManager(), fragments));
        tabLayout.setupWithViewPager(viewPager);
        ViewUtil.changeTabText(tabLayout, tabs);
    }

}
