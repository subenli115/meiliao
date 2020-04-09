package com.ziran.meiliao.ui.workshops.adapter;

import android.content.Context;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.ui.adapter.helper.CourseLibraryTeamHelper;
import com.ziran.meiliao.ui.bean.CourseLibraryTeamBean;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/11/9 9:41
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/11/9$
 * @updateDes ${TODO}
 */

public class WorkshopsMainTeamAdapter extends CommonRecycleViewAdapter<CourseLibraryTeamBean> {

    public WorkshopsMainTeamAdapter(Context context) {
        super(context, R.layout.item_workshops_main_team);
    }
    @Override
    protected float getWidthRatio() {
        return 0.93f;
    }


    @Override
    public void convert(ViewHolderHelper helper, CourseLibraryTeamBean bean, int position) {
        CourseLibraryTeamHelper.setTeam(helper, bean, position,false);
    }
}
