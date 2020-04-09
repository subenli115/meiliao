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
import com.ziran.meiliao.entry.MusicEntry;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.StudyFinishBean;
import com.ziran.meiliao.ui.bean.SubscribeAudioDataBean;
import com.ziran.meiliao.ui.bean.SubscribeCommentListBean;
import com.ziran.meiliao.ui.bean.SubscribeCommentResultBean;
import com.ziran.meiliao.ui.priavteclasses.adapter.SubscribeProfitAdapter;
import com.ziran.meiliao.ui.priavteclasses.contract.SubscribeAudioContract;
import com.ziran.meiliao.ui.priavteclasses.presenter.SubscribeAudioPresenter;
import com.ziran.meiliao.ui.priavteclasses.util.BuyZhuanLanUtil;
import com.ziran.meiliao.ui.priavteclasses.util.ZLStudyUtil;
import com.ziran.meiliao.ui.priavteclasses.util.ZhuanLanSubscribeProfitUtil;
import com.ziran.meiliao.ui.settings.activity.RechargeActivity;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.MusicPanelFloatManager;
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

public class SJKSubscribeAudioProfitFragment extends CommonRefreshFragment<SubscribeAudioPresenter, CommonModel> implements
        SubscribeAudioContract.View {


    private String audioId = "1";
    private String subscriptionId = "1";
    private BuyZhuanLanUtil mBuyZhuanLanUtil;
    private BuyAlbumPopupWindow.BuyAlbumClickListener mBuyAlbumClickListener ;

    private Map<String, String> apiKeyMap;
    private SubscribeAudioDataBean.DataBean mDataBean;
    private ZhuanLanSubscribeProfitUtil mZhuanLanSubscribeProfitUtil;
    public static final int WHAT_COLLECT = 0;
    public static final int WHAT_LIKES = 1;
    public static final int WHAT_SHARE = 2;
    public static final int WHAT_BUY = 3;
    private SharePopupWindow sharePopupWindow;
    private MusicEntry musicEntry;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_subcribe_profit;
    }

    @Override
    protected void initBundle(Bundle extras) {
        subscriptionId = extras.getString(AppConstant.ExtraKey.FROM_ID);
        musicEntry = extras.getParcelable("RecordListBean");
        if(subscriptionId.equals("-1")){
            audioId= this.musicEntry.getMusicId()+"";
        }else{
            audioId = extras.getString(AppConstant.RXTag.AUDIO_ID);
        }

    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        //测试使用1

        loadedTip.setEmptyMsg(getString(R.string.not_subscribe_comment), R.mipmap.ic_empty_nocontent);
        return new SubscribeProfitAdapter(getContext());
    }


    @Override
    protected void initView() {
        super.initView();
        ZLStudyUtil.unRegister();
        mRxManager.on(AppConstant.RXTag.MPS_COMPLETION, new Action1<String>() {
            @Override
            public void call(String aBoolean) {
                MusicPanelFloatManager.getInstance().setProgress(0);
               studyFinish();
            }
        });
        mZhuanLanSubscribeProfitUtil = new ZhuanLanSubscribeProfitUtil(getContext());
        mZhuanLanSubscribeProfitUtil.init();
        apiKeyMap = MapUtils.getOnlyCan(AppConstant.RXTag.AUDIO_ID, audioId);
        if(!subscriptionId.equals("-1")){
            mPresenter.postListenUp(ApiKey.AUDIO_LISTEN_UP, apiKeyMap);
            mPresenter.getData(ApiKey.AUDIO_GET_DATA, apiKeyMap);
        }

    }

    @Override
    protected void loadData() {
        if (headLoad) {
            apiKeyMap.put("page", String.valueOf(page));
            mPresenter.getCommentList(ApiKey.AUDIO_GET_COMMENT, apiKeyMap);
        }


    }


    boolean headLoad = false;

    @Override
    public void showPraiseComment(Result bean) {

    }

    @Override
    public void showCollectResult(Result bean) {
        showShortToast(bean.getResultMsg());
        mDataBean.setIsCollect(!mDataBean.isIsCollect());
//        mDataBean.setIsCollect("收藏成功".equals(bean.getResultMsg()));
        mRxManager.post(AppConstant.RXTag.SUBSCRIBE_UPDATE, HandlerUtil.obj(0,mDataBean.isIsCollect()));
    }

    @Override
    public void showLikeResult(Result bean) {
        showShortToast(bean.getResultMsg());
        mDataBean.setIsLike(!mDataBean.isIsLike());
        mRxManager.post(AppConstant.RXTag.SUBSCRIBE_UPDATE, HandlerUtil.obj(1,mDataBean.isIsLike()));
    }

    @Override
    public void showBuyResult(Result bean) {
        boolean isBuy =true;
        PayResultPopupWindow payResultPopupWindow = new PayResultPopupWindow(getContext());
        payResultPopupWindow.setResult(isBuy,bean.getResultMsg());
        PopupWindowUtil.show(getActivity(), payResultPopupWindow);
        WpyxConfig.getZhuanLanData().setBuy(isBuy);
        mRxManager.post(AppConstant.RXTag.SUBSCRIBE_UPDATE, HandlerUtil.obj(2,isBuy));
    }

    @Override
    public void commentResult(SubscribeCommentResultBean.DataBean bean) {
        page=1;
        apiKeyMap.put("page", String.valueOf(page));
        mAdapter.getPageBean().setRefresh(true);
        mPresenter.getCommentList(ApiKey.AUDIO_GET_COMMENT, apiKeyMap);
    }

    @Override
    public void deleteCommentResult(Result bean) {

    }

    @Override
    public void showCommentList(final List<SubscribeCommentListBean.DataBean> result) {
        List list = new ArrayList();
        if (EmptyUtils.isNotEmpty(result)){
            if (result.size()>3){
                list.addAll(result.subList(0,3));
            }else{
                list.addAll(result);
            }
        }
        if (EmptyUtils.isEmpty(list)){
            list.add(new SubscribeCommentListBean.DataBean());
        }
        updateData(list);
        mZhuanLanSubscribeProfitUtil.setData(mDataBean);
        mZhuanLanSubscribeProfitUtil.bindTarget(iRecyclerView);
        if (result.size()>3){
            mZhuanLanSubscribeProfitUtil.bindFooterView(iRecyclerView,"查看全部",audioId,"audioId");
        }
    }

    @Override
    public void showData(SubscribeAudioDataBean.DataBean bean) {
        try {
            mDataBean = bean;
            mRxManager.post(AppConstant.RXTag.SUBSCRIBE_UPDATE, HandlerUtil.obj(10,bean));

            mRxManager.on(AppConstant.RXTag.SUBSCRIBE_AUDIO_TAG, new Action1<Message>() {
                @Override
                public void call(Message msg) {
                    //执行上传评论请求
                    switch (msg.what) {
                        case AppConstant.RXTag.SUBSCRIBE_AUDIO_TAG_POST_COMMENT:
                            apiKeyMap.put("content", EncodeUtil.encodeUTF(msg.obj.toString()));
                            mPresenter.postComment(ApiKey.AUDIO_COMMENT, apiKeyMap);
                            break;
                        case AppConstant.RXTag.SUBSCRIBE_AUDIO_TAG_POST_PRAISE_COMMENT:
                            SubscribeCommentListBean.DataBean dataBean = (SubscribeCommentListBean.DataBean) msg.obj;
                            apiKeyMap.put("commentId", String.valueOf(dataBean.getId()));
                            apiKeyMap.put("praise", dataBean.isPraise() ? "false" : "true");
                            dataBean.setPraise(!dataBean.isPraise());
                            mPresenter.postPriaseComment(ApiKey.AUDIO_PRAISE_COMMENT, apiKeyMap);
                            break;
                    }
                }
            });
            headLoad = true;
            apiKeyMap.put("page", String.valueOf(page));
            mPresenter.getCommentList(ApiKey.AUDIO_GET_COMMENT, apiKeyMap);
            if (ZLStudyUtil.isStudyFinish()  ){
                studyFinish();
                ZLStudyUtil.setIsStudyFinish(false);
            }
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
    public void showStudyFinish(Result bean) {

    }


    public void doWhat(int what) {
        if (mDataBean == null) return;
        switch (what) {
            case WHAT_COLLECT:
//                apiKeyMap.put("collect", mDataBean.isIsCollect() ? "false" : "true");
                apiKeyMap.put("subscriptionId",subscriptionId);
                apiKeyMap.put("targetId",audioId);
                apiKeyMap.put("collect", mDataBean.isIsCollect() ?"0":"1");
                mPresenter.postCollect(ApiKey.SUBSCRIPTION_COLLECT_SUBSCRIPTION, apiKeyMap);
                break;
            case WHAT_LIKES:
                apiKeyMap.put("like", mDataBean.isIsLike() ? "false" : "true");
                mPresenter.postLike(ApiKey.AUDIO_LIKE, apiKeyMap);
                break;
            case WHAT_SHARE:
                SharePopupWindow.showPopup(getActivity(),sharePopupWindow,mDataBean);
                break;
            case WHAT_BUY:
                if (mBuyZhuanLanUtil==null) mBuyZhuanLanUtil=new BuyZhuanLanUtil();
                if (mBuyAlbumClickListener==null){
                    mBuyAlbumClickListener = new BuyAlbumPopupWindow.BuyAlbumClickListener() {
                        @Override
                        public void buyAlbum(boolean needRecharge) {
                            if (needRecharge) {
                                mRxManager.on(AppConstant.RXTag.BALANCE, new Action1<String>() {
                                    @Override
                                    public void call(String balance) {
                                        try {
                                            WpyxConfig.getZhuanLanData().setUserCoin(Integer.parseInt(balance));
                                            mBuyZhuanLanUtil.setUserCoin(Integer.parseInt(balance));
                                        }catch (Exception e){e.printStackTrace();}

                                    }
                                });
                                RechargeActivity.startAction(getContext(), String.valueOf(WpyxConfig.getZhuanLanData().getUserCoin()));
                            } else {
                                mPresenter.postBuy(ApiKey.SPEC_COLUMN_BUY, MapUtils.getOnlyCan(AppConstant.ExtraKey.SUBSCRIPTION_ID, WpyxConfig.getZhuanLanData().getSpecColumnId()));
                            }
                        }
                    };
                }

                mBuyZhuanLanUtil.showPayV13(getContext(),mBuyAlbumClickListener,mDataBean.getSubscription().getLevelDetail(),mDataBean.getSubscription().getMemberPrice());
                break;
        }
    }



    public void studyFinish(){
        if (WpyxConfig.getZhuanLanData()!=null){
            String progress = WpyxConfig.getZhuanLanData().getProgress();
            if (!"100".equals(progress)){
                Map<String, String> stringMap = MapUtils.getOnlyCan("subscriptionId", ZLStudyUtil.getSubscriptionId());
                stringMap.put("targetId",audioId);
                stringMap.put("audioId",audioId);
                stringMap.put("progress","100");
                mPresenter.getCertificate(ApiKey.SUBSCRIPTION_GET_SINGLE_COURSE_SHARE,stringMap);
                //调用学习进度上传
                mPresenter.updateStudyFinish(ApiKey.AUDIO_UPDATE_STUDY,stringMap);
            }
        }
    }

    public void play() {
        ZLStudyUtil.setAudioId(audioId);
        if(subscriptionId.equals("-1")){
            MusicPanelFloatManager.getInstance().setData(musicEntry.getName(),musicEntry.getDuration(),musicEntry.getSharePic(),musicEntry.getFilePathVideo(),musicEntry.getMusicId()+"",true);
        }else{
            MusicPanelFloatManager.getInstance().setData(mDataBean.getTitle(),mDataBean.getDuration(),mDataBean.getRoundPic(),mDataBean.getAESUrl(),audioId,true);
            try {
                ZLStudyUtil.setIntro(WpyxConfig.getZhuanLanData().getIntro());
                ZLStudyUtil.setSubscriptionId(WpyxConfig.getZhuanLanData().getSpecColumnId());
            }catch (Exception e){e.printStackTrace();}
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ZLStudyUtil.register();
    }
}
