package com.ziran.meiliao.ui.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.viewpager.widget.ViewPager;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseFragment;
import com.ziran.meiliao.common.base.BaseModel;
import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.common.commonutils.AdViewpagerUtil;
import com.ziran.meiliao.common.commonutils.NetWorkUtils;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.commonwidget.LoadingTip;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.ui.bean.PicsBean;

import java.util.List;

/**
 * author 吴祖清
 * create  2017/2/8
 * des     常用的网络请求Fragment
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */

public abstract class CommonHttpFragment<T extends BasePresenter, E extends BaseModel> extends BaseFragment<T, E> implements
        BaseView,AdViewpagerUtil.OnAdItemClickListener, LoadingTip.onReloadListener {
    
    /**
     * 网络加载的状态控件
     */
    protected LoadingTip loadedTip;
    
    /**
     * 是否已经加载过数据
     */
    protected boolean isLoadingData;

    /**
     * 轮播图管理器
     */
    protected AdViewpagerUtil adViewpagerUtil;
    /**
     * 轮播图容器container
     */
    protected View viewPagerContainer;

    @Override
    public void initPresenter () {
        if (mModel != null && mPresenter != null) {
            mPresenter.setVM(this, mModel);
        }
    }
    
    /**
     * 初始化控件
     */
    @Override
    protected void initView () {
        if (loadedTip == null) {
            loadedTip = (LoadingTip) rootView.findViewById(R.id.loadedTip);
        }
        //设置重新加载监听
        if (loadedTip != null) {
            loadedTip.setOnReloadListener(this);
        }
    }
    
    /**
     * 显示加载中状态
     *
     * @param title 显示加载的标题
     */
    @Override
    public void showLoading (String title) {
        if (loadedTip == null)
            return;
        loadedTip.setLoadingTip(LoadingTip.LoadStatus.loading);
    }
    
    /**
     * 显示加载结束状态
     */
    @Override
    public void stopLoading () {
        if (loadedTip == null)
            return;
        loadedTip.setLoadingTip(LoadingTip.LoadStatus.finish);
    }
    
    /**
     * 显示加载错误提示状态
     *
     * @param msg 错误的信息
     */
    @Override
    public void showErrorTip (String msg) {
        if (loadedTip != null) {
            loadedTip.setLoadingTip(LoadingTip.LoadStatus.error);
            loadedTip.setTips(msg);
        }
    }
    
    /**
     * 显示暂无数据 状态
     */
    @Override
    public void showEmtry () {
        if (loadedTip == null)
            return;
        loadedTip.setLoadingTip(LoadingTip.LoadStatus.empty);
    }
    
    /**
     * 设置加载状态
     *
     * @param loadStatus 加载状态
     */
    public void setLoadedTipState (LoadingTip.LoadStatus loadStatus) {
        if (loadedTip == null)
            return;
        loadedTip.setLoadingTip(loadStatus);
    }
    
    public void setEmptyMsg(String msg,int resId){
        if (loadedTip == null)  return;
        loadedTip.setEmptyMsg(msg,resId);
    }

    /**
     * 点击重新加载
     */
    @Override
    public void reload () {
        
    }
    
    /**
     * 当没有网络时执行
     *
     * @return 是否执行缓存加载 true 从数据库中加载缓存数据 FALSE 设置为没有wifi状态
     */
    protected boolean noWifiState () {
        return false;
    }
    
    /**
     * 懒加载模式 用于ViewPager
     *
     * @param isVisible true  不可见 -> 可见
     */
    @Override
    protected void onFragmentVisibleChange (boolean isVisible) {
        if (isVisible && !isLoadingData) {
            if (NetWorkUtils.isNetConnected(getContext())) {
                doLoading();
            } else {
                if (!noWifiState()) {
                    setLoadedTipState(LoadingTip.LoadStatus.noWifi);
                }
            }
        }
    }
    
    /**
     * 执行加载请求
     */
    protected void doLoading () {
    }


    /**
     * 初始化viewPager图片轮播图
     *
     * @param imgs 图片的路径或url
     */
    public void initViewPager (List<?> imgs,ViewGroup viewGroup) {
        if (viewPagerContainer == null) {
            viewPagerContainer = View.inflate(getContext(), R.layout.cusom_viewpager, null);
            ViewPager viewPager = ViewUtil.getView(viewPagerContainer, R.id.viewpager);
            LinearLayout dot = ViewUtil.getView(viewPagerContainer, R.id.ly_dots);
            String[] urls = parseImgs(imgs);
            adViewpagerUtil = new AdViewpagerUtil(getContext(), viewPager, dot, urls);
            adViewpagerUtil.setOnAdItemClickListener(this);
            adViewpagerUtil.setData(imgs);
            viewGroup.addView(viewPagerContainer,0);
        }else{
            adViewpagerUtil.setData(imgs);
        }
    }

    /**
     * 解析图片路径
     *
     * @param imgs 数据源
     * @return String[]数组
     */
    protected String[] parseImgs (List<?> imgs) {
        if (EmptyUtils.isNotEmpty(imgs)) {
            String[] urls = new String[imgs.size()];
            for (int i = 0; i < imgs.size(); i++) {
                Object o = imgs.get(i);
                if (o instanceof String) {
                    urls[i] = (String) o;
                } else if (o instanceof PicsBean) {
                    urls[i] = ((PicsBean) o).getPicture();
                }
            }
            return urls;
        }
        return new String[0];
    }




    @Override
    public void onResume () {
        super.onResume();
        if (adViewpagerUtil != null) {
            adViewpagerUtil.startLoopViewPager();
        }
    }

    @Override
    public void onPause () {
        super.onPause();
        if (adViewpagerUtil != null) {
            adViewpagerUtil.stopLoopViewPager();
        }
    }

    @Override
    public void onDestroy () {
        super.onDestroy();
        if (adViewpagerUtil != null) {
            adViewpagerUtil.destroyAdViewPager();
        }
    }



    /**
     * ViewPager点击item的监听
     *
     * @param clickView 点击的View
     * @param position  点击的索引
     * @param url       点击的url
     */
    @Override
    public void onViewPagerItemClick (View clickView, int position, String url) {

    }
}
