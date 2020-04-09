package com.ziran.meiliao.ui.workshops.fragment;

import android.os.Message;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.KeyBordUtil;
import com.ziran.meiliao.common.commonwidget.FilterTextView;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.constant.BaseItemId;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpFragment;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.JYGColumnBean;
import com.ziran.meiliao.ui.workshops.activity.CrowdFundingChooseTopicActivity;
import com.ziran.meiliao.widget.BaseItemView;
import com.ziran.meiliao.widget.pupop.PayResultPopupWindow;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * 减压馆-分类Fragment
 * Created by Administrator on 2017/1/7.
 */

public class CourseLibraryWorkshopsBuyFragment extends CommonHttpFragment<CommonPresenter, CommonModel> implements BaseItemId,AppConstant.SearchId,
        CommonContract.View<JYGColumnBean> {


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
    BaseItemView bivCourseLibraryCrowdFundingBuyPhone;
    @Bind(R.id.biv_code)
    BaseItemView bivCode;
    @Bind(R.id.biv_course_library_crowd_funding_buy_demo)
    BaseItemView bivCourseLibraryCrowdFundingBuyDemo;
    @Bind(R.id.tv_course_library_submit)
    FilterTextView tvCourseLibrarySubmit;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_course_library_work_shops_buy;
    }

    @Override
    public void returnData(JYGColumnBean result) {

    }

    private int count;

    @Override
    protected void initOther() {
        setData();
        mRxManager.on(AppConstant.RXTag.BASE_ITEM_VIEW_CLICK_ID, new Action1<Integer>() {
            @Override
            public void call(Integer id) {
                if (isPause) return;
                count++;
                switch (id) {
                    case ID_COURSE:
                        CrowdFundingChooseTopicActivity.startAction(TYPE_FORM_TOPIC);
                        break;
                    case ID_TEACHER:
//                        bivTeacher.setIntro("胡君梅" + count);
                        CrowdFundingChooseTopicActivity.startAction(TYPE_FORM_TEACHER);
                        break;
                    case ID_TYPE:
//                        bivType.setIntro("正念" + count);
                        break;
                    case ID_TIME:
                        bivTime.setContent("12月15日" + count);
//                        showCrowdFundingTimePopup();

                        break;
                    case ID_ADDRESS:
//                        bivAddress.setIntro("广州市白云区" + count);
                        break;
                    case ID_PEOPLE:
                        bivPeople.setContent("60");
                        break;
                    case ID_CROWD_FUNDING_CROWD_FUNDING_TIME:
//                        bivDays.setIntro("15天" + count);
                        break;
                    case ID_CROWD_FUNDING_CROWD_OFFICIAL_PRICE:
//                        bivDays.setIntro("15天" + count);
                        break;
                    case ID_CROWD_FUNDING_CROWD_USED_TEMPLE:
//                        bivDays.setIntro("15天" + count);
//                        startActivity(CrowdFundingUsedTemplateActivity.class);
                        break;
                    case ID_CROWD_FUNDING_CROWD_SPONSOR_MSG:
//                        showReferenceTemplate();
                        break;
                    case ID_CROWD_FUNDING_CROWD_AVATAR:
//                        bivDays.setIntro("15天" + count);
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

    private void setData() {
//        ViewUtil.setText(tvCourseLibraryCrowdFundingBuyTitle,"10天深度睡眠");
//        ViewUtil.setText(tvCourseLibraryCrowdFundingBuyAddressAndDate,"北京通州\n2017-12-02至2017-12-08");
//        ImageLoaderUtils.display(getContext(),ivCourseLibraryCrowdFundingBuyPic, AppConstant.URL);
    }


    @OnClick(R.id.tv_course_library_submit)
    public void onViewClicked(View view) {
        KeyBordUtil.hideSoftKeyboard(ntb);
        PayResultPopupWindow mResultPopupWindow = new PayResultPopupWindow(getContext());
        mResultPopupWindow.setResult(true, "订单提交成功，我们将尽快安排活动助手与您联系", "");
        mResultPopupWindow.setContainerLayoutParams(240,160);
        mResultPopupWindow.show();
    }

}
