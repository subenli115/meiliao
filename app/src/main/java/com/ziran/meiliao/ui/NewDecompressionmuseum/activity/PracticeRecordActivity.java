package com.ziran.meiliao.ui.NewDecompressionmuseum.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.NewDecompressionmuseum.adapter.MyPagerAdapter;
import com.ziran.meiliao.ui.NewDecompressionmuseum.contract.PracticeHomeContract;
import com.ziran.meiliao.ui.NewDecompressionmuseum.presenter.PracticeHomePresenter;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.JdxShareBean;
import com.ziran.meiliao.ui.bean.PracticeHomeBean;
import com.ziran.meiliao.ui.bean.PracticeRecordBean;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.StringUtils;

import java.util.Map;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.ziran.meiliao.constant.ApiKey.PRACTIEACTIVITY_PRACTICEHIS;

/**
 * 记录界面 on 2018/10/30.
 */

public class PracticeRecordActivity  extends CommonHttpActivity<PracticeHomePresenter, CommonModel> implements PracticeHomeContract.View {


    @Bind(R.id.ntb_title)
    NormalTitleBar ntbTitle;
    @Bind(R.id.iv_user_avatar)
    CircleImageView ivMeMainAvatar;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    private TabLayout tabLayout;
    @Bind(R.id.tv_user_name)
    TextView tvUserName;
    private String bookId;
    @Bind(R.id.tv_progress_first)
    TextView tv_progress_first;
    @Bind(R.id.progressBarHorizontal)
    ProgressBar progressBarHorizontal;

    @Override
    public void showHomeData(PracticeHomeBean.DataBean result) {

    }

    @Override
    public void showRecordData(PracticeRecordBean.DataBean result) {
        String percent = result.getPercent();
        tv_progress_first.setText(percent+"%");
        progressBarHorizontal.setProgress(new Long( Math.round(Double.valueOf(percent))).intValue());
    }

    @Override
    public void showPracticeStatusData(JdxShareBean.DataBean result) {

    }

    @Override
    public void initPresenter() {
        if (mModel != null && mPresenter != null) {
            mPresenter.setVM(this, mModel);
        }
    }
    public static void startAction(Context context,String id) {
        if (CheckUtil.check(context)) {
            Intent intent = new Intent(context, PracticeRecordActivity.class);
            intent.putExtra(AppConstant.ExtraKey.FROM_ID,id);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (tvUserName != null && MyAPP.getUserInfo() != null) {
            tvUserName.setText(MyAPP.getUserInfo().getNickName());
        }
    }
    @Override
    protected void initBundle(Bundle extras) {
        bookId = extras.getString(AppConstant.ExtraKey.FROM_ID);
    }
    @Override
    public void initView() {
        ntbTitle.setTitleWeizhi();
        ntbTitle.setBackGroundColor(R.color.transparent);
        ntbTitle.setLeftImagSrc(R.mipmap.exercise_back);
        ntbTitle.setTvLeftVisiable(true, true);
        tabLayout= (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("第一周"));
        tabLayout.addTab(tabLayout.newTab().setText("第二周"));
        tabLayout.addTab(tabLayout.newTab().setText("第三周"));
        tabLayout.addTab(tabLayout.newTab().setText("第四周"));
        tabLayout.addTab(tabLayout.newTab().setText("第五周"));
        tabLayout.addTab(tabLayout.newTab().setText("第六周"));
        tabLayout.addTab(tabLayout.newTab().setText("第七周"));
        tabLayout.addTab(tabLayout.newTab().setText("第八周"));
        tabLayout.setupWithViewPager(viewPager);
        //订阅更新用户头像
        if(mPresenter!=null){
            Map<String, String> weekMap = MapUtils.getDefMap(true);

            if(bookId==null){
                weekMap.put("id", "1");
            }else{
                weekMap.put("id", bookId);
            }
            weekMap.put("weeks", "1");
            mPresenter.getPracticeRecordData(PRACTIEACTIVITY_PRACTICEHIS,weekMap);
        }
        ImageLoaderUtils.displayCircle(getBaseContext(), ivMeMainAvatar, StringUtils.headImg(), R.mipmap.ic_user_pic);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(),mContext));

    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_jdx_practice_record;
    }
}
