package com.ziran.meiliao.ui.workshops.fragment;

import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.CourseLibraryTeamBean;
import com.ziran.meiliao.ui.bean.TeamListBean;
import com.ziran.meiliao.ui.workshops.activity.CourseLibraryTeamDetailActivity;
import com.ziran.meiliao.ui.workshops.adapter.CourseLibraryTeamAdapter;
import com.ziran.meiliao.utils.MapUtils;

/**
 * 减压馆-分类Fragment
 * Created by Administrator on 2017/1/7.
 */

public class CourseLibraryTeamFragment extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract.View<TeamListBean> {



    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_common_recyclerview;
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        return new CourseLibraryTeamAdapter(getContext());
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        CourseLibraryTeamBean data = EmptyUtils.parseObject(o);
        startActivity(CourseLibraryTeamDetailActivity.class,getBundle(data.getId()));
    }

    @Override
    protected void loadData() {
        mPresenter.postData(ApiKey.MISSION_BUILT_LIST_MISSION_BUILT, MapUtils.getOnlyPage(page), TeamListBean.class);
    }

    @Override
    public void returnData(TeamListBean result) {
        updateData(result.getData().getMissionBuiltList());
    }
}
