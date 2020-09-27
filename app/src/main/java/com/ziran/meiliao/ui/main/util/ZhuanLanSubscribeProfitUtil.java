package com.ziran.meiliao.ui.main.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.aliyun.player.AliPlayer;
import com.aliyun.player.IPlayer;
import com.aliyun.player.bean.ErrorInfo;
import com.aliyun.player.bean.InfoBean;
import com.aliyun.player.bean.InfoCode;
import com.aliyun.player.nativeclass.CacheConfig;
import com.aliyun.player.nativeclass.TrackInfo;
import com.aliyun.player.source.UrlSource;
import com.aliyun.player.source.VidAuth;
import com.bumptech.glide.Glide;
import com.freegeek.android.materialbanner.MaterialBanner;
import com.freegeek.android.materialbanner.view.indicator.CirclePageIndicator;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.TimeUtil;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.commonwidget.OnNoDoubleClickListener;
import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.ziran.meiliao.common.irecyclerview.IRecyclerView;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.im.activity.CommonGiftActivity;
import com.ziran.meiliao.ui.bean.AliAuthBean;
import com.ziran.meiliao.ui.bean.AliVideoInfoBean;
import com.ziran.meiliao.ui.bean.CommentListBean;
import com.ziran.meiliao.ui.bean.SpaceDetailBean;
import com.ziran.meiliao.ui.main.adapter.SimpleStringViewHolderCreator;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.PicassoEngine;
import com.ziran.meiliao.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import me.xfans.lib.voicewaveview.VoiceWaveView;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/9/8 18:15
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/9/8$
 * @updateDes ${TODO}
 */

public class ZhuanLanSubscribeProfitUtil {
    private final Activity mActivity;
    private Context mContext;
    private AutoLinearLayout headView;
    private static final int LIKE_LIST = 1;
    private View footerView;
    private AutoLinearLayout allPrivate,all;
    private TextView tvDistance,tvName,tvAddress,tvOther,tvTime,tvEmpty,tvComment;
    private ImageView ivHead;
    private ImageView ivRealName;
    private ImageButton ibLeft,ibRight;
    private TextView tvContent,tvNum,tvSend,tvSoundTime;
    private MaterialBanner materialBanner;
    private ImageView img;
    private AutoRelativeLayout arlLike,arl,arlSound, arlInfo;
    private boolean isSelf;
    private View view;
    private String bg;
    private List<String> imgs;
    private CirclePageIndicator circlePageIndicator;
    private AliPlayer aliyunVodPlayer;
    private String playAuth;
    private ImageView ivPlay;
    private VoiceWaveView voiceWaveView4;
    private int state;
    private String duration;
    private SurfaceView surfaceView;
    private AutoRelativeLayout arlSurface;
    private ImageView ivVideoPlay;
    private int playType;
    private ImageView ivBg;
    private String playURL;
    private ImageView ivClick;


    public ZhuanLanSubscribeProfitUtil(Context context, Activity activity, AliPlayer player, AutoLinearLayout parent) {
        mContext = context;
        mActivity=activity;
        aliyunVodPlayer=player;
        headView=parent;
        initView();
        initPlayer();
    }

    private void initView() {
//        if (headView == null) {
//            mParent.addView(headView,0);
            ivHead = (ImageView) headView.findViewById(R.id.iv_top_head);
            tvAddress = (TextView) headView.findViewById(R.id.tv_address);
            arlLike = (AutoRelativeLayout) headView.findViewById(R.id.arl_like);
            ivPlay = headView.findViewById(R.id.iv_play);
            tvTime = (TextView) headView.findViewById(R.id.tv_time);
            tvNum = (TextView) headView.findViewById(R.id.tv_num);
            view = (View) headView.findViewById(R.id.view);
            tvEmpty = (TextView) headView.findViewById(R.id.tv_empty);
            tvComment = (TextView) headView.findViewById(R.id.tv_comment);
            tvSoundTime = (TextView) headView.findViewById(R.id.tv_sound_time);
            tvSend = (TextView) headView.findViewById(R.id.tv_send);
            surfaceView = (SurfaceView) headView.findViewById(R.id.surfaceView);
            ivVideoPlay = (ImageView) headView.findViewById(R.id.iv_video_play);
            ivBg = (ImageView) headView.findViewById(R.id.iv_bg);
            ivClick = (ImageView) headView.findViewById(R.id.iv_click);
            arlSurface = headView.findViewById(R.id.arl_surface);
            voiceWaveView4 = headView.findViewById(R.id.voiceWaveView4);
            img = (ImageView) headView.findViewById(R.id.img);
            allPrivate = (AutoLinearLayout) headView.findViewById(R.id.all_private);
            all = (AutoLinearLayout) headView.findViewById(R.id.all);
            arl = (AutoRelativeLayout) headView.findViewById(R.id.arl);
            arlSound = (AutoRelativeLayout) headView.findViewById(R.id.arl_sound);
            arlInfo = (AutoRelativeLayout) headView.findViewById(R.id.arl_info);
            tvContent = (TextView) headView.findViewById(R.id.tv_content);
            materialBanner = (MaterialBanner) headView.findViewById(R.id.material_banner);
//        }
    }

