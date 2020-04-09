package com.ziran.meiliao.ui.settings.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.base.BaseFragmentAdapter;
import com.ziran.meiliao.common.commonutils.ArrayUtils;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.commonwidget.ViewPagerFixed;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.ui.adapter.OneSlideAdapter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.settings.fragment.DeleteRefreshFragment;
import com.ziran.meiliao.utils.AnimationUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/2/10.
 */

public abstract class CommonDeleteActivity extends BaseActivity {
    
    @Bind(R.id.ntb)
    public NormalTitleBar ntb;
    @Bind(R.id.tab_layout)
    public TabLayout tabLayout;
    @Bind(R.id.viewpager)
    public ViewPagerFixed viewPager;
    @Bind(R.id.linearLayout)
    public View linearLayout;
    @Bind(R.id.btnSelect)
    public Button btnSel;
    @Bind(R.id.btnDelete)
    protected Button btnDelete;
    private AnimationUtil animationUtil1;
    protected DeleteRefreshFragment currFragment;
    
    private static final int NORMAL = 0;
    private static final int CANCEL = 1;
    public int currState = NORMAL;
    protected List<Fragment> fragments;
    
    @Override
    public int getLayoutId () {
        return R.layout.activity_tag_delete;
    }
    
    
    @Override
    public void initPresenter () {
        
    }

    @Override
    public void initView () {
        ntb.setTvLeftVisiable(true, false);
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                checkEditDelete();
            }
        });
        setRightText();
        String[] tabs = ArrayUtils.getArray(this, getTabsArrayId());
        if (EmptyUtils.isEmpty(fragments)) {
            fragments = new ArrayList<>();
            initFragments(fragments);
            viewPager.setAdapter(new BaseFragmentAdapter(getSupportFragmentManager(), fragments));
            viewPager.setOffscreenPageLimit(fragments.size());
            tabLayout.setupWithViewPager(viewPager);
            ViewUtil.changeTabText(tabLayout, tabs);
//            ViewUtil.changeTabText(tabLayout, tabs);
        }
//        initCanTouchListener(viewPager);
    }
    
    public void setRightText () {
        ntb.setRightTitle(getString(R.string.delete_act_edit));
        ntb.setOnRightTextListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                changeState();
            }
        });
        mRxManager.on("updateSel", new Action1<Integer>() {
            @Override
            public void call (Integer count) {
                String string = "全选";
                if (OneSlideAdapter.DeleteItem.isSelectAll()) {
                    string = ("取消全选 (" + count + " )");
                } else if (count > 0) {
                    string = (string + " (" + count + " )");
                }
                btnSel.setText(string);
            }
        });
        mRxManager.on("close", new Action1<Object>() {
            @Override
            public void call (Object o) {
                btnSel.setText(R.string.delete_act_sellect);
                ntb.setRightTitle(getString(R.string.delete_act_edit));
                if (currFragment!=null){
                    currFragment.setCheck(false);
                }
                currState = NORMAL;
                setEnabledView(true);
                linearLayout.setAnimation(animationUtil1.moveToViewBottom());
            }
        });
        if (animationUtil1 == null) {
            animationUtil1 = new com.ziran.meiliao.utils.AnimationUtil(linearLayout);
        }
    }
    
    protected abstract void initFragments (List<Fragment> fragments);
    
    public abstract int getTabsArrayId ();
    
    
    public void changeState () {
        Fragment fragment1 = fragments.get(tabLayout.getSelectedTabPosition());
        if (getString(R.string.delete_act_edit).equals(ntb.getRightText())) {

            if (fragment1 instanceof CommonRefreshFragment){
                CommonRefreshFragment fragment = (CommonRefreshFragment)fragment1 ;
                if (fragment.getItemCount() == 0) {
                    ToastUitl.showLong(getString(R.string.not_data));
                    return;
                }
            }
        }
        if (fragment1 instanceof CommonRefreshFragment){
            currFragment = (DeleteRefreshFragment) fragments.get(tabLayout.getSelectedTabPosition());
        }else{
            currFragment = getCurrFragment(tabLayout.getSelectedTabPosition());
        }
        currFragment.toggle();
        linearLayout.clearAnimation();
        setEnabledView(currState != NORMAL);
        if (currState == NORMAL) {
            currState = CANCEL;
            linearLayout.setAnimation(animationUtil1.moveToViewLocation());
        } else {
            btnSel.setText(R.string.delete_act_sellect);
            currState = NORMAL;
            linearLayout.setAnimation(animationUtil1.moveToViewBottom());
        }
        ntb.setRightToggle();
    }

    protected DeleteRefreshFragment getCurrFragment(int position) {
        return null;
    }

    public void setEnabledView (boolean enabled) {
        viewPager.setScanScroll(enabled);
        LinearLayout tabStrip = (LinearLayout) tabLayout.getChildAt(0);
        for (int i = 0; i < tabStrip.getChildCount(); i++) {
            View tabView = tabStrip.getChildAt(i);
            if (tabView != null) {
                tabView.setClickable(enabled);
            }
        }
    }
    
    @OnClick({R.id.btnSelect, R.id.btnDelete})
    public void onClick (View view) {
        if (currFragment == null)
            return;
        switch (view.getId()) {
            case R.id.btnSelect:
                currFragment.selectAll();
                break;
            case R.id.btnDelete:
                TabLayout.Tab tabAt = tabLayout.getTabAt(tabLayout.getSelectedTabPosition());
                if (tabAt != null && tabAt.getText() != null) {
                    currFragment.delete(tips,tabAt.getText().toString());
                }
                break;
        }
    }
    protected String tips;
    @Override
    public void onBackPressed () {
        checkEditDelete();
    }
    
    private void checkEditDelete () {
        if ("取消".equals(ntb.getRightText())) {
            mRxManager.post("close", 1);
        } else {
            super.onBackPressed();
        }
    }
}
