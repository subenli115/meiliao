package com.ziran.meiliao.ui.settings.contract;

import android.content.Context;

import com.ziran.meiliao.common.base.BaseModel;
import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.ui.bean.ExchangeBean;
import com.ziran.meiliao.ui.bean.IntegralDetailBean;
import com.ziran.meiliao.ui.bean.MemberCenterBean;
import com.ziran.meiliao.ui.bean.MemberExchangeBean;

import java.util.Map;

/**
 * Created by Administrator on 2018/6/7.
 */

public interface MemberDetailsContract {
    interface Model extends BaseModel {
        //执行获取用户会员信息
        void postGoodsDetails(String url,Map<String, String> params, OkHttpClientManager.ResultCallback callback);
        void postGetLevelDetails(String url,Map<String, String> params, OkHttpClientManager.ResultCallback callback);
        void postBuyByScore(String url,Map<String, String> params, OkHttpClientManager.ResultCallback callback);
        void postGetScore(String url,Map<String, String> params, OkHttpClientManager.ResultCallback callback);

    }
    interface View   extends BaseView {
        //返回获取的详情
        void showGoodsData(MemberExchangeBean.DataBean result);
        void showMemberCenterData(MemberCenterBean.DataBean result);
        void showResult(ExchangeBean.DataBean result);
        void showScore(IntegralDetailBean.DataBean result);
    }

    abstract  class Presenter extends BasePresenter<View, Model> {
//        //发起获取详情请求
        public abstract void getMemberGoodsData(Map<String, String> map,Context context);
        public abstract void postBuyByScore(Map<String, String> map,Context context);
        public abstract void postGetScore(Map<String, String> map,Context context);
        public abstract void getMemberCenterData(Map<String, String> map,Context context);
    }
}
