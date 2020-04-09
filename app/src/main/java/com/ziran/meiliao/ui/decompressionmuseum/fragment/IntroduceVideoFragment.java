package com.ziran.meiliao.ui.decompressionmuseum.fragment;

import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.IRecyclerView;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.RecListMainBean;
import com.ziran.meiliao.ui.decompressionmuseum.adapter.CatalogRecordAdapter;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.MapUtils;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import rx.functions.Action1;

public class IntroduceVideoFragment  extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract.View<Result> {


    private CatalogRecordAdapter mCatalogRecordAdapter;
    private String recId;
    @Bind(R.id.recyclerView)
    IRecyclerView recyclerView;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_hint)
    TextView tvHint;
    private List<RecListMainBean.DataBean.RecListBean> recList;
    private int courseId;
    private Result mResult;
    private String title;
    private boolean headLoad=false;

    @Override
    protected void initView() {
        super.initView();
         recId = (String)getArguments().get("recId");
        mRxManager.on(AppConstant.RXTag.SWITCH_FRAGMENT, new Action1<Message>() {
            @Override
            public void call(Message msg) {
                switch (msg.what) {
                    case 0:
                        String str =(String) msg.obj;
                        if(str.equals("update")){
                         loadData();
                        }
                        break;
                }
            }
        });
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_common_recyclerview_gray_rec;
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        iRecyclerView.setLoadMoreEnabled(false);

        mCatalogRecordAdapter = new CatalogRecordAdapter(getContext(), R.layout.item_video_catalog);
        return mCatalogRecordAdapter;
    }


    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        super.onItemClick(parent, view, o, position);
        for(int i=0;i<recList.size();i++){
            if(i==position){
                recList.get(position).setIsclick(true);
            }else{
                recList.get(i).setIsclick(false);

            }
        }

            mCatalogRecordAdapter.notifyItemChanged(position);
            mCatalogRecordAdapter.notifyDataSetChanged();
        RecListMainBean.DataBean.RecListBean bean = (RecListMainBean.DataBean.RecListBean) o;
        mRxManager.post(AppConstant.RXTag.SUBSCRIBE_UPDATE, HandlerUtil.obj(7, bean));


    }

    @Override
    protected void loadData() {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("recId",recId);
        mPresenter.postData(ApiKey.RECORD_GETRECLIST,defMap, RecListMainBean.class);
    }

    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    public void returnData(Result result) {

        mResult=result;
        RecListMainBean result1 = (RecListMainBean) result;
        RecListMainBean.DataBean data = result1.getData();
        recList = result1.getData().getRecList();
        for(int i=0;i<recList.size();i++){
            if(recList.get(i).isLock()){
                courseId=recList.get(i-1).getId();
                 title = recList.get(i).getTitle();
                break;
            }
        }
        headLoad=true;
        mRxManager.post(AppConstant.RXTag.SUBSCRIBE_UPDATE, HandlerUtil.obj(8, courseId+""));
        mRxManager.post(AppConstant.RXTag.SUBSCRIBE_UPDATE, HandlerUtil.obj(7, recList.get(0)));
        mAdapter.clear();
        updateData(recList);
            tvTitle.setText(data.getToptip());
            tvHint.setText(data.getTip());


    }

}