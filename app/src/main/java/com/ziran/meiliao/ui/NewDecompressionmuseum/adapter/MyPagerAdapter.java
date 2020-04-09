package com.ziran.meiliao.ui.NewDecompressionmuseum.adapter;

/**
 * Created by Administrator on 2018/10/30.
 */

        import android.content.Context;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentPagerAdapter;
        import android.view.View;

        import com.ziran.meiliao.ui.NewDecompressionmuseum.fragment.BaseWeekFragment;

        import java.util.List;


public class MyPagerAdapter extends FragmentPagerAdapter {

    private List <View> list;
    final int PAGE_COUNT = 8;
    private Context context;

    private String [] tabTitles = {"第一周", "第二周", "第三周","第四周","第五周","第六周","第七周","第八周"};

    public MyPagerAdapter(FragmentManager fm, Context context){
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {

        return newInstance(position);
    }
             public static BaseWeekFragment newInstance(int num) {
                 BaseWeekFragment frag = new BaseWeekFragment();
             Bundle args = new Bundle();
             args.putString("num", num+1+"");
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
