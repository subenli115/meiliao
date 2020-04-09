package com.ziran.meiliao.ui.NewDecompressionmuseum.presenter;

import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.NewDecompressionmuseum.contract.PracticeFiveContract;
import com.ziran.meiliao.ui.bean.JdxShareBean;
import com.ziran.meiliao.ui.bean.PracticeFiveDetailBean;
import com.ziran.meiliao.ui.bean.PracticeSaveBean;

import java.util.Map;

/**
 * Created by Administrator on 2018/9/25.
 */

public class PracticeFivePresenter extends PracticeFiveContract.Presenter{



    @Override
    public void getPracticeFiveData(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<PracticeFiveDetailBean>(PracticeFiveDetailBean.class) {
            @Override
            public void onSuccess(PracticeFiveDetailBean result) {
                if (EmptyUtils.isNotEmpty(result.getData()))
                    mView.showFiveData(result.getData());
            }
        });
    }

    @Override
    public void getPracticeFiveSaveData(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<PracticeSaveBean>(PracticeSaveBean.class) {
            @Override
            public void onSuccess(PracticeSaveBean result) {
                if (EmptyUtils.isNotEmpty(result.getData()))
                    mView.showFiveSaveData(result.getData());
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
