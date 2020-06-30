package com.ziran.meiliao.ui.main.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MeiliaoConfig;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.entry.MusicEntry;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.ActisData;
import com.ziran.meiliao.ui.bean.AlbumBean;
import com.ziran.meiliao.ui.bean.HeadData;
import com.ziran.meiliao.ui.bean.HomeDataBean;
import com.ziran.meiliao.ui.bean.NewHomeDataBean;
import com.ziran.meiliao.ui.bean.PicsBean;
import com.ziran.meiliao.ui.bean.PractiveChartBean;
import com.ziran.meiliao.ui.bean.StringDataBean;
import com.ziran.meiliao.ui.bean.TempActivityBean;
import com.ziran.meiliao.ui.main.adapter.MainHomeAdapter;
import com.ziran.meiliao.ui.main.contract.MainHomeContract;
import com.ziran.meiliao.ui.main.presenter.MainHomePresenter;
import com.ziran.meiliao.ui.priavteclasses.activity.GongZuoFangActivity;
import com.ziran.meiliao.ui.settings.activity.MessageActivity;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.SlideSearchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;


/**
 * 首页Fragment
 */
public class MainHomeFragment extends CommonRefreshFragment<MainHomePresenter, CommonModel> implements MainHomeContract.View {


    @Bind(R.id.rl_main_home_root)
    RelativeLayout mRlRootView;
    @Bind(R.id.ll_main_home_title_bar)
    View llMainHomeTitleBar;
    @Bind(R.id.slideSearchView)
    SlideSearchView mSearchView;
//    @Bind(R.id.tv_main_home_title_bar_search)
//    TextView tvMainHomeTitleBarSearch;
    @Bind(R.id.iv_main_home_title_ba_message)
    ImageView ivMessage;


