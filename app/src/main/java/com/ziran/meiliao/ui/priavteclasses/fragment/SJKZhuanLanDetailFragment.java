package com.ziran.meiliao.ui.priavteclasses.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.app.WpyxConfig;
import com.ziran.meiliao.common.commonutils.TimeUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.MyOnScrollListener;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.SearchItemBean;
import com.ziran.meiliao.ui.bean.SpceColumnBean;
import com.ziran.meiliao.ui.bean.StudyFinishBean;
import com.ziran.meiliao.ui.bean.ZhuanLanBigInBean;
import com.ziran.meiliao.ui.priavteclasses.activity.SubscribeAudioActivity;
import com.ziran.meiliao.ui.priavteclasses.activity.SubscribeVideoActivity;
import com.ziran.meiliao.ui.priavteclasses.activity.ZhuanLanDetailActivity;
import com.ziran.meiliao.ui.priavteclasses.adapter.SJKZhuanLanAdapter;
import com.ziran.meiliao.ui.priavteclasses.bean.ZhuanLanData;
import com.ziran.meiliao.ui.priavteclasses.contract.SJKZhuanLanDetailContract;
import com.ziran.meiliao.ui.priavteclasses.presenter.SJKZhuanLanDetailPresenter;
import com.ziran.meiliao.ui.priavteclasses.util.BuyZhuanLanUtil;
import com.ziran.meiliao.ui.priavteclasses.util.SearchUtil;
import com.ziran.meiliao.ui.priavteclasses.util.ZhuanLanHeadUtil;
import com.ziran.meiliao.ui.settings.activity.RechargeActivity;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.pupop.BuyAlbumPopupWindow;
import com.ziran.meiliao.widget.pupop.DiplomaPopupWindow;
import com.ziran.meiliao.widget.pupop.PayResultPopupWindow;
import com.ziran.meiliao.widget.pupop.PopupWindowUtil;

import java.util.List;
import java.util.Map;

import rx.functions.Action1;


/**
 * 专栏详情Fragment
 */
