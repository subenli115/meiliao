package com.ziran.meiliao.widget.pupop;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;

import com.zhy.autolayout.AutoLinearLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.im.view.SimpleAppsGridView;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/11 15:13
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/11$
 * @updateDes ${TODO}
 */

public class SimpleGivePopupWindow extends BasePopupWindow {

    private SimpleAppsGridView gridView;

    public SimpleGivePopupWindow(Context context) {
        super(context);
    }

    @Override
    protected void initViews(View contentView) {
        super.initViews(contentView);
        setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        touchDismiss(R.id.touch_outside);
        gridView = getView(R.id.gridView);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.popup_simple_give;
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }

    public void setTvMoney( String gold,String userId){
        gridView = getView(R.id.gridView);
        gridView.setBalance(gold, userId, null);
    }





}
