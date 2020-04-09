package com.ziran.meiliao.ui.NewDecompressionmuseum.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.ui.bean.RecordChildInfoBean;

import java.util.List;


/**
 * 内部的RecyclerView
 * 内容为：
 * imageView + textView
 * Created by gaoshiwei on 2017/9/19.
 */

public class RecordChildInfoAdapter extends RecyclerView.Adapter<RecordChildInfoAdapter.ViewHolder> {

    private Context context;
    private List<RecordChildInfoBean> list; // List 集合（里面是image+text）
    protected ItemClickListener mOnItemClickListener;
    public void setItemClickListener(ItemClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    public interface ItemClickListener{
        void itemClick(int position, String id,String typeId);
    }
    /**
     * 构造函数
     * @param context
     * @param list
     */
    public RecordChildInfoAdapter(Context context, List<RecordChildInfoBean> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_jdx_record_day_info, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        RecordChildInfoBean info = list.get(position);
        holder.imageInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.itemClick(position,list.get(position).getId()+"",list.get(position).getType_id()+"");
                    notifyItemChanged(position);//刷新当前点击item
                    notifyDataSetChanged();
                }

            }
        });
        holder.textInfo.setText(info.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * static ViewHolder
     */
    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView imageInfo;
        TextView textInfo;

        public ViewHolder(View itemView) {
            super(itemView);
            imageInfo = (TextView) itemView.findViewById(R.id.image_info);
            textInfo = (TextView) itemView.findViewById(R.id.text_info);
        }
    }


}
