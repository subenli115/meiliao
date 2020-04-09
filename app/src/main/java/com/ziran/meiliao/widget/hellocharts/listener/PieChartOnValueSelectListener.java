package com.ziran.meiliao.widget.hellocharts.listener;


import com.ziran.meiliao.widget.hellocharts.model.SliceValue;

public interface PieChartOnValueSelectListener extends OnValueDeselectListener {

    public void onValueSelected(int arcIndex, SliceValue value);

}
