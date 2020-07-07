package com.ziran.meiliao.common.commonwidget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatTextView;

import com.ziran.meiliao.common.R;
import com.ziran.meiliao.common.commonutils.DisplayUtil;
import com.ziran.meiliao.common.commonwidget.helper.BackgroundHelper;

/**
 * @author LinJ
 * @ClassName: ThumbnailView
 * @Description: 点击时显示明暗变化(滤镜效果)的ImageView
 * @date 2015-1-6 下午2:13:46
 */
public class FilterTextView extends AppCompatTextView {


    public FilterTextView(Context context) {
        this(context, null);
    }

    public FilterTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        // TODO Auto-generated constructor stub
    }

    public FilterTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initAttrs(context, attrs);
    }

    private BackgroundHelper mBackgroundHelper;
    private void initAttrs(Context context, AttributeSet attrs) {
        mBackgroundHelper = new BackgroundHelper(this);
        if (getBackground()!=null){
            return;
        }

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FilterTextView);
        int defaultClickColor = Color.parseColor("#d9d9d9");
        int bgColor = 0, bgClickColor = 0;
        if (a.hasValue(R.styleable.FilterTextView_cs_bg_style)) {
            int bgStyle = a.getInt(R.styleable.FilterTextView_cs_bg_style, 0);
            switch (bgStyle) {
                case 0:
                    bgClickColor = defaultClickColor;
                    break;
                case 1:
                    bgColor = Color.WHITE;
                    bgClickColor = defaultClickColor;
                    break;
                case 2:
                    bgColor = setDefault(R.color.textColor_teshe);
                    break;
                case 3:
                    bgColor = setDefault(R.color.textColor_teshe5);
                    break;
            }
        } else {
            bgColor = a.getColor(R.styleable.FilterTextView_cs_normal_bg_color, Color.TRANSPARENT);
            bgClickColor = a.getColor(R.styleable.FilterTextView_cs_fitter_bg_color, Color.TRANSPARENT);
        }
        int bgCircleAngle = a.getDimensionPixelOffset(R.styleable.FilterTextView_cs_bg_radius, 0);
        int borderWidth = a.getDimensionPixelOffset(R.styleable.FilterTextView_cs_border_width, 0);
        int borderColor = a.getColor(R.styleable.FilterTextView_cs_border_color, Color.TRANSPARENT); //边框默认为透明色 0
        int borderStyle = a.getInteger(R.styleable.FilterTextView_cs_border_style,-1); //边框默认为透明色 0
//        int pressTextColor = a.getColor(R.styleable.FilterTextView_csv_pressTextColor, Color.TRANSPARENT); //透明色值为 0
        a.recycle();
        //可选
        mBackgroundHelper.init(bgColor, bgCircleAngle, bgClickColor, borderWidth, borderColor);
        mBackgroundHelper.setBorderStyle(borderStyle);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        Drawable drawable = getBackground();
        if (drawable!=null){
            drawable.mutate().setAlpha(enabled?255:130);
        }
    }

    private int setDefault(int color) {
        setGravity(Gravity.CENTER);
        setTextColor(Color.WHITE);
        int padding = DisplayUtil.dip2px(getResources(),12);
        setPadding(padding,0,padding,0);
        return getResources().getColor(color);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mBackgroundHelper!=null) mBackgroundHelper.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mBackgroundHelper!=null)   mBackgroundHelper.onDraw(canvas);

    }
}
