package com.ziran.meiliao.ui.priavteclasses.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.KeyBordUtil;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.LiveCallBack;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.SJKCommentBean;
import com.ziran.meiliao.ui.priavteclasses.adapter.SJKLiveDetailCommentAdapter;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.MapUtils;

import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 私家课-直播详情-简介Fragment
 * Created by Administrator on 2017/1/7.
 */

public class SJKLiveDetailCommentFragment extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract
        .ActionView<SJKCommentBean,Result> {

    String courseId;
    @Bind(R.id.layout_container)
    View lLayoutContainer;
    @Bind(R.id.input_editor)
    EditText inputEditor;

    LiveCallBack mLiveCallBack;

    private String content;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_sjk_live_detail_comment;
    }

    @Override
    protected void initView() {
        super.initView();
        if (getActivity() instanceof LiveCallBack) {
            mLiveCallBack = (LiveCallBack) getActivity();
        }
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        courseId = getIntentExtra(getActivity().getIntent(), AppConstant.SPKey.COURSE_ID);
        if (loadedTip != null) loadedTip.setEmptyMsg(getString(R.string.emtry_sjk_comment), R.mipmap.ic_empty_message);
        return new SJKLiveDetailCommentAdapter(getContext(), R.layout.item_sjk_live_detail_comment);
    }
    @OnClick(R.id.tv_sjk_fulllive_send)
    public void sendMessage(View view){
        String text = inputEditor.getText().toString();
        if (!CheckUtil.check(getContext(), getView())) return;
        if (TextUtils.isEmpty(text)) {
            showShortToast(getString(R.string.not_content));
        } else {
            inputEditor.setText("");
            content = text;
            mPresenter.postAction(ApiKey.POST_COURSE_COMMENT, MapUtils.getCourseCommentMap(text, courseId), Result.class);
            KeyBordUtil.hideSoftKeyboard(inputEditor);
        }
    }

    @Override
    protected void loadData() {
        Map<String, String> map = MapUtils.getSJKLiveComment(courseId, page);
        mPresenter.postData(ApiKey.GET_COURSE_COMMENT, map, SJKCommentBean.class);
    }

    @Override
    public void returnAction(Result result) {
        mAdapter.addAt(0, SJKCommentBean.postComment(content));
        mAdapter.notifyItemChanged(0, 2);
        content = "";
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
        if (isVisible) {
            if (mLiveCallBack != null && lLayoutContainer != null) {
                lLayoutContainer.setVisibility(mLiveCallBack.isBuyCourse() ? View.VISIBLE : View.GONE);
            }
        }
    }

    @Override
    public void returnData(SJKCommentBean result) {
        updateData(result.getData());
    }

    @Override
    public void showEmtry() {
    }
}