    private void initPlayer() {
        aliyunVodPlayer.setOnInfoListener(new IPlayer.OnInfoListener() {
            @Override
            public void onInfo(InfoBean infoBean) {
                if(infoBean.getCode()== InfoCode.CurrentPosition){
                    long extraValue = infoBean.getExtraValue();
                     Log.e("extraValue",""+aliyunVodPlayer.getMediaInfo().getTrackInfos().get(0));
                    tvSoundTime.setText(TimeUtil.getTimeFormLong(extraValue)+"/"+duration);
                }
            }
        });
        aliyunVodPlayer.setOnCompletionListener(new IPlayer.OnCompletionListener() {
            @Override
            public void onCompletion() {
                //播放完成事件
                if(playType==1){
                    voiceWaveView4.stop();
                    aliyunVodPlayer.stop();
                    tvSoundTime.setText("00:00/"+duration);
                    ivPlay.setImageResource(R.mipmap.icon_record_play);
                }else {
                    ivVideoPlay.setVisibility(View.VISIBLE);

                }
            }
        });
        aliyunVodPlayer.setOnErrorListener(new IPlayer.OnErrorListener() {
            @Override
            public void onError(ErrorInfo errorInfo) {
                //出错事件
                if(playType==1){
                    tvSoundTime.setText("00:00/"+duration);
                }
            }
        });
        aliyunVodPlayer.setOnPreparedListener(new IPlayer.OnPreparedListener() {
            @Override
            public void onPrepared() {
                //准备成功事件
                aliyunVodPlayer.start();
            }
        });
        aliyunVodPlayer.setOnStateChangedListener(new IPlayer.OnStateChangedListener() {
            @Override
            public void onStateChanged(int newState) {
                //播放器状态改变事件
                    state=newState;
                    Log.e("onCompletion","state"+state);
                    if(state==3){
                        if(playType==1){
                            voiceWaveView4.start();
                            ivPlay.setImageResource(R.mipmap.icon_record_suspend);
                        }else {
                            ivVideoPlay.setVisibility(View.GONE);
                            ivBg.setVisibility(View.GONE);
                        }
                    }
            }
        });
        //创建VidSts
        CacheConfig cacheConfig = new CacheConfig();
    //开启缓存功能
        cacheConfig.mEnable = true;
        //能够缓存的单个文件最大时长。超过此长度则不缓存
        cacheConfig.mMaxDurationS =100;
    //缓存目录的位置
        cacheConfig.mDir = FileUtil.getDownVideoFolder();
        //缓存目录的最大大小。超过此大小，将会删除最旧的缓存文件
        cacheConfig.mMaxSizeMB = 200;
    //设置缓存配置给到播放器
        aliyunVodPlayer.setCacheConfig(cacheConfig);
    }



