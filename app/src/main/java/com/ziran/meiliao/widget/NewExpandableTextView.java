package com.ziran.meiliao.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.SimpleAnimatorListener;


/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2018/1/11 17:08
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2018/1/11$
 * @updateDes ${TODO}
 */

public class NewExpandableTextView extends TextView {


    private boolean isUsedAnimator = false;
    private boolean isSkrink = false;  //是否收缩
    private int mMaxSkrinkLines = 4;
    private int upHeight;
    private int downHeight;
    private boolean needSkrink;
    private int arrowDownRes = R.mipmap.course_arrow_down;
    private int arrowUpRes = R.mipmap.course_arrow_up;
    private ValueAnimator animator;
    private ViewGroup.LayoutParams mLayoutParams;


    public NewExpandableTextView(Context context) {
        super(context);
        init();
    }

    public NewExpandableTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NewExpandableTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    private void init() {
        if (Build.VERSION.SDK_INT >= 16) {
            mMaxSkrinkLines = getMaxLines();
        }
        setCompoundDrawablePadding(30);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle();
            }
        });
        postDelayed(new Runnable() {
            @Override
            public void run() {
                change(isSkrink, isUsedAnimator);
            }
        }, 500);
    }


    public void change(boolean isSkrink, boolean isUsedAnimator) {
        if (getHeight() < getLineHeight() * mMaxSkrinkLines) {
            needSkrink = false;
            return;
        }
        if (isUsedAnimator && upHeight > 0 && downHeight > 0) {
            if (mLayoutParams == null) {
                mLayoutParams = getLayoutParams();
            }
            if (animator != null && animator.isRunning()) {
                animator.cancel();
            }
            animator = ValueAnimator.ofInt(isSkrink ? upHeight : downHeight, isSkrink ? downHeight : upHeight);
            animator.addUpdateListener(mUpdateListener);
            animator.setInterpolator(new LinearInterpolator());
            animator.setDuration(600);
            animator.addListener(mAnimatorListener);
            animator.start();
        } else {
            setMaxLines(isSkrink ? Integer.MAX_VALUE : mMaxSkrinkLines);
            changeIcon(isSkrink);
        }
    }

    private void changeIcon(boolean isSkrink) {
        setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, isSkrink ? arrowUpRes : arrowDownRes);
    }

    private ValueAnimator.AnimatorUpdateListener mUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            mLayoutParams.height = (int) animation.getAnimatedValue();
            setLayoutParams(mLayoutParams);
        }
    };
    private SimpleAnimatorListener mAnimatorListener = new SimpleAnimatorListener() {

        @Override
        public void onAnimationStart(Animator animation) {
            changeIcon(isSkrink);
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            changeIcon(isSkrink);
        }

        @Override
        public void onAnimationCancel(Animator animation) {
            changeIcon(isSkrink);
        }
    };

    @Override
    public void setMaxLines(int maxLines) {
        super.setMaxLines(maxLines);
        updateHeight();
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        updateHeight();
    }

    private void updateHeight() {
        postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isSkrink) {
                    downHeight = downHeight == 0 ? getHeight() : downHeight;
                } else {
                    upHeight = upHeight == 0 ? getHeight() : upHeight;
                }
//                LogUtils.logd("downHeight:" + downHeight + "\t\tupHeight:" + upHeight);
            }
        }, 200);
    }

    public void toggle() {
        toggle(isUsedAnimator);
    }

    public void toggle(boolean isAnimator) {
        isSkrink = !isSkrink;
        change(isSkrink, isAnimator);
    }

    public boolean isUsedAnimator() {
        return isUsedAnimator;
    }

    public void setUsedAnimator(boolean usedAnimator) {
        isUsedAnimator = usedAnimator;
    }

    public int getMaxSkrinkLines() {
        return mMaxSkrinkLines;
    }

    public void setMaxSkrinkLines(int maxSkrinkLines) {
        mMaxSkrinkLines = maxSkrinkLines;
    }
}
