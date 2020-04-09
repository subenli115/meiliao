package com.ziran.meiliao.ui.settings.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;

import butterknife.Bind;
import butterknife.OnClick;

public class ChangePhoneActivity extends BaseActivity {

    @Bind(R.id.tv_change)
    TextView tvChange;
    @Bind(R.id.tv_phone)
    TextView tvPhone;
    @Bind(R.id.ntb)
    public NormalTitleBar ntb;
    private String phone;

    @Override
    public int getLayoutId() {
        return R.layout.ac_change_phone_start;
    }

    @Override
    public void initPresenter() {

    }
    public static void startAction(Context mContext, String phone) {
        Intent intent = new Intent(mContext, ChangePhoneActivity.class);
        intent.putExtra("phone",phone);
        mContext.startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        phone = MyAPP.getUserInfo().getPhone();
        if(phone!=null){

            phone = phone.substring(0, 3) + "****" + phone.substring(7, 11);
            tvPhone.setText(phone);
        }
    }

    @Override
    public void initView() {
        ntb.setNewTitleText("手机号");
    }
    //点击监听
    @OnClick({R.id.tv_change})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_change:
                 startActivity(ChangeInPutActivity.class);
                break;
        }
    }

}
