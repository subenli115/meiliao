package com.ziran.meiliao.ui.settings.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.ui.bean.MemberExchangeBean;
import com.ziran.meiliao.widget.GlideRoundTransform;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;

/**
 * 正念饮食 on 2018/9/26.
 */

public class PointsExchangeAdapter extends RecyclerView.Adapter<PointsExchangeAdapter.ViewHolder> {

    private  List<MemberExchangeBean.DataBean.GoodsListBean> mData;
    private Context context;
    protected OnItemClickListener mOnItemClickListener;
    private View VIEW_HEADER;
    private int TYPE_NORMAL = 1000;
    private int TYPE_HEADER = 1001;
    private int mposition;

    private boolean haveHeaderView() {
        return VIEW_HEADER != null;
    }
    public void addHeaderView(View headerView) {
        if (haveHeaderView()) {
            throw new IllegalStateException("hearview has already exists!");
        } else {
            //避免出现宽度自适应
            AutoLinearLayout.LayoutParams params = new AutoLinearLayout.LayoutParams(AutoLinearLayout.LayoutParams.WRAP_CONTENT, AutoLinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(30,30,0,0);
            headerView.setLayoutParams(params);

            VIEW_HEADER = headerView;
            notifyItemInserted(0);
        }
    }

    public PointsExchangeAdapter(List<MemberExchangeBean.DataBean.GoodsListBean> mGoodsList, Context mContext) {
        context= mContext;
        mData=mGoodsList;
    }
    public void update(List<MemberExchangeBean.DataBean.GoodsListBean> mGoodsList, Context mContext){
        context= mContext;
        mData=mGoodsList;
        notifyDataSetChanged();

    }
    private boolean isHeaderView(int position) {
        return haveHeaderView() && position == 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderView(position)) {
            return TYPE_HEADER;
        } else {
            return TYPE_NORMAL;
        }

    }

    public interface OnItemClickListener{
        void itemClick(int goodsId, int id, int type, int position, String title);
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    @Override
    public PointsExchangeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_member_exchange, parent, false);
        // 实例化viewholder

        PointsExchangeAdapter.ViewHolder viewHolder = new PointsExchangeAdapter.ViewHolder(v);
        if(viewType == TYPE_HEADER){
            return new PointsExchangeAdapter.ViewHolder(VIEW_HEADER);
        }else {
            return viewHolder;
        }
    }


    @Override
    public void onBindViewHolder(PointsExchangeAdapter.ViewHolder holder, final int position) {
        // 绑定数据
        mposition=position;
        if (!isHeaderView(mposition) ) {
            if (haveHeaderView()) mposition--;
            final MemberExchangeBean.DataBean.GoodsListBean bean = mData.get(mposition);
            Glide.with(context).load(bean.getPicture()).transform(new GlideRoundTransform(context, 5)).into(holder.ivBg);
            holder.tvPoints.setText("需" + bean.getScore() + "积分");
            holder.tvTitle.setText(bean.getTitle());
            holder.tvPrice.setText(bean.getPrice() + "");
            if (bean.isIsCheckout()) {
                holder.tvExchange.setText("已兑换");
                holder.tvExchange.setEnabled(false);
            }
            holder.tvExchange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.itemClick(bean.getGoodsId(), bean.getType(), mposition,bean.getScore(),bean.getTitle());
                    }

                }
            });
        }
}

    @Override
    public int getItemCount() {
        int count = mData == null ? 0 : mData.size();

        if (VIEW_HEADER != null) {
            count++;
        }
        return count;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle,tvPrice,tvPoints,tvExchange;
        ImageView ivBg;

        public ViewHolder(View itemView) {



            super(itemView);
            ivBg= itemView.findViewById(R.id.iv_bg);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvPoints = itemView.findViewById(R.id.tv_points);
            tvExchange = itemView.findViewById(R.id.tv_exchange);
        }
    }
}
