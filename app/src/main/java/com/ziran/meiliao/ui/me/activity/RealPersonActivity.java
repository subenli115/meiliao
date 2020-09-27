package com.ziran.meiliao.ui.me.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.ReportListBean;
import com.ziran.meiliao.utils.Utils;
import com.ziran.meiliao.widget.ItemGroupView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

import static com.ziran.meiliao.constant.ApiKey.ADMIN_DICT_TYPE;


public class RealPersonActivity extends CommonHttpActivity<CommonPresenter, CommonModel> implements CommonContract.ActionView<Result, Result> {


    private List<ReportListBean.DataBean> listData;
    private ArrayList<ItemGroupView> list;



    public static void startAction() {
        Activity activity = AppManager.getAppManager().currentActivity();
        Intent intent = new Intent(activity, RealPersonActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.ac_report_select;
    }

    @Override
    public void initPresenter() {
        if (mModel != null && mPresenter != null) {
            mPresenter.setVM(this, mModel);
        }
    }


    @Override
    public void initView() {
    }

    private void getTitleList() {
        mPresenter.getDataOneHead(ADMIN_DICT_TYPE,"report", MyAPP.getAccessToken(), ReportListBean.class);
    }

    //点击监听
    @OnClick({})
    public void onClick(View view) {
        if (Utils.isFastDoubleClick()) {
            return;
        }
    }

    @Override
    public void returnData(Result result) {

    }

    @Override
    public void returnAction(Result result) {

    }
}