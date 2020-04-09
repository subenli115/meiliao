package com.ziran.meiliao.ui.settings.contract;

import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.BankInfoBean;
import com.ziran.meiliao.ui.bean.WithDrawalsBean;

import java.util.Map;

/**
 * des:图片列表contract
 * Created by xsf
 * on 2016.09.14:38
 */
public interface WithDrawalsContract {


    interface View   extends BaseView {
        //返回获取的图片
        void showListBank(BankInfoBean result);
        void showDrawCash(WithDrawalsBean.DataBean result);
    }

    abstract  class Presenter extends BasePresenter<View, CommonModel> {
        //发起获取图片请求
        public abstract void getListBank(String url,Map<String, String> map);
        public abstract void postDrawCash(String url,Map<String,String> map);
    }
}
