package com.ziran.meiliao.ui.settings.model;

import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.ui.settings.contract.UpdateUserInfoContract;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * des:图片
 * Created by xsf
 * on 2016.09.12:02
 */
public class UpdateUserInfoModel implements UpdateUserInfoContract.Model {


    @Override
    public void updateUserHead(List<File> files, Map<String, String> maps, OkHttpClientManager.ResultCallback callback) {
        OkHttpClientManager.postContentAndFiles(ApiKey.USER_INFO_HEAD,maps,files,callback);
    }

    @Override
    public void updateUserInfo(Map<String, String> parmars, OkHttpClientManager.ResultCallback callback) {
        OkHttpClientManager.postAsync(ApiKey.UPDATE_USER_INFOV2,parmars,callback);
    }
}


