package com.ziran.meiliao.ui.workshops.fragment;

import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonwidget.FilterTextView;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.JYGColumnBean;
import com.ziran.meiliao.ui.workshops.adapter.CourseLibraryTeamDetailAdapter;
import com.ziran.meiliao.ui.workshops.util.ServiceDialogUtil;
import com.ziran.meiliao.utils.MapUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 减压馆-分类Fragment
 * Created by Administrator on 2017/1/7.
 */

public class CourseLibraryWorkshopsDetailFragment extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract
        .View<JYGColumnBean> {


    @Bind(R.id.ntb)
    NormalTitleBar ntb;
    @Bind(R.id.tv_course_library_work_shops_yj_buy)
    FilterTextView tvCourseLibraryWorkShopsYjBuy;
    @Bind(R.id.tv_course_library_work_shops_tg_buy)
    FilterTextView tvCourseLibraryWorkShopsTgBuy;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_course_library_work_shops_detail;
    }

    @Override
    protected void initOther() {

    }

    CourseLibraryTeamDetailAdapter courseLibraryTeacherDetailAdapter;

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        courseLibraryTeacherDetailAdapter = new CourseLibraryTeamDetailAdapter(getContext());
        return courseLibraryTeacherDetailAdapter;
    }

    @Override
    public void onRefresh() {
//        if (courseLibraryTeacherDetailAdapter != null) courseLibraryTeacherDetailAdapter.onRefresh();
        super.onRefresh();

    }

    @Override
    protected void loadData() {
        mPresenter.getData(ApiKey.GET_ALBUM_COLUMN, MapUtils.getDefMap(false), JYGColumnBean.class);
    }

    @Override
    public void returnData(JYGColumnBean result) {
//        addData(QJGDataUtil.getTeacherData());
    }


    @Override
    public void onDestroy() {
        ServiceDialogUtil.cancel();
        super.onDestroy();
    }


    @OnClick({R.id.tv_course_library_work_shops_yj_buy, R.id.tv_course_library_work_shops_tg_buy,R.id.tv_course_library_service})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_course_library_service:
                ServiceDialogUtil.showDialog(getActivity());
                break;
            case R.id.tv_course_library_work_shops_yj_buy:// 原价购买

                break;
            case R.id.tv_course_library_work_shops_tg_buy://团购购买

                break;
        }
    }

}
