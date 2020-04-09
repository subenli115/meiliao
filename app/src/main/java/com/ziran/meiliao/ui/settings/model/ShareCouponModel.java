package com.ziran.meiliao.ui.settings.model;

import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.ui.settings.contract.ShareCouponContract;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * des:图片
 * Created by xsf
 * on 2016.09.12:02
 */
public class ShareCouponModel implements ShareCouponContract.Model {


    @Override
    public void postContentAndFiles(String url, List<File> files, Map<String, String> maps, OkHttpClientManager.ResultCallback callback) {
        OkHttpClientManager.postContentAndFiles(url, maps, files, callback);
    }

    @Override
    public void getGoinCoupon(String url, Map<String, String> parmars, OkHttpClientManager.ResultCallback callback) {
        OkHttpClientManager.postAsync(url, parmars, callback);
    }
}


