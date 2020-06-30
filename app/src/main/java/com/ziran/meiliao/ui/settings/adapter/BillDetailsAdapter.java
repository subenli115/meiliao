package com.ziran.meiliao.ui.settings.adapter;

import android.content.Context;
import android.graphics.Color;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.ziran.meiliao.ui.bean.BillDetailsBean;
import com.ziran.meiliao.ui.bean.CouponBean;
import com.ziran.meiliao.utils.StringUtils;

/**
 * Created by Administrator on 2017/2/8.
 */

public class BillDetailsAdapter extends MultiItemRecycleViewAdapter<BillDetailsBean.DataBean.RecordsBean> {

    public BillDetailsAdapter(Context context) {
        super(context, new CouponMultiItemType());
    }

    @Override
    public void convert(ViewHolderHelper holder, BillDetailsBean.DataBean.RecordsBean itemData, int position) {
        if(itemData.getType()==1){
            holder.setText(R.id.tv_gold,"+ "+ itemData.getMoney());
        }else {
            holder.setText(R.id.tv_gold,"- "+ itemData.getMoney());
            holder.setTextColor(R.id.tv_gold, R.color.textColor_teshe5);
        }
        holder.setText(R.id.tv_title, itemData.getRemarks());
        holder.setText(R.id.tv_time, itemData.getCreateTime());
    }


    private static class CouponMultiItemType implements MultiItemTypeSupport<BillDetailsBean.DataBean.RecordsBean> {

        @Override
        public int getLayoutId(int itemType) {
            switch (itemType) {
                case 1:
                    return R.layout.item_bill_detalis;
            }
            return -1;
        }

        @Override
        public int getItemViewType(int position, BillDetailsBean.DataBean.RecordsBean bean) {
            return 1;
        }
    }
}
