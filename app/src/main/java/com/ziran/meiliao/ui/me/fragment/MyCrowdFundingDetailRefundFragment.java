package com.ziran.meiliao.ui.me.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.CrowdFundingDetailBuyBean;
import com.ziran.meiliao.ui.me.adapter.MyCrowdFundingDetailAdapter;
import com.ziran.meiliao.utils.HtmlUtil;
import com.ziran.meiliao.utils.MapUtils;

import butterknife.Bind;

/**
 * 众筹订单退款明细界面
 * Created by Administrator on 2017/1/7.
 */

public class MyCrowdFundingDetailRefundFragment extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract
        .View<CrowdFundingDetailBuyBean> {

    private String crowdFundId;
    @Bind(R.id.tv_count)
    TextView tvCount;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_me_crowd_funding_detail;
    }

    @Override
    protected void initBundle(Bundle extras) {
        crowdFundId = extras.getString(AppConstant.ExtraKey.FROM_ID);
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        setEmptyMsg("暂无内容",R.mipmap.ic_empty_nocontent);
        return new MyCrowdFundingDetailAdapter(getContext(), 2);
    }

    @Override
    protected void loadData() {
        mPresenter.postData(ApiKey.CROWN_FUND_REFUND_DETAIL, MapUtils.getOnlyCan("crowdFundId", crowdFundId), CrowdFundingDetailBuyBean
                .class);
    }

    @Override
    public void returnData(CrowdFundingDetailBuyBean result) {
        CrowdFundingDetailBuyBean.DataBean resultData = result.getData();
        if (EmptyUtils.isNotEmpty(resultData)) {
            if (resultData.getSupportMembers() > 0) {
                ViewUtil.setText(tvCount, HtmlUtil.format("累计退款人数: %d人", resultData.getSupportMembers()));
            } else {
                ViewUtil.setText(tvCount, "暂无退款人数");
            }
            updateData(resultData.getRefundList());
        }
    }
}
