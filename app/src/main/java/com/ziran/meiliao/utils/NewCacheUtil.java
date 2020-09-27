package com.ziran.meiliao.utils;

import android.content.Context;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;
import com.ziran.meiliao.common.commonutils.ACache;
import com.ziran.meiliao.ui.bean.HomeBannerBean;
import com.ziran.meiliao.ui.bean.HomeMenuBean;
import com.ziran.meiliao.ui.bean.LabelHobbyBean;
import com.ziran.meiliao.ui.bean.RechargeBean;
import com.ziran.meiliao.ui.bean.SelectBean;
import com.ziran.meiliao.ui.bean.SpaceDetailBean;
import com.ziran.meiliao.ui.bean.UserBean;
import com.ziran.meiliao.ui.bean.WechatParentBean;

import java.util.List;

public class  NewCacheUtil {


    private final ACache mCache;
    private final Gson g;

  public NewCacheUtil(Context context){
        g = new Gson();
        mCache = ACache.get(context);
    }


    public void saveUserBean(UserBean.DataBean dataBean) {
        String json = g.toJson(dataBean);
        mCache.put("" + dataBean.getId(), json, 1 * ACache.TIME_DAY);//保存两天，如果超过两天去获取这个key，将为null
    }


    public void saveSpaceBean(List<String> list,String userId) {
        String json = g.toJson(list);
        mCache.put("space" + userId, json, 12 * ACache.TIME_HOUR);
    }

    public void saveLabelBean(List<SelectBean> dataBean,String userId) {
        String json = g.toJson(dataBean);
        mCache.put("label" + userId, json, 12 * ACache.TIME_HOUR);
    }

    public void saveHomeBean(List<String> list,String userId) {
        String json = g.toJson(list);
        mCache.put("homeData"+userId , json, 1 * ACache.TIME_DAY);//保存两天，如果超过两天去获取这个key，将为null
    }

    public void saveRechargeBean(List<RechargeBean.DataBean.RecordsBean> list) {
        String json = g.toJson(list);
        mCache.put("recharge" , json, 30 * ACache.TIME_DAY);//保存两天，如果超过两天去获取这个key，将为null
    }


    public void saveSpaceDeatilBean(SpaceDetailBean.DataBean dataBean,String spaceId) {
        String json = g.toJson(dataBean);
        mCache.put( "spacedeatil"+spaceId, json, 1 * ACache.TIME_DAY);//保存两天，如果超过两天去获取这个key，将为null
    }

    public void saveBannerBean(List<HomeBannerBean.DataBean> dataBean) {
        String json = g.toJson(dataBean);
        mCache.put("banner", json, 12 * ACache.TIME_HOUR);
    }

    public void saveMenuBean(List<HomeMenuBean.DataBean> dataBean) {
        String json = g.toJson(dataBean);
        mCache.put("menu", json, 12 * ACache.TIME_HOUR);
    }

    public void saveWechatBean(List<WechatParentBean> dataBean,String userId) {
        String json = g.toJson(dataBean);
        mCache.put("wechat"+userId, json, 12 * ACache.TIME_HOUR);
    }



   public Object getDataBean(String key,Class c){
       if(mCache.getAsString(key)!=null) {
       Object dataBean = g.fromJson(mCache.getAsString(key),  c);
           return dataBean;
       }else {
           return null;
       }
    }

    public List getDataList(String key,Class c){
        if(mCache.getAsString(key)!=null) {
            List list = JSONArray.parseArray(mCache.getAsString(key), c);
            return list;
        }else {
            return null;
        }
    }




    public String getData(String key){
        if(mCache.getAsString(key)!=null){
            String userString = mCache.getAsString(key);
            return userString;
        }else {
            return null;
        }
    }
}
