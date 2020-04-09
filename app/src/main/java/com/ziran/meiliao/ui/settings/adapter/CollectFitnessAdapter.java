package com.ziran.meiliao.ui.settings.adapter;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.adapter.OneSlideAdapter;
import com.ziran.meiliao.ui.adapter.OneSlideViewHolder;
import com.ziran.meiliao.ui.bean.FitnessCollectListBean;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.GlideRoundTransform;

import java.util.HashSet;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/8.
 */

public class CollectFitnessAdapter extends OneSlideAdapter<FitnessCollectListBean.DataBean.CollectListBean> {

    private Map<String, String> collectMap;

    public CollectFitnessAdapter(Context context, int layoutId) {
        super(context, layoutId);
        from_type = FROM_ACT;
    }

    @Override
    public int onBindSlideViewId() {
        return R.id.item_content_rl;
    }

    @Override
    public void convertData(OneSlideViewHolder holder, FitnessCollectListBean.DataBean.CollectListBean itemData, int position) {
        Glide.with(mContext).load(itemData.getPic()).transform(new GlideRoundTransform(mContext)).into( holder.getImageView(R.id.iv_item_sjk_act_img));
        holder.setText(R.id.tv_title, itemData.getName());
        holder.setText(R.id.tv_time, itemData.getDescription());
    }


    @Override
    protected void deleteSlef(HashSet<FitnessCollectListBean.DataBean.CollectListBean> valueSet) {

        if (EmptyUtils.isEmpty(valueSet)) return;
        StringBuilder sb = new StringBuilder();
        for (FitnessCollectListBean.DataBean.CollectListBean dataBean : valueSet) {
             collectMap = MapUtils.getDefMap(true);
            collectMap.put("courseId",dataBean.getCourseId()+"");
            collectMap.put("cancel",0+"");
        }
        OkHttpClientManager.postAsync(ApiKey.JSCOURSE_COLLECT, collectMap, new NewRequestCallBack<Result>(Result.class) {
            @Override
            public void onSuccess(Result result) {

            }
        });
    }
}
