package com.ziran.meiliao.ui.priavteclasses.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.commonwidget.ViewDragHelperLayout;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.priavteclasses.util.ExtCapStreamingHelper;
import com.ziran.meiliao.ui.priavteclasses.util.SJKRoomHelper;
import com.ziran.meiliao.widget.ChatCustomView;
import com.qiniu.pili.droid.streaming.StreamingEnvKit;
import com.wevey.selector.dialog.DialogOnClickListener;
import com.wevey.selector.dialog.MDAlertDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.rongcloud.live.LiveKit;
import io.rong.message.InformationNotificationMessage;


/**
 *  手机开播界面
 */
public class ExtCapStreamingActivity extends AppCompatActivity {


    @Bind(R.id.ext_camerapreview_surfaceview)
    SurfaceView extCamerapreviewSurfaceview;
    @Bind(R.id.iv_camera_switch)
    ImageView ivCameraSwitch;
    @Bind(R.id.tv_ext_time)
    TextView tvExtTime;
    @Bind(R.id.chatCustomView)
    ChatCustomView mChatCustomView;
    @Bind(R.id.view_drag_helper)
    ViewDragHelperLayout mViewDragHelperLayout;

    private ExtCapStreamingHelper mExtCapStreamingHelper;
    private static final String STREANING_URL = "stream_json_str";
    private String courseId;
    private SJKRoomHelper mSJKRoomHelper;


    public static void startAction(Context context, String url, String courseId) {
        StreamingEnvKit.init(context);
        Intent intent = new Intent(context, ExtCapStreamingActivity.class);
        LogUtils.logd("url:"+url);
        url = "URL:" + url;
        intent.putExtra(STREANING_URL, url);
        intent.putExtra(AppConstant.SPKey.COURSE_ID, courseId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ext_camera_streaming);
        ButterKnife.bind(this);
        MyAPP.initLiveKit(this);
        mExtCapStreamingHelper = new ExtCapStreamingHelper(this, extCamerapreviewSurfaceview);
        mSJKRoomHelper = new SJKRoomHelper(mChatCustomView);
        String publishUrlFromServer = getIntent().getStringExtra(STREANING_URL);
        courseId = getIntent().getStringExtra(AppConstant.SPKey.COURSE_ID);
        mExtCapStreamingHelper.setOpenLiveView(tvExtTime);
        mExtCapStreamingHelper.setStreamStateListener(new ExtCapStreamingHelper.StreamStateListener() {
            @Override
            public void streamState(int state) {
                switch (state) {
                    case 1:
                        mChatCustomView.setVisibility(View.VISIBLE);
                        mSJKRoomHelper.onFragmentVisibleChange(true);
                        break;
                }
            }
        });
        mViewDragHelperLayout.setOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewDragHelperLayout.changeChildViewShown();
            }
        });
        mExtCapStreamingHelper.init(publishUrlFromServer);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mExtCapStreamingHelper.onResume();
        if (isPause) {
            isPause = false;
            mExtCapStreamingHelper.startStreaming();
        }
        mSJKRoomHelper.onResume();
    }

    private boolean isPause;

    @Override
    protected void onPause() {
        super.onPause();
        mExtCapStreamingHelper.onPause();
        mSJKRoomHelper.onPause();
        isPause = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        mExtCapStreamingHelper.onDestroy();
        mSJKRoomHelper.onDestroy();
    }



    @Override
    public void onBackPressed() {
        showDialog();
    }

    @OnClick({R.id.iv_camera_switch, R.id.iv_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_camera_switch:
                mExtCapStreamingHelper.switchCamera();
                break;
            case R.id.iv_close:
                showDialog();
                break;
        }
    }

    private void showDialog() {
        MDAlertDialog.createDialog(ExtCapStreamingActivity.this, "请确认是否需要结束直播!", new DialogOnClickListener() {
            @Override
            public void clickLeftButton(Dialog dialog, View view) {

            }
            @Override
            public void clickRightButton(Dialog dialog, View view) {
                final InformationNotificationMessage content = InformationNotificationMessage.obtain("直播已结束,谢谢观看!!");
                content.setExtra("直播已结束");
                LiveKit.sendMessage(content);
                MobileLiveOverActivity.startAction(ExtCapStreamingActivity.this, courseId);
                dialog.dismiss();
                finish();
            }
        });
    }
}
