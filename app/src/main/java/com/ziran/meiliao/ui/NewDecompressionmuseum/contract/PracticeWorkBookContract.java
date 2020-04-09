package com.ziran.meiliao.ui.NewDecompressionmuseum.contract;

import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.JdxShareBean;
import com.ziran.meiliao.ui.bean.PracticeCalendarBean;
import com.ziran.meiliao.ui.bean.PracticeHeadBean;
import com.ziran.meiliao.ui.bean.PracticeJoinBean;
import com.ziran.meiliao.ui.bean.PracticeStartBean;
import com.ziran.meiliao.ui.bean.PracticeStatusBean;
import com.ziran.meiliao.ui.bean.PracticeWorkBookBean;

import java.util.Map;

/**
 * 练习册
 */
public interface PracticeWorkBookContract {

    interface View   extends BaseView {

        void showWookBookData(PracticeWorkBookBean.DataBean result);
        void showPracticeJoinData(PracticeJoinBean.DataBean result);
        void showPracticeStartData(PracticeStartBean.DataBean result);
        void showPracticeCalendarData(PracticeCalendarBean.DataBean result);
        void showPracticeHeadData(PracticeHeadBean.DataBean result);
        void showPracticeStatusData(JdxShareBean.DataBean result);
        void showPracticeCalendarStatusData(PracticeStatusBean.DataBean result);
    }

    abstract  class Presenter extends BasePresenter<View, CommonModel> {

        public abstract void getPracticeWookBookData(String url, Map<String,String> map);
        public abstract void getPracticeJoin(String url, Map<String,String> map);
        public abstract void getPracticeStart(String url, Map<String,String> map);
        public abstract void getPracticeCalendar(String url, Map<String,String> map);
        public abstract void changePracticeStatus(String url, Map<String,String> map);
        public abstract void getPracticeHead(String url, Map<String,String> map);
        public abstract void getPracticeStatus(String url, Map<String,String> map);
    }
}
