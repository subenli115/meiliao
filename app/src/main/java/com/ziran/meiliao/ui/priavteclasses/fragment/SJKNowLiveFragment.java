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
import com.ziran.meiliao.ui.priavteclasses.adapter.SJKNowLiveAdapter;
import com.ziran.meiliao.ui.priavteclasses.util.SJKLiveUtil;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.MapUtils;

/**
 * 私家课-直播详情-简介Fragment
 * Created by Administrator on 2017/1/7.
 */

public class SJKNowLiveFragment extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract
        .View<SJKMoreBean> {


    @Override
    public CommonRecycleViewAdapter getAdapter() {
        loadedTip.setEmptyMsg(getString(R.string.not_recomment), R.mipmap.ic_empty_nocontent);
        return new SJKNowLiveAdapter(getContext(),R.layout.item_main_sjk_nowlive);
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        if (!CheckUtil.check(getContext(), getView())) return;
        SJKSingeLiveData dataBean = EmptyUtils.parseObject(o);
        SJKLiveUtil.startActivity(getContext(),String.valueOf(dataBean.getId()),dataBean.getTag(),dataBean.getStatus());
    }

    @Override
    protected void loadData() {
        mPresenter.getData(ApiKey.GET_COURSE_ZHIBO_LIST, MapUtils.getOnlyPage(page),SJKMoreBean.class);
    }


    @Override
    public void returnData(SJKMoreBean result) {
        updateData(result.getData());
    }
}
