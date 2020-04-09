package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.ziran.meiliao.ui.adapter.helper.WordshopsHelper;
import com.ziran.meiliao.ui.bean.ActisData;

/**
 * Created by Administrator on 2017/1/14.
 */

public class MainSJKActivityAdapter extends MultiItemRecycleViewAdapter<ActisData> {

    public static final int TYPE_LEFT = 10;
    public static final int TYPE_TOP = 11;
    public MainSJKActivityAdapter(Context context, MultiItemTypeSupport<ActisData> multiItemTypeSupport) {
        super(context, multiItemTypeSupport);

    }

    @Override
    public void convert(final ViewHolderHelper helper, final ActisData bean, final int position) {
        WordshopsHelper.convert(helper,bean,position,getItemViewType(position)== TYPE_TOP);
    }

    public static class ActivityMultiItemType implements MultiItemTypeSupport<ActisData> {

        public ActivityMultiItemType() {
        }

        @Override
        public int getLayoutId(int itemType) {
            switch (itemType) {
                case TYPE_LEFT:
                    return R.layout.item_main_sjk_act_left;
                case TYPE_TOP:
                    return R.layout.item_main_sjk_act_top;
            }
            return -1;
        }

        @Override
        public int getItemViewType(int position, ActisData dataBean) {
            return dataBean.getStatus() == 1 ? TYPE_TOP : TYPE_LEFT;
        }
    }
}
