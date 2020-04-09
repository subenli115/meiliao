package com.ziran.meiliao.ui.priavteclasses.presenter;

import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.SubscribeCommentListBean;
import com.ziran.meiliao.ui.bean.SubscribeCommentResultBean;
import com.ziran.meiliao.ui.priavteclasses.contract.ZhuanLanCommentContract;

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

public class ZhuanLanCommentPresenter extends ZhuanLanCommentContract.Presenter {


    @Override
    public void getCommentByMe(String url, Map<String, String> params) {
        mModel.getData(url, params, new NewRequestCallBack<SubscribeCommentListBean>(SubscribeCommentListBean.class,mView) {
            @Override
            public void onSuccess(SubscribeCommentListBean result) {
                mView.showCommentToMe(result.getData());
            }
        });
    }

    @Override
    public void getCommentByTarget(String url, Map<String, String> params) {
        mModel.getData(url, params, new NewRequestCallBack<SubscribeCommentListBean>(SubscribeCommentListBean.class,mView) {
            @Override
            public void onSuccess(SubscribeCommentListBean result) {
                mView.showCommentToTarget(result.getData());
            }
        });
    }

    @Override
    public void postComment(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<SubscribeCommentResultBean>(SubscribeCommentResultBean.class) {
            @Override
            public void onSuccess(SubscribeCommentResultBean result) {
                mView.showCommentResult(result.getData());
            }
        });
    }
}
