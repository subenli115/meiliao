package com.ziran.meiliao.ui.me.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import com.ziran.meiliao.ui.bean.StringDataV2Bean;
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

public class SetRealPersonActivity extends BaseActivity {

    public static final int RequestCrop = 3;
    private static String cameraScalePath;
    @Bind(R.id.tv_qd)
    TextView tvQd;
    @Bind(R.id.et_name)
    ClearEditText etName;
    @Bind(R.id.et_id)
    ClearEditText etId;
    @Bind(R.id.ntb)
    NormalTitleBar ntb;
    @Bind(R.id.img)
    ImageView img;
    private String url;
    private boolean isReal;

    public static void startAction(int code) {
        Activity activity = AppManager.getAppManager().currentActivity();
        Intent intent = new Intent(activity, SetRealPersonActivity.class);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        activity.startActivityForResult(intent,code);
    }



    @Override
    public int getLayoutId() {
        return R.layout.ac_real_person;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
            if(getIntent()!=null){

            }
        if(MyAPP.getmUserBean().getIdCard()==null||MyAPP.getmUserBean().getIdCard().equals("")){
            isReal=false;
        }else {
            isReal=true;
            etName.setEnabled(false);
            etId.setEnabled(false);
            String id = MyAPP.getmUserBean().getIdCard()+"";
            if(id!=null&&id.length()>4){
                String maskNumber = id.substring(0,3)+"***********"+id.substring(id.length()-4);
                etName.setHint(MyAPP.getmUserBean().getRealName()+"");
                etId.setHint(maskNumber);
                tvQd.setBackgroundResource(R.drawable.normal_bg_bule);
            }
        }
    }

    //点击监听
    @OnClick({R.id.tv_qd,R.id.img})
    public void onClick(View view) {
//        if (!MyAPP.isLogin(mContext)) { //如果没有登录则跳转到登录界面
//            return;
//        }
        switch (view.getId()) {
            case R.id.tv_qd:
                setRealName();
                break;
            case R.id.img:
                ImgSelActivity.startActivity(this,ImgSelConfig.DEFAULT_SIGN, ImgSelConfig.RequestCode);
                break;

        }
    }



    private void setRealName() {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("id", MyAPP.getUserId());
        etName.setHint(MyAPP.getmUserBean().getRealName()+"");
        if(isReal){
            defMap.put("idCard",MyAPP.getmUserBean().getIdCard()+"");
            defMap.put("realName",MyAPP.getmUserBean().getRealName());
        }else {
            defMap.put("idCard",etId.getText().toString());
            defMap.put("realName",etName.getText().toString());
        }
        defMap.put("autoType","2");
        defMap.put("realImg",url);
        OkHttpClientManager.putAsyncAddHead(ApiKey.ADMIN_USER_AUTHENTICATION, defMap, new
                NewRequestCallBack<StringDataBean>(StringDataBean.class) {
                    @Override
                    public void onSuccess(StringDataBean result) {
                        UserBean.DataBean dataBean = MyAPP.getmUserBean();
                        dataBean.setRealName(etName.getText().toString());
                        dataBean.setIdCard(etId.getText().toString());
                        dataBean.setIsReal("1");
                        MyAPP.setmUserBean(dataBean);
                        Intent intent = new Intent();
                        MyAPP.getmUserBean().setRealImg(url);
                        setResult(Activity.RESULT_OK, intent);
                        // RESULT_OK就是一个默认值，=-1，它说OK就OK吧
                        SetRealPersonResultActivity.startAction();
                    }
                    @Override
                    public void onError(String msg, int code) {
                        ToastUitl.showShort(msg);
                    }
                });
    }
    private void upload() {
        OkHttpClientManager.postContentAndFiles(ApiKey.ADMIN_FILE_UPLOAD, MapUtils.getDefMap(true), FileUtil.str2File(cameraScalePath), new
                NewRequestCallBack<UpdateUserNewHeadBean>(UpdateUserNewHeadBean.class) {

                    @Override
                    public void onSuccess(UpdateUserNewHeadBean result) {
                        url = result.getData().getUrl();
                        AutoLinearLayout.LayoutParams params = (AutoLinearLayout.LayoutParams) img.getLayoutParams();
                        params.width= 380;
                        params.height=380;
                        img.setLayoutParams(params);
                        Glide.with(mContext).load(url).into(img);
                    }

                    @Override
                    public void onError(String msg, int code) {
                        ToastUitl.showShort(msg);
                    }
                });
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
