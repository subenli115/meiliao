package com.ziran.meiliao.ui.priavteclasses.contract;

import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.StudyFinishBean;
import com.ziran.meiliao.ui.bean.SubscribeAudioDataBean;
import com.ziran.meiliao.ui.bean.SubscribeCommentListBean;
import com.ziran.meiliao.ui.bean.SubscribeCommentResultBean;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface SubscribeVideoContract {

    interface View extends BaseView {
        //返回获取的图片
        void showPraiseComment(Result bean);

        void showCollectResult(Result bean);

        void showLikeResult(Result bean);
        void showBuyResult(Result bean);

        void commentResult(SubscribeCommentResultBean.DataBean bean);

        void deleteCommentResult(Result bean);

        void showCommentList(List<SubscribeCommentListBean.DataBean> bean);

        void showData(SubscribeAudioDataBean.DataBean bean);

        void showCertificate(StudyFinishBean.DataBean bean);
        void showUpdateStudyFinish(Result bean);
    }

    abstract class Presenter extends BasePresenter<View, CommonModel> {
        //发起获取图片请求
        public abstract void postPriaseComment(String url, Map<String, String> params);

        public abstract void postCollect(String url, Map<String, String> params);

        public abstract void getData(String url, Map<String, String> params);

        public abstract void postLike(String url, Map<String, String> params);

        public abstract void postComment(String url, Map<String, String> params);

        public abstract void postBuy(String url, Map<String, String> params);

        public abstract void deleteComment(String url, Map<String, String> params);

        public abstract void postListenUp(String url, Map<String, String> params);

        public abstract void getCommentList(String url, Map<String, String> params);

        public abstract void getCertificate(String url, Map<String, String> params);

        public abstract void updateStudyFinish(String url, Map<String, String> params);

    }
}
