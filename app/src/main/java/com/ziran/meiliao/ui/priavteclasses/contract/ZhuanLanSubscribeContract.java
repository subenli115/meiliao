package com.ziran.meiliao.ui.priavteclasses.contract;

import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.RecordListBean;

import java.util.List;
import java.util.Map;

/**
 * des:图片列表contract
 * Created by xsf
 * on 2016.09.14:38
 */
public interface ZhuanLanSubscribeContract {

    interface View extends BaseView {

        void showBuySpecColumnResult(Result userCount);

        void showRecordList(List<RecordListBean.DataBean> beanList);

    }

    abstract class Presenter extends BasePresenter<View, CommonModel> {

        public abstract void getRecordList(String url, Map<String, String> params);
        public abstract void postBuySpecColumn(String url, Map<String, String> params);

    }
}
