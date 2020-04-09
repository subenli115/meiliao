package com.ziran.meiliao.ui.main.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.IRecyclerView;
import com.ziran.meiliao.entry.MusicEntry;
import com.ziran.meiliao.ui.bean.AlbumBean;
import com.ziran.meiliao.ui.bean.HomeAlbumBean;
import com.ziran.meiliao.ui.bean.NewHomeDataBean;
import com.ziran.meiliao.ui.bean.PractiveChartBean;
import com.ziran.meiliao.ui.decompressionmuseum.activity.AlbumDetailActivity;
import com.ziran.meiliao.ui.main.fragment.NewMainHomeFragment;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.widget.GlideRoundTransform;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 头部工具类 on 2018/12/20.
 */

public class NewMainHomeHeadViewUtil {
    private final Context mContext;
    private final View headView;
    private final RecyclerView mRecyclerView;
    private final BarChart mChart;
    private final TextView tvStatus;
    private final TextView tvSection;
    private final TextView tvAlbum;
    private final TextView tvTitle;
    private final ImageView ivRecommend;
    private AlbumBean albumBean;

    private final AutoRelativeLayout arlAlbum;
    private final AutoFrameLayout mScrollingHeader;
    private AutoLinearLayout noDataView;
    private NewHomeDataBean.DataBean.RecAlbumBean mReAlbum;
    private TextView tvRq;
    private TextView tvFz;
    private List<MusicEntry> mMusicList;
    private  HomeAlbumBean musicEntry;
    private boolean isRec;
    private String currentAlbumId;
    private String currentMusicId;


    public NewMainHomeHeadViewUtil(BarChart barchart, IRecyclerView iRecyclerView, AutoFrameLayout scrollingHeader) {
        mChart=barchart;

        mScrollingHeader=scrollingHeader;
         mContext = iRecyclerView.getContext();
         mRecyclerView=iRecyclerView;
        headView = LayoutInflater.from(mContext).inflate(R.layout.headerview_main_home_new, null);
        AutoLinearLayout v = headView.findViewById(R.id.all_exercise);
        tvStatus= headView.findViewById(R.id.tv_status);
        tvTitle= headView.findViewById(R.id.tv_title);
        arlAlbum= headView.findViewById(R.id.arl_album);
        arlAlbum.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               AlbumDetailActivity.startAction(mContext, currentAlbumId+"");
           }
       });
        ivRecommend= headView.findViewById(R.id.iv_recommend);
        tvSection= headView.findViewById(R.id.tv_section);
        tvAlbum= headView.findViewById(R.id.tv_album);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewMainHomeFragment newMainHomeFragment = new NewMainHomeFragment();
                newMainHomeFragment.startPlayer(Integer.parseInt(currentMusicId),
                        Integer.parseInt(currentAlbumId) ,albumBean,mContext,mMusicList);
            }
        });
        iRecyclerView.setHeadView(headView);
        initData();

    }
    public void startActivity(Class clz) {
        if (CheckUtil.check(mContext)) {
            Intent intent = new Intent(mContext, clz);
            mContext.startActivity(intent);
        }
    }

//    public AdViewpagerUtil getViewPagerUtil() {
//        return new AdViewpagerUtil(mContext, mViewPager, mDots, null);
//    }



    private void initData() {
         noDataView = mChart.findViewById(R.id.all_nodata);
         tvRq = mScrollingHeader.findViewById(R.id.tv_rq);
         tvFz = mScrollingHeader.findViewById(R.id.tv_fz);
        mChart.setDescription("");
        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);
        mChart.setNoDataText("");
        mChart.setDrawBarShadow(false);
        mChart.setDrawGridBackground(false);
        mChart.setScaleEnabled(false);// 是否可以缩放
        mChart.setTouchEnabled(false); // 设置是否可以触摸
        mChart.setDragEnabled(false);// 是否可以拖拽
        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelsToSkip(0);
        xAxis.setDrawGridLines(false);
        mChart.getAxisLeft().setDrawGridLines(false);
        mChart.getLegend().setEnabled(false);
        mChart.getAxisRight().setDrawLabels(false);//右侧是否显示Y轴数值
        mChart.getAxisLeft().setDrawLabels(false);
        mChart.getAxisRight().setDrawGridLines(false);
        mChart.getAxisLeft().setDrawGridLines(false);
        mChart.getAxisLeft().setDrawAxisLine(false);
        mChart.getAxisRight().setEnabled(false);//是否显示最右侧竖线
        mChart.getAxisRight().setDrawAxisLine(false);
        mChart.getAxisLeft().setDrawAxisLine(false);
        mChart.getXAxis().setAxisLineColor(Color.TRANSPARENT);
            mChart.getXAxis().setTextColor(Color.parseColor("#CCCCCC"));




        int count=14;
        if(count==0){
            noDataView.setVisibility(View.VISIBLE);
        }
