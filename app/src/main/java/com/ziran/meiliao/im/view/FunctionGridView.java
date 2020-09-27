package com.ziran.meiliao.im.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.ziran.meiliao.R;
import com.ziran.meiliao.im.adapter.FunctionAdapter;
import com.ziran.meiliao.im.model.AppBean;

import java.util.ArrayList;

public class FunctionGridView extends RelativeLayout {

    protected View view;
    private int mLevel;
    private ArrayList<AppBean> mAppBeanList;
    private GridView gv_apps;
    private FunctionAdapter adapter;

    public FunctionGridView(Context context, int level) {
        this(context, null,level);
    }

    public FunctionGridView(Context context, AttributeSet attrs,int level) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.view_apps_new, this);
        init();
    }

    protected void init() {
         gv_apps = (GridView) view.findViewById(R.id.gv_apps);
         mAppBeanList = new ArrayList<>();
        mAppBeanList.add(new AppBean(R.mipmap.icon_hot_clock, "私密",false));
        mAppBeanList.add(new AppBean(R.mipmap.icon_loaction_clock, "位置",false));
        mAppBeanList.add(new AppBean(R.mipmap.icon_photo_clock, "图片",false));
        mAppBeanList.add(new AppBean(R.mipmap.icon_file_clock, "文件",false));
        mAppBeanList.add(new AppBean(R.mipmap.icon_call_clock, "语音",false));
        mAppBeanList.add(new AppBean(R.mipmap.icon_video_clock, "视频",false));
         adapter = new FunctionAdapter(getContext(), mAppBeanList);
        gv_apps.setAdapter(adapter);
    }

   public void  setLevel(int level){
        if(level>=2){
            mAppBeanList.set(1,new AppBean(R.mipmap.icon_loaction, "位置",true));
        }else if(level>=3){
            mAppBeanList.set(0,new AppBean(R.mipmap.icon_hot, "私密",true));
        }else if(level>=4){
            mAppBeanList.set(2,new AppBean(R.mipmap.icon_photo, "图片",true));
        }else if(level>=5) {
            mAppBeanList.set(3, new AppBean(R.mipmap.icon_file, "文件", true));
        }else if(level>=6) {
            mAppBeanList.set(4, new AppBean(R.mipmap.icon_call, "语音", true));
        }else if(level>=9){
            mAppBeanList.set(5, new AppBean(R.mipmap.icon_video, "视频", true));
        }
        adapter.update(getContext(),mAppBeanList);
    }
}
