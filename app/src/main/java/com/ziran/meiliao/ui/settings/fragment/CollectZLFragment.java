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
import com.ziran.meiliao.ui.bean.CollectZLBean;
import com.ziran.meiliao.ui.settings.activity.CollectZLItemActivity;
import com.ziran.meiliao.ui.settings.adapter.CollectZLAdapter;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.MapUtils;

/**
 * 收藏专辑Fragment
 * Created by Administrator on 2017/1/7.
 */

public class CollectZLFragment extends DeleteRefreshFragment<CommonPresenter, CommonModel> implements CommonContract.View<CollectZLBean> {


    @Override
    protected void loadData() {
        mPresenter.postData(ApiKey.SUBSCRIPTION_COLLECT_SUBSCRIPTION_LIST, MapUtils.getOnlyPage(page), CollectZLBean.class);
    }

    @Override
    public void returnData(CollectZLBean result) {
        updateData(result.getData());
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        super.onItemClick(parent, view, o, position);
        if (canNotStartAct) return;
        if (!CheckUtil.check(getContext(), getView())) return;
        CollectZLBean.DataBean dataBean = (CollectZLBean.DataBean) o;
        if (EmptyUtils.isNotEmpty(dataBean)) {
            CollectZLItemActivity.startAction(getContext(),Integer.parseInt(dataBean.getSubscriptionId()),dataBean.getType(),dataBean.getTitle(),position);
        }
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        loadedTip.setEmptyMsg(getString(R.string.emtry_collect_zhuan_lan), R.mipmap.ic_empty_collect);
        return new CollectZLAdapter(getContext(), R.layout.item_me_collect_zhuan_lan);
    }

    @Override
    protected void deleteItem(Integer fromType) {
        switch (fromType) {
            case 15:
                if (mAdapter.getItemCount() == 0) {
                    showEmtry();
                }
        }
    }
}
