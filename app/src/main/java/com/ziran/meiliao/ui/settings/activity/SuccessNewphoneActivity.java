package com.ziran.meiliao.ui.settings.activity;

import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import butterknife.OnClick;

public class SuccessNewphoneActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.ac_success_newphone;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {

    }


    //点击监听
    @OnClick({R.id.tv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                //提交
                break;
        }
    }
}
