package com.ziran.meiliao.ui.priavteclasses.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/9/21 18:13
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/9/21$
 * @updateDes ${TODO}
 */

public class TrailerCollectConfig {
    private static Map<String, Object> mConfigMap = new HashMap<>();

    public static void addConfig(String url, Object config) {
        if (!mConfigMap.containsKey(url)) {
            mConfigMap.put(url, config);
        }
    }

    public static <T> T getConfig(String url) {
        return (T)mConfigMap.get(url);
    }

    public static void clear(){
        mConfigMap.clear();
    }
}
