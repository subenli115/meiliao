package com.ziran.meiliao.widget.hellocharts.listener;


import com.ziran.meiliao.widget.hellocharts.model.BubbleValue;

public interface BubbleChartOnValueSelectListener extends OnValueDeselectListener {

    public void onValueSelected(int bubbleIndex, BubbleValue value);

}
