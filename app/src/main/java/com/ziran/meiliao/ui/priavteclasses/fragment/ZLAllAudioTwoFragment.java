package com.ziran.meiliao.ui.priavteclasses.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.app.WpyxConfig;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.IRecyclerView;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.common.security.AES;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.entry.MusicEntry;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.MediaAndTextBean;
import com.ziran.meiliao.ui.bean.MediaDetailBean;
import com.ziran.meiliao.ui.bean.NewMediaAndTextBean;
import com.ziran.meiliao.ui.bean.RecordListBean;
import com.ziran.meiliao.ui.bean.StudyFinishBean;
import com.ziran.meiliao.ui.bean.SubscribeCommentListBean;
import com.ziran.meiliao.ui.bean.SubscribeCommentResultBean;
import com.ziran.meiliao.ui.priavteclasses.activity.SubscribeVideoActivity;
import com.ziran.meiliao.ui.priavteclasses.activity.ZLAudioImageTextActivity;
import com.ziran.meiliao.ui.priavteclasses.adapter.ZLAudioTwoAdapter;
import com.ziran.meiliao.ui.priavteclasses.contract.ZLTextImageContract;
import com.ziran.meiliao.ui.priavteclasses.presenter.ZLTextImagePresenter;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.MusicPanelFloatManager;
import com.ziran.meiliao.utils.MyValueTempCache;
import com.ziran.meiliao.utils.WpyxDownloadUtil;
import com.ziran.meiliao.widget.BottomMoreView;
import com.ziran.meiliao.widget.PlayPauseViewOne;
import com.ziran.meiliao.widget.pupop.SharePopupWindow;
import com.ziran.meiliao.widget.pupop.StudyFinishPopupWindow;
import com.zhy.autolayout.AutoRelativeLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.functions.Action1;

/**
 * 听音频
 */

public class ZLAllAudioTwoFragment extends CommonRefreshFragment<ZLTextImagePresenter, CommonModel> implements View.OnClickListener,BottomMoreView.OnMoreCallBack,ZLTextImageContract.View , WpyxDownloadUtil.onDownloadListener{


