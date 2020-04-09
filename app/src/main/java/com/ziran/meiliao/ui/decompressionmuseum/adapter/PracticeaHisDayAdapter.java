package com.ziran.meiliao.ui.decompressionmuseum.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.ziran.meiliao.ui.bean.PunchHisBean;
import com.ziran.meiliao.widget.GlideRoundTransform;

import static cn.beecloud.BCMockPayActivity.dip2px;

/**
 *  on 2018/9/26.
 */

public class PracticeaHisDayAdapter extends RecyclerView.Adapter<PracticeaHisDayAdapter.ViewHolder> {

    private  Activity mActivity;
    private  TextView arlPunch;
    private  RotateAnimation rotateAnimation;
    private PunchHisBean.DataBean data;
    private Context context;
    protected OnItemClickListener mOnItemClickListener;
    protected TheFinishListener mFinishListener;
    private boolean isCurrentMusic;
    private int mPosition = -1;
    private AlphaAnimation alphaAnimation;

    public interface TheFinishListener<T>
    {
        void onFinish();
    }
    public PracticeaHisDayAdapter(PunchHisBean.DataBean data, Context context1, Activity activity) {
        this.data = data;
        this.context = context1;
        this.mActivity=activity;
    }

//    public void updateData(List<AlbumPracBean.DataBean.PunchBean> data, Context context) {
//        this.data = data;
//        this.context = context;
//        notifyDataSetChanged();
//    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    public void animationfinish(TheFinishListener mBaFinishListener) {
        mFinishListener = mBaFinishListener;
    }

    @Override
    public PracticeaHisDayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gift_week_view, parent, false);
        // 实例化viewholder

        PracticeaHisDayAdapter.ViewHolder viewHolder = new PracticeaHisDayAdapter.ViewHolder(v);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final PracticeaHisDayAdapter.ViewHolder holder, final int position) {
        // 绑定数据
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        int width = mActivity.getWindowManager().getDefaultDisplay().getWidth();
        layoutParams.width = (width - dip2px(context, 22)) / 9;//
        holder.itemView.setLayoutParams(layoutParams);
        PunchHisBean.DataBean.PunchDataBean punchDataBean = data.getPunchData().get(position);
        holder.tvDay.setText(punchDataBean.getDays()+"");
        Glide.with(context).load(punchDataBean.getPicture()).transform(new GlideRoundTransform(context)).into(holder.tvOutSide);

        holder.tvOutSide.setVisibility(View.VISIBLE);

    }

    /**
     * 晃动动画
     * <p>
     * 那么CycleInterpolator是干嘛用的？？
     * Api demo里有它的用法，是个摇头效果！
     *
     * @param counts 1秒钟晃动多少下
     * @return Animation
     */
    public  Animation shakeAnimation(int counts) {
         rotateAnimation = new RotateAnimation(0, 20, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setInterpolator(new CycleInterpolator(counts));
        rotateAnimation.setDuration(2000);
        return rotateAnimation;
    }
    @Override
    public int getItemCount() {
        return data == null ? 0 : data.getPunchData().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        LottieAnimationView lottie;
      public  TextView tvDay;
        ImageView tvOutSide,tvInside;

        public ViewHolder(View itemView) {
            super(itemView);

            tvOutSide = itemView.findViewById(R.id.iv_outside);
            tvInside = itemView.findViewById(R.id.iv_inside);
            tvDay = itemView.findViewById(R.id.tv_day);
            lottie = itemView.findViewById(R.id.lottie_likeanim);
        }
    }
}
