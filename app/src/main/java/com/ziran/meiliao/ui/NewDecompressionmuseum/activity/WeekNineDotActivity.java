package com.ziran.meiliao.ui.NewDecompressionmuseum.activity;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.ui.NewDecompressionmuseum.contract.PracticeTwoDataContract;
import com.ziran.meiliao.ui.NewDecompressionmuseum.presenter.PracticeTwoDataPresenter;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.JdxShareBean;
import com.ziran.meiliao.ui.bean.PracticeSaveBean;
import com.ziran.meiliao.ui.bean.PracticeTwoDetailBean;
import com.ziran.meiliao.utils.MapUtils;

import java.util.Map;

import butterknife.Bind;
import uk.co.senab.photoview.PhotoViewAttacher;

import static com.ziran.meiliao.constant.ApiKey.PRACTIEACTIVITY_DetailTWO;
import static com.ziran.meiliao.constant.ApiKey.PRACTIEACTIVITY_STATUSUPDATE;

/**
 * Created by Administrator on 2018/8/20.
 */

public class WeekNineDotActivity extends CommonHttpActivity<PracticeTwoDataPresenter, CommonModel> implements PracticeTwoDataContract.View {
    @Bind(R.id.ntb_title)
    NormalTitleBar ntbTitle;
    @Bind(R.id.iv_bg)
    ImageView ivBg;
    @Bind(R.id.iv_nine)
    ImageView ivNine;
    @Bind(R.id.tv_finish)
    TextView tvFinish;
//    @Bind(R.id.photoview)
//    PhotoView photoView;

    @Bind(R.id.tv_hint)
    TextView tvHint;
    private String id;
    private String itemId;
    private boolean mRefresh;
    private Map<String, String> statusMap;
    private int practiceStatus;


    public static void startAction(Context context,String id,String itemId) {
        Intent intent = new Intent(context, WeekNineDotActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("itemId",itemId);
        context.startActivity(intent);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_jdx_nine_dot;
    }

    @Override
    public void initPresenter() {
        if (mModel != null && mPresenter != null) {
            mPresenter.setVM(this, mModel);
        }
    }



    PhotoViewAttacher mAttacher;
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void initView() {
        if(getIntent()!=null){
            id = getIntent().getStringExtra("id");
            itemId = getIntent().getStringExtra("itemId");
        }
//        mAttacher = new PhotoViewAttacher(photoView);
        ntbTitle.setTitleWeizhi();

        ntbTitle.setBackGroundColor(R.color.transparent);
        ntbTitle.setTvLeftVisiable(true, true);
        ntbTitle.setTitleColor(Color.BLACK);
        ntbTitle.setOnivBackImagListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mRefresh){
                    setResult(2, new Intent());
                }
                finish();
            }
        });
        ntbTitle.setLeftImagSrc(R.mipmap.back6);
        Map<String, String> twoMap = MapUtils.getDefMap(true);
        twoMap.put("id", id);
        twoMap.put("itemId", itemId);
        mPresenter.getPracticeTwoData(PRACTIEACTIVITY_DetailTWO,twoMap);
        statusMap = MapUtils.getDefMap(true);
        statusMap.put("id",id);
        statusMap.put("itemId",itemId+"");
        statusMap.put("status","1");
        tvFinish.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( practiceStatus==0){
                    mPresenter.changePracticeStatus(PRACTIEACTIVITY_STATUSUPDATE,statusMap);
                }
                    tvFinish.setBackgroundResource(R.drawable.normal_bg_gray_441);
                    tvFinish.setText("已完成");
            }
        });
    }

    @Override
    public void showTwoData(PracticeTwoDetailBean.DataBean result) {
//        photoView.setImageURI(Uri.parse(result.getPicture()));
//        mAttacher.update();
        Glide.with(mContext).load(result.getBgPic()).error(R.mipmap.bg_jdx_nine_dot).into(ivBg);
        Glide.with(mContext).load(result.getPicture()).error(R.mipmap.bg_jdx_nine_dot).into(ivNine);
        if(result.getIsNinthPra().equals("0")){
            tvFinish.setVisibility(View.GONE);
            ivNine.setVisibility(View.GONE);
        }else{
            tvFinish.setVisibility(View.VISIBLE);
            practiceStatus = result.getPracticeStatus();
            tvHint.setText(result.getNotice());
            if(result.getPracticeStatus()==1){
                tvFinish.setBackgroundResource(R.drawable.normal_bg_gray_44);
                tvFinish.setText("已完成");
            }
        }

        ntbTitle.setNewTitleText(result.getItemTitle());

    }

    @Override
    public void showTwoSaveData(PracticeSaveBean.DataBean result) {

    }

    @Override
    public void showPracticeStatusData(JdxShareBean.DataBean result) {

    }
}
