package com.ziran.meiliao.ui.priavteclasses.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.bean.BankInfoBean;
import com.ziran.meiliao.ui.priavteclasses.fragment.MyBankListFragment;
import com.ziran.meiliao.ui.settings.activity.AddBankCardActivity;

import butterknife.Bind;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/7 16:09
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/8/7$
 * @updateDes ${TODO}
 */

public class MyBankListActivity extends BaseActivity {
    @Bind(R.id.rl_rootView)
    View rootView;
    @Bind(R.id.ntb)
    NormalTitleBar mNormalTitleBar;
    private MyBankListFragment bankListFragment;
    private String title = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_common_frame;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        rootView.setBackgroundColor(getResources().getColor(R.color.gray_deep));
        mNormalTitleBar.setStyle(2);
        Bundle bundle = getBundle();
        if (bundle != null) {
             title = bundle.getString(AppConstant.ExtraKey.EXTRAS_TITLE);
            mNormalTitleBar.setTitleText(title);
        }
        mNormalTitleBar.setTvLeftVisiable(true, true);
        mNormalTitleBar.setRightImagSrc(R.mipmap.btn_add_white);
        mNormalTitleBar.setOnRightImagListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(AddBankCardActivity.class, 100);
            }
        });
        bankListFragment = new MyBankListFragment();
        initFragment(bankListFragment);
    }

    public String getNtbTitle() {
        return title;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            BankInfoBean.DataBean bankInfo = data.getParcelableExtra(AppConstant.ExtraKey.BANK_INFO);
            LogUtils.logd("bankInfo" + bankInfo);
            bankListFragment.addBank(bankInfo);
        }
    }
}
