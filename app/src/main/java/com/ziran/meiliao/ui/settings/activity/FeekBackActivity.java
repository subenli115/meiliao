package com.ziran.meiliao.ui.settings.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonutils.KeyBordUtil;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.compressorutils.RegexUtils;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.MapUtils;

import butterknife.Bind;


public class FeekBackActivity extends BaseActivity<CommonPresenter, CommonModel> implements View.OnClickListener, CommonContract
        .View<Result> {

    @Bind(R.id.ntb)
    public NormalTitleBar ntb;
    @Bind(R.id.et_content)
    EditText etCcontent;
    @Bind(R.id.et_phone)
    EditText et_phone;

    /**
     * 入口
     *
     * @param mContext
     */
    public static void startAction(Context mContext) {
        Intent intent = new Intent(mContext, FeekBackActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_feek_back;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        ntb.setTitleText(getString(R.string.feek_back));
        ntb.setRightTitle(getString(R.string.submit));
        ntb.setTvLeftVisiable(true, true);
        ntb.setOnRightTextListener(this);
    }

    //  出来提交意见反馈
    public void onClick(View view) {
        if (!CheckUtil.check(FeekBackActivity.this, ntb)) return;
        String content = etCcontent.getText().toString().trim();
        String contactWay = et_phone.getText().toString().trim();
        if (EmptyUtils.isEmpty(content)) {
            ToastUitl.showShort(getString(R.string.please_input_advice));
            return;
        }
        if (EmptyUtils.isEmpty(contactWay)) {
            ToastUitl.showShort(getString(R.string.contact_not_null));
            return;
        }
        if (RegexUtils.isEmail(contactWay) || RegexUtils.isMobileExact(contactWay)) {
            mPresenter.postAction(ApiKey.USER_ADVICE, MapUtils.getFeekBack(content, contactWay), Result.class);
        } else {
            ToastUitl.showShort("联系方式格式错误,请输入手机号或者邮箱");
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        KeyBordUtil.hideSoftKeyboard(et_phone);
    }

    @Override
    public void returnData(Result result) {
        ToastUitl.showShort(getString(R.string.submit_ok));
        finish();
    }


}
