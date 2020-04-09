package com.ziran.meiliao.ui.settings.adapter;

import android.content.Context;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.ui.bean.ActisData;
import com.ziran.meiliao.utils.StringUtils;

/**
 * Created by Administrator on 2017/2/8.
 */

public class EnrolAdapter extends CommonRecycleViewAdapter<ActisData> {

    public EnrolAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolderHelper holder, ActisData itemData, int position) {
        holder.setImageUrl(R.id.iv_item_enrol_img,
                itemData.getPicture(), R.mipmap.ic_loading_rectangle);
        holder.setText(R.id.tv_item_enrol_title, itemData.getTitle());
        TextView tvFlag = holder.getView(R.id.tv_item_enrol_flag);
        switch (itemData.getTag()) {
            case 0:
                tvFlag.setTextColor(mContext.getResources().getColor(R.color.textColor_teshe));
                tvFlag.setText(R.string.to_attend);
                break;
            case 1:
                tvFlag.setTextColor(mContext.getResources().getColor(R.color.textColor_9f));
                tvFlag.setText(R.string.already_over);
                break;
            default:
                tvFlag.setTextColor(mContext.getResources().getColor(R.color.textColor_666));
                tvFlag.setText(R.string.have_in_hand);
                break;
        }
        holder.setText(R.id.tv_item_enrol_address, itemData.getAddress());
        holder.setText(R.id.tv_item_enrol_date, StringUtils.format(R.string.starttime_to_end,itemData.getBeginTime(),itemData.getEndTime()));
    }
}
