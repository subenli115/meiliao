package com.ziran.meiliao.ui.workshops.contract;

import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.CrowdFundingDetailBean;

import java.util.Map;

/**
 * des:图片列表contract
 * Created by xsf
 * on 2016.09.14:38
 */
public interface CrowdFundingDetailContract {

    interface View   extends BaseView {
        void showData(CrowdFundingDetailBean result);
        void showCollectResult(boolean isCollect);
    }

    abstract  class Presenter extends BasePresenter<View, CommonModel> {
        public abstract void postData(String url, Map<String, String> params);
        public abstract void postCollect(String url, Map<String, String> params);
    }
}
