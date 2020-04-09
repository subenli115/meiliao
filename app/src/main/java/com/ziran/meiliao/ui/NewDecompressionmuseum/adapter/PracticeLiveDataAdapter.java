package com.ziran.meiliao.ui.NewDecompressionmuseum.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.ziran.meiliao.ui.bean.PracticeFiveDetailBean;

import java.util.List;

/**
 * Created by Administrator on 2018/8/16.
 */

public class PracticeLiveDataAdapter extends RecyclerView.Adapter<PracticeLiveDataAdapter.ViewHolder>{

    private List<PracticeFiveDetailBean.DataBean.SenseChooseListBean> mData;
    private Context context;
    protected OnItemClickListener mOnItemClickListener;

    public PracticeLiveDataAdapter(List<PracticeFiveDetailBean.DataBean.SenseChooseListBean> data, Context context) {
        this.mData = data;
        this.context=context;
    }

    public void updateData(List<PracticeFiveDetailBean.DataBean.SenseChooseListBean> data, Context context) {
        this.mData = data;
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jdx_live, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // 绑定数据
        // isCurrentMusic = MyAPP.mServiceManager.checkUrl(AES.get().decrypt(mData.get(position).getUrl()).trim());
        holder.tv_detail.setText(mData.get(position).getSenseDetail());

        holder.tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.itemClick(position,v);
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

        TextView tv_add;
        TextView tv_detail;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_detail = itemView.findViewById(R.id.tv_detail);
            tv_add = itemView.findViewById(R.id.tv_add);
        }
    }
}