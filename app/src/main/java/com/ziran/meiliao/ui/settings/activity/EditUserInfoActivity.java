package com.ziran.meiliao.ui.settings.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.DisplayUtil;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonutils.KeyBordUtil;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.commonwidget.OnNoDoubleClickListener;
import com.ziran.meiliao.common.compressorutils.RegexUtils;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.ui.bean.UpdateUserHeadBean;
import com.ziran.meiliao.ui.settings.contract.UpdateUserInfoContract;
import com.ziran.meiliao.ui.settings.model.UpdateUserInfoModel;
import com.ziran.meiliao.ui.settings.presenter.UpdateUserInfoPresenter;
import com.ziran.meiliao.widget.CustomEditText;

import butterknife.Bind;

/**
 * 编辑用户资料界面
 * Created by Administrator on 2017/1/10.
 */

public class EditUserInfoActivity extends BaseActivity<UpdateUserInfoPresenter, UpdateUserInfoModel> implements UpdateUserInfoContract.View {
    
    @Bind(R.id.ntb)
    public NormalTitleBar ntb;
    
    public static final int TYPE_TEXT = 1;
    public static final int TYPE_EMAIL = 2;
    //编辑框
    @Bind(R.id.et_edit_userinfo_nick)
    CustomEditText etNickName;
    @Bind(R.id.et_edit_userinfo_email)
    CustomEditText etEmail;
    
    //编辑的类型
    private int type;
    //编辑的内容
    private String content;
    private CustomEditText etitText;
    
    public static void startAction (Context mContext, int type, String content) {
        Intent intent = new Intent(mContext, EditUserInfoActivity.class);
        intent.putExtra(AppConstant.KEY_EDIT_USERINFO_FROM, type);          //编辑的类型(1 昵称 2 邮箱)
        intent.putExtra(AppConstant.ExtraKey.KEY_CONTENT, content);    //之前的内容
        mContext.startActivity(intent);
    }
    
    @Override
    public int getLayoutId () {
        return R.layout.activity_edit_userinfo;
    }
    
    @Override
    public void initPresenter () {
        mPresenter.setVM(this, mModel);
    }
    
    @Override
    public void initView () {
        DisplayUtil.measureSoftKeyBoardHeight(this);
        //获取从上个界面传来的数据
        type = getIntentExtra(getIntent(), AppConstant.KEY_EDIT_USERINFO_FROM, 0);
        content = getIntentExtra(getIntent(), AppConstant.ExtraKey.KEY_CONTENT);
        switch (type) {
            case TYPE_EMAIL:    //如果是邮箱则修改
                ntb.setTitleText(getString(R.string.edit_userinfo));
                etNickName.setVisibility(View.GONE);
                etEmail.setVisibility(View.VISIBLE);
                etitText = etEmail;
                break;
            case TYPE_TEXT:
                ntb.setTitleText(getString(R.string.nick_name_title));
                etEmail.setVisibility(View.GONE);
                etNickName.setVisibility(View.VISIBLE);
                etitText = etNickName;
                break;
        }
        final TextView rightTitle = ntb.setRightTitle(getString(R.string.save_title));
        rightTitle.setEnabled(false);
        if (!"未绑定".equals(content)){
            etitText.setContent(content);
        }
        etitText.setOnContentChangeListener(new CustomEditText.OnContentChangeListener() {
            @Override
            public void change (boolean change) {
                rightTitle.setEnabled(change);
            }
        });
        ntb.setTvLeftVisiable(true, true);
        
        ntb.setOnRightTextListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick (View v) {
                String newStr = etitText.getContent();
                if (type == 2) {
                    if (!RegexUtils.isEmail(newStr)) {
                        ToastUitl.showShort(getString(R.string.input_ok_email));
                    } else {
//                        Map<String, String> maps = MapUtils.getUpdateUserInfo(null, "", newStr);
//                        mPresenter.updateUserInfo(maps);
                    }
                } else if (type == 1) {
//                    Map<String, String> maps = MapUtils.getUpdateUserInfo(newStr, "", "");
//                    mPresenter.updateUserInfo(maps);
                }
            }
        });
        KeyBordUtil.showSoftKeyboard(etitText);
    }
    
    @Override
    public void returnUserHead (UpdateUserHeadBean result) {
        
    }
    
    @Override
    public void returnUserInfo (Result result) {
        if (type == TYPE_TEXT) {
            MyAPP.getUserInfo().setNickName(etitText.getContent());
        } else if (type == TYPE_EMAIL) {
            MyAPP.getUserInfo().setEmail(etitText.getContent());
        }
        mRxManager.post(AppConstant.RXTag.EDIT_USER_INFO,type);
        finish();
    }

}
