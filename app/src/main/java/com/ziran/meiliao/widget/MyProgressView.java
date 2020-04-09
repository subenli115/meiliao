package com.ziran.meiliao.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.commonutils.TimeUtil;
import com.ziran.meiliao.envet.OnProgressChangeListener;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/18 13:08
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/8/18$
 * @updateDes ${TODO}
 */

public class MyProgressView extends View implements OnProgressChangeListener {

    private String progressText, totalText;
    private int textColor, finishColor, unFinishColor, cacheColor;
    private int defTextColor = Color.parseColor("#999999");
    private int defFinishColor = Color.parseColor("#45B000");
    private int defUnFinishColor = Color.parseColor("#EAEAE9");
    private int defCacheColor = Color.parseColor("#cccccc");
    private float defTextSize;
    private float defLineWidth;
    private float textSize, lineWidth;
    private int max, progress, cacheProgress;
    private int textMagin, defTextMargin;
    private Drawable thumb;
    private Drawable touchThumb;
    private ShowTextStyle mShowTextStyle = ShowTextStyle.HHMMSS;
    private Paint mPaint;
    private boolean canTouchProgress = true;
    private int intrinsicHeight;
    private int mWidth, textWidth;
    private static final String TAG = "MyProgressView";
    private int startX, stopX, startY;
    private int totalWidth;
    private int minStartX, maxStopX;
    private int thumbX;
    private Paint.FontMetrics fontMetrics;

    public MyProgressView(Context context) {
        this(context, null);
    }

