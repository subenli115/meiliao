package com.ziran.meiliao.ui.main.fragment;


import android.graphics.Typeface;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.im.activity.fragment.ConversationListFragment;
import com.ziran.meiliao.im.utils.CommonUtils;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpFragment;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.StringDataBean;
import com.ziran.meiliao.ui.main.adapter.TabPagerAdapter;
import com.ziran.meiliao.ui.me.activity.MyFollowActivity;

import butterknife.Bind;
import butterknife.OnClick;
import cn.jpush.im.android.api.JMessageClient;


/**
 *
 * 消息主页面
 *
 */



public class MessageFragment extends CommonHttpFragment<CommonPresenter, CommonModel> implements CommonContract.View<StringDataBean> {


    @Bind(R.id.vp)
    ViewPager vp;
    @Bind(R.id.tl)
    TabLayout tl;
    @Bind(R.id.tv_status)
    TextView tvStatus;
    private int status;
    private TabPagerAdapter adapter;
    private TextView numTv;
    private ConversationListFragment fragment;
    private TextView mAllUnReadMsg;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_main_message;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(adapter!=null){
            fragment = (ConversationListFragment)adapter.getItem(1);
            fragment.sortConvList();
            fragment.setView(numTv);
            mAllUnReadMsg = (TextView)getActivity().findViewById(R.id.all_unread_number);
            if(mAllUnReadMsg.getVisibility()==View.GONE){
                numTv.setVisibility(View.GONE);
            }
        }
    }


    @Override
    protected void initView() {
        // 首先创建适配器（一个简单的继承自FragmentPagerAdapter的适配器）
        initTabFragment();
    }

    private void initTabFragment() {
         adapter = new TabPagerAdapter(getChildFragmentManager(),"2", 0);
        vp.setAdapter(adapter);
        tl.setupWithViewPager(vp);
        for (int i = 0; i < adapter.getCount(); i++) {
            TabLayout.Tab tab = tl.getTabAt(i);
            tab.setCustomView(R.layout.tab_title);
            if (i == 0) {
                // 设置第一个tab的TextView是被选择的样式
                TextView tv = tab.getCustomView().findViewById(android.R.id.text1);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                tv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                tv.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
            }else {
                 numTv = tab.getCustomView().findViewById(R.id.all_unread_number);
            }
        }
        tl.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // 获取 tab 组件
                View view = tab.getCustomView().findViewById(android.R.id.text1);
                if (null != view ) {
                    // 改变 tab 选择状态下的字体大小

                    ((TextView) view).setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                    ((TextView) view).setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    // 改变 tab 选择状态下的字体颜色
                    ((TextView) view).setTextColor(ContextCompat.getColor(getContext(), R.color.black));
                }
//                vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view = tab.getCustomView().findViewById(android.R.id.text1);
                if (null != view ) {
                    // 改变 tab 未选择状态下的字体大小
                    ((TextView) view).setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);

                    ((TextView) view).setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    // 改变 tab 未选择状态下的字体颜色
                    ((TextView) view).setTextColor(ContextCompat.getColor(getContext(), R.color.gray));
                }
//                vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void returnData(StringDataBean result) {
    }

    @OnClick({R.id.all_status,R.id.iv_follow})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.all_status:
                        if(status==1){
                            tvStatus.setText("想要聊天");
                            status=0;
                        }else {
                            tvStatus.setText("今日自闭");
                            status=1;
                        }
                break;
            case R.id.iv_follow:
                MyFollowActivity.startAction(getContext());
                break;
        }
    }
}
