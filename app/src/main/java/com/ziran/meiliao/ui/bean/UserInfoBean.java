package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.entry.UserInfo;

/**
 * 登录返回的用户信息bean
 * Created by Administrator on 2017/1/10.
 */


public class UserInfoBean  extends Result{


    /**
     * data : {"headImg":"/user/headImg/1.png","nickName":"陈景辉","sex":"男","age":"23","city":"广州","phone":"13536078831","identity":"440792199305030056"}
     */

    private UserInfo data;

    public UserInfo getUserInfo() {
        return data;
    }

    public void setData(UserInfo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "UserInfoBean{" +
                "data=" + data +
                '}';
    }



}
