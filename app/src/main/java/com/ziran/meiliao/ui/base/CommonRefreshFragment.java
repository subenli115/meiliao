package com.ziran.meiliao.ui.base;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.base.BaseModel;
import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.commonutils.AdViewpagerUtil;
import com.ziran.meiliao.common.commonutils.NetWorkUtils;
import com.ziran.meiliao.common.commonutils.SettingUtil;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.commonwidget.LoadingTip;
import com.ziran.meiliao.common.commonwidget.OnDoubleClickListener;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.IRecyclerView;
import com.ziran.meiliao.common.irecyclerview.OnLoadMoreListener;
import com.ziran.meiliao.common.irecyclerview.OnRefreshListener;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.ziran.meiliao.common.irecyclerview.widget.LoadMoreFooterView;
import com.ziran.meiliao.common.receiver.NetUtil;
import com.ziran.meiliao.constant.AppConstant;

import java.util.List;

import butterknife.Bind;
import rx.functions.Action1;


/**
 * author 吴祖清
 * create  2017/2/8
 * des     常用的刷新RecyclerView的Fragment控件(已对ViewPager进行懒加载,只有第一次显示的时候进行网络请求)
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */


public abstract class CommonRefreshFragment<T extends BasePresenter, E extends BaseModel> extends CommonHttpFragment<T, E> implements
        OnLoadMoreListener, OnRefreshListener,  OnItemClickListener {
    
    /**
     * 绑定列表控件
     */
    @Bind(R.id.recyclerView)
    public IRecyclerView iRecyclerView;
    
    /**
     * 列表控件的内边距
     */
    public int defPadding;
    /**
     * 默认每次加载返回条数为15条
     */
    private   int mLoadCount = 150;
    /**
     * 基类适配器
     */
    public CommonRecycleViewAdapter mAdapter;
    /**
     * 基类数据源
     */
    public List mData;
    /**
     * 当前请求的页数
     */
    public int page = 1;


    /**
     * 标记需要检查登录  默认为不需要
     */
    protected boolean needCheckLogin = false;
    /**
     * 标记需要检查有无网络 默认为TRUE
     */
    protected boolean needCheckNet = true;


    @Override
    protected int getLayoutResource () {
        return R.layout.fragment_common_recyclerview;
    }
    
    
    @Override
    protected void initView () {
        super.initView();
        if (iRecyclerView != null && !hasCreateView) {
            iRecyclerView.setOnRefreshListener(this);//设置刷新监听
            iRecyclerView.setOnLoadMoreListener(this);//设置加载更多监听
            iRecyclerView.setHasFixedSize(true);    //启动加速
            if (mAdapter == null) {
                defPadding = getResources().getDimensionPixelOffset(R.dimen.text_margin_left);
                //初始化RecyclerView
                initRecyclerView();
            }
            if (needCheckNet) {
                //订阅网络状态发生变化的事件
                mRxManager.on(AppConstant.RXTag.NETWORK_CHANGE_STATE, new Action1<Integer>() {
                    @Override
                    public void call (Integer integer) {
                        switch (integer) {
                            case NetUtil.NETWORK_NONE: //没有网络
                                if (EmptyUtils.isEmpty(mData)) { //数据为空
                                    if (!noWifiState()) {       //是否执行数据库缓存加载
                                        setLoadedTipState(LoadingTip.LoadStatus.noWifi);
                                    }
                                }
                                //刷新完成
                                setRefresh(false);
                                break;
                            case NetUtil.NETWORK_MOBILE:
                            case NetUtil.NETWORK_WIFI:
                                if (EmptyUtils.isEmpty(mData) && getUserVisibleHint()) {
                                    //如果数据为空并且是正在显示的Fragment则调用加载
                                    loadedTip.setOperateShown();
                                    doLoading();

                                }
                                break;
                        }
                    }
                });
            }
        }
    }
    
    /**
     * 初始化列表控件和适配器
     */
    protected void initRecyclerView () {
        iRecyclerView.setLayoutManager(getLayoutManager());
        setEmptyMsg("暂无内容", R.mipmap.ic_empty_nocontent);
        mAdapter = getAdapter();
        if (iRecyclerView.getAdapter() == null) {
            iRecyclerView.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener(this); //设置点击监听
        }
    }
    
    /**
     * 构建适配器
     *
     * @return 适配器
     */
    public abstract CommonRecycleViewAdapter getAdapter ();



    public void setBackColor(int color){
        View view = ViewUtil.getView(rootView, R.id.rootFrame);
        if (view!=null){
            view.setBackgroundColor(color);
        }
    }
    /**
     * 获取RecyclerView的布局管理器
     *
     * @return RecyclerView的布局管理器
     */
    public RecyclerView.LayoutManager getLayoutManager () {
        return new LinearLayoutManager(getContext());
    }
    
    
    /**
     * 加载数据
     */
    protected abstract void loadData ();
    
    
        /**
         * 数据是否已加载到最后
         *
         * @return 是否加载的最后
         */
        public boolean isEnd () {
            if(iRecyclerView!=null){
                return iRecyclerView.canLoadMore();
            }else {
                return false;
            }
        }
    
    /**
     * 下拉刷新回调
     */
    @Override
    public void onRefresh () {

        onRefreshBy(true);
    }



    protected void onRefreshBy(boolean refresh) {
        if (mAdapter != null) {
            mAdapter.getPageBean().setRefresh(true);
            mAdapter.clear();
        }
        setLoadState(LoadMoreFooterView.Status.GONE);
        if (mData != null) {
            mData.clear();
        }
        page = 1;
        //发起请求
        if (refresh){
            if(iRecyclerView!=null){
                iRecyclerView.setRefreshing(true);
            }
        }
        if (needCheckNet) { //需要检查网络状态
            if (NetWorkUtils.isNetConnected(getContext())) {
                doLoading();
            } else {
                if (!noWifiState()) {
                    setLoadedTipState(LoadingTip.LoadStatus.noWifi);
                }
            }
        } else {        //正常执行加载
            doLoading();
        }
    }

    //加载更多
    @Override
    public void onLoadMore (View loadMoreView) {
        if (isEnd()) {
            mAdapter.getPageBean().setRefresh(false);
            //发起请求
            iRecyclerView.setLoadMoreStatus(LoadMoreFooterView.Status.LOADING);
            doLoading();
        }
    }
    
    
    /**
     * 处理加载数据
     */
    public void doLoading () {
        if (needCheckLogin) {   //是否需要检查登录
            if (MyAPP.isLogin(getContext())) {
                loadData(); //进行数据加载
            }
        } else {
            loadData(); //进行数据加载
        }
        
    }
    
    /**
     * 当前fragment可见状态发生变化时会回调该方法
     * 如果当前fragment是第一次加载，等待onCreateView后才会回调该方法，其它情况回调时机跟 {@link #setUserVisibleHint(boolean)}一致
     * 在该回调方法中你可以做一些加载数据操作，甚至是控件的操作，因为配合fragment的view复用机制，你不用担心在对控件操作中会报 null 异常
     *
     * @param isVisible true  不可见 -> 可见
     *                  false 可见  -> 不可见
     */
    protected void onFragmentVisibleChange (boolean isVisible) {
        if (isVisible) {
            if (EmptyUtils.isEmpty(mData)) {
                if (needCheckNet) {
                    if (NetWorkUtils.isNetConnected(getContext())) {
                        doLoading();
                    } else {
                        if (!noWifiState()) {
                            setLoadedTipState(LoadingTip.LoadStatus.noWifi);
                        }
                    }
                } else {
                    doLoading();
                }
            } else {
                setRefresh(false);
            }
        }
    }
    
    
    /**
     * 更新数据
     *
     * @param data 数据源
     */
    public void updateData (List data) {
        if(iRecyclerView==null){
            return;
        }
        if (EmptyUtils.isNotEmpty(data)) {
            page++;
            if (EmptyUtils.isEmpty(mData)) {
                mData = data;
            }
            setRefresh(false);
            if (mAdapter.getPageBean().isRefresh()) { //如果是下拉刷新则替换旧数据
                mAdapter.getPageBean().setRefresh(false);
                mAdapter.replaceAll(data);
            } else {
                iRecyclerView.setLoadMoreStatus(LoadMoreFooterView.Status.GONE);
                mAdapter.addAll(data);
                if (data.size() < mLoadCount) {

                    iRecyclerView.setLoadMoreStatus(LoadMoreFooterView.Status.THE_END);
                }
            }
            setLoadedTipState(LoadingTip.LoadStatus.finish);
        } else {    //数据为空
            //iRecyclerView.getIAdapter().getItemCount()  替换  mAdapter.getItemCount();
            if (!iRecyclerView.isHasHeaderView()) {
                if ( mAdapter.getItemCount() == 0) {
                    setLoadedTipState(LoadingTip.LoadStatus.empty);
                    iRecyclerView.setLoadMoreEnabled(false);
                } else {
                    setLoadState(LoadMoreFooterView.Status.THE_END);
                }
            }else{
                if ( mAdapter.getItemCount() > 0) {
                    setLoadState(LoadMoreFooterView.Status.THE_END);
                }
            }
            setRefresh(false);
        }
    }

    /**
     * 设置刷新状态
     *
     * @param refresh true 启动刷新  FALSE 刷新结束
     */
    public void setRefresh (boolean refresh) {
        if (iRecyclerView != null) {
            iRecyclerView.setRefreshing(refresh);
        }
    }
    
    /**
     * 设置是否允许下拉刷新和加载更多
     *
     * @param enabled true 允许  FALSE不允许
     */
    public void setRecyclerEnabled (boolean enabled) {
        setRecyclerEnabled(enabled, enabled);
    }
    
    /**
     * 设置是否允许下拉刷新和加载更多
     *
     * @param loadMoreEnabled true 允许加载更多  FALSE不允许加载更多
     * @param refreshEnabled  true 允许下拉刷新  FALSE不允许下拉刷新
     */
    public void setRecyclerEnabled (boolean loadMoreEnabled, boolean refreshEnabled) {
        if (iRecyclerView != null) {
            iRecyclerView.setLoadMoreEnabled(loadMoreEnabled);
            iRecyclerView.setRefreshEnabled(refreshEnabled);
        }
    }
    
    /**
     * 设置当前加载的状态
     *
     * @param state
     */
    public void setLoadState (LoadMoreFooterView.Status state) {
        if (iRecyclerView != null) {
            iRecyclerView.setLoadMoreStatus(state);
        }
    }

    /**
     * 初始化viewPager图片轮播图
     *
     * @param imgs 图片的路径或url
     */
    public void initViewPager (List<?> imgs) {
        if (viewPagerContainer == null) {
            viewPagerContainer = View.inflate(getContext(), R.layout.cusom_viewpager, null);
            ViewPager viewPager = ViewUtil.getView(viewPagerContainer, R.id.viewpager);
            LinearLayout dot = ViewUtil.getView(viewPagerContainer, R.id.ly_dots);
            String[] urls = parseImgs(imgs);
            adViewpagerUtil = new AdViewpagerUtil(getContext(), viewPager, dot, urls);
            adViewpagerUtil.setOnAdItemClickListener(this);
            adViewpagerUtil.setData(imgs);
            iRecyclerView.addHeaderView(viewPagerContainer);
        }
    }
    /**
     * 显示加载中状态
     *
     * @param title 显示加载的标题
     */
    @Override
    public void showLoading (String title) {
        if (loadedTip == null || mAdapter==null)
            return;
        if (mAdapter.getPageBean().isRefresh()) {
            loadedTip.setLoadingTip(LoadingTip.LoadStatus.loading);
        }
    }
    
    /**
     * 显示错误的提示
     *
     * @param msg 错误的信息
     */
    @Override
    public void showErrorTip (String msg) {
        if (loadedTip == null || iRecyclerView == null)
            return;
        if (mAdapter.getPageBean().isRefresh()) {
            loadedTip.setLoadingTip(LoadingTip.LoadStatus.error);
            loadedTip.setTips(msg);
            iRecyclerView.setRefreshing(false);
        } else {
            iRecyclerView.setLoadMoreStatus(LoadMoreFooterView.Status.ERROR);
        }
    }
    
    /**
     * 显示错误的提示
     *
     * @param msg  错误的信息
     * @param icon 错误的图标
     */
    public void showErrorTip (String msg, int icon) {
        loadedTip.setImg_tip_logo(icon);
        showErrorTip(msg);
    }
    
    
    /**
     * 设置双击滚动到顶部监听
     *
     * @param view 需要点击滚动的view
     */
    public void setScrollTop (View view) {
        if (iRecyclerView == null)
            return;
        view.setOnClickListener(new OnDoubleClickListener() {
            @Override
            protected void onDoubleClick (View v) {
                if (iRecyclerView.getLayoutManager().getItemCount() > 10) {
                    iRecyclerView.getLayoutManager().scrollToPosition(5);
                }
                iRecyclerView.smoothScrollToPosition(0);
            }
        });
    }
    
    
    /**
     * 获取当前Item数量
     *
     * @return 当前Item数量
     */
    public int getItemCount () {
        if (mAdapter != null) {
            return mAdapter.getItemCount();
        }
        return 0;
    }

    
    /**
     * RecyclerView点击监听
     *
     * @param parent   父容器即RecyclerView
     * @param view     点击的contentView
     * @param o        数据bean
     * @param position 下标索引
     */
    @Override
    public void onItemClick (ViewGroup parent, View view, Object o, int position) {
        
    }
    
    /**
     * RecyclerView长按监听
     *
     * @param parent   父容器即RecyclerView
     * @param view     点击的contentView
     * @param o        数据bean
     * @param position 下标索引
     * @return
     */
    @Override
    public boolean onItemLongClick (ViewGroup parent, View view, Object o, int position) {
        return false;
    }
    
    /**
     * 跳转到设置网络界面
     */
    @Override
    public void reload () {
        SettingUtil.startSettings(getContext());
    }

    public void setLoadCount(int loadCount){
        mLoadCount = loadCount;
    }
}
