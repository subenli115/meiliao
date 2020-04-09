package com.ziran.meiliao.ui.workshops.fragment;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.commonwidget.FilterTextView;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.AuthorBean;
import com.ziran.meiliao.ui.bean.CrowdFundingDetailBean;
import com.ziran.meiliao.ui.workshops.activity.CourseLibraryCrowdFundingBuyActivity;
import com.ziran.meiliao.ui.workshops.adapter.CrowdFundingUsedTemplateAdapter;
import com.ziran.meiliao.ui.bean.CrowdFundingPreviewBean;
import com.ziran.meiliao.ui.workshops.contract.CrowdFundingDetailContract;
import com.ziran.meiliao.ui.workshops.presenter.CrowdFundingDetailPresenter;
import com.ziran.meiliao.ui.workshops.util.CrowFundingPreviewUtil;
import com.ziran.meiliao.ui.workshops.util.ServiceDialogUtil;
import com.ziran.meiliao.utils.HtmlUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.SpanUtils;
import com.ziran.meiliao.widget.pupop.SharePopupWindow;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 减压馆-分类Fragment
 * Created by Administrator on 2017/1/7.
 */

public class CrowdFundingDetailFragment extends CommonRefreshFragment<CrowdFundingDetailPresenter, CommonModel> implements
        CrowdFundingDetailContract.View {

    @Bind(R.id.ntb)
    NormalTitleBar ntb;
    @Bind(R.id.tv_course_library_crowd_funding_detail_buy)
    FilterTextView tvCourseLibraryCrowdFundingDetailBuy;
    private CrowFundingPreviewUtil mCrowFundingPreviewUtil;
    private String _id = "1";
    private Map<String, String> paramsMap;
    private CrowdFundingDetailBean.DataBean mCrowdFundingDetailBean;
    private SharePopupWindow mSharePopupWindow;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_crowd_funding_detail;
    }

    @Override
    protected void initBundle(Bundle extras) {
        _id = extras.getString(AppConstant.ExtraKey.FROM_ID);
    }

    @Override
    protected void initOther() {
        ntb.setOnRightImagListener(new View.OnClickListener() {//收藏
            @Override
            public void onClick(View v) {
                postCollect();
            }
        });
        ntb.setOnRightImag2Listener(new View.OnClickListener() {//分享
            @Override
            public void onClick(View v) {
                onShare();
            }
        });
        mCrowFundingPreviewUtil = new CrowFundingPreviewUtil(getContext());
        mCrowFundingPreviewUtil.init();
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        setRecyclerEnabled(false);
        paramsMap = MapUtils.getDefMap(true);
        paramsMap.put("id", _id);//crownFundId
        paramsMap.put("crowdFundId", _id);
        paramsMap.put("crownFundId", _id);
        return new CrowdFundingUsedTemplateAdapter(getContext(), true);
    }

    @Override
    protected void loadData() {
        mPresenter.postData(ApiKey.CROWN_FUND_CROWD_FUND_DETAIL, paramsMap);
    }

    public CrowdFundingDetailBean.DataBean.OthersNeededBean getOthersNeededBean() {
        return EmptyUtils.isEmpty(mCrowdFundingDetailBean) ? null : mCrowdFundingDetailBean.getOthersNeeded();
    }

    public void onShare() {
        if (mCrowdFundingDetailBean == null) {
            showShortToast("分享失败");
        }
        if (mSharePopupWindow == null) {
            mSharePopupWindow = new SharePopupWindow(getContext());
            mSharePopupWindow.setShareTitle(mCrowdFundingDetailBean.getShareTitle());
            mSharePopupWindow.setShareDescript(mCrowdFundingDetailBean.getShareDescript());
            mSharePopupWindow.setShareUrl(mCrowdFundingDetailBean.getShareUrl());
            mSharePopupWindow.openSaveShare(ApiKey.CROWN_FUND_CROWD_FUND_SHARE_SAVE ,paramsMap ,mCrowdFundingDetailBean.getIsShare());
        }
        mSharePopupWindow.show();


    }

    @Override
    public void showData(CrowdFundingDetailBean result) {
        mCrowdFundingDetailBean = result.getData();
        if (EmptyUtils.isEmpty(mCrowdFundingDetailBean)) {
            showEmtry();
            return;
        }
        CrowdFundingDetailBean.DataBean data = result.getData();

        String detail = data.getDetail();
        if (EmptyUtils.isNotEmpty(detail)) {
            List<CrowdFundingPreviewBean> fromJson = new Gson().fromJson(detail, new TypeToken<List<CrowdFundingPreviewBean>>() {
            }.getType());
            updateData(fromJson);
        }
        SpanUtils spanUtils = new SpanUtils();
        SpannableStringBuilder builder = spanUtils.append("立即支持\n").append(HtmlUtil.format("¥%d／人", (int) mCrowdFundingDetailBean
                .getAvgPrice())).setFontSize(11, true).create();
        ViewUtil.setText(tvCourseLibraryCrowdFundingDetailBuy, builder);

        ntb.getIvRight().setSelected(data.isCollect() == 1);

        mCrowFundingPreviewUtil.setHeadData(data.getPicture(), data.getTitle(), data.getStartTime(), data.getEndTime(), data.getAddress()
                , data.getSupportMembers(), data.getLeftTime(), data.getTargetMembers());
        if (EmptyUtils.isNotEmpty(data.getUserInfo())) {
            AuthorBean info = data.getUserInfo();
            mCrowFundingPreviewUtil.setFooterData(info.getHeadImg(), info.getName(), info.getDescript());
        }

        mCrowFundingPreviewUtil.bindTarget(iRecyclerView);
    }

    @Override
    public void showCollectResult(boolean isCollect) {
        ImageView ivRight = ntb.getIvRight();
        if (ivRight != null) {
            mCrowdFundingDetailBean.toggleCollect();
            ivRight.setSelected(mCrowdFundingDetailBean.isCollect() == 1);

        }

    }

    public void postCollect() {
        paramsMap.put("collect", String.valueOf(mCrowdFundingDetailBean.isCollect() == 1 ? 0 : 1));
        mPresenter.postCollect(ApiKey.CROWN_FUND_COLLECT_CROWN_FUND, paramsMap);
    }


    @OnClick({R.id.tv_course_library_service, R.id.tv_course_library_crowd_funding_detail_buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_course_library_service:
                String phone = EmptyUtils.isEmpty(mCrowdFundingDetailBean.getPhone()) ? ServiceDialogUtil.getServicePhone() :
                        mCrowdFundingDetailBean.getPhone();
                ServiceDialogUtil.showDialog(getActivity(), phone);
                break;
            case R.id.tv_course_library_crowd_funding_detail_buy:
                if (mCrowdFundingDetailBean.isCreater()) {
                    showShortToast("这是您发起的众筹,无需购买");
                } else {
                    CrowdFundingDetailBean.DataBean.OthersNeededBean neededBean = getOthersNeededBean();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(AppConstant.ExtraKey.EXTRAS_DATA_NEEDED, neededBean);
                    bundle.putParcelable(AppConstant.ExtraKey.EXTRAS_DATA, mCrowdFundingDetailBean);
                    startActivity(CourseLibraryCrowdFundingBuyActivity.class, bundle);
                }
                break;
        }
    }
}
