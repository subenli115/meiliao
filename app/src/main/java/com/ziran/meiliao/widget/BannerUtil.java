package com.ziran.meiliao.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.freegeek.android.materialbanner.MaterialBanner;
import com.freegeek.android.materialbanner.view.indicator.CirclePageIndicator;
import com.luck.picture.lib.PictureSelector;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.im.activity.ChatActivity;
import com.ziran.meiliao.ui.bean.AliVideoInfoBean;
import com.ziran.meiliao.ui.bean.BillDetailsBean;
import com.ziran.meiliao.ui.bean.UserBean;
import com.ziran.meiliao.ui.main.adapter.SimpleChatViewHolderCreator;
import com.ziran.meiliao.ui.main.adapter.SimpleStringViewHolderCreator;
import com.ziran.meiliao.ui.priavteclasses.activity.UserHomeActivity;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.PicassoEngine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import cn.bingoogolapple.photopicker.activity.BGAPhotoPreviewActivity;

public class BannerUtil {

    private final ChatActivity mContext;
    private final Activity mActivity;
    private final boolean mIsClick;
    private final SimpleStringViewHolderCreator simpleStringViewHolderCreator;
    private final boolean isVideo;
    private String playURL;
    private List<String> imgs;
    private MaterialBanner materialBanner;
    private ArrayList<String> data;

    public BannerUtil(ChatActivity activity, MaterialBanner mb, UserBean.DataBean dataBean, boolean isClick) {
        mContext=activity;
        mIsClick=isClick;
        mActivity=activity;
        materialBanner=mb;
        initIndicator();
        if(dataBean!=null&&dataBean.getVideo()!=null&&dataBean.getVideo().length()>0){
            simpleStringViewHolderCreator = new SimpleStringViewHolderCreator("2");
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
        List<String> homeBean =  mContext.getHomeBean();
        if(homeBean==null){
            setData(dataBean);
        }else {
            if(homeBean.size()>0){
                materialBanner.setOnItemClickListener(new MaterialBanner.OnItemClickListener() {
                    @Override
                    public void onItemClick(int i) {
                        UserHomeActivity.startAction(dataBean.getId());
                    }
                });
                materialBanner.setPages(simpleStringViewHolderCreator,homeBean).setIndicator(circlePageIndicator);
                materialBanner.startTurning(3000);
            }
        }
    }
    private CirclePageIndicator circlePageIndicator;

    private void initIndicator() {
        circlePageIndicator = new CirclePageIndicator(mActivity);
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
        if(dataBean.getHomepageImages()!=null){
            String[] split = dataBean.getHomepageImages().split(",");
            if(split!=null&&split.length>0){
                imgs = Arrays.asList(split);
                data.addAll(imgs);
            }
        }
        if(data.size()>0){
            materialBanner.setPages(new SimpleChatViewHolderCreator("1"), data)
                    .setIndicator(circlePageIndicator);
            if(mIsClick){
                materialBanner.setOnItemClickListener(i -> {
                    if(isVideo){
                        if(i==0){
                            PictureSelector.create(mActivity).externalPictureVideo(playURL);
                        }else {
                            preview(i-1);
                        }
                    }else {
                        preview(i);

                    }

                });
            }
            materialBanner.startTurning(3000);
        }else {
            materialBanner.setVisibility(View.GONE);
        }
    }

    private void preview(int i) {
        BGAPhotoPreviewActivity.IntentBuilder photoPreviewIntentBuilder = new BGAPhotoPreviewActivity.IntentBuilder(mContext)
                .saveImgDir(null); // 保存图片的目录，如果传 null，则没有保存图片功能

        photoPreviewIntentBuilder.previewPhotos(new ArrayList<String>(imgs))

                .currentPosition(i); // 当前预览图片的索引
        mContext.startActivity(photoPreviewIntentBuilder.build());

    }
}
