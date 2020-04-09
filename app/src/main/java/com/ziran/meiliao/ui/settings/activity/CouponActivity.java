package com.ziran.meiliao.ui.settings.activity;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.ui.settings.fragment.CouponFragment;

import butterknife.Bind;

/**
 * 优惠劵界面
 * Created by Administrator on 2017/1/4.
 */

public class CouponActivity extends BaseActivity {
    @Bind(R.id.ntb)
    public NormalTitleBar ntb;



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
        ntb.setTitleText(getString(R.string.coupon));
        initFragment(new CouponFragment());
    }
}
