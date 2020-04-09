package com.ziran.meiliao.ui.decompressionmuseum.adapter;

/**
 * Created by Administrator on 2018/12/28.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.ziran.meiliao.ui.bean.PracticeThreeDetailCheckBean;

import java.util.List;

/**
 * 正念饮食 on 2018/9/26.
 */

public class PunchGiftAdapter extends RecyclerView.Adapter<PunchGiftAdapter.ViewHolder> {

    private List<PracticeThreeDetailCheckBean.DataBean.DetailListBean> data;
    private Context context;
    protected OnItemClickListener mOnItemClickListener;
    private boolean isCurrentMusic;
    private int mPosition = -1;

    public PunchGiftAdapter(List<PracticeThreeDetailCheckBean.DataBean.DetailListBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    public void updateData(List<PracticeThreeDetailCheckBean.DataBean.DetailListBean> data, Context context) {
        this.data = data;
        this.context = context;
        notifyDataSetChanged();
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gift_week_view, parent, false);
        // 实例化viewholder

        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // 绑定数据
//        isCurrentMusic = MyAPP.mServiceManager.checkUrl(AES.get().decrypt(mData.get(position).getUrl()).trim());
//        holder.tvContent.setText();
        PracticeThreeDetailCheckBean.DataBean.DetailListBean detailListBean = data.get(position);
        holder.tvDay.setText(position+1);

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivOutside;
        TextView tvDay;

        public ViewHolder(View itemView) {
            super(itemView);
            ivOutside = itemView.findViewById(R.id.iv_outside);
            tvDay = itemView.findViewById(R.id.tv_day);
        }
    }
}
