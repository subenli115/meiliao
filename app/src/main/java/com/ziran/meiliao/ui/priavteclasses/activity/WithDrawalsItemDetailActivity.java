package com.ziran.meiliao.ui.priavteclasses.activity;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.ui.priavteclasses.fragment.WithDrawalsItemDetailFragment;


/**
 * 单个提现明细界面
 */
public class WithDrawalsItemDetailActivity extends BaseActivity {



    @Override
    public int getLayoutId() {
        return R.layout.activity_one_layout;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        initFragment(new WithDrawalsItemDetailFragment());
    }

}
