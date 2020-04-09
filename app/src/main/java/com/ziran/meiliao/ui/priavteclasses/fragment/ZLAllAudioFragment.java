package com.ziran.meiliao.ui.priavteclasses.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseFragmentAdapter;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpFragment;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.NewMediaAndTextBean;
import com.ziran.meiliao.ui.priavteclasses.activity.NoBuyZLActivity;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.pupop.SharePopupWindow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * 专栏未订阅Fragment
 */

public class ZLAllAudioFragment extends CommonHttpFragment<CommonPresenter, CommonModel> implements CommonContract.View<NewMediaAndTextBean>  {


    @Bind(R.id.viewPager)
    ViewPager mViewPager;
    @Bind(R.id.tab_layout)
    TabLayout mTabLayout;
    @Bind(R.id.ntb)
    NormalTitleBar ntb;
    @Bind(R.id.iv_banner)
    ImageView ivBanner;
    @Bind(R.id.tv_count)
    TextView tvCount;
    @Bind(R.id.tv_sort)
    TextView tvSort;
    @Bind(R.id.fl_bg)
    FrameLayout flBg;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    private NewMediaAndTextBean.DataBean mDataBean;
    private int formType;
    private String subscriptionNum;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_zl_all_audio;
    }


    private Map<String,String > params;
    private String subscriptionId ;
    private boolean isOrder = true;
    @Override
    protected void initBundle(Bundle extras) {
        try {
            subscriptionNum  = extras.getString("subscriptionNum");
            subscriptionId  = extras.getString(AppConstant.ExtraKey.SUBSCRIPTION_ID);
            formType = extras.getInt(AppConstant.ExtraKey.FROM_TYPE,2);
        } catch (Exception e) {
            subscriptionId = "";
            e.printStackTrace();
        } finally {
            params  = MapUtils.getOnlyCan("subscriptionId",subscriptionId);
        }
    }


    @Override
    protected void initView() {
        super.initView();
        ntb.setOnRightImagListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          share();
            }
        });
          ntb.setBackGroundColor(R.color.transparent);
          ntb.setOnivBackImagListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent intent = new Intent();
                  getActivity().setResult(2, intent);
                  finish();
              }
          });
        mRxManager.on("NewMediaAndTextBean", new Action1<NewMediaAndTextBean>() {
            @Override
            public void call(NewMediaAndTextBean mediaAndTextBean) {
                initAllView(mediaAndTextBean);
            }

        });
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new ZLAllAudioTwoFragment());
        mViewPager.setAdapter(new BaseFragmentAdapter(getChildFragmentManager(), fragments));
        ViewUtil.initViewPager(mViewPager, mTabLayout);
        String[] array = getResources().getStringArray(R.array.zl_audio);
        for (String s : array) {
            mTabLayout.addTab(mTabLayout.newTab().setText(s));
        }
        mTabLayout.setVisibility(View.GONE);
        flBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(getContext(), NoBuyZLActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString(AppConstant.ExtraKey.SUBSCRIPTION_ID, subscriptionId);
                    bundle.putInt(AppConstant.ExtraKey.FROM_TYPE, formType);
                     bundle.putInt("back", 1);
                    intent.putExtras(bundle);
                    startActivity(intent);
            }
        });
    }


    public void share() {
            if (mDataBean == null) return;
            SharePopupWindow.showPopup(getContext(), mSharePopupWindow, mDataBean);
    }
   SharePopupWindow mSharePopupWindow;
    private void initAllView(NewMediaAndTextBean mediaAndTextBean) {

        NewMediaAndTextBean.DataBean data = mediaAndTextBean.getData();
        if(data!=null){
            mDataBean=data;
            tvCount.setText("已更新"+subscriptionNum+"讲");
            progressBar.setProgress(mDataBean.getTotalProgress());
            Glide.with(getContext()).load(mDataBean.getPic()).error(R.mipmap.ic_jyg_share_music_norm).into(ivBanner);
        }

    }


    @Override
    protected void doLoading() {
        if (isOrder){ // 1为正序，0为倒序
            params.put("order","1");
        }else{
            params.put("order","0");
        }
        mPresenter.getData(ApiKey.SUBSCRIPTION_NATIVEINDEXV3,params,NewMediaAndTextBean.class);
    }
    @Override
    public void returnData(NewMediaAndTextBean result) {
        mRxManager.post("NewMediaAndTextBean",result);
    }


    @OnClick(R.id.tv_sort)
    public void sort(View view){
        if (isOrder){
            isOrder = false;
            tvSort.setText("正序");

        }else{
            isOrder = true;
            tvSort.setText("倒序");

        }
        //示例：setAdapter之前使用

        mRxManager.post("notify",isOrder);
    }


}
