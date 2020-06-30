package com.ziran.meiliao.ui.me.activity;


import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.zhy.autolayout.AutoLinearLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.StringDataV2Bean;
import com.ziran.meiliao.ui.bean.UserBean;
import com.ziran.meiliao.utils.MapUtils;

import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 我的界面
 * Created by Administrator on 2017/1/4.
 */

public class SetModePwdActivity extends BaseActivity {

    @Bind(R.id.tv_status)
    TextView tvStatus;
    @Bind(R.id.ed_pwd)
    EditText edPwd;
    @Bind(R.id.tv_pwd)
    TextView tvPwd;
    @Bind(R.id.ed_pwd_two)
    EditText edPwdTwo;
    @Bind(R.id.all_Bg)
    AutoLinearLayout allBg;
    private String teenagersIsOpen;
    private View contentView;
    private UserBean.DataBean dataBean;

    public static void startAction(String type,int code) {
        Activity activity = AppManager.getAppManager().currentActivity();
        Intent intent = new Intent(activity, SetModePwdActivity.class);
        activity.startActivityForResult(intent,code);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_model_pwd;
    }

    @Override
    public void initPresenter() {

    }


    @Override
    public void initView() {
         dataBean = MyAPP.getmUserBean();
         teenagersIsOpen = dataBean.getTeenagersIsOpen();
        if(teenagersIsOpen==null||teenagersIsOpen.equals("1")){
            //关闭状态

        }else {
            tvPwd.setVisibility(View.VISIBLE);
            tvStatus.setText("关闭青少年模式");
            edPwd.setVisibility(View.INVISIBLE);
            edPwdTwo.setHint("请输入独立密码");

        }

    }

    //点击监听
    @OnClick({R.id.tv_pwd,R.id.tv_status})
    public void onClick(View view) {
//        if (!MyAPP.isLogin(mContext)) { //如果没有登录则跳转到登录界面
//            return;
//        }
        switch (view.getId()) {
            case R.id.tv_pwd:
                showPopWindow();
                break;
            case R.id.tv_status:
                    setStatus();
                break;


        }
    }

    private void setStatus() {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("userId", MyAPP.getUserId());
        if(teenagersIsOpen!=null&&teenagersIsOpen.equals("1")){
            if(edPwd.getText().toString().equals(edPwdTwo.getText().toString())){
                defMap.put("password",edPwd.getText().toString());
                defMap.put("status","0");
            }else {
                ToastUitl.showShort("两次密码不一致");
                return;
            }
        }else {
            defMap.put("password",edPwdTwo.getText().toString());
            defMap.put("status","1");
        }
        OkHttpClientManager.putAsyncAddHead(ApiKey.ADMIN_TEENAGERS_EDIT, defMap, new
                NewRequestCallBack<StringDataV2Bean>(StringDataV2Bean.class) {
                    @Override
                    public void onSuccess(StringDataV2Bean result) {
                        if(teenagersIsOpen!=null&&teenagersIsOpen.equals("1")){
                            dataBean.setTeenagersIsOpen("0");
                        }else {
                            dataBean.setTeenagersIsOpen("1");
                        }
                        MyAPP.setmUserBean(dataBean);
                        ToastUitl.showShort("修改成功");
                        Intent intent = new Intent();
                        setResult(Activity.RESULT_OK, intent);
                        // RESULT_OK就是一个默认值，=-1，它说OK就OK吧
                        finish();

                    }

                    @Override
                    public void onError(String msg, int code) {
                        ToastUitl.showShort("修改失败");
                    }
                });

    }
    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = (this).getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        allBg.setAlpha(bgAlpha);
    }

    private void showPopWindow() {
        // 一个自定义的布局，作为显示的内容
        int[] location = new int[2];
        contentView = LayoutInflater.from(getBaseContext()).inflate(R.layout.pop_find_pwd, null);
        contentView.getLocationOnScreen(location);
        final PopupWindow popupWindow = new PopupWindow(contentView,
                AutoLinearLayout.LayoutParams.WRAP_CONTENT, AutoLinearLayout.LayoutParams.WRAP_CONTENT, true);

        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);// 设置同意在外点击消失
        popupWindow.setFocusable(true);// 点击空白处时，隐藏掉pop窗口
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.showAtLocation(allBg, Gravity.CENTER, 0, 0);
        TextView qd = contentView.findViewById(R.id.tv_qd);
        setBackgroundAlpha(0.5f);
        qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();

            }
        });
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        setBackgroundAlpha(1.0f);
                    }
                });

    }
}