package com.atech.staggedrv;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.atech.staggedrv.callbacks.LoadMoreAndRefresh;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * 瀑布流layout
 * created by desong
 * 2020 0329
 */
public class StaggerdRecyclerView extends LinearLayout {

    Context c;
    public RecyclerView rv;
    SmartRefreshLayout smartRefreshLayout;
    View root;
    StaggedAdapter staggedAdapter;
    StaggeredGridLayoutManager layoutManager;
    private boolean isItemDeleted=true;


    public StaggerdRecyclerView(@NonNull Context context) {
        super(context);

        initViews(context);
    }

    public StaggerdRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViews(context);

    }

    public StaggerdRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        initViews(context);
    }


    private void initViews(Context c) {

        this.c = c;
        root = inflate(c, R.layout.stagged_layout, this);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
//        ClassicsFooter footer = root.findViewById(R.id.footer);
//        footer.setFinishDuration(0);//设置刷新完成显示的停留时间
        smartRefreshLayout = root.findViewById(R.id.refresh);
        rv = root.findViewById(R.id.rv);

    }

    /**
     * 配置staggedrv
     */

    public void link(final StaggedAdapter staggedAdapter, int spanCount) {

        this.staggedAdapter = staggedAdapter;
        layoutManager = new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        rv.setLayoutManager(layoutManager);
        rv.setHasFixedSize(true);
        rv.setItemAnimator(null);
        rv.setNestedScrollingEnabled(false);//禁止滑动
        rv.setItemViewCacheSize(100);
        rv.setAdapter(staggedAdapter);
    }
    public void addCallbackListener(final LoadMoreAndRefresh refreshCallback) {
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshCallback.onRefresh();
                smartRefreshLayout.finishRefresh();
            }
        });

        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshCallback.onLoadMore();
               smartRefreshLayout.finishLoadMoreWithNoMoreData();
                smartRefreshLayout.finishLoadMore();
            }
        });

    }




    /**
     * 设置间距
     * @param itemDecoration
     */
    public void addDecoration(RecyclerView.ItemDecoration itemDecoration){

        rv.addItemDecoration(itemDecoration);

    }

    /**
     * 禁止或开启刷新
     * @param enable
     */
    public void enableRefresh(boolean enable){

        smartRefreshLayout.setEnableRefresh(enable);

    }
    /**
     * 禁止或开启加载更多
     * @param enable
     */
    public void enableLoadMore(boolean enable){

        smartRefreshLayout.setEnableLoadMore(enable);
    }

}
