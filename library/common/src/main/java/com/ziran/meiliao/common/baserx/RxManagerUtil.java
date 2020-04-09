package com.ziran.meiliao.common.baserx;

import rx.functions.Action1;

/**
 * Created by Administrator on 2017/3/22.
 */

public class RxManagerUtil {
    private static RxManager rxManager = new RxManager();

    public static void post(Object tag, Object content) {
        rxManager.post(tag, content);
    }

    public static void clear() {
        rxManager.clear();
    }
    public static <T> void on(String eventName, Action1<T> action1) {
        rxManager.on(eventName, action1);
    }
}
