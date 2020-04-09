package com.ziran.meiliao.widget.pupop;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.WpyxConfig;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.service.UpdateVersionDownloadService;

import static android.content.Context.DOWNLOAD_SERVICE;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/7 11:33
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/7$
 * @updateDes ${TODO}
 */


public class UpdateVersionPopupWindow extends BasePopupWindow {
    private TextView tvContent;
    private static TextView update;
    private final QueryRunnable mQueryProgressRunnable = new QueryRunnable();
    private static DownloadProgressDialog progressDialog;
    private DownloadManager downloadManager;
    private long downloadId;
    private String apkFilePath;
    private DownloadFinish downloadFinish;

    public UpdateVersionPopupWindow(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.pop_update_version;
    }

    @Override
    protected void initViews(View contentView) {
        apkFilePath =
                FileUtil.getDownApkFile();

        if (!FileUtil.fileIsExists(apkFilePath)) {
            downloadFinish = new DownloadFinish();
            //动态注册广播接收器
            mContext.registerReceiver(downloadFinish, new IntentFilter(
                    DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        }




        downloadManager = (DownloadManager) mContext.getSystemService(DOWNLOAD_SERVICE);
         update = ViewUtil.getView(contentView, R.id.tv_update_version_update);
        ViewUtil.getView(contentView, R.id.iv_update_version_close).setOnClickListener(this);
        ViewUtil.getView(contentView, R.id.tv_update_version_update).setOnClickListener(this);
        tvContent = ViewUtil.getView(contentView, R.id.tv_update_version_content);
    }

    /**
     * 接受下载完成的广播
     */
    class DownloadFinish extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

//            //此ID为下载完成的ID
//            long completeDownloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
//            //如果完成的ID 于 我们下载的ID 一致则表示下载完成
//            if (downloadId == completeDownloadId) {
//              LogUtils.logd( "DownloadFinish downloadId == completeDownloadId");
//                //安装apk
//                mupdate.setText("开始安装");
//            }
            String action = intent.getAction();
            if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
                long downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0);
                // 查询
                DownloadManager.Query query = new DownloadManager.Query();
                query.setFilterById(downloadId);
                Cursor c = downloadManager.query(query);
                if (c != null && c.moveToFirst()) {
                    int columnIndex = c.getColumnIndex(DownloadManager.COLUMN_STATUS);
                    if (DownloadManager.STATUS_SUCCESSFUL == c.getInt(columnIndex)) {
                    }
                }
                if (c != null && !c.isClosed()) {
                    c.close();
                }
            }
        }
    }


    @Override
    public void dismiss() {
        super.dismiss();
        WpyxConfig.setLastVersion("");
    }
    public static void dismissUpdate() {
        progressDialog.dismiss();
        update.setText("开始安装");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_update_version_close:
                dismiss();
                break;
            case R.id.tv_update_version_update:
                if(update.getText().toString().equals("开始安装")){
                    UpdateVersionDownloadService updateVersionDownloadService = new UpdateVersionDownloadService();
                    updateVersionDownloadService.startInstall();

                }else {
                    UpdateVersionDownloadService.downloadApk(mContentView.getContext(), mDownloadUrl, update, downloadManager, progressDialog);
                    DownloadManager.Request request = new DownloadManager.Request(
                            Uri.parse(mDownloadUrl));
                    downloadId = downloadManager.enqueue(request);
                    startQuery();
                    ToastUitl.showShort("更新");
                }
                break;
        }
        RxManagerUtil.post(AppConstant.RXTag.CONFERENCE_GET_CONFERENCE,true);
    }

    private String mDownloadUrl;

    public void setContent(String content) {
        ViewUtil.setText(tvContent, content);
    }

    public void setDownloadUrl(String downUrl) {
        this.mDownloadUrl = downUrl;
    }

    //进度对话框
    private void displayProgressDialog() {
        if (progressDialog == null) {
            // 创建ProgressDialog对象
            progressDialog = new DownloadProgressDialog(mContext);
            // 设置进度条风格，风格为长形
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            // 设置ProgressDialog 标题
            progressDialog.setTitle("下载提示");
            // 设置ProgressDialog 提示信息
            progressDialog.setMessage("当前下载进度:");
            // 设置ProgressDialog 的进度条是否不明确
            progressDialog.setIndeterminate(false);
            // 设置ProgressDialog 是否可以按退回按键取消
            progressDialog.setCancelable(false);
            progressDialog.setProgressDrawable(mContext.getResources().getDrawable(R.drawable.progressbar));
            progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    removeDownload();
                    dialog.dismiss();
                    stopQuery();
                    update.setText("更新");
                    update.setEnabled(true);
                }
            }     );
        }


        if (!progressDialog.isShowing()) {
            // 让ProgressDialog显示
            progressDialog.show();
        }
    }
    //下载停止同时删除下载文件
    private void removeDownload() {
        if(downloadManager!=null){
            downloadManager.remove(downloadId);
        }
    }
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1001) {
                if (progressDialog != null) {
                    progressDialog.setProgress(msg.arg1);
                    progressDialog.setMax(msg.arg2);
                }
                if(progressDialog.getMax()==progressDialog.getProgress()){
                        progressDialog.dismiss();
                    update.setText("开始安装");
                }
            }
        }
    };


    //更新下载进度
    private void startQuery() {
        if (downloadId != 0) {
            displayProgressDialog();
            mHandler.post(mQueryProgressRunnable);
        }
    }
    //查询下载进度
    private class QueryRunnable implements Runnable {
        @Override
        public void run() {
            queryState();
            mHandler.postDelayed(mQueryProgressRunnable,100);
        }
    }



    //查询下载进度
    private void queryState() {
        // 通过ID向下载管理查询下载情况，返回一个cursor
        Cursor c = downloadManager.query(new DownloadManager.Query().setFilterById(downloadId));
        if (c == null) {
            Toast.makeText(mContext, "下载失败",Toast.LENGTH_SHORT).show();
        } else { // 以下是从游标中进行信息提取
            if (!c.moveToFirst()) {
                Toast.makeText(mContext,"下载失败",Toast.LENGTH_SHORT).show();
                if(!c.isClosed()) {
                    c.close();
                }
                return;
            }
            int mDownload_so_far = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
            int mDownload_all = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
            Message msg=Message.obtain();
            if(mDownload_all>0) {
                msg.what = 1001;
                msg.arg1=mDownload_so_far;
                msg.arg2=mDownload_all;
                mHandler.sendMessage(msg);
            }
            if(!c.isClosed()){
                c.close();
            }
        }
    }



    //停止查询下载进度
    private void stopQuery() {
        mHandler.removeCallbacks(mQueryProgressRunnable);
    }
}