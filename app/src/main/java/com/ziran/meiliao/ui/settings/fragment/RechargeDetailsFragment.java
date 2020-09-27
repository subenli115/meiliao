package com.ziran.meiliao.ui.settings.fragment;

import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.BillDetailsBean;
import com.ziran.meiliao.ui.settings.adapter.BillDetailsAdapter;
import com.ziran.meiliao.utils.MapUtils;

import java.util.Map;


public class RechargeDetailsFragment extends CommonRefreshFragment<CommonPresenter, CommonModel>
        implements CommonContract.View<BillDetailsBean> {

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_me_coupon;
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        loadedTip.setEmptyMsg("什么都没有~",R.mipmap.load_empty_bg);
        return new BillDetailsAdapter(getContext());
    }

    @Override
    protected void loadData() {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("userId", MyAPP.getUserId());
        defMap.put("current",page+"");
        mPresenter.getData(ApiKey.ACCOUNT_ACCOUNTLISTING_PAGE, defMap, BillDetailsBean.class);
    }

    @Override
    public void returnData(BillDetailsBean result) {
        updateData(result.getData().getRecords());
    }

}