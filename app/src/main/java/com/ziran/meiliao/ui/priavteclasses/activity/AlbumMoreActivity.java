package com.ziran.meiliao.ui.priavteclasses.activity;

import android.content.Context;
import android.content.Intent;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.priavteclasses.fragment.AlbumMoreFragment;

import butterknife.Bind;

/**
 * 全部音频.
 */

public class AlbumMoreActivity extends CommonHttpActivity {
    @Bind(R.id.ntb)
    public NormalTitleBar ntb;

    public static void startAction(Context mContext) {
        Intent intent = new Intent(mContext, AlbumMoreActivity.class);
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
        super.initView();
        ntb.setTvLeftVisiable(true, true);
        ntb.setTitleText(getString(R.string.all_album));
        initFragment(new AlbumMoreFragment());
    }

}
