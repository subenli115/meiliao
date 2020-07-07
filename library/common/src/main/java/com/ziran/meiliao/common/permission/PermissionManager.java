package com.ziran.meiliao.common.permission;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.ziran.meiliao.common.commonutils.LogUtils;


/**
 *
 * Created by Administrator on 2016/10/21 0021.
 */

public class PermissionManager implements IPermission{

    //首先声明权限授权
    public static final int PERMISSION_DENIEG = 1;//权限不足，权限被拒绝的时候

    public static final int PERMISSION_REQUEST_CODE = 0;//系统授权管理页面时的结果参数

    public static final String PACKAGE_URL_SCHEME = "package:";//权限方案

    public CheckPermission checkPermission;//检测权限类的权限检测器

    private boolean isrequestCheck = true;//判断是否需要系统权限检测。防止和系统提示框重叠
    /**
     * 本应用需要的用户权限
     */
    private   String[] permission;
    /**
     *  需要请求权限的界面
     */
    private Activity mActivity;

    /**
     *  请求权限成功后的接口
     */
    private AccessToSuccess mAccessToSuccess;


    public PermissionManager(Activity activity){
        this.mActivity = activity;
        checkPermission = new CheckPermission(activity);
    }

    public PermissionManager(Fragment fragment){
        this(fragment.getActivity());
    }


    public void onStart(){
        if (permission != null) {

            if (checkPermission.permissionSet(permission)) {
                requestPermissions(permission);     //去请求权限
            } else {
                //获取全部权限,走正常业务
                if (mAccessToSuccess!= null){
                    mAccessToSuccess.onSuccess();
                }
            }
        }else{
           LogUtils.logd("Not Permission to reqsues");
        }
    }
    public void onDestroy(){
        checkPermission = null;
        mAccessToSuccess = null;
    }
    public void onResume(){
        //根据activity生命周期，onRestart()->onResume()
        //此处表示从系统设置页面返回后，检查用户是否将所需的权限打开
        if (!isrequestCheck) {
            if (permission != null) {
                if (checkPermission.permissionSet(permission)) {
                    showMissingPermissionDialog();//dialog
                } else {
                    //获取全部权限,走正常业务
                  if (mAccessToSuccess!= null){
                      mAccessToSuccess.onSuccess();
                  }
                }
            }
        } else {
            isrequestCheck = true;
        }
    }
    //显示对话框提示用户缺少权限
    public void showMissingPermissionDialog() {
//
//        final AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
//        builder.setTitle(R.string.help);//提示帮助
//        builder.setMessage(R.string.string_help_text);
//
//        //如果是拒绝授权，则退出应用
//        //退出
//        builder.setNegativeButton(R.string.quit, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//                dialog.dismiss();
//            }
//        });
//
//        //打开设置，让用户选择打开权限
//        builder.setPositiveButton(R.string.settings, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                startAppSettings();//打开设置
//            }
//        });
//        builder.setCancelable(false);
//        builder.show();
    }

    //获取全部权限
    public boolean hasAllPermissionGranted(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }

    //打开系统应用设置(ACTION_APPLICATION_DETAILS_SETTINGS:系统设置权限)
    public void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse(PACKAGE_URL_SCHEME +mActivity. getPackageName()));
       mActivity. startActivity(intent);
    }

    //请求权限去兼容版本
    public void requestPermissions(String... permission) {
        ActivityCompat.requestPermissions(mActivity, permission, PERMISSION_REQUEST_CODE);

    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        if (PERMISSION_REQUEST_CODE == requestCode && hasAllPermissionGranted(grantResults)) //判断请求码与请求结果是否一致
        {
            isrequestCheck = true;//需要检测权限，直接进入，否则提示对话框进行设置
            if (mAccessToSuccess !=null){
                mAccessToSuccess.onSuccess();
            }
        } else {
            //提示对话框设置
            isrequestCheck = false;
            showMissingPermissionDialog();//dialog
        }
    }

    public String[] getPermission() {
        return permission;
    }

    public void setPermission(String... permission) {
        this.permission = permission;
        onStart();
    }

    public void setPermission(boolean isStart,String... permission) {
        this.permission = permission;
        if (isStart)
        onStart();
    }

    public boolean isrequestCheck() {
        return isrequestCheck;
    }
    //是否需要请求
    public void setIsrequestCheck(boolean isrequestCheck) {
        this.isrequestCheck = isrequestCheck;
    }

    /**
     * 设置当请求成功后回调的接口
     * @param mAccessToSuccess  当请求成功后回调的接口
     */
    public void setmAccessToSuccess(AccessToSuccess mAccessToSuccess) {
        this.mAccessToSuccess = mAccessToSuccess;
    }

    public interface AccessToSuccess{
        /*
        * 当获取到所需权限后，进行相关业务操作
         */
        void onSuccess();
    }

}
