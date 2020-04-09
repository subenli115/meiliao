package com.ziran.meiliao.widget.calendar.theme;

import android.graphics.Color;

/**
 * Created by Administrator on 2016/8/9.
 */
public class ADCircleDayTheme implements IDayTheme {
    @Override
    public int colorSelectBG() {
        return Color.parseColor("#FFA20D");
    }

    @Override
    public int colorSelectDay() {
        return  Color.parseColor("#666666");
    }

    @Override
    public int colorToday() {
        return Color.parseColor("#666666");
    }

    @Override
    public int colorMonthView() {
        return Color.parseColor("#00000000");
    }

    @Override
    public int colorWeekday() {
        return  Color.parseColor("#666666");
//        return Color.WHITE;
    }

    @Override
    public int colorWeekend() {
        return Color.parseColor("#666666");
    }

    @Override
    public int colorDecor() {
        return Color.parseColor("#666666");
    }

    @Override
    public int colorRest() {
        return Color.parseColor("#666666");
    }

    @Override
    public int colorWork() {
        return Color.parseColor("#666666");
    }

    @Override
    public int colorDesc() {
        return Color.parseColor("#666666");
    }

    @Override
    public int sizeDay() {
        return 14;
    }

    @Override
    public int sizeDesc() {
        return 13;
    }

    @Override
    public int sizeDecor() {
        return 4;
    }

    @Override
    public int dateHeight() {
        return 32;
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
