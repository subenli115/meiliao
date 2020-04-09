package com.ziran.meiliao.widget.ninegridimageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.ziran.meiliao.R;

/**
 * Created by Jaeger on 16/2/24.
 * <p>
 * Email: chjie.jaeger@gamil.com
 * GitHub: https://github.com/laobie
 */
public class GridImageView extends AppCompatImageView {

    private boolean showDeleteBtn;

    public GridImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridImageView(Context context) {
        super(context);
    }

    private boolean isClickDelete;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
//                Drawable drawable = getDrawable();
//                if (drawable != null) {
//                    drawable.mutate().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
//                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
//                Drawable drawableUp = getDrawable();
//                if (drawableUp != null) {
//                    drawableUp.mutate().clearColorFilter();
//                }
                if (showDeleteBtn && deleteBitmap != null && mOnDeleteClickListener != null && (event.getX() > getWidth() - deleteBitmap
                        .getWidth() && event.getY() < deleteBitmap.getHeight())) {
                    if (isClickDelete) break;
                    postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            isClickDelete = false;
                            mOnDeleteClickListener.deleteClick();
                        }
                    }, 200);
                    isClickDelete = true;
                } else {
                    performClick();
                }
                break;
        }

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (showDeleteBtn && deleteBitmap != null) {
            int left = getWidth() - deleteBitmap.getWidth();
            int top = 0;
            canvas.drawBitmap(deleteBitmap, left, top, null);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setImageDrawable(null);
    }

    public boolean isShowDeleteBtn() {
        return showDeleteBtn;
    }

    private Bitmap deleteBitmap;
    private OnDeleteClickListener mOnDeleteClickListener;

    public void setOnDeleteClickListener(OnDeleteClickListener onDeleteClickListener) {
        mOnDeleteClickListener = onDeleteClickListener;
    }

    public interface OnDeleteClickListener {
        void deleteClick();
    }

    public void setShowDeleteBtn(boolean showDeleteBtn) {
        this.showDeleteBtn = showDeleteBtn;
        if (showDeleteBtn) {
//            int padding = DisplayUtil.dip2px(getResources(), 8);
            Drawable drawable = getResources().getDrawable(R.mipmap.delete_red);
            if (drawable instanceof BitmapDrawable) {
                deleteBitmap = ((BitmapDrawable) drawable).getBitmap();
                int padding = deleteBitmap.getWidth() / 2;
                setPadding(padding, padding, padding, padding);
            }

        } else {
            setPadding(0, 0, 0, 0);
            deleteBitmap = null;
        }
        invalidate();
    }
}