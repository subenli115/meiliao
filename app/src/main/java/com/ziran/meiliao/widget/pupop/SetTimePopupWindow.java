package com.ziran.meiliao.widget.pupop;

import android.content.Context;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.bean.DailyMindBean;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.StringUtils;
import com.ziran.meiliao.widget.SwitchButton;
import com.ziran.meiliao.widget.wheelpicker.WheelPicker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/7 11:33
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/7$
 * @updateDes ${TODO}
 */


public class SetTimePopupWindow extends BasePopupWindow {

    private TextView mTvContent, tvAm, tvZw, tvPm;
    private View viewNotify;
    private SwitchButton mSwitchButton;
    private WheelPicker mWheelPicker1;
    private WheelPicker mWheelPicker2;
    private WheelPicker mWheelPicker3;
    private DailyMindBean.DataBean mDailyMind = null;
    private int mCownTime = 10;  //单位 分钟

    public SetTimePopupWindow(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.popupw_set_time;
    }

    private boolean isSetSpanMaxMin;

    @Override
    protected void initViews(View contentView) {
        View.OnTouchListener onTouchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        };
        touchDismiss(R.id.background);
        ViewUtil.getView(contentView, R.id.ll_set_time_content).setOnTouchListener(onTouchListener);
        ViewUtil.getView(contentView, R.id.ll_set_time_label).setOnTouchListener(onTouchListener);
        ViewUtil.getView(contentView, R.id.ll_set_time_number).setOnTouchListener(onTouchListener);
        mTvContent = ViewUtil.getView(contentView, R.id.tv_set_time_content);
        viewNotify = ViewUtil.getView(contentView, R.id.ll_set_time_notify);
        mSwitchButton = ViewUtil.getView(contentView, R.id.sb_set_time_switch);
        tvAm = ViewUtil.getView(contentView, R.id.tv_set_time_am);
        tvZw = ViewUtil.getView(contentView, R.id.tv_set_time_zw);
        tvPm = ViewUtil.getView(contentView, R.id.tv_set_time_pm);
        mWheelPicker1 = ViewUtil.getView(contentView, R.id.weelPicker1);
        mWheelPicker2 = ViewUtil.getView(contentView, R.id.weelPicker2);
        mWheelPicker3 = ViewUtil.getView(contentView, R.id.weelPicker3);
        mDailyMind = new DailyMindBean.DataBean();
        WheelPicker.OnItemSelectedListener OnItemSelectedListener = new WheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelPicker picker, Object data, int position) {
                switch (mMode) {
                    case MODE_SET_COWN_TIME:
                        mTvContent.setText(StringUtils.format("%s小时%s分钟", mWheelPicker1.getSelectData().toString(),
                                mWheelPicker2.getSelectData().toString()));
                        break;
                    case MODE_SET_TIME_SPAN:
                        if (picker == mWheelPicker1) {
                            List<String> muinteList = new ArrayList<>();
                            if (position == mWheelPicker1.getData().size() - 1) {
                                if (!isSetSpanMaxMin) {
                                    for (int i = 0; i < maxMuinte; i++) {
                                        muinteList.add(String.valueOf(i));
                                    }
                                    mWheelPicker2.setData(muinteList);
                                    isSetSpanMaxMin = true;
                                }
                            } else {
                                if (isSetSpanMaxMin) {
                                    isSetSpanMaxMin = false;
                                    for (int i = 0; i < 60; i++) {
                                        muinteList.add(String.valueOf(i));
                                    }
                                    mWheelPicker2.setData(muinteList);
                                }
                            }
                        }
                        mTvContent.setText(StringUtils.format("每%s小时%s分钟提醒", mWheelPicker1.getSelectData().toString(),
                                mWheelPicker2.getSelectData().toString()));
                        break;
                    case MODE_SET_TIME_NOTIFY:
                        notifyHasChange = true;
                        String am = mWheelPicker1.getSelectString();
                        String noon = mWheelPicker2.getSelectString();
                        String pm = mWheelPicker3.getSelectString();
                        mTvContent.setText( getNotifyTime("无", am, noon, pm));
                        break;
                }
            }
        };
        mWheelPicker1.setOnItemSelectedListener(OnItemSelectedListener);
        mWheelPicker2.setOnItemSelectedListener(OnItemSelectedListener);
        mWheelPicker3.setOnItemSelectedListener(OnItemSelectedListener);
        mSwitchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                notifyHasChange = true;
            }
        });
    }

    public String getNotifyTime(String tag, String am, String noon, String pm) {
        String moText = tag.equals(am) ? "" : String.format("上午%s时 ", am);
        String noonText = tag.equals(noon) ? "" : String.format("中午%s时 ", noon);
        String nightText = tag.equals(pm) ? "" : String.format("下午%s时", pm);
        String format = StringUtils.format("%s%s%s", moText, noonText, nightText);
        if (TextUtils.isEmpty(format)) format = "每天正念时间";
        return format;
    }


    private boolean notifyHasChange = false;

    @Override
    public void onClick(View v) {

    }

    private int mMode = -1;
    public static final int MODE_SET_COWN_TIME = 10;
    public static final int MODE_SET_TIME_SPAN = 11;
    public static final int MODE_SET_TIME_NOTIFY = 12;
    private int maxMuinte;

    public void setMode(int mode) {
        notifyHasChange = false;
        mWheelPicker1.setSelectedItemPosition(0);
        mWheelPicker2.setSelectedItemPosition(0);
        if (this.mMode != mode) {
            this.mMode = mode;
            boolean flag = mMode == MODE_SET_TIME_NOTIFY;
            setVisible(flag, viewNotify, tvAm, tvPm, tvZw, mWheelPicker3);
            mWheelPicker2.setExtraText(flag ? "时" : "分");
            switch (mMode) {
                case MODE_SET_TIME_NOTIFY:
                    mWheelPicker1.setData(Arrays.asList(mContext.getResources().getStringArray(R.array.WheelArrayAM)));
                    mWheelPicker2.setData(Arrays.asList(mContext.getResources().getStringArray(R.array.WheelArrayZoom)));
                    mSwitchButton.setChecked(mDailyMind.isAble());
                    break;
                case MODE_SET_COWN_TIME:
                    mWheelPicker1.setData(Arrays.asList(mContext.getResources().getStringArray(R.array.WheelArrayHour)));
                    mWheelPicker2.setData(Arrays.asList(mContext.getResources().getStringArray(R.array.WheelArraySecond)));
                    break;
                case MODE_SET_TIME_SPAN:
                    int maxHour = mCownTime / 60;
                    maxMuinte = mCownTime % 60;
                    List<String> hourList = new ArrayList<>();
                    List<String> muinteList = new ArrayList<>();
                    if (maxHour >= 1) {
                        for (int i = 0; i < maxHour + 1; i++) {
                            hourList.add(String.valueOf(i));
                        }
                        for (int i = 0; i < 60; i++) {
                            muinteList.add(String.valueOf(i));
                        }
                    } else {
                        hourList.add("0");
                        for (int i = 0; i < maxMuinte; i++) {
                            muinteList.add(String.valueOf(i));
                        }
                    }
                    mWheelPicker1.setData(hourList);
                    mWheelPicker2.setData(muinteList);
                    break;
            }
            mTvContent.setText(getDefaultContent(mMode));
        }
    }

    private String getDefaultContent(int mode) {
        String result = "";
        switch (mode) {
            case MODE_SET_COWN_TIME:
                result = "-小时-分钟";
                break;
            case MODE_SET_TIME_SPAN:
                result = "每-小时-分钟提醒";
                break;
            case MODE_SET_TIME_NOTIFY:
                if (mDailyMind.isAble()) {
//                    mTvContent.setText( getNotifyTime("-1", mDailyMind.getMo(), mDailyMind.getNoon(), mDailyMind.getNight()));
                } else {
                    result = "上午-时  中午-时  下午-时";
                }

                break;
        }
        return result;
    }

    @Override
    public void dismiss() {
        HandlerUtil.runMain(new Runnable() {
            @Override
            public void run() {
                RxManagerUtil.post(AppConstant.RXTag.POPUW_SET_TIME_DISMISS, mMode);
            }
        }, 500);
        super.dismiss();
    }

    public void setVisible(boolean isShow, View... views) {
        if (EmptyUtils.isNotEmpty(views)) {
            for (View view : views) {
                view.setVisibility(isShow ? View.VISIBLE : View.GONE);
            }
        }
    }

    public int getCownTime() {
        return (Integer.parseInt(mWheelPicker1.getItem().toString()) * 60 + Integer.parseInt(mWheelPicker2.getItem().toString())) * 60;
    }

    public DailyMindBean.DataBean getNotifyTime() {
        mDailyMind.setAble(mSwitchButton.isChecked());
        return mDailyMind;
    }

    public String getCownTimeStr() {
        if ("0".equals(mWheelPicker1.getItem()) && "0".equals(mWheelPicker2.getItem())) return "";
        return StringUtils.format("%s小时%s分钟", mWheelPicker1.getItem(), mWheelPicker2.getItem());
    }

    public void setDailyMind(DailyMindBean.DataBean dailyMind) {
        mDailyMind = dailyMind;
    }


    public boolean isNotifyHasChange() {
        return notifyHasChange;
    }

    public void setCownTime(int cownTime) {
        mCownTime = cownTime / 60;
    }
}