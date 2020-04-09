package com.ziran.meiliao.ui.NewDecompressionmuseum.contract;

import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.JdxShareBean;
import com.ziran.meiliao.ui.bean.PracticeSaveBean;
import com.ziran.meiliao.ui.bean.PracticeThreeBean;
import com.ziran.meiliao.ui.bean.PracticeThreeDetailCheckBean;

import java.util.Map;

/**
 * Created by Administrator on 2018/9/21.
 */

public interface PracticeThreeDataContract {

    interface View   extends BaseView {

        void showThreeData(PracticeThreeBean.DataBean result);
        void showThreeCheckData(PracticeThreeDetailCheckBean.DataBean result);
        void showThreeSaveData(PracticeSaveBean.DataBean result);
        void showPracticeStatusData(JdxShareBean.DataBean result);
    }

    abstract  class Presenter extends BasePresenter<PracticeThreeDataContract.View, CommonModel> {

        public abstract void getPracticeThreeData(String url, Map<String,String> map );
        public abstract void getPracticeThreeSaveData(String url, Map<String,String> map );
        public abstract void changePracticeStatus(String url, Map<String,String> map);
        public abstract void getPracticeThreeCheckData(String url, Map<String,String> map );
    }
}