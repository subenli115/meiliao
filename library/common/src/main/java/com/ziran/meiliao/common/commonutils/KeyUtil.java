package com.ziran.meiliao.common.commonutils;

import android.app.Instrumentation;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/4/18 16:43
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/4/18$
 * @updateDes ${TODO}
 */

public class KeyUtil {

    /**
     * 模拟系统按键。
     *
     * @param keyCode
     */
    public static void onKeyEvent(final int keyCode) {
        new Thread() {
            public void run() {
                try {
                    Instrumentation inst = new Instrumentation();
                    inst.sendKeyDownUpSync(keyCode);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
