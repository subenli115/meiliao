package com.ziran.meiliao.widget.ninegridimageview;

import android.content.Context;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Jaeger on 16/2/24.
 *
 * Email: chjie.jaeger@gmail.com
 * GitHub: https://github.com/laobie
 */
public abstract class NineGridImageViewAdapter<T> {

    private boolean showDeleteBtn;

    public NineGridImageViewAdapter(boolean showDeleteBtn) {
        this.showDeleteBtn = showDeleteBtn;
    }

    protected abstract void onDisplayImage(Context context, ImageView imageView, T t);

    protected void onItemImageClick(Context context, ImageView imageView, int index, List<T> list) {
    }
    protected void onItemImageLongClick(Context context, ImageView imageView, int index, List<T> list) {
    }

    protected ImageView generateImageView(Context context) {
        GridImageView imageView = new GridImageView(context);
        imageView.setShowDeleteBtn(showDeleteBtn);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }
}