package com.ziran.meiliao.ui.me.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.citypicker.citylist.widget.ClearEditText;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;
import com.zhy.autolayout.AutoLinearLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.StringDataBean;
import com.ziran.meiliao.ui.bean.UpdateUserNewHeadBean;
import com.ziran.meiliao.ui.bean.UserBean;
import com.ziran.meiliao.utils.MapUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by ${chenyn} on 2017/5/7.
 */

public class SetRealPersonResultActivity extends BaseActivity {

    public static final int RequestCrop = 3;
    private static String cameraScalePath;

    @Bind(R.id.tv_info)
    TextView tvInfo;
    @Bind(R.id.ntb)
    NormalTitleBar ntb;
    @Bind(R.id.img)
    ImageView img;
    private String url;
    private String realName;

    public static void startAction() {
        Activity activity = AppManager.getAppManager().currentActivity();
        Intent intent = new Intent(activity, SetRealPersonResultActivity.class);
        activity.startActivity(intent);
    }



    @Override
    public int getLayoutId() {
        return R.layout.ac_real_person_result;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        if(MyAPP.getmUserBean().getIdCard()==null||MyAPP.getmUserBean().getIdCard().equals("")){

        }else {
            String id = MyAPP.getmUserBean().getIdCard()+"";
            if(id!=null&&id.length()>4){
                String maskNumber = id.substring(0,3)+"***********"+id.substring(id.length()-4);
                 realName = MyAPP.getmUserBean().getRealName();
                if(realName.length()==2){
                    realName=realName.substring(0,1)+"*";
                }else {
                    realName=realName.substring(0,1)+"*"+realName.substring(realName.length()-1);
                }
                tvInfo.setText(realName+"      "+maskNumber);

            }
            Glide.with(mContext).load(MyAPP.getmUserBean().getRealImg()).into(img);
        }
    }

    //点击监听
    @OnClick({})
    public void onClick(View view) {
//        if (!MyAPP.isLogin(mContext)) { //如果没有登录则跳转到登录界面
//            return;
//        }
        switch (view.getId()) {

        }
    }




}
