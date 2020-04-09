package com.ziran.meiliao.ui.NewDecompressionmuseum.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.OnItemClickListener;

import java.util.List;

/**
 * Created by Administrator on 2018/8/16.
 */

public class ChooseTeacharAdapter extends RecyclerView.Adapter<ChooseTeacharAdapter.ViewHolder>{

    private List<Integer> mData;
    private Context context;
    protected OnItemClickListener mOnItemClickListener;
    private boolean isCurrentMusic;
    private int mPosition=-1;

    public ChooseTeacharAdapter(List<Integer> data, Context context) {
        this.mData = data;
        this.context=context;
    }

    public void updateData(List<Integer> data, Context context) {
        this.mData = data;
        this.context=context;
        notifyDataSetChanged();
    }
    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jdx_choose_mentor, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // 绑定数据
//        isCurrentMusic = MyAPP.mServiceManager.checkUrl(AES.get().decrypt(mData.get(position).getUrl()).trim());

        if(isCurrentMusic&& MyAPP.mServiceManager.isPlaying()){
            holder.tv_play.setBackgroundResource(R.mipmap.player_stop);
        }else{
            holder.tv_play.setBackgroundResource(R.mipmap.player_play);
        }
        if(mPosition==position){
            holder.tv_choose.setBackgroundResource(R.mipmap.jdx_choose_teacher);
        }else{
            holder.tv_choose.setBackgroundResource(R.drawable.bg_jdx_circle);
        }
        holder.tv_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mPosition=position;
                    mOnItemClickListener.onItemClick(null, v, mData.get(position), position);
                    notifyItemChanged(position);//刷新当前点击item
                    notifyDataSetChanged();
                }
            }
        });
        holder.tv_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(null, v, mData.get(position), position);
                    notifyItemChanged(position);//刷新当前点击item
                    notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

            TextView tv_play;
            TextView  tv_choose;
            TextView tv_mentor_name;
            TextView tv_kc;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_kc = itemView.findViewById(R.id.tv_kc);
            tv_choose= itemView.findViewById(R.id.tv_choose);
            tv_mentor_name= itemView.findViewById(R.id.tv_mentor_name);
            tv_play= itemView.findViewById(R.id.tv_play);
        }
    }
}