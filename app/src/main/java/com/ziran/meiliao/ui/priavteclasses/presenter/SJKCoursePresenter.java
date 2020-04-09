package com.ziran.meiliao.ui.priavteclasses.presenter;

import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.GiftBean;
import com.ziran.meiliao.ui.bean.GiveGiftResultBean;
import com.ziran.meiliao.ui.bean.SJKLiveDetailProfileBean;
import com.ziran.meiliao.ui.bean.StringDataBean;
import com.ziran.meiliao.ui.priavteclasses.contract.SJKFullLiveContract;

import java.util.Map;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/5/11 9:58
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/5/11$
 * @updateDes ${TODO}
 */

public class SJKCoursePresenter extends SJKFullLiveContract.Presenter {

    @Override
    public void getCourseData(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<SJKLiveDetailProfileBean>(SJKLiveDetailProfileBean.class,mView) {
            @Override
            public void onSuccess(SJKLiveDetailProfileBean result) {
                mView.stopLoading();
                mView.showCourseData(result.getData());
            }
        });
    }

    @Override
    public void postLike(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<Result>(Result.class) {
            @Override
            public void onSuccess(Result data) {
                if (data==null) return;
                mView.setLike(data);
            }
        });
    }

    @Override
    public void postCollect(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<Result>(Result.class) {
            @Override
            public void onSuccess(Result result) {
                mView.setCollect(result);
            }
        });
    }

    @Override
    public void postShiKan(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<Result>(Result.class) {
            @Override
            public void onSuccess(Result result) {
            }
        });
    }

    @Override
    public void postUserCount(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<StringDataBean>(StringDataBean.class) {
            @Override
            public void onSuccess(StringDataBean data) {
                if (data==null) return;
                mView.setUserCount(data.getNornemData());
            }
        });
    }

    @Override
    public void listGift(String url, Map<String, String> params) {
        mModel.getData(url, params, new NewRequestCallBack<GiftBean>(GiftBean.class) {
            @Override
            public void onSuccess(GiftBean data) {
                if (data == null) return;
                mView.showListGift(data.getData());
            }
        });
    }

    @Override
    public void postGiveGift(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<GiveGiftResultBean>(GiveGiftResultBean.class) {
            @Override
            public void onSuccess(GiveGiftResultBean result) {
                if (result == null) return;
                mView.giveGiftResult(result.getData());
            }
        });
    }


    @Override
    public void buyCourse(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<StringDataBean>(StringDataBean.class) {
            @Override
            public void onSuccess(StringDataBean result) {
                if (result==null) return;
                mView.onPayResult(result.getData());
            }
        });
    }

    @Override
    public void watchUp(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<Result>(Result.class) {
            @Override
            public void onSuccess(Result result) {
            }
        });
    }

    @Override
    public void usedTick(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<StringDataBean>(StringDataBean.class) {
            @Override
            public void onSuccess(StringDataBean result) {
                mView.onPayResult("");
            }
        });
    }

    @Override
    public void updateStudy(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<Result>(Result.class) {
            @Override
            public void onSuccess(Result result) {

            }
        });
    }
}
