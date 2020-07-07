package com.ziran.meiliao.ui.settings.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseFragment;
import com.ziran.meiliao.ui.settings.adapter.FollowTabadapter;

import butterknife.Bind;
import butterknife.OnClick;

public class MyFollowFragment extends BaseFragment {

    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;


    private static final int MAX_ZHUANLAN_COUNT = 8;
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_sjk_follow;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        String[] stringArray = getResources().getStringArray(R.array.private_course);
        for(int i=0;i<stringArray.length;i++){
            tabLayout.addTab(tabLayout.newTab().setText(stringArray[i]));
        }
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#459BFF"));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TextView textView = (TextView)LayoutInflater.from(getContext()).inflate(R.layout.title_text_layout, null);
                textView.setText(tab.getText());
                textView.setGravity(Gravity.CENTER);
                tab.setCustomView(textView);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.setCustomView(null);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.setAdapter(new FollowTabadapter( getActivity().getSupportFragmentManager(),getContext(),stringArray,getActivity()));
    }





    @OnClick(R.id.tv_fm_sjk_zhuanlan_change)
    public void onViewClicked() {
    }
}
