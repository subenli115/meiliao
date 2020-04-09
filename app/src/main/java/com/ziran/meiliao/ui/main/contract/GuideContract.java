package com.ziran.meiliao.ui.main.contract;

import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.GuideListBean;
import com.ziran.meiliao.ui.bean.GuideSaveBean;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/1/14.
 */

public interface GuideContract {

    interface View   extends BaseView {
        void showGuideSaveData(GuideSaveBean.DataBean result);
        void showGuideListData(List<GuideListBean.DataBean> data);

    }

    abstract  class Presenter extends BasePresenter<GuideContract.View, CommonModel> {
        public abstract void getGuideListData(String url, Map<String,String> map );
        public abstract void getGuideSaveData(String url, Map<String,String> map );
    }

}
