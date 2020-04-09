package com.ziran.meiliao.widget.calendar.theme;

import android.graphics.Color;

/**
 * Created by Administrator on 2016/8/9.
 */
public class SelectTheme implements IDayTheme {

    private int colorSelectBG;
    private int colorSelectDay;
    private int colorToday;
    private int colorDecor;
    private int colorWeekday;
    private int colorRest;


    public SelectTheme() {
        colorSelectBG = Color.parseColor("#FFA20D");
        colorSelectDay = Color.WHITE;
        colorWeekday =  colorToday = Color.parseColor("#666666");
        colorDecor = Color.parseColor("#FFA20D");
        colorRest = Color.parseColor("#70FFA20D");
    }

    @Override
    public int colorSelectBG() {
        return colorSelectBG;
    }

    @Override
    public int colorSelectDay() {
        return colorSelectDay;
    }

    @Override
    public int colorToday() {
        return colorToday;
    }

    @Override
    public int colorMonthView() {
        return Color.parseColor("#00000000");
    }

    @Override
    public int colorWeekday() {
        return colorWeekday;
    }

    @Override
    public int colorWeekend() {
        return colorWeekday;
    }

    @Override
    public int colorDecor() {
        return colorDecor;
    }

    @Override
    public int colorRest() {
        return colorRest;
    }

    @Override
    public int colorWork() {
        return colorWeekday;
    }

    @Override
    public int colorDesc() {
        return Color.parseColor("#4F4F4F");
    }

    @Override
    public int sizeDay() {
        return 16;
    }

    @Override
    public int sizeDesc() {
        return 14;
    }

    @Override
    public int sizeDecor() {
        return 4;
    }

    @Override
    public int dateHeight() {
        return 30;
    }

    @Override
    public int colorLine() {
        return Color.parseColor("#CBCBCB");
    }

    @Override
    public int smoothMode() {
        return 0;
    }
}
