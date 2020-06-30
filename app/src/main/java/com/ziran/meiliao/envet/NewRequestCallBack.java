package com.ziran.meiliao.envet;

import android.util.Log;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.common.commonutils.JsonUtils;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.ui.bean.StringDataBean;
import com.ziran.meiliao.utils.StringUtils;
import org.apache.http.conn.ConnectTimeoutException;

import java.io.IOException;
import java.net.ConnectException;
import okhttp3.Request;


/**
 * 统一处理网络请求
 */

public abstract class NewRequestCallBack<T extends Result> extends OkHttpClientManager.ResultCallback {
    private BaseView baseView;
    private Class<T> mClass,mClass1;

    public NewRequestCallBack(Class<T> aClass) {
        mClass = aClass;
    }
    public NewRequestCallBack(Class<T> aClass,Class<T> bClass) {
        mClass = aClass;
        mClass1 = bClass;
    }
    public NewRequestCallBack(Class<T> aClass,BaseView view) {
        mClass = aClass;
        this.baseView = view;
    }



    @Override
    public void onResponse(String response) {
        try {
             T result = JsonUtils.fromJsonToType(response, mClass);
            if (result == null) {
                StringDataBean da = JsonUtils.fromJsonToType(response, StringDataBean.class);
                if(da!=null&&da.getResultCode()==1001){
                    MyAPP.refreshToken();
                    onError("请重试", da.getResultCode());
                    return;
                }
                onError("服务器繁忙", -1);
                return;
            }
            Log.e("meiliaoresponse",result.toString());
            switch (result.getResultCode()) {
                case 0:
                    if (baseView!=null) baseView.stopLoading();
                    Log.e("json",""+response);
                    onSuccess(result);
                    break;
                case -5:
                    break;
                case 10:
                    ToastUitl.showShort(result.getResultMsg());
                    showEmpty(result);
                    break;
                case 1001:
                    ToastUitl.showShort("操作失败，请重试");
                    MyAPP.refreshToken();
                    break;
                case 1002:
                    onError(result.getResultMsg(), result.getResultCode());
                    break;
                case 1003:
                    onError(result.getResultMsg(), result.getResultCode());
                    break;
                case 2000:
                    onError(result.getResultMsg(), result.getResultCode());
                    break;
                case 3000:
                    onError(result.getResultMsg(), result.getResultCode());
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

    protected abstract void onSuccess(T result) throws IOException;

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
