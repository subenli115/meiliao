package com.ziran.meiliao.ui.priavteclasses.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.webkit.WebView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.commonwidget.OnNoDoubleClickListener;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.common.security.AES;
import com.ziran.meiliao.common.security.EncodeUtil;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.entry.MusicEntry;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.MediaDetailBean;
import com.ziran.meiliao.ui.bean.RecordListBean;
import com.ziran.meiliao.ui.bean.StudyFinishBean;
import com.ziran.meiliao.ui.bean.SubscribeAudioDataBean;
import com.ziran.meiliao.ui.bean.SubscribeCommentListBean;
import com.ziran.meiliao.ui.bean.SubscribeCommentResultBean;
import com.ziran.meiliao.ui.priavteclasses.adapter.SubscribeProfitAdapter;
import com.ziran.meiliao.ui.priavteclasses.contract.ZLTextImageContract;
import com.ziran.meiliao.ui.priavteclasses.presenter.ZLTextImagePresenter;
import com.ziran.meiliao.ui.priavteclasses.util.BuyZhuanLanUtil;
import com.ziran.meiliao.ui.priavteclasses.util.ZLImageTextHeadUtil;
import com.ziran.meiliao.ui.priavteclasses.util.ZhuanLanSubscribeProfitUtil;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.MusicPanelFloatManager;
import com.ziran.meiliao.utils.StringUtils;
import com.ziran.meiliao.utils.WpyxDownloadUtil;
import com.ziran.meiliao.widget.ARewardV1View;
import com.ziran.meiliao.widget.CustomMusicPanelNewView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import rx.functions.Action1;

/**
 * 专栏已订阅Fragment
 */

public class ZLAudioImageTextFragment extends CommonRefreshFragment<ZLTextImagePresenter, CommonModel> implements ZLTextImageContract.View, View.OnClickListener, WpyxDownloadUtil.onDownloadListener {

