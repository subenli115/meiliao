package com.ziran.meiliao.ui.settings.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.ui.base.CommonWebActivity;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/3/8.
 */

public class MessageDetailActivity extends CommonWebActivity {

    @Bind(R.id.tv_headview_title)
    TextView tvHeadviewTitle;
    @Bind(R.id.iv_headview_collect)
    ImageView ivCollect;
    @Bind(R.id.iv_headview_share)
    ImageView ivShare;

    public static void startAction(Context context, Bundle bundle) {
        if (MyAPP.isLogin(context)) {
            Intent intent = new Intent(context, MessageDetailActivity.class);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            context.startActivity(intent);
        }
    }

    @Override
    protected void initBundle(Bundle extras) {
        super.initBundle(extras);
    }

    @Override
    public void initView() {
        super.initView();
        tvHeadviewTitle.setText(R.string.message_detail);
        ivCollect.setVisibility(View.GONE);
        ivShare.setVisibility(View.GONE);
    }


}
