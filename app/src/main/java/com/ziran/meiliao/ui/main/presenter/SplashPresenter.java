package com.ziran.meiliao.ui.main.presenter;

import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.AdvertBean;
import com.ziran.meiliao.ui.bean.StringDataBean;
import com.ziran.meiliao.ui.bean.VersionBean;
import com.ziran.meiliao.ui.bean.VersionNewBean;
import com.ziran.meiliao.ui.main.contract.SplashContract;

import java.util.Map;


/**
 * des:图片列表
 * Created by xsf
 * on 2016.09.12:01
 */
public class SplashPresenter extends SplashContract.Presenter{

    @Override
    public void checkToken(Map<String, String> params) {
        mModel.post(ApiKey.USER_CHECK_TOKEN, params, new NewRequestCallBack<StringDataBean>(StringDataBean.class) {
            @Override
            protected void onSuccess(StringDataBean result) {
                mView.showCheckTokenResult("false".equals(result.getNornemData()));
            }

            @Override
            public void onError(String msg, int code) {
                mView.showCheckTokenResult(true);
            }
        });
    }

    @Override
    public void getVersion(Map<String, String> params) {
        mModel.getData(ApiKey.ADMIN_APPVERSION_APPVERSION, params, new NewRequestCallBack<VersionNewBean>(VersionNewBean.class) {
            @Override
            protected void onSuccess(VersionNewBean result) {

                mView.showVersionResult(result);
            }
        });
    }

    @Override
    public void
    getAdvert(Map<String, String> params) {
        mModel.post(ApiKey.ADVERT_GET_ADVERT, params, new NewRequestCallBack<AdvertBean>(AdvertBean.class) {
            @Override
            protected void onSuccess(AdvertBean result) {
                mView.showAdvertResult(result.getData());
            }

            @Override
            public void onError(String msg, int code) {
                mView.showAdvertResultError();
            }

        });
    }
}
