package com.ziran.meiliao.ui.me.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.vod.upload.VODUploadCallback;
import com.alibaba.sdk.android.vod.upload.VODUploadClient;
import com.alibaba.sdk.android.vod.upload.VODUploadClientImpl;
import com.alibaba.sdk.android.vod.upload.model.UploadFileInfo;
import com.alibaba.sdk.android.vod.upload.model.VodInfo;
import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.BitmapUtils;
import com.umeng.socialize.UMShareAPI;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.common.commonutils.TimeUtil;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.db.MyOpenHelper;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.AliRefreshBean;
import com.ziran.meiliao.ui.bean.AliUploadFileBean;
import com.ziran.meiliao.ui.bean.RechargeBean;
import com.ziran.meiliao.ui.bean.ReportListBean;
import com.ziran.meiliao.ui.bean.UpdateUserNewHeadBean;
import com.ziran.meiliao.ui.bean.UserBean;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.PicassoEngine;
import com.ziran.meiliao.utils.Utils;
import com.ziran.meiliao.widget.GlideCircleTransform;
import com.ziran.meiliao.widget.ItemGroupView;
import com.ziran.meiliao.widget.VoiceManager;
import com.ziran.meiliao.widget.pupop.PopupWindowUtil;
import com.ziran.meiliao.widget.pupop.RecordPopupWindow;
import com.ziran.meiliao.widget.pupop.UpdatePopupWindow;
import com.ziran.meiliao.widget.pupop.UpdateVideoPopupWindow;

import java.io.File;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import me.xfans.lib.voicewaveview.VoiceWaveView;

import static com.ziran.meiliao.constant.ApiKey.ADMIN_DICT_TYPE;

/**
 * 主页形象
 * Created by Administrator on 2017/1/4.
 */

public class HomeImageSelectActivity extends  CommonHttpActivity<CommonPresenter, CommonModel> implements CommonContract.ActionView<Result, Result> {

