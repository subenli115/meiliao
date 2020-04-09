package com.ziran.meiliao.ui.workshops.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.workshops.fragment.CrowdFundingUserMsgInputFragment;
import com.ziran.meiliao.ui.workshops.fragment.CrowdFundingUserMsgTipsFragment;
import com.ziran.meiliao.ui.workshops.util.ServiceDialogUtil;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;

import java.util.ArrayList;

import butterknife.Bind;
import rx.functions.Action1;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/10 14:57
 * @des ${我要众筹用户信息界面}
 * @updateAuthor $Author$
 * @updateDate 2017/8/10$
 * @updateDes ${TODO}
 */

public class CrowdFundingUserMsgActivity extends CommonHttpActivity<CommonPresenter,CommonModel> implements CommonContract.View<Result> {


    @Bind(R.id.ntb)
    NormalTitleBar ntb;
    private CrowdFundingUserMsgInputFragment mCrowdFundingUserMsgInputFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_crowd_funding_user_msg;
    }

    @Override
    public void initView() {
        super.initView();

        ImgSelConfig.needSaveImgs = true;


        ntb.setTitleText("我要众筹");
        ntb.setTvLeftVisiable(true, true);


        ntb.setRightImagSrc(R.mipmap.nav_servicer);
        ntb.setOnRightImagListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceDialogUtil.showDialog(CrowdFundingUserMsgActivity.this);
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras!=null && extras.getBoolean("hasPost")){
            initFragment(new CrowdFundingUserMsgTipsFragment());
            return;
        }
        mRxManager.on(AppConstant.RXTag.SUBMIT_USER_MSG, new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                if (aBoolean) submit();
            }
        });
        mCrowdFundingUserMsgInputFragment = new CrowdFundingUserMsgInputFragment();
        initFragment(mCrowdFundingUserMsgInputFragment);
    }


    public void submit() {
        ntb.setTitleText("提交成功");
        initFragment(new CrowdFundingUserMsgTipsFragment());
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null && requestCode == ImgSelConfig.RequestCode) {
            ArrayList<String> imgPaths = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
            if (imgPaths != null && imgPaths.size() > 0) {
                mCrowdFundingUserMsgInputFragment.setImageResult(imgPaths);
            }
        }
    }

    @Override
    protected void onDestroy() {
        ServiceDialogUtil.cancel();
        ImgSelConfig.needSaveImgs = false;
        super.onDestroy();
    }

    @Override
    public void returnData(Result result) {

    }
}
