package com.ziran.meiliao.ui.priavteclasses.fragment;

import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.SearchAllResultBean;
import com.ziran.meiliao.ui.bean.SearchHotTagBean;
import com.ziran.meiliao.ui.bean.SearchItemBean;
import com.ziran.meiliao.ui.priavteclasses.adapter.SearchAllAdapter;
import com.ziran.meiliao.ui.priavteclasses.contract.SearchContract;
import com.ziran.meiliao.ui.priavteclasses.presenter.SearchPresenter;
import com.ziran.meiliao.ui.priavteclasses.util.SearchUtil;
import com.ziran.meiliao.utils.MapUtils;

import java.util.List;
import java.util.Map;


/**
 * 私家课-活动Fragment
 * Created by Administrator on 2017/1/7.
 */

public class SearchDetailFragment extends CommonRefreshFragment<SearchPresenter, CommonModel> implements SearchContract.View {


    @Override
    public void getHotTag(SearchHotTagBean data) {
    }

    @Override
    public void searchResult(SearchAllResultBean.DataBean data) {
        List<SearchItemBean> searchItemBeen = SearchUtil.changeData(data,false,true);
        updateData(searchItemBeen);
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        type = getIntentExtra(AppConstant.ExtraKey.FROM_TYPE);
        content = getIntentExtra(AppConstant.ExtraKey.KEY_CONTENT);
        setRecyclerEnabled(false,true);
        return new SearchAllAdapter(getContext());
    }

    private String type;
    private String content;

    @Override
    protected void loadData() {
        Map<String, String> stringMap = MapUtils.getOnlyPage(page);
        stringMap.put("type", type);
        stringMap.put("content", content);
        stringMap.put("pageSize", "10");
        mPresenter.searchResult(ApiKey.SEARCH_SELECT_BY_PARAMS_NEW, stringMap);
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        super.onItemClick(parent, view, o, position);
        SearchItemBean bean = (SearchItemBean) o;
        SearchUtil.onItemClick(getContext(), bean);
    }
}
