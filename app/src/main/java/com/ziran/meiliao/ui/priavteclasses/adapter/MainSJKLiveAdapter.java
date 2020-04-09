package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.WrapperAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.support.SimpleOnItemClickListener;
import com.ziran.meiliao.ui.adapter.helper.NowLiveHelper;
import com.ziran.meiliao.ui.adapter.helper.TrailerHelper;
import com.ziran.meiliao.ui.bean.SJKSingeLiveData;
import com.ziran.meiliao.ui.priavteclasses.activity.HistoryCourseMoreActivity;
import com.ziran.meiliao.ui.priavteclasses.util.CountDownUtil;
import com.ziran.meiliao.ui.priavteclasses.util.SJKLiveUtil;
import com.ziran.meiliao.utils.CheckUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/1/14.
 */

public class MainSJKLiveAdapter extends MultiItemRecycleViewAdapter<SJKSingeLiveData> {

    public static final int TYPE_HISTORY = 999996;

    /**
     * 清空资源
     */
    public void cancelAllTimers() {
        CountDownUtil.get().cancelAllTimers();
    }

    private SJKLiveHistoryAdapter footerViewAdapter;

    public MainSJKLiveAdapter(Context context, MultiItemTypeSupport<SJKSingeLiveData> multiItemTypeSupport) {
        super(context, multiItemTypeSupport);
        needCountTime = new HashMap<>();
    }

    private Map<Integer, Boolean> needCountTime;

    @Override
    public void convert(final ViewHolderHelper helper, SJKSingeLiveData bean, int position) {
        int viewType = getItemViewType(position);
        boolean showVip = EmptyUtils.isNotEmpty(bean.getVip());
        switch (viewType) {
            case WrapperAdapter.TITLE:
                helper.setText(R.id.tv_item_jyg_audio_title, bean.getTitle());
                View view = helper.getView(R.id.tv_item_jyg_more);
                view.setVisibility(bean.isHasMore() ? View.VISIBLE : View.GONE);
                break;
            case WrapperAdapter.TYPE_NOW:
                NowLiveHelper.convert(helper, bean, position, needCountTime);
                break;
            case WrapperAdapter.TYPE_Trailer:
                TrailerHelper.convert(helper, bean, showVip);
                break;
            case TYPE_HISTORY:
                RecyclerView footerRecyclerView = helper.getView(R.id.foolter_recyclerView);
                helper.setOnClickListener(R.id.tv_sjk_live_more, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mContext.startActivity(new Intent(mContext, HistoryCourseMoreActivity.class));
                    }
                });

                if (footerViewAdapter == null) {
                    footerViewAdapter = new SJKLiveHistoryAdapter(mContext, R.layout.item_main_sjk_live_history);
                    footerRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                    footerViewAdapter.setHeadCount(0);
                    footerRecyclerView.setAdapter(footerViewAdapter);
                    footerViewAdapter.setOnItemClickListener(new SimpleOnItemClickListener<SJKSingeLiveData>() {
                        @Override
                        public void onItemClick(ViewGroup parent, View view, SJKSingeLiveData bean, int position) {
                            if (!CheckUtil.check(mContext, view)) return;
                            //需要修改
                            SJKLiveUtil.startActivity(mContext, bean.getId(), bean.getTag(), bean.getStatus());
                        }
                    });
                }
                if (footerViewAdapter.getSize() == 0) {
                    footerViewAdapter.replaceAll((List<SJKSingeLiveData>) bean.getHistoryData());
                }
                break;
        }
    }

    private int getResid(boolean flag) {
        return flag ? R.mipmap.ic_main_jyg_lab_playing : R.mipmap.ic_main_jyg_lab_play_back;
    }


    public void onRefash() {
        if (footerViewAdapter != null) {
            footerViewAdapter.clear();
        }
    }

    public static class MainSJKLiveMultiItemType implements MultiItemTypeSupport<SJKSingeLiveData> {

        @Override
        public int getLayoutId(int itemType) {
            switch (itemType) {
                case WrapperAdapter.TYPE_NOW:
                    return R.layout.item_main_sjk_nowlive;
                case WrapperAdapter.TYPE_Trailer:
                    return R.layout.item_main_sjk_trailer;

                case WrapperAdapter.TITLE:
                    return R.layout.item_single_title;
                case TYPE_HISTORY:
                    return R.layout.inflate_main_sjk_history;
            }
            return -1;
        }

        @Override
        public int getItemViewType(int position, SJKSingeLiveData bean) {
            return bean.getItemType();
        }
    }

    public void updateCountTime() {
        if (EmptyUtils.isNotEmpty(needCountTime)) {
            Set<Integer> entries = needCountTime.keySet();
            for (Integer entry : entries) {
                notifyItemChanged(entry);
            }
        }
    }
}
