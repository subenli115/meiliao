package com.ziran.meiliao.ui.main.presenter;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;

import com.chuanglan.shanyan_sdk.OneKeyLoginManager;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.entry.LoginBean;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.im.activity.BaseActivity;
import com.ziran.meiliao.ui.bean.StringDataV2Bean;
import com.ziran.meiliao.ui.bean.CheckPhoneBean;
import com.ziran.meiliao.ui.bean.TagCheckBean;
import com.ziran.meiliao.ui.bean.UserBean;
import com.ziran.meiliao.ui.main.activity.SplashActivity;
import com.ziran.meiliao.ui.main.contract.LoginContract;
import com.ziran.meiliao.ui.settings.activity.IntputCodeActivity;

import java.util.Map;


/**
 * des: 登录控制器
 * Created by xsf
 * on 2016.09.12:01
 */
public class LoginPresenter extends LoginContract.Presenter {

    @Override
    public void postLoginRequest(Map<String, String> map) {

        mModel.getLoginData(map, new NewRequestCallBack<LoginBean>(LoginBean.class,mView) {
            @Override
            public void onSuccess(LoginBean result) {

                mView.stopLoading();
                mView.returnLoginData(result);
            }

            @Override
            public void onError(String msg, int code) {
                ToastUitl.showShort("验证码有误");
                mView.stopLoading();
                OneKeyLoginManager.getInstance().setLoadingVisibility(false);
            }
        });
    }

    @Override
    public void postBindPhoneRequest(Map<String, String> map) {

        mModel.getBindPhoneData(map, new NewRequestCallBack<LoginBean>(LoginBean.class) {

            @Override
            public void onSuccess(LoginBean d) {
                if (d==null) return;
                mView.returnBindPhoneData(d);
            }
        });
    }

    @Override
    public void postLoginCode(Map<String, String> map) {
        mModel.getLoginCode(map, new NewRequestCallBack<StringDataV2Bean>(StringDataV2Bean.class) {
            @Override
            public void onSuccess(StringDataV2Bean result) {
                mView.returnLoginCode(result);
            }
            @Override
            public void onError(String msg, int code) {
                ToastUitl.showShort(msg);
            }
        });
    }

    @Override
    public void postBindCode(Map<String, String> map) {
        mModel.getBindCode(map, new NewRequestCallBack<LoginBean>(LoginBean.class) {

            @Override
            public void onSuccess(LoginBean result) {
                mView.returnBindCode(result);
            }
            @Override
            public void onError(String msg, int code) {
                ToastUitl.showShort(msg);
            }
        });
    }

    @Override
    public void postPwdLogin(Map<String, String> map) {
        mModel.postPwdLogin(map, new NewRequestCallBack<LoginBean>(LoginBean.class) {

            @Override
            public void onSuccess(LoginBean result) {
                mView.showPwdLogin(result);
            }

            @Override
            public void onError(String msg, int code) {
                ToastUitl.showShort("用户名不存在或者密码错误");
            }
        });
    }

    @Override
    public void getUserInfo(String map,String token) {

        mModel.getUserInfo(map, token,new NewRequestCallBack<UserBean>(UserBean.class) {

            @Override
            protected void onSuccess(UserBean result) {
                mView.showUserInfo(result);

            }

            @Override
            public void onError(String msg, int code) {
                if(code==1001){
                    Intent intent = new Intent(mContext,IntputCodeActivity.class);
                    intent.putExtra("Connected", "false");
                    mContext.startActivity(intent);
                }
            }
        });
    }

    @Override
    public void postCheckLoginPhone(Map<String, String> map) {
        mModel.getCheckLoginPhone(map, new NewRequestCallBack<CheckPhoneBean>(CheckPhoneBean.class) {

            @Override
            protected void onSuccess(CheckPhoneBean result) {
                mView.showLoginCheck(result);
            }

            @Override
            public void onError(String msg, int code) {
                OneKeyLoginManager.getInstance().setLoadingVisibility(false);
                ToastUitl.showShort(msg);
            }
        });
    }
    @Override
    public void postPartyLoginData(Map<String, String> map) {

        mModel.getPartyLoginData(map, new NewRequestCallBack<LoginBean>(LoginBean.class) {
            @Override
            public void onSuccess(LoginBean result) {
                mView.returnPartyLogin(result);
            }
            @Override
            public void onError(String msg, int code) {
                    if(code==1002){

                    }
            }
        });
    }


    @Override
    public void postTagSaveCheck(Map<String, String> map) {

        mModel.getTagSaveCheck(map, new NewRequestCallBack<TagCheckBean>(TagCheckBean.class) {

            @Override
            protected void onSuccess(TagCheckBean result) {
                mView.showCheckSaveData(result);
            }

            @Override
            public void onError(String msg, int code) {
                ToastUitl.showShort(msg);
            }
        });
    }
}
