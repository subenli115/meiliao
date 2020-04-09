package com.ziran.meiliao.ui.decompressionmuseum.contract;

import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.DailyMindBean;
import com.ziran.meiliao.ui.bean.ExerciseUploadBean;

import java.util.Map;

/**
 * 练习馆契约类
 */
public interface MindfulnessHallCountTimeContract {

    interface View   extends BaseView {
        void practiceUploadResult(ExerciseUploadBean result);
        void showUpdateDailyMind(Result result);
        void showGainByDay(Result result);
        void showDailyMind(DailyMindBean.DataBean result);
    }

    abstract  class Presenter extends BasePresenter<View, CommonModel> {
        public abstract void postPractice(String url, Map<String,String> map );
        public abstract void updateDailyMind(String url, Map<String,String> map );
        public abstract void getDailyMind(String url, Map<String,String> map );
        public abstract void getByDay(String url, Map<String,String> map );
    }
}
