package com.ziran.meiliao.ui.main.fragment;


import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.app.WpyxConfig;
import com.ziran.meiliao.common.commonutils.NetWorkUtils;
import com.ziran.meiliao.common.commonutils.SPUtils;
import com.ziran.meiliao.common.commonwidget.OnNoDoubleClickListener;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.entry.UserInfo;
import com.ziran.meiliao.envet.OnLoadDataListener;
import com.ziran.meiliao.ui.base.CommonHttpFragment;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.AdvertBean;
import com.ziran.meiliao.ui.bean.VersionBean;
import com.ziran.meiliao.ui.main.activity.MainActivity;
import com.ziran.meiliao.ui.main.activity.NewLoginActivity;
import com.ziran.meiliao.ui.main.contract.SplashContract;
import com.ziran.meiliao.ui.main.presenter.SplashPresenter;
import com.ziran.meiliao.ui.settings.widget.DonutProgress;
import com.ziran.meiliao.utils.DeviceUtil;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.UpdateManager;

import java.util.Map;

import butterknife.Bind;

/**
 * 整理界面注册Fragment
 */
public class SplashFragment extends CommonHttpFragment<SplashPresenter, CommonModel> implements SplashContract.View {

    @Bind(R.id.fl_container)
    View flContainer;
    @Bind(R.id.tv_skip)
    TextView tvSkip;
    @Bind(R.id.donutProgress)
    DonutProgress mDonutProgress;
    @Bind(R.id.lottie_likeanim)
    LottieAnimationView lottie;
    private Activity activity;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_main_splash;
    }


    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {
        super.initView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            View decorView = window.getDecorView();
            decorView.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
                @TargetApi(Build.VERSION_CODES.KITKAT_WATCH)
                @Override
                public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
                    WindowInsets defaultInsets = v.onApplyWindowInsets(insets);
                    return defaultInsets.replaceSystemWindowInsets(
                            defaultInsets.getSystemWindowInsetLeft(),
                            0,
                            defaultInsets.getSystemWindowInsetRight(),
                            defaultInsets.getSystemWindowInsetBottom());
                }
            });
            ViewCompat.requestApplyInsets(decorView);
            //将状态栏设成透明，如不想透明可设置其他颜色
            window.setStatusBarColor(ContextCompat.getColor(getContext(), android.R.color.transparent));
        }
            initAni();
        mPresenter.getAdvert(MapUtils.getDefMap(true));

        tvSkip.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                HandlerUtil.remove(runMain);
                String token = SPUtils.getString(AppConstant.SPKey.TOKEN);
                String phone = SPUtils.getString(AppConstant.SPKey.PHONE);

                if (EmptyUtils.isEmpty(token)||EmptyUtils.isEmpty(phone)) {
                    NewLoginActivity.startAction(getActivity());
                    finish();
                    return;
                }
                MainActivity.startAction(getActivity());
                finish();
            }
        });
    }

    private void gotoMain(final boolean isMain, long time) {
        HandlerUtil.runMain(new Runnable() {
            @Override
            public void run() {
                HandlerUtil.remove(runMain);
                NewLoginActivity.startAction(getActivity());
                finish();
            }
        }, time);
    }
    private void initAni() {
        lottie.setImageAssetsFolder("initial/");
        lottie.setAnimation("initial.json");
        lottie.loop(false);
        lottie.playAnimation();
    }

    private void gotoMain(final boolean isMain) {
        gotoMain(isMain, 1000);
    }


//    private void showUpdateDialog(final VersionBean result) {
//        //弹出强制更新的对话框
//        MDAlertDialog.createDialog(getContext(), "温馨提示", result.getData().getContent(), "更新", "退出", new DialogOnClickListener() {
//            @Override
//            public void clickLeftButton(Dialog dialog, View view) {
//                dialog.dismiss();
//                UpdateVersionDownloadService.downloadApk(getContext(), result.getData().getUrl(), update);
//            }
//
//            @Override
//            public void clickRightButton(Dialog dialog, View view) {
//                dialog.dismiss();
//                finish();
//            }
//        });
//    }

    @Override
    public void showVersionResult(final VersionBean result) {
        UpdateManager.setVersionBean(result);
        if (result.getData().isForce()) {
            HandlerUtil.runMain(new Runnable() {
                @Override
                public void run() {

                }
            });
            return;
        }

        MyAPP.init();
        String token = SPUtils.getString(AppConstant.SPKey.TOKEN);
        mDonutProgress.startCountTime(4000);
        if (EmptyUtils.isEmpty(token)) {
            gotoMain(false,4000);
            return;
        }

        if (!NetWorkUtils.isNetConnected(getContext())) {
            MyAPP.loadUserInfo(new OnLoadDataListener<UserInfo>() {
                @Override
                public void loadSuccess(UserInfo userInfo) {
                    gotoMain(userInfo != null);
                }
            });
            return;
        }
        Map<String, String> defMap = MapUtils.getDefMap(false);
        defMap.put("accessToken", token);
        mPresenter.checkToken(defMap);
    }

    @Override
    public void showCheckTokenResult(boolean result) {

        if (result) {
            SPUtils.remove(AppConstant.SPKey.TOKEN);
            HandlerUtil.remove(runMain);
            gotoMain(false,5000);
        } else {
            MyAPP.loadUserInfo(new OnLoadDataListener<UserInfo>() {
                @Override
                public void loadSuccess(UserInfo userInfo) {

                }
            });
            HandlerUtil.runMain(runMain, 5000);
        }
    }

    @Override
    public void showAdvertResultError() {
        loadedTip.setOperate();
    }


//    @OnClick(R.id.iv_advertisement)
//    public void onJump(View view) {
//    }

    private AdvertBean.DataBean mDataBean;

    @Override
    public void showAdvertResult(AdvertBean.DataBean result) {
        loadedTip.setVisibility(View.GONE);
        mDataBean = result;
//        ImageLoaderUtils.display(getContext(), ivAdvertisement, result.getPicture());
        WpyxConfig.setAdvertDetail(result.getAdvertDetail());
        WpyxConfig.setAppDetail(result.getAppDetail());
//        viewContainer.setVisibility(View.GONE);
        flContainer.setVisibility(View.VISIBLE);
        getVersion();
    }

    private void getVersion() {
        final String currentVersion = DeviceUtil.getVersionName(getContext());
        Map<String, String> defMap = MapUtils.getDefMap(false);
        defMap.put("version", currentVersion);
        defMap.put("platform", "android");
        mPresenter.getVersion(defMap);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        HandlerUtil.remove(runMain);
    }

    private Runnable runMain = new Runnable() {
        @Override
        public void run() {
            String phone = SPUtils.getString(AppConstant.SPKey.PHONE);
            if(!EmptyUtils.isEmpty(phone)){
                MainActivity.startAction(activity);
                finish();
            }else{
                NewLoginActivity.startAction(getActivity());
                finish();
            }
        }
    };

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public void reload() {
        mPresenter.getAdvert(MapUtils.getDefMap(true));
    }
}
