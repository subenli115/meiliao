package com.ziran.meiliao.im.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import com.ziran.meiliao.R;
import com.ziran.meiliao.im.activity.fragment.FriendFragment;
import com.ziran.meiliao.im.activity.fragment.GroupFragment;
import com.ziran.meiliao.im.adapter.ViewPagerAdapter;
import com.ziran.meiliao.im.view.NoScrollViewPager;

/**
 * Created by ${chenyn} on 2017/11/7.
 */

public class VerificationMessageActivity extends FragmentActivity {

    private NoScrollViewPager mPager;
    private List<Fragment> mFragmentList;
    private RadioGroup mRg;
    private int mCurTabIndex;
    private ImageButton mReturn_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_message);
        initData();
    }

    private void initData() {
        mPager = findViewById(R.id.verification_viewpager);
        mRg = findViewById(R.id.rg_verification);
        mReturn_btn = findViewById(R.id.return_btn);

        mFragmentList = new ArrayList<>();
        mFragmentList.add(new FriendFragment());
        mFragmentList.add(new GroupFragment());
        mRg.check(R.id.rb_friend);

        mPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), mFragmentList));

        mRg.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_friend:
                    mCurTabIndex = 0;
                    break;
                case R.id.rb_group:
                    mCurTabIndex = 1;
                    break;
                default:
                    break;
            }
            mPager.setCurrentItem(mCurTabIndex, false);
        });

        mReturn_btn.setOnClickListener(v -> finish());
    }

}
