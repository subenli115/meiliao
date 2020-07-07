package com.ziran.meiliao.ui.base;


import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseFragment;
import com.ziran.meiliao.common.base.BaseFragmentAdapter;
import com.ziran.meiliao.common.commonutils.ArrayUtils;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.commonwidget.ViewPagerFixed;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/4 14:06
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/4$
 * @updateDes ${TODO}
 */

public abstract class CommonViewPagerFragment extends BaseFragment {
    @Bind(R.id.ntb)
  protected NormalTitleBar ntb;
    @Bind(R.id.tab_layout)
    TabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPagerFixed viewPager;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_common_viewpager;
    }

    @Override
    public void initPresenter() {

    }

    protected List<Fragment> mFragments;

    @Override
    protected void initView() {
        mFragments = new ArrayList<>();
        initFragments(mFragments);
        viewPager.setAdapter(new BaseFragmentAdapter(getChildFragmentManager(), mFragments));
        tabLayout.setupWithViewPager(viewPager);
        ViewUtil.changeTabText(tabLayout, ArrayUtils.getArray(getContext(), getArrayResId()));
    }

    protected abstract void initFragments( List<Fragment>  fragments);

    protected abstract int getArrayResId();
}
