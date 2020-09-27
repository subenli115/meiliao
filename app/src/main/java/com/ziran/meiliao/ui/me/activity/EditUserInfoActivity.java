package com.ziran.meiliao.ui.me.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.ReportListBean;
import com.ziran.meiliao.ui.bean.UserBean;
import com.ziran.meiliao.utils.Utils;
import com.ziran.meiliao.widget.ItemGroupView;
import com.ziran.meiliao.widget.SingleOptionsPicker;
import com.ziran.meiliao.widget.pupop.PopupWindowUtil;
import com.ziran.meiliao.widget.pupop.VideoCouponTipsPopupWindow;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

import static com.ziran.meiliao.constant.ApiKey.ADMIN_DICT_TYPE;

public class EditUserInfoActivity extends CommonHttpActivity<CommonPresenter, CommonModel> implements CommonContract.ActionView<Result, Result> {

    @Bind(R.id.tv_me_main_new_choose)
    ItemGroupView tv_me_main_new_choose;
    @Bind(R.id.tv_me_main_new_region)
    ItemGroupView tv_me_main_new_region;
    @Bind(R.id.tv_me_main_new_sex)
    ItemGroupView tv_me_main_new_sex;
    @Bind(R.id.tv_me_main_new_age)
    ItemGroupView tv_me_main_new_age;
    @Bind(R.id.tv_me_main_new_nick)
    ItemGroupView tv_me_main_new_nick;
    @Bind(R.id.tv_me_main_new_sign)
    ItemGroupView tv_me_main_new_sign;
    @Bind(R.id.tv_me_main_new_school)
    ItemGroupView tv_me_main_new_school;
    @Bind(R.id.tv_me_main_new_job)
    ItemGroupView tv_me_main_new_job;
    @Bind(R.id.tv_me_main_new_major)
    ItemGroupView tv_me_main_new_major;
    @Bind(R.id.tv_me_main_new_annualSalary)
    ItemGroupView tv_me_main_new_annualSalary;
    @Bind(R.id.tv_me_main_new_objective)
    ItemGroupView tv_me_main_new_objective;
    @Bind(R.id.tv_me_main_new_industry)
    ItemGroupView tv_me_main_new_industry;
    @Bind(R.id.tv_me_main_new_education)
    ItemGroupView tv_me_main_new_education;
    @Bind(R.id.tv_me_main_new_constellation)
    ItemGroupView tv_me_main_new_constellation;
    @Bind(R.id.tv_me_main_new_height)
    ItemGroupView tv_me_main_new_height;
    @Bind(R.id.tv_me_main_new_shape)
    ItemGroupView tv_me_main_new_shape;
    @Bind(R.id.tv_me_main_new_personality)
    ItemGroupView tv_me_main_new_personality;

    public final int REQUEST_CODE_B = 1;
    private UserBean.DataBean bean;
    private ArrayList<String> constellations;
    private ArrayList<String> ages;
    private ArrayList<String> heights;
    private ArrayList<String> shapes;
    private ArrayList<String> objectives;
    private List<ReportListBean.DataBean> listData;
    private ArrayList<String> incomes;
    private String type;
    private ArrayList<String> educations;
    private ArrayList<String> industrys;


