package com.ziran.meiliao.ui.settings.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonutils.SPUtils;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.SimpleTextWatcher;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.AuthorBean;
import com.ziran.meiliao.ui.bean.BankInfoBean;
import com.ziran.meiliao.ui.bean.WithDrawalsBean;
import com.ziran.meiliao.ui.priavteclasses.activity.MyBankListActivity;
import com.ziran.meiliao.ui.priavteclasses.activity.WithDrawalsItemDetailActivity;
import com.ziran.meiliao.ui.settings.contract.WithDrawalsContract;
import com.ziran.meiliao.ui.settings.presenter.WithDrawalsPresenter;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.StringUtils;

import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 优惠劵界面
 * Created by Administrator on 2017/1/4.
 */

public class WithDrawalsActivity extends BaseActivity<WithDrawalsPresenter, CommonModel> implements WithDrawalsContract.View {
    @Bind(R.id.ntb)
    public NormalTitleBar ntb;
    @Bind(R.id.tv_with_drawals_add_card)
    TextView tvWithDrawalsAddCard;
    @Bind(R.id.tv_with_drawals_bank_name)
    TextView tvWithDrawalsBankName;
    @Bind(R.id.tv_with_drawals_bank_extra)
    TextView tvWithDrawalsBankExtra;
    @Bind(R.id.tv_with_drawals_balance)
    TextView tvWithDrawalsBalance;
    @Bind(R.id.tv_with_drawals_error_tips)
    TextView tvWithDrawalsErrorTips;
    @Bind(R.id.ll_with_drawals_bank_message)
    View llbankMessage;
    @Bind(R.id.et_with_drawals_amount)
    EditText mEditText;


    public static void startAction(Context mContext) {
        Intent intent = new Intent(mContext, WithDrawalsActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_with_drawals;
    }

    @Override
    public void initPresenter() {
        if (mModel != null) {
            mPresenter.setVM(this, mModel);
        }
    }

    private int balance = 50;

    @Override
    public void initView() {
        ntb.setTvLeftVisiable(true, true);
        ntb.setTitleText(getString(R.string.with_drawals));
        ntb.setRightTitle("我的银行卡");
        ntb.setOnRightTextListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                if (mBankInfoBean != null) {
                    bundle.putParcelable(AppConstant.ExtraKey.BANK_LIST, mBankInfoBean);
                    bundle.putString(AppConstant.ExtraKey.EXTRAS_TITLE, "我的银行卡");
                }
                startActivity(MyBankListActivity.class, bundle);
            }
        });
        AuthorBean authorBean = (AuthorBean) getParcelable(AppConstant.ExtraKey.AUTHOR_DATA);
        balance = Integer.parseInt(authorBean.getAvailableCash());
        tvWithDrawalsBalance.setText(StringUtils.format("可用余额是%d元", balance));
        mEditText.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //这儿判断操作，如果输入错误可以给用户提示
                if (EmptyUtils.isNotEmpty(s.toString()) && Integer.parseInt(s.toString()) > balance) {
                    tvWithDrawalsErrorTips.setVisibility(View.VISIBLE);
                } else {
                    tvWithDrawalsErrorTips.setVisibility(View.INVISIBLE);
                }
            }
        });
        mPresenter.getListBank(ApiKey.LIVE_BANK_CARD_LIST, MapUtils.getDefMap(true));
    }

    boolean hasBlankCard = false;

    @OnClick(R.id.fl_with_drawals_card_msg)
    public void cardMsg() {
        if (hasBlankCard) {
            //选择银行卡
            Bundle bundle = new Bundle();
            bundle.putString(AppConstant.ExtraKey.EXTRAS_TITLE, "选择银行卡");
            bundle.putParcelable(AppConstant.ExtraKey.BANK_LIST, mBankInfoBean);
            startActivityForResult(MyBankListActivity.class, bundle, 100);
        } else {
            startActivityForResult(AddBankCardActivity.class, 100);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            mCurrentBankData = data.getParcelableExtra(AppConstant.ExtraKey.BANK_INFO);
            setBankMessage(mCurrentBankData);
        }
    }

    private BankInfoBean mBankInfoBean;


    private void setBankMessage(BankInfoBean.DataBean dataBean) {
        tvWithDrawalsBankName.setText(dataBean.getBankName());
        tvWithDrawalsBankExtra.setText(StringUtils.format("%s %s", dataBean.getBankCardNo(), StringUtils.getBankType(dataBean.getBankCardType())));
        tvWithDrawalsAddCard.setVisibility(View.GONE);
        llbankMessage.setVisibility(View.VISIBLE);
        hasBlankCard = true;
    }


    @OnClick(R.id.tv_with_drawals_card_req)
    public void onViewClicked() {
        if (tvWithDrawalsErrorTips.isShown()) {
            showShortToast("提现金额不能大于可用余额,请重新输入提现金额");
            mEditText.setText("");
        } else if (EmptyUtils.isEmpty(mCurrentBankData)) {
            showShortToast("请添加银行卡");
        } else {
            String value = mEditText.getText().toString().trim();
            Map<String, String> maps = MapUtils.getOnlyCan("money", value);
            maps.put("cardId", String.valueOf(mCurrentBankData.getId()));
            maps.put("name", String.valueOf(MyAPP.getUserInfo().getNickName()));
            maps.put("bankCardNo", String.valueOf(mCurrentBankData.getBankCardNo()));
            maps.put("bankName", String.valueOf(mCurrentBankData.getBankName()));
            maps.put("phone", SPUtils.getString(AppConstant.SPKey.PHONE));
            mPresenter.postDrawCash(ApiKey.LIVE_GET_MONEY_SUPPLY, maps);
        }
    }

    private BankInfoBean.DataBean mCurrentBankData;

    @Override
    public void showListBank(BankInfoBean result) {
        mBankInfoBean = result;
        if (EmptyUtils.isNotEmpty(result.getData())) {
            mCurrentBankData = result.getData().get(0);
            setBankMessage(mCurrentBankData);
            ntb.setRightTitleVisibility(true);
        } else {
            tvWithDrawalsAddCard.setVisibility(View.VISIBLE);
            llbankMessage.setVisibility(View.GONE);
            ntb.setRightTitleVisibility(false);
            hasBlankCard = false;
        }
    }

    @Override
    public void showDrawCash(WithDrawalsBean.DataBean result) {
        showShortToast("提现申请成功");
        mRxManager.post(ApiKey.LIVE_GET_MONEY_SUPPLY,result.getAbleMoney());
        startActivity(WithDrawalsItemDetailActivity.class,getBundle(result.getOrderNo()));
        finish();
    }
}
