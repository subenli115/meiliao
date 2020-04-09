package com.ziran.meiliao.common.commonutils;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

/**
 * Created by Administrator on 2017/3/22.
 */

public class CommonTarget extends SimpleTarget<Bitmap>{
    private ImageView imageView;
    public CommonTarget(ImageView imageView){
        this.imageView = imageView;
    }

    public CommonTarget( ){
   
    }

    @Override
    public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
        // do something with the bitmap
        // for demonstration purposes, let's just set it to an ImageView
        if (imageView != null) {
            imageView.setImageBitmap(resource);
        }
    }

    @Override
    public void onLoadStarted(Drawable placeholder) {
        if (imageView != null && placeholder!=null) {
            imageView.setImageDrawable(placeholder);
        }
    }

    @Override
    public void onLoadFailed(Exception e, Drawable errorDrawable) {
        if (imageView != null && errorDrawable!=null) {
            imageView.setImageDrawable(errorDrawable);
        }
    }

}
