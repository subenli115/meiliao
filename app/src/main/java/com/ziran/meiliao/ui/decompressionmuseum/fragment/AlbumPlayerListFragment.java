package com.ziran.meiliao.ui.decompressionmuseum.fragment;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.widget.LoadMoreFooterView;
import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.entry.MusicEntry;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.decompressionmuseum.activity.AlbumPlayerActivity;
import com.ziran.meiliao.ui.decompressionmuseum.adapter.AlbumPlayerListAdapter;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.MyValueTempCache;

/**
 * 减压馆-和身体对话Fragment
 * Created by Administrator on 2017/1/7.
 */

public class AlbumPlayerListFragment extends CommonRefreshFragment {

    AlbumPlayerActivity albumPlayActivity;


    @Override
    public CommonRecycleViewAdapter getAdapter() {
        if (getActivity() instanceof AlbumPlayerActivity) {
            albumPlayActivity = (AlbumPlayerActivity) getActivity();
        }
        View viewById = rootView.findViewById(R.id.rootFrame);
        if (viewById != null) {
            viewById.setBackgroundColor(Color.WHITE);
        }
        needCheckNet = false;
        return new AlbumPlayerListAdapter(getContext(), new AlbumPlayerListAdapter.MultiItemType());
    }


    public void notifyDataSetChanged() {
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void loadData() {
        mData = MyValueTempCache.get().get(AppConstant.SPKey.PLAY_DATA);
        int selPos = MyValueTempCache.getSelectPosition(mData);
        if (selPos != 0) {
            mAdapter.setSelectPosition(selPos);
        }
        mAdapter.replaceAll(mData);
        setLoadState(LoadMoreFooterView.Status.THE_END);
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        //如果按下的是同一个位置
        MusicEntry audioList = (MusicEntry) mAdapter.get(position);
        if (audioList.isHead()) return;
        if ( !FileUtil.fileIsExists(audioList.getFilePath())){
            if (!CheckUtil.check(getContext(), getView()))
                return;
        }
        int selectPosition = MyValueTempCache.getSelectPosition(mData);
        if (position == selectPosition) {
            MyAPP.mServiceManager.playById(audioList.getMusicId());
            return;
        }
        //播放音频
        MyAPP.mServiceManager.playById(audioList.getMusicId());
        notifyDataSetChanged();
        MyValueTempCache.setCurrentPlayMusicEntry(audioList);
        if (albumPlayActivity != null) {
            albumPlayActivity.showList(false);
        }
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if (enter) {
            return AnimationUtils.loadAnimation(getActivity(), R.anim.ft_slide_in_bottom);
        } else {
            return AnimationUtils.loadAnimation(getActivity(), R.anim.ft_slide_out_bottom);
        }
    }
}
