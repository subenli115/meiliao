package com.ziran.meiliao.ui.me.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.me.fragment.MyWorkshopsApplyRefundFragment;
import com.ziran.meiliao.ui.me.fragment.MyWorkshopsRefundResultFragment;


/**
 * @author 吴祖清
 * @version $R* @createTime 2017/8/10 14:57
 * @des ${shenq申请退款界面}
 * @updateAuthor $Author$
 * @updateDate 2017/8/10$
 * @updateDes ${TODO}
 */

public class MyApplyRefundActivity extends BaseActivity {
    public static final int FROM_TYPE_APPLY_REFUND = 0;
    public static final int FROM_TYPE_APPLY_REFUND_RESULT = 1;

    public static void startAction(int type, String orderId) {
        Activity activity = AppManager.getAppManager().currentActivity();
        Intent intent = new Intent(activity, MyApplyRefundActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(AppConstant.ExtraKey.FROM_TYPE, type);
        bundle.putString(AppConstant.ExtraKey.FROM_ID, orderId);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_one_layout;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    protected void initBundle(Bundle extras) {
        int formType = extras.getInt(AppConstant.ExtraKey.FROM_TYPE, 0);
        switch (formType) {
            case FROM_TYPE_APPLY_REFUND:
                initFragment(new MyWorkshopsApplyRefundFragment());
                break;
            case FROM_TYPE_APPLY_REFUND_RESULT:
                initFragment(new MyWorkshopsRefundResultFragment());
                break;
        }
    }

    @Override
    public void initView() {
    }

}
