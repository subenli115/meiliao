package com.ziran.meiliao.ui.priavteclasses.fragment;

import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.NewCourseBean;
import com.ziran.meiliao.ui.priavteclasses.adapter.CourseTwoAdapter;
import com.ziran.meiliao.utils.MapUtils;

import java.util.Map;



/**
 * 私家课-活动Fragment
 * Created by Administrator on 2017/1/7.
 */

public class SJKHomeGhongZuoFangFragment  extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract

        .View<NewCourseBean> {

    @Override
    protected int getLayoutResource () {
        return R.layout.fragment_sjk_home_gzf;
    }
    @Override
    public CommonRecycleViewAdapter getAdapter() {
        return new CourseTwoAdapter(getContext() , new CourseTwoAdapter.ActivityMultiItemType() {
        });
    }

    @Override
    protected void loadData() {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("page",page+"");

        mPresenter.postData(ApiKey.ACTIVITY_GETACTIVITYLIST,defMap,NewCourseBean.class);
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
    }



    @Override
    public void returnData(NewCourseBean result) {
        updateData(result.getData());
    }
}
