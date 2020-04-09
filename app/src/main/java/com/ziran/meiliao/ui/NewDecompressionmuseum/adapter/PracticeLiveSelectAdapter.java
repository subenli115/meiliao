package com.ziran.meiliao.ui.NewDecompressionmuseum.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.ziran.meiliao.widget.SwipeLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2018/8/16.
 */

public class PracticeLiveSelectAdapter extends RecyclerView.Adapter<PracticeLiveSelectAdapter.ViewHolder>{

    private  ArrayList<Integer> selectList;
    private List<String> mData;
    private Context context;
    private Set<SwipeLayout> mSet = new HashSet<>();
    protected OnItemClickListener mOnItemClickListener;
    private boolean isCurrentMusic;
    private int mPosition=-1;

    public PracticeLiveSelectAdapter(List<String> data, ArrayList<Integer> selectList, Context context) {
        this.mData = data;
        this.selectList=selectList;
        this.context=context;
    }

    public void updateData(List<String> data, ArrayList<Integer> selectList, Context context) {
        this.selectList=selectList;
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jdx_live_add, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    private SwipeLayout.OnSwipeStateChangedListener mOnSwipeStateChangedListener = new SwipeLayout.OnSwipeStateChangedListener() {
        @Override
        public void onOpen(SwipeLayout swipeLayout) {
            //关闭上一个条目
            for (SwipeLayout preSwipeLayout : mSet) {
                preSwipeLayout.close(true);
            }
            //保存当前打开的条目
            mSet.add(swipeLayout);
        }

        @Override
        public void onClose(SwipeLayout swipeLayout) {
            mSet.remove(swipeLayout);
        }
    };
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // 绑定数据
        // isCurrentMusic = MyAPP.mServiceManager.checkUrl(AES.get().decrypt(mData.get(position).getUrl()).trim());
        holder.mSwipeLayout.close(false);
        holder.mSwipeLayout.setOnSwipeStateChangedListener(mOnSwipeStateChangedListener);
        if(selectList.get(position)==0){
            holder.tv_delete.setBackgroundResource(R.mipmap.jdx_live_select);
        }else {
            holder.tv_delete.setBackgroundResource(R.mipmap.jdx_no_select);
        }
        holder.tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    itemClickListener.itemClick(position,(TextView) v);
                    mPosition=position;
                    mOnItemClickListener.onItemClick(null, v, mData.get(position), position);
                    notifyItemChanged(position);//刷新当前点击item
                    notifyDataSetChanged();
                }
            }
        });
        holder.mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDeleteListener != null) {
                    mDeleteListener.DeleteItem(position);
                }
            }
        });
    }
    //接口回调
    public interface DeleteListener {
        //删除条目
        void DeleteItem(int position);
    }

    private DeleteListener mDeleteListener;

    public void setDeleteListener(DeleteListener mDeleteListener) {
        this.mDeleteListener = mDeleteListener;
    }
    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
         SwipeLayout mSwipeLayout;
        TextView tv_delete;
        TextView mDelete;
        public ViewHolder(View itemView) {
            super(itemView);
            mDelete   = itemView.findViewById(R.id.delete);
            mSwipeLayout   = itemView.findViewById(R.id.sideslipview);
            tv_delete = itemView.findViewById(R.id.tv_delete);
        }
    }
}