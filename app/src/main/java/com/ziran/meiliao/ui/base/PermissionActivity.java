package com.ziran.meiliao.ui.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.permission.PermissionManager;


/**
 * author 吴祖清
 * create  2017/2/22
 * des     获取权限的Acitivity
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */


public abstract class PermissionActivity extends BaseActivity implements PermissionManager.AccessToSuccess {
    /**
     * 获取全选的管理类
     */
    protected PermissionManager permissionManager;
    
    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
