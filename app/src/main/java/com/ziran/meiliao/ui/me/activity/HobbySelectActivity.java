package com.ziran.meiliao.ui.me.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.common.commonwidget.OnNoDoubleClickListener;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.HobbySelectBean;
import com.ziran.meiliao.ui.bean.LabelHobbyBean;
import com.ziran.meiliao.ui.bean.SelectBean;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.Utils;
import com.ziran.meiliao.widget.ItemGroupView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

import static com.ziran.meiliao.constant.ApiKey.ADMIN_LABEL_GETPAGE;
import static com.ziran.meiliao.constant.ApiKey.ADMIN_USERTABLE_GETPAGE;

/**
 * 兴趣列表
 * Created by Administrator on 2017/1/4.
 */

public class HobbySelectActivity extends  CommonHttpActivity<CommonPresenter, CommonModel> implements CommonContract.ActionView<Result, Result> {


    private static final int HOBBY =1 ;
    private static final int REQUEST_CODE_A =3 ;
    @Bind(R.id.ll)
    LinearLayout ll;
    private List<LabelHobbyBean.DataBean> listData;
    private ArrayList<ItemGroupView> list;
    public final int REQUEST_CODE_B = 1;
    private List<HobbySelectBean.DataBean> selectlist;
    private ArrayList<SelectBean> objects;


    public static void startAction() {
        Activity activity = AppManager.getAppManager().currentActivity();
        Intent intent = new Intent(activity, HobbySelectActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.ac_hobby_select;
    }

    @Override
    public void initPresenter() {
        if (mModel != null && mPresenter != null) {
            mPresenter.setVM(this, mModel);
        }
    }


    @Override
    public void initView() {
    }

    @Override
    protected void onResume() {
        super.onResume();
        objects = new ArrayList<>();
        objects.clear();
        list = new ArrayList<>();
        getTitleList();
    }

    private void getTitleList() {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("level","1");
        mPresenter.getData(ADMIN_LABEL_GETPAGE,defMap, LabelHobbyBean.class);
    }

    //点击监听
    @OnClick({})
    public void onClick(View view) {
        if (Utils.isFastDoubleClick()) {
            return;
        }
    }

    @Override
    public void returnData(Result result) {
       if(result instanceof  LabelHobbyBean){
           objects.clear();
            listData = ((LabelHobbyBean) result).getData();
           for( int i=0; i<listData.size();i++){
               ItemGroupView igv =(ItemGroupView) ll.getChildAt(i);
               igv.setVisibility(View.VISIBLE);
               SelectBean selectBean = new SelectBean();
               selectBean.setText("");
               selectBean.setType(listData.get(i).getId()+"");
               objects.add(selectBean);
               int finalI = i;
               igv.setLeftText(listData.get(i).getLabelContent());
           }
           Map<String, String> defMap = MapUtils.getDefMap(true);
           defMap.put("userId",MyAPP.getUserId());
           mPresenter.getData(ADMIN_USERTABLE_GETPAGE,defMap, HobbySelectBean.class);
       }else if(result instanceof  HobbySelectBean){
           selectlist = ((HobbySelectBean) result).getData();
           for(int i=0;i<selectlist.size();i++){
               for(int k=0;k<objects.size();k++){
                   if(selectlist.get(i).getType().equals(objects.get(k).getType()+"")){
                       if(!objects.get(k).getText().equals("")){
                           objects.get(k).setText(objects.get(k).getText()+","+selectlist.get(i).getTableName());
                       }else {
                           objects.get(k).setText(selectlist.get(i).getTableName());
                       }
                   }
                   ItemGroupView itemGroupView =(ItemGroupView) ll.getChildAt(k);
                   int finalK = k;
                   itemGroupView.setOnClickListener(new OnNoDoubleClickListener() {
                       @Override
                       protected void onNoDoubleClick(View v) {
                           if(objects.size()>0){
                               SelectTagFlowActivity.startAction(REQUEST_CODE_A,listData.get(finalK).getId(),objects.get(finalK).getText());
                           }
                       }
                   });
                   if(objects.get(k).getText().equals("")){
                       itemGroupView.setRigthText(" ");
                   }else {
                       itemGroupView.setRigthText(objects.get(k).getText());
                       List<String> strings = Arrays.asList((objects.get(k).getText() + "").split(","));
                       if(strings.size()>2){
                           itemGroupView.setRigthText(String.join(",", strings.subList(0,3))+"...");
                       }
                   }
               }
           }

       }
    }

    @Override
    public void returnAction(Result result) {

    }
}