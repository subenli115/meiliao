package com.ziran.meiliao.ui.main.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.baserx.RxManager;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.ziran.meiliao.ui.bean.NewHomeDataBean;
import com.ziran.meiliao.ui.priavteclasses.activity.ZhuanLanDetailActivity;
import com.ziran.meiliao.widget.GlideRoundTransform;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.List;

/**
 * 专栏adapter on 2019/1/16.
 */

public class ZhuanLanAdapter extends RecyclerView.Adapter<ZhuanLanAdapter.ViewHolder> {


    private final Activity mActivity;
    private final RxManager mRxManager;
    private List<NewHomeDataBean.DataBean.SubscriptionBean> data;
    private Context context;
    protected OnItemClickListener mOnItemClickListener;

    public ZhuanLanAdapter(List<NewHomeDataBean.DataBean.SubscriptionBean> data, Context context, Activity mActivity, RxManager mRxManager){
        this.data = data;
        this.context = context;
        this.mRxManager=mRxManager;
        this.mActivity=mActivity;
    }

    public void updateData(List<NewHomeDataBean.DataBean.SubscriptionBean> data, Context context) {
        this.data = data;
        this.context = context;
        notifyDataSetChanged();
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    @Override
    public ZhuanLanAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_sjk_act_left_new_two, parent, false);
        // 实例化viewholder
        ZhuanLanAdapter.ViewHolder viewHolder = new ZhuanLanAdapter.ViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ZhuanLanAdapter.ViewHolder holder, final int position) {
        // 绑定数据
        final NewHomeDataBean.DataBean.SubscriptionBean subscriptionBean = data.get(position);

        if(data.size()!=1){
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(30,0,30,0);
            AutoRelativeLayout arl = (AutoRelativeLayout) (holder.itemView);
            arl.setLayoutParams(lp);
        }
        Glide.with(context).load(subscriptionBean.getPicture()).transform(new GlideRoundTransform(context)).into(holder.ivLeft);
        holder.tvTitle.setText(subscriptionBean.getTitle());
        holder.tvContent.setText(subscriptionBean.getDetail());
        holder.tvTeacher.setText(subscriptionBean.getTeacher());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                ZhuanLanDetailActivity.startAction(context,subscriptionBean.getSubscriptionId(),subscriptionBean.isBuy(),subscriptionBean.getHtmlLink(),mActivity,9,subscriptionBean.getSubscriptionNum());
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0: data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle,tvTeacher,tvContent;
        ImageView ivLeft;
        public ViewHolder(View itemView) {
            super(itemView);
            ivLeft=itemView.findViewById(R.id.iv_item_sjk_act_img);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvContent = itemView.findViewById(R.id.tv_content);
            tvTeacher=itemView.findViewById(R.id.tv_teacher_name);
        }
    }
}
