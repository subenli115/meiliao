package com.ziran.meiliao.service;

import android.app.DownloadManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.v4.content.FileProvider;
import android.widget.TextView;

import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.commonutils.SPUtils;
import com.ziran.meiliao.common.commonwidget.FilterTextView;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.ziran.meiliao.app.WpyxConfig;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.widget.pupop.DownloadProgressDialog;
import com.ziran.meiliao.widget.pupop.UpdateVersionPopupWindow;

import java.io.File;

/**
 *  版本更新
 * 功能描述: 运用DownloadManager实现下载 一样通知栏会显示
 */
public class UpdateVersionDownloadService extends Service {
    public static final String DOWNLOAD_FOLDER_NAME = "wpyx/apk";
    private static DownloadProgressDialog mprogressDialog;
    //    public static final String DOWNLOAD_FILE_NAME = "wpyx.apk";
private Handler handler = new Handler(Looper.getMainLooper());
    public static final String APK_URL = "APK_URL";
    public static final String APK_KEY = "APK_KEY";
    private static TextView mupdate;
    private static Context mContext;
    private static DownloadManager downloadManager;
    private static String DOWN_APK_URL = "http://dgli.net/version/android/2.3.0.apk";
    static String apkFilePath;
    private static  long downloadId;
    private DownloadFinish downloadFinish;
    private FilterTextView update;
    private DownloadProgressDialog progressDialog;

    public static void downloadApk(Context context, String url, TextView update, DownloadManager mdownloadManager, DownloadProgressDialog progressDialog) {
        Intent intent = new Intent(context, UpdateVersionDownloadService.class);
        intent.putExtra(APK_URL, url);
        mContext=context;
        mprogressDialog=progressDialog;
        downloadManager=mdownloadManager;
        context.startService(intent);
        mupdate=update;
        mupdate.setText("下载中");

    }

    public static void stopService(Context context) {
        Intent intent = new Intent(context, UpdateVersionDownloadService.class);
        context.stopService(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
      LogUtils.logd( "onCreate() 启动服务");
//        SPUtils.get(UpdateVersionDownloadService.this, SPUtils.KEY, (long) 0);
        //下载之前先移除上一个 不然会导致 多次下载不成功问题
        apkFilePath =
                FileUtil.getDownApkFile();

        if (!FileUtil.fileIsExists(apkFilePath)) {
            downloadFinish = new DownloadFinish();
            //动态注册广播接收器
            registerReceiver(downloadFinish, new IntentFilter(
                    DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (FileUtil.fileIsExists(apkFilePath)) {
//            install(this, apkFilePath);
        } else {
            long id = SPUtils.getLong( APK_KEY, 0L);
            if (id != 0) {
                downloadManager.remove(id);
            }
            String stringExtra = intent.getStringExtra(APK_URL);
            if (EmptyUtils.isNotEmpty(stringExtra)) {
                      DOWN_APK_URL = stringExtra;
            }
            initData();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void initData() {
      LogUtils.logd( "initData() 执行了~");

        //判断文件是否存在 不存在则创建
        File folder = new File(apkFilePath);

//        if (!folder.exists() || !folder.isDirectory()) {
//            folder.mkdirs();
//        }

        //设置下载的URL
        DownloadManager.Request request = new DownloadManager.Request(
                Uri.parse(DOWN_APK_URL));

        request.setDestinationInExternalPublicDir(DOWNLOAD_FOLDER_NAME,
                folder.getName());
        //设置样式 貌似必须用 getString() 如果不用 下载完毕后会显示 下载的路径
        //request.setName(getString(R.string.download_notification_title));
        request.setTitle("5P医学APP更新");
        request.setDescription("下载完成点击安装");

        //判断开关状态
        Boolean isOpen = SPUtils.getBoolean( AppConstant.SPKey.WIFI_DOWNLOAD_SWITCH);
        //自动下载开关是否打开 如果打开
        if (isOpen) {
          LogUtils.logd( "下载完才显示");
            /**
             * 在下载过程中通知栏会一直显示该下载的Notification，在下载完成后该Notification会继续显示，直到用户点击该
             * Notification或者消除该Notification。
             */
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION);
        } else {
          LogUtils.logd( "正在下载时显示");
            /**
             * 在下载过程中通知栏会一直显示该下载的Notification，在下载完成后该Notification会继续显示，
             * 直到用户点击该Notification或者消除该Notification。
             */
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }

        //是否显示下载用户接口
        request.setVisibleInDownloadsUi(false);
        request.setMimeType("application/vnd.android.package-archive");//设置此Type不然点击通知栏无法安装

        downloadId = downloadManager.enqueue(request);
        SPUtils.setLong( APK_KEY, downloadId);

    }

    /**
     * 接受下载完成的广播
     */
    class DownloadFinish extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            //此ID为下载完成的ID
            long completeDownloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            //如果完成的ID 于 我们下载的ID 一致则表示下载完成
            if (downloadId == completeDownloadId) {
                //安装apk
                UpdateVersionPopupWindow.dismissUpdate();
            }
        }
    }
    public void startInstall(){

        install(mContext, apkFilePath);
    }
    /**
     * 安装APK
     *
     * @param context
     * @param filePath
     */
    private void install(Context context, String filePath) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        File file = new File(filePath);
        if (file.length() > 0 && file.exists() && file.isFile()) {


            // 由于没有在Activity环境下启动Activity,设置下面的标签
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                //判读版本是否在7.0以上
                // 参数1 上下文, 参数2 Provider主机地址 和清单配置文件中保持一致
                // 参数2 = android:authorities="应用包名.fileprovider"属性值
                // 参数3 = 上一步中共享的apk文件
                Uri apkUri = FileProvider.getUriForFile(context, context.getPackageName()+".fileprovider", file);
                //添加这一句表示对目标应用临时授权该Uri所代表的文件
                i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                i.setDataAndType(apkUri, "application/vnd.android.package-archive");
            } else {
                i.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            }
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
            SPUtils.setString(AppConstant.SPKey.SYSTEM_VERSION,WpyxConfig.getLastVersion());
            WpyxConfig.setLastVersion("");
//            FileUtil.delete(filePath);
        }
        //停止服务
        stopSelf();
    }


    //打印看看有没有停止服务 带完善的地方 下载完成以后通知栏应该移除掉啊 还没弄不知道行不行 这里只能用户手动移除
    //而且也没有做用户点击前台服务就启动安装 以前做过貌似报错 记不清了
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (downloadFinish != null) {
            unregisterReceiver(downloadFinish);
        }
    }

}
