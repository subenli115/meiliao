package com.ziran.meiliao.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;

import androidx.multidex.MultiDex;

import com.activeandroid.ActiveAndroid;
import com.aliyun.player.AliPlayer;
import com.aliyun.player.AliPlayerFactory;
import com.baidu.mapapi.SDKInitializer;
import com.bumptech.glide.request.target.ViewTarget;
import com.chuanglan.shanyan_sdk.OneKeyLoginManager;
import com.chuanglan.shanyan_sdk.listener.InitListener;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.ziran.meiliao.BuildConfig;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.common.baseapp.BaseApplication;
import com.ziran.meiliao.common.commonutils.ACache;
import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.commonutils.NetWorkUtils;
import com.ziran.meiliao.common.commonutils.SPUtils;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.crash.Cockroach;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.dao.UserInfoDao;
import com.ziran.meiliao.db.DbCore;
import com.ziran.meiliao.entry.LoginBean;
import com.ziran.meiliao.entry.UserInfo;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.envet.OnLoadDataListener;
import com.ziran.meiliao.im.activity.MainActivity;
import com.ziran.meiliao.im.application.JGApplication;
import com.ziran.meiliao.im.database.UserEntry;
import com.ziran.meiliao.im.entity.NotificationClickEventReceiver;
import com.ziran.meiliao.im.location.service.LocationService;
import com.ziran.meiliao.im.pickerimage.utils.StorageUtil;
import com.ziran.meiliao.im.utils.SharePreferenceManager;
import com.ziran.meiliao.im.utils.imagepicker.GlideImageLoader;
import com.ziran.meiliao.im.utils.imagepicker.ImagePicker;
import com.ziran.meiliao.im.utils.imagepicker.view.CropImageView;
import com.ziran.meiliao.ui.bean.CheckVipLevelBean;
import com.ziran.meiliao.ui.bean.UserAccountBean;
import com.ziran.meiliao.ui.bean.UserBean;
import com.ziran.meiliao.ui.bean.UserInfoBean;
import com.ziran.meiliao.ui.main.activity.NewLoginActivity;
import com.ziran.meiliao.ui.main.activity.SplashActivity;
import com.ziran.meiliao.ui.settings.activity.InputUserInfoActivity;
import com.ziran.meiliao.ui.settings.activity.IntputCodeActivity;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.PrefUtils;
import com.ziran.meiliao.utils.StringUtils;
import com.ziran.meiliao.utils.UMengKit;
//import com.taobao.sophix.SophixManager;
//import com.taobao.sophix.listener.PatchLoadStatusListener;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;


import java.io.File;
import java.util.Map;

import cn.bingoogolapple.photopicker.imageloader.BGAImage;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;


/**
 * APPLICATION
 */
public class MyAPP extends BaseApplication {

    //用户token
    private static String accessToken;
    public static LocationService locationService;

    //用户id
    private static String userId;
    //当前用户的资料
    public static UserInfo mUserInfo;
    public static boolean reqPerOk = false;
    //是否登出
    public static boolean isLogout;
    //支付
    public static String secretId;


    public static String mobCourseId = "";

    //微信APP id
    public static String wcAppId = "wx9f81f7c83ead9d5d";



    //是否是VIP会员
    private static CheckVipLevelBean vipLevelBean;

    //友盟设备token
    private static String deviceToken;
    private static UserInfo userInfo;
    private static ACache mCache;
    private static Gson g;
    private MediaPlayer mMediaPlayer;

    public static UserBean.DataBean getmUserBean() {
        if(mUserBean!=null){
            return mUserBean;
        }else {
            return g.fromJson(mCache.getAsString(""+userId),  UserBean.DataBean.class);
        }

    }

    public static void setmUserBean(UserBean.DataBean mUserBean) {
        String json = g.toJson(mUserBean);
        mCache.put(mUserBean.getId(),json);
        MyAPP.mUserBean = mUserBean;
    }

    private static UserBean.DataBean mUserBean;
    public static Context context;
    private static String THUMP_PICTURE_DIR;

