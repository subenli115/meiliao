package com.ziran.meiliao.ui.settings.adapter;

/**
 * Created by Administrator on 2018/10/30.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import com.ziran.meiliao.ui.settings.fragment.IntegralDetailsFragment;

import java.util.List;


public class IntegralDetailsPagerAdapter extends FragmentPagerAdapter {

    private List <View> list;
    final int PAGE_COUNT = 3;
    private Context context;

    private String [] tabTitles = {"全部", "收入", "支出"};

    public IntegralDetailsPagerAdapter(FragmentManager fm, Context context){
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {

        return newInstance(position);
    }

             public static IntegralDetailsFragment newInstance(int num) {
             IntegralDetailsFragment frag = new IntegralDetailsFragment();
             Bundle args = new Bundle();
             args.putString("num", num+"");
             frag.setArguments(args);
             return frag;
             }



    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return tabTitles[position];
    }
}
