package com.ziran.meiliao.ui.settings.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.MessageBean;
import com.ziran.meiliao.ui.settings.activity.MessageDetailActivity;
import com.ziran.meiliao.ui.settings.adapter.GFMessageAdapter;
import com.ziran.meiliao.utils.MapUtils;

/**
 * 官方消息Fragment
 * Created by Administrator on 2017/1/16.
 */

public class MessageFragment extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract.ActionView<MessageBean,Result> {

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        loadedTip.setEmptyMsg(getString(R.string.not_message), R.mipmap.ic_empty_message);
        iRecyclerView.setPadding(defPadding, 0, defPadding, 0);
        return new GFMessageAdapter(getContext(), R.layout.item_me_gf_message);
    }

    @Override
    protected void initView() {
        super.initView();
        int unReadCount = getIntentExtra(getActivity().getIntent(), AppConstant.ExtraKey.UNREAD_COUNT,-1);
        if (unReadCount>0){
            mPresenter.postAction(ApiKey.MESSAGE_READ_ALL,MapUtils.getDefMap(true), Result.class);
        }
    }

    @Override
    public void returnData(MessageBean result) {
        updateData(result.getData());
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        MessageBean.DataBean dataBean = EmptyUtils.parseObject(o);
        if (EmptyUtils.isNotEmpty(dataBean)) {
            Bundle bundle = new Bundle();
            bundle.putString(AppConstant.SPKey.EXTRAS_URL, dataBean.getUrl());
            bundle.putString(AppConstant.ExtraKey.EXTRAS_TITLE, dataBean.getTitle());
            MessageDetailActivity.startAction(getContext(), bundle);
        } else {
            MessageDetailActivity.startAction(getContext(), null);
        }
    }

    @Override
    protected void loadData() {
        mPresenter.postData(ApiKey.LIST_MESSAGE_OFFICIAL, MapUtils.getOnlyPage(page), MessageBean.class);
    }

    @Override
    public void returnAction(Result result) {

    }
}
