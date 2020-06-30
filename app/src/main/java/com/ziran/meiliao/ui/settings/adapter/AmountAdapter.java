package com.ziran.meiliao.ui.settings.adapter;

import android.content.Context;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.abslistview.CommonAblistViewAdapter;
import com.ziran.meiliao.ui.bean.PurseListCoinBean;
import com.ziran.meiliao.ui.bean.RechargeBean;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/6 15:35
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/6$
 * @updateDes ${TODO}
 */


public class AmountAdapter extends CommonAblistViewAdapter<RechargeBean.DataBean.RecordsBean> {

    public AmountAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }


    @Override
    public void convert(ViewHolderHelper holder,RechargeBean.DataBean.RecordsBean item, int position) {
        holder.setText(R.id.tv_recharge_gold, item.getName());
        if (item.getPrice() > 1) {
            holder.setText(R.id.tv_recharge_amount, String.format("￥%.0f", item.getPrice()));
        } else {
            holder.setText(R.id.tv_recharge_amount, String.format("￥%.2f", item.getPrice()));
        }
    }


}
