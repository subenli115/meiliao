package com.ziran.meiliao.common.permission;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

/**
 * Created by Administrator on 2016/10/21 0021.
 */

public class PermissionUtil {

    public static String[] getWriteAndReadExternalStorage() {
        return new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
        };
    }
    public static String[] getSMS() {
        return new String[]{
                Manifest.permission.RECEIVE_SMS,
                Manifest.permission.READ_SMS,
                Manifest.permission.SEND_SMS,
        };
    }

    public static String[] getAllPermission() {
        return new String[]{
//                Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                Manifest.permission.READ_EXTERNAL_STORAGE,
//                Manifest.permission.SYSTEM_ALERT_WINDOW,
////                Manifest.permission.SET_ALARM,
//                Manifest.permission.CAMERA,
////                Manifest.permission.CALL_PHONE,
//                Manifest.permission.ACCESS_FINE_LOCATION,
////                Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS,
//                Manifest.permission.ACCESS_COARSE_LOCATION,
//                Manifest.permission.ACCESS_NETWORK_STATE,
//                Manifest.permission.ACCESS_WIFI_STATE,
//                Manifest.permission.READ_PHONE_STATE,
//                Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,
        };
    }
    //  无法拿到权限
    //  Manifest.permission.SYSTEM_ALERT_WINDOW,
    //   Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS,

    public static void requstSettingPermission(Activity context, int reqsuteCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.System.canWrite(context)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS,
                        Uri.parse("package:" + context.getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivityForResult(intent, reqsuteCode);
            }
        }
    }
}