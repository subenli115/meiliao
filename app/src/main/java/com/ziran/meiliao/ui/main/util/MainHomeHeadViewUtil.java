package com.ziran.meiliao.ui.main.util;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonutils.AdViewpagerUtil;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.commonwidget.PlayPauseView;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.IRecyclerView;
import com.ziran.meiliao.service.ServiceManager;
import com.ziran.meiliao.ui.NewDecompressionmuseum.activity.PracticeHomeActivity;
import com.ziran.meiliao.ui.bean.HomeDataBean;
import com.ziran.meiliao.ui.decompressionmuseum.activity.ListenGuideActivity;
import com.ziran.meiliao.ui.priavteclasses.activity.DefWebActivity;
import com.ziran.meiliao.ui.priavteclasses.activity.PrivateCourseActivity;
import com.ziran.meiliao.ui.workshops.activity.WorkshopsMainActivity;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.MusicPanelFloatManager;
import com.ziran.meiliao.widget.RxTextViewVertical;
import com.ziran.meiliao.widget.WaveViewBySinCos;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/11 14:58
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/8/11$
 * @updateDes ${TODO}
 */

public class MainHomeHeadViewUtil implements View.OnClickListener {
    private View headView;
    private View viewNews;
    private ViewPager mViewPager;
    private LinearLayout mDots;
    private TextView tvMainHomeMusicTitle;
    private TextView tvMainHomeMusicDepict;
    private TextView tvMainHomeMusicAuthor;
    private ImageView ivMainHomeMusicCover;
    private PlayPauseView ivMainHomeMusicPlayOrPause;
    private IRecyclerView mRecyclerView;
    private Context mContext;
    RxTextViewVertical<HomeDataBean.DataBean.NewsBean> mRxVText;
    private WaveUtil mWaveUtil;

    public MainHomeHeadViewUtil(IRecyclerView recyclerView) {
        mRecyclerView = recyclerView;
        mContext = recyclerView.getContext();
        headView = LayoutInflater.from(mContext).inflate(R.layout.headerview_main_home, null);
        mViewPager = ViewUtil.getView(headView, R.id.viewpager);
        mDots = ViewUtil.getView(headView, R.id.ly_dots);


        tvMainHomeMusicTitle = ViewUtil.getView(headView, R.id.tv_main_home_music_title);
        tvMainHomeMusicDepict = ViewUtil.getView(headView, R.id.tv_main_home_music_depict);
        tvMainHomeMusicAuthor = ViewUtil.getView(headView, R.id.tv_main_home_music_author);
        ivMainHomeMusicCover = ViewUtil.getView(headView, R.id.iv_main_home_music_cover);
        viewNews = ViewUtil.getView(headView, R.id.ll_main_home_news);
        ivMainHomeMusicPlayOrPause = ViewUtil.getView(headView, R.id.iv_main_home_music_play_or_pause);
        mRxVText = ViewUtil.getView(headView, R.id.tv_main_home_hot_content);
        WaveViewBySinCos view1 = ViewUtil.getView(headView, R.id.wave_sin);
        WaveViewBySinCos view2 = ViewUtil.getView(headView, R.id.wave_sin2);
        mWaveUtil = new WaveUtil(view1, view2);
        ViewUtil.setOnClickListener(headView, R.id.tv_main_home_top_jyg, this);
        ViewUtil.setOnClickListener(headView, R.id.tv_main_home_top_sjk, this);
        ViewUtil.setOnClickListener(headView, R.id.tv_main_home_top_zczn, this);
        ViewUtil.setOnClickListener(headView, R.id.fl_main_home_music_more, this);
        ViewUtil.setOnClickListener(headView, R.id.iv_main_home_music_play_or_pause, this);


        mRxVText.setText(13, 5, 0xff333333);//设置属性
        mRxVText.setTextStillTime(3000);//设置停留时长间隔
        mRxVText.setAnimTime(300);//设置进入和退出的时间间隔
        mRxVText.setCallText(new RxTextViewVertical.CallText<HomeDataBean.DataBean.NewsBean>() {
            @Override
            public String getText(List<HomeDataBean.DataBean.NewsBean> list, int index) {
                return list.get(index).getTitle();
            }
        });
        mRxVText.setOnItemClickListener(new RxTextViewVertical.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                HomeDataBean.DataBean.NewsBean item = mRxVText.getItem();
                if (EmptyUtils.isNotEmpty(item.getShareUrl())) {
                    DefWebActivity.startAction(mContext, item);
                } else {
                    DefWebActivity.startAction(mContext, item.getUrl(), item.getTitle());
                }
            }
        });
    }

    public void bindHeadView() {
        mRecyclerView.setHeadView(headView);
    }

    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.tv_main_home_top_jyg:
