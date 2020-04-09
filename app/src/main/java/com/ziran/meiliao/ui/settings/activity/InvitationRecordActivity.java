package com.ziran.meiliao.ui.settings.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.InvitationRecordBean;
import com.ziran.meiliao.ui.settings.adapter.InvitationRecordAdapter;
import com.ziran.meiliao.utils.MapUtils;

import java.util.List;

import butterknife.Bind;

import static com.ziran.meiliao.constant.ApiKey.USERCENTER_SHAREFRDSLIST;


/**
 *
 * 邀请好友
 */
public class InvitationRecordActivity extends CommonHttpActivity<CommonPresenter, CommonModel> implements CommonContract.ActionView<Result, Result>{


    @Bind(R.id.recyclerView)
    public RecyclerView recyclerView;
    @Bind(R.id.ntb)
    public NormalTitleBar ntb;
    @Bind(R.id.tv_num)
    public TextView tvNum;

    public static void startAction(Context mContext) {
        Intent intent = new Intent(mContext, InvitationRecordActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.ac_invitation_record;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }



    @Override
    public void initView() {
        ntb.setVerLineVisiable(false);
        ntb.setNewTitleText("邀请记录");
        ntb.setOnRightTextListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        ntb.setBackGroundColor(R.color.transparent);

    mPresenter.getData(USERCENTER_SHAREFRDSLIST,MapUtils.getDefMap(true),InvitationRecordBean.class);

    }

    @Override
    public void returnData(Result result) {
    InvitationRecordBean bean = (InvitationRecordBean) result;
    InvitationRecordBean.DataBean data = bean.getData();
        tvNum.setText(data.getFrdsList().size()+"");
        List<InvitationRecordBean.DataBean.FrdsListBean> frdsList = data.getFrdsList();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        InvitationRecordAdapter invitationRecordActivity = new InvitationRecordAdapter(frdsList,mContext, 22);
        recyclerView.setAdapter(invitationRecordActivity);
    }



    @Override
    public void returnAction(Result result) {

    }
}
