package com.ziran.meiliao.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.freegeek.android.materialbanner.holder.Holder;
import com.squareup.picasso.Picasso;
import com.ziran.meiliao.R;

public class SimpleStringChatHolder implements Holder<String> {
    private ImageView imageView,imagePlay;

    public SimpleStringChatHolder() {
    }

    public View createView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.simple_banner_home_chat_item, (ViewGroup)null);
        this.imageView = view.findViewById(R.id.imageView);
        this.imagePlay = view.findViewById(R.id.iv_play);
        this.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return view;
    }

    public void updateUI(Context context, int position, String data) {
        Glide.with(context).load(data).into(this.imageView);
        if(position==0){
            imagePlay.setVisibility(View.VISIBLE);
        }
    }
}
