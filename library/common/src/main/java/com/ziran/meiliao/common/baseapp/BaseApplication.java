package com.ziran.meiliao.common.baseapp;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.okhttplib.OkHttpUtil;
import com.ziran.meiliao.common.commonutils.SPUtils;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;


/**
 * APPLICATION
 */
public class BaseApplication extends Application {

    private static BaseApplication baseApplication;
    private static boolean sShowDialog;

    public static boolean isShowDialog() {
        return sShowDialog;
    }

    public static void setShowDialog(boolean showDialog) {
        sShowDialog = showDialog;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
        OkHttpClientManager.init(getApplicationContext());
        OkHttpUtil.init(this);
        SPUtils.init(getApplicationContext());
    }
    public static Context getAppContext() {
        return baseApplication;
    }

    public static Context getContext() {
        return baseApplication;
    }

    public static Resources getAppResources() {
        return baseApplication.getResources();
    }

    private static boolean offLine;

    public static boolean offLine() {
        return offLine;
    }

    public static void setOffLine(boolean offLine) {
        BaseApplication.offLine = offLine;
    }
}
