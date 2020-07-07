package com.ziran.meiliao.ui.helpserver.fragment;

import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseFragmentAdapter;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.commonwidget.ViewPagerFixed;
import com.ziran.meiliao.ui.base.CommonHttpFragment;

import java.util.ArrayList;
import java.util.List;

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

public class HelpServerHomeMsgFragment extends CommonHttpFragment {


    @Bind(R.id.tab_layout)
    TabLayout tabLayout;
    @Bind(R.id.iv_help_server_home_message)
    ImageView ivHelpServerHomeMessage;
    @Bind(R.id.viewpager)
    ViewPagerFixed viewpager;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_help_server_home_msg;
    }

    @Override
    protected void initView() {
        super.initView();
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new HelpServerHomeMsgConversationFragment());
        fragments.add(new HelpServerHomeMsgConversationFragment());
        viewpager.setAdapter(new BaseFragmentAdapter(getChildFragmentManager(),fragments));
        ViewUtil.initViewPager(viewpager,tabLayout);

    }

    @OnClick(R.id.iv_help_server_home_message)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_help_server_home_message:

                break;
        }
    }

}
