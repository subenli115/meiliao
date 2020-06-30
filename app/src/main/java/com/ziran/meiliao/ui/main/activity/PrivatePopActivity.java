package com.ziran.meiliao.ui.main.activity;


import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MeiliaoConfig;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.utils.ExitUtil;

import butterknife.Bind;


public class PrivatePopActivity extends BaseActivity {

    @Bind(R.id.tv_qd)
    TextView tvQd;
    @Bind(R.id.tv_close)
    TextView tvClose;
    @Bind(R.id.tv_content)
    TextView tvContent;
    String content="1、我们会遵循隐私政策收集、使用信息，但不会强制捆绑；\n" +
            "2、在仅浏览时，为了保证账号登录安全及信息安全，我们会收集设备信息与日志信息；\n" +
            "3、地理位置信息、摄像头、麦克风、相册权限均需要经过授权才能使用相应功能或服务。\n" +
            "您可以查看完整版《用户隐私政策》"+"和《用户服务协议》";

    @Override
    public int getLayoutId() {
        return R.layout.pop_user_privacy;
    }
    @Override
    public void initPresenter() {

    }
    @Override
    public void initView() {
        final SpannableStringBuilder style = new SpannableStringBuilder();
        //设置文字
        style.append(content);
        //设置部分文字点击事件
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                String agreement = MeiliaoConfig.getUserAgreement();
                UserAgreementWebActivity.startAction(mContext, agreement, "用户服务协议");
            }
        };
        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                String privacyUrl = MeiliaoConfig.getPrivacyUrl();
                UserAgreementWebActivity.startAction(mContext, privacyUrl, "用户隐私政策");
            }
        };
        style.setSpan(clickableSpan, content.length()-8, content.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        style.setSpan(clickableSpan1, content.length()-17, content.length()-9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvContent.setText(style);

        //设置部分文字颜色
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#459BFF"));
        ForegroundColorSpan foregroundColorSpan1 = new ForegroundColorSpan(Color.parseColor("#459BFF"));
        style.setSpan(foregroundColorSpan, content.length()-8, content.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        style.setSpan(foregroundColorSpan1, content.length()-17, content.length()-9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //配置给TextView
        tvContent.setMovementMethod(LinkMovementMethod.getInstance());
        tvContent.setText(style);
        tvQd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExitUtil.exit(mContext);
            }
        });
    }

}