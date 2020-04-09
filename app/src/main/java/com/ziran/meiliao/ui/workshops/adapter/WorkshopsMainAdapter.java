package com.ziran.meiliao.ui.workshops.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.commonutils.AdViewpagerUtil;
import com.ziran.meiliao.common.commonwidget.OnNoDoubleClickListener;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.support.SimpleOnItemClickListener;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.ActisData;
import com.ziran.meiliao.ui.bean.CourseLibraryTeamBean;
import com.ziran.meiliao.ui.bean.CrownFundListBean;
import com.ziran.meiliao.ui.bean.FamousTeacherListBean;
import com.ziran.meiliao.ui.bean.MultiItemBean;
import com.ziran.meiliao.ui.bean.PicsBean;
import com.ziran.meiliao.ui.bean.StringDataBean;
import com.ziran.meiliao.ui.priavteclasses.activity.GongZuoFangActivity;
import com.ziran.meiliao.ui.priavteclasses.activity.HorizontalHistoryActivity;
import com.ziran.meiliao.ui.priavteclasses.activity.HorizontalLiveActivity;
import com.ziran.meiliao.ui.workshops.activity.CourseLibraryActivity;
import com.ziran.meiliao.ui.workshops.activity.CourseLibraryCrowdFundingDetailActivity;
import com.ziran.meiliao.ui.workshops.activity.CourseLibraryTeacherDetailActivity;
import com.ziran.meiliao.ui.workshops.activity.CourseLibraryTeamDetailActivity;
import com.ziran.meiliao.ui.workshops.activity.CrowdFundingProjectMsgActivity;
import com.ziran.meiliao.ui.workshops.activity.CrowdFundingUserMsgActivity;
import com.ziran.meiliao.ui.workshops.activity.GuideTeamCustomActivity;
import com.ziran.meiliao.ui.workshops.util.QJGDataUtil;
import com.ziran.meiliao.ui.workshops.widget.TitleListView;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.MapUtils;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/11/9 9:41
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/11/9$
 * @updateDes ${TODO}
 */

public class WorkshopsMainAdapter extends MultiItemRecycleViewAdapter<MultiItemBean> {

    public static final int TYPE_HEAD_VIEW = 0x5210;
    public static final int TYPE_LIST_VIEW = 0x5211;

    public WorkshopsMainAdapter(Context context) {
        super(context, new CrowdFundingUsedTemplateMultiItemType());

    }

