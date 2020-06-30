package com.ziran.meiliao.ui.me.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.im.activity.RecommedPreviewActivity;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.ReportListBean;
import com.ziran.meiliao.ui.bean.UserAccountBean;
import com.ziran.meiliao.ui.main.presenter.GuidePresenter;
import com.ziran.meiliao.utils.Utils;
import com.ziran.meiliao.widget.ItemGroupView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

import static com.ziran.meiliao.constant.ApiKey.ADMIN_DICT_TYPE;

/**
 * 举报内容列表
 * Created by Administrator on 2017/1/4.
 */

public class ReportSelectActivity extends  CommonHttpActivity<CommonPresenter, CommonModel> implements CommonContract.ActionView<Result, Result> {


    @Bind(R.id.igv1)
    ItemGroupView igv1;
    @Bind(R.id.igv2)
    ItemGroupView igv2;
    @Bind(R.id.igv3)
    ItemGroupView igv3;
    @Bind(R.id.igv4)
    ItemGroupView igv4;
    @Bind(R.id.igv5)
    ItemGroupView igv5;
    @Bind(R.id.igv6)
    ItemGroupView igv6;
    private List<ReportListBean.DataBean> listData;
    private ArrayList<ItemGroupView> list;



    public static void startAction() {
        Activity activity = AppManager.getAppManager().currentActivity();
        Intent intent = new Intent(activity, ReportSelectActivity.class);
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
         list = new ArrayList<>();
        list.add(igv1);
        list.add(igv2);
        list.add(igv3);
        list.add(igv4);
        list.add(igv5);
        list.add(igv6);
        getTitleList();
    }

    private void getTitleList() {
            mPresenter.getDataOneHead(ADMIN_DICT_TYPE,"report", MyAPP.getAccessToken(), ReportListBean.class);
    }

    //点击监听
    @OnClick({R.id.igv1,R.id.igv2,R.id.igv3,R.id.igv4,R.id.igv5,R.id.igv6})
    public void onClick(View view) {
        if (Utils.isFastDoubleClick()) {
            return;
        }
           gotoActivity(((ItemGroupView)view).gettvLeftText().getText().toString());
    }
        public void gotoActivity(String title){
            ReportDetailActivity.startAction(title);
        }

    @Override
    public void returnData(Result result) {
        if(result instanceof ReportListBean){
            listData = ((ReportListBean) result).getData();
                for(int i=0;i<listData.size();i++){
                    list.get(i).setLeftText(listData.get(i).getLabel());
                    list.get(i).setVisibility(View.VISIBLE);
                }

        }

    }

    @Override
    public void returnAction(Result result) {

    }
}