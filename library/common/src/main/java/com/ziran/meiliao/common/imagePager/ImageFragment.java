package com.ziran.meiliao.common.imagePager;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.ziran.meiliao.common.R;
import com.ziran.meiliao.common.commonutils.CommonTarget;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;


/**
 * Created by Administrator on 2017/1/7.
 */

public class ImageFragment extends Fragment {

    private static final String URL = "URL";

    public static ImageFragment getInstance(String url) {
        Bundle bundle = new Bundle();
        bundle.putString(URL, url);
        ImageFragment fragment = new ImageFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    private String url;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getArguments().getString(URL);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.item_pager_image, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final PhotoView imageView = (PhotoView) view.findViewById(R.id.image);

        //单击图片退出
        imageView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {
                getActivity().finish();
            }
        });
        final ProgressBar loading = new ProgressBar(getContext());
        FrameLayout.LayoutParams loadingLayoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        loadingLayoutParams.gravity = Gravity.CENTER;
        loading.setLayoutParams(loadingLayoutParams);
        ((FrameLayout) view).addView(loading);

        Glide.with(getContext()).load(url).asBitmap().fitCenter().thumbnail(0.3f).into(new CommonTarget(imageView) {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
                loading.setVisibility(View.GONE);
                super.onResourceReady(resource, glideAnimation);
            }

            @Override
            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                loading.setVisibility(View.GONE);
                e.printStackTrace();
                super.onLoadFailed(e, errorDrawable);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
