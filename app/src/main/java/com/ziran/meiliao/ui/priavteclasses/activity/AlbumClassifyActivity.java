package com.ziran.meiliao.ui.priavteclasses.activity;

import android.content.Context;
import android.content.Intent;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.AlbumClassifyBean;
import com.ziran.meiliao.ui.priavteclasses.fragment.AlbumClassifyFragment;


import butterknife.Bind;



/**
 * 私家课-活动Fragment
 * Created by Administrator on 2017/1/7.
 */

public class AlbumClassifyActivity extends CommonHttpActivity<CommonPresenter, CommonModel> implements CommonContract

        .View<AlbumClassifyBean> {
    @Bind(R.id.ntb)
    public NormalTitleBar ntb;

    public static void startAction(Context mContext) {
        Intent intent = new Intent(mContext, AlbumClassifyActivity.class);
        mContext.startActivity(intent);
    }




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
        ntb.setTitleText(getString(R.string.classify));
        initFragment(new AlbumClassifyFragment());
    }

    @Override
    public void returnData(AlbumClassifyBean result) {

    }
}
