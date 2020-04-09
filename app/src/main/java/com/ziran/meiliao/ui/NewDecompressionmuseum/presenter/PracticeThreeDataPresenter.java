package com.ziran.meiliao.ui.NewDecompressionmuseum.presenter;

import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.NewDecompressionmuseum.contract.PracticeThreeDataContract;
import com.ziran.meiliao.ui.bean.JdxShareBean;
import com.ziran.meiliao.ui.bean.PracticeSaveBean;
import com.ziran.meiliao.ui.bean.PracticeThreeBean;
import com.ziran.meiliao.ui.bean.PracticeThreeDetailCheckBean;

import java.util.Map;

/**
 * Created by Administrator on 2018/9/21.
 */

public class PracticeThreeDataPresenter  extends PracticeThreeDataContract.Presenter {



    @Override
    public void getPracticeThreeData(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<PracticeThreeBean>(PracticeThreeBean.class) {
            @Override
            public void onSuccess(PracticeThreeBean result) {
                if (EmptyUtils.isNotEmpty(result.getData()))
                    mView.showThreeData(result.getData());
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
    @Override
    public void getPracticeThreeSaveData(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<PracticeSaveBean>(PracticeSaveBean.class) {
            @Override
            public void onSuccess(PracticeSaveBean result) {
                if (EmptyUtils.isNotEmpty(result.getData()))
                    mView.showThreeSaveData(result.getData());
            }
        });
    }

    @Override
    public void getPracticeThreeCheckData(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<PracticeThreeDetailCheckBean>(PracticeThreeDetailCheckBean.class) {
            @Override
            public void onSuccess(PracticeThreeDetailCheckBean result) {
                if (EmptyUtils.isNotEmpty(result.getData()))
                    mView.showThreeCheckData(result.getData());
            }
        });
    }

}
