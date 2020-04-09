package com.ziran.meiliao.ui.NewDecompressionmuseum.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.ui.bean.PracticeCalendarBean;
import com.ziran.meiliao.widget.GlideRoundTransform;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;


/**
 * 内部的RecyclerView
 * 内容为：
 * imageView + textView
 * Created by gaoshiwei on 2017/9/19.
 */

public class PracticeCourseChildInfoAdapter extends RecyclerView.Adapter<PracticeCourseChildInfoAdapter.ViewHolder> {
    private int TYPE_NORMAL = 1000;
    private int TYPE_HEADER = 1001;
    private final String bookId;
    private  List<PracticeCalendarBean.DataBean.ExerciseListBean> mData;
    private Context context;
    private View VIEW_HEADER;
    private int mposition;

    public PracticeCourseChildInfoAdapter(List<PracticeCalendarBean.DataBean.ExerciseListBean> data, Context context, String bookId) {
        setData(data);
        this.context=context;
        this.bookId=bookId;

    }

    public void setData(List<PracticeCalendarBean.DataBean.ExerciseListBean> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override
    public PracticeCourseChildInfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jdx_course_select, parent, false);
        PracticeCourseChildInfoAdapter.ViewHolder viewHolder = new PracticeCourseChildInfoAdapter.ViewHolder(v);
        if(viewType == TYPE_HEADER){
            return new ViewHolder(VIEW_HEADER);
        }else {
            return viewHolder;
        }
    }
    public void setItemClickListener(PracticeCourseChildInfoAdapter.ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;

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
    @Override
    public int getItemViewType(int position) {
        if (isHeaderView(position)) {
            return TYPE_HEADER;
        } else {
            return TYPE_NORMAL;
        }

    }
    private boolean haveHeaderView() {
        return VIEW_HEADER != null;
    }


    private boolean isHeaderView(int position) {
        return haveHeaderView() && position == 0;
    }
    public interface ItemClickListener{
        void itemClick(int position, AutoFrameLayout v, int itemId, int typeId, int practiceStatus);
    }
    private ItemClickListener itemClickListener;

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        mposition=position;
        if (!isHeaderView(mposition) ) {
            if (haveHeaderView()) mposition--;
            final PracticeCalendarBean.DataBean.ExerciseListBean bean = mData.get(mposition);
            holder.tvTitle.setText(bean.getItemName());
            if (bean.getItemStatus() == 0) {
                holder.tvClock.setBackgroundResource(R.mipmap.jdx_lock);
            } else {
                holder.tvClock.setVisibility(View.INVISIBLE);
            }
            holder.main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        if (bean.getItemStatus() == 1) {
                            itemClickListener.itemClick(position-1, (AutoFrameLayout) v, bean.getItemId(),bean.getTypeId(),bean.getPracticeStatus());
                        } else {

                            ToastUitl.showShort("尚未解锁");
                        }
                    }
                }
            });
            Glide.with(context).load(bean.getPicture()).transform(new GlideRoundTransform(context)).into(holder.mIv);
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
        TextView tvClock;
        ImageView mIv;
        TextView tvTitle;
        AutoFrameLayout main;

        public ViewHolder(View itemView) {
            super(itemView);
            main = (AutoFrameLayout) itemView.findViewById(R.id.afl_main);
            mIv = (ImageView) itemView.findViewById(R.id.iv_xc);
            tvClock = (TextView) itemView.findViewById(R.id.tv_clock);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }


}
