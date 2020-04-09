package com.ziran.meiliao.ui.main.model;

import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.ui.main.contract.MainMeContract;
import com.ziran.meiliao.utils.DownloadUtil;

import java.io.File;
import java.util.Map;

/**
 * des:图片
 * Created by xsf
 * on 2016.09.12:02
 */
public class MainMeModel implements MainMeContract.Model {


    @Override
    public void downloadHeadImage(String url, String fileName, OkHttpClientManager.ResultCallback callback) {
        File file = new File(fileName);
        DownloadUtil.getInstance().downloadFile( url, file.getParent()+"/",file.getName());
//        OkHttpClientManager.singleDownloadFile(url, fileName, callback);
    }

    @Override
    public void postRecordToken(String url, Map<String, String> parmars, OkHttpClientManager.ResultCallback callback) {
        OkHttpClientManager.postAsync(url, parmars, callback);
    }

    @Override
    public void postCheckLevel(String url, Map<String, String> parmars, OkHttpClientManager.ResultCallback callback) {
        OkHttpClientManager.postAsync(url, parmars, callback);
    }

    @Override
    public void getUserHomeRes(String url, Map<String, String> parmars, OkHttpClientManager.ResultCallback callback) {
        OkHttpClientManager.postAsync(url, parmars, callback);
    }

    @Override
    public void getUpdateTrialer(String url, Map<String, String> parmars, OkHttpClientManager.ResultCallback callback) {
        OkHttpClientManager.postAsync(url, parmars, callback);
    }

    @Override
    public void getGainSpread(String url, Map<String, String> parmars, OkHttpClientManager.ResultCallback callback) {
        OkHttpClientManager.getAsync(url, parmars, callback);
    }

    @Override
    public void postGiveAlbum(String url, Map<String, String> parmars, OkHttpClientManager.ResultCallback callback) {
        OkHttpClientManager.postAsync(url, parmars, callback);
    }

    @Override
    public void postGetLevel(String url, Map<String, String> parmars, OkHttpClientManager.ResultCallback callback) {
        OkHttpClientManager.postAsync(url, parmars, callback);
    }
}
