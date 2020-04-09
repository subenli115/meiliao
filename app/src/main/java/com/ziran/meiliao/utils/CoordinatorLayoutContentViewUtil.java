package com.ziran.meiliao.utils;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.commonwidget.ViewPagerFixed;
import com.ziran.meiliao.widget.MyCoordinatorLayout;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/1 14:18
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/1$
 * @updateDes ${TODO}
 */

public class CoordinatorLayoutContentViewUtil {


    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private Context mContext;

    public void init(Context context) {
        mContext = context;

        if (mTabLayout == null) {
            mTabLayout = new TabLayout(context);
            mTabLayout.setId(R.id.tabLayout);
            mTabLayout.setTabTextColors(getColor(R.color.textColor_9f), getColor(R.color.textColor_teshe));
            mTabLayout.setSelectedTabIndicatorColor(getColor(R.color.textColor_teshe));
            mTabLayout.setSelectedTabIndicatorHeight((int) TypedValue.applyDimension(1, 1, mContext.getResources().getDisplayMetrics()));
        }

        if (mViewPager == null) {
            mViewPager = new ViewPagerFixed(mContext);
            mViewPager.setId(R.id.viewPager);
//            mViewPager.setPadding(0, 12, 0, 0);
        }
    }

    public int getColor(int color) {
        return mContext.getResources().getColor(color);
    }

    public void bindTarget(MyCoordinatorLayout coordinatorLayout) {
        if (mTabLayout == null) {
            init(coordinatorLayout.getContext());
        }
        coordinatorLayout.addContentView(mTabLayout, -1, -2);
        coordinatorLayout.addContentView(mViewPager, -1, -1);
    }

    public void setAdapter(PagerAdapter adapter,String[] tabs){
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        ViewUtil.changeTabText(mTabLayout, tabs);
    }
    public void setAdapter(PagerAdapter adapter,int arrayId){
        mViewPager.setAdapter(adapter);
        String[] tabs = mContext.getResources().getStringArray(arrayId);
        mTabLayout.setupWithViewPager(mViewPager);
        ViewUtil.changeTabText(mTabLayout, tabs);
    }
}
