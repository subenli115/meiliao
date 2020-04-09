package com.ziran.meiliao.ui.workshops.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonwidget.RoundImageView;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.support.SimpleOnItemClickListener;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.adapter.helper.CourseLibraryTeamHelper;
import com.ziran.meiliao.ui.base.RecListHelper;
import com.ziran.meiliao.ui.bean.ActisData;
import com.ziran.meiliao.ui.bean.AlbumListBean;
import com.ziran.meiliao.ui.bean.AuthorBean;
import com.ziran.meiliao.ui.bean.CourseLibraryTeamBean;
import com.ziran.meiliao.ui.bean.HeadData;
import com.ziran.meiliao.ui.bean.SJKSingeLiveData;
import com.ziran.meiliao.ui.bean.SearchItemBean;
import com.ziran.meiliao.ui.bean.SpecColumnData;
import com.ziran.meiliao.ui.priavteclasses.util.SJKLiveUtil;
import com.ziran.meiliao.ui.bean.HotCourseBean;
import com.ziran.meiliao.ui.workshops.widget.ProgressTipsView;
import com.ziran.meiliao.ui.workshops.widget.TitleListView;
import com.ziran.meiliao.utils.HtmlUtil;
import com.ziran.meiliao.utils.StringUtils;
import com.ziran.meiliao.widget.SimpleTextView;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/11/9 9:41
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/11/9$
 * @updateDes ${TODO}
 */

public class CourseLibraryTeacherDetailAdapter extends MultiItemRecycleViewAdapter<Object> implements AppConstant.SearchId {
    public static final int TYPE_HEAD_VIEW = 0x5210;
    public static final int TYPE_TEAM = 0x5211;
    public static final int TYPE_WORKSHOPS = 0x5212;
    public static final int TYPE_RELEVANT_ALBUM = 0x5213;//Relevant
    public static final int TYPE_RELEVANT_SUB = 0x5214;//Relevant
    public static final int TYPE_HOT_COURSE = 0x5215;     //热门课程

    public CourseLibraryTeacherDetailAdapter(Context context) {
        super(context, new CourseLibraryTeacherDetailMultiItemType());

    }

    @Override
    public void convert(ViewHolderHelper helper, Object bean, int position) {
        SearchItemBean searchEntry = null;
        ImageView iv = null;
        switch (getItemViewType(position)) {
            case TYPE_TITLE:
                setTitle(helper, bean, position);
                break;
            case TYPE_HEAD_VIEW:
                setHeadView(helper, bean, position);
                break;
            case TYPE_TEAM:
                CourseLibraryTeamHelper.setTeam(helper, bean, position, false);
                break;
            case TYPE_WORKSHOPS:
                setWorkshops(helper, bean, position);
                break;
            case TYPE_RELEVANT_ALBUM:
            case RESULT_ALBUM:
                setAlbum(helper, bean, position);
                break;
            case TYPE_RELEVANT_SUB:
                setRelevantSub(helper, bean, position);
                break;
            case TYPE_HOT_COURSE:
                setHotCourse(helper, bean, position);
                break;
            case RESULT_TEAM:
                CourseLibraryTeamHelper.setTeam(helper, bean, position, false);
                break;
            case RESULT_CROWD_FUNDING:
                searchEntry = (SearchItemBean) bean;
                helper.setVisible(R.id.top_line, false);
                helper.setImageUrlTarget(R.id.iv_item_workshops_main_crowd_funding_pic, searchEntry.getPicture(), R.mipmap
                        .ic_loading_square_big);
                helper.setText(R.id.tv_item_workshops_main_crowd_funding_title, searchEntry.getTitle());
                helper.setText(R.id.tv_item_workshops_main_crowd_funding_price, HtmlUtil.format("¥%d", searchEntry.getAvgPrice()));
                helper.setText(R.id.tv_item_workshops_main_crowd_funding_people, String.valueOf(searchEntry.getTargetMembers()));
                helper.setText(R.id.tv_item_workshops_main_crowd_funding_time, searchEntry.getTotalTime());
                ProgressTipsView progressTipsView = helper.getView(R.id.progressTipsView);
                progressTipsView.setParms(searchEntry.getSupportMembers(), searchEntry.getLeftTime(), searchEntry.getTargetMembers());
                break;
            case RESULT_TEACHER:
                searchEntry = (SearchItemBean) bean;
                helper.setImageUrlTarget(R.id.iv_item_course_library_teacher_pic, searchEntry.getPicture(), R.mipmap
                        .ic_loading_square_small);
                helper.setText(R.id.tv_item_course_library_teacher_name, searchEntry.getName());
                helper.setText(R.id.tv_item_course_library_teacher_des, searchEntry.getIntro());
                helper.setText(R.id.tv_item_course_library_teacher_count, HtmlUtil.format("共%d个课程", searchEntry.getCourseNumbers()));
                break;
        }
        if (iv != null) {
            ImageLoaderUtils.displayTager(mContext, iv, searchEntry.getPicture(), R.mipmap.ic_loading_rectangle);
        }
    }

