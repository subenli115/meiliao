package com.ziran.meiliao.ui.main.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.aliyun.player.AliPlayer;
import com.aliyun.player.AliPlayerFactory;
import com.aliyun.player.IPlayer;
import com.aliyun.player.bean.InfoBean;
import com.aliyun.player.bean.InfoCode;
import com.aliyun.player.nativeclass.TrackInfo;
import com.aliyun.player.source.VidAuth;
import com.aliyun.vod.common.utils.StringUtils;
import com.freegeek.android.materialbanner.MaterialBanner;
import com.freegeek.android.materialbanner.view.indicator.CirclePageIndicator;
import com.luck.picture.lib.PictureSelector;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonutils.TimeUtil;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonwidget.OnNoDoubleClickListener;
import com.ziran.meiliao.common.irecyclerview.IRecyclerView;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.AliAuthBean;
import com.ziran.meiliao.ui.bean.AliVideoInfoBean;
import com.ziran.meiliao.ui.bean.StringDataV2Bean;
import com.ziran.meiliao.ui.bean.UserBean;
import com.ziran.meiliao.ui.main.adapter.SimpleStringViewHolderCreator;
import com.ziran.meiliao.ui.me.activity.EditUserInfoActivity;
import com.ziran.meiliao.ui.me.activity.HomeImageSelectActivity;
import com.ziran.meiliao.ui.priavteclasses.activity.UserHomeActivity;
import com.ziran.meiliao.utils.MapUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import cn.bingoogolapple.photopicker.activity.BGAPhotoPreviewActivity;
import me.xfans.lib.voicewaveview.VoiceWaveView;

/**
 * 头部工具类 on 2018/12/20.
 */

public class MainHomeHeadViewV2Util {
    private final Context mContext;
    private final View headView;
    private final RecyclerView mRecyclerView;
    private final ImageView ivRealName,ivSex,ivLike;
    private final TextView tvUserName;
    private final TextView tvSign;
    private final View allInfo;
    private final View ivInto;
    private final View tvReal;
    private final TextView tvLike;
    private final View allLike;
    private final UserHomeActivity mUserHomeActivity;
    private final View allRecord,allAddRecord;
    private final MaterialBanner materialBanner;
    private final TextView tvTime;
    private final VoiceWaveView voiceWaveView4;
    private final boolean isVideo;
    private final AliPlayer aliyunVodPlayer;
    private final AutoLinearLayout allEdit;
    private boolean isLike;
    private CirclePageIndicator circlePageIndicator;
    private int num;
    private List<String> imgs;
    private String playURL;
    private SimpleStringViewHolderCreator simpleStringViewHolderCreator;
    private ArrayList<String> data;

    private int state=5;
//    private final ImageView ivHead;

