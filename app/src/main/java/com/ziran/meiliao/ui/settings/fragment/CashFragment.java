package com.ziran.meiliao.ui.settings.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpFragment;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.ExchangeBean;
import com.ziran.meiliao.ui.bean.PointsListBean;
import com.ziran.meiliao.ui.settings.adapter.AmountPointsAdapter;
import com.ziran.meiliao.utils.MapUtils;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

import static com.ziran.meiliao.constant.ApiKey.PURSE_GETUSERSCORE;
import static com.ziran.meiliao.constant.ApiKey.USERCENTER_BUYBYSCORE;

public class CashFragment extends CommonHttpFragment<CommonPresenter, CommonModel> implements CommonContract.View<Result>{


    @Bind(R.id.gv_recharge_amount)
    GridView gvRechargeAmount;
    private AmountPointsAdapter mAmountAdapter;
    private PointsListBean.DataBean mDataBean;

    @Override
    protected int getLayoutResource() {
        return 0;
    }

    @Override
    public void returnData(Result result) {
        if (result instanceof PointsListBean) {
            mDataBean = ((PointsListBean) result).getData();
            List<PointsListBean.DataBean.ScoreListBean> list = mDataBean.getScoreList();
            list.get(0).setSelect(true);
            mAmountAdapter.replaceAll(list);
        }else {
            ToastUitl.showShort("兑换成功");
            reflash();
        }

    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {
        super.initView();
        mAmountAdapter = new AmountPointsAdapter(getContext(), R.layout.item_grid_amount);
        gvRechargeAmount.setAdapter(mAmountAdapter);

        gvRechargeAmount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mAmountAdapter.changeSelect(position);
            }
        });
        reflash();


//        PurseListCoinBean.DataBean.ListBean dataBean = mAmountAdapter.getSelect();

    }

    private void reflash() {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        mPresenter.postData(PURSE_GETUSERSCORE, defMap, PointsListBean.class);
    }


    @OnClick({ R.id.iv_recharge_req})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_recharge_req:
                PointsListBean.DataBean.ScoreListBean dataBean = mAmountAdapter.getSelect();
                Map<String, String> defMap = MapUtils.getDefMap(true);
                defMap.put("goodsId",dataBean.getGoodsId()+"");
                defMap.put("type","4");
                mPresenter.postData(USERCENTER_BUYBYSCORE, defMap, ExchangeBean.class);
                break;
        }
}
}
