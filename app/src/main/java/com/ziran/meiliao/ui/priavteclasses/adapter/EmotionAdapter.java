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
import com.ziran.meiliao.common.commonutils.GlideCircleTransfromUtil;
import com.ziran.meiliao.ui.bean.AlbumMoreBean;
import com.ziran.meiliao.ui.priavteclasses.activity.EmotionPlayerActicity;

import java.util.ArrayList;
import java.util.List;

/**
 *  2018/12/25.
 */
public class EmotionAdapter extends RecyclerView.Adapter<EmotionAdapter.EmotionHolder> {

    private Context mContext;
    private List<AlbumMoreBean.DataBean.TagListBean.NextTagListBean> mList;

    public EmotionAdapter(Context context, @NonNull ArrayList<AlbumMoreBean.DataBean.TagListBean.NextTagListBean> list) {
        mContext = context;
        mList = list;
    }
    @Override
    public EmotionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_bottom_music_view,parent,false);
        EmotionHolder holder = new EmotionHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onBindViewHolder(final EmotionHolder holder, final int position) {

        Glide.with(mContext).load(mList.get(position).getNextTagPicture()).error(R.mipmap.ic_member_pic).transform(new GlideCircleTransfromUtil(mContext)).into(holder.ivVoice);
        final AlbumMoreBean.DataBean.TagListBean.NextTagListBean nextTagListBean = mList.get(position);
        holder.tvMusicName.setText(mList.get(position).getNextTagName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmotionPlayerActicity.startAction(mContext,nextTagListBean.getUrl(),nextTagListBean.getNextTagName());
            }
        });
        // 保存子项的ViewHolder
    }

    /**
     * 刷新区局按钮点击状态
     */

    class EmotionHolder extends RecyclerView.ViewHolder{
        TextView tvMusicName;
        ImageView ivVoice;
        public EmotionHolder(View itemView) {
            super(itemView);
            ivVoice = (ImageView) itemView.findViewById(R.id.iv_voice1);
            tvMusicName = (TextView) itemView.findViewById(R.id.tv_music_name1);
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