    private void setTitle(ViewHolderHelper helper, Object bean, int position) {
        HeadData data = EmptyUtils.parseObject(bean);
        SimpleTextView simpleTextView = helper.getView(R.id.simpleTextView);
        simpleTextView.setTitle(data.getTitle());
    }

    private void setHeadView(ViewHolderHelper helper, Object bean, int position) {
        AuthorBean data = EmptyUtils.parseObject(bean);
        helper.setText(R.id.tv_item_course_library_teacher_detail_head_des, data.getTeaIntro());
        RoundImageView iv = helper.getView(R.id.iv_item_course_library_teacher_detail_head_pic);
        ImageLoaderUtils.displayRatioTarger(mContext, iv, data.getHeadImg(), R.mipmap.ic_loading_square_big);
    }


    private void setWorkshops(ViewHolderHelper helper, Object bean, int position) {
        ActisData data = EmptyUtils.parseObject(bean);
        helper.setText(R.id.tv_item_sjk_act_title, data.getTitle());
        helper.setText(R.id.tv_item_sjk_act_date, StringUtils.format("%s 至 %s", data.getBeginTime(), data.getEndTime()));
        helper.setText(R.id.tv_item_sjk_act_place, data.getAddress());
        helper.setImageUrl(R.id.iv_item_sjk_act_img, data.getPicture());
    }

    private void setAlbum(ViewHolderHelper helper, Object bean, int position) {
        AlbumListBean albumListBean = EmptyUtils.parseObject(bean);
        helper.setImageUrlTarget(R.id.iv_jyg_recommend_img, albumListBean.getPicture(),R.mipmap.ic_loading_square_small);

        helper.setText(R.id.tv_jyg_recommend_title, albumListBean.getTitle());
        helper.setText(R.id.tv_jyg_recommend_anchor, albumListBean.getAuthor().getName());
        helper.setText(R.id.tv_jyg_recommend_profile, albumListBean.getDetail());
        helper.setText(R.id.tv_jyg_recommend_lable, albumListBean.getVip());
        helper.setVisible(R.id.tv_jyg_recommend_lable, EmptyUtils.isNotEmpty(albumListBean.getVip()));
        helper.setText(R.id.tv_jyg_listen_count, StringUtils.format(StringUtils.getText(R.string.lister_count), albumListBean
                .getListenCount()));
    }

