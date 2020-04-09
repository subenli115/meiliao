package com.ziran.meiliao.ui.workshops.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.KeyBordUtil;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.constant.BaseItemId;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpFragment;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.CrowdFundingDetailBean;
import com.ziran.meiliao.ui.bean.OrderCreateResultBean;
import com.ziran.meiliao.ui.bean.TopBean;
import com.ziran.meiliao.ui.me.activity.MyCrowdFundingOrderActivity;
import com.ziran.meiliao.ui.me.widget.CourseDetailView;
import com.ziran.meiliao.ui.workshops.util.CheckBaseItemUtil;
import com.ziran.meiliao.ui.workshops.widget.NumberAddView;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.PayUtil;
import com.ziran.meiliao.utils.SpanUtils;
import com.ziran.meiliao.widget.BaseItemView;
import com.ziran.meiliao.widget.pupop.DataPopupWindow;
import com.ziran.meiliao.widget.pupop.SimplePayPopupWindow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * 减压馆-分类Fragment
 * Created by Administrator on 2017/1/7.
 */

public class CourseLibraryCrowdFundingBuyFragment extends CommonHttpFragment<CommonPresenter, CommonModel> implements CommonContract
        .View<OrderCreateResultBean>, BaseItemId {


    @Bind(R.id.courseDetailView)
    CourseDetailView mCourseDetailView;
    @Bind(R.id.biv_name)
    BaseItemView bivName;
    @Bind(R.id.biv_phone)
    BaseItemView bivPhone;
    @Bind(R.id.biv_sex)
    BaseItemView bivSex;
    @Bind(R.id.biv_age)
    BaseItemView bivAge;
    @Bind(R.id.biv_email)
    BaseItemView bivEmail;
    @Bind(R.id.biv_demo)
    BaseItemView bivDemo;
    @Bind(R.id.numberAddView)
    NumberAddView numberAddView;
    @Bind(R.id.tv_course_library_crowd_funding_buy_price)
    TextView tvCourseLibraryCrowdFundingBuyPrice;

    private int onePrice = 2200;


    private CrowdFundingDetailBean.DataBean.OthersNeededBean neededBean;
    private TopBean mTopBean;
    private int priceColor;
    private int newCount = 1;

    private SimplePayPopupWindow mSimplePayPopupWindow;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_course_library_crowd_funding_buy;
    }

    private DataPopupWindow mDataPopupWindow;

    @Override
    protected void initOther() {
        priceColor = Color.parseColor("#FFA008");
        Intent intent = getActivity().getIntent();
        if (intent != null && intent.getExtras() != null) {
            neededBean = intent.getExtras().getParcelable(AppConstant.ExtraKey.EXTRAS_DATA_NEEDED);
            mTopBean = intent.getExtras().getParcelable(AppConstant.ExtraKey.EXTRAS_DATA);
            onePrice = (int) mTopBean.getAvgPrice();
            setPrice(1);
        }
        regexViews = new ArrayList<>();

        initViewShows();
        setData();
        numberAddView.setOnNumberChangeListener(new NumberAddView.OnNumberChangeListener() {
            @Override
            public void onNumberChange(int newNumber) {
                newCount = newNumber;
                setPrice(newNumber);
            }
        });
        mDataPopupWindow = new DataPopupWindow(getContext());
        mRxManager.on(AppConstant.RXTag.BASE_ITEM_VIEW_CLICK_ID, new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                switch (integer) {
                    case ID_AGE:
                        bivName.hideKeyBoard();
                        mDataPopupWindow.setItemData(DataPopupWindow.getPeopleData(3, 120, 1), bivAge, "年龄");
                        break;
                }
            }
        });
    }

    private List<BaseItemView> regexViews;

    private void initViewShows() {

        if (neededBean.getName() == 1) {
            regexViews.add(bivName);
            bivName.setVisibility(View.VISIBLE);
        }
        if (neededBean.getPhone() == 1) {
            regexViews.add(bivPhone);
            bivPhone.setVisibility(View.VISIBLE);
        }
        if (neededBean.getSex() == 1) {
            regexViews.add(bivSex);
            bivSex.setVisibility(View.VISIBLE);
        }
        if (neededBean.getAge() == 1) {
            regexViews.add(bivAge);
            bivAge.setVisibility(View.VISIBLE);
        }
        if (neededBean.getEmail() == 1) {
            regexViews.add(bivEmail);
            bivEmail.setVisibility(View.VISIBLE);
        }
        if (neededBean.getRemarks() == 1) {
            regexViews.add(bivDemo);
            bivDemo.setVisibility(View.VISIBLE);
        }
    }


    private void setData() {
        if (mTopBean != null) {
            mCourseDetailView.setParams(mTopBean.getPicture(), mTopBean.getTitle(), mTopBean.getStartTime(), mTopBean.getEndTime(),
                    mTopBean.getAddress(), 0);
//        } else {
//            mCourseDetailView.setParams(AppConstant.URL, "10天深度睡眠", "2017-12-02", "2017-12-08", "北京通州", 0);
        }
    }

    private void setPrice(int newNumber) {
        tvCourseLibraryCrowdFundingBuyPrice.setText(new SpanUtils().append("合计：").append(String.valueOf(newNumber * onePrice))
                .setForegroundColor(priceColor).append("元").setForegroundColor(priceColor).setFontSize(12, true).create());
    }


    @OnClick(R.id.tv_course_library_crowd_funding_buy_buy)
    public void onViewClicked() {
        if (CheckBaseItemUtil.check(regexViews)) {
//            reqBuy();
            if (mSimplePayPopupWindow == null) {
                mSimplePayPopupWindow = new SimplePayPopupWindow(getContext());
                mSimplePayPopupWindow.setPamras(onePrice * newCount, "购买众筹课程" , String.valueOf(mTopBean.getId()), "crowd");
                mSimplePayPopupWindow.setOnPayCallBack(new PayUtil.OnPayCallBack() {
                    @Override
                    public void onPaySuccess(int platform) {
                        reqBuy();
                    }
                    @Override
                    public void onPayFailed() {

                    }
                });
            }
            mSimplePayPopupWindow.show();
        }
        KeyBordUtil.hideSoftKeyboard(numberAddView.getEtNumber());
    }

    private void reqBuy() {
        Map<String, String> stringMap = MapUtils.getDefMap(true);
        stringMap.put("id", String.valueOf(mTopBean.getId()));
        stringMap.put("sex", bivSex.getSex());
        bivName.put("name", stringMap);
        bivPhone.put("phone", stringMap);
        bivAge.put("age", stringMap);
        bivEmail.put("email", stringMap);
        bivDemo.put("remarks", stringMap);
        stringMap.put("supportMembers", String.valueOf(newCount));
        stringMap.put("totalPrice", String.valueOf(onePrice * newCount));
        mPresenter.postData(ApiKey.CROWN_FUND_BUY_CROWD_FUND, stringMap, OrderCreateResultBean.class);
    }

    @Override
    public void returnData(OrderCreateResultBean result) {
        startActivity(MyCrowdFundingOrderActivity.class,getBundle(MyCrowdFundingOrderActivity.FROM_TYPE_LAUNCH, result.getData().getOrderId()),true);
    }
}
