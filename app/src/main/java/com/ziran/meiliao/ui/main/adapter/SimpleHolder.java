package com.ziran.meiliao.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.freegeek.android.materialbanner.holder.Holder;
import com.freegeek.android.materialbanner.simple.SimpleBannerData;
import com.squareup.picasso.Picasso;
import com.ziran.meiliao.R;
import com.ziran.meiliao.ui.bean.HomeBannerBean;
import com.ziran.meiliao.widget.GlideRoundTransform;

public class SimpleHolder implements Holder<HomeBannerBean.DataBean> {
    private ImageView imageView;

    public SimpleHolder() {
    }

    public View createView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.simple_banner_item, (ViewGroup)null);
        this.imageView = (ImageView)view.findViewById(R.id.imageView);
        this.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return view;
    }

    public void updateUI(Context context, int position, HomeBannerBean.DataBean data) {
        Picasso.with(context).load(data.getImg()).into(this.imageView);
    }
}
