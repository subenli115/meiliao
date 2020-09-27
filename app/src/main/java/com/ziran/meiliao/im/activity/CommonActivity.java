package com.ziran.meiliao.im.activity;

import android.content.Context;
import android.content.Intent;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.im.activity.fragment.CommonFragment;

/**
 * 统一列表
 *
 */
public class CommonActivity extends BaseActivity {


    public static void startAction(Context mContext, int type,String userId) {
        Intent intent = new Intent(mContext, CommonActivity.class);
        intent.putExtra("userId",userId);
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
        initFragment(new CommonFragment());
    }

}