package com.ziran.meiliao.ui.workshops.adapter;

import android.content.Context;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.ui.bean.FamousTeacherListBean;
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

public class WorkshopsMainTeacherAdapter extends CommonRecycleViewAdapter<FamousTeacherListBean> {

    public WorkshopsMainTeacherAdapter(Context context) {
        super(context, R.layout.item_workshops_main_teacher);
    }

    @Override
    protected float getWidthRatio() {
        return 0.93f;
    }

    @Override
    public void convert(ViewHolderHelper helper, FamousTeacherListBean bean, int position) {
        helper.setImageUrlTarget(R.id.iv_item_course_library_teacher_pic, bean.getPicture(), R.mipmap.ic_loading_square_small);
        helper.setText(R.id.tv_item_course_library_teacher_name, bean.getName());
        helper.setText(R.id.tv_item_course_library_teacher_des, bean.getIntro());
        helper.setText(R.id.tv_item_course_library_teacher_count, HtmlUtil.format("共%d个课程", bean.getCourseMembers()));
        helper.setVisible(R.id.view_line, false);
    }
}
