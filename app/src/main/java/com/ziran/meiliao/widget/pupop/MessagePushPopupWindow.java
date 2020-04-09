package com.ziran.meiliao.widget.pupop;

import android.content.Context;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonutils.ViewUtil;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/7 11:33
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/7$
 * @updateDes ${TODO}
 */


public class MessagePushPopupWindow extends BasePopupWindow {

    public MessagePushPopupWindow(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.popupw_message_push;
    }

    @Override
    protected void initViews(View contentView) {
        ViewUtil.getView(contentView, R.id.iv_push_tips_close).setOnClickListener(this);
        ViewUtil.getView(contentView, R.id.iv_push_tips_goto_open).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_push_tips_close:
                dismiss();
                break;
            case R.id.iv_push_tips_goto_open:
                ToastUitl.showShort("前往设置");
                break;

        }

    }
}