package com.ziran.meiliao.ui.settings.contract;

import com.ziran.meiliao.common.base.BaseModel;
import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.ui.bean.GainCouponBean;
import com.ziran.meiliao.ui.bean.ShareCouponBean;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * des:图片列表contract
 * Created by xsf
 * on 2016.09.14:38
 */
public interface ShareCouponContract {
    interface Model extends BaseModel {
        //请求获取图片
        void postContentAndFiles(String url,List<File> files, Map<String, String> maps, OkHttpClientManager.ResultCallback callback);
        void getGoinCoupon(String url,Map<String, String> parmars, OkHttpClientManager.ResultCallback callback);
    }

    interface View   extends BaseView {
        //返回获取的图片
        void showUploadFile(ShareCouponBean result);
        void showGoinCoupon(GainCouponBean result);
    }

    abstract  class Presenter extends BasePresenter<View, Model> {
        //发起获取图片请求
        public abstract void postContentAndFiles(Map<String, String> map, List<File> files);
        public abstract void getGoinCoupon(Map<String,String> map);
    }
}
