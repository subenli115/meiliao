package com.ziran.meiliao.widget.hellocharts.listener;


import com.ziran.meiliao.widget.hellocharts.model.SubcolumnValue;

public interface ColumnChartOnValueSelectListener extends OnValueDeselectListener {

    public void onValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value);

}
