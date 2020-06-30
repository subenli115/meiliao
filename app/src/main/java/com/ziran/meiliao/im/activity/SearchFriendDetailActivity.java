package com.ziran.meiliao.im.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cn.jpush.im.android.api.ContactManager;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;

import com.ziran.meiliao.R;
import com.ziran.meiliao.im.application.JGApplication;
import com.ziran.meiliao.im.entity.Event;
import com.ziran.meiliao.im.entity.EventType;
import com.ziran.meiliao.im.utils.DialogCreator;
import com.ziran.meiliao.im.utils.NativeImageLoader;
import com.ziran.meiliao.im.utils.SharePreferenceManager;

/**
 * Created by ${chenyn} on 2017/3/21.
 */

public class SearchFriendDetailActivity extends BaseActivity {

    private ImageView mIv_friendPhoto;
    private TextView mTv_nickName;
    private TextView mTv_additionalMsg;
    private TextView mTv_signature;
    private TextView mTv_gender;
    private TextView mTv_gift;
    private TextView mTv_birthday;
    private TextView mTv_address;
    private Button mBtn_refusal;
    private Button mBtn_agree;
    private String mUsername;
    private String mAppKey;
    private UserInfo mToUserInfo;

    private String mAvatarPath;
    private String mDisplayName;
    private TextView mUserName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result_detail);

        initView();
    }

    private void initView() {
        initTitle(true, true, "详细信息", "", false, "");
        mIv_friendPhoto = (ImageView) findViewById(R.id.iv_friendPhoto);
        mTv_nickName = (TextView) findViewById(R.id.tv_nickName);
        mTv_additionalMsg = (TextView) findViewById(R.id.tv_additionalMsg);
        mTv_gender = (TextView) findViewById(R.id.tv_gender);
        mTv_gift = (TextView) findViewById(R.id.tv_gift);
        mTv_birthday = (TextView) findViewById(R.id.tv_birthday);
        mBtn_refusal = (Button) findViewById(R.id.btn_refusal);
        mBtn_agree = (Button) findViewById(R.id.btn_agree);

        initModel();

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
    private void initModel() {
        final Dialog dialog = DialogCreator.createLoadingDialog(this, this.getString(R.string.jmui_loading));
        dialog.show();
        final Intent intent = getIntent();
        mUsername = intent.getStringExtra(JGApplication.TARGET_ID);
        mAppKey = intent.getStringExtra(JGApplication.TARGET_APP_KEY);
        JMessageClient.getUserInfo(mUsername, mAppKey, new GetUserInfoCallback() {
            @Override
            public void gotResult(int responseCode, String responseMessage, UserInfo info) {
                dialog.dismiss();
                if (responseCode == 0) {
                    mToUserInfo = info;
                    Bitmap bitmap = NativeImageLoader.getInstance().getBitmapFromMemCache(mUsername);
                    if (bitmap != null) {
                        mIv_friendPhoto.setImageBitmap(bitmap);
                    } else if (!TextUtils.isEmpty(info.getAvatar())) {
                        mAvatarPath = info.getAvatarFile().getPath();
                        info.getAvatarBitmap(new GetAvatarBitmapCallback() {
                            @Override
                            public void gotResult(int responseCode, String responseMessage, Bitmap avatarBitmap) {
                                if (responseCode == 0) {
                                    mIv_friendPhoto.setImageBitmap(avatarBitmap);
                                    NativeImageLoader.getInstance().updateBitmapFromCache(mUsername, avatarBitmap);
                                } else {
                                    mIv_friendPhoto.setImageResource(R.drawable.jmui_head_icon);
                                }
                            }
                        });
                    } else {
                        mIv_friendPhoto.setImageResource(R.drawable.jmui_head_icon);
                    }
                    mDisplayName = info.getNickname();
                    if (TextUtils.isEmpty(mDisplayName)) {
                        mDisplayName = info.getUserName();
                    }
                    mTv_nickName.setText(mDisplayName);
                    if (info.getGender() == UserInfo.Gender.male) {
                        mTv_gender.setText("男");
                    } else if (info.getGender() == UserInfo.Gender.female) {
                        mTv_gender.setText("女");
                    } else {
                        mTv_gender.setText("保密");
                    }
                    mTv_gift.setText("玫瑰花");
                    mTv_additionalMsg.setText(intent.getStringExtra("reason"));
                    long birthday = info.getBirthday();
                    if (birthday == 0) {
                        mTv_birthday.setText("");
                    } else {
                        mTv_birthday.setText(getBirthday(info)+"岁");
                    }
                }
            }
        });

        final int position = intent.getIntExtra("position", -1);

        View.OnClickListener listener = new View.OnClickListener() {
            final Dialog dialog = DialogCreator.createLoadingDialog(SearchFriendDetailActivity.this, "正在加载");

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_refusal:
                        //拒绝添加
                        dialog.show();
                        ContactManager.declineInvitation(mToUserInfo.getUserName(), mToUserInfo.getAppKey(), "拒绝了您的好友请求", new BasicCallback() {
                            @Override
                            public void gotResult(int responseCode, String responseMessage) {
                                dialog.dismiss();
                                if (responseCode == 0) {
                                    //拒绝时候要修改button数据库状态,并更新内存
                                    Intent btnIntent = new Intent();
                                    btnIntent.putExtra("position", position);
                                    btnIntent.putExtra("btn_state", 1);
                                    setResult(JGApplication.RESULT_BUTTON, btnIntent);
                                    finish();
                                }
                            }
                        });
                        break;
                    case R.id.btn_agree:
                        //同意添加
                        dialog.show();
                        ContactManager.acceptInvitation(mToUserInfo.getUserName(), mToUserInfo.getAppKey(), new BasicCallback() {
                            @Override
                            public void gotResult(int responseCode, String responseMessage) {
                                dialog.dismiss();
                                if (responseCode == 0) {
                                    Intent btnIntent2 = new Intent();
                                    btnIntent2.putExtra("position", position);
                                    btnIntent2.putExtra("btn_state", 2);
                                    setResult(JGApplication.RESULT_BUTTON, btnIntent2);
                                    EventBus.getDefault().post(new Event.Builder().setType(EventType.addFriend)
                                            .setFriendId(SharePreferenceManager.getItem()).build());
                                    finish();
                                }
                            }
                        });
                        break;
                    default:
                        break;
                }

            }
        };
        mBtn_agree.setOnClickListener(listener);
        mBtn_refusal.setOnClickListener(listener);
    }


}
