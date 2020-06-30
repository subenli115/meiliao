package com.ziran.meiliao.ui.base;

import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.im.activity.BaseActivity;

import java.util.Map;

/**
 * author 吴祖清
 * create  2017/2/8
 * des     常用的MVP模式中的主持者()
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */


public class CommonPresenter extends CommonContract.Presenter {


    public <D extends Result> void postData(String url, Map<String, String> params, final Class<D> clz) {
        mModel.post(url, params, new NewRequestCallBack<D>(clz, mView) {
            @Override
            public void onSuccess(D result) {
                mView.stopLoading();
                mView.returnData(result);
            }

            @Override
            public void onError(String msg, int code) {
                super.onError(msg, code);
                ( (CommonHttpActivity)mView).stopProgressDialog();
                ToastUitl.showShort(msg);

            }
        });
    }



    @Override
    public <D extends Result> void postAction(String url, Map<String, String> params, final Class<D> clz) {
        mModel.post(url, params, new NewRequestCallBack<D>(clz, mView) {
            @Override
            public void onSuccess(D result) {
                mView.stopLoading();
                if (mView instanceof CommonContract.ActionView) {
                    ((CommonContract.ActionView) mView).returnAction(result);
                } else {
                    mView.returnData(result);
                }
            }

            @Override
            public void onError(String msg, int code) {
                super.onError(msg, code);
            }
        });
    }

    @Override
    public <D extends Result> void getData(String url, Map<String, String> params, final Class<D> clz) {
        mModel.getData(url, params, new NewRequestCallBack<D>(clz, mView) {
            @Override
            public void onSuccess(D result) {
                mView.returnData(result);
            }
        });
    }



    @Override
    public <D extends Result> void getDataOneHead(String url, String params,String token, final Class<D> clz) {
        mModel.getDataOneHead(url, params,token, new NewRequestCallBack<D>(clz, mView) {
            @Override
            public void onSuccess(D result) {
                mView.returnData(result);
            }
        });
    }
}
