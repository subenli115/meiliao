package com.ziran.meiliao.ui.settings.fragment;

import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonwidget.LoadingTip;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.widget.LoadMoreFooterView;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.SearchItemBean;
import com.ziran.meiliao.ui.priavteclasses.util.SearchUtil;
import com.ziran.meiliao.ui.settings.adapter.NewCollectCourse2Adapter;
import com.ziran.meiliao.ui.settings.contract.CollectCourseContract;
import com.ziran.meiliao.ui.settings.presenter.CollectCoursePresenter;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 收藏课程Fragment
 * Created by Administrator on 2017/1/7.
 */

public class CollectCourseFragment extends DeleteRefreshFragment<CollectCoursePresenter, CommonModel> implements CollectCourseContract.View, AppConstant.CollectCourse {

    NewCollectCourse2Adapter newCollectCourseAdapter;

    @Override
    protected void initRecyclerView() {
        super.initRecyclerView();
        loadedTip.setEmptyMsg(StringUtils.getText(R.string.emtry_collect_course), R.mipmap.ic_empty_collect);
        setRecyclerEnabled(false, true);
    }

    private Map<String, String> stringMap;

    @Override
    protected void initView() {
        super.initView();
        stringMap = MapUtils.getDefMap(true);
        stringMap.put("pageSize", "10");

    }

    @Override
    protected void loadData() {
        final String flagKey = newCollectCourseAdapter.getFlagKey();
        if (EmptyUtils.isNotEmpty(flagKey)) {
            stringMap.put("page", String.valueOf(page));
            switch (flagKey) {
                case ITEM_TYPE_CROWD_FUND:
                    mPresenter.getCrowdFundingList(stringMap);
                    break;
                case ITEM_TYPE_TEAM:
                    mPresenter.getTeamList(stringMap);
                    break;
                case ITEM_TYPE_TEACHER:
                    mPresenter.getTeacherList(stringMap);
                    break;
                case ITEM_TYPE_ACTIVITY:
                    mPresenter.getActivityList(stringMap);
                    break;
            }
        } else {
            if (isFirstLoad) {
                isFirstLoad = false;
                setLoadedTipState(LoadingTip.LoadStatus.empty);
            }
            stringMap.put("page", String.valueOf(page));
            mPresenter.getAddCrowdFundingList(stringMap);
            mPresenter.getAddTeamList(stringMap);
            mPresenter.getAddTeacherList(stringMap);
            mPresenter.getAddActivityList(stringMap);
            page++;
        }
    }

    private boolean isFirstLoad = true;

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        super.onItemClick(parent, view, o, position);
        if (canNotStartAct) return;
        if (o instanceof SearchItemBean) {
            SearchItemBean bean = EmptyUtils.parseObject(o);
            SearchUtil.onItemClickNew(getContext(), bean, true);
        }
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        newCollectCourseAdapter = new NewCollectCourse2Adapter(getContext(), iRecyclerView);
        return newCollectCourseAdapter;
    }

    private boolean isRefresh;

    @Override
    public void onRefresh() {
        newCollectCourseAdapter.onRefresh();
        isRefresh = true;
        super.onRefresh();
    }

    @Override
    protected void deleteItem(Integer fromType) {
        switch (fromType) {
            case 2:
                if (mAdapter.getItemCount() == 0) {
                    showEmtry();
                }
        }
    }

    @Override
    public void showCrowdFundingList(List<SearchItemBean> result) {
        checkState(result);
        page++;
        if (EmptyUtils.isEmpty(result)) {
            setLoadState(LoadMoreFooterView.Status.THE_END);
        }
        newCollectCourseAdapter.updateData(ITEM_TYPE_CROWD_FUND, result);
    }

    @Override
    public void showTeamList(List<SearchItemBean> result) {
        checkState(result);
        page++;
        if (EmptyUtils.isEmpty(result)) {
            setLoadState(LoadMoreFooterView.Status.THE_END);
        }
        newCollectCourseAdapter.updateData(ITEM_TYPE_TEAM, result);
    }

    @Override
    public void showActivityList(List<SearchItemBean> result) {
        checkState(result);
        page++;
        if (EmptyUtils.isEmpty(result)) {
            setLoadState(LoadMoreFooterView.Status.THE_END);
        }
        newCollectCourseAdapter.updateData(ITEM_TYPE_ACTIVITY, result);
    }

    @Override
    public void showTeacherList(List<SearchItemBean> result) {
        checkState(result);
        page++;
        if (EmptyUtils.isEmpty(result)) {
            setLoadState(LoadMoreFooterView.Status.THE_END);
        }
        newCollectCourseAdapter.updateData(ITEM_TYPE_TEACHER, result);
    }

    @Override
    public void showAddCrowdFundingList(List<SearchItemBean> result) {
        checkState(result);
        newCollectCourseAdapter.addData(ITEM_TYPE_CROWD_FUND, result);

    }

    @Override
    public void showAddTeamList(List<SearchItemBean> result) {
        checkState(result);
        newCollectCourseAdapter.addData(ITEM_TYPE_TEAM, result);
    }

    @Override
    public void showAddActivityList(List<SearchItemBean> result) {
        checkState(result);
        newCollectCourseAdapter.addData(ITEM_TYPE_ACTIVITY, result);
    }

    public void checkState(List<SearchItemBean> result) {
        if (EmptyUtils.isNotEmpty(result)) {
            mData = result;
            setLoadedTipState(LoadingTip.LoadStatus.finish);
        }
    }

    @Override
    public void showAddTeacherList(List<SearchItemBean> result) {
        checkState(result);
        newCollectCourseAdapter.addData(ITEM_TYPE_TEACHER, result);
        if (isRefresh) {
            isRefresh = false;
            iRecyclerView.setRefreshing(false);
        }
    }
}
