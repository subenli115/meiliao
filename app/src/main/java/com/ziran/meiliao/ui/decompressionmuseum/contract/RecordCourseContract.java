package com.ziran.meiliao.ui.decompressionmuseum.contract;

import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.PracticeListBean;
import com.ziran.meiliao.ui.bean.RecordTotalBean;

import java.util.List;
import java.util.Map;

/**
 *  正念记录契约类
 */
public interface RecordCourseContract {

    interface View   extends BaseView {

        void showTotalTime(RecordTotalBean.DataBean result);
        void showCourseList(List<PracticeListBean.DataBean> result);
    }

    abstract  class Presenter extends BasePresenter<View, CommonModel> {

        public abstract void getTotalTime(String url, Map<String,String> map );
        public abstract void getCourseList(String url, Map<String,String> map );
    }
}
