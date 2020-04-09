package com.ziran.meiliao.ui.priavteclasses.adapter;

/**
 * Created by Administrator on 2018/8/14.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.widget.GlideRoundTransform;

import java.util.List;

/**
 * 减压馆-消息
 * Created by Administrator on 2017/1/14.
 */
public class PptAdapter extends RecyclerView.Adapter<PptAdapter.ViewHolder> {
    private final String prefix;
    private List<String> mData;
    private Context context;

    public PptAdapter(List<String> data, Context context, String prefix) {
        this.mData = data;
        this.context=context;
        this.prefix=prefix;
    }

    public void updateData(List<String> data, Context context) {
        this.mData = data;
        this.context=context;
        notifyDataSetChanged();
    }

    @Override
    public PptAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sjk_subscribe_audio_ppt, parent, false);
        // 实例化viewholder
        PptAdapter.ViewHolder viewHolder = new PptAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(mData.get(position)).transform(new GlideRoundTransform(context)).into(holder.mIv);

    }



    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mIv;

        public ViewHolder(View itemView) {
            super(itemView);
            mIv = (ImageView) itemView.findViewById(R.id.iv_item_subscribe_pic);
        }
    }
}