package com.ziran.meiliao.envet;

import android.widget.TextView;

import com.ziran.meiliao.widget.CustomGridView;

public interface FitnessMediaControllerListener {
    void showList(CustomGridView gridView,TextView tvLeft, TextView tvRight);
    void buy();
    void left(TextView tvLeft, TextView tvRight);
    void right(TextView tvLeft, TextView tvRight);
}