    public MyProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(textSize);
        mPaint.setColor(textColor);

    }

    public String getProgressText() {
        return progressText;
    }

    public void setProgressText(String progressText) {
        this.progressText = progressText;
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        defTextSize = TypedValue.applyDimension(2, 12, getResources().getDisplayMetrics());
        defLineWidth = TypedValue.applyDimension(1, 1, getResources().getDisplayMetrics());
        defTextMargin = (int) TypedValue.applyDimension(1, 12, getResources().getDisplayMetrics());
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyProgressView);
        progressText = typedArray.getString(R.styleable.MyProgressView_MyProgressView_progress_text);
        totalText = typedArray.getString(R.styleable.MyProgressView_MyProgressView_total_text);
        textColor = typedArray.getColor(R.styleable.MyProgressView_MyProgressView_textColor, defTextColor);
        finishColor = typedArray.getColor(R.styleable.MyProgressView_MyProgressView_finishColor, defFinishColor);
        unFinishColor = typedArray.getColor(R.styleable.MyProgressView_MyProgressView_unFinishColor, defUnFinishColor);
        cacheColor = typedArray.getColor(R.styleable.MyProgressView_MyProgressView_cacheColor, defCacheColor);
        textSize = typedArray.getDimension(R.styleable.MyProgressView_MyProgressView_textSize, defTextSize);
        canTouchProgress = typedArray.getBoolean(R.styleable.MyProgressView_MyProgressView_can_touch_progress, true);
        lineWidth = typedArray.getDimension(R.styleable.MyProgressView_MyProgressView_line_width, defLineWidth);
        textMagin = (int) typedArray.getDimension(R.styleable.MyProgressView_MyProgressView_text_margin, defTextMargin);
        max = typedArray.getInt(R.styleable.MyProgressView_MyProgressView_max, 100);
        progress = typedArray.getInt(R.styleable.MyProgressView_MyProgressView_progress, 0);
        int textStyle = typedArray.getInt(R.styleable.MyProgressView_MyProgressView_text_style, 1);
        if (textStyle == 0) {
            mShowTextStyle = ShowTextStyle.HHMMSS;
        } else {
            mShowTextStyle = ShowTextStyle.MMSS;
        }
        cacheProgress = typedArray.getInt(R.styleable.MyProgressView_MyProgressView_cache, 0);
        thumb = typedArray.getDrawable(R.styleable.MyProgressView_MyProgressView_Thumb);
        touchThumb = typedArray.getDrawable(R.styleable.MyProgressView_MyProgressView_touch_Thumb);
        if (thumb == null) {
            thumb = getResources().getDrawable(R.mipmap.ic_sjk_video_playing_dot);
        }
        if (touchThumb == null) {
            touchThumb = getResources().getDrawable(R.mipmap.ic_sjk_video_playing_dot_pre);
        }
        typedArray.recycle();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int resultWidth = measureSize(modeWidth, sizeWidth, (int) TypedValue.applyDimension(1, 500, getResources().getDisplayMetrics()));
        int resultHeight = measureSize(modeHeight, sizeHeight, (int) TypedValue.applyDimension(1, 30, getResources().getDisplayMetrics()));
        setMeasuredDimension(resultWidth, resultHeight);
    }

    private int measureSize(int mode, int sizeExpect, int sizeActual) {
        int realSize;
        if (mode == MeasureSpec.EXACTLY) {
            realSize = sizeExpect;
        } else {
            realSize = sizeActual;
            if (mode == MeasureSpec.AT_MOST) realSize = Math.min(realSize, sizeExpect);
        }
        return realSize;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawText(canvas);
        drawLine(canvas);

        if (isTouch) {
            if (touchThumb != null) {
                drawThumb(canvas, touchThumb);
            } else {
                drawThumb(canvas, thumb);
            }
        } else {
            drawThumb(canvas, thumb);
        }

    }


    private void drawThumb(Canvas canvas, Drawable drawable) {
        if (drawable == null || (progress == 0 && max == 0)) return;
        int intrinsicWidth = drawable.getIntrinsicWidth() / 2;
        intrinsicHeight = drawable.getIntrinsicHeight() / 2;
        drawable.setBounds(thumbX - intrinsicWidth, startY - intrinsicHeight, thumbX + intrinsicWidth, startY + intrinsicHeight);
        drawable.draw(canvas);
    }


    private void drawLine(Canvas canvas) {
        totalWidth = mWidth - textMagin * 2 - getPaddingRight() - getPaddingLeft() - textWidth * 2;
        mPaint.setStrokeWidth(lineWidth);
        minStartX = startX = textWidth + textMagin + getPaddingLeft();
        startY = getHeight() / 2;
        maxStopX = mWidth - getPaddingRight() - textMagin - textWidth;
        stopX = (int) (totalWidth * cacheProgress * 1f / max) + minStartX;

        if (progress == 0 && max == 0) { //如果没有设置max ,默认显示全部未完成
            mPaint.setColor(unFinishColor);
            canvas.drawLine(minStartX, startY, maxStopX, startY, mPaint);
            return;
        }
        int temp;
        //绘制缓存的进度
        drawTwoLine(canvas, cacheColor, stopX);
        temp = stopX;

        //绘制完成的进度
        thumbX = stopX = (int) (totalWidth * progress * 1f / max) + minStartX;
        drawTwoLine(canvas, finishColor, stopX);
        temp = temp > stopX ? temp : stopX;

        if (progress < max && cacheProgress < max) {
            //绘制未完成的颜色
            mPaint.setColor(unFinishColor);
            canvas.drawLine(temp, startY, maxStopX, startY, mPaint);
        }
    }

    private void drawTwoLine(Canvas canvas, int color, int stopX) {
        if (stopX > minStartX) {
            mPaint.setColor(color);
            canvas.drawLine(startX, startY, stopX, startY, mPaint);
        }
    }

    @Override
    public boolean onTrackballEvent(MotionEvent event) {
        return canTouchProgress || super.onTrackballEvent(event);
    }

    private boolean isTouch;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!canTouchProgress) return super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isTouch = true;
                checkUpdate(event.getX(), event.getY(), true);
                break;
            case MotionEvent.ACTION_MOVE:
                checkUpdate(event.getX(), event.getY(), true);
                break;
            case MotionEvent.ACTION_UP:
                checkUpdate(event.getX(), event.getY(), false);
                isTouch = false;
                if (mOnSeekStopListener != null) {
                    mOnSeekStopListener.onSeekStop(this, progress);
                }else if(mOnSeekStopListenerTwo!=null){
                    mOnSeekStopListenerTwo.onSeekStoptwo(this,progress,max);
                }else{
                    setSeekTo();

                }
                break;
        }
        return true;
    }

    private void checkUpdate(float downX, float downY, boolean needCallListener) {
        if (isTouch) {
            if (max==0) return;
            if (downX > minStartX - textMagin && downX < maxStopX + textMagin && downY > startY - intrinsicHeight * 4 && downY < startY +
                    intrinsicHeight * 4) {
                float dis = downX - minStartX;
                progress = (int) (dis * max / totalWidth);
                progress = progress < 0 ? 0 : progress;
                if (needCallListener && mOnSeekChangeListener != null) {
                    mOnSeekChangeListener.onSeekChange(this, progress);
                }
                invalidate();
            }
        }
    }


    private void drawText(Canvas canvas) {
        if (progress > max) progress = max;
        if (cacheProgress > max) cacheProgress = max;
        fontMetrics = mPaint.getFontMetrics();
        progressText = formatData(progress);
        totalText = formatData(max);
        mPaint.setColor(textColor);
        textWidth = (int) mPaint.measureText(progressText);
        int textY = (int) (getHeight() / 2 + (fontMetrics.descent - fontMetrics.ascent) / 2 - fontMetrics.bottom);
        canvas.drawText(progressText, getPaddingLeft(), textY, mPaint);
        canvas.drawText(totalText, mWidth - getPaddingRight() - textWidth, textY, mPaint);
    }

    public void setMax(String duration) {
        setMax(TimeUtil.getMax(duration));
    }

    public enum ShowTextStyle {
        HHMMSS, MMSS
    }

    public String formatData(int progress) {
        int min;
        int second = progress % 60; //多少秒
        int hour = progress / 3600 % 24; //多少小时
        if (mShowTextStyle == ShowTextStyle.HHMMSS) {
            min = progress / 60 % 60; //多少分
            return String.format("%02d:%02d:%02d", hour, min, second);
        } else {
            if(max>10000){
                min=progress/60/10;
            }
            min = progress / 60; //多少分
            return String.format("%02d:%02d", min, second);
        }
    }

    public void resProgress() {
        progress = 0;
        cacheProgress = 0;
        invalidate();
    }


    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        if (this.textColor != textColor) {
            this.textColor = textColor;
            invalidate();
        }
    }

    public int getFinishColor() {
        return finishColor;
    }

    public void setFinishColor(int finishColor) {
        if (this.finishColor != finishColor) {
            this.finishColor = finishColor;
            invalidate();
        }
    }

    public int getUnFinishColor() {
        return unFinishColor;
    }

    public void setUnFinishColor(int unFinishColor) {
        if (this.unFinishColor != unFinishColor) {
            this.unFinishColor = unFinishColor;
            invalidate();
        }
    }

    public int getCacheColor() {
        return cacheColor;
    }

    public void setCacheColor(int cacheColor) {
        if (this.cacheColor != cacheColor) {
            this.cacheColor = cacheColor;
            invalidate();
        }
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        if (this.textSize != textSize) {
            this.textSize = textSize;
            invalidate();
        }
    }

    public float getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(float lineWidth) {
        if (this.lineWidth != lineWidth) {
            this.lineWidth = lineWidth;
            invalidate();
        }
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        if (this.max != max) {
            this.max = max;
            invalidate();
        }
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        if (!isTouch && this.progress != progress) {
            this.progress = progress;
            invalidate();
        }
    }

    public void setProgressAndMax(int progress, int max) {
        this.progress = progress;
        this.max = max;
        invalidate();
    }

    public void setProgressAndCache(int progress, int cache) {
        this.progress = progress;
        this.cacheProgress = cache;
        invalidate();
    }

    public int getCacheProgress() {
        return cacheProgress;

    }

    public void setCacheProgress(int cacheProgress) {
        if (this.cacheProgress != cacheProgress) {
            this.cacheProgress = cacheProgress;
            invalidate();
        }
    }

    public int getTextMagin() {
        return textMagin;
    }

    public void setTextMagin(int textMagin) {
        if (this.textMagin != textMagin) {
            this.textMagin = textMagin;
            invalidate();
        }
    }

    public Drawable getThumb() {
        return thumb;
    }

    public void setThumb(Drawable thumb) {
        if (thumb != null && this.thumb != thumb) {
            this.thumb = thumb;
            invalidate();
        }
    }

    public Drawable getTouchThumb() {
        return touchThumb;
    }

    public void setTouchThumb(Drawable touchThumb) {
        if (touchThumb != null && this.touchThumb != touchThumb) {
            this.touchThumb = touchThumb;
            invalidate();
        }
    }

    public ShowTextStyle getShowTextStyle() {
        return mShowTextStyle;
    }

    public void setShowTextStyle(ShowTextStyle showTextStyle) {
        if (showTextStyle != null && this.mShowTextStyle != showTextStyle) {
            this.mShowTextStyle = showTextStyle;
            invalidate();
        }
    }

    public boolean isCanTouchProgress() {
        return canTouchProgress;
    }

    public void setCanTouchProgress(boolean canTouchProgress) {
        if (this.canTouchProgress != canTouchProgress) {
            this.canTouchProgress = canTouchProgress;
            invalidate();
        }
    }

    private OnSeekChangeListener mOnSeekChangeListener;
    private OnSeekStopListener mOnSeekStopListener;
    private OnSeekStopListenerTwo mOnSeekStopListenerTwo;

    public void setOnSeekChangeListener(OnSeekChangeListener OnSeekChangeListener) {
        mOnSeekChangeListener = OnSeekChangeListener;
    }

    public void setOnSeekStopListener(OnSeekStopListener onSeekStopListener) {
        mOnSeekStopListener = onSeekStopListener;
    }

    public void setOnSeekStopListenerTwo(OnSeekStopListenerTwo onSeekStopListener) {
        mOnSeekStopListenerTwo = onSeekStopListener;
    }

    public interface OnSeekChangeListener {
        void onSeekChange(MyProgressView myProgressView, int progress);
    }

    public interface OnSeekStopListener {
        void onSeekStop(MyProgressView myProgressView, int progress);
    }
    public interface OnSeekStopListenerTwo {
        void onSeekStoptwo(MyProgressView myProgressView, int progress,int max);
    }
    private boolean isAutoSeek = true;

    public void setAutoSeek(boolean autoSeek) {
        this.isAutoSeek = autoSeek;
    }


    /**
     * 当换另一个APP时,需要更换里面的调用方法
     */
    public void setSeekTo() {
        if (isAutoSeek) {
            int duration = MyAPP.mServiceManager.duration();
            int i = (int) (duration * getProgressRatio());
            LogUtils.logd("duration:"+duration + " progress:"+progress + " max:"+max  + " progress:"+i);
            MyAPP.mServiceManager.seekTo(progress*1000);
        }
    }

    public float getProgressRatio(){
        return progress*1f/max;
    }
}
