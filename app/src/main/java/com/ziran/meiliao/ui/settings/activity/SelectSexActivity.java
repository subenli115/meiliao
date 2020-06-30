package com.ziran.meiliao.ui.settings.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.entry.UserRegBean;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * 选择性别
 * Created by Administrator on 2017/1/14.
 */

public class SelectSexActivity extends BaseActivity {


    @Bind(R.id.ntb)
    NormalTitleBar ntb;
    @Bind(R.id.fl_man)
    FrameLayout flMan;
    @Bind(R.id.fl_woman)
    FrameLayout flWoman;
    @Bind(R.id.iv_sex_man)
    ImageView ivMan;
    @Bind(R.id.iv_sex_woman)
    ImageView ivWoman;
    private UserRegBean userBean;

    public static void startAction(Context context, UserRegBean userInfoBean) {
        Intent intent = new Intent(context, SelectSexActivity.class);
        intent.putExtra("USER_INFO", userInfoBean);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_input_info_two;
    }

    @Override
    public void initPresenter() {

    }

    //点击监听
    @OnClick({R.id.fl_woman, R.id.fl_man})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fl_woman:
                ivWoman.setVisibility(View.VISIBLE);
                ivMan.setVisibility(View.GONE);
                userBean.setSex("2");
                UploadUserPhotoAcitivty.startAction(mContext, userBean);
                break;
            case R.id.fl_man:
                ivMan.setVisibility(View.VISIBLE);
                ivWoman.setVisibility(View.GONE);
                userBean.setSex("1");
                UploadUserPhotoAcitivty.startAction(mContext, userBean);
                break;
        }

    }

    @Override
    public void initView() {
        if (getIntent() != null) {
            userBean = getIntent().getParcelableExtra("USER_INFO");
        }
    }

}
