package com.ziran.meiliao.utils;

import android.view.View;
import android.widget.ImageView;

import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonutils.TimeUtil;
import com.ziran.meiliao.common.listener.PlayPauseListener;
import com.ziran.meiliao.entry.MusicEntry;
import com.ziran.meiliao.envet.OnProgressChangeListener;
import com.ziran.meiliao.service.ServiceManager;
import com.ziran.meiliao.ui.priavteclasses.util.ZLStudyUtil;
import com.ziran.meiliao.ui.priavteclasses.util.ZhuanLanMusicDataConfig;
import com.ziran.meiliao.widget.CustomMusicPanelNewView;
import com.ziran.meiliao.widget.PlayPauseView;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/11/24 16:53
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/11/24$
 * @updateDes ${TODO}
 */

public class MusicPanelFloatManager {
    public static CustomMusicPanelNewView mMusicPanelView = null;
    private ProgressUtil mProgressUtil;
    private RotationAnimationUtil mRotationAnimationUtil;
    private ImageView mIvCover;
    private boolean isShowing;
    private static MusicPanelFloatManager instance;
    private PlayPauseListener playPauseView;
    public static MusicEntry zhuanLanMusic;

    private static MusicEntry mCurrentMusicEntry;


    private MusicPanelFloatManager() {
        mProgressUtil = ProgressUtil.get();
    }

    public static MusicPanelFloatManager getInstance() {
        if (instance == null) {
            synchronized (MusicPanelFloatManager.class) {
                if (instance == null) {
                    instance = new MusicPanelFloatManager();
                }
            }
        }
        return instance;
    }


    public boolean isShowing() {
        return isShowing;
    }


    public void setIsShowingAnimation(boolean isShowing) {
        if (mMusicPanelView == null || this.isShowing == isShowing) return;
//        if (mMusicPanelView == null || this.isShowing == isShowing || !MyAPP.mServiceManager.isPlaying()) return;
        this.isShowing = isShowing;
        AnimationUtil.startAnimationVer(isShowing, mMusicPanelView, true, 300, null);
    }

    public void setIsShowing(boolean isShowing) {
        if (mMusicPanelView == null) return;
        this.isShowing = isShowing;
        mMusicPanelView.setVisibility(isShowing ? View.VISIBLE : View.GONE);

    }

