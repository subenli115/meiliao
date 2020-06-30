package com.ziran.meiliao.ui.settings.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.citypicker.citylist.widget.ClearEditText;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.entry.UserRegBean;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.CheckNameBean;
import com.ziran.meiliao.utils.ExitUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.SoftKeyBroadManager;

import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

import static android.support.v4.content.PermissionChecker.PERMISSION_GRANTED;

/**
 * 完善资料
 * Created by Administrator on 2017/1/14.
 */

public class InputUserInfoActivity extends BaseActivity {


    @Bind(R.id.ntb)
    NormalTitleBar ntb;
    @Bind(R.id.ed_nick)
    ClearEditText edNick;
    @Bind(R.id.tv_region)
    TextView tvRegion;
    @Bind(R.id.ed_age)
    EditText edAge;
    @Bind(R.id.tv_next)
    TextView tvNext;
    @Bind(R.id.tv_hint)
    TextView tvHint;
    @Bind(R.id.arl)
    AutoRelativeLayout arl;
    String hotCity[]={"131","289","332","132"};
    private static final int BAIDU_READ_PHONE_STATE = 100;//定位权限请求
    private static final int REQUEST_CONTACTS = 1000;
    private static final int PRIVATE_CODE = 1315;//开启GPS权限

    private static final String[] PERMISSIONS_CONTACT = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE};
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    private LocationManager lm;
    private String region;
    private String age;
    private String nickName;
    private SoftKeyBroadManager softKeyBroadManager;
    private View contentView;
    private PopupWindow popupWindow;
    private boolean ok;
    private boolean flag;
    private boolean isShow;


    public static void startAction(Context context) {
        Intent intent = new Intent(context, InputUserInfoActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_input_info_one;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        //注册监听函数
        initLocation();
        edNick.addTextChangedListener(textWatcher);
        edAge.addTextChangedListener(textWatcher);
        Map<String, String> defMap = MapUtils.getDefMap(false);
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
        OkHttpClientManager.getAsyncHead(ApiKey.ADMIN_USER_EXISTENCE,edNick.getText().toString(), MyAPP.getAccessToken(),new
                NewRequestCallBack<CheckNameBean>(CheckNameBean.class) {
                    @Override
                    public void onSuccess(CheckNameBean result) {
                        tvHint.setVisibility(View.VISIBLE);
                        if(!result.getData().isSuccess()){
                            //不存在
                            tvHint.setText("*昵称可用");
                            tvHint.setTextColor(Color.parseColor("#80C269"));
                        }else {
                            tvHint.setText("*该昵称已被使用");
                            tvHint.setTextColor(Color.parseColor("#FF4F68"));
                        }
                        updateButton();
                    }
                    @Override
                    public void onError(String msg, int code) {
                        tvHint.setText("*昵称不合法");
                        tvHint.setTextColor(Color.parseColor("#FF4F68"));
                        super.onError(msg, code);
                    }
                });
        // 失去焦点
    }
    private void showContacts() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, PERMISSIONS_CONTACT, REQUEST_CONTACTS);
        } else {
            showGPSContacts();
        }
    }

    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取地址相关的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明
            String addr = location.getAddrStr();    //获取详细地址信息
            String country = location.getCountry();    //获取国家
            String province = location.getProvince();    //获取省份
            String city = location.getCity();    //获取城市
            String district = location.getDistrict();    //获取区县
            String street = location.getStreet();    //获取街道信息
            String adcode = location.getAdCode();    //获取adcode
            String town = location.getTown();    //获取乡镇信息
            if(!province.equals(city)){
                tvRegion.setText(province+city);
            }else {
                tvRegion.setText(city);
            }
            region= province+"-"+city;
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
    @OnClick({R.id.tv_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_next:

                UserRegBean userInfoBean = new UserRegBean();
                userInfoBean.setRegion(region);
                userInfoBean.setAge(age);
                userInfoBean.setNickname(nickName);
                SelectSexActivity.startAction(mContext, userInfoBean);
                break;
        }

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
            nickName = edNick.getText().toString();
            updateButton();
        }
    };

    private void updateButton() {
        if (age.length() > 0 && nickName.length()>=2 && !region.equals("地区")&&tvHint.getText().toString().equals("*昵称可用")) {
            tvNext.setBackgroundResource(R.drawable.normal_bg_bule);
            tvNext.setEnabled(true);
        } else {
            tvNext.setBackgroundResource(R.drawable.normal_bg_bule50);
            tvNext.setEnabled(false);
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
        Log.e("onActivityResult",""+requestCode);
        switch (requestCode) {
            case PRIVATE_CODE:
//                showContacts();
                popupWindow.dismiss();
                popupWindow=null;
                showGPSContacts();
                break;

        }
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

}
