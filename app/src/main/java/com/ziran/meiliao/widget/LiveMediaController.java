package com.ziran.meiliao.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.pili.pldroid.player.IMediaController;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonutils.DisplayUtil;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.envet.CommonMediaControllerListener;
import com.ziran.meiliao.envet.LiveMediaControllerListener;
import com.ziran.meiliao.ui.bean.GiftBean;
import com.ziran.meiliao.ui.priavteclasses.util.MediaControllerUtil;
import com.ziran.meiliao.utils.StringUtils;
import com.ziran.meiliao.utils.UpdateVersionUtil;
import com.ziran.meiliao.widget.livegift.GiftControl;
import com.ziran.meiliao.widget.livegift.GiftFrameLayout;
import com.ziran.meiliao.widget.livegift.GiftModel;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 媒体播放器的控制器
 * Created by Administrator on 2017/1/20.
 */

public class LiveMediaController extends FrameLayout implements IMediaController, IMyMediaController {

    private MediaPlayerControl mPlayer;
    private int sDefaultTimeout = 5000;
    @Bind(R.id.rl_bottom)
    View viewBottom;
    @Bind(R.id.rl_top)
    View viewTop;
    @Bind(R.id.iv_contorller_full)
    ImageView ivFull;
    @Bind(R.id.iv_controller_message)
    ImageView ivMessage;
    @Bind(R.id.iv_controller_left_gift)
    ImageView ivLeftGift;
    @Bind(R.id.iv_controller_right_gift)
    ImageView ivRightGift;
    @Bind(R.id.iv_contorller_share)
    ImageView ivShare;
    @Bind(R.id.iv_contorller_back)
    ImageView ivBack;
    @Bind(R.id.tv_contorller_lister)
    TextView tvControllerLister;
    @Bind(R.id.tv_controller_title)
    TextView tvTitle;
    @Bind(R.id.tv_contorller_toast)
    TextView tvControllerToast;

    ViewStub viewStubChatCustomView;


    private ChatCustomView mChatCustomView;
    private int paddRight;
    private static final int FADE_OUT = 1;
    private boolean topBottomVisible;
    private MediaControllerUtil mMediaControllerUtil;

    GiftFrameLayout mGiftFrameLayout1;
    GiftFrameLayout mGiftFrameLayout2;
    private GiftControl giftControl;
    private GiftModel giftModel;

    public LiveMediaController(@NonNull Context context) {
        this(context, null);
    }

    public LiveMediaController(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LiveMediaController(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
    }

    private void initViews(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_media_controller_live, this, true);
        ButterKnife.bind(this, this);
        mMediaControllerUtil = new MediaControllerUtil(mHandler);
        ivShare.setOnClickListener(mOnClickListener);
        ivFull.setOnClickListener(mOnClickListener);
        ivBack.setOnClickListener(mOnClickListener);
        ivLeftGift.setOnClickListener(mOnClickListener);
        ivRightGift.setOnClickListener(mOnClickListener);
        ivMessage.setOnClickListener(mOnClickListener);
        ivBack.setOnClickListener(mOnClickListener);

        paddRight = DisplayUtil.getNavigationBarHeightEx(getContext()) - DisplayUtil.dip2px(2);
        if (paddRight < 0) {
            paddRight = 0;
        }
        if (viewStubChatCustomView == null) {
            viewStubChatCustomView = ViewUtil.getView(this, R.id.viewStub_chat_custom_view);
            viewStubChatCustomView.inflate();
            mChatCustomView = ViewUtil.getView(this, R.id.chatCustomView);
            mGiftFrameLayout1 = ViewUtil.getView(this, R.id.gift_layout1);
            mGiftFrameLayout2 = ViewUtil.getView(this, R.id.gift_layout2);
            giftControl = new GiftControl(mGiftFrameLayout1, mGiftFrameLayout2);
            mChatCustomView.setChatListViewWidth(getResources().getDimensionPixelSize(R.dimen.chat_width));
            mChatCustomView.setVisibility(GONE);

        }
        setAutoHide(false);
    }


    @Override
    public void setMediaPlayer(MediaPlayerControl mediaPlayerControl) {
        this.mPlayer = mediaPlayerControl;
    }

    @Override
    public void show() {
        show(sDefaultTimeout);
    }

