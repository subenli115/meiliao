package com.ziran.meiliao.widget.pupop;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;
import com.ziran.meiliao.R;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/7 11:33
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/7$
 * @updateDes ${TODO}
 */


public class UpdatePopupWindow extends BasePopupWindow {


    private final FragmentActivity activity;
    private TextView tv_update;
    private LinearLayout ll_headView;


    public UpdatePopupWindow(Context context, FragmentActivity activity) {
        super(context);
        this.mContext =context;
        this.activity=activity;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.popupw_update_head;
    }

    @Override
    protected void initViews(View contentView) {
        touchDismiss(R.id.touch_outside);
        tv_update = getView(R.id.tv_update);
        ll_headView = getView(R.id.ll_headView);
        setOnClickListener(R.id.tv_update);
        setOnClickListener(R.id.tv_cancel);
    }


public void setTitle(String title,boolean is){
        if(is){
            ll_headView.setVisibility(View.VISIBLE);
        }
    tv_update.setText(title);
}

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_update:
                ImgSelActivity.startActivity(activity,ImgSelConfig.DEFAULT_SIGN, ImgSelConfig.RequestCode);
                break;
        }
        dismiss();
    }


}