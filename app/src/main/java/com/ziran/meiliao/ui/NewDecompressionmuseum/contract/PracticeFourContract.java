package com.ziran.meiliao.ui.NewDecompressionmuseum.contract;

import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.JdxShareBean;
import com.ziran.meiliao.ui.bean.PracticeFourCheckBean;
import com.ziran.meiliao.ui.bean.PracticeFourDetailBean;
import com.ziran.meiliao.ui.bean.PracticeSaveBean;

import java.util.Map;

/**
 * Created by Administrator on 2018/9/25.
 */

public interface PracticeFourContract {

    interface View   extends BaseView {

        void showFourData(PracticeFourDetailBean.DataBean result);
        void showPracticeStatusData(JdxShareBean.DataBean result);
        void showFourSaveData(PracticeSaveBean result);
        void showFourCheckData(PracticeFourCheckBean.DataBean result);
    }

    abstract  class Presenter extends BasePresenter<PracticeFourContract.View, CommonModel> {

        public abstract void getPracticeFourData(String url, Map<String,String> map );
        public abstract void getPracticeFourSaveData(String url, Map<String,String> map );
        public abstract void getPracticeFourCheckData(String url, Map<String,String> map );
        public abstract void changePracticeStatus(String url, Map<String,String> map);

    }

}