public class SJKZhuanLanDetailFragment extends CommonRefreshFragment<SJKZhuanLanDetailPresenter, CommonModel> implements
        SJKZhuanLanDetailContract.View {

    private ZhuanLanDetailActivity mZhuanLanDetailActivity;
    private SpceColumnBean.DataBean mDataBean;
    private Map<String, String> stringMap;
    private SJKZhuanLanAdapter sjkZhuanLanAdapter;
    private ZhuanLanHeadUtil mZhuanLanHeadUtil;


    String specColumnId;


    @Override
    protected void initView() {
        super.initView();
        mRxManager.on(AppConstant.RXTag.UPDATE_STUDY_FINISH, new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                if (sjkZhuanLanAdapter != null) sjkZhuanLanAdapter.notifyDataSetChanged();
                if (mZhuanLanHeadUtil != null) {
                    try {
                        ZhuanLanBigInBean.DataBean.DirBean dirBean = WpyxConfig.getZhuanLanData().getDirBean();
                        mZhuanLanHeadUtil.setFooterViewData(dirBean.getTitle(), dirBean.getDuration(), dirBean.getStudyCount(), dirBean
                                .getStudyStatus(),false,false);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        mZhuanLanDetailActivity = (ZhuanLanDetailActivity) getActivity();

        try {
            specColumnId = getActivity().getIntent().getExtras().getString(AppConstant.ExtraKey.SUBSCRIPTION_ID);
        } catch (Exception e) {
            specColumnId = "";
            e.printStackTrace();
        }
        stringMap = MapUtils.getOnlyCan(AppConstant.ExtraKey.SUBSCRIPTION_ID, specColumnId);
        setRecyclerEnabled(false, true);
        iRecyclerView.addOnScrollListener(new MyOnScrollListener(AppConstant.RXTag.HOME_MUSIC_PLANE_SHOW_OR_HIDE));
        mPresenter.postListenUp(ApiKey.SPEC_COLUMN_SEE_UP, stringMap);
        sjkZhuanLanAdapter = new SJKZhuanLanAdapter(getContext(), mDataBean);
        mZhuanLanHeadUtil = new ZhuanLanHeadUtil(getContext(), false);
        mZhuanLanHeadUtil.initHeadView(true);
        mZhuanLanHeadUtil.openCoupon();
        mZhuanLanHeadUtil.openCoupon();
        mZhuanLanHeadUtil.bindFooterView(iRecyclerView, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }, View.VISIBLE);
        mZhuanLanHeadUtil.setTarget(iRecyclerView, sjkZhuanLanAdapter, true);
        mZhuanLanHeadUtil.setFooterOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZhuanLanBigInBean.DataBean.DirBean dirData = mZhuanLanHeadUtil.getDirData();
                startJump(dirData);
            }
        });
        return sjkZhuanLanAdapter;
    }

    @Override
        protected void loadData() {
        mPresenter.getData(ApiKey.SPEC_COLUMN_NATIVE_INDEX, stringMap);
    }


    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        if (mAdapter.getItemViewType(position) == MultiItemRecycleViewAdapter.TYPE_HEAD) return;
        ZhuanLanBigInBean.DataBean.DirBean dirBean = EmptyUtils.parseObject(o);
        sjkZhuanLanAdapter.onItemClick(position);
        mZhuanLanHeadUtil.setCustomStudyFinish();
        if (mZhuanLanHeadUtil.isShowCal()) {
            iRecyclerView.setFooterViewState(true);
        } else {
            startJump(dirBean);
        }

        mZhuanLanHeadUtil.setDirData(dirBean);
        mZhuanLanHeadUtil.setFooterViewData(dirBean.getTitle(), dirBean.getDuration(), dirBean.getStudyCount(), dirBean.getStudyStatus(),
                false,false);
    }

    private boolean startJump(ZhuanLanBigInBean.DataBean.DirBean dirBean) {
        int type = dirBean.getType();

        if (dirBean.getStatus() == 0) {
            showShortToast("该课程尚未开始");
            return true;
        } else if (!dirBean.isSt() && !mDataBean.isBuy()) {
            showShortToast("请订阅后再学习");
            return true;
        }
        getBundle(specColumnId, type, dirBean.isSt(), dirBean.getProgress(), dirBean);
        if (type == 1) {//视频
            SubscribeVideoActivity.startAction(getContext(), specColumnId, dirBean.getTargetId());
        } else if (type == 2) {//音频
            SubscribeAudioActivity.startAction(getContext(), specColumnId, dirBean.getTargetId());
        }
        return false;
    }


    public void gzZhuanLan() {
        if (mDataBean == null) return;
        stringMap.put("gz", mDataBean.isIsGz() ? "false" : "true");
        mPresenter.postGz(ApiKey.SPEC_COLUMN_GZ, stringMap);
    }


    public void share() {
        mZhuanLanHeadUtil.share();
    }

    @Override
    public void showData(SpceColumnBean.DataBean result) {
        mDataBean = result;
        mZhuanLanDetailActivity.setFollow(mDataBean.isIsGz());
        if (mDataBean.isBuy()) {
            mZhuanLanDetailActivity.setPrice("已订阅");
        } else {
            mZhuanLanDetailActivity.setPrice(mDataBean);
        }
        if (mDataBean.getTotalProgress() >= 100 && mDataBean.isGetCertificate() != 1) {
            mPresenter.getCertificate(ApiKey.SUBSCRIPTION_GET_CERTIFICATE, MapUtils.getOnlyCan("subscriptionId", specColumnId));
            subscriptionShareSave();
        }
        mZhuanLanHeadUtil.setHeadData(mDataBean.getPic(), mDataBean.getTitle(), mDataBean.getAuthor().getTeaIntro(), mDataBean.getDetail());
        mZhuanLanHeadUtil.setHeadData2(mDataBean.getBuyCount(), TimeUtil.getStringByFormat(mDataBean.getBeginTime(), "MM.dd"), TimeUtil
                .getStringByFormat(mDataBean.getEndTime(), "MM.dd"), mDataBean.getCourseNumbers());
        mZhuanLanHeadUtil.setProgressFinish(result.getTotalProgress());
//        mZhuanLanHeadUtil.setCoupon("完成系列课程练习并按期打卡转发分享，系统奖励","¥200优惠券","，可通用于5P医学APP组织的所有正念专业课程。");
        mZhuanLanHeadUtil.setCoupon(mDataBean.getFirstWorld(),mDataBean.getSecondWorld(),mDataBean.getThirdWorld());
        mZhuanLanHeadUtil.bindTarget(iRecyclerView);
        mZhuanLanHeadUtil.setShareDataBean(result);

        List<SearchItemBean> beanList = SearchUtil.changeData(mDataBean.getRecMap(), false,false);
        mZhuanLanHeadUtil.setZlList(beanList);
        try {
            if (EmptyUtils.isNotEmpty(mDataBean.getDir())) {
                updateData(mDataBean.getDir());
                setFooterData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setFooterData() {
        if (iRecyclerView != null) iRecyclerView.setFooterViewState(true);
        ZhuanLanBigInBean.DataBean.DirBean dirBean = mDataBean.getDir().get(0);
        mZhuanLanHeadUtil.setDirData(dirBean);
        mZhuanLanHeadUtil.setFooterViewData(dirBean.getTitle(), dirBean.getDuration(), dirBean.getStudyCount(), dirBean
                .getStudyStatus(), dirBean.isSt(), false);
    }


    @Override
    public void showGzResult(Result result) {
        boolean gz = "关注成功".equals(result.getResultMsg());
        mDataBean.setIsGz(gz);
        mZhuanLanDetailActivity.setFollow(gz);
    }

    @Override
    public void showBuySpecColumnResult(Result result) {
        boolean isBuy = "购买成功".equals(result.getResultMsg());
        PayResultPopupWindow payResultPopupWindow = new PayResultPopupWindow(getContext());
        payResultPopupWindow.setResult(isBuy, result.getResultMsg());
        PopupWindowUtil.show(getActivity(), payResultPopupWindow);
        WpyxConfig.getZhuanLanData().setBuy(isBuy);
        mDataBean.setBuy(true);
        mZhuanLanDetailActivity.setBuyState(true);
        mRxManager.post(AppConstant.RXTag.PRACTICE_CAN_SCROLL, true);
    }

    @Override
    public void showCertificate(StudyFinishBean.DataBean bean) {
        DiplomaPopupWindow diplomaPopupWindow = new DiplomaPopupWindow(getContext());
        int price = EmptyUtils.isNotEmpty(bean.getCoursePrice()) ? Integer.parseInt(bean.getCoursePrice()) : 0;
        diplomaPopupWindow.setData(bean,price ,
                mDataBean.getTitle());
        diplomaPopupWindow.show();
    }

    public Bundle getBundle(String specColumnId, int type, boolean isSt, String progress, ZhuanLanBigInBean.DataBean.DirBean dirBean) {
        Bundle bundle = new Bundle();
        ZhuanLanData zhuanLanData = null;
        if (WpyxConfig.getZhuanLanData() == null) {
            zhuanLanData = new ZhuanLanData(type, mDataBean.getUserCoin(), mDataBean.getNeedCoin(), specColumnId, mDataBean.getTitle(),
                    mDataBean.getPic(),mDataBean.getLevelDetail(),mDataBean.getMemberPrice());
            WpyxConfig.setZhuanLanData(zhuanLanData);

        } else {
            zhuanLanData = WpyxConfig.getZhuanLanData();
            zhuanLanData.setType(type);
        }
        zhuanLanData.setUserCoin(mDataBean.getUserCoin());
        zhuanLanData.setNeedCoin(mDataBean.getNeedCoin());
        zhuanLanData.setDirBean(dirBean);
        zhuanLanData.setBuy(mDataBean.isBuy());
        zhuanLanData.setIntro(mDataBean.getTitle());
        zhuanLanData.setProgress(progress);
        return bundle;
    }

    public Bundle getBundle(String specColumnId, int type) {
        return getBundle(specColumnId, type, false, "0%", null);
    }

    public void setBuy(boolean buy) {
        mDataBean.setBuy(buy);
    }

    private BuyZhuanLanUtil mBuyZhuanLanUtil;
    private BuyAlbumPopupWindow.BuyAlbumClickListener mBuyAlbumClickListener;

    public void buy() {
        if (mBuyZhuanLanUtil == null) mBuyZhuanLanUtil = new BuyZhuanLanUtil();
        if (mBuyAlbumClickListener == null) {

            mBuyAlbumClickListener = new BuyAlbumPopupWindow.BuyAlbumClickListener() {
                @Override
                public void buyAlbum(boolean needRecharge) {
                    if (needRecharge) {
                        mRxManager.on(AppConstant.RXTag.BALANCE, new Action1<String>() {
                            @Override
                            public void call(String balance) {
                                mDataBean.setUserCoin(1200);
                            }
                        });
                        RechargeActivity.startAction(getContext(), String.valueOf(WpyxConfig.getZhuanLanData().getUserCoin()));
                    } else {
                        mPresenter.postBuySpecColumn(ApiKey.SPEC_COLUMN_BUY, MapUtils.getOnlyCan(AppConstant.ExtraKey.SUBSCRIPTION_ID,
                                specColumnId));
                    }
                }
            };
        }
        mBuyZhuanLanUtil.showPayV13(getContext(), mBuyAlbumClickListener,mDataBean.getLevelDetail(),mDataBean.getMemberPrice());
    }


    public void subscriptionShareSave() {
        if (mDataBean.getIsShare() == 0) {
            mDataBean.setIsShare(1);
            mPresenter.subscriptionShareSave(ApiKey.SUBSCRIPTION_SUBSCRIPTION_SHARE_SAVE, stringMap);
        }
    }
}
