package com.ziran.meiliao.ui.main.fragment;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhy.autolayout.AutoRelativeLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MeiliaoConfig;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.commonutils.SPUtils;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.entry.MusicEntry;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.service.ServiceManager;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.ActisData;
import com.ziran.meiliao.ui.bean.AlbumBean;
import com.ziran.meiliao.ui.bean.BootCampBean;
import com.ziran.meiliao.ui.bean.CourseGZLBean;
import com.ziran.meiliao.ui.bean.Entity;
import com.ziran.meiliao.ui.bean.HeadData;
import com.ziran.meiliao.ui.bean.HomeAlbumBean;
import com.ziran.meiliao.ui.bean.HomeDataBean;
import com.ziran.meiliao.ui.bean.HotAlbumBean;
import com.ziran.meiliao.ui.bean.NewHomeDataBean;
import com.ziran.meiliao.ui.bean.PicsBean;
import com.ziran.meiliao.ui.bean.PractiveChartBean;
import com.ziran.meiliao.ui.bean.RecActivityBean;
import com.ziran.meiliao.ui.bean.StringDataBean;
import com.ziran.meiliao.ui.bean.TempActivityBean;
import com.ziran.meiliao.ui.bean.ZhiBoData;
import com.ziran.meiliao.ui.bean.ZhuanLanBean;
import com.ziran.meiliao.ui.main.adapter.NewMainHomeAdapter;
import com.ziran.meiliao.ui.main.contract.MainHomeContract;
import com.ziran.meiliao.ui.main.presenter.MainHomePresenter;
import com.ziran.meiliao.ui.main.util.NewMainHomeHeadViewUtil;
import com.ziran.meiliao.ui.priavteclasses.activity.GongZuoFangActivity;
import com.ziran.meiliao.ui.settings.activity.MessageActivity;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.DeviceUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.MyValueTempCache;
import com.ziran.meiliao.utils.PrefUtils;
import com.ziran.meiliao.widget.LocationUtils;
import com.ziran.meiliao.widget.SlideSearchView;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;

import static com.ziran.meiliao.constant.ApiKey.PRACTICE_GETCHARTDATAV2;

/**
 * 主界面
 */

public class NewMainHomeFragment extends CommonRefreshFragment<MainHomePresenter, CommonModel> implements MainHomeContract.View {

    @Bind(R.id.rl_main_home_root)
    CoordinatorLayout mRlRootView;
    @Bind(R.id.scrolling_header)
    AutoFrameLayout scrollingHeader;
    @Bind(R.id.slideSearchView)
    SlideSearchView mSearchView;
    @Bind(R.id.iv_main_home_title_ba_message)
    ImageView ivMessage;
    @Bind(R.id.all_action)
    AutoFrameLayout all_action;
    @Bind(R.id.tv_know)
    TextView tvKnow;
    @Bind(R.id.all_js)
    AutoLinearLayout allJs;
    @Bind(R.id.ll_main_home_title_bar)
    View llMainHomeTitleBar;
    @Bind(R.id.max_num)
    TextView maxNum;
    @Bind(R.id.mid_num)
    TextView midNum;
    @Bind(R.id.arl_bg)
    AutoRelativeLayout arlBg;
    private ArrayList<Entity> list;
    private String hotWord;
    private int unReadCount;
    private boolean flag;
    private NewMainHomeHeadViewUtil newMainHomeHeadViewUtil;
    private List<MusicEntry> mMusicList;
    private String albumId;
    private AlbumBean mAuthor;
    private LocListener locListener;
    private NewMainHomeAdapter newMainHomeAdapter;
    private HomeAlbumBean homeAlbumBean;
    private Gson gson;
    private ArrayList homeList;
    private static Context mContext;


    @Override
    public void showHomeData(HomeDataBean result) {

    }


    @Override
    public void showUnReadCount(StringDataBean result) {
        unReadCount = result.getIntData();
        ivMessage.setImageResource(unReadCount > 0 ? R.mipmap.index_nav_messages : R.mipmap.index_nav_message);
    }

