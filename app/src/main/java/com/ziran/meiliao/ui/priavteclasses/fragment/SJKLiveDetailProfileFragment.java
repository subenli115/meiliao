package com.ziran.meiliao.ui.priavteclasses.fragment;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.entry.VideoSectionEntry;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.SJKLiveDetailProfileBean;
import com.ziran.meiliao.ui.bean.SubscriptionBean;
import com.ziran.meiliao.ui.bean.TrailerDetailBean;
import com.ziran.meiliao.ui.priavteclasses.adapter.SJKLiveDetailProfileNewAdapter;
import com.ziran.meiliao.ui.priavteclasses.contract.SJKCourseProfileContract;
import com.ziran.meiliao.ui.priavteclasses.model.ItemPlayCallBack;
import com.ziran.meiliao.ui.priavteclasses.presenter.SJKCourseProfilePresenter;
import com.ziran.meiliao.ui.priavteclasses.util.ZhuanLanHeadUtil;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.MapUtils;

import java.util.Map;

/**
 * 私家课-直播详情-简介Fragment
 * Created by Administrator on 2017/1/7.
 */

public class SJKLiveDetailProfileFragment extends CommonRefreshFragment<SJKCourseProfilePresenter, CommonModel> implements
        SJKCourseProfileContract.View {


    String courseId;
    ItemPlayCallBack mItemPlayCallBack;
    private ZhuanLanHeadUtil mZhuanLanHeadUtil;
    private SJKLiveDetailProfileNewAdapter mSJKLiveDetailProfileNewAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_sjk_live_detail_profile;
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        FragmentActivity activity = getActivity();
        if (activity instanceof ItemPlayCallBack) {
            this.mItemPlayCallBack = (ItemPlayCallBack) activity;
        }
        loadedTip.setEmptyMsg(getString(R.string.not_course_detail), R.mipmap.ic_empty_nocontent);
        mSJKLiveDetailProfileNewAdapter = new SJKLiveDetailProfileNewAdapter(getContext());
        mZhuanLanHeadUtil = new ZhuanLanHeadUtil(getContext(), false);
        mZhuanLanHeadUtil.initHeadView(false);
//        mRxManager.on(AppConstant.RXTag.CHANGE_VIDEO_PLAY_STATE, new Action1<Boolean>() {
//            @Override
//            public void call(Boolean iPlaying) {
//                mZhuanLanHeadUtil.updatePlayState(iPlaying);
//            }
//        });
        mZhuanLanHeadUtil.bindFooterView(iRecyclerView, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentSelectData == null) {
                    currentSelectData = mSJKLiveDetailProfileNewAdapter.get(0);
                    return;
                }
                LogUtils.logd("mItemPlayCallBack: " + mItemPlayCallBack + "\tcurrentSelectData:" + currentSelectData);
                if (currentSelectData.getTag() == 2) {
                    ToastUitl.showShort("课程还未开始");
                } else {
                    mItemPlayCallBack.playUrl(currentSelectData);
                }
            }
        });
        mZhuanLanHeadUtil.setTarget(iRecyclerView, mSJKLiveDetailProfileNewAdapter);
        return mSJKLiveDetailProfileNewAdapter;
    }

    private boolean isLoadDetail = true;

    @Override
    protected void initView() {

        super.initView();
        courseId = getIntentExtra(getActivity().getIntent(), AppConstant.SPKey.COURSE_ID);
        if (getArguments() != null) {
            isLoadDetail = getArguments().getBoolean(AppConstant.ExtraKey.IS_LOAD_DETAIL, true);
        }
        setRecyclerEnabled(false);
        Map<String, String> map = MapUtils.getCourseMap(courseId);
        mPresenter.getCourseProfile(ApiKey.TRAILER_NATIVE, map);
        if (isLoadDetail) {
            mPresenter.getCourseDetail(ApiKey.GET_COURSE_DETAIL, map);
        }
    }

    @Override
    protected void loadData() {


    }

    private void setFooterData(VideoSectionEntry dirBean) {
        if (dirBean == null) return;
        mZhuanLanHeadUtil.setFooterViewData(dirBean.getUrlAndPath(), dirBean.getTitle(), dirBean.getDuration(), dirBean.getStudyCount(),
                dirBean.getStudyStatus(), false, true);
    }

    private VideoSectionEntry currentSelectData;


    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        onClickItem(o, position);
    }

    private void onClickItem(Object o, int position) {
        currentSelectData = EmptyUtils.parseObject(o);

        if (mItemPlayCallBack != null && EmptyUtils.isNotEmpty(currentSelectData)) {
            mSJKLiveDetailProfileNewAdapter.onItemClick(position);
            setFooterData(currentSelectData);
            if (mZhuanLanHeadUtil.isShowCal()) {

            } else {
                if (currentSelectData.getTag() == 2) {
                    ToastUitl.showShort("课程还未开始");
                } else {
                    mItemPlayCallBack.playUrl(currentSelectData);
                }
            }
        }
    }


    @Override
    public void showCourseDetail(SJKLiveDetailProfileBean.DataBean bean) {
        if (isLoadDetail) {
            mRxManager.post(AppConstant.RXTag.VIDEO_PATH, bean);
            if (bean.getSubscription() != null) {
                final SubscriptionBean subscription = bean.getSubscription();
                if (!subscription.isBuy()) {
                    mZhuanLanHeadUtil.initSub(subscription);
                }
            }
        }
    }


    @Override
    public void showCourseProfile(final TrailerDetailBean.DataBean dataBean) {
//        LogUtils.logd("dataBean"+dataBean.toString());
        HandlerUtil.runMain(new Runnable() {
            @Override
            public void run() {
                mZhuanLanHeadUtil.bindTarget(iRecyclerView);
                mZhuanLanHeadUtil.setHeadData(dataBean.getPic(), dataBean.getTitle(), dataBean.getAuthor().getTeaIntro(), dataBean.getDetail());
                updateData(dataBean.getDir());
                if (EmptyUtils.isNotEmpty(dataBean.getDir())){
                    currentSelectData = mSJKLiveDetailProfileNewAdapter.getItem();
                    setFooterData(currentSelectData);
                }
            }
        }, 120);
    }


    public VideoSectionEntry getCurrentData() {
        return currentSelectData;
    }
}
