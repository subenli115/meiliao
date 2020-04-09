package com.ziran.meiliao.ui.settings.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.citypicker.citylist.sortlistview.SortModel;
import com.citypicker.citylist.utils.CityDataDb;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.commonwidget.OnNoDoubleClickListener;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.compressorutils.RegexUtils;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpFragment;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.ExchangeBean;
import com.ziran.meiliao.ui.bean.PointsListBean;
import com.ziran.meiliao.ui.bean.PurseListCoinBean;
import com.ziran.meiliao.ui.bean.StringDataBean;
import com.ziran.meiliao.ui.main.activity.RegionActivity;
import com.ziran.meiliao.ui.main.activity.UserAgreementWebActivity;
import com.ziran.meiliao.ui.settings.adapter.AmountAdapter;
import com.ziran.meiliao.ui.settings.adapter.AmountPointsAdapter;
import com.ziran.meiliao.utils.HtmlUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.PayUtil;
import com.ziran.meiliao.widget.PhoneCodeView;
import com.ziran.meiliao.widget.SmoothCheckBox;
import com.ziran.meiliao.widget.SmsCodeView;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

import static com.ziran.meiliao.constant.ApiKey.PURSE_GETUSERSCORE;
import static com.ziran.meiliao.constant.ApiKey.USERCENTER_BUYBYSCORE;

/**
 * 优惠劵Fragment
 * Created by Administrator on 2017/1/16.
 */

public class RechargeFragment extends CommonHttpFragment<CommonPresenter, CommonModel> implements CommonContract.View<Result> {


