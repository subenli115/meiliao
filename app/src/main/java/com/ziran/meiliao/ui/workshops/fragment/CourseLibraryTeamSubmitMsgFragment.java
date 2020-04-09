package com.ziran.meiliao.ui.workshops.fragment;

import android.os.Bundle;
import android.os.Message;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.KeyBordUtil;
import com.ziran.meiliao.common.commonwidget.FilterTextView;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.constant.BaseItemId;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpFragment;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.OrderCreateResultBean;
import com.ziran.meiliao.ui.bean.SearchItemBean;
import com.ziran.meiliao.ui.me.activity.MyTeamOrderActivity;
import com.ziran.meiliao.ui.workshops.activity.CrowdFundingChooseTopicActivity;
import com.ziran.meiliao.ui.workshops.util.CheckBaseItemUtil;
import com.ziran.meiliao.ui.workshops.util.ServiceDialogUtil;
import com.ziran.meiliao.widget.BaseItemView;
import com.ziran.meiliao.widget.pupop.CalendarPopupWindow;
import com.ziran.meiliao.widget.pupop.DataPopupWindow;

import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * 减压馆-分类Fragment
 * Created by Administrator on 2017/1/7.
 */

public class CourseLibraryTeamSubmitMsgFragment extends CommonHttpFragment<CommonPresenter, CommonModel> implements BaseItemId,
        AppConstant.SearchId, CommonContract.View<OrderCreateResultBean> {


    @Bind(R.id.ntb)
    NormalTitleBar ntb;
    @Bind(R.id.bivPeople)
    BaseItemView bivPeople;
    @Bind(R.id.bivTime)
    BaseItemView bivTime;
    @Bind(R.id.bivTeacher)
    BaseItemView bivTeacher;
    @Bind(R.id.bivName)
    BaseItemView bivName;
    @Bind(R.id.bivPhone)
    BaseItemView bivPhone;
    @Bind(R.id.bivDemo)
    BaseItemView bivDemo;
    @Bind(R.id.tv_course_library_team_submit_msg_ok)
    FilterTextView tvCourseLibraryTeamSubmitMsgOk;
    private String courseId;

    @Override
    protected void initBundle(Bundle extras) {
        courseId = extras.getString(AppConstant.ExtraKey.FROM_ID);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_course_library_team_submit_msg;
    }

    private CalendarPopupWindow calendarPopupWindow;
    private DataPopupWindow mDataPopupWindow;

    @Override
    protected void initOther() {
        mDataPopupWindow = new DataPopupWindow(getContext());
        ntb.setOnRightImagListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceDialogUtil.showDialog(getActivity());
            }
        });
        mRxManager.on(AppConstant.RXTag.BASE_ITEM_VIEW_CLICK_ID, new Action1<Integer>() {
            @Override
            public void call(Integer id) {
                if (isPause) return;
                switch (id) {
                    case ID_PEOPLE:
                        mDataPopupWindow.setItemData(DataPopupWindow.getPeopleData(20, 35, 1), bivPeople, "人数", true);
                        break;
                    case ID_TEACHER:
                        CrowdFundingChooseTopicActivity.startAction(TYPE_FORM_HOME_TEACHER_LIST);
                        break;
                    case ID_TIME:
                        if (calendarPopupWindow == null) {
                            calendarPopupWindow = new CalendarPopupWindow(getContext());
                        }
                        calendarPopupWindow.setBaseItemView(bivTime);
                        KeyBordUtil.closeKeyboard(getActivity());
                        calendarPopupWindow.show();
                        break;
                }
            }
        });
        mRxManager.on(AppConstant.RXTag.CROWD_FUNDING_CHOOSE_DATA, new Action1<Message>() {
            @Override
            public void call(Message message) {
                switch (message.what) {
                    case CrowdFundingChooseTeacherFragment.WHAT_HOME_TEACHER_LIST:
                        mTeacherListBean = (SearchItemBean) message.obj;
                        bivTeacher.setContent(mTeacherListBean.getName());
                        break;
                }
            }
        });
    }


    @Override
    public void onDestroyView() {
        bivPhone.hideKeyBoard();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ServiceDialogUtil.cancel();
    }

    private SearchItemBean mTeacherListBean;

    @OnClick(R.id.tv_course_library_team_submit_msg_ok)
    public void onViewClicked() {
        if(CheckBaseItemUtil.check(bivPeople,bivTime,bivTeacher,bivName,bivPhone)){
            Map<String, String> teamBuyMap = CheckBaseItemUtil.getTeamBuyMap(courseId, mTeacherListBean.getId(), "1", bivPeople, bivTime,
                    bivName, bivPhone, bivDemo);
            mPresenter.postAction(ApiKey.MISSION_BUILT_BUY_MISSION_BUILT, teamBuyMap, OrderCreateResultBean.class);
        }
    }


    @Override
    public void returnData(OrderCreateResultBean result) {
        startActivity(MyTeamOrderActivity.class, getBundle(1, result.getData().getOrderId()), true);
    }

}
