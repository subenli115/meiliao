package com.ziran.meiliao.im.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.List;

import cn.jiguang.api.JCoreInterface;

import com.zhy.autolayout.AutoLinearLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.im.controller.MainController;
import com.ziran.meiliao.im.view.MainView;
import com.ziran.meiliao.ui.me.activity.YouthModelActivity;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.UpdateManager;

public class MainActivity extends FragmentActivity {
    private MainController mMainController;
    private MainView mMainView;
    private View contentView;
    private boolean flag;

    private static final int MIN_DELAY_TIME= 500;  // 两次点击间隔不能少于1000ms
    private static long lastClickTime;
    private InputMethodManager imm;

    public static boolean isFastClick() {
        boolean flag = true;
        long currentClickTime = System.currentTimeMillis();
        if ((currentClickTime - lastClickTime) >= MIN_DELAY_TIME) {
            flag = false;
        }
        lastClickTime = currentClickTime;
        return flag;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (this.getCurrentFocus() != null) {
                if (this.getCurrentFocus().getWindowToken() != null) {
                    imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        }
        return super.onTouchEvent(event);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN){
            // 判断连续点击事件时间差
//            if (isFastClick()){
//                return true;
//            }
        }
        return super.dispatchTouchEvent(ev);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppManager.getAppManager().addActivity(this);
        mMainView = (MainView) findViewById(R.id.main_view);
        mMainView.initModule();
        mMainController = new MainController(mMainView, this);
        HandlerUtil.runMain(new Runnable() {
            @Override
            public void run() {
                //最新版本检查
                new UpdateManager(getBaseContext()).checkUpdate();
                // 请求是否有赠送推广音频数据
            }
        });
        mMainView.setOnClickListener(mMainController);
        mMainView.setOnPageChangeListener(mMainController);
        imm= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(!flag){
            if(getIntent()!=null){
                String type = getIntent().getStringExtra("type");
                if(type!=null&&type.equals("First")){
                    registerReward();
                }
            }
            flag=true;
        }
    }

    private void registerReward() {
        OkHttpClientManager.getAsyncHead(ApiKey.ADMIN_USER_REGISTERRE, MyAPP.getUserId(),MyAPP.getAccessToken(), new
                NewRequestCallBack<Result>(Result.class) {
                    @Override
                    public void onSuccess(Result result) {
                        showPopWindow();
                    }

                    @Override
                    public void onError(String msg, int code) {
                        ToastUitl.showShort(msg);
                    }
                });
    }
    public static void startAction( String type) {
        Activity activity = AppManager.getAppManager().currentActivity();
        Intent intent = new Intent(activity, MainActivity.class);
        intent.putExtra("type", type);
        activity.startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getSupportFragmentManger().getFragments().get(2).onActivityResult(requestCode, resultCode, data);
    }

    public FragmentManager getSupportFragmentManger() {
        return getSupportFragmentManager();
    }

    @Override
    protected void onPause() {
        JCoreInterface.onPause(this);
        super.onPause();
    }

    @Override
    protected void onResume() {
        JCoreInterface.onResume(this);
        mMainController.sortConvList();
        super.onResume();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return keyCode == KeyEvent.KEYCODE_BACK;
    }

    private void showPopWindow() {
        // 一个自定义的布局，作为显示的内容
        int[] location = new int[2];
        contentView = LayoutInflater.from(this).inflate(R.layout.pop_cash_bonus, null);
        contentView.getLocationOnScreen(location);
        final PopupWindow popupWindow = new PopupWindow(contentView,
                AutoLinearLayout.LayoutParams.MATCH_PARENT, AutoLinearLayout.LayoutParams.MATCH_PARENT, true);

        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);// 设置同意在外点击消失
        popupWindow.setFocusable(true);// 点击空白处时，隐藏掉pop窗口
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.showAtLocation(mMainView, Gravity.CENTER, 0, 0);
        TextView qd = contentView.findViewById(R.id.tv_qd);
//        setBackgroundAlpha(0.5f);
//        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                setBackgroundAlpha(1.0f);
//            }
//        });
        qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                showPopYouth();
            }
        });
    }
//    public void setBackgroundAlpha(float bgAlpha) {
//        WindowManager.LayoutParams lp = (this).getWindow()
//                .getAttributes();
//        lp.alpha = bgAlpha;
//        mMainView.setAlpha(bgAlpha);
//    }
private void showPopYouth() {
    // 一个自定义的布局，作为显示的内容
    int[] location = new int[2];
    contentView = LayoutInflater.from(this).inflate(R.layout.pop_youth_model, null);
    contentView.getLocationOnScreen(location);
    final PopupWindow popupWindow = new PopupWindow(contentView,
            AutoLinearLayout.LayoutParams.MATCH_PARENT, AutoLinearLayout.LayoutParams.MATCH_PARENT, true);
    popupWindow.setTouchable(true);
    popupWindow.setOutsideTouchable(true);// 设置同意在外点击消失
    popupWindow.setFocusable(true);// 点击空白处时，隐藏掉pop窗口
    popupWindow.setBackgroundDrawable(new BitmapDrawable());
    // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
    popupWindow.setBackgroundDrawable(new ColorDrawable());
    popupWindow.showAtLocation(mMainView, Gravity.CENTER, 0, 0);
    TextView zd = contentView.findViewById(R.id.tv_zd);
    TextView tvInto = contentView.findViewById(R.id.tv_into);
//        setBackgroundAlpha(0.5f);
//        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                setBackgroundAlpha(1.0f);
//            }
//        });
    tvInto.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            YouthModelActivity.startAction();
        }
    });
    zd.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            popupWindow.dismiss();

        }
    });
}

}
