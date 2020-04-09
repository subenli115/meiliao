package com.ziran.meiliao.ui.settings.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.ui.adapter.OneSlideAdapter;
import com.ziran.meiliao.ui.adapter.OneSlideViewHolder;
import com.ziran.meiliao.ui.bean.AlbumBean;
import com.ziran.meiliao.utils.StringUtils;

/**
 * Created by Administrator on 2017/1/14.
 */

public class BuyAlbumAdapter extends OneSlideAdapter<AlbumBean> {


    public BuyAlbumAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public int onBindSlideViewId() {
        return R.id.item_content_rl;
    }

    @Override
    public void convertData(OneSlideViewHolder helper, AlbumBean bean, int position) {
        if (bean == null) return;
        try {
            ImageView iv = helper.getView(R.id.iv_item_buy_course_img);
            ImageLoaderUtils.display(mContext, iv, bean.getPicture(),R.mipmap.ic_loading_square);
            helper.setText(R.id.tv_item_buy_course_title, bean.getTitle());
            helper.setText(R.id.tv_item_buy_course_profile, bean.getDetail());
            helper.setText(R.id.tv_item_buy_course_anchor, bean.getName());
            helper.setText(R.id.tv_item_buy_course_play_count, StringUtils.format(R.string.lister_count, bean.getListenCount()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
