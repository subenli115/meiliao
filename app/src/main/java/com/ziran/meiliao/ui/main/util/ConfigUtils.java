package com.ziran.meiliao.ui.main.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chuanglan.shanyan_sdk.OneKeyLoginManager;
import com.chuanglan.shanyan_sdk.tool.ShanYanUIConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.soexample.LoginApi;
import com.zhy.autolayout.AutoRelativeLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.envet.ThreeLoginCallBack;
import com.ziran.meiliao.im.activity.MainActivity;
import com.ziran.meiliao.ui.bean.StringDataBean;
import com.ziran.meiliao.ui.main.activity.SplashActivity;
import com.ziran.meiliao.ui.settings.activity.BindPhoneActivity;
import com.ziran.meiliao.ui.settings.activity.IntputCodeActivity;
import com.ziran.meiliao.utils.MapUtils;

import java.util.Map;


public class ConfigUtils {

    public static String shareString;
    private com.umeng.soexample.UserInfo userInfo;

    /**
     * 闪验三网运营商授权页配置类
     *
     *
     * @param context
     * @return
     */



    //沉浸式竖屏样式
    public static ShanYanUIConfig getCJSConfig(Context context, SplashActivity activity) {
        /************************************************自定义控件**************************************************************/
        Drawable logBtnImgPath = context.getResources().getDrawable(R.drawable.normal_bg_bule);
        Drawable backgruond = context.getResources().getDrawable(R.drawable.shanyan_demo_auth_no_bg);
        Drawable logoImgPath = context.getResources().getDrawable(R.mipmap.logo);
        Drawable checkLogin = context.getResources().getDrawable(R.mipmap.check_login_xy);
        Drawable uncheckLogin = context.getResources().getDrawable(R.mipmap.uncheck_login_xy);
        //号码栏背景
        LayoutInflater numberinflater = LayoutInflater.from(context);
        RelativeLayout numberLayout = (RelativeLayout) numberinflater.inflate(R.layout.view_other_login, null);
        RelativeLayout.LayoutParams numberParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        numberParams.setMargins(0, 0, 0, AbScreenUtils.dp2px(context, 355));
        numberParams.width = AbScreenUtils.getScreenWidth(context, false) - AbScreenUtils.dp2px(context, 89);
        numberParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        numberParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        numberLayout.setLayoutParams(numberParams);
        otherCodeLogin(context,numberLayout);


        LayoutInflater inflater1 = LayoutInflater.from(context);
        RelativeLayout relativeLayout = (RelativeLayout) inflater1.inflate(R.layout.shanyan_demo_other_login_item, null);
        RelativeLayout.LayoutParams layoutParamsOther = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParamsOther.setMargins(0, 0, 0, AbScreenUtils.dp2px(context, 98));
        relativeLayout.setLayoutParams(layoutParamsOther);
        layoutParamsOther.addRule(RelativeLayout.CENTER_HORIZONTAL);
        layoutParamsOther.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        otherLogin(context, relativeLayout,activity);
        ShanYanUIConfig uiConfig = new ShanYanUIConfig.Builder()

                .setActivityTranslateAnim("shanyan_demo_fade_in_anim", "shanyan_dmeo_fade_out_anim")
                //授权页导航栏：
                .setNavColor(Color.parseColor("#ffffff"))  //设置导航栏颜色
                .setNavText("")  //设置导航栏标题文字
                .setNavReturnBtnWidth(35)
                .setNavReturnBtnHeight(35)
                .setAuthBGImgPath(backgruond)
                .setLogoImgPath(logoImgPath)
                .setLogoHeight(144)
                .setLogoWidth(144)
                .setLogoOffsetBottomY(529)
                .setNavReturnImgHidden(true)
                .setLogoHidden(false)   //是否隐藏logo
                .setDialogDimAmount(0f)
                .setFullScreen(true)
                .setStatusBarHidden(true)


                //授权页号码栏：
                .setNumberColor(Color.parseColor("#4C4C4C"))  //设置手机号码字体颜色
                .setNumFieldOffsetBottomY(478)    //设置号码栏相对于标题栏下边缘y偏移
                .setNumberSize(19)
                .setNumFieldHeight(50)



                //授权页登录按钮：
                .setLogBtnText("本机号码一键登录")  //设置登录按钮文字
                .setLogBtnTextColor(0xffffffff)   //设置登录按钮文字颜色
                .setLogBtnImgPath(logBtnImgPath)   //设置登录按钮图片
                .setLogBtnTextSize(15)
                .setLogBtnHeight(44)
                .setLogBtnOffsetBottomY(420)
                .setLogBtnWidth(AbScreenUtils.getScreenWidth(context, true) - 89)

                //授权页隐私栏：

                .setAppPrivacyOne("用户服务协议", "http://39.98.216.174/userservice.html")  //设置开发者隐私条款2名称和URL(名称，url)
                .setAppPrivacyTwo("用户隐私政策", "http://39.98.216.174/privacy.html")  //设置开发者隐私条款2名称和URL(名称，url)
                .setAppPrivacyColor(Color.parseColor("#808080"), Color.parseColor("#459BFF"))    //	设置隐私条款名称颜色(基础文字颜色，协议文字颜色)
                .setPrivacyText("同意", "和", "、", "", "并授权闪验获得本机号码")
                .setPrivacyNavColor(Color.parseColor("#FF57A2"))
                .setCheckedImgPath(checkLogin)
                .setUncheckedImgPath(uncheckLogin)
                .setPrivacyOffsetBottomY(24)//设置隐私条款相对于屏幕下边缘y偏
                .setPrivacyState(true)
                .setPrivacyTextSize(12)
                .setPrivacyOffsetX(26)
                .setSloganHidden(true)
                .setShanYanSloganTextColor(Color.parseColor("#ffffff"))

                .addCustomView(numberLayout, false, false, null)

                // 添加自定义控件:
                .addCustomView(relativeLayout, false, false, null)
                //标题栏下划线，可以不写
                .build();
        return uiConfig;

    }

    private static void otherCodeLogin(final Context context, RelativeLayout relativeLayout) {
        final TextView tvOther = relativeLayout.findViewById(R.id.tv_other);
        tvOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(context, IntputCodeActivity.class);
                context.startActivity(intent);
            }
        });
    }


    public static void otherLogin(final Context context, RelativeLayout relativeLayout, SplashActivity activity) {
        ImageView weixin = relativeLayout.findViewById(R.id.iv_wechat);
        ImageView qq = relativeLayout.findViewById(R.id.iv_qq);
        ImageView ivOther = relativeLayout.findViewById(R.id.iv_other);
        AutoRelativeLayout arlDown = relativeLayout.findViewById(R.id.arl_down);
        AutoRelativeLayout arlLogin = relativeLayout.findViewById(R.id.arl_login);
        arlDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(arlLogin.getVisibility()==View.INVISIBLE){
                    arlLogin.setVisibility(View.VISIBLE);
                    ivOther.setImageResource(R.mipmap.icon_other_top);
                }else {
                    arlLogin.setVisibility(View.INVISIBLE);
                    ivOther.setImageResource(R.mipmap.icon_other_down);
                }

            }
        });
        weixin.setOnClickListener(v -> {
            shareString = "WX";
            LoginApi.get().login(activity, SHARE_MEDIA.WEIXIN, activity.threeLoginCallBack);
        });
        qq.setOnClickListener(v -> {
            shareString = "QQ";
            activity.loginQQ();

        });
    }


}
