package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.ziran.meiliao.ui.adapter.helper.SubscribeCommentListHelper;
import com.ziran.meiliao.ui.bean.SubscribeCommentListBean;

/**
 * 减压馆-消息
 * Created by Administrator on 2017/1/14.
 */

public class SubscribeProfitAdapter extends MultiItemRecycleViewAdapter<SubscribeCommentListBean.DataBean> {


    public SubscribeProfitAdapter(Context context) {
        super(context, new MultiItemType());
    }

    @Override
    public void convert(final ViewHolderHelper helper, final SubscribeCommentListBean.DataBean bean, int position) {
        switch (getItemViewType(position)){
            case TYPE_DATA:
                SubscribeCommentListHelper.convert(helper, bean, position);
                break;
            case TYPE_EMPTY:  break;
        }

    }

    private static final int TYPE_EMPTY = 9994;

    public static class MultiItemType implements MultiItemTypeSupport<SubscribeCommentListBean.DataBean> {

        @Override
        public int getLayoutId(int itemType) {
            switch (itemType) {
                case TYPE_EMPTY:
                    return R.layout.item_empty;
                case TYPE_DATA:
                    return R.layout.item_sjk_subscribe_audio_profit;
            }
            return -1;
        }

        @Override
        public int getItemViewType(int position, SubscribeCommentListBean.DataBean bean) {
            if (bean.getId() == -2) {
                return TYPE_EMPTY;
            }
            return TYPE_DATA;
        }
    }
}
