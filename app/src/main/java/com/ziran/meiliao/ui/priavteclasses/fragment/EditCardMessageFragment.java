package com.ziran.meiliao.ui.priavteclasses.fragment;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.citypicker.citylist.sortlistview.SortModel;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonwidget.OnNoDoubleClickListener;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonHttpFragment;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.BankInfoBean;
import com.ziran.meiliao.ui.bean.CheckCardBean;
import com.ziran.meiliao.ui.main.activity.RegionActivity;
import com.ziran.meiliao.ui.priavteclasses.contract.EditCardMessageContract;
import com.ziran.meiliao.ui.priavteclasses.presenter.EditCardMessagePresenter;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.PhoneCodeView;
import com.ziran.meiliao.widget.SmsCodeView;
import com.ziran.meiliao.widget.pupop.TipsDialog;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 私家课-直播详情-简介Fragment
 * Created by Administrator on 2017/1/7.
 */

public class EditCardMessageFragment extends CommonHttpFragment<EditCardMessagePresenter, CommonModel> implements EditCardMessageContract.View {


    @Bind(R.id.et_add_blank_type)
    TextView etAddBlankType;
    @Bind(R.id.phone_code_view)
    PhoneCodeView mPhoneCodeView;
    @Bind(R.id.smsCodeView)
    SmsCodeView smsCodeView;
    private CheckCardBean.DataBean mCheckCardBean;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_edit_card_message;
    }


    private SortModel mCityItem;

    @Override
    protected void initView() {

        mPhoneCodeView.setTvAreaCodeClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                startActivityForResult(new Intent(getContext(), RegionActivity.class), 100);
            }
        });
        mCityItem = SortModel.getDefault();
        smsCodeView.setTvPhone(mPhoneCodeView.getEtPhone());
        smsCodeView.setOnSmsCallBack(new SmsCodeView.OnSmsCallBack() {
            @Override
            public void call(int type, Map<String, String> map) {
                if (!CheckUtil.checkNet(getContext(), etAddBlankType)) return;
                map.put("areaCode", mCityItem.getCodeNumber());
                mPresenter.getCode(ApiKey.BANK_CARD_GET_CODE, map);
            }
        });
        smsCodeView.setCodeNumberCallBack(new SmsCodeView.CodeNumberCallBack() {
            @Override
            public String getCodeNumber() {
                return mCityItem != null ? mCityItem.getCodeNumber() : "";
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (etAddBlankType != null && EmptyUtils.isNotEmpty(mCheckCardBean)) {
            etAddBlankType.setText(mCheckCardBean.getBankName());
            if (!TextUtils.isEmpty(phone)){
                mPhoneCodeView.setPhoneText(phone);
            }
        }

    }

    @OnClick({R.id.iv_edit_card_message_tips, R.id.tv_add_card_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_edit_card_message_tips:
                TipsDialog tipsDialog = new TipsDialog(getContext(), "手机号说明", "银行卡预留的手机号码是办理该银行卡时所填写的手机号码。没有预留、手机号忘记或停用，请联系银行客服更新处理。大陆手机号为11位数字，非大陆手机号为“国家代码-手机号码”形式。");
                tipsDialog.show();
                break;
            case R.id.tv_add_card_finish:
                Map<String, String> defMap = MapUtils.getDefMap(true);
                String code = smsCodeView.getCode();
                defMap.put("bankCardNo", mCheckCardBean.getBankCardNo());
                defMap.put("name", mCheckCardBean.getName());
                defMap.put("bankName", mCheckCardBean.getBankName());
                defMap.put("bankCardType", mCheckCardBean.getBankCardType());
                defMap.put("phone", mPhoneCodeView.getPhoneText());
                defMap.put("code", code);
                mPresenter.bindCard(ApiKey.LIVE_BANK_CARD_ADD, defMap);
                break;
        }
    }

    public void setCardNo(CheckCardBean.DataBean cardNo) {
        mCheckCardBean = cardNo;
    }


    @Override
    public void returnCode(Result data) {
        smsCodeView.startDjs();
    }

    public void setAreaCode(SortModel cityItem) {
        if (EmptyUtils.isEmpty(cityItem)) return;
        this.mCityItem = cityItem;
        mPhoneCodeView.setAreaCode(mCityItem.getData());
    }

    @Override
    public void bindCard(List<BankInfoBean.DataBean> data) {
        Intent intentResult = getActivity().getIntent();
        intentResult.putExtra(AppConstant.ExtraKey.BANK_INFO, data.get(data.size()-1));
        getActivity(). setResult(Activity.RESULT_OK, intentResult);
        finish();
    }
    private String phone;
    public void setPhone(String phone) {
        this.phone  = phone;
        if (!TextUtils.isEmpty(phone) && mPhoneCodeView != null) {
            mPhoneCodeView.setPhoneText(phone);
        }
    }
}
