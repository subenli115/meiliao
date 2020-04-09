package com.ziran.meiliao.utils;

import android.content.Context;

/**
 * 网络缓存工具类
 * 原则:
 * 以url+参数为key, 以json为value,保存起来
 *
 */
public class CacheUtils {

    /**
     * 写缓存
     */
    public static void setCache(String url, String json, Context ctx) {
        //有时候,可以将缓存写在本地文件中, 以MD5(url)为文件名, 以json为文件内容保存
        PrefUtils.putString(url, json, ctx);
    }

    /**
     * 读缓存
     */
    public static String getCache(String url, Context ctx) {
        //如果缓存写在文件中, 先找文件MD5(url)存不存在,如果存在,说明有缓存
        return PrefUtils.getString(url, null, ctx);
    }
}