//              MindfulnessHallActivity.startAction(mContext);
                PracticeHomeActivity.startAction(mContext);
//                BaiduDemo.startAction(mContext);
                break;
            case R.id.tv_main_home_top_sjk:
                PrivateCourseActivity.startAction(mContext,"");
                break;
            case R.id.tv_main_home_top_zczn:
                startActivity(WorkshopsMainActivity.class);
                break;
            case R.id.fl_main_home_music_more:
                startActivity(ListenGuideActivity.class);
                break;
            case R.id.iv_main_home_music_play_or_pause:
                if (!CheckUtil.check(view.getContext(), view)) return;
                MusicPanelFloatManager.getInstance().addPlayOrPauseView(ivMainHomeMusicPlayOrPause);
                MyAPP.mServiceManager.setClickFrom(ServiceManager.CLICK_FROM_PUSH);
                MyAPP.mServiceManager.playUrl(recMusicBean.getUrl());
                break;
        }
    }

    public void startActivity(Class clz) {
        if (CheckUtil.check(mContext)) {
            Intent intent = new Intent(mContext, clz);
            mContext.startActivity(intent);
        }
    }

    private HomeDataBean.DataBean.RecMusicBean recMusicBean;
    private int tvDepictViewWidth;

    public void setHomeMusic(List<HomeDataBean.DataBean.RecMusicBean> recMusic) {
        if (EmptyUtils.isNotEmpty(recMusic)) {
            recMusicBean = recMusic.get(0);
            ViewUtil.setText(tvMainHomeMusicTitle, recMusicBean.getTitle());

            if (EmptyUtils.isNotEmpty(recMusicBean.getDescript())) {
                tvDepictViewWidth = tvMainHomeMusicDepict.getWidth();
                if (tvDepictViewWidth == 0) {
                    ViewUtil.addOnGlobalLayoutListener(tvMainHomeMusicDepict, new ViewUtil.BaseCallBack() {
                        @Override
                        public void call() {
                            tvDepictViewWidth = tvMainHomeMusicDepict.getWidth();
                            setMusicDepict(tvDepictViewWidth);
                        }
                    });
                } else {
                    setMusicDepict(tvDepictViewWidth);
                }
            }
            ViewUtil.setText(tvMainHomeMusicAuthor, recMusicBean.getName());
            ImageLoaderUtils.displayTager(mContext, ivMainHomeMusicCover, recMusicBean.getPicture());
        }
    }

    private void setMusicDepict(int tvDepictViewWidth) {
        float textWidth = tvMainHomeMusicDepict.getPaint().measureText(recMusicBean.getDescript());
        if (tvDepictViewWidth < textWidth) {//有两行
            tvMainHomeMusicDepict.setGravity(Gravity.CENTER_VERTICAL);
            ViewUtil.setText(tvMainHomeMusicDepict, "\t\t\t" + recMusicBean.getDescript());
        } else {
            tvMainHomeMusicDepict.setGravity(Gravity.CENTER);
            ViewUtil.setText(tvMainHomeMusicDepict, recMusicBean.getDescript());
        }
        mWaveUtil.onRefresh();
    }

    private boolean hasNews;

    public void setNews(List<HomeDataBean.DataBean.NewsBean> news) {
        if (EmptyUtils.isNotEmpty(news)) {
            hasNews = true;
            viewNews.setVisibility(View.VISIBLE);
            mRxVText.setTextList(news);
        } else {
            hasNews = false;
            viewNews.setVisibility(View.GONE);
        }
        HandlerUtil.runMain(new Runnable() {
            @Override
            public void run() {
                mRxVText.startAutoScroll();
            }
        }, 600);
    }

    public void onPause() {
        mWaveUtil.onPause();
        if (!hasNews) return;
        mRxVText.stopAutoScroll();
    }

    public void onResume() {
        mWaveUtil.onResume();
        if (!hasNews) return;
        mRxVText.startAutoScroll();

    }

    public AdViewpagerUtil getViewPagerUtil() {
        return new AdViewpagerUtil(mContext, mViewPager, mDots, null);
    }


    public View getTopView() {
        return mViewPager;
    }


}
