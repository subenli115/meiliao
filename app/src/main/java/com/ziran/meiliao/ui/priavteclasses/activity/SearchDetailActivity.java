package com.ziran.meiliao.ui.priavteclasses.activity;

import android.app.Activity;
import android.content.Intent;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.priavteclasses.fragment.SearchDetailFragment;

import butterknife.Bind;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/4/19 15:02
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/4/19$
 * @updateDes ${TODO}
 */

public class SearchDetailActivity extends BaseActivity {

    @Bind(R.id.ntb)
    NormalTitleBar ntb;

    public static void startAction(String type, String content) {
        Activity activity = AppManager.getAppManager().currentActivity();
        Intent intent = new Intent(activity, SearchDetailActivity.class);
        intent.putExtra(AppConstant.ExtraKey.FROM_TYPE, type);
        intent.putExtra(AppConstant.ExtraKey.KEY_CONTENT, content);
        activity.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_common_frame;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        String content = getIntentExtra(getIntent(), AppConstant.ExtraKey.KEY_CONTENT);
        ntb.setTvLeftVisiable(true, true);
        ntb.setTitleText(content);
        initFragment(new SearchDetailFragment());
    }

}
