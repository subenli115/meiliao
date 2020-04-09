package com.ziran.meiliao.ui.settings.widget;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/3 14:00
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/3$
 * @updateDes ${TODO}
 */


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.DisplayUtil;

/**
 * Created by bruce on 14-10-30.
 */
public class DonutProgress extends View {
    private Paint finishedPaint;
    private Paint unfinishedPaint;
    private Paint innerCirclePaint;

    protected Paint textPaint;
    protected Paint innerBottomTextPaint;

    private RectF finishedOuterRect = new RectF();
    private RectF unfinishedOuterRect = new RectF();

    private int attributeResourceId = 0;
    private boolean showText;
    private float textSize;
    private int textColor;
    private int innerBottomTextColor;
    private float progress = 0;
    private int max;
    private int finishedStrokeColor;
    private int unfinishedStrokeColor;
    private int startingDegree;
    private float finishedStrokeWidth;
    private float unfinishedStrokeWidth;
    private int innerBackgroundColor;
    private String prefixText = "";
    private String suffixText = "%";
    private String text = null;
    private float innerBottomTextSize;
    private String innerBottomText;
    private float innerBottomTextHeight;
    public static final int TEXT_MODE_BFB = 0;
    public static final int TEXT_MODE_TIME = 1;
    private int showTextMode;
    private final float default_stroke_width;
    private int default_finished_color;
    private int default_unfinished_color;
    private int default_text_color;
    private final int default_inner_bottom_text_color = Color.rgb(66, 145, 241);
    private final int default_inner_background_color = Color.TRANSPARENT;
    private final int default_max = 100;
    private final int default_startingDegree = 270;
    private final float default_text_size;
    private final float default_inner_bottom_text_size;
    private final int min_size;
    private Bitmap backgroudBitmap;

    private static final String INSTANCE_STATE = "saved_instance";
    private static final String INSTANCE_TEXT_COLOR = "text_color";
    private static final String INSTANCE_TEXT_SIZE = "text_size";
    private static final String INSTANCE_TEXT = "text";
    private static final String INSTANCE_INNER_BOTTOM_TEXT_SIZE = "inner_bottom_text_size";
    private static final String INSTANCE_INNER_BOTTOM_TEXT = "inner_bottom_text";
    private static final String INSTANCE_INNER_BOTTOM_TEXT_COLOR = "inner_bottom_text_color";
    private static final String INSTANCE_FINISHED_STROKE_COLOR = "finished_stroke_color";
    private static final String INSTANCE_UNFINISHED_STROKE_COLOR = "unfinished_stroke_color";
    private static final String INSTANCE_MAX = "max";
    private static final String INSTANCE_PROGRESS = "progress";
    private static final String INSTANCE_SUFFIX = "suffix";
    private static final String INSTANCE_PREFIX = "prefix";
    private static final String INSTANCE_FINISHED_STROKE_WIDTH = "finished_stroke_width";
    private static final String INSTANCE_UNFINISHED_STROKE_WIDTH = "unfinished_stroke_width";
    private static final String INSTANCE_BACKGROUND_COLOR = "inner_background_color";
    private static final String INSTANCE_STARTING_DEGREE = "starting_degree";
    private static final String INSTANCE_INNER_DRAWABLE = "inner_drawable";
    private boolean downOnArc;
    private float paddingOuterThumb;

    public DonutProgress(Context context) {
        this(context, null);
    }

