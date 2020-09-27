package com.ziran.meiliao.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.im.activity.PairActivity;
import com.ziran.meiliao.ui.bean.HomeMenuBean;
import com.ziran.meiliao.ui.priavteclasses.activity.RecommendCommonActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 * author：pz
 * 时间：2017/4/19 :22:22
 */

public class PagerGridAdapter extends RecyclerView.Adapter<PagerGridAdapter.MyViewHolder> {


    private final List<HomeMenuBean.DataBean> mDatas;
    private final Context mContext;

    public PagerGridAdapter(List<HomeMenuBean.DataBean> list, Context context){
        mDatas=list;
       mContext=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_page_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        HomeMenuBean.DataBean recordsBean = mDatas.get(position);
        holder.tv_title.setText(recordsBean.getMenuName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if(recordsBean.getMenuName().equals("颜值配对")){
                  PairActivity.startAction(mContext);
                }else if(recordsBean.getMenuName().equals("真人匹配")) {
                  RecommendCommonActivity.startAction(mContext,1);
              }else if(recordsBean.getMenuName().equals("附近的人")){
                  RecommendCommonActivity.startAction(mContext,2);
              }
            }
        });
        Glide.with(mContext).load(recordsBean.getMenuUrl()).into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        ImageView icon;
        private MyViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.text);
            icon = (ImageView) itemView.findViewById(R.id.icon);
        }
    }
}
