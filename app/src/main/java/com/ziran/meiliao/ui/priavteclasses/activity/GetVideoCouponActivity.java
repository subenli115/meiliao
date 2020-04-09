package com.ziran.meiliao.ui.priavteclasses.activity;

import android.content.Intent;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.ui.priavteclasses.fragment.GetVideoCouponFragment;
import com.umeng.socialize.UMShareAPI;

import butterknife.Bind;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/12 10:23
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/12$
 * @updateDes ${TODO}
 */

public class GetVideoCouponActivity extends BaseActivity {
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
        ntb.setTitleText(getString(R.string.free_coupon));
        final GetVideoCouponFragment videoCouponFragment = new GetVideoCouponFragment();
        initFragment(videoCouponFragment);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }
}
