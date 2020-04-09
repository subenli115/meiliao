package com.ziran.meiliao.ui.priavteclasses.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.base.BaseFragmentAdapter;
import com.ziran.meiliao.common.commonutils.ArrayUtils;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.commonwidget.ViewPagerFixed;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.ui.priavteclasses.fragment.MainOpenLiveFragment;
import com.ziran.meiliao.ui.priavteclasses.fragment.SJKHomeBootCampFragment;
import com.ziran.meiliao.ui.priavteclasses.fragment.SJKHomeGhongZuoFangFragment;
import com.ziran.meiliao.ui.priavteclasses.fragment.SJKHomeLiveFragment;
import com.ziran.meiliao.ui.priavteclasses.fragment.SJKHomeZhuanLanFragment;
import com.ziran.meiliao.utils.MusicPanelFloatManager;
import com.ziran.meiliao.widget.CustomMusicPanelNewView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


/**
 * 私家课界面
 * Created by Administrator on 2017/1/4.
 */

public class PrivateCourseActivity extends BaseActivity {

    @Bind(R.id.ntb)
    NormalTitleBar ntb;
    @Bind(R.id.tab_layout)
    TabLayout tabLayout;
    @Bind(R.id.viewpager)
    ViewPagerFixed viewPager;
    List<Fragment> fragments;
    @Bind(R.id.musicPanelView)
    CustomMusicPanelNewView mCustomMusicPanelNewView;
    private String type;


    public static void startAction(Context mContext,String type) {
        Intent intent = new Intent(mContext, PrivateCourseActivity.class);
        intent.putExtra("type",type);
        mContext.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_private_course;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }


    @Override
    protected void onPause() {
        super.onPause();
        MusicPanelFloatManager.getInstance().unBindView(mCustomMusicPanelNewView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MusicPanelFloatManager.getInstance().bindView(mCustomMusicPanelNewView);
    }

    @Override
    public void initView() {
        if(getIntent()!=null){
            Intent intent = getIntent();
             type = intent.getStringExtra("type");
        }
        ntb.setTvLeftVisiable(true, true);
        String[] tabs = ArrayUtils.getArray(this, getTabsArrayId());
        if (EmptyUtils.isEmpty(fragments)) {
            fragments = new ArrayList<>();
            initFragments(fragments);
            viewPager.setAdapter(new BaseFragmentAdapter(getSupportFragmentManager(), fragments));
            tabLayout.setupWithViewPager(viewPager);
            ViewUtil.changeTabText(tabLayout, tabs);
            tabLayout.getTabAt(Integer.parseInt(type)).select();
        }
    }

    protected void initFragments(List<Fragment> fragments) {
//        fragments.add(new SJKHomeRecommendFragment());
        fragments.add(new SJKHomeZhuanLanFragment());
        fragments.add(new SJKHomeBootCampFragment());
        fragments.add(new SJKHomeGhongZuoFangFragment());
        fragments.add(new SJKHomeLiveFragment());

        if (MyAPP.getUserInfo().getIsTeacher()){
            fragments.add(new MainOpenLiveFragment());
        }
    }


    public int getTabsArrayId() {
        return MyAPP.getUserInfo().getIsTeacher()?R.array.private_course_1:R.array.private_course;
    }

}
