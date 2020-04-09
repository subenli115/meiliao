package com.ziran.meiliao.widget.hellocharts.formatter;


import com.ziran.meiliao.widget.hellocharts.model.SubcolumnValue;

public interface ColumnChartValueFormatter {

    public int formatChartValue(char[] formattedValue, SubcolumnValue value);

}
