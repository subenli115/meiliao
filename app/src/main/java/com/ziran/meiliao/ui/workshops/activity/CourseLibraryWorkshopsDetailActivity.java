package com.ziran.meiliao.ui.workshops.activity;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.ui.workshops.fragment.CourseLibraryWorkshopsDetailFragment;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/10 14:57
 * @des ${我要众筹官方报价界面}
 * @updateAuthor $Author$
 * @updateDate 2017/8/10$
 * @updateDes ${TODO}
 */

public class CourseLibraryWorkshopsDetailActivity extends BaseActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_one_layout;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        initFragment(new CourseLibraryWorkshopsDetailFragment());
    }
    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

}
