package com.ziran.meiliao.ui.settings.model;


import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.ui.settings.contract.MemberDetailsContract;
import java.util.Map;



public class MemberModel implements MemberDetailsContract.Model {


    @Override
    public void postGoodsDetails(String url, Map<String, String> params, OkHttpClientManager.ResultCallback callback) {
        OkHttpClientManager.postAsync(ApiKey.USERCENTER_GOODSLIST, params, callback);
    }

    @Override
    public void postGetLevelDetails(String url, Map<String, String> params, OkHttpClientManager.ResultCallback callback) {
        OkHttpClientManager.postAsync(ApiKey.USERCENTER_CENTERINTRO, params, callback);
    }

    @Override
    public void postBuyByScore(String url, Map<String, String> params, OkHttpClientManager.ResultCallback callback) {
        OkHttpClientManager.postAsync(ApiKey.USERCENTER_BUYBYSCORE, params, callback);
    }

    @Override
    public void postGetScore(String url, Map<String, String> params, OkHttpClientManager.ResultCallback callback) {
        OkHttpClientManager.postAsync(ApiKey.USERCENTER_SCOREDETAIL, params, callback);
    }
}
