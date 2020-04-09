package com.ziran.meiliao.ui.workshops.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.JYGColumnBean;
import com.ziran.meiliao.ui.workshops.adapter.CourseLibrarySearchAdapter;
import com.ziran.meiliao.ui.workshops.util.QJGDataUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.TopSearchBarView;

import butterknife.Bind;

/**
 * 减压馆-分类Fragment
 * Created by Administrator on 2017/1/7.
 */

public class CourseLibrarySearchFragment extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract
        .View<JYGColumnBean> {


    @Bind(R.id.topSearchBar)
    TopSearchBarView topSearchBar;
    @Bind(R.id.tv_key)
    TextView tvKey;
    private String key;
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_course_library_search;
    }


    @Override
    protected void initView() {
        super.initView();
        key = getIntentExtra(getActivity().getIntent(), AppConstant.ExtraKey.FROM_TYPE);
        ViewUtil.setText(tvKey,key);
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        return new CourseLibrarySearchAdapter(getContext());
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {

    }

    @Override
    protected void loadData() {
        mPresenter.getData(ApiKey.GET_ALBUM_COLUMN, MapUtils.getDefMap(false), JYGColumnBean.class);
    }

    @Override
    public void returnData(JYGColumnBean result) {
        updateData(QJGDataUtil.getTeamData());
    }

}
