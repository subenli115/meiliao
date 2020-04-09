package com.ziran.meiliao.ui.settings.fragment;

import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.base.BaseFragment;
import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.utils.StringUtils;

import butterknife.Bind;

/**
 * 我的VIP的Fragment
 * Created by Administrator on 2017/1/16.
 */

public class MyVipFragment extends BaseFragment {

    @Bind(R.id.tv_me_myvip_date)
    TextView tvDate;

    @Override
    public void initView() {
        try {
            LogUtils.logd(""+MyAPP.getVipLevelBean());
            tvDate.setText(StringUtils.format(R.string.myvip_tips,MyAPP.getVipLevelBean().getData().getEndTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_me_myvip;
    }

    @Override
    public void initPresenter() {

    }

}
