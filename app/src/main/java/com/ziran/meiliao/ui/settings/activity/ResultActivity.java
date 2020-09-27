package com.ziran.meiliao.ui.settings.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonutils.KeyBordUtil;
import com.ziran.meiliao.common.commonutils.TimeUtil;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;

import butterknife.Bind;
import butterknife.OnClick;

public class ResultActivity extends BaseActivity<CommonPresenter, CommonModel> implements View.OnClickListener, CommonContract
        .View<Result> {

    @Bind(R.id.tv_money)
    TextView tvMoney;
    @Bind(R.id.tv_time)
    TextView tvTime;

    /**
     * 入口
     *
     * @param mContext
     */
    public static void startAction(Context mContext,String money) {
        Intent intent = new Intent(mContext, ResultActivity.class);
        intent.putExtra("money",money);
        mContext.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.ac_capitalextract_result;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        if(getIntent()!=null){
            String money = getIntent().getStringExtra("money");
            tvMoney.setText(money+"元");
        }
        tvTime.setText(TimeUtil.getCurrentDate(TimeUtil.dateFormatYMDHMS_q));
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void returnData(Result result) {
        ToastUitl.showShort(getString(R.string.submit_ok));
        finish();
    }


    //点击监听
    @OnClick({R.id.tv_qd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_qd:
                finish();
                break;

        }
    }
}