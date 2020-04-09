package com.ziran.meiliao.ui.decompressionmuseum.presenter;

import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.PracticeListBean;
import com.ziran.meiliao.ui.bean.RecordTotalBean;
import com.ziran.meiliao.ui.decompressionmuseum.contract.RecordCourseContract;

import java.util.Map;


/**
 * 正念笔记页面契约类
 */
public class RecordCoursePresenter extends RecordCourseContract.Presenter {


    @Override
    public void getTotalTime(String url, Map<String, String> params) {
        mModel.getData(url, params, new NewRequestCallBack<RecordTotalBean>(RecordTotalBean.class) {
            @Override
            public void onSuccess(RecordTotalBean result) {
                if (EmptyUtils.isNotEmpty(result.getData())) mView.showTotalTime(result.getData());
            }
        });
    }

    @Override
    public void getCourseList(String url, Map<String, String> params) {
        mModel.getData(url, params, new NewRequestCallBack<PracticeListBean>(PracticeListBean.class) {
            @Override
            public void onSuccess(PracticeListBean result) {
                if (EmptyUtils.isNotEmpty(result.getData())) mView.showCourseList(result.getData());
            }
        });
    }

}
