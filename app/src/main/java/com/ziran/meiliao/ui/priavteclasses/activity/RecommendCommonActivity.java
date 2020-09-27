package com.ziran.meiliao.ui.priavteclasses.activity;

import android.content.Context;
import android.content.Intent;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.im.activity.fragment.CommonFragment;
import com.ziran.meiliao.ui.priavteclasses.fragment.RecommendCommonFragment;

/**
 * 统一列表
 *
 */
public class RecommendCommonActivity extends BaseActivity {


    public static void startAction(Context mContext, int type) {
        Intent intent = new Intent(mContext, RecommendCommonActivity.class);
        intent.putExtra("type",type);
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
        initFragment(new RecommendCommonFragment());
    }

}