    public void bindView(CustomMusicPanelNewView musicPanelView) {
        try {
            if ( MyAPP.mServiceManager.getClick_from() ==  ServiceManager.CLICK_FROM_NONE ) {
                musicPanelView.setVisibility(View.GONE);
                return;
            }
            if (MyAPP.mServiceManager.getClick_from() == ServiceManager.CLICK_FROM_URL && !isZhuanLan){
                musicPanelView.setVisibility(View.GONE);
                return;
            }
            if(MyAPP.mServiceManager.getClick_from() ==ServiceManager.CLICK_FROM_ALBUM){
                musicPanelView.setVisibility(View.VISIBLE);
            }
            mMusicPanelView = musicPanelView;
            if (!MyAPP.mServiceManager.isPlayStop()) {
                if (isZhuanLan){
                    CustomMusicPanelNewView mCustomMusicPanelView = mMusicPanelView;
                    if (mCustomMusicPanelView.getMusicId() != zhuanLanMusic.getId()) {
                        mCustomMusicPanelView.setTvMusicPanelTitle(zhuanLanMusic.getName());
                        mCustomMusicPanelView.setTvMusicPanelDuration(zhuanLanMusic.getDuration());
                        mCustomMusicPanelView.setIvMusicPanelCover(zhuanLanMusic.getSharePic());
                        mCustomMusicPanelView.setIsZhuanLan(true);
                        mCustomMusicPanelView.setMusicUrl(zhuanLanMusic.getUrl());
                        mCustomMusicPanelView.setMusicId(zhuanLanMusic.getId());
                        mCustomMusicPanelView.setSubscriptionId(zhuanLanMusic.getAlbumId());
                    }
                    mCustomMusicPanelView.updatePlayState(MyAPP.mServiceManager.isPlaying());
                }else{
                    setCurrentMusic(mCurrentMusicEntry);
                }
                mProgressUtil.registerProgressListener(mMusicPanelView);
                setIsShowing(true);
            }
            if (mRotationAnimationUtil == null) {
                mRotationAnimationUtil = new RotationAnimationUtil(getTargetView());
            } else {
                mRotationAnimationUtil.setTarget(getTargetView());
            }
            updateRotationAnimation();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private View getTargetView() {
        return mIvCover != null ? mIvCover : mMusicPanelView.getIvCover();
    }

    public void unBindView(CustomMusicPanelNewView musicPanelView) {
        try {
            if (mMusicPanelView == null) return;
            mProgressUtil.unRegisterProgressListener(musicPanelView);
            mMusicPanelView = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePlayState() {
        if (mMusicPanelView != null) mMusicPanelView.updatePlayState();
        updateRotationAnimation();
        updatePlayPauseState();
    }

    public void updatePlayPauseState() {
        boolean flag = MyAPP.mServiceManager.isPlaying();
        if (playPauseView != null) {
            playPauseView.toggle(flag);
        }

    }

    public void updateRotationAnimation() {
        if (MyAPP.mServiceManager.isPlaying() && mRotationAnimationUtil != null) {
            mRotationAnimationUtil.pause();
            mRotationAnimationUtil.start();
        }
    }

    public void onDestroy() {
        if (mRotationAnimationUtil != null) mRotationAnimationUtil.onDestroy();
        if (mProgressUtil != null) mProgressUtil.onDestroy();
    }

    public void startPlay() {
        if (mProgressUtil != null) {
            mProgressUtil.startUpdateAsTime(16);
        }
        if (mRotationAnimationUtil != null) {
            mRotationAnimationUtil.start();
        }
    }


    public void setMusicPrepare(MusicEntry musicEntry) {
        getInstance();
        mProgressUtil.setPlayMode(ProgressUtil.PLAY_MODE_MUSIC);
        mProgressUtil.setMax(TimeUtil.getMax(musicEntry.getDuration()));

        mCurrentMusicEntry = musicEntry;
        setCurrentMusic(musicEntry);

    }

    private boolean isZhuanLan;
    public void setData(String name, String duration, String picture, String url, String musicId,boolean iszl) {
        if (zhuanLanMusic==null){
            zhuanLanMusic = new MusicEntry();
        }
        if(iszl){
            isZhuanLan = true;
        }else {
            isZhuanLan = false;
        }
        zhuanLanMusic.setName(name);
        zhuanLanMusic.setDuration(duration);
        zhuanLanMusic.setSharePic(picture);
        zhuanLanMusic.setUrl(url);
        zhuanLanMusic.setId(Long.parseLong(musicId));
    }

    public void setData(String name, String duration, String picture, String url, String musicId,String subscriptionId) {
        if (zhuanLanMusic==null){
            zhuanLanMusic = new MusicEntry();
        }
        isZhuanLan = true;
        zhuanLanMusic.setName(name);
        zhuanLanMusic.setDuration(duration);
        zhuanLanMusic.setSharePic(picture);
        zhuanLanMusic.setUrl(url);
        zhuanLanMusic.setId(Long.parseLong(musicId));
        zhuanLanMusic.setAlbumId(subscriptionId);
    }
    private void setCurrentMusic(MusicEntry musicEntry) {
        if (mMusicPanelView == null) return;
        isZhuanLan = false;
        ZLStudyUtil.unRegister();
        CustomMusicPanelNewView mCustomMusicPanelView = mMusicPanelView;
        if (musicEntry!=null&&
                mCustomMusicPanelView.getMusicId() != musicEntry.getId()) {
            mCustomMusicPanelView.setTvMusicPanelTitle(musicEntry.getName());
            mCustomMusicPanelView.setTvMusicPanelDuration(musicEntry.getDuration());
            mCustomMusicPanelView.setIvMusicPanelCover(MyAPP.mServiceManager.getAlbumPicture());
            mCustomMusicPanelView.setMusicUrl(musicEntry.getAESUrl());
            mCustomMusicPanelView.setIsZhuanLan(false);
            mCustomMusicPanelView.setMusicId(musicEntry.getId());
        }
        mCustomMusicPanelView.updatePlayState(MyAPP.mServiceManager.isPlaying());
    }

    public void onPause() {
        if (mProgressUtil != null) mProgressUtil.pauseUpdate();
        if (mRotationAnimationUtil != null) mRotationAnimationUtil.pause();

    }

    public void setVisibility(int visibility) {
        if (mMusicPanelView != null && mMusicPanelView.isShown()) {
            mMusicPanelView.setVisibility(visibility);
        }
    }

    public void setIvCover(ImageView ivCover) {
        mIvCover = ivCover;
        if (mIvCover == null) return;
        if (mRotationAnimationUtil == null) {
            mRotationAnimationUtil = new RotationAnimationUtil(mIvCover);
        } else {
            mRotationAnimationUtil.cancel();
            mRotationAnimationUtil.setTarget(mIvCover);
        }
//        LogUtils.logd("ivCover:" + ivCover + "mRotationAnimationUtilionUtil:" + mRotationAnimationUtil);
        updateRotationAnimation();
    }

    public void registerProgressListener(OnProgressChangeListener view) {
        mProgressUtil.registerProgressListener(view);
        if (MyAPP.mServiceManager.isPlaying()) {
            updatePlayState();
        }
    }

    public void registerProgressListener(OnProgressChangeListener view, String url) {
        mProgressUtil.registerProgressListener(view, url);
    }

    public void setProgressView(OnProgressChangeListener changeListener){
        mProgressUtil.setOnPracticeProgressView(changeListener);
    }

    public void setMax(int max) {
        mProgressUtil.setMax(max);
    }

    public void setProgress(int progress) {
        mProgressUtil.setCurrProgress(progress);
    }

    public void unRegisterProgressListener(OnProgressChangeListener view) {
        mProgressUtil.unRegisterProgressListener(view);
    }


    public void addPlayOrPauseView(PlayPauseListener playPauseView) {
        if (this.playPauseView != null) {
            this.playPauseView.toggle(true);
        }
        if (this.playPauseView != playPauseView) {
            this.playPauseView = playPauseView;
        }
    }


    public void removePlayOrPauseView(PlayPauseView playPauseView) {
        if (this.playPauseView != null) {
            this.playPauseView.toggle(true);
        }
        this.playPauseView = playPauseView;
    }

    public void res(boolean isStop) {
        ZhuanLanMusicDataConfig.setTarget(null);
        if (isStop) {
            MyAPP.mServiceManager.stop();
            MusicPanelFloatManager.getInstance().setIsShowing(false);
        }
    }

    public void setMedia() {


    }
}
