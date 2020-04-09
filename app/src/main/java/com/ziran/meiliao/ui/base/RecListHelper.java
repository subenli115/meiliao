package com.ziran.meiliao.ui.base;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.WrapperAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.bean.SearchItemBean;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2018/1/18 17:58
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2018/1/18$
 * @updateDes ${TODO}
 */

public class RecListHelper implements AppConstant.SearchId {
    private MultiItemRecycleViewAdapter mAdapter;

    public RecListHelper(MultiItemRecycleViewAdapter adapter) {
        mAdapter = adapter;
    }

    public static int getLayoutId(int itemType) {
        switch (itemType) {
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

    public static int getItemViewType(int position, SearchItemBean bean) {
        if (bean.isHead()) {
            return WrapperAdapter.TITLE;
        } else if (EmptyUtils.isEmpty(bean.getType())){
            return -1;
        }else{
            switch (bean.getType()) {
                case "album":
                    return RESULT_ALBUM;
                case "course":
                    return RESULT_COURSE;
                case "activity":
                    return RESULT_ACTIVITY;
                case "missionBuilts":
                    return RESULT_TEAM;
                case "crowdFunds":
                    return RESULT_CROWD_FUNDING;
                case "famousTeachers":
                    return RESULT_TEACHER;
                case "xiangGuan":
                    return RESULT_XIANG_GUAN;
                case "empty":
                    return RESULT_EMPTY;
            }
        }
        return -1;
    }
}
