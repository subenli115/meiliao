package com.ziran.meiliao.ui.priavteclasses.activity;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.ui.priavteclasses.fragment.ZLFreeFragment;

import rx.functions.Action1;


public class ZLFreeZLActivity extends BaseActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_one_layout;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        mRxManager.on(ApiKey.SPEC_COLUMN_BUY, new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                finish();
            }
        });
        initFragment(new ZLFreeFragment());
    }
}
