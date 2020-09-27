package com.ziran.meiliao.ui.me.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import com.zhy.view.flowlayout.TagView;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.im.application.JGApplication;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.LabelHobbyBean;
import com.ziran.meiliao.ui.bean.ReportListBean;
import com.ziran.meiliao.ui.bean.StringDataV2Bean;
import com.ziran.meiliao.ui.bean.UserBean;
import com.ziran.meiliao.utils.MapUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;
import butterknife.OnClick;

import static com.ziran.meiliao.constant.ApiKey.ADMIN_DICT_TYPE;
import static com.ziran.meiliao.constant.ApiKey.ADMIN_LABEL_GETPAGE;
import static com.ziran.meiliao.constant.ApiKey.ADMIN_USERTABLE_ADD;

public class SelectTagFlowActivity extends  CommonHttpActivity<CommonPresenter, CommonModel> implements CommonContract.ActionView<Result, Result> {
    @Bind(R.id.flowlayout)
    public TagFlowLayout flowlayout;
    @Bind(R.id.tv_qd)
    public TextView tvQd;
    private List<ReportListBean.DataBean> listData;
    private ArrayList<String> personalitys;
    private TagAdapter<String> mAdapter;
    private Set<Integer> selectedList;
    private String[] savePersons;
    private List<LabelHobbyBean.DataBean> lists;
    private String typeText;
    private int type;
    private boolean flag;

    public static void startAction(int code, int type, String rightText) {
        Activity activity = AppManager.getAppManager().currentActivity();
        Intent intent = new Intent(activity, SelectTagFlowActivity.class);
        intent.putExtra("type",type);
        intent.putExtra("typeText",rightText);
        activity.startActivityForResult(intent,code);
    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_common_tag;
    }

    @Override
    public void initPresenter() {
        if (mModel != null && mPresenter != null) {
            mPresenter.setVM(this, mModel);
        }
    }


    @Override
    public void initView() {
        if(getIntent()!=null){
             type = getIntent().getIntExtra("type", 0);
             typeText = getIntent().getStringExtra("typeText");
        }

        personalitys = new ArrayList<>();
        selectedList = new HashSet<>();
        if(MyAPP.getmUserBean().getPersonality()!=null){
            String personality = MyAPP.getmUserBean().getPersonality()+"";
           savePersons = personality.split(",");
        }
        if(typeText!=null&&typeText.length()>0){
            savePersons=typeText.split(",");
        }

        if(type==0){
            mPresenter.getDataOneHead(ADMIN_DICT_TYPE,"personality", MyAPP.getAccessToken(), ReportListBean.class);
        }else {
            tvQd.setVisibility(View.GONE);
            Map<String, String> defMap = MapUtils.getDefMap(true);
            defMap.put("tableId",type+"");
            mPresenter.getData(ADMIN_LABEL_GETPAGE,defMap, LabelHobbyBean.class);
        }
    }

    //点击监听
    @OnClick({R.id.tv_qd,R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_qd:
                selectedList = flowlayout.getSelectedList();
                edit();
                break;
            case R.id.iv_back:
                finish();
                if(flag){
                    ToastUitl.showShort("修改成功");
                }
                break;
        }
    }



    private void edit() {
        StringBuilder sb = new StringBuilder();
        List list=new ArrayList(selectedList);
        for(int i=0;i<list.size();i++){
            if (sb.length() > 0) {//该步即不会第一位有逗号，也防止最后一位拼接逗号！
                sb.append(",");
            }
            sb.append(personalitys.get((Integer) list.get(i)));
        }
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("id", MyAPP.getUserId());
        defMap.put("personality",sb.toString());
        OkHttpClientManager.putAsyncAddHead(ApiKey.ADMIN_USER_UPDATE, defMap, new
                NewRequestCallBack<UserBean>(UserBean.class) {
                    @Override
                    public void onSuccess(UserBean result) {
                        ToastUitl.showShort(result.getResultMsg());
                        UserBean.DataBean data = result.getData();
                        data.setUserAccount(MyAPP.getmUserBean().getUserAccount());
                        MyAPP.setmUserBean(data);
                        mRxManager.post(AppConstant.RXTag.UPDATE_USER, "");
                        Intent intent = new Intent();
                        setResult(Activity.RESULT_OK, intent);
                        // RESULT_OK就是一个默认值，=-1，它说OK就OK吧
                        finish();
                    }

                    @Override
                    public void onError(String msg, int code) {
                        ToastUitl.showShort(msg);
                    }
                });
    }


    @Override
    public void returnData(Result result) {

        if(result instanceof ReportListBean){
            listData = ((ReportListBean) result).getData();
            for(int i=0;i<listData.size();i++){
                personalitys.add(listData.get(i).getLabel());
            }
            initAdapter(false);
        }else if(result instanceof LabelHobbyBean){
            lists = ((LabelHobbyBean) result).getData();
            for(int i=0;i<lists.size();i++){
                personalitys.add(lists.get(i).getLabelContent());
            }
            initAdapter(true);
        }else if(result instanceof StringDataV2Bean){
            mRxManager.post(AppConstant.RXTag.UPDATE_USER, "");
        }

    }

    private void initAdapter(boolean isclick) {
        mAdapter=  new TagAdapter<String>(personalitys) {
            @Override
            public View getView(FlowLayout parent, int position, String s)
            {
                final LayoutInflater mInflater = LayoutInflater.from(SelectTagFlowActivity.this);
                TextView tv = (TextView) mInflater.inflate(R.layout.item_tag_bg,
                        flowlayout, false);
                tv.setText(s);
                return tv;
            }
        };
        if(savePersons!=null&&savePersons.length>0){
            for(int k=0;k<personalitys.size();k++){
                for(int i=0;i<savePersons.length;i++){
                    if(personalitys.get(k).equals(savePersons[i])){
                        selectedList.add(k);
                    }
                }
            }
            mAdapter.setSelectedList(selectedList);
        }
        flowlayout.setAdapter(mAdapter);
        if(isclick){
            flowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int position, FlowLayout parent) {
                    Map<String, String> defMap = MapUtils.getDefMap(true);
                        defMap.put("tableName",lists.get(position).getLabelContent());
                        defMap.put("type",lists.get(position).getTableId()+"");
                        defMap.put("userId",MyAPP.getUserId());
                        mPresenter.postData(ADMIN_USERTABLE_ADD,defMap, StringDataV2Bean.class);
                        flag=true;
                    return false;
                }
            });
        }
    }

    @Override
    public void returnAction(Result result) {

    }
}