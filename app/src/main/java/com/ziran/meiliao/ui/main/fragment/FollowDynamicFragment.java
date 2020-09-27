package com.ziran.meiliao.ui.main.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.BillDetailsBean;
import com.ziran.meiliao.ui.bean.DynamicBean;
import com.ziran.meiliao.ui.bean.SpaceRecommendBean;
import com.ziran.meiliao.ui.main.adapter.DynamicListAdapter;
import com.ziran.meiliao.ui.settings.adapter.BillDetailsAdapter;
import com.ziran.meiliao.utils.MapUtils;

import java.util.Map;

import butterknife.Bind;

/**
 *
 * 社区关注
 *
 */
public class FollowDynamicFragment extends CommonRefreshFragment<CommonPresenter, CommonModel>
        implements CommonContract.View<DynamicBean> {


    private View headView;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_community_follow;
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        loadedTip.setEmptyMsg("什么都没有~",R.mipmap.load_empty_bg);
        return new DynamicListAdapter(getContext());
    }

    @Override
    protected void loadData() {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("id", MyAPP.getUserId());
        mPresenter.getData(ApiKey.ADMIN_USER_DYNAMIC, defMap, DynamicBean.class);
    }


    @Override
    public void returnData(DynamicBean result) {
        headView = LayoutInflater.from(getContext()).inflate(R.layout.item_view_head_follow, null);
        iRecyclerView.setHeadView(headView);
        updateData(result.getData().getRecords());
    }
}