    public static final int RequestCrop = 3;
    private static final int REQUEST_CODE_C =5 ;
    private static String cameraScalePath;
    private List<ReportListBean.DataBean> listData;
    private ArrayList<ItemGroupView> list;
    private String id;
    @Bind(R.id.voiceWaveView4)
    VoiceWaveView voiceWaveView4;

    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.iv_left)
    ImageView ivLeft;
    @Bind(R.id.iv_right_one)
    ImageView ivOne;
    @Bind(R.id.iv_right_two)
    ImageView ivTwo;
    @Bind(R.id.iv_right_three)
    ImageView ivThree;
    @Bind(R.id.iv_video)
    ImageView ivVideo;
    @Bind(R.id.iv_play)
    ImageView ivPlay;
    @Bind(R.id.arl_bg)
    AutoRelativeLayout arlBg;
    private UpdateVideoPopupWindow pop;
    private List<LocalMedia> selectList;
    private int selectNum;
    private String homepageImages;
    private UpdatePopupWindow imgPop;
    private Bitmap bitmap;
    private String videoCover;
    private String videoId;
    private String CoverUrl;
    private VoiceManager voiceManager;
    private String mStrLength;
    private String mFilePath;
    private ArrayList<ImageView> imageViews;
    private String[] split;
    private List<String> imgs;
    private View contentView;

    public static void startAction() {
        Activity activity = AppManager.getAppManager().currentActivity();
        Intent intent = new Intent(activity, HomeImageSelectActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.ac_homepage_image;
    }

    @Override
    public void initPresenter() {
        if (mModel != null && mPresenter != null) {
            mPresenter.setVM(this, mModel);
        }
    }


    @Override
    public void initView() {
        if(getIntent()!=null){
            Intent intent = getIntent();
             id = intent.getStringExtra("id");
        }
        addVoice();
        imageViews = new ArrayList<>();
        imageViews.add(ivLeft);
        imageViews.add(ivOne);
        imageViews.add(ivTwo);
        imageViews.add(ivThree);
        homepageImages = MyAPP.getmUserBean().getHomepageImages();
        String videoCover = MyAPP.getmUserBean().getVideoCover();
        if(videoCover!=null&&videoCover.length()>0){
            Glide.with(mContext).load(MyAPP.getmUserBean().getVideoCover()).into(ivVideo);
        }
        imgs=new ArrayList<>();
        imgs.add("");
        imgs.add("");
        imgs.add("");
        imgs.add("");
        if(homepageImages!=null&&homepageImages.length()>0){
             split = homepageImages.split(",");
            for(int i=0;i<split.length;i++){
                Glide.with(mContext).load(split[i]).into(imageViews.get(i));
                imgs.set(i,split[i]);
            }
        }
        String voice = MyAPP.getmUserBean().getVoice();
        if(voice!=null&&voice.length()>0){
            ivPlay.setVisibility(View.VISIBLE);
            tvTime.setText("00:00"+"/"+MyAPP.getmUserBean().getVoiceDuration());
        }
        voiceManager =  VoiceManager.getInstance(mContext);
    }




    private void addVoice() {
        for(int i=0;i<100;i++){
            voiceWaveView4.addBody((int)(Math.random()*100));
        }
    }
    private void userUpdate() {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("id",MyAPP.getUserId());
        if(selectNum==5){
            defMap.put("videoCover",videoCover);
            defMap.put("video",videoId);
        }else if(selectNum==6) {
            defMap.put("voiceDuration",mStrLength);
            defMap.put("voice",videoId);
        }else {
            ArrayList<String> temps = new ArrayList<>();
            for(int i=0;i<imgs.size();i++){
                if(!imgs.get(i).equals("")){
                    temps.add(imgs.get(i));
                }
            }
            if(temps.size()>1){
                defMap.put("homepageImages",String.join(",", temps));
            }else {
                defMap.put("homepageImages",temps.get(0));
            }

        }
        OkHttpClientManager.putAsyncAddHead(ApiKey.ADMIN_USER_UPDATE, defMap, new
                NewRequestCallBack<UserBean>(UserBean.class) {
                    @Override
                    public void onSuccess(UserBean result) {
                            UserBean.DataBean dataBean = ((UserBean) result).getData();
                            if(selectNum<5){
                                Glide.with(mContext).load(CoverUrl).into(imageViews.get(selectNum));
                            }
                            if(dataBean!=null){
                                MyAPP.setmUserBean(dataBean);
                            }
                            ToastUitl.showShort(result.getResultMsg());
                            stopProgressDialog();
                    }
                    @Override
                    public void onError(String msg, int code) {
                        ToastUitl.showShort(msg);
                        stopProgressDialog();
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(voiceManager!=null){
            voiceManager.stopRecordAndPlay();
            voiceManager=null;
        }
    }

    //点击监听
    @OnClick({R.id.iv_play,R.id.arl_video,R.id.iv_record,R.id.arl_left_one,R.id.arl_left_two,R.id.arl_left_three,R.id.arl_left})
    public void onClick(View view) {
        if (Utils.isFastDoubleClick()) {
            return;
        }
        switch (view.getId()){
            case R.id.iv_play:
                ImageView iv = (ImageView) view;
                voiceManager.setVoicePlayListener(new VoiceManager.VoicePlayCallBack() {
                    @Override
                    public void voiceTotalLength(long time, String strTime) {
                    }

                    @Override
                    public void playDoing(long time, String strTime) {
                        tvTime.setText(strTime+" / "+mStrLength);
                    }

                    @Override
                    public void playPause() {
                        voiceWaveView4.stop();
                        iv.setImageResource(R.mipmap.icon_image_play);
                    }

                    @Override
                    public void playStart() {
                        voiceWaveView4.start();
                        iv.setImageResource(R.mipmap.icon_image_pause);
                    }

                    @Override
                    public void playFinish() {
                        if(voiceWaveView4!=null){
                            voiceWaveView4.stop();
                        }
                        tvTime.setText("00:00"+" / "+mStrLength);
                        iv.setImageResource(R.mipmap.icon_image_play);
                    }
                });
                voiceManager.continueOrPausePlay(mFilePath);
                break;
            case R.id.iv_record:
                if(ContextCompat.checkSelfPermission(mContext, android.Manifest.permission.RECORD_AUDIO)
                        != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions((Activity) mContext,new String[]{
                            android.Manifest.permission.RECORD_AUDIO},1);
                }else {
                    pop();
                }
                break;
            case R.id.arl_video:
                selectNum=5;
                pop = new UpdateVideoPopupWindow(mContext, this);
                pop.show();
                break;
            case R.id.arl_left:
                selectNum=0;
                showPop();
                break;
            case R.id.arl_left_one:
                selectNum=1;
                showPop();
                break;
            case R.id.arl_left_two:
                selectNum=2;
                showPop();
                break;
            case R.id.arl_left_three:
                selectNum=3;
                showPop();
                break;

        }
    }

    private void showApplyPopWindow() {
        // 一个自定义的布局，作为显示的内容
        contentView = LayoutInflater.from(mContext).inflate(R.layout.pop_real_apply, null);
        final PopupWindow popupWindow = new PopupWindow(contentView,
                AutoLinearLayout.LayoutParams.MATCH_PARENT, AutoLinearLayout.LayoutParams.MATCH_PARENT, true);

        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);// 设置同意在外点击消失
        popupWindow.setFocusable(true);// 点击空白处时，隐藏掉pop窗口
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.showAtLocation(arlBg, Gravity.CENTER, 0, 0);
        TextView qd = contentView.findViewById(R.id.tv_qd);
        TextView qx = contentView.findViewById(R.id.tv_qx);
        TextView etReason = contentView.findViewById(R.id.et_reason);
        etReason.setText("认证用户才能上传主页形象照片");
        qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetRealPersonActivity.startAction(REQUEST_CODE_C);
            }
        });
        qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();

            }
        });
    }



    private void pop() {
        RecordPopupWindow popupWindow = new RecordPopupWindow(mContext);
        popupWindow.setOnFinish(( strLength, filePath) -> {
            selectNum=6;
            mFilePath=filePath;
            tvTime.setText("00:00"+" / "+strLength);
            mStrLength=strLength;
            ivPlay.setVisibility(View.VISIBLE);
            getUploadAuth(mFilePath,"音频");
        });
        PopupWindowUtil.show(this, popupWindow);
    }

    /**
     * ⑨重写onRequestPermissionsResult方法
     * 获取动态权限请求的结果,再开启录制音频
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==1&&grantResults[0]== PackageManager.PERMISSION_GRANTED){
            pop();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    private void showPop() {
        if(MyAPP.getmUserBean().getIsReal()==null||MyAPP.getmUserBean().getIsReal().equals("0")){
            showApplyPopWindow();
        }else {
            imgPop = new UpdatePopupWindow(mContext, this);
            imgPop.setTitle("从相册中选择",true);
            imgPop.show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == PictureConfig.CHOOSE_REQUEST){
            selectList = PictureSelector.obtainMultipleResult(data);
            if(selectList!=null&&selectList.size()>0) {
                LocalMedia localMedia = selectList.get(0);
                getUploadAuth(TextUtils.isEmpty(localMedia.getAndroidQToPath()) ? localMedia.getPath() : localMedia.getAndroidQToPath(), "视频");
            }
        }
        if (requestCode == ImgSelConfig.RequestCode && data != null) {
            final ArrayList<String> imgPaths = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
            startUCrop(this,imgPaths.get(0),RequestCrop);
        } else if(resultCode == RESULT_OK &&requestCode==RequestCrop){
            if(cameraScalePath.length()>0){
                upload();
            }
        }
        if(requestCode==REQUEST_CODE_C &&resultCode == Activity.RESULT_OK){


        }
    }
    private void upload() {
        OkHttpClientManager.postContentAndFiles(ApiKey.ADMIN_FILE_UPLOAD, MapUtils.getDefMap(true), FileUtil.str2File(cameraScalePath), new
                NewRequestCallBack<UpdateUserNewHeadBean>(UpdateUserNewHeadBean.class) {

                    @Override
                    public void onSuccess(UpdateUserNewHeadBean result) {
                        CoverUrl = result.getData().getUrl();
                        imgs.set(selectNum,CoverUrl);
                        userUpdate();
                    }

                    @Override
                    public void onError(String msg, int code) {
                        ToastUitl.showShort(msg);
                    }
                });
    }

    public void  getUploadAuth(String filePath, String type){
        if(type.equals("视频")){
            bitmap = ThumbnailUtils.createVideoThumbnail(filePath //url的参数
                    , MediaStore.Video.Thumbnails.MINI_KIND);
        }
        startProgressDialog("正在上传..");
        Map<String, String> defMap = MapUtils.getDefMap(true);
        File file = new File(filePath);
        defMap.put("fileName", file.getName());
        defMap.put("title",type);
        OkHttpClientManager.getAsyncMore(ApiKey.ADMIN_TOOL_CREATEUPLOADVIDEO, defMap, new NewRequestCallBack<AliUploadFileBean>(AliUploadFileBean.class) {
            @Override
            protected void onSuccess(AliUploadFileBean result) {
                upload(result.getData(),filePath,type);
            }
            @Override
            public void onError(String msg, int code) {
                ToastUitl.showShort(msg);
            }
        });
    }
    public void upload(AliUploadFileBean.DataBean result, String path, String type){
// create VODUploadClient
        final VODUploadClient uploader = new VODUploadClientImpl(getApplicationContext());
// setup callback
        VODUploadCallback vodUploadCallback = new VODUploadCallback() {


            @Override
            public void onUploadFailed(UploadFileInfo info, String code, String message) {
                OSSLog.logError("onfailed ------------------ " + info.getFilePath() + " " + code + " " + message);
                Log.e("onUploadFailed", "" + " " + code + " " + message);
            }


            @Override
            public void onUploadProgress(UploadFileInfo info, long uploadedSize, long totalSize) {
                OSSLog.logDebug("onProgress ------------------ " + info.getFilePath() + " " + uploadedSize + " " + totalSize);
            }

            @Override
            public void onUploadRetry(String code, String message) {
                OSSLog.logError("onUploadRetry ------------- ");
            }


            @Override
            public void onUploadRetryResume() {
                OSSLog.logError("onUploadRetryResume ------------- ");
            }


            @Override
            public void onUploadStarted(UploadFileInfo uploadFileInfo) {
                OSSLog.logError("onUploadStarted ------------- ");
                Log.e("onUploadStarted", "" + "onUploadStarted ");
                uploader.setUploadAuthAndAddress(uploadFileInfo, result.getUploadAuth(), result.getUploadAddress());
            }

            @Override
            public void onUploadSucceed(UploadFileInfo info) {
                OSSLog.logDebug("onsucceed ------------------" + info.getVodInfo().getCoverUrl());
                videoId = result.getVideoId();
                if(selectNum==5){
                    uploadCoverUrl();
                }else {
                    userUpdate();
                }
            }

            @Override
            public void onUploadTokenExpired() {
                OSSLog.logError("onExpired ------------- ");
                // 重新刷新上传凭证:RefreshUploadVideo

                refreshUploadAuth(path, type, uploader, result.getVideoId());
            }
        };
        uploader.init(vodUploadCallback);
        aliUploadFile(path, type, uploader);
    }

    private void aliUploadFile(String path, String type, VODUploadClient uploader) {
        VodInfo vodInfo = new VodInfo();
        vodInfo.setTitle(type );
        vodInfo.setDesc("描述." + "3333");
        vodInfo.setCateId(1);
        uploader.addFile(path,vodInfo);
        uploader.start();
    }

    private void uploadCoverUrl() {
        File file = new File(Environment.getExternalStorageDirectory() + "/temp.jpg");
        BitmapUtils.saveBitmapFile(bitmap,file);
        OkHttpClientManager.postContentAndFiles(ApiKey.ADMIN_FILE_UPLOAD, MapUtils.getDefMap(true),FileUtil.str2File(file.getPath()), new
                NewRequestCallBack<UpdateUserNewHeadBean>(UpdateUserNewHeadBean.class) {

                    @Override
                    public void onSuccess(UpdateUserNewHeadBean result) {
                        videoCover = result.getData().getUrl();
                        Glide.with(mContext).load(videoCover).into(ivVideo);
                        userUpdate();
                    }

                    @Override
                    public void onError(String msg, int code) {
                        stopProgressDialog();
                        ToastUitl.showShort(msg);
                    }
                });
    }
    public void  refreshUploadAuth(String filePath, String type, VODUploadClient uploader,String videoId){
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("videoId",videoId);
        OkHttpClientManager.getAsyncMore(ApiKey.ADMIN_TOOL_REFRESH, defMap, new NewRequestCallBack<AliRefreshBean>(AliRefreshBean.class) {
            @Override
            protected void onSuccess(AliRefreshBean result) {
                uploader.resumeWithAuth(result.getData().getUploadAuth());
                aliUploadFile(filePath, type, uploader);
            }
            @Override
            public void onError(String msg, int code) {
                ToastUitl.showShort(msg);
            }
        });
    }
    /**
     * 启动裁剪
     * @param activity 上下文
     * @param sourceFilePath 需要裁剪图片的绝对路径
     * @param requestCode 比如：UCrop.REQUEST_CROP
     * @return
     */
    public static String startUCrop(Activity activity, String sourceFilePath,
                                    int requestCode) {
        Uri sourceUri = Uri.fromFile(new File(sourceFilePath));
        File outDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        if (!outDir.exists()) {
            outDir.mkdirs();
        }
        File outFile = new File(outDir, System.currentTimeMillis() + ".jpg");
        //裁剪后图片的绝对路径
        cameraScalePath = outFile.getAbsolutePath();
        Uri destinationUri = Uri.fromFile(outFile);
        //初始化，第一个参数：需要裁剪的图片；第二个参数：裁剪后图片
        UCrop uCrop = UCrop.of(sourceUri, destinationUri);
        //初始化UCrop配置
        UCrop.Options options = new UCrop.Options();
        //设置裁剪图片可操作的手势
        options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL);
        //是否隐藏底部容器，默认显示
        options.setHideBottomControls(true);
        //是否能调整裁剪框
        options.setFreeStyleCropEnabled(true);
        //UCrop配置
        uCrop.withOptions(options);
        //设置裁剪图片的宽高比，比如16：9
        uCrop.withAspectRatio(1, 1);
        //uCrop.useSourceImageAspectRatio();
        //跳转裁剪页面
        uCrop.start(activity, requestCode);
        return cameraScalePath;
    }

    @Override
    public void returnData(Result result) {

    }

    @Override
    public void returnAction(Result result) {

    }
}