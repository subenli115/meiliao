package com.ziran.meiliao.ui.priavteclasses.fragment;

import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.SJKMoreBean;
import com.ziran.meiliao.ui.bean.SJKSingeLiveData;
import com.ziran.meiliao.ui.bean.TrailerBean;
import com.ziran.meiliao.ui.priavteclasses.activity.TrailerWebActivity;
import com.ziran.meiliao.ui.priavteclasses.adapter.SJKTrailerAdapter;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.MapUtils;

/**
 * 私家课-直播详情-简介Fragment
 * Created by Administrator on 2017/1/7.
 */

public class SJKTrailerFragment extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract
        .View<SJKMoreBean> {


    @Override
    public CommonRecycleViewAdapter getAdapter() {
        return new SJKTrailerAdapter(getContext(),R.layout.item_main_sjk_trailer);
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        if (!CheckUtil.check(getContext(), getView())) return;
        SJKSingeLiveData bean = EmptyUtils.parseObject(o);
        TrailerBean.DataBean dataBean = new TrailerBean.DataBean();
        dataBean.setAuthor(bean.getAuthor());
        dataBean.setId(bean.getId());
        dataBean.setPicture(bean.getPicture());
        dataBean.setUrl(bean.getUrl());
        dataBean.setTitle(bean.getTitle());
//        dataBean.setDescript(bean.getDescript());
        dataBean.setCollect(bean.isCollect());
        TrailerWebActivity.startAction(getContext(),dataBean,dataBean.getUrl());
    }

    @Override
    protected void loadData() {
        mPresenter.getData(ApiKey.GET_COURSE_TRAILER_LIST, MapUtils.getOnlyPage(page), SJKMoreBean.class);
    }


    @Override
    public void returnData(SJKMoreBean result) {
        updateData(result.getData());
    }
}
