package com.ziran.meiliao.ui.me.fragment;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.CategoryBean;
import com.ziran.meiliao.ui.me.adapter.MyCrowdFundingDetailAdapter;
import com.ziran.meiliao.ui.workshops.util.QJGDataUtil;
import com.ziran.meiliao.utils.MapUtils;

/**
 *  参与众筹订单详情关注界面
 * Created by Administrator on 2017/1/7.
 */

public class MyCrowdFundingDetailFollowFragment extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract
        .View<CategoryBean> {


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_me_crowd_funding_detail;
    }


    @Override
    public CommonRecycleViewAdapter getAdapter() {
        return new MyCrowdFundingDetailAdapter(getContext(),0);
    }

    @Override
    protected void loadData() {
        mPresenter.postData(ApiKey.GET_LIST_BY_COLUMN, MapUtils.getCategoryData("1", page), CategoryBean.class);
    }

    @Override
    public void returnData(CategoryBean result) {
        updateData(QJGDataUtil.getStringData(12));
    }
}
