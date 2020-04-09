package com.ziran.meiliao.ui.workshops.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.commonwidget.LoadingTip;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.SearchItemBean;
import com.ziran.meiliao.ui.bean.TeacherLibraryBean;
import com.ziran.meiliao.ui.workshops.adapter.CrowdFundingChooseTopicAdapter;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.TopSearchBarView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/11 11:37
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/11$
 * @updateDes ${TODO}
 */

public class TeamChooseTeacherFragment extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract
        .View<TeacherLibraryBean>, AppConstant.SearchId {
    @Bind(R.id.topSearchBar)
    TopSearchBarView mTopSearchBarView;


    private Map<String, String> defMap;
    private String courseId = "";

    @Override
    protected void initBundle(Bundle extras) {
        courseId = extras.getString(AppConstant.ExtraKey.FROM_ID);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_main_choose_common;
    }

    @Override
    protected void initOther() {
        setLoadedTipState(LoadingTip.LoadStatus.empty);
        defMap = MapUtils.getDefMap(true);
        defMap.put("courseId", courseId);
        defMap.put("pageSize", "10");
        mTopSearchBarView.setOnSearchListener(new TopSearchBarView.onSearchListener() {
            @Override
            public void search(String newValue) {
                onRefresh();
            }
        });
    }

    private void searchText() {
        defMap.put("page", String.valueOf(page));
        defMap.put("condition", mTopSearchBarView.getContent());
        mPresenter.postData(ApiKey.CROWN_FUND_SEARCH_CFQCOURSE_TEACHERS, defMap, TeacherLibraryBean.class);
    }


    @Override
    public CommonRecycleViewAdapter getAdapter() {
        loadedTip.setEmptyMsg("暂无内容", R.mipmap.ic_empty_nocontent);
//        return new CourseLibraryTeacherAdapter(getContext());
        return new CrowdFundingChooseTopicAdapter(getContext(), AppConstant.SearchId.RESULT_TEAM);
    }

    @Override
    protected void loadData() {
        searchText();
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        if (mAdapter.getItemViewType(position) == RESULT_TEAM) {
            RxManagerUtil.post(AppConstant.RXTag.CROWD_FUNDING_CHOOSE_DATA, HandlerUtil.obj(WHAT_HOME_TEACHER_LIST, o));
            finish();
        }
    }

    @Override
    public void returnData(TeacherLibraryBean result) {
        List<SearchItemBean> list = new ArrayList<>();
        boolean loadMore = true;
        if (EmptyUtils.isNotEmpty(result.getData().getDataList())){
            list.addAll(result.getData().getDataList());
        }else{
            list.add(SearchItemBean.empty());
            loadMore = false;
        }
        if (EmptyUtils.isNotEmpty(result.getData().getRecList())){
            list.add(SearchItemBean.xiangGuan());
            list.addAll(result.getData().getRecList());
        }
        updateData(list);
        if (!loadMore){
            iRecyclerView.setLoadMoreEnabled(false);
        }
//        if (EmptyUtils.isNotEmpty(result.getData())) updateData(result.getData().getDataList());
    }


    @Override
    public void onPause() {
        super.onPause();
        mTopSearchBarView.hideKeyBoard();
    }


}
