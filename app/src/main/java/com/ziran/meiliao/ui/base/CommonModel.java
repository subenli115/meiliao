package com.ziran.meiliao.ui.base;

import com.ziran.meiliao.common.okhttp.OkHttpClientManager;

import java.util.Map;

/**
 * author 吴祖清
 * create  2017/2/8
 * des     常用MVP中的Model    使用OkHttp请求
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */


public class CommonModel implements CommonContract.Model {

    @Override
    public void getData(String url, Map<String, String> params, OkHttpClientManager.ResultCallback callback) {
        OkHttpClientManager.getAsyncMore(url, params, callback);
    }
    @Override
    public void getDataOneHead(String url, String params,String token, OkHttpClientManager.ResultCallback callback) {
        OkHttpClientManager.getDataOneHead(url, params,token, callback);
    }

    @Override
    public void post(String url, Map<String, String> params, OkHttpClientManager.ResultCallback callback) {
        OkHttpClientManager.postAsyncAddHead(url, params,"", callback);
    }

}
