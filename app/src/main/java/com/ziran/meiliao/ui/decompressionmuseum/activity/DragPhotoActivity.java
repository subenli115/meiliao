package com.ziran.meiliao.ui.decompressionmuseum.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.irecyclerview.SimpleAnimatorListener;
import com.ziran.meiliao.widget.DragPhotoView;
import com.ziran.meiliao.widget.ninegridimageview.NineGridImageView;
import com.yuyh.library.imgsel.ImgSelActivity;

import java.util.ArrayList;
import java.util.List;

public class DragPhotoActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private List<String> mList;
    private ArrayList<NineGridImageView.Locations> mLocationses;
    private DragPhotoView[] mPhotoViews;
    private DragPhotoView dragPhotoView;
    int mOriginLeft;
    int mOriginTop;
    int mOriginHeight;
    int mOriginWidth;
    int mOriginCenterX;
    int mOriginCenterY;
    private float mTargetHeight;
    private float mTargetWidth;
    private float mScaleX;
    private float mScaleY;
    private float mTranslationX;
    private float mTranslationY;
    int index;
    private float targetCenterX;
    private float targetCenterY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_drag_photo);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        dragPhotoView = (DragPhotoView) findViewById(R.id.dragPhotoView);
        Intent intent = getIntent();
        mList = intent.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
        mLocationses = intent.getParcelableArrayListExtra("location");
        index = intent.getIntExtra("index", 0);
        mPhotoViews = new DragPhotoView[mList.size()];
        ImageLoaderUtils.display(DragPhotoActivity.this, dragPhotoView, mList.get(index));
