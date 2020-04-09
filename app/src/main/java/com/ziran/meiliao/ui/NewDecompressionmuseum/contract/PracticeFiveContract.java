package com.ziran.meiliao.ui.NewDecompressionmuseum.contract;

import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.JdxShareBean;
import com.ziran.meiliao.ui.bean.PracticeFiveDetailBean;
import com.ziran.meiliao.ui.bean.PracticeSaveBean;

import java.util.Map;

/**
 * Created by Administrator on 2018/9/25.
 */

public interface PracticeFiveContract {

    interface View   extends BaseView {

        void showFiveData(PracticeFiveDetailBean.DataBean result);
        void showPracticeStatusData(JdxShareBean.DataBean result);
        void showFiveSaveData(PracticeSaveBean.DataBean result);
    }

    abstract  class Presenter extends BasePresenter<PracticeFiveContract.View, CommonModel> {
        public abstract void changePracticeStatus(String url, Map<String,String> map);
        public abstract void getPracticeFiveData(String url, Map<String,String> map );
        public abstract void getPracticeFiveSaveData(String url, Map<String,String> map );
    }

}
