package com.ziran.meiliao.ui.main.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.common.commonutils.ArrayUtils;
import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.ziran.meiliao.common.permission.PermissionUtil;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.receiver.MusicPlayStateBroadcast;
import com.ziran.meiliao.service.MusicControl;
import com.ziran.meiliao.ui.NewDecompressionmuseum.activity.FitnessExeActivity;
import com.ziran.meiliao.ui.base.CommonHttpFragment;
import com.ziran.meiliao.ui.base.PermissionActivity;
import com.ziran.meiliao.ui.bean.AdvertBean;
import com.ziran.meiliao.ui.bean.TabEntity;
import com.ziran.meiliao.ui.decompressionmuseum.activity.AlbumDetailActivity;
import com.ziran.meiliao.ui.main.fragment.NewMainHomeFragment;
import com.ziran.meiliao.ui.main.fragment.NewMainMeFragment;
import com.ziran.meiliao.ui.priavteclasses.activity.DefWebActivity;
import com.ziran.meiliao.ui.workshops.activity.CourseLibraryActivity;
import com.ziran.meiliao.ui.workshops.activity.CourseLibraryCrowdFundingDetailActivity;
import com.ziran.meiliao.ui.workshops.activity.CourseLibraryTeamDetailActivity;
import com.ziran.meiliao.utils.ExitUtil;
import com.ziran.meiliao.utils.MusicPanelFloatManager;
import com.ziran.meiliao.widget.CustomMusicPanelNewView;
import com.umeng.socialize.UMShareAPI;

import java.util.ArrayList;

import butterknife.Bind;

import static com.ziran.meiliao.app.MyAPP.mServiceManager;


/**
 * des:主界面
 * Created by xsf
 * on 2016.09.15:32
 */
public class MainNewActivity extends PermissionActivity    {

    @Bind(R.id.tab_layout)
    CommonTabLayout tabLayout;
    private String[] mTitles;
    private int[] mIconUnselectIds = {R.mipmap.tab_index_norm, R.mipmap.tab_me_norm};
    private int[] mIconSelectIds = {R.mipmap.tab_index_pre, R.mipmap.tab_me_pre};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private CommonHttpFragment mMainMeFragment;
    private NewMainHomeFragment mMainHomeFragment;
    private MusicPlayStateBroadcast mMusicPlayStateBroadcast;
    @Bind(R.id.musicPanelView)
    CustomMusicPanelNewView mMusicPanelView;

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
        Log.e("mobCourseId",""+MyAPP.getMobCourseId());
        if(MyAPP.getMobCourseId()!=null&&!MyAPP.getMobCourseId().equals("")){
            FitnessExeActivity.startAction(mContext,Integer.parseInt(MyAPP.getMobCourseId()));
            MyAPP.setMobCourseId("");
        }
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
            switch (bean.getTag()) {
                case 1:
                    AppManager.getAppManager().jumpToActivity(CourseLibraryCrowdFundingDetailActivity.class,bundle);
                    break;
                case 2:
                    AppManager.getAppManager().jumpToActivity(CourseLibraryTeamDetailActivity.class,bundle);
                    break;
                case 3:
                    AppManager.getAppManager().jumpToActivity(CourseLibraryActivity.class,bundle);
                    break;
                case 4:
                    AlbumDetailActivity.startAction(this,bean.getId());
//                    AppManager.getAppManager().jumpToActivity(AlbumDetailActivity.class,bundle);
                    break;
                case 5:
//                    ZhuanLanDetailActivity.startAction(this,bean.getIntId());
                    break;
                case 6:
                    DefWebActivity.startAction(this, bean.getDetailUrl(), null);
                    break;
            }

        }
        super.onCreate(savedInstanceState);
        MyAPP.setIsLogout(false);
        //初始化frament
        initFragment(savedInstanceState);

        //获取权限
        setPermission(PermissionUtil.getAllPermission());
        IntentFilter intentFilter = new IntentFilter(MusicControl.BROADCAST_NAME);
        intentFilter.addAction(MusicControl.BROADCAST_NAME);
        mMusicPlayStateBroadcast = new MusicPlayStateBroadcast();
        registerReceiver(mMusicPlayStateBroadcast, intentFilter);
    }


    /**
     * 初始化tab
     */
    private void initTab() {
        mTitles = ArrayUtils.getArray(this, R.array.main_bottom_tabs_new);
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        tabLayout.setTabData(mTabEntities);
        //点击监听
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
            mMainMeFragment = (NewMainMeFragment) getSupportFragmentManager().findFragmentByTag("NewMainMeFragment");
            currentTabPosition = savedInstanceState.getInt(AppConstant.HOME_CURRENT_TAB_POSITION);
        } else {

            mMainHomeFragment = new NewMainHomeFragment();
            transaction.add(R.id.fl_body, mMainHomeFragment, "MainHomeFragment");
            mMainMeFragment = new NewMainMeFragment();
            transaction.add(R.id.fl_body, mMainMeFragment, "NewMainMeFragment");
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
                transaction.show(mMainHomeFragment);
                transaction.hide(mMainMeFragment);
                transaction.commitAllowingStateLoss();
                break;
            //私家课
            case 1:
                transaction.hide(mMainHomeFragment);
                transaction.show(mMainMeFragment);
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
        MusicPanelFloatManager.getInstance().bindView(mMusicPanelView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MusicPanelFloatManager.getInstance().unBindView(mMusicPanelView);
    }

    //销毁时执行
    @Override
    protected void onDestroy() {
        FileUtil.deleteGlideCache();
        mServiceManager.stop();
        UMShareAPI.get(this).release();
        if (mMusicPlayStateBroadcast != null) {
            unregisterReceiver(mMusicPlayStateBroadcast);
        }
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
