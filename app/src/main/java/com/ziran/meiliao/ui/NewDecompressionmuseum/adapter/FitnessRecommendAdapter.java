package com.ziran.meiliao.ui.NewDecompressionmuseum.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.ui.NewDecompressionmuseum.activity.FitnessExeActivity;
import com.ziran.meiliao.ui.bean.FintessDetailBean;
import com.ziran.meiliao.widget.GlideRoundTransform;

import java.util.List;

/**
 * Created by Administrator on 2019/1/16.
 */

public class FitnessRecommendAdapter extends RecyclerView.Adapter<FitnessRecommendAdapter.ViewHolder> {


    private final FitnessExeActivity activity;
    private List<FintessDetailBean.DataBean.JsCoursesRecommendBean> data ;
    private Context context;
    public interface OnItemClickListener{
        void itemClick(int position, int itemId);
    }
    protected OnItemClickListener mOnItemClickListener;

    public void setItemClickListener(OnItemClickListener itemClickListener){
        this.mOnItemClickListener=itemClickListener;
    }

    public FitnessRecommendAdapter(List<FintessDetailBean.DataBean.JsCoursesRecommendBean> data , Context context,FitnessExeActivity activity){
        this.data = data;
        this.context = context;
        this.activity=activity;
    }

    public void updateData(List<FintessDetailBean.DataBean.JsCoursesRecommendBean> data  , Context context) {
        this.data = data;
        this.context = context;
        notifyDataSetChanged();
    }


    @Override
    public FitnessRecommendAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view



        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fitness_recommend, parent, false);

        FitnessRecommendAdapter.ViewHolder viewHolder = new FitnessRecommendAdapter.ViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(FitnessRecommendAdapter.ViewHolder holder, final int position) {
        final FintessDetailBean.DataBean.JsCoursesRecommendBean bean = data.get(position);
        Glide.with(context).load(bean.getRecPic()).transform(new GlideRoundTransform(context,5)).into(holder.ivBg);
        holder.tvTime.setText(bean.getDuration()/60+"分钟");
        holder.tvTitle.setText(bean.getName());
        holder.tvLevel.setText("活动量"+bean.getDifficult());
        holder.tvPeopleCount.setText(bean.getPracticeCount()+"人参加");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.itemClick(position,bean.getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0: data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle,tvTime,tvLevel,tvPeopleCount;
        ImageView ivBg;


        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvPeopleCount = itemView.findViewById(R.id.tv_peoplecount);
            ivBg = itemView.findViewById(R.id.iv_item_sjk_act_img);
            tvLevel = itemView.findViewById(R.id.tv_level);

        }
    }
}
