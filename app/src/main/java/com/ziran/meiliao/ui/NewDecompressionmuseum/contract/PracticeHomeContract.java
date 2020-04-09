package com.ziran.meiliao.ui.NewDecompressionmuseum.contract;

import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.JdxShareBean;
import com.ziran.meiliao.ui.bean.PracticeHomeBean;
import com.ziran.meiliao.ui.bean.PracticeRecordBean;

import java.util.Map;

/**
 */
public interface PracticeHomeContract {

    interface View   extends BaseView {
        void showHomeData(PracticeHomeBean.DataBean result);
        void showRecordData(PracticeRecordBean.DataBean result);
        void showPracticeStatusData(JdxShareBean.DataBean result);
    }

    abstract  class Presenter extends BasePresenter<View, CommonModel> {
        public abstract void changePracticeStatus(String url, Map<String,String> map);
        public abstract void getPracticeRecordData(String url, Map<String,String> map );
        public abstract void getPracticeHomeData(String url, Map<String,String> map );
    }
}
