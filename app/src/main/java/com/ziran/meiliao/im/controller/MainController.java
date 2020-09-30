package com.ziran.meiliao.im.controller;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import com.yc.toollib.network.ui.NetRequestActivity;
import com.yc.toollib.network.utils.NetworkTool;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.im.activity.MainActivity;
import com.ziran.meiliao.im.activity.ReleaseWechatActivity;
import com.ziran.meiliao.im.activity.fragment.NewMeFragment;
import com.ziran.meiliao.im.adapter.ViewPagerAdapter;
import com.ziran.meiliao.im.view.MainView;
import com.ziran.meiliao.ui.main.fragment.CommunityFragment;
import com.ziran.meiliao.ui.main.fragment.MessageFragment;
import com.ziran.meiliao.ui.main.fragment.NewMainHomeFragment;
import com.ziran.meiliao.utils.Utils;

/**
 * Created by ${chenyn} on 2017/2/20.
 */

public class MainController implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private MainView mMainView;
    private MainActivity mContext;
    private NewMeFragment mMeFragment;
    private NewMainHomeFragment mHomeFragment;
    private MessageFragment messageFragment;
    private CommunityFragment communityFragment;
    private static final int REQUEST_CODE_A = 2;

    public MainController(MainView mMainView, MainActivity context) {
        this.mMainView = mMainView;
        this.mContext = context;
        setViewPager();
    }

    private void setViewPager() {
        final List<Fragment> fragments = new ArrayList<>();
        // init Fragment

        mHomeFragment = new NewMainHomeFragment();
        communityFragment = new CommunityFragment();
        messageFragment = new MessageFragment();
        mMeFragment = new NewMeFragment();

        fragments.add(mHomeFragment);
        fragments.add(communityFragment);
        fragments.add(messageFragment);
        fragments.add(mMeFragment);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(mContext.getSupportFragmentManger(),
                fragments);
        mMainView.setViewPagerAdapter(viewPagerAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
                case R.id.actionbar_contact_btn:
                    mMainView.setCurrentItem(0, false);
                    break;
                case R.id.actionbar_community_btn:
                    mMainView.setCurrentItem(1, false);
                    break;
                case R.id.actionbar_msg_btn:
                    mMainView.setCurrentItem(2, false);
                    break;
                case R.id.actionbar_me_btn:
                    mMainView.setCurrentItem(3, false);
                    break;
                case R.id.iv_fb:
                    ReleaseWechatActivity.startAction(REQUEST_CODE_A);
//                    NetRequestActivity.start(mContext);
                    break;
            }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mMainView.setButtonColor(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }



}