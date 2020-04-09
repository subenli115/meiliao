package com.ziran.meiliao.ui.decompressionmuseum.contract;

import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.PractiveChartBean;
import com.ziran.meiliao.ui.bean.PunchHisBean;
import com.ziran.meiliao.ui.bean.RecordChartBean;
import com.ziran.meiliao.ui.bean.RecordTotalBean;
import com.ziran.meiliao.ui.bean.UserNoteBean;

import java.util.List;
import java.util.Map;

/**
 *  正念记录契约类
 */
public interface RecordContract {

    interface View   extends BaseView {

        void showTotalTime(RecordTotalBean.DataBean result);
        void showTagByMonth(List<String> result);
        void showTotalTimeByDate(Result result);
        void showNewChartData(PractiveChartBean.DataBean result);
        void showPunchHisData(PunchHisBean.DataBean data);
        void showChartData(RecordChartBean result);
        void deleteNoteResult(Result result);
        void showUserNote(List<UserNoteBean.DataBean> result);
    }

    abstract  class Presenter extends BasePresenter<View, CommonModel> {
        public abstract void getPunchHisData(String url, Map<String,String> map );
        public abstract void getTagByMonth(String url, Map<String,String> map );
        public abstract void getTotalTime(String url, Map<String,String> map );
        public abstract void getTotalTimeByDate(String url,Map<String,String> map);
        public abstract void getChartData(String url,Map<String,String> map);
        public abstract void getNewChartData(String url, Map<String,String> map );
        public abstract void getUserNote(String url,Map<String,String> map);
        public abstract void postDeleteNote(String url,Map<String,String> map);
    }
}
