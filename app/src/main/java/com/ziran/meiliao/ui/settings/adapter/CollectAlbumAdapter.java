package com.ziran.meiliao.ui.settings.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.R;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.adapter.OneSlideAdapter;
import com.ziran.meiliao.ui.adapter.OneSlideViewHolder;
import com.ziran.meiliao.ui.bean.AlbumBean;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.StringUtils;

import java.util.HashSet;

/**
 * Created by Administrator on 2017/1/14.
 */

public class CollectAlbumAdapter extends OneSlideAdapter<AlbumBean> {

    public CollectAlbumAdapter(Context context, int layoutId) {
        super(context, layoutId);
        from_type = FROM_ALBUM;
    }

    @Override
    public int onBindSlideViewId() {
        return R.id.item_content_rl;
    }

    @Override
    public void convertData(OneSlideViewHolder helper, final AlbumBean bean, int position) {
        ImageView iv = helper.getView(R.id.iv_me_collect_album_img);
        TextView tv_title = helper.getView(R.id.tv_me_collect_album_title);
        TextView tv_anchor = helper.getView(R.id.tv_me_collect_album_anchor);
        TextView tv_listen_count = helper.getView(R.id.tv_jyg_listen_count);
        ImageLoaderUtils.display(mContext, iv, bean.getPicture(),R.mipmap.ic_loading_rectangle);
        tv_anchor.setText(bean.getName());
        tv_title.setText(bean.getTitle());
        tv_listen_count.setText(StringUtils.format(R.string.lister_count, bean.getListenCount()));
    }

    @Override
    protected void deleteSlef(HashSet<AlbumBean> valueSet) {
        if (EmptyUtils.isEmpty(valueSet)) return;
        StringBuilder sb = new StringBuilder();
        for (AlbumBean dataBean : valueSet) {
            sb.append(",").append(dataBean.getId());
        }
        sb.deleteCharAt(0);
        OkHttpClientManager.postAsync(ApiKey.COLLECT_ALBUM, MapUtils.getCollect(true, "albumIds", sb.toString()), new NewRequestCallBack<Result>(Result.class) {
            @Override
            public void onSuccess(Result result) {

            }
        });
    }
}
