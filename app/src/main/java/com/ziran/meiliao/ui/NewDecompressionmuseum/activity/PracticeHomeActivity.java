package com.ziran.meiliao.ui.NewDecompressionmuseum.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.ui.NewDecompressionmuseum.contract.PracticeHomeContract;
import com.ziran.meiliao.ui.NewDecompressionmuseum.presenter.PracticeHomePresenter;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.JdxShareBean;
import com.ziran.meiliao.ui.bean.PracticeHomeBean;
import com.ziran.meiliao.ui.bean.PracticeRecordBean;
import com.ziran.meiliao.ui.decompressionmuseum.activity.RecordActivity;
import com.ziran.meiliao.ui.main.activity.NewLoginActivity;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import butterknife.Bind;
import butterknife.OnClick;

import static com.ziran.meiliao.constant.ApiKey.PRACTIEACTIVITY_HOMEDATA;

/**
 * 觉当下首页
 */

public class PracticeHomeActivity extends CommonHttpActivity<PracticeHomePresenter, CommonModel> implements PracticeHomeContract.View {


    @Bind(R.id.all_exercise)
    AutoLinearLayout allExercise;
    @Bind(R.id.tv_times)
    TextView tvTimes;
    @Bind(R.id.tv_exe_dyas)
    TextView tvExeDyas;
    @Bind(R.id.tv_exe_num)
    TextView tvExeNum;
    @Bind(R.id.all_begin)
    AutoLinearLayout allBegin;
    @Bind(R.id.tv_mbct_book)
    TextView tvMbctBook;
    @Bind(R.id.arl_home)
    AutoRelativeLayout arlHome;
    @Bind(R.id.arl_pop_hint)
    AutoLinearLayout arlPopHint;
    @Bind(R.id.ntb_title)
    NormalTitleBar ntbTitle;
    @Bind(R.id.tv_hint)
    TextView tvHint;
    @Bind(R.id.tv_minutes)
    TextView tvMinutes;
    @Bind(R.id.tv_exercise_day)
    TextView tvHour;
    @Bind(R.id.iv_bg)
    ImageView ivBg;

    private int status;
    private int BookId;
    private int days;
    private String notice;

    @Override
    public int getLayoutId() {
        return R.layout.activity_jdx_home;
    }

    @Override
    public void initPresenter() {
        if (mModel != null && mPresenter != null) {
            mPresenter.setVM(this, mModel);
        }
    }


    @Override
    public void initView() {
        super.initView();
        ntbTitle.setTitleWeizhi();
        ntbTitle.setBackGroundColor(R.color.transparent);
        ntbTitle.setLeftImagSrc(R.mipmap.exercise_back);
        ntbTitle.setTvLeftVisiable(true, true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getPracticeHomeData(PRACTIEACTIVITY_HOMEDATA, MapUtils.getDefMap(true));
    }

    public static void startAction(Context context) {
        if (CheckUtil.check(context)) {
            Intent intent = new Intent(context, PracticeHomeActivity.class);
            context.startActivity(intent);
        }
    }


    @OnClick({R.id.tv_back, R.id.all_exercise, R.id.all_begin, R.id.tv_mbct_book, R.id.iv_close})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                if (MyAPP.isLogout) {
                    NewLoginActivity.startAction(this);
                } else {
                    finish();
                }
                break;
            case R.id.all_exercise:
                startActivity(RecordActivity.class);
                break;
            case R.id.all_begin:
                PracticeStartActivity.startAction(mContext,1,null, "","");
                break;
            case R.id.tv_mbct_book:
                    if(status==0){
                        MbsrWorkbookMainActivity.startAction(mContext,BookId+"");
                    }else {
                        PracticeTaskActivity.startAction(mContext,BookId+"",0+"");
                    }
                break;
            case R.id.iv_close:
                arlPopHint.setVisibility(View.INVISIBLE);
                break;
        }
    }


    @Override
    public void showHomeData(PracticeHomeBean.DataBean result) {
            if (result!=null){
                int booksStatus = result.getBooksStatus();
                PracticeHomeBean.DataBean.PracticeDataBean practiceData = result.getPracticeData();
                Glide.with(mContext).load(practiceData.getPicture()).error(R.mipmap.bg_jdx_home).into(ivBg);
                PracticeHomeBean.DataBean.PracticeBooksBean practiceBooksBean = result.getPracticeBooks().get(0);
                 notice = practiceBooksBean.getNotice();
                 if(notice!=null&&notice.length()>0){
                     tvHint.setText(notice);
                     arlPopHint.setVisibility(View.VISIBLE);
                 }else {
                     arlPopHint.setVisibility(View.INVISIBLE);
                 }

                if(booksStatus==0){
                    arlPopHint.setVisibility(View.GONE);
                    tvMbctBook.setVisibility(View.GONE);
                }
                BookId = practiceBooksBean.getId();
                status = practiceBooksBean.getStatus();
                tvExeDyas.setText(practiceData.getSerialDays()+"");
                tvHour.setText(practiceData.getHours()+"");
                tvMinutes.setText(practiceData.getMinutes()+"");
                tvTimes.setText(practiceData.getTimes()+"");
            }
    }

    @Override
    public void showRecordData(PracticeRecordBean.DataBean result) {

    }

    @Override
    public void showPracticeStatusData(JdxShareBean.DataBean result) {

    }
}
