package com.ziran.meiliao.ui.workshops.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseFragmentAdapter;
import com.ziran.meiliao.common.commonutils.ArrayUtils;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.commonwidget.ViewPagerFixed;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.priavteclasses.fragment.SJKHomeGhongZuoFangFragment;
import com.ziran.meiliao.ui.workshops.fragment.CourseLibraryCrowdFundingFragment;
import com.ziran.meiliao.ui.workshops.fragment.CourseLibraryTeacherFragment;
import com.ziran.meiliao.ui.workshops.fragment.CourseLibraryTeamFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/10 14:57
 * @des ${我要众筹官方报价界面}
 * @updateAuthor $Author$
 * @updateDate 2017/8/10$
 * @updateDes ${TODO}
 */

public class CourseLibraryActivity extends CommonHttpActivity {


    @Bind(R.id.tab_layout)
    TabLayout tabLayout;
    @Bind(R.id.ntb)
    NormalTitleBar ntb;
    @Bind(R.id.viewpager)
    ViewPagerFixed viewPager;
    protected List<Fragment> fragments;

    @Override
    public int getLayoutId() {
        return R.layout.activity_course_library;
    }

    private String[] tabs;

    private int currentIndex;

    @Override
    public void initView() {
        super.initView();
        try {
            currentIndex = getIntentExtra(getIntent(), AppConstant.ExtraKey.FROM_TYPE, 0);
//            ViewUtil.setText(tvSearch, WpyxConfig.getHotWord());
        } catch (Exception e) {
            e.printStackTrace();
        }

        tabs = ArrayUtils.getArray(this, R.array.course_library);
        if (EmptyUtils.isEmpty(fragments)) {
            fragments = new ArrayList<>();
            fragments.add(new CourseLibraryCrowdFundingFragment());
            fragments.add(new CourseLibraryTeamFragment());
            fragments.add(new CourseLibraryTeacherFragment());
            fragments.add(new SJKHomeGhongZuoFangFragment());
            viewPager.setAdapter(new BaseFragmentAdapter(getSupportFragmentManager(), fragments));
            tabLayout.setupWithViewPager(viewPager);
            ViewUtil.changeTabText(tabLayout, tabs);

            viewPager.setCurrentItem(currentIndex, false);

            tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition(), false);
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                }
            });
        }
    }


    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

//
//    @OnClick({R.id.iv_workshops_main_back})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.iv_workshops_main_back:
//                finish();
//                break;
//            case R.id.iv_workshops_main_search:
//                SearchActivity.startAction(WpyxConfig.getHotWord());
//                CourseLibrarySearchActivity.startAction(tabs[viewPager.getCurrentItem()]);
//                break;
//        }
//    }

    public static void startAction(Context context, int index) {
        Intent intent = new Intent(context, CourseLibraryActivity.class);
        intent.putExtra(AppConstant.ExtraKey.FROM_TYPE, index);
        context.startActivity(intent);
    }
}
