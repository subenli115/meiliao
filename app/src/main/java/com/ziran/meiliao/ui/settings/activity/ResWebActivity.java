package com.ziran.meiliao.ui.settings.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ziran.meiliao.app.WpyxConfig;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonWebActivity;
import com.ziran.meiliao.ui.bean.ResBean;
import com.ziran.meiliao.utils.CheckUtil;

/**
 * author admin
 * create  2017/3/27 17
 * des     ${推广大使页面}
 * <p>
 * updateAuthor     $admin
 * updateDate   2017/3/27 17
 * updateDes    ${TODO}
 */

public class ResWebActivity extends CommonWebActivity {

    private ResBean.DataBean mDataBean;

    public static void startAction(Context context, ResBean.DataBean dataBean) {
        if (CheckUtil.check(context)) {
            Intent intent = new Intent(context, ResWebActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString(AppConstant.SPKey.EXTRAS_URL, dataBean.getExtension());
            intent.putExtras(bundle);
            context.startActivity(intent);
        }
    }

    @Override
    public void initView() {
        super.initView();
        tvHeadviewTitle.setText("推广大使");
        ivCollect.setVisibility(View.GONE);
        ivShare.setVisibility(View.GONE);
    }

    @Override
    protected void initBundle(Bundle extras) {
        if (EmptyUtils.isNotEmpty(WpyxConfig.getResBean()) && EmptyUtils.isNotEmpty(WpyxConfig.getResBean().getData())) {
            mDataBean = WpyxConfig.getResBean().getData();
        }
    }

}
