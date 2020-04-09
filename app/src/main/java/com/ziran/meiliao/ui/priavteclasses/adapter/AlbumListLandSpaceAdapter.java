package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.ui.bean.AlbumTwoMoreBean;
import com.ziran.meiliao.ui.decompressionmuseum.activity.AlbumDetailActivity;
import com.ziran.meiliao.widget.GlideRoundTransform;
import com.zhy.autolayout.AutoFrameLayout;

import java.util.List;

/**
 * Created by Administrator on 2018/12/25.
 */
public class AlbumListLandSpaceAdapter extends RecyclerView.Adapter<AlbumListLandSpaceAdapter.AlbumHolder> {

    private Context mContext;
    private List<AlbumTwoMoreBean.DataBean.DetailListBean> mList;

    public AlbumListLandSpaceAdapter(Context context, @NonNull List<AlbumTwoMoreBean.DataBean.DetailListBean> list) {
        mContext = context;
        mList = list;
    }
    @Override
    public AlbumHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_more_album_top,parent,false);
        AlbumHolder holder = new AlbumHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onBindViewHolder(final AlbumHolder holder, final int position) {
        AutoFrameLayout.LayoutParams lp = new AutoFrameLayout.LayoutParams(AutoFrameLayout.LayoutParams.WRAP_CONTENT, AutoFrameLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(32, 0, 0, 69);
        holder.itemView.setLayoutParams(lp);
        holder.tvTitle.setText(mList.get(position).getAlbumName());
        Glide.with(mContext).load(mList.get(position).getPicture()).transform(new GlideRoundTransform(mContext)).into(holder.ivBg);
        holder.tvContent.setText(mList.get(position).getSubHead());
        holder.tvG.setText(mList.get(position).getTeacherIntro());
        holder.tvTN.setText(mList.get(position).getTeacherName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlbumDetailActivity.startAction(mContext,mList.get(position).getAlbumId()+"");
            }
        });
    }

    /**
     * 刷新区局按钮点击状态
     */

    class AlbumHolder extends RecyclerView.ViewHolder{
        TextView tvTitle,tvContent,tvTN,tvG;
        ImageView ivBg;
        public AlbumHolder(View itemView) {
            super(itemView);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);
            ivBg = (ImageView) itemView.findViewById(R.id.iv_item_sjk_act_img);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvTN = (TextView) itemView.findViewById(R.id.tv_teacher_name);
            tvG = (TextView) itemView.findViewById(R.id.tv_gong);
        }
    }
    public interface OnItemClickListener{
        void itemClick(int position, String url);
    }
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}