    public void setData(SpaceDetailBean.DataBean dataBean) {
        if(dataBean!=null){
            if(dataBean.getContent()!=null&&dataBean.getContent().length()>0){
                tvContent.setText(dataBean.getContent()+"");
            }else {
                tvContent.setVisibility(View.GONE);
            }
            ivClick.setVisibility(View.VISIBLE);
            tvTime.setText(TimeUtil.convertToShowStr(dataBean.getCreateTime()));
            if(dataBean.getAddress()!=null&&dataBean.getAddress().length()>0){
                tvAddress.setText(dataBean.getAddress());
                all.setVisibility(View.VISIBLE);
            }
            if (dataBean.getStatus() == 0) {
                allPrivate.setVisibility(View.VISIBLE);
            }
            if(all.getVisibility()==View.GONE&&allPrivate.getVisibility()==View.GONE){
                arlInfo.setVisibility(View.GONE);
            }
            List<String> avatarList = dataBean.getAvatarList();
            if(dataBean.getGiftTotal()==0){
                arl.setVisibility(View.GONE);
                tvSend.setVisibility(View.GONE);
                tvNum.setVisibility(View.GONE);
            }else {
                arl.setVisibility(View.VISIBLE);
                view.setVisibility(View.VISIBLE);
                tvNum.setText(dataBean.getGiftTotal()+"");
                arl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (Utils.isFastDoubleClick()) {
                            return;
                        } else {
                            CommonGiftActivity.startAction(mContext,dataBean.getId());
                        }
                    }
                });
                for(int i=0;i<dataBean.getGiftTotal();i++){

                    if(arlLike.getChildAt(i)!=null&&i<3){
                        ImageView v =(ImageView) arlLike.getChildAt(i);
                        v.setVisibility(View.VISIBLE);
                        Glide.with(mContext).load(avatarList.get(i)).error(R.drawable.jmui_head_icon).into(v);
                    }
                }
            }
            if(dataBean.getEnclosureType()==2){
                    //音频
                playType=1;
                addVoice();
                getPlayUrl(dataBean.getVideoId());
                initPlayData(dataBean);
                arlSound.setVisibility(View.VISIBLE);
                img.setVisibility(View.GONE);
                if(dataBean.getDuration()!=null){
                     duration = dataBean.getDuration();
                    tvSoundTime.setText("00:00/"+dataBean.getDuration()+"");
                }
                ivPlay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(state==3){
                            //正在播放
                            voiceWaveView4.stop();
                            aliyunVodPlayer.pause();
                            ivPlay.setImageResource(R.mipmap.icon_record_play);
                        }else if(state==4||state==6){
                            aliyunVodPlayer.start();
                            voiceWaveView4.start();
                            ivPlay.setImageResource(R.mipmap.icon_record_suspend);
                        }else if(state==5){
                            aliyunVodPlayer.prepare();
                            voiceWaveView4.start();
                            ivPlay.setImageResource(R.mipmap.icon_record_suspend);
                        }
                    }
                });
            }else if(dataBean.getEnclosureType()==1){
                    //视频
                playType=2;
                initSurface();
                getPlayUrl(dataBean.getVideoId());

                initPlayData(dataBean);

                surfaceView.setOnClickListener(new OnNoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        PictureSelector.create(mActivity).externalPictureVideo(playURL);
                    }
                });
            }else {
                //图片
                if(dataBean.getImages()!=null&&dataBean.getImages().contains(",")){
                    String[] split = dataBean.getImages().split(",");
                    if(split!=null&&split.length>0){
                        imgs = Arrays.asList(split);
                    }
                    if(split.length>1){
                        initIndicator();
                        img.setVisibility(View.GONE);
                        materialBanner.setVisibility(View.VISIBLE);
                        materialBanner.setPages(new SimpleStringViewHolderCreator(""), imgs)
                                .setIndicator(circlePageIndicator);
                        materialBanner.startTurning(3000);
                        materialBanner.setOnItemClickListener(new MaterialBanner.OnItemClickListener() {
                            @Override
                            public void onItemClick(int i) {

                                    preview(i);
                            }
                        });
                    }
                }else {
                    if(dataBean.getImages()!=null&&dataBean.getImages().length()>0){
                        Glide.with(mContext).load(dataBean.getImages()).placeholder(R.mipmap.icon_meiliao_error).error(R.mipmap.icon_meiliao_error).into(img);

                        img.setOnClickListener(new OnNoDoubleClickListener() {
                            @Override
                            protected void onNoDoubleClick(View v) {
                                ArrayList<LocalMedia> lists = new ArrayList<>();
                                LocalMedia localMedia = new LocalMedia();
                                localMedia.setPath(dataBean.getImages());
                                lists.add(localMedia);
                                PictureSelector.create(mActivity)
                                        .themeStyle(R.style.picture_default_style)
                                        .isNotPreviewDownload(true)
                                        .loadImageEngine(PicassoEngine.createPicassoEngine())
                                        .openExternalPreview(0, lists);
                            }
                        });
                    }else {
                        img.setVisibility(View.GONE);


                    }
                }
            }
        }
    }

    private void getPlayUrl(String videoId) {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("videoId",videoId);
        OkHttpClientManager.getAsyncMore(ApiKey.ADMIN_TOOL_GETPLAYINFO, defMap, new NewRequestCallBack<AliVideoInfoBean>(AliVideoInfoBean.class) {
            @Override
            protected void onSuccess(AliVideoInfoBean result) {
                playURL = result.getData().getPlayInfoList().get(0).getPlayURL();
                UrlSource urlSource = new UrlSource();
                urlSource.setUri(playURL);
                aliyunVodPlayer.setDataSource(urlSource);
                if(playType==2){
                    aliyunVodPlayer.setVolume(0);
                }
                aliyunVodPlayer.prepare();
            }
            @Override
            public void onError(String msg, int code) {
                ToastUitl.showShort(msg);
            }
        });
    }

    private void preview(int i) {
        ArrayList<LocalMedia> temps = new ArrayList<>();
        for(int k=0;k<imgs.size();k++){
            LocalMedia localMedia = new LocalMedia();
            localMedia.setAndroidQToPath(imgs.get(k));
            localMedia.setPath(imgs.get(k));
            temps.add(localMedia);
        }
        PictureSelector.create(mActivity)
                .themeStyle(R.style.picture_default_style)
                .isNotPreviewDownload(false)
                .loadImageEngine(PicassoEngine.createPicassoEngine())
                .openExternalPreview(i, temps);
    }

    private void initPlayData(SpaceDetailBean.DataBean dataBean) {
        if(playType==2){
            Glide.with(mContext).load( dataBean.getResponse().getVideoMeta().getCoverURL()).centerCrop()
                    .into(ivBg);
        }
//        playAuth = dataBean.getResponse().getPlayAuth();
//        VidAuth vidAuth = new VidAuth();
//        vidAuth.setVid(dataBean.getVideoId()+"");
//        vidAuth.setPlayAuth(playAuth);
//        vidAuth.setRegion("cn-shanghai");
//        //设置播放源
//        aliyunVodPlayer.setDataSource(vidAuth);
        //准备播放
    }

    private void initSurface() {
        img.setVisibility(View.GONE);
        arlSurface.setVisibility(View.VISIBLE);
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                aliyunVodPlayer.setDisplay(holder);
            }
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                aliyunVodPlayer.redraw();
            }
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                aliyunVodPlayer.setDisplay(null);
            }
        });
        ivVideoPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aliyunVodPlayer.prepare();
            }
        });
    }

    public MaterialBanner getBanner(){
        return materialBanner;
    }

    private void addVoice() {
        for(int i=0;i<100;i++){
            voiceWaveView4.addBody((int)(Math.random()*100));
        }
    }


    private void initIndicator() {
        circlePageIndicator = new CirclePageIndicator(mContext);
        circlePageIndicator.setFillColor(Color.WHITE);
        circlePageIndicator.setRadius(MaterialBanner.dip2Pix(mContext, 3));
        circlePageIndicator.setBetween(20);
    }


    private TextView tvFooter;
    public void bindFooterView(IRecyclerView iRecyclerView, CharSequence text, List<CommentListBean.DataBean.RecordsBean> records, CommonRecycleViewAdapter mAdapter){
        if (footerView==null){
            footerView =  LayoutInflater.from(mContext).inflate(R.layout.footerview_text,null);
            tvFooter = ViewUtil.getView(footerView, R.id.tv_title);
            footerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iRecyclerView.setFooterViewState(false);
                    mAdapter.replaceAll(records);
                }
            });
        }
        tvFooter.setText(text);
        iRecyclerView.setFooterView(footerView);
    }

    public void setEmpty(boolean b) {
        if(b){
            tvEmpty.setVisibility(View.VISIBLE);
        }else {
                tvEmpty.setVisibility(View.GONE);
        }
    }

}
