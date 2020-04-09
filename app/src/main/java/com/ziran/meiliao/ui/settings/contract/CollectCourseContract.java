package com.ziran.meiliao.ui.settings.contract;

import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.SearchItemBean;

import java.util.List;
import java.util.Map;

/**
 * des:图片列表contract
 * Created by xsf
 * on 2016.09.14:38
 */
public interface CollectCourseContract {


    interface View extends BaseView {
        //返回获取的图片
        void showCrowdFundingList(List<SearchItemBean> result);

        void showTeamList(List<SearchItemBean> result);
        void showActivityList(List<SearchItemBean> result);

        void showTeacherList(List<SearchItemBean> result);

        void showAddCrowdFundingList(List<SearchItemBean> result);

        void showAddTeamList(List<SearchItemBean> result);
        void showAddActivityList(List<SearchItemBean> result);

        void showAddTeacherList(List<SearchItemBean> result);
    }

    abstract class Presenter extends BasePresenter<View, CommonModel> {
        //发起获取图片请求
        public abstract void getCrowdFundingList(Map<String, String> map);

        public abstract void getTeamList(Map<String, String> map);

        public abstract void getActivityList(Map<String, String> map);
        public abstract void getTeacherList(Map<String, String> map);

        public abstract void getAddCrowdFundingList(Map<String, String> map);

        public abstract void getAddTeamList(Map<String, String> map);
        public abstract void getAddActivityList(Map<String, String> map);

        public abstract void getAddTeacherList(Map<String, String> map);
    }
}
