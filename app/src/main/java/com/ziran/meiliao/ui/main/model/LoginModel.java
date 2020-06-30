package com.ziran.meiliao.ui.main.model;

import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.ui.main.contract.LoginContract;
import com.ziran.meiliao.utils.MapUtils;

import java.util.Map;

/**
 * Created by Administrator on 2016/12/26.
 */

public class LoginModel implements LoginContract.Model {

    @Override
    public void getLoginData(Map<String, String> parmars, OkHttpClientManager.ResultCallback callback) {
        OkHttpClientManager.postAsyncAddHead(ApiKey.CODEANDPHONE_LOGIN, MapUtils.getDefMap(false),"?mobile="+parmars.get("mobile")+"&"+"code="+parmars.get("code")
                +"&grant_type=mobile"
                ,callback);
    }

    @Override
    public void getBindPhoneData(Map<String, String> parmars, OkHttpClientManager.ResultCallback callback) {
        OkHttpClientManager.postAsync(ApiKey.USER_REGISIT, parmars,callback);
    }

    @Override
    public void getLoginCode(Map<String, String> parmars, OkHttpClientManager.ResultCallback callback) {
        OkHttpClientManager.getAsyncMore(ApiKey.USER_GET_LOG_CODE, parmars,callback);
    }
    @Override
    public void getBindCode(Map<String, String> parmars, OkHttpClientManager.ResultCallback callback) {
        OkHttpClientManager.postAsyncAddHead(ApiKey.AUTH_MOBILE_TOKEN_SOCIAL, parmars,"",callback);
    }
    @Override
    public void postPwdLogin(Map<String, String> parmars, OkHttpClientManager.ResultCallback callback) {
        OkHttpClientManager.postAsyncAddHead(ApiKey.USER_LOGIN, parmars,"",callback);
    }

    @Override
    public void getPartyLoginData(Map<String, String> parmars, OkHttpClientManager.ResultCallback callback) {
        OkHttpClientManager.postAsync(ApiKey.ADMIN_SOCIAL_SOCIAL,parmars,callback);
    }

    @Override
    public void getCheckLoginPhone(Map<String, String> parmars, OkHttpClientManager.ResultCallback callback) {
        OkHttpClientManager.getAsyncOne(ApiKey.ONEKEYLOGIN,parmars.get("token"),callback);
    }

    @Override
    public void getTagSaveCheck(Map<String, String> params, OkHttpClientManager.ResultCallback callback) {
        OkHttpClientManager.postAsync(ApiKey.USERINFO_TAGSAVECHECK,params,callback);
    }

    @Override
    public void getUserInfo(String params,String token, OkHttpClientManager.ResultCallback callback) {
        OkHttpClientManager.getAsyncHead(ApiKey.ADMIN_USER_COMPLETEUSERINFO,params, token,callback);
    }
}
