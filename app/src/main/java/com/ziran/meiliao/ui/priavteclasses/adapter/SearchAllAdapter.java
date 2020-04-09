package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.irecyclerview.WrapperAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.adapter.helper.CourseLibraryTeamHelper;
import com.ziran.meiliao.ui.base.RecListHelper;
import com.ziran.meiliao.ui.bean.SearchItemBean;
import com.ziran.meiliao.ui.workshops.widget.ProgressTipsView;
import com.ziran.meiliao.utils.HtmlUtil;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/5 13:43
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/5$
 * @updateDes ${TODO}
 */


public class SearchAllAdapter extends MultiItemRecycleViewAdapter<SearchItemBean> implements AppConstant.SearchId {


    public SearchAllAdapter(Context context) {
        super(context, new SearchAllMultiItemType());
    }

    @Override
    public void convert(ViewHolderHelper helper, SearchItemBean searchEntry, int position) {
        int viewType = getItemViewType(position);
        ImageView iv = null;
        switch (viewType) {
            case WrapperAdapter.TITLE:
                helper.setText(R.id.tv_search_title, searchEntry.getTitle());
            case RESULT_TEAM:
                CourseLibraryTeamHelper.setTeam(helper, searchEntry, position, false);
                break;
            case RESULT_CROWD_FUNDING:
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
                helper.setImageUrlTarget(R.id.iv_item_course_library_teacher_pic, searchEntry.getPicture(), R.mipmap
                        .ic_loading_square_small);
                helper.setText(R.id.tv_item_course_library_teacher_name, searchEntry.getName());
                helper.setText(R.id.tv_item_course_library_teacher_des, searchEntry.getIntro());
                helper.setText(R.id.tv_item_course_library_teacher_count, HtmlUtil.format("共%d个课程", searchEntry.getCourseNumbers()));
                break;
            case RESULT_ALBUM:
                iv = helper.getView(R.id.iv_me_collect_album_img);
                helper.setText(R.id.tv_me_collect_album_title, searchEntry.getTitle());
                if (searchEntry.getAuthor() != null) {
                    helper.setText(R.id.tv_me_collect_album_anchor, searchEntry.getName());
                }
                helper.setText(R.id.tv_me_collect_album_profile, searchEntry.getDescript());
                break;
            case RESULT_COURSE:
                iv = helper.getView(R.id.iv_sjk_tralier_img);
                helper.setText(R.id.tv_sjk_tralier_title, searchEntry.getTitle());
                if (searchEntry.getAuthor() != null) {
                    helper.setText(R.id.tv_sjk_tralier_name, searchEntry.getName());
                    helper.setText(R.id.tv_sjk_tralier_descript, String.valueOf(searchEntry.getAuthor().getDescript()));
                }
                helper.setText(R.id.tv_sjk_tralier_intro, searchEntry.getIntro());

                break;
            case RESULT_ACTIVITY:
                iv = helper.getView(R.id.iv_item_sjk_act_img);
                helper.setText(R.id.tv_item_sjk_act_title, searchEntry.getTitle());
                helper.setText(R.id.tv_item_sjk_act_place, String.valueOf(searchEntry.getAddress()));
                helper.setText(R.id.tv_item_sjk_act_date, String.format("%s 至 %s", searchEntry.getBeginTime(), searchEntry.getEndTime()));
                break;
        }
        if (iv != null) {
            ImageLoaderUtils.displayTager(mContext, iv, searchEntry.getPicture(), R.mipmap.ic_loading_rectangle);
        }
    }

    private static class SearchAllMultiItemType implements MultiItemTypeSupport<SearchItemBean> {

        @Override
        public int getLayoutId(int itemType) {
            switch (itemType) {
                case RESULT_ALBUM:  //album
                    return R.layout.item_me_collect_album;
                case RESULT_COURSE://course
                    return R.layout.item_main_sjk_trailer;
                case RESULT_ACTIVITY://activity
                    return R.layout.item_main_sjk_act_left;
                case RESULT_TEAM://activity
                    return R.layout.item_search_team_left;
                case RESULT_CROWD_FUNDING://activity
                    return R.layout.item_course_library_crowd_funding;
                case RESULT_TEACHER://activity
                    return R.layout.item_course_library_teacher;
                case RESULT_XIANG_GUAN://activity
                    return R.layout.item_search_xiang_guan_title;
                case RESULT_EMPTY://activity
                    return R.layout.item_search_empty;
                case WrapperAdapter.TITLE:
                    return R.layout.item_single_title_search;
            }
            return -1;
        }

        @Override
        public int getItemViewType(int position, SearchItemBean bean) {
            return RecListHelper.getItemViewType(position, bean);
        }
    }
}
