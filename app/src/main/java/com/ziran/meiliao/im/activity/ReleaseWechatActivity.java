package com.ziran.meiliao.im.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;

import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.vod.upload.VODUploadCallback;
import com.alibaba.sdk.android.vod.upload.VODUploadClient;
import com.alibaba.sdk.android.vod.upload.VODUploadClientImpl;
import com.alibaba.sdk.android.vod.upload.model.UploadFileInfo;
import com.alibaba.sdk.android.vod.upload.model.VodInfo;
import com.aliyun.vod.common.utils.StringUtils;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.decoration.GridSpacingItemDecoration;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.PermissionChecker;
import com.luck.picture.lib.tools.BitmapUtils;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.yalantis.ucrop.util.ScreenUtils;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;
import com.ziran.meiliao.R;


import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.ziran.meiliao.common.irecyclerview.FullyGridLayoutManager;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.im.adapter.GridImageAdapter;
import com.ziran.meiliao.ui.bean.AliRefreshBean;
import com.ziran.meiliao.ui.bean.AliUploadFileBean;
import com.ziran.meiliao.ui.bean.AliVideoInfoBean;
import com.ziran.meiliao.ui.bean.FileListBean;
import com.ziran.meiliao.ui.bean.SpaceRecommendBean;
import com.ziran.meiliao.ui.bean.UpdateUserNewHeadBean;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.PicassoEngine;
import com.ziran.meiliao.widget.CustomClickListener;
import com.ziran.meiliao.widget.CustomEditText;
import com.ziran.meiliao.widget.ItemGroupView;
import com.ziran.meiliao.widget.SoundPlayer;
import com.ziran.meiliao.widget.SoundRecorder;
import com.ziran.meiliao.widget.VoiceManager;
import com.ziran.meiliao.widget.pupop.BasePopupWindow;
import com.ziran.meiliao.widget.pupop.PopupWindowUtil;
import com.ziran.meiliao.widget.pupop.PrivatePopupWindow;
import com.ziran.meiliao.widget.pupop.RecordPopupWindow;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerActivity;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerPreviewActivity;
import cn.bingoogolapple.photopicker.widget.BGASortableNinePhotoLayout;
import me.xfans.lib.voicewaveview.LineType;
import me.xfans.lib.voicewaveview.VoiceWaveView;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static com.ziran.meiliao.constant.ApiKey.ADMIN_TOOL_TESTSECURITY;

/**
 * Created by ${chenyn} on 2017/5/7.
 */

public class ReleaseWechatActivity extends BaseActivity implements BGASortableNinePhotoLayout.Delegate{
    private static final int RC_PHOTO_PREVIEW = 2;
    private static final int REQUEST_CODE_A = 3;
    private static final int RC_CHOOSE_PHOTO = 1;
    private static final int PRC_PHOTO_PICKER = 1;
    private static final int REQUEST_CODE = 1024;

