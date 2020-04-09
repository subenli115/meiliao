package com.ziran.meiliao.ui.priavteclasses.util;

import android.content.Context;
import android.hardware.Camera;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import com.ziran.meiliao.BuildConfig;
import com.ziran.meiliao.common.commonutils.DisplayUtil;
import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.qiniu.android.dns.DnsManager;
import com.qiniu.android.dns.IResolver;
import com.qiniu.android.dns.NetworkInfo;
import com.qiniu.android.dns.http.DnspodFree;
import com.qiniu.android.dns.local.AndroidDnsServer;
import com.qiniu.android.dns.local.Resolver;
import com.qiniu.pili.droid.streaming.AVCodecType;
import com.qiniu.pili.droid.streaming.CameraStreamingSetting;
import com.qiniu.pili.droid.streaming.Config;
import com.qiniu.pili.droid.streaming.StreamStatusCallback;
import com.qiniu.pili.droid.streaming.StreamingEnvKit;
import com.qiniu.pili.droid.streaming.StreamingManager;
import com.qiniu.pili.droid.streaming.StreamingProfile;
import com.qiniu.pili.droid.streaming.StreamingSessionListener;
import com.qiniu.pili.droid.streaming.StreamingState;
import com.qiniu.pili.droid.streaming.StreamingStateChangedListener;
import com.qiniu.pili.droid.streaming.code.ExtAudioCapture;
import com.qiniu.pili.droid.streaming.code.ExtVideoCapture;

import org.json.JSONObject;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.util.List;

import cn.rongcloud.live.LiveKit;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/5/5 11:30
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/5/5$
 * @updateDes ${TODO}
 */

public class ExtCapStreamingHelper implements StreamStatusCallback, StreamingSessionListener, StreamingStateChangedListener {
    private static final int MSG_START_STREAMING = 1000;
    private static final int MSG_STOP_STREAMING = 1001;
    private static final int MSG_SHOW_TIME = 500;

    private SurfaceView mSurfaceView;
    private Context mContext;
    private boolean mIsEncodingMirror = false;
    private boolean mShutterButtonPressed = false;
    private ExtAudioCapture mExtAudioCapture;
    private ExtVideoCapture mExtVideoCapture;
    private StreamingManager mStreamingManager;
    private boolean mOrientationChanged = false;
    private StreamingProfile mProfile;
    private JSONObject mJSONObject;

    private Switcher mSwitcher = new Switcher();
    private boolean mIsReady = false;
    private String mLogContent = "\n";


    private static final String TAG = "ExtCapStreamingHelper";
    private TextView mOpenLiveView;
    PowerManager.WakeLock wakeLock = null;

