package com.ziran.meiliao.ui.priavteclasses.fragment;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/7/31 15:18
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/7/31$
 * @updateDes ${TODO}
 */

import android.os.Bundle;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.TimeUtil;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpFragment;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.WithDrawalsItemDetailBean;
import com.ziran.meiliao.ui.me.widget.ProgressStepView;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.BaseItemView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


/**
 * 查看提现明细界面
 * Created by Administrator on 2017/1/7.
 */

public class WithDrawalsItemDetailFragment extends CommonHttpFragment<CommonPresenter, CommonModel> implements CommonContract.View<WithDrawalsItemDetailBean> {


    @Bind(R.id.biv_amount)
    BaseItemView mBivAmount;
    @Bind(R.id.biv_time)
    BaseItemView mBivTime;
    @Bind(R.id.biv_bill_no)
    BaseItemView mBivBillNo;
    @Bind(R.id.biv_balance)
    BaseItemView mBivBalance;
    @Bind(R.id.biv_bland_no)
    BaseItemView mBivBlandNo;
    @Bind(R.id.progressStepView)
    ProgressStepView mProgressStepView;
    String orderNo;

    @Override
    protected void initBundle(Bundle extras) {
        orderNo = extras.getString("_ID");
    }

    @Override
    public void returnData(WithDrawalsItemDetailBean result) {
        WithDrawalsItemDetailBean.DataBean data = result.getData();
        List<ProgressStepView.Item> items = new ArrayList<>();
        items.add(new ProgressStepView.Item(data.getSupplyTime() > 0, data.getSupplyItem(), TimeUtil.getStringByFormat(data.getSupplyTime(), TimeUtil.dateFormatYMDHMS)));
        items.add(new ProgressStepView.Item(data.getDealTime() > 0, data.getDealItem(), TimeUtil.getStringByFormat(data.getDealTime(), TimeUtil.dateFormatYMDHMS)));
        items.add(new ProgressStepView.Item(data.getSuccessTime() > 0, data.getSuccessItem(), TimeUtil.getStringByFormat(data.getSuccessTime(), TimeUtil.dateFormatYMDHMS)));
        mProgressStepView.setItems(items);

        mBivAmount.setRightText(String.valueOf(data.getMoney()));
        mBivBalance.setRightText(String.valueOf(data.getMoney()));
        mBivBillNo.setRightText(data.getOrderNo());
        mBivBlandNo.setRightText(data.getIncomeBank());

        mBivTime.setRightText(TimeUtil.getStringByFormat(data.getSupplyTime(), TimeUtil.dateFormatYMDHMS));

    }

    @Override
    protected void initOther() {
        mPresenter.postData(ApiKey.LIVE_GET_MONEY_SUPPLY_DETAIL, MapUtils.getOnlyCan("orderNo", orderNo), WithDrawalsItemDetailBean.class);


    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_with_drawals_item_detail;
    }


}