    @Bind(R.id.bottomMoreView)
    BottomMoreView bottomMoreView;
    @Bind(R.id.recyclerView)
    IRecyclerView recyclerView;
    private List<MediaAndTextBean.DataBean> data;
    private String url;
    private NewMediaAndTextBean mMediaAndTextBean;
    private int mPosition;
    private SharePopupWindow mSharePopupWindow;
    private MusicEntry musicEntry;
    Map<String, String> mStringMap;
    private NewMediaAndTextBean.DataBean.DirBean.PrevListBean dataBean;
    private long studySubscriptionId;
    private int studyTarget;
    private NewMediaAndTextBean.DataBean pData;
    private ZLAudioTwoAdapter zlAudioTwoAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_common_recyclerview;
    }


    @Override
    protected void initView() {
        super.initView();
        mStringMap = MapUtils.getDefMap(true);
        bottomMoreView.setOnMoreListener(this);
        mRxManager.on("NewMediaAndTextBean", new Action1<NewMediaAndTextBean>() {
            @Override
            public void call(NewMediaAndTextBean mediaAndTextBean) {
                pData = mediaAndTextBean.getData();


                updateData(mediaAndTextBean.getData().getDir());
                 mMediaAndTextBean = mediaAndTextBean;
//                zlAudioTwoAdapter.notifyDataSetChanged();
            }
        });
        mRxManager.on("notify", new Action1<Boolean>() {
            @Override
            public void call(Boolean isOrder) {
                LinearLayoutManager layout = new LinearLayoutManager(getContext());
                if(zlAudioTwoAdapter.getItemCount()==1){
                    zlAudioTwoAdapter.reverse(isOrder);
                }else {
                    layout.setStackFromEnd(isOrder);//列表再底部开始展示，反转后由上面开始展示
                    layout.setReverseLayout(isOrder);//列表翻转
                    recyclerView.setLayoutManager(layout);
                    recyclerView.setAdapter(zlAudioTwoAdapter);
                    zlAudioTwoAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        setRecyclerEnabled(false);
         zlAudioTwoAdapter = new ZLAudioTwoAdapter(getContext());
        zlAudioTwoAdapter.setItemClickListener(new ZLAudioTwoAdapter.ItemClickListener() {
            @Override
            public void itemClick1(int mPosition,final View view, NewMediaAndTextBean.DataBean.DirBean t,final int position) {
                    dataBean = t.getPrevList().get(mPosition);
                RecordListBean.DataBean recordBean = new RecordListBean.DataBean();
                recordBean.setTitle(dataBean.getTitle());
                recordBean.setPic(dataBean.getRoundPic());
                musicEntry = new MusicEntry();
                musicEntry.setName(dataBean.getTitle());
                musicEntry.setUrl(dataBean.getUrl());
                musicEntry.setShareTitle(pData.getShareTitle());
                musicEntry.setShareDescript(pData.getShareDescript());
                musicEntry.setShareUrl(pData.getShareUrl());
                musicEntry.setDuration(dataBean.getDuration());
                musicEntry.setSharePic(dataBean.getRoundPic());
                musicEntry.setMusicId(dataBean.getTargetId());
                WpyxConfig.setMusicEntry(musicEntry);
                MusicPanelFloatManager.getInstance().setMusicPrepare(musicEntry);
                switch (view.getId()) {
                    case R.id.iv_item_jump_detail:
                        Bundle bundle = setBundle(recordBean);
                        startActivity(ZLAudioImageTextActivity.class, bundle);
                        break;

                    case R.id.iv_item_more:
                        if (!bottomMoreView.isShowOrHide()) {
                            bottomMoreView.setShowMore(true);
                        }
                        if(dataBean.getType()==1){
                            isDown(true,true);
                        }else {
                            isDown(false,true);
                        }
                        if(WpyxConfig.getDownUrl()!=null&&!WpyxConfig.isDowning&&WpyxConfig.getDownUrl().equals(AES.get().decrypt(dataBean.getUrl()))){
                            bottomMoreView.setDowning();
                        }
                        bottomMoreView.setCollectText( dataBean.isIsCollect());
                        break;
                    case R.id.all_left:
                        if(dataBean.getType()==1){
                            SubscribeVideoActivity.startAction(getContext(), String.valueOf(pData.getSubscriptionId()), String.valueOf(dataBean.getTargetId()),true);
                        }else {
                            studySubscriptionId = pData.getSubscriptionId();
                            studyTarget = dataBean.getTargetId();
                            WpyxConfig.setSubscriptionId(pData.getSubscriptionId()+"");
                            url = AES.get().decrypt(dataBean.getUrl()).trim();
                            MyValueTempCache.setCurrentPlayMusicEntry(musicEntry);
                            MyValueTempCache.setTagId(dataBean.getTargetId());
                            MusicPanelFloatManager.getInstance().setData(dataBean.getTitle(),dataBean.getDuration(),dataBean.getRoundPic(),url,dataBean.getTargetId()+"",true);
                            MyAPP.mServiceManager.playUrl(url);
                            Bundle bundle1 = setBundle(recordBean);
                            WpyxConfig.setBundle(bundle1);
                            AutoRelativeLayout view2 = (AutoRelativeLayout) view;
                            PlayPauseViewOne view1= (PlayPauseViewOne)view2.findViewById(R.id.playPauseView);
                            if(view1.getStatus()==R.mipmap.ic_sjk_live_video_stop){
                                view1.setImageResource(R.mipmap.ic_sjk_live_video_play);
                            }else {
                                view1.setImageResource(R.mipmap.ic_sjk_live_video_stop);
                            }
                           if(view1.getStatus()==R.mipmap.ic_sjk_live_video_stop){
                                 view1.setImageResource(R.mipmap.ic_sjk_live_video_play);
                             }

                        }

                        break;
                }
            }
        });
        return zlAudioTwoAdapter;
    }

    @NonNull
    private Bundle setBundle(RecordListBean.DataBean recordBean) {
        Bundle bundle = new Bundle();
        bundle.putString("subscriptionId", String.valueOf(pData.getSubscriptionId()));
        bundle.putString("targetId", String.valueOf(dataBean.getTargetId()));
        bundle.putString("typeId", String.valueOf(dataBean.getType()));
        bundle.putString("courseHtml", dataBean.getCourseHtml());
        bundle.putString("duration", dataBean.getDuration());
        bundle.putParcelable("RecordListBean", recordBean);
        return bundle;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }

    @Override
    public void onResume() {
        MusicPanelFloatManager.getInstance().setIsShowing(true);
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    protected void loadData() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDownload() {
        if (isBuy()) {
            if (EmptyUtils.isNotEmpty(dataBean)) {
                if(dataBean.getType()==1){
                    WpyxDownloadUtil.get().downMusic(getContext(),this, pData.getSubscriptionId()+"", musicEntry,true,true);
                }else{
                    WpyxDownloadUtil.get().downMusic(getContext(),this, pData.getSubscriptionId()+"", musicEntry,false,true);
                }
            }
        } else {
            ToastUitl.showShort(getString(R.string.notbuy_album));
        }
    }
    //判断是否购买该單曲
    public boolean isBuy() {

        boolean flag = mMediaAndTextBean != null && pData.isIsBuy();
        return flag;
    }
    @Override
    public void onCollect() {
        collectMusic();
    }


    public void collectMusic() {
        mStringMap.put("subscriptionId", pData.getSubscriptionId()+"");
        mStringMap.put("targetId", dataBean.getTargetId()+"");
        mStringMap.put("collect", dataBean.isIsCollect() ? "0" : "1");
        mPresenter.postCollect(ApiKey.SUBSCRIPTION_COLLECT_SUBSCRIPTION, mStringMap);
    }
    @Override
    public void onShare() {
        SharePopupWindow.showPopup(getActivity(), mSharePopupWindow, musicEntry);
    }

    @Override
    public void onCancel() {

    }



    @Override
    public void showMediaDetail(MediaDetailBean.DataBean result) {

    }

    @Override
    public void showBuySpecColumnResult(Result result) {

    }

    @Override
    public void showCommentList(List<SubscribeCommentListBean.DataBean> bean) {

    }

    @Override
    public void showCollectResult(Result bean) {
        dataBean.setIsCollect(!dataBean.isIsCollect());
        ToastUitl.showShort(bean.getResultMsg());
    }

    @Override
    public void showLikeResult(Result bean) {

    }

    @Override
    public void commentResult(SubscribeCommentResultBean.DataBean bean) {

    }

    @Override
    public void showCertificate(StudyFinishBean.DataBean bean) {
        StudyFinishPopupWindow finishPopupWindow = new StudyFinishPopupWindow(getContext());
        finishPopupWindow.setData(bean);
    }

    @Override
    public void showUpdateStudyFinish(Result bean) {

    }

    @Override
    public void onFinish(String url, File file) {
                bottomMoreView.setDownText(true);
    }

    @Override
    public void onDownStart(String url) {
                bottomMoreView.setDowning();
    }



    public void isDown( Boolean isVideo, Boolean isZl){
        File file;
        if(isZl){
            file = new File(musicEntry.getFilePathZl());
        }else {
            file = new File(musicEntry.getFilePath());
        }
        if(isVideo){
            file = new File(musicEntry.getFilePathVideo());
        }
        if (file.exists()) {
            bottomMoreView.setDownText(true);
            return;
        }else {
            bottomMoreView.setDownText(false);
            return;
        }
    }


    public List<MusicEntry> getMusicEntry(List<NewMediaAndTextBean.DataBean.DirBean.PrevListBean> prevList) {
        List<MusicEntry> musicList =new ArrayList<>();
        for(int  i=0;i<prevList.size();i++){
            NewMediaAndTextBean.DataBean.DirBean.PrevListBean prevListBean = prevList.get(i);
            musicEntry = new MusicEntry();
            musicEntry.setName(prevListBean.getTitle());
            musicEntry.setUrl(prevListBean.getUrl());
            musicEntry.setShareTitle(pData.getShareTitle());
            musicEntry.setShareDescript(pData.getShareDescript());
            musicEntry.setShareUrl(pData.getShareUrl());
            musicEntry.setDuration(prevListBean.getDuration());
            musicEntry.setSharePic(prevListBean.getRoundPic());
            musicEntry.setMusicId(prevListBean.getTargetId());
            musicList.add(musicEntry);
        }
        return musicList;
    }
}
