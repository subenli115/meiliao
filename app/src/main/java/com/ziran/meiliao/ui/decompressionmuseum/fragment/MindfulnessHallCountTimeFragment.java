package com.ziran.meiliao.ui.decompressionmuseum.fragment;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.app.WpyxConfig;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonutils.JsonUtils;
import com.ziran.meiliao.common.commonutils.NetWorkUtils;
import com.ziran.meiliao.common.commonutils.SPUtils;
import com.ziran.meiliao.common.commonutils.TimeUtil;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.constant.IConstants;
import com.ziran.meiliao.entry.MusicEntry;
import com.ziran.meiliao.service.ServiceManager;
import com.ziran.meiliao.ui.base.CommonHttpFragment;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.DailyMindBean;
import com.ziran.meiliao.ui.bean.ExerciseUploadBean;
import com.ziran.meiliao.ui.decompressionmuseum.activity.MindfulnessHallActivity;
import com.ziran.meiliao.ui.decompressionmuseum.activity.TakeNotesActivity;
import com.ziran.meiliao.ui.decompressionmuseum.contract.MindfulnessHallCountTimeContract;
import com.ziran.meiliao.ui.decompressionmuseum.presenter.MindfulnessHallCountTimePresenter;
import com.ziran.meiliao.ui.decompressionmuseum.util.MusicDingPlayerUtil;
import com.ziran.meiliao.ui.main.util.DailyMindUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.MusicPanelFloatManager;
import com.ziran.meiliao.utils.ProgressUtil;
import com.ziran.meiliao.utils.StringUtils;
import com.ziran.meiliao.widget.BottomExerciseView;
import com.ziran.meiliao.widget.CustomNumbersView;
import com.ziran.meiliao.widget.MyProgressView;
import com.ziran.meiliao.widget.RippleView;
import com.ziran.meiliao.widget.pupop.PopupWindowUtil;
import com.ziran.meiliao.widget.pupop.SetTimePopupWindow;

import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * 正念馆练习界面的fragment
 * Created by Administrator on 2017/1/7.
 */

