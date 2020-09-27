package com.ziran.meiliao.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonwidget.OnNoDoubleClickListener;
import com.ziran.meiliao.ui.me.activity.HomeImageSelectActivity;

import java.util.ArrayList;
import java.util.List;


public class DynamicHomeImgAdapter extends RecyclerView.Adapter<DynamicHomeImgAdapter.MyViewHolder> {

    private final boolean mIsSelf;
    private  boolean isVideo;
    List<String> mDatas = new ArrayList<>();
    Context mContext = null;

    //通过构造函数把要显示的数据读取进来
    public DynamicHomeImgAdapter(String video, String videoCover, String homepageImages, Context mContext, boolean isSelf) {
        this.mContext = mContext;
        mIsSelf=isSelf;
        if(video!=null&&video.length()>0){
            isVideo=true;
            mDatas.add(videoCover);
        }
        if(homepageImages!=null&&homepageImages.contains(",")){
            String[] split = homepageImages.split(",");
            for(int i=0;i<split.length;i++){
                mDatas.add(split[i]);
            }
        }else {
            mDatas.add(homepageImages);
        }
    }


    //相当于ListView中的getView()方法
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//      View v = View.inflate(mContext, R.layout.item, null);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        //加载布局的时候需要注意要把parent传进去
        View v = inflater.inflate(R.layout.item_other_img, parent, false);

        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    //在这里需要设置显示的数据
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if(position>=mDatas.size()){
            holder.iv.setVisibility(View.GONE);

        }else {
            Glide.with(mContext).load(mDatas.get(position)).into(holder.iv);
        }

        if(isVideo&&position==0){
            holder.ivBg.setVisibility(View.VISIBLE);
            holder.ivVideo.setVisibility(View.VISIBLE);
        }
        if(mIsSelf){
            holder.itemView.setOnClickListener(new OnNoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View v) {
                    HomeImageSelectActivity.startAction();
                }
            });
        }
    }

    @Override
    public int getItemCount() {

        return mIsSelf?5:mDatas.size();
    }

    //自定义ViewHolder必须继承RecyclerView.ViewHolder，在构造函数中进行ID绑定控件，条目监听等
    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView iv,ivBg,ivVideo;

        public MyViewHolder(final View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv);
            ivBg = (ImageView) itemView.findViewById(R.id.iv_bg);
            ivVideo = (ImageView) itemView.findViewById(R.id.iv_video);
        }
    }




}
