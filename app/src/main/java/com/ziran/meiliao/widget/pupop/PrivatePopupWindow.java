package com.ziran.meiliao.widget.pupop;

import android.content.Context;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.widget.ItemGroupView;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/7 11:33
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/7$
 * @updateDes ${TODO}
 */


public class PrivatePopupWindow extends BasePopupWindow {


    private final ItemGroupView igvPrivate;

    public PrivatePopupWindow(Context context, ItemGroupView igvPrivate) {
        super(context);
        this.igvPrivate=igvPrivate;
        this.mContext =context;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.popupw_private_wechat;
    }

    @Override
    protected void initViews(View contentView) {
        touchDismiss(R.id.touch_outside);
        setOnClickListener(R.id.tv_allpeople);
        setOnClickListener(R.id.tv_self);
        setOnClickListener(R.id.tv_cancel);
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_self:
                igvPrivate.setRigthText("仅自己可见");
                break;
            case R.id.tv_allpeople:
                igvPrivate.setRigthText("所有人");

                break;
        }
        dismiss();
    }


}