package com.ziran.meiliao.ui.priavteclasses.contract;

import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.TeacherGoinBean;
import com.ziran.meiliao.ui.bean.TeacherPageBean;

import java.util.Map;

/**
 * des:图片列表contract
 * Created by xsf
 * on 2016.09.14:38
 */
public interface MainOpenLiveContract {

    interface View extends BaseView {
        //返回获取的图片
        void showData(TeacherPageBean.DataBean data);

        void openLive(TeacherGoinBean.DataBean data);
    }

    abstract class Presenter extends BasePresenter<View, CommonModel> {
        //发起获取图片请求
        public abstract void getTeacherIndex(String url, Map<String, String> params);

        public abstract void checkOpenLive(String url, Map<String, String> params);
    }
}
