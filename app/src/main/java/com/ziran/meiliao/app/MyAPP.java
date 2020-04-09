package com.ziran.meiliao.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.multidex.MultiDex;
import android.text.TextUtils;
import android.util.Log;

import com.mob.moblink.MobLink;
import com.mob.moblink.RestoreSceneListener;
import com.mob.moblink.Scene;
import com.ziran.meiliao.BuildConfig;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.common.baseapp.BaseApplication;
import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.commonutils.NetWorkUtils;
import com.ziran.meiliao.common.commonutils.SPUtils;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.ziran.meiliao.common.crash.Cockroach;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.common.security.AES;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.dao.UserInfoDao;
import com.ziran.meiliao.db.DbCore;
import com.ziran.meiliao.entry.UserInfo;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.envet.OnLoadDataListener;
import com.ziran.meiliao.service.ServiceManager;
import com.ziran.meiliao.ui.bean.CheckVipLevelBean;
import com.ziran.meiliao.ui.bean.ResBean;
import com.ziran.meiliao.ui.bean.UserInfoBean;
import com.ziran.meiliao.ui.main.activity.NewLoginActivity;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.PracticeDataUtil;
import com.ziran.meiliao.utils.StringUtils;
import com.ziran.meiliao.utils.UMengKit;
import com.ziran.meiliao.utils.UpdateVersionUtil;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;


import java.io.File;
import java.io.IOException;

import cn.beecloud.BCPay;
import cn.beecloud.BeeCloud;
import cn.rongcloud.live.LiveKit;


/**
 * APPLICATION
 */
public class MyAPP extends BaseApplication {

    //用户token
    private static String accessToken;
    //当前用户的资料
    public static UserInfo mUserInfo;
    public static boolean reqPerOk = false;
    //是否登出
    public static boolean isLogout;
    //支付
    public static String secretId;


    public static String mobCourseId="";

    //微信APP id
    private static String wcAppId = "wxb3c0d81ce83e5636";
    //是否初始化融云
    public static boolean isInit;

    //融云APP key
    public static final String RECOLE_APP_KEY = "8luwapkv8lzrl";//上线环境
//    public static final String RECOLE_APP_KEY = AES.get().decrypt("HZZxQqOo8yT6GDUPel+tpA==");//上线环境
//    public static final String RECOLE_APP_KEY = "uwd1c0sxuwbv1";//开发环境

    //音频播放服务管理类
    public static ServiceManager mServiceManager = null;

    //是否是VIP会员
    private static CheckVipLevelBean vipLevelBean;

    //友盟设备token
    private static String deviceToken;
    private static UserInfo userInfo;


