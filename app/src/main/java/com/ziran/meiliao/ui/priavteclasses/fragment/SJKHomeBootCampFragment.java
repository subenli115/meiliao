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
import com.ziran.meiliao.ui.bean.BootCampV2Bean;
//import com.ziran.meiliao.ui.bean.NewBootCampBean;
import com.ziran.meiliao.ui.priavteclasses.adapter.BootCampAdapter;
import com.ziran.meiliao.utils.MapUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/1/31.
 */

public class SJKHomeBootCampFragment extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract

        .View<BootCampV2Bean> {

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_sjk_home_gzf;
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        return new BootCampAdapter(getContext(), new BootCampAdapter.ActivityMultiItemType() {
        });
    }

    @Override
    protected void loadData() {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("page", 1 + "");
        defMap.put("portVersion", "2");
        mPresenter.postData(ApiKey.PRACTICEACTIVITY_GETLIST, defMap, BootCampV2Bean.class);
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
    }



    @Override
    public void returnData(BootCampV2Bean result) {
        List<BootCampV2Bean.DataBean.JsCoursesBean> jsCourses = result.getData().getJsCourses();
        List<BootCampV2Bean.DataBean.PracticeBooksBean> practiceBooks = result.getData().getPracticeBooks();
        List<Object> list=new ArrayList<>();
        if(practiceBooks!=null&&practiceBooks.size()!=0){
            list.add(practiceBooks.get(0));
        }
        if(jsCourses!=null&&jsCourses.size()!=0){
            for(int i=0;i<jsCourses.size();i++){
                list.add(jsCourses.get(i));
            }
        }
       mAdapter.clear();
        updateData(list);
    }
}
