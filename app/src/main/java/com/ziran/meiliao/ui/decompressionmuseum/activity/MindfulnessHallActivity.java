package com.ziran.meiliao.ui.decompressionmuseum.activity;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseFragmentAdapter;
import com.ziran.meiliao.common.commonwidget.OnNoDoubleClickListener;
import com.ziran.meiliao.common.commonwidget.ViewPagerFixed;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.ziran.meiliao.common.irecyclerview.SimpleAnimatorListener;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.ShareActivity;
import com.ziran.meiliao.ui.bean.PracticePageBean;
import com.ziran.meiliao.ui.decompressionmuseum.fragment.MindfulnessHallCountTimeFragment;
import com.ziran.meiliao.ui.decompressionmuseum.fragment.PracticeBgVideoFragment;
import com.ziran.meiliao.utils.AnimationUtil;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.PracticeDataUtil;
import com.ziran.meiliao.utils.ProgressUtil;
import com.ziran.meiliao.widget.menu.AnnularMenu;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.wevey.selector.dialog.MDAlertDialog;
import com.wevey.selector.dialog.SimpleDialogClickListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;


/**
 * 正念馆页面 ,
 */

public class MindfulnessHallActivity extends ShareActivity {

    @Bind(R.id.viewpager)
    ViewPagerFixed mViewPagerFixed;
    @Bind(R.id.fl_mindfulness_hall_top)
    FrameLayout flMindfulnessHallTop;
    @Bind(R.id.frameLayout)
    View viewFragment;
    @Bind(R.id.iv_mindfulness_hall_close)
    View ivClose;
    @Bind(R.id.annularMenu)
    AnnularMenu mAnnularMenu;
    public int currentItem;
    private MindfulnessHallCountTimeFragment mMindfulnessHallCountTimeFragment;
    /**
     * 标记练习中是否显示面板
     */
    private boolean isShowFragment = true;

