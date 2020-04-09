package com.ziran.meiliao.ui.main.presenter;

import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.entry.LoginBean;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.CheckPhoneBean;
import com.ziran.meiliao.ui.bean.StringDataBean;
import com.ziran.meiliao.ui.bean.TagCheckBean;
import com.ziran.meiliao.ui.main.contract.LoginContract;

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
        mModel.getLoginCode(map, new NewRequestCallBack<Result>(Result.class) {
            @Override
            public void onSuccess(Result result) {
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
        mModel.getBindCode(map, new NewRequestCallBack<StringDataBean>(StringDataBean.class) {

            @Override
            public void onSuccess(StringDataBean result) {
                mView.returnBindCode(result);
            }
            @Override
            public void onError(String msg, int code) {
                ToastUitl.showShort(msg);
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
                ToastUitl.showShort(msg);
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
