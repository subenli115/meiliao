package com.ziran.meiliao.ui.NewDecompressionmuseum.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.ziran.meiliao.ui.bean.SenseSaveBean;
import com.ziran.meiliao.widget.SwipeLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2018/8/16.
 */

public class PracticeLiveSaveAdapter extends RecyclerView.Adapter<PracticeLiveSaveAdapter.ViewHolder>{

    private  ArrayList<String> nameList;
    private List<SenseSaveBean> mData;
    private Context context;
    private Set<SwipeLayout> mSet = new HashSet<>();
    protected OnItemClickListener mOnItemClickListener;
    private int mPosition=-1;
    private List<Boolean> selectList;

    public PracticeLiveSaveAdapter(List<SenseSaveBean> data, ArrayList<Boolean> selectList, ArrayList<String> nameList, Context context) {
        this.mData = data;
        this.nameList=nameList;
        this.selectList=  selectList;
        this.context=context;
    }

    public void updateData(List<SenseSaveBean> data, ArrayList<Boolean> selectList, ArrayList<String> nameList, Context context) {
        this.mData = data;
        this.nameList=nameList;
        this.selectList=  selectList;
        this.context=context;
        notifyDataSetChanged();
    }

    public void updateData(List<SenseSaveBean> data,ArrayList<String> nameList, Context context) {
        selectList=null;
        this.mData = data;
        this.nameList=nameList;
        this.context=context;
        notifyDataSetChanged();
    }
    public interface ItemClickListener{
        void itemClick(int position, View v);
    }
    private ItemClickListener itemClickListener;
    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jdx_live_add, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // 绑定数据
        if(mData!=null&&mData.size()>0) {
            if (selectList == null) {
                holder.tv_delete.setBackgroundResource(R.mipmap.jdx_item_delete);
            }else {
                if (mData.get(position).getPracticeStatus() == 1) {
                    holder.tv_delete.setBackgroundResource(R.mipmap.jdx_live_select);
                } else {
                    holder.tv_delete.setBackgroundResource(R.mipmap.jdx_live_no_select);
                }
            }


        }
        holder.tv_detail.setText(nameList.get(position));
        holder.tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.itemClick(position,(TextView) v);
                    mPosition=position;
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
        TextView tv_delete;
        TextView tv_detail;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_detail   = itemView.findViewById(R.id.tv_detail);
            tv_delete = itemView.findViewById(R.id.tv_delete);
        }
    }
}