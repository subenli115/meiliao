package com.ziran.meiliao.ui.workshops.fragment;

import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.CrowdFundingListBean;
import com.ziran.meiliao.ui.bean.CrownFundListBean;
import com.ziran.meiliao.ui.workshops.activity.CourseLibraryCrowdFundingDetailActivity;
import com.ziran.meiliao.ui.workshops.adapter.CourseLibraryCrowdFundingAdapter;
import com.ziran.meiliao.utils.MapUtils;

/**
 * 减压馆-分类Fragment
 * Created by Administrator on 2017/1/7.
 */

public class CourseLibraryCrowdFundingFragment extends CommonRefreshFragment<CommonPresenter, CommonModel>
        implements CommonContract.View<CrowdFundingListBean> {


    @Override
    public CommonRecycleViewAdapter getAdapter() {

        return new CourseLibraryCrowdFundingAdapter(getContext());
    }

    @Override
    protected void initOther() {

    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        startActivity(CourseLibraryCrowdFundingDetailActivity.class,getBundle(((CrownFundListBean)o).getIdString()));
    }

    @Override
    protected void loadData() {
        mPresenter.postData(ApiKey.CROWN_FUND_LIST_CROWD_FUND, MapUtils.getOnlyPage(page), CrowdFundingListBean.class);
    }

    @Override
    public void returnData(CrowdFundingListBean result) {
        updateData(result.getData().getCrownFundList());
    }

}
