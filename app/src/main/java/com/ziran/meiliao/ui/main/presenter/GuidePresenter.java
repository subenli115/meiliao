package com.ziran.meiliao.ui.main.presenter;

import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.GuideListBean;
import com.ziran.meiliao.ui.bean.GuideSaveBean;
import com.ziran.meiliao.ui.main.contract.GuideContract;

import java.util.Map;

/**
 * Created by Administrator on 2019/1/14.
 */

public class GuidePresenter  extends GuideContract.Presenter{

    @Override
    public void getGuideListData(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<GuideListBean>(GuideListBean.class) {
            @Override
            public void onSuccess(GuideListBean result) {
                if (EmptyUtils.isNotEmpty(result.getData()))
                    mView.showGuideListData(result.getData());
            }
        });
    }
    @Override
    public void getGuideSaveData(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<GuideSaveBean>(GuideSaveBean.class) {
            @Override
            public void onSuccess(GuideSaveBean result) {
                    mView.showGuideSaveData(result.getData());
            }
        });
    }

}