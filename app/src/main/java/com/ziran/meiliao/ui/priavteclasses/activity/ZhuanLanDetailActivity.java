package com.ziran.meiliao.ui.priavteclasses.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.WpyxConfig;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.ShareActivity;
import com.ziran.meiliao.ui.bean.SpceColumnBean;
import com.ziran.meiliao.ui.priavteclasses.bean.ZhuanLanData;
import com.ziran.meiliao.ui.priavteclasses.fragment.SJKZhuanLanDetailFragment;
import com.ziran.meiliao.utils.MusicPanelFloatManager;
import com.ziran.meiliao.utils.StringUtils;
import com.ziran.meiliao.widget.ARewardV1View;
import com.ziran.meiliao.widget.CustomMusicPanelNewView;
import com.umeng.socialize.bean.SHARE_MEDIA;

import butterknife.Bind;


public class ZhuanLanDetailActivity extends ShareActivity {


    @Bind(R.id.ntb)
    NormalTitleBar ntb;
    @Bind(R.id.aRewardView)
    ARewardV1View mARewardV1View;
    @Bind(R.id.musicPanelView)
    CustomMusicPanelNewView mCustomMusicPanelNewView;
    private final static int REQUESTCODE = 1; // 返回的结果码

    private String subscriptionId;

    private SJKZhuanLanDetailFragment sjkZhuanLanDetailFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_sjk_zhuanlan;
    }

    @Override
    public void initPresenter() {

    }

    public static void startAction(Context context, int specColumnId, boolean isBuy, String htmlLink, Activity activity,int num,int subscriptionNum) {
        startAction(context, specColumnId, num,isBuy,htmlLink,subscriptionNum);
    }

    public static void startAction(Context context, int specColumnId, int type, boolean isBuy, String htmlLink,int subscriptionNum) {
        Intent intent = new Intent(context, NoBuyZLActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(AppConstant.ExtraKey.SUBSCRIPTION_ID, String.valueOf(specColumnId));
        bundle.putString("subscriptionNum", String.valueOf(subscriptionNum));
        bundle.putInt(AppConstant.ExtraKey.FROM_TYPE, type);
        if(isBuy){
            bundle.putString("isbuy","1");
        }else {
            bundle.putString("isbuy","0");
        }
        bundle.putString("htmlLink",htmlLink);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public void initView() {
        try {
            subscriptionId = getIntent().getExtras().getString(AppConstant.ExtraKey.SUBSCRIPTION_ID);
        } catch (Exception e) {
            subscriptionId = "";
            e.printStackTrace();
        }
        ntb.setOnRightImagListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sjkZhuanLanDetailFragment.gzZhuanLan();
            }
        });
        ntb.setOnRightImag2Listener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sjkZhuanLanDetailFragment.share();
            }
        });
        sjkZhuanLanDetailFragment = new SJKZhuanLanDetailFragment();
        initFragment(sjkZhuanLanDetailFragment);
        mARewardV1View.setOnShareClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sjkZhuanLanDetailFragment.share();
            }
        });
        mARewardV1View.setOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sjkZhuanLanDetailFragment.getBundle(subscriptionId, 1);
                sjkZhuanLanDetailFragment.buy();
            }
        });


    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }


    public void setFollow(boolean isGz) {
        ntb.getIvRight().setSelected(isGz);
    }

    public void setPrice(String text) {
        mARewardV1View.setVisibility(View.VISIBLE);
        mARewardV1View.setPrice(text);
        mARewardV1View.setParmes(8, 3);
        setBuyState("已订阅".equals(text));
    }

    public void setPrice(SpceColumnBean.DataBean dataBean) {
        mARewardV1View.setVisibility(View.VISIBLE);
        String text = StringUtils.format("订阅 ( ¥%d )", dataBean.getNeedCoin() / 10);
        mARewardV1View.setPrice(text);
        mARewardV1View.setParmes(dataBean.getShareTimes(), dataBean.getFinishTimes());
        setBuyState("已订阅".equals(text));
        setAvd(dataBean.getSubscribeWord());
    }

    public void setAvd(String url) {
        mARewardV1View.setAvd(url);
    }

    public void setBuyState(boolean isBuy) {
        if (isBuy) {
            mARewardV1View.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        ZhuanLanData zhuanLanData = WpyxConfig.getZhuanLanData();
        if (zhuanLanData != null) {
            setBuyState(zhuanLanData.isBuy());
        }
        MusicPanelFloatManager.getInstance().bindView(mCustomMusicPanelNewView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MusicPanelFloatManager.getInstance().unBindView(mCustomMusicPanelNewView);
    }


    @Override
    public void onStart(SHARE_MEDIA share_media) {
    }
}
