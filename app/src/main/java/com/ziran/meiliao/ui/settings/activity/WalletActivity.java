package com.ziran.meiliao.ui.settings.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.ui.settings.fragment.WalletFragment;

import butterknife.Bind;

/**
 * 正念币余额界面
 */

public class WalletActivity extends BaseActivity {
    @Bind(R.id.ntb)
    public NormalTitleBar ntb;

    public static void startAction(Context mContext) {
        Intent intent = new Intent(mContext, WalletActivity.class);
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
        ntb.setTitleText(getString(R.string.get_goal));
        ntb.setRightTitle("明细");
        ntb.setOnRightTextListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(WalletDetailActivity.class);
            }
        });
        initFragment(new WalletFragment());
    }
}
