package com.ziran.meiliao.ui.workshops.contract;

import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.StringDataBean;
import com.ziran.meiliao.ui.bean.TeacherDetailBean;

import java.util.Map;

/**
 * des:图片列表contract
 * Created by xsf
 * on 2016.09.14:38
 */
public interface TeacherDetailContract {

    interface View   extends BaseView {
        void showData(TeacherDetailBean.DataBean result);
        void showCollectResult(boolean isCollect);

        void showCheckInfo(StringDataBean result);

    }

    abstract  class Presenter extends BasePresenter<View, CommonModel> {
        public abstract void postData(String url, Map<String, String> params);
        public abstract void postCollect(String url, Map<String, String> params);
        public abstract void checkInfo(String url, Map<String, String> params);
    }
}
