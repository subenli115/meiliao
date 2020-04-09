package com.ziran.meiliao.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.decompressionmuseum.activity.MindfulnessHallActivity;
import com.ziran.meiliao.utils.AlarmUtil;
import com.ziran.meiliao.utils.MusicPanelFloatManager;
import com.ziran.meiliao.utils.NotificationUtil;


/**
 *  设置闹钟处理服务
 */
public class AlarmNotifyService extends Service {

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
    public int onStartCommand(Intent intent, int flags, int startId) {
        int from = intent.getIntExtra(AppConstant.ExtraKey.FROM_TYPE, -1);

        switch (from) {
            case AlarmUtil.FROM_COUNT_TIME: //专辑倒计时
                MusicPanelFloatManager.getInstance().res(true);
                RxManagerUtil.post(AppConstant.RXTag.ALBUM_COUNT_DOWN_TIME, -1L);
                break;
            case AlarmUtil.FROM_NPTIFY_MO:      //每日正念练习
            case AlarmUtil.FROM_NPTIFY_NOON:
            case AlarmUtil.FROM_NPTIFY_NIGHT:
                Intent intendPending = new Intent(this, MindfulnessHallActivity.class);
                if (AppManager.getAppManager().isForeground()){
                    intendPending.addCategory(Intent.CATEGORY_LAUNCHER);
                    intendPending.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                }
                NotificationUtil.showNotification(this,"温馨提示","您设定的正念练习时间到了",intendPending);
                break;
        }
        return super.onStartCommand(intent, flags, startId);
    }
}
