package com.ziran.meiliao.common.baseapp;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.view.View;

import com.ziran.meiliao.common.commonutils.LogUtils;

import java.util.List;
import java.util.Stack;

/**
 * activity管理
 */
public class AppManager {
    private static Stack<Activity> activityStack;
    private volatile static AppManager instance;

    private AppManager() {

    }

    /**
     * 单一实例
     */
    public static AppManager getAppManager() {
        if (instance == null) {
            synchronized (AppManager.class) {
                if (instance == null) {
                    instance = new AppManager();
                    activityStack = new Stack();
                }
            }
        }
        return instance;
    }


    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        try {
            return activityStack.lastElement();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取当前Activity的前一个Activity
     */
    public Activity preActivity() {
        int index = activityStack.size() - 2;
        if (index < 0) {
            return null;
        }
        return activityStack.get(index);
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }


    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 移除指定的Activity
     */
    public void removeActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        try {
            for (Activity activity : activityStack) {
                if (activity.getClass().equals(cls)) {
                    finishActivity(activity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 结束指定类名的Activity
     */
    public Activity getActivityByClassName(String className) {
        try {
            LogUtils.logd("className = " + className + "\n" + activityStack.toString());
            for (Activity activity : activityStack) {
                if (activity.getClass().getSimpleName().equals(className)) {
                    return activity;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 返回到指定的activity
     *
     * @param cls
     */
    public void returnToActivity(Class<?> cls) {
        while (activityStack.size() != 0) if (activityStack.peek().getClass() == cls) {
            break;
        } else {
            finishActivity(activityStack.peek());
        }
    }

    /**
     * 前往activity
     *
     * @param cls
     */
    public void jumpToActivity(Class<?> cls) {
        jumpToActivity(cls, false);
    }

    /**
     * 前往activity
     *
     * @param cls
     */
    public void jumpToActivity(Class<?> cls, Bundle bundle) {
        Activity activity = getAppManager().currentActivity();
        Intent intent = new Intent(activity, cls);
        if (bundle != null) intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    /**
     * 前往activity
     *
     * @param cls
     */
    public void jumpToActivity(Class<?> cls, boolean isReg) {
        Activity activity = getAppManager().currentActivity();
        if (isReg) {
            String simpleName = activity.getClass().getSimpleName();
            if (!cls.getSimpleName().equals(simpleName)) {
                activity.startActivity(new Intent(activity, cls));
                activity.finish();
            }
        } else {
            activity.startActivity(new Intent(activity, cls));
        }

    }


    /**
     * 是否已经打开指定的activity
     *
     * @param cls
     * @return
     */
    public boolean isOpenActivity(Class<?> cls) {
        if (activityStack != null) {
            for (int i = 0, size = activityStack.size(); i < size; i++) {
                if (cls == activityStack.peek().getClass()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 退出应用程序
     *
     * @param context      上下文
     * @param isBackground 是否开开启后台运行
     */
    public void AppExit(Context context, Boolean isBackground) {
        try {
            finishAllActivity();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 注意，如果您有后台程序运行，请不要支持此句子
            if (!isBackground) {
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        }
    }

    /*
    * 判断某个界面是否在前台
    *
            * @param activity 要判断的Activity
    * @return 是否在前台显示
    */
    public boolean isForeground() {
        return isForeground(currentActivity());
    }
    /*
    * 判断某个界面是否在前台
    *
            * @param activity 要判断的Activity
    * @return 是否在前台显示
    */

    public boolean isForeground(Activity activity) {
        return isForeground(activity, activity.getClass().getName());
    }

    /**
     * 判断某个界面是否在前台
     *
     * @param context   Context
     * @param className 界面的类名
     * @return 是否在前台显示
     */
    public boolean isForeground(Context context, String className) {
        if (context == null || TextUtils.isEmpty(className)) return false;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(1);
        if (list != null && list.size() > 0) {
            ComponentName cpn = list.get(0).topActivity;
            if (className.equals(cpn.getClassName())) return true;
        }
        return false;
    }


    /**
     * 获取倒数第二个 Activity
     *
     * @return
     */
    @Nullable
    public Activity getPenultimateActivity() {
        Activity activity = null;
        try {
            if (activityStack.size() > 1) {
                activity = activityStack.get(activityStack.size() - 2);
            }
        } catch (Exception e) {
        }
        return activity;
    }

    public static void onPanelSlide(float slideOffset) {
        try {
            Activity activity = getAppManager().getPenultimateActivity();
            if (activity != null) {
                View decorView = activity.getWindow().getDecorView();
                ViewCompat.setTranslationX(decorView, -(decorView.getMeasuredWidth() / 3.0f) * (1 - slideOffset));
            }
        } catch (Exception e) {
        }
    }

    public static void onPanelClosed() {
        try {
            Activity activity = getAppManager().getPenultimateActivity();
            if (activity != null) {
                View decorView = activity.getWindow().getDecorView();
                ViewCompat.setTranslationX(decorView, 0);
            }
        } catch (Exception e) {
        }
    }
}