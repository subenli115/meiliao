package com.ziran.meiliao.common.irecyclerview.universaladapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ziran.meiliao.common.R;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;

import java.io.File;

public class ViewHolderHelper extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    public int mPosition;
    private View mConvertView;
    private Context mContext;
    public int mLayoutId;

    public ViewHolderHelper(Context context, View itemView, ViewGroup parent, int position) {
        super(itemView);
        mContext = context;
        mConvertView = itemView;
        mPosition = position;
        mViews = new SparseArray<>();
        mConvertView.setTag(this);

    }


    public static ViewHolderHelper get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {

        if (convertView == null) {
            View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
            ViewHolderHelper holder = new ViewHolderHelper(context, itemView, parent, position);
            holder.mLayoutId = layoutId;
            return holder;
        } else {
            try {
                ViewHolderHelper holder = (ViewHolderHelper) convertView.getTag();
                holder.mPosition = position;
                return holder;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 通过viewId获取控件
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            if (view != null) {
                mViews.put(viewId, view);
            } else {
                return null;
            }
        }
        return (T) view;
    }

    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 设置TextView的值
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolderHelper setText(int viewId, CharSequence text) {
        if (TextUtils.isEmpty(text)) {
            text = "";
        }
        TextView tv = getView(viewId);

        if (tv != null) tv.setText(text);
        return this;
    }

    /**
     * 设置TextView的值
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolderHelper setTextAndVis(int viewId, CharSequence text) {
        TextView tv = getView(viewId);
        if (tv != null) {
            tv.setText(text);
            tv.setVisibility(TextUtils.isEmpty(text) ? View.GONE : View.VISIBLE);
        }
        return this;
    }

    /**
     * 设置TextView的值
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolderHelper setText(int viewId, int text) {
        TextView tv = getView(viewId);
        if (tv != null) tv.setText(String.valueOf(text));
        return this;
    }
//    /**
//     * 设置TextView的值
//     *
//     * @param viewId
//     * @param text
//     * @return
//     */
//    public ViewHolderHelper setFormatText(int viewId, int stingId, Object text) {
//        if (EmptyUtils.isNotEmpty(text)) {
//            text = "";
//        }
//        TextView tv = getView(viewId);
//        tv.setText(String);
//        return this;
//    }

    public ViewHolderHelper setImageResource(int viewId, int resId) {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        view.setVisibility(View.VISIBLE);
        return this;
    }

    public ViewHolderHelper setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    public ViewHolderHelper setImageUrl(int viewId, String url) {
        if (!TextUtils.isEmpty(url)) {
            ImageView view = getView(viewId);
            ImageLoaderUtils.display(mContext, view, url);
        }
        return this;
    }

    public ViewHolderHelper setImageUrl(int viewId, String url, int errorId) {
        if (!TextUtils.isEmpty(url)) {
            ImageView view = getView(viewId);
            ImageLoaderUtils.display(mContext, view, url, errorId);
        }
        return this;
    }

    public ViewHolderHelper setBigImageUrl(int viewId, String url) {
        if (!TextUtils.isEmpty(url)) {
            ImageView view = getView(viewId);
            ImageLoaderUtils.displayBigPhoto(mContext, view, url);
        }
        return this;
    }

    public ViewHolderHelper setSmallImageUrl(int viewId, String url) {
        if (!TextUtils.isEmpty(url)) {
            ImageView view = getView(viewId);
            ImageLoaderUtils.displaySmallPhoto(mContext, view, url);
        }
        return this;
    }

    public ViewHolderHelper setImageRoundUrl(int viewId, String url) {
        if (!TextUtils.isEmpty(url)) {
            ImageView view = getView(viewId);
            ImageLoaderUtils.displayRound(mContext, view, url);
        }
        return this;
    }

    public ViewHolderHelper setImageCircle(int viewId, String url) {
        if (!TextUtils.isEmpty(url)) {
            ImageView view = getView(viewId);
            ImageLoaderUtils.displayCircle(mContext, view, url, R.mipmap.ic_loading_rectangle);
        }
        return this;
    }

    public ViewHolderHelper setImageFile(int viewId, File url) {
        ImageView view = getView(viewId);
        ImageLoaderUtils.display(mContext, view, url);
        return this;
    }

    public ViewHolderHelper setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    public ViewHolderHelper setBackgroundColor(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public ViewHolderHelper setBackgroundColorNew(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(view.getResources().getColor(color));
        return this;
    }

    public ViewHolderHelper setBackgroundRes(int viewId, int backgroundRes) {
        View view = getView(viewId);
        if (view == null) return this;
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    public ViewHolderHelper setTextColor(int viewId, int colorId) {
        TextView view = getView(viewId);
        if (view == null) return this;
        view.setTextColor(mContext.getResources().getColor(colorId));
        return this;
    }

    public ViewHolderHelper setTextColorNew(int viewId, int textColor) {
        TextView view = getView(viewId);
        if (view == null) return this;
        view.setTextColor(textColor);
        return this;
    }

    public ViewHolderHelper setTextColorRes(int viewId, int textColorRes) {
        TextView view = getView(viewId);
        if (view == null) return this;
        view.setTextColor(mContext.getResources().getColor(textColorRes));
        return this;
    }

    @SuppressLint("NewApi")
    public ViewHolderHelper setAlpha(int viewId, float value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getView(viewId).setAlpha(value);
        } else {
            // Pre-honeycomb hack to set Alpha value
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            getView(viewId).startAnimation(alpha);
        }
        return this;
    }

    public ViewHolderHelper setVisible(int viewId, boolean visible) {
        View view = getView(viewId);
        if (view == null) return this;
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    public ViewHolderHelper linkify(int viewId) {
        TextView view = getView(viewId);
        Linkify.addLinks(view, Linkify.ALL);
        return this;
    }

    public ViewHolderHelper setTypeface(Typeface typeface, int... viewIds) {
        for (int viewId : viewIds) {
            TextView view = getView(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }
        return this;
    }

    public ViewHolderHelper setProgress(int viewId, int progress) {
        ProgressBar view = getView(viewId);
        view.setProgress(progress);
        return this;
    }

    public ViewHolderHelper setProgress(int viewId, int progress, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    public ViewHolderHelper setMax(int viewId, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        return this;
    }

    public ViewHolderHelper setRating(int viewId, float rating) {
        RatingBar view = getView(viewId);
        view.setRating(rating);
        return this;
    }

    public ViewHolderHelper setRating(int viewId, float rating, int max) {
        RatingBar view = getView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    public ViewHolderHelper setTag(int viewId, Object tag) {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    public ViewHolderHelper setTag(int viewId, int key, Object tag) {
        View view = getView(viewId);
        view.setTag(key, tag);
        return this;
    }

    public ViewHolderHelper setChecked(int viewId, boolean checked) {
        Checkable view = (Checkable) getView(viewId);
        view.setChecked(checked);
        return this;
    }

    /**
     * 关于事件的
     */
    public ViewHolderHelper setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public ViewHolderHelper setOnTouchListener(int viewId, View.OnTouchListener listener) {
        View view = getView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

    public ViewHolderHelper setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

    public void updatePosition(int position) {
        mPosition = position;
    }

    public int getLayoutId() {
        return mLayoutId;
    }


    public <T> T getView(int viewId, int width, int weight) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = width / 10 * weight;
        view.requestLayout();
        return (T) view;
    }

    public void setImageUrlTarget(int viewId, String url) {
        if (!TextUtils.isEmpty(url)) {
            ImageView view = getView(viewId);
            ImageLoaderUtils.displayTager(mContext, view, url);
        }
    }

    public void setImageUrlTarget(int viewId, String url, int res) {
        if (!TextUtils.isEmpty(url)) {
            ImageView view = getView(viewId);
            ImageLoaderUtils.displayTager(mContext, view, url, res);
        }
    }

    public void setFocusable(int viewId, boolean focus) {
        View view = getView(viewId);
        if (view != null) {
            view.setFocusable(focus);
            view.setFocusableInTouchMode(focus);
        }
    }


    public Context getContext() {
        return mContext;
    }

    public void setOnTextChangeListener(int id, TextWatcher textWatcher) {
        TextView tv = getView(id);
        if (tv != null && textWatcher != null) {
            if (tv.getTag() != null) {
                TextWatcher watcher = (TextWatcher) tv.getTag();
                tv.removeTextChangedListener(watcher);
            }
            tv.addTextChangedListener(textWatcher);
            tv.setTag(textWatcher);
        }
    }

    public ImageView getImageView(int id) {
        return getView(id);
    }
}
