package com.ziran.meiliao.ui.workshops.contract;

import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.OrderCreateResultBean;
import com.ziran.meiliao.ui.bean.StringDataBean;

import java.util.Map;

/**
 * des:图片列表contract
 * Created by xsf
 * on 2016.09.14:38
 */
public interface QJGTeacherBuyContract {
    interface View extends BaseView {
        void showGetCodeResult(StringDataBean result);

        void showCheckCodeResult(Result result);

        void showSubmitResult(OrderCreateResultBean.DataBean result);
    }

    abstract class Presenter extends BasePresenter<View, CommonModel> {
        public abstract void getCode(String url, Map<String, String> params);

        public abstract void checkCode(String url, Map<String, String> params);

        public abstract void submit(String url, Map<String, String> params);
    }
}
