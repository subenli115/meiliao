//package com.ziran.meiliao.ui.priavteclasses.activity;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.widget.VideoView;
//
//import com.ziran.meiliao.R;
//import com.ziran.meiliao.constant.AppConstant;
//
//import butterknife.Bind;
//import butterknife.ButterKnife;
//
//
//public class DefVideo2Activity extends AppCompatActivity {
//
//
//    @Bind(R.id.videoView)
//    VideoView mVideoView;
//
//
//    public static void startAction(Context context, String mVideoPath) {
//        Intent intent = new Intent(context, DefVideo2Activity.class);
//        intent.putExtra(AppConstant.ExtraKey.M_VIDEO_PATH, mVideoPath);
//        context.startActivity(intent);
//    }
//
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_locat_full_video2);
//        ButterKnife.bind(this);
//        initVideoView();
//    }
//
//    private void initVideoView() {
////        Uri parse = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.pubo);
////        mVideoView.setVideoURI(parse);
////        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
////            @Override
////            public void onCompletion(MediaPlayer mp) {
////                mp.start();
////            }
////        });
////        mVideoView.start();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        ButterKnife.unbind(this);
//    }
//
//
//}
