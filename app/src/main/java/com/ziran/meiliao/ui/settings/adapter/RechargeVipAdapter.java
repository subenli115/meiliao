package com.ziran.meiliao.ui.settings.adapter;

import android.content.Context;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.abslistview.CommonAblistViewAdapter;
import com.ziran.meiliao.ui.bean.UserVipV1Bean;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/6 15:35
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/6$
 * @updateDes ${TODO}
 */


public class RechargeVipAdapter extends CommonAblistViewAdapter<UserVipV1Bean.DataBean> {

    public RechargeVipAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    private int selectPosition;

    @Override
    public void convert(ViewHolderHelper holder, UserVipV1Bean.DataBean item, int position) {
        if (item.isSelect()) {
            holder.setBackgroundRes(R.id.ll_joinvip_bg, R.drawable.shape_grid_amount_select);
//            holder.setTextColor(R.id.tv_recharge_amount, R.color.textColor_teshe);
            selectPosition = position;
        } else {
//            holder.setTextColor(R.id.tv_recharge_amount, R.color.textColor_999);
            holder.setBackgroundRes(R.id.ll_joinvip_bg, R.drawable.shape_grid_amount);
        }
        holder.setText(R.id.tv_joinvip_title,item.getTitle());
        int price = (int)(item.getPrice()>1 ?  item.getPrice() : 1);
        holder.setText(R.id.tv_joinvip_amount, String.format("%d元", price));
    }

    public void changeSelect(int position) {
        if (selectPosition != -1 && selectPosition != position) {
            getItem(selectPosition).setSelect(false);
            get(position).setSelect(true);
            notifyDataSetChanged();
        }
    }


    public UserVipV1Bean.DataBean getSelect(){
        return getItem(selectPosition);
    }
}
