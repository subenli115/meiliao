package com.ziran.meiliao.ui.main.presenter;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.entry.LoginBean;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.main.contract.RegisterContract;

import java.util.Map;


/**
 * des:图片列表
 * Created by xsf
 * on 2016.09.12:01
 */
public class RegisterPresenter extends RegisterContract.Presenter{
    private static final String TAG = "RegisterPresenter";
    @Override
    public void postRegisterRequest(Map<String,String> map) {
        mModel.getRegisterData(map, new NewRequestCallBack<LoginBean>(LoginBean.class,mView) {
            @Override
            public void onSuccess(LoginBean result) {
                mView.stopLoading();
                mView.returnRegisterData(result);
            }


            @Override
            public void onStart() {
                mView.showLoading(mContext.getString(R.string.registering));
            }
        });
    }

    @Override
    public void postRegisterCode(Map<String, String> map) {
        mModel.getRegisterCode(map, new NewRequestCallBack<Result>(Result.class) {
            @Override
            public void onSuccess(Result result) {
                mView.returnRegisterCode(result);
            }

            @Override
            public void onError(String msg, int code) {
                Result result = new Result();
                result.setResultCode(code);
                result.setResultMsg(msg);
                mView.returnRegisterCode(result);
            }
        });
    }
}
