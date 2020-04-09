package com.ziran.meiliao.ui.NewDecompressionmuseum.presenter;

import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.NewDecompressionmuseum.contract.PracticeTwoDataContract;
import com.ziran.meiliao.ui.bean.JdxShareBean;
import com.ziran.meiliao.ui.bean.PracticeSaveBean;
import com.ziran.meiliao.ui.bean.PracticeTwoDetailBean;

import java.util.Map;

/**
 * Created by Administrator on 2018/9/21.
 */

public class PracticeTwoDataPresenter extends PracticeTwoDataContract.Presenter {



    @Override
    public void getPracticeTwoData(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<PracticeTwoDetailBean>(PracticeTwoDetailBean.class) {
            @Override
            public void onSuccess(PracticeTwoDetailBean result) {
                if (EmptyUtils.isNotEmpty(result.getData()))
                    mView.showTwoData(result.getData());
            }
        });
    }

    @Override
    public void getPracticeTwoSaveData(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<PracticeSaveBean>(PracticeSaveBean.class) {
            @Override
            public void onSuccess(PracticeSaveBean result) {
                if (EmptyUtils.isNotEmpty(result.getData()))
                    mView.showTwoSaveData(result.getData());
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
