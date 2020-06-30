package com.ziran.meiliao.im.activity;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.model.UserInfo;
import com.ziran.meiliao.im.application.JGApplication;
import com.ziran.meiliao.im.database.GroupApplyEntry;
import com.ziran.meiliao.im.database.UserEntry;
import com.ziran.meiliao.im.utils.DialogCreator;
import com.ziran.meiliao.widget.GlideCircleTransform;

/**
 * Created by ${chenyn} on 2017/11/22.
 */

public class ApplyGroupInfoActivity extends BaseActivity {

    private ImageView mIv_avatar;
    private TextView mTv_nickName;
    private TextView mTv_sign;
    private TextView mTv_additionalMsg;
    private TextView mTv_userName;
    private TextView mTv_gender;
    private TextView mTv_birthday;
    private TextView mTv_address;
    private Button mBtn_refusal;
    private Button mBtn_agree;
    private String name;
    private LinearLayout mBtn_refuseAgree;
    private LinearLayout mLl_state;
    private TextView mTv_state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_apply_group_info);

        initView();
        initData();

    }

    private void initView() {
        initTitle(true, true, "返回", "详细信息", false, "");

        mIv_avatar = (ImageView) findViewById(R.id.iv_avatar);
        mTv_nickName = (TextView) findViewById(R.id.tv_nickName);
        mTv_sign = (TextView) findViewById(R.id.tv_sign);
        mTv_additionalMsg = (TextView) findViewById(R.id.tv_additionalMsg);
        mTv_userName = (TextView) findViewById(R.id.tv_userName);
        mTv_gender = (TextView) findViewById(R.id.tv_gender);
        mTv_birthday = (TextView) findViewById(R.id.tv_birthday);
        mTv_address = (TextView) findViewById(R.id.tv_address);

        mBtn_refuseAgree = (LinearLayout) findViewById(R.id.btn_refuseAgree);
        mBtn_refusal = (Button) findViewById(R.id.btn_refusal);
        mBtn_agree = (Button) findViewById(R.id.btn_agree);

        mLl_state = (LinearLayout) findViewById(R.id.ll_state);
        mTv_state = (TextView) findViewById(R.id.tv_state);
    }

    private void initData() {

        UserEntry user = JGApplication.getUserEntry();
        String toName = getIntent().getStringExtra("toName");
        String reason = getIntent().getStringExtra("reason");
        final GroupApplyEntry entry = GroupApplyEntry.getEntry(user, toName, JMessageClient.getMyInfo().getAppKey());

        if (entry.btnState == 0) {
            mBtn_refuseAgree.setVisibility(View.VISIBLE);
            mLl_state.setVisibility(View.GONE);
        } else if (entry.btnState == 1) {
            mBtn_refuseAgree.setVisibility(View.GONE);
            mLl_state.setVisibility(View.VISIBLE);
            mTv_state.setText("已同意");
        } else {
            mBtn_refuseAgree.setVisibility(View.GONE);
            mLl_state.setVisibility(View.VISIBLE);
            mTv_state.setText("已拒绝");
        }

        mTv_additionalMsg.setText(reason);
        final Dialog dialog = DialogCreator.createLoadingDialog(ApplyGroupInfoActivity.this, "正在加载...");
        dialog.show();
        JMessageClient.getUserInfo(toName, new GetUserInfoCallback() {
            @Override
            public void gotResult(int i, String s, UserInfo userInfo) {
                dialog.dismiss();
                if (i == 0) {
                    if (userInfo.getAvatar() != null) {
                        Glide.with(getBaseContext()).load(userInfo.getAvatarFile().getPath()).transform(new GlideCircleTransform(getBaseContext())).into(mIv_avatar);
                    }else {
                        Glide.with(getBaseContext()).load(R.drawable.jmui_head_icon).transform(new GlideCircleTransform(getBaseContext())).into(mIv_avatar);
                    }
                    mTv_nickName.setText(userInfo.getNickname());
                    mTv_sign.setText(userInfo.getSignature());
                    mTv_userName.setText(userInfo.getUserName());
                    UserInfo.Gender gender = userInfo.getGender();
                    if (gender.equals(UserInfo.Gender.male)) {
                        name = "男";
                    } else if (gender.equals(UserInfo.Gender.female)) {
                        name = "女";
                    } else {
                        name = "保密";
                    }
                    mTv_gender.setText(name);

                    mTv_address.setText(userInfo.getRegion());
                    mTv_birthday.setText(getBirthday(userInfo));
                }
            }
        });
        mBtn_refusal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                entry.btnState = 2;
                entry.save();
                finish();
            }
        });
        mBtn_agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                entry.btnState = 1;
                entry.save();
                finish();
            }
        });

    }

    public String getBirthday(UserInfo info) {
        long birthday = info.getBirthday();
        Date date = new Date(birthday);
        String personAgeByBirthDate = getPersonAgeByBirthDate(date);
        return personAgeByBirthDate;
    }
    /**
     * 根据出生日期获取人的年龄
     *
     * @return
     */
    public static String getPersonAgeByBirthDate(Date dateBirthDate){
        if (dateBirthDate == null){
            return "";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strBirthDate=dateFormat.format(dateBirthDate);

        //读取当前日期
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        int day = c.get(Calendar.DATE);
        //计算年龄
        int age = year - Integer.parseInt(strBirthDate.substring(0, 4)) - 1;
        if (Integer.parseInt(strBirthDate.substring(5,7)) < month) {
            age++;
        } else if (Integer.parseInt(strBirthDate.substring(5,7))== month && Integer.parseInt(strBirthDate.substring(8,10)) <= day){
            age++;
        }
        return String.valueOf(age);
    }
}
