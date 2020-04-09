package com.ziran.meiliao.ui.priavteclasses.fragment;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.TimeUtil;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.entry.VideoSectionEntry;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.SJKLiveDetailProfileBean;
import com.ziran.meiliao.ui.priavteclasses.presenter.SJKCoursePresenter;
import com.ziran.meiliao.ui.priavteclasses.util.MediaControllerUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.MyProgressView;
import com.ziran.meiliao.widget.PlayPauseView;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;


/**
 * 私家课-活动Fragment
 * Created by Administrator on 2017/1/7.
 */

public class SJKVerticalHistoryLiveFragment extends SJKVerticalLiveBaseFragment<SJKCoursePresenter, CommonModel> {

    @Bind(R.id.myProgressView)
    MyProgressView mMyProgressView;

    @Bind(R.id.playPauseView)
    PlayPauseView mPlayPauseView;
    @Bind(R.id.iv_sjk_fulllive_like)
    ImageView mIvLike;
    @Bind(R.id.iv_sjk_fulllive_collect)
    ImageView mIvCollect;
    private MediaControllerUtil mMediaControllerUtil;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_sjk_vertical_history_live;
    }

    @Override
    protected void initOther() {
        super.initOther();

        mMediaControllerUtil = new MediaControllerUtil(mHandler);
        mMediaControllerUtil.setPlayer(mVideoView);
        mMediaControllerUtil.setViews(mMyProgressView);
        mMediaControllerUtil.setPlayPauseView(mPlayPauseView);
        mVideoPlayerHelper.setPlayPauseView(mPlayPauseView);
        mRxManager.on(AppConstant.RXTag.PLAYER_END, new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                if (currentDirbean != null) {
                    updateStudy(currentDirbean, true);
                }
            }
        });
    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 200:
                    setTopBottomVisible(View.GONE);
                    break;
                case 500:
                    if (mMediaControllerUtil != null) {
                        if (mMediaControllerUtil.updateProgress()) {
                            mHandler.sendEmptyMessageDelayed(500, 800);
                        } else {
                            if (mVideoView!=null && !mVideoView.isPlaying()) {
                                mHandler.sendEmptyMessageDelayed(500, 800);
                                mMediaControllerUtil.startDismissTopBottomTimer();
                            }
                        }
                    }
                    break;
            }
        }
    };

    private void setTopBottomVisible(int vis) {
        if (mMyProgressView!=null){
            mMyProgressView.setVisibility(vis);
            mPlayPauseView.setVisibility(vis);
        }
    }

    @Override
    public void showBuyTip() {
        super.showBuyTip();
        mMyProgressView.setVisibility(View.GONE);
    }

    @Override
    protected void startPlay() {
        super.startPlay();
        mMyProgressView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPayResult(String url) {
        super.onPayResult(url);

    }


    @OnClick({R.id.iv_sjk_fulllive_like, R.id.iv_sjk_fulllive_collect, R.id.playPauseView})
    public void onViewClicked2(View view) {
        try {
            switch (view.getId()) {
                case R.id.iv_sjk_fulllive_like:
                    if (mDataBean != null) {
                        mPresenter.postLike(ApiKey.LIKE_COURSE, MapUtils.getLike(AppConstant.SPKey.COURSE_ID, courseId, mDataBean.isLike
                                ()));
                    }
                    break;
                case R.id.iv_sjk_fulllive_collect:
                    if (mDataBean != null) {
                        mPresenter.postCollect(ApiKey.COLLECT_COURSE, MapUtils.getCollect(mDataBean.isCollect(), "courseIds", courseId));
                    }
                    break;
                case R.id.playPauseView:
                    mMediaControllerUtil.doPauseResume();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showCourseData(SJKLiveDetailProfileBean.DataBean result) {
        super.showCourseData(result);
        mLveAvatarView.setContent("历史课程", String.valueOf(mDataBean.getWatchCount()));
        setCollect();
        setLike();
    }

    @Override
    public void setCollect(Result result) {
        mDataBean.setCollect(!mDataBean.isCollect());
        setCollect();
        ToastUitl.showShort(result.getResultMsg());
    }

    @Override
    public void setLike(Result result) {
        mDataBean.setLike(!mDataBean.isLike());
        ToastUitl.showShort(result.getResultMsg());
        setLike();
    }


    private void setCollect() {
        if (mDataBean.isCollect()) {
            mIvCollect.setImageResource(R.mipmap.ic_sjk_full_living_collect_sel);
        } else {
            mIvCollect.setImageResource(R.mipmap.ic_sjk_full_living_collect);
        }
    }

    private void setLike() {
        if (mDataBean.isLike()) {
            mIvLike.setImageResource(R.mipmap.ic_sjk_full_living_like_sel);
        } else {
            mIvLike.setImageResource(R.mipmap.ic_sjk_full_living_like);
        }
    }

    private VideoSectionEntry currentDirBean;

    @Override
    public void playUrl(VideoSectionEntry dirBean) {
        super.playUrl(dirBean);
        if (currentDirBean != dirBean) {
            currentDirBean = dirBean;
            mMediaControllerUtil.setMax(TimeUtil.getMax(dirBean.getDuration()));
            mMediaControllerUtil.startPlay();

        } else {
            mMediaControllerUtil.updatePausePlay();
        }

    }


    protected void showOrHide(boolean viewShown) {
        if (viewShown) {
            mMediaControllerUtil.startUpdateProgressTimer();
            mMediaControllerUtil.startDismissTopBottomTimer();
            setTopBottomVisible(View.VISIBLE);
        } else {
            mMediaControllerUtil.cancelUpdateProgressTimer();
        }
    }

}
