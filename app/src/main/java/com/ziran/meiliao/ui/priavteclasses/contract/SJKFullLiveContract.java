package com.ziran.meiliao.ui.priavteclasses.contract;

import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.GiftBean;
import com.ziran.meiliao.ui.bean.GiveGiftResultBean;
import com.ziran.meiliao.ui.bean.SJKLiveDetailProfileBean;

import java.util.List;
import java.util.Map;

/**
 * des:图片列表contract
 * Created by xsf
 * on 2016.09.14:38
 */
public interface SJKFullLiveContract {

    interface View extends BaseView {
        //返回获取的图片
        void showCourseData(SJKLiveDetailProfileBean.DataBean data);

        void setCollect(Result result);

        void setLike(Result result);

        void setUserCount(String userCount);

        void onPayResult(String url);

        void showListGift(List<GiftBean.DataBean> beanList);

        void giveGiftResult(GiveGiftResultBean.DataBean result);
    }

    abstract class Presenter extends BasePresenter<View, CommonModel> {
        //发起获取图片请求
        public abstract void getCourseData(String url, Map<String, String> parmars);

        public abstract void postLike(String url, Map<String, String> parmars);

        public abstract void postCollect(String url, Map<String, String> parmars);

        public abstract void postShiKan(String url, Map<String, String> parmars);

        public abstract void postUserCount(String url, Map<String, String> parmars);

        public abstract void listGift(String url, Map<String, String> parmars);

        public abstract void postGiveGift(String url, Map<String, String> parmars);

        public abstract void buyCourse(String url, Map<String, String> parmars);

        public abstract void watchUp(String url, Map<String, String> parmars);

        public abstract void usedTick(String url, Map<String, String> parmars);

        public abstract void updateStudy(String url, Map<String, String> parmars);
    }
}