    @Override
    public void showAuthor(AlbumBean result) {
        mAuthor = result;
        mAuthor.setAlbumId(albumId);
        MyValueTempCache.get().put(AppConstant.DOWN_ALBUM, mAuthor);
    }

    @Override
    public void showChartData(PractiveChartBean.DataBean result) {
        List<PractiveChartBean.DataBean.ChartDataBean> chartData = result.getChartData();
    }


    public void startPlayer(int musicId, int id, AlbumBean albumBean, Context mContext, List<MusicEntry> mMusicList) {
        MusicEntry musicEntry = new MusicEntry();
        musicEntry.setMusicId(musicId);
        if(mMusicList!=null&&mMusicList.size()>0){
            for(int i=0;i<mMusicList.size();i++){
                if(mMusicList.get(i).getMusicId()==musicId){
                    MyValueTempCache.setCurrentPlayMusicEntry(mMusicList.get(i));
                }
            }
            if(MyValueTempCache.getCurrentData()==null){
                ToastUitl.showShort("暂无该音频");
               return;
            }
        }
        MyAPP.mServiceManager.refreshMusicList(mMusicList);
        MyAPP.mServiceManager.setAlbumPicture(albumBean.getRoundPic());
        MyAPP.mServiceManager.setAlbumName1(albumBean.getTitle());

        MyAPP.mServiceManager.setClickFrom(ServiceManager.CLICK_FROM_ALBUM);
        MyAPP.mServiceManager.setAlbumId(id+"");
        MyAPP.mServiceManager.setIsbuy(albumBean.isBuy());
        MyAPP.mServiceManager.setAlbum(albumBean);
        MyValueTempCache.get().put(AppConstant.SPKey.PLAY_DATA,mMusicList);
    }

    @Override
    public void showNewHomeData(NewHomeDataBean result) {
        NewHomeDataBean.DataBean   data = result.getData();
        String albumBeanString = getSaveAlbumData(data.getRecAlbum());
        if(mMusicList==null){
            albumId= data.getRecAlbum().getAlbumId() + "";
            if(!albumBeanString.equals("")){
                albumId= homeAlbumBean.getAlbumId();
            }
            mPresenter.getAuthorData(ApiKey.GET_AUTHOR_DATA, MapUtils.getAlbumData(albumId, 1));
        }
        hotWord = data.getHotWord();
        if (EmptyUtils.isNotEmpty(data.getHotAlbum())) {
            homeList.add(HeadData.create(HeadData.Type.ALBUM, result.getData().getHotAlbumWord(), flag));
            HotAlbumBean hotAlbumBean = new HotAlbumBean();
            hotAlbumBean.setHotAlbum(data.getHotAlbum());
            homeList.add(hotAlbumBean);
        }
        RecActivityBean recActivityBean = new RecActivityBean();
        recActivityBean.setBeans(data.getRecActivity());
        if(data.getRecActivity().size()>0){
            homeList.add(recActivityBean);
        }
        MeiliaoConfig.setHotWord(hotWord);
        if (EmptyUtils.isNotEmpty(hotWord)) {
            mSearchView.setText(hotWord);
        }
        if (EmptyUtils.isNotEmpty(data.getPractice())) {
            homeList.add(HeadData.create(HeadData.Type.BOOTCAMP, "训练营", flag));
            BootCampBean bootCampBean = new BootCampBean();
            bootCampBean.setBeans(data.getPractice());
            homeList.add(bootCampBean);
        }
        if (EmptyUtils.isNotEmpty(data.getActivity())) {
            homeList.add(HeadData.create(HeadData.Type.COURSE, "课程", flag));
            CourseGZLBean courseGZLBean = new CourseGZLBean();
            courseGZLBean.setActivity(data.getActivity());
            homeList.add(courseGZLBean);
        }
        if (EmptyUtils.isNotEmpty(data.getSubscription())) {
            homeList.add(HeadData.create(HeadData.Type.ZHUANLAN, "专栏", flag));
            ZhuanLanBean zhuanLanBean = new ZhuanLanBean();
            zhuanLanBean.setSubscription(data.getSubscription());
            homeList.add(zhuanLanBean);
        }
        updateData(homeList); //显示item数据
}

