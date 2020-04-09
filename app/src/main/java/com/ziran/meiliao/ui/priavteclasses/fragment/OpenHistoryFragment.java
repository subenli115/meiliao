package com.ziran.meiliao.ui.priavteclasses.fragment;

import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.LiveRoomBean;
import com.ziran.meiliao.ui.bean.OpenHistoryBean;
import com.ziran.meiliao.ui.priavteclasses.adapter.OpenHistoryAdapter;
import com.ziran.meiliao.ui.priavteclasses.util.SJKLiveUtil;

/**
 * 直播间后台-开播历史Fragment
 * Created by Administrator on 2017/1/7.
 */

public class OpenHistoryFragment extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract
        .View<OpenHistoryBean> {


    @Override
    public CommonRecycleViewAdapter getAdapter() {
        setRecyclerEnabled(false);
        return new OpenHistoryAdapter(getContext(),R.layout.item_to_be_open);
    }


    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        LiveRoomBean.DataBean.HeraldListBean heraldListBean   = EmptyUtils.parseObject(o);
        SJKLiveUtil.startActivity(getContext(), String.valueOf(heraldListBean.getId()), 0, heraldListBean.getStatusX());
    }

    @Override
    protected void loadData() {
        if (getArguments()!=null){
            updateData(getArguments().getParcelableArrayList("list"));
        }
    }


    @Override
    public void returnData(OpenHistoryBean result) {
    }
}