    //搜索热词
    private String hotWord;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_main_home1;
    }



    @Override
    protected void initView() {
        super.initView();
        //首页头部帮助类
        //订阅点击更多的监听
        mRxManager.on(AppConstant.RXTag.MAIN_HOME_MORE_CLICK, new Action1<HeadData>() {
            @Override
            public void call(HeadData headData) {
                if (headData.getId() == HeadData.Type.ZHIBO) {
                } else if (headData.getId() == HeadData.Type.COURSE) {
                } else if (headData.getId() == HeadData.Type.ZHUANLAN) {
//                    startActivity(ZhuanLanMoreActivity.class);//工作坊更多页面
                }
            }
        });
        mSearchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SearchActivity.startAction(hotWord);
            }
        });
        //大会内容窗口
        mRxManager.on(AppConstant.RXTag.CONFERENCE_GET_CONFERENCE, new Action1<Boolean>() {
            @Override
            public void call(Boolean s) {
                OkHttpClientManager.postAsync(ApiKey.CONFERENCE_GET_CONFERENCE, MapUtils.getDefMap(true), new NewRequestCallBack<TempActivityBean>(TempActivityBean.class) {
                    @Override
                    protected void onSuccess(TempActivityBean result) {
                        if (EmptyUtils.isNotEmpty(result.getData())) {
                            if (EmptyUtils.isNotEmpty(result.getData().getShareUrl())) {
                                return;
                            }
                        }
                        RxManagerUtil.post(AppConstant.RXTag.GET_GAIN_SPREAD, true);
                    }

                    @Override
                    public void onError(String msg, int code) {
                        RxManagerUtil.post(AppConstant.RXTag.GET_GAIN_SPREAD, true);
                    }
                });
            }
        });
        mPresenter.getUnReadCount(MapUtils.getDefMap(true));

        HandlerUtil.runMain(new Runnable() {
            @Override
            public void run() {
                if (!isShowHodeData) onRefresh();
            }
        }, 2000);
    }




    //创建首页列表适配器
    @Override
    public CommonRecycleViewAdapter getAdapter() {
        return new MainHomeAdapter(getContext(), new MainHomeAdapter.MainHomeMultiItemType());
    }

    //执行请求数据
    @Override
    protected void loadData() {
        mPresenter.getHomeData(MapUtils.getDefMap(true));
    }

    //点击item监听
    @Override
    public void onItemClick(ViewGroup parent, View view, Object bean, int position) {
        if (!CheckUtil.check(getContext(), getView())) return;
        int itemViewType = mAdapter.getItemViewType(position);
        switch (itemViewType) {
            case HeadData.Type.GONGZUOFANG_TOP:
                break;
            case HeadData.Type.GONGZUOFANG_LEFT:
                ActisData actisData = EmptyUtils.parseObject(bean);
                if (actisData != null) {
                    actisData.setPosition(position);
                    GongZuoFangActivity.startAction(getContext(), actisData);  //工作坊活动详情页面
                }
                break;
            case HeadData.Type.ZHIBO:
                break;
            case HeadData.Type.ZHUANLAN:
//                ZhuanLanDetailActivity.startAction(getContext(), bean);
                break;
        }
    }


    //轮播图点击监听
    @Override
    public void onViewPagerItemClick(View clickView, int position, String url) {
        PicsBean picsBean = (PicsBean) adViewpagerUtil.getDatas().get(position);
        if ("zhibo".equals(picsBean.getType())) {
            if (!CheckUtil.check(getContext(), getView())) return;
        } else if ("history".equals(picsBean.getType())) {
//            HorizontalHistoryActivity.startAction(getContext(), picsBean.getLink(), 0);
        } else if ("h5".equals(picsBean.getType())) {
            ActisData dataBean = new ActisData();
            dataBean.setPicture(picsBean.getPicture());
            dataBean.setUrl(picsBean.getLink());
            dataBean.setSignup(picsBean.getLink());
            dataBean.setShareTitle(picsBean.getShareTitle());
            dataBean.setShareDescript(picsBean.getShareDescript());
            dataBean.setId(Integer.parseInt(picsBean.getId()));
            dataBean.setIsCollect(picsBean.isCollect());
            GongZuoFangActivity.startAction(getContext(), dataBean);
        }
    }

    //button点击监听
    @OnClick({ R.id.iv_main_home_title_ba_message})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.iv_main_home_title_ba_message:
                //跳转到消息详情页面
                if (CheckUtil.check(getContext(), view)) {
                    MessageActivity.startAction(getContext(), unReadCount);
                    ivMessage.setImageResource(R.mipmap.index_nav_message);
                }
                break;
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        //停止今天头条的滚动
    }


    @Override
    public void onResume() {
        super.onResume();
        //恢复今天头条的滚动
    }

    private boolean isShowHodeData;

    @Override
    public void showHomeData(HomeDataBean result) {
        isShowHodeData = true;
        //显示首页数据
        HomeDataBean.DataBean data = result.getData();
        //首页轮播图
        if (adViewpagerUtil == null) {
            adViewpagerUtil.setOnAdItemClickListener(this);
        }
        adViewpagerUtil.setDataAndRef(data.getPics(), parseImgs(data.getPics()));
        boolean flag = false;
        hotWord = data.getHotWord();                            //搜索热词
        MeiliaoConfig.setHotWord(hotWord);
        if (EmptyUtils.isNotEmpty(hotWord)) {
            mSearchView.setText(hotWord);
//            tvMainHomeTitleBarSearch.setText(hotWord);
        }
        List list = new ArrayList();
        //绑定headView
        flag = EmptyUtils.isEmpty(data.getNews());
        if (EmptyUtils.isNotEmpty(data.getZhibo())) {
            list.add(HeadData.create(HeadData.Type.ZHIBO, "精彩直播", flag));
            list.addAll(data.getZhibo());
            flag = true;
        }
        if (EmptyUtils.isNotEmpty(data.getSubscription())) {
            list.add(HeadData.create(HeadData.Type.ZHUANLAN, "专栏", false));
            list.addAll(data.getSubscription());
        }
        if (EmptyUtils.isNotEmpty(data.getActis())) {
            list.add(HeadData.create(HeadData.Type.COURSE, "工作坊", flag));
            list.addAll(data.getActis());
        }
        updateData(list); //显示item数据

    }

    private ImageView floatView;


    private int unReadCount;


    @Override
    public void showUnReadCount(StringDataBean result) {
        unReadCount = result.getIntData();
        ivMessage.setImageResource(unReadCount > 0 ? R.mipmap.index_nav_messages : R.mipmap.index_nav_message);
    }

    @Override
    public void showAuthor(AlbumBean result) {

    }

    @Override
    public void showChartData(PractiveChartBean.DataBean result) {

    }

    @Override
    public void showNewHomeData(NewHomeDataBean result) {

    }

    @Override
    public void updateCC(NewHomeDataBean.DataBean.RecAlbumBean result) {

    }

    @Override
    public void showMusicList(List<MusicEntry> musicEntries) {

    }

    @Override
    public void showNewMusicList(List<MusicEntry> musicEntries) {

    }

}
