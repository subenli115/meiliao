package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.ziran.meiliao.ui.bean.CourseJoinDeatilBean;

import java.util.List;

public class CouresSelectAdapter extends RecyclerView.Adapter<CouresSelectAdapter.ViewHolder>{

    private List<CourseJoinDeatilBean.DataBean.DetailListBean> data ;
    private Context context;
    protected OnItemClickListener mOnItemClickListener;
    private int mposition;
    private int isSelect;

    public CouresSelectAdapter(Context context, List<CourseJoinDeatilBean.DataBean.DetailListBean> data){
        this.data = data;
        this.context = context;
    }

    private View VIEW_HEADER;
    private int TYPE_NORMAL = 1000;
    public interface ItemClickListener{
        void itemClick(int isSelect, int id);
    }
    private ItemClickListener itemClickListener;
    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }
    public void updateData(List<CourseJoinDeatilBean.DataBean.DetailListBean> data , Context context) {
        this.data = data;
        this.context = context;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ac_city_time, parent, false);
        // 实例化viewholder

        ViewHolder viewHolder = new ViewHolder(v);
            return viewHolder;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        mposition=position;

        holder.tvCity.setText(data.get(position).getName());
        if(isSelect!=position){

            holder.itemView.setBackgroundResource(R.color.white);
        }else {
            holder.itemView.setBackgroundResource(R.drawable.normal_bg_green_tran);
        }
        holder.itemView.setOnClickListener ( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSelect=position;
                itemClickListener.itemClick(isSelect,data.get(position).getId());
                 notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        int count = data == null ? 0 : data.size();

        return count;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCity;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCity = itemView.findViewById(R.id.tv_activity_city);
        }
    }
}