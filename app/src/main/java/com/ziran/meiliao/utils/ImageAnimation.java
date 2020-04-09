package com.ziran.meiliao.utils;

/**
 * Created by Administrator on 2017/3/11.
 */

import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.ziran.meiliao.R;

/**
 *   正在播放的动画效果
 * ImageAnimation animation = new ImageAnimation(Iv_guide（控件 ImageView）, images （图片资源 int[]）, 115 (每张图片的时间间隔));
 */
public class ImageAnimation {

    private Handler handler;//线程处理
    private MovieAction action;//播放器
    private static ImageAnimation imageAnimation;
    private static int[] frameRes = {R.mipmap.ic_jyg_play_1, R.mipmap.ic_jyg_play_2, R.mipmap.ic_jyg_play_3, R.mipmap.ic_jyg_play_4};

    //固定循环时间
    public ImageAnimation(ImageView view, int[] frameRes, int duration) {
        int len = frameRes.length;
        int[] frameDuration = new int[len];
        for (int i = 0; i < len; i++) {
            frameDuration[i] = duration;
        }
        this.Init(view, frameRes, frameDuration);
    }

    //非固定循环时间
    public ImageAnimation(ImageView view, int[] frameRes, int[] frameDuration) {
        this.Init(view, frameRes, frameDuration);
    }

    private void Init(ImageView view, int[] frameRes, int[] frameDuration) {
        //null == view) {  Log.e("ImageAnimation", "创建动画失败。");
        if (null == frameRes || null == frameDuration || 0 == frameRes.length || 0 == frameDuration.length) {
        } else if (frameRes.length != frameDuration.length) {
        } else {
            handler = new Handler();
            action = new MovieAction(handler, view, frameRes, frameDuration);
            action.setPlayCount(-1);
        }
    }

    public static ImageAnimation get() {
        if (imageAnimation == null) {
            synchronized (ImageAnimation.class) {
                if (imageAnimation == null)
                    imageAnimation = new ImageAnimation(null, frameRes, 200);
            }
        }
        return imageAnimation;
    }

    public void setTager(ImageView imageView) {
        if (action != null) {
            action.destory();
            action.setTager(imageView);
        }
    }

    public void setTager(ImageView imageView, boolean autoPlay) {
        if (action != null) {
            action.destory();
            action.setTager(imageView);
            if (autoPlay) {
                action.start();
            }
        }
    }

    public void start() {
        if (action != null) {
            action.start();
        }
    }

    public void onDestroy() {
        if (action != null) {
            action.destory();
        }
    }
    public void onDestroy(boolean hideTargetView) {
        if (action != null) {
            action.destory(hideTargetView);
        }
    }

    //动画播放类
    private class MovieAction implements Runnable {

        private ImageView mView;//画布
        private int[] mFrameRes;//资源
        private int[] mFrameDuration;//间隔
        private int currentFrame;//当前帧
        private int totalFrame;//总帧数

        private Handler mHandler;//线程

        public MovieAction(Handler handler, ImageView view, int[] frameRes, int[] frameDuration) {
            this.mView = view;
            this.mFrameRes = frameRes;
            this.mFrameDuration = frameDuration;
            this.mHandler = handler;

            totalFrame = this.mFrameRes.length;
            currentFrame = 0;

        }

        public void start() {
            currentFrame = 0;
            currentPos = 0;
            mHandler.postDelayed(this, mFrameDuration[currentFrame]);
        }

        public void destory() {
            this.mHandler.removeCallbacks(this);
        }
        public void destory(boolean hideTargetView) {
            if (mView!=null && hideTargetView){
                mView.setVisibility(View.GONE);
            }
            this.mHandler.removeCallbacks(this);
        }


        public void setTager(ImageView imageView) {
            this.mView = imageView;
        }

        /**
         * Starts executing the active part of the class' code. This method is
         * called when a thread is started that has been created with a class which
         * implements {@code Runnable}.
         */
        @Override
        public void run() {
            mView.setBackgroundResource(mFrameRes[currentFrame]);

            currentFrame++;
            if (++currentFrame < totalFrame) {
                mHandler.postDelayed(this, mFrameDuration[currentFrame]);
            } else {
                destory();
                currentPos++;
                if (playCount == -1) {
                    currentFrame = 0;
                    mHandler.postDelayed(this, mFrameDuration[currentFrame]);
                } else if
                        (currentPos < playCount) {
                    currentFrame = 0;
                    mHandler.postDelayed(this, mFrameDuration[currentFrame]);
                }
            }
        }

        private int currentPos = 0;
        private int playCount;

        private void setPlayCount(int playCount) {
            this.playCount = playCount;
        }
    }
}
