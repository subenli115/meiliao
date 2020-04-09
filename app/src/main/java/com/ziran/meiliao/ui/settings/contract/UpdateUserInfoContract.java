package com.ziran.meiliao.ui.settings.contract;

import com.ziran.meiliao.common.base.BaseModel;
import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.ui.bean.UpdateUserHeadBean;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * des:图片列表contract
 * Created by xsf
 * on 2016.09.14:38
 */
public interface UpdateUserInfoContract {
    interface Model extends BaseModel {
        //请求获取图片

        void updateUserHead(List<File> files, Map<String,String> maps,OkHttpClientManager.ResultCallback callback);
        void updateUserInfo(Map<String, String> parmars, OkHttpClientManager.ResultCallback callback);
    }

    interface View   extends BaseView {
        //返回获取的图片
        void returnUserHead(UpdateUserHeadBean ForgetBean);
        void returnUserInfo(Result ForgetBean);
    }

    abstract  class Presenter extends BasePresenter<View, Model> {
        //发起获取图片请求
        public abstract void updateUserHead(List<File> files,Map<String,String> maps);
        public abstract void updateUserInfo(Map<String,String> map);

    }
}
