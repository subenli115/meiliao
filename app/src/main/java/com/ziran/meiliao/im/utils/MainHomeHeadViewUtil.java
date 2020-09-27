package com.ziran.meiliao.im.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.freegeek.android.materialbanner.MaterialBanner;
import com.freegeek.android.materialbanner.view.indicator.CirclePageIndicator;
import com.gcssloop.widget.PagerGridLayoutManager;
import com.gcssloop.widget.PagerGridSnapHelper;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.AdViewpagerUtil;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.commonwidget.PlayPauseView;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.IRecyclerView;
import com.ziran.meiliao.ui.bean.HomeBannerBean;
import com.ziran.meiliao.ui.bean.HomeDataBean;
import com.ziran.meiliao.ui.bean.HomeMenuBean;
import com.ziran.meiliao.ui.main.adapter.PagerGridAdapter;
import com.ziran.meiliao.ui.main.adapter.SimpleViewHolderCreator;
import com.ziran.meiliao.ui.main.util.PageIndicatorView;

import java.util.List;

import butterknife.Bind;

import static com.ziran.meiliao.utils.Utils.dp2px;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/11 14:58
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/8/11$
 * @updateDes ${TODO}
 */

public class MainHomeHeadViewUtil implements View.OnClickListener {
    private final RecyclerView recycler_view;
    private View headView;
    private View viewNews;
    private IRecyclerView mRecyclerView;
    private Context mContext;
    private MaterialBanner materialBanner;
    private CirclePageIndicator circlePageIndicator;
    private PageIndicatorView pageindicator;

    public MainHomeHeadViewUtil(IRecyclerView recyclerView) {
        mRecyclerView = recyclerView;
        mContext = recyclerView.getContext();
        headView = LayoutInflater.from(mContext).inflate(R.layout.item_view_home_head, null);
        materialBanner = ViewUtil.getView(headView, R.id.material_banner);
        pageindicator = ViewUtil.getView(headView, R.id.indicator);
        recycler_view = ViewUtil.getView(headView, R.id.recycler_view);
        initIndicator();
        initData();
        ViewUtil.setOnClickListener(headView, R.id.all_more, this);
        recyclerView.setHeadView(headView);
    }

    public View getHeadView() {
        return headView;
    }



    private void initData() {
        //set circle indicator
        // 1.水平分页布局管理器
        // 设置滚动辅助工具
        PagerGridSnapHelper pageSnapHelper = new PagerGridSnapHelper();
        pageSnapHelper.attachToRecyclerView(recycler_view);
    }
    private void initIndicator() {
        circlePageIndicator = new CirclePageIndicator(mContext);
        circlePageIndicator.setFillColor(Color.WHITE);
        circlePageIndicator.setRadius(MaterialBanner.dip2Pix(mContext, 3));
        circlePageIndicator.setBetween(20);
    }

    public  void setData( List<HomeBannerBean.DataBean> data){
        materialBanner.setPages(new SimpleViewHolderCreator(), data)
                .setIndicator(circlePageIndicator);
        materialBanner.startTurning(3000);
    }

   public void setMenuData(List<HomeMenuBean.DataBean> records){
        PagerGridAdapter mAdapter = new PagerGridAdapter(records,mContext);
        mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override public void onChanged() {
                super.onChanged();
            }
        });
       PagerGridLayoutManager layoutManager = new PagerGridLayoutManager(
               1, records.size(), PagerGridLayoutManager.HORIZONTAL);
       pageindicator.initIndicator(2);
       layoutManager.setPageListener(new PagerGridLayoutManager.PageListener() {
           @Override
           public void onPageSizeChanged(int pageSize) {

           }

           @Override
           public void onPageSelect(int pageIndex) {
               pageindicator.setSelectedPage(pageIndex);
           }
       });    // 设置页面变化监听器
       recycler_view.setLayoutManager(layoutManager);
       recycler_view.setAdapter(mAdapter);
    }

    public void onClick(View view) {
        switch (view.getId()) {

        }
    }





}
