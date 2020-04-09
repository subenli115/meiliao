package com.ziran.meiliao.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.app.WpyxConfig;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.service.UpdateVersionDownloadService;
import com.ziran.meiliao.ui.priavteclasses.util.TrailerCollectConfig;

import java.util.List;


/**
 * author 吴祖清
 * create  2017/3/31 10
 * des     退出应用的工具类
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */


public class ExitUtil {


    /**
     * 退出APP释放资源
     *
     * @param context 上下文对象
     */
    public static void exit(Context context) {
        MyAPP.setOffLine(true);
        UpdateVersionDownloadService.stopService(context);
        RxManagerUtil.clear();
        TrailerCollectConfig.clear();
        UMengKit.trackMsgDismissed(context);
        WpyxConfig.setMusicPanelHide(false);
        MyAPP.mServiceManager.exit();
        HandlerUtil.onDestroy();
        PracticeDataUtil.onDestroy();
        AppManager.getAppManager().AppExit(context, false);
    }

    /**
     * @param context 上下文对象
     * @return 是否需要退出APP
     */
    public static boolean keyBack(Activity context) {
        return keyBack(context, null);
    }

    /**
     * 在3秒内点两次back按钮退出应用
     *
     * @param context        上下文对象
     * @param onExitCallBack 确定退出回调
     * @return 是否需要退出APP
     */
    public static boolean keyBack(Activity context, OnExitCallBack onExitCallBack) {
        if ((System.currentTimeMillis() - exitTime) > 3000) {
            ToastUitl.showShort(context.getString(R.string.action_up_algin_exit));
            exitTime = System.currentTimeMillis();
        } else {
            if (onExitCallBack != null) {
                onExitCallBack.exit(context);
            }
        }
        return true;
    }

    //退出回调
    public interface OnExitCallBack {
        void exit(Activity activity);
    }

    /**
     * 最后一次按下back建的时间
     */
    private static long exitTime = 0;


    /**
     * 返回app运行状态
     * 1:程序在前台运行
     * 2:程序在后台运行
     * 3:程序未启动
     * 注意：需要配置权限<uses-permission android:name="android.permission.GET_TASKS" />
     */
    public static int getAppSatus(Context context, String pageName) {

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(30);

        //判断程序是否在栈顶
        if (list.get(0).topActivity.getPackageName().equals(pageName)) {
            return 1;
        } else {
            //判断程序是否在栈里
            for (ActivityManager.RunningTaskInfo info : list) {
                if (info.topActivity.getPackageName().equals(pageName)) {
                    return 2;
                }
            }
            return 3;//栈里找不到，返回3
        }
    }
    /**
     * 返回app运行状态
     * 1:程序在前台运行
     * 2:程序在后台运行
     * 3:程序未启动
     * 注意：需要配置权限<uses-permission android:name="android.permission.GET_TASKS" />
     */
    public static int getAppSatus(Context context) {
        String pageName = context.getPackageName();
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(30);

        //判断程序是否在栈顶
        if (list.get(0).topActivity.getPackageName().equals(pageName)) {
            return 1;
        } else {
            //判断程序是否在栈里
            for (ActivityManager.RunningTaskInfo info : list) {
                if (info.topActivity.getPackageName().equals(pageName)) {
                    return 2;
                }
            }
            return 3;//栈里找不到，返回3
        }
    }
}
