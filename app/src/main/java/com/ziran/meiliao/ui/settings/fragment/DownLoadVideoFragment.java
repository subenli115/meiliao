package com.ziran.meiliao.ui.settings.fragment;

import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.R;
import com.ziran.meiliao.entry.VideoSectionEntry;
import com.ziran.meiliao.ui.NewDecompressionmuseum.activity.FitnessExeActivity;
import com.ziran.meiliao.ui.settings.adapter.DownloadVideoAdapter;

import java.util.List;

/**
 * 我的下载 视频Fragment
 * Created by Administrator on 2017/1/7.
 */

public class DownLoadVideoFragment extends DeleteRefreshFragment {

    String rootCourseId="";

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void loadData() {
        List<VideoSectionEntry> courseEntries = VideoSectionEntry.loadWhereCourseId(rootCourseId);
        for(int k=0;k<5;k++){
            for(int i=0;i<courseEntries.size();i++){
                if(courseEntries.size()>k){
                    if(courseEntries.get(k).getTitle().equals(courseEntries.get(i).getTitle())){
                        courseEntries.remove(i);
                    }
                }
            }
        }
        updateData(courseEntries);
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        super.onItemClick(parent, view, o, position);
        if (canNotStartAct) return;
        VideoSectionEntry courseEntry = (VideoSectionEntry) o;
        FitnessExeActivity.startAction(getContext(),Integer.parseInt(courseEntry.getRootCourseId()));
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        setRecyclerEnabled(false, true);
        needCheckNet = false;
        loadedTip.setEmptyMsg(getString(R.string.emtry_download_video), R.mipmap.ic_empty_download);
        return new DownloadVideoAdapter(getContext(), R.layout.item_main_sjk_act_long_new_second);
    }
}
