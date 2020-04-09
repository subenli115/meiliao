package com.ziran.meiliao.ui.main.contract;

import com.ziran.meiliao.common.base.BaseModel;
import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.entry.LoginBean;

import java.util.Map;

/**
 * 注册页面契约类
 */
public interface RegisterContract {
    interface Model extends BaseModel {
        void getRegisterData(Map<String, String> params, OkHttpClientManager.ResultCallback callback);

        void getRegisterCode(Map<String, String> params, OkHttpClientManager.ResultCallback callback);
    }

    interface View extends BaseView {
        //返回注册结果
        void returnRegisterData(LoginBean result);
        //返回获取验证码结果
        void returnRegisterCode(Result result);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        //执行注册申请请求
        public abstract void postRegisterRequest(Map<String, String> map);
//获取注册验证码
        public abstract void postRegisterCode(Map<String, String> map);
    }
}
