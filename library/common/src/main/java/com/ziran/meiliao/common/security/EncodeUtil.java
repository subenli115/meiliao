package com.ziran.meiliao.common.security;

import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2017/1/5.
 */

public class EncodeUtil {
    /**
     *  再次编码
     * @param value
     * @return
     */
    public static String encodeUTF(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return value;
        }
    }

    /**
     *  拿下来进行解码
     * @param value
     * @return
     */
    public static String decodeUTF(String value) {
        try {
            if (TextUtils.isEmpty(value)) return "";
            return URLDecoder.decode(value,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return value;
        }
    }
}
