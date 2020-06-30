package com.ziran.meiliao.ui.settings.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhy.autolayout.AutoLinearLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpFragment;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.PayListBean;
import com.ziran.meiliao.ui.bean.RechargeBean;
import com.ziran.meiliao.ui.bean.StringDataV2Bean;
import com.ziran.meiliao.ui.bean.UserAccountBean;
import com.ziran.meiliao.ui.bean.UserBean;
import com.ziran.meiliao.ui.bean.UserExternalAccountBean;
import com.ziran.meiliao.ui.me.activity.SetRealNameActivity;
import com.ziran.meiliao.ui.settings.activity.RechargeDetailsActivity;
import com.ziran.meiliao.ui.settings.adapter.AmountAdapter;
import com.ziran.meiliao.ui.settings.adapter.AmountPointsAdapter;
import com.ziran.meiliao.utils.MapUtils;
import com.zhy.autolayout.AutoRelativeLayout;
import com.ziran.meiliao.widget.pupop.SimplePayPopupWindow;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

import static com.ziran.meiliao.constant.ApiKey.ACCOUNT_ACCOUNT_INFO;
import static com.ziran.meiliao.constant.ApiKey.ACCOUNT_CAPITALEXTRACT_ADD;
import static com.ziran.meiliao.constant.ApiKey.ACCOUNT_EXTERNAL;


/**
 * 优惠劵Fragment
 * Created by Administrator on 2017/1/16.
 */

public class RechargeFragment extends CommonHttpFragment<CommonPresenter, CommonModel> implements CommonContract.View<Result> {


