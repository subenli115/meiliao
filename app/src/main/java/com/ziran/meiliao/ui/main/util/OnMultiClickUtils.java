package com.ziran.meiliao.ui.main.util;

import android.content.Context;

public class OnMultiClickUtils {
    private static long lastClickTime;
    private static final int MIN_CLICK_DELAY_TIME = 1000;
    public static boolean isMultiClickClick(Context context) {
        boolean flag = false;
        try {
            long curClickTime = System.currentTimeMillis();
            if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME || (curClickTime - lastClickTime) < 0) {
                flag = true;
                lastClickTime = curClickTime;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }
}
