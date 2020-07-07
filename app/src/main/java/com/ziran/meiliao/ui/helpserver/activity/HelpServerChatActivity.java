package com.ziran.meiliao.ui.helpserver.activity;


import com.google.android.material.tabs.TabLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.commonwidget.ViewPagerFixed;

import butterknife.Bind;

/**
 * 助人端聊天界面
 * Created by Administrator on 2017/1/4.
 */

public class HelpServerChatActivity extends BaseActivity {


    @Bind(R.id.ntb)
    NormalTitleBar ntb;
    @Bind(R.id.tab_layout)
    TabLayout tabLayout;
    @Bind(R.id.viewpager)
    ViewPagerFixed viewpager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_help_server_chat;
    }

    @Override
    public void initPresenter() {
        
    }


    @Override
    public void initView() {

    }


}