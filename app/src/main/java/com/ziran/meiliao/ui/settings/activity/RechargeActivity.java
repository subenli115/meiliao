package com.ziran.meiliao.ui.settings.activity;

import android.content.Context;
import android.content.Intent;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.settings.fragment.RechargeFragment;

import butterknife.Bind;

/**
 *  充值界面Activity
 * Created by Administrator on 2017/1/4.
 */

public class RechargeActivity extends BaseActivity {
    @Bind(R.id.ntb)
    public NormalTitleBar ntb;

    public static void startAction(Context mContext,String balance) {
        Intent intent = new Intent(mContext, RechargeActivity.class);
        intent.putExtra(AppConstant.ExtraKey.BALANCE, balance);
        mContext.startActivity(intent);
    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
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
        ntb.setTitleText(getString(R.string.recharge));
        initFragment(new RechargeFragment());
    }
}
