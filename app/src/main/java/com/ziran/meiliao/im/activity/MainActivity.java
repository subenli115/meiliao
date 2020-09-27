package com.ziran.meiliao.im.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import cn.jiguang.api.JCoreInterface;
import cn.jpush.android.api.JPushInterface;

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

import java.util.List;

public class MainActivity extends FragmentActivity {
    private MainController mMainController;
    private MainView mMainView;
    private View contentView;
    private boolean flag;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";
    public static boolean isForeground = false;

    private InputMethodManager imm;


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
                    showPopWindow();
                }
            }
            flag=true;
        }
    }

//    private void registerReward() {
//        OkHttpClientManager.getAsyncHead(ApiKey.ADMIN_USER_REGISTERRE, MyAPP.getUserId(),MyAPP.getAccessToken(), new
//                NewRequestCallBack<Result>(Result.class) {
//                    @Override
//                    public void onSuccess(Result result) {
//                        showPopWindow();
//                    }
//
//                    @Override
//                    public void onError(String msg, int code) {
//                        ToastUitl.showShort(msg);
//                    }
//                });
//    }
    public static void startAction( String type) {
        Activity activity = AppManager.getAppManager().currentActivity();
        Intent intent = new Intent(activity, MainActivity.class);
        intent.putExtra("type", type);
        activity.startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getSupportFragmentManger().getFragments().get(3).onActivityResult(requestCode, resultCode, data);
    }

    public FragmentManager getSupportFragmentManger() {
        return getSupportFragmentManager();
    }

    @Override
    protected void onPause() {
        isForeground = false;
        JCoreInterface.onPause(this);
        super.onPause();
    }

    @Override
    protected void onResume() {
        isForeground = true;
        JCoreInterface.onResume(this);
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
        qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                showPopYouth();
            }
        });
    }
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
