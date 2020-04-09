package com.ziran.meiliao.ui.settings.adapter;

import android.content.Context;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.ziran.meiliao.ui.bean.RechargeDetailBean;

/**
 * Created by Administrator on 2017/2/8.
 */

public class RechargeDetailAdapter extends MultiItemRecycleViewAdapter<RechargeDetailBean.DataBean> {

    public RechargeDetailAdapter(Context context) {
        super(context, new RechargeDetailMultiItemType());
    }

    @Override
    public void convert(ViewHolderHelper holder, RechargeDetailBean.DataBean itemData, int position) {
        switch (getItemViewType(position)) {
            case 1:
                //                holder.setText(R.id.tv_item_single_title, TimeUtil.formatData("yyyy年MM月",itemData.getCreateTime()));
                holder.setText(R.id.tv_item_single_title, itemData.getCreateTime());
                break;
            case 2:
                holder.setText(R.id.tv_wallet_recharge_title, itemData.getTitle());
                holder.setText(R.id.tv_wallet_recharge_balance, String.format("余额: %s", itemData.getNewCoin()));
                String type = "add".equals(itemData.getType()) ? "+" : "-";
                holder.setText(R.id.tv_wallet_recharge_amount, String.format("%s%s", type, itemData.getValue()));
                break;
        }

    }


    private static class RechargeDetailMultiItemType implements MultiItemTypeSupport<RechargeDetailBean.DataBean> {

        @Override
        public int getLayoutId(int itemType) {
            switch (itemType) {
                case 1:
                    return R.layout.item_single_title2;
                case 2:
                    return R.layout.item_wallet_recharge_detail;
            }
            return -1;
        }

        @Override
        public int getItemViewType(int position, RechargeDetailBean.DataBean bean) {
            return bean.isHead() ? 1 : 2;
        }
    }
}
