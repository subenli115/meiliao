package com.ziran.meiliao.ui.settings.adapter;

import android.content.Context;

import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.ziran.meiliao.R;
import com.ziran.meiliao.ui.bean.CouponBean;
import com.ziran.meiliao.utils.StringUtils;

/**
 * Created by Administrator on 2017/2/8.
 */

public class CouponAdapter extends MultiItemRecycleViewAdapter<CouponBean.DataBean> {

    public CouponAdapter(Context context) {
        super(context, new CouponMultiItemType());
    }

    @Override
    public void convert(ViewHolderHelper holder, CouponBean.DataBean itemData, int position) {
        holder.setText(R.id.tv_item_coupon_title, itemData.getTitle());
        holder.setText(R.id.tv_item_coupon_date, itemData.getBeginTime() + " ~ " + itemData.getEndTime());
        holder.setText(R.id.tv_item_coupon_money, StringUtils.format(R.string.price, itemData.getFaceValue()));
    }

    private static class CouponMultiItemType implements MultiItemTypeSupport<CouponBean.DataBean> {

        @Override
        public int getLayoutId(int itemType) {
            switch (itemType) {
                case 1:
                    return R.layout.item_me_coupon;
                case 2:
                    return R.layout.item_me_coupon_used;
            }
            return -1;
        }

        @Override
        public int getItemViewType(int position, CouponBean.DataBean bean) {
            return bean.isUsed() ? 2 : 1;
        }
    }
}
