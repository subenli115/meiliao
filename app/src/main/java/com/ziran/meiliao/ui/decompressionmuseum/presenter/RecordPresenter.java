package com.ziran.meiliao.ui.decompressionmuseum.presenter;

import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.GetTagByMonthBean;
import com.ziran.meiliao.ui.bean.PractiveChartBean;
import com.ziran.meiliao.ui.bean.PunchHisBean;
import com.ziran.meiliao.ui.bean.RecordChartBean;
import com.ziran.meiliao.ui.bean.RecordTotalBean;
import com.ziran.meiliao.ui.bean.UserNoteBean;
import com.ziran.meiliao.ui.decompressionmuseum.contract.RecordContract;

import java.util.HashMap;
import java.util.Map;


/**
 * 正念笔记页面契约类
 */
public class RecordPresenter extends RecordContract.Presenter {


    @Override
    public void getTagByMonth(String url, Map<String, String> params) {
        mModel.getData(url, params, new NewRequestCallBack<GetTagByMonthBean>(GetTagByMonthBean.class) {
            @Override
            public void onSuccess(GetTagByMonthBean result) {
                if (EmptyUtils.isNotEmpty(result.getData())) mView.showTagByMonth(result.getData());
            }
        });
    }

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
    public void getTotalTimeByDate(String url, Map<String, String> params) {
        mModel.getData(url, params, new NewRequestCallBack<Result>(Result.class) {
            @Override
            public void onSuccess(Result result) {
                mView.showTotalTimeByDate(result);
            }
        });
    }

    Map<String, RecordChartBean> mRecordChartBeanMap;

    @Override
    public void getChartData(final String url, final Map<String, String> map) {
        final String key = url + map.get("type");
        if (EmptyUtils.isNotEmpty(mRecordChartBeanMap) && mRecordChartBeanMap.containsKey(key)) {
            mView.showChartData(mRecordChartBeanMap.get(key));
            return;
        }
        mModel.getData(url, map, new NewRequestCallBack<RecordChartBean>(RecordChartBean.class) {
            @Override
            public void onSuccess(RecordChartBean result) {
                if (EmptyUtils.isEmpty(mRecordChartBeanMap)) {
                    mRecordChartBeanMap = new HashMap<>();
                }
                if (!mRecordChartBeanMap.containsKey(key)) {
                    mRecordChartBeanMap.put(key, result);
                }
                mView.showChartData(result);
            }
        });
    }

    @Override
    public void getPunchHisData(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<PunchHisBean>(PunchHisBean.class) {
            @Override
            public void onSuccess(PunchHisBean result) {
                if (EmptyUtils.isNotEmpty(result.getData())){

                    mView.showPunchHisData(result.getData());
                }
            }
        });
    }

    @Override
    public void getUserNote(String url, Map<String, String> params) {
        mModel.getData(url, params, new NewRequestCallBack<UserNoteBean>(UserNoteBean.class) {
            @Override
            public void onSuccess(UserNoteBean result) {
                mView.showUserNote(result.getData());
            }
        });
    }

    @Override
    public void postDeleteNote(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<Result>(Result.class) {
            @Override
            public void onSuccess(Result result) {
                mView.deleteNoteResult(result);
            }
        });
    }

    @Override
    public void getNewChartData(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<PractiveChartBean>(PractiveChartBean.class) {
            @Override
            public void onSuccess(PractiveChartBean result) {
                if (EmptyUtils.isNotEmpty(result.getData()))
                    mView.showNewChartData(result.getData());
            }
        });
    }

}
