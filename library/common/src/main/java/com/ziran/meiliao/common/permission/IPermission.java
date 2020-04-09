package com.ziran.meiliao.common.permission;

/**
 *
 * Created by Administrator on 2016/10/21 0021.
 */

interface IPermission {

    void onStart();

    void onResume();

    void onDestroy();

    void showMissingPermissionDialog();

    boolean hasAllPermissionGranted(int[] grantResults);


    void startAppSettings();


    void requestPermissions(String... permission);


    void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults);

}
