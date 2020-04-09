package com.ziran.meiliao.ui.me.activity;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.ui.me.fragment.MyCrowdFundingDetailFragment;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/10 14:57
 * @des ${发起众筹查看明细页面}
 * @updateAuthor $Author$
 * @updateDate 2017/8/10$
 * @updateDes ${TODO}
 */

public class MyCrowdFundingDetailActivity extends BaseActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_one_layout;
    }

    @Override
    public void initPresenter() {

    }
    @Override
    public void initView() {
        initFragment(new MyCrowdFundingDetailFragment());
    }

}
