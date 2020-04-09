package com.ziran.meiliao.ui.NewDecompressionmuseum.contract;

import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.JdxShareBean;
import com.ziran.meiliao.ui.bean.PracticeSaveBean;
import com.ziran.meiliao.ui.bean.PracticeTwoDetailBean;

import java.util.Map;

/**
 * Created by Administrator on 2018/9/21.
 */

public interface PracticeTwoDataContract {

    interface View   extends BaseView {

        void showTwoData(PracticeTwoDetailBean.DataBean result);
        void showTwoSaveData(PracticeSaveBean.DataBean result);
        void showPracticeStatusData(JdxShareBean.DataBean result);
    }

    abstract  class Presenter extends BasePresenter<PracticeTwoDataContract.View, CommonModel> {

        public abstract void getPracticeTwoData(String url, Map<String,String> map );
        public abstract void changePracticeStatus(String url, Map<String,String> map);
        public abstract void getPracticeTwoSaveData(String url, Map<String,String> map );
    }
}