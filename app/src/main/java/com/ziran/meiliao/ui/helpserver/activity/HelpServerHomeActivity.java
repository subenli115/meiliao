package com.ziran.meiliao.ui.helpserver.activity;

import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.ui.helpserver.fragment.HelpServerHomeMeFragment;
import com.ziran.meiliao.ui.helpserver.fragment.HelpServerHomeMsgFragment;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 我的活动界面
 * Created by Administrator on 2017/1/4.
 */

public class HelpServerHomeActivity extends BaseActivity {


    @Bind(R.id.iv_help_server_home_page)
    TextView ivHelpServerHomePage;
    @Bind(R.id.iv_help_server_home_me)
    TextView ivHelpServerHomeMe;

    private HelpServerHomeMsgFragment mHelpServerHomeMsgFragment;
    private HelpServerHomeMeFragment mHelpServerHomeMeFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_help_server;
    }

    @Override
    public void initPresenter() {

    }


    @Override
    public void initView() {
        mHelpServerHomeMsgFragment = new HelpServerHomeMsgFragment();
        mHelpServerHomeMeFragment = new HelpServerHomeMeFragment();
        changePosition(ivHelpServerHomePage, ivHelpServerHomeMe, 0);
    }


    @OnClick({R.id.iv_help_server_home_page, R.id.iv_help_server_home_me})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.iv_help_server_home_page:
                changePosition(view, ivHelpServerHomeMe, 0);
                break;
            case R.id.iv_help_server_home_me:
                changePosition(view, ivHelpServerHomePage, 1);
                break;
        }
    }

    private void changePosition(View view, View otherView, int item) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!view.isSelected()) {
            view.setSelected(true);
            otherView.setSelected(false);
            if (item == 0) {
                transaction.show(mHelpServerHomeMsgFragment).show(mHelpServerHomeMeFragment);
            } else {
                transaction.show(mHelpServerHomeMeFragment).show(mHelpServerHomeMsgFragment);
            }
        }
        transaction.commit();
    }


}