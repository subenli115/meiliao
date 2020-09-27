package com.ziran.meiliao.common.commonutils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.RequestListener;
import com.ziran.meiliao.common.R;
import com.ziran.meiliao.common.commonwidget.RoundImageView;

import java.io.File;

/**
 * Description : 图片加载工具类 使用glide框架封装
 */
public class ImageLoaderUtils {


    public static void loadFit(Context context, String url, ImageView view, int defaultResId) {
        if (NetWorkUtils.isNetConnected(context)) {
            view.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(context).load(url).fitCenter().dontAnimate().placeholder(defaultResId).into(view);
        } else {
            view.setImageResource(defaultResId);
        }
    }

    /**
     * 带监听处理
     *
     * @param context
     * @param url
     * @param view
     * @param listener
     */
    public static void loadFitCenter(Context context, String url, ImageView view, RequestListener listener) {
        Glide.with(context).load(url).fitCenter().dontAnimate().listener(listener).into(view);
    }

    public static void loadCenterCrop(Context context, String url, ImageView view, RequestListener listener) {
        Glide.with(context).load(url).centerCrop().dontAnimate().listener(listener).into(view);
    }

    /**
     * 设置图片大小处理
     *
     * @param context
     * @param url
     * @param view
     * @param defaultResId
     * @param width
     * @param height
     */
    public static void loadFitOverride(Context context, String url, ImageView view, int defaultResId, int width, int height) {
        if (NetWorkUtils.isWifiConnected(context)) {
            Glide.with(context).load(url).fitCenter().dontAnimate().override(width, height).placeholder(defaultResId).into(view);
        } else {
            view.setImageResource(defaultResId);
        }
    }

    public static void display(Context context, ImageView imageView, String url, int placeholder, int error, BitmapTransformation transformation, DiskCacheStrategy
            diskCacheStrategy) {
        display(context, imageView, url, placeholder, error, true, transformation, diskCacheStrategy);
    }

    public static void display(Context context, ImageView imageView, String url, int placeholder, int error, boolean crop, BitmapTransformation transformation, DiskCacheStrategy
            diskCacheStrategy) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        if (TextUtils.isEmpty(url)) return;
//        DrawableTypeRequest<String> typeRequest = Glide.with(context).load(url);
//        typeRequest.diskCacheStrategy(diskCacheStrategy).centerCrop();
//        if (placeholder != 0) {
//            typeRequest.placeholder(placeholder);
//        }
//        if (error != 0) {
//            typeRequest.error(error);
//        }
//        if (crop) {
//            typeRequest.centerCrop();
//        } else {
//            typeRequest.fitCenter();
//        }
////        typeRequest.thumbnail(0.2f);
//        if (transformation != null) {
//            typeRequest.bitmapTransform(transformation);
//        }
//        typeRequest.into(imageView);
    }

    public static void display(Context context, ImageView imageView, String url) {
        display(context, imageView, url, 0, 0, null, DiskCacheStrategy.ALL);
    }

    public static void display(ImageView imageView, String url) {
        if (imageView == null) return;
        display(imageView.getContext(), imageView, url, 0, 0, null, DiskCacheStrategy.ALL);
    }

    public static void display(Context context, ImageView imageView, String url, boolean crop) {
        display(context, imageView, url, 0, 0, crop, null, DiskCacheStrategy.ALL);
    }

    public static void displayResult(Context context, ImageView imageView, String url) {
//        display(context, imageView, url, 0, 0, null, DiskCacheStrategy.RESULT);
    }

    public static void display(Context context, ImageView imageView, String url, int errorId) {
        display(context, imageView, url, errorId, errorId, null, DiskCacheStrategy.ALL);
    }

    public static void displayTager(Context context, final ImageView imageView, String url, int errorId) {
//        Glide.with(context).load(url).asBitmap().placeholder(errorId).fitCenter().error(errorId).into(new CommonTarget(imageView));
    }

    public static void displayRatioTarger(Context context, final RoundImageView imageView, String url, int errorId) {
//        Glide.with(context).load(url).asBitmap().placeholder(errorId).fitCenter().error(errorId).into(new RatioGlideTarget(imageView));
    }

    public static void displayTager(Context context, final ImageView imageView, String url) {
//        Glide.with(context).load(url).asBitmap().fitCenter().into(new CommonTarget(imageView));
    }

    public static void display(Context context, ImageView imageView, String url, int errorId, boolean crop) {
        display(context, imageView, url, errorId, errorId, crop, null, DiskCacheStrategy.ALL);
    }

    public static void display(Context context, ImageView imageView, File url) {
//        if (imageView == null) {
//            throw new IllegalArgumentException("argument error");
//        }
//        Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.RESULT).centerCrop().placeholder(R.mipmap.ic_loading_rectangle).error(R.mipmap.ic_loading_rectangle)
//                .crossFade().into(imageView);
    }

//    public static void displayNone(final Context context, ImageView imageView, String url) {
//        display(context, imageView, url, R.mipmap.ic_loading_rectangle, R.mipmap.ic_loading_rectangle, null, DiskCacheStrategy.NONE);
//    }

    public static void displaySmallPhoto(Context context, ImageView imageView, String url) {
//        if (imageView == null) {
//            throw new IllegalArgumentException("argument error");
//        }
//        Glide.with(context).load(url).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.mipmap.ic_loading_rectangle).error(R.mipmap.ic_loading_rectangle)
//                .thumbnail(0.5f).into(imageView);
    }

    public static void displayBigPhoto(Context context, ImageView imageView, String url) {
//        if (imageView == null) {
//            throw new IllegalArgumentException("argument error");
//        }
//        Glide.with(context).load(url).asBitmap().format(DecodeFormat.PREFER_ARGB_8888).diskCacheStrategy(DiskCacheStrategy.NONE).placeholder(R.mipmap.ic_loading_rectangle).error
//                (R.mipmap.ic_loading_rectangle).into(imageView);
    }

//    public static void display(Context context, ImageView imageView, int url) {
//        if (imageView == null) {
//            throw new IllegalArgumentException("argument error");
//        }
//        Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.RESULT).centerCrop().placeholder(R.mipmap.ic_loading_rectangle).error(R.mipmap.ic_loading_rectangle)
//                .crossFade().into(imageView);
//    }

    public static void displayRound(Context context, ImageView imageView, String url) {
//        display(context, imageView, url, GlideRoundTransformUtil.get(context), false);
    }

//    public static void displayRound(Context context, ImageView imageView, String url, int errorId) {
//        display(context, imageView, url, R.mipmap.ic_loading_rectangle, errorId, GlideCircleTransfromUtil.get(context), DiskCacheStrategy.ALL);
//    }

    public static void displayCircle(Context context, ImageView imageView, String url, int loading) {
//        display(context, imageView, url, loading, loading, GlideCircleTransfromUtil.get(context), DiskCacheStrategy.ALL);
    }

    public static void displayCircle(Context context, ImageView imageView, String url) {
//        display(context, imageView, url, 0, 0, GlideCircleTransfromUtil.get(context), DiskCacheStrategy.ALL);
    }

    public static void display(Context context, ImageView imageView, String url, BitmapTransformation transformation, boolean skip) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }

        Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.mipmap.ic_loading_rectangle).error(R.mipmap.ic_loading_rectangle).error(R.mipmap
                .ic_loading_rectangle).centerCrop().transform(transformation).into(imageView);
    }

}
