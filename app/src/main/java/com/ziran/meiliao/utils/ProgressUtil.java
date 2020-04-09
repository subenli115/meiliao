package com.ziran.meiliao.utils;

import android.os.Handler;
import android.os.Message;

import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.entry.ExerciseProgressEntry;
import com.ziran.meiliao.envet.OnProgressChangeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * author 吴祖清
 * create  2017/3/31 10
 * des     音乐播放进度工具类
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */

public class ProgressUtil {

    //当前进度
    private int currProgress;
    //最大值
    private int max;
    //当前播放的模式
    private int playMode = PLAY_MODE_MUSIC;
    //音乐播放模式
    public static final int PLAY_MODE_MUSIC = 0;
    //练习播放模式
    public static final int PLAY_MODE_EXERCISE = 1;

    //标记是否需要更新
    private static final int WHAT_UPDATE_ING = 20;
    /**
     * 更新练习界面的圆环进度
     */
    public static final int WHAT_UPDATE_MIND_POSITION = 0x5e01;

    /**
     * 标记按下back键时弹出对话框提示
     */
    public static final int WHAT_IS_PRACTICE_END = 0x5e03;
    /**
     * 标记是否显示练习界面的专辑播放面板
     */
    public static final int WHAT_SHOW_ALBUM_PLAY = 0x5e04;
    /**
     * 标记是否练习结束
     */
    public static final int WHAT_END = 10000000;
    /**
     * 标记是否是间隔时间提醒
     */
    public static final int WHAT_SPAN_DING = 0x5e05;
    /**
     * 标记
     */
    public static final int WHAT_SEEK_TO = 0x5e06;

    //是否运行
    private boolean isRuning;
    /**
     * 练习间隔提醒的时间
     */
    private int spanTime = -1;

    private ProgressUtil() {
    }

    public static ProgressUtil get() {
        return new ProgressUtil();
    }

