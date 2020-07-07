package com.ziran.meiliao.ui.settings.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.ui.bean.IntegralDetailBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;




public class IntegralAdapter extends RecyclerView.Adapter<IntegralAdapter.ViewHolder> {

    private  Context mContext;
    private  List<IntegralDetailBean.DataBean.ScoreListBean> mData;
    protected OnItemClickListener mOnItemClickListener;
    private boolean isCurrentMusic;
    private int mPosition = -1;


        public IntegralAdapter(List<IntegralDetailBean.DataBean.ScoreListBean> scoreList, Context context) {
                    mData=scoreList;
                   mContext=context;

        }
public interface OnItemClickListener{
    void itemClick(int position);
}

    public void setItemClickListener(IntegralAdapter.OnItemClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    @Override
    public IntegralAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_integral_details, parent, false);
        // 实例化viewholder
        IntegralAdapter.ViewHolder viewHolder = new IntegralAdapter.ViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(IntegralAdapter.ViewHolder holder, final int position) {
        IntegralDetailBean.DataBean.ScoreListBean scoreListBean = mData.get(position);
        // 绑定数据
        holder.tvPoints.setText(scoreListBean.getScore()+"");
        if(scoreListBean.getScore()>0){
            holder.tvPoints.setTextColor(Color.RED);
        }else {
            holder.tvPoints.setTextColor(Color.GREEN);
        }
        holder.tvTime.setText(longToDate(scoreListBean.getTime()));
        holder.tvTitle.setText(scoreListBean.getTitle());
    }
    /**
     * @Description: long类型转换成日期
     *
     * @param lo 毫秒数
     * @return String yyyy-MM-dd HH:mm:ss
     */
    public static String longToDate(long lo){
        Date date = new Date(lo);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sd.format(date);
    }
    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

public static class ViewHolder extends RecyclerView.ViewHolder {

    TextView tvPoints,tvTime,tvTitle;

    public ViewHolder(View itemView) {
        super(itemView);
        tvPoints = itemView.findViewById(R.id.tv_points);
        tvTime = itemView.findViewById(R.id.tv_time);
        tvTitle = itemView.findViewById(R.id.tv_title);
    }
}
}
