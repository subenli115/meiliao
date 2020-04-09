package com.ziran.meiliao.ui.me.activity;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.ui.me.fragment.MyTeamOrderFragment;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/10 14:57
 * @des ${团建定制订单界面}
 * @updateAuthor $Author$
 * @updateDate 2017/8/10$
 * @updateDes ${TODO}
 */

public class MyTeamOrderActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_one_layout;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        initFragment(new MyTeamOrderFragment());
    }

}
