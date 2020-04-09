package com.ziran.meiliao.ui.settings.fragment;

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
import com.ziran.meiliao.ui.bean.ActisData;
import com.ziran.meiliao.ui.bean.SJKActivityBean;
import com.ziran.meiliao.ui.me.activity.MyWorkshopsOrderActivity;
import com.ziran.meiliao.ui.settings.adapter.EnrolAdapter;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.MapUtils;

/**
 * 报名活动Fragment
 * Created by Administrator on 2017/1/16.
 */

public class MyEnrolActFragment extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract.View<SJKActivityBean> {


    @Override
    public CommonRecycleViewAdapter getAdapter() {
        loadedTip.setEmptyMsg(getString(R.string.emtry_enrol_act), R.mipmap.ic_empty_act, getString(R.string.find_act));
        return new EnrolAdapter(getContext(), R.layout.item_me_enrol_act);
    }

    @Override
    protected void loadData() {
        mPresenter.postData(ApiKey.JOIN_ACTIVITY_LIST, MapUtils.getOnlyPage(page), SJKActivityBean.class);
    }

    @Override
    public void returnData(SJKActivityBean result) {
        updateData(result.getData());
    }

    @Override
    public void onResume() {
        super.onResume();
        if (needRefresh){
            onRefresh();
            needRefresh = false;
        }
    }
    private boolean needRefresh= false;
    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        if (!CheckUtil.check(getContext(), getView())) return;
        ActisData dataBean = EmptyUtils.parseObject(o);
        if (dataBean != null) {
            MyWorkshopsOrderActivity.startAction(getContext(), dataBean);
            needRefresh = true;
        }
    }

    @Override
    public void reload() {
        mPresenter.postData(ApiKey.JOIN_ACTIVITY_LIST, MapUtils.getOnlyPage(page), SJKActivityBean.class);
    }
}
