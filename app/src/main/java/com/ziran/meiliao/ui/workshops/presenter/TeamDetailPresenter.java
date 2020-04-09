package com.ziran.meiliao.ui.workshops.presenter;

import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.StringDataBean;
import com.ziran.meiliao.ui.bean.TeamDetailBean;
import com.ziran.meiliao.ui.workshops.contract.TeamDetailContract;

import java.util.Map;


/**
 *
 */
public class TeamDetailPresenter extends TeamDetailContract.Presenter {


    @Override
    public void postData(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<TeamDetailBean>(TeamDetailBean.class, mView) {
            @Override
            protected void onSuccess(TeamDetailBean result) {
                mView.showData(result.getData());
            }
        });
    }

    @Override
    public void postCollect(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<Result>(Result.class) {
            @Override
            protected void onSuccess(Result result) {
                mView.showCollectResult(true);
                ToastUitl.showShort(result.getResultMsg());
            }
        });
    }

    @Override
    public void checkInfo(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<StringDataBean>(StringDataBean.class) {
            @Override
            protected void onSuccess(StringDataBean result) {
                mView.showCheckInfo(result);
            }
        });
    }
}
