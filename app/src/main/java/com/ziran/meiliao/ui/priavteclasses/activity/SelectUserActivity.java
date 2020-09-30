package com.ziran.meiliao.ui.priavteclasses.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaygoo.widget.OnRangeChangedListener;
import com.jaygoo.widget.RangeSeekBar;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.entry.UserSelectBean;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.ReportListBean;
import com.ziran.meiliao.widget.SwitchButton;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.Bind;
import butterknife.OnClick;

import static com.ziran.meiliao.constant.ApiKey.ADMIN_DICT_TYPE;


public class SelectUserActivity extends CommonHttpActivity<CommonPresenter, CommonModel> implements CommonContract.ActionView<Result, Result> {
    @Bind(R.id.iv_sex_woman)
    ImageView ivWoman;
    @Bind(R.id.iv_sex_man)
    ImageView ivMan;
    @Bind(R.id.tv_age)
    TextView tvAge;
    @Bind(R.id.tv_height)
    TextView tvHeight;
    @Bind(R.id.tfl_shape)
    public TagFlowLayout tflShape;
    @Bind(R.id.tfl_objective)
    public TagFlowLayout tflObjective;
    @Bind(R.id.tfl_constellation)
    public TagFlowLayout tflConstellation;
    @Bind(R.id.tv_qd)
    public TextView tvQd;
    @Bind(R.id.sb_range_age)
    public RangeSeekBar sbAge;
    @Bind(R.id.sb_range_height)
    public RangeSeekBar sbHeight;
    @Bind(R.id.sb_switch)
    public SwitchButton sbSwitch;

