package com.ziran.meiliao.ui.priavteclasses.fragment;

import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.entry.CachePageEntry;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.SJKHistoryBean;
import com.ziran.meiliao.ui.priavteclasses.activity.HorizontalHistoryActivity;
import com.ziran.meiliao.ui.priavteclasses.adapter.SJKHistoryCourseAdapter;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.MapUtils;

/**
 * 私家课-直播详情-简介Fragment
 * Created by Administrator on 2017/1/7.
 */

public class SJKHistoryCourseFragment extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract
        .View<SJKHistoryBean> {

    @Override
    public CommonRecycleViewAdapter getAdapter() {

        String courseId = getIntentExtra(getActivity().getIntent(), AppConstant.SPKey.COURSE_ID);
        return new SJKHistoryCourseAdapter(getContext(), R.layout.item_sjk_history_course);
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        if (!CheckUtil.check(getContext(), getView())) return;
        SJKHistoryBean.DataBean dataBean = EmptyUtils.parseObject(o);
        HorizontalHistoryActivity.startAction(getContext(), String.valueOf(dataBean.getId()), 0);
    }

    @Override
    protected void loadData() {
        mPresenter.getData(ApiKey.GET_COURSE_HISTORY, MapUtils.getOnlyPage(page), SJKHistoryBean.class);
    }

    @Override
    protected boolean noWifiState() {
        final SJKHistoryBean result = CachePageEntry.loadData(AppConstant.CACHE_PAGE_HISTORY_COURSE, SJKHistoryBean.class);
        if (EmptyUtils.isNotEmpty(result)) {
            HandlerUtil.runMain(new Runnable() {
                @Override
                public void run() {
                    updateData(result.getData());
                }
            }, 80);
            return true;
        }
        return false;
    }

    @Override
    public void returnData(SJKHistoryBean result) {
        CachePageEntry.insertData(AppConstant.CACHE_PAGE_HISTORY_COURSE, result, page);
        updateData(result.getData());
    }
}
