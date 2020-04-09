package com.ziran.meiliao.ui.settings.adapter;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.ziran.meiliao.R;
import com.ziran.meiliao.db.DbCore;
import com.ziran.meiliao.entry.VideoSectionEntry;
import com.ziran.meiliao.ui.adapter.OneSlideAdapter;
import com.ziran.meiliao.ui.adapter.OneSlideViewHolder;
import com.ziran.meiliao.widget.GlideRoundTransform;

/**
 * Created by Administrator on 2017/1/14.
 */

public class DownloadVideoAdapter extends OneSlideAdapter<VideoSectionEntry> {


    private final Context context;

    public DownloadVideoAdapter(Context context, int layoutId) {
        super(context, layoutId);
        from_type = FROM_VIDEO;
        this.context=context;
    }

    @Override
    public int onBindSlideViewId() {
        return R.id.item_content_rl;
    }

    @Override
    public void convertData(OneSlideViewHolder holder, VideoSectionEntry itemData, int position) {
        try {
            holder.setText(R.id.tv_title, itemData.getTitle());
            holder.setText(R.id.tv_time,"长期练习可提高身体柔韧性，缓解焦虑和抑郁");
            Glide.with(mContext).load(itemData.getDuration()).transform(new GlideRoundTransform(mContext)).into( holder.getImageView(R.id.iv_item_sjk_act_img));
        } catch (Exception e) {
            e.printStackTrace();
        }

//        Glide.with(mContext).load(itemData.getPic()).transform(new GlideRoundTransform(mContext)).into( holder.getImageView(R.id.iv_item_sjk_act_img));
//        holder.setText(R.id.tv_title, itemData.getTitle());
//        holder.setText(R.id.tv_time, itemData.getDescription());
    }

    @Override
    protected void delete(VideoSectionEntry audioDetailListBean) {
        if (EmptyUtils.isNotEmpty(audioDetailListBean)) {
            DbCore.getDaoSession().getVideoSectionEntryDao().delete(audioDetailListBean);
            FileUtil.delete(audioDetailListBean.getFilePath(context));
        }
    }
}
