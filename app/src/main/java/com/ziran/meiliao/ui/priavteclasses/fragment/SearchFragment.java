package com.ziran.meiliao.ui.priavteclasses.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.citypicker.citylist.widget.ClearEditText;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.KeyBordUtil;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonwidget.LoadingTip;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.IRecyclerView;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.db.DbCore;
import com.ziran.meiliao.entry.SearchEntry;
import com.ziran.meiliao.ui.base.CommonHttpFragment;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.SearchAllResultBean;
import com.ziran.meiliao.ui.bean.SearchHotTagBean;
import com.ziran.meiliao.ui.bean.SearchItemBean;
import com.ziran.meiliao.ui.priavteclasses.activity.SearchDetailActivity;
import com.ziran.meiliao.ui.priavteclasses.adapter.SearchAllAdapter;
import com.ziran.meiliao.ui.priavteclasses.adapter.SearchHistoryAdapter;
import com.ziran.meiliao.ui.priavteclasses.contract.SearchContract;
import com.ziran.meiliao.ui.priavteclasses.presenter.SearchPresenter;
import com.ziran.meiliao.ui.priavteclasses.util.SearchUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.FlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * 私家课-活动Fragment
 * Created by Administrator on 2017/1/7.
 */

public class SearchFragment extends CommonHttpFragment<SearchPresenter, CommonModel> implements SearchContract.View {

