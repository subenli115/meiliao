package com.ziran.meiliao.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.priavteclasses.activity.VerticalLiveActivity;
import com.ziran.meiliao.utils.HandlerUtil;

/**
 * author admin
 * create  2017/3/27 14
 * des     ${TODO}
 * <p>
 * updateAuthor     $admin
 * updateDate   2017/3/27 14
 * updateDes    ${TODO}
 */

public class HomeService extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (!AppManager.getAppManager().isForeground()) { //显示浮窗
            String courseId = intent.getStringExtra("floatView");
            Intent notificationIntent = new Intent(Intent.ACTION_MAIN);
            if (EmptyUtils.isNotEmpty(courseId)) {
                notificationIntent.putExtra(AppConstant.SPKey.COURSE_ID,courseId);
                notificationIntent.setClass(getBaseContext(), VerticalLiveActivity.class);
            } else { //启动从桌面或其他应用\返回该应用的服务
                notificationIntent.setClass(getBaseContext(), AppManager.getAppManager().currentActivity().getClass());
            }
            notificationIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
            startActivity(notificationIntent);
        }
        HandlerUtil.runMain(new Runnable() {
            @Override
            public void run() {
                stopSelf();
            }
        });
        return super.onStartCommand(intent, flags, startId);
    }
}
