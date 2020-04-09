package com.ziran.meiliao.ui.settings.adapter;

import android.content.Context;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.adapter.OneSlideAdapter;
import com.ziran.meiliao.ui.adapter.OneSlideViewHolder;
import com.ziran.meiliao.ui.bean.ActisData;
import com.ziran.meiliao.utils.MapUtils;

import java.util.HashSet;

/**
 * Created by Administrator on 2017/2/8.
 */

public class CollectActivityAdapter extends OneSlideAdapter<ActisData> {

    public CollectActivityAdapter(Context context, int layoutId) {
        super(context, layoutId);
        from_type = FROM_ACT;
    }

    @Override
    public int onBindSlideViewId() {
        return R.id.item_content_rl;
    }

    @Override
    public void convertData(OneSlideViewHolder holder, ActisData itemData, int position) {
        holder.setImageUrl(R.id.iv_item_enrol_img,
                itemData.getPicture(), R.mipmap.ic_loading_rectangle);
        holder.setText(R.id.tv_item_enrol_title, itemData.getTitle());
//        holder.setText(R.id.tv_item_enrol_price, StringUtils.format(R.string.price,itemData.getPrice()));
        holder.setText(R.id.tv_item_enrol_address, itemData.getAddress());
        holder.setText(R.id.tv_item_enrol_date, String.format(mContext.getString(R.string.starttime_to_end), itemData.getBeginTime(), itemData.getEndTime()));
    }


    @Override
    protected void deleteSlef(HashSet<ActisData> valueSet) {
        if (EmptyUtils.isEmpty(valueSet)) return;
        StringBuilder sb = new StringBuilder();
        for (ActisData dataBean : valueSet) {
            sb.append(",").append(dataBean.getId());
        }
        sb.deleteCharAt(0);
        OkHttpClientManager.postAsync(ApiKey.COLLECT_ACT, MapUtils.getCollect(true, "activityIds", sb.toString()), new NewRequestCallBack<Result>(Result.class) {
            @Override
            public void onSuccess(Result result) {

            }
        });
    }
}