public class MindfulnessHallCountTimeFragment extends CommonHttpFragment<MindfulnessHallCountTimePresenter, CommonModel> implements
        MindfulnessHallCountTimeContract.View {


    @Bind(R.id.tv_mindfulness_hall_cown_time_cown_time)
    TextView tvMindfulnessHallCountTimeCownTime;
    @Bind(R.id.tv_mindfulness_hall_cown_time_span)
    TextView tvMindfulnessHallCountTimeSpan;
    @Bind(R.id.tv_mindfulness_hall_cown_time_cown_notify)
    TextView tvMindfulnessHallCountTimeCownNotify;
    @Bind(R.id.ll_mindfulness_hall_cown_time_set_time)
    LinearLayout llMindfulnessHallCountTimeSetTime;
    @Bind(R.id.tv_mindfulness_hall_cown_time_album_close)
    ImageView tvMindfulnessHallCountTimeAlbumClose;
    @Bind(R.id.tv_mindfulness_hall_cown_time_album_title)
    TextView tvMindfulnessHallCountTimeAlbumTitle;
    @Bind(R.id.myProgressView)
    MyProgressView mMyProgressView;
    @Bind(R.id.rl_mindfulness_hall_exing_play_panel)
    RelativeLayout rlMindfulnessHallExingPlayPanel;
    @Bind(R.id.rippleView)
    RippleView mRippleView;
    @Bind(R.id.tv_mindfulness_hall_cown_time_cown_pause)
    TextView tvMindfulnessHallCountTimeCownPause;
    @Bind(R.id.tv_mindfulness_hall_cown_time_cown_stop)
    TextView tvMindfulnessHallCountTimeCownStop;
    @Bind(R.id.fl_mindfulness_hall_exing)
    FrameLayout flMindfulnessHallExing;
    @Bind(R.id.iv_mindfulness_hall_cown_time_cown_start)
    ImageView ivMindfulnessHallCountTimeCownStart;
    @Bind(R.id.tv_mindfulness_hall_cown_time_total_title)
    TextView tvMindfulnessHallCountTimeTotalTitle;
    @Bind(R.id.customNumbersView)
    CustomNumbersView mCustomNumbersView;
    @Bind(R.id.iv_mindfulness_hall_finish_avatar)
    ImageView ivMindfulnessHallFinishAvatar;
    @Bind(R.id.tv_mindfulness_hall_finish_restart)
    TextView tvMindfulnessHallFinishRestart;
    @Bind(R.id.fl_mindfulness_hall_finish)
    FrameLayout flMindfulnessHallFinish;
    @Bind(R.id.tv_share_day_tips)
    View tvShareDayTips;
    @Bind(R.id.bg_mask)
    View bgMask;

    private ProgressUtil progressUtil;
    @Bind(R.id.bottom_exercise_view1)
    BottomExerciseView mBottomExerciseView;

    private SetTimePopupWindow mSetTimePopupWindow;
    private MindfulnessHallActivity mMindfulnessHallActivity;
    private MusicDingPlayerUtil mMusicDingPlayerUtil;
    //  导聆选中的专辑音乐
    private MusicEntry currentMusicEntry;
    // 标记是否正在播放专辑音乐
    private boolean isPlayAlbum = false;
    //练习请求返回的数据
    private ExerciseUploadBean.DataBean mDataBean;
    //本次练习的总时间
    private int practiceDuration;
    //默认的练习时间(单位:秒)
    private int countTime = 600;
    //是否间隔时间提醒
    private boolean isSpanDing = false;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_mindfulness_hall_cown_time;
    }

    @Override
    protected void initView() {
        super.initView();
        MusicPanelFloatManager.getInstance().res(true);
        if (mSetTimePopupWindow == null) {
            mSetTimePopupWindow = new SetTimePopupWindow(getContext());
        }
        mMusicDingPlayerUtil = new MusicDingPlayerUtil();
        mMusicDingPlayerUtil.setOnDingFinishListener(new MusicDingPlayerUtil.OnDingFinishListener() {
            @Override
            public void onFinish() {
                if (isPracticeRunning()) {
                    if (isSpanDing) {
                        progressUtil.startUpdate();
                        MyAPP.mServiceManager.rePlay();
                    }
                }
            }
        });

        mMindfulnessHallActivity = (MindfulnessHallActivity) getActivity();
        mRxManager.on(AppConstant.RXTag.POPUW_SET_TIME_DISMISS, new Action1<Integer>() {
            @Override
            public void call(Integer mode) {
                String countTimeStr = mSetTimePopupWindow.getCownTimeStr();
                switch (mode) {
                    case SetTimePopupWindow.MODE_SET_COWN_TIME:
                        countTime = mSetTimePopupWindow.getCownTime();
                        countTime = countTime > 0 ? countTime : 600;
                        mSetTimePopupWindow.setCownTime(countTime);
                        tvMindfulnessHallCountTimeCownTime.setText(EmptyUtils.isNotEmpty(countTimeStr) ? countTimeStr : "设置练习时间");
                        break;
                    case SetTimePopupWindow.MODE_SET_TIME_SPAN:
                        tvMindfulnessHallCountTimeSpan.setText(EmptyUtils.isNotEmpty(countTimeStr) ? countTimeStr : "时间间隔提醒");
                        progressUtil.setSpanTime(mSetTimePopupWindow.getCownTime());
                        break;
                    case SetTimePopupWindow.MODE_SET_TIME_NOTIFY:
                        if (mSetTimePopupWindow.isNotifyHasChange()) {
                            DailyMindBean.DataBean mDailyMind = mSetTimePopupWindow.getNotifyTime();
                            SPUtils.setString(WpyxConfig.getNofityLianXi(), JsonUtils.toJson(mDailyMind));
                            DailyMindUtil.initNotify(getContext());
                            if (mDailyMind.isAble()) {
                                setTimeCownNotify(mDailyMind);

                            } else {
                                tvMindfulnessHallCountTimeCownNotify.setText("每天正念时间");
                            }
                            Map<String, String> stringMap = MapUtils.getDefMap(true);
                            stringMap.put("able", mDailyMind.isAble() ? "true" : "false");
//                            mPresenter.updateDailyMind(ApiKey.PRACTICE_UPDATE_DAILY_MIND, stringMap);
                        }
                        break;
                }
                llMindfulnessHallCountTimeSetTime.setVisibility(View.VISIBLE);
            }
        });
//        mCustomNumbersView.setTextColor(Color.WHITE);
        mRippleView.hidePicker();
        mBottomExerciseView.setOnCloseListener(new BottomExerciseView.OnCloseListener() {
            @Override
            public void onClose() {
                flMindfulnessHallFinish.setVisibility(View.VISIBLE);
                mMindfulnessHallActivity.showTopBar(View.VISIBLE);
            }
        });
        progressUtil = ProgressUtil.get();
        progressUtil.setPlayMode(ProgressUtil.PLAY_MODE_EXERCISE);
        progressUtil.setOnPracticeProgressView(mRippleView);

        //注册练习的订阅者
        mRxManager.on(AppConstant.RXTag.EXERCISE_PLAY,
                new Action1<Message>() {
            @Override
            public void call(Message msg) {
                switch (msg.what) {
                    case ProgressUtil.WHAT_END:
                    case ProgressUtil.WHAT_IS_PRACTICE_END:
                        stopPractice();
                        break;
                    case ProgressUtil.WHAT_SHOW_ALBUM_PLAY:
                        MusicEntry musicEntry = (MusicEntry) msg.obj;
                        if (EmptyUtils.isNotEmpty(musicEntry)) showAlbumPlay(musicEntry);
                        break;
                    case ProgressUtil.WHAT_SEEK_TO:
                        mMusicDingPlayerUtil.seekTo(msg.obj.toString());
                        break;
                    case ProgressUtil.WHAT_SPAN_DING:
                        if (isPlayAlbum) return;
                        isSpanDing = true;
                        MyAPP.mServiceManager.pause();
                        mMusicDingPlayerUtil.startPlayDing();
                        break;
                }
            }
        });
        mCustomNumbersView.clickFinish(false);
        //请求获取  获取每日正念时间
        mPresenter.getDailyMind(ApiKey.PRACTICE_GET_DAILY_MIND, MapUtils.getDefMap(true));
    }

    private boolean isPracticeEnd = true;

    /**
     * 开始练习
     */
    @OnClick(R.id.iv_mindfulness_hall_cown_time_cown_start)
    public void onStart(View view) {
        mMindfulnessHallActivity.showTopBar(View.GONE);
        mRxManager.post(AppConstant.RXTag.PRACTICE_CAN_SCROLL, false);
        if (mSetTimePopupWindow != null) {
            progressUtil.setMax(countTime);
            mRippleView.setMax(countTime);
        }
        view.setVisibility(View.GONE);
        llMindfulnessHallCountTimeSetTime.setVisibility(View.GONE);
        flMindfulnessHallExing.setVisibility(View.VISIBLE); //显示练习中的View对象面板

        MusicPanelFloatManager.getInstance().registerProgressListener(mMyProgressView);
        //  重置首页播放按钮
        MyAPP.mServiceManager.setPlayMode(IConstants.MPM_ORDER_PLAY); //设置循环播放模式
        MyAPP.mServiceManager.setClickFrom(ServiceManager.CLICK_FROM_EXERCISE);//设置播放练习音乐
        mRippleView.setPause(null); //开启动画
        isPracticeEnd = false;
        if (currentMusicEntry != null) {
            playAlbumMusic(currentMusicEntry);
        } else { //播放叮声音
            mMusicDingPlayerUtil.startPlayDing();
        }
        progressUtil.startUpdate(); //开始计时
        bgMask.setVisibility(View.GONE);

    }


    @OnClick({R.id.tv_mindfulness_hall_cown_time_album_close, R.id.tv_mindfulness_hall_finish_restart, R.id
            .tv_mindfulness_hall_finish_share, R.id.tv_mindfulness_hall_finish_make_notes, R.id.tv_mindfulness_hall_cown_time_cown_pause,
            R.id.tv_mindfulness_hall_cown_time_cown_stop})
    public void onViewClicked(View view) {
        mMindfulnessHallActivity.closeMenu();
        if (mMindfulnessHallActivity.isAlphaRunning()) return;
        switch (view.getId()) {
            case R.id.tv_mindfulness_hall_cown_time_album_close: //点击专辑面板关闭按钮
                if (currentMusicEntry != null) {
                    rlMindfulnessHallExingPlayPanel.setVisibility(View.INVISIBLE);
                    MyAPP.mServiceManager.stop();
                    currentMusicEntry = null;
                    isPlayAlbum = false;
                    MusicPanelFloatManager.getInstance().unRegisterProgressListener(mMyProgressView);
                    tvMindfulnessHallCountTimeSpan.setVisibility(View.VISIBLE);
                    tvMindfulnessHallCountTimeCownNotify.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.tv_mindfulness_hall_finish_restart:  //继续练习

                flMindfulnessHallFinish.setVisibility(View.INVISIBLE);
                llMindfulnessHallCountTimeSetTime.setVisibility(View.VISIBLE);
                ivMindfulnessHallCountTimeCownStart.setVisibility(View.VISIBLE);
                tvMindfulnessHallCountTimeCownTime.setText("设置练习时间");
                tvMindfulnessHallCountTimeSpan.setText("时间间隔提醒");
                mRxManager.post(AppConstant.RXTag.PRACTICE_CAN_SCROLL, true);
                tvMindfulnessHallCountTimeSpan.setVisibility(View.VISIBLE);
                tvMindfulnessHallCountTimeCownNotify.setVisibility(View.VISIBLE);
                bgMask.setVisibility(View.VISIBLE);
                progressUtil.resUpdate();
                mMindfulnessHallActivity.setStateFinish(false);
                break;
            case R.id.tv_mindfulness_hall_finish_share: //分享
                mMindfulnessHallActivity.showTopBar(View.GONE);
                flMindfulnessHallFinish.setVisibility(View.GONE);
                mBottomExerciseView.setBG(mMindfulnessHallActivity.getBg());
                if (EmptyUtils.isNotEmpty(mDataBean)) {
                    mBottomExerciseView.setCountTextView(mDataBean.getDays(), mDataBean.getTimesFormat(), mDataBean.getAmounts());
                    mBottomExerciseView.setTitleText(practiceDuration / 60);
                    mBottomExerciseView.setHisId(mDataBean.getHisId());
                    mBottomExerciseView.setShow(true);
                }
                break;
            case R.id.tv_mindfulness_hall_finish_make_notes: //跳转到做笔记界面
                Bundle bundle = new Bundle();
                bundle.putString("hisId", mDataBean.getHisId());
                bundle.putString("type", "add");
                startActivity(TakeNotesActivity.class, bundle);
                break;
            case R.id.tv_mindfulness_hall_cown_time_cown_pause: //暂停或继续
                String text = tvMindfulnessHallCountTimeCownPause.getText().toString();

                if ("暂停".equals(text)) {
                    mRippleView.stopRippleAnimation();
                    progressUtil.pauseUpdate();
                    tvMindfulnessHallCountTimeCownPause.setText("继续");
                    MyAPP.mServiceManager.pause();
                } else {
                    mRippleView.startRippleAnimation();
                    progressUtil.startUpdate();
                    MyAPP.mServiceManager.rePlay();
                    tvMindfulnessHallCountTimeCownPause.setText("暂停");
                }
                break;
            case R.id.tv_mindfulness_hall_cown_time_cown_stop: //结束练习
                stopPractice();
                break;
        }
    }


    /**
     * 结束练习
     */
    private void stopPractice() {
        Map<String, String> stringMap = MapUtils.getDefMap(true);
        practiceDuration = progressUtil.getCurrProgress();
        mMusicDingPlayerUtil.startPlayDing();
        mRippleView.stopRippleAnimation();
        progressUtil.resUpdate();
        MusicPanelFloatManager.getInstance().unRegisterProgressListener(mMyProgressView);
        MyAPP.mServiceManager.pause();
        MusicPanelFloatManager.getInstance().res(true);
        mMindfulnessHallActivity.showTopBar(View.VISIBLE);
        if (NetWorkUtils.isNetConnected(getContext())) {
            int minTime =  59;
//            int minTime = BuildConfig.DEBUG ? 6 : 60;
            if (practiceDuration > minTime) {
                stringMap.put("duration", String.valueOf(practiceDuration));
                if (currentMusicEntry != null) {
                    stringMap.put("albMusicId", String.valueOf(currentMusicEntry.getMusicId()));
                }
                stringMap.put("praMusicId", mMindfulnessHallActivity.getPreMusicId());
                mPresenter.postPractice(ApiKey.PRACTICE_UPLOAD, stringMap);
            } else {
                showShortToast("练习时间过短不作记录");
                reset();
            }
        } else {
            reset();
        }
    }

    private void reset() {
        flMindfulnessHallFinish.setVisibility(View.INVISIBLE);
        llMindfulnessHallCountTimeSetTime.setVisibility(View.VISIBLE);
        ivMindfulnessHallCountTimeCownStart.setVisibility(View.VISIBLE);
        flMindfulnessHallExing.setVisibility(View.INVISIBLE);
        rlMindfulnessHallExingPlayPanel.setVisibility(View.INVISIBLE);
        tvMindfulnessHallCountTimeCownTime.setText("设置练习时间");
        tvMindfulnessHallCountTimeSpan.setText("时间间隔提醒");
        mRxManager.post(AppConstant.RXTag.PRACTICE_CAN_SCROLL, true);
        progressUtil.resUpdate();
        mMindfulnessHallActivity.setStateFinish(false);
        bgMask.setVisibility(View.VISIBLE);
        isPracticeEnd = true;
        currentMusicEntry = null;
    }


    @OnClick({R.id.tv_mindfulness_hall_cown_time_cown_time, R.id.tv_mindfulness_hall_cown_time_span, R.id
            .tv_mindfulness_hall_cown_time_cown_notify})
    public void onViewClicked1(View view) {
        llMindfulnessHallCountTimeSetTime.setVisibility(View.GONE);
        mMindfulnessHallActivity.closeMenu();
        switch (view.getId()) {
            case R.id.tv_mindfulness_hall_cown_time_cown_time:  //显示设置练习时间
                mSetTimePopupWindow.setMode(SetTimePopupWindow.MODE_SET_COWN_TIME);
                break;
            case R.id.tv_mindfulness_hall_cown_time_span:  //显示时间间隔提醒
                mSetTimePopupWindow.setMode(SetTimePopupWindow.MODE_SET_TIME_SPAN);
                break;
            case R.id.tv_mindfulness_hall_cown_time_cown_notify:  //显示每日正念时间
                mSetTimePopupWindow.setMode(SetTimePopupWindow.MODE_SET_TIME_NOTIFY);
                break;
        }
        PopupWindowUtil.show(getActivity(), mSetTimePopupWindow);
    }
    @Override
    public void onDestroyView() {
        progressUtil.onDestroy();
        mMusicDingPlayerUtil.onDestroy();
        super.onDestroyView();
    }

    /**
     * 上传练习请求返回的数据
     *
     * @param result 练习请求返回的数据
     */
    @Override
    public void practiceUploadResult(ExerciseUploadBean result) {
        if (EmptyUtils.isNotEmpty(result.getData())) {
            mDataBean = result.getData();
            int minute = practiceDuration / 60;
            ViewUtil.setText(tvMindfulnessHallCountTimeTotalTitle, StringUtils.format("%s完成%d分钟的冥想", MyAPP.getUserInfo().getNickName(),
                    minute > 0 ? minute : 1));
            mCustomNumbersView.setDatas(mDataBean.getDays(), mDataBean.getTimesFormat(), mDataBean.getAmounts(), true);
            ImageLoaderUtils.displayCircle(getContext(), ivMindfulnessHallFinishAvatar, MyAPP.getUserInfo().getHeadImg());
        }
        isFirstPractice = mDataBean.getIsFirstPractice();
        flMindfulnessHallExing.setVisibility(View.INVISIBLE);
        rlMindfulnessHallExingPlayPanel.setVisibility(View.INVISIBLE);
        flMindfulnessHallFinish.setVisibility(View.VISIBLE);
        tvShareDayTips.setVisibility(isFirstPractice == 0 ? View.VISIBLE : View.GONE);
        mMindfulnessHallActivity.setStateFinish(true);
        isPracticeEnd = true;
        currentMusicEntry = null;
    }


    private int isFirstPractice;

    @Override
    public void showUpdateDailyMind(Result registerBean) {

    }

    @Override
    public void showGainByDay(Result result) {
        showLongToast(result.getResultMsg());
    }

    /**
     * 显示从服务器返回的每日正念时间
     *
     * @param result 服务器返回的每日正念时间
     */
    @Override
    public void showDailyMind(DailyMindBean.DataBean result) {
        mSetTimePopupWindow.setDailyMind(result);
        setTimeCownNotify(result);
    }

    private void setTimeCownNotify(DailyMindBean.DataBean result) {
        if (result.isAble()) {
//            tvMindfulnessHallCountTimeCownNotify.setText(mSetTimePopupWindow.getNotifyTime("-1", result.getMo(), result.getNoon(), result
////                    .getNight()));
        }
    }

    /**
     * 显示导聆从导聆选择音乐回来
     *
     * @param musicEntry 导聆选择的音乐
     */
    public void showAlbumPlay(MusicEntry musicEntry) {
        currentMusicEntry = musicEntry;
        tvMindfulnessHallCountTimeAlbumTitle.setText(musicEntry.getName());
        countTime = TimeUtil.getMax(musicEntry.getDuration());
        mMyProgressView.setProgressAndMax(0, countTime);
        MusicPanelFloatManager.getInstance().setMusicPrepare(musicEntry);
        rlMindfulnessHallExingPlayPanel.setVisibility(View.VISIBLE);
        if (countTime / 3600 > 0) {
            tvMindfulnessHallCountTimeCownTime.setText(StringUtils.format("%s小时%s分钟", countTime / 60, countTime % 60));
        } else {
            tvMindfulnessHallCountTimeCownTime.setText(StringUtils.format("%s分%s秒", countTime / 60, countTime % 60));
        }
        tvMindfulnessHallCountTimeSpan.setVisibility(View.GONE);
        tvMindfulnessHallCountTimeCownNotify.setVisibility(View.GONE);

        playAlbumMusic(musicEntry);
    }

    /**
     * 播放专辑音乐
     *
     * @param musicEntry 需要播放的音乐
     */
    private void playAlbumMusic(MusicEntry musicEntry) {
        if (!isPracticeEnd()) {
            MyAPP.mServiceManager.playUrl(musicEntry.getAESUrl());
        }
        isPlayAlbum = true;
    }

    /**
     * 是否正在练习
     *
     * @return true 正在练习
     */
    public boolean isPracticeEnd() {
        return isPracticeEnd;
    }

    /**
     * 是否正在练习
     *
     * @return true 正在练习
     */
    public boolean isPracticeRunning() {
        return mRippleView.isAnimationRunning();
    }

    public void getByDay() {
        if (isFirstPractice == 0) {
            mPresenter.getByDay(ApiKey.PRACTICE_GET_BY_DAY, MapUtils.getDefMap(true));
        }
    }
}