    @Bind(R.id.et_seach_content)
    ClearEditText etSeachContent;
    @Bind(R.id.tv_seach_cancel)
    TextView tvSeachCancel;
    @Bind(R.id.lsv_search_history)
    ListView lsvSearchHistory;
    @Bind(R.id.rl_search_history_con)
    RelativeLayout rlSearchHistoryCon;
    @Bind(R.id.tv_search_hot_label)
    TextView tvSearchHotLabel;
    @Bind(R.id.fl_keyword)
    FlowLayout mFlowLayout;
    @Bind(R.id.rl_search_hot_con)
    RelativeLayout rlSearchHotCon;
    @Bind(R.id.irv_search_result)
    IRecyclerView iRecyclerViewResult;
    SearchHistoryAdapter mSearchHistoryAdapter;
    SearchAllAdapter mSearchAllAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_search;
    }


    @Override
    protected void initView() {
        super.initView();
        iRecyclerViewResult.setLoadMoreEnabled(false);
        setEmptyMsg("搜索失败,没有找到相关内容", R.mipmap.ic_empty_nocontent);

        mFlowLayout.setFlowLayout(new FlowLayout.OnItemClickListener() {
            @Override
            public void onItemClick(String content) {
                search(content);
            }
        });
        etSeachContent.setOnTextChangeListener(new ClearEditText.OnTextChangeListener() {
            @Override
            public void hasContent(boolean hasContent) {
                tvSeachCancel.setText(hasContent ? "搜索" : "取消");
            }
        });
        etSeachContent.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {//修改回车键功能
                    String content = etSeachContent.getText().toString();
                    startSearch(content);
                }
                return false;
            }
        });
        etSeachContent.setOnClearListener(new ClearEditText.OnClearListener() {
            @Override
            public void clear() {
                req(false);
            }
        });
        mSearchHistoryAdapter = new SearchHistoryAdapter(getContext(), R.layout.item_search_history);
        lsvSearchHistory.setAdapter(mSearchHistoryAdapter);
        lsvSearchHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                search(mSearchHistoryAdapter.getItem(position).getContent());
            }
        });
        mSearchAllAdapter = new SearchAllAdapter(getContext());

        iRecyclerViewResult.setLayoutManager(new LinearLayoutManager(getContext()));
        iRecyclerViewResult.setAdapter(mSearchAllAdapter);
        mSearchAllAdapter.setOnItemClickListener(new OnItemClickListener<SearchItemBean>() {
            @Override
            public void onItemClick(ViewGroup parent, View view, SearchItemBean bean, int position) {
                if (bean.isHead()) {
                    SearchDetailActivity.startAction(bean.getType(), etSeachContent.getText().toString());
                } else {
                    SearchUtil.onItemClick(getContext(), bean);
                }
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, SearchItemBean o, int position) {
                return false;
            }
        });
        List<SearchEntry> searchEntries = DbCore.getDaoSession().getSearchEntryDao().loadAll();

        if (EmptyUtils.isNotEmpty(searchEntries)) {
            List<SearchEntry> entries = new ArrayList<>();
            if (searchEntries.size() > 10) {
                for (int i = searchEntries.size() - 1; i >= 0; i--) {
                    entries.add(searchEntries.get(i));
                    if (entries.size() == 9) {
                        break;
                    }
                }
                mSearchHistoryAdapter.addAll(entries);
            } else {
                mSearchHistoryAdapter.addAll(searchEntries);
            }
            rlSearchHistoryCon.setVisibility(View.VISIBLE);
        } else {
            rlSearchHistoryCon.setVisibility(View.GONE);
        }
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            String stringExtra = intent.getStringExtra(AppConstant.ExtraKey.FROM_TYPE);
            if (EmptyUtils.isNotEmpty(stringExtra)) {
                search(stringExtra);
            }
        }
        mPresenter.getHotTag(ApiKey.SEARCH_GET_HOT_TAG, MapUtils.getDefMap(false));
    }

    private void search(String content) {
        etSeachContent.setText(content);
        etSeachContent.setSelection(content.length());
        startSearch(content);
    }

    private void startSearch(String content) {
        if (EmptyUtils.isNotEmpty(content)) {
            if (!SearchUtil.check(mSearchHistoryAdapter.getAll(), content)) {
                SearchEntry searchEntry = new SearchEntry(content, 0);
                DbCore.getDaoSession().getSearchEntryDao().insertOrReplace(searchEntry);
                mSearchHistoryAdapter.add(searchEntry);

            }
            Map<String, String> stringMap = MapUtils.getDefMap(true);
            stringMap.put("type", "all");
            stringMap.put("content", content);
            stringMap.put("page", "1");
            stringMap.put("pageSize", "3");
            req(true);
            mPresenter.searchResult(ApiKey.SEARCH_SELECT_BY_PARAMS_NEW, stringMap);
        } else {
            ToastUitl.showShort("请输入需要搜索的内容");
            KeyBordUtil.showSoftKeyboard(etSeachContent);
        }
    }


    private void req(boolean showResult) {
        iRecyclerViewResult.setVisibility(showResult ? View.VISIBLE : View.GONE);
        if (mSearchHistoryAdapter.getItemCount() > 0) {
            rlSearchHistoryCon.setVisibility(showResult ? View.GONE : View.VISIBLE);
        }
        rlSearchHotCon.setVisibility(showResult ? View.GONE : View.VISIBLE);
        if (showResult) {
            setLoadedTipState(LoadingTip.LoadStatus.loading);
            KeyBordUtil.hideSoftKeyboard(etSeachContent);
        } else {
            setLoadedTipState(LoadingTip.LoadStatus.finish);
            KeyBordUtil.showSoftKeyboard(etSeachContent);
        }
    }

    private void setSearchText(String content) {
        if (EmptyUtils.isNotEmpty(content)) {
            etSeachContent.setText(content);
            etSeachContent.setSelection(content.length());
            KeyBordUtil.showSoftKeyboard(etSeachContent);
        }
    }

    private void setAdapter(List<String> tags) {
        mFlowLayout.setFlowLayout(tags);
    }


    @OnClick({R.id.iv_seach_icon, R.id.tv_seach_cancel, R.id.tv_search_clear_history})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_seach_icon:
                KeyBordUtil.reqFocus(etSeachContent);
                break;
            case R.id.tv_seach_cancel:
                KeyBordUtil.hideSoftKeyboard(etSeachContent);
                if (etSeachContent.hasContent()) {
                    search(etSeachContent.getText().toString());
                } else {
                    finish();
                }
                break;
            case R.id.tv_search_clear_history:
                rlSearchHistoryCon.setVisibility(View.GONE);
                DbCore.getDaoSession().getSearchEntryDao().deleteAll();
                mSearchHistoryAdapter.clear();
                break;
        }
    }

    @Override
    public void getHotTag(SearchHotTagBean data) {
        setAdapter(data.getData());
    }

    @Override
    public void searchResult(SearchAllResultBean.DataBean result) {
        List<SearchItemBean> searchItemBeen = SearchUtil.changeData(result, true,true);
        if (EmptyUtils.isNotEmpty(searchItemBeen)) {
            setLoadedTipState(LoadingTip.LoadStatus.finish);
        } else {
            setLoadedTipState(LoadingTip.LoadStatus.empty);
        }
        mSearchAllAdapter.replaceAll(searchItemBeen);
    }

}