    @Bind(R.id.iv_delete)
    ImageView ivDelete;
    @Bind(R.id.arl_sound)
    AutoRelativeLayout arlSound;
    @Bind(R.id.recycler)
    RecyclerView recycler;
    @Bind(R.id.all_select)
    AutoLinearLayout allSelect;
    @Bind(R.id.et_content)
    CustomEditText et_content;
    @Bind(R.id.igv_private)
    ItemGroupView igvPrivate;
    @Bind(R.id.igv_address)
    ItemGroupView igvAddress;
    @Bind(R.id.tv_fb)
    TextView tvFb;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.voiceWaveView4)
    VoiceWaveView voiceWaveView4;
    @Bind(R.id.snpl_moment_add_photos)
    BGASortableNinePhotoLayout mPhotosSnpl;
    private static final int REQUEST_IMAGE = 2;
    List<String> mSelect;
    private int status;
    private String longitude;
    private String latitude;
    private String address;
    private ArrayList<String> paths;
    private ArrayList<String> GDpaths;
    private boolean mSingleChoiceCb = false;
    private LocationClient mLocationClient;
    private int chooseMode;
    private Bundle savedInstanceState;
    private List<LocalMedia> selectList;
    private GridImageAdapter mAdapter;
    private SoundRecorder mSoundRecorder;
    private SoundPlayer soundPlayer;
    private RecordPopupWindow popupWindow;
    private VoiceManager voiceManager;
    private String mFilePath="";
    private String mStrLength;
    private Bitmap bitmap;


    public static void startAction(int code) {
        Activity activity = AppManager.getAppManager().currentActivity();
        Intent intent = new Intent(activity, ReleaseWechatActivity.class);
        activity.startActivityForResult(intent, code);
    }


    @Override
    public int getLayoutId() {
        return R.layout.ac_release_wechat;
    }

    @Override
    public void initPresenter() {

    }
    /**
     * 清空缓存包括裁剪、压缩、AndroidQToPath所生成的文件，注意调用时机必须是处理完本身的业务逻辑后调用；非强制性
     */
    private void clearCache() {
        // 清空图片缓存，包括裁剪、压缩后的图片 注意:必须要在上传完成后调用 必须要获取权限
        if (PermissionChecker.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            //PictureFileUtils.deleteCacheDirFile(this, PictureMimeType.ofImage());
            PictureFileUtils.deleteAllCacheDirFile(mContext);
        } else {
            PermissionChecker.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PictureConfig.APPLY_STORAGE_PERMISSIONS_CODE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(arlSound.getVisibility()==View.VISIBLE){
            allSelect.setVisibility(View.GONE);
        }
    }

    @Override
    public void onCreate(Bundle save) {
        super.onCreate(save);
        savedInstanceState = save;
        if (save != null) {
            // 被回收
        } else {
            clearCache();
        }
    }

    @Override
    public void initView() {
        igvPrivate.setLeftImage();
        igvAddress.setLeftImage();
        igvAddress.showBottomLine(false);
        mSelect = new ArrayList<>();
        // 设置拖拽排序控件的代理
        mPhotosSnpl.setDelegate(this);
        addVoice();
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        mLocationClient.start();
        tvFb.setOnClickListener(new CustomClickListener() {
            @Override
            protected void onSingleClick() {
                if(mFilePath.length()==0&&selectList==null&&paths==null){
                    ToastUitl.showShort("请至少添加一张图片或音视频才能发布哦");
                    return;
                }
                if(!StringUtils.isEmpty(et_content.getContent())){
                    checkSecurity();
                }else {
                    typeRelease();
                }
            }

            @Override
            protected void onFastClick() {
                //连续点击
            }
        });
        voiceManager =  VoiceManager.getInstance(mContext);
        mAdapter = new GridImageAdapter(mContext,null,allSelect,recycler);
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this,
                4, GridLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(manager);

        recycler.addItemDecoration(new GridSpacingItemDecoration(4,
                ScreenUtils.dip2px(this, 8), false));
        mAdapter.setOnItemClickListener((v, position) -> {
            List<LocalMedia> selectList = mAdapter.getData();
            if (selectList.size() > 0) {
                LocalMedia media = selectList.get(position);
                String mimeType = media.getMimeType();
                PictureSelector.create(this)
                        .themeStyle(R.style.picture_default_style)
                        .externalPictureVideo(TextUtils.isEmpty(media.getAndroidQToPath()) ? media.getPath() : media.getAndroidQToPath());
            }
        });
        recycler.setAdapter(mAdapter);
        if (savedInstanceState != null && savedInstanceState.getParcelableArrayList("") != null) {
            mAdapter.setList(savedInstanceState.getParcelableArrayList(""));
        }    }

    public void typeRelease(){
            if (paths != null) {
                uploadFiles();
            } else if(selectList!=null&&selectList.size()>0){
                LocalMedia localMedia = selectList.get(0);
                getUploadAuth(TextUtils.isEmpty(localMedia.getAndroidQToPath()) ? localMedia.getPath() : localMedia.getAndroidQToPath(),"视频");
            }else if(arlSound.getVisibility()==View.VISIBLE&&mFilePath.length()>0){
                getUploadAuth(mFilePath,"音频");
            }else {
                spaceAdd(null,0, "","");
            }
        }






    private void addVoice() {
        for(int i=0;i<100;i++){
            voiceWaveView4.addBody((int)(Math.random()*100));
        }
    }

    private void checkSecurity() {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("content",et_content.getContent());
        OkHttpClientManager.getAsyncMore(ADMIN_TOOL_TESTSECURITY,defMap, new
                NewRequestCallBack<Result>(Result.class) {
                    @Override
                    public void onSuccess(Result result) {
                        if(result.getResultCode()==0){
                            typeRelease();
                        }
                    }

                    @Override
                    public void onError(String msg, int code) {
                        ToastUitl.showShort(msg);

                    }
                });
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mAdapter != null && mAdapter.getData() != null && mAdapter.getData().size() > 0) {
            outState.putParcelableArrayList("",
                    (ArrayList<? extends Parcelable>) mAdapter.getData());
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if(resultCode == RESULT_OK && requestCode == PictureConfig.CHOOSE_REQUEST){
                selectList = PictureSelector.obtainMultipleResult(data);
                mAdapter.setList(selectList);
                recycler.setVisibility(View.VISIBLE);
                mAdapter.notifyDataSetChanged();
            }
        if (resultCode == RESULT_OK && requestCode == RC_CHOOSE_PHOTO&&data!=null) {
            mPhotosSnpl.addMoreData(BGAPhotoPickerActivity.getSelectedPhotos(data));
            paths = BGAPhotoPickerPreviewActivity.getSelectedPhotos(data);
        } else if (requestCode == RC_PHOTO_PREVIEW&&data!=null) {
            mPhotosSnpl.setData(BGAPhotoPickerPreviewActivity.getSelectedPhotos(data));
            paths = BGAPhotoPickerPreviewActivity.getSelectedPhotos(data);
        }
        if(paths==null||paths.size()==0){
            mPhotosSnpl.setVisibility(View.GONE);
        }else {
            mPhotosSnpl.setVisibility(View.VISIBLE);
        }
        if((paths==null||paths.size()==0)&&(selectList==null||selectList.size()==0)){
            allSelect.setVisibility(View.VISIBLE);
        }else {
            allSelect.setVisibility(View.GONE);
        }
        if (requestCode == REQUEST_CODE_A) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    address = data.getStringExtra("address");
                    longitude = data.getStringExtra("longitude");
                    latitude = data.getStringExtra("latitude");
                    igvAddress.setRigthText(address);
                }
            }

        }
    }

    //点击监听
    @OnClick({R.id.tv_back, R.id.igv_address, R.id.igv_private,R.id.iv_video,R.id.iv_pic,R.id.iv_sound,R.id.iv_delete,R.id.iv_play})
    public void onClick(View view) {
//        if (!MyAPP.isLogin(mContext)) { //如果没有登录则跳转到登录界面
//            return;
//        }
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.igv_private:
                PrivatePopupWindow pop = new PrivatePopupWindow(mContext, igvPrivate);
                PopupWindowUtil.show(this, pop);
                break;
            case R.id.igv_address:
                Intent intent = new Intent(this, BaiduMapActivity.class);
                startActivityForResult(intent, REQUEST_CODE_A);
                break;
            case R.id.iv_video:
                PictureSelector.create(this)
                        .openGallery(PictureMimeType.ofVideo())
                        .loadImageEngine(PicassoEngine.createPicassoEngine()) // 请参考Demo GlideEngine.java
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                break;
            case  R.id.iv_pic:
                choicePhotoWrapper();
                break;
            case R.id.iv_sound:
                if(ContextCompat.checkSelfPermission(mContext, android.Manifest.permission.RECORD_AUDIO)
                        != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions((Activity) mContext,new String[]{
                            android.Manifest.permission.RECORD_AUDIO},1);
                }else {
                    pop();
                }
                break;
            case R.id.iv_delete:
                allSelect.setVisibility(View.VISIBLE);
                ivDelete.setVisibility(View.GONE);
                arlSound.setVisibility(View.GONE);
                break;
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
                        iv.setImageResource(R.mipmap.icon_record_play);
                    }

                    @Override
                    public void playStart() {
                        voiceWaveView4.start();
                        iv.setImageResource(R.mipmap.icon_record_suspend);
                    }

                    @Override
                    public void playFinish() {
                        if(voiceWaveView4!=null){
                            voiceWaveView4.stop();
                        }
                        tvTime.setText("00:00"+" / "+mStrLength);
                        iv.setImageResource(R.mipmap.icon_record_play);
                    }
                });
                voiceManager.continueOrPausePlay(mFilePath);
                break;
        }
    }

    private void pop() {
        RecordPopupWindow popupWindow = new RecordPopupWindow(mContext);
        popupWindow.setOnFinish(( strLength, filePath) -> {
            allSelect.setVisibility(View.GONE);
            arlSound.setVisibility(View.VISIBLE);
            ivDelete.setVisibility(View.VISIBLE);
            tvTime.setText("00:00"+" / "+strLength);
            mStrLength=strLength;
            mFilePath=filePath;
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(voiceManager!=null){
            voiceManager.stopRecordAndPlay();
            voiceManager=null;
        }
    }


    private void uploadFiles() {
        startProgressDialog("正在发布...");
        OkHttpClientManager.upLoadFiles(ApiKey.ADMIN_SYS_FILE_UPLOADS, MyAPP.getAccessToken(),paths, new
                NewRequestCallBack<FileListBean>(FileListBean.class) {

                    @Override
                    public void onSuccess(FileListBean listData) {
                        ArrayList<String> imgs = new ArrayList<>();
                        for (int i = 0; i < listData.getData().size(); i++) {
                            imgs.add(listData.getData().get(i).getUrl());
                        }
                        spaceAdd(imgs, 0,"","");
                    }

                    @Override
                    public void onError(String msg, int code) {
                        ToastUitl.showShort(msg);
                        stopProgressDialog();
                    }
                });
    }

    private void uploadCoverUrl(String videoId) {
        File file = new File(Environment.getExternalStorageDirectory() + "/temp.jpg");
        BitmapUtils.saveBitmapFile(bitmap,file);
        OkHttpClientManager.postContentAndFiles(ApiKey.ADMIN_FILE_UPLOAD, MapUtils.getDefMap(true),FileUtil.str2File(file.getPath()), new
                NewRequestCallBack<UpdateUserNewHeadBean>(UpdateUserNewHeadBean.class) {

                    @Override
                    public void onSuccess(UpdateUserNewHeadBean result) {
                        String url = result.getData().getUrl();
                        spaceAdd(null,1,videoId,url);
                    }

                    @Override
                    public void onError(String msg, int code) {
                        stopProgressDialog();
                        ToastUitl.showShort(msg);
                    }
                });
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void spaceAdd(ArrayList<String> list, int type, String videoId,String videoImg) {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("userId", MyAPP.getUserId());
        if(videoImg!=null&&videoImg.length()>0){
            defMap.put("images", videoImg);
        }
        if(list!=null&&list.size()>0){
            if(list.size()>1){
                String str = String.join(",", list);
                defMap.put("images", str);
            }else {
                defMap.put("images", list.get(0));
            }
        }
        if (igvPrivate.getRightText().equals("仅自己可见")) {
            defMap.put("status", "0");
        } else {
            defMap.put("status", "1");
        }
        defMap.put("enclosureType", type+"");
        if (!igvAddress.getRightText().equals("不显示位置")) {
            defMap.put("address", address);
        }
        defMap.put("enclosureType",type+"");
        defMap.put("latitude", latitude);
        defMap.put("content",et_content.getContent());
        defMap.put("longitude", longitude);
        if(!EmptyUtils.isEmpty(videoId)){
            defMap.put("videoId", videoId);
        }
        defMap.put("duration",mStrLength);
        OkHttpClientManager.postAsyncAddHead(ApiKey.ADMIN_SPACE_ADD, defMap, "", new
                NewRequestCallBack<Result>(Result.class) {

                    @Override
                    public void onSuccess(Result listBean) {
                        Intent intent = new Intent();
                        mRxManager.post(AppConstant.RXTag.UPDATE_USER, "");
                        setResult(Activity.RESULT_OK, intent);
                        // RESULT_OK就是一个默认值，=-1，它说OK就OK吧
                        finish();
                        stopProgressDialog();
                    }

                    @Override
                    public void onError(String msg, int code) {
                        ToastUitl.showShort(msg);
                        stopProgressDialog();
                    }
                });


    }
    /**
     * 压缩本地图片
     * @param srcPath
     * @return
     */
    private Bitmap compressImageFromFile(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;//只读边,不读内容
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        float hh = 800f;//
        float ww = 480f;//
        int be = 1;
        if (w > h && w > ww) {
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置采样率

        newOpts.inPreferredConfig = Bitmap.Config.ARGB_8888;//该模式是默认的,可不设
        newOpts.inPurgeable = true;// 同时设置才会有效
        newOpts.inInputShareable = true;//。当系统内存不够时候图片自动被回收

        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
//      return compressBmpFromBmp(bitmap);//原来的方法调用了这个方法企图进行二次压缩
        //其实是无效的,大家尽管尝试
        return bitmap;
    }

public void upload(AliUploadFileBean.DataBean result, String path, String type){
// create VODUploadClient
    final VODUploadClient uploader = new VODUploadClientImpl(getApplicationContext());
// setup callback
    VODUploadCallback vodUploadCallback = new VODUploadCallback(){


        @Override
        public void onUploadFailed(UploadFileInfo info, String code, String message) {
            OSSLog.logError("onfailed ------------------ " + info.getFilePath() + " " + code + " " + message);
            Log.e("onUploadFailed",""+ " " + code + " " + message);
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
            Log.e("onUploadStarted",""+ "onUploadStarted " );
            uploader.setUploadAuthAndAddress(uploadFileInfo, result.getUploadAuth(), result.getUploadAddress());
        }

        @Override
        public void onUploadSucceed(UploadFileInfo info) {
            OSSLog.logDebug("onsucceed ------------------" + info.getVodInfo().getCoverUrl());
            if(type.equals("视频")){
                uploadCoverUrl(result.getVideoId());
            }else {
                spaceAdd(null,2,result.getVideoId(),"");
            }
        }

        @Override
        public void onUploadTokenExpired() {
            OSSLog.logError("onExpired ------------- ");
            // 重新刷新上传凭证:RefreshUploadVideo

            refreshUploadAuth(path,type,uploader,result.getVideoId());
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


    @AfterPermissionGranted(PRC_PHOTO_PICKER)
    private void choicePhotoWrapper() {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(this, perms)) {
            // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话就没有拍照功能
            File takePhotoDir = new File(Environment.getExternalStorageDirectory(), "BGAPhotoPickerTakePhoto");

            Intent photoPickerIntent = new BGAPhotoPickerActivity.IntentBuilder(this)
                    .cameraFileDir(takePhotoDir) // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话则不开启图库里的拍照功能
                    .maxChooseCount(mPhotosSnpl.getMaxItemCount() - mPhotosSnpl.getItemCount()) // 图片选择张数的最大值
                    .selectedPhotos(null) // 当前已选中的图片路径集合
                    .pauseOnScroll(false) // 滚动列表时是否暂停加载图片
                    .build();
            startActivityForResult(photoPickerIntent, RC_CHOOSE_PHOTO);
        } else {
            EasyPermissions.requestPermissions(this, "图片选择需要以下权限:\n\n1.访问设备上的照片\n\n2.拍照", PRC_PHOTO_PICKER, perms);
        }
    }

    @Override
    public void onClickAddNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, ArrayList<String> models) {
        choicePhotoWrapper();
    }

    @Override
    public void onClickDeleteNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, String model, ArrayList<String> models) {
        mPhotosSnpl.removeItem(position);
        paths=mPhotosSnpl.getData();
        if(paths==null||paths.size()==0){
            mPhotosSnpl.setVisibility(View.GONE);
            allSelect.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onClickNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, String model, ArrayList<String> models) {
        Intent photoPickerPreviewIntent = new BGAPhotoPickerPreviewActivity.IntentBuilder(this)
                .previewPhotos(models) // 当前预览的图片路径集合
                .selectedPhotos(models) // 当前已选中的图片路径集合
                .maxChooseCount(mPhotosSnpl.getMaxItemCount()) // 图片选择张数的最大值
                .currentPosition(position) // 当前预览图片的索引
                .isFromTakePhoto(false) // 是否是拍完照后跳转过来
                .build();
        startActivityForResult(photoPickerPreviewIntent, RC_PHOTO_PREVIEW);
    }

    @Override
    public void onNinePhotoItemExchanged(BGASortableNinePhotoLayout sortableNinePhotoLayout, int fromPosition, int toPosition, ArrayList<String> models) {
    }


    /**
     * 定位SDK监听函数
     *
     * @author
     */
    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null) {
                return;
            }
            longitude = location.getLongitude() + "";
            latitude = location.getLatitude() + "";


        }
    }

    public BDLocationListener myListener = new MyLocationListener();

}
