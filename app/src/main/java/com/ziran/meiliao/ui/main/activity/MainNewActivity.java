package com.ziran.meiliao.ui.main.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentTransaction;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.luck.picture.lib.entity.LocalMedia;
import com.umeng.socialize.UMShareAPI;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonutils.ArrayUtils;
import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.im.activity.ReleaseWechatActivity;
import com.ziran.meiliao.im.activity.fragment.MeFragment;
import com.ziran.meiliao.im.activity.fragment.NewMeFragment;
import com.ziran.meiliao.ui.base.PermissionActivity;
import com.ziran.meiliao.ui.bean.AdvertBean;
import com.ziran.meiliao.ui.bean.TabEntity;
import com.ziran.meiliao.ui.main.fragment.CommunityFragment;
import com.ziran.meiliao.ui.main.fragment.MessageFragment;
import com.ziran.meiliao.ui.main.fragment.NewMainHomeFragment;
import com.ziran.meiliao.utils.ExitUtil;
import com.ziran.meiliao.widget.CustomClickListener;

import java.util.ArrayList;

import butterknife.Bind;



/**
 * des:主界面
 * Created by xsf
 * on 2016.09.15:32
 */
public class MainNewActivity extends BaseActivity {

    private static final int REQUEST_CODE_A = 2;

    @Bind(R.id.iv_fb)
    ImageView ivFb;
    @Bind(R.id.tab_layout)
    CommonTabLayout tabLayout;
    private String[] mTitles;
    private int[] mIconUnselectIds = {R.mipmap.tab_home_norm,R.mipmap.tab_community_norm,R.mipmap.tab_release,R.mipmap.tab_msg_norm, R.mipmap.tab_me_norm};
    private int[] mIconSelectIds = {R.mipmap.tab_home_pre,R.mipmap.tab_community_pre,R.mipmap.tab_release,R.mipmap.tab_msg_pre, R.mipmap.tab_me_pre};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private NewMainHomeFragment mMainHomeFragment;
    private CommunityFragment communityFragment;
    private NewMeFragment mMeFragment;
    private MessageFragment messageFragment;

    @Override
    public int getLayoutId() {
        return R.layout.act_main_new;
    }

    @Override
    public void initPresenter() {

    }


    @Override
    public void initView() {
        //初始化菜单
        initTab();
    }


    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("skip")) {
            AdvertBean.DataBean bean = intent.getParcelableExtra("skip");
           // tag：1 众筹；tag：2 团建；tag：3 课程库（忽略） ；tag：4 专辑；tag：5 专栏；tag：6 工作坊。
            Bundle bundle = new Bundle();
            bundle .putString(AppConstant.ExtraKey.FROM_ID,bean.getId());

        }
        super.onCreate(savedInstanceState);
        MyAPP.setIsLogout(false);
        //初始化frament
        initFragment(savedInstanceState);

        //获取权限
    }


    /**
     * 初始化tab
     */
    private void initTab() {
        mTitles = ArrayUtils.getArray(this, R.array.main_bottom_tabs_new);
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        ivFb.setOnClickListener(new CustomClickListener() {
            @Override
            protected void onSingleClick() {
                ReleaseWechatActivity.startAction(REQUEST_CODE_A);
            }

            @Override
            protected void onFastClick() {
                //连续点击
            }
        });
        tabLayout.setTabData(mTabEntities);
        //点击监听
        tabLayout.getIconView(2).getRootView().setEnabled(false);
        tabLayout.getIconView(2).getRootView().setClickable(false);
        tabLayout.getIconView(2).getRootView().setFocusable(false);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                SwitchTo(position);
            }


            @Override
            public void onTabReselect(int position) {
            }
        });
    }


    /**
     * 初始化碎片
     */
    private void initFragment(Bundle savedInstanceState) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int currentTabPosition = 0;
        if (savedInstanceState != null) {
            mMainHomeFragment = (NewMainHomeFragment) getSupportFragmentManager().findFragmentByTag("MainHomeFragment");
            communityFragment = (CommunityFragment) getSupportFragmentManager().findFragmentByTag("CommunityFragment");
            messageFragment = (MessageFragment) getSupportFragmentManager().findFragmentByTag("MessageFragment");
            mMeFragment = (NewMeFragment) getSupportFragmentManager().findFragmentByTag("MeFragment");
            currentTabPosition = savedInstanceState.getInt(AppConstant.HOME_CURRENT_TAB_POSITION);
        } else {

            mMainHomeFragment = new NewMainHomeFragment();
            transaction.add(R.id.fl_body, mMainHomeFragment, "MainHomeFragment");
            communityFragment = new CommunityFragment();
            transaction.add(R.id.fl_body, communityFragment, "CommunityFragment");
            messageFragment = new MessageFragment();
            transaction.add(R.id.fl_body, messageFragment, "MessageFragment");
            mMeFragment = new NewMeFragment();
            transaction.add(R.id.fl_body, mMeFragment, "MeFragment");
        }
        transaction.commit();
        SwitchTo(currentTabPosition);
        tabLayout.setCurrentTab(currentTabPosition);
    }
    /**
     * 切换
     */
    private void SwitchTo(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            //减压馆
            case 0:
                transaction.hide(messageFragment);
                transaction.hide(mMeFragment);
                transaction.hide(communityFragment);
                transaction.show(mMainHomeFragment);
                transaction.commitAllowingStateLoss();
                break;
            //私家课
            case 1:
                transaction.hide(messageFragment);
                transaction.hide(mMainHomeFragment);
                transaction.hide(mMeFragment);
                transaction.show(communityFragment);
                transaction.commitAllowingStateLoss();
                break;

            case 3:
                transaction.hide(communityFragment);
                transaction.hide(mMeFragment);
                transaction.hide(mMainHomeFragment);
                transaction.show(messageFragment);
                transaction.commitAllowingStateLoss();
                break;

            case 4:
                transaction.hide(messageFragment);
                transaction.hide(communityFragment);
                transaction.hide(mMainHomeFragment);
                transaction.show(mMeFragment);
                transaction.commitAllowingStateLoss();
                break;
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //奔溃前保存位置
        if (tabLayout != null) {
            outState.putInt(AppConstant.HOME_CURRENT_TAB_POSITION, tabLayout.getCurrentTab());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    //销毁时执行
    @Override
    protected void onDestroy() {
        FileUtil.deleteGlideCache();
        UMShareAPI.get(this).release();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        ExitUtil.keyBack(this, new ExitUtil.OnExitCallBack() {
            @Override
            public void exit(Activity context) {
                ExitUtil.exit(MainNewActivity.this);
            }
        });
    }


}
