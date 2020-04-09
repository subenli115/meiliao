package com.ziran.meiliao.ui.settings.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;


public class RechargePagerAdapter extends FragmentPagerAdapter {

    private List <Fragment> list;
    final int PAGE_COUNT = 2;
    private Context context;

    private String [] tabTitles = {"现金充值", "积分兑换"};

    public RechargePagerAdapter(FragmentManager fm, Context context, List<Fragment> list){
        super(fm);
        this.context = context;
        this.list=list;
    }

    @Override
    public Fragment getItem(int position) {

        return list.get(position);
    }
//    public static BaseWeekFragment newInstance(int num) {
//        BaseWeekFragment frag = new BaseWeekFragment();
//        Bundle args = new Bundle();
//        args.putString("num", num+1+"");
//        frag.setArguments(args);
//        return frag;
//    }
    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return tabTitles[position];
    }
}
