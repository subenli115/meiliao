package com.ziran.meiliao.widget.pupop;

import android.content.Context;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.utils.AlarmUtil;
import com.ziran.meiliao.widget.wheelpicker.WheelPicker;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/7 11:33
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/7$
 * @updateDes ${TODO}
 */


public class CownTimePopupWindow extends BasePopupWindow {

    public CownTimePopupWindow(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.popupw_cown_time;
    }

    private Object itemSelectedDaa;

    @Override
    protected void initViews(View contentView) {
        WheelPicker wheelPicker = ViewUtil.getView(contentView, R.id.wheelPicker);
        wheelPicker.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelPicker picker, Object data, int position) {
                itemSelectedDaa = data;
            }
        });
        touchDismiss(R.id.touch_outside);
        setOnClickListener(R.id.tv_popuw_cown_time_ok);
    }

    @Override
    public void onClick(View v) {
        int time = Integer.parseInt(itemSelectedDaa.toString());
        AlarmUtil.setAlarm(mContext, time);
        ToastUitl.showShort("定时设置成功,将在" + time + "分钟后停止播放音乐");
        dismiss();
    }


}