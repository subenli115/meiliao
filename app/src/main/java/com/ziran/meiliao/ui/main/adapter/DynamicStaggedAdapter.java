package com.ziran.meiliao.ui.main.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aliyun.player.AliPlayer;
import com.atech.staggedrv.StaggedAdapter;
import com.atech.staggedrv.model.StaggedModel;
import com.bumptech.glide.Glide;
import com.zhy.autolayout.AutoRelativeLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonwidget.OnNoDoubleClickListener;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.ui.bean.SpaceRecommendBean;
import com.ziran.meiliao.ui.main.activity.DynamicDeatilsActivity;

public class DynamicStaggedAdapter<T extends StaggedModel> extends StaggedAdapter<T>{

    private final Context mc;
    private AliPlayer player;
    private String bgImg;

    public DynamicStaggedAdapter(Context c) {
        super(c);
        mc=c;
    }

    @Override
    public RecyclerView.ViewHolder addViewHolder(ViewGroup viewGroup, int i) {
        //绑定自定义的viewholder
        View v = LayoutInflater.from(mc).inflate(R.layout.custom_item_layout,viewGroup,false);
        return new MyHolder(v);
    }




    @Override
    public void bindView(RecyclerView.ViewHolder viewHolder, int i) {
        //onBindViewHolder代码块
        MyHolder myHolder = (MyHolder)viewHolder;
        // 在加载图片之前设定好图片的宽高，防止出现item错乱及闪烁
        ViewGroup.LayoutParams layoutParams = myHolder.img.getLayoutParams();
        SpaceRecommendBean.DataBean bean =(SpaceRecommendBean.DataBean) datas.get(i);
        layoutParams.width =bean.getWidth();
        if(!EmptyUtils.isEmpty(bean.getContent())){
            myHolder.tvContent.setVisibility(View.VISIBLE);
            myHolder.tvContent.setText(bean.getContent());
            myHolder.ivType.setVisibility(View.GONE);
        }else {
            myHolder.tvContent.setVisibility(View.GONE);
        }
        if(bean.getEnclosureType()==0){
            myHolder.arlSound.setVisibility(View.GONE);
            //图片
            if(bean.getImages()!=null&&bean.getImages().length()>0){
                myHolder.img.setVisibility(View.VISIBLE);
                myHolder.ivType.setVisibility(View.VISIBLE);
                myHolder.ivType.setImageResource(R.mipmap.icon_wechat_pics);
                layoutParams.height=bean.getHeight();
                myHolder.img.setLayoutParams(layoutParams);
                Glide.with(mc).load(bean.getImages()).into(myHolder.img);
            }else {
                myHolder.ivType.setVisibility(View.GONE);
                layoutParams.height=0;
                myHolder.img.setLayoutParams(layoutParams);
            }
        }else if(bean.getEnclosureType()==2){
            //音频
            if(bean.getDuration()!=null){
                myHolder.tvTime.setText(bean.getDuration());
            }
            myHolder.arlSound.setVisibility(View.VISIBLE);
            layoutParams.height=0;
            myHolder.img.setLayoutParams(layoutParams);
        }else if(bean.getEnclosureType()==1){
            //视频
            myHolder.arlSound.setVisibility(View.GONE);
            myHolder.img.setVisibility(View.VISIBLE);
            myHolder.ivType.setVisibility(View.VISIBLE);
            myHolder.ivType.setImageResource(R.mipmap.icon_wechat_play);
            layoutParams.height=bean.getHeight();
            myHolder.img.setLayoutParams(layoutParams);

            Glide.with(mc).load(bean.getImages()).into(myHolder.img);
        }
        myHolder.tvLikes.setText(bean.getClickNum()+"赞");
        myHolder.tvName.setText(bean.getNickname());
        Glide.with(mc).load(bean.getAvatar()).into(myHolder.roundIv);
        myHolder.itemView.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                DynamicDeatilsActivity.startAction(mc,bean.getId());
            }
        });
    }
    /**
     * 自定义viewholder
     */

    class MyHolder extends RecyclerView.ViewHolder{

        ImageView img,roundIv,ivType;
       AutoRelativeLayout arlSound;
       TextView tvContent,tvLikes,tvName,tvTime;



        public MyHolder(@NonNull View itemView) {
            super(itemView);
            arlSound = itemView.findViewById(R.id.arl_sound);
            img = itemView.findViewById(R.id.img);
            roundIv = itemView.findViewById(R.id.roundIv);
            tvContent = itemView.findViewById(R.id.tv_content);
            tvLikes = itemView.findViewById(R.id.tv_likes);
            tvName = itemView.findViewById(R.id.tv_name);
            ivType = itemView.findViewById(R.id.iv_type);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }

}