    public MainHomeHeadViewV2Util(IRecyclerView iRecyclerView, UserBean.DataBean dataBean, boolean isSelf, UserHomeActivity userHomeActivity,  AliPlayer aliyunVodPlayer) {
        mContext = iRecyclerView.getContext();
        mRecyclerView = iRecyclerView;
        mUserHomeActivity=userHomeActivity;
        this.headView = LayoutInflater.from(mContext).inflate(R.layout.headview_user_home, null);
        ivRealName = this.headView.findViewById(R.id.iv_real_name);
        allInfo = this.headView.findViewById(R.id.all_info);
        materialBanner = this.headView.findViewById(R.id.material_banner);
        allAddRecord = this.headView.findViewById(R.id.all_add_record);
        this.aliyunVodPlayer = aliyunVodPlayer;
        allRecord = this.headView.findViewById(R.id.all_record);
        tvReal = this.headView.findViewById(R.id.tv_real);
        ivLike = this.headView.findViewById(R.id.iv_like);
        tvLike = this.headView.findViewById(R.id.tv_like);
        allLike = this.headView.findViewById(R.id.all_like);
        allEdit = this.headView.findViewById(R.id.all_edit);
        voiceWaveView4= this.headView.findViewById(R.id.voiceWaveView4);
        tvTime = this.headView.findViewById(R.id.tv_time);
        ivInto = this.headView.findViewById(R.id.iv_into);
        initIndicator();
        List<String> homeBean = mUserHomeActivity.getHomeBean();
        if(dataBean!=null&&dataBean.getVideo()!=null&&dataBean.getVideo().length()>0){
            simpleStringViewHolderCreator = new SimpleStringViewHolderCreator("1");
            Map<String, String> defMap = MapUtils.getDefMap(true);
            defMap.put("videoId",dataBean.getVideo());
            OkHttpClientManager.getAsyncMore(ApiKey.ADMIN_TOOL_GETPLAYINFO, defMap, new NewRequestCallBack<AliVideoInfoBean>(AliVideoInfoBean.class) {
                @Override
                protected void onSuccess(AliVideoInfoBean result) {
                    playURL = result.getData().getPlayInfoList().get(0).getPlayURL();
                }
                @Override
                public void onError(String msg, int code) {
                    ToastUitl.showShort(msg);
                }
            });
            isVideo=true;
        }else {
            isVideo=false;
            simpleStringViewHolderCreator = new SimpleStringViewHolderCreator("");
        }
        if(homeBean==null){
            setData(dataBean);
        }else {
            setPreview(homeBean);
        }
        if(isSelf){
            allEdit.setVisibility(View.VISIBLE);
            allEdit.setOnClickListener(new OnNoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View v) {
                    HomeImageSelectActivity.startAction();
                }
            });
            allInfo.setOnClickListener(new OnNoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View v) {
                    EditUserInfoActivity.startAction();
                }
            });
            ivInto.setVisibility(View.VISIBLE);

        }else {
            allAddRecord.setVisibility(View.GONE);
            allLike.setOnClickListener(new OnNoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View v) {
                    if(!isLike){
                        Map<String, String> defMap = MapUtils.getDefMap(true);
                        defMap.put("clickContentId",dataBean.getId());
                        defMap.put("userId",MyAPP.getUserId());
                        defMap.put("type","1");
                        OkHttpClientManager.postAsyncAddHead(ApiKey.ADMIN_USERCLICK_ADD, defMap, "", new
                                NewRequestCallBack<StringDataV2Bean>(StringDataV2Bean.class) {

                                    @Override
                                    public void onSuccess(StringDataV2Bean listBean) {
                                        mUserHomeActivity.getData();

                                    }

                                    @Override
                                    public void onError(String msg, int code) {
                                        ToastUitl.showShort(msg);
                                    }
                                });
                    }

                }
            });
        }
        ivSex = this.headView.findViewById(R.id.iv_sex);
        tvUserName = this.headView.findViewById(R.id.tv_username);
        tvSign = this.headView.findViewById(R.id.tv_sign);
        if(iRecyclerView.isHasHeaderView()){
            iRecyclerView.getHeaderContainer().removeAllViews();
            iRecyclerView.setHasHeaderView(false);
            iRecyclerView.setHeadView(headView);
        }else {
            iRecyclerView.setHeadView(headView);
        }
        initData(dataBean);
    }


    private void initIndicator() {
        circlePageIndicator = new CirclePageIndicator(mContext);
        circlePageIndicator.setFillColor(Color.WHITE);
        circlePageIndicator.setRadius(MaterialBanner.dip2Pix(mContext, 3));
        circlePageIndicator.setBetween(20);
    }

    public  void setData(UserBean.DataBean dataBean){
        if(dataBean!=null){
             initBanner(dataBean);
        }
    }

    private void initBanner(UserBean.DataBean dataBean) {
        data = new ArrayList<>();
        if(isVideo){
            data.add(dataBean.getVideoCover());
        }
        if( !StringUtils.isEmpty(dataBean.getHomepageImages())){
            String[] split = dataBean.getHomepageImages().split(",");
            if(split!=null&&split.length>0){
                imgs = Arrays.asList(split);
                data.addAll(imgs);
            }
        }
        setPreview( data);
        mUserHomeActivity.saveHomeBean(data);
    }

    private void setPreview(List<String> data) {
        imgs=data;
        if(data.size()==0){
            materialBanner.setVisibility(View.GONE);
        }else {
            materialBanner.setPages(simpleStringViewHolderCreator, data)
                    .setIndicator(circlePageIndicator);
            materialBanner.setOnItemClickListener(i -> {
                if(isVideo){
                    if(i==0){
                        PictureSelector.create(mUserHomeActivity).externalPictureVideo(playURL);
                    }else {
                        preview(i-1);
                    }
                }else {
                    preview(i);

                }
            });
            materialBanner.startTurning(3000);
        }
    }
    private void addVoice() {
        for(int i=0;i<15;i++){
            voiceWaveView4.addBody((int)(Math.random()*100));
        }
    }

    private void preview(int i) {

        BGAPhotoPreviewActivity.IntentBuilder photoPreviewIntentBuilder = new BGAPhotoPreviewActivity.IntentBuilder(mContext)
                .saveImgDir(null); // 保存图片的目录，如果传 null，则没有保存图片功能
        ArrayList<String> strings = new ArrayList<>(imgs);
        if(isVideo){
            strings.remove(0);
        }
        photoPreviewIntentBuilder.previewPhotos(strings)

                .currentPosition(i); // 当前预览图片的索引
        mContext.startActivity(photoPreviewIntentBuilder.build());
    }
    public void startActivity(Class clz) {
        Intent intent = new Intent(mContext, clz);
        mContext.startActivity(intent);
    }

    public void initData( UserBean.DataBean dataBean) {
        if (dataBean != null) {
            ivRealName.setVisibility(View.VISIBLE);
            if(dataBean.getIsReal()!=null&&dataBean.getIsReal().equals("1")){
                    ivRealName.setImageResource(R.mipmap.icon_real_people_home);
                }else {
                ivRealName.setImageResource(R.mipmap. icon_noreal_people_home);
                }
            if (dataBean.getSex() == 1) {
                ivSex.setImageResource(R.mipmap.icon_man_home);
            }else {
                ivSex.setImageResource(R.mipmap.icon_woman_home);
            }
            if(dataBean.getVoice()!=null&&dataBean.getVoice().length()>0){
                allRecord.setVisibility(View.VISIBLE);
                addVoice();
                initPlayer(dataBean);
                tvTime.setText(dataBean.getVoiceDuration());
                allRecord.setOnClickListener(new OnNoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        if(state==3){
                            //正在播放
                            voiceWaveView4.stop();
                            aliyunVodPlayer.pause();
                        }else if(state==4||state==6){
                            aliyunVodPlayer.start();
                            voiceWaveView4.start();
                        }else if(state==5){
                            aliyunVodPlayer.prepare();
                        }
                    }
                });
                allAddRecord.setVisibility(View.GONE);
            }else {
                allAddRecord.setOnClickListener(new OnNoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        HomeImageSelectActivity.startAction();
                    }
                });

            }
            tvUserName.setText(dataBean.getNickname());

            if (dataBean.getIntroduce() != null && !dataBean.getIntroduce().equals("")) {
                tvSign.setText(dataBean.getIntroduce() + "");
                tvSign.setTextColor(Color.parseColor("#474766"));
            }else {
                tvSign.setText("期待认识有趣的人和事");
            }

            if(dataBean.getIsReal()!=null&&dataBean.getIsReal().equals("1")){
                tvReal.setVisibility(View.VISIBLE);
            }
            if(dataBean.getIsClick()!=null&&dataBean.getIsClick().equals("1")){
                isLike=true;
                ivLike.setImageResource(R.mipmap.icon_user_home_islike);
                tvLike.setTextColor(Color.parseColor("#FFEC73"));
            }
            if(dataBean.getClick()!=null){
                num = Integer.parseInt(dataBean.getClick());
            }

            tvLike.setText(num+"");

        }
    }

    private void initPlayer(UserBean.DataBean dataBean) {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("videoId",dataBean.getVoice());
        OkHttpClientManager.getAsyncMore(ApiKey.ADMIN_TOOL_GETVIDEOPLAYAUTH, defMap, new NewRequestCallBack<AliAuthBean>(AliAuthBean.class) {
            @Override
            protected void onSuccess(AliAuthBean result) {
             String  playAuth = result.getData().getPlayAuth();
                VidAuth vidAuth = new VidAuth();
                vidAuth.setVid(dataBean.getVoice()+"");
                vidAuth.setPlayAuth(playAuth);
                vidAuth.setRegion("cn-shanghai");
                //设置播放源
                aliyunVodPlayer.setDataSource(vidAuth);

            }
            @Override
            public void onError(String msg, int code) {
                ToastUitl.showShort(msg);
            }
        });
        aliyunVodPlayer.setOnInfoListener(new IPlayer.OnInfoListener() {
            @Override
            public void onInfo(InfoBean infoBean) {
                if(infoBean.getCode()== InfoCode.CurrentPosition){
                    long extraValue = infoBean.getExtraValue();
                    tvTime.setText(TimeUtil.getTimeFormLong(extraValue));
                    List<TrackInfo> trackInfos = aliyunVodPlayer.getMediaInfo().getTrackInfos();
                    for(int i=0;i<trackInfos.size();i++){
                        Log.e("trackInfos",""+trackInfos.get(i));
                    }
                }
            }
        });
        aliyunVodPlayer.setOnPreparedListener(new IPlayer.OnPreparedListener() {
            @Override
            public void onPrepared() {
                //准备成功事件
                aliyunVodPlayer.start();
                voiceWaveView4.start();
            }
        });
        aliyunVodPlayer.setOnCompletionListener(new IPlayer.OnCompletionListener() {
            @Override
            public void onCompletion() {
                voiceWaveView4.stop();
                aliyunVodPlayer.stop();
                tvTime.setText(dataBean.getVoiceDuration());
            }
        });
        aliyunVodPlayer.setOnStateChangedListener(new IPlayer.OnStateChangedListener() {
            @Override
            public void onStateChanged(int newState) {
                //播放器状态改变事件
                state=newState;
            }
        });
    }


}
