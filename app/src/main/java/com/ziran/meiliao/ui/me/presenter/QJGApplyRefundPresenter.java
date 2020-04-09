package com.ziran.meiliao.ui.me.presenter;

import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.OrderCreateResultBean;
import com.ziran.meiliao.ui.bean.OrderInfoBean;
import com.ziran.meiliao.ui.me.contract.QJGApplyRefundContract;

import java.util.Map;


/**
 * des:图片列表
 * Created by xsf
 * on 2016.09.12:01
 */
public class QJGApplyRefundPresenter extends QJGApplyRefundContract.Presenter {


    @Override
    public void postData(String url, Map<String, String> params) {

        mModel.post(url, params, new NewRequestCallBack<OrderInfoBean>(OrderInfoBean.class, mView) {
            @Override
            protected void onSuccess(OrderInfoBean result) {
                mView.showData(result.getData());
            }
        });
    }

    @Override
    public void submit(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<OrderCreateResultBean>(OrderCreateResultBean.class) {
            @Override
            protected void onSuccess(OrderCreateResultBean result) {
                mView.submitResult(result.getData());
            }
        });
    }

}
