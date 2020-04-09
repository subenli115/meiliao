package com.ziran.meiliao.ui.workshops.presenter;

import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.OrderCreateResultBean;
import com.ziran.meiliao.ui.bean.StringDataBean;
import com.ziran.meiliao.ui.workshops.contract.QJGTeacherBuyContract;

import java.util.Map;


/**
 * des:图片列表
 * Created by xsf
 * on 2016.09.12:01
 */
public class QJGTeacherBuyPresenter extends QJGTeacherBuyContract.Presenter {


    @Override
    public void getCode(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<StringDataBean>(StringDataBean.class) {

            @Override
            public void onSuccess(StringDataBean result) {
                mView.showGetCodeResult(result);
            }
        });

    }

    @Override
    public void checkCode(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<Result>(Result.class) {
            @Override
            protected void onSuccess(Result result) {
                mView.showCheckCodeResult(result);
            }
        });
    }

    @Override
    public void submit(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<OrderCreateResultBean>(OrderCreateResultBean.class) {
            @Override
            protected void onSuccess(OrderCreateResultBean result) {
                mView.showSubmitResult(result.getData());
            }
        });

    }
}
