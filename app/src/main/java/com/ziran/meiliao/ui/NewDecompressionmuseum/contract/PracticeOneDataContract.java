package com.ziran.meiliao.ui.NewDecompressionmuseum.contract;

import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.JdxShareBean;
import com.ziran.meiliao.ui.bean.PracticeOneDetailBean;
import com.ziran.meiliao.ui.bean.PracticeSaveBean;

import java.util.Map;

/**
 * Created by Administrator on 2018/9/21.
 */

public interface PracticeOneDataContract {

    interface View   extends BaseView {

        void showOneData(PracticeOneDetailBean.DataBean result);
        void showOneSaveData(PracticeSaveBean.DataBean result);
        void showPracticeStatusData(JdxShareBean.DataBean result);
    }

    abstract  class Presenter extends BasePresenter<PracticeOneDataContract.View, CommonModel> {
        public abstract void changePracticeStatus(String url, Map<String,String> map);
        public abstract void getPracticeOneData(String url, Map<String,String> map );
        public abstract void getPracticeOneSaveData(String url, Map<String,String> map );
    }
}