package com.ziran.meiliao.widget.pupop;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;
import com.yuyh.library.imgsel.common.Constant;
import com.ziran.meiliao.R;
import com.ziran.meiliao.im.activity.fragment.NewMeFragment;
import com.ziran.meiliao.utils.PicassoEngine;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/7 11:33
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/7$
 * @updateDes ${TODO}
 */


public class UpdateVideoPopupWindow extends BasePopupWindow {


    private final FragmentActivity activity;
    private TextView tv_update;
    private LinearLayout ll_headView;
    NewMeFragment  newMeFragment;
    private View ll_headView2;



    public UpdateVideoPopupWindow(Context context, FragmentActivity activity) {
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
        ll_headView2 = getView(R.id.ll_headView2);
        setOnClickListener(R.id.tv_update);
        setOnClickListener(R.id.tv_cancel);
        ll_headView2.setVisibility(View.VISIBLE);
        tv_update.setText("从相册中选择");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_update:
                PictureSelector.create(activity)
                        .openGallery(PictureMimeType.ofVideo())
                        .loadImageEngine(PicassoEngine.createPicassoEngine()) // 请参考Demo GlideEngine.java
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                break;
        }
        dismiss();
    }




}