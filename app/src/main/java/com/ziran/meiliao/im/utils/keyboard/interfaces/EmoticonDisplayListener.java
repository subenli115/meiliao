package com.ziran.meiliao.im.utils.keyboard.interfaces;

import android.view.ViewGroup;

import com.ziran.meiliao.im.utils.keyboard.adpater.EmoticonsAdapter;

public interface EmoticonDisplayListener<T> {

    void onBindView(int position, ViewGroup parent, EmoticonsAdapter.ViewHolder viewHolder, T t, boolean isDelBtn);
}
