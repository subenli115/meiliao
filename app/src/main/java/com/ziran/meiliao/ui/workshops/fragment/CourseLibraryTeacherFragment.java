package com.ziran.meiliao.ui.workshops.fragment;

import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.FamousTeacherListBean;
import com.ziran.meiliao.ui.bean.TeacherListBean;
import com.ziran.meiliao.ui.workshops.activity.CourseLibraryTeacherDetailActivity;
import com.ziran.meiliao.ui.workshops.adapter.CourseLibraryTeacherAdapter;
import com.ziran.meiliao.utils.MapUtils;

/**
 * 减压馆-分类Fragment
 * Created by Administrator on 2017/1/7.
 */

public class CourseLibraryTeacherFragment extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract
        .View<TeacherListBean> {


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_common_recyclerview_gray;
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        return new CourseLibraryTeacherAdapter(getContext());
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        startActivity(CourseLibraryTeacherDetailActivity.class, getBundle(((FamousTeacherListBean) o).getId()));
    }

    @Override
    protected void loadData() {
        mPresenter.postData(ApiKey.FAMOUS_TEACHERS_FAMOUS_TEACHERS_LIST, MapUtils.getOnlyPage(page), TeacherListBean.class);
    }

    @Override
    public void returnData(TeacherListBean result) {
        updateData(result.getData().getFamousTeacherList());
    }
}
