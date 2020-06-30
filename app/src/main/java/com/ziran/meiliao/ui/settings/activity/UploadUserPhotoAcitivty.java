package com.ziran.meiliao.ui.settings.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.bumptech.glide.Glide;
import com.umeng.analytics.AnalyticsConfig;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MeiliaoConfig;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonwidget.LoadingDialog;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.compressorutils.FileSizeUtil;
import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.entry.UserRegBean;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.im.activity.MainActivity;
import com.ziran.meiliao.im.database.UserEntry;
import com.ziran.meiliao.im.utils.SharePreferenceManager;
import com.ziran.meiliao.im.utils.ThreadUtil;
import com.ziran.meiliao.ui.bean.UpdateUserNewHeadBean;
import com.ziran.meiliao.ui.bean.UserBean;
import com.ziran.meiliao.utils.DeviceUtil;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.GlideCircleTransform;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;


/**
 * 上传照片
 * Created by Administrator on 2017/1/14.
 */

public class UploadUserPhotoAcitivty extends BaseActivity {


    private static String cameraScalePath;
    @Bind(R.id.ntb)
    NormalTitleBar ntb;
    @Bind(R.id.tv_finish)
    TextView tvFinish;
    @Bind(R.id.iv_user_avatar)
    ImageView iv_user_avatar;
    private UserRegBean userBean;
    private String path;
    private String latitude;
    private String longitude;
    private LocationClient mLocationClient;
    private UserInfo mMyInfo;
    private File file;
    public static final int RequestCrop = 3;
    private Uri output;

    public static void startAction(Context context, UserRegBean userInfo) {
        Intent intent = new Intent(context, UploadUserPhotoAcitivty.class);
        intent.putExtra("USER_INFO", userInfo);
        Log.e("startAction", "" + userInfo.toString());
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_input_info_three;
    }

    @Override
    public void initPresenter() {

    }

