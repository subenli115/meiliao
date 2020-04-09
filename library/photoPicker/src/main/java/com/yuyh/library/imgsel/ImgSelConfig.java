package com.yuyh.library.imgsel;

import android.content.Context;
import android.graphics.Color;
import android.os.Environment;
import android.widget.ImageView;

import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.yuyh.library.imgsel.utils.MyFileUtils;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * @author yuyh.
 * @date 2016/8/5.
 */
public class ImgSelConfig {
    public static final int RequestCode = 1;
    public static boolean needSaveImgs = false;

    public int fromType;
    /**
     * 是否需要裁剪
     */
    public boolean needCrop;

    /**
     * 是否多选
     */
    public boolean multiSelect;

    /**
     * 最多选择图片数
     */
    public int maxNum = 9;

    /**
     * 第一个item是否显示相机
     */
    public boolean needCamera;

    public int statusBarColor = -1;

    public String cropImagePath;
    /**
     * 返回图标资源
     */
    public int backResId = -1;

    /**
     * 标题
     */
    public String title;

    /**
     * 标题颜色
     */
    public int titleColor;

    /**
     * titlebar背景色
     */
    public int titleBgColor;

    /**
     * 确定按钮文字颜色
     */
    public int btnTextColor;

    /**
     * 确定按钮背景色
     */
    public int btnBgColor;

    /**
     * 拍照存储路径
     */
    public String filePath;

    /**
     * 自定义图片加载器
     */
    public ImageLoader loader;


    /**
     * 裁剪输出大小
     */
    public int aspectX = 1;
    public int aspectY = 1;
    public int outputX = 500;
    public int outputY = 500;
    public static ArrayList<String> SELECT_DATA;

    public ImgSelConfig(Builder builder) {
        this.fromType = builder.fromType;
        this.needCrop = builder.needCrop;
        this.multiSelect = builder.multiSelect;
        this.maxNum = builder.maxNum;
        this.needCamera = builder.needCamera;
        this.statusBarColor = builder.statusBarColor;
        this.backResId = builder.backResId;
        this.title = builder.title;
        this.titleBgColor = builder.titleBgColor;
        this.titleColor = builder.titleColor;
        this.btnBgColor = builder.btnBgColor;
        this.btnTextColor = builder.btnTextColor;
        this.filePath = builder.filePath;
        this.loader = builder.loader;
        this.aspectX = builder.aspectX;
        this.aspectY = builder.aspectY;
        this.outputX = builder.outputX;
        this.outputY = builder.outputY;
        this.cropImagePath = builder.cropImagePath;
    }

    public static class Builder implements Serializable {

        private boolean needCrop = false;
        private boolean multiSelect = true;
        private int fromType = 0;
        private int maxNum = 9;
        private boolean needCamera = true;
        public int statusBarColor = -1;
        private int backResId = -1;
        private String title = "图片";
        private int titleColor;
        private int titleBgColor;
        private int btnTextColor;
        private int btnBgColor;
        private String filePath;
        private ImageLoader loader;
        private String cropImagePath;
        private int aspectX = 1;
        private int aspectY = 1;
        private int outputX = 400;
        private int outputY = 400;

        public Builder(ImageLoader loader) {
            this.loader = loader;

            if (MyFileUtils.isSdCardAvailable()) filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Camera";
            else filePath = Environment.getRootDirectory().getAbsolutePath() + "/Camera";

            titleBgColor = Color.BLACK;
            titleColor = Color.WHITE;

            btnBgColor = Color.TRANSPARENT;
            btnTextColor = Color.WHITE;

            MyFileUtils.createDir(filePath);
        }

        public Builder needCrop(boolean needCrop) {
            this.needCrop = needCrop;
            return this;
        }

        public Builder multiSelect(boolean multiSelect) {
            this.multiSelect = multiSelect;
            return this;
        }

        public Builder maxNum(int maxNum) {
            this.maxNum = maxNum;
            return this;
        }

        public Builder needCamera(boolean needCamera) {
            this.needCamera = needCamera;
            return this;
        }

        public Builder statusBarColor(int statusBarColor) {
            this.statusBarColor = statusBarColor;
            return this;
        }

        public Builder backResId(int backResId) {
            this.backResId = backResId;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder titleColor(int titleColor) {
            this.titleColor = titleColor;
            return this;
        }

        public Builder titleBgColor(int titleBgColor) {
            this.titleBgColor = titleBgColor;
            return this;
        }

        public Builder btnTextColor(int btnTextColor) {
            this.btnTextColor = btnTextColor;
            return this;
        }

        public Builder btnBgColor(int btnBgColor) {
            this.btnBgColor = btnBgColor;
            return this;
        }

        public Builder fromType(int fromType) {
            this.fromType = fromType;
            return this;
        }

        private Builder filePath(String filePath) {
            this.filePath = filePath;
            return this;
        }

        private Builder cropImagePath(String cropImagePath) {
            this.cropImagePath = cropImagePath;
            return this;
        }

        public Builder cropSize(int aspectX, int aspectY, int outputX, int outputY) {
            this.aspectX = aspectX;
            this.aspectY = aspectY;
            this.outputX = outputX;
            this.outputY = outputY;
            return this;
        }

        public ImgSelConfig build() {
            return new ImgSelConfig(this);
        }
    }

    public static ImgSelConfig DEFAULT_SIGN = new ImgSelConfig.Builder(new ImageLoader() {
        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            ImageLoaderUtils.displayResult(context, imageView, path);
        }
    }).needCrop(false).multiSelect(false).build();

    public static ImgSelConfig DEFAULT_SIGN_HEAD(String cropImagePath) {
        return new ImgSelConfig.Builder(new ImageLoader() {
            @Override
            public void displayImage(Context context, String path, ImageView imageView) {
                ImageLoaderUtils.displayResult(context, imageView, path);
            }
        }).needCrop(true).cropImagePath(cropImagePath).cropSize(2, 2, 150, 150).multiSelect(false).build();
    }
    public static ImgSelConfig MULTI_SELECT(int maxNum) {
        return new ImgSelConfig.Builder(new ImageLoader() {
            @Override
            public void displayImage(Context context, String path, ImageView imageView) {
                ImageLoaderUtils.displayResult(context, imageView, path);
            }
        }).needCrop(false).multiSelect(true).maxNum(maxNum).build();
    }


    public static ImgSelConfig DEFAULT_NULTI_SIX = new ImgSelConfig.Builder(new ImageLoader() {
        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            ImageLoaderUtils.displayResult(context, imageView, path);
        }
    }).needCrop(false).multiSelect(true).maxNum(6).build();
    public static ImgSelConfig DEFAULT_NULTI_NICE = new ImgSelConfig.Builder(new ImageLoader() {
        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            ImageLoaderUtils.displayResult(context, imageView, path);
        }
    }).needCrop(false).multiSelect(true).maxNum(9).build();


    public static boolean checkSelect(String url) {
        return EmptyUtils.isNotEmpty(SELECT_DATA) && SELECT_DATA.contains(url);
    }
}
