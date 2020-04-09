package com.ziran.meiliao.ui.priavteclasses.contract;

import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.GetTicketBean;

import java.util.Map;

/**
 * des:图片列表contract
 * Created by xsf
 * on 2016.09.14:38
 */
public interface GetVideoCouponContract {

    interface View extends BaseView {
        //返回获取的图片
        void getVideoCouponData(GetTicketBean.DataBean data);

    }

    abstract class Presenter extends BasePresenter<View, CommonModel> {
        //发起获取图片请求
        public abstract void getVideoCouponData(String url,Map<String, String> params);

    }
}
