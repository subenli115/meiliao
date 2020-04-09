package com.ziran.meiliao.ui.me.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.bean.ActisData;
import com.ziran.meiliao.ui.me.fragment.MyWorkshopsOrderFragment;
import com.ziran.meiliao.ui.me.fragment.MyWorkshopsOrderPingFragment;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/10 14:57
 * @des ${工作坊订单界面}
 * @updateAuthor $Author$
 * @updateDate 2017/8/10$
 * @updateDes ${TODO}
 */


public class MyWorkshopsOrderActivity extends BaseActivity {

    public static final int FROM_TYPE_ORDER = 0;
    public static final int FROM_TYPE_ORDER_PING = 1;
    public static final int FROM_TYPE_REFUND = 3;

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
        switch (formType) {
            case FROM_TYPE_ORDER:
                initFragment(new MyWorkshopsOrderFragment());
                break;
            case FROM_TYPE_ORDER_PING:
                initFragment(new MyWorkshopsOrderPingFragment());
                break;
//            case FROM_TYPE_FOLLOW:
//                initFragment(new MyCrowdFundingDetailFollowFragment());
//                break;
        }
    }

    public static void startAction(Context context, ActisData dataBean) {
                Intent intent=new Intent(context,MyWorkshopsOrderActivity.class);
                Bundle bundle = new Bundle();
                 bundle.putParcelable("projectList", dataBean);
                  intent.putExtras(bundle);
                intent.putExtra(AppConstant.ExtraKey.FROM_TYPE,FROM_TYPE_ORDER);
                 context.startActivity(intent);
    }
}
