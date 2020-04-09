package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonwidget.OnNoDoubleClickListener;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.DefaultOnStateChangeListener;
import com.ziran.meiliao.ui.bean.BankInfoBean;
import com.ziran.meiliao.widget.SlideLayoutNew;

/**
 * Created by Administrator on 2017/1/14.
 */

public class MyBankListAdapter extends CommonRecycleViewAdapter<BankInfoBean.DataBean> {
    private boolean isChooseBank;
    public MyBankListAdapter(Context context, int layoutId,boolean isChooseBank) {
        super(context, layoutId);
        this.isChooseBank = isChooseBank;
    }

    @Override
    public void convert(final ViewHolderHelper helper,final BankInfoBean.DataBean bean, int position) {
        ImageView ivBg = helper.getView(R.id.iv_item_my_bank_list_bg);
        ImageLoaderUtils.display(mContext, ivBg, bean.getBankPicture());
        helper.setText(R.id.tv_item_my_bank_list_bank, bean.getBankName());
        helper.setText(R.id.tv_item_my_bank_list_card_no, bean.getBankCardNo());
        helper.setText(R.id.tv_item_my_bank_list_card_type, bean.getBankCardType());
        final SlideLayoutNew slideLayout = (SlideLayoutNew) helper.getConvertView();
        slideLayout.setCanSlideLayout(!isChooseBank);
        slideLayout.setOnStateChangeListener(new DefaultOnStateChangeListener());

        slideLayout.setOnMenuClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                RxManagerUtil.post(AppConstant.RXTag.UNBIND_BANK,bean.getBankCardNo());
            }
        });
//        TextView slideView = helper.getView(R.id.menu);
//        slideView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

}
