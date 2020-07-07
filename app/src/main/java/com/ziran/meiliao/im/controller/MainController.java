package com.ziran.meiliao.im.controller;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import com.ziran.meiliao.R;
import com.ziran.meiliao.im.activity.MainActivity;
import com.ziran.meiliao.im.activity.ContactsActivity;
import com.ziran.meiliao.im.activity.fragment.ConversationListFragment;
import com.ziran.meiliao.im.activity.fragment.MeFragment;
import com.ziran.meiliao.im.activity.fragment.NewMainHomeFragment;
import com.ziran.meiliao.im.adapter.ViewPagerAdapter;
import com.ziran.meiliao.im.view.MainView;

/**
 * Created by ${chenyn} on 2017/2/20.
 */

public class MainController implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private MainView mMainView;
    private MainActivity mContext;
    private ConversationListFragment mConvListFragment;
    private ContactsActivity mContactsFragment;
    private NewMainHomeFragment mHomeFragment;
    private MeFragment mMeFragment;


    public MainController(MainView mMainView, MainActivity context) {
        this.mMainView = mMainView;
        this.mContext = context;
        setViewPager();
    }

    private void setViewPager() {
        final List<Fragment> fragments = new ArrayList<>();
        // init Fragment
        mHomeFragment = new NewMainHomeFragment();
        mConvListFragment = new ConversationListFragment();
        mMeFragment = new MeFragment();
        fragments.add(mHomeFragment);
        fragments.add(mConvListFragment);
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
            case R.id.actionbar_msg_btn:
                mMainView.setCurrentItem(1, false);
                break;
            case R.id.actionbar_me_btn:
                mMainView.setCurrentItem(2, false);
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

    public void sortConvList() {
        mConvListFragment.sortConvList();
    }


}