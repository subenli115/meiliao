package com.ziran.meiliao.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonutils.NetWorkUtils;
import com.ziran.meiliao.common.commonutils.SettingUtil;
import com.ziran.meiliao.common.tsnackbar.SnackBarUtil;
import com.wevey.selector.dialog.MDAlertDialog;
import com.wevey.selector.dialog.SimpleDialogClickListener;

/**
 * author 吴祖清
 * create  2017/3/31 10
 * des    检查是否有网络 并且用户是否登录 (1 弹出对话框 2.弹出SnackBar)
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */


public class CheckUtil {
    
    /**
     * 检查是否有网络或者用户已登录
     *
     * @param context 上下文对象
     * @return 检查是否通过
     */
    public static boolean check (final Context context) {
        boolean flag = NetWorkUtils.isNetConnected(context);
        if (!flag) {
            MDAlertDialog.createDialog(context, "提示", "当前网络无连接,是否前往设置打开网络?", "取消", "前往设置", new SimpleDialogClickListener() {
                @Override
                public void clickRightButton (Dialog dialog, View view) {
                    SettingUtil.startSettings(context);
                    dialog.dismiss();
                }
            });
            return false;
        }
        flag = MyAPP.isLogin(context);
        return flag;
        
    }
    /**
     * 版本号比较
     *
     * @param v1
     * @param v2
     * @return 0代表相等，1代表左边大，-1代表右边大
     * Utils.compareVersion("1.0.358_20180820090554","1.0.358_20180820090553")=1
     */
    public static int compareVersion(String v1, String v2) {
        if (v1.equals(v2)) {
            return 0;
        }
        String[] version1Array = v1.split("[._]");
        String[] version2Array = v2.split("[._]");
        int index = 0;
        int minLen = Math.min(version1Array.length, version2Array.length);
        long diff = 0;

        while (index < minLen
                && (diff = Long.parseLong(version1Array[index])
                - Long.parseLong(version2Array[index])) == 0) {
            index++;
        }
        if (diff == 0) {
            for (int i = index; i < version1Array.length; i++) {
                if (Long.parseLong(version1Array[i]) > 0) {
                    return 1;
                }
            }

            for (int i = index; i < version2Array.length; i++) {
                if (Long.parseLong(version2Array[i]) > 0) {
                    return -1;
                }
            }
            return 0;
        } else {
            return diff > 0 ? 1 : -1;
        }
    }


    /**
     * 检查是否有网络或者用户已登录
     *
     * @param context 上下文对象
     * @param view    当前Activity中的控件 最好是顶部的控件
     * @return 是否检查通过
     */
    public static boolean check (final Context context, View view) {
        boolean flag = NetWorkUtils.isNetConnected(context);
        if (!flag) {
            SnackBarUtil.makeWarning(view, "当前网络不可用,请检查网络设置.");
            return false;
        }
        flag = MyAPP.isLogin(context);
        return flag;
        
    }
    /**
     * 检查是否有网络或者用户已登录
     *
     * @param context 上下文对象
     * @param view    当前Activity中的控件 最好是顶部的控件
     * @return 是否检查通过
     */
    public static boolean checkNet (final Context context, View view) {
        boolean flag = NetWorkUtils.isNetConnected(context);
        if (!flag) {
            SnackBarUtil.makeWarning(view, "当前网络不可用,请检查网络设置.");
            return false;
        }
        return true;

    }
    private static final int MIN_DELAY_TIME= 1000;  // 两次点击间隔不能少于1000ms
    private static long lastClickTime;


}
