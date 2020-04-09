package com.ziran.meiliao.ui.priavteclasses.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.CoursePaySuccessResult;
import com.ziran.meiliao.utils.MapUtils;

import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

import static com.ziran.meiliao.constant.ApiKey.ACTIVITY_RECOMMEND;

public class CoursePayFailedActivity extends CommonHttpActivity<CommonPresenter, CommonModel> implements CommonContract.ActionView<Result, Result>{

    @Bind(R.id.ntb_title)
    NormalTitleBar ntbTitle;
    @Bind(R.id.tv_hint)
    TextView tvHint;
    @Bind(R.id.tv_back_course)
    TextView tvCourse;

    @Override
    public int getLayoutId() {
        return R.layout.ac_course_pay_failed;
    }

    @Override
    public void returnData(Result result) {
        CoursePaySuccessResult bean=(CoursePaySuccessResult)result;
//        CourseSuccessAdapter courseSuccessAdapter = new CourseSuccessAdapter(mContext,bean);

    }

    @Override
    public void returnAction(Result result) {

    }
    public static void startAction(Context context) {
        Intent intent = new Intent();
        intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClass(context, CoursePayFailedActivity.class);
        context.startActivity(intent);
    }
    @Override
    public void initView() {
        super.initView();
        ntbTitle.setBackGroundColor1();
        ntbTitle.setLeftImagSrc(R.mipmap.exercise_back);
        ntbTitle.setTitleWeizhi();
        ntbTitle.setTitleText("支付失败");
        Map<String, String> defMap = MapUtils.getDefMap(true);
        mPresenter.postData(ACTIVITY_RECOMMEND, defMap, CoursePaySuccessResult.class);
    }

    @OnClick({R.id.tv_back_course})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back_course:
                finish();
                break;
        }
    }
    }