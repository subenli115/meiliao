package com.ziran.meiliao.ui.workshops.fragment;

import android.os.Message;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.commonwidget.FilterTextView;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.constant.BaseItemId;
import com.ziran.meiliao.ui.base.CommonHttpFragment;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.OrderCreateResultBean;
import com.ziran.meiliao.ui.bean.SearchItemBean;
import com.ziran.meiliao.ui.bean.TeamCustomBean;
import com.ziran.meiliao.ui.me.activity.MyTeamOrderActivity;
import com.ziran.meiliao.ui.workshops.activity.CourseLibraryActivity;
import com.ziran.meiliao.ui.workshops.activity.CrowdFundingChooseTopicActivity;
import com.ziran.meiliao.ui.workshops.contract.TeamCustomContract;
import com.ziran.meiliao.ui.workshops.presenter.TeamCustomPresenter;
import com.ziran.meiliao.ui.workshops.util.CheckBaseItemUtil;
import com.ziran.meiliao.ui.workshops.util.ServiceDialogUtil;
import com.ziran.meiliao.ui.workshops.widget.PeopleSelectView;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.SpanUtils;
import com.ziran.meiliao.widget.BaseItemView;
import com.ziran.meiliao.widget.calendar.entity.CalendarInfo;
import com.ziran.meiliao.widget.pupop.BasePopupWindow;
import com.ziran.meiliao.widget.pupop.CalendarPopupWindow;
import com.ziran.meiliao.widget.pupop.DataPopupWindow;
import com.ziran.meiliao.widget.pupop.ValidatePhoneDialog;
import com.wevey.selector.dialog.SimpleDialog;

import java.util.LinkedList;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * 内容:团建定制
 * Created by Administrator on 2017/1/7.
 */

public class TeamCustomFragment extends CommonHttpFragment<TeamCustomPresenter, CommonModel> implements TeamCustomContract.View, BaseItemId, AppConstant.SearchId {


    @Bind(R.id.ntb)
    NormalTitleBar ntb;
    @Bind(R.id.bivGoal)
    BaseItemView bivGoal;



