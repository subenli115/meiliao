package com.ziran.meiliao.ui.priavteclasses.fragment;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.SJKMoreBean;
import com.ziran.meiliao.ui.priavteclasses.adapter.CourseDetailAdapter;
import com.ziran.meiliao.utils.MapUtils;

/**
 * 私家课-直播详情-简介Fragment
 * Created by Administrator on 2017/1/7.
 */

public class CourseDetailFragment extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract
        .View<SJKMoreBean> {


    @Override
    public CommonRecycleViewAdapter getAdapter() {
        return new CourseDetailAdapter(getContext(),R.layout.item_course_detail);
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
