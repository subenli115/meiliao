package com.ziran.meiliao.ui.decompressionmuseum.presenter;

import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.DailyMindBean;
import com.ziran.meiliao.ui.bean.ExerciseUploadBean;
import com.ziran.meiliao.ui.decompressionmuseum.contract.MindfulnessHallCountTimeContract;

import java.util.Map;


/**
 * 自我察觉练习契约类
 */
public class MindfulnessHallCountTimePresenter extends MindfulnessHallCountTimeContract.Presenter {


    @Override
    public void postPractice(String url, Map<String, String> map ) {
        mModel.post(url, map, new NewRequestCallBack<ExerciseUploadBean>(ExerciseUploadBean.class) {
            @Override
            public void onSuccess(ExerciseUploadBean result) {
                mView.practiceUploadResult(result);
            }
        });
    }

    @Override
    public void updateDailyMind(String url, Map<String, String> map) {
        mModel.post(url, map, new NewRequestCallBack<Result>(Result.class) {
            @Override
            public void onSuccess(Result result) {
                mView.showUpdateDailyMind(result);
            }
        });
    }

    @Override
    public void getDailyMind(String url, Map<String, String> map) {
        mModel.post(url, map, new NewRequestCallBack<DailyMindBean>(DailyMindBean.class) {
            @Override
            public void onSuccess(DailyMindBean result) {
                mView.showDailyMind(result.getData());
            }
        });
    }

    @Override
    public void getByDay(String url, Map<String, String> map) {
        mModel.getData(url, map, new NewRequestCallBack<Result>(Result.class) {
            @Override
            public void onSuccess(Result result) {
                mView.showGainByDay(result);
            }
        });
    }
}
