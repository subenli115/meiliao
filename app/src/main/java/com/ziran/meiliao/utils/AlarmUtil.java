package com.ziran.meiliao.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.commonutils.SPUtils;
import com.ziran.meiliao.common.commonutils.TimeUtil;
import com.ziran.meiliao.constant.AppConstant;

import java.util.Calendar;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/28 14:16
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/8/28$
 * @updateDes ${TODO}
 */

public class AlarmUtil {

    private static final String ALARM_ACTION = "wzq.com.dingdingalarm.STOP_PLAYER";
    public static final int FROM_COUNT_TIME = 0;
    public static final int FROM_NPTIFY_MO = 1;
    public static final int FROM_NPTIFY_NOON = 2;
    public static final int FROM_NPTIFY_NIGHT = 3;

    /**
     *  设置一个倒计时的闹钟,不需要显示进度
     * @param context       上下文对象
     * @param minute        距离到时分钟提醒
     */
    public static void setAlarm(Context context, int minute) {
        AlarmManager alarms = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        int alarmType = AlarmManager.RTC_WAKEUP;
        //设定一个五秒后的时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.MINUTE, minute);
        Intent intentToFire = new Intent(ALARM_ACTION);
        intentToFire.putExtra(AppConstant.ExtraKey.FROM_TYPE, FROM_COUNT_TIME);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intentToFire, 0);
        alarms.cancel(pendingIntent);
        alarms.set(alarmType, calendar.getTimeInMillis(), pendingIntent);
        SPUtils.setLong(AppConstant.ExtraKey.COUNT_DOWN_STOP,calendar.getTimeInMillis());
    }
    private   AlarmManager alarms;


    /**
     *  根据具体的时间设置闹钟
     * @param context   上下文对象
     * @param day       多少号
     * @param hour      多少小时
     * @param minute    多少分钟
     * @param second    多少秒
     * @param fromType  设置的来源
     */
    public  void setAlarm(Context context, int day, int hour, int minute, int second, int fromType) {
        //设定一个五秒后的时间
        Calendar calendar = Calendar.getInstance();
        day = day >0 ? day :  calendar.get(Calendar.DATE);
        if (hour > -1) {
            int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
            if (hourOfDay>hour ){
                day++;
            }
            calendar.set(Calendar.HOUR_OF_DAY, hour);
        }
        if (alarms==null){
            alarms = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        }
        minute = minute>-1? minute:0;
        second = second>-1? second:0;
        calendar.set(Calendar.DATE, day);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        int alarmType = AlarmManager.RTC_WAKEUP;
        Intent intentToFire = new Intent(ALARM_ACTION);
        intentToFire.putExtra(AppConstant.ExtraKey.FROM_TYPE, fromType);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intentToFire, 0);
        alarms.cancel(pendingIntent);
        alarms.set(alarmType, calendar.getTimeInMillis(), pendingIntent);
        LogUtils.logd("DailyMindUtil"+ TimeUtil.getStringByFormat(calendar.getTimeInMillis(),"yyyy-MM-dd HH:mm:ss"));
    }


}
