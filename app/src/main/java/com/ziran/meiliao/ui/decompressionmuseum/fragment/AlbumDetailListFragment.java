package com.ziran.meiliao.ui.decompressionmuseum.fragment;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.entry.MusicEntry;
import com.ziran.meiliao.envet.MyOnScrollListener;
import com.ziran.meiliao.service.ServiceManager;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.AlbumBean;
import com.ziran.meiliao.ui.bean.StringDataBean;
import com.ziran.meiliao.ui.decompressionmuseum.activity.AlbumDetailActivity;
import com.ziran.meiliao.ui.decompressionmuseum.activity.AlbumPlayerActivity;
import com.ziran.meiliao.ui.decompressionmuseum.adapter.AlbumDetailListAdapter;
import com.ziran.meiliao.ui.decompressionmuseum.contract.AlbumDetailListContract;
import com.ziran.meiliao.ui.decompressionmuseum.presenter.AlbumDetailListPresenter;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.ImageAnimation;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.MusicPanelFloatManager;
import com.ziran.meiliao.utils.MyValueTempCache;
import com.ziran.meiliao.utils.ProgressUtil;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

/**
 * 减压馆-音频课程列表Fragment
 * Created by Administrator on 2017/1/7.
 */

public class AlbumDetailListFragment extends CommonRefreshFragment<AlbumDetailListPresenter, CommonModel> implements
        AlbumDetailListContract.View {

    private String albumId;
    private AlbumDetailActivity albumDetailActivity;
    private int fromType;
    private AlbumBean mAuthor;
    private List<MusicEntry> auditionMusics = new ArrayList<>();
    private boolean updated;
    private boolean mBuy;
    private List<MusicEntry> mResult;


    @Override
    protected void initView() {
        super.initView();
        mRxManager.on(AppConstant.RXTag.ALBUM_COUNT_DOWN_TIME, new Action1<Long>() {
            @Override
            public void call(Long message) {
                if (message==-1){
                    ImageAnimation.get().onDestroy(true);
                    if (mAdapter!=null){
                        mAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }





    @Override
    public CommonRecycleViewAdapter getAdapter() {
        if (TextUtils.isEmpty(albumId)) {
        albumId = getIntentExtra(getActivity().getIntent(), AppConstant.SPKey.ALBUM_ID); }
        fromType = getIntentExtra(getActivity().getIntent(), AppConstant.SPKey.ALBUM_FLAG, -1);
        albumDetailActivity = (AlbumDetailActivity) getActivity();
        iRecyclerView.setRefreshEnabled(false);
        iRecyclerView.setLoadMoreEnabled(false);
        iRecyclerView.addOnScrollListener(new MyOnScrollListener(AppConstant.RXTag.HOME_MUSIC_PLANE_SHOW_OR_HIDE));
        return new AlbumDetailListAdapter(getContext(), new AlbumDetailListAdapter.MultiItemType(),mAuthor);
    }

    @Override
    protected void loadData() {
        mPresenter.getAuthorData(ApiKey.GET_AUTHOR_DATA, MapUtils.getAlbumData(albumId, page));
    }

    @Override
    public void onResume() {
        super.onResume();
        MusicPanelFloatManager.getInstance().updatePlayState();
        if(mAdapter!=null){
            mAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 点击item事件
     */
    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        if (!CheckUtil.check(getContext(), view)) return;
        MusicEntry musicEntry = (MusicEntry) o;
        if (mBuy) {
            if (!musicEntry.isHead()) {
                if (fromType == 2) {
                    setResult(musicEntry);
                    return;
                }
                MyValueTempCache.get().put(AppConstant.SPKey.PLAY_DATA, mAdapter.getAll());
                MyValueTempCache.setCurrentPlayMusicEntry(musicEntry);
                startPlayer(musicEntry, changeData(),true);
            }

        } else if (musicEntry.isSt()) {
            if (fromType == 2) {
                //返回该音频的url
                setResult(musicEntry);
                return;
            }
            resShiTing();
            MyValueTempCache.get().put(AppConstant.SPKey.PLAY_DATA,auditionMusics);
            MyValueTempCache.setCurrentPlayMusicEntry(musicEntry);
            startPlayer(musicEntry, auditionMusics,false);
        } else {
            showShortToast(getString(R.string.cannot_goto_playact));
        }
    }

    private void resShiTing() {
        auditionMusics.clear();
        List<MusicEntry> data = getData();
        for (int i = 0; i < data.size(); i++) {
//            if (data.get(i).isSt()){
                auditionMusics.add(data.get(i));
//            }
        }
    }


    private void startPlayer(MusicEntry musicEntry, List<MusicEntry> musicEntries, boolean isbuy) {
        MusicPanelFloatManager.getInstance().res(false);
        if (MyAPP.mServiceManager.getCurMusicId() != musicEntry.getMusicId()  ) {
//        if (MyAPP.mServiceManager.getCurMusicId() != musicEntry.getMusicId() && MyAPP.mServiceManager.isPlaying()) {
            MyAPP.mServiceManager.stop();
        }

        MyAPP.mServiceManager.refreshMusicList(musicEntries);
        MyAPP.mServiceManager.setAlbumPicture(mAuthor.getRoundPic());
        MyAPP.mServiceManager.setAlbumName(mAuthor.getName());
        MyAPP.mServiceManager.setClickFrom(ServiceManager.CLICK_FROM_ALBUM);
//        HomePageMusicManager.get().setFrom(HomePageMusicManager.PlayFrom.NONE);

        MyAPP.mServiceManager.setAlbumId(albumId);
        MyAPP.mServiceManager.setIsbuy(isbuy);
        MyAPP.mServiceManager.setAlbum(mAuthor);
        AlbumPlayerActivity.startAction(getContext(), albumId);
    }

    private List<MusicEntry> changeData() {
        List<MusicEntry> musicEntries = getData();
        List<MusicEntry> musics = new ArrayList<>();
        for (int i = 0; i < musicEntries.size(); i++) {
            MusicEntry musicEntry = musicEntries.get(i);
            if (!musicEntry.isHead()) {
                musics.add(musicEntry);
            }
        }
        return musics;
    }


    public void collectAlbum() {
        mPresenter.postCollectAlbum(ApiKey.COLLECT_ALBUM, MapUtils.getCollect(mAuthor.isIsCollectAlbum(), "albumIds", albumId));
    }

    public void collectMusic(MusicEntry cuttMusicData) {
        mPresenter.postCollectMusic(ApiKey.COLLECT_MUSIC, MapUtils.getCollect(cuttMusicData.getIsCollectMusic(), "musicIds", String
                .valueOf(cuttMusicData.getMusicId())));
    }

    public List<MusicEntry>     getData() {
        if (mAdapter != null) return mAdapter.getAll();
        return null;
    }

    @Override
    public void onPause() {
        super.onPause();
        ImageAnimation.get().onDestroy();
    }

    private void setResult(MusicEntry musicEntry) {
        mRxManager.post(AppConstant.RXTag.EXERCISE_PLAY, HandlerUtil.obj(ProgressUtil.WHAT_SHOW_ALBUM_PLAY, musicEntry));
        finish();
    }


    @Override
    public void showCollectAlbum(StringDataBean result) {
        mAuthor.setIsCollectAlbum(!mAuthor.isCollectAlbum());

        ToastUitl.showShort(result.getResultMsg());
    }

    @Override
    public void showCollectMusic(StringDataBean result) {
        albumDetailActivity.setIsCollect();
        ToastUitl.showShort(result.getResultMsg());
    }

    @Override
    public void showAuthor(AlbumBean result) {
        mAuthor = result;
        mAuthor.setAlbumId(albumId);
        MyValueTempCache.get().put(AppConstant.DOWN_ALBUM, mAuthor);
        mRxManager.post(AppConstant.RXTag.UPDATE_TITLE, result);
    }

    @Override
    public void showMusicList(List<MusicEntry> musicEntries, boolean buy) {
        mAdapter.clear();
        updateData(musicEntries);
        mResult=musicEntries;
        if(!updated&&buy){
            mBuy=buy;
            albumDetailActivity.updateBuyStatus();
            updated=true;
        }
    }
    //接口回调

}