package com.ziran.meiliao.ui.workshops.contract;

import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.OrderCreateResultBean;
import com.ziran.meiliao.ui.bean.TeamCustomBean;

import java.util.Map;

/**
 * des:图片列表contract
 * Created by xsf
 * on 2016.09.14:38
 */
public interface TeamCustomContract {
    interface View   extends BaseView {
        void showMissionBuiltInfo(TeamCustomBean.DataBean result);
        void postMissionBuilt(OrderCreateResultBean.DataBean result);
    }
    abstract  class Presenter extends BasePresenter<View, CommonModel> {
        public abstract void getListMissionBuiltInfo(String url, Map<String, String> params);
        public abstract void postMissionBuilt(String url, Map<String, String> params);
    }
}
