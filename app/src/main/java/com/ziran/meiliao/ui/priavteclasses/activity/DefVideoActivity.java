package com.ziran.meiliao.ui.priavteclasses.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pili.pldroid.player.AVOptions;
import com.pili.pldroid.player.widget.PLVideoTextureView;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.priavteclasses.util.VideoPlayerHelper;
import com.ziran.meiliao.widget.SimpleMediaController;

import butterknife.Bind;
import butterknife.ButterKnife;


public class DefVideoActivity extends AppCompatActivity {


    @Bind(R.id.VideoView)
    PLVideoTextureView mVideoView;
    @Bind(R.id.LoadingView)
    View mLoadingView;
    @Bind(R.id.media_controller)
    SimpleMediaController mMediaController;
    private VideoPlayerHelper mVideoPlayerHelper;

    public static void startAction(Context context, String mVideoPath) {
        Intent intent = new Intent(context, DefVideoActivity.class);
        intent.putExtra(AppConstant.ExtraKey.M_VIDEO_PATH, mVideoPath);
        context.startActivity(intent);
    }

    public static void startAction(Context context, String mVideoPath, String title) {
        Intent intent = new Intent(context, DefVideoActivity.class);
        intent.putExtra(AppConstant.ExtraKey.M_VIDEO_PATH, mVideoPath);
        intent.putExtra(AppConstant.ExtraKey.VIDEO_TITLE, title);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locat_full_video);
        ButterKnife.bind(this);
        initVideoView();
    }

    private void initVideoView() {
        mVideoPlayerHelper = new VideoPlayerHelper(null);
        String videoPath = getIntentExtra(getIntent(), AppConstant.ExtraKey.M_VIDEO_PATH);
        String title = getIntentExtra(getIntent(), AppConstant.ExtraKey.VIDEO_TITLE);
        mVideoView.setBufferingIndicator(mLoadingView);
        int codec = getIntent().getIntExtra(AVOptions.KEY_MEDIACODEC, AVOptions.MEDIA_CODEC_AUTO);
        mVideoPlayerHelper.init(mVideoView, mLoadingView, null);
        mVideoPlayerHelper.setOptions(mVideoView, codec, videoPath.startsWith("rtmp://")?1:0);
        mVideoView.setMediaController(mMediaController);
        mMediaController.setTitle(title);
        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if (visibility == View.VISIBLE) {
                    mMediaController.show();
                }
            }
        });

        mLoadingView.setVisibility(View.VISIBLE);
//        if ("111".equals(videoPath)){
////            Uri parse = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.pubu);
////            mVideoView.setVideoURI(parse);
////            mVideoView.setLooping(true);
////            mVideoView.start();
//        }else{
//
//        }
        mVideoView.setVideoPath(videoPath);
        mVideoView.start();
    }

    private String getIntentExtra(Intent intent, String mVideoPath) {
        if (EmptyUtils.isNotEmpty(intent)) {
            return intent.getStringExtra(mVideoPath);
        }
        return "";
    }

    @Override
    protected void onPause() {
        super.onPause();
        mVideoPlayerHelper.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVideoPlayerHelper.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVideoPlayerHelper.onDestroy();
        ButterKnife.unbind(this);
    }


}
