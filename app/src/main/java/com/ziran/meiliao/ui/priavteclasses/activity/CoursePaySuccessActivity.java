package com.ziran.meiliao.ui.priavteclasses.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.CoursePaySuccessResult;
import com.ziran.meiliao.ui.decompressionmuseum.activity.AlbumDetailActivity;
import com.ziran.meiliao.ui.priavteclasses.adapter.CourseSuccessAdapter;
import com.ziran.meiliao.ui.settings.activity.IntegralDetailsActivity;
import com.ziran.meiliao.utils.MapUtils;

import java.util.ArrayList;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

import static com.ziran.meiliao.constant.ApiKey.ACTIVITY_RECOMMEND;

public class CoursePaySuccessActivity extends CommonHttpActivity<CommonPresenter, CommonModel> implements CommonContract.ActionView<Result, Result>{

    @Bind(R.id.ntb_title)
    NormalTitleBar ntbTitle;
    @Bind(R.id.tv_hint)
    TextView tvHint;
    @Bind(R.id.tv_points)
    TextView tvPoints;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    private String points;
    private String detaillId;

    @Override
    public int getLayoutId() {
        return R.layout.ac_course_pay_success;
    }

    @Override
    public void returnData(Result result) {

        CoursePaySuccessResult bean=(CoursePaySuccessResult)result;
        CoursePaySuccessResult.DataBean data = bean.getData();
        ArrayList<Object> objects = new ArrayList<>();
        if(data.getCourse()!=null){
            for(int i=0;i<data.getCourse().size();i++){
                objects.add(data.getCourse().get(i));
            }
        }
            if(data.getAlbum()!=null){

                for(int k=0;k<data.getAlbum().size();k++){
                    objects.add(data.getAlbum().get(k));
                }
            }

        CourseSuccessAdapter courseSuccessAdapter = new CourseSuccessAdapter(mContext,objects);
        courseSuccessAdapter.setItemClickListener(new CourseSuccessAdapter.ItemClickListener() {
            @Override
            public void itemClick(int isSelect, int id, boolean isAlbum, String name) {
                if(isAlbum){
                    AlbumDetailActivity.startAction(mContext,id+"");
                }else {
                    NewGongZuoFangActivity.startAction(mContext, id+"",name);
                }

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(courseSuccessAdapter);
    }

    @Override
    public void returnAction(Result result) {

    }
    public static void startAction(Context context, String points,String detaillId) {
        Intent intent = new Intent();
        intent.putExtra("points",points);
        intent.putExtra("detaillId",detaillId);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setClass(context, CoursePaySuccessActivity.class);
        context.startActivity(intent);
    }
    @Override
    public void initView() {
        super.initView();
        if(getIntent()!=null){
             points = getIntent().getStringExtra("points");
            detaillId = getIntent().getStringExtra("detaillId");
        }

        tvPoints.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );
        tvHint.setText("恭喜您获得"+points+"积分,已存入您的积分账户！");
        ntbTitle.setBackGroundColor1();
        ntbTitle.setLeftImagSrc(R.mipmap.exercise_back);
        ntbTitle.setTitleWeizhi();
        ntbTitle.setTitleText("支付成功");
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("detailId",detaillId);
        mPresenter.postData(ACTIVITY_RECOMMEND, defMap, CoursePaySuccessResult.class);
    }

    @OnClick({R.id.tv_back_course,R.id.tv_points})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back_course:
                finish();
                break;
            case R.id.tv_points:
                IntegralDetailsActivity.startAction(mContext);
                finish();
                break;
        }
    }
    }