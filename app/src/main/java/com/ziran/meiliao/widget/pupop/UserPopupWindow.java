package com.ziran.meiliao.widget.pupop;

import android.content.Context;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.ui.me.activity.ReportSelectActivity;
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


public class UserPopupWindow extends BasePopupWindow {


    private String mId;

    public UserPopupWindow(Context context,String id) {
        super(context);
        this.mContext =context;
        mId=id;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.popupw_private_user;
    }

    @Override
    protected void initViews(View contentView) {
        touchDismiss(R.id.touch_outside);
        setOnClickListener(R.id.iv_report);
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_report:
                ReportSelectActivity.startAction(mId);
                break;
        }
        dismiss();
    }


}