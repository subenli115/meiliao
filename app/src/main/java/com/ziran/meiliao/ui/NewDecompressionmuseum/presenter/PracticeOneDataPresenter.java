package com.ziran.meiliao.ui.NewDecompressionmuseum.presenter;

import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.NewDecompressionmuseum.contract.PracticeOneDataContract;
import com.ziran.meiliao.ui.bean.JdxShareBean;
import com.ziran.meiliao.ui.bean.PracticeOneDetailBean;
import com.ziran.meiliao.ui.bean.PracticeSaveBean;

import java.util.Map;

/**
 * Created by Administrator on 2018/9/21.
 */

public class PracticeOneDataPresenter extends PracticeOneDataContract.Presenter {



    @Override
    public void getPracticeOneData(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<PracticeOneDetailBean>(PracticeOneDetailBean.class) {
            @Override
            public void onSuccess(PracticeOneDetailBean result) {
                if (EmptyUtils.isNotEmpty(result.getData()))
                    mView.showOneData(result.getData());
            }
        });
    }

    @Override
    public void getPracticeOneSaveData(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<PracticeSaveBean>(PracticeSaveBean.class) {
            @Override
            public void onSuccess(PracticeSaveBean result) {
                if (EmptyUtils.isNotEmpty(result.getData()))
                    mView.showOneSaveData(result.getData());
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
