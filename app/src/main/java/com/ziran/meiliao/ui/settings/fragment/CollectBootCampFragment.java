package com.ziran.meiliao.ui.settings.fragment;

import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.NewDecompressionmuseum.activity.FitnessExeActivity;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.ActisData;
import com.ziran.meiliao.ui.bean.FitnessCollectListBean;
import com.ziran.meiliao.ui.settings.adapter.CollectFitnessAdapter;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.MapUtils;

import rx.functions.Action1;

/**
 * 收藏训练营Fragment
 * Created by Administrator on 2017/1/7.
 */

public class CollectBootCampFragment extends DeleteRefreshFragment<CommonPresenter, CommonModel>
        implements CommonContract.View<FitnessCollectListBean> {

    @Override
    protected void loadData() {
        mPresenter.postData(ApiKey.JSCOURSE_LISTCOLLECT, MapUtils.getOnlyPage(page), FitnessCollectListBean.class);
    }

    @Override
    public void returnData(FitnessCollectListBean result) {
        updateData(result.getData().getCollectList());
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        super.onItemClick(parent, view, o, position);
        if (canNotStartAct) return;
        if (!CheckUtil.check(getContext(),getView())) return;
        FitnessCollectListBean.DataBean.CollectListBean dataBean = EmptyUtils.parseObject(o);
        FitnessExeActivity.startAction(getContext(),dataBean.getCourseId());
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
        loadedTip.setEmptyMsg(getString(R.string.empry_collect_fitness), R.mipmap.ic_empty_collect);
        return new CollectFitnessAdapter(getContext(), R.layout.item_main_sjk_act_long_new_second);
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
