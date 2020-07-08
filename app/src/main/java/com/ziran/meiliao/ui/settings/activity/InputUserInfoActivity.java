package com.ziran.meiliao.ui.settings.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.bumptech.glide.Glide;
import com.citypicker.citylist.widget.ClearEditText;
import com.umeng.analytics.AnalyticsConfig;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MeiliaoConfig;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonwidget.LoadingDialog;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.entry.UserRegBean;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.im.activity.MainActivity;
import com.ziran.meiliao.im.database.UserEntry;
import com.ziran.meiliao.im.utils.SharePreferenceManager;
import com.ziran.meiliao.im.utils.ThreadUtil;
import com.ziran.meiliao.ui.bean.CheckNameBean;
import com.ziran.meiliao.ui.bean.UpdateUserNewHeadBean;
import com.ziran.meiliao.ui.bean.UserBean;
import com.ziran.meiliao.utils.DeviceUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.GlideCircleTransform;
import com.ziran.meiliao.widget.SoftKeyBroadManager;
import com.ziran.meiliao.widget.pupop.PopupWindowUtil;
import com.ziran.meiliao.widget.pupop.UpdatePopupWindow;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;

import static androidx.core.content.PermissionChecker.PERMISSION_GRANTED;

/**
 * 完善资料
 * Created by Administrator on 2017/1/14.
 */

public class InputUserInfoActivity extends BaseActivity {

    public static final int RequestCrop = 3;
    private static String cameraScalePath="";
    @Bind(R.id.ntb)
    NormalTitleBar ntb;
    @Bind(R.id.ed_nick)
    ClearEditText edNick;
    @Bind(R.id.ed_age)
    EditText edAge;
    @Bind(R.id.tv_next)
    TextView tvNext;
    @Bind(R.id.arl)
    AutoRelativeLayout arl;
    @Bind(R.id.all_red)
    AutoLinearLayout allRed;
    @Bind(R.id.arl_woman)
    AutoRelativeLayout arlWoman;
    @Bind(R.id.arl_man)
    AutoRelativeLayout arlMan;
    @Bind(R.id.iv_user_avatar)
    ImageView iv_user_avatar;
    @Bind(R.id.tv_woman)
    TextView tvWoman;
    @Bind(R.id.tv_man)
    TextView tvMan;
    @Bind(R.id.iv_sex_woman)
    ImageView ivWoman;
    @Bind(R.id.iv_sex_man)
    ImageView ivMan;
    private static final int BAIDU_READ_PHONE_STATE = 100;//定位权限请求
    private static final int REQUEST_CONTACTS = 1000;
    private static final int PRIVATE_CODE = 1315;//开启GPS权限

