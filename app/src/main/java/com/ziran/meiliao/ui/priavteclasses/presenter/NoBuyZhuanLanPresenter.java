package com.ziran.meiliao.ui.priavteclasses.presenter;

import com.ziran.meiliao.app.WpyxConfig;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.NewMediaAndTextBean;
import com.ziran.meiliao.ui.bean.StudyFinishBean;
import com.ziran.meiliao.ui.bean.SubscribeCommentListBean;
import com.ziran.meiliao.ui.bean.SubscribeCommentResultBean;
import com.ziran.meiliao.ui.bean.ZhuanLanBigInBean;
import com.ziran.meiliao.ui.priavteclasses.contract.NoBuyZhuanLanContract;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/5/11 10:46
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/5/11$
 * @updateDes ${TODO}
 */

public class NoBuyZhuanLanPresenter extends NoBuyZhuanLanContract.Presenter {

    @Override
    public void getData(String url, Map<String, String> params) {
        mModel.getData(url, params, new NewRequestCallBack<NewMediaAndTextBean>(NewMediaAndTextBean.class, mView) {
            @Override
            public void onSuccess(NewMediaAndTextBean result) {
                NewMediaAndTextBean.DataBean data = result.getData();
                WpyxConfig.isIsBuy(data.isIsBuy());
                mView.showData(data);

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
    private List changeData(List<ZhuanLanBigInBean.DataBean.DirBean> dir) {
        if (EmptyUtils.isEmpty(dir)) return null;
        Set<String> mouths = new HashSet<>();
        String month = "", date = "";

        for (int i = 0; i < dir.size(); i++) {
            ZhuanLanBigInBean.DataBean.DirBean dirBean = dir.get(i);
            if (i == 0) {
                dirBean.setCheck(true);
            }
            String startTime = dirBean.getStartTime();
            if (EmptyUtils.isNotEmpty(startTime)) {
                month = startTime.substring(5, 7);
                date = startTime.substring(8, 10);
            }
            if (!mouths.contains(month)) {
                mouths.add(month);
                dirBean.setMouth(month);
            }
            if (i%8==0){
                dirBean.setMouth(month);
            }
            dirBean.setDay(date);
        }
        mouths.clear();
        return dir;
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
    public void getCertificate(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<StudyFinishBean>(StudyFinishBean.class) {
            @Override
            public void onSuccess(StudyFinishBean result) {
                mView.showCertificate(result.getData());
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

}
