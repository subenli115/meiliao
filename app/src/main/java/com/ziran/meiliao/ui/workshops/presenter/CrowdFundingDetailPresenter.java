package com.ziran.meiliao.ui.workshops.presenter;

import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.CrowdFundingDetailBean;
import com.ziran.meiliao.ui.workshops.contract.CrowdFundingDetailContract;

import java.util.Map;


/**
 * des:图片列表
 * Created by xsf
 * on 2016.09.12:01
 */
public class CrowdFundingDetailPresenter extends CrowdFundingDetailContract.Presenter {


    @Override
    public void postData(String url, Map<String, String> params) {
//        mModel.post(url, params, new StringResultCallBack(mView) {
//            @Override
//            public void onSuccess(String result) {
//                result =    result.replace("\\","");
//                CrowdFundingDetailBean fundingDetailBean = JsonUtils.fromJsonToType(result, CrowdFundingDetailBean.class);
//                mView.showData(fundingDetailBean);
//            }
////
////            @Override
////            protected void onSuccess(CrowdFundingDetailBean result) {
////                mView.showData(result);
////            }
//        });
        mModel.post(url, params, new NewRequestCallBack<CrowdFundingDetailBean>(CrowdFundingDetailBean.class,mView) {
            @Override
            protected void onSuccess(CrowdFundingDetailBean result) {
                mView.showData(result);
            }
        });
    }

    @Override
    public void postCollect(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<Result  >(Result.class) {
            @Override
            protected void onSuccess(Result result) {
                mView.showCollectResult(true);
                ToastUitl.showShort(result.getResultMsg());
            }
        });
    }
}
