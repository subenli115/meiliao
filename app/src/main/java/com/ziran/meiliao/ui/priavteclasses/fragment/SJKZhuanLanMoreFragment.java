package com.ziran.meiliao.ui.priavteclasses.fragment;

import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.SpecColumnBean;
import com.ziran.meiliao.ui.priavteclasses.adapter.SJKHomeZhuanLanAdapter;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.MapUtils;

/**
 * 私家课-直播详情-简介Fragment
 * Created by Administrator on 2017/1/7.
 */

public class SJKZhuanLanMoreFragment extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract
        .View<SpecColumnBean> {


    @Override
    public CommonRecycleViewAdapter getAdapter() {
//        return new SJKHomeZhuanLanAdapter(getContext(), R.layout.item_sjk_recommend_zhuanlan);
        return new SJKHomeZhuanLanAdapter(getContext());
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        if (!CheckUtil.check(getContext(), getView())) return;
//        ZhuanLanDetailActivity.startAction(getContext(),o);
    }

    @Override
    protected void loadData() {
        mPresenter.getData(ApiKey.SJK_GET_SPEC_DATA, MapUtils.getOnlyPage(page), SpecColumnBean.class);
    }


    @Override
    public void returnData(SpecColumnBean result) {
        updateData(result.getData());
    }
}
