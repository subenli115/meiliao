/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.citypicker.citylist.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;

import com.citypicker.R;

/**
 * 说明:
 * 作者: fangkaijin on 2017/4/10.13:27
 * 邮箱:fangkaijin@gmail.com
 */

public class ClearEditText extends AppCompatEditText implements TextWatcher, View.OnFocusChangeListener {
    /**
     * 左右两侧图片资源
     */
    private Drawable left, right;
    /**
     * 是否获取焦点，默认没有焦点
     */
    private boolean hasFocus = false;
    /**
     * 手指抬起时的X坐标
     */
    private int xUp = 0;

    public ClearEditText(Context context) {
        this(context, null);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initWeigets(attrs);
    }

    /**
     * 初始化各组件
     *
     * @param attrs 属性集
     */
    private void initWeigets(AttributeSet attrs) {
        try {
            //获取EditText的DrawableRight,假如没有设置我们就使用默认的图片
            left = getCompoundDrawables()[0];
            right = getCompoundDrawables()[2];
            if (right == null) {
                right = getResources().getDrawable(R.drawable.ic_code_del);
            }
            initDatas();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化数据
     */
    private void initDatas() {
        try {

            // 第一次显示，隐藏删除图标
            setCompoundDrawablesWithIntrinsicBounds(left, null, null, null);
            addListeners();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加事件监听
     */
    private void addListeners() {
        try {
            setOnFocusChangeListener(this);
            addTextChangedListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    public boolean hasContent() {
        return hasContent;
    }

    private boolean hasContent;

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int after) {
        if (hasFocus) {
            if (TextUtils.isEmpty(s)) {
                // 如果为空，则不显示删除图标
                setCompoundDrawablesWithIntrinsicBounds(left, null, null, null);
                if (mOnTextChangeListener != null && hasContent) {
                    mOnTextChangeListener.hasContent(false);
                    hasContent = false;
                }
            } else {
                // 如果非空，则要显示删除图标
                if (null == right) {
                    right = getCompoundDrawables()[2];
                }
                if (mOnTextChangeListener != null && !hasContent) {
                    hasContent = true;
                    mOnTextChangeListener.hasContent(true);
                }
                setCompoundDrawablesWithIntrinsicBounds(left, null, right, null);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try {
            switch (event.getAction()) {
                case MotionEvent.ACTION_UP:
                    // 获取点击时手指抬起的X坐标
                    xUp = (int) event.getX();
                    // 当点击的坐标到当前输入框右侧的距离小于等于getCompoundPaddingRight()的距离时，则认为是点击了删除图标
                    // getCompoundPaddingRight()的说明：Returns the right padding of the view, plus space for the right Drawable if any.
                    if ((getWidth() - xUp) <= getCompoundPaddingRight()) {
                        if (!TextUtils.isEmpty(getText().toString())) {
                            setText("");
                            if (mOnClearListener != null) {
                                mOnClearListener.clear();
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        try {
            this.hasFocus = hasFocus;
            String msg = getText().toString();

            if (hasFocus) {
                if (msg.equalsIgnoreCase("")) {
                    setCompoundDrawablesWithIntrinsicBounds(left, null, null, null);
                } else {
                    setCompoundDrawablesWithIntrinsicBounds(left, null, right, null);
                }
            }
            if (!hasFocus) {
                setCompoundDrawablesWithIntrinsicBounds(left, null, null, null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取输入内容
    public String text_String() {
        return getText().toString();
    }

    /**
     * 设置晃动动画
     */
    public void setShakeAnimation() {
        this.setAnimation(shakeAnimation(5));
    }


    /**
     * 晃动动画
     *
     * @param counts 1秒钟晃动多少下
     * @return
     */
    public static Animation shakeAnimation(int counts) {
        Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
        translateAnimation.setInterpolator(new CycleInterpolator(counts));
        translateAnimation.setDuration(1000);
        return translateAnimation;
    }

    private OnClearListener mOnClearListener;
    private OnTextChangeListener mOnTextChangeListener;

    public void setOnTextChangeListener(OnTextChangeListener onTextChangeListener) {
        mOnTextChangeListener = onTextChangeListener;
    }

    public void setOnClearListener(OnClearListener onClearListener) {
        mOnClearListener = onClearListener;
    }

    public static interface OnClearListener {
        void clear();
    }

    public static interface OnTextChangeListener {
        void hasContent(boolean hasContent);
    }
}
