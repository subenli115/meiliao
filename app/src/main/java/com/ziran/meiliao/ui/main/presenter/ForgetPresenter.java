package com.ziran.meiliao.ui.main.presenter;

import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.main.contract.ForgetContract;

import java.util.Map;


/**
 * des:图片列表
 * Created by xsf
 * on 2016.09.12:01
 */
public class ForgetPresenter extends ForgetContract.Presenter{

    @Override
    public void postForgetRequest(Map<String, String> map) {
        mModel.getForgetData(map, new NewRequestCallBack<Result>(Result.class) {
            @Override
            public void onSuccess(Result result) {
                mView.returnForgetData(result);
            }
            @Override
            public void onError(String msg,int code) {
                mView.showErrorTip(msg);
            }
            @Override
            public void onStart() {
            }
        });
    }

    @Override
    public void postForgetCode(Map<String, String> map) {
        mModel.getForgetCode(map, new NewRequestCallBack<Result>(Result.class) {
            @Override
            public void onSuccess(Result result) {
                mView.returnForgetCode(result);
            }
            @Override
            public void onError(String msg, int code) {
                ToastUitl.showShort(msg);
            }
        });
    }
}
