package com.ziran.meiliao.ui.main.fragment;

import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.alibaba.fastjson.JSONArray;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MeiliaoConfig;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.entry.UserSelectBean;
import com.ziran.meiliao.im.utils.MainHomeHeadViewUtil;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.HomeBannerBean;
import com.ziran.meiliao.ui.bean.HomeMenuBean;
import com.ziran.meiliao.ui.bean.HomeRecommendBean;
import com.ziran.meiliao.ui.bean.UserBean;
import com.ziran.meiliao.ui.main.adapter.HomeRecommendAdapter;
import com.ziran.meiliao.ui.priavteclasses.activity.SelectUserActivity;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.NewCacheUtil;


import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;


/**
 * 主界面
 */

public class NewMainHomeFragment extends CommonRefreshFragment<CommonPresenter, CommonModel>
        implements CommonContract.View<Result> {
    private static final int REQUEST_CODE_B =5 ;
    @Bind(R.id.tv)
    TextView tv;


    private MainHomeHeadViewUtil mainHomeHeadViewUtil;
    private UserSelectBean resultBean;
    private LocationClient mLocationClient;
    private NewCacheUtil newCacheUtil;
    private List<UserBean.DataBean> records;

    @Override
    protected void initView() {
        super.initView();
        tv.setText(MyAPP.getmUserBean().getRegion());
        mLocationClient = new LocationClient(getActivity().getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        mLocationClient.start();
        mainHomeHeadViewUtil = new MainHomeHeadViewUtil(iRecyclerView);
        newCacheUtil = new NewCacheUtil(getContext());
        View headView = mainHomeHeadViewUtil.getHeadView();
        ViewTreeObserver observer = headView.getViewTreeObserver();

        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                headView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int height = headView.getMeasuredHeight();
                ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) loadedTip.getLayoutParams();
                p.setMargins(0, mainHomeHeadViewUtil.getHeadView().getHeight()+200, 0,0);
                loadedTip.setLayoutParams(p);
                loadedTip.setbg();
            }
        } );
        getData();
    }



    private void getData() {
        List banner = newCacheUtil.getDataList("banner", HomeBannerBean.DataBean.class);
        if(banner==null){
            Map<String, String> defMap = MapUtils.getDefMap(true);
            defMap.put("type", "1");
            mPresenter.getData(ApiKey.ADMIN_EXHIBITION_LIST, defMap, HomeBannerBean.class);
        }else {
            mainHomeHeadViewUtil.setData(banner);
        }
        List menu = newCacheUtil.getDataList("menu", HomeMenuBean.DataBean.class);
        if(menu==null){
            Map<String, String> defMap1 = MapUtils.getDefMap(true);
            defMap1.put("type", "1");
            mPresenter.getData(ApiKey.ADMIN_APPMENU_PAGE, defMap1, HomeMenuBean.class);
        }else {
            mainHomeHeadViewUtil.setMenuData(menu);
        }
    }



    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_main_home_new;
    }



    @Override
    public CommonRecycleViewAdapter getAdapter() {
        iRecyclerView.setLoadMoreEnabled(true);

        return new HomeRecommendAdapter(getContext(),-1);
    }

    @Override
    protected void loadData() {
            refresh(resultBean);
    }


    private void refresh(UserSelectBean bean) {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("id", MyAPP.getUserId());
        if(bean!=null){
            page=1;
            if(bean.getObjective()!=null){
                defMap.put("objective",bean.getObjective());
            }
            if(bean.getFigure()!=null){
                defMap.put("shape",bean.getFigure());
            }
            if(bean.getConstellation()!=null){
                defMap.put("constellation",bean.getConstellation());
            }

            defMap.put("startHeight",bean.getLeftHeight());
            defMap.put("startAge",bean.getLeftAge());
            defMap.put("endHeight",bean.getRightHeight());
            defMap.put("endAge",bean.getRightAge());
            if(bean.getOnline().equals("1")){
                defMap.put("status",bean.getOnline());
            }
            defMap.put("sex",bean.getSex());
        }
        defMap.put("size","20");
        defMap.put("current",page+"");
        mPresenter.getData(ApiKey.ADMIN_USER_RECOMMEND, defMap, HomeRecommendBean.class);
    }

    @Override
    public void returnData(Result result) {
        if (result instanceof HomeBannerBean) {
            List<HomeBannerBean.DataBean> data = ((HomeBannerBean) result).getData();
            if(data!=null&&data.size()>0){
                mainHomeHeadViewUtil.setData(data);
                newCacheUtil.saveBannerBean(data);
            }
        }else if(result instanceof HomeRecommendBean){
            records = ((HomeRecommendBean) result).getData().getRecords();
            if(records.size()==0){
//                setEmptyMsg("暂无内容", R.mipmap.ic_empty_nocontent);
                refresh(null);
            }else {
                if(resultBean==null&&records.size()>3){
                    UserBean.DataBean dataBean = MyAPP.getmUserBean();
                    records.add(3,dataBean);
                }
                updateData(records);
            }
        }else if(result instanceof HomeMenuBean){
            List<HomeMenuBean.DataBean> records = ((HomeMenuBean) result).getData();
            mainHomeHeadViewUtil.setMenuData(records);
            newCacheUtil.saveMenuBean(records);
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_B:
                if (resultCode == RESULT_OK) {
                     resultBean =data.getParcelableExtra("USER_INFO");
                    page=1;
                    onRefreshBy(false);
                }
            default:
                break;
        }


    }

    //点击监听
    @OnClick({R.id.all_sx})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.all_sx:
                Intent intent = new Intent(getContext(), SelectUserActivity.class);
                if(resultBean!=null){
                    intent.putExtra("resultBean",resultBean);
                }
                startActivityForResult(intent,REQUEST_CODE_B);
                break;
        }
    }

    @Override
    public void showLoading(String title) {
        loadedTip.setVisibility(View.GONE);
//        super.showLoading(title);
    }

    /**
     * 定位SDK监听函数
     *
     * @author
     */
    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null) {
                return;
            }
           String longitude = location.getLongitude() + "";
           String  latitude = location.getLatitude() + "";
            MeiliaoConfig.setLongitude(longitude);
            MeiliaoConfig.setLatitude(latitude);

        }
    }


    public BDLocationListener myListener = new MyLocationListener();

}