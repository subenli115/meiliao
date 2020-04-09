package com.ziran.meiliao.ui.NewDecompressionmuseum.presenter;

import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.NewDecompressionmuseum.contract.PracticeFourContract;
import com.ziran.meiliao.ui.bean.JdxShareBean;
import com.ziran.meiliao.ui.bean.PracticeFourCheckBean;
import com.ziran.meiliao.ui.bean.PracticeFourDetailBean;
import com.ziran.meiliao.ui.bean.PracticeSaveBean;

import java.util.Map;

/**
 * Created by Administrator on 2018/9/25.
 */

public class PracticeFourPresenter extends PracticeFourContract.Presenter{


    @Override
    public void getPracticeFourData(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<PracticeFourDetailBean>(PracticeFourDetailBean.class) {
            @Override
            public void onSuccess(PracticeFourDetailBean result) {
                if (EmptyUtils.isNotEmpty(result.getData()))
                    mView.showFourData(result.getData());
            }
        });
    }

    @Override
    public void getPracticeFourSaveData(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<PracticeSaveBean>(PracticeSaveBean.class) {
            @Override
            public void onSuccess(PracticeSaveBean result) {
                if (EmptyUtils.isNotEmpty(result.getData()))
                    mView.showFourSaveData(result);
            }
        });
    }


    @Override
    public void getPracticeFourCheckData(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<PracticeFourCheckBean>(PracticeFourCheckBean.class) {
            @Override
            public void onSuccess(PracticeFourCheckBean result) {
                if (EmptyUtils.isNotEmpty(result.getData()))
                    mView.showFourCheckData(result.getData());
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
