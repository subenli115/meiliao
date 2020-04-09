package com.ziran.meiliao.ui.settings.fragment;

import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.ziran.meiliao.R;
import com.ziran.meiliao.db.DbCore;
import com.ziran.meiliao.entry.CourseEntry;
import com.ziran.meiliao.ui.settings.activity.DownloadVideoActivity;
import com.ziran.meiliao.ui.settings.adapter.DownloadCourseAdapter;

import java.util.List;

/**
 * 我的下载 课程Fragment
 * Created by Administrator on 2017/1/7.
 */

public class DownLoadCourseFragment extends DeleteRefreshFragment {

    @Override
    protected void loadData() {
        List<CourseEntry> courseEntries = DbCore.getDaoSession().getCourseEntryDao().loadAll();
        if (EmptyUtils.isEmpty(courseEntries)) {
            FileUtil.delete(FileUtil.getDownVideoFolder());
        }
        updateData(courseEntries);
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        super.onItemClick(parent, view, o, position);
        if (canNotStartAct) return;
        CourseEntry courseEntry = (CourseEntry) o;
        DownloadVideoActivity.startAction(getContext(), courseEntry.getTitle(), courseEntry.getCourseId());
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        setRecyclerEnabled(false, true);
        needCheckNet = false;
        loadedTip.setEmptyMsg(getString(R.string.emtry_download_course), R.mipmap.ic_empty_download);
        return new DownloadCourseAdapter(getContext(), R.layout.item_me_course);
    }
}
