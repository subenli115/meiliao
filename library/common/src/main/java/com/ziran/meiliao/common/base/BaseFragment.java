package com.ziran.meiliao.common.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.common.R;
import com.ziran.meiliao.common.baserx.RxManager;
import com.ziran.meiliao.common.commonutils.TUtil;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonwidget.LoadingDialog;

import butterknife.ButterKnife;


/**
 * des:基类fragment
 * Created by xsf
 * on 2016.07.12:38
 */

/***************
 * 使用例子
 *********************/
//1.mvp模式
//public class SampleFragment extends BaseFragment<NewsChanelPresenter, NewsChannelModel>implements NewsChannelContract.View {
//    @Override
//    public int getLayoutId() {
//        return R.layout.activity_news_channel;
//    }
//
//    @Override
//    public void initPresenter() {
//        mPresenter.setVM(this, mModel);
//    }
//
//    @Override
//    public void initView() {
//    }
//}
//2.普通模式
//public class SampleFragment extends BaseFragment {
//    @Override
//    public int getLayoutResource() {
//        return R.layout.activity_news_channel;
//    }
//
//    @Override
//    public void initPresenter() {
//    }
//
//    @Override
//    public void initView() {
//    }
//}
public abstract class BaseFragment<T extends BasePresenter, E extends BaseModel> extends Fragment implements BaseTip {
    protected View rootView;
    public T mPresenter;
    public E mModel;
    public RxManager mRxManager;

    /**
     * rootView是否初始化标志，防止回调函数在rootView为空的时候触发
     */
    protected boolean hasCreateView;

    /**
     * 当前Fragment是否处于可见状态标志，防止因ViewPager的缓存机制而导致回调函数的触发
     */
    private boolean isFragmentVisible;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariable();
        if (getActivity().getIntent()!=null && getActivity().getIntent().getExtras()!=null){
            initBundle(getActivity().getIntent().getExtras());
        }
    }
    protected void initBundle(Bundle extras) {

    }
    public Bundle getBundle(String id) {
        Bundle bundle = new Bundle();
        bundle.putString("_ID", id);
        return bundle;
    }

    public Bundle getBundle(int type, String id) {
        Bundle bundle = getBundle(id);
        bundle.putInt("FROM_TYPE", type);
        return bundle;
    }
    /**
     * onCreateView()里返回的view，修饰为protected,所以子类继承该类时，在onCreateView里必须对该变量进行初始化
     */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        long start = System.currentTimeMillis();
        if (rootView == null) rootView = inflater.inflate(getLayoutResource(), container, false);

        mRxManager = new RxManager();
        if (needBind()) {
            ButterKnife.bind(this, rootView);
        }
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        if (mPresenter != null) {
            mPresenter.mContext = this.getActivity();
        }
        initPresenter();
        initView();
        initOther();
        if (!hasCreateView && getUserVisibleHint()) {
            onFragmentVisibleChange(true);
            isFragmentVisible = true;
        }
//        LogUtils.logd("onCreateView need " + (System.currentTimeMillis() - start) + " is : " + this);
        return rootView;
    }

    protected void initOther() {
    }

    //获取布局文件
    protected abstract int getLayoutResource();

    //简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
    public abstract void initPresenter();

    //初始化view
    protected abstract void initView();


    public boolean needBind() {
        return true;
    }

    /**
     * 当前fragment可见状态发生变化时会回调该方法
     * 如果当前fragment是第一次加载，等待onCreateView后才会回调该方法，其它情况回调时机跟 {@link #setUserVisibleHint(boolean)}一致
     * 在该回调方法中你可以做一些加载数据操作，甚至是控件的操作，因为配合fragment的view复用机制，你不用担心在对控件操作中会报 null 异常
     *
     * @param isVisible true  不可见 -> 可见
     *                  false 可见  -> 不可见
     */
    protected void onFragmentVisibleChange(boolean isVisible) {

    }

    private void initVariable() {
        hasCreateView = false;
        isFragmentVisible = false;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (rootView == null) {
            return;
        }
        hasCreateView = true;
        if (isVisibleToUser) {
            onFragmentVisibleChange(true);
            isFragmentVisible = true;
            return;
        }
        if (isFragmentVisible) {
            onFragmentVisibleChange(false);
            isFragmentVisible = false;
        }
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }
    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle,boolean isFinish) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        if (isFinish) finish();
    }


    /**
     * 开启加载进度条
     */
    @Override
    public void startProgressDialog() {
        LoadingDialog.showDialogForLoading(getActivity());
    }

    /**
     * 开启加载进度条
     *
     * @param msg
     */
    @Override
    public void startProgressDialog(String msg) {
        LoadingDialog.showDialogForLoading(getActivity(), msg, true);
    }

    /**
     * 停止加载进度条
     */
    @Override
    public void stopProgressDialog() {
        LoadingDialog.cancelDialogForLoading();
    }


    /**
     * 短暂显示Toast提示(来自String)
     **/
    @Override
    public void showShortToast(String text) {
        ToastUitl.showShort(text);
    }

    /**
     * 短暂显示Toast提示(id)
     **/
    @Override
    public void showShortToast(int resId) {
        ToastUitl.showShort(resId);
    }

    /**
     * 长时间显示Toast提示(来自res)
     **/
    @Override
    public void showLongToast(int resId) {
        ToastUitl.showLong(resId);
    }

    /**
     * 长时间显示Toast提示(来自String)
     **/
    @Override
    public void showLongToast(String text) {
        ToastUitl.showLong(text);
    }


    public void showToastWithImg(String text, int res) {
        ToastUitl.showToastWithImg(text, res);
    }

    /**
     * 网络访问错误提醒
     */
    @Override
    public void showNetErrorTip() {
        showNetErrorTip(getText(R.string.net_error).toString());
    }

    @Override
    public void showNetErrorTip(String error) {
        ToastUitl.showToastWithImg(error, R.drawable.ic_wifi_off);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        if (mPresenter != null) mPresenter.onDestroy();
        mRxManager.clear();
    }


    public void finish() {
        if (getActivity()!=null)
        getActivity().finish();
    }

    public int getIntentExtra(Intent intent, String key, int defValue) {
        if (intent == null) {
            return defValue;
        }
        return intent.getIntExtra(key, defValue);
    }

    public String getIntentExtra(Intent intent, String key) {
        if (intent == null) {
            return "0";
        }
        return intent.getStringExtra(key);
    }

    public String getIntentExtra(String key) {
        return getIntentExtra(getActivity().getIntent(), key);
    }

    public int getColor(int color) {
        return getResources().getColor(color);
    }

//
//    public void initBackViewPager(ViewPager viewPager) {
//        if (getActivity() instanceof BaseActivity) {
//            ((BaseActivity) getActivity()).initCanTouchListener(viewPager);
//        }
//    }


    @Override
    public void onResume() {
        super.onResume();
        isPause = false;
    }
    protected boolean isPause;
    @Override
    public void onPause() {
        super.onPause();
        isPause = true;
    }
}
