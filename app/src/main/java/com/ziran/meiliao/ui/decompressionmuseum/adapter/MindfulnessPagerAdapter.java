package com.ziran.meiliao.ui.decompressionmuseum.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;

import java.util.List;

/**
 *  减压馆练习滑动背景图片的适配器
 */

public class MindfulnessPagerAdapter extends PagerAdapter {
    private ImageView[] imageViews;
    private Context context;

    public void setUrls(List<String> urls) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        int length = urls.size();
        imageViews = new ImageView[length];
        for (int i = 0; i < length; i++) {
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(params);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnClickListener!=null){
                        mOnClickListener.onClick(v);
                    }
                }
            });
            ImageLoaderUtils.displayTager(context, imageView, urls.get(i));
            imageViews[i] = imageView;
        }
    }

    public MindfulnessPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return imageViews != null ? imageViews.length : 1;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imageViews[position]);//删除页卡
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(imageViews[position], 0);
        return imageViews[position];
    }
    private View.OnClickListener mOnClickListener;
    public void setOnClickListener(View.OnClickListener onClickListener){
        this.mOnClickListener = onClickListener;
    }
}