    private String getSaveAlbumData(NewHomeDataBean.DataBean.RecAlbumBean data) {
        String albumBeanString = PrefUtils.getString("albumBean", "",getContext());
        if(!albumBeanString.equals("")){
            homeAlbumBean =gson.fromJson(albumBeanString, HomeAlbumBean.class);
        }else{
        }
        return albumBeanString;
    }

    @Override
    public void updateCC(NewHomeDataBean.DataBean.RecAlbumBean result) {
                    getSaveAlbumData(result);
    }

    @Override
    public void showMusicList(List<MusicEntry> musicEntries) {
    }

    @Override
    public void showNewMusicList(List<MusicEntry> musicEntries) {
        mMusicList=musicEntries;
    }



    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_main_home_new;
    }


    @Override
    public void onResume() {
        super.onResume();
        showQuitPopWindow();
    }


    private void showQuitPopWindow() {
        boolean isFisrtHome = PrefUtils.getBoolean("isFisrtHome", false, getContext());
        if(!isFisrtHome){
            mRlRootView.setEnabled(false);
            all_action.setVisibility(View.VISIBLE);
            all_action.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return true;
                }
            });
            PrefUtils.putBoolean("isFisrtHome", true, getContext());
        }
    }

    //轮播图点击监听
    @Override
    public void onViewPagerItemClick(View clickView, int position, String url) {
        PicsBean picsBean = (PicsBean) adViewpagerUtil.getDatas().get(position);
        if ("zhibo".equals(picsBean.getType())) {
            if (!CheckUtil.check(getContext(), getView())) return;
        } else if ("history".equals(picsBean.getType())) {
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

    @Override
    protected void initView() {
        super.initView();
         mContext = getContext();
        FileUtil.createFileFolder(MeiliaoConfig.setPhone(SPUtils.getString("phone")), mContext);
        try {
        //初始数据
            iRecyclerView.setFocusableInTouchMode(false);
            iRecyclerView.setFocusable(false);
            iRecyclerView.setHasFixedSize(true);
             gson=new Gson();
        initHeadData();
        mRxManager.on(AppConstant.RXTag.MAIN_HOME_MORE_CLICK, new Action1<HeadData>() {
            @Override
            public void call(HeadData headData) {
            }
        });

        mSearchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
//大会内容窗口
        mRxManager.on(AppConstant.RXTag.CONFERENCE_GET_CONFERENCE, new Action1<Boolean>() {
            @Override
            public void call(Boolean s) {
                OkHttpClientManager.postAsync(ApiKey.CONFERENCE_GET_CONFERENCE, MapUtils.getDefMapSp(true), new NewRequestCallBack<TempActivityBean>(TempActivityBean.class) {
                    @Override
                    protected void onSuccess(TempActivityBean result) {
                        if (EmptyUtils.isNotEmpty(result.getData())) {
                            if (EmptyUtils.isNotEmpty(result.getData().getShareUrl())) {
                                String shareUrl = PrefUtils.getString("shareUrl", "", mContext);
                                if(shareUrl.equals("")){
                                    showZndh(result.getData());
                                }
                                PrefUtils.putString("shareUrl",result.getData().getShareUrl(),mContext);
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

        } catch (Exception e) {
            e.printStackTrace();
        }
        mRxManager.on(AppConstant.RXTag.MPS_COMPLETION, new Action1<String>() {

            @Override
            public void call(String aBoolean) {
            if(aBoolean.equals("next")){
                String albumBeanString = PrefUtils.getString("albumBean", "", getContext());
                    homeAlbumBean =gson.fromJson(albumBeanString, HomeAlbumBean.class);
            }
            }
        });
    }

    private void update() {
        homeList  = new ArrayList();
        Map<String, String> defMap = MapUtils.getDefMapSp(true);
        String locations = LocationUtils.getInstance().getLocations(getContext());
        if(locations.equals("0,0")){
            defMap.put("address","");
        }else{
            defMap.put("address",locations);
        }
        defMap.put("platform","安卓");
        defMap.put("cellphone",android.os.Build.BRAND+android.os.Build.MODEL);
        defMap.put("version", DeviceUtil.getVersionCode(getContext()));
        defMap.put("page",page+"");
        defMap.put("portVersion", MeiliaoConfig.portVersion);
        mPresenter.getNewHomeData(defMap);
        getChartData();
        mPresenter.getUnReadCount(MapUtils.getDefMapSp(true));

    }
    private void getChartData() {
        Map<String, String> chartMap = MapUtils.getDefMapSp(true);
        chartMap.put("type","0");
        mPresenter.getChartData(PRACTICE_GETCHARTDATAV2, chartMap);
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
         newMainHomeAdapter = new NewMainHomeAdapter(getContext(), getActivity(),new NewMainHomeAdapter.NewMainHomeMultiItemType(),mRxManager);
        newMainHomeAdapter.setItemClickListener(new NewMainHomeAdapter.ItemClickListener() {
            @Override
            public void itemClick(int id) {
                switch (id){
                    case R.id.all_yxs:
                        break;
                    case R.id.all_zztj:
                        break;
                }
            }
        });
        return newMainHomeAdapter;
    }
    @Override
    protected void loadData() {
        update();
    }
    /**
     * 将经纬度转换成中文地址
     *
     * @param location
     * @return
     */
    private String getLocationAddress(Location location) {
        String add = "";
        Geocoder geoCoder = new Geocoder(getContext(), Locale.CHINESE);
        try {
            List<Address> addresses = geoCoder.getFromLocation(
                    location.getLatitude(), location.getLongitude(),
                    1);
            Address address = addresses.get(0);
                add = address.getAddressLine(0) + address.getAddressLine(1);
        } catch (IOException e) {
            add = "";
            e.printStackTrace();
        }
        return add;
    }

    /**
     * 获取位置后监听
     */
    private class LocListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            String locationAddress = getLocationAddress(location);
            //TODO:当前地址为：locationAddress="北京市海淀区华奥饭店公司写字间中关村创业大街"
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {


        }
    }
    private void initHeadData() {
        list = new ArrayList<>();
        locListener = new LocListener();
        String[] array = getResources().getStringArray(R.array.home_main);
        for (int i = 0; i < array.length; i++) {
            Entity entity = new Entity();
            entity.title = array[i];
            List<Entity.InnerEntity> innerEntities = new ArrayList<>();
            for (int j = 1; j < 11; j++) {
                innerEntities.add(new Entity.InnerEntity(("Inner Title" + i + " - " + j), j % 3 == 0 ? R.mipmap.ic_launcher : R.mipmap.ic_launcher));
            }
            entity.innerEntities = innerEntities;
            list.add(entity);
        }
    }
    public void startActivity(Class clz) {
        if (CheckUtil.check(getContext())) {
            Intent intent = new Intent(getContext(), clz);
             startActivity(intent);
         }
    }

    //点击item监听
    @Override
    public void onItemClick(ViewGroup parent, View view, Object bean, int position) {
        if (!CheckUtil.check(getContext(), getView())) return;
        int itemViewType = mAdapter.getItemViewType(position);
        switch (itemViewType) {
            case HeadData.Type.TOP_OTHER:
                break;
            case HeadData.Type.GONGZUOFANG_TOP:
                break;
            case HeadData.Type.GONGZUOFANG_LEFT:
                ActisData actisData = EmptyUtils.parseObject(bean);
                break;
            case HeadData.Type.ZHIBO:
                ZhiBoData zhiBoData = EmptyUtils.parseObject(bean);
                break;
            case HeadData.Type.ZHUANLAN:
                break;
        }
    }

    private ImageView floatView;

    private void showZndh(final TempActivityBean.DataBean result) {
    }
    //button点击监听
    @OnClick({ R.id.iv_main_home_title_ba_message,R.id.tv_know,R.id.all_js})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.iv_main_home_title_ba_message:
                //跳转到消息详情页面
                if (CheckUtil.check(getContext(), view)) {
                    MessageActivity.startAction(getContext(), unReadCount);
                    ivMessage.setImageResource(R.mipmap.index_nav_message);
                }
                break;
            case R.id.tv_know:
                all_action.setVisibility(View.GONE);
                mRlRootView.setEnabled(true);
                break;
            case R.id.all_js:
                break;
        }
    }
}
