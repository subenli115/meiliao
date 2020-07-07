package com.ziran.meiliao.widget.pupop;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhy.autolayout.AutoLinearLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.PayListBean;
import com.ziran.meiliao.ui.settings.adapter.PayListAdapter;
import com.ziran.meiliao.utils.PayUtils;

import java.util.List;



/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/11 15:13
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/11$
 * @updateDes ${TODO}
 */

public class SimplePayPopupWindow extends BasePopupWindow {

    private TextView tvMoney, tvGold;
    private String mPrice;
    private int mId;
    private double mRecordsBeanPrice;

    private TextView tvWx;
    private TextView tvAi;
    private AutoLinearLayout all_wx,all_ai;
    private IWXAPI msgApi;
    private RecyclerView recyclerView;
    private PayUtils payUtils;

    public SimplePayPopupWindow(Context context) {
        super(context);
    }

    @Override
    protected void initViews(View contentView) {
        super.initViews(contentView);
        touchDismiss(R.id.touch_outside);
        tvGold = getView(R.id.tv_gold);
        tvMoney = getView(R.id.tv_money);
        recyclerView = getView(R.id.recyclerView);
    }

    private void initAdapter(List<PayListBean.DataBean.RecordsBean> records) {
        PayListAdapter payListAdapter = new PayListAdapter(records,mContext);
        payListAdapter.setOnItemClickListener(new PayListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position, String type) {
                switch (type){
                    case "1":
                        payUtils.ZfbH5Pay();
                        break;
                    case "2":
                        payUtils.WxH5Pay();
                        break;
                    case "3":
                        payUtils.ZfbPay();
                        break;
                    case "4":
                        payUtils.WxPay();
                        break;

                }
                dismiss();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(payListAdapter);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.popup_simple_pay;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
        dismiss();
    }


    public void setPamras(String price, String money, int id, double recordsBeanPrice) {
        mPrice = price+"";
        tvGold.setText("充值" + price);
        tvMoney.setText(money);
        payUtils = new PayUtils(mContext,recordsBeanPrice,id);

    }

    public void setPopList(List<PayListBean.DataBean.RecordsBean> records){
        initAdapter(records);
    }


}
