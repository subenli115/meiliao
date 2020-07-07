package com.ziran.meiliao.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.KeyBordUtil;
import com.ziran.meiliao.envet.SimpleTextWatcher;


/**
 * author 吴祖清
 * create  2017/3/31 10
 * des     删除和显示密码和隐藏密码的编辑框
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */


public class CustomEditText extends AppCompatEditText implements View.OnFocusChangeListener{
    /**
     * 是否获取焦点，默认没有焦点
     */
    private boolean hasFocus = false;
    /**
     * 删除按钮的图片资源显示和隐藏密码的图片资源id
     */
    private int delResId, showPwdResId, hidePwdResId;
    /**
     * 类型（ 2 密码编辑框 1 删除按钮编辑框 默认值 2）
     */
    private int type;
    /**
     * 标记是否有内容
     */
    private boolean hasContent;
    
    /**
     * 标记是否显示密码
     */
    private boolean isShowPassword = false;
    /**
     * 松开时的X坐标
     */
    private int upX;
    
    /**
     * 当前的图片资源ID
     */
    public int currResId;
    
    /**
     * 删除 显示密码 隐藏密码的图片背景
     */
    private Drawable delDrawable, showPwdDrawable, hidePwdDrawable;
    
    /**
     * 默认的文本内容
     */
    private String startContent;
    
    /**
     * 标记文本内容是否发生变化
     */
    private boolean hasChange;
    /**
     * 设置内容发生变化的监听
     */
    private OnContentChangeListener mOnContentChangeListener;
    
    public CustomEditText(Context context) {
        this(context, null);
    }
    
    public CustomEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    
    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
        initListener();
    }
    
    /**
     * 初始化xml属性
     *
     * @param attrs
     */
    private void initAttrs (AttributeSet attrs) {

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomEditText);
        delDrawable = typedArray.getDrawable(R.styleable.CustomEditText_rightIconDel);
        showPwdDrawable = typedArray.getDrawable(R.styleable.CustomEditText_rightIconShowPwd);
        hidePwdDrawable = typedArray.getDrawable(R.styleable.CustomEditText_rightIconHidePwd);
        type = typedArray.getInteger(R.styleable.CustomEditText_type, -1);
        typedArray.recycle();
        setType(type);
//        if(type==3){
//            setRightIcon(R.mipmap.jdx_voice_question);
//        }
    }
    
    /**
     * 设置编辑框的类型
     *
     * @param type 类型
     */
    public void setType (int type) {
        this.type = type;
        switch (type) {
            case 1:
                delResId = R.mipmap.ic_code_del;
                currResId = delResId;
                break;
            case 2:
                hidePwdResId = R.mipmap.ic_pwd_hide;
                showPwdResId = R.mipmap.ic_pwd_show;
                currResId = hidePwdResId;
                break;
        }
    }
    
    /**
     * 设置文本变化监听
     */
    private void initListener () {
        addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged (CharSequence s, int start, int before, int count) {
                changeRightIcon(s);
                if (mOnContentChangeListener != null) {
                    if (startContent.equals(s.toString())) {
                        hasChange = false;
                        mOnContentChangeListener.change(false);
                    } else {
                        if (!hasChange) {
                            mOnContentChangeListener.change(true);
                            hasChange = true;
                        }
                    }
                }
            }
        });
    }


    @Override
    public boolean onTouchEvent (MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            // 获取点击时手指抬起的X坐标
            upX = (int) event.getX();
            // 当点击的坐标到当前输入框右侧的距离小于等于 getCompoundPaddingRight() 的距离时，则认为是点击了删除图标
            if ((getWidth() - upX) <= getCompoundPaddingRight()) {
                doClick();
            } else {
                KeyBordUtil.showSoftKeyboard(this);
            }
        }
        return super.onTouchEvent(event);
    }
    
    //点击图片监听
    private void doClick () {
        if (type == 1) { //清楚内容
            if (!TextUtils.isEmpty(getText().toString())) {
                setText("");
            }
        } else if (type == 2) {
            if (!isShowPassword) { //如果隐藏密码则显示密码
                currResId = showPwdResId;
                isShowPassword = true;
                setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            } else {  //否则隐藏密码
                currResId = hidePwdResId;
                setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                isShowPassword = false;
            }
            setRightIcon(currResId);
            setSelection(getText().length());
        }else {
        }
    }
    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        if (hasFocus) {
            if (TextUtils.isEmpty(text)) {


            } else {


            }
        }
    }

    /**
     * 设置右侧图标
     *
     * @param drawable
     */
    public void setRightIcon (int drawable) {
        setPadding(0,0,23,0);
        setCompoundDrawablesWithIntrinsicBounds(0, 0, drawable, 0);
    }
    
    /**
     * 设置右侧图标
     *
     * @param s 文本内容
     */
    private void changeRightIcon (CharSequence s) {
        if (type == 0)
            return;
        if (s.length() == 0) {
            setRightIcon(0);
            hasContent = false;
        } else {
            if (!hasContent) {
                hasContent = true;
                setRightIcon(currResId);
            }
        }
    }
    
    /**
     * 返回文本内容
     *
     * @return
     */
    public String getContent () {
        return getText().toString();
    }
    
    /**
     * 设置内容发生变化的监听
     *
     * @param onContentChangeListener
     */
    public void setOnContentChangeListener (OnContentChangeListener onContentChangeListener) {
        this.mOnContentChangeListener = onContentChangeListener;
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        this.hasFocus=b;
    }

    /**
     * 文本内容发生变化的借口
     */
    public interface OnContentChangeListener {
        void change (boolean change);
    }
    
    /**
     * 设置默认的文本内容
     *
     * @param content 文本内容
     */
    public void setContent (String content) {
        setText(content);
        startContent = content;
    }
}
