package com.ziran.meiliao.ui.me.fragment;

import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.OrderDetailBean;
import com.ziran.meiliao.ui.bean.OrderListBean;
import com.ziran.meiliao.ui.me.activity.MyApplyRefundActivity;
import com.ziran.meiliao.ui.me.activity.MyCrowdFundingOrderActivity;
import com.ziran.meiliao.ui.me.adapter.MyActivityAdapter;
import com.ziran.meiliao.utils.MapUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import rx.functions.Action1;

/**
 * 我的活动众筹界面
 * Created by Administrator on 2017/1/7.
 */

public class MyCrowdFundingFragment extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract
        .View<OrderListBean> {

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_common_recyclerview_gray;
    }

    @Override
    protected void initView() {
        super.initView();
        mRxManager.on(AppConstant.RXTag.REFUND_RESULT, new Action1<Boolean>() {
            @Override
            public void call(Boolean refund) {
                onRefresh();
            }
        });
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        setEmptyMsg("暂无内容", R.mipmap.ic_empty_nocontent);
        return new MyActivityAdapter(getContext(), 1);
    }


    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        OrderDetailBean detailBean = (OrderDetailBean) o;
        //getOrderTypeId // 1 发起  2 购买
        if (detailBean.getStatusType() == 3) {
            startActivity(MyApplyRefundActivity.class, getBundle(MyApplyRefundActivity.FROM_TYPE_APPLY_REFUND_RESULT, detailBean
                    .getOrderId()));
        } else {
            startActivity(MyCrowdFundingOrderActivity.class, getBundle(detailBean.getOrderTypeId(), detailBean.getOrderId()));
        }
    }

    @Override
    protected void loadData() {
        Map<String, String> onlyPage = MapUtils.getOnlyPage(page);
        onlyPage.put("pageSize", "10");
        mPresenter.postData(ApiKey.CROWN_FUND_CROWD_FUND_ORDER_LIST, onlyPage, OrderListBean.class);
    }

    @Override
    public void returnData(OrderListBean result) {
        OrderListBean.DataBean resultData = result.getData();
        List<OrderDetailBean> resultList = new ArrayList<>();
        if (EmptyUtils.isNotEmpty(resultData)) {
            if (EmptyUtils.isNotEmpty(resultData.getCrownFundBuyOrderList())) {
                resultList.addAll(resultData.getCrownFundBuyOrderList());
            }
            if (EmptyUtils.isNotEmpty(resultData.getCrownFundCreateOrderList())) {
                resultList.addAll(resultData.getCrownFundCreateOrderList());
            }
            if (EmptyUtils.isNotEmpty(resultList)) {
                Collections.sort(resultList);
            }
        }
        updateData(resultList);
    }
}
