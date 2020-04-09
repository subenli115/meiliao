package com.ziran.meiliao.ui.workshops.adapter;

import android.content.Context;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.ziran.meiliao.ui.adapter.helper.CourseLibraryTeamHelper;
import com.ziran.meiliao.ui.bean.CourseLibraryTeamBean;
import com.ziran.meiliao.ui.bean.HeadData;
import com.ziran.meiliao.utils.RandomUtil;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/11/9 9:41
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/11/9$
 * @updateDes ${TODO}
 */

public class CourseLibraryTeamAdapter extends MultiItemRecycleViewAdapter<Object> {
    private static final int TYPE_TITLE = 8881;
    public static final int TYPE_TEAM_TOP = 8882;
    public static final int TYPE_TEAM_LEFT = 8883;


    public CourseLibraryTeamAdapter(Context context) {
        super(context, new CourseLibraryTeamMultiItemType());

    }

    @Override
    public void convert(ViewHolderHelper helper, Object bean, int position) {
        switch (getItemViewType(position)) {
            case TYPE_TITLE:
                HeadData dataBean = EmptyUtils.parseObject(bean);
                helper.setText(R.id.tv_item_main_home_title, dataBean.getTitle());
                helper.setVisible(R.id.view_top, dataBean.isShowDivler());

                break;
            case TYPE_TEAM_TOP:
                CourseLibraryTeamHelper.setTeam(helper, bean, position, true);
                break;
            case TYPE_TEAM_LEFT:
                CourseLibraryTeamHelper.setTeam(helper, bean, position, false);
                break;
        }
    }


    private static class CourseLibraryTeamMultiItemType implements MultiItemTypeSupport<Object> {
        private int maxRandom;

        public CourseLibraryTeamMultiItemType() {
            maxRandom = RandomUtil.getRandom().nextInt(2) + 1;
        }

        @Override
        public int getLayoutId(int itemType) {
            switch (itemType) {
                case TYPE_TITLE:
                    return R.layout.item_main_home_title;
                case TYPE_TEAM_TOP:
                    return R.layout.item_workshops_main_team_top;
                case TYPE_TEAM_LEFT:
                    return R.layout.item_course_library_teacher_detail_team;
            }
            return -1;
        }

        @Override
        public int getItemViewType(int position, Object bean) {
            if (bean instanceof HeadData) {
                return TYPE_TITLE;
            } else if (bean instanceof CourseLibraryTeamBean) {
                return position % maxRandom == 0 ? TYPE_TEAM_TOP : TYPE_TEAM_LEFT;
            }
            return 0;
        }
    }
}
