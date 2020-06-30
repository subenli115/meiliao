package com.ziran.meiliao.im.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.im.activity.fragment.WechatFragment;

/**
 * 动态列表
 *
 */
public class WechatActivity extends BaseActivity {


    public static void startAction(Context mContext, String homepageImages, String avatar, String userId, boolean mIsSelf) {
        Intent intent = new Intent(mContext, WechatActivity.class);
        intent.putExtra("homepageImages",homepageImages);
        intent.putExtra("avatar",avatar);
        intent.putExtra("userId",userId);
        if(mIsSelf){
            intent.putExtra("isSelf","1");
        }else {
            intent.putExtra("isSelf","2");
        }
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
        initFragment(new WechatFragment());
    }

}