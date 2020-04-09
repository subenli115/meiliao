package com.ziran.meiliao.utils;

import android.app.Activity;
import android.text.TextUtils;

import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.compressorutils.FileUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


/**
 * author 吴祖清
 * create  2017/3/31 10
 * des      String拼接的工具类
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */

public class StringUtils {
    public static String headImg() {
        try {
            if (TextUtils.isEmpty(MyAPP.getUserInfo().getHeadImg())) {
                return FileUtil.getImageCacheFolder() + "/0000.jpg";
            } else {
                return newHeadImgPath();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return MyAPP.getUserInfo().getHeadImg();
    }

    public static String newHeadImgPath(int version) {
        return new File(FileUtil.getDownImageFolder(), version + "head.jpeg").getPath();
    }


    private static String newHeadImgPath() {
        return newHeadImgPath(MyAPP.getUserInfo().getHeadImgVersion());
    }


    public static String format(String s, Object... format) {
        return String.format(Locale.getDefault(), s, format);
    }

    public static String format(int stingId, Object... format) {
        return String.format(Locale.getDefault(), getText(stingId), format);
    }

    public static String getText(int textId) {
        Activity activity = AppManager.getAppManager().currentActivity();
        return activity == null ? "" : activity.getString(textId);
    }
    public static Map getUrlParams(String url){
        Map<String,Object> map = new HashMap<>();
        url = url.replace("?",";");
        if (!url.contains(";")){
            return map;
        }
        if (url.split(";").length > 0){
            String[] arr = url.split(";")[1].split("&");
            for (String s : arr){
                String key = s.split("=")[0];
                String value = s.split("=")[1];
                map.put(key,value);
            }
            return  map;

        }else{
            return map;
        }
    }

    public static String getHeadVersionKey() {
        return format("HeadImgVersion%s", MyAPP.getUserInfo().getPhone());
    }

    public static String[] getStringArray(String... strings) {
        return strings;
    }

    public static String getBankLogo(String cardBankEng) {
        return format("https://apimg.alipay.com/combo.png?d=cashier&t=%s", cardBankEng);
    }

    public static String getBankType(String text) {
        if (EmptyUtils.isEmpty(text)) return "";
        switch (text) {
            case "DC":
                return "储蓄卡";
            case "CC":
                return "信用卡";
        }
        return "";
    }
}
