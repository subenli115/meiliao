package com.ziran.meiliao.ui.NewDecompressionmuseum.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.ui.NewDecompressionmuseum.adapter.PracticeLiveDataAdapter;
import com.ziran.meiliao.ui.NewDecompressionmuseum.adapter.PracticeLiveSaveAdapter;
import com.ziran.meiliao.ui.NewDecompressionmuseum.contract.PracticeFiveContract;
import com.ziran.meiliao.ui.NewDecompressionmuseum.presenter.PracticeFivePresenter;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.JdxShareBean;
import com.ziran.meiliao.ui.bean.PracticeFiveDetailBean;
import com.ziran.meiliao.ui.bean.PracticeSaveBean;
import com.ziran.meiliao.ui.bean.SenseSaveBean;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.BottomMbsrView;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

import static com.ziran.meiliao.constant.ApiKey.PRACTIEACTIVITY_DETATILFIVELIST;
import static com.ziran.meiliao.constant.ApiKey.PRACTIEACTIVITY_FIVESTATUSSAVE;
import static com.ziran.meiliao.constant.ApiKey.PRACTIEACTIVITY_STATUSUPDATE;

/**
 * Created by Administrator on 2018/8/21.
 */

public class WeekLiveHomeActivity extends CommonHttpActivity<PracticeFivePresenter, CommonModel> implements PracticeFiveContract.View,View.OnClickListener   {

    private static Boolean mIsFinish;
    private static int mpracticeStatus;
    @Bind(R.id.ntb_title)
    NormalTitleBar ntbTitle;
    @Bind(R.id.tv_add)
    ImageView tvAdd;
    @Bind(R.id.recyclerView_top)
    RecyclerView recyclerViewTop;
    @Bind(R.id.recyclerView_bottom)
    RecyclerView recyclerViewBottom;
    @Bind(R.id.arl_top)
    AutoRelativeLayout arlTop;
    @Bind(R.id.tv_save)
    TextView tvSave;
    @Bind(R.id.all_bottom)
    AutoLinearLayout all_bottom;
    @Bind(R.id.bottom_exercise_view)
    BottomMbsrView mBottomExerciseView;
    @Bind(R.id.iv_bg)
    ImageView iv_bg;
    @Bind(R.id.tv_title_ground)
    TextView tvTitle;
    private String id;
    private String itemId;
    private PracticeLiveDataAdapter mAdapter;
    private List<PracticeFiveDetailBean.DataBean.SenseChooseListBean> mData;
    private Map<String, String> fiveSaveMap;
    private List<SenseSaveBean> saveBeans;
    private PracticeLiveSaveAdapter mTopAdapter;
    private ArrayList<Boolean> selectList;
    private ArrayList<String> nameList;
    private List<PracticeFiveDetailBean.DataBean.SenseChooseListBean> mDataSelect;
    private JdxShareBean.DataBean mBean;
    private boolean mRefresh;
    private Map<String, String> statusMap;


