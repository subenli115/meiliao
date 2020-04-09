package com.ziran.meiliao.ui.main.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.citypicker.citylist.sortlistview.SortModel;
import com.citypicker.citylist.utils.CityDataDb;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.app.WpyxConfig;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonutils.DisplayUtil;
import com.ziran.meiliao.common.commonutils.JsonUtils;
import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.commonutils.SPUtils;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.common.security.AES;
import com.ziran.meiliao.common.security.EncodeUtil;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.entry.LoginBean;
import com.ziran.meiliao.envet.ThreeLoginCallBack;
import com.ziran.meiliao.ui.bean.CheckPhoneBean;
import com.ziran.meiliao.ui.bean.StringDataBean;
import com.ziran.meiliao.ui.bean.TagCheckBean;
import com.ziran.meiliao.ui.main.contract.LoginContract;
import com.ziran.meiliao.ui.main.model.LoginModel;
import com.ziran.meiliao.ui.main.presenter.LoginPresenter;
import com.ziran.meiliao.ui.settings.activity.BindPhoneActivity;
import com.ziran.meiliao.ui.settings.activity.InputPasswordActivity;
import com.ziran.meiliao.ui.settings.activity.RegisterActivity;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.PrefUtils;
import com.ziran.meiliao.widget.SmsCodeView;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.soexample.LoginApi;
import com.umeng.soexample.UserInfo;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/6/12.
 */

public class NewLoginActivity extends BaseActivity<LoginPresenter, LoginModel> implements RadioGroup.OnCheckedChangeListener, LoginContract.View{


