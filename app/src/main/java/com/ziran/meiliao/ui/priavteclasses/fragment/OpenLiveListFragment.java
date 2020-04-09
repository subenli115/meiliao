package com.ziran.meiliao.ui.priavteclasses.fragment;

import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.LiveListBean;
import com.ziran.meiliao.ui.priavteclasses.adapter.OpenLiveListAdapter;

/**
 * 私家课-直播详情-简介Fragment
 * Created by Administrator on 2017/1/7.
 */

public class OpenLiveListFragment extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract
        .View<LiveListBean> {


    @Override
    public CommonRecycleViewAdapter getAdapter() {
        setRecyclerEnabled(false);
        return new OpenLiveListAdapter(getContext(),R.layout.item_open_live_list);
    }



    @Override
    protected void loadData() {
//        mPresenter.getData(ApiKey.TEACHER_LIST_TRAILER, MapUtils.getOnlyPage(page), LiveListBean.class);
        if (getArguments()!=null){
            updateData(getArguments().getParcelableArrayList("list"));
        }
    }


    @Override
    public void returnData(LiveListBean result) {
        updateData(result.getData());
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {

    }
}
