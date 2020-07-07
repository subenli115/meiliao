package com.ziran.meiliao.ui.helpserver.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonwidget.ViewPagerFixed;
import com.ziran.meiliao.ui.base.CommonHttpFragment;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/4 14:02
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/4$
 * @updateDes ${TODO}
 */

public class HelpServerHomeMeFragment extends CommonHttpFragment {


    @Bind(R.id.tab_layout)
    TabLayout tabLayout;
    @Bind(R.id.iv_help_server_home_message)
    ImageView ivHelpServerHomeMessage;
    @Bind(R.id.viewpager)
    ViewPagerFixed viewpager;
    @Bind(R.id.iv_help_server_home_page)
    TextView ivHelpServerHomePage;
    @Bind(R.id.iv_help_server_home_me)
    TextView ivHelpServerHomeMe;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_help_server_home;
    }

    @Override
    protected void initView() {
        super.initView();
        
    }

    @OnClick({R.id.iv_help_server_home_message, R.id.iv_help_server_home_user, R.id.iv_help_server_home_page, R.id.iv_help_server_home_me})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_help_server_home_message:
                break;
            case R.id.iv_help_server_home_user:

                break;
            case R.id.iv_help_server_home_page:
                changePosition(view, ivHelpServerHomeMe, 0);
                break;
            case R.id.iv_help_server_home_me:
                changePosition(view, ivHelpServerHomePage, 1);
                break;
        }
    }

    private void changePosition(View view, View otherView, int item) {
        if (!view.isSelected()) {
            view.setSelected(true);
            otherView.setSelected(false);
            viewpager.setCurrentItem(item, false);
        }
    }

}