//        ImageLoaderUtils.display(DragPhotoActivity.this, dragPhotoView, mList.get(index), R.mipmap.ic_loading_square_big);
        for (int i = 0; i < mPhotoViews.length; i++) {
            mPhotoViews[i] = new DragPhotoView(this);
            ImageLoaderUtils.display(this, mPhotoViews[i], mList.get(i));
//            ImageLoaderUtils.display(this, mPhotoViews[i], mList.get(i), R.mipmap.ic_loading_square_big);
            mPhotoViews[i].setOnTapListener(new DragPhotoView.OnTapListener() {
                @Override
                public void onTap(DragPhotoView view) {
                    finishWithAnimation();
                }
            });
            mPhotoViews[i].setOnExitListener(new DragPhotoView.OnExitListener() {
                @Override
                public void onExit(DragPhotoView view, float x, float y, float w, float h, float s) {
                    performExitAnimation(view, x, y, w, h, s);
                }
            });
        }
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
//                ImageLoaderUtils.display(DragPhotoActivity.this, dragPhotoView, mList.get(position), R.mipmap.ic_loading_square_big);
                ImageLoaderUtils.display(DragPhotoActivity.this, dragPhotoView, mList.get(position));
            }
        });
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mList.size();
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(mPhotoViews[position]);
                return mPhotoViews[position];
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mPhotoViews[position]);
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        });
        mViewPager.setCurrentItem(index);


        dragPhotoView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ViewUtil.removeGlobalLayout(dragPhotoView,this);

                mOriginLeft = getIntent().getIntExtra("left", 0);
                mOriginTop = getIntent().getIntExtra("top", 0);
                mOriginHeight = getIntent().getIntExtra("height", 0);
                mOriginWidth = getIntent().getIntExtra("width", 0);
                mOriginCenterX = mOriginLeft + mOriginWidth / 2;
                mOriginCenterY = mOriginTop + mOriginHeight / 2;
                int[] location = new int[2];
                final DragPhotoView photoView = dragPhotoView;
                photoView.getLocationOnScreen(location);
                mTargetHeight = (float) photoView.getHeight();
                mTargetWidth = (float) photoView.getWidth();
                mScaleX = (float) mOriginWidth / mTargetWidth;
                mScaleY = (float) mOriginHeight / mTargetHeight;

                targetCenterX = location[0] + mTargetWidth / 2;
                targetCenterY = location[1] + mTargetHeight / 2;

                mTranslationX = mOriginCenterX - targetCenterX;
                mTranslationY = mOriginCenterY - targetCenterY;
                photoView.setTranslationX(mTranslationX);
                photoView.setTranslationY(mTranslationY);
                photoView.setScaleX(mScaleX);
                photoView.setScaleY(mScaleY);
                performEnterAnimation();
                for (int i = 0; i < mPhotoViews.length; i++) {
                    mPhotoViews[i].setMinScale(mScaleX);
                }
            }
        });
    }

    /**
     * ===================================================================================
     * <p>
     * 底下是低版本"共享元素"实现   不需要过分关心  如有需要 可作为参考.
     * <p>
     * Code  under is shared transitions in all android versions implementation
     */
    private void performExitAnimation(final DragPhotoView view, float x, float y, float w, float h, float scale) {
        view.finishAnimationCallBack();
        float viewX = mTargetWidth / 2 + x - mTargetWidth * mScaleX / 2;
        float viewY = mTargetHeight / 2 + y - mTargetHeight * mScaleY / 2;
        ViewGroup.LayoutParams params = dragPhotoView.getLayoutParams();
        params.width = (int) (w * scale);
        params.height = (int) (h * scale);
        dragPhotoView.setX(viewX);
        dragPhotoView.setY(viewY);
        NineGridImageView.Locations locations = mLocationses.get(mViewPager.getCurrentItem());
        mOriginCenterX = locations.getLeft() + locations.getWidth() / 2;
        mOriginCenterY = locations.getTop() + locations.getHeight() / 2;
        float centerX = dragPhotoView.getX() + locations.getWidth() / 2;
        float centerY = dragPhotoView.getY() + locations.getHeight() / 2;
        float translateX = mOriginCenterX - centerX;
        float translateY = mOriginCenterY - centerY;

        dragPhotoView.setVisibility(View.VISIBLE);
        mViewPager.setVisibility(View.GONE);

        animator(dragPhotoView, AnimatorType.TRANSLATE_X, false, viewX, (viewX + translateX));
        animator(dragPhotoView, AnimatorType.TRANSLATE_Y, true, viewY, (viewY + translateY));
    }

    private void performEnterAnimation() {
        final DragPhotoView photoView = dragPhotoView;
        animator(photoView, AnimatorType.TRANSLATE_X, false, photoView.getX(), 0);
        animator(photoView, AnimatorType.TRANSLATE_Y, false, photoView.getY(), 0);
        animator(photoView, AnimatorType.SCALE_X, true, mScaleX, 1);
        animator(photoView, AnimatorType.SCALE_Y, false, mScaleY, 1);
    }

    private void finishWithAnimation() {
        NineGridImageView.Locations locations = mLocationses.get(mViewPager.getCurrentItem());
        final DragPhotoView photoView = dragPhotoView;

        mOriginCenterX = locations.getLeft() + locations.getWidth() / 2;
        mOriginCenterY = locations.getTop() + locations.getHeight() / 2;

        mTranslationX = mOriginCenterX - targetCenterX;
        mTranslationY = mOriginCenterY - targetCenterY;
        dragPhotoView.setVisibility(View.VISIBLE);
        mViewPager.setVisibility(View.GONE);
        animator(photoView, AnimatorType.TRANSLATE_X, false, 0, mTranslationX);
        animator(photoView, AnimatorType.TRANSLATE_Y, false, 0, mTranslationY);
        animator(photoView, AnimatorType.SCALE_X, false, 1, mScaleX);
        animator(photoView, AnimatorType.SCALE_Y, true, 1, mScaleY);
    }
    private enum AnimatorType {
        TRANSLATE_X, TRANSLATE_Y, SCALE_X, SCALE_Y


    }

    private ValueAnimator animator(final DragPhotoView photoView, final AnimatorType animatorType, boolean addListener, float... values) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(values);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                switch (animatorType) {
                    case TRANSLATE_X:
                        photoView.setX((Float) valueAnimator.getAnimatedValue());
                        break;
                    case TRANSLATE_Y:
                        photoView.setY((Float) valueAnimator.getAnimatedValue());
                        break;
                    case SCALE_X:
                        photoView.setScaleX((Float) valueAnimator.getAnimatedValue());
                        break;
                    case SCALE_Y:
                        photoView.setScaleY((Float) valueAnimator.getAnimatedValue());
                        break;
                }
            }
        });
        if (addListener) {
            valueAnimator.addListener(new SimpleAnimatorListener() {
                @Override
                public void onAnimationEnd(Animator animator) {
                    if (animatorType == AnimatorType.TRANSLATE_Y || animatorType == AnimatorType.SCALE_Y) {
                        animator.removeAllListeners();
                        finish();
                        overridePendingTransition(0, 0);
                    } else if (animatorType == AnimatorType.SCALE_X) {  //进入
                        mViewPager.setVisibility(View.VISIBLE);
                        dragPhotoView.setVisibility(View.GONE);
                    }
                }
            });
        }
        valueAnimator.setDuration(300);
        valueAnimator.start();
        return valueAnimator;
    }

    @Override
    public void onBackPressed() {
        finishWithAnimation();
    }
}
