package com.ziran.meiliao.ui.priavteclasses.activity;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.ui.priavteclasses.fragment.SJKHomeGhongZuoFangFragment;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/2/10.
 */

public class GongZuoFangMoreActivity extends BaseActivity {
    @Bind(R.id.ntb)
    public NormalTitleBar ntb;


    @Override
    public int getLayoutId() {
        return R.layout.activity_common_frame;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        ntb.setTvLeftVisiable(true, true);
        ntb.setTitleText(getString(R.string.gongzuofang));
        initFragment(new SJKHomeGhongZuoFangFragment());
    }

}
