package com.ziran.meiliao.ui.priavteclasses.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.bean.AuthorBean;
import com.ziran.meiliao.ui.bean.LiveIncomeBean;
import com.ziran.meiliao.ui.priavteclasses.fragment.ProfitFragment;
import com.ziran.meiliao.ui.settings.activity.WithDrawalsActivity;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 *  收益情况
 */

public class ProfitActivity extends BaseActivity{


    @Bind(R.id.ntb)
    NormalTitleBar ntb;
    @Bind(R.id.tv_sjk_live_room_profit_total_profit)
    TextView tvSjkLiveRoomProfitTotalProfit;
    @Bind(R.id.tv_sjk_live_room_profit_withdrawals_amount)
    TextView tvSjkLiveRoomProfitWithdrawalsAmount;


    @Override
    public int getLayoutId() {
        return R.layout.activity_sjk_live_room_profit;
    }

    @Override
    public void initPresenter() {

    }
    private LiveIncomeBean.DataBean mLiveIncomeData;
    private   AuthorBean authorBean;
    @Override
    public void initView() {
        ntb.setTitleText("收益");
        ntb.setTvLeftVisiable(true, true);
         authorBean = (AuthorBean) getParcelable(AppConstant.ExtraKey.AUTHOR_DATA);
        LogUtils.logd("authorBean" + authorBean);
        if (authorBean == null) return;
        mLiveIncomeData  =  (LiveIncomeBean.DataBean) getParcelable("liveIncome");
        Bundle bundle   = new Bundle();
        tvSjkLiveRoomProfitTotalProfit.setText(authorBean.getIncome());
        tvSjkLiveRoomProfitWithdrawalsAmount.setText(authorBean.getAvailableCash());
        if (mLiveIncomeData!=null){
            bundle.putParcelable("liveIncome",mLiveIncomeData);
        }
        initFragment(new ProfitFragment(),bundle);

        mRxManager.on(ApiKey.LIVE_GET_MONEY_SUPPLY, new Action1<String>() {
            @Override
            public void call(String s) {
                authorBean.setAvailableCash(s);
                tvSjkLiveRoomProfitWithdrawalsAmount.setText(authorBean.getAvailableCash());
            }
        });
    }


    @OnClick({R.id.tv_sjk_live_room_profit_withdrawals, R.id.tv_sjk_live_room_profit_withdrawals_detail})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_sjk_live_room_profit_withdrawals://提现
                startActivity(WithDrawalsActivity.class,getIntent().getExtras());
                break;
            case R.id.tv_sjk_live_room_profit_withdrawals_detail://查看提现明细
                startActivity(WithDrawalsListActivity.class );
                break;
        }
    }


}