    private boolean mShowing;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case FADE_OUT:
                    hide();
                    break;
                case 200:
                    setTopBottomVisible(false);
                    break;

            }
        }
    };

    @Override
    public void show(int time) {
        if (!canHideTopAndBottom) return;
        if (!mShowing) {
            mShowing = true;
        }
        setTopBottomVisible(true);

    }

    @Override
    public void hide() {
        if (!canHideTopAndBottom) return;
        if (mShowing) {
            mShowing = false;
        }
        setTopBottomVisible(false);
    }

    @Override
    public boolean isShowing() {
        return mPlayer.isPlaying();
    }

    @Override
    public void setAnchorView(View view) {

    }

    private CommonMediaControllerListener mCommonMediaControllerListener;
    private LiveMediaControllerListener mLiveMediaControllerListener;


    public void setAutoHide(boolean autoHide) {
        isAutoHide = autoHide;
    }

    public void setCommonMediaControllerListener(CommonMediaControllerListener commonMediaControllerListener) {
        mCommonMediaControllerListener = commonMediaControllerListener;
    }


    public void setLiveMediaControllerListener(LiveMediaControllerListener liveMediaControllerListener) {
        mLiveMediaControllerListener = liveMediaControllerListener;
        if (mLiveMediaControllerListener != null && mChatCustomView != null) {
            mLiveMediaControllerListener.getChatListView(mChatCustomView);
        }
    }


    private OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_contorller_back:
                    if (mCommonMediaControllerListener != null) {
                        mCommonMediaControllerListener.onBack();
                    }
                    return;
                case R.id.iv_contorller_full:
                    changeScreen();
                    if (mCommonMediaControllerListener != null) {
                        mCommonMediaControllerListener.changeScreen(isFullScreen);
                    }
                    break;
                case R.id.iv_controller_message:
                    if (mLiveMediaControllerListener != null) {
                        mLiveMediaControllerListener.sendMessage();
                    }
                    break;
                case R.id.iv_controller_left_gift:
                case R.id.iv_controller_right_gift:
                    if (mLiveMediaControllerListener != null) {
                        mLiveMediaControllerListener.giveGift();
                    }
                    break;
                case R.id.iv_contorller_share:
                    if (mCommonMediaControllerListener != null) {
                        mCommonMediaControllerListener.onShare();
                    }
                    break;
            }
            show();
        }
    };


    private void setTopBottomVisible(boolean visible) {
        if (viewTop != null) {
            viewTop.setVisibility(visible ? View.VISIBLE : View.GONE);
        }

        if (viewBottom != null) {
            viewBottom.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
        topBottomVisible = visible;
        if (visible) {
            mMediaControllerUtil.startDismissTopBottomTimer();
        } else {
            mMediaControllerUtil.cancelDismissTopBottomTimer();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isAutoHide) {
            show(sDefaultTimeout);
        } else {
            if (!canHideTopAndBottom) return true;
            switch (event.getAction()) {
                case MotionEvent.ACTION_UP:
                    setTopBottomVisible(!topBottomVisible);
                    break;
            }
        }
        return true;
    }

    private boolean isAutoHide = true;

    @Override
    public boolean onTrackballEvent(MotionEvent ev) {
        if (isAutoHide) {
            show(sDefaultTimeout);
        } else {
            if (!canHideTopAndBottom) return false;
            setTopBottomVisible(!topBottomVisible);
        }
        return false;
    }


    public void setWatchCount(int watchCount) {
        if (watchCount == 0) {
            tvControllerLister.setText("");
        } else tvControllerLister.setText(String.valueOf(watchCount));
    }


    public void showShikan(boolean show) {
        if (canHideTopAndBottom != show) return;
        tvControllerToast.setVisibility(show?VISIBLE:GONE);
        this.canHideTopAndBottom = !show;
        show(5 * 1000 * 60 * 24);
        ivFull.setEnabled(!show);
    }

    private boolean canHideTopAndBottom = true;

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ButterKnife.unbind(this);
    }

    /**
     * 控制器恢复到初始状态
     */
    public void reset() {
        topBottomVisible = false;
        mMediaControllerUtil.cancelDismissTopBottomTimer();
    }

    public void giveGift(GiftBean.DataBean giftBean) {
        giftModel = new GiftModel(giftBean.getName(), giftBean.getName(), 1, giftBean.getPath(), "1", MyAPP.getUserInfo().getNickName(),
                StringUtils.headImg(), System.currentTimeMillis());
        giftControl.loadGift(giftModel);
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }


    public static final int LIVE_STATE_HELF = 2;
    public static final int LIVE_STATE_FULL = 4;

    private boolean isFullScreen = false;

    public void setState(int state) {
        switch (state) {
            case LIVE_STATE_HELF:
                setViewState(GONE, tvTitle, ivRightGift, ivMessage, mChatCustomView);
                setViewState(VISIBLE, ivLeftGift);
                break;
            case LIVE_STATE_FULL:
                setViewState(VISIBLE, tvTitle, ivRightGift, ivMessage, mChatCustomView);
                setViewState(GONE, ivLeftGift);
                break;
        }
    }

    private void setViewState(int visibility, View... views) {
        for (int i = 0; i < views.length; i++) {
            if (views[i].getVisibility() != visibility) {
                views[i].setVisibility(visibility);
            }
        }
    }

    public void changeScreen() {
        isFullScreen = !isFullScreen;
        if (mChatCustomView != null) mChatCustomView.setVisibility(isFullScreen ? VISIBLE : GONE);
        setState(isFullScreen ? LIVE_STATE_FULL : LIVE_STATE_HELF);

        if (isFullScreen) {
            ivFull.setImageResource(R.drawable.selector_sjk_live_unfull);
        } else {
            ivFull.setImageResource(R.mipmap.ic_sjk_live_list_full);
        }
        if (UpdateVersionUtil.IS(19)) {
            if (isFullScreen) {
                setPadding(0, 0, 0, 0);
            } else {
                setPadding(0, 0, 0, 0);
            }
        }
    }


    public boolean isFullScreen() {
        return isFullScreen;
    }
}
