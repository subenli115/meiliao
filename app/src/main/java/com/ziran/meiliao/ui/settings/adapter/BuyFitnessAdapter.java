package com.ziran.meiliao.ui.settings.adapter;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.ui.adapter.OneSlideAdapter;
import com.ziran.meiliao.ui.adapter.OneSlideViewHolder;
import com.ziran.meiliao.ui.bean.FitnessIsBuyBean;
import com.ziran.meiliao.widget.GlideRoundTransform;

import java.util.Map;

/**
 * Created by Administrator on 2017/2/8.
 */

public class BuyFitnessAdapter extends OneSlideAdapter<FitnessIsBuyBean.DataBean.BuyListBean> {

    private Map<String, String> collectMap;

    public BuyFitnessAdapter(Context context, int layoutId) {
        super(context, layoutId);
        from_type = FROM_ACT;
    }

    @Override
    public int onBindSlideViewId() {
        return R.id.item_content_rl;
    }

    @Override
    public void convertData(OneSlideViewHolder holder, FitnessIsBuyBean.DataBean.BuyListBean itemData, int position) {
        Glide.with(mContext).load(itemData.getPic()).transform(new GlideRoundTransform(mContext)).into( holder.getImageView(R.id.iv_item_sjk_act_img));
        holder.setText(R.id.tv_title, itemData.getName());
        holder.setText(R.id.tv_time, itemData.getDescription());
    }


}
