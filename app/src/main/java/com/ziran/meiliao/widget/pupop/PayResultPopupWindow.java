package com.ziran.meiliao.widget.pupop;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.DisplayUtil;
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


public class PayResultPopupWindow extends BasePopupWindow {
    TextView tvResult;
    private FrameLayout flContainer;
    public PayResultPopupWindow(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.popupw_payresult;
    }

    @Override
    protected void initViews(View contentView) {
        tvResult = ViewUtil.getView(contentView, R.id.tv_popupwindow_result);
        flContainer = ViewUtil.getView(contentView, R.id.fl_container);
    }

    public void setContainerLayoutParams(int width,int height){
        ViewGroup.LayoutParams params = flContainer.getLayoutParams();
        params.width = DisplayUtil.dip2px(mContext.getResources(),width);
        params.height = DisplayUtil.dip2px(mContext.getResources(),height);
    }

    public void setResult(boolean result) {
        setResult(result, "购买成功", "购买失败");
    }

    public void setResult(boolean result, String tips) {
        setResult(result, "购买成功", tips);
    }

    public void setResult(boolean result, String subTips, String fieldTips) {
        if (result) {
            tvResult.setText(subTips);
            tvResult.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.pop_icon_success, 0, 0);
        } else {
            tvResult.setText(fieldTips);
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