    @Bind(R.id.bivTeacher)
    BaseItemView bivTeacher;
    @Bind(R.id.tv_crowd_funding_user_msg_input_submit)
    FilterTextView tvCrowdFundingUserMsgInputSubmit;
    @Bind(R.id.bivCourse)
    BaseItemView mBivCourse;
    @Bind(R.id.bivPrice)
    BaseItemView mBivPrice;
    @Bind(R.id.bivPeople)
    BaseItemView mBivPeople;
    @Bind(R.id.peopleSelectView)
    PeopleSelectView mPeopleSelectView;
    @Bind(R.id.bivTime)
    BaseItemView mBivTime;
    private CalendarPopupWindow calendarPopupWindow;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_team_custom;
    }


    int count;
    private DataPopupWindow mDataPopupWindow;
    private LinkedList<CalendarInfo> checkDatas;
    private SearchItemBean mTeacherBean,mCourseBean;
    private boolean showSelectTeacher;
    private int totalPrice = 20000;
    @Override
    protected void initOther() {

        ntb.setRightImagSrc(R.mipmap.nav_servicer);
        ntb.setRightImag2Src(R.mipmap.nav_course);
        ntb.setOnRightImag2Listener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(CourseLibraryActivity.class);
            }
        });
        ntb.setOnRightImagListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceDialogUtil.showDialog(getActivity());
            }
        });


        mPeopleSelectView.setOnValueChangeListener(new PeopleSelectView.OnValueChangeListener() {
            @Override
            public void onValueChange(String newValue) {
                mBivPeople.setContent(newValue);
                int price = mPeopleSelectView.getPrice();
                int i = totalPrice / price;
                SpanUtils spanUtils = new SpanUtils();
                spanUtils.append("¥"+i).append("人").setFontSize(11,true);
                mBivPeople.setRightText(spanUtils.create());
            }
        });
        mDataPopupWindow = new DataPopupWindow(getContext());

        mRxManager.on(AppConstant.RXTag.CROWD_FUNDING_CHOOSE_DATA, new Action1<Message>() {
            @Override
            public void call(Message message) {
                switch (message.what) {
                    case WHAT_HOME_TEACHER_LIST:
                        mTeacherBean = (SearchItemBean) message.obj;
                        bivTeacher.setContent(mTeacherBean.getName());
                        showSelectTeacher = false;
                        break;
                    case WHAT_TOPIC:
                        mCourseBean = (SearchItemBean) message.obj;
                        mBivCourse.setContent(mCourseBean.getTitle());
                        showSelectTeacher = false;
                        break;
                }
            }
        });

        mDataPopupWindow.setOnDissmisListener(new BasePopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
//                if (mDataPopupWindow.getItem() == bivType &&  "讲座".equals(bivType.getContent())){
//                    bean = null;
//                    bivPeople.setContent("");
//                }
            }
        });
        mRxManager.on(AppConstant.RXTag.BASE_ITEM_VIEW_CLICK_ID, new Action1<Integer>() {
            @Override
            public void call(Integer id) {
                if (isPause) return;
                count++;
                switch (id) {
                    case ID_TEACHER:
                        CrowdFundingChooseTopicActivity.startAction(TYPE_FORM_HOME_TEACHER_LIST);

                        break;
                    case ID_COURSE:
                        CrowdFundingChooseTopicActivity.startAction(TYPE_FORM_TOPIC);

                        break;

                    case ID_TIME://开始时间
                        if (calendarPopupWindow == null) {
                            calendarPopupWindow = new CalendarPopupWindow(getContext());
                        }
                        calendarPopupWindow.setBaseItemView(mBivTime);

                        calendarPopupWindow.show();
                        break;

                    case ID_CROWD_FUNDING_CROWD_SPONSOR_MSG: //目的
                        if (checkDataBeanIsNull()) return;
                        mDataPopupWindow.setItemData(mDataBean.getDestinationList(), bivGoal, "目的");
                        break;
                    case ID_TEAM_PRICE_DETAIL: //

                        final SimpleDialog simpleDialog = new SimpleDialog(getContext());
                        simpleDialog.setCancelable(true);
                        simpleDialog.setCanceledOnTouchOutside(true);
                      View  contentView = View.inflate(getContext(), R.layout.dialog_team_price_detail, null);
                      ViewUtil.setText(contentView,R.id.tv_dialog_tips,"");
                      ViewUtil.setOnClickListener(contentView, R.id.iv_close, new View.OnClickListener() {
                          @Override
                          public void onClick(View v) {
                              simpleDialog.dismiss();
                          }
                      });
                        simpleDialog.setContentView(contentView);
                        break;
                }
            }

        });

        mPresenter.getListMissionBuiltInfo(ApiKey.MISSION_BUILT_LIST_MISSION_BUILT_INFO, MapUtils.getDefMap(true));
    }

    @Override
    public void onResume() {
        super.onResume();
        showSelectTeacher = false;
    }

    private boolean checkDataBeanIsNull() {
        if (EmptyUtils.isEmpty(mDataBean)) {
            ToastUitl.showShort("正在初始化数据,请稍后重试");
            return true;
        }
        return false;
    }


    @OnClick(R.id.tv_crowd_funding_user_msg_input_submit)
    public void onViewClicked() {

        if (CheckBaseItemUtil.check(bivGoal, "团建目的不能为空")  && CheckBaseItemUtil.check(mBivTime, "团建时间不能为空")) {
            final ValidatePhoneDialog dialog = new ValidatePhoneDialog(getContext());
            dialog.setCallBack(new ViewUtil.BaseCallBack() {
                @Override
                public void call() {
                    submit(dialog.getPhone());
                }
            });
            dialog.show();
        }
    }

    private void submit(String phone) {
        Map<String, String> stringMap = MapUtils.getDefMap(true);
        stringMap.put("phone", phone);
        stringMap.put("destinationName", bivGoal.getContent());//团建目的（ID）
        if (EmptyUtils.isNotEmpty(mTeacherBean)) {
            stringMap.put("authorName", mTeacherBean.getName());//指定老师
            stringMap.put("authorId", mTeacherBean.getId());//指定老师
        }

//        stringMap.put("targetMembers", bivPeople.getContent());//团建人数
//        stringMap.put("typeName", bivType.getContent());    //团建类型
//        stringMap.put("time", bivTime.getContent());       //团建时间
//        stringMap.put("totalTime", bivDuration.getContent());  //总时间
//        stringMap.put("avgPrice", String.valueOf(pricedSelectView.getPrice()));   //价格

        mPresenter.postMissionBuilt(ApiKey.MISSION_BUILT_CREATE_MISSION_BUILT, stringMap);
    }

    private TeamCustomBean.DataBean mDataBean;


    @Override
    public void showMissionBuiltInfo(TeamCustomBean.DataBean result) {
        mDataBean = result;
    }

    @Override
    public void postMissionBuilt(OrderCreateResultBean.DataBean result) {
        startActivity(MyTeamOrderActivity.class, getBundle(0, result.getOrderId()), true);
    }

}
