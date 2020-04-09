package com.ziran.meiliao.ui.settings.activity;

import android.content.Context;
import android.content.Intent;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.ui.settings.fragment.GetGoalFragment;

import butterknife.Bind;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/28 11:08
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/28$
 * @updateDes ${TODO}
 */

public class GetGoalActivity extends BaseActivity {
    @Bind(R.id.ntb)
    public NormalTitleBar ntb;

    public static void startAction(Context mContext) {
        Intent intent = new Intent(mContext, GetGoalActivity.class);
        mContext.startActivity(intent);
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
        ntb.setTvLeftVisiable(true, true);
        ntb.setTitleText(getString(R.string.get_goal));
        initFragment(new GetGoalFragment());
    }
}