    @Bind(R.id.tv_withdrawal)
    TextView tvWithdrawal;
    @Bind(R.id.tv_recharge)
    TextView tvRecharge;
    @Bind(R.id.tv_recharge_gold)
    TextView tvRechargeGold;
    @Bind(R.id.tv_recharge_money)
    TextView tvRechargeMoney;
    @Bind(R.id.gv_recharge_amount)
    GridView gvRechargeAmount;
    @Bind(R.id.gv_recharge_amount_two)
    GridView gvRechargeAmountTwo;
    @Bind(R.id.rll_two)
    AutoRelativeLayout rll_two;
    @Bind(R.id.rll_one)
    RelativeLayout rll_one;
    @Bind(R.id.arl_bg)
    AutoRelativeLayout arlBg;
    private AmountAdapter mAmountAdapter;
    private AmountPointsAdapter mAmountAdapterTwo;
    private List<RechargeBean.DataBean.RecordsBean> mDataBeanTwo;
    private UserBean.DataBean dataBean;
    private List<RechargeBean.DataBean.RecordsBean> mDataBeanOne;
    private UserAccountBean.DataBean mUserAccountBean;
    private List<RechargeBean.DataBean.RecordsBean> mDataBean;
    private double money;
    private View contentView;
    private static final int REQUEST_CODE_C = 4;
    private  UserExternalAccountBean.DataBean mUserExternalBean;
    private boolean haveThreeAccount;
    private String userId;
    private String accessToken;
    private double currency;
    private SimplePayPopupWindow simplePayPopupWindow;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_me_recharge;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }


    @Override
    protected void initView() {
        dataBean = MyAPP.getmUserBean();
        userId = MyAPP.getUserId();
        accessToken = MyAPP.getAccessToken();
         simplePayPopupWindow = new SimplePayPopupWindow(getActivity());
        mAmountAdapter = new AmountAdapter(getContext(), R.layout.item_grid_amount);
        gvRechargeAmount.setAdapter(mAmountAdapter);
        gvRechargeAmount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mDataBeanOne != null) {
                    RechargeBean.DataBean.RecordsBean recordsBean = mDataBeanOne.get(position);
                    int money = (int) recordsBean.getPrice();
                    simplePayPopupWindow.setPamras(recordsBean.getName(), money + "", recordsBean.getId(), recordsBean.getPrice());
                    simplePayPopupWindow.show();
                }
            }
        });
        balance = getIntentExtra(AppConstant.ExtraKey.BALANCE);
        if (balance == null || balance.equals("")) {
        }
        mAmountAdapterTwo = new AmountPointsAdapter(getContext(), R.layout.item_grid_point_amount, currency);
        gvRechargeAmountTwo.setAdapter(mAmountAdapterTwo);
        gvRechargeAmountTwo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mDataBeanTwo != null) {
                    RechargeBean.DataBean.RecordsBean recordsBean = mDataBeanTwo.get(position);
                    if (mDataBeanTwo.get(position).getPrice() > money) {
                        ToastUitl.showShort("余额不足");
                    } else {
                        if (MyAPP.getmUserBean().getIdCard() == null || MyAPP.getmUserBean().getIdCard().equals("")) {
                            showApplyPopWindow(recordsBean, 3);
                        } else {
                            if(haveThreeAccount){
                                showApplyPopWindow(recordsBean, 1);
                            }else {
                                showApplyPopWindow(recordsBean, 2);
                            }
                        }
                    }
                }
            }
        });
        getCommodityList("2");
        getCommodityList("3");
        getThreeAccount();
        getPayList();
    }
    public void    getPayList(){
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("android","0");
        defMap.put("status","0");
        OkHttpClientManager.getAsyncMore(ApiKey.ACCOUNT_METHOD_PAGE, defMap, new NewRequestCallBack<PayListBean>(PayListBean.class) {
            @Override
            protected void onSuccess(PayListBean result) {
                simplePayPopupWindow.setPopList(result.getData().getRecords());
            }

            @Override
            public void onError(String msg, int code) {
                ToastUitl.showShort(msg);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getDataOneHead(ACCOUNT_ACCOUNT_INFO, userId,accessToken, UserAccountBean.class);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_C:
                if (resultCode == Activity.RESULT_OK) {
                    getThreeAccount();
                }
            default:
                break;
        }

    }

    private void getThreeAccount() {
        mPresenter.getDataOneHead(ACCOUNT_EXTERNAL, userId,accessToken, UserExternalAccountBean.class);
    }

    private void showApplyPopWindow(RechargeBean.DataBean.RecordsBean recordsBean, int type) {
        // 一个自定义的布局，作为显示的内容
        int[] location = new int[2];
        if (type == 1) {
            contentView = LayoutInflater.from(getContext()).inflate(R.layout.pop_withdrawal_apply, null);
        } else if (type == 2) {
            contentView = LayoutInflater.from(getContext()).inflate(R.layout.pop_withdrawal_set_account, null);
        } else if (type == 3) {
            contentView = LayoutInflater.from(getContext()).inflate(R.layout.pop_real_apply, null);
        }
        contentView.getLocationOnScreen(location);
        final PopupWindow popupWindow = new PopupWindow(contentView,
                AutoLinearLayout.LayoutParams.MATCH_PARENT, AutoLinearLayout.LayoutParams.MATCH_PARENT, true);

        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);// 设置同意在外点击消失
        popupWindow.setFocusable(true);// 点击空白处时，隐藏掉pop窗口
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.showAtLocation(arlBg, Gravity.CENTER, 0, 0);
        TextView qd = contentView.findViewById(R.id.tv_qd);
        TextView qx = contentView.findViewById(R.id.tv_qx);
        TextView etReason = contentView.findViewById(R.id.et_reason);
        if(type==1){
            etReason.setText("确定提现"+recordsBean.getPrice()+"元到您的支付宝账户？");
        }
        qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type == 2) {
                    popupWindow.dismiss();
                    if(haveThreeAccount){
                        SetRealNameActivity.startAction("ThirdPay", REQUEST_CODE_C,mUserExternalBean.getRealName(),mUserExternalBean.getName(),mUserExternalBean.getId()+"");
                    }else {
                        SetRealNameActivity.startAction("ThirdPay", REQUEST_CODE_C);
                    }
                } else if (type == 1) {
                     capitalextractAdd(recordsBean);
                } else {
                    //去实名
                    popupWindow.dismiss();
                    SetRealNameActivity.startAction("real", REQUEST_CODE_C);
                }


            }
        });
        qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();

            }
        });
    }

    private void capitalextractAdd(RechargeBean.DataBean.RecordsBean recordsBean) {
        startProgressDialog("请等待");
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("userId", userId);
        defMap.put("channel", "1");
        defMap.put("userName", MyAPP.getmUserBean().getRealName()+"");
        defMap.put("money", recordsBean.getPrice() + "");
        OkHttpClientManager.postAsyncAddHead(ACCOUNT_CAPITALEXTRACT_ADD, defMap, "", new
                NewRequestCallBack<StringDataV2Bean>(StringDataV2Bean.class) {

                    @Override
                    public void onSuccess(StringDataV2Bean listBean) {
                        ToastUitl.showShort(listBean.getResultMsg());
                        stopProgressDialog();
                    }

                    @Override
                    public void onError(String msg, int code) {
                        stopProgressDialog();
                        ToastUitl.showShort(msg);
                        if (code == 3000) {
                            //提现成功
                            ToastUitl.showShort("提现成功");
                            getCommodityList("3");
                            mPresenter.getDataOneHead(ACCOUNT_ACCOUNT_INFO,userId,accessToken, UserAccountBean.class);
                        }
                    }
                });
    }

    public void getCommodityList(String type) {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("status", "0");
        defMap.put("type", type);
        mPresenter.getData(ApiKey.ACCOUNT_COMMODITY_PAGE, defMap, RechargeBean.class);

    }

    private String balance;

    @Override
    public void returnData(Result result) {
        if (result instanceof RechargeBean) {
            //充值列表返回
            mDataBean = ((RechargeBean) result).getData().getRecords();
            if (mDataBean != null && mDataBean.size() > 0) {

                if (mDataBean.get(0).getType().equals("2")) {
                    mDataBeanOne = mDataBean;
                    mAmountAdapter.replaceAll(mDataBeanOne);
                } else {
                    mDataBeanTwo = mDataBean;
                    mAmountAdapterTwo.replaceAll(mDataBeanTwo);

                }
            }
        } else if (result instanceof UserAccountBean) {
            mUserAccountBean = ((UserAccountBean) result).getData();
            int gold = MyAPP.saveMoney(((UserAccountBean) result));
            tvRechargeGold.setText(gold + "");
            money=(int)mUserAccountBean.getMoney();
             currency = mUserAccountBean.getCurrency()/1000+mUserAccountBean.getMoney();
             if(mAmountAdapterTwo!=null){
                 mAmountAdapterTwo.update(currency);
             }
            tvRechargeMoney.setText(money + "");
        } else if (result instanceof UserExternalAccountBean) {
            mUserExternalBean = ((UserExternalAccountBean) result).getData();
            if(mUserExternalBean!=null){
                if (mUserExternalBean.getName()!= null && mUserExternalBean.getName().length() > 0) {
                    haveThreeAccount = true;
                }
            }
        }

    }


    @OnClick({R.id.iv_real_name, R.id.tv_withdrawal, R.id.tv_recharge,R.id.ll_details})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_real_name:
                if (MyAPP.getmUserBean().getIdCard() == null || MyAPP.getmUserBean().getIdCard().equals("")) {
                    showApplyPopWindow(null, 3);
                } else {
                    if(haveThreeAccount){
                        SetRealNameActivity.startAction("ThirdPay", REQUEST_CODE_C,mUserExternalBean.getRealName(),mUserExternalBean.getName(),mUserExternalBean.getId()+"");
                    }else {
                        SetRealNameActivity.startAction("ThirdPay", REQUEST_CODE_C);
                    }
                }
                break;
            case R.id.tv_recharge:
                rll_one.setVisibility(View.VISIBLE);
                rll_two.setVisibility(View.GONE);
                setTextSize(tvRecharge, tvWithdrawal);
                break;
            case R.id.tv_withdrawal:
                rll_one.setVisibility(View.GONE);
                rll_two.setVisibility(View.VISIBLE);
                setTextSize(tvWithdrawal, tvRecharge);
                break;
           case  R.id.ll_details:
               RechargeDetailsActivity.startAction(getContext());
            break;
        }
    }

    private void setTextSize(TextView tv1, TextView tv2) {
        tv1.setTextColor(Color.parseColor("#000000"));
        tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
        tv1.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        tv2.setTextColor(Color.parseColor("#808080"));
        tv2.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);
        tv2.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
    }
}
