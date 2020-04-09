package com.ziran.meiliao.ui.workshops.fragment;

import android.view.View;
import android.widget.LinearLayout;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.WpyxConfig;
import com.ziran.meiliao.common.commonutils.AdViewpagerUtil;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.MyOnScrollListener;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.MultiItemBean;
import com.ziran.meiliao.ui.priavteclasses.activity.SearchActivity;
import com.ziran.meiliao.ui.workshops.adapter.WorkshopsMainAdapter;
import com.ziran.meiliao.ui.workshops.contract.QJGMainContract;
import com.ziran.meiliao.ui.workshops.presenter.QJGMainPresenter;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.SlideSearchView;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * 导聆-分类-内容Fragment
 * Created by Administrator on 2017/1/7.
 */

public class WorkshopsMainFragment extends CommonRefreshFragment<QJGMainPresenter, CommonModel> implements QJGMainContract.View {

    @Bind(R.id.slideSearchView)
    SlideSearchView mSlideSearchView;
    @Bind(R.id.iv_workshops_main_title_bar)
    LinearLayout ivWorkshopsMainTitleBar;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_workshops_main;
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        WorkshopsMainAdapter workshopsMain1Adapter = new WorkshopsMainAdapter(getContext());
        mRxManager.on(AppConstant.RXTag.BIND_VIEWPAGER_UTIL, new Action1<AdViewpagerUtil>() {
            @Override
            public void call(AdViewpagerUtil util) {
                adViewpagerUtil = util;
                if (adViewpagerUtil != null){
                    MyOnScrollListener listener = new MyOnScrollListener(iRecyclerView, ivWorkshopsMainTitleBar, adViewpagerUtil.getViewPager(), AppConstant.RXTag
                            .WORKSHOPS_MAIN_TOP_BAR_SHOW_HIDE);
                    listener.setOnChangeListener(new MyOnScrollListener.OnChangeListener() {
                        @Override
                        public void change(boolean showOrHide) {
                            mSlideSearchView.toggle();
                        }
                    });
                    iRecyclerView.addOnScrollListener(listener);
                }

            }
        });
        mSlideSearchView.setText(WpyxConfig.getHotWord());
//        ViewUtil.setText(mSlideSearchView,WpyxConfig.getHotWord());
        return workshopsMain1Adapter;
    }

    @Override
    protected void loadData() {
        mPresenter.getData(ApiKey.QJG_GET_REC_DATA, MapUtils.getOnlyPage(page));
    }




    @OnClick({R.id.iv_workshops_main_back, R.id.slideSearchView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_workshops_main_back:
                finish();
                break;
            case R.id.slideSearchView:
                SearchActivity.startAction(WpyxConfig.getHotWord());
                break;
        }
    }

    @Override
    public void showData( List<MultiItemBean> result) {
        updateData(result);
    }
}
