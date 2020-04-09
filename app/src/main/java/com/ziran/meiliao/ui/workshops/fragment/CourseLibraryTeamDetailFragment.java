package com.ziran.meiliao.ui.workshops.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonwidget.FilterTextView;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.commonwidget.OnNoDoubleClickListener;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.MyCallBack;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.CourseLibraryTeamBean;
import com.ziran.meiliao.ui.bean.MultiItemBean;
import com.ziran.meiliao.ui.bean.StringDataBean;
import com.ziran.meiliao.ui.bean.TeamDetailBean;
import com.ziran.meiliao.ui.workshops.activity.CourseLibraryTeacherDetailActivity;
import com.ziran.meiliao.ui.workshops.activity.CourseLibraryTeamSubmitMsgActivity;
import com.ziran.meiliao.ui.workshops.activity.CrowdFundingProjectMsgActivity;
import com.ziran.meiliao.ui.workshops.activity.CrowdFundingUserMsgActivity;
import com.ziran.meiliao.ui.workshops.adapter.CourseLibraryTeamDetailAdapter;
import com.ziran.meiliao.ui.workshops.contract.TeamDetailContract;
import com.ziran.meiliao.ui.workshops.presenter.TeamDetailPresenter;
import com.ziran.meiliao.ui.workshops.util.QJGDataUtil;
import com.ziran.meiliao.ui.workshops.util.ServiceDialogUtil;
import com.ziran.meiliao.utils.CatchUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.pupop.SharePopupWindow;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 减压馆-分类Fragment
 * Created by Administrator on 2017/1/7.
 */

public class CourseLibraryTeamDetailFragment extends CommonRefreshFragment<TeamDetailPresenter, CommonModel> implements
        TeamDetailContract.View {


    @Bind(R.id.ntb)
    NormalTitleBar ntb;
    @Bind(R.id.tv_course_library_crowd_funding_detail_buy)
    FilterTextView tvCourseLibraryCrowdFundingDetailBuy;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_course_library_team_detail;
    }

    @Override
    protected void initBundle(Bundle extras) {
        courseId = extras.getString(AppConstant.ExtraKey.FROM_ID);
    }

    Map<String, String> stringMap;
    private SharePopupWindow mSharePopupWindow;

    @Override
    protected void initOther() {
        ntb.setOnRightImagListener(new View.OnClickListener() {//收藏
            @Override
            public void onClick(View v) {
                postCollect();
            }
        });
        ntb.setOnRightImag2Listener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                if (mSharePopupWindow == null) {
                    mSharePopupWindow = new SharePopupWindow(getContext());

                    mSharePopupWindow.openSaveShare(ApiKey.MISSION_BUILT_MISSION_BUILT_SHARE_SAVE, stringMap, mDataBean.getIsShare());
                }
                if (EmptyUtils.isNotEmpty(mDataBean)) {
                    mSharePopupWindow.setShareBean(mDataBean);
                    mSharePopupWindow.show();
                }
            }
        });

        stringMap = MapUtils.getDefMap(true);
        stringMap.put("missionBuiltId", courseId);
        stringMap.put("courseId", courseId);
        stringMap.put("tagId", tagId);
    }

    CourseLibraryTeamDetailAdapter courseLibraryTeacherDetailAdapter;

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        courseLibraryTeacherDetailAdapter = new CourseLibraryTeamDetailAdapter(getContext());
        return courseLibraryTeacherDetailAdapter;
    }


    private String courseId = "1";
    private String tagId = "2";


    public void postCollect() {
        stringMap.put("collect", String.valueOf(mDataBean.isCollect() == 1 ? 0 : 1));
        mPresenter.postCollect(ApiKey.MISSION_BUILT_COLLECT_MISSION_BUILT, stringMap);
    }

    @Override
    protected void loadData() {
        mPresenter.postData(ApiKey.MISSION_BUILT_MISSION_BUILT_DETAIL, stringMap);
    }

    private TeamDetailBean.DataBean.MissionBuiltListBean mDataBean;

    @Override
    public void onDestroy() {
        ServiceDialogUtil.cancel();
        super.onDestroy();
    }


    @OnClick({R.id.tv_course_library_service, R.id.tv_course_library_crowd_funding_detail_buy, R.id
            .tv_course_library_crowd_funding_detail_fa})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_course_library_service:
                ServiceDialogUtil.showDialog(getActivity());
                break;
            case R.id.tv_course_library_crowd_funding_detail_buy:
                startActivity(CourseLibraryTeamSubmitMsgActivity.class, getBundle(courseId));
                break;
            case R.id.tv_course_library_crowd_funding_detail_fa:
                mPresenter.checkInfo(ApiKey.CROWN_FUND_CHECK_CROWD_FUND_USER, stringMap);
                break;
        }
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        super.onItemClick(parent, view, o, position);
        switch (mAdapter.getItemViewType(position)) {
            case CourseLibraryTeamDetailAdapter.TYPE_TEAM_LEFT:
                MultiItemBean teamBean = EmptyUtils.parseObject(o);
                CourseLibraryTeamBean teamBean2 =EmptyUtils.parseObject(teamBean.getObj());
                startActivity(CourseLibraryTeacherDetailActivity.class,getBundle(String.valueOf(teamBean2.getAuthorId())));
                break;
        }
    }

    @Override
    public void showData(final TeamDetailBean.DataBean result) {
        CatchUtil.execute(new MyCallBack() {
            @Override
            public void call() {
                List<MultiItemBean> teamDetailData = QJGDataUtil.getTeamDetailDataNew(result.getMissionBuiltList());
                updateData(teamDetailData);
                mDataBean = result.getMissionBuiltList();
                ntb.getIvRight().setSelected(mDataBean.isCollect() == 1);
            }
        });
    }

    @Override
    public void showCollectResult(boolean isCollect) {
        ImageView ivRight = ntb.getIvRight();
        if (ivRight != null) {
            mDataBean.toggleCollect();
            ivRight.setSelected(mDataBean.isCollect() == 1);
        }
    }

    @Override
    public void showCheckInfo(StringDataBean result) {
        int data = result.getIntData();
        if (data == 1) {
            Bundle bundle = getBundle(1, courseId);
            bundle.putString("name", mDataBean.getTitle());
            bundle.putString("picture", mDataBean.getPicture());
            bundle.putString("price", String.valueOf((int)mDataBean.getOfficePrice()));
            startActivity(CrowdFundingProjectMsgActivity.class, bundle);
        } else {
            Bundle bundle = new Bundle();
            bundle.putBoolean("hasPost", data == 0);
            startActivity(CrowdFundingUserMsgActivity.class, bundle);
        }
    }
}
