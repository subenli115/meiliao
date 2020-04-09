package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.ui.bean.AlbumClassifyBean;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;

/**
 *  2018/12/25.
 */
public class AlbumTwoClassAdapter extends RecyclerView.Adapter<AlbumTwoClassAdapter.AlbumTwoClassHolder> {

    private  List<Boolean> mIsHy;
    private Context mContext;
    private List<AlbumClassifyBean.DataBean.TagListBean.NextTagListBean> mList;
    private boolean isSave;

    public AlbumTwoClassAdapter(Context context, @NonNull List<AlbumClassifyBean.DataBean.TagListBean.NextTagListBean> list) {
        mContext = context;
        mList = list;
    }


    @Override
    public AlbumTwoClassHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_album_classify,parent,false);
        AlbumTwoClassHolder holder = new AlbumTwoClassHolder(view);
        return holder;
    }

    public void update(List<AlbumClassifyBean.DataBean.TagListBean.NextTagListBean> nextTagList,List<Boolean> booleans){
        this.mList=nextTagList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
        public void onBindViewHolder(final AlbumTwoClassHolder holder, final int position) {
        holder.tvTitle.setText(mList.get(position).getNextTagName());
        if(mList.get(position).isSelected()                                                                                                                                                                                                           ){
            ((AutoLinearLayout)(holder.itemView)).setBackgroundResource(R.drawable.normal_bg_green_tran_shandow22);
        }else{
            ((AutoLinearLayout)(holder.itemView)).setBackgroundResource(R.drawable.normal_bg_white);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mList.get(position).getName().equals("导师")){

                    if(mList.get(position).isSelected()){
                        mList.get(position).setSelected(false);
                        isSave=false;
                    }else{
                        mList.get(position).setSelected(true);
                        isSave=true;
                    }
                for(AlbumClassifyBean.DataBean.TagListBean.NextTagListBean bean:mList){

                    bean.setSelected(false);
                }

                mList.get(position).setSelected(isSave);
                notifyDataSetChanged();
            }
                onItemClickListener.itemClick(position,mList.get(position).getNextTagId(),mList.get(position).getNextTagName(),v,mList.get(position).getName(),isSave);
            }
        });
        // 保存子项的ViewHolder
    }

    /**
     * 刷新区局按钮点击状态
     */

    class AlbumTwoClassHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        public AlbumTwoClassHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }


    public interface OnItemClickListener{
        void itemClick(int position, int TagId, String nextTagName, View view, String name, boolean isSave);
    }
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}