package com.ziran.meiliao.ui.priavteclasses.presenter;

import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.StudyFinishBean;
import com.ziran.meiliao.ui.bean.SubscribeAudioDataBean;
import com.ziran.meiliao.ui.bean.SubscribeCommentListBean;
import com.ziran.meiliao.ui.bean.SubscribeCommentResultBean;
import com.ziran.meiliao.ui.priavteclasses.contract.SubscribeVideoContract;

import java.util.Map;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/5/11 10:46
 * @des ${订阅音乐契约类}
 * @updateAuthor $Author$
 * @updateDate 2017/5/11$
 * @updateDes ${TODO}
 */

public class SubscribeVideoPresenter extends SubscribeVideoContract.Presenter {


    @Override
    public void postPriaseComment(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<Result>(Result.class) {
            @Override
            public void onSuccess(Result result) {
                mView.showPraiseComment(result);
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
    public void getData(String url, Map<String, String> params) {
        mModel.getData(url, params, new NewRequestCallBack<SubscribeAudioDataBean>(SubscribeAudioDataBean.class,mView) {
            @Override
            public void onSuccess(SubscribeAudioDataBean result) {
                mView.showData(result.getData());

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
    public void postComment(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<SubscribeCommentResultBean>(SubscribeCommentResultBean.class) {
            @Override
            public void onSuccess(SubscribeCommentResultBean result) {
                mView.commentResult(result.getData());
            }
        });
    }

    @Override
    public void postBuy(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<Result>(Result.class) {
            @Override
            public void onSuccess(Result result) {
                switch (result.getResultCode()) {
                    case 1:
                        mView.showBuyResult(result);
                        break;
                    case 10:
                        ToastUitl.showShort(result.getResultMsg());
                        break;
                }
            }
        });
    }

    @Override
    public void deleteComment(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<Result>(Result.class) {
            @Override
            public void onSuccess(Result result) {
                mView.deleteCommentResult(result);
            }
        });
    }

    @Override
    public void postListenUp(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<Result>(Result.class) {
            @Override
            public void onSuccess(Result result) {
            }
        });
    }

    @Override
    public void getCommentList(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<SubscribeCommentListBean>(SubscribeCommentListBean.class) {
            @Override
            public void onSuccess(SubscribeCommentListBean result) {
                mView.showCommentList(result.getData());
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
