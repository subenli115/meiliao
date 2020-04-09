package com.ziran.meiliao.ui.priavteclasses.contract;

import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.ZhuanLanBigInBean;

import java.util.Map;

/**
 * des:图片列表contract
 * Created by xsf
 * on 2016.09.14:38
 */
public interface SJKZhuanLanBigInContract {

    interface View extends BaseView {
        //返回获取的图片
        void showData(ZhuanLanBigInBean.DataBean result);

        void showGzResult(Result result);
        void showBuyResult(Result result);
    }

    abstract class Presenter extends BasePresenter<View, CommonModel> {
        //发起获取图片请求
        public abstract void getData(String url, Map<String, String> params);

        public abstract void postGz(String url, Map<String, String> params);
        public abstract void postBuy(String url, Map<String, String> params);
        public abstract void postListenUp(String url, Map<String, String> params);
    }
}
