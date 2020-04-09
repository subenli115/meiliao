package com.ziran.meiliao.envet;

import android.util.Log;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.common.commonutils.JsonUtils;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.utils.StringUtils;
import org.apache.http.conn.ConnectTimeoutException;
import java.net.ConnectException;
import okhttp3.Request;


/**
 * 统一处理网络请求
 */

public abstract class NewRequestCallBack<T extends Result> extends OkHttpClientManager.ResultCallback {
    private BaseView baseView;
    private Class<T> mClass;

    public NewRequestCallBack(Class<T> aClass) {
        mClass = aClass;
    }
    public NewRequestCallBack(Class<T> aClass,BaseView view) {
        mClass = aClass;
        this.baseView = view;
    }



    @Override
    public void onResponse(String response) {
        try {
            T result = JsonUtils.fromJsonToType(response, mClass);
            // 1 表示请求成功,其他表示请求失败
            if (result == null) {
                onError(response, -1);
                return;
            }

            switch (result.getResultCode()) {
                case 0:
                    if (baseView!=null) baseView.stopLoading();
                    Log.e("json",""+response);
                    onSuccess(result);
                    break;
                case -5:
                    MyAPP.logout(MyAPP.getContext());
                    break;
                case 10:
                    ToastUitl.showShort(result.getResultMsg());
                    showEmpty(result);
                    break;
                default:
                    onError(result.getResultMsg(), result.getResultCode());
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (response != null) {
                onError("服务器繁忙,请稍后重试", -1);
            } else {
                onError("服务器繁忙,请稍后重试", -1);
            }
        }
    }

    private String getExceptionMessage(Exception e) {
        if (e instanceof ConnectException || e instanceof ConnectTimeoutException) {
            ToastUitl.showShort("服务器繁忙,请稍后重试");
            return "服务器繁忙,请稍后重试";
        }
        return e.getMessage();
    }

    @Override
    public void onError(Request request, Exception e) {
        onError(getExceptionMessage(e), -1);
    }

    protected abstract void onSuccess(T result);

    public void onError(String msg, int code) {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (baseView != null) {
                baseView.showErrorTip(msg);
            }
        }
    }


    public void showEmpty(T result) {
        if (baseView != null) {
            baseView.showEmtry();
        }
    }

    @Override
    public void onStart() {
        if (baseView != null) {
            baseView.showLoading(StringUtils.getText(R.string.loading));
        }
    }
}
