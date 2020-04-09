package com.ziran.meiliao.ui.main.util;

import android.graphics.Color;

import com.ziran.meiliao.widget.WaveViewBySinCos;

import java.util.Calendar;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/11/28 16:32
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/11/28$
 * @updateDes ${TODO}
 */

public class WaveUtil {
    private WaveViewBySinCos mWaveViewBySinCos1;
    private WaveViewBySinCos mWaveViewBySinCos2;

    private int alpha = 40;

    private int am = 0xff2c7eff;//8-12
    private int zw = 0xff00c50e;//12-20
    private int pm = 0xffedc700;//20-8:
//    private int am = 0xff00c50e;//8
//    private int zw = 0xffedc700;//12:
//    private int pm = 0xff2c7eff;//20


    public WaveUtil(WaveViewBySinCos waveViewBySinCos1, WaveViewBySinCos waveViewBySinCos2) {
        mWaveViewBySinCos1 = waveViewBySinCos1;
        mWaveViewBySinCos2 = waveViewBySinCos2;
    }

    public void onRefresh() {
        mWaveViewBySinCos1.stopAnimation();
        mWaveViewBySinCos2.stopAnimation();
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int color = getColor(am);
        if (hour > 8 && hour < 12) {
            color = getColor(zw);
        } else if (hour > 12 && hour < 20) {
            color = getColor(pm);
        }
        mWaveViewBySinCos1.setWaveColor(color);
        mWaveViewBySinCos2.setWaveColor(color);
        mWaveViewBySinCos1.startAnimation();
        mWaveViewBySinCos2.startAnimation();
    }

    public int getColor(int color) {
        return Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color));
    }


    public void onPause(){
        if (mWaveViewBySinCos1!=null){
            mWaveViewBySinCos1.stopAnimation();
        }
        if (mWaveViewBySinCos2!=null){
            mWaveViewBySinCos2.stopAnimation();
        }
    }
    public void onResume(){
        if (mWaveViewBySinCos1!=null){
            mWaveViewBySinCos1.startAnimation();
        }
        if (mWaveViewBySinCos2!=null){
            mWaveViewBySinCos2.startAnimation();
        }
    }
}
