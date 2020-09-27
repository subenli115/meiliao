package com.ziran.meiliao.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.ui.bean.NewHomeDataBean;
import com.ziran.meiliao.ui.priavteclasses.activity.NewGongZuoFangActivity;
import com.ziran.meiliao.widget.GlideRoundTransform;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.List;

/**
 * Created by Administrator on 2019/1/16.
 */

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {


    private List<NewHomeDataBean.DataBean.ActivityBean> data;
    private Context context;
    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }
    public interface ItemClickListener{
        void itemClick(int position, int itemId);
    }
    private ItemClickListener itemClickListener;

    public CourseAdapter(List<NewHomeDataBean.DataBean.ActivityBean> data, Context context){
        this.data = data;
        this.context = context;
    }

    public void updateData(List<NewHomeDataBean.DataBean.ActivityBean> data, Context context) {
        this.data = data;
        this.context = context;
        notifyDataSetChanged();
    }


    @Override
    public CourseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_sjk_act_left_new, parent, false);
        // 实例化viewholder

        CourseAdapter.ViewHolder viewHolder = new CourseAdapter.ViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(CourseAdapter.ViewHolder holder, final int position) {

        if(data.size()!=1){
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(20,0,30,0);
            AutoRelativeLayout arl = (AutoRelativeLayout) (holder.itemView);
            arl.setLayoutParams(lp);
        }
        final NewHomeDataBean.DataBean.ActivityBean activityBean = data.get(position);
        Glide.with(context).load(activityBean.getPicture()).into(holder.ivCourse);
        if(activityBean.getTagName()!=null&&activityBean.getTagName().equals("")){
            holder.tvTag.setVisibility(View.INVISIBLE);
        }else {
            holder.tvTag.setText(activityBean.getTagName());
        }
        holder.tvTime.setText(activityBean.getTime());
        holder.tvAddress.setText(activityBean.getAddress());
        holder.tvTeacher.setText(activityBean.getTeacher());
        holder.tvTitle.setText(activityBean.getActivityName());
        holder.tvPrice.setText(activityBean.getPrice()+"");
        holder.tvRemain.setText("仅限"+activityBean.getRemain()+"位");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewGongZuoFangActivity.startAction(context, activityBean.getActivityId()+"",activityBean.getActivityName());
            }
        });

    }

    @Override
    public int getItemCount() {
        return data == null ? 0: data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTag,tvTitle,tvTime,tvAddress,tvTeacher,tvPrice,tvRemain;
        ImageView ivCourse;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTag = itemView.findViewById(R.id.iv_item_sjk_act_label);
            tvTitle = itemView.findViewById(R.id.tv_title);
            ivCourse = itemView.findViewById(R.id.iv_item_sjk_act_img);
            tvTime= itemView.findViewById(R.id.tv_time);
            tvAddress= itemView.findViewById(R.id.tv_address);
            tvTeacher= itemView.findViewById(R.id.tv_teacher_name);
            tvPrice= itemView.findViewById(R.id.tv_price);
            tvRemain= itemView.findViewById(R.id.tv_remain);
        }
    }
}
