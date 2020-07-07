package com.ziran.meiliao.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.ui.bean.NewHomeDataBean;
import com.ziran.meiliao.widget.GlideRoundTransform;

import java.util.List;

/**
 * 正念饮食 on 2018/9/26.
 */

public class HotAlbumAdapter extends RecyclerView.Adapter<HotAlbumAdapter.ViewHolder> {

    private List<NewHomeDataBean.DataBean.HotAlbumBean> data;
    private Context context;
    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }
    public interface ItemClickListener{
        void itemClick(int position, int itemId);
    }
    private ItemClickListener itemClickListener;
    private boolean isCurrentMusic;
    private int mPosition = -1;

    public HotAlbumAdapter(List<NewHomeDataBean.DataBean.HotAlbumBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    public void updateData(List<NewHomeDataBean.DataBean.HotAlbumBean> data, Context context) {
        this.data = data;
        this.context = context;
        notifyDataSetChanged();
    }

    @Override
    public HotAlbumAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_album_top_new, parent, false);
        // 实例化viewholder

        HotAlbumAdapter.ViewHolder viewHolder = new HotAlbumAdapter.ViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(HotAlbumAdapter.ViewHolder holder, final int position) {
        NewHomeDataBean.DataBean.HotAlbumBean hotAlbumBean = data.get(position);
        Glide.with(context).load(hotAlbumBean.getPicture()).transform(new GlideRoundTransform(context)).into(holder.ivBg);
        if(hotAlbumBean.getTagName()!=null&&hotAlbumBean.getTagName().equals("")){
            holder.tvTag.setVisibility(View.INVISIBLE);
        }else {
            holder.tvTag.setText(hotAlbumBean.getTagName());
        }
        holder.tvTitle.setText(hotAlbumBean.getAlbumName());
        holder.tvContent.setText(hotAlbumBean.getSubHead());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.itemClick(position,data.get(position).getAlbumId());
                    }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0: data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle,tvContent,tvTag;
        ImageView ivBg;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvContent = itemView.findViewById(R.id.tv_content);
            ivBg = itemView.findViewById(R.id.iv_item_sjk_act_img);
            tvTag = itemView.findViewById(R.id.iv_item_sjk_act_label);
        }
    }
}
