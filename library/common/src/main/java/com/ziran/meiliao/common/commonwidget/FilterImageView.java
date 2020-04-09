package com.ziran.meiliao.common.commonwidget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.ziran.meiliao.common.R;
import com.ziran.meiliao.common.commonwidget.helper.BackgroundHelper;

/**
 * @author LinJ
 * @ClassName: ThumbnailView
 * @Description: 点击时显示明暗变化(滤镜效果)的ImageView
 * @date 2015-1-6 下午2:13:46
 */
public class FilterImageView extends AppCompatImageView {

    public FilterImageView(Context context) {
        this(context, null);
    }

    public FilterImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        // TODO Auto-generated constructor stub
    }

    public FilterImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initAttrs(context, attrs);
    }

    private String defTextColor = "#d9d9d9";
    private void initAttrs(Context context, AttributeSet attrs) {
        if (getBackground() != null) return;
        BackgroundHelper backgroundHelper = new BackgroundHelper(this);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FilterImageView);
        int defaultClickColor = Color.parseColor(defTextColor);
        int bgColor = 0, bgClickColor = 0;
        if (a.hasValue(R.styleable.FilterImageView_cs_bg_style)) {
            int bgStyle = a.getInt(R.styleable.FilterImageView_cs_bg_style, 0);
            switch (bgStyle) {
                case 0:
                    bgClickColor = defaultClickColor;
                    break;
                case 1:
                    bgColor = Color.WHITE;
                    bgClickColor = defaultClickColor;
                    break;
            }
        } else {
            bgColor = a.getColor(R.styleable.FilterImageView_cs_normal_bg_color, Color.TRANSPARENT);
            bgClickColor = a.getColor(R.styleable.FilterImageView_cs_fitter_bg_color, Color.TRANSPARENT);
        }
        int bgCircleAngle = a.getDimensionPixelOffset(R.styleable.FilterImageView_cs_bg_radius, 0);
        int borderWidth = a.getDimensionPixelOffset(R.styleable.FilterImageView_cs_border_width, 0);
        int borderColor = a.getColor(R.styleable.FilterImageView_cs_border_color, Color.TRANSPARENT); //边框默认为透明色 0
        a.recycle();
        //可选
        backgroundHelper.init(bgColor, bgCircleAngle, bgClickColor, borderWidth, borderColor);
    }
  

}
