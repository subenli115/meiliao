package com.ziran.meiliao.ui.me.fragment;

import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.CategoryBean;
import com.ziran.meiliao.ui.me.activity.MyWorkshopsOrderActivity;
import com.ziran.meiliao.ui.me.adapter.MyActivityAdapter;
import com.ziran.meiliao.ui.bean.OrderDetailBean;
import com.ziran.meiliao.ui.workshops.util.QJGDataUtil;
import com.ziran.meiliao.utils.MapUtils;

/**
 * 我的活动工作坊界面
 * Created by Administrator on 2017/1/7.
 */

public class MyWorkshopsFragment extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract.View<CategoryBean> {

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_common_recyclerview_gray;
    }


    @Override
    public CommonRecycleViewAdapter getAdapter() {
        return new MyActivityAdapter(getContext(), 2);
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        OrderDetailBean detailBean = (OrderDetailBean) o;
//        MyWorkshopsOrderActivity.startAction(position % 3,detailBean.getOrderId());
        startActivity(MyWorkshopsOrderActivity.class,getBundle(position % 3,detailBean.getOrderId()));
    }

    @Override
    protected void loadData() {
        mPresenter.postData(ApiKey.GET_LIST_BY_COLUMN, MapUtils.getCategoryData("1", page), CategoryBean.class);
    }

    @Override
    public void returnData(CategoryBean result) {
        updateData(QJGDataUtil.getOrderDetail(3,12));
    }
}
