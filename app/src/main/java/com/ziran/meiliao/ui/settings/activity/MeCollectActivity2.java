package com.ziran.meiliao.ui.settings.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.ui.settings.fragment.CollectBootCampFragment;
import com.ziran.meiliao.ui.settings.fragment.CollectCourseFragment;
import com.ziran.meiliao.ui.settings.fragment.CollectJDXFragment;
import com.ziran.meiliao.ui.settings.fragment.CollectZLFragment;
import com.ziran.meiliao.ui.settings.fragment.DeleteRefreshFragment;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * 收藏界面
 * Created by Administrator on 2017/1/4.
 */

public class MeCollectActivity2 extends CommonDeleteActivity {

    @Bind(R.id.iv_switch)
    ImageView ivSwitch;

    @Override
    public int getLayoutId() {
        return R.layout.activity_me_collect2;
    }

    @Override
    public void initView() {
        super.initView();
        ntb.setTitleText(getString(R.string.myfavorites));
        tips = "取消收藏";
        btnDelete.setText("取消收藏");
    }

    private CollectJDXFragment collectJDXFragment;

    @Override
    protected void initFragments(List<Fragment> fragments) {
        collectJDXFragment = new CollectJDXFragment();
        fragments.add(collectJDXFragment);
        fragments.add(new CollectCourseFragment());
        fragments.add(new CollectZLFragment());
        fragments.add(new CollectBootCampFragment());
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                ivSwitch.setVisibility(position == 0 ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    protected DeleteRefreshFragment getCurrFragment(int position) {
        return collectJDXFragment.getCurrFragment();
    }

    @OnClick(R.id.iv_switch)
    public void switchFragment(View view) {
        collectJDXFragment.switchFragment(ivSwitch);
    }

    @Override
    public int getTabsArrayId() {
        return R.array.follow_tabs;
    }

}