    public static void startAction(Context context) {
        if (CheckUtil.check(context)) {
            Intent intent = new Intent(context, MindfulnessHallActivity.class);
            context.startActivity(intent);
        }
    }


    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_mindfulness_hall;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isStartShare){
            isStartShare = false;
            mMindfulnessHallCountTimeFragment.getByDay();
        }
    }

    @Override
    public void initView() {
        initViewPager();
        currentItem = 0;
        if (mMindfulnessHallCountTimeFragment == null) {
            mMindfulnessHallCountTimeFragment = new MindfulnessHallCountTimeFragment();
        }
        initFragment(mMindfulnessHallCountTimeFragment);
        //点击右上角菜单监听
        mAnnularMenu.setOnMenuItemClickListener(new AnnularMenu.OnMenuItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                switch (position) {
                    case 1:
                        startActivity(RecordActivity.class);
                        break;
                    case 2:
                        ListenGuideActivity.startAction(MindfulnessHallActivity.this, 2);
                        break;
                }
            }
        });
        HandlerUtil.runMain(new Runnable() {
            @Override
            public void run() {
                mAnnularMenu.toggle();
            }
        });
    }

    private List<Fragment> fragmentList = new ArrayList<>();

    /**
     * 初始化View
     */
    private void initViewPager() {
        List<PracticePageBean.DataBean.ListBean> list = PracticeDataUtil.getPracticePageBean().getData().getList();
        for (int i = 0; i < list.size(); i++) {
            PracticeBgVideoFragment fragment = PracticeBgVideoFragment.newInstance(i, getVideoUrl(i), mOnNoDoubleClickListener, new
                    PracticeBgVideoFragment.OnPracticeFinishListener() {
                @Override
                public boolean onPracticeFinish(int index) {
                    return !isPracticeFinish;
                }
            });
            fragmentList.add(fragment);
        }
        mViewPagerFixed.setOffscreenPageLimit(fragmentList.size());
        mViewPagerFixed.setAdapter(new BaseFragmentAdapter(getSupportFragmentManager(), fragmentList));
        mViewPagerFixed.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                currentItem = position;
            }
        });
        mRxManager.on(AppConstant.RXTag.PRACTICE_CAN_SCROLL, new Action1<Boolean>() {
            @Override
            public void call(Boolean scroll) {
                mViewPagerFixed.setScanScroll(scroll);
            }
        });
    }

    public OnNoDoubleClickListener mOnNoDoubleClickListener = new OnNoDoubleClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            //正在练习中的点击隐藏和显示面板
            if (isAlphaRunning) return;
            boolean practice = mMindfulnessHallCountTimeFragment.isPracticeRunning();
            if (practice) {
                if (isShowFragment) {
                    isShowFragment = false;
                    AnimationUtil.startAlphaAnimation(false, viewFragment, mSimpleAnimatorListener);
                } else {
                    isShowFragment = true;
                    AnimationUtil.startAlphaAnimation(true, viewFragment, mSimpleAnimatorListener);
                }
            }
        }
    };
    boolean isAlphaRunning;
    private SimpleAnimatorListener mSimpleAnimatorListener = new SimpleAnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {
            isAlphaRunning = true;
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            if (!isShowFragment) {
                flMindfulnessHallTop.setVisibility(View.GONE);
                viewFragment.setVisibility(View.GONE);
            }
            isAlphaRunning = false;
        }
    };

    //关闭页面
    @OnClick(R.id.iv_mindfulness_hall_close)
    public void onViewClicked() {
        checkPractice();
    }

    private void checkPractice() {
        if (!mMindfulnessHallCountTimeFragment.isPracticeEnd()) {
            MDAlertDialog.createDialog(this, "提示", "您正在练习,是否需要退出练习?", "取消", "退出练习", new SimpleDialogClickListener() {
                @Override
                public void clickRightButton(Dialog dialog, View view) {
                    dialog.dismiss();
                    mRxManager.post(AppConstant.RXTag.EXERCISE_PLAY, HandlerUtil.obj(ProgressUtil.WHAT_IS_PRACTICE_END));
                }
            });
        } else {
            super.onBackPressed();
        }
    }


    /**
     * 显示头部菜单
     *
     * @param visibility 是否显示
     */
    public void showTopBar(int visibility) {
        flMindfulnessHallTop.setVisibility(visibility);
    }

    /**
     * 关闭右上角的菜单
     */
    public void closeMenu() {
        if (mAnnularMenu.isOpen()) {
            mAnnularMenu.toggle();
        }
    }

    /**
     * back键监听
     */
    @Override
    public void onBackPressed() {
        checkPractice();
    }

    private boolean isStartShare;

    @Override
    public void onStart(SHARE_MEDIA share_media) {
        isStartShare = true;
    }

    @Override
    public void onCancel(SHARE_MEDIA share_media) {
        super.onCancel(share_media);
        isStartShare = false;
    }

    @Override
    public void onResult(SHARE_MEDIA share_media) {
        super.onResult(share_media);
//        isStartShare = false;
    }

    @Override
    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
        super.onError(share_media, throwable);
        isStartShare = false;
    }



    /**
     * 获取当前背景图片的背景音乐
     *
     * @return 背景音乐路径
     */
    public String getVideoUrl(int index) {
        String mp4FileName = FileUtil.getExerciseBj2Mp4FileName(index);
        if (new File(mp4FileName).exists()) {
            return mp4FileName;
        } else {
            PracticePageBean.DataBean.ListBean listBean = PracticeDataUtil.get(index);
            if (EmptyUtils.isNotEmpty(listBean.getVedioPathUrl())) {
                PracticeDataUtil.downFile(listBean.getVedioPathUrl(), mp4FileName);
            }
            return listBean.getVedioPathUrl();
        }
    }

    /**
     * @return 当前背景图片路径
     */
    public String getBg() {
        return getVideoUrl(mViewPagerFixed.getCurrentItem());
    }

    /**
     * 获取当前背景的id
     *
     * @return id
     */
    public String getPreMusicId() {
        return "1";
    }

    private boolean isPracticeFinish;

    public void setStateFinish(boolean finish) {
        this.isPracticeFinish = finish;
        ivClose.setVisibility(finish ? View.GONE : View.VISIBLE);
        mAnnularMenu.setVisibility(finish ? View.GONE : View.VISIBLE);
//        ((PracticeBgVideoFragment) fragmentList.get(mViewPagerFixed.getCurrentItem())).setPracticeState(finish);
    }

    public boolean isAlphaRunning() {
        return isAlphaRunning;
    }


}


