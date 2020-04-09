package com.ziran.meiliao.widget.calendar.theme;

import android.graphics.Color;

/**
 * Created by Administrator on 2016/7/31.
 */
public class DefaultWeekTheme implements IWeekTheme {
    @Override
    public int colorTopLinen() {
        return Color.TRANSPARENT;
    }

    @Override
    public int colorBottomLine() {
        return Color.TRANSPARENT;
    }

    @Override
    public int colorWeekday() {
        return Color.WHITE;
    }

    @Override
    public int colorWeekend() {
        return Color.WHITE;
    }

    @Override
    public int colorWeekView() {
        return Color.TRANSPARENT;
    }

    @Override
    public int sizeLine() {
        return 4;
    }

    @Override
    public int sizeText() {
        return 16;
    }
}
