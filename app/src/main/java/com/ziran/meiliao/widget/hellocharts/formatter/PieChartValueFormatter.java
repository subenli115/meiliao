package com.ziran.meiliao.widget.hellocharts.formatter;


import com.ziran.meiliao.widget.hellocharts.model.SliceValue;

public interface PieChartValueFormatter {

    public int formatChartValue(char[] formattedValue, SliceValue value);
}