//        setData(14,null,"","");
    }

    public void setMusicModule(NewHomeDataBean.DataBean.RecAlbumBean recAlbum) {
        mReAlbum=recAlbum;
        currentAlbumId=mReAlbum.getAlbumId()+"";
        currentMusicId=mReAlbum.getMusicId()+"";
        tvStatus.setText("为你推荐");
        if (Util.isOnMainThread()) {
            Glide.with(mContext).load(mReAlbum.getPicture()).transform(new GlideRoundTransform(mContext)).error(R.mipmap.ic_launcher).into(ivRecommend);
        }
        tvSection.setText(mReAlbum.getMusicName());
            tvAlbum.setText("开始练习");
        tvTitle.setText(mReAlbum.getAlbumName());
    }

    public void setMusicModule(HomeAlbumBean m) {
        musicEntry=m;
        currentMusicId =musicEntry.getMusicId();
        currentAlbumId=musicEntry.getAlbumId();
        tvStatus.setText("进行中");
        if (Util.isOnMainThread()) {
            Glide.with(mContext).load(musicEntry.getPic()).transform(new GlideRoundTransform(mContext)).error(R.mipmap.ic_launcher).into(ivRecommend);
        }
        tvSection.setText(musicEntry.getMusicName());
            tvAlbum.setText("继续练习");
        tvTitle.setText(musicEntry.getAlbumName());
    }
    public String getMax(List<PractiveChartBean.DataBean.ChartDataBean> chartData){
        String max="1";
        for(int i=0;i<chartData.size();i++){
                if(Integer.parseInt(max)<Integer.parseInt(chartData.get(i).getY_axis())){
                    max=chartData.get(i).getY_axis();
            }
        }
        return max;
    }

    public void setData(int count, List<PractiveChartBean.DataBean.ChartDataBean> chartData, String x, String y, TextView tv,TextView tv1) {
        tvRq.setText(x);
        tvFz.setText(y);
        tv.setText(getMax(chartData));
        tv1.setText(Integer.parseInt(tv.getText().toString())/2+"");
        ArrayList<BarEntry> yVals = new ArrayList<>();
        ArrayList<String> xVals = new ArrayList<>();
        List<BarDataSet> dataSets=new ArrayList<>();
        getMax(chartData);
        if(chartData!=null){
            for (int i = 0; i < chartData.size(); i++) {
                float val =(float) Integer.parseInt(chartData.get(i).getY_axis());
                yVals.add(new BarEntry((int) val, i));
                xVals.add(chartData.get(i).getX_axis() + "");
            }
        }



        BarDataSet set = new BarDataSet(yVals, "Data Set1");
        set.setBarSpacePercent(50);
        set.setColor(Color.parseColor("#FEB675"));
        set.setDrawValues(false);
        dataSets.add(set);
        BarData data = new BarData(xVals, set);
        mChart.setData(data);
        mChart.setDrawBarShadow(true);
        mChart.invalidate();
        mChart.animateY(800);
        mChart.notifyDataSetChanged();
    }


    public void setResult(AlbumBean result,List<MusicEntry> mMusicList) {
        if(result==null||mMusicList==null){
        }
        this.mMusicList=mMusicList;
        this.albumBean = result;
    }
}