    private String subscriptionId;
    private String targetId;
    private String typeId;
    RecordListBean.DataBean recordBean;
    @Bind(R.id.aRewardView)
    ARewardV1View mARewardV1View;
    @Bind(R.id.ntb)
    NormalTitleBar ntb;
    private MapUtils mApiKeyMap;
    private Map<String, String> apiKeyMap;
    private String duration;
    @Bind(R.id.musicPanelView)
    CustomMusicPanelNewView mMusicPanelView;
    private ZhuanLanSubscribeProfitUtil mZhuanLanSubscribeProfitUtil;
    private EditCommentFragment mEditCommentFragment;
    boolean editCommentFragmentShowOrHide = false;
    private SubscribeAudioDataBean mDataBean;
    boolean headLoad = false;
    private ArrayList<String> ppt;
    private String prefix;
    private WebView webView;
    private String courseHtml;
    private MusicEntry musicEntry;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_zl_audio_image_text;
    }

    @Override
    protected void initBundle(Bundle extras) {
        recordBean = extras.getParcelable("RecordListBean");
        subscriptionId = extras.getString("subscriptionId");
        targetId = extras.getString("targetId");
        typeId = extras.getString("typeId");
        courseHtml = extras.getString("courseHtml");
        duration = extras.getString("duration");
        prefix = extras.getString("prefix");
//             ppt = extras.getStringArrayList("ppt");
        mStringMap = MapUtils.getDefMap(true);
        mStringMap.put("subscriptionId", subscriptionId);
        mStringMap.put("targetId", targetId);
        mStringMap.put("typeId", typeId);
        mStringMap.put("courseId", subscriptionId);

    }

    Map<String, String> mStringMap;
    private ZLImageTextHeadUtil mZLImageTextHeadUtil;





    @Override
    protected void initOther() {
        mZLImageTextHeadUtil = new ZLImageTextHeadUtil(getContext());
        mZhuanLanSubscribeProfitUtil = new ZhuanLanSubscribeProfitUtil(getContext());
        mZhuanLanSubscribeProfitUtil.init();
        ntb.setOnRightImagListener(this);
        ntb.setOnRightImag2Listener(this);
        ntb.setOnRightImag3Listener(this);
        setRecyclerEnabled(false);
        iRecyclerView.setHeadView(mZLImageTextHeadUtil.getHeadView());
        mZLImageTextHeadUtil.setOnClickListener(this);
        mZLImageTextHeadUtil.initWebView(courseHtml);
        mPresenter.postMediaDetail(ApiKey.SUBSCRIPTION_MEDIA_DETAIL, mStringMap);
        if(typeId.equals("1")){
            mZLImageTextHeadUtil.setVideo();
            apiKeyMap = MapUtils.getOnlyCan("courseIds", targetId);
            apiKeyMap.put("courseId", targetId);
            apiKeyMap.put("page", String.valueOf(page));
            mPresenter.getCommentList(ApiKey.GET_COURSE_COMMENT, apiKeyMap);
        }else{
            apiKeyMap = MapUtils.getOnlyCan(AppConstant.RXTag.AUDIO_ID, "");
            apiKeyMap.put("audioId", targetId);
            apiKeyMap.put("page", String.valueOf(page));
            mPresenter.getCommentList(ApiKey.AUDIO_GET_COMMENT, apiKeyMap);
        }
        mARewardV1View.setOnClick(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                buy();
            }
        });
        mRxManager.on(AppConstant.RXTag.SUBSCRIBE_COMMENT_FRAGMENT_SHOW_OR_HIDE, new Action1<Boolean>() {
            @Override
            public void call(Boolean showOrHide) {
                editCommentFragmentShowOrHide = showOrHide;
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                if (showOrHide) {
                    if (mEditCommentFragment == null) {
                        mEditCommentFragment = new EditCommentFragment();
                        ft.add(R.id.frameLayout, mEditCommentFragment, "EditCommentFragment");
                    } else {
                        ft.show(mEditCommentFragment);
                    }
                } else {
                    ft.hide(mEditCommentFragment);
                }
                ft.commit();
            }
        });


    }


    @Override
    public CommonRecycleViewAdapter getAdapter() {
        return new SubscribeProfitAdapter(getContext());
    }
    @Override
    protected void loadData() {

    }




    private MediaDetailBean.DataBean mediaTextData;
    private String musicUrl;

    @Override
    public void showMediaDetail(final MediaDetailBean.DataBean result) {
        mediaTextData = result;
        musicUrl = AES.get().decrypt(mediaTextData.getUrl()).trim();
        mZLImageTextHeadUtil.changeState(musicUrl);
        mRxManager.post(AppConstant.RXTag.SUBSCRIBE_UPDATE, HandlerUtil.obj(10, result));
        mRxManager.on(AppConstant.RXTag.SUBSCRIBE_AUDIO_TAG, new Action1<Message>() {
            @Override
            public void call(Message msg) {
                //执行上传评论请求
                switch (msg.what) {
                    case AppConstant.RXTag.SUBSCRIBE_AUDIO_TAG_POST_COMMENT:
                        apiKeyMap.put("content", EncodeUtil.encodeUTF(msg.obj.toString()));
                            if(typeId.equals("1")){
                                mPresenter.postComment(ApiKey.POST_COURSE_COMMENT, apiKeyMap);
                            }else {
                                mPresenter.postComment(ApiKey.AUDIO_COMMENT, apiKeyMap);

                            }
                        break;
                    case AppConstant.RXTag.SUBSCRIBE_AUDIO_TAG_POST_PRAISE_COMMENT:
                        SubscribeCommentListBean.DataBean dataBean = (SubscribeCommentListBean.DataBean) msg.obj;
                        apiKeyMap.put("commentId", String.valueOf(dataBean.getId()));
                        apiKeyMap.put("praise", dataBean.isPraise() ? "false" : "true");
                        dataBean.setPraise(!dataBean.isPraise());
                        if(typeId.equals("1")){
                            mPresenter.postPriaseComment(ApiKey.COURSE_PRAISE_COMMENT, apiKeyMap);
                        }else {
                            mPresenter.postLike(ApiKey.AUDIO_PRAISE_COMMENT, apiKeyMap);

                        }

                        break;
                }
            }
        });
        musicUrl = AES.get().decrypt(result.getUrl()).trim();


        mZLImageTextHeadUtil.setData(result, recordBean,(ArrayList<String>) result.getPpt(),prefix);

        if (!result.isBuy()) {
            mARewardV1View.setParmes(0, 0);
            String text = StringUtils.format("订阅 ( ¥%d )", mediaTextData.getBuyCount());
            mARewardV1View.setPrice(text);
        }
    }

    @Override
    public void showBuySpecColumnResult(Result result) {

    }
    @Override
    public void showCommentList(List<SubscribeCommentListBean.DataBean> result) {
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
        mZhuanLanSubscribeProfitUtil.bindTarget(iRecyclerView);
        if (result.size() > 3) {
            if(typeId.equals("1")){
                mZhuanLanSubscribeProfitUtil.bindFooterView(iRecyclerView, "查看全部", targetId, "courseId");
            }else{

                mZhuanLanSubscribeProfitUtil.bindFooterView(iRecyclerView, "查看全部", targetId, "audioId");
            }
        }
    }

    @Override
    public void showCollectResult(Result bean) {
        showShortToast(bean.getResultMsg());
        mediaTextData.setCollect(!mediaTextData.isCollect());
    }

    @Override
    public void showLikeResult(Result bean) {
        showShortToast(bean.getResultMsg());
        mediaTextData.setLike(!mediaTextData.isLike());
        mRxManager.post(AppConstant.RXTag.SUBSCRIBE_UPDATE, HandlerUtil.obj(1, mediaTextData.isLike()));
    }

    @Override
    public void commentResult(SubscribeCommentResultBean.DataBean bean) {
        page = 1;
        apiKeyMap.put("page", String.valueOf(page));
        mAdapter.getPageBean().setRefresh(true);
        if(typeId.equals("1")){
            mPresenter.getCommentList(ApiKey.GET_COURSE_COMMENT, apiKeyMap);
        }else{
            mPresenter.getCommentList(ApiKey.AUDIO_GET_COMMENT, apiKeyMap);
        }
    }

    @Override
    public void showCertificate(StudyFinishBean.DataBean bean) {

    }


    @Override
    public void showUpdateStudyFinish(Result bean) {

    }

    @Override
    public void onResume() {
        MusicPanelFloatManager.getInstance().updatePlayState();
        super.onResume();
         musicEntry=new MusicEntry();
        musicEntry.setName(recordBean.getTitle());
        if (new File(musicEntry.getFilePathZl()).exists()) {
            mZLImageTextHeadUtil.mIvZlDownload.setImageResource(R.mipmap.ic_jyg_player_download_finish);

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_headview_back:
                finish();
                break;
            case R.id.iv_headview_music_panel_zl_download:
                musicEntry.setUrl(mediaTextData.getUrl());
                WpyxDownloadUtil.get().downMusic(getContext(),this, subscriptionId+"", musicEntry,false,true);
                break;
            case R.id.playPauseView:
                MyAPP.mServiceManager.playUrl(musicUrl);
                MusicPanelFloatManager.getInstance().setData(recordBean.getTitle(),duration,recordBean.getPic(),musicUrl,targetId+"",true);
                new Handler().postDelayed(new Runnable(){

                    public void run() {
                        mZLImageTextHeadUtil.changeState(musicUrl);
                    }

                }, 600);
                break;
            case R.id.id_right_img1:
                mStringMap.put("like", mediaTextData.isLike() ? "false" : "true");
                mPresenter.postLike(ApiKey.AUDIO_LIKE, mStringMap);
                break;
            case R.id.id_right_img2:
                mStringMap.put("targetId", targetId);
                mStringMap.put("collect", mediaTextData.isCollect() ? "0" : "1");
                mPresenter.postCollect(ApiKey.SUBSCRIPTION_COLLECT_SUBSCRIPTION, mStringMap);
                break;
            case R.id.id_right_img3:
                break;
        }
    }



    private BuyZhuanLanUtil mBuyZhuanLanUtil;

    public void buy() {
        if (mBuyZhuanLanUtil == null) mBuyZhuanLanUtil = new BuyZhuanLanUtil();
        mBuyZhuanLanUtil.buy(getContext(), mPresenter, mediaTextData.getUserCoin(),
                mediaTextData.getBuyCount(), targetId, recordBean.getTitle(), recordBean.getPic(), mediaTextData.isBuy());
    }

    @Override
    public void onFinish(String url, File file) {
        mZLImageTextHeadUtil.mIvZlDownload.setImageResource(R.mipmap.ic_jyg_player_download_finish);
    }

    @Override
    public void onDownStart(String url) {

    }
}
