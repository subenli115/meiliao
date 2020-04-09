package com.ziran.meiliao.ui.settings.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.entry.CourseBean;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.priavteclasses.activity.NoBuyZLActivity;
import com.ziran.meiliao.ui.settings.adapter.BuyCourseAdapter;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.MapUtils;

/**
 * 购买课程Fragment
 * Created by Administrator on 2017/1/16.
 */

public class BuyCourseFragment extends CommonRefreshFragment<CommonPresenter, CommonModel>
        implements CommonContract.View<CourseBean> {


    @Override
    public CommonRecycleViewAdapter getAdapter() {
        loadedTip.setEmptyMsg(getString(R.string.emtry_course), R.mipmap.ic_empty_course);
        return new BuyCourseAdapter(getContext(), R.layout.item_me_course);
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        if (!CheckUtil.check(getContext(),getView())) return;
        CourseBean.DataBean dataBean = EmptyUtils.parseObject(o);
        if (EmptyUtils.isNotEmpty(dataBean)) {
//            SJKLiveUtil.startActivity(getContext(),String.valueOf(dataBean.getId()),dataBean.getTag(),dataBean.getStatus());
            Intent intent = new Intent(getContext(), NoBuyZLActivity.class);
            Bundle bundle = new Bundle();
            if(dataBean.isBuy()){
                bundle.putString("isbuy","1");
            }else {
                bundle.putString("isbuy","0");
            }
            bundle.putString("htmlLink",dataBean.getHtmlLink());
            bundle.putString(AppConstant.ExtraKey.SUBSCRIPTION_ID, String.valueOf(dataBean.getSubscriptionId()));
            intent.putExtras(bundle);
            getContext().startActivity(intent);
        }
    }

    @Override
    protected void loadData() {
        mPresenter.postData(ApiKey.BUY_COURSE_LIST, MapUtils.getOnlyPage(page), CourseBean.class);
    }

    @Override
    public void returnData(CourseBean result) {
        updateData(result.getData());
    }
}
