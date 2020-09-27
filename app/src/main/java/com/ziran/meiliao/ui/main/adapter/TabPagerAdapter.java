package com.ziran.meiliao.ui.main.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ziran.meiliao.im.activity.fragment.ConversationListFragment;
import com.ziran.meiliao.ui.main.fragment.FollowDynamicFragment;
import com.ziran.meiliao.ui.main.fragment.MessageNoticeFragment;
import com.ziran.meiliao.ui.main.fragment.RecommendDynamicFragment;
import com.ziran.meiliao.ui.main.fragment.RecommendFollowDynamicFragment;

import java.util.ArrayList;
import java.util.List;


public class TabPagerAdapter extends FragmentPagerAdapter {
    private final String mType;
    private final int mSize;
    private List<Fragment> fragmentList = new ArrayList<>();
        private String[] strings = {"关注", "推荐"};
    private String[] otherStrings = {"通知", "聊天"};
        public TabPagerAdapter(FragmentManager fm, String type, int size) {
            super(fm);
            mType=type;
            mSize=size;
        }

        @Override
        public int getCount() {
                return 2;
        }

        @Override
        public Fragment getItem(int position) {
            if(mType.equals("1")){
                if(mSize==0){
                    fragmentList.add(new FollowDynamicFragment());
                }else {
                    fragmentList.add(new RecommendFollowDynamicFragment());
                }
                fragmentList.add(new RecommendDynamicFragment());
            }else {
                fragmentList.add(new MessageNoticeFragment());
                fragmentList.add(new ConversationListFragment());
            }
            return fragmentList.get(position);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            if(mType.equals("1")){
                return strings[position];
            }else {
                return otherStrings[position];
            }
        }
}
