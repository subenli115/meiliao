package com.ziran.meiliao.ui.settings.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.citypicker.citylist.sortlistview.SortModel;
import com.citypicker.citylist.utils.CityDataDb;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonutils.SPUtils;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonwidget.OnNoDoubleClickListener;
import com.ziran.meiliao.common.compressorutils.RegexUtils;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.BindPhoneBean;
import com.ziran.meiliao.ui.bean.StringDataBean;
import com.ziran.meiliao.ui.bean.TagCheckBean;
import com.ziran.meiliao.ui.bean.UserInfoBean;
import com.ziran.meiliao.ui.main.activity.LabelActivity;
import com.ziran.meiliao.ui.main.activity.MainActivity;
import com.ziran.meiliao.ui.main.activity.RegionActivity;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.SmsCodeView;

import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

import static com.ziran.meiliao.constant.ApiKey.USERINFO_TAGSAVECHECK;

/**
 * 绑定手机 on 2019/3/18.
 */

public class BindPhoneActivity extends CommonHttpActivity<CommonPresenter, CommonModel> implements CommonContract

        .View<TagCheckBean>{

    SortModel mCityItem;
    private Dialog threeLogindialog;
    private Map<String, String> codeMap;
    @Bind(R.id.smsCodeView)
    SmsCodeView bindDialogSmsCodeView;

    @Bind(R.id.tv_input_phone)
    EditText etPhone;
    @Bind(R.id.tv_bind)
    TextView tvBind;
    @Bind(R.id.tv_86)
    TextView tv_86;

    /**
     * 入口
     */
    public static void startAction(Context activity) {
        Intent intent = new Intent(activity, BindPhoneActivity.class);
        activity.startActivity(intent);
    }



    //点击监听
    @OnClick({R.id.tv_86,R.id.tv_bind})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_86:
                startActivityForResult(new Intent(getBaseContext(), RegionActivity.class), 100);
                break;
            case R.id.tv_bind:

                break;
        }
    }

    private void getSmsCode(Map<String, String> codeMap) {
        this.codeMap = codeMap;
        OkHttpClientManager.postAsync(ApiKey.SMS_GET_SMSCODE, codeMap, new NewRequestCallBack<StringDataBean>(StringDataBean.class) {
            @Override
            protected void onSuccess(StringDataBean result) {
                HandlerUtil.startDjs(bindDialogSmsCodeView.getTvSmsGetCode());
                tvBind.setBackgroundResource(R.drawable.normal_bg_green);
                tvBind.setEnabled(true);
            }
        });
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_bind_phone;
    }

    @Override
    public void initPresenter() {
        if (mModel != null && mPresenter != null) {
            mPresenter.setVM(this, mModel);
        }
    }

    @Override
    public void initView() {
        mCityItem = CityDataDb.getCountryZipCode(this);
            tvBind.setEnabled(false);
        etPhone.setText(SPUtils.getString(AppConstant.SPKey.PHONE));

        bindDialogSmsCodeView.setTvPhone(etPhone);
        bindDialogSmsCodeView.setCodeNumberCallBack(new SmsCodeView.CodeNumberCallBack() {
            @Override
            public String getCodeNumber() {
                return mCityItem.getCodeNumber();
            }
        });


        bindDialogSmsCodeView.setOnSmsCallBack(new SmsCodeView.OnSmsCallBack() {
            @Override
            public void call(int type, Map<String, String> codeMap) {
                codeMap.put("areaCode", mCityItem.getCodeNumber());
                getSmsCode(codeMap);
            }
        });
        bindDialogSmsCodeView.bindBtn(tvBind);
        tv_86.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                startActivityForResult(new Intent(getBaseContext(), RegionActivity.class), 100);
            }
        });

        tvBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = etPhone.getText().toString();
                String code = bindDialogSmsCodeView.getCode();
                if (RegexUtils.regex(phone, "notCheck", code, mCityItem.getCodeNumber())) {
                    Map<String, String> codeMap = MapUtils.getSmsMap( phone, code,1);
                    OkHttpClientManager.postAsync(ApiKey.SMS_CHECK_SMSCODE, codeMap, new NewRequestCallBack<BindPhoneBean>(BindPhoneBean.class) {
                        @Override
                        protected void onSuccess(BindPhoneBean result) {
                            if (result.getResultCode()==1){
                                ToastUitl.show("绑定成功",0);
                                mPresenter.postData(USERINFO_TAGSAVECHECK,MapUtils.getDefMap(true),TagCheckBean.class);
                                MyAPP.setAccessToken(result.getData().getAccessToken());
                            }
                        }

                        @Override
                        public void onError(String msg, int code) {
                            super.onError(msg, code);
                            ToastUitl.showShort("绑定失败");
                        }
                    });
                }
            }
        });
    }

    @Override
    public void returnData(TagCheckBean result) {
        if (!MyAPP.isLogout()) {
            OkHttpClientManager.postAsync(ApiKey.USER_INFO, MapUtils.getDefMap(true), new NewRequestCallBack<UserInfoBean>(UserInfoBean.class) {
                @Override
                public void onSuccess(UserInfoBean result) {//登录请求成功后执行
                    //判断数据库是否有该用户,没有则添加进去
                    MyAPP.getDBUserInfo(result.getUserInfo());
                }

                @Override
                public void onError(String msg, int code) {
                }
            });
        }
        if(result.getData().getStatus()==0){
            LabelActivity.startAction(this);
        }else {
            MainActivity.startAction(this, 1);

        }

    }
}
