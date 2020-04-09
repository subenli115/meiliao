package com.ziran.meiliao.ui.settings.activity;

import android.content.Context;
import android.content.Intent;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.ui.settings.fragment.CouponFragment;

import butterknife.Bind;

/**
 * 优惠劵界面
 * Created by Administrator on 2017/1/4.
 */

public class RecommentPosterActivity extends BaseActivity {
    @Bind(R.id.ntb)
    public NormalTitleBar ntb;

    public static void startAction(Context mContext) {
        Intent intent = new Intent(mContext, RecommentPosterActivity.class);
        mContext.startActivity(intent);
    }

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
