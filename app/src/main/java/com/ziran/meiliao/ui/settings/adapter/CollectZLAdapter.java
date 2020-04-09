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
import com.ziran.meiliao.ui.bean.CollectZLBean;
import com.ziran.meiliao.utils.MapUtils;

import java.util.HashSet;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/9.
 */

public class CollectZLAdapter extends OneSlideAdapter<CollectZLBean.DataBean> {

    public CollectZLAdapter(Context context, int layoutId) {
        super(context, layoutId);
        from_type = FROM_GUANZHU;
    }

    @Override
    public int onBindSlideViewId() {
        return R.id.item_content_rl;
    }

    @Override
    public void convertData(final OneSlideViewHolder holder, final CollectZLBean.DataBean itemData, final int position) {
        holder.setImageUrlTarget(R.id.iv_me_collect_zl_img,
                itemData.getPicture(), R.mipmap.ic_loading_square);
        holder.setText(R.id.tv_me_collect_zl_title, itemData.getTitle());

        holder.setText(R.id.tv_me_collect_zl_profile,itemData.getDescript());
    }

    @Override
    protected void deleteSlef(HashSet<CollectZLBean.DataBean> valueSet) {
        if (EmptyUtils.isEmpty(valueSet)) return;
        Map<String, String> apiKeyMap;
        for (CollectZLBean.DataBean dataBean : valueSet) {
            apiKeyMap  = MapUtils.getDefMap(true);
            apiKeyMap.put("subscriptionId",dataBean.getSubscriptionId());
            apiKeyMap.put("targetId",dataBean.getTargetId());
            apiKeyMap.put("collect", "0");
            OkHttpClientManager.postAsync(ApiKey.SUBSCRIPTION_COLLECT_SUBSCRIPTION,apiKeyMap , new NewRequestCallBack<Result>(Result.class) {
                @Override
                public void onSuccess(Result result) {

                }
            });
        }
    }
}

