package com.ziran.meiliao.ui.settings.activity;

import android.content.Intent;
import android.view.View;

import com.citypicker.citylist.sortlistview.SortModel;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.permission.PermissionUtil;
import com.ziran.meiliao.ui.base.PermissionActivity;
import com.ziran.meiliao.ui.bean.CheckCardBean;
import com.ziran.meiliao.ui.priavteclasses.fragment.EditCardCodeFragment;
import com.ziran.meiliao.ui.priavteclasses.fragment.EditCardMessageFragment;

import butterknife.Bind;

/**
 * 优惠劵界面
 * Created by Administrator on 2017/1/4.
 */

public class AddBankCardActivity extends PermissionActivity {
    @Bind(R.id.ntb)
    public NormalTitleBar ntb;

    private EditCardCodeFragment mEditCardCodeFragment;
    private EditCardMessageFragment mEditCardMessageFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_common_frame;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        setPermission(PermissionUtil.getSMS());
        ntb.setTvLeftVisiable(true);
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentStep==STEP_EDIT_CARD_CODE){
                    finish();
                }else if (currentStep==STEP_EDIT_CARD_MESSAGE){
                    setStep(STEP_EDIT_CARD_CODE);
                }
            }
        });
        ntb.setTitleText(getString(R.string.add_card_code));
        if (mEditCardCodeFragment==null){
            mEditCardCodeFragment = new EditCardCodeFragment();
        }
        if (mEditCardMessageFragment==null){
            mEditCardMessageFragment = new EditCardMessageFragment();
        }
        initFragment(mEditCardCodeFragment);
    }
    public static final int STEP_EDIT_CARD_CODE = 0;
    public static final int STEP_EDIT_CARD_MESSAGE = 1;
    private int currentStep = STEP_EDIT_CARD_CODE ;
    public void setStep(int step){
        currentStep = step;
        switch (step){
            case STEP_EDIT_CARD_CODE:
                initFragment(mEditCardCodeFragment);
                ntb.setTitleText(getString(R.string.add_card_code));
                break;
            case STEP_EDIT_CARD_MESSAGE :

                initFragment(mEditCardMessageFragment);
                ntb.setTitleText(getString(R.string.add_card_message));
                break;
        }
    }

    public void setCardNo(CheckCardBean.DataBean cardNo) {
        mEditCardMessageFragment.setCardNo(cardNo);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (null != data) {
            SortModel cityData = data.getParcelableExtra("cityData");
            if (EmptyUtils.isNotEmpty(cityData)) {
                if (mEditCardMessageFragment != null) {
                    mEditCardMessageFragment.setAreaCode(cityData);
                }
            }
        }
    }

    public void setPhone(String phone) {
        mEditCardMessageFragment.setPhone(phone);
    }
}