    public ExtCapStreamingHelper(Context context, SurfaceView surfaceView) {
        this.mContext = context;
        this.mSurfaceView = surfaceView;
        PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        this.wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, this.getClass().getName());
    }




    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_START_STREAMING:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            // disable the shutter button before startStreaming
                            setShutterButtonEnabled(false);
                            boolean res = mStreamingManager.startStreaming();
                            mShutterButtonPressed = true;
                            LogUtils.logd("res:" + res);
                            if (!res) {
                                mShutterButtonPressed = false;
                                setShutterButtonEnabled(true);
                            }
                            setShutterButtonPressed(mShutterButtonPressed);
                        }
                    }).start();
                    break;
                case MSG_STOP_STREAMING:
                    if (mShutterButtonPressed) {
                        // disable the shutter button before stopStreaming
                        setShutterButtonEnabled(false);
                        boolean res = mStreamingManager.stopStreaming();
                        if (!res) {
                            mShutterButtonPressed = true;
                            setShutterButtonEnabled(true);
                        }
                        setShutterButtonPressed(mShutterButtonPressed);
                    }
                    break;
                case MSG_SHOW_TIME:
                    if (msg.arg1 == 0) {
                        mOpenLiveView.setVisibility(View.GONE);
                        if (mIsReady) {
                            startStreaming();
                        }
                    } else {
                        mOpenLiveView.setText(String.valueOf(msg.arg1));
                        int count = --msg.arg1;
                        mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SHOW_TIME, count, 0), 1000);
                    }
                    break;
                default:
                    LogUtils.loge("Invalid message");
                    break;
            }
        }
    };


    public void init(String publishUrlFromServer) {

        StreamingProfile.AudioProfile aProfile = new StreamingProfile.AudioProfile(44100, 96 * 1024);
        StreamingProfile.VideoProfile vProfile = new StreamingProfile.VideoProfile(30, 1000 * 1024, 60, StreamingProfile.H264Profile.HIGH);
        StreamingProfile.AVProfile avProfile = new StreamingProfile.AVProfile(vProfile, aProfile);

        mProfile = new StreamingProfile();

        if (publishUrlFromServer.startsWith(Config.EXTRA_PUBLISH_URL_PREFIX)) {
            try {
                mProfile.setPublishUrl(publishUrlFromServer.substring(Config.EXTRA_PUBLISH_URL_PREFIX.length()));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        } else if (publishUrlFromServer.startsWith(Config.EXTRA_PUBLISH_JSON_PREFIX)) {
            try {
                mJSONObject = new JSONObject(publishUrlFromServer.substring(Config.EXTRA_PUBLISH_JSON_PREFIX.length()));
                StreamingProfile.Stream stream = new StreamingProfile.Stream(mJSONObject);
                mProfile.setStream(stream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            ToastUitl.showShort("Invalid Publish Url");
        }

        mProfile.setVideoQuality(StreamingProfile.VIDEO_QUALITY_HIGH2).setAudioQuality(StreamingProfile.AUDIO_QUALITY_HIGH1)
                .setPreferredVideoEncodingSize(DisplayUtil.getScreenWidth(mContext), DisplayUtil.getScreenHeight(mContext)).
                setEncodingSizeLevel(Config.ENCODING_LEVEL).setEncoderRCMode(StreamingProfile
                .EncoderRCModes.QUALITY_PRIORITY).setAVProfile(avProfile).setDnsManager(getMyDnsManager()).setStreamStatusConfig(new
                StreamingProfile.StreamStatusConfig(3))
                .setEncodingOrientation(StreamingProfile.ENCODING_ORIENTATION.PORT)
                .setSendingBufferProfile(new StreamingProfile.SendingBufferProfile(0.2f, 0.8f, 3.0f, 20 * 1000));
        CameraStreamingSetting cameraStreamingSetting = new CameraStreamingSetting();
        cameraStreamingSetting.setFaceBeautySetting(new CameraStreamingSetting.FaceBeautySetting(1.0f, 1.0f, 0.8f))
                .setVideoFilter(CameraStreamingSetting.VIDEO_FILTER_TYPE.VIDEO_FILTER_BEAUTY);

        mStreamingManager = new StreamingManager(mContext, AVCodecType.HW_VIDEO_YUV_AS_INPUT_WITH_HW_AUDIO_CODEC); // sw codec 硬编


        mStreamingManager.prepare(mProfile);
        mStreamingManager.setStreamingStateListener(this);
        mStreamingManager.setStreamingSessionListener(this);
        mStreamingManager.setNativeLoggingEnabled(false);
        mStreamingManager.setStreamStatusCallback(this);
        // update the StreamingProfile

        mExtVideoCapture = new ExtVideoCapture(mSurfaceView);
        mExtVideoCapture.setOnPreviewFrameCallback(mOnPreviewFrameCallback);
        mExtAudioCapture = new ExtAudioCapture();
        if (StreamingEnvKit.hasFrontFacingCamera()) {
            mHandler.removeCallbacks(mSwitcher);
            mHandler.postDelayed(mSwitcher, 100);
        }
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SHOW_TIME, 5, 0));
    }

    public void onResume() {
        if (BuildConfig.DEBUG) {
//            ToastUitl.showShort("mExtAudioCapture" + mExtAudioCapture);
        }
        if (mExtAudioCapture == null) return;
        mExtAudioCapture.startCapture();
        mExtAudioCapture.setOnAudioFrameCapturedListener(mOnAudioFrameCapturedListener);
        mStreamingManager.resume();
        this.wakeLock.acquire();
        mCountDownTimer.start();
    }

    public void onPause() {
//        if (BuildConfig.DEBUG){
//            ToastUitl.showShort("mExtAudioCapture" + mExtAudioCapture);
//        }
        if (mExtAudioCapture == null) return;
        mIsReady = false;
        mShutterButtonPressed = false;
        mHandler.removeCallbacksAndMessages(null);
        mExtAudioCapture.stopCapture();
        mStreamingManager.pause();

            // sanity check for null as this is a public method
        if (wakeLock != null) {
            try {
                wakeLock.release();
            } catch (Throwable th) {

            }
        } else {

        }
        mCountDownTimer.cancel();
    }

    public void onDestroy() {
        if (mExtAudioCapture == null) return;
        mStreamingManager.destroy();
        wakeLock = null;
        mCountDownTimer = null;
    }

    protected void setShutterButtonPressed(final boolean pressed) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mShutterButtonPressed = pressed;
//                mShutterButton.setPressed(pressed);
            }
        });
    }

    protected void setShutterButtonEnabled(final boolean enable) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
