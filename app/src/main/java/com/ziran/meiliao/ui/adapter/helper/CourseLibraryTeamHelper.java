package com.ziran.meiliao.ui.adapter.helper;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.ui.bean.CourseLibraryTeamBean;
import com.ziran.meiliao.ui.bean.SearchItemBean;
import com.ziran.meiliao.utils.HtmlUtil;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/11/29 15:08
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/11/29$
 * @updateDes ${TODO}
 */

public class CourseLibraryTeamHelper {
    public static void setTeam(ViewHolderHelper helper, Object bean, int position, boolean isTop) {
        if (bean instanceof SearchItemBean) {
            setTeam(helper, (SearchItemBean) bean, position, isTop);
            return;
        }
        CourseLibraryTeamBean data = EmptyUtils.parseObject(bean);
        helper.setText(R.id.tv_item_course_library_teacher_detail_team_title, data.getTitle());
        helper.setText(R.id.tv_item_course_library_teacher_detail_team_people, data.getTargetMembers());
        helper.setText(R.id.tv_item_course_library_teacher_detail_team_date, data.getTotalTime());

        if (isTop) {
            helper.setText(R.id.tv_item_workshops_main_team_price, HtmlUtil.format("¥%d", data.getOfficePrice()));
            helper.setImageUrlTarget(R.id.iv_item_course_library_teacher_detail_team_pic, data.getPicture(), R.mipmap
                    .ic_loading_square_big);
        } else {
            helper.setText(R.id.tv_item_course_library_teacher_detail_team_price, HtmlUtil.format("¥%d", data.getOfficePrice()));
            helper.setVisible(R.id.tv_item_course_library_teacher_detail_team_price, true);
            helper.setImageUrlTarget(R.id.iv_item_course_library_teacher_detail_team_pic, data.getPicture(), R.mipmap.ic_loading_rectangle);
        }
    }

    private static void setTeam(ViewHolderHelper helper, SearchItemBean data, int position, boolean isTop) {
        helper.setText(R.id.tv_item_course_library_teacher_detail_team_title, data.getTitle());
        helper.setText(R.id.tv_item_course_library_teacher_detail_team_people, data.getTargetMembers());
        helper.setText(R.id.tv_item_course_library_teacher_detail_team_date, data.getTotalTime());

        if (isTop) {
            helper.setText(R.id.tv_item_workshops_main_team_price, HtmlUtil.format("¥%d", data.getOfficePrice()));
            helper.setImageUrlTarget(R.id.iv_item_course_library_teacher_detail_team_pic, data.getPicture(), R.mipmap
                    .ic_loading_square_big);
        } else {
            helper.setText(R.id.tv_item_course_library_teacher_detail_team_price, HtmlUtil.format("¥%d", data.getOfficePrice()));
            helper.setImageUrlTarget(R.id.iv_item_course_library_teacher_detail_team_pic, data.getPicture(), R.mipmap.ic_loading_rectangle);
        }
    }
}