    public static void startAction(Context context, String id, String itemId, JdxShareBean.DataBean result, Boolean isFinish, int practiceStatus) {
        Intent intent = new Intent(context, WeekLiveHomeActivity.class);
        intent.putExtra("id",id);
        Bundle bundle = new Bundle();
        intent.setExtrasClassLoader(JdxShareBean.class.getClassLoader());
        bundle.putParcelable("JdxShareBean", result);
        mpracticeStatus=practiceStatus;
        intent.putExtras(bundle);
        mIsFinish=isFinish;
        intent.putExtra("itemId",itemId);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_jdx_live_list;
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
             id = getIntent().getStringExtra("id");
            itemId = getIntent().getStringExtra("itemId");
            mBean = getIntent().getExtras().getParcelable("JdxShareBean");
        }
        if(mIsFinish){
            isFinish();
            return;
        }
        tvTitle.setText("已完成的时候打勾“√”");
        nameList = new ArrayList<>();
        saveBeans = new ArrayList<>();
        arlTop.setVisibility(View.GONE);
        ntbTitle.setTitleWeizhi();
        ntbTitle.setLeftImagSrc(R.mipmap.back6);
        ntbTitle.setBackGroundColor(R.color.transparent);
        ntbTitle.setTvLeftVisiable(true, true);
        ntbTitle.setTitleColor(R.color.black);
        ntbTitle.setOnivBackImagListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mRefresh){
                    setResult(2, new Intent());
                }
                finish();
            }
        });
        statusMap = MapUtils.getDefMap(true);
        statusMap.put("id",id);
        statusMap.put("itemId",itemId+"");
        statusMap.put("status","1");
        Map<String, String> fiveMap = MapUtils.getDefMap(true);
        selectList = new ArrayList<>();
        tvSave.setOnClickListener(this);
        all_bottom.setOnClickListener(this);
        fiveSaveMap = MapUtils.getDefMap(true);
        fiveSaveMap.put("id",id);
        fiveSaveMap.put("itemId",itemId);
        fiveMap.put("id",id);
        fiveMap.put("itemId",itemId);
        mPresenter.getPracticeFiveData(PRACTIEACTIVITY_DETATILFIVELIST,fiveMap);
    }

    @Override
    public void showFiveData(PracticeFiveDetailBean.DataBean result) {
        ntbTitle.setNewTitleText(result.getItemTitle());
        recyclerViewBottom.setLayoutManager(new LinearLayoutManager(this));
        mDataSelect = result.getSenseChooseList();
        mData= result.getSenseList();
        Glide.with(mContext).load(result.getBgPic()).error(R.mipmap.jdx_bg_live).into(iv_bg);
        mAdapter = new PracticeLiveDataAdapter(mData, mContext);
        mAdapter.setItemClickListener(new PracticeLiveDataAdapter.ItemClickListener() {
            @Override
            public void itemClick(int position, View v) {
                arlTop.setVisibility(View.VISIBLE);
                SenseSaveBean senseSaveBean = new SenseSaveBean();
                senseSaveBean.setSenseId(mData.get(position).getSenseId());
                senseSaveBean.setSenseDetail(mData.get(position).getSenseDetail());
                senseSaveBean.setPracticeStatus(0);
                senseSaveBean.setSenseStatus(1);
                saveBeans.add(senseSaveBean);
                nameList.add(mData.get(position).getSenseDetail());
                selectList.add(false);
                mData.remove(position);
                mAdapter.updateData(mData,mContext);
                mTopAdapter.updateData(saveBeans,nameList,mContext);
            }

        });
        recyclerViewBottom.setAdapter(mAdapter);
        //top
        if(mDataSelect!=null&&mDataSelect.size()>0){
            for(int i=0;i<mDataSelect.size();i++){
                PracticeFiveDetailBean.DataBean.SenseChooseListBean senseChooseListBean = mDataSelect.get(i);
                SenseSaveBean senseSaveBean = new SenseSaveBean();
                senseSaveBean.setSenseId(senseChooseListBean.getSenseId());
                senseSaveBean.setSenseDetail(senseChooseListBean.getSenseDetail());
                senseSaveBean.setSenseStatus(senseChooseListBean.getSenseStatus());
                senseSaveBean.setPracticeStatus(senseChooseListBean.getPracticeStatus());
                saveBeans.add(senseSaveBean);
                if(senseChooseListBean.getPracticeStatus()==1){
                    selectList.add(true);
                }else {
                    selectList.add(false);
                }
                nameList.add(mDataSelect.get(i).getSenseDetail());
            }
            arlTop.setVisibility(View.VISIBLE);
        }else {
            recyclerViewBottom.setVisibility(View.VISIBLE);

        }
            recyclerViewTop.setLayoutManager(new LinearLayoutManager(mContext));

            mTopAdapter = new PracticeLiveSaveAdapter(saveBeans,selectList,nameList , mContext);
            recyclerViewTop.setAdapter(mTopAdapter);

            mTopAdapter.setItemClickListener(new PracticeLiveSaveAdapter.ItemClickListener() {
                @Override
                public void itemClick(int position, View v) {
                    if(recyclerViewBottom.getVisibility()==View.VISIBLE){
                        PracticeFiveDetailBean.DataBean.SenseChooseListBean senseSaveBean = new PracticeFiveDetailBean.DataBean.SenseChooseListBean();
                        senseSaveBean.setSenseDetail(nameList.get(position));
                        senseSaveBean.setSenseId(saveBeans.get(position).getSenseId());
                        mData.add(senseSaveBean);
                        saveBeans.remove(position);
                        nameList.remove(position);
                        selectList.remove(position);
                        mTopAdapter.updateData(saveBeans,nameList,mContext);
                        mAdapter.updateData(mData,mContext);
                        if(selectList.size()==0){
                            arlTop.setVisibility(View.GONE);
                        }
                    }else {
                        if(selectList.get(position)){
                            selectList.set(position,false);
                            saveBeans.get(position).setPracticeStatus(0);
                        }else {
                            selectList.set(position,true);
                            saveBeans.get(position).setPracticeStatus(1);
                        }
                        mTopAdapter.updateData(saveBeans,selectList,nameList,mContext);
                    }
                }
            });
    }

    @Override
    public void showPracticeStatusData(JdxShareBean.DataBean result) {
        mBean=result;

    }

    @Override
    public void showFiveSaveData(PracticeSaveBean.DataBean result) {
        if((mpracticeStatus+"").equals("0")){
            mPresenter.changePracticeStatus(PRACTIEACTIVITY_STATUSUPDATE,statusMap);
        }
       if(fiveSaveMap.get("type").equals("2")){
          isFinish();
       }
            ToastUitl.showShort("保存成功");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_save:
                if(recyclerViewBottom.getVisibility()==View.VISIBLE){
                    recyclerViewBottom.setVisibility(View.GONE);
                    tvTitle.setText("已完成的时候打勾“√”");
                    tvAdd.setImageResource(R.mipmap.jdx_red_add);
                    mTopAdapter.updateData(saveBeans,selectList,nameList,mContext);
                    fiveSaveMap.put("type","1");
                }else {
                    fiveSaveMap.put("type","2");
                    mPresenter.getPracticeFiveSaveData(PRACTIEACTIVITY_FIVESTATUSSAVE,fiveSaveMap);
                }
                fiveSaveMap.put("senseJson",getJSONArrayByList(saveBeans).toString());
                mPresenter.getPracticeFiveSaveData(PRACTIEACTIVITY_FIVESTATUSSAVE,fiveSaveMap);
                break;
            case R.id.all_bottom:
                    if(recyclerViewBottom.getVisibility()==View.VISIBLE){
                        recyclerViewBottom.setVisibility(View.GONE);
                        tvTitle.setText("已完成的时候打勾");
                        tvAdd.setImageResource(R.mipmap.jdx_red_add);
                        if(mTopAdapter!=null){
                            mTopAdapter.updateData(saveBeans,selectList,nameList,mContext);
                        }
                    }else {
                        recyclerViewBottom.setVisibility(View.VISIBLE);
                        tvTitle.setText("今日我要将正念带入以下的日常活动");
                        tvAdd.setImageResource(R.mipmap.jdx_live_down);
                        if(mTopAdapter!=null){
                            mTopAdapter.updateData(saveBeans,nameList,mContext);
                        }
                    }
                break;
        }
    }
    /**
     * 根据List获取到对应的JSONArray
     * @param list
     * @return
     */
    public static JSONArray getJSONArrayByList(List<SenseSaveBean> list){
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(list));
        return array;
    }

    private void isFinish() {
        if (EmptyUtils.isNotEmpty(mBean)) {
            mBottomExerciseView.setId(id+"");
            mBottomExerciseView.setItemId(itemId);
            mBottomExerciseView.setUserHead(mBean.getUserPic(),mBean.getPicture());
            mBottomExerciseView.setTitleText(mBean.getDuration() ,mBean.getWords(),mBean.getTitle());
            mBottomExerciseView.setHisId(mBean.getHisId()+"");
            mBottomExerciseView.setTimeShow();
            mBottomExerciseView.setShow(true);
            mBottomExerciseView.setOnCloseListener(new BottomMbsrView.OnCloseListener() {
                @Override
                public void onClose() {
                    if(mIsFinish){
                        finish();
                    }else {
                        mBottomExerciseView.setVisibility(View.GONE);
                    }
                }
            });
        }
    }
}