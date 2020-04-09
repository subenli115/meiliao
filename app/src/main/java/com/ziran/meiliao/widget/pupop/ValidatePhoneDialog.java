package com.ziran.meiliao.widget.pupop;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.citypicker.citylist.sortlistview.SortModel;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.commonutils.KeyBordUtil;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.entry.BindBean;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.StringDataBean;
import com.ziran.meiliao.ui.main.activity.NewLoginActivity;
import com.ziran.meiliao.ui.main.activity.RegionActivity;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.PhoneCodeView;
import com.ziran.meiliao.widget.SmsCodeView;
import com.wevey.selector.dialog.SimpleDialog;

import java.util.Map;

import rx.functions.Action1;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/11/29 14:22
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/11/29$
 * @updateDes ${TODO}
 */

public class ValidatePhoneDialog extends SimpleDialog {
    View rootView;
    private PhoneCodeView mPhoneCodeView;
    private SmsCodeView mSmsCodeView;
    private TextView tvOk;
    private SortModel mCityItem;

    public ValidatePhoneDialog(@NonNull Context context) {
        super(context);
        rootView = LayoutInflater.from(context).inflate(R.layout.dialog_validate_phone, null);
        mPhoneCodeView = ViewUtil.getView(rootView, R.id.phone_code_view);
        mSmsCodeView = ViewUtil.getView(rootView, R.id.smsCodeView);
        tvOk = ViewUtil.getView(rootView, R.id.tv_dialog_ok);
        setContentView(rootView);
        tvOk.setEnabled(false);
        mSmsCodeView.setTvPhone(mPhoneCodeView.getEtPhone());
        mSmsCodeView.setCodeNumberCallBack(new SmsCodeView.CodeNumberCallBack() {
            @Override
            public String getCodeNumber() {
                return mCityItem.getCodeNumber();
            }
        });
        mPhoneCodeView.setTvAreaCodeClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManager.getAppManager().currentActivity().startActivityForResult(new Intent(getContext(), RegionActivity.class), 100);
            }
        });
        mCityItem = SortModel.getDefault();
        RxManagerUtil.on(AppConstant.RXTag.CITY_DATA, new Action1<SortModel>() {
            @Override
            public void call(SortModel cityItem) {
                mCityItem = cityItem;
                mPhoneCodeView.setAreaCode(cityItem.getData());
            }
        });

        mSmsCodeView.setOnSmsCallBack(new SmsCodeView.OnSmsCallBack() {
            @Override
            public void call(int type, Map<String, String> codeMap) {
//                codeMap.put("areaCode", mCityItem.getCodeNumber());
                getSmsCode(codeMap);
            }
        });
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> smsMap = MapUtils.getSmsMap(mPhoneCodeView.getEtPhone().getText().toString(), mSmsCodeView.getCode(),1);
                KeyBordUtil.hideSoftKeyboard(mSmsCodeView.getEtitTextCode());
                OkHttpClientManager.postAsync(ApiKey.SMS_CHECK_SMSCODE, smsMap, new NewRequestCallBack<BindBean>(BindBean.class) {
                    @Override
                    protected void onSuccess(BindBean result) {
                        if (result.getResultCode()==1){
                            dismiss();
                            ToastUitl.show("绑定成功,请重新登录",0);
                            NewLoginActivity.startAction(getContext());
                            if (mBaseCallBack != null) {
                                mBaseCallBack.call();
                            }
                        }
                    }
                });
            }
        });
    }
    private Map<String,String> codeMap;
    private void getSmsCode(Map<String, String> codeMap) {
        this.codeMap = codeMap;
        OkHttpClientManager.postAsync(ApiKey.SMS_GET_SMSCODE, codeMap, new NewRequestCallBack<StringDataBean>(StringDataBean.class) {
            @Override
            protected void onSuccess(StringDataBean result) {
                HandlerUtil.startDjs(mSmsCodeView.getTvSmsGetCode());
                tvOk.setEnabled(true);
                ToastUitl.showShort(result.getResultMsg());
            }
        });
    }

    private ViewUtil.BaseCallBack mBaseCallBack;

    public void setCallBack(ViewUtil.BaseCallBack callBack) {
        this.mBaseCallBack = callBack;
    }

    public String getPhone() {
        return mPhoneCodeView.getPhoneText();
    }
}
