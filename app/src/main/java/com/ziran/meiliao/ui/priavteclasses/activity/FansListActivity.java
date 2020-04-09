package com.ziran.meiliao.ui.priavteclasses.activity;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.ui.priavteclasses.fragment.FansListFragment;

import butterknife.Bind;

/**
 *  我的粉丝界面
 */
public class FansListActivity extends BaseActivity {


    @Bind(R.id.ntb)
    NormalTitleBar ntb;

    @Override
    public int getLayoutId() {
        return R.layout.activity_common_frame;
    }
    @Override
    public void initPresenter() {

    }
    @Override
    public void initView() {
        ntb.setTitleText("粉丝");
        ntb.setTvLeftVisiable(true,true);
        initFragment(new FansListFragment());
    }


}
