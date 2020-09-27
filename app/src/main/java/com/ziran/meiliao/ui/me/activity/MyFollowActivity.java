package com.ziran.meiliao.ui.me.activity;

import android.content.Context;
import android.content.Intent;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.ui.settings.fragment.MyFollowFragment;

import butterknife.Bind;

/**
 * 关注列表
 * Created by Administrator on 2017/1/4.
 */

public class MyFollowActivity extends BaseActivity {
    @Bind(R.id.ntb)
    public NormalTitleBar ntb;

    public static void startAction(Context mContext) {
        Intent intent = new Intent(mContext, MyFollowActivity.class);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getSupportFragmentManager().getFragments().get(0).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void initView() {
        initFragment(new MyFollowFragment());
    }
}