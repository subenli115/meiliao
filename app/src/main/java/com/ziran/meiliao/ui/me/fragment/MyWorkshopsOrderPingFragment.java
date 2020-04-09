package com.ziran.meiliao.ui.me.fragment;

import android.os.Bundle;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpFragment;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.CategoryBean;
import com.ziran.meiliao.ui.me.util.OrderTopViewUtil;
import com.ziran.meiliao.ui.me.widget.CourseDetailView;
import com.ziran.meiliao.ui.me.widget.RefundView;
import com.ziran.meiliao.ui.me.widget.SomeTextView;
import com.ziran.meiliao.ui.workshops.util.ServiceDialogUtil;

import butterknife.Bind;

/**
 * 我的活动工作坊界面
 * Created by Administrator on 2017/1/7.
 */

public class MyWorkshopsOrderPingFragment extends CommonHttpFragment<CommonPresenter, CommonModel> implements CommonContract
        .View<CategoryBean> {


    @Bind(R.id.ntb)
    NormalTitleBar ntb;
    @Bind(R.id.courseDetailView)
    CourseDetailView mCourseDetailView;
    @Bind(R.id.someTextView)
    SomeTextView mSomeTextView;
    @Bind(R.id.refundView)
    RefundView mRefundView;
    private OrderTopViewUtil mOrderTopViewUtil;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_workshops_order_ping;
    }

    @Override
    protected void initBundle(Bundle extras) {

    }
    @Override
    protected void initView() {
        super.initView();
        mOrderTopViewUtil = new OrderTopViewUtil(rootView, 2);
        ntb.setOnRightImagListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceDialogUtil.showDialog(getActivity());
            }
        });

        setData();
    }

    private void setData() {
        mCourseDetailView.setParams(AppConstant.URL, "10天深度睡眠", "2017-12-02", "2017-12-08", "北京", 99);
        mSomeTextView.setParams("Ariel", "15999999999", "15999999999@163.com", "8888888888888888", "2017-07-13");
        mRefundView.setRefundLastTime("2017-07-13 22:22:33");
        mOrderTopViewUtil.setAvatars(AppConstant.URL, "", "");
    }

    @Override
    public void returnData(CategoryBean result) {

    }

}