//                mShutterButton.setFocusable(enable);
//                mShutterButton.setClickable(enable);
//                mShutterButton.setEnabled(enable);
            }
        });
    }


    @Override
    public boolean onRecordAudioFailedHandled(int err) {
        return false;
    }

    @Override
    public boolean onRestartStreamingHandled(int err) {
        LogUtils.logd("onRestartStreamingHandled");
        return false;
    }

    @Override
    public Camera.Size onPreviewSizeSelected(List<Camera.Size> list) {
        return null;
    }

    @Override
    public int onPreviewFpsSelected(List<int[]> list) {
        return 0;
    }

    @Override
    public void notifyStreamStatusChanged(final StreamingProfile.StreamStatus streamStatus) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
//                mStreamStatus.setText("bitrate:" + streamStatus.totalAVBitrate / 1024 + " kbps"
//                        + "\naudio:" + streamStatus.audioFps + " fps"
//                        + "\nvideo:" + streamStatus.videoFps + " fps");
            }
        });
    }

    @Override
    public void onStateChanged(StreamingState streamingState, Object extra) {
        switch (streamingState) {
            case PREPARING:
                String statusMsgContent = getString(com.qiniu.pili.droid.streaming.R.string.string_state_preparing);
                break;
            case READY:
                mIsReady = true;
                statusMsgContent = getString(com.qiniu.pili.droid.streaming.R.string.string_state_ready);
                // start streaming when READY

                break;
            case CONNECTING:
                statusMsgContent = getString(com.qiniu.pili.droid.streaming.R.string.string_state_connecting);
                break;
            case STREAMING:
                statusMsgContent = getString(com.qiniu.pili.droid.streaming.R.string.string_state_streaming);
                setShutterButtonEnabled(true);
                setShutterButtonPressed(true);
                break;
            case SHUTDOWN:
                statusMsgContent = getString(com.qiniu.pili.droid.streaming.R.string.string_state_ready);
                setShutterButtonEnabled(true);
                setShutterButtonPressed(false);
                if (mOrientationChanged) {
                    mOrientationChanged = false;
                    startStreaming();
                }
                break;
            case IOERROR:
                mLogContent += "IOERROR\n";
                statusMsgContent = getString(com.qiniu.pili.droid.streaming.R.string.string_state_ready);
                setShutterButtonEnabled(true);
                break;
            case UNKNOWN:
                statusMsgContent = getString(com.qiniu.pili.droid.streaming.R.string.string_state_ready);
                break;
            case SENDING_BUFFER_EMPTY:
                break;
            case SENDING_BUFFER_FULL:
                break;
            case DISCONNECTED:
                mLogContent += "DISCONNECTED\n";
                break;
            case INVALID_STREAMING_URL:
                LogUtils.loge("Invalid streaming url:" + extra);
                break;
            case UNAUTHORIZED_STREAMING_URL:
                LogUtils.loge("Unauthorized streaming url:" + extra);
                mLogContent += "Unauthorized Url\n";
                break;
        }
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                //                if (mLogTextView != null) {
//                    mLogTextView.setText(mLogContent);
//                }
//                mStatusTextView.setText(mStatusMsgContent);
            }
        });
    }

    private String getString(int resId) {
        return mContext.getString(resId);
    }


    private static DnsManager getMyDnsManager() {
        IResolver r0 = new DnspodFree();
        IResolver r1 = AndroidDnsServer.defaultResolver();
        IResolver r2 = null;
        try {
            r2 = new Resolver(InetAddress.getByName("119.29.29.29"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return new DnsManager(NetworkInfo.normal, new IResolver[]{r0, r1, r2});
    }

    /**
     *
     */
    private ExtVideoCapture.OnPreviewFrameCallback mOnPreviewFrameCallback = new ExtVideoCapture.OnPreviewFrameCallback() {
        @Override
        public void onPreviewFrameCaptured(byte[] data, int width, int height, int orientation, boolean mirror, int fmt, long
                tsInNanoTime) {
            mStreamingManager.inputVideoFrame(data, width, height, orientation, mIsEncodingMirror, fmt, tsInNanoTime);
        }
    };

    /**
     *
     */
    private ExtAudioCapture.OnAudioFrameCapturedListener mOnAudioFrameCapturedListener = new ExtAudioCapture.OnAudioFrameCapturedListener
            () {
        @Override
        public void onAudioFrameCaptured(byte[] audioData) {
            long timestamp = System.nanoTime();
            mStreamingManager.inputAudioFrame(audioData, timestamp, false);
        }
    };

    /**
     * 启动推流
     */
    public void startStreaming() {
        mHandler.removeCallbacksAndMessages(null);
        mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_START_STREAMING), 50);
        if (mStreamStateListener != null) {
            mStreamStateListener.streamState(1);
        }
    }

    private CountDownTimer mCountDownTimer = new CountDownTimer(1000 * 60 * 60 * 6, 1000 * 60 * 5) {
        @Override
        public void onTick(long millisUntilFinished) {
            LiveKit.sendMessage(LiveKit.getNotifytionMessage());
        }

        @Override
        public void onFinish() {

        }
    };

    /**
     * 停止推流
     */
    public void stopStreaming() {
        mHandler.removeCallbacksAndMessages(null);
        mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_STOP_STREAMING), 50);
        if (mStreamStateListener != null) {
            mStreamStateListener.streamState(0);
        }
    }

    /**
     * 切换摄像头
     */
    public void switchCamera() {
        if (StreamingEnvKit.hasFrontFacingCamera()) {
            mHandler.removeCallbacks(mSwitcher);
            mHandler.postDelayed(mSwitcher, 100);
        } else {
            ToastUitl.showShort("切换失败,您的设备没有前置摄像头...");
        }
    }

    /**
     * 切换镜像
     */
    public void encodingMirror() {
        mIsEncodingMirror = !mIsEncodingMirror;
        ToastUitl.showShort("镜像成功");
    }

    public void setOpenLiveView(TextView openLiveView) {
        mOpenLiveView = openLiveView;
    }

    private class Switcher implements Runnable {
        @Override
        public void run() {
            if (mExtVideoCapture != null) {
                mSurfaceView.setVisibility(View.GONE);
                mExtVideoCapture.switchCamera();
                mSurfaceView.setVisibility(View.VISIBLE);
            }
        }
    }

    private StreamStateListener mStreamStateListener;

    public void setStreamStateListener(StreamStateListener streamStateListener) {
        mStreamStateListener = streamStateListener;
    }

    public interface StreamStateListener {
        void streamState(int state);
    }
}
