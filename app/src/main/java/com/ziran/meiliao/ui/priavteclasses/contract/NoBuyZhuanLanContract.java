package com.ziran.meiliao.ui.priavteclasses.contract;

import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.NewMediaAndTextBean;
import com.ziran.meiliao.ui.bean.StudyFinishBean;
import com.ziran.meiliao.ui.bean.SubscribeCommentListBean;
import com.ziran.meiliao.ui.bean.SubscribeCommentResultBean;
import com.ziran.meiliao.ui.priavteclasses.presenter.BuyColumnPresenter;

import java.util.List;
import java.util.Map;

/**
 * des:图片列表contract
 * Created by xsf
 * on 2016.09.14:38
 */
public interface NoBuyZhuanLanContract {

    interface View extends BaseView {
        //返回获取的图片
        void showData(NewMediaAndTextBean.DataBean result);


        void showBuySpecColumnResult(Result userCount);

        void showLikeResult(Result bean);
        void showCertificate(StudyFinishBean.DataBean bean);
        void commentResult(SubscribeCommentResultBean.DataBean bean);
        void showCommentList(List<SubscribeCommentListBean.DataBean> bean);
    }

    abstract class Presenter extends BuyColumnPresenter<View, CommonModel> {
        //发起获取图片请求
        public abstract void getData(String url, Map<String, String> params);
        public abstract void postPriaseComment(String url, Map<String, String> params);
        public abstract void postListenUp(String url, Map<String, String> params);
        public abstract void postLike(String url, Map<String, String> params);
        public abstract void getCertificate(String url, Map<String, String> params);
        public abstract void postComment(String url, Map<String, String> params);
        public abstract void getCommentList(String url, Map<String, String> params);

    }
}
