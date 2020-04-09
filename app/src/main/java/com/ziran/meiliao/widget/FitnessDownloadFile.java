 package com.ziran.meiliao.widget;;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
 import java.net.URL;

 import android.content.Context;
 import android.os.Environment;
import android.os.Handler;
import android.os.Message;

 public class FitnessDownloadFile{

    private static final String DOWNLOAD_INIT = "1";
    private static final String DOWNLOAD_ING = "2";
    private static final String DOWNLOAD_PAUSE = "3";
    private static final int DEFAULT_THREAD_COUNT = 4;//默认下载线程数
    private String stateDownload = DOWNLOAD_INIT;//当前线程状态

    private Thread[] mThreads;
    private int CurrentProgress;//当前总共下载的大小
    private int finishThread = 0;//记录完成下载的线程数

    private Context context;
    private String loadUrl;//网络获取的url
    private String filePath;//下载到本地的path
    private int threadCount = DEFAULT_THREAD_COUNT;//下载线程数
    private static DownloadListener mDownloadListener;

    private static final int SUCCESS = 1;
    private static final int FAILURE = 0;

    public void setOnDownloadListener(DownloadListener downloadListener){
        this.mDownloadListener = downloadListener;
    }

   public interface DownloadListener{
        //返回当前下载进度的百分比
        void getProgress(int progress);
        void onComplete();
        void onFailer();
    }

    public FitnessDownloadFile(Context context, String loadUrl, String filePath){
        this(context, loadUrl, filePath, DEFAULT_THREAD_COUNT, null);
    }

    public FitnessDownloadFile(Context context, String loadUrl, String filePath, DownloadListener downloadListener){
        this(context, loadUrl, filePath, DEFAULT_THREAD_COUNT, downloadListener);
    }

    public FitnessDownloadFile(Context context, String loadUrl, String filePath, int threadCount){
        this(context, loadUrl, filePath, threadCount, null);
    }

    public FitnessDownloadFile(Context context, String loadUrl, String filePath, int threadCount, DownloadListener downloadListener){
        this.context = context;
        this.loadUrl = loadUrl;
        this.filePath = filePath;
        this.threadCount = threadCount;
        this.mDownloadListener = downloadListener;
    }

    private static Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            if(msg.what == SUCCESS){
                mDownloadListener.onComplete();
            }else if(msg.what == FAILURE){
                mDownloadListener.onFailer();
            }else{
                mDownloadListener.getProgress(msg.what);
            }
        }
    };
    public void onPause(){
        if(mThreads != null){
            stateDownload = DOWNLOAD_PAUSE;
        }
    }

    //继续下载
    public void onStart(){
        if(mThreads != null){
            synchronized(DOWNLOAD_PAUSE){
                stateDownload = DOWNLOAD_ING;
                DOWNLOAD_PAUSE.notifyAll();
            }
        }
    }

    public void onDestroy(){
        if(mThreads != null){
            mThreads = null;
        }
    }

    //取消下载
    public void cancel(){
        if(mThreads != null){
            //若线程处于等待状态，则while循环处于阻塞状态，无法跳出循环，必须先唤醒线程，才能执行取消任务
            if(stateDownload.equals(DOWNLOAD_PAUSE))
                onStart();
            for(Thread td : mThreads){
                ((DownloadThead) td).cancel();
            }
        }
    }

    public void download(){
        //在线程中运行，防止anr
        Thread t = new Thread(){
            @Override
            public void run(){
                //初始化数据
                if(mThreads == null)
                    mThreads = new Thread[threadCount];
                try{
                    URL url= new URL(loadUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setReadTimeout(5000);
                    conn.setConnectTimeout(5000);
                    //请求成功,根据文件大小开始分多线程下载
                    if(conn.getResponseCode() == 200){
                        int length = conn.getContentLength();
                        File file = new File(filePath);
                        RandomAccessFile raf = new RandomAccessFile(file, "rwd");
                        raf.setLength(length);
                        raf.close();
                        //计算各个线程下载的数据段
                        int blockLength = length / threadCount;
                        for(int i = 0; i < threadCount; i++){
                            int startIndex = i * blockLength;
                            int endIndex = (i + 1) * blockLength - 1;
                            if(i == threadCount - 1){
                                endIndex = length - 1;
                            }
                            mThreads[i] = new DownloadThead(startIndex, endIndex, i);
                            mThreads[i].start();
                        }
                    }else{
                        handler.sendEmptyMessage(SUCCESS);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    handler.sendEmptyMessage(SUCCESS);
                }
            }
        };
        t.start();
    }


    class DownloadThead extends Thread{

        private boolean isGon = true;//是否继续下载
        private int startIndex;//开始下载点
        private int endIndex;//结束下载点
        private int threadId;//当前线程的ID

        public DownloadThead(int startIndex, int endIndex, int threadId){
            super();
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.threadId = threadId;
        }

        @Override
        public void run(){
            URL url = null;
                File fileProgress = new File(Environment.getExternalStorageDirectory(), threadId+".txt");
                //判断存储下载进度的临时文件是否存在
                if(fileProgress.exists()){
                    FileInputStream fis = null;
                    try {
                        fis = new FileInputStream(fileProgress);
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                    //从下载进度的临时文件中读取上一次下载的总进度，然后和原来文本的开始位置相加，得到新的下载位置
                    int lastProgress = 0;
                    try {
                        lastProgress = Integer.parseInt(br.readLine());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    //把上次下载的进度显示到进度条，更新进度条
                    startIndex += lastProgress;
                    CurrentProgress += lastProgress;
                    //主线程刷新文本进度
                    handler.sendEmptyMessage(CurrentProgress);
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            try {
                url = new URL(loadUrl);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpURLConnection conn = null;
            try {
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
                conn.setConnectTimeout(5000);
                //设置请求数据的区间
                conn.setRequestProperty("Range", "bytes="+startIndex + "-" + endIndex);
                //若请求头加上Range这个参数，则返回状态码为206，而不是200
                if(conn.getResponseCode() == 206){
                    InputStream is = conn.getInputStream();
                    byte[] b = new byte[1024];
                    int len = 0;
                    int total = 0;
                    File file = new File(filePath);
                    //拿到临时文件的引用
                    RandomAccessFile raf = new RandomAccessFile(file, "rwd");
                    //更新文件的写入位置，startIndex
                    raf.seek(startIndex);
                    while((len = is.read(b)) != -1){
                        //是否继续下载
                        if(!isGon)
                            break;
                        raf.write(b, 0 ,len);
                        if(mDownloadListener != null){
                            total += len;
                            CurrentProgress +=len;
                            //主线程刷新文本进度
                            handler.sendEmptyMessage(CurrentProgress);
                        }
                        //生成一个专门记录下载进度的临时文件
                        RandomAccessFile fileProgressRaf = new RandomAccessFile(fileProgress, "rwd");
                        //每一次读取流里面的数据以后，把当前线程下载的总进度写入临时文件中
                        fileProgressRaf.write((total + "").getBytes());
                        fileProgressRaf.close();
                        synchronized(DOWNLOAD_PAUSE){
                            if(stateDownload.equals(DOWNLOAD_PAUSE)){
                                DOWNLOAD_PAUSE.wait();
                            }
                        }

                    }
                    handler.sendEmptyMessage(SUCCESS);
                    raf.close();
                    //条线程下载完成以后，清理临时文件
                    finishThread ++;
                    //线程安全
                    synchronized(loadUrl){
                        if(finishThread == threadCount){
                            for(int i = 0; i < threadCount; i++){
                                File fileFinish = new File(Environment.getExternalStorageDirectory(), threadId + ".txt");
                                fileFinish.delete();
                            }
                            finishThread = 0;
                        }
                    }
                }
                handler.sendEmptyMessage(FAILURE);
            }catch(Exception e){
                e.printStackTrace();
                handler.sendEmptyMessage(FAILURE);
            }
        }

        public void cancel(){
            isGon = false;
        }

    }
}