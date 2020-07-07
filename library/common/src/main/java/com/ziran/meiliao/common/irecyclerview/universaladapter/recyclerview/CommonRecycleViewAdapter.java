package com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import androidx.recyclerview.widget.RecyclerView;

import com.ziran.meiliao.common.commonutils.DisplayUtil;
import com.ziran.meiliao.common.irecyclerview.animation.AlphaInAnimation;
import com.ziran.meiliao.common.irecyclerview.animation.BaseAnimation;
import com.ziran.meiliao.common.irecyclerview.bean.PageBean;
import com.ziran.meiliao.common.irecyclerview.universaladapter.DataIO;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by zhy on 16/4/9.
 */
public abstract class CommonRecycleViewAdapter<T> extends RecyclerView.Adapter<ViewHolderHelper> implements DataIO<T> {
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;

    protected OnItemClickListener mOnItemClickListener;


    //动画
    private int mLastPosition = -1;
    private boolean mOpenAnimationEnable = true;
    private Interpolator mInterpolator = new LinearInterpolator();
    private int mDuration = 300;
    private PageBean pageBean;
    private BaseAnimation mSelectAnimation = new AlphaInAnimation();
    public boolean check;
    private ViewGroup.MarginLayoutParams mMParams;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public CommonRecycleViewAdapter(Context context, int layoutId, List<T> mDatass) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = mDatass;
        pageBean = new PageBean();
        if (getWidthRatio() < 1f) {
            int width = (int) (DisplayUtil.getScreenWidth(mContext) * getWidthRatio());
            mMParams = new ViewGroup.MarginLayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (getMarginValue() > 0) {
                mMParams.rightMargin = DisplayUtil.dip2px(mContext.getResources(), getMarginValue());
            }
        }
    }


    public CommonRecycleViewAdapter(Context context, int layoutId, List<T> mDatass,float widthRatio) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = mDatass;
        pageBean = new PageBean();
        if (widthRatio < 1f) {
            int width = (int) (DisplayUtil.getScreenWidth(mContext) * widthRatio);
            mMParams = new ViewGroup.MarginLayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (getMarginValue() > 0) {
                mMParams.rightMargin = DisplayUtil.dip2px(mContext.getResources(), getMarginValue());
            }
        }
    }

    protected float getMarginValue() {
        return 10f;
    }

    protected float getWidthRatio() {
        return 1f;
    }

    public CommonRecycleViewAdapter(Context context, int layoutId) {
        this(context, layoutId, new ArrayList<T>());
    }

    @Override
    public ViewHolderHelper onCreateViewHolder(final ViewGroup parent, int viewType) {
        ViewHolderHelper viewHolder = ViewHolderHelper.get(mContext, null, parent, mLayoutId, -1);
        if (mMParams != null && viewHolder != null) {
            viewHolder.getConvertView().setLayoutParams(mMParams);
        }
        setListener(parent, viewHolder, viewType);
        return viewHolder;
    }


    protected int getPosition(RecyclerView.ViewHolder viewHolder) {
        return viewHolder.getAdapterPosition() - headCount;
    }

    private int headCount = 2;

    public void setHeadCount(int headCount) {
        this.headCount = headCount;
    }

    protected boolean isEnabled(int viewType) {
        return true;
    }


    protected void setListener(final ViewGroup parent, final ViewHolderHelper viewHolder, int viewType) {
        if (!isEnabled(viewType)) return;
        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    int position = getPosition(viewHolder);
                    if (position < 0 || position >= mDatas.size()) return;
                    mOnItemClickListener.onItemClick(parent, v, mDatas.get(position), position);
                }
            }
        });

        viewHolder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemClickListener != null) {
                    int position = getPosition(viewHolder);
                    if (position < 0 || position >= mDatas.size()) return false;
                    return mOnItemClickListener.onItemLongClick(parent, v, mDatas.get(position), position);
                }
                return false;
            }
        });
    }

    @Override
    public void onBindViewHolder(ViewHolderHelper holder, int position) {
        holder.updatePosition(position);
        //添加动画
        addAnimation(holder);
        convert(holder, mDatas.get(position), position);
    }

    public abstract void convert(ViewHolderHelper helper, T t, int position);


    /**
     * add animation when you want to show time
     *
     * @param holder
     */
    public void addAnimation(RecyclerView.ViewHolder holder) {
        if (mOpenAnimationEnable) {
            if (holder.getLayoutPosition() > mLastPosition) {
                BaseAnimation animation = null;
                if (mSelectAnimation != null) {
                    animation = mSelectAnimation;
                }
                for (Animator anim : animation.getAnimators(holder.itemView)) {
                    startAnim(anim, holder.getLayoutPosition());
                }
                mLastPosition = holder.getLayoutPosition();
            }
        }
    }

    /**
     * set anim to start when loading
     *
     * @param anim
     * @param index
     */
    protected void startAnim(Animator anim, int index) {
        anim.setDuration(mDuration).start();
        anim.setInterpolator(mInterpolator);
    }

    /**
     * 设置动画
     *
     * @param animation ObjectAnimator
     */
    public void openLoadAnimation(BaseAnimation animation) {
        this.mOpenAnimationEnable = true;
        this.mSelectAnimation = animation;
    }

    /**
     * 关闭动画
     */
    public void closeLoadAnimation() {
        this.mOpenAnimationEnable = false;
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public void add(T elem) {
        mDatas.add(elem);
        notifyDataSetChanged();
    }

    @Override
    public void addAt(int location, T elem) {
        mDatas.add(location, elem);
        notifyDataSetChanged();
    }

    @Override
    public void addAll(List<T> elements) {
        mDatas.addAll(elements);
        notifyDataSetChanged();
    }

    @Override
    public void addAllAt(int location, List<T> elements) {
        mDatas.addAll(location, elements);
        notifyDataSetChanged();
    }

    @Override
    public void remove(T elem) {
        mDatas.remove(elem);
        notifyDataSetChanged();
    }

    @Override
    public void removeAt(int index) {
        mDatas.remove(index);
        notifyDataSetChanged();
    }

    @Override
    public void removeAll(List<T> elements) {
        mDatas.removeAll(elements);
        notifyDataSetChanged();
    }

    public void removeAll(Collection<T> elements) {
        mDatas.removeAll(elements);
        notifyDataSetChanged();
    }

    @Override
    public void clear() {
        if (mDatas != null && mDatas.size() > 0) {
            mDatas.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public void replace(T oldElem, T newElem) {
        replaceAt(mDatas.indexOf(oldElem), newElem);
    }

    @Override
    public void replaceAt(int index, T elem) {
        mDatas.set(index, elem);
        notifyDataSetChanged();
    }

    @Override
    public void replaceAll(List<T> elements) {
        if (mDatas.size() > 0) {
            mDatas.clear();
        }
        mDatas.addAll(elements);
        notifyDataSetChanged();
    }

    @Override
    public T get(int position) {
        if (position >= mDatas.size()) return null;
        return mDatas.get(position);
    }

    @Override
    public List<T> getAll() {
        return mDatas;
    }

    @Override
    public int getSize() {
        return mDatas.size();
    }

    @Override
    public boolean contains(T elem) {
        return mDatas.contains(elem);
    }

    /**
     * 分页
     *
     * @return
     */
    public PageBean getPageBean() {
        return pageBean;
    }


    public int currentPosition = -1;

    public void setSelectPosition(int position) {
        this.currentPosition = position;
    }

    public T getItem() {
        if (currentPosition != -1) {
            return get(currentPosition);
        } else {
            return null;
        }
    }

    public int getSelectPosition() {
        return currentPosition;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }


    public int getColor(int color) {
        return mContext.getResources().getColor(color);
    }

    public void startActivity(Class clz) {
        Intent intent = new Intent(mContext, clz);
        mContext.startActivity(intent);
    }

    public void startActivity(Class clz, Bundle bundle) {
        Intent intent = new Intent(mContext, clz);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

    public int getLastPosition() {
        return getItemCount() + headCount ;
    }
}
