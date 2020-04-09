package com.ziran.meiliao.ui.workshops.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonwidget.RoundImageView;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.ziran.meiliao.ui.bean.CourseLibraryTeamBean;
import com.ziran.meiliao.ui.bean.MultiItemBean;
import com.ziran.meiliao.ui.bean.TeamDetailBean;
import com.ziran.meiliao.utils.HtmlUtil;
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

public class CourseLibraryTeamDetailAdapter extends MultiItemRecycleViewAdapter<MultiItemBean> {
    public static final int TYPE_TEAM_LIST = 8881;
    public static final int TYPE_TEAM_IMAGE = 8887;
    public static final int TYPE_TEAM_TOP = 8882;
    public static final int TYPE_TEAM_TITLE = 8885;
    public static final int TYPE_TEAM_LEFT = 8883;
    public static final int TYPE_TEAM_TEXT = 8886;
    public static final int TYPE_TEAM_FOOTER_VIEW = 8884;

    public CourseLibraryTeamDetailAdapter(Context context) {
        super(context, new CourseLibraryTeamMultiItemType());
    }

    @Override
    public void convert(ViewHolderHelper helper, MultiItemBean bean, int position) {
        switch (getItemViewType(position)) {
            case TYPE_TEAM_TITLE:
                SimpleTextView simpleTextView = helper.getView(R.id.simpleTextView);
                simpleTextView.setTitle(bean.getTitle());
                break;
            case TYPE_TEAM_TEXT:
                helper.setText(R.id.tv_content,HtmlUtil.formatEm( bean.getTitle()));
                break;
            case TYPE_TEAM_FOOTER_VIEW:
                setFooter(helper, bean, position);
            case TYPE_TEAM_LIST:
                setList(helper, bean, position);
                break;
            case TYPE_TEAM_IMAGE:
                RoundImageView iv = helper.getView(R.id.iv_detail);
                ImageLoaderUtils.displayRatioTarger(mContext,iv, bean.getTitle(),R.mipmap.ic_loading_square_small);

                break;
            case TYPE_TEAM_TOP:
                TeamDetailBean.DataBean.MissionBuiltListBean data = EmptyUtils.parseObject(bean.getObj());
                helper.setImageUrlTarget(R.id.iv_head_ban,data.getPicture(),R.mipmap.ic_loading_square_big);
                helper.setText(R.id.tv_item_course_library_teacher_detail_team_title,data.getTitle());
                helper.setText(R.id.tv_people,data.getTargetMembers());
                helper.setText(R.id.tv_time,data.getTotalTime());
                helper.setText(R.id.tv_item_course_library_teacher_detail_team_title,data.getTitle());
                break;
            case TYPE_TEAM_LEFT:
                CourseLibraryTeamBean teamBean =EmptyUtils.parseObject(bean.getObj());
                helper.setImageUrlTarget(R.id.iv_item_course_library_teacher_pic,teamBean.getPicture(), R.mipmap.ic_loading_square_small);
                helper.setText(R.id.tv_item_course_library_teacher_name, teamBean.getName());
                helper.setText(R.id.tv_item_course_library_teacher_des, teamBean.getIntro());
                helper.setText(R.id.tv_item_course_library_teacher_count, HtmlUtil.format("共%d个课程", teamBean.getCourseNumbers()));
                break;
        }
    }

    private void setFooter(ViewHolderHelper helper, MultiItemBean bean, int position) {

    }

    private CourseLibraryTeamDetailTripAdapter mCourseLibraryTeamDetailTripAdapter;

    private void setList(ViewHolderHelper helper, MultiItemBean bean, int position) {
        if (mCourseLibraryTeamDetailTripAdapter == null) {
            mCourseLibraryTeamDetailTripAdapter = new CourseLibraryTeamDetailTripAdapter(mContext);
            RecyclerView recyclerView = (RecyclerView) helper.getConvertView();
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            recyclerView.setAdapter(mCourseLibraryTeamDetailTripAdapter);
        }
        if (mCourseLibraryTeamDetailTripAdapter.getSize() == 0) {
            mCourseLibraryTeamDetailTripAdapter.addAll(bean.getData());
        }
    }


    private static class CourseLibraryTeamMultiItemType implements MultiItemTypeSupport<MultiItemBean> {
        @Override
        public int getLayoutId(int itemType) {
            switch (itemType) {
                case TYPE_TEAM_TITLE:
                    return R.layout.item_team_detail_title;
                case TYPE_TEAM_TEXT:
                    return R.layout.item_team_detail_text;
                case TYPE_TEAM_LIST:
                    return R.layout.item_team_detail_list;
                case TYPE_TEAM_IMAGE:
                    return R.layout.item_team_image;
                case TYPE_TEAM_FOOTER_VIEW:
                    return R.layout.item_team_detail_footer;
                case TYPE_TEAM_TOP:
                    return R.layout.item_team_detail_top;
                case TYPE_TEAM_LEFT:
                    return R.layout.item_course_library_teacher;
            }
            return -1;
        }

        @Override
        public int getItemViewType(int position, MultiItemBean bean) {
            return bean.getItemType();
        }
    }
}
