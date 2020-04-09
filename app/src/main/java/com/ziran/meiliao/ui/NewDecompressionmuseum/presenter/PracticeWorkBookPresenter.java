package com.ziran.meiliao.ui.NewDecompressionmuseum.presenter;

import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.NewDecompressionmuseum.contract.PracticeWorkBookContract;
import com.ziran.meiliao.ui.bean.JdxShareBean;
import com.ziran.meiliao.ui.bean.PracticeCalendarBean;
import com.ziran.meiliao.ui.bean.PracticeHeadBean;
import com.ziran.meiliao.ui.bean.PracticeJoinBean;
import com.ziran.meiliao.ui.bean.PracticeStartBean;
import com.ziran.meiliao.ui.bean.PracticeStatusBean;
import com.ziran.meiliao.ui.bean.PracticeWorkBookBean;

import java.util.Map;


/**
 * 笔记页面契约类
 */
public class PracticeWorkBookPresenter extends PracticeWorkBookContract.Presenter {



    @Override
    public void getPracticeWookBookData(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<PracticeWorkBookBean>(PracticeWorkBookBean.class) {
            @Override
            public void onSuccess(PracticeWorkBookBean result) {
                if (EmptyUtils.isNotEmpty(result.getData()))
                    mView.showWookBookData(result.getData());
            }
        });
    }

    @Override
    public void getPracticeJoin(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<PracticeJoinBean>(PracticeJoinBean.class) {
            @Override
            public void onSuccess(PracticeJoinBean result) {
                if (EmptyUtils.isNotEmpty(result.getData()))
                    mView.showPracticeJoinData(result.getData());
            }
        });
    }

    @Override
    public void getPracticeStart(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<PracticeStartBean>(PracticeStartBean.class) {
            @Override
            public void onSuccess(PracticeStartBean result) {
                if (EmptyUtils.isNotEmpty(result.getData()))
                    mView.showPracticeStartData(result.getData());
            }
        });
    }


    @Override
    public void getPracticeCalendar(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<PracticeCalendarBean>(PracticeCalendarBean.class) {
            @Override
            public void onSuccess(PracticeCalendarBean result) {
                if (EmptyUtils.isNotEmpty(result.getData()))
                    mView.showPracticeCalendarData(result.getData());
            }
        });
    }

    @Override
    public void getPracticeHead(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<PracticeHeadBean>(PracticeHeadBean.class) {
            @Override
            public void onSuccess(PracticeHeadBean result) {
                if (EmptyUtils.isNotEmpty(result.getData()))
                    mView.showPracticeHeadData(result.getData());
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
    public void getPracticeStatus(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<PracticeStatusBean>(PracticeStatusBean.class) {
            @Override
            public void onSuccess(PracticeStatusBean result) {
                if (EmptyUtils.isNotEmpty(result.getData()))
                    mView.showPracticeCalendarStatusData(result.getData());
            }
        });
    }
}
