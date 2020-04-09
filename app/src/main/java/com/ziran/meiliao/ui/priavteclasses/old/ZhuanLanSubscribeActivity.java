package com.ziran.meiliao.ui.priavteclasses.old;

import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.WpyxConfig;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.ui.priavteclasses.fragment.SJKZhuanLanSubscribeFragment;

import butterknife.Bind;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/15 10:09
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/8/15$
 * @updateDes ${TODO}
 */

public class ZhuanLanSubscribeActivity extends BaseActivity {
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
        ntb.setLeftImagSrc(R.mipmap.livetelecast_close);
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ntb.setTitleText(WpyxConfig.getZhuanLanData().getType() == 0 ? "限免" : "订阅");
        initFragment(new SJKZhuanLanSubscribeFragment());
    }


}
