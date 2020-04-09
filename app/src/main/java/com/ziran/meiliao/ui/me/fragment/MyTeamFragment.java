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
import com.ziran.meiliao.ui.bean.OrderListBean;
import com.ziran.meiliao.ui.me.activity.MyTeamOrderActivity;
import com.ziran.meiliao.ui.me.adapter.MyActivityAdapter;
import com.ziran.meiliao.ui.bean.OrderDetailBean;
import com.ziran.meiliao.utils.MapUtils;

import java.util.Map;

/**
 * 我的活动团建界面
 * Created by Administrator on 2017/1/7.
 */

public class MyTeamFragment extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract.View<OrderListBean> {

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_common_recyclerview_gray;
    }


    @Override
    public CommonRecycleViewAdapter getAdapter() {
        setEmptyMsg("暂无内容",R.mipmap.ic_empty_nocontent);
        return new MyActivityAdapter(getContext(),0);
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        OrderDetailBean detailBean = (OrderDetailBean) o;
        //getOrderTypeId // 1 发起  2 购买
        startActivity(MyTeamOrderActivity.class,getBundle(detailBean.getOrderTypeId(),detailBean.getOrderId()));
    }

    @Override
    protected void loadData() {

        Map<String, String> onlyPage = MapUtils.getOnlyPage(page);
        onlyPage.put("pageSize","10");
        mPresenter.postData(ApiKey.MISSION_BUILT_MISSION_BUILT_ORDER_LIST,onlyPage, OrderListBean.class);
    }

    @Override
    public void returnData(OrderListBean result) {
        updateData(result.getData().getMissionOrderList());
    }
}
