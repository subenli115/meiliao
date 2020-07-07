package com.ziran.meiliao.common.permission;

import android.content.Context;
import android.content.pm.PackageManager;
import androidx.core.content.ContextCompat;

/**
 * 检查权限的工具类
 */
class CheckPermission {

    private final Context context;

    //构造器
    CheckPermission(Context context) {
        this.context = context.getApplicationContext();
    }

    //检查权限时，判断系统的权限集合
    boolean permissionSet(String... permissions) {
        for (String permission : permissions) {
            if (isLackPermission(permission)) {//是否添加完全部权限集合
                return true;
            }
        }
        return false;
    }

    //检查系统权限是，判断当前是否缺少权限(PERMISSION_DENIED:权限是否足够)
    private boolean isLackPermission(String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED;
    }

}