    //handler对象
    private Handler mHandle = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == WHAT_UPDATE_ING) {

                Message message = getMessage();
                if (currProgress > max) {
                    message.what = 0;   //结束
                    if (playMode == PLAY_MODE_EXERCISE) {
                        RxManagerUtil.post(AppConstant.RXTag.EXERCISE_PLAY, mHandle.obtainMessage(WHAT_END));
                    }
                } else {
                    message.what = WHAT_UPDATE_MIND_POSITION;   //未结束
                    mHandle.sendEmptyMessageDelayed(WHAT_UPDATE_ING, 1000);
                }
                if (playMode == PLAY_MODE_MUSIC) {
                    refreshData(currProgress);
                } else if (playMode == PLAY_MODE_EXERCISE) {
                    if (onPracticeProgressView != null) {
                        onPracticeProgressView.setProgress(currProgress);
                    } else {
                        RxManagerUtil.post(AppConstant.RXTag.EXERCISE_PLAY, message);
                    }
                }
            }
            return false;
        }
    });

    private void refreshData(int pos) {
        for (OnProgressChangeListener updateView : updateViews) {
            updateView.setProgress(pos);
        }
        if (onPracticeProgressView!=null){
            onPracticeProgressView.setProgress(pos);
        }
    }


    private Message getMessage() {
        if (playMode == PLAY_MODE_MUSIC) {
            if (MyAPP.mServiceManager.isPlaying()) {
                if (max == 0) {
                    LogUtils.logd( "max:"+max + " currProgress:"+currProgress + " duration:"+MyAPP.mServiceManager.duration() + " position:" +MyAPP.mServiceManager.position());
                    max = MyAPP.mServiceManager.duration() / 1000;
                }
                currProgress =  MyAPP.mServiceManager.position()/1000;
            }
//            LogUtils.logd( "max:"+max + " currProgress:"+currProgress + " duration:"+MyAPP.mServiceManager.duration() + " position:" +MyAPP.mServiceManager.position() + " handler:"+mHandle);
        } else {
            currProgress++;
            //处理间隔提醒事件
            if (spanTime > 30 && currProgress % spanTime == 0) {
                RxManagerUtil.post(AppConstant.RXTag.EXERCISE_PLAY, HandlerUtil.obj(WHAT_SPAN_DING));
            }
        }
        Message message = mHandle.obtainMessage();
        message.arg1 = currProgress;
        message.arg2 = max;
        return message;
    }

    public void startUpdateAsTime(long delayMillis) {
        if (updateViews.size()==0) return;
        mHandle.removeMessages(WHAT_UPDATE_ING);
        mHandle.removeMessages(WHAT_UPDATE_ING);
        mHandle.sendEmptyMessageDelayed(WHAT_UPDATE_ING, delayMillis);
        isRuning = true;
        if (playMode == PLAY_MODE_EXERCISE) {
            Message message = getMessage();
            RxManagerUtil.post(AppConstant.RXTag.EXERCISE_PLAY_UPDATE_POSITION, message);
        }
    }

    public void startUpdate() {
        mHandle.removeMessages(WHAT_UPDATE_ING);
        mHandle.sendEmptyMessage(WHAT_UPDATE_ING);
        isRuning = true;
    }

    public void pauseUpdate() {
        mHandle.removeMessages(WHAT_UPDATE_ING);
        isRuning = false;
    }

    public void resUpdate() {
        currProgress = -1;
        max = -1;
        mHandle.removeMessages(WHAT_UPDATE_ING);
        isRuning = false;
    }

    public int getCurrProgress() {
        return currProgress;
    }

    public void setCurrProgress(int currProgress) {
        this.currProgress = currProgress;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

//    public int getPlayMode() {
//        return playMode;
//    }

    public void setPlayMode(int playMode) {
        this.playMode = playMode;
    }

    //释放资源
    public void onDestroy() {
        if (max != -1 && currProgress != -1) {
            ExerciseProgressEntry.insert(max, currProgress);
        }
        mHandle.removeCallbacksAndMessages(null);
        currProgress = -1;
        max = -1;
        spanTime = -1;
        mHandle = null;
        if (onPracticeProgressView != null) onPracticeProgressView = null;
    }

    public void onPause() {
        if (playMode == PLAY_MODE_EXERCISE) {
            ExerciseProgressEntry.insert(max, currProgress);
        }
    }

    public void onResume() {
        if (playMode == PLAY_MODE_EXERCISE) {
            ExerciseProgressEntry progressEntry = ExerciseProgressEntry.load();
            if (EmptyUtils.isNotEmpty(progressEntry)) {
                max = progressEntry.getMax();
                currProgress = progressEntry.getCurrentProgress();
            }
        }
    }


    public void setSpanTime(int spanTime) {
        this.spanTime = spanTime;
    }

    public void registerProgressListener(OnProgressChangeListener view) {
        if (view != null && !updateViews.contains(view)) {
            updateViews.add(view);
        }
        if (view != null && currProgress > 0) view.setProgress(currProgress);
        if (!isRuning && MyAPP.mServiceManager.isPlaying()) {
            startUpdate();
        }
    }

    public void registerProgressListener(OnProgressChangeListener view, String url) {
        if ( !MyAPP.mServiceManager.checkUrl(url)) return;
        if (view != null && !updateViews.contains(view)) {
            updateViews.add(view);
        }
        if (view != null && currProgress > 0) view.setProgress(currProgress);
        if (!isRuning && MyAPP.mServiceManager.isPlaying() ) {
            startUpdate();
        }
    }

    public void unRegisterProgressListener(OnProgressChangeListener view) {
        if (view != null && updateViews.contains(view)) {
            updateViews.remove(view);
        }
        if (updateViews.size() == 0) {
            mHandle.removeMessages(WHAT_UPDATE_ING);
            isRuning = false;
        }
    }

    private static List<OnProgressChangeListener> updateViews = new ArrayList<>();


    public void setOnPracticeProgressView(OnProgressChangeListener onPracticeProgressView) {
        this.onPracticeProgressView = onPracticeProgressView;
    }

    private OnProgressChangeListener onPracticeProgressView;

}
