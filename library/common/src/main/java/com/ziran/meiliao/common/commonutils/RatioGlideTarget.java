package com.ziran.meiliao.common.commonutils;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.ziran.meiliao.common.commonwidget.RoundImageView;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/1 10:34
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/1$
 * @updateDes ${TODO}
 */

public class RatioGlideTarget extends SimpleTarget<Bitmap> {
    private RoundImageView imageView;
    public RatioGlideTarget(RoundImageView imageView){
        this.imageView = imageView;
    }

    public RatioGlideTarget( ){

    }

    @Override
    public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
        // do something with the bitmap
        // for demonstration purposes, let's just set it to an ImageView

        if (imageView != null) {
            float ratio = resource.getHeight() * 1f / resource.getWidth();
            imageView.setHeightRatio(ratio);
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
