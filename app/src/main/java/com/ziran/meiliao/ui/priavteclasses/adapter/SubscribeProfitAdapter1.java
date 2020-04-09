package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;

import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.ui.adapter.helper.SubscribeCommentListHelper;
import com.ziran.meiliao.ui.bean.SubscribeCommentListBean;

/**
 * 减压馆-消息
 * Created by Administrator on 2017/1/14.
 */

public class SubscribeProfitAdapter1 extends CommonRecycleViewAdapter<SubscribeCommentListBean.DataBean> {


    public SubscribeProfitAdapter1(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(final ViewHolderHelper helper, final SubscribeCommentListBean.DataBean bean, int position) {
        SubscribeCommentListHelper.convert(helper, bean, position);
    }
}
