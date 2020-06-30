package com.ziran.meiliao.ui.me.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.citypicker.citylist.widget.ClearEditText;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.StringDataBean;
import com.ziran.meiliao.ui.bean.StringDataV2Bean;
import com.ziran.meiliao.ui.bean.UserBean;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.BaseItemView;

import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by ${chenyn} on 2017/5/7.
 */

public class SetRealNameActivity extends BaseActivity {

    @Bind(R.id.tv_id)
    TextView tvId;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_qd)
    TextView tvQd;
    @Bind(R.id.tv_hint)
    TextView tvHint;
    @Bind(R.id.et_name)
    ClearEditText etName;
    @Bind(R.id.et_id)
    ClearEditText etId;
    @Bind(R.id.ntb)
    NormalTitleBar ntb;
    private String type;
    private String ids;
    private String mid;

    public static void startAction(String type,int code) {
        Activity activity = AppManager.getAppManager().currentActivity();
        Intent intent = new Intent(activity, SetRealNameActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("type",type);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent,code);
    }
    public static void startAction(String type,int code,String name,String id,String mid) {
        Activity activity = AppManager.getAppManager().currentActivity();
        Intent intent = new Intent(activity, SetRealNameActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("type",type);
        bundle.putString("name",name);
        bundle.putString("id",id);
        bundle.putString("mid",mid);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent,code);
    }



    @Override
    public int getLayoutId() {
        return R.layout.ac_user_realname;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
            if(getIntent()!=null){
                 type = getIntent().getStringExtra("type");
                 ids = getIntent().getStringExtra("id");
                mid = getIntent().getStringExtra("mid");
               String name = getIntent().getStringExtra("name");
                if(type.equals("ThirdPay")){
                    tvHint.setText("*支付宝账号必须为本人账户，否则无法提现");
                    ntb.setTitleText("支付宝账号");
                    tvId.setText("支付宝账号");
                    etId.setText(ids);
                    tvQd.setText("修改");
                    if(MyAPP.getmUserBean().getRealName()!=null){
                        etName.setHint(MyAPP.getmUserBean().getRealName()+"");
                    }
                    etName.setEnabled(false);
                }else {
                    ntb.setTitleText("身份认证");
                    if(MyAPP.getmUserBean().getIdCard()==null||MyAPP.getmUserBean().getIdCard().equals("")){
                    }else {
                        etName.setEnabled(false);
                        etId.setEnabled(false);
                        tvQd.setText("已认证");
                        tvQd.setEnabled(false);
                        String id = MyAPP.getmUserBean().getIdCard()+"";
                        if(id!=null&&id.length()>4){
                            String maskNumber = id.substring(0,3)+"***********"+id.substring(id.length()-4,id.length());
                            tvQd.setBackgroundResource(R.drawable.normal_bg_green_real);
                            etName.setHint(MyAPP.getmUserBean().getRealName()+"");
                            etId.setHint(maskNumber);
                        }
                    }
                }
            }





    }

    //点击监听
    @OnClick({R.id.tv_qd})
    public void onClick(View view) {
//        if (!MyAPP.isLogin(mContext)) { //如果没有登录则跳转到登录界面
//            return;
//        }
        switch (view.getId()) {
            case R.id.tv_qd:
                if(type.equals("ThirdPay")){
                    if(ids!=null&&ids.length()>0){
                        setEditAccount();
                    }else {
                        setBindAccount();
                    }
                }else {
                    setRealName();
                }
                break;

        }
    }

    private void setRealName() {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("id", MyAPP.getUserId());
        defMap.put("idCard",etId.getText().toString());
        defMap.put("realName",etName.getText().toString());
        OkHttpClientManager.putAsyncAddHead(ApiKey.ADMIN_USER_AUTHENTICATION, defMap, new
                NewRequestCallBack<StringDataBean>(StringDataBean.class) {
                    @Override
                    public void onSuccess(StringDataBean result) {
                        ToastUitl.showShort(result.getResultMsg());
                        UserBean.DataBean dataBean = MyAPP.getmUserBean();
                        dataBean.setRealName(etName.getText().toString());
                        dataBean.setIdCard(etId.getText().toString());
                        MyAPP.setmUserBean(dataBean);
                        Intent intent = new Intent();
                        setResult(Activity.RESULT_OK, intent);
                        // RESULT_OK就是一个默认值，=-1，它说OK就OK吧
                        finish();
                    }
                    @Override
                    public void onError(String msg, int code) {
                        ToastUitl.showShort(msg);
                    }
                });
    }

    private void setBindAccount() {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("type", "1");
        defMap.put("name",etId.getText().toString());
        defMap.put("realName",MyAPP.getmUserBean().getRealName()+"");
        defMap.put("userId",MyAPP.getUserId());
        OkHttpClientManager.postAsyncAddHead(ApiKey.ACCOUNT_EXTERNAL_BINDING, defMap,"", new
                NewRequestCallBack<StringDataV2Bean>(StringDataV2Bean.class) {
                    @Override
                    public void onSuccess(StringDataV2Bean result) {
                        Intent intent = new Intent();
                        setResult(Activity.RESULT_OK, intent);
                        // RESULT_OK就是一个默认值，=-1，它说OK就OK吧
                        finish();
                    }
                    @Override
                    public void onError(String msg, int code) {
                        ToastUitl.showShort(msg);
                    }
                });
    }

    private void setEditAccount() {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("type", "1");
        defMap.put("id",mid);
        defMap.put("name",etId.getText().toString());
        defMap.put("realName",MyAPP.getmUserBean().getRealName()+"");
        defMap.put("userId",MyAPP.getUserId());
        OkHttpClientManager.putAsyncAddHead(ApiKey.ACCOUNT_EXTERNAL_UPDATEBYUSERID, defMap, new
                NewRequestCallBack<StringDataV2Bean>(StringDataV2Bean.class) {
                    @Override
                    public void onSuccess(StringDataV2Bean result) {

                        Intent intent = new Intent();
                        setResult(Activity.RESULT_OK, intent);
                        // RESULT_OK就是一个默认值，=-1，它说OK就OK吧
                        finish();
                        ToastUitl.showShort("修改成功");
                    }
                    @Override
                    public void onError(String msg, int code) {
                        ToastUitl.showShort(msg);
                    }
                });
    }





}
