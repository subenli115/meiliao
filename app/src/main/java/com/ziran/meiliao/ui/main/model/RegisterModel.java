package com.ziran.meiliao.ui.main.model;

import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.ui.main.contract.RegisterContract;

import java.util.Map;

/**
 * des:图片
 * Created by xsf
 * on 2016.09.12:02
 */
public class RegisterModel implements RegisterContract.Model {


    @Override
    public void getRegisterData(Map<String, String> parmars, OkHttpClientManager.ResultCallback callback) {
        OkHttpClientManager.postAsync(ApiKey.USER_REGISIT, parmars,callback);
    }

    @Override
    public void getRegisterCode(Map<String, String> parmars, OkHttpClientManager.ResultCallback callback) {
        OkHttpClientManager.postAsync(ApiKey.USER_GET_REG_CODE, parmars,callback);
    }

}


