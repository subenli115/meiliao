package com.ziran.meiliao.ui.decompressionmuseum.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseFragmentAdapter;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.commonwidget.LoadingTip;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.commonwidget.ViewPagerFixed;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.receiver.NetUtil;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.JYGColumnBean;
import com.ziran.meiliao.ui.decompressionmuseum.fragment.CategoryDetailFragment;
import com.ziran.meiliao.ui.decompressionmuseum.fragment.CategoryRecommendFragment;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.MusicPanelFloatManager;
import com.ziran.meiliao.utils.ProgressUtil;
import com.ziran.meiliao.widget.CustomMusicPanelNewView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import rx.functions.Action1;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/10 14:57
 * @des ${导聆界面}
 * @updateAuthor $Author$
 * @updateDate 2017/8/10$
 * @updateDes ${TODO}
 */

public class ListenGuideActivity extends CommonHttpActivity<CommonPresenter, CommonModel> implements CommonContract.View<JYGColumnBean> {


    @Bind(R.id.ntb)
    NormalTitleBar ntb;
    @Bind(R.id.musicPanelView)
    CustomMusicPanelNewView mMusicPanelView;
    @Bind(R.id.tab_layout)
    TabLayout tabLayout;
    @Bind(R.id.viewpager)
    ViewPagerFixed viewPager;
    private boolean isLoadingData;

    @Override
    public int getLayoutId() {
        return R.layout.activity_listen_guide;
    }


    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    public void initView() {
        super.initView();

        ntb.setTvLeftVisiable(true, true);
        ntb.setTitleText("导聆");
        mRxManager.on(AppConstant.RXTag.NETWORK_CHANGE_STATE, new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                switch (integer) {
                    case NetUtil.NETWORK_NONE:
                        if (!isLoadingData) {
                            loadedTip.setLoadingTip(LoadingTip.LoadStatus.noWifi);
                        }
                        break;
                    case NetUtil.NETWORK_MOBILE:
                    case NetUtil.NETWORK_WIFI:
                        if (!isLoadingData) {
                            loadedTip.setOperateShown();
                            doLoading();
                        }
                        break;
                }
            }
        });
        mRxManager.on(AppConstant.RXTag.EXERCISE_PLAY, new Action1<Message>() {
            @Override
            public void call(Message msg) {
                if (msg.what == ProgressUtil.WHAT_SHOW_ALBUM_PLAY) finish();
            }
        });
        doLoading();
    }

    private void doLoading() {
        if (isLoadingData) return;
        mPresenter.getData(ApiKey.GET_ALBUM_COLUMN, MapUtils.getDefMap(false), JYGColumnBean.class);
    }

    @Override
    public void returnData(JYGColumnBean result) {
        List<Fragment> fragments = new ArrayList<>();
        List<JYGColumnBean.DataBean> resultData = result.getData();
        if (EmptyUtils.isNotEmpty(resultData)) {
            resultData.add(0, new JYGColumnBean.DataBean("推荐", "11"));
            String[] tabs = new String[resultData.size()];
            for (int i = 0; i < resultData.size(); i++) {
                JYGColumnBean.DataBean dataBean = resultData.get(i);
                tabs[i] = dataBean.getName();
                if ("推荐".equals(dataBean.getName())) {
                    fragments.add(new CategoryRecommendFragment());
                } else {
                    fragments.add(CategoryDetailFragment.newInstance(dataBean.getName(), dataBean.getColumnId()));
                }
            }
            viewPager.setAdapter(new BaseFragmentAdapter(getSupportFragmentManager(), fragments));
            tabLayout.setupWithViewPager(viewPager);
            ViewUtil.changeTabText(tabLayout, tabs);
            tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition(), false);
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                }
            });

            isLoadingData = true;
        }
    }


    public static void startAction(Activity context, int flag) {
        if (CheckUtil.check(context)) {
            Intent intent = new Intent(context, ListenGuideActivity.class);
            intent.putExtra(AppConstant.SPKey.ALBUM_FLAG, flag);
            context.startActivity(intent);
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
}
