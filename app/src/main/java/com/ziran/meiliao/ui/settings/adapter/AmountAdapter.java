package com.ziran.meiliao.ui.settings.adapter;

import android.content.Context;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.abslistview.CommonAblistViewAdapter;
import com.ziran.meiliao.ui.bean.PurseListCoinBean;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/6 15:35
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/6$
 * @updateDes ${TODO}
 */


public class AmountAdapter extends CommonAblistViewAdapter<PurseListCoinBean.DataBean.ListBean> {

    public AmountAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    private int selectPosition;

    @Override
    public void convert(ViewHolderHelper holder, PurseListCoinBean.DataBean.ListBean item, int position) {
        if (item.isSelect()) {
            holder.setBackgroundRes(R.id.ll_recharge_bg, R.drawable.shape_grid_amount_select);
            holder.setTextColor(R.id.tv_recharge_amount, R.color.textColor_teshe);
            selectPosition = position;
            holder.getView(R.id.tv_points).setVisibility(View.VISIBLE);
        } else {
            holder.setTextColor(R.id.tv_recharge_amount, R.color.textColor_999);
            holder.setBackgroundRes(R.id.ll_recharge_bg, R.drawable.shape_grid_amount);
            holder.getView(R.id.tv_points).setVisibility(View.INVISIBLE);
        }
        holder.setText(R.id.tv_recharge_gold, String.format("%d金币", item.getCoin()));
        if (item.getRmb()>1){
            holder.setText(R.id.tv_recharge_amount, String.format("￥%.0f", item.getRmb()));
        }else{
            holder.setText(R.id.tv_recharge_amount, String.format("￥%.2f", item.getRmb()));
        }
        holder.setText(R.id.tv_points,item.getJifen()+"积分");
    }

    public void changeSelect(int position) {
        if (selectPosition != -1 && selectPosition != position) {
            getItem(selectPosition).setSelect(false);
            get(position).setSelect(true);
            notifyDataSetChanged();
        }
    }

    public PurseListCoinBean.DataBean.ListBean getSelect() {
        return getItem(selectPosition);
    }
}
