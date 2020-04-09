package com.ziran.meiliao.ui.settings.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;

import butterknife.Bind;

import static com.ziran.meiliao.ui.main.activity.NewLoginActivity.isMobile;

/**
 *
 * 换手机
 */

public class ChangeInPutActivity extends BaseActivity {
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.tv_next)
    TextView tvNext;
    private boolean isRight;

    @Bind(R.id.ntb)
    public NormalTitleBar ntb;

    @Override
    public int getLayoutId() {
        return R.layout.ac_intput_newphone;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
            ntb.setNewTitleText("手机号");
        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            ChangePhoneFinishActivity.startAction(mContext,etPhone.getText().toString());
            }
        });
        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(etPhone.getText().toString().length()!=11){
                    tvNext.setBackgroundResource(R.drawable.normal_bg_gray_c0);
                    isRight=false;
                }else {

                    isRight=true;
                }

                String number = etPhone.getText().toString();
                boolean judge = isMobile(number);
                if (judge == true) {
                    isRight=true;
                } else {
                    isRight=false;
                }

                if(isRight){
                    tvNext.setBackgroundResource(R.drawable.normal_bg_green);
                }else {
                    tvNext.setBackgroundResource(R.drawable.normal_bg_gray_c0);
                }
            }
        });
    }





}