    //分包处理
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
        xcrash.XCrash.init(this);
    }

    public static String getUserId() {
        if(userId==null||userId.length()==0){
            userId= SPUtils.getString(AppConstant.SPKey.USERID);
        }
            return userId;

    }

    public static void setUserId(String userId) {
        MyAPP.userId = userId;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        initHotfix();
        ViewTarget.setTagId(R.id.glide_tag);
        //初始化数据库
        DbCore.init(this);
        //初始化服务管理器
        context = getApplicationContext();
        //安装异常处理
        initCockroach();
        //友盟初始化
        UMengKit.init(this);
        //闪验SDK配置debug开关 （必须放在初始化之前，开启后可打印闪验SDK更加详细日志信息）
        OneKeyLoginManager.getInstance().setDebug(false);
        //闪验SDK初始化（建议放在Application的onCreate方法中执行）
        initShanyanSDK(getApplicationContext());
        MeiliaoConfig.isDowning = true;
        //java代码
        //是否开启友盟日志
        Config.DEBUG = BuildConfig.DEBUG;
        LogUtils.logInit(BuildConfig.DEBUG);
        setOffLine(false);
        g = new Gson();
        mCache = ACache.get(this);
        OkHttpClientManager.setDebug(false);
        initIm();
        JPushInterface.setDebugMode(false); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush
        mMediaPlayer=MediaPlayer.create(this, R.raw.music);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();
    }


    private void initIm() {
        THUMP_PICTURE_DIR = context.getFilesDir().getAbsolutePath() + "/JChatDemo";
        JGApplication.setThumpPictureDir(THUMP_PICTURE_DIR);
        StorageUtil.init(context, null);
        Fresco.initialize(getApplicationContext());
        SDKInitializer.initialize(getApplicationContext());
        locationService = new LocationService(getApplicationContext());
        JMessageClient.init(getApplicationContext(), true);
        JMessageClient.setDebugMode(true);
        SharePreferenceManager.init(getApplicationContext(), JGApplication.JCHAT_CONFIGS);
        //设置Notification的模式
        JMessageClient.setNotificationFlag(JMessageClient.FLAG_NOTIFY_WITH_SOUND | JMessageClient.FLAG_NOTIFY_WITH_LED | JMessageClient.FLAG_NOTIFY_WITH_VIBRATE);
        //注册Notification点击的接收器
        new NotificationClickEventReceiver(getApplicationContext());
        initImagePicker();
        ActiveAndroid.initialize(context);
    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(true);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(JGApplication.maxImgCount);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
    }



    //Java代码
    private void initCockroach() {
        Cockroach.install(new Cockroach.ExceptionHandler() {
            // handlerException内部建议手动try{  你的异常处理逻辑  }catch(Throwable e){ } ，以防handlerException内部再次抛出异常，导致循环调用handlerException
            @Override
            public void handlerException(final Thread thread, final Throwable throwable) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            LogUtils.logd(thread + "\n" + throwable.toString());
                            throwable.printStackTrace();
                            if (BuildConfig.DEBUG) {
                                ToastUitl.showShort(thread + "\n" + throwable.toString());
                            }
                        } catch (Throwable e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }



    private void initHotfix() {
        if (!getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext())))
            return;
        String appVersion;
        try {
            appVersion = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
        } catch (Exception e) {
            appVersion = "1.0.0";
        }
//
//        SophixManager.getInstance().setContext(this)
//                .setAppVersion(appVersion)
//                .setAesKey(null)
//                //.setAesKey("0123456789123456")
//                .setEnableDebug(BuildConfig.DEBUG)
//                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
//                    @Override
//                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
//                        String msg = new StringBuilder("").append("Mode:").append(mode)
//                                .append(" Code:").append(code)
//                                .append(" Info:").append(info)
//                                .append(" HandlePatchVersion:").append(handlePatchVersion).toString();
//                        LogUtils.logd("PatchLoadStatusListener:" + msg);
//                    }
//                }).initialize();
//
//        SophixManager.getInstance().queryAndLoadNewPatch();
    }


    //各个平台的配置，建议放在全局Application或者程序入口
    {
        PlatformConfig.setWeixin("wx9f81f7c83ead9d5d", "568ced93be85a80376e3d4d822e35af3");
//        PlatformConfig.setSinaWeibo("1819414227", AES.get().decrypt("wlM5kjycuUmjrXoIr/odsvkebRtFng5+EWNlXPh0tzwtd8zmrRwh08+fPVrP78JP"), "http://sns.whalecloud.com");
        PlatformConfig.setQQZone("101865253", "4cadff1a78775740c1f0073e6b736f1b");
    }

    //获取当前进程的名称
    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }


    //判断用户是否登录
    public static boolean isLogin() {
        return !TextUtils.isEmpty(accessToken);
    }


    //如果用户没有登录则直接跳转到登录界面
    public static boolean isLogin(Context context) {
        if (TextUtils.isEmpty(accessToken)) {
            NewLoginActivity.startAction(context);
            return false;
        }
        return true;
    }


    //登出
    public static void logout(Context context) {

        OkHttpClientManager.deleteAsync(ApiKey.AUTH_TOKEN_LOGOUT,MapUtils.getDefMap(true), "",new
                NewRequestCallBack<Result>(Result.class) {
                    @Override
                    public void onSuccess(Result result) {
                        logout(context, SplashActivity.class);
                    }

                    @Override
                    public void onError(String msg, int code) {
                        logout(context, SplashActivity.class);
                    }
                });
    }

    //登出
    public static void logout(Context context, Class cls) {
        setAccessToken(null);
        setIsLogout(true);
        setmUserInfo(null);
        JMessageClient.logout();
        mCache.remove("mUserBean");
        AppManager.getAppManager().finishAllActivity();
        SPUtils.remove(AppConstant.SPKey.TOKEN);
        SPUtils.remove(AppConstant.SPKey.USERID);
        Intent intent = new Intent(context, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


//    //登录
    public static void loadUserInfo(final OnLoadDataListener<UserInfo> listener) {
        if (!isLogin()) {    //如果没有登录则
            String token = SPUtils.getString(AppConstant.SPKey.TOKEN);
            if (TextUtils.isEmpty(token)) {  //如果当期token为空则表示没有登录过,返回游客
                return;
            } else {
                setAccessToken(token);
            }
        }
        //如果已经登录了,直接返回
        if (getUserInfo().getId() != null && getUserInfo().getId() == 1) {
            listener.loadSuccess(getUserInfo());
            return;
        }

        //如果网络已连接则从网络中获取用户信息
        if (NetWorkUtils.isNetConnected(getAppContext())) {
            OkHttpClientManager.postAsync(ApiKey.USER_INFO, MapUtils.getDefMap(true), new NewRequestCallBack<UserInfoBean>(UserInfoBean.class) {
                @Override
                public void onSuccess(UserInfoBean result) {//登录请求成功后执行
                    //判断数据库是否有该用户,没有则添加进去
                    getDBUserInfo(result.getUserInfo());
                    listener.loadSuccess(result.getUserInfo());     //回调用户信息

                }

                @Override
                public void onError(String msg, int code) {
                    if (code == -1) {
                        listener.loadSuccess(null);
                    }
                }
            });
        } else {//如果没有网络则从数据库中获取数据
            LogUtils.logd(StringUtils.getText(R.string.userinfo_from_db));
            final UserInfoDao userInfoDao = DbCore.getDaoSession().getUserInfoDao();
            UserInfo userInfo = userInfoDao.load(1L);
            if (userInfo == null) {
                userInfo = UserInfo.GUSET();
            }
            setmUserInfo(userInfo);
            listener.loadSuccess(userInfo);
        }
    }

    public static void imLogin(Context context) {
        //检测账号是否登陆
        if(JMessageClient.getMyInfo()!=null&&JMessageClient.getMyInfo().getUserName().equals(MyAPP.getUserInfo())){
            goToMainActivity(context);
        }else {
            jmLogin(context);
        }
    }

    private static void jmLogin(Context context) {
        JMessageClient.login(MyAPP.getUserId(), MeiliaoConfig.IMUserPwd, new BasicCallback() {
            @Override
            public void gotResult(int responseCode, String responseMessage) {
                if (responseCode == 0) {
                    SharePreferenceManager.setCachedPsw(MeiliaoConfig.IMUserPwd);
                    cn.jpush.im.android.api.model.UserInfo myInfo = JMessageClient.getMyInfo();
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
                    goToMainActivity(context);
                }else {
                    ToastUitl.showShort("正在重试");
                    jmLogin(context);

                }
            }
        });
    }

    public static void goToMainActivity(Context context) {
        MainActivity.startAction( "");
//        InputUserInfoActivity.startAction(context);
//     com.ziran.meiliao.ui.main.activity.MainActivity.startAction((Activity) context, 1);
        if(!(((Activity)context) instanceof SplashActivity)){
            ((Activity)context).finish();
        }
        OneKeyLoginManager.getInstance().finishAuthActivity();
        OneKeyLoginManager.getInstance().removeAllListener();
        OneKeyLoginManager.getInstance().setLoadingVisibility(false);
    }

    //判断数据库是否有该用户,没有则添加进去
    public static UserInfo getDBUserInfo(UserInfo result) {
        final UserInfoDao userInfoDao = DbCore.getDaoSession().getUserInfoDao();

        try {
            //代码区
            userInfo = userInfoDao.load(1L);
        } catch (Exception e) {
            //异常处理
        }
        if (userInfo == null) {
            userInfoDao.insert(result);
            userInfo = result;
        } else {
            try {
                result.setId(1L);
                userInfoDao.update(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //设置当前全局用户信息
        setmUserInfo(result);
        return userInfo;
    }


    //获取当前登录的用户信息
    public static UserInfo getUserInfo() {
        return mUserInfo == null ? UserInfo.GUSET() : mUserInfo;
    }

    public static void setAccessToken(String accessToken) {
        MyAPP.accessToken = accessToken;
    }


    public static String getAccessToken() {
        if(accessToken==null||accessToken.length()==0){
            accessToken = SPUtils.getString(AppConstant.SPKey.TOKEN);
        }
        return accessToken;
    }

    public static void setReqPerOk(boolean reqPerOk) {
        MyAPP.reqPerOk = reqPerOk;
    }

    public static String getSecretId() {
        return secretId;
    }

    //设置当前登录的用户
    public static void setmUserInfo(UserInfo userInfo) {
        mUserInfo = userInfo;
        if (mUserInfo != null) {
            mUserInfo.setId(1L);
        }
    }


    private void initShanyanSDK(Context context) {
        OneKeyLoginManager.getInstance().init(context, "tN4dhmtp", new InitListener() {
            @Override
            public void getInitStatus(int code, String result) {
                //闪验SDK初始化结果回调
                Log.e("VVV", "初始化： code==" + code + "   result==" + result);
            }
        });
    }

    public static int saveMoney(UserAccountBean result) {
        if(result!=null&&result.getData()!=null){
            double gold = result.getData().getRecharge()+result.getData().getCurrency() ;
            MyAPP.getmUserBean().setUserAccount(result.getData());
            return (int)gold;
        }else {
            UserAccountBean.DataBean userAccount = MyAPP.getmUserBean().getUserAccount();
            if(userAccount!=null){
                UserAccountBean.DataBean data = userAccount;
                if(data!=null){
                    double gold = data.getRecharge()+data.getCurrency();
                    return (int)gold;
                }else {
                    return 0;
                }
            }else {
                return 0;
            }
        }
    }


    public static void saveUserLoginData(LoginBean loginBean) {
        SPUtils.setString(AppConstant.SPKey.TOKEN, loginBean.getAccess_token());
        SPUtils.setString(AppConstant.SPKey.USERID, loginBean.getId());
        MyAPP.setAccessToken(loginBean.getAccess_token());
        MyAPP.setUserId(loginBean.getId());
        SPUtils.setString(AppConstant.SPKey.R_TOKEN, loginBean.getRefresh_token());
        PrefUtils.putString("phone", loginBean.getUsername(), getContext());
    }

    public static void refreshToken() {
        String RToken = SPUtils.getString(AppConstant.SPKey.R_TOKEN);
        if (RToken != null && !RToken.equals("")) {
            Map<String, String> defMap = MapUtils.getDefMap(false);
            defMap.put("grant_type", "refresh_token");
            defMap.put("refresh_token", RToken);
            defMap.put("scope", "server");
            OkHttpClientManager.postAsyncAddHead(ApiKey.AUTH_OAUTH_TOKEN, defMap, "?grant_type=refresh_token", new
                    NewRequestCallBack<LoginBean>(LoginBean.class) {
                        @Override
                        public void onSuccess(LoginBean loginBean) {
                            MyAPP.saveUserLoginData(loginBean);
                        }

                        @Override
                        public void onError(String msg, int code) {
                            Intent intent = new Intent();
                            intent.setClass(context, IntputCodeActivity.class);
                            intent.putExtra("Connected", "false");
                            context.startActivity(intent);
                        }
                    });
        }
    }

    public static boolean isLogout() {
        return isLogout;
    }

    public static void setIsLogout(boolean isLogout) {
        MyAPP.isLogout = isLogout;
    }

    public static String getWcAppId() {
        return wcAppId;
    }


    public static CheckVipLevelBean getVipLevelBean() {
        return vipLevelBean;
    }


    public static String getDeviceToken() {
        return deviceToken;
    }

    public static void setDeviceToken(String deviceToken) {
        MyAPP.deviceToken = deviceToken;
    }




}
