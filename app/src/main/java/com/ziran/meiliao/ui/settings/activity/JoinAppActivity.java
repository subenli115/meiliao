package com.ziran.meiliao.ui.settings.activity;

import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.ui.settings.fragment.JoinAppFragment;
import com.ziran.meiliao.ui.settings.fragment.MyVipFragment;

import butterknife.Bind;

/**
 *  我的VIP界面
 */

public class JoinAppActivity extends BaseActivity {

    @Bind(R.id.ntb)
    public NormalTitleBar ntb;

    @Bind(R.id.frameLayout)
    public View frameLayout;

    @Override
    public int getLayoutId() {
        return R.layout.activity_common_frame;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        ntb.setTvLeftVisiable(true, true);
        frameLayout.setVisibility(View.INVISIBLE);
        checkInitFragment(MyAPP.isVip());
    }

    public void checkInitFragment(boolean isVip) {
        if (isVip) {
            ntb.setTitleText(getString(R.string.myvip));
            initFragment(new MyVipFragment());
            frameLayout.setVisibility(View.VISIBLE);
        } else {
            startProgressDialog("加载中...");
            ntb.setTitleText(getString(R.string.payment));
            initFragment(new JoinAppFragment());
        }
    }

    public void show(int vis){
        frameLayout.setVisibility(vis);
        stopProgressDialog();
    }
}
