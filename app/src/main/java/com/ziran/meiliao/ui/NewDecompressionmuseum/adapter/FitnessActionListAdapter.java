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
import com.ziran.meiliao.ui.bean.FintessDetailBean;
import com.ziran.meiliao.ui.settings.activity.MemberRuleActivity;
import com.ziran.meiliao.widget.GlideRoundTransform;

import java.util.List;

public class FitnessActionListAdapter  extends RecyclerView.Adapter<FitnessActionListAdapter.MyHolder>{

    private final boolean isBuy;
    private final FintessDetailBean.DataBean mJsCourse;
    private Context context;
        private List<FintessDetailBean.DataBean.DetailsBean> list;

        public FitnessActionListAdapter(Context context, List<FintessDetailBean.DataBean.DetailsBean> list, boolean isBuy, FintessDetailBean.DataBean jsCourse) {
            this.isBuy=isBuy;
            this.context = context;
            this.list=list;
            this.mJsCourse=jsCourse;
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.item_fitness_action, parent, false);
            return new MyHolder(v);
        }


    @Override
        public void onBindViewHolder(MyHolder holder, final int position) {
            final FintessDetailBean.DataBean.DetailsBean bean = list.get(position);
                 Glide.with(context).load(bean.getThumbnailPic()).transform(new GlideRoundTransform(context,5)).into(holder.ivImg);
                 if(bean.getFree()==1){
                     holder.tvTry.setVisibility(View.VISIBLE);
                 }else {
                     holder.tvTry.setVisibility(View.GONE);
                 }
                 String min = (bean.getDuration()) % 3600 / 60+"";
                 if((bean.getDuration()) % 3600 / 60==0){
                     holder.tvTime.setText((bean.getDuration())%60+"秒");
                 }else {
                     holder.tvTime.setText(min+"分"+(bean.getDuration())%60+"秒");
                 }
                holder.tvAction.setText(bean.getName());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(bean.getFree()==1||isBuy){
                            MemberRuleActivity.startAction(context,bean.getShareUrl(), mJsCourse);
                    }else {
                        ToastUitl.showShort("购买后才能观看该课程");
                    }

                }
            });
        }

        @Override
        public int getItemCount() {
            return list == null ? 0 : list.size();
        }

        //隐藏
        public void setHideList(List<FintessDetailBean.DataBean.DetailsBean> newList) {
            this.list = newList;
            notifyDataSetChanged();
        }

        //展开
        public void setOpenList(List<FintessDetailBean.DataBean.DetailsBean> openList) {
            this.list = openList;
            notifyDataSetChanged();
        }

        //不需要隐藏/展开
        public void setRealList(List<FintessDetailBean.DataBean.DetailsBean> realList) {
            this.list = realList;
            notifyDataSetChanged();
        }

        //清除数据
        public void clearData() {
            if (list != null) {
                this.list.clear();
                notifyDataSetChanged();
            }
        }

        class MyHolder extends RecyclerView.ViewHolder {
            TextView tv,tvAction,tvTime,tvTry;
            ImageView ivImg;
            public MyHolder(View itemView) {
                super(itemView);
                ivImg = itemView.findViewById(R.id.iv_item_sjk_act_img);
                tvTry = itemView.findViewById(R.id.tv_try);
                tvTime = itemView.findViewById(R.id.tv_time);
                tvAction = itemView.findViewById(R.id.tv_action);
            }
    }
}
