package com.ziran.meiliao.ui.NewDecompressionmuseum.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.entry.VideoSectionEntry;
import com.ziran.meiliao.ui.bean.FintessDetailBean;
import com.ziran.meiliao.utils.PrefUtils;
import com.ziran.meiliao.widget.FitnessDownloadFile;

import java.io.File;

import butterknife.Bind;
import butterknife.OnClick;


public class FitnessLoadActivity extends BaseActivity {

    FintessDetailBean.DataBean.JsCourseBean jsBean;
    @Bind(R.id.tv_people)
    TextView tvPeople ;
    @Bind(R.id.tv_content)
    TextView tvContent ;
    @Bind(R.id.iv_bg)
    ImageView ivBg ;
    @Bind(R.id.progressBar)
    ProgressBar progressBar ;

    FintessDetailBean.DataBean mData;
    private String loadUrl="";
    private FitnessDownloadFile downLoadFile;
    private boolean isStart=true;
    private String rootCourseId;


    @Override
    public int getLayoutId() {
        return R.layout.ac_fintness_load;
    }

    @Override
    public void initPresenter() {

    }
    @Override
    public void initView() {
        if(getIntent()!=null){
            Intent intent = getIntent();
            mData =(FintessDetailBean.DataBean) intent.getSerializableExtra("mData");
            rootCourseId  =intent.getStringExtra("courseId");
            if(mData!=null){
                String practiceCount = mData.getPracticeCount()+"";
                tvPeople.setText(practiceCount);
                tvContent.setText("即将开始"+mData.getJsCourse().getName()+"练习");
                Glide.with(mContext).load(mData.getJsCourse().getPracticePic()).into(ivBg);
                loadUrl = mData.getDetails().get(0).getUrl();
                VideoSectionEntry videoSectionEntry = new VideoSectionEntry();
                videoSectionEntry.setTitle(mData.getJsCourse().getName());
                videoSectionEntry.setId(Long.parseLong( mData.getDetails().get(0).getId()+""));
                videoSectionEntry.setRootCourseId(rootCourseId);
                videoSectionEntry.setUrl(loadUrl);
                videoSectionEntry.setDuration(mData.getJsCourse().getRecPic());
                videoSectionEntry.setTime(mData.getJsCourse().getDescription());
                videoSectionEntry.insert(videoSectionEntry);
                downLoadFile = new FitnessDownloadFile(this,loadUrl, videoSectionEntry.getFilePath(mContext), 3);
                if(fileIsExists(videoSectionEntry.getFilePath(mContext))){
                    mHandler.postDelayed(r,3000);
                    progressBar.setProgress(100);
                }else{
                    downLoadFile.download();
                }
                downLoadFile.setOnDownloadListener(new FitnessDownloadFile.DownloadListener() {
                    @Override
                    public void getProgress(int progress) {
                        progressBar.setProgress(progress);
                    }

                    @Override
                    public void onComplete() {
                        mHandler.postDelayed(r,3000);
                    }

                    @Override
                    public void onFailer() {
                        mHandler.postDelayed(r,3000);
                    }


                });


            }
        }
    }
    public boolean fileIsExists(String strFile)
    {
        try
        {
            File f=new File(strFile);
            if(!f.exists())
            {
                return false;
            }

        }
        catch (Exception e)
        {
            return false;
        }

        return true;
    }

    private void startVideo() {
        if(isStart){

            finish();
            PrefUtils.putLong("FitnessStartTime", System.currentTimeMillis(), mContext);
            FitnessVideoPlayerActivity.startAction(mContext, mData,mData.getJsCourse().getId());
            isStart=false;
        }
    }

    public static void startAction(Context context,FintessDetailBean.DataBean mData) {
        Intent intent = new Intent(context, FitnessLoadActivity.class);
        intent.putExtra("mData",mData);
        context.startActivity(intent);
    }
    @OnClick({R.id.tv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
             onBackPressed();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(r);
        if(downLoadFile!=null){
            downLoadFile.onDestroy();
        }
    }

    Handler mHandler = new Handler();
    Runnable r = new Runnable() {

                      @Override
                public void run() {
                          startVideo();
                          }
                 };
}
