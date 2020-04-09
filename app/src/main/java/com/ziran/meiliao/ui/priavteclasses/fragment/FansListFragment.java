package com.ziran.meiliao.ui.priavteclasses.fragment;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.SimpleDividerItemDecoration;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.FansBean;
import com.ziran.meiliao.ui.priavteclasses.adapter.FansListAdapter;
import com.ziran.meiliao.utils.MapUtils;

/**
 *  我的粉丝界面Fragment
 */

public class FansListFragment extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract
        .View<FansBean> {


    @Override
    public CommonRecycleViewAdapter getAdapter() {
        //添加下滑线
        iRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getContext(),null, 1));
        return new FansListAdapter(getContext(),R.layout.item_sjk_fans_list);
    }

    @Override
    protected void loadData() {
        mPresenter.postData(ApiKey.LIVE_FANS_LIST, MapUtils.getOnlyPage(page), FansBean.class);
    }

    @Override
    public void returnData(FansBean result) {
        updateData(result.getData());
    }
}
