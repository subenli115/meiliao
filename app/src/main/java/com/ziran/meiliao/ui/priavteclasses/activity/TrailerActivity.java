package com.ziran.meiliao.ui.priavteclasses.activity;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.ui.priavteclasses.fragment.TrailerFragment;

/**
 * 预告H5页面
 * Created by Administrator on 2017/3/2.
 */

public class TrailerActivity extends BaseActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_common_frame;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        initFragment(new TrailerFragment());
    }
}
