package com.ziran.meiliao.utils;

import android.text.TextUtils;

import com.ziran.meiliao.common.commonutils.SPUtils;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.security.EncodeUtil;
import com.ziran.meiliao.common.security.Md5Security;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.constant.AppConstant;

import java.util.HashMap;
import java.util.Map;

/**
 * author 吴祖清
 * create  2017/3/31 10
 * des     网络请求参数的封装
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */



public class MapUtils {

    //第三方登录需要的map数据
    public static Map<String, String> getPartyLoginMap(String platName, String jsonData) {
        Map<String, String> map = getDefMap(false);
        put("platName", platName, map);
        put("jsonData", jsonData, map);
        return map;
    }
    //登录需要的map数据
    public static Map<String, String> getLoginMap(String phone, String code, String password) {
        Map<String, String> map = getDefMap(false);
        put("userName", phone, map);
        put("logCode", code, map);

        put("deviceId", DeviceUtil.getDeviceId(MyAPP.getContext()) , map);
        put("password", password, map);
        return map;
    }
    //重置密码需要的map数据
    public static Map<String, String> getResetMap(String phoneNum, String resetCode, String password) {
        Map<String, String> map = getDefMap(false);
        put("phoneNum", phoneNum, map);
        put("resetCode", resetCode, map);
        put("password", password, map);
        return map;
    }
    //注册需要的map数据
    public static Map<String, String> getRegisterMap(String areaCode, String phoneNum, String regCode, String password) {
        Map<String, String> map = getDefMap(false);
        put("areaCode", areaCode, map);
        put("phoneNum", phoneNum, map);
        put("regCode", regCode, map);
        put("deviceId", DeviceUtil.getDeviceId(MyAPP.getContext()), map);
        put("password", password, map);
        return map;
    }
        //获取验证码需要的map数据
    public static Map<String, String> getCodeMap(String areaCode, String phoneNum, String isVoice) {
        Map<String, String> map = getDefMap(true);
        put("areaCode", areaCode, map);
        put("phoneNum", phoneNum, map);
        put("isVoice", isVoice, map);
        return map;
    }

    //获取验证码需要的map数据
    public static Map<String, String> getSmsCodeMap(String areaCode, String phoneNum, String isVoice) {
        Map<String, String> map = getDefMap(true);
        put("areaCode", areaCode, map);
        put("phoneNum", phoneNum, map);
        put("isVoice", isVoice, map);
        return map;
    }

    //获取验证码需要的map数据
    public static Map<String, String> getSmsMap( String phoneNum,String smsCode,int project) {
        Map<String, String> map = getDefMap(true);
        put("phoneNum", phoneNum, map);
        put("smsCode", smsCode, map);
        put("project", Integer.toString(project), map);
        return map;
    }
    //将数据添加到map
    public static Map<String, String> put(String key, String value, Map<String, String> map) {
        if (!TextUtils.isEmpty(value)) {
            if ("password".equals(key)) {
                map.put(key, Md5Security.getMD5(value));
            } else {
                map.put(key, value);
            }
        }
        return map;
    }

//    //更新用户信息的map数据
//    public static Map<String, String> getUpdateUserInfo(String nickName, String sex,String city,String realName,String career,String age) {
//        return getUpdateUserInfo(nickName, sex, realName, career, age, city);
//        getUpdateUserInfo(sex)
//    }
    public static Map<String, String> getUpdateUserInfo(
            String nickName, String sex, String realName, String career,
            String age, String city) {
        Map<String, String> defMap = getDefMap(true);
        put("nickName", nickName, defMap);
        put("sex", sex, defMap);
        put("birthday", age, defMap);
        put("realName", realName, defMap);
        put("career", career, defMap);
        put("city", city, defMap);
        return defMap;
    }

    public static Map<String, String> getDefMap(boolean needToken) {
        Map<String, String> map = new HashMap<>();
        if (needToken && EmptyUtils.isNotEmpty( MyAPP.getAccessToken())) {
            map.put("accessToken", MyAPP.getAccessToken());
        }
        return map;
    }
    public static Map<String, String> getDefMapSp(boolean needToken) {
        Map<String, String> map = new HashMap<>();
        if (needToken && EmptyUtils.isNotEmpty( SPUtils.getString(AppConstant.SPKey.TOKEN, ""))) {
            map.put("accessToken",SPUtils.getString(AppConstant.SPKey.TOKEN, ""));
        }
        return map;
    }
    //获取专辑的map数据
    public static Map<String, String> getAlbumData(String albumId, int page) {
        Map<String, String> defMap = getDefMap(true);
        put("albumId", albumId, defMap);
        if (page > 0) {
            put("page", Integer.toString(page), defMap);
        }
        return defMap;
    }
    //获取收藏的map数据
    public static Map<String, String> getCollect(boolean collect, String key, String... ids) {
        Map<String, String> defMap = getDefMap(true);
        if (EmptyUtils.isNotEmpty(ids)) {
            if (ids.length == 1) {
                put(key, ids[0], defMap);
            } else {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < ids.length; i++) {
                    sb.append(",").append(ids[i]);
                }
                sb.deleteCharAt(0);
                put(key, sb.toString(), defMap);
            }
        }
        put("collect", collect ? "false" : "true", defMap);
        return defMap;
    }

    //获取专辑分类的map数据
    public static Map<String, String> getCategoryData(String columnId, int page) {
        Map<String, String> defMap = getDefMap(true);
        put("columnId", columnId, defMap);
        put("page", Integer.toString(page), defMap);
        return defMap;
    }


    public static Map<String, String> getOnlyPage(int page) {
        return getOnlyCan("page", page);
    }
    public static Map<String, String> getTagId(String id) {
        return getOnlyCan("tagId", id);
    }

    public static Map<String, String> getOnlyCan(String key, Object value) {
        Map<String, String> defMap = getDefMap(true);
        put(key, value.toString(), defMap);
        return defMap;
    }

    public static Map<String, String> getCourseMap(String courseId) {
        return getOnlyCan("courseId", courseId);
    }

    public static Map<String, String> getCourseCommentMap(String content, String courseId) {
        Map<String, String> defMap = getDefMap(true);
        put("courseId", courseId, defMap);
        put("content", EncodeUtil.encodeUTF(content), defMap);
        return defMap;
    }

    public static Map<String, String> getSJKLiveComment(String courseId, int page) {
        Map<String, String> defMap = getDefMap(true);
        put("courseId", courseId, defMap);
        put("page", Integer.toString(page), defMap);
        return defMap;
    }

    public static Map<String, String> getFeekBack(String content, String contactWay) {
        Map<String, String> defMap = getDefMap(true);
        put("content", EncodeUtil.encodeUTF(content.trim()), defMap);
        put("contactWay", contactWay, defMap);
        return defMap;
    }

    public static Map<String, String> getLike(String key, String value, boolean collect) {
        Map<String, String> defMap = getDefMap(true);
        put(key, value, defMap);
        put("like", collect ? "false" : "true", defMap);
        return defMap;
    }
}
