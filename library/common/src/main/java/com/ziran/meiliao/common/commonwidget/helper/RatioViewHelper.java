package com.ziran.meiliao.common.commonwidget.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.ziran.meiliao.common.R;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/11/21 11:27
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/11/21$
 * @updateDes ${TODO}
 */

public class RatioViewHelper {
    private Context mContext;
    private float widthRatio;
    private float heightRatio;
    private int defaultSize = 300;
    private int[] measures;


    public RatioViewHelper(Context context, float widthRatio, float heightRatio) {
        this.mContext = context;
        this.widthRatio = widthRatio;
        this.heightRatio = heightRatio;
    }

    public RatioViewHelper(float widthRatio, float heightRatio) {
        this.widthRatio = widthRatio;
        this.heightRatio = heightRatio;
    }

    public RatioViewHelper(float widthRatio, float heightRatio, int defaultSize) {
        this.widthRatio = widthRatio;
        this.heightRatio = heightRatio;
        this.defaultSize = defaultSize;
    }

    public int[] onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = measureSize(defaultSize, widthMeasureSpec);
        int height = measureSize(defaultSize, heightMeasureSpec);

        return getRatio(width, height);
    }

    public int[] getRatio(int width, int height) {
        if (heightRatio > 0 && width > 0) {
            height = (int) (width * heightRatio);
        } else if (widthRatio > 0 && height > 0) {
            width = (int) (height * widthRatio);
        }
        measures = new int[]{width, height};
        return measures;
    }

    public static int measureSize(int defaultSize, int measureSpec) {
        int specMode = View.MeasureSpec.getMode(measureSpec);
        int specSize = View.MeasureSpec.getSize(measureSpec);
        //设置一个默认值，就是这个View的默认宽度为500，这个看我们自定义View的要求
        int result = 0;
        if (specMode == View.MeasureSpec.AT_MOST) {//相当于我们设置为wrap_content
            result = defaultSize;
        } else if (specMode == View.MeasureSpec.EXACTLY) {//相当于我们设置为match_parent或者为一个具体的值
            result = specSize;
        }
        return result;
    }

    /**
     * drawable转bitmap
     *
     * @param drawable
     * @return
     */
    public Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bd = (BitmapDrawable) drawable;
            return bd.getBitmap();
        }
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }

    public void loadDefault() {
        if (measures == null || mContext == null || mOnLoadDefaultBitmapListener == null) return;
        int type = measures[0] - measures[1];
        if (type > 0) {//宽大于高
            if (measures[0] * 1f / measures[1] > 2) {
                mOnLoadDefaultBitmapListener.loadDefault(R.mipmap.ic_loading_square_big);
            } else {
                mOnLoadDefaultBitmapListener.loadDefault(R.mipmap.ic_loading_square);
            }
        } else if (type < 0) {//宽小于高
            mOnLoadDefaultBitmapListener.loadDefault(R.mipmap.ic_loading_square_small);
        } else {//宽等于高
            mOnLoadDefaultBitmapListener.loadDefault(R.mipmap.ic_loading_rectangle);
        }
    }

    private OnLoadDefaultBitmapListener mOnLoadDefaultBitmapListener;

    public void setOnLoadDefaultBitmapListener(OnLoadDefaultBitmapListener onLoadDefaultBitmapListener) {
        mOnLoadDefaultBitmapListener = onLoadDefaultBitmapListener;
    }

    public float getWidthRatio() {
        return widthRatio;
    }

    public boolean setWidthRatio(float widthRatio) {
        if (this.widthRatio == widthRatio) return false;
        this.widthRatio = widthRatio;
        return true;
    }

    public float getHeightRatio() {
        return heightRatio;
    }

    public boolean setHeightRatio(float heightRatio) {
        if (this.heightRatio == heightRatio) return false;
        this.heightRatio = heightRatio;
        return true;
    }

    public interface OnLoadDefaultBitmapListener {
        void loadDefault(int resId);
    }

}
