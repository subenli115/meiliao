package com.ziran.meiliao.widget.pupop;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.utils.HandlerUtil;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/7 11:33
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/7$
 * @updateDes ${TODO}
 */


public class EditCommentPopupWindow extends BasePopupWindow {
    TextView tvResult;

    public EditCommentPopupWindow(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.popupw_edit_comment;
    }

    @Override
    protected void initViews(View contentView) {
        tvResult = ViewUtil.getView(contentView, R.id.tv_popupwindow_result);
    }

    public void setResult(boolean result) {
        if (result) {
            tvResult.setText("购买成功");
            tvResult.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.pop_icon_success, 0, 0);
        } else {
            tvResult.setText("购买失败");
            tvResult.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.pop_icon_fault, 0, 0);
        }
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
        HandlerUtil.runMain(new Runnable() {
            @Override
            public void run() {
                dismiss();
            }
        }, 2000);
    }

}