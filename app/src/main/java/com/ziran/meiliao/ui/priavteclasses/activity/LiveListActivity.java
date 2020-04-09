package com.ziran.meiliao.ui.priavteclasses.activity;

import android.content.Context;
import android.content.Intent;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.ui.priavteclasses.fragment.CourseDetailFragment;
import com.ziran.meiliao.ui.priavteclasses.fragment.CourseListFragment;
import com.ziran.meiliao.ui.priavteclasses.fragment.OpenLiveListFragment;

import butterknife.Bind;


public class LiveListActivity extends BaseActivity {


    @Bind(R.id.ntb)
    NormalTitleBar ntb;

    @Override
    public int getLayoutId() {
        return R.layout.activity_common_frame;
    }

    @Override
    public void initPresenter() {

    }

    public static void startAction(Context context, String title) {
        Intent intent = new Intent(context, LiveListActivity.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    @Override
    public void initView() {
        String title = getIntentExtra(getIntent(), "title");
        ntb.setTitleText(title);
        ntb.setTvLeftVisiable(true, true);
        if ("开播列表".equals(title)) {
            initFragment(new OpenLiveListFragment());
        } else if ("课程列表".equals(title)) {
            initFragment(new CourseListFragment());
        } else {
            initFragment(new CourseDetailFragment());
        }
    }

}
