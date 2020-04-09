package com.ziran.meiliao.ui.priavteclasses.activity;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.AuthorBean;
import com.ziran.meiliao.ui.bean.TeacherInfoBean;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.BaseItemView;

import butterknife.Bind;

/**
 *   直播间后台-老师详情
 */
public class TeacherDetailActivity extends BaseActivity<CommonPresenter,CommonModel> implements CommonContract.View<TeacherInfoBean> {


    @Bind(R.id.bivSponsorAvatar)
    BaseItemView mBivSponsorAvatar;
    @Bind(R.id.bivName)
    BaseItemView mBivName;
    @Bind(R.id.bivProfile)
    BaseItemView mBivProfile;

    @Override
    public int getLayoutId() {
        return R.layout.activity_sjk_teacher_detail;
    }

    @Override
    public void initPresenter() {
        if (mPresenter != null) {
            mPresenter.setVM(this, mModel);
        }
    }

    @Override
    public void initView() {
        AuthorBean authorBean = (AuthorBean) getParcelable(AppConstant.ExtraKey.AUTHOR_DATA);
        if (authorBean == null) return;
        mPresenter.postData(ApiKey.LIVE_LIVE_TEACHER_INFO, MapUtils.getDefMap(true),TeacherInfoBean.class);

    }


    @Override
    public void returnData(TeacherInfoBean result) {
        TeacherInfoBean.DataBean data = result.getData();
        mBivSponsorAvatar.setImageUrl(data.getHeadImg());
        mBivName.setContent(data.getTeacherName());
        if (EmptyUtils.isNotEmpty(data.getIntro())){
            mBivProfile.setContent(data.getIntro());
        }
    }
}
