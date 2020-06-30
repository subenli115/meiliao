package com.ziran.meiliao.ui.main.contract;

import com.ziran.meiliao.common.base.BaseModel;
import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.entry.LoginBean;
import com.ziran.meiliao.ui.bean.StringDataV2Bean;
import com.ziran.meiliao.ui.bean.CheckPhoneBean;
import com.ziran.meiliao.ui.bean.TagCheckBean;
import com.ziran.meiliao.ui.bean.UserBean;

import java.util.Map;

/**
 * 登录页面的契约类
 */
public interface LoginContract {
    interface Model extends BaseModel {

        void getLoginData(Map<String, String> params, OkHttpClientManager.ResultCallback callback);

        void getBindPhoneData(Map<String, String> params, OkHttpClientManager.ResultCallback callback);
        void getLoginCode(Map<String, String> params, OkHttpClientManager.ResultCallback callback);
        void postPwdLogin(Map<String, String> params, OkHttpClientManager.ResultCallback callback);
        void getBindCode(Map<String, String> params, OkHttpClientManager.ResultCallback callback);
        void getUserInfo(String params, String token,OkHttpClientManager.ResultCallback callback);
        void getPartyLoginData(Map<String, String> params, OkHttpClientManager.ResultCallback callback);
        void getCheckLoginPhone(Map<String, String> params, OkHttpClientManager.ResultCallback callback);
        void getTagSaveCheck(Map<String, String> params, OkHttpClientManager.ResultCallback callback);
    }

    interface View extends BaseView {
        void returnLoginData(LoginBean registerBean);
        void returnBindPhoneData(LoginBean registerBean);

        void returnLoginCode(StringDataV2Bean registerBean);
        void returnBindCode(LoginBean registerBean);

        void returnPartyLogin(LoginBean registerBean);
        void showPwdLogin(LoginBean result);
        void showUserInfo(UserBean result);
        void showLoginCheck(CheckPhoneBean result);
        void showCheckSaveData(TagCheckBean data);
    }

    abstract  class Presenter extends BasePresenter<View, Model> {
        //发起获取图片请求
        public abstract void postLoginRequest(Map<String, String> map);
        //请求绑定手机操作
        public abstract void postBindPhoneRequest(Map<String, String> map);
        //请求获取登录验证码
        public abstract void postLoginCode(Map<String, String> map);
        //请求获取绑定验证码
        public abstract void postBindCode(Map<String, String> map);
        //请求第三方登录
        public abstract void postPartyLoginData(Map<String, String> map);
        public abstract void postCheckLoginPhone(Map<String, String> map);
        public abstract void postTagSaveCheck(Map<String,String> map );
        public abstract void postPwdLogin(Map<String,String> map );
        public abstract void getUserInfo(String map ,String token);

    }
}
