package com.ziran.meiliao.ui.priavteclasses.fragment;

import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.SJKMoreBean;
import com.ziran.meiliao.ui.bean.SJKSingeLiveData;
import com.ziran.meiliao.ui.priavteclasses.activity.LiveListActivity;
import com.ziran.meiliao.ui.priavteclasses.adapter.CourseListAdapter;
import com.ziran.meiliao.utils.MapUtils;

/**
 * 私家课-直播详情-简介Fragment
 * Created by Administrator on 2017/1/7.
 */

public class CourseListFragment extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract
        .View<SJKMoreBean> {


    @Override
    public CommonRecycleViewAdapter getAdapter() {
        return new CourseListAdapter(getContext(),R.layout.item_course_list);
    }


    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        LiveListActivity.startAction(getContext(),((SJKSingeLiveData)o).getTitle());
    }

    @Override
    protected void loadData() {
        mPresenter.getData(ApiKey.GET_COURSE_TRAILER_LIST, MapUtils.getOnlyPage(page), SJKMoreBean.class);
    }


    @Override
    public void returnData(SJKMoreBean result) {
        updateData(result.getData());
    }
}
