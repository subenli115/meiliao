package com.ziran.meiliao.ui.priavteclasses.adapter;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.RecListHelper;
import com.ziran.meiliao.ui.bean.SearchItemBean;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2018/1/20 16:58
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2018/1/20$
 * @updateDes ${TODO}
 */

public class SearchAllMultiItemType implements MultiItemTypeSupport<SearchItemBean>, AppConstant.SearchId {
    private int defItemType = -1;

    public SearchAllMultiItemType(int defItemType) {
        this.defItemType = defItemType;
    }

    public SearchAllMultiItemType() {
    }

    @Override
    public int getLayoutId(int itemType) {
        switch (itemType) {

            case RESULT_COURSE://course
                return R.layout.item_crowd_funding_choose_topic;
            case RESULT_TEAM://course
                return  R.layout.item_course_library_teacher;
            case RESULT_TEACHER://course
                return R.layout.item_course_library_teacher;
            case RESULT_XIANG_GUAN://activity
                return R.layout.item_search_xiang_guan_title;
            case RESULT_EMPTY://activity
                return R.layout.item_search_empty;

        }
        return -1;
    }

    @Override
    public int getItemViewType(int position, SearchItemBean bean) {
        int viewType = RecListHelper.getItemViewType(position, bean);
        if (defItemType != -1) {
            viewType = viewType == -1 ? defItemType : viewType;
        }
        return viewType;
    }
}
