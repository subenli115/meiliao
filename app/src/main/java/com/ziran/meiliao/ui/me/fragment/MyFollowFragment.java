package com.ziran.meiliao.ui.me.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.TimeUtil;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpFragment;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.OrderInfoBean;
import com.ziran.meiliao.ui.me.util.OrderTopViewUtil;
import com.ziran.meiliao.ui.me.widget.CourseDetailView;
import com.ziran.meiliao.ui.me.widget.SomeTextView;
import com.ziran.meiliao.ui.workshops.util.ServiceDialogUtil;
import com.ziran.meiliao.utils.MapUtils;

import java.util.Calendar;

import butterknife.Bind;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/4 14:58
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/4$
 * @updateDes ${TODO}
 */

public class MyFollowFragment extends CommonHttpFragment<CommonPresenter, CommonModel> implements CommonContract.View<OrderInfoBean> {

    @Bind(R.id.ntb)
    NormalTitleBar ntb;
    @Bind(R.id.tv_state)
    TextView tvState;
    @Bind(R.id.courseDetailView)
    CourseDetailView mCourseDetailView;
    @Bind(R.id.someTextView)
    SomeTextView mSomeTextView;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_team_order;
    }

    private String orderId;

    @Override
    protected void initBundle(Bundle extras) {
        orderId = extras.getString(AppConstant.ExtraKey.FROM_ID); //mbc79761512711915511
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
        mPresenter.postData(ApiKey.MISSION_BUILT_MISSION_BUILT_ORDER_LIST_INTRO, MapUtils.getOnlyCan("orderId", orderId), OrderInfoBean
                .class);
    }

    @Override
    public void returnData(OrderInfoBean result) {
        OrderInfoBean.DataBean data = result.getData();
        if (EmptyUtils.isNotEmpty(data)) {

            if (data.getStartTime() instanceof String) {
                Calendar instance = Calendar.getInstance();
                instance.setTimeInMillis(TimeUtil.getDatelongMills(TimeUtil.dateFormatYMD, data.getStartTime().toString()));
                instance.add(Calendar.DATE, Integer.parseInt(data.getTotalTime()));
                mCourseDetailView.setParams(data.getPicture(), data.getTitle(), data.getStartTime(), TimeUtil.getStringByFormat(instance
                        .getTimeInMillis()), data.getTeacherName(), 0);

            } else {
                mCourseDetailView.setParams(data.getPicture(), data.getTitle(), data.getStartTime(), data.getEndTime(), data
                        .getTeacherName(), 0);
            }

            mSomeTextView.setTeamParams(String.valueOf(data.getTargetMembers()), data.getPhone(), EmptyUtils.isEmpty(data.getRemarks()) ?
                    "无" : data.getRemarks(), data.getOrderId(), TimeUtil.getStringByFormat(data.getCreateTime(), "yyyy-MM-dd"));
            OrderTopViewUtil.setState(tvState, data.getStatus(), data.getStatusMsg(), true);
        }
    }
}
