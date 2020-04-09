package com.ziran.meiliao.ui.priavteclasses.presenter;

import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.MediaDetailBean;
import com.ziran.meiliao.ui.bean.StudyFinishBean;
import com.ziran.meiliao.ui.bean.SubscribeCommentListBean;
import com.ziran.meiliao.ui.bean.SubscribeCommentResultBean;
import com.ziran.meiliao.ui.priavteclasses.contract.ZLTextImageContract;

import java.util.Map;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/5/11 10:46
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/5/11$
 * @updateDes ${TODO}
 */

public class ZLTextImagePresenter extends ZLTextImageContract.Presenter {


    @Override
    public void getData(String url, Map<String, String> params) {

    }
    @Override
    public void postComment(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<SubscribeCommentResultBean>(SubscribeCommentResultBean.class) {
            @Override
            public void onSuccess(SubscribeCommentResultBean result) {
                switch (result.getResultCode()) {
                    case 1:
                        mView.commentResult(result.getData());
                        break;
//                    case 10:
//                        ToastUitl.showShort(result.getResultMsg());
//                        break;
                }
            }
        });
    }
    @Override
    public void postMediaDetail(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<MediaDetailBean>(MediaDetailBean.class) {
            @Override
            protected void onSuccess(MediaDetailBean result) {
                mView.showMediaDetail(result.getData());
            }
        });
    }

    @Override
    public void getCommentList(String url, Map<String, String> params) {
        mModel.getData(url, params, new NewRequestCallBack<SubscribeCommentListBean>(SubscribeCommentListBean.class) {
            @Override
            public void onSuccess(SubscribeCommentListBean result) {
                mView.showCommentList(result.getData());
            }
        });
    }

    @Override
    public void postBuySpecColumn(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<Result>(Result.class) {
            @Override
            public void onSuccess(Result result) {
                mView.showBuySpecColumnResult(result);
            }
        });
    }


    @Override
    public void postCollect(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<Result>(Result.class) {
            @Override
            public void onSuccess(Result result) {
                mView.showCollectResult(result);
            }
        });
    }

    @Override
    public void postLike(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<Result>(Result.class) {
            @Override
            public void onSuccess(Result result) {
                mView.showLikeResult(result);
            }
        });
    }

    @Override
    public void postPriaseComment(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<Result>(Result.class) {
            @Override
            public void onSuccess(Result result) {
                mView.showLikeResult(result);
            }
        });
    }

    @Override
    public void getCertificate(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<StudyFinishBean>(StudyFinishBean.class) {
            @Override
            public void onSuccess(StudyFinishBean result) {
                mView.showCertificate(result.getData());
            }
        });
    }

    @Override
    public void updateStudyFinish(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<Result  >(Result.class) {
            @Override
            protected void onSuccess(Result result) {
                mView.showUpdateStudyFinish(result);
            }
        });
    }
}
