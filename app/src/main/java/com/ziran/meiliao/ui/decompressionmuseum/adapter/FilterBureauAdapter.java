package com.ziran.meiliao.ui.decompressionmuseum.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.GlideCircleTransfromUtil;
import com.ziran.meiliao.ui.bean.AlbumBgBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 专辑背景 on 2018/12/25.
 */
public class FilterBureauAdapter extends RecyclerView.Adapter<FilterBureauAdapter.FilterBureauHolder> {

    private final int mpo;
    private Context mContext;
    private List<AlbumBgBean.DataBean> mList;
    private Map<Integer,FilterBureauHolder> mHolderMap;

    public FilterBureauAdapter(Context context, @NonNull List<AlbumBgBean.DataBean> list, int mPosition) {
        mContext = context;
        mList = list;
        mHolderMap = new HashMap<>();
        mpo=mPosition;
    }
    @Override
    public FilterBureauHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_bottom_music_view,parent,false);
        FilterBureauHolder holder = new FilterBureauHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onBindViewHolder(final FilterBureauHolder holder, final int position) {
        Glide.with(mContext).load(mList.get(position).getPicture()).error(R.mipmap.ic_member_pic).transform(new GlideCircleTransfromUtil(mContext)).into(holder.ivVoice);
        holder.tvMusicName.setText(mList.get(position).getName());
        holder.ivVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.itemClick(position,mList.get(position).getUrl());
            }
        });
        // 保存子项的ViewHolder
        mHolderMap.put(position,holder);
        if(position==mpo){
            refreshBureau(mpo);
        }
    }

    /**
     * 刷新区局按钮点击状态
     */
    public void refreshBureau(int position){
        for (Map.Entry<Integer,FilterBureauHolder> entry: mHolderMap.entrySet()) {
            FilterBureauHolder holder = entry.getValue();
            // 点击项背景以及字体变色，其它项背景及字体设置为默认颜色
            if (entry.getKey() == position){
                holder.view_yuan.setImageResource(R.drawable.round_corners_layout_tran_music);
            } else {
                holder.view_yuan.setImageResource(R.drawable.round_corners_layout_tran_music_gray);
            }
        }
    }

    class FilterBureauHolder extends RecyclerView.ViewHolder{
        TextView tvMusicName;
        ImageView ivVoice;
        ImageView view_yuan;
        public FilterBureauHolder(View itemView) {
            super(itemView);
            view_yuan = (ImageView) itemView.findViewById(R.id.view_yuan);
            ivVoice = (ImageView) itemView.findViewById(R.id.iv_voice1);
            tvMusicName = (TextView) itemView.findViewById(R.id.tv_music_name1);
        }
    }
    public interface OnItemClickListener{
        void itemClick(int position, String url);
    }
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}