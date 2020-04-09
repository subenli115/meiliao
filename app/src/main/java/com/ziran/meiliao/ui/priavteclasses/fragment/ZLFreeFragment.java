package com.ziran.meiliao.ui.priavteclasses.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.RecordListBean;
import com.ziran.meiliao.ui.priavteclasses.activity.SubscribeVideoActivity;
import com.ziran.meiliao.ui.priavteclasses.activity.ZLAudioImageTextActivity;
import com.ziran.meiliao.ui.priavteclasses.adapter.ZLFreeAdapter;
import com.ziran.meiliao.ui.priavteclasses.contract.ZhuanLanSubscribeContract;
import com.ziran.meiliao.ui.priavteclasses.presenter.ZhuanLanScbscribePresenter;
import com.ziran.meiliao.utils.MapUtils;

import java.util.List;
import java.util.Map;

/**
 * 专栏未订阅Fragment
 */

public class ZLFreeFragment extends CommonRefreshFragment<ZhuanLanScbscribePresenter, CommonModel> implements ZhuanLanSubscribeContract.View {

    String specColumnId;
    private Map<String, String> stringMap;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_zl_free;
    }

    @Override
    protected void initBundle(Bundle extras) {
        try {
            specColumnId = extras.getString("_ID");
        } catch (Exception e) {
            specColumnId = "";
            e.printStackTrace();
        } finally {
            stringMap = MapUtils.getOnlyCan(AppConstant.ExtraKey.SUBSCRIPTION_ID, specColumnId);
            stringMap.put("free", "1");
        }
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        return new ZLFreeAdapter(getContext());
    }

    @Override
    protected void loadData() {
        LogUtils.logd("stringMap:" + stringMap);
        if (stringMap == null) return;
        stringMap.put("page", String.valueOf(page));
        mPresenter.getRecordList(ApiKey.SPEC_COLUMN_LIST_RECORD, stringMap);
    }


    @Override
    public void showBuySpecColumnResult(Result userCount) {

    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        RecordListBean.DataBean recordBean = EmptyUtils.parseObject(o);
        Bundle bundle = new Bundle();
        bundle.putString("subscriptionId", specColumnId);
        bundle.putString("targetId", String.valueOf(recordBean.getTargetId()));
        bundle.putString("typeId", String.valueOf(recordBean.getType()));
        bundle.putParcelable("RecordListBean", recordBean);
        LogUtils.logd("recordBean.type:"+recordBean.getType());
        if (recordBean.getType() == 1) {
            SubscribeVideoActivity.startAction(getContext(), specColumnId, String.valueOf(recordBean.getTargetId()),true);
        } else {
            startActivity(ZLAudioImageTextActivity.class, bundle);
        }
    }

    @Override
    public void showRecordList(List<RecordListBean.DataBean> beanList) {
        updateData(beanList);
    }

}
