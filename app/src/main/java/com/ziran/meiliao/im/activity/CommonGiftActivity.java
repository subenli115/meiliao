package com.ziran.meiliao.im.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.im.activity.fragment.CommonGiftFragment;

import butterknife.Bind;

/**
 * 统一列表
 *
 */
public class CommonGiftActivity extends BaseActivity {
    @Bind(R.id.ntb)
    NormalTitleBar ntb;

    public static void startAction(Context mContext, String type) {
        Intent intent = new Intent(mContext, CommonGiftActivity.class);
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
        initFragment(new CommonGiftFragment());
    }

}