package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.ziran.meiliao.ui.bean.CoursePaySuccessResult;
import com.ziran.meiliao.widget.GlideRoundTransform;

import java.util.ArrayList;
import java.util.List;

public class CourseSuccessAdapter extends RecyclerView.Adapter<CourseSuccessAdapter.ViewHolder>{

    private List<Object> data ;
    private Context context;
    protected OnItemClickListener mOnItemClickListener;
    private int mposition;
    private int isSelect;
    private CoursePaySuccessResult.DataBean.AlbumBean bean;
    private int id;
    private boolean isAlbum;
    private String name;
    private CoursePaySuccessResult.DataBean.CourseBean bean1;

    public CourseSuccessAdapter(Context context, ArrayList<Object> data){
        this.data = data;
        this.context = context;
    }

    public interface ItemClickListener{
        void itemClick(int isSelect, int id, boolean b, String s);
    }
    private ItemClickListener itemClickListener;
    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }
    public void updateData(List<Object> data , Context context) {
        this.data = data;
        this.context = context;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_sjk_act_left_new_more, parent, false);
        // 实例化viewholder

        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        mposition=position;
        final Object o = data.get(mposition);
        if(o instanceof CoursePaySuccessResult.DataBean.AlbumBean){
             bean  =(CoursePaySuccessResult.DataBean.AlbumBean)o;
            Glide.with(context).load(bean.getPicture()).transform(new GlideRoundTransform(context)).into(holder.ivImg);
            holder.tvTitle.setText(bean.getTitle());
            holder.tvTeacher.setText("导师:"+bean.getName());
            holder.tvPrice.setText(bean.getPrice()+"");
             id = bean.getId();
        }else if(o instanceof CoursePaySuccessResult.DataBean.CourseBean){
              bean1  =(CoursePaySuccessResult.DataBean.CourseBean)o;
            Glide.with(context).load(bean1.getPicture()).transform(new GlideRoundTransform(context)).into(holder.ivImg);
            holder.tvTitle.setText(bean1.getName());
            holder.tvTeacher.setText("导师:"+bean1.getName());
            holder.tvAddress.setText(bean1.getAddress());
             name = bean1.getName();
            holder.tvTime.setText(bean1.getTime());
            holder.tvRemain.setText("仅限"+bean1.getRemain()+"位");
            holder.tvPrice.setText(bean1.getPrice()+"");
        }
        holder.itemView.setOnClickListener ( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Object o = data.get(position);
                if(o instanceof CoursePaySuccessResult.DataBean.AlbumBean){
                    bean  =(CoursePaySuccessResult.DataBean.AlbumBean)o;
                    itemClickListener.itemClick(isSelect,bean.getId(),true,name);
                }else {
                    bean1  =(CoursePaySuccessResult.DataBean.CourseBean)o;
                    itemClickListener.itemClick(isSelect,bean1.getId(),false,"");
                }
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
        TextView tvTitle,tvTeacher,tvAddress,tvTime,tvRemain,tvPrice;
        ImageView ivImg;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle= itemView.findViewById(R.id.tv_title);
            tvTeacher = itemView.findViewById(R.id.tv_teacher_name);
            tvAddress = itemView.findViewById(R.id.tv_address);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvRemain = itemView.findViewById(R.id.tv_remain);
            tvPrice = itemView.findViewById(R.id.tv_price);
            ivImg = itemView.findViewById(R.id.iv_item_sjk_act_img);

        }
    }
}