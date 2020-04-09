package com.ziran.meiliao.ui.priavteclasses.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.entry.VideoSectionEntry;
import com.ziran.meiliao.envet.HistoryMediaControllerListener;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.SJKLiveDetailProfileBean;
import com.ziran.meiliao.ui.priavteclasses.AbsHorizontalLiveActivity;
import com.ziran.meiliao.ui.priavteclasses.fragment.SJKLiveDetailCommentFragment;
import com.ziran.meiliao.ui.priavteclasses.fragment.SJKLiveDetailProfileFragment;
import com.ziran.meiliao.ui.priavteclasses.fragment.SJKLiveDetailRecommendCourseFragment;
import com.ziran.meiliao.ui.priavteclasses.presenter.SJKCoursePresenter;
import com.ziran.meiliao.ui.priavteclasses.util.VideoPlayerHelper1;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.HistoryCourseMediaController;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import rx.functions.Action1;


/**
 * Created by Administrator on 2017/1/14.
 */

public class HorizontalHistoryActivity extends AbsHorizontalLiveActivity<SJKCoursePresenter, CommonModel> implements
        HistoryMediaControllerListener {

    @Bind(R.id.media_controller)
    protected HistoryCourseMediaController mMediaController;

    public static void startAction(Context context, String courseId, int liveStreaming) {
        startAction(context, courseId, liveStreaming, false);
    }

    public static void startAction(int courseId) {
        startAction(AppManager.getAppManager().currentActivity(), String.valueOf(courseId), 0, true);
    }

    public static void startAction(Context context, String courseId, int liveStreaming, boolean isFinish) {

        if (CheckUtil.check(context)) {
            Intent intent = new Intent(context, HorizontalHistoryActivity.class);
            intent.putExtra("courseId", courseId);
            intent.putExtra("liveStreaming", liveStreaming);
            context.startActivity(intent);
            if (isFinish) {
                ((Activity) context).finish();
            }
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_sjk_horizontal_history;
    }

    @Override
    protected void initVideoPlayerHelper() {
        mVideoPlayerHelper = new VideoPlayerHelper1(this);
        mVideoPlayerHelper.init(mVideoView, mLoadingView, mMediaController, coverView);
        mMediaController.setCommonMediaControllerListener(this);
        mMediaController.setHistoryMediaControllerListener(this);
        mVideoPlayerHelper.setIsLiveStreaming(0, 2);
        mIMyMediaController = mMediaController;
        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if (visibility == View.VISIBLE) {
                    mMediaController.show();
                }
            }
        });
    }

    @Override
    protected void initRx() {
        super.initRx();
        mRxManager.on(AppConstant.RXTag.VIDEO_PATH, new Action1<SJKLiveDetailProfileBean.DataBean>() {
            @Override
            public void call(final SJKLiveDetailProfileBean.DataBean bean) {
                if (bean == null) return;
                mDataBean = bean;
                mDataBean.setCourseId(courseId);
                mMediaController.setWatchCount(mDataBean.getWatchCount());
                mMediaController.setLikeCount(mDataBean.getLikeCount());
                mMediaController.setTitle(mDataBean.getTitle());
                mMediaController.setCollect(mDataBean.isIsCollect());
                mMediaController.setLikes(mDataBean.isIsLike(), false);
                mMediaController.setMax(mDataBean.getDuration());
                currentDirbean = mVideoPlayerHelper.getCurrentSection(bean);

                mVideoPath = mVideoPlayerHelper.getCurrentVideoPath(bean);
                if (bean.isHasBuy()) {
                    aRewardView.setVisibility(View.GONE);
                } else {
                    aRewardView.setVisibility(View.VISIBLE);
                    mMediaController.showShikan(true);
                    aRewardView.setCount(1);
                    aRewardView.setNeedCoin("课程", mDataBean.getNeedCoin());
                    mVideoPlayerHelper.checkShiKan(mDataBean.isShikan(), mVideoPath);
                }
            }
        });


        mRxManager.on(AppConstant.RXTag.PLAYER_END, new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                if (currentDirbean != null) {
                    updateStudy(currentDirbean, true);
                }
            }
        });
    }

    protected void updateStudy(VideoSectionEntry dirBean, boolean isPlayerEnd) {
        if (dirBean.getStudyStatus() != 2) {
            Map<String, String> stringMap = MapUtils.getOnlyCan("courseId", dirBean.getCourseId());
            if (isPlayerEnd) {
                stringMap.put("progress", "100");
            } else {
                int progress = (int) (mVideoView.getCurrentPosition() * 100 / mVideoView.getDuration());
                stringMap.put("progress", String.valueOf(progress));
            }

            mPresenter.updateStudy(ApiKey.BANK_CARD_CHECK_CARD, stringMap);
        }
    }

    private boolean isWatchUp = false;

    private void startPlaying() {
        mVideoPlayerHelper.startPlay(mVideoPath);
        if (!isWatchUp) {
            isWatchUp = true;
            mPresenter.watchUp(ApiKey.watchUp, MapUtils.getOnlyCan(AppConstant.SPKey.COURSE_ID, courseId));
        }
    }

    protected int getOffscreenPageLimit() {
        return 3;
    }

    private SJKLiveDetailProfileFragment sjkLiveDetailProfileFragment;

    protected void initFragments(List<Fragment> fragments) {
        sjkLiveDetailProfileFragment = new SJKLiveDetailProfileFragment();
        fragments.add(sjkLiveDetailProfileFragment);
        fragments.add(new SJKLiveDetailRecommendCourseFragment());
        mSJKLiveDetailCommentFragment = new SJKLiveDetailCommentFragment();
        fragments.add(mSJKLiveDetailCommentFragment);
    }

    protected int getStringArrayId() {
        return R.array.sjk_live_tabs_3;
    }

    @Override
    public void showCourseData(SJKLiveDetailProfileBean.DataBean data) {

    }

    @Override
    public void setCollect(Result result) {
        mDataBean.setCollect(!mDataBean.isCollect());
        mMediaController.setCollect(mDataBean.isCollect());
        ToastUitl.showShort(result.getResultMsg());
    }

    @Override
    public void setLike(Result result) {
        mDataBean.setLike(!mDataBean.isLike());
        mMediaController.setLikes(mDataBean.isLike(), true);
        ToastUitl.showShort(result.getResultMsg());
    }


    @Override
    public void playUrl(VideoSectionEntry dirBean) {
        LogUtils.logd("videoSectionEntry" + dirBean);
        if (mDataBean.isHasBuy() || dirBean.isFree()) {
            reStart(dirBean);
        } else {
            mVideoPlayerHelper.stop();
            mMediaController.showShikan(true);
            ToastUitl.showShort("该章节需要购买课程或成为VIP才能观看");
        }
        currentDirbean = dirBean;
    }

    private void reStart(VideoSectionEntry dirBean) {
        if (mVideoPlayerHelper.startHistoryPlay(dirBean.getUrlAndPath())) {
            mMediaController.setMax(dirBean.getDuration());
            mMediaController.startPlay();
        } else {
            mMediaController.updatePausePlay();
            HandlerUtil.runMain(new Runnable() {
                @Override
                public void run() {
                    RxManagerUtil.post(AppConstant.RXTag.CHANGE_VIDEO_PLAY_STATE,mVideoView.isPlaying());
                }
            },100);
        }

    }

    @Override
    public void playShiKan(VideoSectionEntry videoSectionEntry) {

    }

    @Override
    public int isLiveStreaming() {
        return 0;
    }

    @Override
    public void historyShiKan() {

    }

    @Override
    public void onCollect() {
        if (mDataBean != null) {
            mPresenter.postCollect(ApiKey.COLLECT_COURSE, MapUtils.getCollect(mDataBean.isCollect(), "courseIds", courseId));
        }
    }

    @Override
    public void onLike() {
        if (mDataBean != null) {
            mPresenter.postLike(ApiKey.LIKE_COURSE, MapUtils.getLike("courseId", courseId, mDataBean.isLike()));
        }
    }

    @Override
    public void playOrPause() {
        if (currentDirbean == null) {
            currentDirbean = getCurrentDirBean();
        }
        if (currentDirbean != null) {
            if (mDataBean.isHasBuy() || currentDirbean.isFree()) {
                reStart(currentDirbean);
            } else {
                mMediaController.showShikan(true);
                mVideoPlayerHelper.stop();
                ToastUitl.showShort("该章节需要购买课程或成为VIP才能观看");
            }
        }
    }

    private VideoSectionEntry getCurrentDirBean() {
        return sjkLiveDetailProfileFragment.getCurrentData();
    }


}
