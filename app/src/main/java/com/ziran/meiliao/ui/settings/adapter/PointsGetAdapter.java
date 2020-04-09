package com.ziran.meiliao.ui.settings.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.ui.bean.PracticeThreeDetailCheckBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 正念饮食 on 2018/9/26.
 */

public class PointsGetAdapter extends RecyclerView.Adapter<PointsGetAdapter.ViewHolder> {

    private final String[] mTitles;
    private final String[] mDetails;
    private final String[] mButtons;
    private final int[] mMipList;
    private  ArrayList<Boolean> mBooleans;

    private List<PracticeThreeDetailCheckBean.DataBean.DetailListBean> data;
    private Context context;
    protected OnItemClickListener mOnItemClickListener;
    private boolean isCurrentMusic;
    private int mPosition = -1;
    private boolean mInfoComplete;


    public PointsGetAdapter(String[] titles, String[] details, int[] mipList, String[] buttons, Context context, ArrayList<Boolean> booleans) {
                mTitles=titles;
                mDetails=details;
                mButtons=buttons;
                mMipList=mipList;
        mBooleans = booleans;
        this.context = context;
    }
    public interface OnItemClickListener{
        void itemClick(int position);
    }
    public void updateData(ArrayList<Boolean> booleans, Context context) {
        this.context = context;
        mBooleans = booleans;
        notifyItemChanged(mTitles.length-1);
        notifyDataSetChanged();
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    @Override
    public PointsGetAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_member_earn, parent, false);
        // 实例化viewholder
        PointsGetAdapter.ViewHolder viewHolder = new PointsGetAdapter.ViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(PointsGetAdapter.ViewHolder holder, final int position) {
        // 绑定数据
           holder.tvContent.setText(mTitles[position]);
           holder.tvDetail.setText(mDetails[position]);
           holder.ivMember.setBackgroundResource(mMipList[position]);
           holder.tvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.itemClick(position);
                    notifyItemChanged(position);//刷新当前点击item
                    notifyDataSetChanged();
                }

            }
        });
           if(mBooleans.get(position)){
                   holder.tvButton.setBackgroundResource(R.drawable.normal_bg_green_tran_bg);
                   holder.tvButton.setText("已完成");
                   holder.tvButton.setTextColor(Color.parseColor("#58BEA8"));
                   holder.tvButton.setEnabled(false);
           }else{
               holder.tvButton.setText(mButtons[position]);
           }
}

    @Override
    public int getItemCount() {
        return mTitles == null ? 0 : mTitles.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvDetail;
        ImageView ivMember;
        TextView tvContent;
        TextView tvButton;

        public ViewHolder(View itemView) {
            super(itemView);
            tvButton= itemView.findViewById(R.id.tv_button);
            tvContent = itemView.findViewById(R.id.tv_title);
            ivMember = itemView.findViewById(R.id.iv_member1);
            tvDetail = itemView.findViewById(R.id.tv_detail);
        }
    }
}
