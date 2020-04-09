package com.ziran.meiliao.ui.workshops.activity;

import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.workshops.fragment.CrowdFundingPreviewFragment;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/10 14:57
 * @des ${我要众筹预览界面}
 * @updateAuthor $Author$
 * @updateDate 2017/8/10$
 * @updateDes ${TODO}
 */

public class CrowdFundingPreviewActivity extends BaseActivity{


    @Bind(R.id.ntb)
    NormalTitleBar ntb;

    @Override
    public int getLayoutId() {
        return R.layout.activity_crowd_funding_preview;
    }

    @Override
    public void initPresenter() {

    }


    @Override
    public void initView() {
        ntb.setTvLeftVisiable(true, true);
        ntb.setTitleText("预览");
        initFragment(new  CrowdFundingPreviewFragment());
        mRxManager.on(AppConstant.RXTag.PREVIEW_CLOSE, new Action1<Boolean  >() {
            @Override
            public void call(Boolean aBoolean) {
                finish();
            }
        });
    }

    @OnClick(R.id.tv_crowd_funding_project_msg_submit)
    public void onViewClicked(View view) {
        mRxManager.post(AppConstant.RXTag.SUBMIT_CROWD_FUNDING_MSG,true);
        finish();
    }

}
