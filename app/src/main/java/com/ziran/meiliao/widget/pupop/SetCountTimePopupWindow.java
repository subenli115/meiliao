package com.ziran.meiliao.widget.pupop;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.commonutils.SPUtils;
import com.ziran.meiliao.common.commonutils.TimeUtil;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.utils.AlarmUtil;
import com.ziran.meiliao.utils.HandlerUtil;
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


public class SetCountTimePopupWindow extends BasePopupWindow {

    public SetCountTimePopupWindow(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.popupw_cown_time;
    }

    private Object itemSelectedDaa;
    private TextView tvCountDownTime;

    @Override
    protected void initViews(View contentView) {
        WheelPicker wheelPicker = ViewUtil.getView(contentView, R.id.wheelPicker);
        tvCountDownTime = ViewUtil.getView(contentView, R.id.tv_count_down_time);
        wheelPicker.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelPicker picker, Object data, int position) {
                itemSelectedDaa = data;
            }
        });
        touchDismiss(R.id.touch_outside);
        setOnClickListener(R.id.tv_popuw_cown_time_ok);
        wheelPicker.setSelectedItemPosition(2);
        itemSelectedDaa = wheelPicker.getItem();
    }

    private int countTimeSecond;

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
        long countTime = SPUtils.getLong(AppConstant.ExtraKey.COUNT_DOWN_STOP);
        long currentTime = System.currentTimeMillis();
        countTimeSecond = (int) ((countTime - currentTime) / 1000);
        LogUtils.logd("time:" + (countTimeSecond) + " countTime:" + countTime + "  currentTime:" + currentTime);
        if (countTime > 0 && countTimeSecond > 0) {
            task = true;
            tvCountDownTime.setVisibility(View.VISIBLE);
            HandlerUtil.runTask(mRunnable);
        } else {
            tvCountDownTime.setVisibility(View.GONE);
            SPUtils.setLong(AppConstant.ExtraKey.COUNT_DOWN_STOP, 0);
        }
    }

    @Override
    public void dismiss() {
        task = false;
        super.dismiss();
    }

    private boolean task;
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                while (task) {
                    HandlerUtil.runMain(updateTime, 0);
                    if (countTimeSecond==0){
                        break;
                    }
                    countTimeSecond--;
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    private Runnable updateTime = new Runnable() {
        @Override
        public void run() {
            if (countTimeSecond==0){
                tvCountDownTime.setVisibility(View.GONE);
            }
            tvCountDownTime.setText(TimeUtil.formatMMSS(countTimeSecond));
        }
    };

    @Override
    public void onClick(View v) {
        int time = Integer.parseInt(itemSelectedDaa.toString());
        AlarmUtil.setAlarm(mContext, time);
        ToastUitl.showShort("定时设置成功,将在" + time + "分钟后停止播放音乐");
        dismiss();
    }


}