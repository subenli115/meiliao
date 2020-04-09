package com.ziran.meiliao.ui.settings.activity;

import android.content.Context;
import android.content.Intent;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.settings.fragment.MessageFragment;

import butterknife.Bind;

/**
 * 减压馆 -消息界面
 * Created by Administrator on 2017/1/14.
 */

public class MessageActivity extends BaseActivity {


    @Bind(R.id.ntb)
    NormalTitleBar ntb;

    public static void startAction(Context context,int unReadCount) {
        if (MyAPP.isLogin(context)){
            Intent intent = new Intent(context, MessageActivity.class);
            intent.putExtra(AppConstant.ExtraKey.UNREAD_COUNT,unReadCount);
            context.startActivity(intent);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_common_frame;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        ntb.setTitleText(getString(R.string.massage));
        ntb.setTvLeftVisiable(true, true);
        initFragment(new MessageFragment());
    }

}
