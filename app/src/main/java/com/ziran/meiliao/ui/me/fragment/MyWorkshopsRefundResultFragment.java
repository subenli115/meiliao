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
import com.ziran.meiliao.ui.me.widget.CourseDetailView;
import com.ziran.meiliao.ui.me.widget.ProgressStepView;
import com.ziran.meiliao.ui.me.widget.SomeTextView;
import com.ziran.meiliao.ui.workshops.util.ServiceDialogUtil;
import com.ziran.meiliao.utils.HtmlUtil;
import com.ziran.meiliao.utils.MapUtils;

import java.util.List;
import java.util.Random;

import butterknife.Bind;

/**
 * 我的活动工作坊界面
 * Created by Administrator on 2017/1/7.
 */

public class MyWorkshopsRefundResultFragment extends CommonHttpFragment<CommonPresenter, CommonModel> implements CommonContract
        .View<OrderInfoBean> {


    @Bind(R.id.ntb)
    NormalTitleBar ntb;
    @Bind(R.id.progressStepView)
    ProgressStepView progressStepView;
    @Bind(R.id.courseDetailView)
    CourseDetailView courseDetailView;
    @Bind(R.id.someTextView)
    SomeTextView someTextView;
    @Bind(R.id.tv_refund_price)
    TextView tvRefundPrice;
    @Bind(R.id.tv_refund_des)
    TextView tvRefundDes;
    @Bind(R.id.et_refund_reason)
    TextView etRefundReason;

    private String orderId="";

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_workshops_refund_result;
    }
    @Override
    protected void initBundle(Bundle extras) {
        orderId = extras.getString(AppConstant.ExtraKey.FROM_ID);
    }
    @Override
    protected void initView() {
        super.initView();
        mPresenter.postData(ApiKey.CROWN_FUND_REFUND_SUPPLY_PROGRESS, MapUtils.getOnlyCan("orderId", orderId), OrderInfoBean.class);
        ntb.setOnRightImagListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceDialogUtil.showDialog(getActivity());
            }
        });
    }

    @Override
    public void returnData(OrderInfoBean result) {
        OrderInfoBean.DataBean resultData = result.getData();
        if (EmptyUtils.isNotEmpty(resultData)){
            courseDetailView.setParams(resultData.getPicture(),resultData.getTitle(), resultData.getStartTime(), resultData.getEndTime(), resultData.getAddress(), resultData.getPrice());
            someTextView.setRefundResult(resultData.getName(), resultData.getPhone(), "", resultData.getOrderId(), TimeUtil.getStringByFormat(resultData.getCreateTime()), resultData.getRefundId());
            ViewUtil.setHtmlText(tvRefundDes, resultData.getRefundDescript());
            ViewUtil.setText(tvRefundPrice, HtmlUtil.fromHtml(HtmlUtil.format("退款金额：<font color=#FFA008 >¥%d</font>", resultData.getPrice
                    ())));
            ViewUtil.setText(etRefundReason,resultData.getReason());
        }
        Random random = new Random();
        progressStepView.setStep(random.nextInt(3), System.currentTimeMillis());
        List<ProgressStepView.Item> items = progressStepView.getItems();
        switch (resultData.getStatus()){
            case 2 :
                items.get(2).setDateLong(resultData.getRefundSucceedTime());
            case 1 :
                items.get(1).setDateLong(resultData.getBankDealTime());
            case 0 :
                items.get(0).setDateLong(resultData.getRefundCreateTime());
        }
        progressStepView.setStep(resultData.getStatus());
    }

}
