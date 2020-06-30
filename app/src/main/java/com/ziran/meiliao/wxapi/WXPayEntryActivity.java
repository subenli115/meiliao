package com.ziran.meiliao.wxapi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhy.autolayout.AutoLinearLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MeiliaoConfig;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.OrderDataBean;
import com.ziran.meiliao.utils.PayUtils;

import static com.ziran.meiliao.constant.ApiKey.ACCOUNT_ORDER;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private final String TAG = "WXPayEntryActivity";

    private IWXAPI api;
    private TextView tv_num,tv_money,tv_time,tv_pay_way;
    AutoLinearLayout all_qd;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);
        initView();
        api = WXAPIFactory.createWXAPI(this, MyAPP.wcAppId);
        api.handleIntent(getIntent(), this);
    }

    private void initView() {
        if(getIntent()!=null&&getIntent().getStringExtra("resultStatus")!=null){
            if(getIntent().getStringExtra("resultStatus").equals("9000")){
                getPayResult();
            }
        }
          tv_num = (TextView)findViewById(R.id.tv_num);
          tv_money = (TextView)findViewById(R.id.tv_money);
          tv_time =(TextView) findViewById(R.id.tv_time);
          tv_pay_way = (TextView)findViewById(R.id.tv_pay_way);
        all_qd = (AutoLinearLayout)findViewById(R.id.all_qd);
        all_qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }
    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        if(resp.errCode==0){
            getPayResult();
        }else {
            finish();
        }
    }

    private void getPayResult() {
        OkHttpClientManager.getDataOneHead(ACCOUNT_ORDER, PayUtils.orderId, MyAPP.getAccessToken(), new
                NewRequestCallBack<OrderDataBean>(OrderDataBean.class) {

                    @Override
                    public void onSuccess(OrderDataBean bean) {
                        OrderDataBean.DataBean data = bean.getData();
                        if(bean.getData().getStatus()==2){
                            tv_time.setText(data.getPayTime());
                            if(bean.getData().getType()==3){
                                tv_pay_way.setText("支付宝app支付");
                            }else {
                                tv_pay_way.setText("微信app支付");
                            }
                            tv_num.setText( PayUtils.orderId);
                            tv_money.setText("¥"+(int)data.getPrice()+"");
                        }else {
                            finish();
                        }
                    }

                    @Override
                    public void onError(String msg, int code) {
                    }
                });
    }
}