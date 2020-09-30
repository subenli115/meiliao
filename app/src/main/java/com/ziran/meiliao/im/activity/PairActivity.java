package com.ziran.meiliao.im.activity;

import android.content.Context;
import android.content.Intent;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.im.activity.fragment.PairFragment;

/**
 * 统一列表
 *
 */
public class PairActivity extends BaseActivity {


    public static void startAction(Context mContext) {
        Intent intent = new Intent(mContext, PairActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getSupportFragmentManager().getFragments().get(0).onActivityResult(requestCode, resultCode, data);
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
        initFragment(new PairFragment());
    }

}