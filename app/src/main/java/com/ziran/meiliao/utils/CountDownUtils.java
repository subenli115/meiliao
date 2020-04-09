package com.ziran.meiliao.utils;


import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;


public class CountDownUtils {
    public static long day = 0;
    public static long hour = 0;
    public static long minute = 0;
    public static long second = 0;
    public static boolean dayNotAlready = false;
    public static boolean hourNotAlready = false;
    public static boolean minuteNotAlready = false;
    public static boolean secondNotAlready = false;
    private static TextView tvEndTime;
//    public static void main(String[] args) {
//        long totalSeconds = 60 * 60 * 24 * 34 + 21;
//        initData(totalSeconds);
//        new Timer().schedule(new TimerTask() {
//            public void run() {
//                if (secondNotAlready) {
//                    startCount(tvEndTime);
//                } else {
//                    cancel();
//                }
//            }
//        }, 0, 1000);
//    }
    /**
     * 初始化赋值
     *
     * @param totalSeconds
     */
    public static void initData(long totalSeconds) {

        resetData();
        if (totalSeconds > 0) {
            secondNotAlready = true;
            second = totalSeconds;
            if (second >= 60) {
                minuteNotAlready = true;
                minute = second / 60;
                second = second % 60;
                if (minute >= 60) {
                    hourNotAlready = true;
                    hour = minute / 60;
                    minute = minute % 60;
                    if (hour > 24) {
                        dayNotAlready = true;
                        day = hour / 24;
                        hour = hour % 24;
                    }
                }
            }
        }
        System.out.println("初始格式化后——>" + day + "天" + hour + "小时" + minute
                + "分钟" + second + "秒");
    }
    private static void resetData() {
        day = 0;
        hour = 0;
        minute = 0;
        second = 0;
        dayNotAlready = false;
        hourNotAlready = false;
        minuteNotAlready = false;
        secondNotAlready = false;
    }
    /**
     * 计算各个值的变动
     *
     */
    public static void startCount(TextView mTvEndTime, Context context) {
        tvEndTime=mTvEndTime;
        if (secondNotAlready) {
            if (second > 0) {
                second--;
                if (second == 0 && !minuteNotAlready) {
                    secondNotAlready = false;
                }
            } else {
                if (minuteNotAlready) {
                    if (minute > 0) {
                        minute--;
                        second = 59;
                        if (minute == 0 && !hourNotAlready) {
                            minuteNotAlready = false;
                        }
                    } else {
                        if (hourNotAlready) {
                            if (hour > 0) {
                                hour--;
                                minute = 59;
                                second = 59;
                                if (hour == 0 && !dayNotAlready) {
                                    hourNotAlready = false;
                                }
                            } else {
                                if (dayNotAlready) {
                                    day--;
                                    hour = 23;
                                    minute = 59;
                                    second = 59;
                                    if (day == 0) {
                                        dayNotAlready = false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
//        tvEndTime.setText("距结束仅剩"+ TimeUtil.formatData("dd天HH时mm分ss秒", data.getEndTime()));

        Message message = new Message();
        message.what = 1;
        handler.sendMessage(message);
    }

   static Handler handler = new Handler(){

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if(tvEndTime!=null){
                        tvEndTime.setText("距结束仅剩" + day + "天" + hour + "时" + minute
                                + "分" + second + "秒");
                    }
                    break;
            }
            super.handleMessage(msg);
        }

    };

}