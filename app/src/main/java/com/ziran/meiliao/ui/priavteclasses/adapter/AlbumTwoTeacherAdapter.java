package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ziran.meiliao.R;

import java.util.List;

/**
 * Created by Administrator on 2019/3/14.
 */

public class AlbumTwoTeacherAdapter extends RecyclerView.Adapter<AlbumTwoTeacherAdapter.AlbumTwoClassHolder>{



    private Context mContext;
    private List<String> mList;

    public AlbumTwoTeacherAdapter(Context context,  List<String> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public AlbumTwoTeacherAdapter.AlbumTwoClassHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_teacher_view,parent,false);
        AlbumTwoTeacherAdapter.AlbumTwoClassHolder holder = new AlbumTwoTeacherAdapter.AlbumTwoClassHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onBindViewHolder(final AlbumTwoTeacherAdapter.AlbumTwoClassHolder holder, final int position) {
        holder.tvTitle.setText(mList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
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
        void itemClick(int position, int TagId, String nextTagName);
    }
    private AlbumTwoTeacherAdapter.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(AlbumTwoTeacherAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
