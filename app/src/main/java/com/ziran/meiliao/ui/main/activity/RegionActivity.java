package com.ziran.meiliao.ui.main.activity;


import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.ui.main.fragment.RegionFragment;

import butterknife.Bind;


/**
 * des: 地区选择界面
 * Created by xsf
 * on 2016.09.15:16
 */
public class RegionActivity extends BaseActivity {

    @Bind(R.id.ntb)
    NormalTitleBar ntb;

    @Override
    public int getLayoutId() {
        return R.layout.activity_common_frame;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        ntb.setTitleText("搜索国家和地区");
        ntb.setTvLeftVisiable(true, true);
        initFragment(new RegionFragment());
    }

}
