package com.ziran.meiliao.widget.pupop;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.bean.GiftBean;
import com.ziran.meiliao.ui.priavteclasses.adapter.GiftAdapter;
import com.ziran.meiliao.ui.settings.activity.RechargeActivity;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/7 11:33
 * @des 赠送礼物的窗口
 * @updateAuthor $Author$
 * @updateDate 2017/6/7$
 * @updateDes ${TODO}
 */


public class GiveGiftPopupWindow extends BasePopupWindow {
    private TextView tvBalance;
    private RecyclerView mRecyclerView;
    private GiftAdapter mGiftAdapter;

    public GiveGiftPopupWindow(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.popupw_give_gift;
    }

    @Override
    protected void initViews(View contentView) {
        tvBalance = getView(R.id.tv_popuw_give_gift_balance);
        mRecyclerView = getView(R.id.recyclerView);
        touchDismiss(R.id.touch_outside);
        setOnClickListener(R.id.tv_popuw_give_gift_give);
        setOnClickListener(R.id.tv_popuw_give_gift_recharge);

    }
    public void addBottomMargin(){
        setBottomMargin(getView(R.id.contentView));
    }
    public void setDatas(List<GiftBean.DataBean> datas) {
        if (mGiftAdapter == null) {
            mGiftAdapter = new GiftAdapter(mContext, R.layout.item_sjk_live_gift);
            mGiftAdapter.setHeadCount(0);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            mRecyclerView.setAdapter(mGiftAdapter);
            mGiftAdapter.setOnItemClickListener(new OnItemClickListener<GiftBean.DataBean>() {
                @Override
                public void onItemClick(ViewGroup parent, View view, GiftBean.DataBean bean, int position) {
                    mGiftAdapter.changeCheck(position);
                }

                @Override
                public boolean onItemLongClick(ViewGroup parent, View view, GiftBean.DataBean o, int position) {
                    return false;
                }
            });
        }
        mGiftAdapter.replaceAll(datas);
    }

    private int mBalance = -1;

    public void setBalance(int balance) {
        if (balance >= 0 && mBalance != balance) {
            mBalance = balance;
            tvBalance.setText(String.format("余额: %d", balance));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_popuw_give_gift_give:
                GiftBean.DataBean mGiftAdapterSelect = mGiftAdapter.getSelect();
                if (mGiftAdapterSelect == null) {
                    ToastUitl.showShort("您还没有选中礼物");
                } else if (mBalance < mGiftAdapterSelect.getNeedCoin()) {
                    ToastUitl.showShort("您的正念币不够,请前往充值");
                } else {
                    RxManagerUtil.post(AppConstant.RXTag.GIVE_GIFT, mGiftAdapterSelect);
                }
                break;
            case R.id.tv_popuw_give_gift_recharge:
                RechargeActivity.startAction(mContext, String.valueOf(mBalance));
                dismiss();
                break;
        }
    }


}