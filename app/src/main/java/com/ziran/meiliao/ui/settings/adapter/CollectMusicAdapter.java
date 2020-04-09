package com.ziran.meiliao.ui.settings.adapter;

import android.content.Context;

import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.R;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.entry.MusicEntry;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.adapter.OneSlideAdapter;
import com.ziran.meiliao.ui.adapter.OneSlideViewHolder;
import com.ziran.meiliao.utils.MapUtils;

import java.util.HashSet;

/**
 * Created by Administrator on 2017/1/14.
 */

public class CollectMusicAdapter extends OneSlideAdapter<MusicEntry> {


    public CollectMusicAdapter(Context context, int layoutId) {
        super(context, layoutId);
        from_type = FROM_MUSIC;
    }

    @Override
    public int onBindSlideViewId() {
        return R.id.item_content_rl;
    }

    @Override
    public void convertData(OneSlideViewHolder helper, MusicEntry bean, int position) {
        helper.setText(R.id.tv_item_jyg_audio_name, bean.getName());
    }

    @Override
    protected void deleteSlef(HashSet<MusicEntry> valueSet) {
        if (EmptyUtils.isEmpty(valueSet)) return;
        StringBuilder sb = new StringBuilder();
        for (MusicEntry dataBean : valueSet) {
            sb.append(",").append(dataBean.getId());
        }
        sb.deleteCharAt(0);
        OkHttpClientManager.postAsync(ApiKey.COLLECT_MUSIC, MapUtils.getCollect(true, "musicIds", sb.toString()), new NewRequestCallBack<Result>(Result.class) {
            @Override
            public void onSuccess(Result result) {

            }
        });
    }
}
