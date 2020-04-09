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

public class ZhuanLanCommentAdapter extends MultiItemRecycleViewAdapter<Object> {


    public ZhuanLanCommentAdapter(Context context) {
        super(context, new CommentInMultiItemType());
    }

    @Override
    public void convert(final ViewHolderHelper helper, final Object obj, int position) {
        switch (getItemViewType(position)) {
            case TYPE_DATA:
                SubscribeCommentListHelper.convert(helper, (SubscribeCommentListBean.DataBean) obj, position);
                break;
            case TYPE_HEAD:
                helper.setText(R.id.tv_item_jyg_audio_title, obj.toString());
                break;
        }
    }

    private static class CommentInMultiItemType implements MultiItemTypeSupport<Object> {
        @Override
        public int getLayoutId(int itemType) {
            switch (itemType) {
                case TYPE_DATA:
                    return R.layout.item_sjk_subscribe_audio_profit;
                case TYPE_HEAD:
                    return R.layout.item_single_title;
            }
            return -1;
        }

        @Override
        public int getItemViewType(int position, Object dataBean) {
            if (dataBean instanceof String) {
                return TYPE_HEAD;
            }
            return TYPE_DATA;
        }
    }

}
