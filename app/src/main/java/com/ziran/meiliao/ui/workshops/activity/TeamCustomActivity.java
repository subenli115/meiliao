package com.ziran.meiliao.ui.workshops.activity;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.ui.workshops.fragment.TeamCustomFragment;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/11/28 20:52
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/11/28$
 * @updateDes ${TODO}
 */

public class TeamCustomActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_one_layout;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        initFragment( new TeamCustomFragment());
    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }
}
