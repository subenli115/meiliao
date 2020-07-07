package com.ziran.meiliao.common.viewpager;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.ziran.meiliao.common.compressorutils.EmptyUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CardPagerAdapter<T> extends PagerAdapter implements CardAdapter {

    private List<T> mData;
    private Map<Integer, View> viewMap;

    @SuppressLint("UseSparseArrays")
    public CardPagerAdapter() {
        mData = new ArrayList<>();
        viewMap = new HashMap<>();
    }

    private OnItemClickListener onItemClickListener;

    private void addCardItem(T item) {
        mData.add(item);
    }

    public void removeItem(int position) {
        mData.remove(position);
        notifyDataSetChanged();
    }

    public void addCardItems(List<T> datas) {
        if (EmptyUtils.isEmpty(datas)) return;
        mData.clear();
        for (int i = 0; i < datas.size(); i++) {
            addCardItem(datas.get(i));
        }
    }

    public abstract int getAdapterResourse();


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        if (!viewMap.containsKey(position)) {
            View view = LayoutInflater.from(container.getContext())
                    .inflate(getAdapterResourse(), container, false);
            container.addView(view);
            if (getCount() != 0) {
                bind(mData.get(position), view, position);
            }
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(container, mData.get(position), position);
                    }
                }
            });
            viewMap.put(position, view);
            return view;
        } else {
            return viewMap.get(position);
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        viewMap.remove(position);
        ((ViewPager) container).removeView((View) object);
    }

    public abstract void bind(T item, View view, int position);

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener<T> {
        void onItemClick(ViewGroup container, T item, int position);
    }
}
