package com.ziran.meiliao.widget.hellocharts.formatter;


import com.ziran.meiliao.widget.hellocharts.model.PointValue;

public interface LineChartValueFormatter {

    public int formatChartValue(char[] formattedValue, PointValue value);
}