    private List<ReportListBean.DataBean> listData;
    private ArrayList<String> datas;
    private UserSelectBean userSelectBean;
    private ArrayList<String> list1;
    private ArrayList<String> list2;
    private ArrayList<String> list3;
    private int leftAge=18;
    private int rightAge=99;
    private int leftHeight=140;
    private int rightHeight=230;


    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.ac_select_user;
    }

    @Override
    public void initPresenter() {
        if (mModel != null && mPresenter != null) {
            mPresenter.setVM(this, mModel);
        }
    }

    public void reset(UserSelectBean userSelectBean){
        if(userSelectBean==null){
            int sex = MyAPP.getmUserBean().getSex();
            sbAge.setProgress(18,99);
            sbHeight.setProgress(140,230);
            if(sex==1){
                ivMan.setImageResource(R.mipmap.icon_man_select);
                ivMan.setSelected(true);
                ivWoman.setImageResource(R.mipmap.icon_woman_noselect);
                ivWoman.setSelected(false);
            }else {
                ivWoman.setImageResource(R.mipmap.icon_woman_select);
                ivWoman.setSelected(true);
                ivMan.setImageResource(R.mipmap.icon_man_noselect);
                ivMan.setSelected(false);
            }
            tvAge.setText("18-99 岁");
            tvHeight.setText("140-230 cm");
            tflObjective.getAdapter().setSelectedList(new HashSet<>());
            tflConstellation.getAdapter().setSelectedList(new HashSet<>());
            tflShape.getAdapter().setSelectedList(new HashSet<>());
            sbSwitch.setChecked(true);
        }else {
            if(userSelectBean.getOnline().equals("0")){
                sbSwitch.setChecked(true);
            }else {
                sbSwitch.setChecked(false);
            }
            if(userSelectBean.getSex().equals("2")){
                ivWoman.setImageResource(R.mipmap.icon_woman_select);
                ivWoman.setSelected(true);
                ivMan.setImageResource(R.mipmap.icon_man_noselect);
                ivMan.setSelected(false);
            }else {
                ivMan.setImageResource(R.mipmap.icon_man_select);
                ivMan.setSelected(true);
                ivWoman.setImageResource(R.mipmap.icon_woman_noselect);
                ivWoman.setSelected(false);
            }

            sbAge.setProgress(Integer.parseInt(userSelectBean.getLeftAge()),Integer.parseInt(userSelectBean.getRightAge()));
            sbHeight.setProgress(Integer.parseInt(userSelectBean.getLeftHeight()),Integer.parseInt(userSelectBean.getRightHeight()));
            tvAge.setText(userSelectBean.getLeftAge()+"-"+userSelectBean.getRightAge()+" 岁");
            tvHeight.setText(userSelectBean.getLeftHeight()+"-"+userSelectBean.getRightHeight()+" cm");
            for(int i=0;i<list1.size();i++){
                        if(list1.get(i).equals(userSelectBean.getObjective())){
                            tflObjective.getAdapter().setSelectedList(i);
                        }
             }
            for(int i=0;i<list2.size();i++){
                if(list2.get(i).equals(userSelectBean.getConstellation())){
                    tflConstellation.getAdapter().setSelectedList(i);
                }
            }
            for(int i=0;i<list3.size();i++){
                if(list3.get(i).equals(userSelectBean.getFigure())){
                    tflShape.getAdapter().setSelectedList(i);
                }
            }
        }
    }

    @Override
    public void initView() {
        //交友母的.
        if(getIntent()!=null&&getIntent().getParcelableExtra("resultBean")!=null){
            userSelectBean = getIntent().getParcelableExtra("resultBean");
            Log.e("userSelectBean",""+userSelectBean.getOnline());
        }
        list1=new ArrayList<>();
        list2=new ArrayList<>();
        list3=new ArrayList<>();
        datas = new ArrayList<>();
        sbAge.setRange(18,99);
        sbHeight.setRange(140,230);
        sbAge.setProgress(18,99);
        sbHeight.setProgress(140,230);
        mPresenter.getDataOneHead(ADMIN_DICT_TYPE,"objective", MyAPP.getAccessToken(), ReportListBean.class);
        mPresenter.getDataOneHead(ADMIN_DICT_TYPE,"constellation", MyAPP.getAccessToken(), ReportListBean.class);
        mPresenter.getDataOneHead(ADMIN_DICT_TYPE,"figure", MyAPP.getAccessToken(), ReportListBean.class);
        sbAge.setOnRangeChangedListener(new OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {
                tvAge.setText((int)leftValue+"-"+(int)rightValue+" 岁");
                leftAge=(int)leftValue;
                rightAge=(int)rightValue;
            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }
        });
        sbHeight.setOnRangeChangedListener(new OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {
                tvHeight.setText((int)leftValue+"-"+(int)rightValue+" cm");
                leftHeight=(int)leftValue;
                rightHeight=(int)rightValue;
            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }
        });
    }

    //点击监听
    @OnClick({R.id.tv_qd,R.id.iv_back, R.id.iv_sex_woman, R.id.iv_sex_man,R.id.all_status})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_qd:
                Intent intent = new Intent();
                save();
                intent.putExtra("USER_INFO", userSelectBean);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_sex_woman:
                    ivWoman.setImageResource(R.mipmap.icon_woman_select);
                    ivMan.setImageResource(R.mipmap.icon_man_noselect);
                    ivWoman.setSelected(true);
                    ivMan.setSelected(false);
                break;
            case R.id.iv_sex_man:
                    ivMan.setImageResource(R.mipmap.icon_man_select);
                    ivWoman.setImageResource(R.mipmap.icon_woman_noselect);
                    ivMan.setSelected(true);
                    ivWoman.setSelected(false);
                break;
            case R.id.all_status:
                reset(null);
                break;

        }
    }

    private void save() {
        userSelectBean = new UserSelectBean();
        if(ivMan.isSelected()){
            userSelectBean.setSex("1");
        }else {
            userSelectBean.setSex("2");
        }
        Log.e("sbSwitch",""+sbSwitch.isChecked());
        if(sbSwitch.isChecked()){
            userSelectBean.setOnline("0");
        }
        userSelectBean.setLeftAge(leftAge+"");
        userSelectBean.setLeftHeight(leftHeight+"");
        userSelectBean.setRightAge(rightAge+"");
        userSelectBean.setRightHeight(rightHeight+"");
        Set<Integer> selectedList1 = tflObjective.getSelectedList();
        if(selectedList1!=null){
            List list=new ArrayList(selectedList1);
            if(list.size()>0){
                userSelectBean.setObjective(list1.get((Integer) list.get(0)));
            }
        }
        Set<Integer> selectedList2 = tflConstellation.getSelectedList();
        if(selectedList2!=null){
            List list=new ArrayList(selectedList2);
            if(list.size()>0){
                userSelectBean.setConstellation(list2.get((Integer) list.get(0)));
            }
        }
        Set<Integer> selectedList3 = tflShape.getSelectedList();
        if(selectedList3!=null){
            List list=new ArrayList(selectedList3);
            if(list.size()>0){
                userSelectBean.setFigure(list3.get((Integer) list.get(0)));
            }
        }
    }


    @Override
    public void returnData(Result result) {
        if(result instanceof ReportListBean){
            listData = ((ReportListBean) result).getData();
            datas.clear();
            for(int i=0;i<listData.size();i++){
                datas.add(listData.get(i).getLabel());
            }
            String type = listData.get(0).getType();
            if(type.equals("objective")){
                list1.addAll(datas);
                initAdapter(type,list1);
            }else if(type.equals("constellation")){
                list2.addAll(datas);
                initAdapter(type,list2);
            }else if(type.equals("figure")){
                list3.addAll(datas);
                initAdapter(type,list3);
            }
                reset(userSelectBean);
        }


    }


    private void initAdapter(String type, ArrayList<String> datas) {
        TagAdapter   mAdapter=  new TagAdapter<String>(datas) {
            @Override
            public View getView(FlowLayout parent, int position, String s)
            {
                final LayoutInflater mInflater = LayoutInflater.from(SelectUserActivity.this);
                TextView tv = (TextView) mInflater.inflate(R.layout.item_tag_bg_other,
                        parent, false);
                tv.setText(s);
                return tv;
            }
        };
        if(type.equals("objective")){
            tflObjective.setAdapter(mAdapter);
        }else if(type.equals("constellation")){
            tflConstellation.setAdapter(mAdapter);
        }else if(type.equals("figure")){
            tflShape.setAdapter(mAdapter);
        }
    }


    @Override
    public void returnAction(Result result) {

    }
}