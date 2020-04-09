package com.ziran.meiliao.ui.priavteclasses.fragment;

import android.os.Bundle;
import android.os.Message;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.WpyxConfig;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.common.security.EncodeUtil;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.StudyFinishBean;
import com.ziran.meiliao.ui.bean.SubscribeAudioDataBean;
import com.ziran.meiliao.ui.bean.SubscribeCommentListBean;
import com.ziran.meiliao.ui.bean.SubscribeCommentResultBean;
import com.ziran.meiliao.ui.priavteclasses.adapter.SubscribeProfitAdapter;
import com.ziran.meiliao.ui.priavteclasses.contract.SubscribeVideoContract;
import com.ziran.meiliao.ui.priavteclasses.presenter.SubscribeVideoPresenter;
import com.ziran.meiliao.ui.priavteclasses.util.BuyZhuanLanUtil;
import com.ziran.meiliao.ui.priavteclasses.util.ZhuanLanSubscribeProfitUtil;
import com.ziran.meiliao.ui.settings.activity.RechargeActivity;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.pupop.BuyAlbumPopupWindow;
import com.ziran.meiliao.widget.pupop.PayResultPopupWindow;
import com.ziran.meiliao.widget.pupop.PopupWindowUtil;
import com.ziran.meiliao.widget.pupop.SharePopupWindow;
import com.ziran.meiliao.widget.pupop.StudyFinishPopupWindow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.functions.Action1;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/7 14:00
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/8/7$
 * @updateDes ${TODO}
 */