    public static void startAction() {
        Activity activity = AppManager.getAppManager().currentActivity();
        Intent intent = new Intent(activity, EditUserInfoActivity.class);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    public void initPresenter() {
        if (mModel != null && mPresenter != null) {
            mPresenter.setVM(this, mModel);
        }
    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    protected void initBundle(Bundle extras) {
    }

    @Override
    public void initView() {
        initData();
        update();
        mPresenter.getDataOneHead(ADMIN_DICT_TYPE,"figure", MyAPP.getAccessToken(), ReportListBean.class);
        mPresenter.getDataOneHead(ADMIN_DICT_TYPE,"objective", MyAPP.getAccessToken(), ReportListBean.class);
        mPresenter.getDataOneHead(ADMIN_DICT_TYPE,"education", MyAPP.getAccessToken(), ReportListBean.class);
        mPresenter.getDataOneHead(ADMIN_DICT_TYPE,"income", MyAPP.getAccessToken(), ReportListBean.class);
        mPresenter.getDataOneHead(ADMIN_DICT_TYPE,"industry", MyAPP.getAccessToken(), ReportListBean.class);
    }

    private void initData() {
        constellations = new ArrayList<>();
        constellations.add("白羊座");
        constellations.add("金牛座");
        constellations.add("双子座");
        constellations.add("巨蟹座");
        constellations.add("狮子座");
        constellations.add("处女座");
        constellations.add("天秤座");
        constellations.add("天蝎座");
        constellations.add("射手座");
        constellations.add("摩羯座");
        constellations.add("水瓶座");
        constellations.add("双鱼座");
        ages = new ArrayList<>();
        for(int i=18;i<99;i++){
            ages.add(i+"");
        }
        heights = new ArrayList<>();
        for(int i=150;i<200;i++){
            heights.add(i+"");
        }
        shapes = new ArrayList<>();
        objectives = new ArrayList<>();
        incomes = new ArrayList<>();
        educations= new ArrayList<>();
        industrys= new ArrayList<>();
    }

    //点击监听
    @OnClick({R.id.tv_me_main_new_nick, R.id.tv_me_main_new_sex, R.id.tv_me_main_new_age, R.id.tv_me_main_new_region, R.id
            .tv_me_main_new_choose, R.id.tv_me_main_new_sign,R.id.tv_me_main_new_constellation,R.id.tv_me_main_new_height,
    R.id.tv_me_main_new_shape,R.id.tv_me_main_new_personality,R.id.tv_me_main_new_interest,R.id.tv_me_main_new_education,
            R.id.tv_me_main_new_school,R.id.tv_me_main_new_major,R.id.tv_me_main_new_objective,R.id.tv_me_main_new_industry,
            R.id.tv_me_main_new_job,R.id.tv_me_main_new_annualSalary,R.id.tv_me_main_new_homepic

    })
    public void onClick(View view) {
        if (Utils.isFastDoubleClick()) {
            return;
        }
        switch (view.getId()) {
            case R.id.tv_me_main_new_nick:
                SetNoteActivity.startAction("nick",REQUEST_CODE_B, "");
                break;
            case R.id.tv_me_main_new_age:
                SingleOptionsPicker.openOptionsPicker(this, ages, 1, ((ItemGroupView)view).getRightTextView(), "", mRxManager);
                break;
            case R.id.tv_me_main_new_sign:
                SetNoteActivity.startAction("sign",REQUEST_CODE_B, bean.getIntroduce());
                break;
            case R.id.tv_me_main_new_choose:
                VideoCouponTipsPopupWindow videoCouponTipsPopupwindow = new VideoCouponTipsPopupWindow(this, tv_me_main_new_choose,mRxManager);
                PopupWindowUtil.show(this, videoCouponTipsPopupwindow);
                break;
            case R.id.tv_me_main_new_constellation:
                SingleOptionsPicker.openOptionsPicker(this, constellations, 3, ((ItemGroupView)view).getRightTextView(),"constellation",mRxManager);
                break;
            case R.id.tv_me_main_new_height:
                SingleOptionsPicker.openOptionsPicker(this, heights, 2, ((ItemGroupView)view).getRightTextView(), "", mRxManager);
                break;
            case R.id.tv_me_main_new_shape:
                SingleOptionsPicker.openOptionsPicker(this, shapes, 3, ((ItemGroupView)view).getRightTextView(),"shape", mRxManager);
                break;
            case R.id.tv_me_main_new_personality:
                SelectTagFlowActivity.startAction(REQUEST_CODE_B, 0, "");
                break;
            case R.id.tv_me_main_new_interest:
                HobbySelectActivity.startAction();
                break;
            case R.id.tv_me_main_new_education:
                SingleOptionsPicker.openOptionsPicker(this, educations, 3, ((ItemGroupView)view).getRightTextView(),"education", mRxManager);
                break;
            case R.id.tv_me_main_new_school:
                SetNoteActivity.startAction("school",REQUEST_CODE_B, "");
                break;
            case R.id.tv_me_main_new_major:
                SetNoteActivity.startAction("major",REQUEST_CODE_B, "");
                break;
            case R.id.tv_me_main_new_objective:
                SingleOptionsPicker.openOptionsPicker(this, objectives, 3, ((ItemGroupView)view).getRightTextView(),"objective", mRxManager);
                break;
            case R.id.tv_me_main_new_industry:
                //行业
                SingleOptionsPicker.openOptionsPicker(this, industrys, 3, ((ItemGroupView)view).getRightTextView(),"industry", mRxManager);
                break;
            case R.id.tv_me_main_new_job:
                SetNoteActivity.startAction("job",REQUEST_CODE_B, "");
                break;
            case R.id.tv_me_main_new_annualSalary:
                SingleOptionsPicker.openOptionsPicker(this, incomes, 3, ((ItemGroupView)view).getRightTextView(),"annualSalary", mRxManager);
                break;
            case R.id.tv_me_main_new_homepic:
                HomeImageSelectActivity.startAction();
                break;


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_B: //返回的结果是来自于Activity B
                if (resultCode == Activity.RESULT_OK) {
                    update();

                }
                break;
            default:
                break;
        }
    }

    private void update() {
         bean = MyAPP.getmUserBean();
        if (bean != null) {
            tv_me_main_new_region.setRigthText(bean.getRegion());
            if (bean.getSex() == 1) {
                tv_me_main_new_sex.setRigthText("男");
            } else {
                tv_me_main_new_sex.setRigthText("女");
            }
            if (bean.getIntroduce()!= null) {
                tv_me_main_new_sign.getRightTextView().setMaxLines(1);
                tv_me_main_new_sign.getRightTextView().setMaxEms(8);
                tv_me_main_new_sign.getRightTextView().setEllipsize(TextUtils.TruncateAt.END);
                tv_me_main_new_sign.setRigthText(bean.getIntroduce() + "");
                if(bean.getIntroduce().equals("")){
                    tv_me_main_new_sign.setRigthText("没有填写");
                }
            }else {
                tv_me_main_new_sign.setRigthText("没有填写");
            }
            if(bean.getSex() == 1){
                if(bean.getPreference().equals("1")){
                    tv_me_main_new_choose.setRigthText("同性"+"(男)");
                }else {
                    tv_me_main_new_choose.setRigthText("异性"+"(女)");
                }
            }else {
                if(bean.getPreference().equals("1")){
                    tv_me_main_new_choose.setRigthText("异性"+"(男)");
                }else {
                    tv_me_main_new_choose.setRigthText("同性"+"(女)");
                }
            }
            tv_me_main_new_age.setRigthText(bean.getAge() + "岁");
            tv_me_main_new_nick.setRigthText(bean.getNickname());
            if(bean.getSchool()!=null){
                tv_me_main_new_school.setRigthText(bean.getSchool()+"");
            }
            if(bean.getMajor()!=null){
                tv_me_main_new_major.setRigthText(bean.getMajor()+"");
            }
            if(bean.getJob()!=null){
                tv_me_main_new_job.setRigthText(bean.getJob()+"");
            }
            if(bean.getAnnualSalary()!=null){
                tv_me_main_new_annualSalary.setRigthText(bean.getAnnualSalary()+"");
            }
            if(bean.getObjective()!=null){
                tv_me_main_new_objective.setRigthText(bean.getObjective()+"");
            }
            if(bean.getIndustry()!=null){
                tv_me_main_new_industry.setRigthText(bean.getIndustry()+"");
            }
            if(bean.getEducation()!=null){
                tv_me_main_new_education.setRigthText(bean.getEducation()+"");
            }
            if(bean.getConstellation()!=null){
                tv_me_main_new_constellation.setRigthText(bean.getConstellation()+"");
            }
            if(bean.getHeight()!=null){
                tv_me_main_new_height.setRigthText(bean.getHeight()+"CM");
            }
            if(bean.getShape()!=null){
                tv_me_main_new_shape.setRigthText(bean.getShape()+"");
            }
            if(bean.getPersonality()!=null){
                tv_me_main_new_personality.setRigthText(bean.getPersonality()+"");
                List<String> strings = Arrays.asList((bean.getPersonality() + "").split(","));
                if(strings.size()>4){
                    tv_me_main_new_personality.setRigthText(String.join(",", strings.subList(0,5))+"...");
                }
            }

        }
    }

    @Override
    public void returnData(Result result) {
        if(result instanceof ReportListBean){
            listData = ((ReportListBean) result).getData();
             type = listData.get(0).getType();
            if(type.equals("income")){
                saveList(incomes);
            }else if(type.equals("objective")){
                saveList(objectives);
            }else if(type.equals("figure")){
                saveList(shapes);
            }else if(type.equals("education")){
                saveList(educations);
            }else if(type.equals("industry")){
                saveList(industrys);
            }
        }
    }

    private void saveList(ArrayList<String> list) {
        for(int i=0;i<listData.size();i++){
            list.add(listData.get(i).getLabel());
        }
    }

    @Override
    public void returnAction(Result result) {

    }
}