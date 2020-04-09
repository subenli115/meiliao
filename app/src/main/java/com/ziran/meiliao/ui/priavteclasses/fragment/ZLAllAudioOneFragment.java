package com.ziran.meiliao.ui.priavteclasses.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.MediaAndTextBean;
import com.ziran.meiliao.ui.bean.RecordListBean;
import com.ziran.meiliao.ui.bean.StudyFinishBean;
import com.ziran.meiliao.ui.bean.SubscribeAudioDataBean;
import com.ziran.meiliao.ui.bean.SubscribeCommentListBean;
import com.ziran.meiliao.ui.bean.SubscribeCommentResultBean;
import com.ziran.meiliao.ui.priavteclasses.activity.ZLAudioImageTextActivity;
import com.ziran.meiliao.ui.priavteclasses.adapter.ZLAudioOneAdapter;
import com.ziran.meiliao.ui.priavteclasses.contract.SubscribeVideoContract;
import com.ziran.meiliao.ui.priavteclasses.presenter.SubscribeVideoPresenter;
import com.ziran.meiliao.ui.priavteclasses.util.ZhuanLanSubscribeProfitUtil;
import com.ziran.meiliao.utils.MapUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import rx.functions.Action1;

/**
 * 看图文
 */

public class ZLAllAudioOneFragment extends CommonRefreshFragment<SubscribeVideoPresenter, CommonModel> implements SubscribeVideoContract.View{


    private Map<String, String> apiKeyMap;
    private List<MediaAndTextBean.DataBean> data;
    private Map<String, String> stringMap;
    private ZhuanLanSubscribeProfitUtil mZhuanLanSubscribeProfitUtil;
    private SubscribeAudioDataBean.DataBean mDataBean;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_common_recyclerview;
    }


    private View footerView;
    private RecyclerView flRecyclerView;
    private TextView flTvMore;
    private ImageView flIvEdit;
    @Override
    public CommonRecycleViewAdapter getAdapter() {
        setRecyclerEnabled(false);
        return new ZLAudioOneAdapter(getContext());
    }

    @Override
    protected void initView() {
        super.initView();
        mZhuanLanSubscribeProfitUtil = new ZhuanLanSubscribeProfitUtil(getContext());
        mZhuanLanSubscribeProfitUtil.init();
        apiKeyMap = MapUtils.getOnlyCan(AppConstant.RXTag.AUDIO_ID, "");
        stringMap = MapUtils.getOnlyCan(AppConstant.ExtraKey.SUBSCRIPTION_ID, "");
        mRxManager.on("MediaAndTextBean", new Action1<MediaAndTextBean>() {
            @Override
            public void call(MediaAndTextBean mediaAndTextBean) {
//                iRecyclerView.setFooterView(footerView);
                data = mediaAndTextBean.getData();
                getDataType();
                updateData(data);
            }

        });
        mRxManager.on("notify", new Action1<Boolean>() {
            @Override
            public void call(Boolean mediaAndTextBean) {
                Collections.reverse(mAdapter.getAll());
                mAdapter.notifyDataSetChanged();
            }
        });
        initFooterView();

    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        MediaAndTextBean.DataBean dataBean =  EmptyUtils.parseObject(o);
        RecordListBean.DataBean recordBean = new RecordListBean.DataBean();
        recordBean.setTitle(dataBean.getTitle());
        recordBean.setPic(dataBean.getRoundPic());
        recordBean.setDuration(dataBean.getDuration());
            Bundle bundle = new Bundle();
            bundle.putString("subscriptionId", String.valueOf(dataBean.getSubscriptionId()));
            bundle.putString("targetId", String.valueOf(dataBean.getTargetId()));
            bundle.putString("typeId", String.valueOf(dataBean.getType()));
            bundle.putString("duration", String.valueOf(dataBean.getDuration()));
            if(mDataBean.getPpt()!=null){
                bundle.putStringArrayList("ppt", (ArrayList<String>) mDataBean.getPpt());
            }
            bundle.putParcelable("RecordListBean", recordBean);
            bundle.putString("prefix",mDataBean.getPrefix());
            startActivity(ZLAudioImageTextActivity.class, bundle);
//        }

    }

    private void initFooterView() {
        footerView = LayoutInflater.from(getContext()).inflate(R.layout.inflate_audio_footer, null);
        flIvEdit  = ViewUtil.getView(footerView,R.id.iv_edit_comment);
        flTvMore  = ViewUtil.getView(footerView,R.id.tv_comment_more);
        flRecyclerView  = ViewUtil.getView(footerView,R.id.fl_recycler_view);
    }

    @Override
    protected void loadData() {
//        apiKeyMap.put("page", String.valueOf(page));
//        stringMap.put("page", String.valueOf(page));
//        getDataType();
    }
    public void getDataType() {
        if(data!=null&&data.size()==0){
            mPresenter.getCommentList(ApiKey.GET_COURSE_COMMENT, stringMap);
            return;
        }
        if(data!=null&&data.size()!=0) {
            apiKeyMap.put("audioId", data.get(0).getSubscriptionId()+"");
            stringMap.put("courseId", data.get(0).getTargetId()+"");
            if (data.get(0).getType() == 1) {
                mPresenter.getCommentList(ApiKey.GET_COURSE_COMMENT, stringMap);
                mPresenter.getData(ApiKey.COURSE_GET_DATA, stringMap);
                //视频
            } else {
                //音频
                mPresenter.getCommentList(ApiKey.AUDIO_GET_COMMENT, apiKeyMap);
                mPresenter.getData(ApiKey.AUDIO_GET_DATA, apiKeyMap);
            }
        }
    }


    @Override
    public void showPraiseComment(Result bean) {

    }

    @Override
    public void showCollectResult(Result bean) {

    }

    @Override
    public void showLikeResult(Result bean) {

    }

    @Override
    public void showBuyResult(Result bean) {

    }

    @Override
    public void commentResult(SubscribeCommentResultBean.DataBean bean) {

    }

    @Override
    public void deleteCommentResult(Result bean) {

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
            mZhuanLanSubscribeProfitUtil.bindFooterView(iRecyclerView, "查看全部", data.get(0).getTargetId()+"", "courseId");
        }
    }

    @Override
    public void showData(SubscribeAudioDataBean.DataBean bean) {
        mDataBean = bean;
    }

    @Override
    public void showCertificate(StudyFinishBean.DataBean bean) {

    }

    @Override
    public void showUpdateStudyFinish(Result bean) {

    }
}
