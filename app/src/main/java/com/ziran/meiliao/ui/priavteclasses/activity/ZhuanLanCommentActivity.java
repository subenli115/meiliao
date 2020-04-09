package com.ziran.meiliao.ui.priavteclasses.activity;

import android.content.Context;
import android.content.Intent;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.priavteclasses.fragment.SJKZhuanLanCommentFragment;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/2/10.
 */

public class ZhuanLanCommentActivity extends BaseActivity {
    @Bind(R.id.ntb)
    public NormalTitleBar ntb;

    /**
     *
     * @param mContext
     * @param key           audioId or courseId
     * @param targetId
     */
    public static void startAction(Context mContext, String key, String targetId) {
        Intent intent = new Intent(mContext, ZhuanLanCommentActivity.class);
        intent.putExtra(AppConstant.ExtraKey.TARGET_ID, targetId);
        intent.putExtra(AppConstant.ExtraKey.TARGET_KEY, key);
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
        ntb.setTitleText(getString(R.string.comment));
        initFragment(new SJKZhuanLanCommentFragment());
    }

}
