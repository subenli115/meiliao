package com.ziran.meiliao.im.activity.fragment;

import android.os.Bundle;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.im.adapter.CommonAdapter;
import com.ziran.meiliao.im.adapter.CommonNoticeAdapter;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.CommonListBean;
import com.ziran.meiliao.ui.bean.NewsNoticeBean;
import com.ziran.meiliao.utils.MapUtils;

import java.util.List;
import java.util.Map;

import butterknife.Bind;

import static com.ziran.meiliao.constant.ApiKey.ADMIN_NEWSNOTICE_PAGE;


/**
 * Created by Administrator on 2019/1/31.
 */
public class CommonNoticeFragment extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract

        .View<NewsNoticeBean> {
    private static final int REQUEST_CODE_A = 2;
    private String tagId;
    private CommonNoticeAdapter adapter;
    @Bind(R.id.ntb_title)
    NormalTitleBar ntb;
    private String userId;
    private String type;

    @Override
    public void returnData(NewsNoticeBean result) {
        updateData(result.getData().getRecords());
    }



    @Override
    public CommonRecycleViewAdapter getAdapter() {
        loadedTip.setEmptyMsg("什么都没有~",R.mipmap.load_empty_bg);
        adapter = new CommonNoticeAdapter(getContext(), mRxManager,null, new CommonNoticeAdapter.ActivityMultiItemType() {
        },type);
        return adapter;
    }

    @Override
    protected void initView() {
        userId = getIntentExtra(getActivity().getIntent(), "userId");
        type = getIntentExtra(getActivity().getIntent(), "type");
        super.initView();
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_common_list;
    }

    @Override
    protected void initBundle(Bundle extras) {
        try {

        } catch (Exception e) {
        }
    }


    @Override
    protected void loadData() {
        Map<String, String> map = MapUtils.getDefMap(true);
        map.put("noticeUserId", MyAPP.getUserId());
        map.put("current", page + "");
        map.put("type", type+"");
        mPresenter.getData(ADMIN_NEWSNOTICE_PAGE, map, NewsNoticeBean.class);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}