public class SJKSubscribeVideoProfitFragment extends CommonRefreshFragment<SubscribeVideoPresenter, CommonModel> implements
        SubscribeVideoContract.View {

    private BuyZhuanLanUtil mBuyZhuanLanUtil;
    private BuyAlbumPopupWindow.BuyAlbumClickListener mBuyAlbumClickListener;
    private ZhuanLanSubscribeProfitUtil mZhuanLanSubscribeProfitUtil;
    private SubscribeAudioDataBean.DataBean mDataBean;
    private Map<String, String> apiKeyMap;
    public static final int WHAT_COLLECT = 0;
    public static final int WHAT_LIKES = 1;
    public static final int WHAT_SHARE = 2;
    public static final int WHAT_BUY = 3;
    private SharePopupWindow sharePopupWindow;
    boolean headLoad = false;
    private String targetId;
    private String subscriptionId;


    @Override
    protected void initBundle(Bundle extras) {
        targetId = extras.getString(AppConstant.RXTag.AUDIO_ID);
        subscriptionId = extras.getString(AppConstant.ExtraKey.FROM_ID);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_subcribe_profit;
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        //测试使用1
        loadedTip.setEmptyMsg(getString(R.string.not_subscribe), R.mipmap.ic_empty_nocontent);
        return new SubscribeProfitAdapter(getContext());
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    protected void initView() {
        super.initView();
        mZhuanLanSubscribeProfitUtil = new ZhuanLanSubscribeProfitUtil(getContext());
        mZhuanLanSubscribeProfitUtil.init();
        apiKeyMap = MapUtils.getOnlyCan("courseIds", targetId);
        apiKeyMap.put("courseId", targetId);
        mPresenter.postListenUp(ApiKey.watchUp, apiKeyMap);
        mPresenter.getData(ApiKey.COURSE_GET_DATA, apiKeyMap);
    }

    public void updateStudyFinish() {
        Map<String, String> stringMap = MapUtils.getOnlyCan("subscriptionId", subscriptionId);
        stringMap.put("targetId", targetId);
        stringMap.put("courseId", targetId);
        stringMap.put("progress", "100");
        mPresenter.getCertificate(ApiKey.SUBSCRIPTION_GET_SINGLE_COURSE_SHARE, stringMap);
        mPresenter.updateStudyFinish(ApiKey.COURSE_UPDATE_STUDY, stringMap);
    }

    @Override
    protected void loadData() {
        if (headLoad) {
            apiKeyMap.put("page", String.valueOf(page));
            mPresenter.getCommentList(ApiKey.GET_COURSE_COMMENT, apiKeyMap);
        }
    }

    @Override
    public void showPraiseComment(Result bean) {

    }

    @Override
    public void showCollectResult(Result bean) {
        showShortToast(bean.getResultMsg());
        mDataBean.setIsCollect(!mDataBean.isIsCollect());
        mRxManager.post(AppConstant.RXTag.SUBSCRIBE_UPDATE, HandlerUtil.obj(0, mDataBean.isIsCollect()));
    }

    @Override
    public void showLikeResult(Result bean) {
        showShortToast(bean.getResultMsg());
        mDataBean.setIsLike(!mDataBean.isIsLike());
        mRxManager.post(AppConstant.RXTag.SUBSCRIBE_UPDATE, HandlerUtil.obj(1, mDataBean.isIsLike()));
    }

    @Override
    public void showBuyResult(Result bean) {
        PayResultPopupWindow payResultPopupWindow = new PayResultPopupWindow(getContext());
        payResultPopupWindow.setResult(true);
        PopupWindowUtil.show(getActivity(), payResultPopupWindow);
        WpyxConfig.getZhuanLanData().setBuy(true);
        mRxManager.post(AppConstant.RXTag.SUBSCRIBE_UPDATE, HandlerUtil.obj(2, true));
    }

    @Override
    public void commentResult(SubscribeCommentResultBean.DataBean bean) {
        page = 1;
        apiKeyMap.put("page", String.valueOf(page));
        mAdapter.getPageBean().setRefresh(true);
        mPresenter.getCommentList(ApiKey.GET_COURSE_COMMENT, apiKeyMap);
    }

    @Override
    public void deleteCommentResult(Result bean) {

    }

    @Override
    public void showCommentList(final List<SubscribeCommentListBean.DataBean> result) {
        List list = new ArrayList();
        if (EmptyUtils.isNotEmpty(result)) {
            if (result.size() > 3) {
                list.addAll(result.subList(0, 3));
            } else {
                list.addAll(result);
            }
        }
        if (EmptyUtils.isEmpty(list)) {
            list.add(new SubscribeCommentListBean.DataBean());
        }
        updateData(list);
        mZhuanLanSubscribeProfitUtil.setData(mDataBean);
        mZhuanLanSubscribeProfitUtil.bindTarget(iRecyclerView);
        if (result.size() > 3) {
            mZhuanLanSubscribeProfitUtil.bindFooterView(iRecyclerView, "查看全部", targetId, "courseId");
        }
    }

    @Override
    public void showData(SubscribeAudioDataBean.DataBean bean) {
        try {
            mDataBean = bean;
            mRxManager.post(AppConstant.RXTag.SUBSCRIBE_UPDATE, HandlerUtil.obj(10, bean));
            mRxManager.on(AppConstant.RXTag.SUBSCRIBE_AUDIO_TAG, new Action1<Message>() {
                @Override
                public void call(Message msg) {
                    //执行上传评论请求
                    switch (msg.what) {
                        case AppConstant.RXTag.SUBSCRIBE_AUDIO_TAG_POST_COMMENT:
                            apiKeyMap.put("content", EncodeUtil.encodeUTF(msg.obj.toString()));
                            mPresenter.postComment(ApiKey.POST_COURSE_COMMENT, apiKeyMap);
                            break;
                        case AppConstant.RXTag.SUBSCRIBE_AUDIO_TAG_POST_PRAISE_COMMENT:
                            SubscribeCommentListBean.DataBean dataBean = (SubscribeCommentListBean.DataBean) msg.obj;
                            apiKeyMap.put("commentId", String.valueOf(dataBean.getId()));
                            apiKeyMap.put("praise", dataBean.isPraise() ? "false" : "true");
                            dataBean.setPraise(!dataBean.isPraise());
                            mPresenter.postPriaseComment(ApiKey.COURSE_PRAISE_COMMENT, apiKeyMap);
                            break;
                    }
                }
            });
            headLoad = true;
            apiKeyMap.put("page", String.valueOf(page));
            mPresenter.getCommentList(ApiKey.GET_COURSE_COMMENT, apiKeyMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showCertificate(StudyFinishBean.DataBean bean) {
        StudyFinishPopupWindow finishPopupWindow = new StudyFinishPopupWindow(getContext());
        finishPopupWindow.setData(bean);
    }

    @Override
    public void showUpdateStudyFinish(Result result) {

//        LogUtils.logd("result:"+result);A
    }

    public void doWhat(int what) {
        if (mDataBean == null) return;
        switch (what) {
            case WHAT_COLLECT:
                apiKeyMap.put("subscriptionId",subscriptionId);
                apiKeyMap.put("targetId",targetId);
                apiKeyMap.put("collect", mDataBean.isIsCollect() ?"0":"1");
                mPresenter.postCollect(ApiKey.SUBSCRIPTION_COLLECT_SUBSCRIPTION, apiKeyMap);
//                apiKeyMap.put("collect", mDataBean.isIsCollect() ? "false" : "true");
//                mPresenter.postCollect(ApiKey.COLLECT_COURSE, apiKeyMap);
                break;
            case WHAT_LIKES:
                apiKeyMap.put("like", mDataBean.isIsLike() ? "false" : "true");
                mPresenter.postLike(ApiKey.LIKE_COURSE, apiKeyMap);
                break;
            case WHAT_SHARE:
                SharePopupWindow.showPopup(getActivity(), sharePopupWindow, mDataBean);
                break;
            case WHAT_BUY:
                if (mBuyZhuanLanUtil == null) mBuyZhuanLanUtil = new BuyZhuanLanUtil();
                if (mBuyAlbumClickListener == null) {
                    mBuyAlbumClickListener = new BuyAlbumPopupWindow.BuyAlbumClickListener() {
                        @Override
                        public void buyAlbum(boolean needRecharge) {
                            if (needRecharge) {
                                mRxManager.on(AppConstant.RXTag.BALANCE, new Action1<String>() {
                                    @Override
                                    public void call(String balance) {
//                    tvWalletBalance.setText(balance);
                                        try {
                                            mBuyZhuanLanUtil.setUserCoin(Integer.parseInt(balance));
                                            WpyxConfig.getZhuanLanData().setUserCoin(Integer.parseInt(balance));
                                        }catch (Exception e){e.printStackTrace();}
                                    }
                                });
                                RechargeActivity.startAction(getContext(), String.valueOf(WpyxConfig.getZhuanLanData().getUserCoin()));
                            } else {
                                mPresenter.postBuy(ApiKey.SPEC_COLUMN_BUY, MapUtils.getOnlyCan(AppConstant.ExtraKey.SUBSCRIPTION_ID,
                                        WpyxConfig.getZhuanLanData().getSpecColumnId()));
                            }
                        }
                    };
                }

                mBuyZhuanLanUtil.showPayV13(getContext(), mBuyAlbumClickListener,mDataBean.getSubscription().getLevelDetail(),mDataBean.getSubscription().getMemberPrice());
                break;
        }
    }

}
