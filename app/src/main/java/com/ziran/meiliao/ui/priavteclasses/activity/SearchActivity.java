package com.ziran.meiliao.ui.priavteclasses.activity;

import android.app.Activity;
import android.content.Intent;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.priavteclasses.fragment.SearchFragment;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/4/19 15:02
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/4/19$
 * @updateDes ${TODO}
 */

public class SearchActivity extends BaseActivity {


    public static void startAction() {
        Activity activity = AppManager.getAppManager().currentActivity();
        Intent intent = new Intent(activity, SearchActivity.class);
        activity.startActivity(intent);
    }
    public static void startAction(String hotNew) {
        Activity activity = AppManager.getAppManager().currentActivity();
        Intent intent = new Intent(activity, SearchActivity.class);
        intent.putExtra(AppConstant.ExtraKey.FROM_TYPE,hotNew);
        activity.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_seach1;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        initFragment(new SearchFragment());
    }

}