    @Bind(R.id.tv_recharge_balance)
    TextView tvRechargeBalance;
    @Bind(R.id.gv_recharge_amount)
    GridView gvRechargeAmount;
    @Bind(R.id.gv_recharge_amount_two)
    GridView gvRechargeAmountTwo;
    @Bind(R.id.smoothCheckBox_wechat)
    SmoothCheckBox smoothCheckBoxWechat;
    @Bind(R.id.smoothCheckBox_zfb)
    SmoothCheckBox smoothCheckBoxZfb;
    @Bind(R.id.tv_recharge_agreement)
    TextView tvRechargeAgreement;
    @Bind(R.id.tv_hint)
    TextView tv_hint;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.rll_two)
    AutoRelativeLayout rll_two;
    @Bind(R.id.rll_one)
    RelativeLayout rll_one;
    private AmountAdapter mAmountAdapter;
    private PayUtil payUtil;
    private Dialog threeLogindialog;
    private SmsCodeView bindDialogSmsCodeView;
    private SortModel mCityItem;
    private Map<String, String> codeMap;
    private int status;
    private List<Fragment> list;
    private AmountPointsAdapter mAmountAdapterTwo;
    private PointsListBean.DataBean mDataBeanTwo;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_me_recharge;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    private PayUtil.OnPayCallBack mOnPayCallBack = new PayUtil.OnPayCallBack() {
        @Override
        public void onPaySuccess(int platform) {
            mPresenter.getData(ApiKey.PURSE_GET_BALANCE, MapUtils.getDefMap(true), StringDataBean.class);
        }

        @Override
        public void onPayFailed() {

        }
    };

    @Override
    protected void initView() {
        mCityItem = CityDataDb.getCountryZipCode(getContext());
        if (payUtil == null) {
            payUtil = new PayUtil(getContext());
            payUtil.setOnPayCallBack(mOnPayCallBack);
            mAmountAdapter = new AmountAdapter(getContext(), R.layout.item_grid_amount);
            gvRechargeAmount.setAdapter(mAmountAdapter);
            gvRechargeAmount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    mAmountAdapter.changeSelect(position);
                }
            });


            smoothCheckBoxWechat.setChecked(true);
            smoothCheckBoxWechat.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
                    if (isChecked) {
                        smoothCheckBoxZfb.setChecked(false);
                        checkBox.setEnabled(false);
                        smoothCheckBoxZfb.setEnabled(true);
                    }
                }
            });
            smoothCheckBoxZfb.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
                    if (isChecked) {
                        smoothCheckBoxWechat.setChecked(false);
                        checkBox.setEnabled(false);
                        smoothCheckBoxWechat.setEnabled(true);
                    }
                }
            });
            balance = getIntentExtra(AppConstant.ExtraKey.BALANCE);
            tvRechargeBalance.setText(HtmlUtil.parseRechargeBalance(balance));
            mPresenter.getData(ApiKey.PURSE_LIST_COIN, MapUtils.getDefMap(false), PurseListCoinBean.class);
            if(balance.equals("")){
                mPresenter.getData(ApiKey.PURSE_GET_BALANCE, MapUtils.getDefMap(true), StringDataBean.class);
            }
        }
        tabLayout.addTab(tabLayout.newTab().setText("现金充值"));
        tabLayout.addTab(tabLayout.newTab().setText("积分兑换"));
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#93DEDB"));
        tabLayout.setTabTextColors(Color.parseColor("#2A2A2A"), Color.parseColor("#93DEDB"));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==0){
                    rll_one.setVisibility(View.VISIBLE);
                    rll_two.setVisibility(View.GONE);
                }else {
                    rll_one.setVisibility(View.GONE);
                    rll_two.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mAmountAdapterTwo = new AmountPointsAdapter(getContext(), R.layout.item_grid_amount);
        gvRechargeAmountTwo.setAdapter(mAmountAdapterTwo);

        gvRechargeAmountTwo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mAmountAdapterTwo.changeSelect(position);
            }
        });
        reflash();
    }
    private void reflash() {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        mPresenter.getData(PURSE_GETUSERSCORE, defMap, PointsListBean.class);
    }

    private String balance;
    private PurseListCoinBean.DataBean mDataBean;

    @Override
    public void returnData(Result result) {
        if (result instanceof PurseListCoinBean) {
            mDataBean = ((PurseListCoinBean) result).getData();
            List<PurseListCoinBean.DataBean.ListBean> list = mDataBean.getList();
            list.get(0).setSelect(true);
            mAmountAdapter.replaceAll(list);
        } else if (result instanceof StringDataBean) {
            if(balance.equals("")){
                String balance = ((StringDataBean) result).getNornemData();
                tvRechargeBalance.setText(HtmlUtil.parseRechargeBalance(balance));
                RxManagerUtil.post(AppConstant.RXTag.BALANCE, balance);
                return;
            }else{
                String balance = ((StringDataBean) result).getNornemData();
                tvRechargeBalance.setText(HtmlUtil.parseRechargeBalance(balance));
                RxManagerUtil.post(AppConstant.RXTag.BALANCE, balance);
                finish();
            }
        }else if(result instanceof PointsListBean){
            mDataBeanTwo = ((PointsListBean) result).getData();
            List<PointsListBean.DataBean.ScoreListBean> list = mDataBeanTwo.getScoreList();
            list.get(0).setSelect(true);
            tv_hint.setText("积分可以兑换金币啦，您当前的积分为"+mDataBeanTwo.getScore());
            tvRechargeBalance.setText("当前金币数量"+mDataBeanTwo.getCoin());
            mAmountAdapterTwo.replaceAll(list);
            mAmountAdapterTwo.update(mDataBeanTwo.getScore());
        }else {
            reflash();
            ToastUitl.showShort("兑换成功");
        }

    }
    //绑定手机的对话框
    private void showBindDialog() {
        threeLogindialog = new Dialog(getContext(), R.style.MyDialogStyle);
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_bind_phone, null);
        bindDialogSmsCodeView = ViewUtil.getView(contentView, R.id.smsCodeView);
        final PhoneCodeView phoneCodeView = ViewUtil.getView(contentView, R.id.phone_code_view);
        final TextView tvBind = ViewUtil.getView(contentView, R.id.btn_bing);
        bindDialogSmsCodeView.setTvPhone(phoneCodeView.getEtPhone());
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
        phoneCodeView.setTvAreaCodeClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                startActivityForResult(new Intent(getContext(), RegionActivity.class), 100);
            }
        });

        tvBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = phoneCodeView.getPhoneText();
                String code = bindDialogSmsCodeView.getCode();
                if (RegexUtils.regex(phone, "notCheck", code, mCityItem.getCodeNumber())) {
                    Map<String, String> codeMap = MapUtils.getSmsMap( phone, code,1);
                    OkHttpClientManager.postAsync(ApiKey.SMS_CHECK_SMSCODE, codeMap, new NewRequestCallBack<StringDataBean>(StringDataBean.class) {
                        @Override
                        protected void onSuccess(StringDataBean result) {
                            if (result.getResultCode()==1){
                                if (threeLogindialog != null) {
                                    threeLogindialog.dismiss();
                                }
                                ToastUitl.show("绑定成功",0);
                            }
                        }
                    });
                }
            }
        });
        Window dialogWindow = threeLogindialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialogWindow.setAttributes(lp);
        threeLogindialog.setContentView(contentView);
        threeLogindialog.show();
    }
    private void getSmsCode(Map<String, String> codeMap) {
        this.codeMap = codeMap;
        OkHttpClientManager.postAsync(ApiKey.SMS_GET_SMSCODE, codeMap, new NewRequestCallBack<StringDataBean>(StringDataBean.class) {
            @Override
            protected void onSuccess(StringDataBean result) {



            }
        });
    }
    @OnClick({R.id.check_wechat, R.id.check_alipay, R.id.iv_recharge_req, R.id.tv_recharge_agreement,R.id.iv_recharge_req_two})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.check_wechat:
                smoothCheckBoxWechat.setChecked(true);
                break;
            case R.id.check_alipay:
                smoothCheckBoxZfb.setChecked(true);
                break;
            case R.id.tv_recharge_agreement:
                //充值协议
                UserAgreementWebActivity.startAction(getContext(), mDataBean.getPro(),"充值协议");
                break;
            case R.id.iv_recharge_req:
                    PurseListCoinBean.DataBean.ListBean dataBean = mAmountAdapter.getSelect();
                    if (EmptyUtils.isEmpty(dataBean)) return;
                    payUtil.setPrice(dataBean.getRmb() );
                    payUtil.setTitle(dataBean.getTitle());
                    payUtil.setPayId(dataBean.getId());
                    payUtil.setType(dataBean.getType());
                    if (smoothCheckBoxWechat.isChecked()) {
                        //使用微信支付
                        payUtil.weChatPay();
                    } else {
                        //使用支付宝支付
                        payUtil.alipayPay();
                    }

                break;

            case R.id.iv_recharge_req_two:
                PointsListBean.DataBean.ScoreListBean dataBeanTwo = mAmountAdapterTwo.getSelect();
                Map<String, String> defMap = MapUtils.getDefMap(true);
                defMap.put("goodsId",dataBeanTwo.getGoodsId()+"");
                defMap.put("type","4");
                mPresenter.postData(USERCENTER_BUYBYSCORE, defMap, ExchangeBean.class);
                break;
        }
    }
}
