package com.ziran.meiliao.ui.workshops.activity;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.workshops.fragment.CourseLibraryCrowdFundingBuyFragment;

import butterknife.Bind;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/10 14:57
 * @des ${我要众筹官方报价界面}
 * @updateAuthor $Author$
 * @updateDate 2017/8/10$
 * @updateDes ${TODO}
 */

public class CourseLibraryCrowdFundingBuyActivity extends CommonHttpActivity {


    @Bind(R.id.ntb)
    NormalTitleBar ntb;

    @Override
    public int getLayoutId() {
        return R.layout.activity_common_frame;
    }

    @Override
    public void initView() {
        super.initView();
        ntb.setTitleText("立即支持");
        ntb.setTvLeftVisiable(true,true);
        initFragment(new CourseLibraryCrowdFundingBuyFragment());
    }




}