    //点击监听
    @OnClick({R.id.tv_finish, R.id.iv_user_avatar})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_finish:
                registerIm();
                break;
            case R.id.iv_user_avatar:
                ImgSelActivity.startActivity(this,ImgSelConfig.DEFAULT_SIGN, ImgSelConfig.RequestCode);
                break;
        }
    }

    private void registerIm() {
        JMessageClient.logout();
        startProgressDialog("正在上传资料..");
        JMessageClient.register(MyAPP.getUserId(), MeiliaoConfig.IMUserPwd, new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                if (i == 0) {
                    login(mContext);
                    SharePreferenceManager.setRegisterName(MyAPP.getUserId());
                    SharePreferenceManager.setRegistePass(MeiliaoConfig.IMUserPwd);
                } else {
                    Log.e("registerIm",""+i);
                    //注册失败 登录 登录失败
                    if (i == 898001) {
                        //已注册 登录
                        login(mContext);
                    }
                }
            }
        });

    }

    public void login(Context mContext){
        JMessageClient.login(MyAPP.getUserId(), MeiliaoConfig.IMUserPwd, new BasicCallback() {
            @Override
            public void gotResult(int responseCode, String responseMessage) {
//            dialog.dismiss();
                if (responseCode == 0) {
                    SharePreferenceManager.setCachedPsw(MeiliaoConfig.IMUserPwd);
                    UserInfo myInfo = JMessageClient.getMyInfo();
                    File avatarFile = myInfo.getAvatarFile();
                    //登陆成功,如果用户有头像就把头像存起来,没有就设置null
                    if (avatarFile != null) {
                        SharePreferenceManager.setCachedAvatarPath(avatarFile.getAbsolutePath());
                    } else {
                        SharePreferenceManager.setCachedAvatarPath(null);
                    }
                    String username = myInfo.getUserName();
                    String appKey = myInfo.getAppKey();
                    UserEntry user = UserEntry.getUser(username, appKey);
                    if (null == user) {
                        user = new UserEntry(username, appKey);
                        user.save();
                    }
                    uploadImHeadIv();

                }
            }
        });
    }


    private void uploadImHeadIv() {
        imHeadUpload();
        upload();
    }

    private void imHeadUpload() {
        ThreadUtil.runInThread(new Runnable() {
            @Override
            public void run() {
                JMessageClient.updateUserAvatar(new File(cameraScalePath), new BasicCallback() {
                    @Override
                    public void gotResult(int responseCode, String responseMessage) {
                        if (responseCode == 0) {
                            Log.e("imHeadUpload",responseCode+""+responseMessage);
                            SharePreferenceManager.setCachedAvatarPath(cameraScalePath);
                        }else {
                            Log.e("imHeadUpload",responseCode+""+responseMessage);

                        }
                    }
                });
                updateImUserInfo();
            }
        });
    }

    private void upload() {
        OkHttpClientManager.postContentAndFiles(ApiKey.ADMIN_FILE_UPLOAD, MapUtils.getDefMap(true), FileUtil.str2File(cameraScalePath), new
                NewRequestCallBack<UpdateUserNewHeadBean>(UpdateUserNewHeadBean.class) {

                    @Override
                    public void onSuccess(UpdateUserNewHeadBean result) {
                        String url = result.getData().getUrl();
                        userBean.setAvatar(url);
                        InfoPerfect();
                    }

                    @Override
                    public void onError(String msg, int code) {
                        stopProgressDialog();
                        ToastUitl.showShort(msg);
                    }
                });
    }

    public void updateImUserInfo() {
        mMyInfo = JMessageClient.getMyInfo();
        if(mMyInfo!=null){
            mMyInfo.setNickname(userBean.getNickname());
            if (userBean.getSex().equals("1")) {
                mMyInfo.setGender(UserInfo.Gender.male);
            } else {
                mMyInfo.setGender(UserInfo.Gender.female);
            }

            JMessageClient.updateMyInfo(UserInfo.Field.all, mMyInfo, new BasicCallback() {
                @Override
                public void gotResult(int responseCode, String responseMessage) {
                    if (responseCode == 0) {
                    } else {
                        registerIm();
                        stopProgressDialog();
                    }
                }
            });
        }
    }

    private void InfoPerfect() {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("age", userBean.getAge());
        defMap.put("avatar", userBean.getAvatar());
        defMap.put("sex", userBean.getSex());
        defMap.put("nickname", userBean.getNickname());
        defMap.put("region", userBean.getRegion());
        defMap.put("latitude", latitude + "");
        defMap.put("id", MyAPP.getUserId());
        defMap.put("longitude", longitude + "");
        defMap.put("channelNo", getChannelName(this));
        defMap.put("versionNo", DeviceUtil.getVersionName(this));
        OkHttpClientManager.putAsyncAddHead(ApiKey.ADMIN_USER_INFOPERFECT, defMap, new
                NewRequestCallBack<UserBean>(UserBean.class) {
                    @Override
                    public void onSuccess(UserBean result) {
                        MyAPP.setmUserBean(result.getData());
                        MainActivity.startAction( "First");
                        finish();
                    }

                    @Override
                    public void onError(String msg, int code) {
                        stopProgressDialog();
                        ToastUitl.showShort(msg);
                    }
                });

    }
    public static String getChannelName(Context ctx){
        String channelName = AnalyticsConfig.getChannel(ctx);
        return channelName;
    }
        public Uri imageUri;


    private Bitmap decodeUriBitmap(Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }
    /**
     * 头像选择返回结果
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ImgSelConfig.RequestCode && data != null) {
            final ArrayList<String> imgPaths = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
            startUCrop(this,imgPaths.get(0),RequestCrop);
        }else if(resultCode == RESULT_OK &&requestCode==RequestCrop){
            if(cameraScalePath.length()>0){
                updateUserHead();
            }
        }
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
        //设置toolbar颜色
        options.setToolbarColor(ActivityCompat.getColor(activity, com.yuyh.library.imgsel.R.color.black));
        //设置状态栏颜色
        options.setStatusBarColor(ActivityCompat.getColor(activity, com.yuyh.library.imgsel.R.color.black));
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

    public File getFile(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        File file = new File(Environment.getExternalStorageDirectory() + "/temp.jpg");
        try {
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            InputStream is = new ByteArrayInputStream(baos.toByteArray());
            int x = 0;
            byte[] b = new byte[1024 * 100];
            while ((x = is.read(b)) != -1) {
                fos.write(b, 0, x);
            }
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }


    private void updateUserHead() {
        LoadingDialog.cancelDialogForLoading();
        Glide.with(mContext).load(cameraScalePath).transform(new GlideCircleTransform(mContext)).into(iv_user_avatar);
        if (TextUtils.isEmpty(MyAPP.getUserInfo().getHeadImg())) {
            MyAPP.getUserInfo().setHeadImg(cameraScalePath);
        }
        tvFinish.setBackgroundResource(R.drawable.normal_bg_bule);
        tvFinish.setEnabled(true);
    }

    @Override
    public void initView() {
        if (getIntent() != null) {
            userBean = getIntent().getParcelableExtra("USER_INFO");
        }
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        mLocationClient.start();

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
