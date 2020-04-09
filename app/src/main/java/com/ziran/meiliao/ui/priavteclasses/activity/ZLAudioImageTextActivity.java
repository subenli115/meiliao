package com.ziran.meiliao.ui.priavteclasses.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ziran.meiliao.R;
import com.ziran.meiliao.ui.base.ShareActivity;
import com.ziran.meiliao.ui.priavteclasses.fragment.ZLAudioImageTextFragment;


public class ZLAudioImageTextActivity extends ShareActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_one_layout;
    }

    @Override
    public void initPresenter() {

    }
    private String typeId;

    @Override
    protected void initBundle(Bundle extras) {
        typeId = extras.getString("typeId");
    }

    @Override
    public void initView() {
        initFragment(new ZLAudioImageTextFragment());

    }

    public static void startAction(Context context,Bundle bundle) {
        Intent intent = new Intent(context, ZLAudioImageTextActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }


}
