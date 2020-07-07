package com.ziran.meiliao.ui.settings.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ziran.meiliao.ui.settings.fragment.OneZhuanLanFragment;

import java.util.List;


public class FollowTabadapter extends FragmentPagerAdapter {

    private final Activity mActivity;
    private  String[]  mData;
    private  String[] tabTitles;
    private List <View> list;
    final int PAGE_COUNT = 8;
    private Context context;


    public FollowTabadapter(FragmentManager fm, Context context, String[] data, Activity activity){
        super(fm);
        mData=data;
        mActivity=activity;
        int size = mData.length;
         tabTitles = new String[size];
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("position", position+"");
        OneZhuanLanFragment oneZhuanLanFragment = new OneZhuanLanFragment();
        oneZhuanLanFragment.setArguments(bundle);
        return oneZhuanLanFragment;
    }

    @Override
    public int getCount() {
        return mData.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mData[position];
    }
}

