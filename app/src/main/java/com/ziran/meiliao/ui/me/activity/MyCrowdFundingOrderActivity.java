package com.ziran.meiliao.ui.me.activity;

import android.os.Bundle;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.me.fragment.MyCrowdFundingLaunchOrderDetailFragment;
import com.ziran.meiliao.ui.me.fragment.MyCrowdFundingPartakeOrderDetailFragment;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/10 14:57
 * @des ${发起众筹订单}
 * @updateAuthor $Author$
 * @updateDate 2017/8/10$
 * @updateDes ${TODO}
 */

public class MyCrowdFundingOrderActivity extends BaseActivity {

    public static final int FROM_TYPE_LAUNCH = 2;
    public static final int FROM_TYPE_PARTAKE = 1;



    @Override
    public int getLayoutId() {
        return R.layout.activity_one_layout;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {

    }

    @Override
    protected void initBundle(Bundle extras) {
        int formType = extras.getInt(AppConstant.ExtraKey.FROM_TYPE, 0);
        //getOrderTypeId // 1 发起  2 购买
        switch (formType) {
            case FROM_TYPE_PARTAKE://发起
                initFragment(new MyCrowdFundingPartakeOrderDetailFragment());
                break;
            case FROM_TYPE_LAUNCH://参与
                initFragment(new MyCrowdFundingLaunchOrderDetailFragment());
                break;
        }
    }

}