    @Bind(R.id.all_wechat)
    AutoLinearLayout allWechat;
    @Bind(R.id.tv_86)
    TextView tv86;
    @Bind(R.id.tv_input_phone)
    EditText et_phone;
    @Bind(R.id.tv_register)
    TextView tvRegister;
    private NewLoginActivity loginActivity;
    private Dialog threeLogindialog;
    private SmsCodeView bindDialogSmsCodeView;
    private SortModel mCityItem;
    private Intent intent;
    private static NewLoginActivity Activity;
    private CheckPhoneBean mbean;
    private TagCheckBean mData;

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyAPP.setReqPerOk(false);
        UMShareAPI.get(this).release();
    }
    @Override
    public void initView() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        loginActivity = (NewLoginActivity) this;
        mCityItem = CityDataDb.getCountryZipCode(this);
        DisplayUtil.measureSoftKeyBoardHeight(this);
        intent = new Intent();
       Activity=this;
    }
    
    
    
    //点击监听
    @OnClick({R.id.tv_input_phone,R.id.iv_close,R.id.all_wechat,R.id.iv_submit,R.id.tv_86,R.id.tv_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.all_wechat:
                //微信登录
                    LoginApi.get().login(this, SHARE_MEDIA.WEIXIN, threeLoginCallBack);
                break;
            case R.id.iv_close:
                exit();
                break;
            case R.id.iv_submit:
                if(tv86.getText().toString().equals("+86")){
                    if(et_phone.getText().toString().length()!=11){
                        ToastUitl.showShort("手机号码不正确");
                        return;
                    }
                    String number = et_phone.getText().toString();
                    boolean judge = isMobile(number);
                    if (judge == true) {
                    } else {
                        ToastUitl.showShort("手机号码不合法");
                        return;
                    }
                }
                Map<String, String> defMap = MapUtils.getDefMap(false);
                defMap.put("mobile",et_phone.getText().toString());
                mPresenter.postCheckLoginPhone(defMap);
                break;
            case R.id.tv_86:
                startActivityForResult(new Intent(getBaseContext(), RegionActivity.class), 100);
                break;
            case R.id.tv_register:
                intent.setClass(this,RegisterActivity.class);
                startActivity(intent);
                break;

        }
    }


    private boolean isExit;
    //主线程处理视图，isExit默认为false，就是点击第一次时，弹出"再按一次退出程序"
    //点击第二次时关闭应用
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 点击两次退出程序
     */
    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            //参数用作状态码；根据惯例，非 0 的状态码表示异常终止。
            System.exit(0);
        }
    }
    /**
     * 验证手机格式
     */
    public static boolean isMobile(String number) {
    /*
    移动：134、135、136、137、138、139、150、151、152、157(TD)、158、159、178(新)、182、184、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、170、173、177、180、181、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        String num = "[1][34578]\\d{9}";//"[1]"代表第1位为数字1，"[34578]"代表第二位可以为3、4、5、7、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(number)) {
            return false;
        } else {
            //matches():字符串是否在给定的正则表达式匹配
            return number.matches(num);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    private String jsonData;
    private String platName;
    private ThreeLoginCallBack threeLoginCallBack = new ThreeLoginCallBack() {
        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            super.onComplete(share_media, i, map);
            UserInfo userInfo = UserInfo.parse(map, share_media.name());
            LogUtils.logd("userInfo" + userInfo.toString());
            String toJson = JsonUtils.toJson(userInfo);
            String utf = EncodeUtil.encodeUTF(toJson);
            jsonData = AES.get().encrypt(utf.getBytes());
            loginActivity.setPlatName(userInfo.getPlatName());
            loginActivity.setJsonData(jsonData);
            platName = userInfo.getPlatName();
            Map<String, String> partyLoginMap = MapUtils.getPartyLoginMap(share_media.name(), jsonData);
            //检测第三方登录是否绑定
            mPresenter.postPartyLoginData(partyLoginMap);
        }
    };




    @Override
    public void returnLoginData(LoginBean registerBean) {

    }


    @Override
    public void returnBindPhoneData(LoginBean registerBean) {
        if (EmptyUtils.isNotEmpty(registerBean)) {
            loginSuccess(registerBean);
        }
        if (threeLogindialog != null) {
            threeLogindialog.dismiss();
        }
    }

    @Override
    public void returnLoginCode(Result registerBean) {

    }

    @Override
    public void returnBindCode(StringDataBean registerBean) {

    }
    //登录成功跳转页面
    private void loginSuccess(LoginBean registerBean) {
        //保存token
        SPUtils.setString(AppConstant.SPKey.TOKEN, registerBean.getData().getAccessToken());
        MyAPP.setAccessToken(registerBean.getData().getAccessToken());
       WpyxConfig.setPhone(registerBean.getData().getUserInfo().getPhone());
        PrefUtils.putString("phone",registerBean.getData().getUserInfo().getPhone(),mContext);
        SPUtils.setString(AppConstant.SPKey.PHONE,registerBean.getData().getUserInfo().getPhone());
        com.ziran.meiliao.entry.UserInfo userInfo = registerBean.getData().getUserInfo();
        MyAPP.setmUserInfo(userInfo);
        if (!registerBean.getData().isPhoneRegister()) {
           BindPhoneActivity.startAction(this);
           return;
        }
        //跳转到主界面
        MainActivity.startAction(this, 1);
        if (!MyAPP.isLogout()) {
            mRxManager.post(AppConstant.RXTag.UPDATE_USER, "login");
        }
        mRxManager.post("exercise", "login");
        finish();
    }
    @Override
    public void returnPartyLogin(LoginBean loginBean) {
        if (loginBean.getData() == null) {
            ToastUitl.showShort(loginBean.getResultMsg());
            return;
        }
        //如果已经绑定则跳转到跳转到首页
        loginSuccess(loginBean);
    }


    @Override
    public void showLoginCheck(CheckPhoneBean bean) {
        if(bean.getData()!=null){
            intent = new Intent();
            intent.putExtra("phone",""+et_phone.getText().toString());
            intent.putExtra("CodeNumber",""+tv86.getText().toString());
            intent.setClass(this,InputPasswordActivity.class);
            startActivity(intent);
        }else{
            ToastUitl.showShort("手机号未注册");

        }
    }

    @Override
    public void showCheckSaveData(TagCheckBean data) {

    }

    public String getPlatName() {
        return platName;
    }

    public void setPlatName(String platName) {
        this.platName = platName;
    }

    public String getJsonData() {
        return jsonData;
    }

    public static void startAction(Context mContext) {
        if(mContext!=null){

            Intent intent = new Intent(mContext, NewLoginActivity.class);
            mContext.startActivity(intent);
        }else{

        }
    }
    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }
    //从区号选择页面返回数据处理
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (null != data) {
            SortModel cityData = data.getParcelableExtra("cityData");
            if (EmptyUtils.isNotEmpty(cityData)) {
               setAreaCode(cityData);

            } else {
                UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    public void setAreaCode(SortModel cityItem) {
        if (EmptyUtils.isEmpty(cityItem)) return;
            this.mCityItem = cityItem;
        if (EmptyUtils.isNotEmpty(mCityItem.getData())) {
            tv86.setText((mCityItem.getData()));
        }
        }
    @Override
    public void onBackPressed() {
        if (MyAPP.isLogout) {
//            MainActivity.startAction(this);
            finish();
        } else {
            super.onBackPressed();
        }
    }
    //第三方登录数据的封装
    public static class ThreeLoginBean {
        private String third;//0  or 1
        private String platName;
        private String jsonData;

        public String getThird() {
            return third;
        }

        public void setThird(String third) {
            this.third = third;
        }

        public String getPlatName() {
            return platName;
        }

        public void setPlatName(String platName) {
            this.platName = platName;
        }

        public String getJsonData() {
            return jsonData;
        }

        public void setJsonData(String jsonData) {
            this.jsonData = jsonData;
        }
    }
    }
