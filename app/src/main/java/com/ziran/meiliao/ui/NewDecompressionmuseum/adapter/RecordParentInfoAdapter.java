package com.ziran.meiliao.ui.NewDecompressionmuseum.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.ui.bean.RecordParentInfoBean;

import java.util.List;


/**
 * 父层RecyclerView
 * 标题 + 内部的RecyclerView
 * Created by gaoshiwei on 2017/9/19.
 */

public class RecordParentInfoAdapter extends RecyclerView.Adapter<RecordParentInfoAdapter.ViewHolder> {

    private Context context;
    private List<RecordParentInfoBean> list;//父层列表 （里面是 text + 子List（子list是image+text））
    private int mPosition;
    private View VIEW_HEADER;

    public RecordParentInfoAdapter(Context context, List<RecordParentInfoBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_jdx_record_day_data, parent, false);
        return new ViewHolder(view);

    }
    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;

    }
    public interface ItemClickListener{
        void itemClick(int position,String id,String typeId);
    }
    private ItemClickListener itemClickListener;



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTitle.setText(list.get(position).getTitle());
        //把内层的RecyclerView 绑定在外层的onBindViewHolder
        // 先判断一下是不是已经设置了Adapter
        mPosition=position;
        if (holder.mRecyclerView.getAdapter() == null) {
            LinearLayoutManager layoutManager1=new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
            holder.mRecyclerView.setLayoutManager(layoutManager1);

            RecordChildInfoAdapter recordChildInfoAdapter = new RecordChildInfoAdapter(context, list.get(position).getMenuList());
            recordChildInfoAdapter.setItemClickListener(new RecordChildInfoAdapter.ItemClickListener() {
                @Override
                public void itemClick(int position, String id,String typeId) {
                    if(itemClickListener!=null){
                        itemClickListener.itemClick(position--,id,typeId);
                    }
                }
            });
            holder.mRecyclerView.setAdapter(recordChildInfoAdapter);
        } else {
            holder.mRecyclerView.getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * static ViewHolder
     */
    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTitle;//标题
        RecyclerView mRecyclerView; // 父层的 RecyclerView

        public ViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.menu_title);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.menu_info_recyclerview);
            RecyclerView.LayoutManager manager = new GridLayoutManager(itemView.getContext(), 4);
            // 需要注意的是GridLayoutManager要设置setAutoMeasureEnabled(true)成自适应高度
            manager.setAutoMeasureEnabled(true);
            mRecyclerView.setLayoutManager(manager);
        }
    }
}
