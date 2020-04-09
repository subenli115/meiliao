package com.ziran.meiliao.ui.settings.activity;

import android.view.View;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.WpyxConfig;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.constant.BaseItemId;

import butterknife.Bind;
import rx.functions.Action1;


public class AboutActivity extends BaseActivity  implements BaseItemId {

    @Bind(R.id.ntb)
    public NormalTitleBar ntb;
    @Bind(R.id.tv_advert_detail)
    public TextView tvAdvertDetail;
    @Bind(R.id.tv_app_detail)
    public TextView tvAppDetail;

    @Override
    public int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void initView() {
        ntb.setTvLeftVisiable(true, true);
        ntb.setTitleText(getString(R.string.about_title));
        ViewUtil.setText(tvAdvertDetail, WpyxConfig.getAdvertDetail());
        if (EmptyUtils.isNotEmpty(WpyxConfig.getAppDetail())){
            tvAppDetail.setVisibility(View.VISIBLE);
            ViewUtil.setText(tvAppDetail, WpyxConfig.getAppDetail());
        }

        mRxManager.on(AppConstant.RXTag.BASE_ITEM_VIEW_CLICK_ID, new Action1<Integer>() {
            @Override
            public void call(Integer id) {
                if (isPause) return;
                switch (id) {
                    case ID_ABOUT_FEED_BACK:
                        FeekBackActivity.startAction(AboutActivity.this);
                        break;
                    case ID_ABOUT_TEXT:
                        break;
                }
            }
        });
    }

}
