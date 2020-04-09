package com.ziran.meiliao.ui.priavteclasses.fragment;

import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpFragment;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.CheckCardBean;
import com.ziran.meiliao.ui.settings.activity.AddBankCardActivity;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.BaseItemView;
import com.ziran.meiliao.widget.pupop.TipsDialog;

import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 私家课-直播详情-简介Fragment
 * Created by Administrator on 2017/1/7.
 */

public class EditCardCodeFragment extends CommonHttpFragment<CommonPresenter,CommonModel> implements CommonContract.View<CheckCardBean> {


    @Bind(R.id.biv_name)
    BaseItemView bivName;
    @Bind(R.id.biv_bland_card_name)
    BaseItemView mBivBlandCardName;
    @Bind(R.id.biv_bland_card_no)
    BaseItemView mBivBlandCardNo;
    @Bind(R.id.biv_phone)
    BaseItemView mBivPhone;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_edit_card_code;
    }

    @Override
    protected void initView() {
        super.initView();
        bivName.setContent(MyAPP.getUserInfo().getNickName());
    }

    private   String cardNo;
    @OnClick({R.id.tv_add_card_next, R.id.iv_edit_card_code_tips})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_edit_card_code_tips:
                TipsDialog tipsDialog = new TipsDialog(getContext(),"持卡人说明","为保证账户资金安全，只能绑定认证用户本人的银行卡。");
                tipsDialog.show();
                break;
            case R.id.tv_add_card_next:
                 cardNo = mBivBlandCardNo.getContent();
                 String name  = bivName.getContent();
                if (EmptyUtils.isNotEmpty(cardNo) && cardNo.length()>15){
                    cardNo =  cardNo.replaceAll("\\s*", "");
                    Map<String, String> onlyCan = MapUtils.getOnlyCan(AppConstant.ExtraKey.CARD_NO, cardNo);
                    onlyCan.put("name",name);
                    mPresenter.postData(ApiKey.LIVE_BANK_CARD_CHECK,onlyCan,CheckCardBean.class);
                }else{
                    ToastUitl.showShort("请输入完整的银行卡号");
                }
                break;
        }
    }

    @Override
    public void returnData(CheckCardBean result) {
        AddBankCardActivity addBankCardActivity = (AddBankCardActivity) getActivity();
        if (result.getData()!=null){
            addBankCardActivity.setCardNo(result.getData()) ;
            addBankCardActivity.setPhone(mBivPhone.getContent());
        }
        addBankCardActivity.setStep(AddBankCardActivity.STEP_EDIT_CARD_MESSAGE);
    }
}
