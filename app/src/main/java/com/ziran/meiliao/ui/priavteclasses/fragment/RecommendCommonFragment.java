package com.ziran.meiliao.ui.priavteclasses.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.zhy.autolayout.AutoLinearLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MeiliaoConfig;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.entry.UserSelectBean;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.HomeRecommendBean;
import com.ziran.meiliao.ui.main.adapter.HomeRecommendAdapter;
import com.ziran.meiliao.ui.priavteclasses.activity.SelectUserActivity;
import com.ziran.meiliao.utils.MapUtils;

import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;
import static com.ziran.meiliao.constant.ApiKey.ADMIN_USER_NEARBY;
import static com.ziran.meiliao.constant.ApiKey.ADMIN_USER_REALPEOPLE;


/**
 * Created by Administrator on 2019/1/31.
 */
public class RecommendCommonFragment extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract.View<HomeRecommendBean> {
    @Bind(R.id.ntb_title)
    NormalTitleBar ntb;
    @Bind(R.id.arl)
    AutoLinearLayout arl;
    @Bind(R.id.tv)
    TextView tv;
    private int type;
    private static final int REQUEST_CODE_B =5 ;
    private UserSelectBean resultBean;

    @Override
    public void returnData(HomeRecommendBean result) {

        updateData(result.getData().getRecords());
    }



    @Override
    public CommonRecycleViewAdapter getAdapter() {

        return new HomeRecommendAdapter(getContext(),type);
    }

    @Override
    protected void initView() {
        arl.setVisibility(View.VISIBLE);
        tv.setText(MyAPP.getmUserBean().getRegion());
        type = getIntentExtra(getActivity().getIntent(), "type",-1);
        super.initView();
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_common_list;
    }

    @Override
    protected void initBundle(Bundle extras) {
        try {

        } catch (Exception e) {
        }
    }


    @Override
    protected void loadData() {
        refresh(resultBean);
    }

    private void refresh(UserSelectBean bean) {
        Map<String, String> map = MapUtils.getDefMap(true);
        map.put("id", MyAPP.getUserId());
        map.put("size", "20");
        map.put("current", page + "");
        if(bean!=null){
            page=1;
            if(bean.getObjective()!=null){
                map.put("objective",bean.getObjective());
            }
            if(bean.getFigure()!=null){
                map.put("shape",bean.getFigure());
            }
            if(bean.getConstellation()!=null){
                map.put("constellation",bean.getConstellation());
            }
            map.put("startHeight",bean.getLeftHeight());
            map.put("startAge",bean.getLeftAge());
            map.put("endHeight",bean.getRightHeight());
            map.put("endAge",bean.getRightAge());
            if(bean.getOnline().equals("0")){
                map.put("status",bean.getOnline());
            }
            map.put("sex",bean.getSex());
        }
        if(type==1){
            mPresenter.getData(ADMIN_USER_REALPEOPLE, map, HomeRecommendBean.class);
        }else {
            if(MeiliaoConfig.getLatitude().equals("")){
                map.put("latitude", MyAPP.getmUserBean().getLatitude());
                map.put("longitude", MyAPP.getmUserBean().getLongitude());
            }else {
                map.put("latitude", MeiliaoConfig.getLatitude());
                map.put("longitude", MeiliaoConfig.getLongitude());
            }
            mPresenter.getData(ADMIN_USER_NEARBY, map, HomeRecommendBean.class);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
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
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_B:
                if (resultCode == RESULT_OK) {
                    resultBean =data.getParcelableExtra("USER_INFO");
                    page=1;
                    onRefreshBy(true);

                }
            default:
                break;
        }


    }
}