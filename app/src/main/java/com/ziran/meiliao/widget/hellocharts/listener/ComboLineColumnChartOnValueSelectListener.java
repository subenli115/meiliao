package com.ziran.meiliao.widget.hellocharts.listener;


import com.ziran.meiliao.widget.hellocharts.model.PointValue;
import com.ziran.meiliao.widget.hellocharts.model.SubcolumnValue;

public interface ComboLineColumnChartOnValueSelectListener extends OnValueDeselectListener {

    public void onColumnValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value);

    public void onPointValueSelected(int lineIndex, int pointIndex, PointValue value);

}
