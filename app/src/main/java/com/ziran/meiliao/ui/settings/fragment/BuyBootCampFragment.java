package com.ziran.meiliao.ui.settings.fragment;


import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.ui.NewDecompressionmuseum.activity.FitnessExeActivity;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.FitnessIsBuyBean;
import com.ziran.meiliao.ui.settings.adapter.BuyFitnessAdapter;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.MapUtils;

import java.util.Map;

public class BuyBootCampFragment extends CommonRefreshFragment<CommonPresenter, CommonModel>
        implements CommonContract.View<FitnessIsBuyBean> {

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        loadedTip.setEmptyMsg(getString(R.string.emtry_fitness), R.mipmap.ic_empty_course);
        return new BuyFitnessAdapter(getContext(), R.layout.item_main_sjk_act_long_new_second);


    }

    @Override
    protected void loadData() {
        Map<String, String> onlyPage = MapUtils.getOnlyPage(page);
        onlyPage.put("size","11");
        mPresenter.postData(ApiKey.JSCOURSE_LISTBUY, onlyPage, FitnessIsBuyBean.class);
    }

    @Override
    public void returnData(FitnessIsBuyBean result) {
        updateData(result.getData().getBuyList());
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        if (!CheckUtil.check(getContext(),getView())) return;
        FitnessIsBuyBean.DataBean.BuyListBean dataBean = EmptyUtils.parseObject(o);
        if (EmptyUtils.isNotEmpty(dataBean)) {
            FitnessExeActivity.startAction(getContext(),dataBean.getCourseId());
        }
    }
}
