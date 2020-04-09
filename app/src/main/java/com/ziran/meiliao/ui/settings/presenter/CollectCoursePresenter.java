package com.ziran.meiliao.ui.settings.presenter;

import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.ActivityListBean;
import com.ziran.meiliao.ui.bean.CollectCourseBean;
import com.ziran.meiliao.ui.bean.SearchItemBean;
import com.ziran.meiliao.ui.settings.contract.CollectCourseContract;

import java.util.List;
import java.util.Map;


/**
 * des:图片列表
 * Created by xsf
 * on 2016.09.12:01
 */
public class CollectCoursePresenter extends CollectCourseContract.Presenter implements AppConstant.CollectCourse{



    @Override
    public void getCrowdFundingList(Map<String, String> map) {
        mModel.post(ApiKey.CROWN_FUND_COLLECT_CROWN_FUND_LIST, map, new NewRequestCallBack<CollectCourseBean>(CollectCourseBean.class) {
            @Override
            public void onSuccess(CollectCourseBean bean) {
                changeData(bean.getData(),ITEM_TYPE_CROWD_FUND);
                mView.showCrowdFundingList(bean.getData());
            }
        });
    }

    @Override
    public void getTeamList(Map<String, String> map) {
        mModel.post(ApiKey.MISSION_BUILT_COLLECT_MISSION_LIST, map, new NewRequestCallBack<CollectCourseBean>(CollectCourseBean.class) {
            @Override
            public void onSuccess(CollectCourseBean bean) {
                changeData(bean.getData(),ITEM_TYPE_TEAM);
                mView.showTeamList(bean.getData());
            }
        });
    }

    @Override
    public void getActivityList(Map<String, String> map) {
        mModel.post(ApiKey.USER_HOME_LIST_ACTIVITY, map, new NewRequestCallBack<ActivityListBean>(ActivityListBean.class) {
            @Override
            protected void onSuccess(ActivityListBean bean) {
                changeData(bean.getData(),ITEM_TYPE_ACTIVITY);
                mView.showActivityList(bean.getData());
            }
        });
    }

    @Override
    public void getTeacherList(Map<String, String> map) {
        mModel.post(ApiKey.FAMOUS_TEACHERS_COLLECT_FAMOUS_TEACHER_LIST, map, new NewRequestCallBack<CollectCourseBean>(CollectCourseBean.class) {
            @Override
            public void onSuccess(CollectCourseBean bean) {
                changeData(bean.getData(),ITEM_TYPE_TEACHER);
                mView.showTeacherList(bean.getData());
            }
        });
    }

    @Override
    public void getAddCrowdFundingList(Map<String, String> map) {
        mModel.post(ApiKey.CROWN_FUND_COLLECT_CROWN_FUND_LIST, map, new NewRequestCallBack<CollectCourseBean>(CollectCourseBean.class) {
            @Override
            public void onSuccess(CollectCourseBean bean) {
                changeData(bean.getData(),ITEM_TYPE_CROWD_FUND);
                mView.showAddCrowdFundingList(bean.getData());
            }
        });
    }

    @Override
    public void getAddTeamList(Map<String, String> map) {
        mModel.post(ApiKey.MISSION_BUILT_COLLECT_MISSION_LIST, map, new NewRequestCallBack<CollectCourseBean>(CollectCourseBean.class) {
            @Override
            public void onSuccess(CollectCourseBean bean) {
                changeData(bean.getData(),ITEM_TYPE_TEAM);
                mView.showAddTeamList(bean.getData());
            }
        });
    }

    @Override
    public void getAddActivityList(Map<String, String> map) {
        mModel.post(ApiKey.USER_HOME_LIST_ACTIVITY, map, new NewRequestCallBack<ActivityListBean>(ActivityListBean.class) {
            @Override
            protected void onSuccess(ActivityListBean result) {
                changeData(result.getData(),ITEM_TYPE_ACTIVITY);
                mView.showAddActivityList(result.getData());
            }
        });
    }

    private void changeData(List<SearchItemBean> data, String  type) {
        if (EmptyUtils.isNotEmpty(data)){
            for (SearchItemBean bean : data) {
                bean.setType(type);
            }
        }
    }

    @Override
    public void getAddTeacherList(Map<String, String> map) {
        mModel.post(ApiKey.FAMOUS_TEACHERS_COLLECT_FAMOUS_TEACHER_LIST, map, new NewRequestCallBack<CollectCourseBean>(CollectCourseBean.class) {
            @Override
            public void onSuccess(CollectCourseBean bean) {
                changeData(bean.getData(),ITEM_TYPE_TEACHER);
                mView.showAddTeacherList(bean.getData());
            }
        });
    }
}
