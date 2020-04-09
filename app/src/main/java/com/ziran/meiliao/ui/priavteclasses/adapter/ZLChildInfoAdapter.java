package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.ui.bean.NewMediaAndTextBean;
import com.ziran.meiliao.utils.MyValueTempCache;
import com.ziran.meiliao.widget.PlayPauseViewOne;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.List;


/**
 * 内部的RecyclerView
 * 内容为：
 * imageView + textView
 * Created by gaoshiwei on 2017/9/19.
 */
public class ZLChildInfoAdapter extends RecyclerView.Adapter<ZLChildInfoAdapter.ViewHolder> {

    private Context context;
    private  List<NewMediaAndTextBean.DataBean.DirBean.PrevListBean> list; // List 集合（里面是image+text）
    private boolean isCurrentMusic;
    protected ItemClickListener mOnItemClickListener;
    private int lastClickPosition=-1;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    public interface ItemClickListener{
        void itemClick(int position,View  view);
    }
    /**
     * 构造函数
     * @param context
     * @param list
     */
    public ZLChildInfoAdapter(Context context, List<NewMediaAndTextBean.DataBean.DirBean.PrevListBean> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_new_zl_info, parent, false);
        return new ViewHolder(view);
    }
    public void singleChoose(int position){
        lastClickPosition = position;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        NewMediaAndTextBean.DataBean.DirBean.PrevListBean info = list.get(position);
            if(MyValueTempCache.getCurrentData()!=null&&info.getTargetId()== MyValueTempCache.getTagId()&&MyAPP.mServiceManager.isPlaying()==true){

                holder.ppv.setImageResource(R.mipmap.ic_sjk_live_video_stop);
            }else{
                holder.ppv.setImageResource(R.mipmap.ic_sjk_live_video_play);
            }
        holder.itemView.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.itemClick(position,view);

                    notifyItemChanged(position);//刷新当前点击item
                    notifyDataSetChanged();

                }
            }
        });
        holder.ivDetail.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.itemClick(position,view);
                }
            }
        });
        holder.ivMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.itemClick(position,view);
                }
            }
        });


//        holder.ppv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (mOnItemClickListener != null) {
//                    mOnItemClickListener.itemClick(position,view);
//
//                    notifyItemChanged(position);//刷新当前点击item
//                    singleChoose(position);
//                    new Handler().postDelayed(new Runnable(){
//
//                        public void run() {
//                            notifyDataSetChanged();
//                        }
//
//                    }, 600);
//
//                }
//
//            }
//        });

        holder.allLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.itemClick(position,view);

                    notifyItemChanged(position);//刷新当前点击item
                    singleChoose(position);
                    new Handler().postDelayed(new Runnable(){

                        public void run() {
                            notifyDataSetChanged();
                        }

                    }, 600);

                }

            }
        });
        holder.textInfo.setText(info.getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * static ViewHolder
     */
    static class ViewHolder extends RecyclerView.ViewHolder {

        PlayPauseViewOne ppv;
        TextView textInfo;
        ImageView ivDetail,ivMore;
        AutoRelativeLayout allLeft;

        public ViewHolder(View itemView) {
            super(itemView);
            allLeft = (AutoRelativeLayout) itemView.findViewById(R.id.all_left);
            ppv = (PlayPauseViewOne) itemView.findViewById(R.id.playPauseView);
            textInfo = (TextView) itemView.findViewById(R.id.tv_item_title);
            ivDetail = (ImageView) itemView.findViewById(R.id.iv_item_jump_detail);
            ivMore = (ImageView) itemView.findViewById(R.id.iv_item_more);

        }
    }


}
