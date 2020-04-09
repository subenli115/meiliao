package com.ziran.meiliao.ui.NewDecompressionmuseum.presenter;

import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.NewDecompressionmuseum.contract.PracticeHomeContract;
import com.ziran.meiliao.ui.bean.JdxShareBean;
import com.ziran.meiliao.ui.bean.PracticeHomeBean;
import com.ziran.meiliao.ui.bean.PracticeRecordBean;

import java.util.Map;


/**
 * 主页面契约类
 */
public class PracticeHomePresenter extends PracticeHomeContract.Presenter {



    @Override
    public void getPracticeHomeData(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<PracticeHomeBean>(PracticeHomeBean.class) {
            @Override
            public void onSuccess(PracticeHomeBean result) {
                if (EmptyUtils.isNotEmpty(result.getData())) mView.showHomeData(result.getData());
            }
        });
    }
    @Override
    public void getPracticeRecordData(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<PracticeRecordBean>(PracticeRecordBean.class) {
            @Override
            public void onSuccess(PracticeRecordBean result) {
                if (EmptyUtils.isNotEmpty(result.getData())) mView.showRecordData(result.getData());
            }
        });
    }

    @Override
    public void changePracticeStatus(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<JdxShareBean>(JdxShareBean.class) {
            @Override
            public void onSuccess(JdxShareBean result) {
                if (EmptyUtils.isNotEmpty(result.getData()))
                    mView.showPracticeStatusData(result.getData());
            }
        });
    }
}
