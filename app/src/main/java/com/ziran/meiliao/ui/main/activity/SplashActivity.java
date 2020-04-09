package com.ziran.meiliao.ui.main.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;


import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.SPUtils;
import com.ziran.meiliao.common.permission.rom.PermisionUtils;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.main.fragment.GuideFragment;
import com.ziran.meiliao.ui.main.fragment.SplashFragment;
import com.ziran.meiliao.utils.DeviceUtil;


/**
 * des:启动页
 * Created by xsf
 * on 2016.09.15:16
 */
public class SplashActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!this.isTaskRoot() && getIntent() != null) {
            String action = getIntent().getAction();
            if (getIntent().hasCategory(Intent.CATEGORY_LAUNCHER) && Intent.ACTION_MAIN.equals(action)) {
                finish();
            }
        } else {
            setContentView(R.layout.act_splash);
            initView();
        }
    }


    public void initView() {
        String oldVersionCode = SPUtils.getString(AppConstant.VERSION_CODE);
        String newVersionCode = DeviceUtil.getVersionCode(getApplicationContext());
        if (newVersionCode.equals(oldVersionCode)) {
            initFragment(new SplashFragment(), null);
        } else {
            initFragment(new GuideFragment(), null);
            SPUtils.setString(AppConstant.VERSION_CODE, newVersionCode);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        PermisionUtils.verifyStoragePermissions(this);
    }

    public void initFragment(Fragment fragment, Bundle arguments) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (arguments != null) fragment.setArguments(arguments);
        ft.replace(R.id.frameLayout, fragment, fragment.getClass().getSimpleName());
        ft.commit();
    }



}