    //分包处理
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    //Android 4.0会调用此方法获取数据库。
    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory,
                                               DatabaseErrorHandler errorHandler) {
        SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), null);
        return result;
    }
    @Override
    public File getDatabasePath(String name) {
        File parentFile = new File(Environment.getExternalStorageDirectory() + File.separator +
                "smartDB" + File.separator);
        if(!parentFile.exists()){
            boolean mkParentRes = parentFile.mkdirs();
        }

        File realDBFile = new File(parentFile,name);
        if(!realDBFile.exists()){
            try {
                realDBFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return realDBFile;
    }
    @Override
    public void onCreate() {
        super.onCreate();
//        initHotfix();
        //初始化数据库
        DbCore.init(this);
        //初始化服务管理器
        mServiceManager = new ServiceManager(this);
        //安装异常处理
        initCockroach();
        //友盟初始化
//        UMengKit.init(this);
        MyAPP.initLiveKit(this);
        WpyxConfig.isDowning=true;
        //java代码
        //是否开启友盟日志
        Config.DEBUG = BuildConfig.DEBUG;
        LogUtils.logInit(BuildConfig.DEBUG);
        setOffLine(false);
        OkHttpClientManager.setDebug(true);
        MobLink.setRestoreSceneListener(new SceneListener());
    }

    //Java代码
     class SceneListener extends Object implements RestoreSceneListener {
        @Override
        public Class<? extends Activity> willRestoreScene(Scene scene) {

            return null;
        }
        @Override
        public void notFoundScene(Scene scene) {
            //TODO 未找到处理scene的activity时回调
            MyAPP.setMobCourseId(scene.getParams().get("courseId")+"");
            Log.e("mobCourseId3",""+scene.getParams().get("courseId")+"");
        }
        @Override
        public void completeRestore(Scene scene) {
            // TODO 在"拉起"处理场景的Activity之后调用
        }
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
                            if (BuildConfig.DEBUG){
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
    public static String getMobCourseId() {
        return mobCourseId;
    }

    public static void setMobCourseId(String mobCourseId) {
        MyAPP.mobCourseId = mobCourseId;
    }


    private void initHotfix() {
        if (!getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext()))) return;
        String appVersion;
        try {
            appVersion = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
        } catch (Exception e) {
            appVersion = "1.0.0";
        }

        SophixManager.getInstance().setContext(this)
                .setAppVersion(appVersion)
                .setAesKey(null)
                //.setAesKey("0123456789123456")
                .setEnableDebug(BuildConfig.DEBUG)
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        String msg = new StringBuilder("").append("Mode:").append(mode)
                                .append(" Code:").append(code)
                                .append(" Info:").append(info)
                                .append(" HandlePatchVersion:").append(handlePatchVersion).toString();
                       LogUtils.logd("PatchLoadStatusListener:"+msg);
                    }
                }).initialize();

        SophixManager.getInstance().queryAndLoadNewPatch();
    }


    //各个平台的配置，建议放在全局Application或者程序入口
    {
        PlatformConfig.setWeixin("wxb3c0d81ce83e5636", AES.get().decrypt("bYYIl2Usv00zdru6yiVWj8FZDH/DLc3xlzFVTjzn/lLRIszZ+WmBWF7aO1gZ2i3F"));
        PlatformConfig.setSinaWeibo("1819414227", AES.get().decrypt("wlM5kjycuUmjrXoIr/odsvkebRtFng5+EWNlXPh0tzwtd8zmrRwh08+fPVrP78JP"), "http://sns.whalecloud.com");
        PlatformConfig.setQQZone("101370185", AES.get().decrypt("nXVg37X2CJy3soy/noSGtX6GUbr0dCQzHfcbtO2pZqTF070k0ggVJzDYrZzf0r87"));
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


    //初始化融云
    public static void initLiveKit(Context context) {
        if (!isInit) {
            if (context.getApplicationInfo().packageName.equals(getCurProcessName(context))) {
                LiveKit.init(context, MyAPP.RECOLE_APP_KEY);
                isInit = true;
            }
        }
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
        logout(context, NewLoginActivity.class);
    }

    //登出
    public static void logout(Context context, Class cls) {
        MyAPP.mServiceManager.stop();
        setAccessToken(null);
        setIsLogout(true);
        setmUserInfo(null);
        AppManager.getAppManager().finishAllActivity();
        SPUtils.remove(AppConstant.SPKey.TOKEN);
        Intent intent = new Intent(context, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    //登出
    public static void logout(Context context, boolean showDialog) {
        MyAPP.mServiceManager.stop();
        setAccessToken(null);
        setIsLogout(true);
        setmUserInfo(null);

//        SimpleDialog simpleDialog = new SimpleDialog(getContext());
        
        AppManager.getAppManager().finishAllActivity();
        SPUtils.remove(AppConstant.SPKey.TOKEN);
        Intent intent = new Intent(context, NewLoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    //登录
    public static void loadUserInfo(final OnLoadDataListener<UserInfo> listener) {
        if (!isLogin()) {    //如果没有登录则
            String token = SPUtils.getString(AppConstant.SPKey.TOKEN);
            if (TextUtils.isEmpty(token)) {  //如果当期token为空则表示没有登录过,返回游客
                listener.loadSuccess(UserInfo.GUSET());
                LogUtils.logd(StringUtils.getText(R.string.not_login));
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
                    if (code==-1){
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

    //判断数据库是否有该用户,没有则添加进去
    public static UserInfo getDBUserInfo(UserInfo result) {
        final UserInfoDao userInfoDao = DbCore.getDaoSession().getUserInfoDao();

        try{
        //代码区
             userInfo = userInfoDao.load(1L);
        }catch(Exception e){
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

    //启动页面初始化
    public static void init() {
        HandlerUtil.runTask(new Runnable() {
            @Override
            public void run() {
                //获取推广大使信息
                OkHttpClientManager.getAsync(ApiKey.USER_HOME_RES, MapUtils.getDefMap(false), new NewRequestCallBack<ResBean>(ResBean.class) {
                    @Override
                    public void onSuccess(ResBean result) {
                        WpyxConfig.setResBean(result);
                    }
                });
                //创建文件夹
                FileUtil.makeDirs();
                //链接音乐播放服务
                MyAPP.mServiceManager.connectService();
                PracticeDataUtil.init();
                UpdateVersionUtil.updateVersion();
                //注册服务
                FileUtil.delete(FileUtil.getDownApkFile());
            }
        });
    }

    //获取当前登录的用户信息
    public static UserInfo getUserInfo() {
        return mUserInfo == null ? UserInfo.GUSET() : mUserInfo;
    }

    public static void setAccessToken(String accessToken) {
        MyAPP.accessToken = accessToken;
    }


    public static String getAccessToken() {
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

    //BeeCloud初始化
    public static void setSecretId(String secretId) {
        MyAPP.secretId = secretId;
        BeeCloud.setSandbox(false);
        Config.DEBUG = true;
        String appId = AES.get().decrypt("FcdZjVtfj7TMwi1TwLYfKFY0PG5xzDBAujwNOGGdYdQc2qcQPWp+XWp68+YdEIBz");
        BeeCloud.setAppIdAndSecret(appId, secretId);
        String initInfo = BCPay.initWechatPay(getAppContext(), wcAppId);
        if (initInfo != null) {
            LogUtils.logd("微信初始化失败：" + initInfo);
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


    public static void setIsVip(CheckVipLevelBean isVip) {
        MyAPP.vipLevelBean = isVip;
    }


    public static CheckVipLevelBean getVipLevelBean() {
        return vipLevelBean;
    }

    //是否是VIP
    public static boolean isVip() {
        return vipLevelBean != null && vipLevelBean.getData() != null && vipLevelBean.getData().isIsMember();
    }


    public static String getDeviceToken() {
        return deviceToken;
    }

    public static void setDeviceToken(String deviceToken) {
        MyAPP.deviceToken = deviceToken;
    }

}
