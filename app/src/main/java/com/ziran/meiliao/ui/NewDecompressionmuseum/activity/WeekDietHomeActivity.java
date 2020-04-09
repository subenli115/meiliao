package com.ziran.meiliao.ui.NewDecompressionmuseum.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.ui.NewDecompressionmuseum.adapter.PracticeDietAdapter;
import com.ziran.meiliao.ui.NewDecompressionmuseum.contract.PracticeThreeDataContract;
import com.ziran.meiliao.ui.NewDecompressionmuseum.presenter.PracticeThreeDataPresenter;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.JdxShareBean;
import com.ziran.meiliao.ui.bean.PracticeSaveBean;
import com.ziran.meiliao.ui.bean.PracticeThreeBean;
import com.ziran.meiliao.ui.bean.PracticeThreeDetailCheckBean;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.BottomMbsrView;
import com.ziran.meiliao.widget.pupop.SharePopupWindow;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.Map;

import butterknife.Bind;

import static com.ziran.meiliao.constant.ApiKey.PRACTIEACTIVITY_DetailThreeCheck;

/**
 * Created by Administrator on 2018/8/20.
 */

public class WeekDietHomeActivity extends CommonHttpActivity<PracticeThreeDataPresenter, CommonModel> implements PracticeThreeDataContract.View,View.OnClickListener {

    private static boolean mIsFinish;
    private static int mpracticeStatus;
    @Bind(R.id.ntb_title)
    NormalTitleBar ntbTitle;
    @Bind(R.id.arl_add)
    AutoRelativeLayout arlAdd;
    @Bind(R.id.all_add1)
    AutoRelativeLayout arlAdd1;
    @Bind(R.id.arl_times)
    AutoRelativeLayout arlTimes;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.tv_share)
    TextView tvShare;
    @Bind(R.id.tv_times)
    TextView tvTimes;
    @Bind(R.id.tv_add)
    ImageView tvAdd;
    @Bind(R.id.iv_bg)
    ImageView ivbg;
    @Bind(R.id.arl_tj)
    AutoRelativeLayout arl_tj;
    @Bind(R.id.tv_content_diet)
    TextView tv_content_diet;

    private String itemId;
    private String id;
    private PracticeDietAdapter mAdapter;
    private final static int REQUESTCODE = 1; // 返回的结果码
    private SharePopupWindow mSharePopupWindow;
    private PracticeThreeDetailCheckBean.DataBean mResult;
    private int amounts;
    private JdxShareBean.DataBean mBean;
    @Bind(R.id.bottom_exercise_view)
    BottomMbsrView mBottomExerciseView;
    private boolean mRefresh;


    @Override
    public int getLayoutId() {
        return R.layout.activity_jdx_diet_list;
    }

    @Override
    public void initPresenter() {
        if (mModel != null && mPresenter != null) {
            mPresenter.setVM(this, mModel);
        }
    }
    public static void startAction(Context context, String id, String itemId, JdxShareBean.DataBean result, boolean isFinish, int practiceStatus) {
        Intent intent = new Intent(context, WeekDietHomeActivity.class);
        intent.putExtra("id",id);
        Bundle bundle = new Bundle();
        mpracticeStatus=practiceStatus;
        mIsFinish=isFinish;
        bundle.putParcelable("JdxShareBean", result);
        intent.putExtras(bundle);
        intent.setExtrasClassLoader(JdxShareBean.class.getClassLoader());
        intent.putExtra("itemId",itemId);
        context.startActivity(intent);
    }

    @Override
    public void initView() {
        super.initView();
        if (getIntent() != null) {
            Intent intent = getIntent();
            id = intent.getStringExtra("id");
            mBean =  intent.getExtras().getParcelable("JdxShareBean");
            itemId = intent.getStringExtra("itemId");
        }

        ntbTitle.setTitleWeizhi();
        ntbTitle.setBackGroundColor(R.color.transparent);
        ntbTitle.setTvLeftVisiable(true, true);
        ntbTitle.setTitleText("正念饮食");
        ntbTitle.setOnivBackImagListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mRefresh){
                    setResult(2, new Intent());
                }
                finish();
            }
        });
        ntbTitle.setLeftImagSrc(R.mipmap.exercise_back);
        arlAdd.setOnClickListener(this);
        arlAdd1.setOnClickListener(this);
        tvShare.setOnClickListener(this);
        if(mIsFinish){
            isFinish();
            return;
        }
        getThreeData();
    }

    private void getThreeData() {
        Map<String, String> threeMap = MapUtils.getDefMap(true);
        threeMap.put("id", id);
        threeMap.put("itemId", itemId);
        threeMap.put("page", "1");
        threeMap.put("pageSize", "3");
        mPresenter.getPracticeThreeCheckData(PRACTIEACTIVITY_DetailThreeCheck, threeMap);
    }

    @Override
    public void showThreeData(PracticeThreeBean.DataBean result) {


    }

    @Override
    public void showThreeCheckData(PracticeThreeDetailCheckBean.DataBean result) {
        Glide.with(mContext).load(result.getBgPic()).error(R.mipmap.jdx_bg_diet).into(ivbg);
        mResult = result;
        tv_content_diet.setText(result.getNotice());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new PracticeDietAdapter(result.getDetailList(), mContext);
        recyclerView.setAdapter(mAdapter);
        amounts = result.getAmounts();
        tvTimes.setText(result.getAmounts()+"");
        if(result.getAmounts()!=0){
            arl_tj.setVisibility(View.VISIBLE);
            arlAdd.setVisibility(View.VISIBLE);
            arlAdd1.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showThreeSaveData(PracticeSaveBean.DataBean result) {

    }

    @Override
    public void showPracticeStatusData(JdxShareBean.DataBean result) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.all_add1:
            case R.id.arl_add:
//                WeekDietSaveActivity.startAction(mContext, id, itemId, amounts + "");
                Intent intent = new Intent(mContext, WeekDietSaveActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("itemId", itemId);
                intent.putExtra("amounts", amounts+"");
                intent.putExtra("bgpic",mResult.getBgPic());
                intent.putExtra("mpracticeStatus",mpracticeStatus+"");
                startActivityForResult(intent, REQUESTCODE);
                break;
            case R.id.tv_share:
                isFinish();
                break;
        }
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

    // 为了获取结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // RESULT_OK，判断另外一个activity已经结束数据输入功能，Standard activity result:
        if (resultCode == 2) {
            if (requestCode == REQUESTCODE) {
                getThreeData();
            }
        }
    }
}
