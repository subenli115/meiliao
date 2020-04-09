package com.ziran.meiliao.ui.me.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpFragment;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.ActisData;
import com.ziran.meiliao.ui.bean.CategoryBean;
import com.ziran.meiliao.ui.me.util.OrderTopViewUtil;
import com.ziran.meiliao.ui.me.widget.CourseDetailView;
import com.ziran.meiliao.ui.me.widget.SomeTextView;
import com.ziran.meiliao.ui.priavteclasses.activity.GongZuoFangActivity;
import com.ziran.meiliao.ui.workshops.util.ServiceDialogUtil;

import butterknife.Bind;

/**
 * 我的活动工作坊界面
 * Created by Administrator on 2017/1/7.
 */

public class MyWorkshopsOrderFragment extends CommonHttpFragment<CommonPresenter, CommonModel> implements CommonContract
        .View<CategoryBean> ,View.OnClickListener{

    @Bind(R.id.ntb)

    NormalTitleBar ntb;
    @Bind(R.id.tv_state)
    TextView tvState;
    @Bind(R.id.courseDetailView)
    CourseDetailView mCourseDetailView;
    @Bind(R.id.someTextView)
    SomeTextView mSomeTextView;
    private ActisData actisData;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_workshops_order;
    }

    @Override
    protected void initBundle(Bundle extras) {

    }

    @Override
    protected void initView() {
        super.initView();
        ntb.setOnRightImagListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceDialogUtil.showDialog(getActivity());
            }
        });
         actisData = (ActisData)getActivity().getIntent().getParcelableExtra("projectList");
        mCourseDetailView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GongZuoFangActivity.startAction(getContext(), actisData);
            }
        });
        setData();

    }

    private void setData() {

        Double aDouble = Double.valueOf(actisData.getPrice());
        mCourseDetailView.setParams(actisData.getPicture(), actisData.getTitle(), actisData.getBeginTime(), actisData.getEndTime(), actisData.getAddress(),
                Integer.parseInt(new java.text.DecimalFormat("0").format(aDouble)));
        mSomeTextView.setParams(actisData.getName(),actisData.getCellPhone() , "", actisData.getOrder(), actisData.getTime());
        OrderTopViewUtil.setState(tvState,2);
    }




    @Override
    public void returnData(CategoryBean result) {

    }

    @Override
    public void onClick(View view) {

    }
}
