package com.ziran.meiliao.ui.me.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.widget.GlideCircleTransform;

import java.util.List;

import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.model.UserInfo;

/**
 * Created by Administrator on 2019/1/16.
 */

public class BlackListAdapter extends RecyclerView.Adapter<BlackListAdapter.ViewHolder> {



    private List<UserInfo> data;
    private Context context;

    public BlackListAdapter(List<UserInfo> list, Context mContext) {
        this.data = list;
        this.context = mContext;
    }

    public interface OnItemClickListener{
        void itemClick(int position, int itemId);
    }
    protected OnItemClickListener mOnItemClickListener;

    public void setItemClickListener(OnItemClickListener itemClickListener){
        this.mOnItemClickListener=itemClickListener;
    }

    public void updateData(List<UserInfo> list  , Context context) {
        this.data = list;
        this.context = context;
        notifyDataSetChanged();
    }


    @Override
    public BlackListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_blacklist, parent, false);

        BlackListAdapter.ViewHolder viewHolder = new BlackListAdapter.ViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(BlackListAdapter.ViewHolder holder, final int position) {
        UserInfo bean = data.get(position);
        if (bean != null) {
            bean.getAvatarBitmap(new GetAvatarBitmapCallback() {
                @Override
                public void gotResult(int status, String desc, Bitmap bitmap) {
                    if (status == 0) {
                        holder.ivHead.setImageBitmap(bitmap);
                    } else {
                        holder.ivHead.setImageResource(R.drawable.jmui_head_icon);
                    }
                }
            });
        } else {
            holder.ivHead.setImageResource(R.drawable.jmui_head_icon);
        }
        holder.tvName.setText(bean.getNickname());
        holder.tvQx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.itemClick(position,bean.getNoDisturb());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data == null ? 0: data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName,tvQx;
        ImageView ivHead;


        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvQx = itemView.findViewById(R.id.tv_qx);
            ivHead = itemView.findViewById(R.id.iv_head);

        }
    }
}
