package com.ziran.meiliao.ui.workshops.activity;

import android.app.Activity;
import android.content.Intent;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.workshops.fragment.CourseLibrarySearchFragment;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/11/29 15:56
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/11/29$
 * @updateDes ${TODO}
 */

public class CourseLibrarySearchActivity extends BaseActivity {

    public static void startAction(String key) {
        Activity activity = AppManager.getAppManager().currentActivity();
        Intent intent = new Intent(activity, CourseLibrarySearchActivity.class);
        intent.putExtra(AppConstant.ExtraKey.FROM_TYPE,key);
        activity.startActivity(intent);
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_one_layout;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        initFragment(new CourseLibrarySearchFragment());
    }
}
