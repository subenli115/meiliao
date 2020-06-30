package com.ziran.meiliao.ui.me.activity;


import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.ui.bean.UserBean;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 我的界面
 * Created by Administrator on 2017/1/4.
 */

public class YouthModelActivity extends BaseActivity {
    public final int REQUEST_CODE_B = 1;
    @Bind(R.id.tv_status)
    TextView tvStatus;
    @Bind(R.id.tv_title_name)
    TextView tvName;
    public static void startAction() {
        Activity activity = AppManager.getAppManager().currentActivity();
        Intent intent = new Intent(activity, YouthModelActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_youth_model;
    }

    @Override
    public void initPresenter() {

    }


    @Override
    public void initView() {
        update();
    }

    private void update() {

        UserBean.DataBean dataBean = MyAPP.getmUserBean();
        if(dataBean!=null){
            if(dataBean.getTeenagersIsOpen()==null||dataBean.getTeenagersIsOpen().equals("1")){
                //关闭状态
                tvName.setText("青少年模式未开启");
                tvStatus.setText("开启青少年模式");
            }else {
                tvName.setText("青少年模式已开启");
                tvStatus.setText("关闭青少年模式");
            }
        }
    }

    //点击监听
    @OnClick({R.id.tv_status})
    public void onClick(View view) {
//        if (!MyAPP.isLogin(mContext)) { //如果没有登录则跳转到登录界面
//            return;
//        }
        switch (view.getId()) {
            case R.id.tv_status:
                SetModePwdActivity.startAction("pwd",REQUEST_CODE_B);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_B: //返回的结果是来自于Activity B
                if (resultCode == Activity.RESULT_OK) {
                    update();
                }
            default:
                break;
        }
    }

}