package com.ziran.meiliao.ui.main.contract;

import com.ziran.meiliao.common.base.BaseModel;
import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.common.okhttp.Result;

import java.util.Map;

/**
 * 忘记密码/修改密码契约类
 */
public interface ForgetContract {
    interface Model extends BaseModel {
        //执行修改密码请求操作
        void getForgetData(Map<String, String> params, OkHttpClientManager.ResultCallback callback);
        //执行获取验证码请求操作
        void getForgetCode(Map<String, String> params, OkHttpClientManager.ResultCallback callback);
    }

    interface View   extends BaseView {
        //返回修改密码请求结果
        void returnForgetData(Result result);
        //返回获取验证码结果
        void returnForgetCode(Result result);
    }

    abstract  class Presenter extends BasePresenter<View, Model> {
        //请求修改密码
        public abstract void postForgetRequest(Map<String,String> params);
        //请求获取验证码
        public abstract void postForgetCode(Map<String,String> params);
    }
}
