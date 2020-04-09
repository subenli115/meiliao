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
import com.ziran.meiliao.ui.bean.ActisData;
import com.ziran.meiliao.ui.bean.AlbumListBean;
import com.ziran.meiliao.ui.bean.CourseLibraryTeamBean;
import com.ziran.meiliao.ui.bean.SearchItemBean;
import com.ziran.meiliao.ui.bean.SpecColumnData;
import com.ziran.meiliao.ui.bean.StringDataBean;
import com.ziran.meiliao.ui.bean.TeacherDetailBean;
import com.ziran.meiliao.ui.decompressionmuseum.activity.AlbumDetailActivity;
import com.ziran.meiliao.ui.priavteclasses.activity.GongZuoFangActivity;
import com.ziran.meiliao.ui.priavteclasses.util.SearchUtil;
import com.ziran.meiliao.ui.workshops.activity.CourseLibraryTeacherBuyActivity;
import com.ziran.meiliao.ui.workshops.activity.CourseLibraryTeamDetailActivity;
import com.ziran.meiliao.ui.workshops.activity.CrowdFundingProjectMsgActivity;
import com.ziran.meiliao.ui.workshops.activity.CrowdFundingUserMsgActivity;
import com.ziran.meiliao.ui.workshops.adapter.CourseLibraryTeacherDetailAdapter;
import com.ziran.meiliao.ui.workshops.contract.TeacherDetailContract;
import com.ziran.meiliao.ui.workshops.presenter.TeacherDetailPresenter;
import com.ziran.meiliao.ui.workshops.util.QJGDataUtil;
import com.ziran.meiliao.ui.workshops.util.ServiceDialogUtil;
import com.ziran.meiliao.utils.CatchUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.pupop.SharePopupWindow;

import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 减压馆-分类Fragment
 * Created by Administrator on 2017/1/7.
 */

public class CourseLibraryTeacherDetailFragment extends CommonRefreshFragment<TeacherDetailPresenter, CommonModel> implements
        TeacherDetailContract.View {


    @Bind(R.id.ntb)
    NormalTitleBar ntb;
    @Bind(R.id.tv_course_library_teacher_detail_buy)
    FilterTextView tvCourseLibraryTeacherDetailBuy;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_course_library_teacher_detail;
    }

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
                    mSharePopupWindow.openSaveShare(ApiKey.FAMOUS_TEACHERS_FAMOUS_TEACHERS_SHARE_SAVE,stringMap,mShareBean.getIsShare());
                }
                if (EmptyUtils.isNotEmpty(mShareBean)) {
                    mSharePopupWindow.setShareBean(mShareBean);
                    mSharePopupWindow.show();
                }
            }
        });
    }

    private CourseLibraryTeacherDetailAdapter courseLibraryTeacherDetailAdapter;
    private String id = "1";
    private Map<String, String> stringMap;

    @Override
    protected void initBundle(Bundle extras) {
        id = extras.getString(AppConstant.ExtraKey.FROM_ID);
        stringMap = MapUtils.getOnlyCan("id", this.id);
        stringMap.put("famousTeacherId",id);
    }

    public void postCollect() {
        stringMap.put("collect", String.valueOf(mShareBean.isCollect()==1?0:1));
        mPresenter.postCollect(ApiKey.FAMOUS_TEACHERS_COLLECT_FAMOUS_TEACHER, stringMap);
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        courseLibraryTeacherDetailAdapter = new CourseLibraryTeacherDetailAdapter(getContext());
        return courseLibraryTeacherDetailAdapter;
    }

    @Override
    public void onRefresh() {
        if (courseLibraryTeacherDetailAdapter != null) courseLibraryTeacherDetailAdapter.onRefresh();
        super.onRefresh();

    }

    @Override
    protected void loadData() {
        mPresenter.postData(ApiKey.FAMOUS_TEACHERS_FAMOUS_TEACHERS_DETAIL,stringMap );
    }


    @Override
    public void onItemClick(ViewGroup parent, View view, Object obj, int position) {
        if (obj instanceof CourseLibraryTeamBean) {
            startActivity(CourseLibraryTeamDetailActivity.class,getBundle(((CourseLibraryTeamBean) obj).getId()));
        } else if (obj instanceof ActisData) { //工作坊
            GongZuoFangActivity.startAction(getContext(), (ActisData) obj);
        } else if (obj instanceof SpecColumnData) {
//            ZhuanLanDetailActivity.startAction(getContext(), obj);
        } else if (obj instanceof AlbumListBean) {
            AlbumListBean albumListBean = (AlbumListBean) obj;
            AlbumDetailActivity.startAction(getActivity(), String.valueOf(albumListBean.getId()));
        }else if (obj instanceof SearchItemBean){
            SearchUtil.onItemClick(getContext(),(SearchItemBean) obj);
        }
    }


    @OnClick({R.id.tv_course_library_teacher_detail_service, R.id.tv_course_library_teacher_detail_buy,R.id.tv_course_library_teacher_detail_fa})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_course_library_teacher_detail_service:
                ServiceDialogUtil.showDialog(getActivity());
                break;
            case R.id.tv_course_library_teacher_detail_fa:
                mPresenter.checkInfo(ApiKey.CROWN_FUND_CHECK_CROWD_FUND_USER, stringMap);
//                ServiceDialogUtil.showDialog(getActivity());
                break;
            case R.id.tv_course_library_teacher_detail_buy:
                startActivity(CourseLibraryTeacherBuyActivity.class, getBundle(id));
                break;
        }
    }

    private TeacherDetailBean.DataBean.FamousTeacherMapBean mShareBean;

    @Override
    public void onDestroy() {
        ServiceDialogUtil.cancel();
        super.onDestroy();
    }

    @Override
    public void showData(final TeacherDetailBean.DataBean result) {
        CatchUtil.execute(new MyCallBack() {
            @Override
            public void call() {
                mShareBean = result.getFamousTeacherMap();
                ntb.getIvRight().setSelected(mShareBean.isCollect()==1);
                ntb.setTitleText(result.getFamousTeacherMap().getName());
//                ViewUtil.setText(tvCourseLibraryTeacherDetailBuy, HtmlUtil.format("团建购买\\n（¥3000／人，20人起）",mShareBean.getCourseNumbers()));
            }
        });
        updateData(QJGDataUtil.getTeacherData(result));
    }

    @Override
    public void showCollectResult(boolean isCollect) {
        ImageView ivRight = ntb.getIvRight();
        if (ivRight != null) {
            mShareBean.toggleCollect();
            ivRight.setSelected(mShareBean.isCollect() == 1);

        }
    }

    @Override
    public void showCheckInfo(StringDataBean result) {
        int data = result.getIntData();
        if (data == 1) {
            Bundle bundle = getBundle(2, id);
            bundle.putString("name", mShareBean.getName());
            bundle.putString("picture", mShareBean.getPicture());

            startActivity(CrowdFundingProjectMsgActivity.class, bundle);
        } else {
            Bundle bundle = new Bundle();
            bundle.putBoolean("hasPost", data == 0);
            startActivity(CrowdFundingUserMsgActivity.class, bundle);
        }
    }
}
