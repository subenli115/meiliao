package com.ziran.meiliao.ui.priavteclasses.util;

import android.os.CountDownTimer;

import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/3 11:41
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/3$
 * @updateDes ${TODO}
 */

public class CountDownUtil {
    private static CountDownUtil ourInstance;
    private Map<ViewHolderHelper, CountDownTimer> countDownMap;

    public static CountDownUtil get() {
        if (ourInstance == null) {
            synchronized (CountDownUtil.class) {
                if (ourInstance == null) {
                    ourInstance = new CountDownUtil();
                }
            }
        }
        return ourInstance;
    }


    private CountDownUtil() {
        countDownMap = new ConcurrentHashMap<>();
    }


    /**
     * 清空资源
     */
    public void cancelAllTimers() {
        if (countDownMap == null) {
            return;
        }
        Set<ViewHolderHelper> helpers = countDownMap.keySet();
        for (ViewHolderHelper helper : helpers) {
            countDownMap.remove(helper).cancel();
        }
    }

    public void cancel(ViewHolderHelper helper) {
        if (countDownMap.containsKey(helper)) {
            countDownMap.remove(helper).cancel();
        }
    }

    public void execute(final ViewHolderHelper holder, final int id, long time,final CallBack callback) {

        time = time - System.currentTimeMillis();

        //将前一个缓存清除
        //将前一个缓存清除
        if (countDownMap.containsKey(holder)) {
            countDownMap.remove(holder).cancel();
        }
        if (time > 0) {
            CountDownTimer countDownTimer = new CountDownTimer(time, 1000) {
                public void onTick(long millisUntilFinished) {
                    holder.setText(id, formatData(millisUntilFinished));
                }

                public void onFinish() {
                    holder.setVisible(id, false);
                    if (callback!=null){
                        callback.onFinish();
                    }
                }
            }.start();
            countDownMap.put(holder, countDownTimer);
        } else {
            holder.setVisible(id, false);
        }
    }
    public interface CallBack{
        void onFinish();
    }
    private String formatData(long data) {
        long temp = data/1000;
        long miao = temp%60;
        long fen = temp/60%60;
        long hour = temp/3600%24;
        long day = temp/3600/24;
        return String.format("%d天%d小时%d分%d秒",day,hour,fen,miao);
    }
}
