package com.ziran.meiliao.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.ui.NewDecompressionmuseum.activity.MbsrWorkbookMainActivity;
import com.ziran.meiliao.ui.NewDecompressionmuseum.activity.PracticeTaskActivity;
import com.ziran.meiliao.ui.bean.NewHomeDataBean;
import com.ziran.meiliao.widget.GlideRoundTransform;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.List;

/**
 * Created by Administrator on 2019/1/16.
 */

public class BootCampAdapter extends RecyclerView.Adapter<BootCampAdapter.ViewHolder> {


    private List<NewHomeDataBean.DataBean.PracticeBean> data ;
    private Context context;
    public interface ItemClickListener{
        void itemClick(int position, int itemId);
    }

    public BootCampAdapter(List<NewHomeDataBean.DataBean.PracticeBean> data, Context context){
        this.data = data;
        this.context = context;
    }

    public void updateData(List<NewHomeDataBean.DataBean.PracticeBean> data , Context context) {
        this.data = data;
        this.context = context;
        notifyDataSetChanged();
    }


    @Override
    public BootCampAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view



            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_sjk_act_long_new, parent, false);

        BootCampAdapter.ViewHolder viewHolder = new BootCampAdapter.ViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(BootCampAdapter.ViewHolder holder, final int position) {
        final NewHomeDataBean.DataBean.PracticeBean practiceBean = data.get(position);
        Glide.with(context).load(practiceBean.getPicture()).transform(new GlideRoundTransform(context)).into(holder.ivBg);
        holder.tvTitle.setText(practiceBean.getName());
        holder.tvTime.setText(practiceBean.getStartTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(practiceBean.getName().equals("MBSR八周练习"))
                    if(practiceBean.getStatus()==0){
                        MbsrWorkbookMainActivity.startAction(context,practiceBean.getBooksId()+"");
                    }else {
                        PracticeTaskActivity.startAction(context,practiceBean.getBooksId()+"",0+"");
                    }
            }
        });
        Log.e("","");
    }

    @Override
    public int getItemCount() {
        return data == null ? 0: data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle,tvTime;
        ImageView ivBg;

       AutoRelativeLayout arlTime;

        public ViewHolder(View itemView) {
            super(itemView);
                    tvTitle = itemView.findViewById(R.id.tv_title);
            tvTime = itemView.findViewById(R.id.tv_time);
            arlTime = itemView.findViewById(R.id.arl_times);
            ivBg = itemView.findViewById(R.id.iv_item_sjk_act_img);
        }
    }
}
