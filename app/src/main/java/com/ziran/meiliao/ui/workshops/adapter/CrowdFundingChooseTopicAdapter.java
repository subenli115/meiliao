package com.ziran.meiliao.ui.workshops.adapter;

import android.content.Context;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.bean.SearchItemBean;
import com.ziran.meiliao.ui.priavteclasses.adapter.SearchAllMultiItemType;
import com.ziran.meiliao.utils.HtmlUtil;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/11/9 9:41
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/11/9$
 * @updateDes ${TODO}
 */

public class CrowdFundingChooseTopicAdapter extends MultiItemRecycleViewAdapter<SearchItemBean> implements AppConstant.SearchId {



    public CrowdFundingChooseTopicAdapter(Context context,int type) {
        super(context, new SearchAllMultiItemType(type));
    }

    @Override
    public void convert(ViewHolderHelper helper, SearchItemBean bean, int position) {
        switch (getItemViewType(position)) {
            case RESULT_TEAM:
            case RESULT_TEACHER:
                helper.setImageUrlTarget(R.id.iv_item_course_library_teacher_pic, bean.getPicture(), R.mipmap.ic_loading_square_small);
                helper.setText(R.id.tv_item_course_library_teacher_name, bean.getName());
                helper.setText(R.id.tv_item_course_library_teacher_des, bean.getIntro());
                helper.setText(R.id.tv_item_course_library_teacher_count, HtmlUtil.format("共%d个课程", bean.getCourseNumbers()));
                break;
            case RESULT_COURSE:
                helper.setText(R.id.tv_item_crowd_funding_choose_topic_title, bean.getTitle());
                helper.setText(R.id.tv_item_crowd_funding_choose_topic_profile, bean.getIntro());
                helper.setImageUrlTarget(R.id.iv_item_crowd_funding_choose_topic_cover, bean.getPicture(), R.mipmap.ic_loading_square);
                break;
//                helper.setImageUrlTarget(R.id.iv_item_course_library_teacher_pic, bean.getPicture(), R.mipmap.ic_loading_square_small);
//                helper.setText(R.id.tv_item_course_library_teacher_name, bean.getName());
//                helper.setText(R.id.tv_item_course_library_teacher_des, bean.getIntro());
//                helper.setText(R.id.tv_item_course_library_teacher_count, HtmlUtil.format("共%d个课程", bean.getCourseNumbers()));
//                break;
        }
    }

}
