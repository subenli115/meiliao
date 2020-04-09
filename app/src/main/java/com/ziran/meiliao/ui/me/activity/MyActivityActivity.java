package com.ziran.meiliao.ui.me.activity;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.ui.me.fragment.MyActivityFragment;

/**
 * 我的活动界面
 * Created by Administrator on 2017/1/4.
 */

public class MyActivityActivity extends BaseActivity {



    @Override
    public int getLayoutId() {
        return R.layout.activity_one_layout;
    }

    @Override
    public void initPresenter() {

    }


    @Override
    public void initView() {
       initFragment(new MyActivityFragment());
    }


}