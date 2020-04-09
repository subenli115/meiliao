package com.ziran.meiliao.ui.priavteclasses.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.SJKReCoommendCourseBean;
import com.ziran.meiliao.ui.priavteclasses.activity.HorizontalHistoryActivity;
import com.ziran.meiliao.ui.priavteclasses.adapter.SJKLiveDetailRecommendCourseAdapter;
import com.ziran.meiliao.utils.MapUtils;

/**
 * 私家课-直播详情-简介Fragment
 * Created by Administrator on 2017/1/7.
 */

public class SJKLiveDetailRecommendCourseFragment extends CommonRefreshFragment<CommonPresenter, CommonModel>
        implements CommonContract.View<SJKReCoommendCourseBean> {

    String courseId;
    @Override
    public CommonRecycleViewAdapter getAdapter() {
        courseId = getIntentExtra(getActivity().getIntent(), AppConstant.SPKey.COURSE_ID);
        setRecyclerEnabled(false,true);
        loadedTip.setEmptyMsg(getString(R.string.not_live_detail), R.mipmap.ic_empty_nocontent);
        return new SJKLiveDetailRecommendCourseAdapter(getContext(), R.layout.item_main_sjk_history_course);
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        iRecyclerView.setRefreshEnabled(false);
        return new GridLayoutManager(getContext(), 2);
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        super.onItemClick(parent, view, o, position);
        HorizontalHistoryActivity.startAction(getContext(),"1",0,true);
    }

    @Override
    protected void loadData() {
        mPresenter.postData(ApiKey.GET_COURSE_RECOMMEND, MapUtils.getCourseMap(courseId),SJKReCoommendCourseBean.class);
    }

    @Override
    public void returnData(SJKReCoommendCourseBean result) {
        updateData(result.getData());
    }
}
