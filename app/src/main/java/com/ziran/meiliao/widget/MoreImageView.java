package com.ziran.meiliao.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.ziran.meiliao.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/5/11 17:35
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/5/11$
 * @updateDes ${TODO}
 */

public class MoreImageView extends View {

    private List<Bitmap> mBitmaps;
    private Rect[] mRects;
    private int mWidth, mHeight;
    private int itemWidth, itemHeight;
    private int count;
    private Paint alphaPaint;
    private int currentPosition = -1;
    private int defalutData = -1;
    private int[] resourseIcon;

    public MoreImageView(Context context) {
        this(context, null);
    }

    public MoreImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MoreImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MoreImageView);
        itemWidth = typedArray.getDimensionPixelSize(R.styleable.MoreImageView_moreImageView_itemWidth, 60);
        itemHeight = typedArray.getDimensionPixelSize(R.styleable.MoreImageView_moreImageView_itemHeight, 60);
        count = typedArray.getInteger(R.styleable.MoreImageView_moreImageView_count, 5);
        defalutData = typedArray.getInt(R.styleable.MoreImageView_moreImageView_default_data, -1);
        typedArray.recycle();
        mBitmaps = new ArrayList<>();
        mRects = new Rect[2];
        // 建立Paint 物件
        alphaPaint = new Paint();
        alphaPaint.setStyle(Paint.Style.STROKE);   //空心
        alphaPaint.setAlpha(165);   //

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getDefaultSize(itemWidth * 2 + 60 * 4, widthMeasureSpec);
        int height = getDefaultSize(itemHeight + 60, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        if (userSet) {
            initRects();
        } else {
            resourseIcon = ResUtil.createDefData(2);
            setResourses(resourseIcon);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mBitmaps == null || mBitmaps.size() == 0) return;
        for (int i = 0; i < mRects.length; i++) {
            drawBitmap(canvas, i);
        }
    }

    private static final String TAG = "MoreImageView";

    private void drawBitmap(Canvas canvas, int index) {
        if (currentPosition == index) {
            canvas.drawBitmap(mBitmaps.get(index), mRects[index].left, mRects[index].top, alphaPaint);  //有透明
        } else {
            canvas.drawBitmap(mBitmaps.get(index), mRects[index].left, mRects[index].top, null);  //无透明
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (checkInPosition(event) != -1) {
                    invalidate();
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                if (checkInPosition(event) != -1) {
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                if (checkInPosition(event) != -1) {
                    if (mOnMoreImageViewClickListener != null) {
                        mOnMoreImageViewClickListener.onClick(currentPosition);
                    }
                }
                currentPosition = -1;
                invalidate();
                return true;
        }
        return super.onTouchEvent(event);
    }

    private OnMoreImageViewClickListener mOnMoreImageViewClickListener;

    public void setOnMoreImageViewClickListener(OnMoreImageViewClickListener onMoreImageViewClickListener) {
        mOnMoreImageViewClickListener = onMoreImageViewClickListener;
    }

    public interface OnMoreImageViewClickListener {
        void onClick(int index);
    }

    private int checkInPosition(MotionEvent event) {
        float rawX = event.getX();
        float rawY = event.getY();
        int tempMargin = Math.max(getPaddingLeft() / 2, getPaddingRight() / 2);
        for (int i = 0; i < mRects.length; i++) {
            if (mRects[i].left - tempMargin < rawX && mRects[i].right + tempMargin > rawX && mRects[i].top - tempMargin < rawY &&
                    mRects[i].bottom + tempMargin > rawY) {
                currentPosition = i;
                return i;
            }
        }
        return -1;
    }

    private boolean userSet;

    public void setResourses(int[] res) {
        if (res == null || res.length == 0) return;
        if (res.length < 2 || res.length > 8) {
            throw new IllegalStateException("传参错误");
        }
        userSet = true;
        mBitmaps.clear();
        mRects = new Rect[res.length];
        for (int i = 0; i < res.length; i++) {
            mBitmaps.add(BitmapFactory.decodeResource(getResources(), res[i]));
        }
        if (mWidth > 0) {
            initRects();
            invalidate();
        }
    }

    private void setRect(int index, int left, int top, int right, int bottom) {
        mRects[index] = new Rect(left, top, right, bottom);
    }

    private void initRects() {
        int size = mBitmaps.size();
        int itemHeight = mBitmaps.get(0).getHeight();
        int itemWidth = mBitmaps.get(0).getHeight();
        int top = mHeight / 2 - itemHeight / 2;
        int bottom = top + itemHeight;
        setRect(size - 1, mWidth - getPaddingRight() - 10 - itemWidth, top, mWidth - getPaddingRight(), bottom);//右边
        int totalWidth = mWidth - getPaddingRight() - itemWidth;
        int aveWidth = totalWidth / (size - 1);
        for (int i = 0; i < size - 1; i++) {
            int left = aveWidth * i + getPaddingLeft();
            setRect(i, left, top, left + itemWidth, bottom);//右边
        }
    }


    public static class ResUtil {
        public static int[] createDefData(int type) {
            int[] res = null;
            if (type == 2) {
                res = new int[]{ R.mipmap.living_share_wechat,R.mipmap.living_share_wechatzone
                        };
            } else if (type == 3) {
                res = new int[]{R.mipmap.share_wechat_white, R.mipmap.share_wechatzoom_white, R.mipmap.share_weibo_white, R.mipmap
                        .share_qq_white, R.mipmap.share_qqzoom_white};
            } else if (type == 1) {
                res = new int[]{R.mipmap.living_share_wechat, R.mipmap.living_share_wechatzone, R.mipmap.living_share_weibo, R.mipmap
                        .living_share_qq, R.mipmap.living_share_qzoon};
            }else if (type == 4){
                res = new int[]{R.mipmap.living_share_wechat, R.mipmap.living_share_wechatzone, R.mipmap.living_share_weibo, R.mipmap
                        .living_share_qq};
            }
            return res;
        }
    }
}
