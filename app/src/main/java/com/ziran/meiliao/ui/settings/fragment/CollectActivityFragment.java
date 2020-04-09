package com.ziran.meiliao.ui.settings.fragment;

import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.ActisData;
import com.ziran.meiliao.ui.bean.SJKActivityBean;
import com.ziran.meiliao.ui.priavteclasses.activity.GongZuoFangActivity;
import com.ziran.meiliao.ui.settings.adapter.CollectActivityAdapter;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.MapUtils;

import rx.functions.Action1;

/**
 * 收藏活动Fragment
 * Created by Administrator on 2017/1/7.
 */

public class CollectActivityFragment extends DeleteRefreshFragment<CommonPresenter, CommonModel>
        implements CommonContract.View<SJKActivityBean> {

    @Override
    protected void loadData() {
        mPresenter.postData(ApiKey.USER_HOME_LIST_ACTIVITY, MapUtils.getOnlyPage(page), SJKActivityBean.class);
    }

    @Override
    public void returnData(SJKActivityBean result) {
        updateData(result.getData());
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
       super.onItemClick(parent, view, o, position);
        if (canNotStartAct) return;
        if (!CheckUtil.check(getContext(),getView())) return;
        ActisData dataBean = EmptyUtils.parseObject(o);
        if (EmptyUtils.isNotEmpty(dataBean)) {
            dataBean.setIsCollect(true);
            GongZuoFangActivity.startAction(getContext(), dataBean);
        }
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        mRxManager.on(AppConstant.UPDATE_ENROL_ACTIVITY, new Action1<ActisData>() {
            @Override
            public void call(ActisData dataBean) {
                if (mAdapter!=null ){
                    mAdapter.remove(dataBean);
                }
            }
        });
        loadedTip.setEmptyMsg(getString(R.string.empry_collect_act), R.mipmap.ic_empty_collect);
        return new CollectActivityAdapter(getContext(), R.layout.item_me_collect_act);
    }

    @Override
    protected void deleteItem(Integer fromType) {
        switch (fromType) {
            case 3:
                if (mAdapter.getItemCount() == 0) {
                    showEmtry();
                }
        }
    }

}
