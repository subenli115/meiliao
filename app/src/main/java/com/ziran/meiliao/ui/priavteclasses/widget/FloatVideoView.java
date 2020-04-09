//package com.ziran.meiliao.ui.priavteclasses.widget;
//
//import android.content.Context;
//import android.content.Intent;
//import android.support.annotation.Nullable;
//import android.util.AttributeSet;
//import android.view.View;
//import android.widget.ImageView;
//
//import com.pili.pldroid.player.widget.PLVideoTextureView;
//import com.ziran.meiliao.R;
//import com.ziran.meiliao.common.baseapp.AppManager;
//import com.ziran.meiliao.common.commonutils.ViewUtil;
//import com.ziran.meiliao.common.permission.BaseAVCallFloatView;
//import com.ziran.meiliao.common.permission.FloatWindowManager;
//import com.ziran.meiliao.service.HomeService;
//import com.ziran.meiliao.ui.priavteclasses.activity.VerticalLiveActivity;
//import com.ziran.meiliao.ui.priavteclasses.util.VideoPlayerHelper;
//
///**
// * @author 吴祖清
// * @version $Rev$
// * @createTime 2017/5/11 13:58
// * @des ${TODO}
// * @updateAuthor $Author$
// * @updateDate 2017/5/11$
// * @updateDes ${TODO}
// */
//
//public class FloatVideoView extends BaseAVCallFloatView implements View.OnClickListener{
//    private String mCourseId;
//
//    public FloatVideoView(Context context) {
//        super(context);
//    }
//
//    public FloatVideoView(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    @Override
//    protected void initView() {
//        super.initView();
//        PLVideoTextureView videoTextureView = ViewUtil.getView(this, R.id.videoView);
//        View loadView = ViewUtil.getView(this, R.id.loadingView);
//        ImageView coverView = ViewUtil.getView(this, R.id.iv_cover_view);
//        mVideoPlayerHelper = new VideoPlayerHelper(null);
//        mVideoPlayerHelper.init(videoTextureView, loadView, null, coverView);
//        setOnClickListener(this);
//    }
//
//    private VideoPlayerHelper mVideoPlayerHelper;
//
//
//    public void startPlay(String videoPath) {
//        mVideoPlayerHelper.startPlay(videoPath);
//    }
//
//    public void setIsLiveStreaming(int isLiveStreaming) {
//        mVideoPlayerHelper.setIsLiveStreaming(isLiveStreaming, 2);
//    }
//
//    @Override
//    protected int getLayoutResourseId() {
//        return R.layout.video_floatview;
//    }
//
//
//    @Override
//    public void onClick(View v) {
//        dissmis();
//
//        if (!AppManager.getAppManager().isForeground()) {
//            Intent intent = new Intent(getContext(), HomeService.class);
//            intent.putExtra("floatView", getCourseId());
//            getContext().startService(intent);
//        } else {
//            if (!(AppManager.getAppManager().currentActivity() instanceof VerticalLiveActivity)) {
//                VerticalLiveActivity.startAction(getCourseId());
//            }
//        }
//    }
//
//    public void dissmis() {
//        mVideoPlayerHelper.stop();
//        //处理页面跳转
////        ToastUitl.showShort("this float window is clicked");
//        FloatWindowManager.getInstance().dismissWindow();
//    }
//
//    @Override
//    protected void onDetachedFromWindow() {
//        super.onDetachedFromWindow();
//        mVideoPlayerHelper.onDestroy();
//    }
//
//    public void setCourseId(String courseId) {
//        mCourseId = courseId;
//    }
//
//    public String getCourseId() {
//        return mCourseId;
//    }
//
//}