    private void setRelevantSub(ViewHolderHelper holder, Object bean, int position) {
        SpecColumnData specColumnData = EmptyUtils.parseObject(bean);
        ImageView iv = holder.getView(R.id.iv_item_sjk_recommend_pic);
        ImageLoaderUtils.displayTager(mContext, iv, specColumnData.getPic());
        holder.setText(R.id.tv_item_sjk_recommend_title, specColumnData.getTitle());
        holder.setText(R.id.tv_item_sjk_recommend_des, specColumnData.getDescript());
        if (EmptyUtils.isNotEmpty(specColumnData.getTag())) {
            holder.setVisible(R.id.tv_item_sjk_recommend_tag, true);
            holder.setText(R.id.tv_item_sjk_recommend_tag, specColumnData.getTag());
        } else {
            holder.setVisible(R.id.tv_item_sjk_recommend_tag, false);
        }

    }

    private HotCourseAdapter mHotCourseAdapter;

    private void setHotCourse(ViewHolderHelper helper, Object bean, int position) {
        HotCourseBean data = EmptyUtils.parseObject(bean);
        TitleListView titleListView = helper.getView(R.id.titleListView);
        if (mHotCourseAdapter == null) {
            mHotCourseAdapter = new HotCourseAdapter(mContext);
            titleListView.setMoreTitleVis(View.GONE);
            titleListView.setTvTitle(data.getTitle());
            titleListView.setTvTitleIcon(R.mipmap.workshops_ic_hot);
            mHotCourseAdapter.setHeadCount(0);
            mHotCourseAdapter.setOnItemClickListener(new SimpleOnItemClickListener() {
                @Override
                public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                    if (o instanceof SJKSingeLiveData) {
                        SJKSingeLiveData sjkSingeLiveData = (SJKSingeLiveData) o;
                        SJKLiveUtil.startActivity(mContext, sjkSingeLiveData.getId(), sjkSingeLiveData.getTag(), sjkSingeLiveData
                                .getStatus());
                    }
                }
            });
            titleListView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
            titleListView.setAdapter(mHotCourseAdapter);
        }
        if (mHotCourseAdapter.getSize() == 0) {
            mHotCourseAdapter.replaceAll(data.getData());
        }
    }

    public void onRefresh() {
        if (mHotCourseAdapter != null) {
            mHotCourseAdapter.clear();
        }
    }

    private static class CourseLibraryTeacherDetailMultiItemType implements MultiItemTypeSupport<Object> {
        @Override
        public int getLayoutId(int itemType) {
            switch (itemType) {
                case TYPE_HEAD_VIEW:
                    return R.layout.item_course_library_teacher_detail_head_view;
                case TYPE_TITLE:
                    return R.layout.item_course_library_teacher_detail_title;
                case TYPE_TEAM:
                    return R.layout.item_course_library_teacher_detail_team;
                case TYPE_WORKSHOPS:
                    return R.layout.item_main_sjk_act_left;
                case TYPE_RELEVANT_ALBUM:
                case RESULT_ALBUM:
                    return R.layout.item_main_jyg_recommend;
                case TYPE_RELEVANT_SUB:
                    return R.layout.item_sjk_recommend_zhuanlan;
                case TYPE_HOT_COURSE:
                case RESULT_COURSE:
                    return R.layout.item_course_library_teacher_detail_hot_course;
            }
            return RecListHelper.getLayoutId(itemType);
        }

        @Override
        public int getItemViewType(int position, Object bean) {
            if (bean instanceof HeadData) {
                return TYPE_TITLE;
            } else if (bean instanceof AuthorBean) {
                return TYPE_HEAD_VIEW;
            } else if (bean instanceof CourseLibraryTeamBean) {
                return TYPE_TEAM;
            } else if (bean instanceof ActisData) {
                return TYPE_WORKSHOPS;
            } else if (bean instanceof SpecColumnData) {
                return TYPE_RELEVANT_SUB;
            } else if (bean instanceof AlbumListBean) {
                return TYPE_RELEVANT_ALBUM;
            } else if (bean instanceof HotCourseBean) {
                return TYPE_HOT_COURSE;
            } else if (bean instanceof SearchItemBean) {
                return RecListHelper.getItemViewType(position, (SearchItemBean) bean);
            }
            return 0;
        }
    }
}