    private static final String[] PERMISSIONS_CONTACT = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE};
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    private LocationManager lm;
    private String region;
    private String age="";
    private String nickName="";
    private SoftKeyBroadManager softKeyBroadManager;
    private View contentView;
    private PopupWindow popupWindow;
    private boolean ok;
    private boolean flag;
    private boolean isShow;
    private UserInfo mMyInfo;
    private UserRegBean userBean;
    private double longitude;
    private double latitude;
    private UpdatePopupWindow pop;
    private String sex="";

    public static void startAction(Context context) {
        Intent intent = new Intent(context, InputUserInfoActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_input_info;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        userBean = new UserRegBean();
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        //注册监听函数
        initLocation();
        pop = new UpdatePopupWindow(mContext, this);
        edNick.addTextChangedListener(textWatcher);
        edAge.addTextChangedListener(textWatcher);
        edNick.setOnFocusChangeListener(new ClearEditText.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                edNick.change(hasFocus);
                if (hasFocus) {
                    // 获得焦点
                } else {
                    checkNick();
                }
            }

        });
        softKeyBroadManager = new SoftKeyBroadManager(arl);
        softKeyBroadManager.addSoftKeyboardStateListener(softKeyboardStateListener);

    }
    private void checkNick() {
        if(edNick.getText().toString().equals("")){
            allRed.setVisibility(View.VISIBLE);
            return;
        }
        OkHttpClientManager.getAsyncHead(ApiKey.ADMIN_USER_EXISTENCE,edNick.getText().toString(), MyAPP.getAccessToken(),new
                NewRequestCallBack<CheckNameBean>(CheckNameBean.class) {
                    @Override
                    public void onSuccess(CheckNameBean result) {
                        if(!result.getData().isSuccess()){
                            //不存在
                            Drawable right1 = getResources().getDrawable(com.citypicker.R.drawable.ic_code_right);
                            edNick.setCompoundDrawablesWithIntrinsicBounds(null, null, right1, null);
                            allRed.setVisibility(View.INVISIBLE);
                        }else {
                            ToastUitl.showShort(result.getResultMsg());
                            edNick.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                            allRed.setVisibility(View.VISIBLE);
                        }
                        updateButton();
                    }
                    @Override
                    public void onError(String msg, int code) {
                        allRed.setVisibility(View.VISIBLE);
                        super.onError(msg, code);
                    }
                });
        // 失去焦点
    }

    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取地址相关的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明
            String province = location.getProvince();    //获取省份
            String city = location.getCity();    //获取城市

            latitude = location.getLatitude();
             longitude = location.getLongitude();
            region= province+"-"+city;
            userBean.setRegion(region);
            mLocationClient.stop();
        }
    }
    private SoftKeyBroadManager.SoftKeyboardStateListener softKeyboardStateListener = new
            SoftKeyBroadManager.SoftKeyboardStateListener() {


                @Override
                public void onSoftKeyboardOpened(int keyboardHeightInPx) {

                }

                @Override
                public void onSoftKeyboardClosed() {
                    checkNick();
                }
            };

    //点击监听
    @OnClick({R.id.arl_woman,R.id.arl_man,R.id.iv_user_avatar,R.id.tv_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.arl_woman:
                ivWoman.setImageResource(R.mipmap.icon_sex_woman_select);
                ivMan.setImageResource(R.mipmap.icon_sex_man);
                sex="2";
                userBean.setSex(sex);
                tvMan.setTextColor(Color.parseColor("#BFBFBF"));
                tvWoman.setTextColor(Color.parseColor("#FF5773"));
                view.setBackgroundResource(R.drawable.normal_bg_red_sex_tran);
                arlMan.setBackgroundResource(R.drawable.normal_bg_tran);
                if(!flag){
                    showPopWindow1();
                    flag=true;
                }
                break;
            case R.id.arl_man:
                ivMan.setImageResource(R.mipmap.icon_sex_man_select);
                sex="1";
                userBean.setSex(sex);
                tvMan.setTextColor(Color.parseColor("#459BFF"));
                tvWoman.setTextColor(Color.parseColor("#BFBFBF"));
                ivWoman.setImageResource(R.mipmap.icon_sex_woman);
                view.setBackgroundResource(R.drawable.normal_bg_bule_sex_tran);
                arlWoman.setBackgroundResource(R.drawable.normal_bg_tran);
                if(!flag){
                    showPopWindow1();
                    flag=true;
                }
                break;
            case R.id.iv_user_avatar:
                pop.setTitle("从相册中选择",true);
                PopupWindowUtil.show(this, pop);
                break;
            case R.id.tv_next:
                if(cameraScalePath.equals("")){
                    ToastUitl.showShort("请上传头像");
                    return;
                }else if(nickName.length()<=2 ){
                    ToastUitl.showShort("请填写正确昵称");
                    return;
                }else if(age.equals("") ){
                    ToastUitl.showShort("请填写年龄");
                    return;
                }
                registerIm();
                break;


        }
    }

    private void registerIm() {
        JMessageClient.logout();
        startProgressDialog("正在上传资料..");
        JMessageClient.register(MyAPP.getUserId(), MeiliaoConfig.IMUserPwd, new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                if (i == 0) {
                    login(mContext);
                    SharePreferenceManager.setRegisterName(MyAPP.getUserId());
                    SharePreferenceManager.setRegistePass(MeiliaoConfig.IMUserPwd);
                } else {
                    Log.e("registerIm",""+i);
                    //注册失败 登录 登录失败
                    if (i == 898001) {
                        //已注册 登录
                        login(mContext);
                    }
                }
            }
        });

    }
    public void login(Context mContext){
        JMessageClient.login(MyAPP.getUserId(), MeiliaoConfig.IMUserPwd, new BasicCallback() {
            @Override
            public void gotResult(int responseCode, String responseMessage) {
//            dialog.dismiss();
                if (responseCode == 0) {
                    SharePreferenceManager.setCachedPsw(MeiliaoConfig.IMUserPwd);
                    UserInfo myInfo = JMessageClient.getMyInfo();
                    File avatarFile = myInfo.getAvatarFile();
                    //登陆成功,如果用户有头像就把头像存起来,没有就设置null
                    if (avatarFile != null) {
                        SharePreferenceManager.setCachedAvatarPath(avatarFile.getAbsolutePath());
                    } else {
                        SharePreferenceManager.setCachedAvatarPath(null);
                    }
                    String username = myInfo.getUserName();
                    String appKey = myInfo.getAppKey();
                    UserEntry user = UserEntry.getUser(username, appKey);
                    if (null == user) {
                        user = new UserEntry(username, appKey);
                        user.save();
                    }
                    uploadImHeadIv();

                }
            }
        });
    }

    private void uploadImHeadIv() {
        imHeadUpload();
        upload();
    }

    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span=1000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认false，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }

    /**
     * 检测GPS、位置权限是否开启
     */
    public void showGPSContacts() {
        lm = (LocationManager) this.getSystemService(this.LOCATION_SERVICE);
        ok = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (ok) {//开了定位服务
            if (Build.VERSION.SDK_INT >= 23) { //判断是否为android6.0系统版本，如果是，需要动态添加权限
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PERMISSION_GRANTED) {// 没有权限，申请权限。
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            BAIDU_READ_PHONE_STATE);
                } else {
                    mLocationClient.start();
                }
            } else {
                mLocationClient.start();
            }
        } else {
                showPopWindow();
        }
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            age = edAge.getText().toString();
            userBean.setAge(age);
            nickName = edNick.getText().toString();
            userBean.setNickname(nickName);
            updateButton();
        }
    };

    private void updateButton() {
        if (age.length() > 0 && nickName.length()>=2 && allRed.getVisibility()==View.INVISIBLE&& !sex.equals("")&&cameraScalePath.length()>0) {
            tvNext.setBackgroundResource(R.drawable.normal_bg_bule);
        } else {
            tvNext.setBackgroundResource(R.drawable.normal_bg_bule50);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_CONTACTS) {
            if (grantResults.length > 0&&grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mLocationClient.start();
            } else {
                ActivityCompat.requestPermissions(this, PERMISSIONS_CONTACT, REQUEST_CONTACTS);
            }
        } else if(requestCode ==BAIDU_READ_PHONE_STATE){
            if (grantResults.length > 0&&grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if(mLocationClient!=null){
                    mLocationClient.start();
                }
            } else {
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ImgSelConfig.RequestCode && data != null) {
            final ArrayList<String> imgPaths = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
            startUCrop(this,imgPaths.get(0),RequestCrop);
        }else if(resultCode == RESULT_OK &&requestCode==RequestCrop){
            if(cameraScalePath.length()>0){
                updateUserHead();
                 }
            }
        switch (requestCode) {
            case PRIVATE_CODE:
                popupWindow.dismiss();
                popupWindow=null;
                showGPSContacts();
                break;

        }
    }

    private void updateUserHead() {
        updateButton();
        LoadingDialog.cancelDialogForLoading();
        Glide.with(mContext).load(cameraScalePath).transform(new GlideCircleTransform(mContext)).into(iv_user_avatar);
        if (TextUtils.isEmpty(MyAPP.getUserInfo().getHeadImg())) {
            MyAPP.getUserInfo().setHeadImg(cameraScalePath);
        }
        tvNext.setBackgroundResource(R.drawable.normal_bg_bule);
    }
    private void imHeadUpload() {
        ThreadUtil.runInThread(new Runnable() {
            @Override
            public void run() {
                JMessageClient.updateUserAvatar(new File(cameraScalePath), new BasicCallback() {
                    @Override
                    public void gotResult(int responseCode, String responseMessage) {
                        if (responseCode == 0) {
                            Log.e("imHeadUpload",responseCode+""+responseMessage);
                            SharePreferenceManager.setCachedAvatarPath(cameraScalePath);
                        }else {
                            Log.e("imHeadUpload",responseCode+""+responseMessage);

                        }
                    }
                });
                updateImUserInfo();
            }
        });
    }

    public void updateImUserInfo() {
        mMyInfo = JMessageClient.getMyInfo();
        if(mMyInfo!=null){
            mMyInfo.setNickname(userBean.getNickname());
            if (userBean.getSex().equals("1")) {
                mMyInfo.setGender(UserInfo.Gender.male);
            } else {
                mMyInfo.setGender(UserInfo.Gender.female);
            }

            JMessageClient.updateMyInfo(UserInfo.Field.all, mMyInfo, new BasicCallback() {
                @Override
                public void gotResult(int responseCode, String responseMessage) {
                    if (responseCode == 0) {
                    } else {
                        registerIm();
                        stopProgressDialog();
                    }
                }
            });
        }
    }
    private void upload() {
        OkHttpClientManager.postContentAndFiles(ApiKey.ADMIN_FILE_UPLOAD, MapUtils.getDefMap(true), FileUtil.str2File(cameraScalePath), new
                NewRequestCallBack<UpdateUserNewHeadBean>(UpdateUserNewHeadBean.class) {

                    @Override
                    public void onSuccess(UpdateUserNewHeadBean result) {
                        String url = result.getData().getUrl();
                        userBean.setAvatar(url);
                        InfoPerfect();
                    }

                    @Override
                    public void onError(String msg, int code) {
                        stopProgressDialog();
                        ToastUitl.showShort(msg);
                    }
                });
    }

    private void InfoPerfect() {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("age", userBean.getAge());
        defMap.put("avatar", userBean.getAvatar());
        defMap.put("sex", userBean.getSex());
        defMap.put("nickname", userBean.getNickname());
        defMap.put("region", userBean.getRegion());
        defMap.put("latitude", latitude + "");
        defMap.put("id", MyAPP.getUserId());
        defMap.put("longitude", longitude + "");
        defMap.put("channelNo", AnalyticsConfig.getChannel(this));
        defMap.put("versionNo", DeviceUtil.getVersionName(this));
        OkHttpClientManager.putAsyncAddHead(ApiKey.ADMIN_USER_INFOPERFECT, defMap, new
                NewRequestCallBack<UserBean>(UserBean.class) {
                    @Override
                    public void onSuccess(UserBean result) {
                        MyAPP.setmUserBean(result.getData());
                        MainActivity.startAction( "First");
                        finish();
                    }

                    @Override
                    public void onError(String msg, int code) {
                        stopProgressDialog();
                        ToastUitl.showShort(msg);
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
        options.setToolbarColor(ActivityCompat.getColor(activity, com.yuyh.library.imgsel.R.color.black));
        //设置状态栏颜色
        options.setStatusBarColor(ActivityCompat.getColor(activity, com.yuyh.library.imgsel.R.color.black));
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

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus&&!isShow) {
            showGPSContacts();
            isShow=true;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void showPopWindow() {
        // 一个自定义的布局，作为显示的内容
        int[] location = new int[2];
        contentView = LayoutInflater.from(mContext).inflate(R.layout.pop_open_location, null);
        contentView.getLocationOnScreen(location);
        popupWindow = new PopupWindow(contentView,
                AutoLinearLayout.LayoutParams.MATCH_PARENT, AutoLinearLayout.LayoutParams.MATCH_PARENT, true);

        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);// 设置同意在外点击消失
        popupWindow.setFocusable(true);// 点击空白处时，隐藏掉pop窗口
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.showAtLocation(arl, Gravity.CENTER, 0, 0);
        TextView qd = contentView.findViewById(R.id.tv_qd);
        qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivityForResult(intent, PRIVATE_CODE);

            }
        });
    }

    private void showPopWindow1() {

        // 一个自定义的布局，作为显示的内容
        int[] location = new int[2];
        contentView = LayoutInflater.from(mContext).inflate(R.layout.pop_register_sex, null);
        contentView.getLocationOnScreen(location);
        popupWindow = new PopupWindow(contentView,
                AutoLinearLayout.LayoutParams.MATCH_PARENT, AutoLinearLayout.LayoutParams.MATCH_PARENT, true);

        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);// 设置同意在外点击消失
        popupWindow.setFocusable(true);// 点击空白处时，隐藏掉pop窗口
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.showAtLocation(arl, Gravity.CENTER, 0, 0);
        TextView qd = contentView.findViewById(R.id.tv_qd);
        qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();

            }
        });
    }

}
