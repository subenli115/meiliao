package com.ziran.meiliao.ui.me.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.TimeUtil;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpFragment;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.OrderInfoBean;
import com.ziran.meiliao.ui.me.activity.MyCrowdFundingDetailActivity;
import com.ziran.meiliao.ui.me.widget.CourseDetailView;
import com.ziran.meiliao.ui.me.widget.SomeTextView;
import com.ziran.meiliao.ui.workshops.util.ServiceDialogUtil;
import com.ziran.meiliao.ui.workshops.widget.ProgressTipsView;
import com.ziran.meiliao.utils.MapUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 发起众筹订单详情支持界面
 * Created by Administrator on 2017/1/7.
 */

public class MyCrowdFundingPartakeOrderDetailFragment extends CommonHttpFragment<CommonPresenter, CommonModel> implements
        CommonContract.View<OrderInfoBean> {


    @Bind(R.id.ntb)
    NormalTitleBar ntb;
    @Bind(R.id.progressTipsView)
    ProgressTipsView progressTipsView;
    @Bind(R.id.tv_look_detail)
    TextView tvLookDetail;
    @Bind(R.id.tv_state)
    TextView tvState;
    @Bind(R.id.courseDetailView)
    CourseDetailView mCourseDetailView;
    @Bind(R.id.someTextView)
    SomeTextView mSomeTextView;


    private String orderId;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_crowd_funding_partake_order_buy_detail;
    }

    @Override
    protected void initBundle(Bundle extras) {
        orderId = extras.getString(AppConstant.ExtraKey.FROM_ID); //mbc79761512711915511
    }

    @Override
    protected void initView() {
        super.initView();

        ntb.setOnRightImagListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceDialogUtil.showDialog(getActivity());
            }
        });
        mPresenter.postData(ApiKey.CROWN_FUND_CROWN_FUND_ORDER_LIST_INTRO, MapUtils.getOnlyCan("orderId", orderId), OrderInfoBean.class);

    }
    private String crowdFundId;
    @Override
    public void returnData(OrderInfoBean result) {
        OrderInfoBean.DataBean data = result.getData();

        if (EmptyUtils.isNotEmpty(data)) {
            crowdFundId = data.getCrowdFundId();
            mCourseDetailView.setParams(data.getPicture(), data.getTitle(), data.getStartTime(), data.getEndTime(), data.getAddress(),
                    data.getPrice());
            mSomeTextView.setPartakeParams(data.getName(), data.getIntro(), data.getPhone(), data.getOrderId(), TimeUtil
                    .getStringByFormat(data.getCreateTime()));
            progressTipsView.setParms(data.getSupportMembers(), data.getLeftTime(), data.getTargetMembers());
            ViewUtil.setText(tvState, data.getStatusMsg());
        }
    }

    @OnClick(R.id.tv_look_detail)
    public void onViewClicked() {
        if (EmptyUtils.isNotEmpty(crowdFundId))
            startActivity(MyCrowdFundingDetailActivity.class,getBundle(crowdFundId));
    }
}
