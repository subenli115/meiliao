package com.ziran.meiliao.ui.settings.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.ui.bean.InvitationRecordBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


    public class InvitationRecordAdapter extends RecyclerView.Adapter<InvitationRecordAdapter.ViewHolder> {

        private final int mCoin;
        private  Context mContext;
    private  List<InvitationRecordBean.DataBean.FrdsListBean> mData;
    protected OnItemClickListener mOnItemClickListener;


        public InvitationRecordAdapter(List<InvitationRecordBean.DataBean.FrdsListBean> scoreList, Context context, int coin) {
                    mData=scoreList;
                   mContext=context;
                   mCoin=coin;

        }
    public interface OnItemClickListener{
        void itemClick(int position);
    }

    public void setItemClickListener(InvitationRecordAdapter.OnItemClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    @Override
    public InvitationRecordAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_invitation_record, parent, false);
        InvitationRecordAdapter.ViewHolder viewHolder = new InvitationRecordAdapter.ViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(InvitationRecordAdapter.ViewHolder holder, final int position) {
               InvitationRecordBean.DataBean.FrdsListBean frdsListBean = mData.get(position);
                holder.tvTime.setText(stampToDate(frdsListBean.getCreateTime() + ""));
                holder.tvMoney.setText("金币+"+mCoin+"");
                holder.tvPhone.setText(frdsListBean.getUserReadyPhone());
                holder.tvPoints.setText("积分+"+frdsListBean.getScore()+"");

    }

        public static String stampToDate(String s){
            String res;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
            //如果它本来就是long类型的,则不用写这一步
            long lt = new Long(s);
            Date date = new Date(lt);
            res = simpleDateFormat.format(date);
            return res;
        }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

    TextView tvPoints,tvMoney,tvPhone,tvTime;

    public ViewHolder(View itemView) {
        super(itemView);


        tvTime = itemView.findViewById(R.id.tv_time);
        tvMoney = itemView.findViewById(R.id.tv_money);
        tvPhone = itemView.findViewById(R.id.tv_phone);
        tvPoints= itemView.findViewById(R.id.tv_points);
    }
}
}
