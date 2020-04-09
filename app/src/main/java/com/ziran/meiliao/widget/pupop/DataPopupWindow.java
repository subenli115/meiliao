package com.ziran.meiliao.widget.pupop;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.common.commonutils.KeyBordUtil;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.utils.HtmlUtil;
import com.ziran.meiliao.widget.BaseItemView;
import com.ziran.meiliao.widget.wheelpicker.WheelPicker;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/6 12:01
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/6$
 * @updateDes ${TODO}
 */

public class DataPopupWindow extends BasePopupWindow {
    TextView tvTitle;
    WheelPicker wheelPicker;

    public DataPopupWindow(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.popup_data;
    }

    @Override
    protected void initViews(View contentView) {
        super.initViews(contentView);
        tvTitle = ViewUtil.getView(contentView, R.id.tv_title);
        TextView tvOk = ViewUtil.getView(contentView, R.id.tv_ok);
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        wheelPicker = ViewUtil.getView(contentView, R.id.wheelPicker);
        touchDismiss(R.id.touch_outside);
    }

    private BaseItemView mBaseItemView;

    public void setBaseItemView(BaseItemView baseItemView) {
        this.mBaseItemView = baseItemView;
    }


    public void setItemData(List<String> list, BaseItemView baseItemView, String title) {
        setItemData(list, baseItemView, title, false);
    }

    public void setItemData(List<String> list, BaseItemView baseItemView, String title, boolean closeKeyboard) {
        if (closeKeyboard) {
            KeyBordUtil.closeKeyboard(AppManager.getAppManager().currentActivity());
        }
        tvTitle.setText(HtmlUtil.format("设置%s", title));
        setData(list);
        setBaseItemView(baseItemView);
        show();
    }

    public void setData(List<String> list) {
        if (list.size()>2){
            wheelPicker.setmCurrentItemPosition(2);
        }
        wheelPicker.setData(list);
    }

    @Override
    public void dismiss() {
        if (mBaseItemView != null) {
            mBaseItemView.setContent(wheelPicker.getSelectString());
        }
        super.dismiss();
    }

    public static List<String> getPeopleData(int min, int max, int space) {
        List<String> result = new ArrayList<>();
        for (int i = min; i <= max; i += space) {
            result.add(String.valueOf(i));
        }
        return result;
    }

    public BaseItemView getItem() {
        return mBaseItemView;
    }
}
