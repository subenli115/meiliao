package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import com.ziran.meiliao.ui.bean.SubTagBean;
import com.ziran.meiliao.ui.priavteclasses.fragment.OneZhuanLanFragment;

import java.util.List;


public class ZhuanLanTabadapter extends FragmentPagerAdapter {

    private final Activity mActivity;
    private  List<SubTagBean.DataBean> mData;
    private  String[] tabTitles;
    private List <View> list;
    final int PAGE_COUNT = 8;
    private Context context;


    public ZhuanLanTabadapter(FragmentManager fm, Context context, List<SubTagBean.DataBean> data, Activity activity){
        super(fm);
        mData=data;
        mActivity=activity;
        int size = mData.size();
         tabTitles = new String[size];
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("tagId", mData.get(position).getTagId()+"");
        OneZhuanLanFragment oneZhuanLanFragment = new OneZhuanLanFragment();
        oneZhuanLanFragment.setArguments(bundle);
        return oneZhuanLanFragment;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        for(int i=0;i<mData.size();i++){
            tabTitles[i]=mData.get(i).getTagName();
        }
        return tabTitles[position];
    }
}

