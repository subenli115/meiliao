package com.ziran.meiliao.ui.base;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.permission.PermissionManager;
import com.ziran.meiliao.im.activity.LoginActivity;
import com.ziran.meiliao.im.activity.MainActivity;
import com.ziran.meiliao.im.utils.DialogCreator;
import com.ziran.meiliao.im.utils.FileHelper;
import com.ziran.meiliao.im.utils.SharePreferenceManager;
import com.ziran.meiliao.im.utils.dialog.LoadDialog;
import com.ziran.meiliao.ui.main.activity.SplashActivity;
import com.ziran.meiliao.ui.main.contract.LoginContract;
import com.ziran.meiliao.ui.main.model.LoginModel;
import com.ziran.meiliao.ui.main.presenter.LoginPresenter;

import java.io.File;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.event.LoginStateChangeEvent;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;


/**
 * author 吴祖清
 * create  2017/2/22
 * des     获取权限的Acitivity
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */


public abstract class PermissionActivity<L extends LoginContract.Presenter, L1> extends BaseActivity<LoginPresenter, LoginModel> implements PermissionManager.AccessToSuccess {
    /**
     * 获取全选的管理类
     */
    protected PermissionManager permissionManager;
    private Dialog dialog;
    private int mWidth;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        mWidth = dm.widthPixels;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            permissionManager = new PermissionManager(this);
            permissionManager.setmAccessToSuccess(this);
        }
    }




    //获取权限成功回调
    @Override
    public void onSuccess () {
        
    }
    
    @Override
    protected void onResume () {
        super.onResume();
        if (permissionManager != null) {
            permissionManager.onResume();
        }
    }
    
    @Override
    protected void onDestroy () {
        super.onDestroy();
        if (dialog != null) {
            dialog.dismiss();
        }
        if (permissionManager != null) {
            permissionManager.onDestroy();
        }
    }



    @Override
    public void onRequestPermissionsResult (int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissionManager != null) {
            permissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    
    protected void setPermission (String... permission) {
        if (permissionManager != null && EmptyUtils.isNotEmpty(permission)) {
            permissionManager.setPermission(permission);
        }
    }
}
