package com.ziran.meiliao.ui.helpserver.contract;

import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.OrderCreateResultBean;
import com.ziran.meiliao.ui.bean.OrderInfoBean;

import java.util.Map;

/**
 * des:图片列表contract
 * Created by xsf
 * on 2016.09.14:38
 */
public interface QJGApplyRefundContract {

    interface View   extends BaseView {
        void showData(OrderInfoBean.DataBean result);
        void submitResult(OrderCreateResultBean.DataBean result);
    }

    abstract  class Presenter extends BasePresenter<View, CommonModel> {
        public abstract void postData(String url, Map<String, String> params);
        public abstract void submit(String url, Map<String, String> params);
    }
}
