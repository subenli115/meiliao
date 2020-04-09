package com.ziran.meiliao.widget.hellocharts.listener;


import com.ziran.meiliao.widget.hellocharts.model.PointValue;

public interface LineChartOnValueSelectListener extends OnValueDeselectListener {

    public void onValueSelected(int lineIndex, int pointIndex, PointValue value);

}
