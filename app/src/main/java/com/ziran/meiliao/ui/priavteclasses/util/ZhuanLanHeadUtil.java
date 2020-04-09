package com.ziran.meiliao.ui.priavteclasses.util;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.IRecyclerView;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.support.SimpleOnItemClickListener;
import com.ziran.meiliao.envet.CalendarState;
import com.ziran.meiliao.ui.bean.SearchItemBean;
import com.ziran.meiliao.ui.bean.ShareBean;
import com.ziran.meiliao.ui.bean.SubscriptionBean;
import com.ziran.meiliao.ui.bean.ZhuanLanBigInBean;
import com.ziran.meiliao.ui.priavteclasses.adapter.SearchAllAdapter;
import com.ziran.meiliao.ui.workshops.widget.SimpleProgressView;
import com.ziran.meiliao.ui.workshops.widget.TitleListView;
import com.ziran.meiliao.utils.HtmlUtil;
import com.ziran.meiliao.utils.SpanUtils;
import com.ziran.meiliao.utils.StringUtils;
import com.ziran.meiliao.widget.CustomStudyPanelView;
import com.ziran.meiliao.widget.pupop.SharePopupWindow;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/9/18 17:29
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/9/18$
 * @updateDes ${TODO}
 */

public class ZhuanLanHeadUtil {
    private TextView tvTrailerIntro;
    private TextView tvTrailerTitle;
    private TextView tvTrailerTeaIntro;
    private ImageView ivTrailerStyle;
    private ImageView ivTrailerPic;
    private TextView tvTrailerCourseDetail;
    private Context mContext;
    private View headView;
    private boolean isShowMuLuCalender = true;
    private IRecyclerView mIRecyclerView;
    private CalendarState mCalendarState;


    private CustomStudyPanelView mCustomStudyPanelView;
    private View footerView;
    private ZhuanLanBigInBean.DataBean.DirBean mDirData;


    private TextView tvBuyCount;
    private TextView mTvDuration;
    private TextView mTvCourseCount;

    private TextView tvCouponTips;
    private ImageView ivCouponBg;
    private View ivLine;
    private View flCouponContainer;
    private View llTimeContainer;

    public ZhuanLanHeadUtil(Context context) {
        mContext = context;
    }

    public ZhuanLanHeadUtil(Context context, boolean show) {
        mContext = context;
        isShowMuLuCalender = show;
    }

