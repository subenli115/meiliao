package com.ziran.meiliao.ui.workshops.activity;

import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.workshops.fragment.CrowdFundingProjectMsgFragment;
import com.ziran.meiliao.ui.workshops.util.QJGDataUtil;
import com.ziran.meiliao.ui.workshops.util.ServiceDialogUtil;

import butterknife.Bind;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/10 14:57
 * @des ${我要众筹项目信息界面}
 * @updateAuthor $Author$
 * @updateDate 2017/8/10$
 * @updateDes ${TODO}
 */

public class CrowdFundingProjectMsgActivity extends CommonHttpActivity {


    private CrowdFundingProjectMsgFragment mCrowdFundingProjectMsgFragment;

    @Bind(R.id.ntb)
    NormalTitleBar ntb;

    @Override
    public int getLayoutId() {
        return R.layout.activity_common_frame;
    }
    @Override
    public void initView() {
        super.initView();
        ntb.setTitleText("我要众筹");
        ntb.setTvLeftVisiable(true,true);
        ntb.setRightImagSrc(R.mipmap.nav_servicer);
        ntb.setRightImag2Src(R.mipmap.nav_course);
        ntb.setOnRightImag2Listener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(CourseLibraryActivity.class);
            }
        });
        ntb.setOnRightImagListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceDialogUtil.showDialog(CrowdFundingProjectMsgActivity.this);
            }
        });

        mCrowdFundingProjectMsgFragment = new CrowdFundingProjectMsgFragment();
        initFragment(mCrowdFundingProjectMsgFragment);
    }
    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    protected void onDestroy() {
        ServiceDialogUtil.cancel();
        QJGDataUtil.clearTemplateData();
        super.onDestroy();
    }
}
