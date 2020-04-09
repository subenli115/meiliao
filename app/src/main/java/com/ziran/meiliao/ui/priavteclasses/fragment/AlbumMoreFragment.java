package com.ziran.meiliao.ui.priavteclasses.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonwidget.PlayPauseView;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.common.security.AES;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.entry.MusicEntry;
import com.ziran.meiliao.service.ServiceManager;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.AlbumMoreBean;
import com.ziran.meiliao.ui.bean.CommAlbumMoreBean;
import com.ziran.meiliao.ui.bean.HeadData;
import com.ziran.meiliao.ui.priavteclasses.activity.AlbumClassifyActivity;
import com.ziran.meiliao.ui.priavteclasses.activity.AlbumMoreTwoActivity;
import com.ziran.meiliao.ui.priavteclasses.adapter.SJKHomeZhuanLanAdapter1;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.MusicPanelFloatManager;
import com.ziran.meiliao.widget.GlideRoundTransform;
import com.ziran.meiliao.widget.pupop.SharePopupWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * 私家课-直播详情-简介Fragment
 * Created by Administrator on 2017/1/7.
 */

public class AlbumMoreFragment extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract
        .View<AlbumMoreBean> {
    @Bind(R.id.iv_voice1)
    ImageView ivVoice1;
    @Bind(R.id.iv_voice2)
    ImageView ivVoice2;
    @Bind(R.id.iv_voice3)
    ImageView ivVoice3;
    @Bind(R.id.iv_voice4)
    ImageView ivVoice4;
    @Bind(R.id.iv_main_home_music_play_or_pause)
    PlayPauseView ivMainHomeMusicPlayOrPause;
    @Bind(R.id.tv_music_name1)
    TextView tvName1;
    @Bind(R.id.tv_music_name2)
    TextView tvName2;
    @Bind(R.id.tv_music_name3)
    TextView tvName3;
    @Bind(R.id.tv_music_name4)
    TextView tvName4;
    private SharePopupWindow mSharePopupWindow;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.iv_rec_bg)
    ImageView ivRec;
    @Bind(R.id.tv_main_home_music_title)
    TextView tv_music;
    @Bind(R.id.tv_main_home_music_more)
    TextView tv_name;

    @Bind(R.id.iv_share)
    ImageView ivShare;

    private List<ImageView> objects;
    private List<TextView> nameList;
    private AlbumMoreBean.DataBean data;
    private List<String> ids;
    private String albumUrl;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_new_listen_guide;
    }

    @Override
    protected void initView() {
        super.initView();
        iRecyclerView.setNestedScrollingEnabled(false);
       nameList = new ArrayList<>();
       ids=new ArrayList<>();
        nameList.add(tvName1);
        nameList.add(tvName2);
        nameList.add(tvName3);
        nameList.add(tvName4);
         objects = new ArrayList<>();
        objects.add(ivVoice1);
        objects.add(ivVoice2);
        objects.add(ivVoice3);
        objects.add(ivVoice4);
        //订阅点击更多的监听
        mRxManager.on(AppConstant.RXTag.MAIN_HOME_MORE_CLICK, new Action1<HeadData>() {
            @Override
            public void call(HeadData headData) {
                AlbumMoreTwoActivity.startAction(getContext(),headData);
            }
        });

    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return true;
            }
        };

        iRecyclerView.setLayoutManager(linearLayoutManager);
        return new SJKHomeZhuanLanAdapter1(getContext());
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        if (!CheckUtil.check(getContext(), getView())) return;

    }

    @Override
    protected void loadData() {
        mPresenter.postData(ApiKey.ALBUM_GETALBUMLIST, MapUtils.getDefMap(true), AlbumMoreBean.class);
    }


    //button点击监听
    @OnClick({ R.id.iv_main_home_music_play_or_pause,R.id.all_emotion,R.id.all_more,R.id.all_jy,R.id.all_zm,R.id.iv_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_main_home_music_play_or_pause:
                if (!CheckUtil.check(view.getContext(), view)) return;
                MusicPanelFloatManager.getInstance().addPlayOrPauseView(ivMainHomeMusicPlayOrPause);
                MyAPP.mServiceManager.setClickFrom(ServiceManager.CLICK_FROM_PUSH);
                MyAPP.mServiceManager.playUrl(albumUrl);
                break;
            case R.id.all_emotion:
                startActivity(0);
                break;
            case R.id.all_jy:
                startActivity(1);
                break;
            case R.id.all_zm:
                startActivity(2);
                break;

            case R.id.all_more:
                AlbumClassifyActivity.startAction(getContext());
                break;
            case R.id.iv_share:
                MusicEntry musicEntry = new MusicEntry();
                AlbumMoreBean.DataBean.RecAlbumMusicBean recAlbumMusic = data.getRecAlbumMusic();
                musicEntry.setShareTitle(recAlbumMusic.getShareTitle());
                musicEntry.setShareDescript(recAlbumMusic.getShareDescript());
                musicEntry.setShareUrl(recAlbumMusic.getShareUrl());
                musicEntry.setSharePic(recAlbumMusic.getSharePic());
                SharePopupWindow.showPopup(getActivity(), mSharePopupWindow, musicEntry);
                break;
        }
    }

    private void startActivity(int num) {
        HeadData headData = HeadData.create(HeadData.Type.TITLE, "", false);
        headData.setId(Integer.parseInt(ids.get(num)));
        headData.setTitle(nameList.get(num).getText().toString());
        AlbumMoreTwoActivity.startAction(getContext(), headData);
    }

    @Override
    public void onPause() {
        super.onPause();
        String currentPlayUrl = MyAPP.mServiceManager.currentPlayUrl;
        if(currentPlayUrl!=null&&currentPlayUrl.equals(albumUrl)){
            MyAPP.mServiceManager.stop();
        }
    }

    @Override
    public void returnData(AlbumMoreBean result) {
         albumUrl = result.getData().getRecAlbumMusic().getUrl();
        albumUrl = AES.get().decrypt(albumUrl);
        List list = new ArrayList();
         data = result.getData();
        setRECMusic(data.getRecAlbumMusic());
        for(int k=0;k<data.getTagList().size();k++){
            Glide.with(getContext()).load(data.getTagList().get(k).getPicture()).transform(new GlideRoundTransform(getContext())).into(objects.get(k));
            nameList.get(k).setText(data.getTagList().get(k).getName());
            ids.add(data.getTagList().get(k).getTagId()+"");
        }
        List<AlbumMoreBean.DataBean.AlbumListBean> albumList = data.getAlbumList();
        for(int i=0;i<albumList.size();i++){

            if (EmptyUtils.isNotEmpty(albumList.get(i).getDetailList())) {
                HeadData headData = HeadData.create(HeadData.Type.TITLE, albumList.get(i).getTagName(), false);
                headData.setId(albumList.get(i).getTagId());
                list.add(headData);
                CommAlbumMoreBean commAlbumMoreBean = new CommAlbumMoreBean();
                commAlbumMoreBean.setDetailList(albumList.get(i).getDetailList());
                list.add(commAlbumMoreBean);
            }
        }
        updateData(list);
    }

    private void setRECMusic(AlbumMoreBean.DataBean.RecAlbumMusicBean recAlbumMusic) {
        tvTime.setText(recAlbumMusic.getDuration());
        tv_name.setText(recAlbumMusic.getName());
        tv_music.setText(recAlbumMusic.getNotice());
        Glide.with(getContext()).load(recAlbumMusic.getPicture()).into(ivRec);
    }

}
