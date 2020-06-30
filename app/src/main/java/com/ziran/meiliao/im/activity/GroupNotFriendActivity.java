package com.ziran.meiliao.im.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.UserInfo;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.im.application.JGApplication;
import com.ziran.meiliao.im.entity.Event;
import com.ziran.meiliao.im.entity.EventType;
import com.ziran.meiliao.im.utils.DialogCreator;
import com.ziran.meiliao.im.utils.ToastUtil;
import com.ziran.meiliao.widget.GlideCircleTransform;

/**
 * Created by ${chenyn} on 2017/5/10.
 */

public class GroupNotFriendActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mIv_friendPhoto;
    private TextView mTv_noteName;
    private TextView mTv_sign;
    private TextView mTv_userName;
    private TextView mTv_gender;
    private TextView mTv_birthday;
    private TextView mTv_address;
    private Button mBtn_add_friend;
    private Button mBtn_send_message;
    private String mUserName;
    private UserInfo mUserInfo;
    private String mMyName;
    private RelativeLayout mRl_NickName;
    private TextView mTv_NickName;
    private String mNickName;
    private String mAvatarPath;
    private TextView mTv_additionalMsg;
    private RelativeLayout mLl_additional;
    private ImageButton mReturnBtn;
    private ImageView mIvMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_not_friend);

        initView();
        initData();
    }

    private void initData() {
        final Dialog dialog = DialogCreator.createLoadingDialog(this, this.getString(R.string.jmui_loading));
        dialog.show();
        mUserName = getIntent().getStringExtra(JGApplication.TARGET_ID);
        String reason = getIntent().getStringExtra("reason");
        if (reason == null) {
            mLl_additional.setVisibility(View.GONE);
        } else {
            mLl_additional.setVisibility(View.VISIBLE);
            mTv_additionalMsg.setText("附加消息: " + reason);
        }
        JMessageClient.getUserInfo(mUserName, new GetUserInfoCallback() {
            @Override
            public void gotResult(int responseCode, String responseMessage, UserInfo info) {
                if (responseCode == 0) {
                    mUserInfo = info;
                    File avatar = info.getAvatarFile();
                    if (avatar != null) {
                        mAvatarPath = avatar.getAbsolutePath();
                        Glide.with(getBaseContext()).load(mAvatarPath).transform(new GlideCircleTransform(getBaseContext())).into(mIv_friendPhoto);
                    } else {
                        Glide.with(getBaseContext()).load(R.drawable.rc_default_portrait).transform(new GlideCircleTransform(getBaseContext())).into(mIv_friendPhoto);
                    }
                    mNickName = info.getNickname();
                    //有备注 有昵称
                    mTv_userName.setText(info.getNickname());
                    mTv_sign.setText(info.getSignature());
                    if (info.getGender() == UserInfo.Gender.male) {
                        mTv_gender.setText("男");
                    } else if (info.getGender() == UserInfo.Gender.female) {
                        mTv_gender.setText("女");
                    } else {
                        mTv_gender.setText("未知");
                    }
                    mTv_birthday.setText(getBirthday(info)+"岁");
                }
                dialog.dismiss();
            }
        });

        UserInfo myInfo = JMessageClient.getMyInfo();
        mMyName = myInfo.getNickname();
        if (TextUtils.isEmpty(mMyName)) {
            mMyName = myInfo.getUserName();
        }
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
    private void initView() {
        mReturnBtn = (ImageButton) findViewById(R.id.return_btn);
        mIvMore = (ImageView) findViewById(R.id.iv_more);
        mIv_friendPhoto = (ImageView) findViewById(R.id.iv_friendPhoto);
        mTv_sign = (TextView) findViewById(R.id.tv_sign);
        mTv_userName = (TextView) findViewById(R.id.tv_userName);
        mTv_gender = (TextView) findViewById(R.id.tv_gender);
        mTv_birthday = (TextView) findViewById(R.id.tv_birthday);
        mBtn_add_friend = (Button) findViewById(R.id.btn_add_friend);
        mBtn_send_message = (Button) findViewById(R.id.btn_send_message);
        mRl_NickName = (RelativeLayout) findViewById(R.id.rl_nickName);
        mTv_NickName = (TextView) findViewById(R.id.tv_nick);

        mTv_additionalMsg = (TextView) findViewById(R.id.tv_additionalMsg);
        mLl_additional = (RelativeLayout) findViewById(R.id.ll_additional);

        mBtn_add_friend.setOnClickListener(this);
        mBtn_send_message.setOnClickListener(this);
        mReturnBtn.setOnClickListener(this);
        mIvMore.setOnClickListener(this);
    }

    public String getBirthday(UserInfo info) {
        long birthday = info.getBirthday();
        Date date = new Date(birthday);
        String personAgeByBirthDate = getPersonAgeByBirthDate(date);
        return personAgeByBirthDate;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_add_friend:
                if (mUserInfo.isFriend()) {
                    ToastUtil.shortToast(GroupNotFriendActivity.this, "对方已经是你的好友");
                } else {
                    intent.setClass(GroupNotFriendActivity.this, VerificationActivity.class);
                    //对方信息
                    intent.putExtra("detail_add_friend", mUserName);
                    intent.putExtra("detail_add_nick_name", mNickName);
                    intent.putExtra("detail_add_avatar_path", mAvatarPath);
                    //自己的昵称或者是用户名
                    intent.putExtra("detail_add_friend_my_nickname", mMyName);
                    intent.setFlags(1);
                    startActivity(intent);
                }
                break;
            case R.id.btn_send_message:
                intent.setClass(GroupNotFriendActivity.this, ChatActivity.class);
                //创建会话
                intent.putExtra(JGApplication.TARGET_ID, mUserInfo.getUserName());
                intent.putExtra(JGApplication.TARGET_APP_KEY, mUserInfo.getAppKey());
                String notename = mUserInfo.getNotename();
                if (TextUtils.isEmpty(notename)) {
                    notename = mUserInfo.getNickname();
                    if (TextUtils.isEmpty(notename)) {
                        notename = mUserInfo.getUserName();
                    }
                }
                intent.putExtra(JGApplication.CONV_TITLE, notename);
                Conversation conv = JMessageClient.getSingleConversation(mUserInfo.getUserName(), mUserInfo.getAppKey());
                //如果会话为空，使用EventBus通知会话列表添加新会话
                if (conv == null) {
                    conv = Conversation.createSingleConversation(mUserInfo.getUserName(), mUserInfo.getAppKey());
                    EventBus.getDefault().post(new Event.Builder()
                            .setType(EventType.createConversation)
                            .setConversation(conv)
                            .build());
                }
                startActivity(intent);
                break;
            case R.id.return_btn:
                finish();
                break;
            case R.id.iv_more:
                intent.setClass(GroupNotFriendActivity.this, NotFriendSettingActivity.class);
                intent.putExtra("notFriendUserName", mUserName);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
