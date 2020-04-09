package com.ziran.meiliao.ui.me.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.TimeUtil;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonHttpFragment;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.OrderCreateResultBean;
import com.ziran.meiliao.ui.bean.OrderInfoBean;
import com.ziran.meiliao.ui.me.activity.MyApplyRefundActivity;
import com.ziran.meiliao.ui.me.contract.QJGApplyRefundContract;
import com.ziran.meiliao.ui.me.presenter.QJGApplyRefundPresenter;
import com.ziran.meiliao.ui.me.widget.CourseDetailView;
import com.ziran.meiliao.utils.HtmlUtil;
import com.ziran.meiliao.utils.MapUtils;

import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 我的活动工作坊界面
 * Created by Administrator on 2017/1/7.
 */

public class MyWorkshopsApplyRefundFragment extends CommonHttpFragment<QJGApplyRefundPresenter, CommonModel> implements
        QJGApplyRefundContract.View {


    @Bind(R.id.courseDetailView)
    CourseDetailView courseDetailView;
    @Bind(R.id.tv_order_number_and_ref_time)
    TextView tvOrderNumberAndRefTime;
    @Bind(R.id.tv_refund_days)
    TextView tvRefundDays;
    @Bind(R.id.tv_refund_price)
    TextView tvRefundPrice;
    @Bind(R.id.tv_refund_des)
    TextView tvRefundDes;
    @Bind(R.id.et_refund_reason)
    EditText etRefundReason;
    @Bind(R.id.tv_refund_submit)
    TextView tvRefundSubmit;
    private String orderId = "";

    private OrderInfoBean.DataBean mDataBean;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_workshops_apply_refund;
    }

    @Override
    protected void initBundle(Bundle extras) {
        orderId = extras.getString(AppConstant.ExtraKey.FROM_ID);
    }

    @Override
    protected void initView() {
        super.initView();

        stringMap = MapUtils.getOnlyCan("orderId", orderId);
        mPresenter.postData(ApiKey.CROWN_FUND_REFUND_SUPPLY_INFO, stringMap);
    }

    private Map<String, String> stringMap;


    @Override
    public void showData(OrderInfoBean.DataBean resultData) {
        if (EmptyUtils.isNotEmpty(resultData)) {
            mDataBean = resultData;
            ViewUtil.setText(tvRefundDays, HtmlUtil.format("%d个工作日内退款", resultData.getRefundDays()));
            courseDetailView.setParams(resultData.getPicture(), resultData.getTitle(), resultData.getStartTime(), resultData.getEndTime()
                    , resultData.getAddress(), resultData.getPrice());
            tvOrderNumberAndRefTime.setText(HtmlUtil.format("订单编号：%s%n创建时间：%s", resultData.getOrderId(), TimeUtil.getStringByFormat
                    (resultData.getCreateTime())));
            ViewUtil.setText(tvRefundPrice, HtmlUtil.fromHtml(HtmlUtil.format("退款金额：<font color=#FFA008 >¥%d</font>", resultData.getPrice
                    ())));
            ViewUtil.setHtmlText(tvRefundDes, resultData.getRefundDescript());
        }
    }

    @OnClick(R.id.tv_refund_submit)
    public void onViewClicked(View view) {
        String reason = etRefundReason.getText().toString();
        if (EmptyUtils.isEmpty(reason)) {
            showShortToast("请填写退款原因");
            return;
        }
        stringMap.put("reason", reason);
        stringMap.put("refundDays", String.valueOf(mDataBean.getRefundDays()));
        mPresenter.submit(ApiKey.CROWN_FUND_REFUND_SUPPLY, stringMap);
    }


    @Override
    public void submitResult(OrderCreateResultBean.DataBean result) {
        mRxManager.post(AppConstant.RXTag.REFUND_RESULT,true);
        startActivity(MyApplyRefundActivity.class,getBundle(MyApplyRefundActivity.FROM_TYPE_APPLY_REFUND_RESULT, result.getOrderId()),true);
    }
}
