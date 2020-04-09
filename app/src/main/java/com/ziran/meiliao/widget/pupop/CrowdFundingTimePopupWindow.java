package com.ziran.meiliao.widget.pupop;

import android.content.Context;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.widget.wheelpicker.WheelPicker;

import java.util.ArrayList;
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


public class CrowdFundingTimePopupWindow extends BasePopupWindow {

    public CrowdFundingTimePopupWindow(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.popupw_crowd_funding_tile;
    }

    private Object itemSelectedDaa;
    private WheelPicker wheelPicker;

    @Override
    protected void initViews(View contentView) {
        wheelPicker = ViewUtil.getView(contentView, R.id.wheelPicker);
        wheelPicker.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelPicker picker, Object data, int position) {
                itemSelectedDaa = data;
            }
        });

        touchDismiss(R.id.touch_outside);
        setOnClickListener(R.id.tv_popup_ok);
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }


    public void initData(int start, int end) {
        List<String> datas = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            datas.add(String.valueOf(i));
        }
        wheelPicker.setData(datas);
    }

    public Object getData(){
        return itemSelectedDaa;
    }
}