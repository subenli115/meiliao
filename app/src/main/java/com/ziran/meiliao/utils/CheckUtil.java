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

}
