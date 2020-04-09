package com.ziran.meiliao.ui.priavteclasses.contract;

import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.SJKLiveDetailProfileBean;
import com.ziran.meiliao.ui.bean.TrailerDetailBean;

import java.util.Map;

/**
 * des:图片列表contract
 * Created by xsf
 * on 2016.09.14:38
 */
public interface SJKCourseProfileContract {

    interface View extends BaseView {
        //返回获取的图片
        void showCourseDetail(SJKLiveDetailProfileBean.DataBean bean);
        void showCourseProfile(TrailerDetailBean.DataBean bean);
    }

    abstract class Presenter extends BasePresenter<View, CommonModel> {
        //发起获取图片请求
        public abstract void getCourseDetail(String url, Map<String, String> params);
        public abstract void getCourseProfile(String url, Map<String, String> params);
    }
}
