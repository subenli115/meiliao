package com.ziran.meiliao.utils;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.ziran.meiliao.common.commonutils.ToastUitl;

import org.apache.http.conn.ConnectTimeoutException;

import java.net.ConnectException;

/**
 * author 吴祖清
 * create  2017/3/31 10
 * des      开辟任务(工作)线程的工具类
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */

public class HandlerUtil {
    private static Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 100:
                    ToastUitl.showShort(msg.obj.toString());
                    break;
                case 300:
                    exDjs(msg);
                    break;
            }
            return false;
        }
    });

    private static void exDjs(Message msg) {
        if (tvDjs==null) return;
        if (msg.arg1 == 0) {
            tvDjs.setEnabled(true);
            tvDjs.setText("重获验证码");
            return;
        }
        int  second = msg.arg1;
        tvDjs.setText(HtmlUtil.format("%ds", second));
        handler.sendMessageDelayed(obj(300,--second),1000);
    }

    //运行子线程
    public static void runTask(Runnable runnable) {
        if (runnable != null)
            new Thread(runnable).start();
    }

    //运行主线程
    public static void runMain(Runnable runnable) {
        runMain(runnable, 1000);
    }

    //运行主线程
    public static void runMain(Runnable runnable, long delayMillis) {
        handler.postDelayed(runnable, delayMillis);
    }
    public static void remove(Runnable runnable){
        handler.removeCallbacks(runnable);
    }
    public static String getExceptionMessage(Exception e) {
        if (e instanceof ConnectException || e instanceof ConnectTimeoutException) {
            return "服务器繁忙,请稍后重试";
        }
        return e.getMessage();
    }

    /**
     *  生成一个Message对象
     * @param what
     * @param arg1
     * @param arg2
     * @param obj
     * @return
     */
    public static Message obj(int what, int arg1, int arg2, Object obj) {
        Message msg =handler.obtainMessage();
        msg.what = what;
        msg.arg1 = arg1;
        msg.arg2 = arg2;
        msg.obj = obj;
        return msg;
    }

    public static Message obj(int what, Object obj) {
        return obj(what, 0, 0, obj);
    }

    public static Message obj(int what, int arg1) {
        return obj(what, arg1, 0, null);
    }

    public static Message obj(int what) {
        return obj(what, 0, 0, null);
    }

    public static void onDestroy(){
        handler.removeCallbacksAndMessages(null);
        handler = null;
    }

    public static void sendToast(String msg){
        handler.sendMessage(obj(100,msg));
    }
    private static TextView tvDjs;
    public static void startDjs(TextView textView) {
        tvDjs = textView;
        if (tvDjs!=null){
            tvDjs.setEnabled(false);
            handler.sendMessage(obj(300,60));
        }
    }

    public static void startDjsStop(TextView tvCountDownTime, long countTime) {

    }
}