    @Override
    public void convert(ViewHolderHelper helper, final MultiItemBean bean, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case TYPE_HEAD_VIEW:
                setHeadView(helper, bean);
                break;
            case TYPE_LIST_VIEW:
                setListView(helper, bean);
                break;
        }
    }


    private void setListView(ViewHolderHelper helper, MultiItemBean bean) {
        TitleListView titleListView = (TitleListView) helper.getConvertView();
        CommonRecycleViewAdapter adapter = null;
        titleListView.setTvTitle(bean.getTitle());
        if ("众筹课程".equals(bean.getTitle())) {
            if (mWorkshopsMainCrowdFundingAdapter == null) {
                mWorkshopsMainCrowdFundingAdapter = new WorkshopsMainCrowdFundingAdapter(mContext);
                titleListView.openSnapHelper();
                adapter = mWorkshopsMainCrowdFundingAdapter;
            }
        } else if ("课程推荐".equals(bean.getTitle())) {
            if (mWorkshopsMainTeamAdapter == null) {
                mWorkshopsMainTeamAdapter = new WorkshopsMainTeamAdapter(mContext);
                titleListView.openSnapHelper();
                adapter = mWorkshopsMainTeamAdapter;
            }
        } else if ("名师教场".equals(bean.getTitle())) {
            if (mWorkshopsMainTeacherAdapter == null) {
                mWorkshopsMainTeacherAdapter = new WorkshopsMainTeacherAdapter(mContext);
                titleListView.openSnapHelper();
                adapter = mWorkshopsMainTeacherAdapter;
            }
        } else if ("工作坊".equals(bean.getTitle())) {
            if (mWorkshopsMainWorkshopsAdapter == null) {
                mWorkshopsMainWorkshopsAdapter = new WorkshopsMainWorkshopsAdapter(mContext);
                titleListView.openSnapHelper();
                adapter = mWorkshopsMainWorkshopsAdapter;
            }
        }
        titleListView.setMoreTag(bean.getTitle());
        titleListView.setMoreClickListener(onMoreClickListener);
        if (adapter != null) {
            titleListView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            titleListView.setAdapter(adapter);
            adapter.setHeadCount(0);
            adapter.setOnItemClickListener(mSimpleOnItemClickListener);
            if (adapter.getSize() == 0) {
                adapter.replaceAll(bean.getData());
            }
        }
    }

    private View.OnClickListener onMoreClickListener = new OnNoDoubleClickListener() {

        @Override
        protected void onNoDoubleClick(View v) {
            Object tag = v.getTag();
            if (EmptyUtils.isNotEmpty(tag)) {
                switch (tag.toString()) {
                    case "众筹课程":
                        CourseLibraryActivity.startAction(mContext, 0);
                        break;
                    case "课程推荐":
                        CourseLibraryActivity.startAction(mContext, 1);
                        break;
                    case "名师教场":
                        CourseLibraryActivity.startAction(mContext, 2);
                        break;
                    case "工作坊":
                        CourseLibraryActivity.startAction(mContext, 3);
                        break;
                }
            }
        }
    };

    private SimpleOnItemClickListener mSimpleOnItemClickListener = new SimpleOnItemClickListener() {
        @Override
        public void onItemClick(ViewGroup parent, View view, Object o, int position) {
            Bundle bundle = new Bundle();
            if (o instanceof CrownFundListBean) {
                bundle.putString(AppConstant.ExtraKey.FROM_ID, ((CrownFundListBean) o).getIdString());
                startActivity(CourseLibraryCrowdFundingDetailActivity.class, bundle);
            } else if (o instanceof CourseLibraryTeamBean) {
                bundle.putString(AppConstant.ExtraKey.FROM_ID, ((CourseLibraryTeamBean) o).getId());
                startActivity(CourseLibraryTeamDetailActivity.class, bundle);
            } else if (o instanceof FamousTeacherListBean) {
                bundle.putString(AppConstant.ExtraKey.FROM_ID, ((FamousTeacherListBean) o).getId());
                startActivity(CourseLibraryTeacherDetailActivity.class, bundle);
            } else if (o instanceof ActisData) {
                GongZuoFangActivity.startAction(mContext, (ActisData)o);
//                startActivity(CourseLibraryWorkshopsDetailActivity.class);
            }
        }
    };
    private WorkshopsMainCrowdFundingAdapter mWorkshopsMainCrowdFundingAdapter;
    private WorkshopsMainTeamAdapter mWorkshopsMainTeamAdapter;
    private WorkshopsMainTeacherAdapter mWorkshopsMainTeacherAdapter;
    private WorkshopsMainWorkshopsAdapter mWorkshopsMainWorkshopsAdapter;

    private void setHeadView(final ViewHolderHelper helper, MultiItemBean bean) {
        if (mAdViewpagerUtil == null) {
            ViewPager viewPager = helper.getView(R.id.viewpager);
            LinearLayout dot = helper.getView(R.id.ly_dots);
            mAdViewpagerUtil = new AdViewpagerUtil(mContext, viewPager, dot, QJGDataUtil.getImgArrayPic(bean.getData()));
            mAdViewpagerUtil.setData(bean.getData());
            mAdViewpagerUtil.setOnAdItemClickListener(new AdViewpagerUtil.OnAdItemClickListener() {
                @Override
                public void onViewPagerItemClick(View clickView, int position, String url) {
                    PicsBean picsBean = (PicsBean) mAdViewpagerUtil.getDatas().get(position);
                    if ("zhibo".equals(picsBean.getType())) {
                        if (!CheckUtil.check(mContext, helper.getConvertView())) return;
                        HorizontalLiveActivity.startAction(mContext, picsBean.getLink(), 1);
                    } else if ("history".equals(picsBean.getType())) {
                        HorizontalHistoryActivity.startAction(mContext, picsBean.getLink(), 0);
                    } else if ("h5".equals(picsBean.getType())) {
                        ActisData dataBean = new ActisData();
                        dataBean.setPicture(picsBean.getPicture());
                        dataBean.setUrl(picsBean.getLink());
                        dataBean.setSignup(picsBean.getLink());
                        dataBean.setShareTitle(picsBean.getShareTitle());
                        dataBean.setShareDescript(picsBean.getShareDescript());
                        dataBean.setId(Integer.parseInt(picsBean.getId()));
                        dataBean.setIsCollect(picsBean.isCollect());
                        GongZuoFangActivity.startAction(mContext, dataBean);
                    }
                }
            });
            RxManagerUtil.post(AppConstant.RXTag.BIND_VIEWPAGER_UTIL, mAdViewpagerUtil);
        }
        helper.setOnClickListener(R.id.tv_workshops_crowd_funding, mOnNoDoubleClickListener);
        helper.setOnClickListener(R.id.tv_workshops_team, mOnNoDoubleClickListener);
//        helper.setOnClickListener(R.id.tv_workshops_lib, mOnNoDoubleClickListener);
    }

    private AdViewpagerUtil mAdViewpagerUtil;

    private OnNoDoubleClickListener mOnNoDoubleClickListener = new OnNoDoubleClickListener() {
        @Override
        protected void onNoDoubleClick(View view) {
            switch (view.getId()) {
                case R.id.tv_workshops_crowd_funding:
                    OkHttpClientManager.postAsync(ApiKey.CROWN_FUND_CHECK_CROWD_FUND_USER, MapUtils.getDefMap(true), new
                            NewRequestCallBack<StringDataBean>(StringDataBean.class) {
                        @Override
                        protected void onSuccess(StringDataBean result) {// -1 没有填写用户信息   1 已通过审核  0 审核中
                            int data = result.getIntData();
                            if (data == 1) {
                                startActivity(CrowdFundingProjectMsgActivity.class);
                            } else {
                                Bundle bundle = new Bundle();
                                bundle.putBoolean("hasPost", data == 0);
                                startActivity(CrowdFundingUserMsgActivity.class, bundle);
                            }
                        }
                    });
                    break;
                case R.id.tv_workshops_team:
                    
                    startActivity(GuideTeamCustomActivity.class);
                    break;
               /* case R.id.tv_workshops_lib:
                    startActivity(CourseLibraryActivity.class);
                    break;*/
            }
        }
    };


    private static class CrowdFundingUsedTemplateMultiItemType implements MultiItemTypeSupport<MultiItemBean> {
        @Override
        public int getLayoutId(int itemType) {
            switch (itemType) {
                case TYPE_HEAD_VIEW:
                    return R.layout.item_workshops_main_head_view;
                case TYPE_LIST_VIEW:
                    return R.layout.item_workshops_main_list_view;
            }
            return -1;
        }

        @Override
        public int getItemViewType(int position, MultiItemBean bean) {
            return bean.getItemType();
        }
    }
}
