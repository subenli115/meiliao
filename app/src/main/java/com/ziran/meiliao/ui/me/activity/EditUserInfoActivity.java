package com.ziran.meiliao.ui.me.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.ui.bean.UserBean;
import com.ziran.meiliao.utils.Utils;
import com.ziran.meiliao.widget.ItemGroupView;
import com.ziran.meiliao.widget.pupop.PopupWindowUtil;
import com.ziran.meiliao.widget.pupop.VideoCouponTipsPopupWindow;

import butterknife.Bind;
import butterknife.OnClick;

public class EditUserInfoActivity extends BaseActivity {

    @Bind(R.id.tv_me_main_new_choose)
    ItemGroupView tv_me_main_new_choose;
    @Bind(R.id.tv_me_main_new_region)
    ItemGroupView tv_me_main_new_region;
    @Bind(R.id.tv_me_main_new_sex)
    ItemGroupView tv_me_main_new_sex;
    @Bind(R.id.tv_me_main_new_age)
    ItemGroupView tv_me_main_new_age;
    @Bind(R.id.tv_me_main_new_nick)
    ItemGroupView tv_me_main_new_nick;
    @Bind(R.id.tv_me_main_new_sign)
    ItemGroupView tv_me_main_new_sign;
    public final int REQUEST_CODE_B = 1;
    private UserBean.DataBean bean;

    public static void startAction() {
        Activity activity = AppManager.getAppManager().currentActivity();
        Intent intent = new Intent(activity, EditUserInfoActivity.class);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_info;
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
    }

    @Override
    public void initView() {
            update();
    }

    //点击监听
    @OnClick({R.id.tv_me_main_new_nick, R.id.tv_me_main_new_sex, R.id.tv_me_main_new_age, R.id.tv_me_main_new_region, R.id
            .tv_me_main_new_choose, R.id.tv_me_main_new_sign})
    public void onClick(View view) {
//        if (!MyAPP.isLogin(mContext)) { //如果没有登录则跳转到登录界面
//            return;
//        }
        if (Utils.isFastDoubleClick()) {
            return;
        }
        switch (view.getId()) {
            case R.id.tv_me_main_new_nick:
                SetNoteActivity.startAction("nick",REQUEST_CODE_B, "");
                break;
            case R.id.tv_me_main_new_age:
                SetNoteActivity.startAction("age",REQUEST_CODE_B, "");
                break;
            case R.id.tv_me_main_new_sign:
                SetNoteActivity.startAction("sign",REQUEST_CODE_B, bean.getIntroduce());
                break;
            case R.id.tv_me_main_new_choose:
                VideoCouponTipsPopupWindow videoCouponTipsPopupwindow = new VideoCouponTipsPopupWindow(this, tv_me_main_new_choose,mRxManager);
                PopupWindowUtil.show(this, videoCouponTipsPopupwindow);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_B: //返回的结果是来自于Activity B
                if (resultCode == Activity.RESULT_OK) {
                    update();
                }
                break;
            default:
                break;
        }
    }

    private void update() {
         bean = MyAPP.getmUserBean();
        if (bean != null) {
            tv_me_main_new_region.setRigthText(bean.getRegion());
            if (bean.getSex() == 1) {
                tv_me_main_new_sex.setRigthText("男");
            } else {
                tv_me_main_new_sex.setRigthText("女");
            }
            if (bean.getIntroduce()!= null) {
                tv_me_main_new_sign.getRightTextView().setMaxLines(1);
                tv_me_main_new_sign.getRightTextView().setMaxEms(8);
                tv_me_main_new_sign.getRightTextView().setEllipsize(TextUtils.TruncateAt.END);
                tv_me_main_new_sign.setRigthText(bean.getIntroduce() + "");
                if(bean.getIntroduce().equals("")){
                    tv_me_main_new_sign.setRigthText("没有填写");
                }
            }else {
                tv_me_main_new_sign.setRigthText("没有填写");
            }
            if(bean.getSex() == 1){
                if(bean.getPreference().equals("1")){
                    tv_me_main_new_choose.setRigthText("同性"+"(男)");
                }else {
                    tv_me_main_new_choose.setRigthText("异性"+"(女)");
                }
            }else {
                if(bean.getPreference().equals("1")){
                    tv_me_main_new_choose.setRigthText("异性"+"(男)");
                }else {
                    tv_me_main_new_choose.setRigthText("同性"+"(女)");
                }
            }
            tv_me_main_new_age.setRigthText(bean.getAge() + "岁");
            tv_me_main_new_nick.setRigthText(bean.getNickname());
        }
    }
}