package com.ziran.meiliao.widget.hellocharts.formatter;


import com.ziran.meiliao.widget.hellocharts.model.BubbleValue;

public interface BubbleChartValueFormatter {

    public int formatChartValue(char[] formattedValue, BubbleValue value);
}