    public void initHeadView(boolean showHeadPic) {
        if (headView == null) {
            headView = View.inflate(mContext, R.layout.headerview_trailer, null);
            ivTrailerPic = ViewUtil.getView(headView, R.id.iv_tralier_headPic);
            tvTrailerIntro = ViewUtil.getView(headView, R.id.tv_trailer_intro);
            tvTrailerTitle = ViewUtil.getView(headView, R.id.tv_trailer_title);

            tvBuyCount = ViewUtil.getView(headView, R.id.tv_buy_count);
            mTvDuration = ViewUtil.getView(headView, R.id.tv_duration);
            mTvCourseCount = ViewUtil.getView(headView, R.id.tv_course_count);

            tvTrailerTeaIntro = ViewUtil.getView(headView, R.id.tv_trailer_teaIntro);
            ivTrailerStyle = ViewUtil.getView(headView, R.id.iv_tralier_style);
            tvTrailerCourseDetail = ViewUtil.getView(headView, R.id.tv_trailer_course_detail);
            tvCouponTips = ViewUtil.getView(headView, R.id.tv_coupon_tips);
            ivCouponBg = ViewUtil.getView(headView, R.id.iv_coupon_bg);
            ivLine = ViewUtil.getView(headView, R.id.view_line);
            flCouponContainer = ViewUtil.getView(headView, R.id.fl_coupon_container);
            llTimeContainer = ViewUtil.getView(headView, R.id.ll_time_container);

            ivTrailerStyle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeMuLuShowStyle(!isShowMuLuCalender);
                }
            });
            if (tvTrailerIntro != null) {
                tvTrailerIntro.setVisibility(View.GONE);
            }
            if (showHeadPic) {
                ivTrailerPic.setVisibility(View.VISIBLE);
            }
        }
    }

    public void setCoupon(int price) {
        boolean showCoupon = price > 0;
        ivCouponBg.setVisibility(showCoupon ? View.VISIBLE : View.GONE);
        tvCouponTips.setVisibility(showCoupon ? View.VISIBLE : View.GONE);
        ivLine.setVisibility(showCoupon ? View.GONE : View.VISIBLE);
        int color = mContext.getResources().getColor(R.color.textColor_teshe5);
        if (price > 0) {
            SpanUtils spanUtils = new SpanUtils();
            spanUtils.append("\u3000\u3000完成系列课程练习并按期打卡转发分享，系统奖励").append("¥200优惠券").setFontSize(14, true).setForegroundColor(color)
                    .append("，可通用于5P医学APP组织的所有正念专业课程。");
            tvCouponTips.setText(spanUtils.create());
        }
    }

    public void openCoupon(){
        llTimeContainer.setVisibility(View.VISIBLE);
        flCouponContainer.setVisibility(View.VISIBLE);
    }
    private void changeMuLuShowStyle(boolean show) {
        this.isShowMuLuCalender = show;
        ivTrailerStyle.setImageResource(isShowMuLuCalender ? R.mipmap.course_icon_list : R.mipmap.course_icon_date);
        int visibleItemPosition = ((LinearLayoutManager) mIRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        if (mOnChangeCalListener != null) {
            mOnChangeCalListener.onShowCal(isShowMuLuCalender);
        } else if (mCalendarState != null) {
            mCalendarState.changeStyle();
            if (isShowMuLuCalender) {
                mIRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 8));
                mIRecyclerView.setAdapter((CommonRecycleViewAdapter) mCalendarState);

            } else {
                mIRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                mIRecyclerView.setAdapter((CommonRecycleViewAdapter) mCalendarState);
            }
            if (visibleItemPosition == 1) {
                visibleItemPosition = 2;
            }
            LogUtils.logd("visibleItemPosition" + visibleItemPosition);
            mIRecyclerView.scrollToPosition(visibleItemPosition);

            if (mCustomStudyPanelView != null) {
                mCustomStudyPanelView.setVisibility(isShowMuLuCalender ? View.VISIBLE : View.GONE);
            }
//            mIRecyclerView.setFooterViewState(isShowMuLuCalender);
        }
    }

    private OnChangeCalListener mOnChangeCalListener;

    public void setOnChangeCalListener(OnChangeCalListener onChangeCalListener) {
        mOnChangeCalListener = onChangeCalListener;
    }

    public boolean isShowCal() {
        return isShowMuLuCalender;
    }

    public void updatePlayState(boolean isPlaying) {
        if (mCustomStudyPanelView != null) {
            mCustomStudyPanelView.updatePlayState(isPlaying);
        }
    }

    public void setFooterOnClickListener(View.OnClickListener listener) {
        if (mCustomStudyPanelView != null) {
            mCustomStudyPanelView.setOnClickListener(listener);
        }
    }

    public void setDirData(ZhuanLanBigInBean.DataBean.DirBean dirData) {
        mDirData = dirData;
    }

    public ZhuanLanBigInBean.DataBean.DirBean getDirData() {
        return mDirData;
    }

    public void setCustomStudyFinish() {
        mCustomStudyPanelView.setVisibility(isShowMuLuCalender ? View.VISIBLE : View.GONE);
//        if (mCustomStudyPanelView!=null)mCustomStudyPanelView.setVisibility(show?View.VISIBLE:View.GONE);
    }

    public void setCoupon(String firstWorld, String secondWorld, String thirdWorld) {
        boolean showCoupon = EmptyUtils.isNotEmpty(firstWorld);
        ivCouponBg.setVisibility(showCoupon ? View.VISIBLE : View.GONE);
        tvCouponTips.setVisibility(showCoupon ? View.VISIBLE : View.GONE);
        ivLine.setVisibility(showCoupon ? View.GONE : View.VISIBLE);
        int color = mContext.getResources().getColor(R.color.textColor_teshe5);
        if (showCoupon) {
            SpanUtils spanUtils = new SpanUtils();
            spanUtils.append("\u3000" + firstWorld).append(secondWorld).setFontSize(13, true).setForegroundColor(color).append
                    (thirdWorld);
            tvCouponTips.setText(spanUtils.create());
        }
    }

    public interface OnChangeCalListener {
        void onShowCal(boolean isShowCal);
    }


    private ShareBean mDataBean;

    public void setShareDataBean(ShareBean dataBean) {
        mDataBean = dataBean;
    }

    public void bindTarget(IRecyclerView iRecyclerView) {
        LogUtils.logd("headView:"+headView  + iRecyclerView  );
        if (EmptyUtils.isNotEmpty(iRecyclerView) && headView != null) {
            iRecyclerView.setHeadView(headView);
        }
    }

    public void setTarget(IRecyclerView recyclerView, CalendarState state) {
        this.mIRecyclerView = recyclerView;
        this.mCalendarState = state;
        mIRecyclerView.setFooterViewState(isShowMuLuCalender);
    }

    public void setTarget(IRecyclerView recyclerView, CalendarState state, boolean showFooterState) {
        this.mIRecyclerView = recyclerView;
        this.mCalendarState = state;
        mIRecyclerView.setFooterViewState(showFooterState);
    }

    private TitleListView mZlTitleListView;

    public void bindFooterView(IRecyclerView iRecyclerView, View.OnClickListener listener) {
        if (footerView == null) {
            footerView = LayoutInflater.from(mContext).inflate(R.layout.footer_zl_detail, null);
            mCustomStudyPanelView = ViewUtil.getView(footerView, R.id.customStudyPanelView);
            mCustomStudyPanelView.setVisibility(View.GONE);
            mZlTitleListView = ViewUtil.getView(footerView, R.id.zlTitleListView);
            mCustomStudyPanelView.setPlayClick(listener);
            iRecyclerView.setFooterView(footerView);
        }
    }

    private CommonRecycleViewAdapter zlAdapter;


    public void setZlList(List data) {
        if (EmptyUtils.isNotEmpty(data) && mZlTitleListView != null) {
            if (zlAdapter == null) {
                zlAdapter = new SearchAllAdapter(mContext);
                mZlTitleListView.setLayoutManager(new LinearLayoutManager(mContext));
                mZlTitleListView.setAdapter(zlAdapter);
                mZlTitleListView.setMoreTitleVis(View.GONE);
                mZlTitleListView.setTvTitle("相关内容");
                mZlTitleListView.setVisibility(View.VISIBLE);
                mZlTitleListView.setTvTitleIcon(R.mipmap.workshops_ic_hot);
                mZlTitleListView.setRecyclerViewPaddLeft(0);
                zlAdapter.setHeadCount(0);
                zlAdapter.setOnItemClickListener(new SimpleOnItemClickListener() {
                    @Override
                    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                        SearchUtil.onItemClick(mContext, (SearchItemBean) o);
                    }
                });
            }
            zlAdapter.replaceAll(data);

        }
    }


    public void bindFooterView(IRecyclerView iRecyclerView, View.OnClickListener listener, int vis) {
        bindFooterView(iRecyclerView, listener);
        mCustomStudyPanelView.setPlayShowState(vis);
    }


    private SharePopupWindow mSharePopupWindow;

    public void share() {
        if (mDataBean == null) return;
        SharePopupWindow.showPopup(mContext, mSharePopupWindow, mDataBean);
    }


    private ViewStub mViewStub;
    private ImageView ivSubImg;
    private TextView tvSubDes;
    private TextView tvSubPrice;
    private TextView tvSubSkip;

    public void initSub(final SubscriptionBean subscription) {
        if (mViewStub == null) {
            mViewStub = ViewUtil.getView(headView, R.id.viewStub_sub_skip);
            View inflate = mViewStub.inflate();
            ivSubImg = ViewUtil.getView(inflate, R.id.iv_trailer_course_detail_sub_img);
            tvSubDes = ViewUtil.getView(inflate, R.id.tv_trailer_course_detail_sub_des);
            tvSubPrice = ViewUtil.getView(inflate, R.id.tv_trailer_course_detail_sub_price);
            tvSubSkip = ViewUtil.getView(inflate, R.id.tv_trailer_course_detail_sub_skip);
            tvSubSkip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    ZhuanLanDetailActivity.startAction(mContext, subscription.getSubscriptionId());
                }
            });
        }
        ImageLoaderUtils.displayTager(mContext, ivSubImg, subscription.getPic());
        ViewUtil.setText(tvSubDes, subscription.getTitle());
        ViewUtil.setText(tvSubPrice, StringUtils.format("¥%d元／年", (int) subscription.getPrice()));
    }


    public void setFooterViewData(String url, String title, String duration, int watchCount, int studyStatus, boolean stVis, boolean
            isBuy) {
        if (mCustomStudyPanelView != null) {
            mCustomStudyPanelView.setResDes(url, title, duration, watchCount, studyStatus, stVis, isBuy);
        }
    }

    public void setFooterViewData(String url, String title, String duration, int watchCount, int studyStatus, boolean stVis, boolean
            isBuy, boolean showPlay) {

        if (mCustomStudyPanelView != null) {
            if (showPlay) {
                mCustomStudyPanelView.setPlayShowState(View.VISIBLE);
            }
            mCustomStudyPanelView.setResDes(url, title, duration, watchCount, studyStatus, stVis, isBuy);
        }
    }

    public void setFooterViewData(String title, String duration, int watchCount, int studyStatus, boolean stVis, boolean isBuy) {
        setFooterViewData(null, title, duration, watchCount, studyStatus, stVis, isBuy);
    }

    public void setHeadData(String picUrl, String title, String teaIntro, String detail) {
        try {
            ImageLoaderUtils.displayTager(mContext, ivTrailerPic, picUrl, R.mipmap.ic_loading_square_big);
            ViewUtil.setText(tvTrailerTitle, title);
            ViewUtil.setText(tvTrailerTeaIntro, teaIntro);
            ViewUtil.setHtmlText(tvTrailerCourseDetail, detail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setHeadData2(int buyCount, String startTime, String endTime, int courseCount) {
        try {

            ViewUtil.setText(tvBuyCount, HtmlUtil.format("%d人", buyCount));
            ViewUtil.setText(mTvDuration, HtmlUtil.format("%s-%s", startTime, endTime));
            ViewUtil.setHtmlText(mTvCourseCount, HtmlUtil.format("%d次", courseCount));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setProgressFinish(int progress) {
        View progressPanel = ViewUtil.getView(headView, R.id.ll_progress_panel);
        progressPanel.setVisibility(View.VISIBLE);
        SimpleProgressView simpleProgressView = ViewUtil.getView(headView, R.id.simpleProgressView);
        simpleProgressView.setProgress(progress);
        ViewUtil.setText(progressPanel, R.id.tv_progress, progress + "%");
    }

}
