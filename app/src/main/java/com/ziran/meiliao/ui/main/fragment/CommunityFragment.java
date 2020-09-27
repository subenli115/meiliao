package com.ziran.meiliao.ui.main.fragment;


import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpFragment;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.SpaceRecommendBean;
import com.ziran.meiliao.ui.bean.StringDataBean;
import com.ziran.meiliao.ui.main.adapter.TabPagerAdapter;
import com.ziran.meiliao.utils.MapUtils;


import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;


/**
 *
 * 社区主页面
 *
 */



public class CommunityFragment extends CommonHttpFragment<CommonPresenter, CommonModel> implements CommonContract.View<SpaceRecommendBean> {


    @Bind(R.id.vp)
    ViewPager vp;
    @Bind(R.id.tl)
    TabLayout tl;
    private int size;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_main_community;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }


    @Override
    protected void initView() {
        // 首先创建适配器（一个简单的继承自FragmentPagerAdapter的适配器）
        loadData();

    }

    private void initTabFragment() {
        TabPagerAdapter adapter = new TabPagerAdapter(getChildFragmentManager(), "1",size);
        vp.setAdapter(adapter);
        tl.setupWithViewPager(vp);
        for (int i = 0; i < adapter.getCount(); i++) {
            TabLayout.Tab tab = tl.getTabAt(i);
            tab.setCustomView(R.layout.tab_title);
            if (i == 0) {
                // 设置第一个tab的TextView是被选择的样式
                TextView tv = tab.getCustomView().findViewById(android.R.id.text1);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                tv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                tv.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
            }
        }
        tl.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // 获取 tab 组件
                View view = tab.getCustomView().findViewById(android.R.id.text1);
                if (null != view) {
                    // 改变 tab 选择状态下的字体大小
                    ((TextView) view).setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                    ((TextView) view).setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    // 改变 tab 选择状态下的字体颜色
                    ((TextView) view).setTextColor(ContextCompat.getColor(getContext(), R.color.black));
                }
//                vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view = tab.getCustomView().findViewById(android.R.id.text1);
                if (null != view ) {
                    // 改变 tab 未选择状态下的字体大小
                    ((TextView) view).setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                    ((TextView) view).setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    // 改变 tab 未选择状态下的字体颜色
                    ((TextView) view).setTextColor(ContextCompat.getColor(getContext(), R.color.gray));
                }
//                vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void loadData() {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("current",1+"");
        defMap.put("size","50");
        defMap.put("userId", MyAPP.getUserId());
        mPresenter.getData(ApiKey.ADMIN_SPACE_FOLLOWSPACE,defMap, SpaceRecommendBean.class);
    }

    @Override
    public void returnData(SpaceRecommendBean result) {
         size = result.getData().size();
        initTabFragment();

    }

    @OnClick({})
    public void onViewClicked(View view) {
        switch (view.getId()) {
        }
    }
}
