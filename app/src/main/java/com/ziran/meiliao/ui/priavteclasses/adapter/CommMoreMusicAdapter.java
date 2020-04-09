package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.ziran.meiliao.ui.bean.AlbumMoreBean;
import com.ziran.meiliao.ui.decompressionmuseum.activity.AlbumDetailActivity;
import com.ziran.meiliao.widget.GlideRoundTransform;

import java.util.List;

/**
 * Created by Administrator on 2019/1/16.
 */

public class CommMoreMusicAdapter extends RecyclerView.Adapter<CommMoreMusicAdapter.ViewHolder> {


    private List<AlbumMoreBean.DataBean.AlbumListBean.DetailListBean> data ;
    private Context context;
    protected OnItemClickListener mOnItemClickListener;
    private boolean isCurrentMusic;
    private int mPosition = -1;

    public CommMoreMusicAdapter(List<AlbumMoreBean.DataBean.AlbumListBean.DetailListBean> data, Context context){
        this.data = data;
        this.context = context;
    }

    public void updateData(List<AlbumMoreBean.DataBean.AlbumListBean.DetailListBean> data , Context context) {
        this.data = data;
        this.context = context;
        notifyDataSetChanged();
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    @Override
    public CommMoreMusicAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_more_album_top, parent, false);
        // 实例化viewholder

        CommMoreMusicAdapter.ViewHolder viewHolder = new CommMoreMusicAdapter.ViewHolder(v);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(CommMoreMusicAdapter.ViewHolder holder, final int position) {
        AlbumMoreBean.DataBean.AlbumListBean.DetailListBean bean = data.get(position);
        Glide.with(context).load(bean.getPicture()).transform(new GlideRoundTransform(context)).into(holder.ivBg);
        holder.tvTitle.setText(bean.getAlbumName());
        holder.tvTeacher.setText(bean.getTeacherName());
        holder.tvbiao.setText(bean.getTeacherIntro());
        holder.tvContent.setText(bean.getSubHead());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlbumDetailActivity.startAction(context,data.get(position).getAlbumId()+"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0: data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle,tvContent,tvbiao,tvTeacher;
        ImageView ivBg;

        public ViewHolder(View itemView) {
            super(itemView);
            ivBg = itemView.findViewById(R.id.iv_item_sjk_act_img);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvContent = itemView.findViewById(R.id.tv_content);
            tvTeacher = itemView.findViewById(R.id.tv_teacher_name);
            tvbiao = itemView.findViewById(R.id.tv_gong);
        }
    }
}
