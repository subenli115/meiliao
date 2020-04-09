package com.ziran.meiliao.ui.main.model;

import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.ui.main.contract.LoginContract;

import java.util.Map;

/**
 * Created by Administrator on 2016/12/26.
 */

public class LoginModel implements LoginContract.Model {

    @Override
    public void getLoginData(Map<String, String> parmars, OkHttpClientManager.ResultCallback callback) {
        OkHttpClientManager.postAsync(ApiKey.USER_LOGIN, parmars,callback);
    }

    @Override
    public void getBindPhoneData(Map<String, String> parmars, OkHttpClientManager.ResultCallback callback) {
        OkHttpClientManager.postAsync(ApiKey.USER_REGISIT, parmars,callback);
    }

    @Override
    public void getLoginCode(Map<String, String> parmars, OkHttpClientManager.ResultCallback callback) {
//        OkHttpClientManager.getAsync()
        OkHttpClientManager.postAsync(ApiKey.USER_GET_LOG_CODE, parmars,callback);
    }
    @Override
    public void getBindCode(Map<String, String> parmars, OkHttpClientManager.ResultCallback callback) {
//        OkHttpClientManager.getAsync()
        OkHttpClientManager.postAsync(ApiKey.USER_GET_REG_CODE, parmars,callback);
    }

    @Override
    public void getPartyLoginData(Map<String, String> parmars, OkHttpClientManager.ResultCallback callback) {
        OkHttpClientManager.postAsync(ApiKey.PARTY_LOGIN,parmars,callback);
    }

    @Override
    public void getCheckLoginPhone(Map<String, String> parmars, OkHttpClientManager.ResultCallback callback) {
        OkHttpClientManager.getAsync(ApiKey.USER_CHECKLOGINPHONE,parmars,callback);
    }

    @Override
    public void getTagSaveCheck(Map<String, String> params, OkHttpClientManager.ResultCallback callback) {
        OkHttpClientManager.postAsync(ApiKey.USERINFO_TAGSAVECHECK,params,callback);
    }
}
