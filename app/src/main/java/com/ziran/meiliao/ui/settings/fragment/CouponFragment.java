package com.ziran.meiliao.ui.settings.fragment;

import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.CouponBean;
import com.ziran.meiliao.ui.settings.adapter.CouponAdapter;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.HtmlUtil;
import com.ziran.meiliao.utils.MapUtils;

import butterknife.OnClick;

/**
 * 优惠劵Fragment
 * Created by Administrator on 2017/1/16.
 */

public class CouponFragment extends CommonRefreshFragment<CommonPresenter, CommonModel>
        implements CommonContract.View<CouponBean> {

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_me_coupon;
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        loadedTip.setEmptyMsg("", R.mipmap.ic_empty_coupon);
        loadedTip.setEmptySpanned(HtmlUtil.getCouponEmptyMsg());
        return new CouponAdapter(getContext());
    }

    @Override
    protected void loadData() {
        mPresenter.postData(ApiKey.USER_COUPON_LIST, MapUtils.getOnlyPage(page), CouponBean.class);
    }

    @Override
    public void returnData(CouponBean result) {
        updateData(result.getData());
    }

}
