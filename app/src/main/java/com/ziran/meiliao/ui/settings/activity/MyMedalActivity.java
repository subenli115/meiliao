package com.ziran.meiliao.ui.settings.activity;


import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.FitnessMedalBean;
import com.ziran.meiliao.ui.settings.adapter.FitnessMedalGridAdapter;
import com.ziran.meiliao.utils.MapUtils;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.List;

import butterknife.Bind;

import static com.ziran.meiliao.constant.ApiKey.JSCOURSE_LISTMEDAL;


public class MyMedalActivity extends CommonHttpActivity<CommonPresenter, CommonModel> implements CommonContract.ActionView<Result, Result> {

    @Bind(R.id.ntb)
    public NormalTitleBar ntb;
    @Bind(R.id.empty)
    public AutoRelativeLayout empty;
    @Bind(R.id.tv_count)
    public TextView tvCount;

    @Bind(R.id.arl_main)
    public AutoRelativeLayout arlMain;
    @Bind(R.id.gridView)
    public GridView gridView;
    private FitnessMedalGridAdapter fitnessGridAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.ac_my_medal;
    }

    @Override
    public void returnData(Result result) {
        if(result instanceof FitnessMedalBean){
            FitnessMedalBean bean=(FitnessMedalBean)result;
            List<FitnessMedalBean.DataBean.MedalBean> medal = bean.getData().getMedal();
            if(medal!=null&&medal.size()!=0){
                tvCount.setText("已获得"+medal.size()+"枚勋章");
                fitnessGridAdapter = new FitnessMedalGridAdapter(mContext,bean.getData().getMedal());
                gridView.setAdapter(fitnessGridAdapter);
            }else {
                empty.setVisibility(View.VISIBLE);
                arlMain.setVisibility(View.GONE);
            }
        }
    }
    @Override
    public void initPresenter () {
        if (mModel != null && mPresenter != null) {
            mPresenter.setVM(this, mModel);
        }
    }

    @Override
    public void returnAction(Result result) {

    }

    @Override
    public void initView() {
        super.initView();
        ntb.setBackGroundColor(R.color.transparent);
        ntb.setNewTitleText("我的勋章");
        mPresenter.postData(JSCOURSE_LISTMEDAL, MapUtils.getDefMap(true), FitnessMedalBean.class);
    }
}
