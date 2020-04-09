package com.ziran.meiliao.common.commonwidget.helper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * @author 吴祖清
 * @version 1.0
 * @createDate 2017/9/1 22:23
 * @des ${TODO}
 * @updateAuthor #author
 * @updateDate 2017/9/1
 * @updateDes ${TODO}
 */

public class BolderLineDrawHelper {

    private int bolderColor;
    private Context mContext;
    private Paint mBolderPaint;
    private int mViewWidth, mViewHeight;
    private int paddingLeft, paddingRight, paddingTop, paddingBottom;

    public BolderLineDrawHelper(Context context) {
        mContext = context;
    }


    public void onSizeChange(int w, int h) {
        this.mViewWidth = w;
        this.mViewHeight = h;
    }

    private void initBolderPaint() {
        if (mBolderPaint == null) {
            mBolderPaint = new Paint();
            mBolderPaint.setAntiAlias(true);
        }
    }

    public void draw(Canvas canvas) {
        if (needDraw && bolderColor != 0) {
            initBolderPaint();
            mBolderPaint.setColor(bolderColor);
            int pwr = mViewWidth - paddingRight;
            int phb = mViewHeight - paddingBottom;
            mBolderPaint.setStrokeWidth(0);
            canvas.drawLine(paddingLeft, paddingTop, pwr, paddingTop, mBolderPaint);
            canvas.drawLine(paddingLeft, phb, pwr, phb, mBolderPaint);

        }
    }




    public boolean setBolderColor(int bolderColor) {
        if (this.bolderColor == bolderColor) return false;
        this.bolderColor = bolderColor;
        return true;
    }


    public void setPadding(int left, int right, int top, int bottom) {
        this.paddingLeft = left;
        this.paddingRight = right;
        this.paddingTop = top;
        this.paddingBottom = bottom;
    }

    private boolean needDraw = true;

    public void setNeedDrawBolder(boolean needDraw) {
        this.needDraw = needDraw;
    }
}
