package com.ziran.meiliao.ui.workshops.presenter;

import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.OrderCreateResultBean;
import com.ziran.meiliao.ui.bean.TeamCustomBean;
import com.ziran.meiliao.ui.workshops.contract.TeamCustomContract;

import java.util.Map;


/**
 * des:图片列表
 * Created by xsf
 * on 2016.09.12:01
 */
public class TeamCustomPresenter extends TeamCustomContract.Presenter {

    @Override
    public void getListMissionBuiltInfo(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<TeamCustomBean>(TeamCustomBean.class) {
            @Override
            protected void onSuccess(TeamCustomBean result) {
                mView.showMissionBuiltInfo(result.getData());
            }
        });
    }

    @Override
    public void postMissionBuilt(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<OrderCreateResultBean>(OrderCreateResultBean.class) {
            @Override
            protected void onSuccess(OrderCreateResultBean result) {
                mView.postMissionBuilt(result.getData());
            }
        });
    }
}
