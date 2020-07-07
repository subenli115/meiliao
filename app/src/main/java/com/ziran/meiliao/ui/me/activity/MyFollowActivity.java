package com.ziran.meiliao.ui.me.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.ReportListBean;
import com.ziran.meiliao.ui.settings.activity.RechargeDetailsActivity;
import com.ziran.meiliao.ui.settings.fragment.MyFollowFragment;
import com.ziran.meiliao.ui.settings.fragment.RechargeDetailsFragment;
import com.ziran.meiliao.utils.Utils;
import com.ziran.meiliao.widget.ItemGroupView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

import static com.ziran.meiliao.constant.ApiKey.ADMIN_DICT_TYPE;

/**
 * 举报内容列表
 * Created by Administrator on 2017/1/4.
 */

public class MyFollowActivity extends BaseActivity {
    @Bind(R.id.ntb)
    public NormalTitleBar ntb;

    public static void startAction(Context mContext) {
        Intent intent = new Intent(mContext, MyFollowActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_common_frame;
    }

    @Override
    public void initPresenter() {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getSupportFragmentManager().getFragments().get(0).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void initView() {
        initFragment(new MyFollowFragment());
    }
}