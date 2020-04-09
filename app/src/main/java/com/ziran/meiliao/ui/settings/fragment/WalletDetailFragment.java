package com.ziran.meiliao.ui.settings.fragment;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.RechargeDetailBean;
import com.ziran.meiliao.ui.settings.adapter.RechargeDetailAdapter;
import com.ziran.meiliao.utils.MapUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 正念币使用明细Fragment
 * Created by Administrator on 2017/1/16.
 */

public class WalletDetailFragment extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract
        .View<RechargeDetailBean> {

    private int fromType;
    public static final int FROM_TYPE_ZHICHU = 1;
    public static final int FROM_TYPE_SHOURU = 2;

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        fromType = getArguments().getInt(AppConstant.ExtraKey.FROM_TYPE, FROM_TYPE_ZHICHU);
        String emptyMsg = fromType == FROM_TYPE_ZHICHU ? "暂无支出明细" : "暂无收入明细";
        loadedTip.setEmptyMsg(emptyMsg, R.mipmap.ic_empty_nocontent);
        return new RechargeDetailAdapter(getContext());
    }

    @Override
    protected void loadData() {
        String apiKey = (fromType == FROM_TYPE_ZHICHU) ? ApiKey.PURSE_GET_USER_OUTCOME : ApiKey.PURSE_GET_USER_INCOME;
        mPresenter.getData(apiKey, MapUtils.getOnlyPage(page), RechargeDetailBean.class);
    }

    private LinkedList<String> titles = new LinkedList<>();

    @Override
    public void returnData(RechargeDetailBean result) {
        updateData(changeData(result.getData()));
    }

    @Override
    public void onRefresh() {
        titles.clear();
        super.onRefresh();
    }

    private List changeData(List<RechargeDetailBean.DataBean> data) {
        List<RechargeDetailBean.DataBean> dataBeanList = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            RechargeDetailBean.DataBean dataBean = data.get(i);
            if (!titles.contains(dataBean.getCreateTime())) {
                titles.add(dataBean.getCreateTime());
                RechargeDetailBean.DataBean titleData = new RechargeDetailBean.DataBean();
                titleData.setHead(true);
                titleData.setCreateTime(dataBean.getCreateTime());
                dataBeanList.add(titleData);
            }
            dataBeanList.add(dataBean);
        }
        return dataBeanList;
    }

}
