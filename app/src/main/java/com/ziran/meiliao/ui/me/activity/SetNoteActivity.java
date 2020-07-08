package com.ziran.meiliao.ui.me.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.citypicker.citylist.widget.ClearEditText;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.CheckNameBean;
import com.ziran.meiliao.ui.bean.UserBean;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.CustomEditText;

import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;

/**
 * Created by ${chenyn} on 2017/5/7.
 */

public class SetNoteActivity extends BaseActivity {

    @Bind(R.id.ed_text)
    ClearEditText editText;
    private String type;
    @Bind(R.id.rl_nike)
    RelativeLayout rl_nike;
    @Bind(R.id.tv_length)
    TextView tv_length;
    @Bind(R.id.tv_hint)
    TextView tvHint;
    @Bind(R.id.et_content)
    CustomEditText et_content;
    @Bind(R.id.tv_save)
    TextView tvSave;
    @Bind(R.id.ntb_title)
    NormalTitleBar ntb;
    private String introduce;
    private UserInfo mMyInfo;

    public static void startAction(String type, int code, String introduce) {
        Activity activity = AppManager.getAppManager().currentActivity();
        Intent intent = new Intent(activity, SetNoteActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("type",type);
        bundle.putString("introduce",introduce);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent,code);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_note_text;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        UserBean.DataBean bean = MyAPP.getmUserBean();
        Bundle bundle = getBundle(getIntent());
        if(bundle!=null){
             type = bundle.getString("type");
            introduce = bundle.getString("introduce");
        }
        if(bean!=null){
            if(type.equals("sign")){
                ntb.setTitleText("个性签名");
                et_content.setVisibility(View.VISIBLE);
                editText.setVisibility(View.GONE);
                if(introduce!=null){
                    et_content.setText(introduce);
                }
                tv_length.setText("可以输入40个字");
            }else if(type.equals("nick")){
                ntb.setTitleText("昵称");
                editText.setText(bean.getNickname());
                tvHint.setVisibility(View.VISIBLE);
            }else {
                ntb.setTitleText("年龄");
                editText.setText(bean.getAge()+"");
                InputFilter[] filters = {new InputFilter.LengthFilter(2)};
                editText.setFilters(filters);
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                tv_length.setText("请输入1-99的数字");
            }
        }
    }
    private void checkNick() {
        if(editText.getText().toString().equals("")){
            tvHint.setText("昵称不能为空");
            tvHint.setTextColor(Color.parseColor("#FF4F68"));
            return;
        }

        OkHttpClientManager.getAsyncHead(ApiKey.ADMIN_USER_EXISTENCE,editText.getText().toString(), MyAPP.getAccessToken(),new
                NewRequestCallBack<CheckNameBean>(CheckNameBean.class) {
                    @Override
                    public void onSuccess(CheckNameBean result) {
                        tvHint.setVisibility(View.VISIBLE);
                        if(!result.getData().isSuccess()){
                            //不存在
                            edit();
                        }else {
                            tvHint.setText("该昵称已被使用");
                            tvHint.setTextColor(Color.parseColor("#FF4F68"));
                        }
                    }
                    @Override
                    public void onError(String msg, int code) {
                        tvHint.setText("昵称不合法");
                        tvHint.setTextColor(Color.parseColor("#FF4F68"));
                        super.onError(msg, code);
                    }
                });
        // 失去焦点
    }

    //点击监听
    @OnClick({R.id.tv_save})
    public void onClick(View view) {
//        if (!MyAPP.isLogin(mContext)) { //如果没有登录则跳转到登录界面
//            return;
//        }
        switch (view.getId()) {
            case R.id.tv_save:
                if(type.equals("nick")){

                    checkNick();
                }else{
                    edit();
                }
                break;

        }
    }
    public void updateImUserInfo() {
        mMyInfo = JMessageClient.getMyInfo();
        if(mMyInfo!=null&&type.equals("nick")){
            mMyInfo.setNickname(editText.getText().toString());
            JMessageClient.updateMyInfo(UserInfo.Field.all, mMyInfo, new BasicCallback() {
                @Override
                public void gotResult(int responseCode, String responseMessage) {
                }
            });
        }
    }
    private void edit() {
        updateImUserInfo();
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("id", MyAPP.getUserId());
        if(type.equals("nick")){
            defMap.put("nickname",editText.getText().toString());
        }else if(type.equals("sign")){
            defMap.put("introduce",et_content.getContent());
        }else {
            defMap.put("age",editText.getText().toString());
        }
        OkHttpClientManager.putAsyncAddHead(ApiKey.ADMIN_USER_UPDATE, defMap, new
                NewRequestCallBack<UserBean>(UserBean.class) {
                    @Override
                    public void onSuccess(UserBean result) {
                        ToastUitl.showShort(result.getResultMsg());
                        UserBean.DataBean data = result.getData();
                        data.setUserAccount(MyAPP.getmUserBean().getUserAccount());
                        MyAPP.setmUserBean(data);
                        mRxManager.post(AppConstant.RXTag.UPDATE_USER, "");
                        Intent intent = new Intent();
                        setResult(Activity.RESULT_OK, intent);
                        // RESULT_OK就是一个默认值，=-1，它说OK就OK吧
                        finish();
                    }

                    @Override
                    public void onError(String msg, int code) {
                        ToastUitl.showShort(msg);
                    }
                });
    }


}
