package com.ziran.meiliao.ui.main.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.im.activity.WechatActivity;
import com.ziran.meiliao.im.utils.keyboard.utils.EmoticonsKeyboardUtils;
import com.ziran.meiliao.ui.main.fragment.DynamicAllAudioOneFragment;

import butterknife.Bind;

/**
 */

public class DynamicDeatilsActivity extends BaseActivity {
    @Bind(R.id.ntb)
    public NormalTitleBar ntb;

    @Override
    public int getLayoutId() {
        return R.layout.activity_common_frame;
    }

    @Override
    public void initPresenter() {

    }

    public static void startAction(Context mContext,String spaceId) {
        Intent intent = new Intent(mContext, DynamicDeatilsActivity.class);
        intent.putExtra("spaceId",spaceId);
        mContext.startActivity(intent);
    }


    @Override
    public void initView() {
        initFragment(new DynamicAllAudioOneFragment());
    }
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (EmoticonsKeyboardUtils.isFullScreen(this)) {
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

}