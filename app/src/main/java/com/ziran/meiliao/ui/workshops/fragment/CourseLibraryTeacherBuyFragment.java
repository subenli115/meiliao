package com.ziran.meiliao.ui.workshops.fragment;

import android.os.Bundle;
import android.os.Message;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.KeyBordUtil;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonwidget.FilterTextView;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.commonwidget.OnNoDoubleClickListener;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.constant.BaseItemId;
import com.ziran.meiliao.ui.base.CommonHttpFragment;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.OrderCreateResultBean;
import com.ziran.meiliao.ui.bean.SearchItemBean;
import com.ziran.meiliao.ui.bean.StringDataBean;
import com.ziran.meiliao.ui.me.activity.MyTeamOrderActivity;
import com.ziran.meiliao.ui.workshops.activity.CrowdFundingChooseTopicActivity;
import com.ziran.meiliao.ui.workshops.contract.QJGTeacherBuyContract;
import com.ziran.meiliao.ui.workshops.presenter.QJGTeacherBuyPresenter;
import com.ziran.meiliao.ui.workshops.util.CheckBaseItemUtil;
import com.ziran.meiliao.ui.workshops.util.ServiceDialogUtil;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.MapUtils;
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

public class CourseLibraryTeacherBuyFragment extends CommonHttpFragment<QJGTeacherBuyPresenter, CommonModel> implements BaseItemId,
        AppConstant.SearchId, QJGTeacherBuyContract.View {


    @Bind(R.id.ntb)
    NormalTitleBar ntb;
    @Bind(R.id.bivPeople)
    BaseItemView bivPeople;
    @Bind(R.id.bivTime)
    BaseItemView bivTime;
    @Bind(R.id.biv_course)
    BaseItemView bivCourse;
    @Bind(R.id.biv_name)
    BaseItemView bivName;
    @Bind(R.id.biv_course_library_crowd_funding_buy_phone)
    BaseItemView bivPhone;
    @Bind(R.id.biv_code)
    BaseItemView bivCode;
    @Bind(R.id.biv_course_library_crowd_funding_buy_demo)
    BaseItemView bivDemo;
    @Bind(R.id.tv_course_library_submit)
    FilterTextView tvCourseLibrarySubmit;
    private String teacherId;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_course_library_teacher_buy;
    }

    @Override
    protected void initBundle(Bundle extras) {
        teacherId = extras.getString(AppConstant.ExtraKey.FROM_ID);
    }

    private CalendarPopupWindow calendarPopupWindow;
    private DataPopupWindow mDataPopupWindow;
    private String courseId;
    private Map<String, String> codeMap;

    @Override
    protected void initOther() {
        ntb.setOnRightImagListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                ServiceDialogUtil.showDialog(getActivity());
            }
        });
        mDataPopupWindow = new DataPopupWindow(getContext());
        mRxManager.on(AppConstant.RXTag.CROWD_FUNDING_CHOOSE_DATA, new Action1<Message>() {
            @Override
            public void call(Message message) {
                switch (message.what) {
                    case WHAT_TOPIC:
                        SearchItemBean bean = (SearchItemBean) message.obj;
                        bivCourse.setContent(bean.getTitle());
                        courseId = String.valueOf(bean.getId());
                        break;
                }
            }
        });
        mRxManager.on(AppConstant.RXTag.BASE_ITEM_VIEW_CLICK_ID, new Action1<Integer>() {
            @Override
            public void call(Integer id) {
                if (isPause) return;
                switch (id) {
                    case ID_COURSE:
                        CrowdFundingChooseTopicActivity.startAction(TYPE_FORM_TOPIC);
                        break;
                    case ID_TIME:
                        if (calendarPopupWindow == null) {
                            calendarPopupWindow = new CalendarPopupWindow(getContext());
                        }
                        calendarPopupWindow.setBaseItemView(bivTime);
                        KeyBordUtil.closeKeyboard(getActivity());
                        calendarPopupWindow.show();
                        break;
                    case ID_PEOPLE:
                        mDataPopupWindow.setItemData(DataPopupWindow.getPeopleData(20, 35, 1), bivPeople, "人数", true);
                        break;
                    case ID_CODE:
                        //获取验证码
                        if (EmptyUtils.isNotEmpty(bivPhone.getContent())) {
                            codeMap = MapUtils.getDefMap(true);
                            codeMap.put("phoneNum", bivPhone.getContent());
                            codeMap.put("isVoice", "false");
                            mPresenter.getCode(ApiKey.SMS_GET_SMSCODE, codeMap);
                        } else {
                            showShortToast("请输入手机号码");
                        }
                        break;
                }
            }
        });

        mRxManager.on(AppConstant.RXTag.CROWD_FUNDING_CHOOSE_DATA, new Action1<Message>() {
            @Override
            public void call(Message message) {
                switch (message.what) {
                    case WHAT_TOPIC:
                        bivCourse.setContent("五日正念止语");
                        break;
                }
            }
        });
    }


    @OnClick(R.id.tv_course_library_submit)
    public void onViewClicked(View view) {
        if (CheckBaseItemUtil.check(bivName,bivPeople,bivCourse,bivTime,bivPhone)){
            if (EmptyUtils.isNotEmpty(bivCode.getContent())) {
                KeyBordUtil.hideSoftKeyboard(ntb);
                codeMap.put("smsCode",bivCode.getContent());
                mPresenter.checkCode(ApiKey.SMS_CHECK_SMSCODE,codeMap);
            }else{
                showShortToast("请输入验证码");
                bivCode.reqFocus();
            }
        }
    }

    @Override
    public void showGetCodeResult(StringDataBean result) {
        ToastUitl.showShort(result.getResultMsg());
        HandlerUtil.startDjs(bivCode.getRightText());
    }


    @Override
    public void showCheckCodeResult(Result result) {
        Map<String, String> stringMap = MapUtils.getDefMap(true);
        stringMap.put("courseId", courseId);
        stringMap.put("authorId", teacherId);
        stringMap.put("buyTag", "2"); //定制通道标识（名师库、团建库）
        bivPeople.put("supportMembers", stringMap);
        String[] times = bivTime.getContent().split("至");
        if (times.length == 2) {
            stringMap.put("beginTime", times[0]);
            stringMap.put("endTime", times[1]);
        }
        bivPhone.put("phone", stringMap);
        bivName.put("name", stringMap);
        bivDemo.put("remarks", stringMap);
        mPresenter.submit(ApiKey.MISSION_BUILT_BUY_MISSION_BUILT, stringMap);

    }

    @Override
    public void showSubmitResult(OrderCreateResultBean.DataBean resultData) {
        if (EmptyUtils.isNotEmpty(resultData)) {
            startActivity(MyTeamOrderActivity.class, getBundle(1, resultData.getOrderId()),true);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ServiceDialogUtil.cancel();
    }
}
