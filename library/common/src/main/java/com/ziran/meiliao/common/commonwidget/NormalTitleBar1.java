package com.ziran.meiliao.common.commonwidget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ziran.meiliao.common.R;


public class NormalTitleBar1 extends RelativeLayout {

    private ImageView ivRight,ivRight2;
    private TextView ivBack, tvTitle, tvRight;
    private RelativeLayout rlCommonTitle;
    private Context context;
    private View verLine;

    public NormalTitleBar1(Context context) {
        this(context, null);
    }

    public NormalTitleBar1(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.bar_normal1,this,true);
        ivBack = (TextView) findViewById(R.id.tv_back);
        verLine = findViewById(R.id.verLine);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvRight = (TextView) findViewById(R.id.tv_right);
        ivRight = (ImageView) findViewById(R.id.image_right);
        ivRight2 = (ImageView) findViewById(R.id.image_right_2);
        rlCommonTitle = (RelativeLayout) findViewById(R.id.common_title);
        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.NormalTitleBar);
        if (array.hasValue(R.styleable.NormalTitleBar_ntb_bg)){
           setBackgroundColor(Color.TRANSPARENT);
        }
        array.recycle();
    }

    public void setBackGroundColor() {
        setBackGroundColor(R.color.titleBar_bg);
    }

    /**
     * 管理返回按钮
     * 设置标题栏左侧字符串
     *
     * @param visiable 是否显示
     */
    public void setTvLeftVisiable(boolean visiable) {
        if (visiable) {
            ivBack.setVisibility(View.VISIBLE);
            verLine.setVisibility(VISIBLE);
        } else {
            ivBack.setVisibility(View.GONE);
            verLine.setVisibility(INVISIBLE);
        }
    }

    /**
     * 设置标题栏左侧字符串
     *
     * @param visiable 是否显示
     */
    public void setTvLeftVisiable(boolean visiable, boolean isFinish) {
        setTvLeftVisiable(visiable);
        if (isFinish) {
            ivBack.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Activity) getContext()).finish();
                }
            });
        }
    }

    /**
     * 设置标题栏左侧字符串
     *
     * @param tvLeftText
     */
    public void setTvLeft(String tvLeftText) {
        ivBack.setVisibility(VISIBLE);
        ivBack.setText(tvLeftText);
        ivBack.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
    }


    /**
     * 管理标题
     */
    public void setTitleVisibility(boolean visible) {
        if (visible) {
            tvTitle.setVisibility(View.VISIBLE);
//            verLine.setVisibility(VISIBLE);
        } else {
            tvTitle.setVisibility(View.INVISIBLE);
//            verLine.setVisibility(INVISIBLE);
        }
    }

    public void setTitleText(String string) {
        tvTitle.setText(string);
    }

    public void setTitleIcon(int rightIcon) {
      setTitleIcon(rightIcon,8);
    }
    public void setTitleIcon(int rightIcon,int drawablePadding) {
        tvTitle.setCompoundDrawablesWithIntrinsicBounds(0, 0, rightIcon, 0);
        tvTitle.setCompoundDrawablePadding(drawablePadding);
    }

    public void setTitleText(int string) {
        tvTitle.setText(string);
    }

    public void setTitleColor(int color) {
        tvTitle.setTextColor(color);
    }

    /**
     * 右图标
     */
    public void setRightImagVisibility(boolean visible) {
        ivRight.setVisibility(visible ? View.VISIBLE : View.GONE);
    }
    /**
     * 右图标2
     */
    public void setRightImag2Visibility(boolean visible) {
        ivRight2.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public void setRightImagSrc(int id) {
        ivRight.setVisibility(View.VISIBLE);
        ivRight.setImageResource(id);
    }
    public void setRightImag2Src(int id) {
        ivRight2.setVisibility(View.VISIBLE);
        ivRight2.setImageResource(id);
    }

    /**
     * 获取右按钮
     *
     * @return
     */
    public View getRightImage() {
        return ivRight;
    }

    /**
     * 获取右按钮
     *
     * @return
     */
    public String getRightText() {
        return tvRight.getText().toString();
    }
    /**
     * 获取右按钮
     *
     * @return
     */
    public TextView getRightTextView() {
        return tvRight;
    }

    /**
     * 左图标
     *
     * @param id
     */
    public void setLeftImagSrc(int id) {
        ivBack.setVisibility(VISIBLE);
        ivBack.setCompoundDrawablesWithIntrinsicBounds(id, 0, 0, 0);

    }

    /**
     * 左文字
     *
     * @param str
     */
    public void setLeftTitle(String str) {
        ivBack.setText(str);
        setLeftImagSrc(0);
    }

    /**
     * 右标题
     */
    public void setRightTitleVisibility(boolean visible) {
        tvRight.setVisibility(visible ? View.VISIBLE : View.GONE);
    }



    public TextView setRightTitle(String text) {
        tvRight.setText(text);
        setRightTitleVisibility(true);
        return tvRight;
    }

    /*
     * 点击事件
     */
    public void setOnBackListener(OnClickListener listener) {
        ivBack.setOnClickListener(listener);
    }

    public void setOnRightImagListener(OnClickListener listener) {
        ivRight.setOnClickListener(listener);
    }
    public void setOnRightImag2Listener(OnClickListener listener) {
        ivRight2.setOnClickListener(listener);
    }

    public void setOnRightTextListener(OnClickListener listener) {
        tvRight.setOnClickListener(listener);
    }

    /**
     * 标题背景颜色
     *
     * @param color
     */
    public void setBackGroundColor(int color) {
        rlCommonTitle.setBackgroundColor(getResources().getColor(color));
    }

    public Drawable getBackGroundDrawable() {
        return rlCommonTitle.getBackground();
    }

    public void setTvRightTextColor(int color) {
        tvRight.setTextColor(color);
    }

    public void setCenterView(View view) {
        setCenterView(view, view.getResources().getDimensionPixelSize(R.dimen.tablayout_width_160));
    }

    public void setCenterView(View view, int width) {
        tvTitle.setVisibility(GONE);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        addView(view, params);
    }
    public void setLeftView(View view) {
        ivBack.setVisibility(GONE);
        int width = getResources().getDimensionPixelSize(R.dimen.titlebar_play);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width, width);
        params.gravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;
        addView(view, params);
    }

    public void setTitleOnClickListener(OnClickListener l) {
        if (l == null) return;
        tvTitle.setOnClickListener(l);
    }

    public void setRightToggle() {
        String rightText = tvRight.getText().toString();
        if ("编辑".equals(rightText)) {
            tvRight.setText("取消");
        } else if ("取消".equals(rightText)) {
            tvRight.setText("编辑");
        }
    }


    public void setStyle(int style) {
        switch (style){
            case 2:
                setBackGroundColor(R.color.transparent);
                tvTitle.setTextColor(getResources().getColor(R.color.white));
                tvRight.setTextColor(getResources().getColor(R.color.white));
                setLeftImagSrc(R.mipmap.back_white);
                break;

        }
    }
}
