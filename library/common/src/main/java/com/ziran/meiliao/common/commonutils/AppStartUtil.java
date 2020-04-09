package com.ziran.meiliao.common.commonutils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

/**
 * author 吴祖清
 * create  2017/3/31 10
 * des     启动第三方APP的工具类
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */

public class AppStartUtil {
    /**
     * 启动APP
     *
     * @param context  上下文对象
     * @param pageName 需要启动APP的包名
     * @param cls      需要启动的Activity
     */
    public static void startApp (Context context, String pageName, String cls) {
        Intent intent = new Intent();
        ComponentName cmp = new ComponentName(pageName, cls);
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setComponent(cmp);
        context.startActivity(intent);//启动
        //        Intent mIntent = new Intent("android.intent.action.MAIN");
        //        ComponentName comp = new ComponentName("你要启动app的包命，如：com.android.app", "你要启动app的首页，如：com.android.app.MainAcrivity");
        //        mIntent.setComponent(comp);
        //        mIntent.addCategory("android.intent.category.LAUNCHER");
        //        context.startActivity(mIntent);//启动
    }
    
    /**
     * 启动微信APP
     *
     * @param context 上下文对象
     */
    public static void startWechat (Context context) {
        startApp(context, "com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
    }
}
