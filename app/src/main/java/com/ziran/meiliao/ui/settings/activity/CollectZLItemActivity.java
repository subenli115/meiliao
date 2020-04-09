package com.ziran.meiliao.ui.settings.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.CollectZLBean;
import com.ziran.meiliao.ui.bean.CollectZLItemBean;
import com.ziran.meiliao.ui.bean.RecordListBean;
import com.ziran.meiliao.ui.priavteclasses.activity.SubscribeVideoActivity;
import com.ziran.meiliao.ui.priavteclasses.activity.ZLAudioImageTextActivity;
import com.ziran.meiliao.ui.settings.adapter.CollectZLItemAdapter;
import com.ziran.meiliao.utils.MapUtils;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2018/7/23.
 */

public class CollectZLItemActivity extends BaseActivity<CommonPresenter, CommonModel> implements CommonContract.View<CollectZLBean> {
    private static int mPosition;
    @Bind(R.id.ntb)
    NormalTitleBar ntb;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private List<String> list;
    private List<String> listTime;
    private List<Integer> listId;
    private List<CollectZLItemBean.DataBean> mResult;
    private String subscriptionId;
    private String title;
    private int type;
    private CollectZLItemBean.DataBean dataBean;

    public static void startAction(Context context, int specColumnId, int type, String title, int position) {
        Intent intent = new Intent(context, CollectZLItemActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(AppConstant.ExtraKey.SUBSCRIPTION_ID, String.valueOf(specColumnId));
        bundle.putString(AppConstant.ExtraKey.EXTRAS_TITLE,title );
        bundle.putInt(AppConstant.ExtraKey.FROM_TYPE, type);
        intent.putExtras(bundle);
        mPosition=position;
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_me_collect;
    }

    @Override
    public void initPresenter() {
        if (mModel != null) {
            mPresenter.setVM(this, mModel);
        }
    }

    @Override
    public void initView() {
        //初始化recyclerview
        Intent intent = getIntent();
       list= new ArrayList<>();
        listTime= new ArrayList<>();
        listId= new ArrayList<>();
        if(intent!=null){
            title= intent.getStringExtra(AppConstant.ExtraKey.EXTRAS_TITLE);
            subscriptionId = intent.getStringExtra(AppConstant.ExtraKey.SUBSCRIPTION_ID);
            type = intent.getIntExtra(AppConstant.ExtraKey.FROM_TYPE,-1);
        }
        ntb.setTitleText(title);
        mModel.post(ApiKey.SUBSCRIPTION_COLLECT_SUBSCRIPTION_LIST,  MapUtils.getOnlyPage(1), new NewRequestCallBack<CollectZLItemBean>(CollectZLItemBean.class) {
            @Override
            public void onSuccess(CollectZLItemBean result) {
                    mResult=result.getData();
                    for(int i=0;i<mResult.size();i++){
                        if(Integer.parseInt(subscriptionId)==mResult.get(i).getSubscriptionId()){
                            for(int k=0;k<mResult.get(i).getSubscriptionTrList().size();k++){
                                list.add(mResult.get(i).getSubscriptionTrList().get(k).getCourseTitle());
                                listId.add(mResult.get(i).getSubscriptionTrList().get(k).getTargetId());
                                listTime.add(mResult.get(i).getSubscriptionTrList().get(k).getDuration());
                            }
                        }
                    }
                CollectZLItemAdapter adpter =  new CollectZLItemAdapter(getBaseContext(),list,listTime);
                recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                adpter.setItemClickListener(new CollectZLItemAdapter.ItemClickListener() {

                    @Override
                    public void itemClick(int position, AutoLinearLayout v) {
                        dataBean = mResult.get(mPosition);
                        int type = dataBean.getSubscriptionTrList().get(position).getType();
                        if(type==1){
                            SubscribeVideoActivity.startAction(AppManager.getAppManager().currentActivity(), subscriptionId,listId.get(position)+"",true);
                        }else if(type==2){
                            RecordListBean.DataBean recordBean = new RecordListBean.DataBean();
                            recordBean.setTitle(dataBean.getTitle());
                            recordBean.setPic(dataBean.getPicture());
                            recordBean.setDuration(dataBean.getSubscriptionTrList().get(position).getDuration());
                            Bundle bundle = new Bundle();
                            bundle.putString("subscriptionId", String.valueOf(dataBean.getSubscriptionId()));
                            bundle.putString("targetId", String.valueOf(dataBean.getSubscriptionTrList().get(position).getTargetId()));
                            bundle.putString("typeId", String.valueOf( dataBean.getSubscriptionTrList().get(position).getType()));
                            bundle.putString("duration", String.valueOf(dataBean.getSubscriptionTrList().get(position).getDuration()));
                            bundle.putParcelable("RecordListBean", recordBean);
                            startActivity(ZLAudioImageTextActivity.class, bundle);
                        }
                    }
                });
                recyclerView.setAdapter(adpter);
            }
        });
    }


    @Override
    public void returnData(CollectZLBean result) {

    }
}
