package com.ziran.meiliao.ui.priavteclasses.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.ui.base.CommonHttpFragment;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.TeacherGoinBean;
import com.ziran.meiliao.ui.bean.TeacherPageBean;
import com.ziran.meiliao.ui.priavteclasses.activity.ExtCapStreamingActivity;
import com.ziran.meiliao.ui.priavteclasses.contract.MainOpenLiveContract;
import com.ziran.meiliao.ui.priavteclasses.presenter.MainOpenLivePresenter;
import com.ziran.meiliao.ui.priavteclasses.util.LiveRoomUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.StringUtils;
import com.ziran.meiliao.widget.MoreImageView;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.soexample.ShareUtil;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * 私家课-活动Fragment
 * Created by Administrator on 2017/1/7.
 */

public class MainOpenLiveFragment extends CommonHttpFragment<MainOpenLivePresenter, CommonModel> implements UMShareListener,
        MainOpenLiveContract.View, MoreImageView.OnMoreImageViewClickListener {

    @Bind(R.id.tv_main_sjk_openlive_time)
    TextView tvMainSjkOpenliveTime;
    @Bind(R.id.iv_main_sjk_openlive_cover)
    ImageView ivMainSjkOpenliveCover;
    @Bind(R.id.tv_main_sjk_openlive_subTitle)
    TextView tvMainSjkOpenliveSubTitle;
    @Bind(R.id.tv_main_sjk_openlive_title)
    TextView tvMainSjkOpenliveTitle;
    @Bind(R.id.tv_main_sjk_openlive_author)
    TextView tvMainSjkOpenliveAuthor;
    @Bind(R.id.tv_main_sjk_openlive_author_descript)
    TextView getTvMainSjkOpenliveAuthorDescript;
    @Bind(R.id.tv_main_sjk_openlive_open)
    TextView ivOpenLive;
    @Bind(R.id.moreImageView)
    MoreImageView mMoreImageView;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_main_sjk_openlive;
    }


    @Override
    protected void initView() {
        ShareUtil.addCallBack(this);
        mMoreImageView.setResourses(new int[]{
                R.mipmap.living_share_wechat,R.mipmap.living_share_wechatzone,R.mipmap.living_share_qq,R.mipmap.living_share_weibo
        });
        mMoreImageView.setOnMoreImageViewClickListener(this);
        mPresenter.getTeacherIndex(ApiKey.TEATHER_INDEX, MapUtils.getDefMap(true));
    }

    //分享回调
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(getActivity()).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopProgressDialog();
        ShareUtil.removeCallBack(this);
        UMShareAPI.get(getActivity()).release();
    }

    @OnClick(R.id.tv_main_sjk_openlive_open)
    public void onViewClicked(View view) {
        if (courseData != null) {
            mPresenter.checkOpenLive(ApiKey.TEATHER_GOIN, MapUtils.getCourseMap(String.valueOf(courseData.getId())));
        }
    }

    @Override
    public void showErrorTip(String msg) {
        showShortToast(msg);
    }

    private void share(final SHARE_MEDIA shareMedia) {
        if (EmptyUtils.isNotEmpty(courseData)) {
            ShareUtil.shareWeb(getContext(), shareMedia, courseData.getShareDescript(), null, courseData.getShareUrl() + "&isShare=1",
                    courseData.getShareTitle(), courseData.getShareDescript());
        }
    }

    @Override
    public void onStart(SHARE_MEDIA share_media) {

    }

    @Override
    public void onResult(SHARE_MEDIA share_media) {
        ToastUitl.showShort(StringUtils.getText(R.string.share_ok));
        stopProgressDialog();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
        if (isVisible) {
            mPresenter.getTeacherIndex(ApiKey.TEATHER_INDEX, MapUtils.getDefMap(true));
        }
    }

    @Override
    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
        ToastUitl.showShort(StringUtils.getText(R.string.share_error));
        stopProgressDialog();
    }

    @Override
    public void onCancel(SHARE_MEDIA share_media) {
        ToastUitl.showShort(StringUtils.getText(R.string.share_cancel));
        stopProgressDialog();
    }

    private TeacherPageBean.DataBean.CourseBean courseData;

    @Override
    public void showData(TeacherPageBean.DataBean resultData) {
        courseData = resultData.getCourse();
        try {
            if (EmptyUtils.isNotEmpty(courseData)) {
                tvMainSjkOpenliveTime.setText(String.format("%s 现场直播", courseData.getTime()));
                ImageLoaderUtils.display(getContext(), ivMainSjkOpenliveCover, courseData.getPicture());
                tvMainSjkOpenliveSubTitle.setText(courseData.getIntro());
                tvMainSjkOpenliveTitle.setText(courseData.getTitle());
                tvMainSjkOpenliveAuthor.setText(courseData.getAuthor().getName());
                getTvMainSjkOpenliveAuthorDescript.setText(courseData.getAuthor().getDescript());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void openLive(TeacherGoinBean.DataBean data) {
        LiveRoomUtil.get().setTeacherDataBean(data);
        ExtCapStreamingActivity.startAction(getContext(), data.getRmtp(), String.valueOf(courseData.getId()));
    }

    /**
     * 分享按钮监听
     *
     * @param index 点击的索引
     */
    @Override
    public void onClick(int index) {
        switch (index) {
            case 0:
                share(SHARE_MEDIA.WEIXIN.toSnsPlatform().mPlatform);
                break;
            case 1:
                share(SHARE_MEDIA.WEIXIN_CIRCLE.toSnsPlatform().mPlatform);
                break;
            case 2:
                share(SHARE_MEDIA.QQ.toSnsPlatform().mPlatform);
                break;
            case 3:
                share(SHARE_MEDIA.SINA.toSnsPlatform().mPlatform);
                break;
        }
    }

}
