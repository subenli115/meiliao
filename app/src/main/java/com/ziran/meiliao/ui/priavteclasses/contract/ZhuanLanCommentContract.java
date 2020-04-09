package com.ziran.meiliao.ui.priavteclasses.contract;

import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.SubscribeCommentListBean;
import com.ziran.meiliao.ui.bean.SubscribeCommentResultBean;

import java.util.List;
import java.util.Map;

/**
 * des:图片列表contract
 * Created by xsf
 * on 2016.09.14:38
 */
public interface ZhuanLanCommentContract {

    interface View extends BaseView {
        //返回获取的图片
        void showCommentToMe(List<SubscribeCommentListBean.DataBean> result);

        void showCommentToTarget(List<SubscribeCommentListBean.DataBean> result);

        void showCommentResult(SubscribeCommentResultBean.DataBean result);
    }

    abstract class Presenter extends BasePresenter<View, CommonModel> {
        //发起获取图片请求
        public abstract void getCommentByMe(String url, Map<String, String> params);

        public abstract void getCommentByTarget(String url, Map<String, String> params);

        public abstract void postComment(String url, Map<String, String> params);
    }
}
