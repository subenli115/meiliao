package com.ziran.meiliao.envet;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.StringUtils;

import okhttp3.Request;

/**
 * 返回String的网络请求
 * Created by Administrator on 2017/2/8.
 */

public abstract class StringResultCallBack extends OkHttpClientManager.ResultCallback {
    private BaseView mView;

    public StringResultCallBack() {

    }

    public StringResultCallBack(BaseView view) {
        this.mView = view;
    }

    @Override
    public void onError(Request request, Exception e) {
        if (mView != null) {
            mView.showErrorTip(HandlerUtil.getExceptionMessage(e));
        }
    }

    @Override
    public void onStart() {
        if (mView != null) {
            mView.showLoading(StringUtils.getText(R.string.loading1));
        }
    }

    @Override
    public void onResponse(String result) {
        try {
            onSuccess(result);
        } catch (Exception e) {
            e.printStackTrace();
            if (mView != null) {
                mView.showLoading("服务器异常,请稍后重试");
            }
        }
    }

    public abstract void onSuccess(String result);

}
