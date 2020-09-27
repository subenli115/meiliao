package com.ziran.meiliao.im.activity;

import com.bumptech.glide.Glide;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;
import com.ziran.meiliao.common.base.BaseActivity;



import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.UpdateUserNewHeadBean;
import com.ziran.meiliao.ui.bean.UserBean;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.pupop.PopupWindowUtil;
import com.ziran.meiliao.widget.pupop.UpdatePopupWindow;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 我的界面
 * Created by Administrator on 2017/1/4.
 */

public class RecommedPreviewActivity extends BaseActivity {
    private static String cameraScalePath;
    @Bind(R.id.card_image_view)
    ImageView ivBg;
    @Bind(R.id.card_user_name)
    TextView userNameTv;
    @Bind(R.id.iv_real_name)
    ImageView ivReal;
    @Bind(R.id.tv_sex)
    TextView userSexTv;
    @Bind(R.id.tv_age)
    TextView userAgeTv;
    @Bind(R.id.tv_region)
    TextView userRegionTv;
    @Bind(R.id.iv_chat)
    ImageView ivChat;
    private UpdatePopupWindow pop;
    public static final int RequestCrop = 3;

    public static void startAction() {
        Activity activity = AppManager.getAppManager().currentActivity();
        Intent intent = new Intent(activity, RecommedPreviewActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.ac_preview_recommed;
    }

    @Override
    public void initPresenter() {

    }


    @Override
    public void initView() {
        ivChat.setVisibility(View.GONE);
        pop = new UpdatePopupWindow(mContext, this);
        bindData(MyAPP.getmUserBean());
    }
    public void bindData(UserBean.DataBean itemData) {
        Glide.with(mContext).load(itemData.getRecommendImages()).into(ivBg);
        userNameTv.setText(itemData.getNickname());
        if (itemData.getIdCard() == null || itemData.getIdCard().equals("")) {
            ivReal.setVisibility(View.GONE);
        }
        userAgeTv.setText(itemData.getAge() + "");
        userRegionTv.setText(itemData.getRegion() + "");
        if (itemData.getSex() == 1) {
            userSexTv.setText("男");
        } else {
            userSexTv.setText("女");
        }
    }

    //点击监听
    @OnClick({R.id.card_top_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card_top_layout:
                pop.setTitle("更换形象照",false);
                PopupWindowUtil.show(this, pop);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ImgSelConfig.RequestCode && data != null) {
            final ArrayList<String> imgPaths = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
            startUCrop(this,imgPaths.get(0),RequestCrop);
        }else if(resultCode == RESULT_OK &&requestCode==RequestCrop){
            if(cameraScalePath.length()>0){
                upload();
            }
        }
    }

    private void upload() {
        OkHttpClientManager.postContentAndFiles(ApiKey.ADMIN_FILE_UPLOAD, MapUtils.getDefMap(true), FileUtil.str2File(cameraScalePath), new
                NewRequestCallBack<UpdateUserNewHeadBean>(UpdateUserNewHeadBean.class) {

                    @Override
                    public void onSuccess(UpdateUserNewHeadBean result) {
                        eidtHead(result);
                    }

                    @Override
                    public void onError(String msg, int code) {
                        ToastUitl.showShort(msg);
                    }
                });
    }
    private void eidtHead(UpdateUserNewHeadBean result) {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("id",MyAPP.getUserId());
        defMap.put("recommendImages",result.getData().getUrl());
        OkHttpClientManager.putAsyncAddHead(ApiKey.ADMIN_USER_UPDATE, defMap, new
                NewRequestCallBack<UserBean>(UserBean.class) {
                    @Override
                    public void onSuccess(UserBean result) {
                        MyAPP.setmUserBean(result.getData());
                        Glide.with(mContext).load(result.getData().getRecommendImages()).into(ivBg);
                        if (TextUtils.isEmpty(MyAPP.getUserInfo().getHeadImg())) {
                            MyAPP.getUserInfo().setHeadImg(result.getData().getAvatar());
                        }
                    }
                    @Override
                    public void onError(String msg, int code) {
                        ToastUitl.showShort("验证码错误");
                    }
                });
    }


    /**
     * 启动裁剪
     * @param activity 上下文
     * @param sourceFilePath 需要裁剪图片的绝对路径
     * @param requestCode 比如：UCrop.REQUEST_CROP
     * @return
     */
    public static String startUCrop(Activity activity, String sourceFilePath,
                                    int requestCode) {
        Uri sourceUri = Uri.fromFile(new File(sourceFilePath));
        File outDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        if (!outDir.exists()) {
            outDir.mkdirs();
        }
        File outFile = new File(outDir, System.currentTimeMillis() + ".jpg");
        //裁剪后图片的绝对路径
        cameraScalePath = outFile.getAbsolutePath();
        Uri destinationUri = Uri.fromFile(outFile);
        //初始化，第一个参数：需要裁剪的图片；第二个参数：裁剪后图片
        UCrop uCrop = UCrop.of(sourceUri, destinationUri);
        //初始化UCrop配置
        UCrop.Options options = new UCrop.Options();
        //设置裁剪图片可操作的手势
        options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL);
        //是否隐藏底部容器，默认显示
        options.setHideBottomControls(true);
        //设置toolbar颜色
        //是否能调整裁剪框
        options.setFreeStyleCropEnabled(true);
        //UCrop配置
        uCrop.withOptions(options);
        //设置裁剪图片的宽高比，比如16：9
        uCrop.withAspectRatio(1, 1);
        //uCrop.useSourceImageAspectRatio();
        //跳转裁剪页面
        uCrop.start(activity, requestCode);
        return cameraScalePath;
    }
}