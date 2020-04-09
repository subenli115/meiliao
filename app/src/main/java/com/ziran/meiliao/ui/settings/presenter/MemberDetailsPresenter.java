package com.ziran.meiliao.ui.settings.presenter;


import android.content.Context;

import com.google.gson.Gson;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.ExchangeBean;
import com.ziran.meiliao.ui.bean.IntegralDetailBean;
import com.ziran.meiliao.ui.bean.MemberCenterBean;
import com.ziran.meiliao.ui.bean.MemberExchangeBean;
import com.ziran.meiliao.ui.settings.contract.MemberDetailsContract;
import com.ziran.meiliao.utils.CacheUtils;

import java.util.Map;

/**
 * 会员中心   2018/6/7.
 */

public class MemberDetailsPresenter extends MemberDetailsContract.Presenter {



    @Override
        public void getMemberGoodsData(Map<String, String> map, final Context context) {
        mModel.postGoodsDetails(ApiKey.USERCENTER_GOODSLIST,map , new NewRequestCallBack<MemberExchangeBean>(MemberExchangeBean.class) {
            @Override
            protected void onSuccess(MemberExchangeBean result) {
                Gson gson=new Gson();
                CacheUtils.setCache(ApiKey.USERCENTER_GOODSLIST, gson.toJson(result),
                        context);
                mView.showGoodsData(result.getData());
            }


        });
    }

    @Override
    public void postBuyByScore(Map<String, String> map, final Context context) {

        mModel.postBuyByScore(ApiKey.USERCENTER_BUYBYSCORE,map , new NewRequestCallBack<ExchangeBean>(ExchangeBean.class) {
            @Override
            protected void onSuccess(ExchangeBean result) {
                Gson gson=new Gson();
                CacheUtils.setCache(ApiKey.USERCENTER_BUYBYSCORE, gson.toJson(result),
                        context);
                mView.showResult(result.getData());
            }

            @Override
            public void onError(String msg, int code) {
                ToastUitl.showShort("兑换失败");
            }
        });
    }


    @Override
    public void postGetScore(Map<String, String> map, final Context context) {

        mModel.postGetScore(ApiKey.USERCENTER_CENTERINTRO,map , new NewRequestCallBack<IntegralDetailBean>(IntegralDetailBean.class) {
            @Override
            protected void onSuccess(IntegralDetailBean result) {
                Gson gson=new Gson();
                CacheUtils.setCache(ApiKey.USERCENTER_CENTERINTRO, gson.toJson(result),
                        context);
                mView.showScore(result.getData());

            }


        });
    }

    @Override
    public void getMemberCenterData(Map<String, String> map, final Context context) {

        mModel.postGetLevelDetails(ApiKey.USERCENTER_SCOREDETAIL,map , new NewRequestCallBack<MemberCenterBean>(MemberCenterBean.class) {
            @Override
            protected void onSuccess(MemberCenterBean result) {
                Gson gson=new Gson();
                CacheUtils.setCache(ApiKey.USERCENTER_SCOREDETAIL, gson.toJson(result),
                        context);
                mView.showMemberCenterData(result.getData());

            }


        });
    }
}
