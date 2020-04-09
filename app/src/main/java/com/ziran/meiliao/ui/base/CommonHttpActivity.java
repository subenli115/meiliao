package com.ziran.meiliao.ui.base;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.base.BaseModel;
import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.common.commonwidget.LoadingTip;

/**
 * author 吴祖清
 * create  2017/2/8
 * des     常用的网络请求Fragment
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */

public abstract class CommonHttpActivity<T extends BasePresenter, E extends BaseModel> extends BaseActivity<T, E> implements BaseView, LoadingTip.onReloadListener {
    
    /**
     * 网络加载的状态控件
     */
    protected LoadingTip loadedTip;
    

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
    public void initView () {
        if (loadedTip == null) {
            loadedTip = (LoadingTip) findViewById(R.id.loadedTip);
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
    

}
