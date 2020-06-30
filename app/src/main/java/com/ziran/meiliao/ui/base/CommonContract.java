package com.ziran.meiliao.ui.base;

import com.ziran.meiliao.common.base.BaseModel;
import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.common.okhttp.Result;

import java.util.Map;


/**
 * author 吴祖清
 * create  2017/2/8
 * des     常用的MVP 的  契约
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */

public interface CommonContract {
    /**
     * mvp中的model基类
     */
    interface Model extends BaseModel {
        /**
         * 获取数据
         *
         * @param url      网络请求的借口
         * @param params  网络请求的参数
         * @param callback 网络请求的结果回调
         */
        void getData(String url, Map<String, String> params, OkHttpClientManager.ResultCallback callback);

        /**
         * POST网络请求
         *
         * @param url      网络请求的借口
         * @param params  网络请求的参数
         * @param callback 网络请求的结果回调
         */
        void post(String url, Map<String, String> params, OkHttpClientManager.ResultCallback callback);






        void getDataOneHead(String url, String params,String token, OkHttpClientManager.ResultCallback callback);

    }


    interface View<D extends Result> extends BaseView {
        void returnData(D result);
    }


    interface ActionView<D extends Result,D1 extends Result> extends View<D> {
        void returnAction(D1 result);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        //发起GET请求
        public abstract <D extends Result> void postData(String url, Map<String, String> params, final Class<D>
                clz);

        public abstract <D extends Result> void postAction(String url, Map<String, String> params, final Class<D> clz);
        //发起GET请求
        public abstract <D extends Result> void getData(String url, Map<String, String> params, final Class<D>
                clz);
        public abstract <D extends Result> void getDataOneHead(String url, String params, String token,final Class<D>
                clz);
    }
}
