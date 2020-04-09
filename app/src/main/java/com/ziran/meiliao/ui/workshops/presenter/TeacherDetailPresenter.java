package com.ziran.meiliao.ui.workshops.presenter;

import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.StringDataBean;
import com.ziran.meiliao.ui.bean.TeacherDetailBean;
import com.ziran.meiliao.ui.workshops.contract.TeacherDetailContract;

import java.util.Map;


/**
 *
 */
public class TeacherDetailPresenter extends TeacherDetailContract.Presenter {


    @Override
    public void postData(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<TeacherDetailBean>(TeacherDetailBean.class,mView) {
            @Override
            protected void onSuccess(TeacherDetailBean result) {
                mView.showData(result.getData());
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
