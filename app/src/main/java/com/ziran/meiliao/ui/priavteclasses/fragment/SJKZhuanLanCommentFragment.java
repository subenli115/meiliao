package com.ziran.meiliao.ui.priavteclasses.fragment;

import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonutils.KeyBordUtil;
import com.ziran.meiliao.common.commonutils.TimeUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.common.security.EncodeUtil;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.SubscribeCommentListBean;
import com.ziran.meiliao.ui.bean.SubscribeCommentResultBean;
import com.ziran.meiliao.ui.priavteclasses.adapter.ZhuanLanCommentAdapter;
import com.ziran.meiliao.ui.priavteclasses.contract.ZhuanLanCommentContract;
import com.ziran.meiliao.ui.priavteclasses.presenter.ZhuanLanCommentPresenter;
import com.ziran.meiliao.utils.MapUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * 评论Fragment
 * Created by Administrator on 2017/1/7.
 */

public class SJKZhuanLanCommentFragment extends CommonRefreshFragment<ZhuanLanCommentPresenter, CommonModel> implements
        ZhuanLanCommentContract.View {
    String apiUrl;

    @Bind(R.id.input_editor)
    EditText etContent;
    @Bind(R.id.iv_sjk_fulllive_send)
    ImageView ivSend;
    private boolean isAudio;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_sjk_zhuanlan_comment;
    }

    @Override
    protected void initOther() {
        super.initOther();
        String targetId = getIntentExtra(AppConstant.ExtraKey.TARGET_ID);
        String targetKey = getIntentExtra(AppConstant.ExtraKey.TARGET_KEY);
        isAudio = "audioId".equals(targetKey);
        apiUrl = isAudio ? ApiKey.AUDIO_GET_COMMENT : ApiKey.GET_COURSE_COMMENT;
        apiKeyMap = MapUtils.getOnlyCan(targetKey, targetId);
        ivSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendComment();
            }
        });
        etContent.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (event.getAction()==KeyEvent.KEYCODE_ENTER){
                    sendComment();
                }
                return true;
            }
        });
    }

    private void sendComment() {
        String content = etContent.getText().toString().trim();
        if (EmptyUtils.isEmpty(content)) {
            showShortToast("还没有输入内容哦");
        } else {
            postCommentData = new SubscribeCommentListBean.DataBean();
            postCommentData.setHeadImg(MyAPP.getUserInfo().getHeadImg());
            postCommentData.setIsOwn(true);
            postCommentData.setNickName(MyAPP.getUserInfo().getNickName());
            postCommentData.setContent(EncodeUtil.encodeUTF(content));
            postCommentData.setCreateTime(TimeUtil.getfriendlyTime(System.currentTimeMillis()));
            apiKeyMap.put("content", postCommentData.getContent());
            mPresenter.postComment(isAudio ? ApiKey.AUDIO_COMMENT : ApiKey.POST_COURSE_COMMENT, apiKeyMap);
        }
    }


    @Override
    public CommonRecycleViewAdapter getAdapter() {
        return new ZhuanLanCommentAdapter(getContext());
    }


    private Map<String, String> apiKeyMap;

    @Override
    protected void loadData() {
        apiKeyMap.put("page", String.valueOf(page));
        if (page == 1) {
            apiKeyMap.put("own", "1");
            mPresenter.getCommentByMe(apiUrl, apiKeyMap);
        } else {
            apiKeyMap.put("own", "0");
            mPresenter.getCommentByTarget(apiUrl, apiKeyMap);
        }
    }


    @Override
    public void showCommentToMe(List<SubscribeCommentListBean.DataBean> result) {
        List list = new ArrayList();
        list.add("与我相关");
        list.addAll(result);
        list.add("全部评论");
        mAdapter.addAllAt(0, list);
        mAdapter.getPageBean().setRefresh(false);
        apiKeyMap.put("own", "0");
        mPresenter.getCommentByTarget(apiUrl, apiKeyMap);
    }

    private SubscribeCommentListBean.DataBean postCommentData;

    @Override
    public void showCommentToTarget(List<SubscribeCommentListBean.DataBean> result) {
        updateData(result);
    }

    @Override
    public void showCommentResult(SubscribeCommentResultBean.DataBean bean) {
        if (postCommentData != null) {
            postCommentData.setId(bean.getId());
            mAdapter.addAt(1, postCommentData);
            etContent.setText("");
            KeyBordUtil.hideSoftKeyboard(etContent);
        }
    }
}
