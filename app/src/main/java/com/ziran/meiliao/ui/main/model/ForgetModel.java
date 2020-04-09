package com.ziran.meiliao.ui.main.model;

import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.ui.main.contract.ForgetContract;

import java.util.Map;

/**
 * des:图片
 * Created by xsf
 * on 2016.09.12:02
 */
public class ForgetModel implements ForgetContract.Model {


    @Override
    public void getForgetData(Map<String, String> parames, OkHttpClientManager.ResultCallback callback) {
        OkHttpClientManager.postAsync(ApiKey.RESET_PWD, parames,callback);
    }

    @Override
    public void getForgetCode(Map<String, String> parmars, OkHttpClientManager.ResultCallback callback) {
        OkHttpClientManager.postAsync(ApiKey.RESET_CODE, parmars,callback);
    }
}


