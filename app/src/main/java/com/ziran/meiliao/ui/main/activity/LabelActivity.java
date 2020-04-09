package com.ziran.meiliao.ui.main.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.GuideListBean;
import com.ziran.meiliao.ui.bean.GuideSaveBean;
import com.ziran.meiliao.ui.main.contract.GuideContract;
import com.ziran.meiliao.ui.main.presenter.GuidePresenter;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.ShadowRelativeLayout;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

import static com.ziran.meiliao.constant.ApiKey.UserINFO_TAGLIST;
import static com.ziran.meiliao.constant.ApiKey.UserINFO_TAGSAVE;

/**
 * 兴趣标签页 2019/1/2.
 */

public class LabelActivity extends CommonHttpActivity<GuidePresenter, CommonModel> implements  GuideContract.View{

    @Bind(R.id.tv_start)
    TextView tv_start;
    private boolean isSelect;
    @Bind(R.id.srl_1)
    ShadowRelativeLayout srl_1;
    @Bind(R.id.srl_2)
    ShadowRelativeLayout srl_2;
    @Bind(R.id.srl_3)
    ShadowRelativeLayout srl_3;
    @Bind(R.id.srl_4)
    ShadowRelativeLayout srl_4;
    @Bind(R.id.srl_5)
    ShadowRelativeLayout srl_5;
    @Bind(R.id.srl_6)
    ShadowRelativeLayout srl_6;
    @Bind(R.id.srl_7)
    ShadowRelativeLayout srl_7;
    @Bind(R.id.srl_8)
    ShadowRelativeLayout srl_8;


    @Bind(R.id.all_first)
    AutoLinearLayout all_1;
    @Bind(R.id.all_two)
    AutoLinearLayout all_2;
    @Bind(R.id.all_third)
    AutoLinearLayout all_3;
    @Bind(R.id.all_four)
    AutoLinearLayout all_4;

    @Bind(R.id.all_five)
    AutoLinearLayout all_5;
    @Bind(R.id.all_six)
    AutoLinearLayout all_6;
    @Bind(R.id.all_seven)
    AutoLinearLayout all_7;
    @Bind(R.id.all_eight)
    AutoLinearLayout all_8;
    @Bind(R.id.tv_num)
    TextView tvNum;

    private ArrayList<ShadowRelativeLayout> srlList;
    private int num=0;
    private List<String> saveString;
    private ArrayList<AutoLinearLayout> AllList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            initView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_select_info;
    }

    @Override
    public void initPresenter() {
        if (mModel != null && mPresenter != null) {
            mPresenter.setVM(this, mModel);
        }
    }

    public void initView() {
        super.initView();
         saveString = new ArrayList<>();
        srlList = new ArrayList<>();
         AllList = new ArrayList<>();
         AllList.add(all_1);
         AllList.add(all_2);
         AllList.add(all_3);
         AllList.add(all_4);
         AllList.add(all_5);
         AllList.add(all_6);
        AllList.add(all_7);
        AllList.add(all_8);

        srlList.add(srl_1);
        srlList.add(srl_2);
        srlList.add(srl_3);
        srlList.add(srl_4);
        srlList.add(srl_5);
        srlList.add(srl_6);
        srlList.add(srl_7);
        srlList.add(srl_8);
        getTagData();
    }



    public static void startAction(Context mContext) {
            Intent intent = new Intent(mContext, LabelActivity.class);
            mContext.startActivity(intent);
    }


    //点击监听
    @OnClick({R.id.tv_start,R.id.srl_1,R.id.srl_2,R.id.srl_3,R.id.srl_4,R.id.srl_5,R.id.srl_6,R.id.srl_7,R.id.srl_8})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_start:
                if(isSelect){
                    saveTagData();
                }
                break;
            case R.id.srl_1:
                setBgForSelect(view,1);
                break;
            case R.id.srl_2:
                setBgForSelect(view,2);
                break;
            case R.id.srl_3:
                setBgForSelect(view,3);
                break;
            case R.id.srl_4:
                setBgForSelect(view,4);
                break;
            case R.id.srl_5:
                setBgForSelect(view,5);
                break;
            case R.id.srl_6:
                setBgForSelect(view,6);
                break;
            case R.id.srl_7:
                setBgForSelect(view,7);
                break;
            case R.id.srl_8:
                setBgForSelect(view,8);
                break;
        }
    }

    private void setBgForSelect(View view, int id) {
            for(int i=0;i<srlList.size();i++){
                if(i==id-1){
                    if(view.isSelected()){
                        view.setSelected(false);
                        view.setBackgroundResource(R.drawable.normal_bg_white_shadow);
                        num--;
                        if(num==0){
                            isSelect=false;
                            tv_start.setBackgroundResource(R.drawable.normal_bg_gray_c0);
                        }
                    }else{
                        if(num==6){
                            return;
                        }
                        view.setSelected(true);
                        tvNum.setTextColor(Color.parseColor("#45C49D"));
                        tv_start.setBackgroundResource(R.drawable.normal_bg_green);
                        view.setBackgroundResource(R.drawable.normal_bg_green_tran_shandow);
                        saveString.add(i+"");
                        isSelect=true;
                        num++;
                    }
                }
            }
            tvNum.setText("("+num+"/6)");

    }
    public static void removeDuplicate(List list) {
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
    }
    @Override
    public void showGuideSaveData(GuideSaveBean.DataBean result) {
        MainActivity.startAction(this, 1);
    }

    @Override
    public void showGuideListData(List<GuideListBean.DataBean> data) {
        if(data!=null&data.size()>0){
            for(int i=0;i<8;i++){
                TextView gtv =(TextView) AllList.get(i).getChildAt(0);
                ImageView giv =(ImageView) AllList.get(i).getChildAt(1);
                gtv.setText(data.get(i).getName());
                Glide.with(mContext).load(data.get(i).getPicture()).error(R.mipmap.ic_launcher).into(giv);

            }
        }
    }


    public String listToString(List list, char separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(separator);
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    private void getTagData() {
        Map<String, String> tagMap = MapUtils.getDefMap(true);
        mPresenter.getGuideListData(UserINFO_TAGLIST, tagMap);
    }

    private void saveTagData() {
        Map<String, String> tagMap = MapUtils.getDefMap(true);
        removeDuplicate(saveString);
        String save = listToString(saveString, ',');
        tagMap.put("ids",save);
        mPresenter.getGuideSaveData(UserINFO_TAGSAVE, tagMap);
    }
}
