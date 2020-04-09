package com.ziran.meiliao.ui.workshops.contract;

import com.ziran.meiliao.common.base.BaseModel;
import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.MultiItemBean;

import java.util.List;
import java.util.Map;

/**
 * des:图片列表contract
 * Created by xsf
 * on 2016.09.14:38
 */
public interface QJGMainContract {
    interface Model extends BaseModel {
        //执行下载用户头像
        void getData(Map<String, String> params, final OkHttpClientManager.ResultCallback callback);
        //执行上传融云Token

    }

    interface View   extends BaseView {
        void showData(List<MultiItemBean> result);

    }

    abstract  class Presenter extends BasePresenter<View, CommonModel> {
        public abstract void getData(String url, Map<String, String> params);
    }
}