    public DonutProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DonutProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        default_text_size = DisplayUtil.sp2px(getResources(), 12);
        min_size = (int) DisplayUtil.dp2px(getResources(), 100);
        default_stroke_width = DisplayUtil.dp2px(getResources(), 1);
        default_inner_bottom_text_size = DisplayUtil.sp2px(getResources(), 18);
        default_finished_color = getResources().getColor(R.color.textColor_teshe);
        default_unfinished_color = getResources().getColor(R.color.textColor_999);
        default_text_color = getResources().getColor(R.color.textColor_teshe);
        final TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.DonutProgress, defStyleAttr, 0);
        initByAttributes(attributes);
        attributes.recycle();

        initPainters();
    }

    protected void initPainters() {
        if (showText) {
            textPaint = new TextPaint();
            textPaint.setColor(textColor);
            textPaint.setTextSize(textSize);
            textPaint.setAntiAlias(true);

            innerBottomTextPaint = new TextPaint();
            innerBottomTextPaint.setColor(innerBottomTextColor);
            innerBottomTextPaint.setTextSize(innerBottomTextSize);
            innerBottomTextPaint.setAntiAlias(true);
        }

        finishedPaint = new Paint();
        finishedPaint.setColor(finishedStrokeColor);
        finishedPaint.setStyle(Paint.Style.STROKE);
        finishedPaint.setAntiAlias(true);
        finishedPaint.setStrokeWidth(finishedStrokeWidth);

        unfinishedPaint = new Paint();
        unfinishedPaint.setColor(unfinishedStrokeColor);
        unfinishedPaint.setStyle(Paint.Style.STROKE);
        unfinishedPaint.setAntiAlias(true);
        unfinishedPaint.setStrokeWidth(unfinishedStrokeWidth);

        innerCirclePaint = new Paint();
        innerCirclePaint.setColor(innerBackgroundColor);
        innerCirclePaint.setAntiAlias(true);
    }

    protected void initByAttributes(TypedArray attributes) {

        finishedStrokeColor = attributes.getColor(R.styleable.DonutProgress_donut_finished_color, default_finished_color);
        unfinishedStrokeColor = attributes.getColor(R.styleable.DonutProgress_donut_unfinished_color, default_unfinished_color);
        showText = attributes.getBoolean(R.styleable.DonutProgress_donut_show_text, true);
        attributeResourceId = attributes.getResourceId(R.styleable.DonutProgress_donut_inner_drawable, 0);

        setMax(attributes.getInt(R.styleable.DonutProgress_donut_max, default_max));
        setProgress(attributes.getFloat(R.styleable.DonutProgress_donut_progress, 0));
        finishedStrokeWidth = attributes.getDimension(R.styleable.DonutProgress_donut_finished_stroke_width, default_stroke_width);
        unfinishedStrokeWidth = attributes.getDimension(R.styleable.DonutProgress_donut_unfinished_stroke_width, default_stroke_width);

        if (showText) {
            if (attributes.getString(R.styleable.DonutProgress_donut_prefix_text) != null) {
                prefixText = attributes.getString(R.styleable.DonutProgress_donut_prefix_text);
            }
            if (attributes.getString(R.styleable.DonutProgress_donut_suffix_text) != null) {
                suffixText = attributes.getString(R.styleable.DonutProgress_donut_suffix_text);
            }
            if (attributes.getString(R.styleable.DonutProgress_donut_text) != null) {
                text = attributes.getString(R.styleable.DonutProgress_donut_text);
            }

            textColor = attributes.getColor(R.styleable.DonutProgress_donut_text_color, default_text_color);
            textSize = attributes.getDimension(R.styleable.DonutProgress_donut_text_size, default_text_size);
            timeTextSize = textSize;
            innerBottomTextSize = attributes.getDimension(R.styleable.DonutProgress_donut_inner_bottom_text_size,
                    default_inner_bottom_text_size);
            innerBottomTextColor = attributes.getColor(R.styleable.DonutProgress_donut_inner_bottom_text_color,
                    default_inner_bottom_text_color);
            innerBottomText = attributes.getString(R.styleable.DonutProgress_donut_inner_bottom_text);
        }

        innerBottomTextSize = attributes.getDimension(R.styleable.DonutProgress_donut_inner_bottom_text_size,
                default_inner_bottom_text_size);
        innerBottomTextColor = attributes.getColor(R.styleable.DonutProgress_donut_inner_bottom_text_color,
                default_inner_bottom_text_color);
        innerBottomText = attributes.getString(R.styleable.DonutProgress_donut_inner_bottom_text);

        startingDegree = attributes.getInt(R.styleable.DonutProgress_donut_circle_starting_degree, default_startingDegree);
        showTextMode = attributes.getInt(R.styleable.DonutProgress_donut_text_mode, TEXT_MODE_BFB);
        innerBackgroundColor = attributes.getColor(R.styleable.DonutProgress_donut_background_color, default_inner_background_color);
        Drawable drawable = attributes.getDrawable(R.styleable.DonutProgress_donut_backgroud);
        if (drawable != null && drawable instanceof BitmapDrawable) {
            backgroudBitmap = ((BitmapDrawable) drawable).getBitmap();
        }

        mThumb = getResources().getDrawable(R.mipmap.ic_progress_end_dot);// 圆点图片
        mDragThumb = getResources().getDrawable(R.mipmap.ic_progress_end_dot_max);// 圆点图片

        paddingOuterThumb = mDragThumb.getIntrinsicHeight() / 2 / 2;
        // 加载拖动图标
        int thumbHalfheight = mThumb.getIntrinsicHeight() / 2;
        int thumbHalfWidth = mThumb.getIntrinsicWidth() / 2;
        mThumb.setBounds(-thumbHalfWidth, -thumbHalfheight, thumbHalfWidth, thumbHalfheight);
        int halfWid = mDragThumb.getIntrinsicWidth() / 2;
        int halfHei = mDragThumb.getIntrinsicHeight() / 2;
        mDragThumb.setBounds(-halfWid, -halfHei, halfWid, halfHei);
    }

    @Override
    public void invalidate() {
        initPainters();
        super.invalidate();
    }

    public boolean isShowText() {
        return showText;
    }

    public void setShowText(boolean showText) {
        if (this.showText != showText) {
            this.showText = showText;
            this.invalidate();
        }
    }

    public float getFinishedStrokeWidth() {
        return finishedStrokeWidth;
    }

    public void setFinishedStrokeWidth(float finishedStrokeWidth) {
        this.finishedStrokeWidth = finishedStrokeWidth;
        this.invalidate();
    }

    public float getUnfinishedStrokeWidth() {
        return unfinishedStrokeWidth;
    }

    public void setUnfinishedStrokeWidth(float unfinishedStrokeWidth) {
        this.unfinishedStrokeWidth = unfinishedStrokeWidth;
        this.invalidate();
    }

    private float getProgressAngle() {
        return getProgress() / (float) max * 360f;
    }

    public float getProgress() {
        return progress;
    }


    public void setProgress(float progress) {
        if (!downOnArc) {
            this.progress = progress;
            if (this.progress > getMax()) {
                this.progress %= getMax();
            }
            invalidate();
//            postInvalidate();
        }
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        if (max > 0) {
            this.max = max;
            invalidate();
        }
    }


    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
        this.invalidate();
    }

    private boolean isShowAllow = false;

    public boolean isShowAllow() {
        return isShowAllow;
    }

    public void setShowAllow(boolean showAllow) {
        isShowAllow = showAllow;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
        this.invalidate();
    }

    public int getFinishedStrokeColor() {
        return finishedStrokeColor;
    }

    public void setFinishedStrokeColor(int finishedStrokeColor) {
        this.finishedStrokeColor = finishedStrokeColor;
        this.invalidate();
    }

    public int getUnfinishedStrokeColor() {
        return unfinishedStrokeColor;
    }

    public void setUnfinishedStrokeColor(int unfinishedStrokeColor) {
        this.unfinishedStrokeColor = unfinishedStrokeColor;
        this.invalidate();
    }

    private String courseText;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        this.invalidate();
    }

    public void setCourseText(String text) {
        this.courseText = text;
        this.invalidate();
    }

    public String getSuffixText() {
        return suffixText;
    }

    public void setSuffixText(String suffixText) {
        this.suffixText = suffixText;
        this.invalidate();
    }

    public String getPrefixText() {
        return prefixText;
    }

    public void setPrefixText(String prefixText) {
        this.prefixText = prefixText;
        this.invalidate();
    }

    public int getInnerBackgroundColor() {
        return innerBackgroundColor;
    }

    public void setInnerBackgroundColor(int innerBackgroundColor) {
        this.innerBackgroundColor = innerBackgroundColor;
        this.invalidate();
    }

    public void setBackgroundBitmap(Drawable drawable) {
        if (drawable == null || !(drawable instanceof BitmapDrawable)) return;
        backgroudBitmap = ((BitmapDrawable) drawable).getBitmap();
        this.invalidate();
    }

    public String getInnerBottomText() {
        return innerBottomText;
    }

    public void setInnerBottomText(String innerBottomText) {
        this.innerBottomText = innerBottomText;
        this.invalidate();
    }


    public float getInnerBottomTextSize() {
        return innerBottomTextSize;
    }

    public void setInnerBottomTextSize(float innerBottomTextSize) {
        this.innerBottomTextSize = innerBottomTextSize;
        this.invalidate();
    }

    public int getInnerBottomTextColor() {
        return innerBottomTextColor;
    }

    public void setInnerBottomTextColor(int innerBottomTextColor) {
        this.innerBottomTextColor = innerBottomTextColor;
        this.invalidate();
    }

    public int getStartingDegree() {
        return startingDegree;
    }

    public void setStartingDegree(int startingDegree) {
        this.startingDegree = startingDegree;
        this.invalidate();
    }

    public int getAttributeResourceId() {
        return attributeResourceId;
    }

    public void setAttributeResourceId(int attributeResourceId) {
        this.attributeResourceId = attributeResourceId;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measure(widthMeasureSpec), measure(heightMeasureSpec));

        //TODO calculate inner circle height and then position bottom text at the bottom (3/4)
        innerBottomTextHeight = getHeight() - (getHeight() * 3) / 4;
    }

    private int measure(int measureSpec) {
        int result;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = min_size;
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }
        return result;
    }

    private int centerX, centerY;
    private int radius;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        try {
            centerX = w / 2;
            centerY = h / 2;
            int minCenter = Math.min(centerX, centerY);
            float delta = Math.max(finishedStrokeWidth, unfinishedStrokeWidth);
            // 进度的宽度                        // 进度阴影的宽度      //动画的宽度
            radius = (int) (minCenter - delta / 2 - mThumb.getBounds().width() / 2 / 2); //圆环的半径


            minValidateTouchArcRadius = (int) (radius - paddingOuterThumb * 1.5f);
            maxValidateTouchArcRadius = (int) (radius + paddingOuterThumb * 1.5f);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static final String TAG = "DonutProgress";

    @Override
    protected void onDraw(Canvas canvas) {

        float delta = Math.max(finishedStrokeWidth, unfinishedStrokeWidth);
        finishedOuterRect.set(delta, delta, getWidth() - delta, getHeight() - delta);
        unfinishedOuterRect.set(delta, delta, getWidth() - delta, getHeight() - delta);

        float innerCircleRadius = (getWidth() - Math.min(finishedStrokeWidth, unfinishedStrokeWidth) + Math.abs(finishedStrokeWidth -
                unfinishedStrokeWidth)) / 2f;
        if (backgroudBitmap != null) {
            int temp = (int) Math.max(finishedStrokeWidth, unfinishedStrokeWidth);
            Rect rect = new Rect();
            rect.left = temp;
            rect.top = temp;
            rect.right = getWidth() - temp;
            rect.bottom = getHeight() - temp;
            canvas.drawBitmap(backgroudBitmap, null, rect, null);
        } else {
            canvas.drawCircle(getWidth() / 2.0f, getHeight() / 2.0f, innerCircleRadius, innerCirclePaint);
        }
        canvas.drawArc(finishedOuterRect, getStartingDegree(), getProgressAngle(), false, finishedPaint);
        canvas.drawArc(unfinishedOuterRect, getStartingDegree() + getProgressAngle(), 360 - getProgressAngle(), false, unfinishedPaint);

        if (showText) {
            String text;
            if (TextUtils.isEmpty(courseText)) {
                textPaint.setTextSize(timeTextSize);
                if (showTextMode == TEXT_MODE_TIME) {
                    int hour = (int) (progress / 60);
                    int mic = (int) (progress % 60);
                    text = (hour < 10 ? "0" + hour : hour) + " : " + ((mic < 10 ? "0" + mic : mic));
                    int hour1 = (max / 60);
                    int mic1 = (max % 60);
                    text = text + "/" + (hour1 < 10 ? "0" + hour1 : hour1) + " : " + ((mic1 < 10 ? "0" + mic1 : mic1));
                } else {
                    text = this.text != null ? this.text : prefixText + (int) progress + suffixText;
                }
            } else {
                textPaint.setTextSize(textSize);
                text = courseText;
            }
            if (!TextUtils.isEmpty(text)) {
                float textHeight = textPaint.descent() + textPaint.ascent();
                canvas.drawText(text, (getWidth() - textPaint.measureText(text)) / 2.0f, (getWidth() - textHeight) / 2.0f, textPaint);
            }

            if (!TextUtils.isEmpty(getInnerBottomText())) {
                innerBottomTextPaint.setTextSize(innerBottomTextSize);
                float bottomTextBaseline = getHeight() - innerBottomTextHeight - (textPaint.descent() + textPaint.ascent()) / 2;
                canvas.drawText(getInnerBottomText(), (getWidth() - innerBottomTextPaint.measureText(getInnerBottomText())) / 2.0f,
                        bottomTextBaseline, innerBottomTextPaint);
            }
        }

        if (attributeResourceId != 0) {
            @SuppressLint("DrawAllocation") Bitmap bitmap = BitmapFactory.decodeResource(getResources(), attributeResourceId);
            canvas.drawBitmap(bitmap, (getWidth() - bitmap.getWidth()) / 2.0f, (getHeight() - bitmap.getHeight()) / 2.0f, null);
        }
        if (isShowAllow) {
            PointF progressPoint = ChartUtil.calcArcEndPointXY(centerX, centerY, radius, 360 * progress / max, 270);
            // 画Thumb
            canvas.save();
            canvas.translate(progressPoint.x, progressPoint.y);
            if (downOnArc) {
                mDragThumb.draw(canvas);
            } else {
                mThumb.draw(canvas);
            }
            canvas.restore();
        }
    }


    // 计算某点到圆点的距离
    private double getTouchRadius(int x, int y) {
        int cx = x - getWidth() / 2;
        int cy = y - getHeight() / 2;
        return Math.hypot(cx, cy);
    }

    private float timeTextSize;
    private int minValidateTouchArcRadius; // 最小有效点击半径
    private int maxValidateTouchArcRadius; // 最大有效点击半径


    public void setTimeTextSize(float size) {
        if (size > 0) {
            timeTextSize = size;
        } else {
            timeTextSize = textSize - DisplayUtil.sp2px(6f);
        }
    }

    // 判断是否按在圆边上
    private boolean isTouchArc(int x, int y) {
        double d = getTouchRadius(x, y);
        if (d >= minValidateTouchArcRadius && d <= maxValidateTouchArcRadius) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isShowAllow()) return super.onTouchEvent(event);
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (isTouchArc(x, y)) {
                    downOnArc = true;
                    if (changeListener != null) {
                        changeListener.onProgressStart();
                    }
                    updateArc(x, y);
                    return true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (downOnArc) {
                    updateArc(x, y);
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                downOnArc = false;
                invalidate();
                if (changeListener != null) {
                    changeListener.onProgressChangeEnd(max, (int) progress);
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    private OnProgressChangeListener changeListener;

    public void setChangeListener(OnProgressChangeListener changeListener) {
        this.changeListener = changeListener;
    }

    private int countTime;

    public void startCountTime(final int i) { //3
        this.countTime = i;
        final int space = 200;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (countTime == 0) {
                    return;
                }
                countTime -= space;
                int i1 = (i - countTime) * 100 / i;
                setProgress(i1);
                postDelayed(this, space);
            }
        };
        postDelayed(runnable, space);
    }

    public interface OnProgressChangeListener {

        void onProgressStart();

        void onProgressChange(int duration, int progress, boolean isSetTime);

        void onProgressChangeEnd(int duration, int progress);
    }

    // 根据点的位置，更新进度
    private void updateArc(int x, int y) {
        int cx = x - getWidth() / 2;
        int cy = y - getHeight() / 2;
        // 计算角度，得出（-1->1）之间的数据，等同于（-180°->180°）
        double angle = Math.atan2(cy, cx) / Math.PI;
        // 将角度转换成（0->2）之间的值，然后加上90°的偏移量
        angle = ((2 + angle) % 2 + (90 / 180f)) % 2;
        // 用（0->2）之间的角度值乘以总进度，等于当前进度
        progress = (int) (angle * max / 2);
        if (changeListener != null) {
            changeListener.onProgressChange(max, (int) progress, true);
        }
        invalidate();
    }

    /**
     *
     */
    private Drawable mThumb;
    private Drawable mDragThumb;

    @Override
    protected Parcelable onSaveInstanceState() {
        final Bundle bundle = new Bundle();
        bundle.putParcelable(INSTANCE_STATE, super.onSaveInstanceState());
        bundle.putInt(INSTANCE_TEXT_COLOR, getTextColor());
        bundle.putFloat(INSTANCE_TEXT_SIZE, getTextSize());
        bundle.putFloat(INSTANCE_INNER_BOTTOM_TEXT_SIZE, getInnerBottomTextSize());
        bundle.putFloat(INSTANCE_INNER_BOTTOM_TEXT_COLOR, getInnerBottomTextColor());
        bundle.putString(INSTANCE_INNER_BOTTOM_TEXT, getInnerBottomText());
        bundle.putInt(INSTANCE_INNER_BOTTOM_TEXT_COLOR, getInnerBottomTextColor());
        bundle.putInt(INSTANCE_FINISHED_STROKE_COLOR, getFinishedStrokeColor());
        bundle.putInt(INSTANCE_UNFINISHED_STROKE_COLOR, getUnfinishedStrokeColor());
        bundle.putInt(INSTANCE_MAX, getMax());
        bundle.putInt(INSTANCE_STARTING_DEGREE, getStartingDegree());
        bundle.putFloat(INSTANCE_PROGRESS, getProgress());
        bundle.putString(INSTANCE_SUFFIX, getSuffixText());
        bundle.putString(INSTANCE_PREFIX, getPrefixText());
        bundle.putString(INSTANCE_TEXT, getText());
        bundle.putFloat(INSTANCE_FINISHED_STROKE_WIDTH, getFinishedStrokeWidth());
        bundle.putFloat(INSTANCE_UNFINISHED_STROKE_WIDTH, getUnfinishedStrokeWidth());
        bundle.putInt(INSTANCE_BACKGROUND_COLOR, getInnerBackgroundColor());
        bundle.putInt(INSTANCE_INNER_DRAWABLE, getAttributeResourceId());
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            final Bundle bundle = (Bundle) state;
            textColor = bundle.getInt(INSTANCE_TEXT_COLOR);
            textSize = bundle.getFloat(INSTANCE_TEXT_SIZE);
            innerBottomTextSize = bundle.getFloat(INSTANCE_INNER_BOTTOM_TEXT_SIZE);
            innerBottomText = bundle.getString(INSTANCE_INNER_BOTTOM_TEXT);
            innerBottomTextColor = bundle.getInt(INSTANCE_INNER_BOTTOM_TEXT_COLOR);
            finishedStrokeColor = bundle.getInt(INSTANCE_FINISHED_STROKE_COLOR);
            unfinishedStrokeColor = bundle.getInt(INSTANCE_UNFINISHED_STROKE_COLOR);
            finishedStrokeWidth = bundle.getFloat(INSTANCE_FINISHED_STROKE_WIDTH);
            unfinishedStrokeWidth = bundle.getFloat(INSTANCE_UNFINISHED_STROKE_WIDTH);
            innerBackgroundColor = bundle.getInt(INSTANCE_BACKGROUND_COLOR);
            attributeResourceId = bundle.getInt(INSTANCE_INNER_DRAWABLE);
            initPainters();
            setMax(bundle.getInt(INSTANCE_MAX));
            setStartingDegree(bundle.getInt(INSTANCE_STARTING_DEGREE));
            setProgress(bundle.getFloat(INSTANCE_PROGRESS));
            prefixText = bundle.getString(INSTANCE_PREFIX);
            suffixText = bundle.getString(INSTANCE_SUFFIX);
            text = bundle.getString(INSTANCE_TEXT);
            super.onRestoreInstanceState(bundle.getParcelable(INSTANCE_STATE));
            return;
        }
        super.onRestoreInstanceState(state);
    }

    public void setDonut_progress(String percent) {
        if (!TextUtils.isEmpty(percent)) {
            setProgress(Integer.parseInt(percent));
        }
    }


    public static class ChartUtil {

        /**
         * 依圆心坐标，半径，扇形角度，计算出扇形终射线与圆弧交叉点的xy坐标
         *
         * @param cirX
         * @param cirY
         * @param radius
         * @param cirAngle
         * @return
         */
        public static PointF calcArcEndPointXY(float cirX, float cirY, float radius, float cirAngle) {
            float posX = 0.0f;
            float posY = 0.0f;
            //将角度转换为弧度
            float arcAngle = (float) (Math.PI * cirAngle / 180.0);
            if (cirAngle < 90) {
                posX = cirX + (float) (Math.cos(arcAngle)) * radius;
                posY = cirY + (float) (Math.sin(arcAngle)) * radius;
            } else if (cirAngle == 90) {
                posX = cirX;
                posY = cirY + radius;
            } else if (cirAngle > 90 && cirAngle < 180) {
                arcAngle = (float) (Math.PI * (180 - cirAngle) / 180.0);
                posX = cirX - (float) (Math.cos(arcAngle)) * radius;
                posY = cirY + (float) (Math.sin(arcAngle)) * radius;
            } else if (cirAngle == 180) {
                posX = cirX - radius;
                posY = cirY;
            } else if (cirAngle > 180 && cirAngle < 270) {
                arcAngle = (float) (Math.PI * (cirAngle - 180) / 180.0);
                posX = cirX - (float) (Math.cos(arcAngle)) * radius;
                posY = cirY - (float) (Math.sin(arcAngle)) * radius;
            } else if (cirAngle == 270) {
                posX = cirX;
                posY = cirY - radius;
            } else {
                arcAngle = (float) (Math.PI * (360 - cirAngle) / 180.0);
                posX = cirX + (float) (Math.cos(arcAngle)) * radius;
                posY = cirY - (float) (Math.sin(arcAngle)) * radius;
            }
            return new PointF(posX, posY);
        }

        public static PointF calcArcEndPointXY(float cirX, float cirY, float radius, float cirAngle, float orginAngle) {
            cirAngle = (orginAngle + cirAngle) % 360;
            return calcArcEndPointXY(cirX, cirY, radius, cirAngle);
        }
    }


}
