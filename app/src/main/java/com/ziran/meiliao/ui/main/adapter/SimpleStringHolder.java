package com.ziran.meiliao.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.freegeek.android.materialbanner.holder.Holder;
import com.squareup.picasso.Picasso;
import com.ziran.meiliao.R;
import com.ziran.meiliao.ui.bean.HomeBannerBean;

public class SimpleStringHolder implements Holder<String> {
    private  String mIsVideo;
    private ImageView imageView,imagePlay;

    public SimpleStringHolder() {

    }

    public SimpleStringHolder(String isVideo) {
        mIsVideo=isVideo;
    }

    public View createView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.simple_banner_home_item, (ViewGroup)null);
        this.imageView = view.findViewById(R.id.imageView);
        this.imagePlay = view.findViewById(R.id.iv_play);
        this.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return view;
    }

    public void updateUI(Context context, int position, String data) {
        Glide.with(context).load(data).placeholder(R.mipmap.icon_meiliao_error).centerCrop().into(this.imageView);
        if(position==0&&mIsVideo.equals("1")){
            imagePlay.setVisibility(View.VISIBLE);
        }else if(position==0&&mIsVideo.equals("2")){
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) imagePlay.getLayoutParams();
            params.height = 74;
            params.width = 74;
            imagePlay.setVisibility(View.VISIBLE);

        }
    }
}
